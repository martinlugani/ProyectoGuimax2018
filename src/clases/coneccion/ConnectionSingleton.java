package clases.coneccion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

//import java.util.ResourseBundle;
public class ConnectionSingleton {
	private static Connection con = null;

	public static Connection getConnection() {
		try {
			if (con == null) {
				Runtime.getRuntime().addShutdownHook(new MishdwnHook());
				Class.forName("com.mysql.jdbc.Driver");
				ResourceBundle rb = ResourceBundle.getBundle("jdbc");
				String bd = rb.getString("bd");
				String host = rb.getString("host");
				String psw = rb.getString("pass");
				String usr = rb.getString("usr");
				String server = "jdbc:mysql://" + host + "/" + bd;

				con = DriverManager.getConnection(server, usr, psw);

			} 

		} catch (ClassNotFoundException e) {
			System.out.println("No se pudo cargar el driver");

		} catch (SQLException e) {
			System.out.println("no se pudo cargar la coneccion");
		}
		return con;
	}

	static class MishdwnHook extends Thread {
		public void run() {
			try {
				Connection con = ConnectionSingleton.getConnection();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
	}
}
