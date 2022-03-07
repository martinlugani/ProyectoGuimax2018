package clases.coneccion;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Vector;

import clases.dao.ClienteDao;
import clases.dao.ConsultasEspeciales;
import clases.dao.ContactactoDao;
import clases.dao.LineaMaterialDao;
import clases.dao.MaterialesDao;
import clases.dao.PedidoDao;
import clases.dao.PresupuestosDao;
import clases.dao.ProductoDao;
import clases.dao.TipoMaterialDao;
import clases.dao.UsuariosDao;
import clases.principales.cliente.Cliente;
import clases.principales.cliente.Contacto;
import clases.principales.pedido.Pedido;
import clases.principales.pedido.Presupuesto;
import clases.principales.productos.Caracteristica;
import clases.principales.productos.LineaMaterial;
import clases.principales.productos.Material;
import clases.principales.productos.Producto;
import clases.principales.productos.TipoMaterial;
import clases.principales.seguridad.OperacionEgreso;
import clases.principales.seguridad.Usuario;

public class Fachada {
	public Vector<Presupuesto> selectPresupuesto() {
		PresupuestosDao name = new PresupuestosDao();
		return (Vector<Presupuesto>) name.select();

	}

	public Vector<Caracteristica> selectCaracteristica() {
		TipoMaterialDao dao = new TipoMaterialDao();
		Vector<Caracteristica> carct = (Vector<Caracteristica>) dao.obtenerCaracteristica();

		if (carct != null) {
			return carct;

		}

		return null;
	}

	public Collection<Cliente> selectCliente() {
		ClienteDao dao = new ClienteDao();
		return dao.selectCliente();

	}

	public Collection<Producto> selectProducto() {
		ProductoDao name = new ProductoDao();
		return name.selectProducto();

	}

	/**
	 * Solicita la carga de materiales con todas las caracteristicas seteadas
	 * 
	 * @return collection Una coleccion con todos los materiales seteados
	 */
	public Collection<Material> selectMaterialSeteado() {
		Collection<Material> mates = selectMaterial();
		Collection<Caracteristica> caracteristicas = selectCaracteristica();
		Collection<TipoMaterial> tipoMaterials = selectTiposMateriales();

		return null;
	}

	public Vector<TipoMaterial> selectTiposMateriales() {
		TipoMaterialDao dao = new TipoMaterialDao();
		Vector<TipoMaterial> tipos = (Vector<TipoMaterial>) dao.select();
		return tipos;
	}

	public Collection<Material> selectMaterial() {
		MaterialesDao dao = new MaterialesDao();

		return (Vector<Material>) dao.selectMaterial();
	}

	public Vector<Usuario> selectUsuario() {
		UsuariosDao dao = new UsuariosDao();
		return (Vector<Usuario>) dao.select();
	}

	public Vector<Vector<String>> selecthsHombre() {
		ProductoDao dao = new ProductoDao();

		return dao.selectHsHombre();
	}

	public boolean selectPersonaId(int id) {
		UsuariosDao name = new UsuariosDao();
		return name.selectId(id);
	}

	public Vector<Vector<String>> selectGenerica(Vector<String> tablas, Vector<String> columnas,
			Vector<String> clausula, Vector<String> busqueda) {
		ConsultasEspeciales name = new ConsultasEspeciales();
		return name.select(tablas, columnas, clausula, busqueda);
	}

	public boolean insertPresupuesto(Presupuesto presupuestoNuevo) {
		PresupuestosDao dao = new PresupuestosDao();
		return dao.insertPresupuesto(presupuestoNuevo);
	}

	public boolean insertTipoMaterial(TipoMaterial mat) {
		TipoMaterialDao dao = new TipoMaterialDao();
		Vector<TipoMaterial> tipos = selectTiposMateriales();
		boolean encontro = false;
		if (tipos != null) {
			for (TipoMaterial tipoMaterial : tipos) {
				if (tipoMaterial.equals(mat)) {
					encontro = true;
				}
			}
		}
		if (!encontro) {
			Vector<Caracteristica> caras = selectCaracteristica();
			boolean encontroCaracteristica = false;
			for (Caracteristica caracteristica : caras) {
				if (mat.getCaracteristica().equals(caracteristica)) {
					mat.setCaracteristica(caracteristica);
					if (dao.ingresarTipoNuevo(mat)) {
						return true;
					}
				}
			}

		}

		return false;

	}

	public boolean insertMaterial(Material mate) {
		MaterialesDao dao = new MaterialesDao();
		Vector<Material> mates = (Vector<Material>) dao.selectMaterial();
		boolean encontro = false;
		if (mates != null) {

			for (Material material : mates) {
				if (material.equals(mate)) {
					encontro = true;
					if (material.getFlagBaja() == 0) {
						mate.setIdMaterial(material.getIdMaterial());
						if (dao.upgradeMaterial(material, mate)) {
							return true;
						}

					}
				}

			}
		}
		if (!encontro) {

			if (dao.isertMaterial(mate)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Ingresa Un producto en la bace de datos
	 * 
	 * @param Recive un valor de tipo <b>Producto</b>
	 * @return <b>true</b> si el producto es insertado correctamente
	 * 
	 */

	public boolean insertProducto(Producto producto) {
		ProductoDao pdao = new ProductoDao();
		if (pdao.insert(producto)) {
			LineaMaterialDao liDao = new LineaMaterialDao();
			Vector<LineaMaterial> lineas = producto.getItems();
			liDao.insert(lineas);
		}
		return false;

	}

	/**
	 * guarda los datosde un nuevo usuario respetando que no se ingrese reprtido ni
	 * nombre apellido, ni dni, ni nombreUsuario,
	 * 
	 * @param usu
	 * @return -1 si el apellido y el nombre se retiten, -2 para dni igual 0 para
	 *         igual nombre de usuario, 1 faborable, 2 fallo la carga
	 */
	public int insertUsuario(Usuario usu) {// transformar en integer

		UsuariosDao usuariosDao = new UsuariosDao();
		Vector<Usuario> usuarios = (Vector<Usuario>) usuariosDao.select();
		if (usuarios != null) {
			for (Usuario usuario : usuarios) {
				if (usu.getApellido().equalsIgnoreCase(usuario.getApellido())
						&& usu.getNombre().equalsIgnoreCase(usuario.getNombre())) {
					return -1;
				}

				if (usu.getDni() == usuario.getDni()) {
					return -2;
				}
				if (usu.getNombreUsuario().equalsIgnoreCase((usuario.getNombreUsuario()))) {
					return 0;
				}
			}
		}

		if (usuariosDao.insertUsuario(usu)) {
			return 1;
		}
		return 2;
	}

	/**
	 * Permite loguear un usuario
	 * 
	 * @param usu
	 * @return 1 para logueo faborable -1 para error de nombre de usuario y 0 para
	 *         error de contrasenia
	 */
	public int inicioSecion(Usuario usu) {
		UsuariosDao dao = new UsuariosDao();
		Vector<Usuario> usus = (Vector<Usuario>) dao.select();

		if (usus != null) {
			dao.setContrasenia(false);
			int encontro = -1;
			for (Usuario usuario : usus) {
				if (usuario.getNombreUsuario().equals(usu.getNombreUsuario())) {
					encontro = 0;
					if (usuario.getIntentos() > 0 && usuario.getFlagBaja() != 0) {
//						realizar un update de cantidad de intentos
						if (usuario.getContrasenia().equals(usu.getContrasenia())) {
							if (usuario.getIntentos() < 3) {

								usu.setIntentos(3);

								dao.updateUsuario(usuario, usu);

							}

							usu.setIntentos(3);
							usu.setFechaUltimoLogin(Date.valueOf(LocalDate.now()));
							dao.updateUsuario(usuario, usu);
							encontro = 1;
							return encontro;
						} else {

							// dao.setContrasenia(false);

							usu.setIntentos(usuario.getIntentos() - 1);
							dao.updateUsuario(usuario, usu);
							usuario.setIntentos(usuario.getIntentos() - 1);

						}
					} else {
						return 2;
					}
//					
					return 0;
				}

			}

			return -1;
		} else {
			ResourceBundle rb = ResourceBundle.getBundle("jdbc");
			String nomb = rb.getString("administrador");
			String cont = rb.getString("contrasenia");
			if (usu.getContrasenia().equals(cont) && usu.getNombreUsuario().equals(nomb)) {
				System.out.println("Funciono");
				return 1;

			}
		}
		return 0;
	}

	public boolean updateMaterial(Material duplicado) {
		MaterialesDao e = new MaterialesDao();
		Vector<Material> materiales = (Vector<Material>) selectMaterial();
		for (Material material : materiales) {
			if (duplicado.getIdMaterial() == material.getIdMaterial()) {
				return e.upgradeMaterial(material, duplicado);
			}
		}

		return false;

	}

	public boolean updateUsuario(Usuario usua) {
		// TODO Auto-generated method stub
		UsuariosDao dao = new UsuariosDao();
		Vector<Usuario> usuarios = (Vector<Usuario>) dao.select();
		Usuario duplicado = null;
		for (Usuario usuario : usuarios) {
			if (usua.getIdpersona() == usuario.getIdpersona()) {
				duplicado = usuario;
			}
		}

		return dao.updateUsuario(duplicado, usua);
	}

	public boolean eliminarFisica(Material material) {
		MaterialesDao m = new MaterialesDao();
		return m.delete(material);
	}

	public int getUltimoPres() {
		PresupuestosDao pre = new PresupuestosDao();
		return pre.selectCountPresup();
	}

	public int cuentaIdPersona() {
		UsuariosDao dao = new UsuariosDao();
		return dao.selectCount();
	}

	public boolean cargaCaracteristica(Caracteristica nueva) {
		TipoMaterialDao dao = new TipoMaterialDao();
		Vector<Caracteristica> caras = (Vector<Caracteristica>) dao.obtenerCaracteristica();
		boolean existe = false;
		if (caras != null) {

			for (Caracteristica caracteristica : caras) {
				if (caracteristica.equals(nueva)) {
					existe = true;
				}
			}

		}
		if (!existe) {
			dao.insertCaracteristica(nueva);
			System.out.println("No existe");
		}
		return existe;

	}

	public void insertaAutoriza(OperacionEgreso name) {
		PresupuestosDao pre = new PresupuestosDao();
		pre.insertarOperacion(name);
	}

	public boolean selectPresVencidos() {
		Vector<Vector<Object>> vector = ConsultasEspeciales.selectString(
				"SELECT * from presupuesto WHERE DATEDIFF(CURDATE() , fecha_alta) > 15 and falgbaja = 1; ");
		if (vector != null && vector.size() > 0) {
			return true;
		}

		return false;
	}

	public boolean eliminarpresVencidos() {
		return ConsultasEspeciales.consultas(
				"UPDATE `presupuesto` set `presupuesto`.`falgbaja` = '0' WHERE DATEDIFF(CURDATE(), `presupuesto`.`fecha_alta`) > '15' and `presupuesto`.`falgbaja` = '1' ;");

	}

	public boolean eliminaPresupuesto(int idPres) {
		return ConsultasEspeciales.consultas(
				"UPDATE `presupuesto` SET `falgbaja` = 0 WHERE `presupuesto`.`id_presupuesto` =" + idPres + ";");
	}

	public boolean actualizaPresupuesto(Presupuesto presu) {
		return ConsultasEspeciales.consultas("UPDATE `presupuesto` SET `costo_presupuesto` = '"
				+ presu.autoCalculaTotal() + "' , `fecha_alta` = CURRENT_DATE WHERE `presupuesto`.`id_presupuesto` = '"
				+ presu.getIdPresupuesto() + "';");

	}

	public boolean generaPedido(Pedido name, Usuario usuario) {
		ConsultasEspeciales.consultas(
				"INSERT INTO `pedido` (`idpedido`, `fecha_alta`, `fecha_entrega`, `total`, `presupuesto_id_presupuesto`) VALUES (NULL, CURRENT_DATE(), '"
						+ name.getFechaEntrega() + "', '" + name.getSumaTotal() + "', '"

						+ name.getPresupuesto().getIdPresupuesto() + "');");
		Vector<Vector<Object>> consulta = ConsultasEspeciales
				.selectString("SELECT * FROM `pedido` ORDER BY `idpedido` DESC");
		name.setIdPedido(Integer.valueOf(consulta.get(0).get(0).toString()));
		System.out.println(consulta.get(0).get(0).toString());
		ConsultasEspeciales.consultas(
				"INSERT INTO `operacionautoriza` (`idoperacion_auto`, `idpedido`, `idpresupuesto`, `fecha`, `idcontacto`) VALUES (NULL, '"
						+ name.getIdPedido() + "', NULL, CURRENT_DATE(), '"
						+ name.getPresupuesto().getContacto().getIdpersona() + "');");
		ConsultasEspeciales.consultas(
				"INSERT INTO `operacion_egreso` (`fecha_eg`, `pedido_idpedido`, `usuario_id_persona`) VALUES (CURRENT_DATE(), '"
						+ name.getIdPedido() + "', '" + usuario.getIdpersona() + "');");
		eliminaPresupuesto(name.getPresupuesto().getIdPresupuesto());
		return true;
	}

	public Vector<Pedido> selectPedidos() {
		PedidoDao dao = new PedidoDao();
		return (Vector<Pedido>) dao.selectPedido();
	}

	public Vector<Contacto> selectContacto() {
		ContactactoDao dao = new ContactactoDao();
		return (Vector<Contacto>) dao.selectCliente();
	}

	public Vector<Vector<Object>> selectString(String consulta) {
		return ConsultasEspeciales.selectString(consulta);
	}

	public boolean insertCliente(Cliente cliente) {
		ClienteDao dao = new ClienteDao();
		Vector<Cliente> clientes = (Vector<Cliente>) dao.selectCliente();
		for (Cliente cliente2 : clientes) {
			if (cliente.getCuit() == cliente2.getCuit()) {
				return false;// cuando el cuit ya exixte
			}
		}
		String consulta = "INSERT INTO `empresa` (`idempresa`, `razon_social`, `cuit`, `direccion`, `telefono`, `localidad`, `codigo_postal`, `flagbaja`) VALUES (NULL, '"
				+ cliente.getRazonSocial() + "', '" + cliente.getCuit() + "', '" + cliente.getDireccion() + "', '"
				+ cliente.getTelefono() + "', '" + cliente.getLocalidad() + "', '" + cliente.getCodPostal()
				+ "', '1');";
		ConsultasEspeciales.consultas(consulta);
		consulta = "SELECT * FROM `empresa` ORDER BY `empresa`.`idempresa` DESC";
		int idEmpresa = (int) ConsultasEspeciales.selectString(consulta).get(0).get(0);
		consulta = "INSERT INTO `cliente` (`idcliente`) VALUES ('" + idEmpresa + "');";
		ConsultasEspeciales.consultas(consulta);
		for (Contacto contacto : cliente.getContacto()) {
			consulta = "INSERT INTO `persona` (`id_persona`, `nombre`, `apellido`, `telefono`, `cargo`, `flagbaja`, `dni`) VALUES (NULL, '"
					+ contacto.getNombre() + "', '" + contacto.getApellido() + "', '" + contacto.getTelefono() + "', '"
					+ contacto.getCargo() + "', '1', '" + contacto.getDni() + "');";
			ConsultasEspeciales.consultas(consulta);
			int idcontacto = (int) ConsultasEspeciales
					.selectString("SELECT * FROM `persona` ORDER BY `persona`.`id_persona` DESC").get(0).get(0);
			consulta = "INSERT INTO `contacto` (`idpersona`, `permisootorgado`, `cliente_idcliente`) VALUES ('"
					+ idcontacto + "', '" + contacto.getPermisoOtorgado() + "', '" + idEmpresa + "');";
			ConsultasEspeciales.consultas(consulta);
		}
		return true;
	}
}
