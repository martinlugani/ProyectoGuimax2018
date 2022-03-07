package clases.principales.productos;

public class Material {
	@Override
	public String toString() {
		return "Material [idMaterial=" + idMaterial + ", descripcion=" + descripcion + ", stockActual=" + stockActual
				+ ", puntoPedido=" + puntoPedido + ", ultimoPrecio=" + ultimoPrecio + ", precioActual=" + precioActual
				+ ", tipoMaterial=" + tipoMaterial + ", flagBaja=" + flagBaja + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + flagBaja;
		result = prime * result + ((tipoMaterial == null) ? 0 : tipoMaterial.hashCode());
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
		Material other = (Material) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equalsIgnoreCase(other.descripcion))
			return false;

		if (tipoMaterial == null) {
			if (other.tipoMaterial != null)
				return false;
		} else if (!tipoMaterial.equals(other.tipoMaterial))
			return false;
		return true;
	}

	private int idMaterial;
	private String descripcion;
	private int stockActual;
	private int puntoPedido;
	private float ultimoPrecio;
	private float precioActual;
	private TipoMaterial tipoMaterial;
	private int flagBaja;

	public int getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(int idMaterial) {
		this.idMaterial = idMaterial;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getStockActual() {
		return stockActual;
	}

	public void setStockActual(int stockActual) {
		this.stockActual = stockActual;
	}

	public int getPuntoPedido() {
		return puntoPedido;
	}

	public void setPuntoPedido(int puntoPedido) {
		this.puntoPedido = puntoPedido;
	}

	public float getUltimoPrecio() {
		return ultimoPrecio;
	}

	public void setUltimoPrecio(float ultimoPrecio) {
		this.ultimoPrecio = ultimoPrecio;
	}

	public float getPrecioActual() {
		return precioActual;
	}

	public void setPrecioActual(float precioActual) {
		this.precioActual = precioActual;
	}

	public TipoMaterial getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(TipoMaterial tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	public int getFlagBaja() {
		return flagBaja;
	}

	public void setFlagBaja(int flagBaja) {
		this.flagBaja = flagBaja;
	}

	public void setDescripcionMaterial(String nombre) {
		TipoMaterial mat = new TipoMaterial();
		mat.setDescripcion(nombre);
		tipoMaterial = mat;

	}

	public void setTipoMaterial(String string) {
		TipoMaterial mat = new TipoMaterial();
		mat.setDescripcion(string);
		tipoMaterial = mat;

	}

	public void setUnidadDeMedida(String unidadMedida) {
		if (this.tipoMaterial == null) {
			tipoMaterial = new TipoMaterial();

		}
		tipoMaterial.setUnidadMedida(unidadMedida);
	}

}
