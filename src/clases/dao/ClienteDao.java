package clases.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

import clases.coneccion.ConnectionSingleton;
import clases.principales.cliente.Cliente;
import clases.principales.cliente.Contacto;


public class ClienteDao {
	private Connection con;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet res;
	
	public Collection<Cliente> selectCliente() {
		try {
			con = ConnectionSingleton.getConnection();
			stm = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
			res = stm.executeQuery("SELECT * FROM 	empresa;");
			Vector<Cliente> clientes = new Vector<Cliente>();
			while (res.next()) {
				Cliente cli = new Cliente();
				cli.setIdEmpresa(res.getInt(1));
				cli.setRazonSocial(res.getString(2));
				cli.setCuit(res.getInt(3));
				cli.setDireccion(res.getString(4));
				cli.setTelefono(res.getInt(5));
				cli.setLocalidad(res.getString(6));
				cli.setCodPostal(res.getInt(7));
				cli.setFlagBaja(res.getInt(8));
				ContactactoDao dao = new ContactactoDao();
				Vector<Contacto> contactos=(Vector<Contacto>) dao.selectCliente();
				for (Contacto contacto : contactos) {
					if (contacto.getIdCliente()==cli.getIdEmpresa()) {		
						cli.setContacto(contacto);
						
					}
				}
				clientes.add(cli);
				
			}
			
			
			return clientes;
			
				
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
