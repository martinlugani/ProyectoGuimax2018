package clases.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

import clases.coneccion.ConnectionSingleton;
import clases.principales.productos.LineaMaterial;
import clases.principales.productos.Producto;

public class LineaMaterialDao {
	private Connection con;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet res;

	/**
	 * Se encarga de buscar las lineas de material acotadas por producto
	 * 
	 * @param int indiceProducto es el numero de productos que contiene el producto
	 * @return {@link Collection} La cual va a tener todas las lineas de matriales
	 */

	public Collection<LineaMaterial> selectLineaMaterial(int indiceProd) {
		try {
			con = ConnectionSingleton.getConnection();
			stm = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
			res = stm.executeQuery("Select * from linea_material;");

			Vector<LineaMaterial> lin = new Vector<LineaMaterial>();
			if (!res.next())
				return null;

			res.beforeFirst();
			while (res.next()) {
				if (res.getInt(3) == indiceProd) {
					LineaMaterial mat = new LineaMaterial();
					mat.setIdLinea(res.getInt(1));
					mat.setMaterial(res.getInt(2));
					mat.setProducto(res.getInt(3));
					mat.setCantidadNecesaria(res.getInt(4));
					lin.add(mat);
				}

			}
			return lin;
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

	public void insert(Vector<LineaMaterial> lineas) {
		try {
			con = ConnectionSingleton.getConnection();
		
			String sql = "INSERT INTO linea_material (idlinea_material, idmaterial, idproducto,  cantidad_material) VALUES (";

			if (lineas.size() > 0) {
				int pasos=0;
				for (LineaMaterial lineaMaterial : lineas) {
					
					sql += lineaMaterial.getIdLinea() + ", " + lineaMaterial.getMaterial() + ", "
							+ lineaMaterial.getProducto() + ", "
							+ lineaMaterial.getCantidadNecesaria() + ")";
					if (pasos<lineas.size()-1) {
						sql+=", (";
					}else {
						sql+=";";
					}
					pasos++;
				}
			}
			System.out.println(sql);
			con.setAutoCommit(false);
			pstm= con.prepareStatement(sql);
			pstm.executeUpdate();
			con.commit();

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
