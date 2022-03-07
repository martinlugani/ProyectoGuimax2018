package clases.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

import clases.coneccion.ConnectionSingleton;
import clases.principales.seguridad.Rol;
import clases.principales.seguridad.Usuario;

public class UsuariosDao {
	private Connection con;
	private ResultSet res;
	private PreparedStatement pstm;
	private Statement stm;
	private boolean contrasenia = true;
	private boolean modificaIntentos = true;

	public Collection<Usuario> select() {
		try {
			con = ConnectionSingleton.getConnection();
			stm = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
			res = stm.executeQuery(
					"SELECT nommbreusuario, contrasenia, usuario.id_persona, intentos, fecha_alta, fecha_ultimo_log, rol, persona.id_persona, nombre, apellido, telefono, cargo, flagbaja, dni  FROM usuario , persona where usuario.id_persona = persona.id_persona;");
			Collection<Usuario> usuario = new Vector<Usuario>();
			if (res != null) {
				while (res.next()) {
					Usuario usu = new Usuario();
					System.out.println(String.valueOf(res.toString()));
					usu.setNombreUsuario(res.getString("nommbreusuario"));
					usu.setContrasenia(res.getString("contrasenia"));
					usu.setIdpersona(res.getInt("usuario.id_persona"));
					usu.setIntentos(res.getInt("intentos"));
					usu.setFechaAlta(res.getDate("fecha_alta"));
					usu.setFechaUltimoLogin(res.getDate("fecha_ultimo_log"));
					usu.setRol(new Rol());
					usu.getRol().setIdRol(res.getInt("rol"));
					usu.setNombre(res.getString("nombre"));
					usu.setApellido(res.getString("apellido"));
					usu.setTelefono(res.getInt("telefono"));
					usu.setCargo(res.getString("cargo"));
					usu.setFlagBaja(res.getInt("flagbaja"));
					usu.setDni(res.getInt("dni"));
					usuario.add(usu);
				}
				if (!usuario.isEmpty()) {
					return usuario;
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

	public boolean insertUsuario(Usuario usu) {
		try {
			con = ConnectionSingleton.getConnection();
			String sql = "INSERT INTO `persona` (`id_persona`, `nombre`, `apellido`, `telefono`, `cargo`, `flagbaja`, `dni`) VALUES (?, ?, ?, ?, ?, ?, ?);";
			pstm = con.prepareStatement(sql);
			con.setAutoCommit(false);
			pstm.setInt(1, usu.getIdpersona());
			pstm.setString(2, usu.getNombre());
			pstm.setString(3, usu.getApellido());
			pstm.setInt(4, usu.getTelefono());
			pstm.setString(5, usu.getCargo());
			pstm.setInt(6, 1);
			pstm.setInt(7, usu.getDni());
			if (pstm.executeUpdate() != 1) {
				return false;
			}
			con.commit();
			con.setAutoCommit(false);
			pstm.close();
			con = ConnectionSingleton.getConnection();
			sql = "INSERT INTO `usuario` (`nommbreUsuario`, `contrasenia`, `id_persona`, `intentos`, `fecha_alta`, `fecha_ultimo_log` , `rol`) VALUES (?, ?, ?, '3', ?, ?, ? );";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, usu.getNombreUsuario());
			pstm.setString(2, usu.getContrasenia());
			pstm.setInt(3, usu.getIdpersona());
			pstm.setDate(4, usu.getFechaAlta());
			pstm.setDate(5, usu.getFechaUltimoLogin());
			pstm.setInt(6, usu.getRol().getIdRol());
			if (pstm.executeUpdate() != 1) {
				return false;
			}
			con.commit();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
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

	public int selectCount() {
		try {
			con = ConnectionSingleton.getConnection();
			stm = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
			res = stm.executeQuery("SELECT COUNT(`id_persona`) FROM `persona` WHERE 1");
			res.first();
			System.out.println(res.getInt(1));
			return res.getInt(1);
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

	/**
	 * para realizar una consulta sencilla no pasar nada para loguin usar contraseña
	 * usar bolean contraseña
	 * 
	 * @param usuario
	 * @param duplicado
	 * @return
	 */
	public boolean updateUsuario(Usuario usuario, Usuario duplicado) {
		// TODO Auto-generated method stub
		try {
			con = ConnectionSingleton.getConnection();
			boolean paso = false;

			String sql = "UPDATE `usuario` SET  ";
			int secuencia = 0;

			if (!usuario.getNombreUsuario().equals(duplicado.getNombreUsuario()) && duplicado.getApellido() != null) {
				sql += "`nommbreUsuario` = '" + duplicado.getNombreUsuario() + "' ";
				secuencia++;
			}
			if (contrasenia) {

				if (!usuario.getContrasenia().equals(duplicado.getContrasenia())) {
					sql += secuencia == 1 ? ", `contrasenia` = '" + duplicado.getContrasenia() + "'"
							: " `contrasenia` = '" + duplicado.getContrasenia() + "'";
					secuencia = 1;
				}
				contrasenia = true;
			}
			if (usuario.getIdpersona() != duplicado.getIdpersona() && duplicado.getIdpersona() != 0) {
				sql += secuencia == 1 ? "`id_persona` = '" + duplicado.getIdpersona() + "',"
						: "`id_persona` = '" + duplicado.getIdpersona() + "'";
				secuencia = 1;
			}

			if (usuario.getIntentos() != duplicado.getIntentos() &&/* usuario.getIntentos() != 0
					&&*/ duplicado.getIntentos() <= 3) {
				sql += secuencia == 1 ? "`intentos` = '" + duplicado.getIntentos() + "',"
						: "`intentos` = '" + duplicado.getIntentos() + "'";
				secuencia = 1;

			}
			if (!usuario.getFechaUltimoLogin().equals(duplicado.getFechaUltimoLogin())
					&& duplicado.getFechaUltimoLogin() != null) {
				sql += secuencia == 1 ? "`fecha_ultimo_log` = '" + duplicado.getFechaUltimoLogin() + "'"
						: "`fecha_ultimo_log` = '" + duplicado.getFechaUltimoLogin() + "'";
				secuencia = 1;
			}
			if (usuario.getIntentos() == duplicado.getIntentos()) {
				sql += secuencia == 1 ? ", `intentos`= " + duplicado.getIntentos()
						: "`intentos`= " + duplicado.getIntentos();
				secuencia = 1;
			}
			if (duplicado.getRol() != null) {
				if (usuario.getRol().getIdRol() != duplicado.getRol().getIdRol()) {
					sql += secuencia == 1 ? ", `rol` = '" + duplicado.getRol().getIdRol() + "'"
							: "`rol` = '" + duplicado.getRol().getIdRol() + "'";
					secuencia = 1;
				}
			}

			if (secuencia == 1) {
				sql += " WHERE `usuario`.`nommbreUsuario` = ? AND `usuario`.`id_persona` = ?;";
				con.setAutoCommit(false);
				con.setAutoCommit(false);
				pstm = con.prepareStatement(sql);
				pstm.setString(1, usuario.getNombreUsuario());
				pstm.setInt(2, usuario.getIdpersona());
				paso = true;
			}
			if (paso) {
				if (pstm.executeUpdate() != 1) {

					return false;
				}
				con.commit();
				paso = false;
			}

			secuencia = 0;
			sql = "UPDATE `persona` SET ";
			if (usuario.getTelefono() != duplicado.getTelefono() && contrasenia) {
				sql += "`telefono`=" + duplicado.getTelefono();
				secuencia = 1;
			}
			if (!usuario.getCargo().equals(duplicado.getCargo()) && contrasenia) {
				sql += secuencia == 1 ? ",`persona`.`cargo` = '" + duplicado.getCargo() + "'"
						: " `persona`.`cargo` = '" + duplicado.getCargo() + "'";
				secuencia = 1;
			}
			if (usuario.getFlagBaja() != duplicado.getFlagBaja() && contrasenia) {
				sql += secuencia == 1 ? ", `flagbaja`= " + duplicado.getFlagBaja()
						: "`flagbaja`= " + duplicado.getFlagBaja();
				secuencia = 1;
			}
			if (secuencia == 1) {
				sql += " WHERE `id_persona`= " + usuario.getIdpersona() + " ;";
				con.setAutoCommit(false);
				pstm = con.prepareStatement(sql);
				paso = true;
			}
			if (paso) {
				if (pstm.executeUpdate() == 1) {
					con.commit();
					return true;
				}

			} else {
				return false;
			}
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			try {
				if (con != null) {
					con.rollback();

				}
				if (pstm != null) {
					pstm.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
				throw new RuntimeException();
			}
		}
	}

	public boolean isContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(boolean contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean isModificaIntentos() {
		return modificaIntentos;
	}

	public void setModificaIntentos(boolean modificaIntentos) {
		this.modificaIntentos = modificaIntentos;
	}

	public boolean selectId(int id) {
		try {
			con = ConnectionSingleton.getConnection();
			stm = con.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
			String sql = "SELECT * FROM `persona` WHERE `id_persona`= " + id + ";";
			res = stm.executeQuery(sql);
			res.beforeFirst();
			if (res.next()) {
				return true;
			}

			return false;
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

}
