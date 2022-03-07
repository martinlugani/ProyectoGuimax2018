package clases.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

import clases.principales.productos.Caracteristica;
import clases.principales.productos.TipoMaterial;
import clases.coneccion.ConnectionSingleton;

public class TipoMaterialDao {
	private Connection con;
	private ResultSet res;
	private PreparedStatement pstm;
	private Statement stm;

	public Collection<TipoMaterial> select() {
		try {
			con = ConnectionSingleton.getConnection();
			stm = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
			res = stm.executeQuery(
					"SELECT  t.id_tipo , t.descripcion , t.id_caract , c.descripcion , c.unidad_medida FROM tipo_material as t , caracteristica as c where t.id_caract = c.id_carac ;");
			Collection<TipoMaterial> tipos = new Vector<TipoMaterial>();
			if (res != null) {

				if (res.next()) {
					res.beforeFirst();
					while (res.next()) {
						TipoMaterial tipo = new TipoMaterial();
						tipo.setIdTipo(res.getInt(1));
						tipo.setDescripcion(res.getString(2));
						Caracteristica cara = new Caracteristica();
						cara.setIdCaracteristica(res.getInt(3));
						cara.setDescripcion(res.getString(4));
						cara.setUnidadMedida(res.getString(5));
						tipo.setCaracteristica(cara);
						tipos.add(tipo);
					}
					return tipos;
				}

			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (res != null) {
					res.close();
				}
				if (stm != null) {
					stm.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}

	}

	public boolean ingresarTipoNuevo(TipoMaterial tipo) {
		try {
			con = ConnectionSingleton.getConnection();
			con.setAutoCommit(false);

			String sql = "INSERT INTO tipo_material (descripcion , id_caract) VALUES ( ?, ? ) ;";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, tipo.getDescripcion());
			pstm.setInt(2, tipo.getCaracteristica().getIdCaracteristica());
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
				if (res != null)
					res.close();

				if (pstm != null)
					pstm.close();

				if (con != null)
					con.rollback();

			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}

	}

	public Collection<Caracteristica> obtenerCaracteristica() {
		try {
			con = ConnectionSingleton.getConnection();
			stm = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
			res = stm.executeQuery("SELECT * FROM caracteristica;");
			Collection<Caracteristica> ccarra = new Vector<Caracteristica>();
			if (ccarra != null) {

				if (res.next()) {
					res.beforeFirst();
					while (res.next()) {

						Caracteristica cara = new Caracteristica();
						cara.setIdCaracteristica(res.getInt(1));
						cara.setDescripcion(res.getString(2));
						cara.setUnidadMedida(res.getString(3));

						ccarra.add(cara);
					}
					return ccarra;
				}

			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (res != null) {
					res.close();
				}
				if (stm != null) {
					stm.close();
				}

			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}
	}

	public boolean insertCaracteristica(Caracteristica nueva) {
		System.out.println("LLega aaca");
		try {
			con = ConnectionSingleton.getConnection();
			con.setAutoCommit(false);

			String sql = "INSERT INTO caracteristica ( descripcion , unidad_medida) VALUES ( ? , ?);";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, nueva.getDescripcion());
			pstm.setString(2, nueva.getUnidadMedida());
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
				if (res != null) {
					res.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (con != null) {
					con.rollback();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}
	}

}
