package clases.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import clases.coneccion.ConnectionSingleton;
import clases.principales.pedido.Presupuesto;

public class PresupuestosDao {
	private Connection con;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet res;

	public int selectCountPresup() {
		try {
			con = ConnectionSingleton.getConnection();
			stm = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
			res = stm.executeQuery("SELECT count(id_presupuesto) FROM presupuesto;");
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
				insert += presupuestoNuevo.getLineaPresupueso().get(i).getIdLinea() + " , " + presupuestoNuevo.getIdPresupuesto()
						+ " , " + presupuestoNuevo.getLineaPresupueso().get(i).getCantidad() + " , "
						+ presupuestoNuevo.getLineaPresupueso().get(i).getProducto().getCodigoProducto();
				if (i != presupuestoNuevo.getLineaPresupueso().size() - 1) {
					insert += ") , (";
				} else {
					insert += ");";
				}

			}
			pstm=con.prepareStatement(insert);

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
}
