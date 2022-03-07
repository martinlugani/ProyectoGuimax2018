package clases.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

import clases.coneccion.ConnectionSingleton;
import clases.principales.cliente.Contacto;

public class ContactactoDao {
	private Connection con;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet res;

	public Collection<Contacto> selectCliente() {
		try {
			con = ConnectionSingleton.getConnection();
			stm = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
			res = stm.executeQuery("SELECT * FROM contacto, persona where contacto.idpersona = persona.id_persona and persona.flagbaja = 1;");
			Vector<Contacto> cont = new Vector<Contacto>();
			while (res.next()) {
				Contacto contac = new Contacto();
				contac.setIdpersona(res.getInt(1));
				contac.setPermisoOtorgado(res.getString(2));
				contac.setIdCliente(res.getInt(3));
				contac.setNombre(res.getString(5));
				contac.setApellido(res.getString(6));
				contac.setTelefono(res.getInt(7));
				contac.setCargo(res.getString(8));
				contac.setFlagBaja(res.getInt(9));
				contac.setDni(res.getInt(10));
				cont.add(contac);
			}

			return cont;

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
