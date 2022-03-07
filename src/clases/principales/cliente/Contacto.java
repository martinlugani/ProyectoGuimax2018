package clases.principales.cliente;

import clases.abstractas.Persona;

public class Contacto extends Persona {
	private String permisoOtorgado;
	private int idCliente;
	public String getPermisoOtorgado() {
		return permisoOtorgado;
	}

	public void setPermisoOtorgado(String permisoOtorgado) {
		this.permisoOtorgado = permisoOtorgado;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
}
