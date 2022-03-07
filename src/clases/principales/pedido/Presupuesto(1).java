package clases.principales.pedido;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Vector;

import javax.print.DocFlavor.STRING;

import com.sun.glass.ui.Timer;

import clases.principales.cliente.Cliente;
import clases.principales.cliente.Contacto;
import clases.principales.productos.Producto;

public class Presupuesto {
	private int idPresupuesto;
	private Date fechaAlta;
	private float sumaTotal;
	private int operariosFabricacion;
	private int flagBaja;
	private Vector<LineaPresupuesto> lineaPresupueso;
	private Vector<Producto> producto;
	private Time hora;
	private Cliente cliente;
	private Contacto contacto;

	private int dias;
	private int porcentage;
	private float costo;

	public Presupuesto() {
		lineaPresupueso = new Vector<LineaPresupuesto>();
		producto = new Vector<Producto>();
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public int getPorcentage() {
		return porcentage;
	}

	public void setPorcentage(int porcentage) {
		this.porcentage = porcentage;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public int getIdPresupuesto() {
		return idPresupuesto;
	}

	public void setIdPresupuesto(int idPresupuesto) {
		this.idPresupuesto = idPresupuesto;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public float getSumaTotal() {
		return sumaTotal;
	}

	public void setSumaTotal(float sumaTotal) {
		this.sumaTotal = sumaTotal;
	}

	public int getOperariosFabricacion() {
		return operariosFabricacion;
	}

	public void setOperariosFabricacion(int operariosFabricacion) {
		this.operariosFabricacion = operariosFabricacion;
	}

	public int getFlagBaja() {
		return flagBaja;
	}

	public void setFlagBaja(int flagBaja) {
		this.flagBaja = flagBaja;
	}

	public Vector<LineaPresupuesto> getLineaPresupueso() {
		return lineaPresupueso;
	}

	public void setLineaPresupueso(Vector<LineaPresupuesto> lineaPresupueso) {
		this.lineaPresupueso = lineaPresupueso;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public void setHora(String hora) {

		try {
			java.util.Date date = new SimpleDateFormat("HH:mm").parse(hora);
			this.hora = new Time(date.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Vector<Producto> getProducto() {
		return producto;
	}

	public void setProducto(Vector<Producto> producto) {
		this.producto = producto;
	}

	public void addProducto(Producto producto, LineaPresupuesto linea) {
		lineaPresupueso.add(linea);
		this.producto.add(producto);
	}

	public void setFecha(String fecha) {

		try {
			java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
			this.fechaAlta = new Date(date.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
