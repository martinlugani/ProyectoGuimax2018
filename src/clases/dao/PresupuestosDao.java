package clases.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

import clases.coneccion.ConnectionSingleton;
import clases.principales.cliente.Cliente;
import clases.principales.cliente.Contacto;
import clases.principales.pedido.LineaPresupuesto;
import clases.principales.pedido.Pedido;
import clases.principales.pedido.Presupuesto;
import clases.principales.productos.LineaMaterial;
import clases.principales.productos.Material;
import clases.principales.productos.Producto;
import clases.principales.seguridad.OperacionEgreso;

public class PresupuestosDao {
	private Connection con;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet res;
	private ResultSet res1;

	public int selectCountPresup() {
		try {
			con = ConnectionSingleton.getConnection();
			stm = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
			res = stm.executeQuery("SELECT * FROM presupuesto ORDER by id_presupuesto DESC LIMIT 1");
			res.first();
			return res.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (res != null)
					res.close();
				if (stm != null)
					stm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}

	}
			
			
			
			
			
		

	public Collection<Presupuesto> select() {
		try {
			ClienteDao daocli = new ClienteDao();
			Vector<Cliente> clientes = (Vector<Cliente>) daocli.selectCliente();
			ContactactoDao name = new ContactactoDao();
			Vector<Contacto> contactos = (Vector<Contacto>) name.selectCliente();
			con = ConnectionSingleton.getConnection();
			stm = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
			res = stm.executeQuery("SELECT * FROM `linea_presupuesto` ORDER BY `id_presupuesto` ASC;");
			Vector<LineaPresupuesto> l = new Vector<LineaPresupuesto>();
			res.beforeFirst();
			int co = 1;
			while (res.next()) {
				System.out.println(res.getMetaData().getColumnName(1)+"  "+res.getInt(1));
				System.out.println(res.getMetaData().getColumnName(2)+"  "+res.getInt(2));
				System.out.println(res.getMetaData().getColumnName(3)+"  "+res.getInt(3));
				System.out.println(res.getMetaData().getColumnName(4)+"  "+res.getInt(4));
			
				LineaPresupuesto lin = new LineaPresupuesto();
				lin.setIdLinea(res.getInt(1));
				lin.setIdPresupuesto(res.getInt(2));
				lin.setCantidad(res.getInt(3));
				Producto p = obtieneProductoPedido(res.getInt(4), res.getInt(2));
				p.setCodigoProducto(res.getInt(4));
				lin.setProducto(p);
				l.add(lin);
				co++;
//				System.out.println("PresupuestosDAo  linea  id pres "+lin.getIdPresupuesto());
			}
//			System.out.println(" presupudao contador lineas  " + co);
			res.close();

			Vector<Producto> todosProd = new Vector<Producto>();
			res = stm.executeQuery("SELECT * FROM presupuesto;");
			Collection<Presupuesto> pres = new Vector<>();
			res.beforeFirst();
			while (res.next()) {
				
				Presupuesto p = new Presupuesto();
				p.setIdPresupuesto(res.getInt(1));
				p.setFechaAlta(res.getDate(2));
				p.setOperariosFabricacion(res.getInt(3));
				p.setFlagBaja(res.getInt(4));
				p.setDias(res.getInt(5));
				p.setHora(res.getTime(6));
				p.setPorcentage(res.getInt(7));
				p.setCosto(res.getFloat(8));
//				System.out.println("Presupuestos dao Id  " + p.getIdPresupuesto());
				Vector<LineaPresupuesto> lineas = new Vector<LineaPresupuesto>();
				for (LineaPresupuesto lineaPresupuesto : l) {
//					System.out.println("presDAo Linea "+lineaPresupuesto.getIdLinea()+"  presupuesto "+lineaPresupuesto.getIdPresupuesto()+
//							" presupuesto "+ p.getIdPresupuesto());
					if (lineaPresupuesto.getIdPresupuesto() == p.getIdPresupuesto()) {
						lineas.add(lineaPresupuesto);
//						System.out.println("presDAo Linea "+lineaPresupuesto.getIdLinea());
					}

				}

				p.setContacto(selectUnContacto(p, contactos));
				for (Cliente cliente : clientes) {
					if (cliente.getIdEmpresa() == p.getContacto().getIdCliente()) {
						p.setCliente(cliente);
					}
				}

				p.setLineaPresupueso(lineas);
				pres.add(p);
			}

			return pres;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (res != null)
					res.close();
				if (stm != null)
					stm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}

	}

	private Producto obtieneProductoPedido(int byte1, int idpres) {
		// TODO Auto-generated method stub
		String consulta = "SELECT p.idproducto, p.modelo ,p.voltamperios, p.horas_hombre ,p. lugarUtilizacion "
				+ ",p.falgbaja , p.precio_hora_hombre , p.id_categoria , l.cantidad FROM producto as p, linea_presupuesto "
				+ "as l, presupuesto as r WHERE p.idproducto = l.id_producto and l.id_presupuesto = r.id_presupuesto "
				+ "and p.idproducto = " + byte1 + " and l.id_presupuesto = " + idpres;
		Vector<Vector<Object>> produc = ConsultasEspeciales.selectString(consulta);
		Producto name = new Producto();

		name.setCodigoProducto(Integer.parseInt(String.valueOf(produc.get(0).get(0))));
		name.setModelo(String.valueOf(produc.get(0).get(1)));
		// System.out.println(produc.get(0).get(5).toString());
		name.setVoltamperios((int) produc.get(0).get(2));
		float horas = (float) Float.valueOf(String.valueOf(produc.get(0).get(3)));
		name.setHorasHombre((int) horas);
		name.setLugarUtilizacion(String.valueOf(produc.get(0).get(4)));
		int flag = Integer.valueOf(String.valueOf(produc.get(0).get(8)));
		name.setFlagBaja(flag);
		name.setPrecioHoraHombre(Float.parseFloat(String.valueOf(produc.get(0).get(6))));
		consulta = "SELECT m.*, l.cantidad_material FROM producto as p , linea_material as l , material as m WHERE p.idproducto = l.idproducto AND l.idmaterial = m.idmaterial AND p.idproducto = "
				+ byte1;
		Vector<Vector<Object>> materiales = ConsultasEspeciales.selectString(consulta);
		Vector<Material> matAgregar = new Vector<Material>();
		for (int f = 0; f < materiales.size(); f++) {
			int cantida = 0;
			// System.out.println(materiales.get(f));
			Material material = new Material();
			material.setIdMaterial(Integer.valueOf(String.valueOf(materiales.get(f).get(0))));
			material.setDescripcion(String.valueOf(materiales.get(f).get(1)));
			material.setStockActual(Integer.valueOf(String.valueOf(materiales.get(f).get(2))));
			material.setPuntoPedido(Integer.valueOf(String.valueOf(materiales.get(f).get(3))));
			material.setUltimoPrecio(Float.valueOf(String.valueOf(materiales.get(f).get(4))));
			material.setPrecioActual(Float.valueOf(String.valueOf(materiales.get(f).get(5))));
			material.setFlagBaja(Integer.valueOf(String.valueOf(materiales.get(f).get(7))));
//			System.out.println(material.toString());

			cantida = Integer.valueOf(String.valueOf(materiales.get(f).get(8)));
			
			matAgregar.add(material);
			LineaMaterial	 linea = new LineaMaterial();
			linea.setProducto(byte1);
			linea.setCantidadNecesaria(cantida);
			linea.setMaterial(material.getIdMaterial());
			name.addItem(linea);
		}
		name.setLineas(matAgregar);
		return name;
	}

	private Contacto selectUnContacto(Presupuesto p, Vector<Contacto> contactos) {
		try {
			con = ConnectionSingleton.getConnection();
			stm = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
			res1 = stm.executeQuery(
					"SELECT * FROM `operacionautoriza` WHERE idpresupuesto = '" + p.getIdPresupuesto() + "';");
			res1.first();
			for (Contacto contacto : contactos) {

				res1.first();
				if (res1.getInt("idcontacto") == contacto.getIdpersona()) {
					return contacto;
				}
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (res1 != null)
					res1.close();
				if (stm != null)
					stm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}

	}

	public boolean insertPresupuesto(Presupuesto presupuestoNuevo) {
		try {
			con = ConnectionSingleton.getConnection();
			con.setAutoCommit(false);
			String insert = "INSERT INTO `presupuesto` (`id_presupuesto`, `fecha_alta`, `operariosfabricacion`, `falgbaja`, `cantidad_dias`,"
					+ " `hora_pres`, `porcentage_beneficio`, `costo_presupuesto`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);";
			pstm = con.prepareStatement(insert);
			pstm.setInt(1, presupuestoNuevo.getIdPresupuesto());
			pstm.setDate(2, presupuestoNuevo.getFechaAlta());
			pstm.setFloat(3, presupuestoNuevo.getOperariosFabricacion());
			pstm.setInt(4, 1);
			pstm.setInt(5, presupuestoNuevo.getDias());
			pstm.setTime(6, presupuestoNuevo.getHora());
			pstm.setInt(7, presupuestoNuevo.getPorcentage());
			pstm.setFloat(8, presupuestoNuevo.getCosto());
			if (pstm.executeUpdate() != 1) {
				con.commit();
				return false;
			}
			con.setAutoCommit(false);
			insert = "INSERT INTO  `linea_presupuesto` (`id_linea`, `id_presupuesto`, `cantidad`, `id_producto`) VALUES ( ";
			for (int i = 0; i < presupuestoNuevo.getLineaPresupueso().size(); i++) {
				insert += presupuestoNuevo.getLineaPresupueso().get(i).getIdLinea() + " , "
						+ presupuestoNuevo.getIdPresupuesto() + " , "
						+ presupuestoNuevo.getLineaPresupueso().get(i).getCantidad() + " , "
						+ presupuestoNuevo.getLineaPresupueso().get(i).getProducto().getCodigoProducto();
				if (i != presupuestoNuevo.getLineaPresupueso().size() - 1) {
					insert += ") , (";
				} else {
					insert += ");";
				}

			}
			pstm = con.prepareStatement(insert);

			if (pstm.executeUpdate() < 1) {

				return false;
			}
			con.commit();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (con != null)
					con.rollback();
				if (pstm != null)
					pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}

	}

	public boolean insertarOperacion(OperacionEgreso name) {
		try {
			// INSERT INTO `operacionautoriza` (`idoperacion_auto`, `idpedido`,
			// `idpresupuesto`, `fecha`, `idcontacto`) VALUES (NULL, NULL, '11',
			// '2018-10-08', '3')
			con = ConnectionSingleton.getConnection();
			String sql = "INSERT INTO `operacionautoriza`( `idoperacion_auto`, `idpedido`, `idpresupuesto`, `fecha`, `idcontacto`) VALUES (NULL, NULL,";

			sql += "'" + String.valueOf(name.getIdPresupuesto()) + "', ";

			sql += "?, ";
			sql += "'" + name.getIdcontacto() + "'); ";

			con.setAutoCommit(false);
			pstm = con.prepareStatement(sql);
			pstm.setDate(1, name.getFecha());
			if (pstm.executeUpdate() == 1) {
				con.commit();
				return true;
			}
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (con != null)
					con.rollback();
				if (pstm != null)
					pstm.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}

	}
}
