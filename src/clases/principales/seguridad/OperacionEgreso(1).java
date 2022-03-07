package clases.principales.seguridad;

import java.sql.Date;

import clases.principales.pedido.Pedido;

public class OperacionEgreso {
	private Usuario usuario;
	private Date fechaOp;
	private Pedido pedido;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaOp() {
		return fechaOp;
	}

	public void setFechaOp(Date fechaOp) {
		this.fechaOp = fechaOp;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
