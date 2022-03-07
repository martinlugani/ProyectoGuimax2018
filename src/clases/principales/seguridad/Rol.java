package clases.principales.seguridad;

import java.util.Vector;

public class Rol {
	private int idRol;
	private String descripcion;
	private Vector<Permiso> permiso;
	private int flagBaja;
	public int getFlagBaja() {
		return flagBaja;
	}

	public void setFlagBaja(int flagBaja) {
		this.flagBaja = flagBaja;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Vector<Permiso> getPermiso() {
		return permiso;
	}

	public void setPermiso(Vector<Permiso> permiso) {
		this.permiso = permiso;
	}
	public void setRolPermisoTodoUno(int idrolPermiso) {
		Permiso per = new Permiso();
		per.setIdPermiso(idrolPermiso);
		if (idrolPermiso==1) {
			per.setDescripcion("Completo");
		} else {
			per.setDescripcion("Restringido");
		}
		if (permiso==null) {
			permiso=new Vector<Permiso>();
		}
		per.setFlagBaja(1);
		this.permiso.add(per);
		this.idRol=idrolPermiso;
	}
}
