package clases.principales.productos;

import java.util.Vector;

public class Producto {
	private int codigoProducto;
	private String modelo;
	private int voltamperios;
	private int horasHombre;
	private String lugarUtilizacion;
	private int flagBaja;
	private float precioHoraHombre;
	private Vector<Material> lineas;
	private Vector<LineaMaterial> items;
	private float costoTotal;
	private int categoria;

	public void addItem(LineaMaterial item) {
		if (items == null) {
			items = new Vector<LineaMaterial>();
		}
		items.add(item);
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public Producto() {
		// TODO Auto-generated constructor stub
		lineas = new Vector<Material>();
		items = new Vector<LineaMaterial>();
	}

	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getVoltamperios() {
		return voltamperios;
	}

	public void setVoltamperios(int voltamperios) {
		this.voltamperios = voltamperios;
	}

	public int getHorasHombre() {
		return horasHombre;
	}

	public void setHorasHombre(int horasHombre) {
		this.horasHombre = horasHombre;
	}

	public String getLugarUtilizacion() {
		return lugarUtilizacion;
	}

	public void setLugarUtilizacion(String lugarUtilizacion) {
		this.lugarUtilizacion = lugarUtilizacion;
	}

	public int getFlagBaja() {
		return flagBaja;
	}

	public void setFlagBaja(int flagBaja) {
		this.flagBaja = flagBaja;
	}

	public float getPrecioHoraHombre() {
		return precioHoraHombre;
	}

	public void setPrecioHoraHombre(float precioHoraHombre) {
		this.precioHoraHombre = precioHoraHombre;
	}

	public Vector<Material> getLineas() {
		return lineas;
	}

	public void setLineas(Vector<Material> lineas) {
		this.lineas = lineas;
	}

	public boolean agregaMaterial(Material mat, int cantidad, int idItem) {
		boolean agregar = true;
		if (!lineas.isEmpty()) {
			for (Material linea : lineas) {
				if (linea.equals(mat)) {
					agregar = false;
				}
			}
		}

		if (agregar) {
			LineaMaterial it = new LineaMaterial();
			it.setCantidadNecesaria(cantidad);
			it.setIdLinea(idItem);
			it.setProducto(this.codigoProducto);
			it.setMaterial(mat.getIdMaterial());
			items.add(it);
			lineas.add(mat);
			return true;
		}
		return false;
	}

	public boolean eliminaMaterial(int idmaterial) {
		boolean eliminar = false;
		int arg0 = 0;
		for (Material line : lineas) {
			if (line.getIdMaterial() == idmaterial) {
				items.remove(arg0);
				lineas.remove(arg0);
				return true;
			}
			arg0++;
		}

		return false;
	}

	public Vector<LineaMaterial> getItems() {
		return items;
	}

	public void setItems(Vector<LineaMaterial> items) {
		this.items = items;
	}

	/**
	 * Este metodo debuelve el costo total de produccion del producto actualizado al
	 * momento del calculo
	 */
	public void generaSuma() {
		float total = 0;
		if (items != null) {

			if (!lineas.isEmpty() && !items.isEmpty()) {
				for (int i = 0; i < lineas.size(); i++) {
					for (int j = 0; j < items.size(); j++) {
						if (lineas.get(i).getIdMaterial() == items.get(j).getMaterial()) {
							total = items.get(i).getCantidadNecesaria() * lineas.get(i).getPrecioActual() + total;

						}
					}

				}
				total = total + this.getHorasHombre() * this.precioHoraHombre;
				costoTotal = total;
			}
		}
		if (lineas != null) {

		}

	}
/**
 * Este metodo devuelve elcosto de produccion segun se va calculando
 * @return
 */
	public float getCostoTotal() {
		if (costoTotal == 0) {
			generaSuma();
		}
		return costoTotal;
	}

	public void setCostoTotal(float costoTotal) {
		this.costoTotal = costoTotal;
	}

}
