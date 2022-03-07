package clases.principales.cliente;

import java.util.Vector;

import clases.abstractas.Empresa;

public class Cliente extends Empresa {
	private Vector<Contacto> contacto;

	public Cliente() {
		contacto = new Vector<Contacto>();
	}

	public Vector<Contacto> getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto.add(contacto);
	}

}
