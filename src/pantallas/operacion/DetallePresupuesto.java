package pantallas.operacion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.text.DateFormatter;

import com.toedter.calendar.JDateChooser;

import clases.principales.pedido.LineaPresupuesto;
import clases.principales.pedido.Pedido;
import clases.principales.pedido.Presupuesto;
import clases.principales.seguridad.Usuario;
import pantallas.componenteGenerico.MyTableModel;
import reportes.GeneraReporte;
import reportes.ListaPedidos;

public class DetallePresupuesto extends JInternalFrame implements ActionListener {
	private JButton btnImprimir;
	private JButton btnCancelar;
	private JScrollPane scrollPane;
	private JTable table;
	private Presupuesto presupuesto;
	private MyTableModel mymodel;
	private PantallaPresListado pantallaPresListado;
	private JTextField txtTotalpres;
	private JLabel lblUsuario;
	private JTextField txtEmpresa;
	private JLabel lblFechaVencimiento;
	private JDateChooser dCvencimiento;
	private JTextField txtEmpleado;
	private Usuario usuario;
	private JLabel lblFechaDeAlta;
	private JDateChooser dCAlta;
	private Date actual;
	private ListaPedidos listadoPedidos;
	private Pedido pedido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario ame = new Usuario();
					DetallePresupuesto frame = new DetallePresupuesto(ame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param usuario2 
	 */
	public DetallePresupuesto(Usuario usuario2) {
		setResizable(true);
		String us = "Empleado";
		if (usuario2 != null) {
			usuario = usuario2;
		}
		actual = new Date();
//		Timer name = new Timer(1000, null);

		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sn = new SimpleDateFormat("HH:mm:ss");
				actual.setTime(System.currentTimeMillis());
				if (listadoPedidos != null) {
					setTitle("Pedido, Usuario: " + usuario.getNombreUsuario() + "  "
							+ Date.from(Instant.now(Clock.system(ZoneId.systemDefault()))));
				} else {
					setTitle("Presupuesto, Usuario: " + usuario.getNombreUsuario() + "  "
							+ Date.from(Instant.now(Clock.system(ZoneId.systemDefault()))));
				}
			}
		});
		timer.start();
		System.out.println("se ejecuta");
		setBounds(100, 100, 616, 360);
		getContentPane().setLayout(null);

		btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(270, 287, 99, 25);
		getContentPane().add(btnImprimir);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(382, 287, 99, 25);
		getContentPane().add(btnCancelar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 71, 586, 176);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		txtTotalpres = new JTextField();
		txtTotalpres.setText("0");
		txtTotalpres.setBounds(482, 252, 116, 22);
		getContentPane().add(txtTotalpres);
		txtTotalpres.setColumns(10);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(412, 258, 56, 16);
		getContentPane().add(lblTotal);

		lblUsuario = new JLabel("Empresa");
		lblUsuario.setBounds(12, 13, 56, 16);
		getContentPane().add(lblUsuario);

		txtEmpresa = new JTextField();
		txtEmpresa.setText("Nombre");
		txtEmpresa.setBounds(100, 10, 116, 22);
		getContentPane().add(txtEmpresa);
		txtEmpresa.setColumns(10);

		lblFechaVencimiento = new JLabel("Fecha vencimiento");
		lblFechaVencimiento.setBounds(270, 42, 116, 16);
		getContentPane().add(lblFechaVencimiento);

		dCvencimiento = new JDateChooser();
		dCvencimiento.setEnabled(false);
		dCvencimiento.setBounds(445, 36, 107, 22);
		getContentPane().add(dCvencimiento);

		JLabel lblAutorizado = new JLabel("Autorizado ");
		lblAutorizado.setBounds(12, 42, 73, 16);
		getContentPane().add(lblAutorizado);

		txtEmpleado = new JTextField();
		txtEmpleado.setText("Empleado");
		txtEmpleado.setBounds(100, 39, 116, 22);
		getContentPane().add(txtEmpleado);
		txtEmpleado.setColumns(10);

		lblFechaDeAlta = new JLabel("Fecha de Alta");
		lblFechaDeAlta.setBounds(270, 13, 116, 16);
		getContentPane().add(lblFechaDeAlta);

		dCAlta = new JDateChooser();
		dCAlta.setEnabled(false);
		dCAlta.setBounds(445, 7, 107, 22);
		getContentPane().add(dCAlta);
		toFront();
		cargaListener();
	}

	private void cargaListener() {
//		botones
		btnCancelar.addActionListener(this);
		btnImprimir.addActionListener(this);
	}

	public void addPresupuesto(Presupuesto presupuesto, PantallaPresListado pantallaPresListado) {
		// TODO Auto-generated method stub
		this.pantallaPresListado = pantallaPresListado;
		this.presupuesto = presupuesto;
		cargaTabla();
	}

	public void addPedido(Pedido pedido, ListaPedidos listaPedido) {
		this.listadoPedidos = listaPedido;
		this.pedido = pedido;
		this.presupuesto = pedido.getPresupuesto();

		cargaTabla();
	}

	private void cargaTabla() {
		// TODO Auto-generated method stub
		mymodel = new MyTableModel();
		mymodel.setCellEditing(false);
		mymodel.addColums(new String[] { "Id", "Descripción", "Interior", "Cantidad", "Precio U", "Subtotal" });
		int i = 1;

		for (LineaPresupuesto producto : presupuesto.getLineaPresupueso()) {
			String descripcion = producto.getProducto().getModelo() + " - " + producto.getProducto().getVoltamperios();
			boolean inter = false;
			if (producto.getProducto().getLugarUtilizacion().equalsIgnoreCase("IN")) {
				inter = true;
			}
			JCheckBox interior = new JCheckBox();
			interior.setSelected(inter);
//			float genera= producto.getProducto();
			float precioUnitario = (producto.getProducto().getCostoTotal()
					* ((float) presupuesto.getPorcentage() / 100)) + producto.getProducto().getCostoTotal();
			System.out.println(producto.getProducto().getCostoTotal());
			float subtotal = producto.getCantidad() * precioUnitario;
			Object[] objeto = { i, descripcion, new Boolean(inter), producto.getCantidad(), precioUnitario, subtotal,
					new JButton("Clic aquí") };
			mymodel.addRow(objeto);
			i++;
			float sub = Float.valueOf(txtTotalpres.getText());
			txtTotalpres.setText(String.valueOf(sub + subtotal));
		}
		table.setModel(mymodel);
		dCAlta.setDate(presupuesto.getFechaAlta());
		Calendar fechamasvencimiento = Calendar.getInstance();
		fechamasvencimiento.setTime(presupuesto.getFechaAlta());
		fechamasvencimiento.add(Calendar.DAY_OF_MONTH, 15);
		dCvencimiento.setDate(fechamasvencimiento.getTime());
		dCAlta.setOpaque(false);
		dCAlta.getDateEditor().getUiComponent().setBackground(Color.WHITE);
//		dCAlta.getDateEditor().getUiComponent().setOpaque(false);
		dCvencimiento.getDateEditor().getUiComponent().setBackground(Color.WHITE);
		dCvencimiento.setOpaque(false);
		txtEmpleado.setText(presupuesto.getContacto().getNombre() + " - " + presupuesto.getContacto().getApellido());
		txtEmpleado.setEditable(false);
		txtEmpresa.setText(presupuesto.getCliente().getRazonSocial());
		txtEmpresa.setEditable(false);
		txtTotalpres.setEditable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			if (listadoPedidos == null) {
				pantallaPresListado.setInstanciasJinternal(0);
				;
				this.dispose();
			} else {
				listadoPedidos.setInstanciasJinternal(0);
				this.dispose();
			}

		}
		if (e.getSource() == btnImprimir) {
			imprimir();
		}

	}

	private void imprimir() {
		// TODO Auto-generated method stub
		GeneraReporte reporte = new GeneraReporte();
		DateFormat formatoh = new SimpleDateFormat("ddMMyyyyHHmmss");
		String nombre = "";

		if (pedido == null) {
			nombre = "Presupuesto" + formatoh.format(actual).toString();
			reporte.createPDF(nombre, "Presupuesto Nº " + presupuesto.getIdPresupuesto());

		} else {
			nombre = "Pedido" + formatoh.format(actual).toString();
			reporte.createPDF(nombre, "Pedido Nº " + pedido.getIdPedido());

		}
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		System.out.println(nombre + " " + formatoh.format(actual).toString());
		if (table.getModel() != null) {
			// float[] tamanio = { 2f, 4.5f, 5f, 7f, 3f, 3f, 5f, 5f, 5f, 3f };
			LinkedList<String> columnas = new LinkedList<String>();
			LinkedList<LinkedList<String>> filas = new LinkedList<LinkedList<String>>();
			for (int i = 0; i < table.getColumnCount(); i++) {
				columnas.add(table.getColumnName(i));
			}
			for (int f = 0; f < table.getRowCount(); f++) {
				LinkedList<String> celdas = new LinkedList<String>();
				for (int c = 0; c < table.getColumnCount(); c++) {
					if (table.getValueAt(f, c).toString().equals("true")) {
						celdas.add("Interior");
					}else {
						if (table.getValueAt(f, c).toString().equals("false")) {
							celdas.add("Exterior");
						}else {
							celdas.add(table.getValueAt(f, c).toString());
						}
					}
					
				}
				filas.add(celdas);
			}
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Object[][] name = {
					{ "Usuario ", usuario.getNombreUsuario(), "Fecha ", DateFormat.getDateInstance().format(actual) },
					{ "Empresa ", txtEmpresa.getText(), "Fecha alta ",
							DateFormat.getInstance().format(dCAlta.getDate()) },
					{ "Persona autorizada", txtEmpleado.getText(), "Fecha de vencimiento ",
							DateFormat.getInstance().format(dCvencimiento.getDate()) } };
			reporte.setEncabezado(
					"Presupuesto " + presupuesto.getIdPresupuesto() + " " + format.format(actual).toString(), name,0);
			reporte.setTabla(columnas, filas, null,-1);
			reporte.setPie("Total Presupuestado", txtTotalpres.getText(), 1);
//			reporte.setPie("Beneficio",txtBeneficio.getText() ,1);
//			reporte.setPie("Total",txtTotalfacturado.getText() ,1);
		}
		if (!reporte.print()) {
			JOptionPane.showMessageDialog(null, "No se pudo realizar", "Error en impresión", 0);
		} else {
			JOptionPane.showMessageDialog(null, "Operación satisfactoria ", "Impresión realizada", 2);

		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
