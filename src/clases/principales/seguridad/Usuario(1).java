package clases.principales.seguridad;

import java.sql.Date;
import java.util.Vector;

import clases.abstractas.Persona;

public class Usuario extends Persona {
	
	private String nombreUsuario;
	private String contrasenia;
	private int intentos;
	private Date fechaAlta;
	private Date fechaUltimoLogin;
	private int opIngresoEgreso;
	private Rol rol; 

	

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol permisos) {
		this.rol = permisos;
	}

	public int getOpIngresoEgreso() {
		return opIngresoEgreso;
	}

	public void setOpIngresoEgreso(int opIngresoEgreso) {
		this.opIngresoEgreso = opIngresoEgreso;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public int getIntentos() {
		return intentos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaUltimoLogin() {
		return fechaUltimoLogin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contrasenia == null) ? 0 : contrasenia.hashCode());
		result = prime * result + ((nombreUsuario == null) ? 0 : nombreUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (contrasenia == null) {
			if (other.contrasenia != null)
				return false;
		} else if (!contrasenia.equals(other.contrasenia))
			return false;
		if (nombreUsuario == null) {
			if (other.nombreUsuario != null)
				return false;
		} else if (!nombreUsuario.equals(other.nombreUsuario))
			return false;
		return true;
	}

	public void setFechaUltimoLogin(Date fechaUltimoLogin) {
		this.fechaUltimoLogin = fechaUltimoLogin;
	}
}
