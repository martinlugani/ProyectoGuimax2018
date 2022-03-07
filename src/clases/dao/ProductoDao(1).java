package clases.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;

import clases.coneccion.ConnectionSingleton;
import clases.principales.productos.Caracteristica;
import clases.principales.productos.LineaMaterial;
import clases.principales.productos.Material;
import clases.principales.productos.Producto;
import clases.principales.productos.Producto;
import clases.principales.productos.TipoMaterial;

public class ProductoDao {
	private Connection con;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet res;

	public Collection<Producto> selectProducto() {
		try {
			con = ConnectionSingleton.getConnection();
			stm = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
			res = stm.executeQuery("SELECT * from producto;");
			Vector<Producto> prod = new Vector<Producto>();
			
			if (!res.next())

				return null;

			res.beforeFirst();
			while (res.next()) {
				Vector<Material> materialesdePro = new Vector<Material>();
				if (!materialesdePro.isEmpty()) {
					materialesdePro.removeAllElements();
				}
				LineaMaterialDao lin = new LineaMaterialDao();
				Vector<LineaMaterial> lineas = (Vector<LineaMaterial>) lin.selectLineaMaterial(res.getInt(1));

				Producto pro = new Producto();
				pro.setCodigoProducto(res.getInt(1));
				pro.setModelo(res.getString(2));
				pro.setVoltamperios(res.getInt(3));
				pro.setLugarUtilizacion(res.getString(5));
				pro.setHorasHombre(res.getInt(4));
				pro.setPrecioHoraHombre(res.getInt(7));
				pro.setFlagBaja(res.getInt(6));
				MaterialesDao daoMAt = new MaterialesDao();
				LineaMaterialDao daoli = new LineaMaterialDao();
				Vector<Material> mates = (Vector<Material>) daoMAt.selectMaterial();
				for (LineaMaterial lineaMaterial : lineas) {
					for (Material material : mates) {
						if (lineaMaterial.getMaterial() == material.getIdMaterial()) {
							materialesdePro.add(material);
						}
					}

				}
				pro.setItems(lineas);
				pro.setLineas(materialesdePro);
				pro.generaSuma();
				prod.add(pro);
				
			}
			return prod;
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

	public boolean insert(Producto producto) {
		try {
			con = ConnectionSingleton.getConnection();
			String sql = "INSERT INTO `producto` (`idproducto`, `modelo`, `voltamperios`, `horas_hombre`, `lugarUtilizacion`, `falgbaja`, `precio_hora_hombre`) VALUES ('";

			sql += producto.getCodigoProducto() + "', '";
			sql += producto.getModelo() + "', '";
			sql += producto.getVoltamperios() + "', '";
			sql += producto.getHorasHombre() + "', '";
			sql += producto.getLugarUtilizacion() + "', '";
			sql += "1', '";
			sql += producto.getPrecioHoraHombre() + "');";
			con.setAutoCommit(false);
			pstm = con.prepareStatement(sql);
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
