package clases.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Vector;

import clases.coneccion.ConnectionSingleton;
import clases.principales.productos.Caracteristica;
import clases.principales.productos.Material;
import clases.principales.productos.TipoMaterial;

public class MaterialesDao {
	private Connection con;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet res;

	public Collection<Material> selectMaterial() {
		try {
			con = ConnectionSingleton.getConnection();
			stm = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
			res = stm.executeQuery(
					"SELECT m.idmaterial,m.descripcion,m.stock_actual,m.stock_minimo,m.ultimo_precio,m.precio_actual,m.flagbaja,t.id_tipo,t.descripcion,c.id_carac,c.descripcion,c.unidad_medida FROM material as m , tipo_material as t , caracteristica as c where m.id_tipo = t.id_tipo and t.id_caract =  c.id_carac;");
//			if (res.next()) {
//				System.out.println("consulta hecha");
			/*
			 * System.out.println(res.getRow());
			 * System.out.println((Integer.toString(res.getInt(1)))); res.beforeFirst(); }
			 */ Vector<Material> mates = new Vector<Material>();
			if (!res.next())

				return null;
			res.beforeFirst();
			while (res.next()) {
				Material mate = new Material();
				mate.setIdMaterial(res.getInt(1));
				mate.setDescripcion(res.getString(2));
				mate.setStockActual(res.getInt(3));
				mate.setPuntoPedido(res.getInt(4));
				mate.setUltimoPrecio(res.getFloat(5));
				mate.setPrecioActual(res.getFloat(6));
				mate.setFlagBaja(res.getInt(7));
				TipoMaterial tipo = new TipoMaterial();
				tipo.setIdTipo(res.getInt(8));
				tipo.setDescripcion(res.getString(9));
				Caracteristica cara = new Caracteristica();
				cara.setIdCaracteristica(res.getInt(10));
				cara.setDescripcion(res.getString(11));
				cara.setUnidadMedida(res.getString(12));
				tipo.setCaracteristica(cara);
				mate.setTipoMaterial(tipo);
				mates.add(mate);
			}
			return mates;
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

	public boolean isertMaterial(Material mate) {
		try {
			con = ConnectionSingleton.getConnection();
			con.setAutoCommit(false);
			String sql = "INSERT INTO material (descripcion, stock_actual, stock_minimo, ultimo_precio, precio_actual, id_tipo, flagbaja) VALUES ( ?, ?, ?, ?, ?, ?, ?);";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, mate.getDescripcion());
			pstm.setInt(2, mate.getStockActual());
			pstm.setInt(3, mate.getPuntoPedido());
			pstm.setFloat(4, mate.getUltimoPrecio());
			pstm.setFloat(5, mate.getPrecioActual());
			pstm.setInt(6, mate.getTipoMaterial().getIdTipo());
			pstm.setInt(7, mate.getFlagBaja());
			if (pstm.executeUpdate() == 1) {
				con.commit();
				return true;

			} else {
				return false;
			}

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
			}
		}

	}

	public boolean upgradeMaterial(Material materialOriginal, Material materialDuplicado) {
		try {
			con = ConnectionSingleton.getConnection();
			con.setAutoCommit(false);
			String sql = "UPDATE material SET ";
			int secuencia = 0;
			boolean des = false;
			boolean actual = false;
			boolean punto = false;
			boolean ultPre = false;
			boolean preActu = false;
			boolean idTipo = false;
			boolean flag = false;
			if (!materialOriginal.getDescripcion().equals(materialDuplicado.getDescripcion())) {
				sql += "descripcion = ?";
				secuencia++;
				des = true;
			}
			if (materialOriginal.getStockActual() != materialDuplicado.getStockActual()) {
				sql += secuencia == 1 ? ", stock_actual = ?" : " stock_actual = ?";
				secuencia = 1;
				actual = true;
			}
			if (materialOriginal.getPuntoPedido() != materialDuplicado.getPuntoPedido()) {
				sql += secuencia == 1 ? ", stock_minimo = ?" : " stock_minimo = ?";
				secuencia = 1;
				punto = true;
			}
			if (materialOriginal.getUltimoPrecio() != materialDuplicado.getUltimoPrecio()) {
				sql += secuencia == 1 ? ", ultimo_precio = ?" : " ultimo_precio = ?";
				secuencia = 1;
				ultPre = true;
			}
			if (materialOriginal.getPrecioActual() != materialDuplicado.getPrecioActual()) {
				sql += secuencia == 1 ? ", precio_actual = ?" : " precio_actual = ?";
				secuencia = 1;
				preActu = true;
			}
			if (materialDuplicado.getTipoMaterial().getIdTipo() != 0) {
				if (materialOriginal.getTipoMaterial().getIdTipo() != materialDuplicado.getTipoMaterial().getIdTipo()) {
					sql += secuencia == 1 ? ", id_tipo = ?" : " id_tipo = ?";
					secuencia = 1;
					idTipo = true;
				}
			}

			if (materialOriginal.getFlagBaja() != materialDuplicado.getFlagBaja()) {
				sql += secuencia == 1 ? ", flagbaja = ?" : " flagbaja = ?";
				secuencia = 1;
				flag = true;
			}
			sql += " WHERE idmaterial = ?;";
			pstm = con.prepareStatement(sql);
			secuencia = 1;
			if (des) {
				pstm.setString(secuencia, materialDuplicado.getDescripcion());
				secuencia++;
			}
			if (actual) {
				if (secuencia == 1) {
					pstm.setInt(secuencia, materialDuplicado.getStockActual());
					secuencia++;
				}

			}
			if (punto) {
				pstm.setInt(secuencia, materialDuplicado.getPuntoPedido());
				secuencia++;
			}
			if (ultPre) {
				pstm.setFloat(secuencia, materialDuplicado.getUltimoPrecio());
				secuencia++;
			}
			if (preActu) {
				pstm.setFloat(secuencia, materialDuplicado.getPrecioActual());
				secuencia++;
			}
			if (idTipo) {
				pstm.setInt(secuencia, materialDuplicado.getTipoMaterial().getIdTipo());
				secuencia++;
			}
			if (flag) {
				pstm.setInt(secuencia, materialDuplicado.getFlagBaja());
				secuencia++;
			}
			pstm.setInt(secuencia, materialDuplicado.getIdMaterial());
			if (flag || des || actual || idTipo || preActu || punto || ultPre) {

				if (pstm.executeUpdate() == 1) {
					con.commit();
					return true;

				}
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
			}
		}

	}

	public boolean delete(Material material) {
		try {
			con = ConnectionSingleton.getConnection();
			con.setAutoCommit(false);
			String sql = " material SET ";
//			pstm+

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
			}
		}
	}
}
