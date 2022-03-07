package clases.principales.seguridad;

import java.sql.Date;

import clases.principales.pedido.Pedido;

public class OperacionEgreso {
	private int idcontacto;
	private int idOperacion;
	private int idUsuario;
	private Date fecha;
	private int idPresupuesto;
	private int idPedido;
	public int getIdcontacto() {
		return idcontacto;
	}
	public void setIdcontacto(int idcontacto) {
		this.idcontacto = idcontacto;
	}
	public int getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(int idOperacion) {
		this.idOperacion = idOperacion;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getIdPresupuesto() {
		return idPresupuesto;
	}
	public void setIdPresupuesto(int idPresupuesto) {
		this.idPresupuesto = idPresupuesto;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
}
