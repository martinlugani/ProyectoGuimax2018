package clases.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

import clases.coneccion.ConnectionSingleton;
import clases.principales.pedido.Pedido;
import clases.principales.pedido.Presupuesto;

public class PedidoDao {
	private Connection con;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet res;
	private ResultSet res1;
	public Collection<Pedido> selectPedido() {
		try {
			PresupuestosDao daoPrs = new PresupuestosDao();
			
			Vector<Presupuesto> presupuestos =(Vector<Presupuesto>) daoPrs.select();
			con = ConnectionSingleton.getConnection();
			stm = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
			res = stm.executeQuery("SELECT * FROM `pedido`");
			Vector<Pedido> pedidos = new Vector<Pedido>();
			while (res.next()) {
				for (Presupuesto presupuesto : presupuestos) {
					if (res.getInt(5)==presupuesto.getIdPresupuesto()) {
						Pedido pedido = new Pedido();
						pedido.setPresupuesto(presupuesto);
						pedido.setIdPedido(res.getInt(1));
						pedido.setFechaAlta(res.getDate(2));
						pedido.setFechaEntrega(res.getDate(3));
						pedido.setSumaTotal(res.getFloat(4));
						pedido.setFlagBaja(res.getInt(6));
						pedidos.add(pedido);
						
					}
				}
			}
			return pedidos;

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
}
