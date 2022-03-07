package clases.principales.productos;

public class LineaMaterial {
	private int idLinea;
	private int material;
	private int producto;
	private int cantidadNecesaria;

	public LineaMaterial() {
		

	}

	public int getIdLinea() {
		return idLinea;
	}

	public void setIdLinea(int idLinea) {
		this.idLinea = idLinea;
	}

	public int getMaterial() {
		return material;
	}

	public void setMaterial(int material) {
		this.material = material;
	}

	public int getCantidadNecesaria() {
		return cantidadNecesaria;
	}

	public void setCantidadNecesaria(int cantidadNecesaria) {
		this.cantidadNecesaria = cantidadNecesaria;
	}

	public int getProducto() {
		return producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}
	public void setCantidadNecesaria(String cantidadNecesaria) {
		try {
			this.cantidadNecesaria=Integer.parseInt(cantidadNecesaria);
		} catch (Exception e) {
			this.cantidadNecesaria=0;
		}
		
	}
	public void setProducto(String producto) {
		this.producto = Integer.parseInt(producto);
	}
	public void setMaterial(String  material) {
		this.material = Integer.parseInt(material);
	}
}
