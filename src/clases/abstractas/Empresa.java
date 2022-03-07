package clases.abstractas;

import java.util.Vector;

import clases.principales.cliente.Contacto;
import clases.principales.cliente.Provincia;

public abstract class Empresa {
	private int idEmpresa;
	private String razonSocial;
	private int cuit;
	private String direccion;
	private int telefono;
	private String localidad;
	private int codPostal;
	private int flagBaja;
	private Provincia provincia;
	private Vector<Contacto> contactos;

	public Vector<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(Vector<Contacto> contactos) {
		this.contactos = contactos;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public int getCuit() {
		return cuit;
	}

	public void setCuit(int cuit) {
		this.cuit = cuit;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public int getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(int codPostal) {
		this.codPostal = codPostal;
	}

	public int getFlagBaja() {
		return flagBaja;
	}

	public void setFlagBaja(int flagBaja) {
		this.flagBaja = flagBaja;
	}
}
