package clases.principales.pedido;

import java.sql.Date;

public class Pedido {
	private int idPedido;
	private Date fechaAlta;
	private Date fechaEntrega;
	private float sumaTotal;
	private Presupuesto presupuesto;
	private int flagBaja;
	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public float getSumaTotal() {
		return sumaTotal;
	}

	public void setSumaTotal(float sumaTotal) {
		this.sumaTotal = sumaTotal;
	}

	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}

	public int getFlagBaja() {
		return flagBaja;
	}

	public void setFlagBaja(int flagBaja) {
		this.flagBaja = flagBaja;
	}

}
