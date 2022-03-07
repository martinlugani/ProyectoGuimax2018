package clases.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Vector;

import clases.coneccion.ConnectionSingleton;

public class ConsultasEspeciales {
	private Connection con;
	private Statement stm;
	private ResultSet res;
	private static Connection cone;
	private static ResultSet resu;
	private static Statement stam;
	private static PreparedStatement psts;
	public Vector<Vector<String>> select(Vector<String> tablas, Vector<String> columnas, Vector<String> clausula,
			Vector<String> busqueda) {
		try {
			con = ConnectionSingleton.getConnection();
			stm = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
			Vector<Vector<String>> tabla = new Vector<>();
			if (!tablas.isEmpty()) {
				String sql = "SELECT ";
				int cont = 1;
				if (!columnas.isEmpty() && columnas != null) {

					for (String string : columnas) {
						sql += cont == columnas.size() ? " " + string + "" : "" + string + ", ";
						cont++;
					}
				} else {
					sql += " * ";
				}
				sql += " FROM ";
				cont = 1;
				for (String string : tablas) {
					sql += cont == tablas.size() ? " " + string + "" : "" + string + ", ";
					cont++;
				}
				cont = 1;

				if (!clausula.isEmpty() && clausula != null && !busqueda.isEmpty() && busqueda != null) {
					sql += " WHERE ";
					int busquedaCont = 0;
					for (String string : clausula) {
						sql += cont == clausula.size() ? " " + string + " = " + busqueda.get(busquedaCont)+";"
								: "" + string + " = " + busqueda.get(busquedaCont) + ", ";
						busquedaCont++;
					}
				} else {
					sql += ";";
				}
				System.out.println("Consulta " + sql);
				res = stm.executeQuery(sql);
				while (res.next()) {
					Vector<String> fila = new Vector<String>();
					cont = 0;
					String valor = "";
					while (columnas.size() > cont) {
						int tipo = res.getMetaData().getColumnType(cont + 1);
						if (tipo == Types.DECIMAL) {
							valor += String.valueOf(res.getInt(cont + 1));
						}
						if (tipo == Types.VARCHAR) {
							valor += res.getString(cont + 1);
						}
						if (tipo == Types.DATE) {
							valor += res.getDate(cont + 1);
						}
						if (tipo== Types.INTEGER) {
							valor += String.valueOf(res.getInt(cont + 1));
						}
						if (tipo == Types.TINYINT) {
							
						}

						fila.add(valor);
						cont++;
					}
					tabla.add(fila);
				}
				return tabla;
			}

			return null;
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

	public static Vector<Vector<Object>> selectString(String consulta) {
		try {
			cone = ConnectionSingleton.getConnection();
			stam = cone.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
			Vector<Vector<Object>> tabla = new Vector<>();
			resu = stam.executeQuery(consulta);
			while (resu.next()) {
				Vector<Object> fila = new Vector<Object>();
				int cont = 0;
				
				while (resu.getMetaData().getColumnCount() > cont) {
					int tipo = resu.getMetaData().getColumnType(cont + 1);
					if (tipo == Types.DECIMAL) {
						int num = resu.getInt(cont+1);
						fila.add(num);
					}
					if (tipo == Types.VARCHAR) {
						fila.add (resu.getString(cont +1));
					}
					if (tipo == Types.DATE) {
						fila.add(resu.getDate(cont + 1));
					}
					if (tipo== Types.INTEGER) {
						fila.add(resu.getInt(cont + 1));
					}
					if (tipo== Types.TINYINT) {
						fila.add(resu.getInt(cont + 1));
					}
					if (tipo== Types.REAL) {
						fila.add(resu.getFloat(cont + 1));
					//	System.out.println(" paso "+resu.getFloat(cont + 1) );
					}
					

				
					cont++;
				}
				tabla.add(fila);
			}
			

			return tabla;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (resu != null)
					resu.close();
				if (stam != null)
					stam.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}
	}
	public static boolean consultas(String consulta) {
		try {
			cone = ConnectionSingleton.getConnection();
	
			cone.setAutoCommit(false);
			psts=cone.prepareStatement(consulta);
			
			if (psts.executeUpdate()==1) {
				cone.commit();
				return true;
			}
 			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (cone != null)
					cone.rollback();
				if (psts!= null)
					psts.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}
	}
}
