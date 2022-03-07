package clases.principales.productos;

import java.util.Vector;

public class Proveedor {
	private Vector<Material> materiales;

	public Vector<Material> getMateriales() {
		return materiales;
	}

	public void setMateriales(Vector<Material> materiales) {
		this.materiales = materiales;
	}
}
