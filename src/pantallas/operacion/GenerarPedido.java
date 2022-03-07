package pantallas.operacion;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import clases.coneccion.Fachada;
import clases.principales.cliente.Cliente;
import clases.principales.cliente.Contacto;
import clases.principales.pedido.Pedido;
import clases.principales.pedido.Presupuesto;
import clases.principales.seguridad.Usuario;
import pantallas.componenteGenerico.MyTableModel;

public class GenerarPedido extends JDialog implements ActionListener, MouseListener {

	private final JPanel contentPanel = new JPanel();

	private Date hora;
	private Timer timer;
	private JDateChooser fecha;
	private JTextField txthora;
	private JButton btnGenerarPedido;
	private JButton btnSalir;
	private MyTableModel modelo;
	private Vector<Presupuesto> pres;
	private Vector<Cliente> clientes;
	private JTable table;
	private JComboBox<String> cBEmpleado;
	private Usuario usuario;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private Fachada fac;
	private NumberFormat flotante;
	private TableRowSorter<MyTableModel> ordenador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Timer time = null;
			GenerarPedido dialog = new GenerarPedido(time, null);

			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GenerarPedido(Timer time, Usuario usu) {

		hora = new Date();

		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sn = new SimpleDateFormat("HH:mm:ss");
				hora.setTime(System.currentTimeMillis());

				txthora.setText(sn.format(hora));
				// System.out.println(sn.format(hora));
				fecha.setDate(hora);
			}
		});
		timer.start();

		if (usu != null) {
			usuario = usu;

		}

		setTitle("Generar pedidos");
		setBounds(100, 100, 795, 579);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		fecha = new JDateChooser();
		fecha.setDateFormatString("dd-MM-yyyy");
		fecha.getCalendarButton().setEnabled(false);
		fecha.setBounds(572, 13, 97, 22);
		fecha.setEnabled(false);
		fecha.setOpaque(false);
		fecha.getDateEditor().setEnabled(false);
		contentPanel.add(fecha);

		txthora = new JTextField();
		txthora.setOpaque(false);
		txthora.setEditable(false);
		txthora.setBounds(681, 13, 60, 22);
		contentPanel.add(txthora);
		txthora.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Elija Presupuesto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 49, 763, 421);
		contentPanel.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 64, 739, 357);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblAutoriza = new JLabel("Autoriza");
		lblAutoriza.setBounds(34, 35, 56, 16);
		panel.add(lblAutoriza);

		cBEmpleado = new JComboBox();
		cBEmpleado.setBounds(102, 31, 122, 24);
		panel.add(cBEmpleado);

		Panel panel_1 = new Panel();
		panel_1.setBounds(12, 490, 765, 48);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		btnGenerarPedido = new JButton("Generar pedido");

		btnGenerarPedido.setBounds(330, 13, 99, 25);
		panel_1.add(btnGenerarPedido);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(663, 13, 99, 25);
		panel_1.add(btnSalir);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(46, 13, 56, 16);
		contentPanel.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setOpaque(false);
		txtUsuario.setFocusable(false);
		txtUsuario.setEditable(false);
		if (usuario != null) {
			obtenerUsuarios();

			txtUsuario.setText(usuario.getNombreUsuario());
		} else {
			usuario = new Usuario();
			usuario.setIdpersona(1);
			txtUsuario.setText("Usuario");
		}
		txtUsuario.setBounds(137, 13, 116, 22);
		contentPanel.add(txtUsuario);
		txtUsuario.setColumns(10);
		cargaModeloTabla();
		cargaListeners();
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void obtenerUsuarios() {
		if (fac==null) {
			fac=new Fachada();
			
		}
		Vector<Usuario> usuarios=fac.selectUsuario();
		Usuario name = new Usuario();
		for (Usuario usuario : usuarios) {
			if (usuario.getNombreUsuario().equals(this.usuario.getNombreUsuario())) {
				name= usuario;
			}
		}
		
		this.usuario=name;
	}

	private void cargaListeners() {
		btnSalir.addActionListener(this);
		table.addMouseListener(this);
		btnGenerarPedido.addActionListener(this);
	}

	private void formatearDatos() {
		flotante = NumberFormat.getInstance(Locale.ENGLISH);
		flotante.setMaximumFractionDigits(2);
		flotante.setMaximumFractionDigits(2);
		flotante.setParseIntegerOnly(false);
		flotante.setMinimumFractionDigits(2);
	}

	private void generarPresupuesto() {
		// TODO Auto-generated method stub
		Pedido name = new Pedido();
		Presupuesto presu = buscaPresupuesto();
		java.util.Date fecha = new java.util.Date();//
		Date fecEntreg = sumaDias(fecha, presu.getDias());
		java.sql.Date fecsql = java.sql.Date.valueOf(LocalDate.now());

		name.setFechaAlta(fecsql);

		fecsql = new java.sql.Date(hora.getTime());
		name.setFechaEntrega(fecsql);
		name.setPresupuesto(presu);
		name.setSumaTotal(presu.getCosto());

		if (fac.generaPedido(name, usuario)) {
			JOptionPane.showMessageDialog(null, "Pedido generado", "Operación satisfactoria", 1);
		}
		cargaModeloTabla();
	}

	private void actualizarPres() {
		Object[] options = { "Eliminar", "Actualizar", "Cancelar" };
		int i = JOptionPane.showOptionDialog(null, "Desea Eliminarlo o Actualizarlo", "Pedido desactualizado",
				JOptionPane.YES_NO_OPTION, 2, null, options, options[0]);

		if (i == 0) {

			JOptionPane.showMessageDialog(null, "Pedido eliminado", "Elimina Pedido ", 2);
			eliminarPresupuesto();

		} else if (i == 1) {

			presupuestoActualizador();

		}
	}

	private void presupuestoActualizador() {
		Presupuesto presu = buscaPresupuesto();
		if (Float.valueOf(table.getValueAt(table.getSelectedRow(), 4).toString()) != presu.autoCalculaTotal()) {
			if (fac.actualizaPresupuesto(presu)) {
				JOptionPane.showMessageDialog(null, "Pedido  actualizado", "Actuliza Pedido ", 2);

				cargaModeloTabla();
			} else {
				JOptionPane.showMessageDialog(null, "Error de actualizacion", "Actuliza Pedido ", 0);
			}
		}
		System.out.println("CAlculo presupuesto  " + presu.autoCalculaTotal());

	}

	private void eliminarPresupuesto() {
		Presupuesto presu = buscaPresupuesto();
		presu.setFlagBaja(0);
		fac.eliminaPresupuesto(presu.getIdPresupuesto());
		cargaModeloTabla();
	}

	/**
	 * devuelve el presupuesto seleccionado en la tabla si no lo encuentra devuelve
	 * null
	 * 
	 * @return
	 */
	private Presupuesto buscaPresupuesto() {
		for (Presupuesto presupuesto : pres) {
			if (presupuesto.getIdPresupuesto() == Integer
					.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString())) {
				System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
				return presupuesto;
			}
		}
		return null;
	}

	private boolean validaFecha() {
		LocalDate name = LocalDate.parse(table.getValueAt(table.getSelectedRow(), 1).toString());
		java.sql.Date fecha = java.sql.Date.valueOf(name);
		long startTime = fecha.getTime();
		fecha = java.sql.Date.valueOf(LocalDate.now());
		long endTime = fecha.getTime();
		long diffTime = endTime - startTime;
		int num = (int) TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
		System.out.println(num);
		if (num > 15) {
			return false;

		}
		return true;
	}

	private void cargaAutorizado() {
		// TODO Auto-generated method stub
		if (table.getSelectedRow() >= 0) {
			if (clientes == null) {
				clientes = (Vector<Cliente>) fac.selectCliente();
			}
			if (cBEmpleado.getItemCount() > 0) {
				cBEmpleado.removeAllItems();
			}
			for (Cliente cliente : clientes) {
				if (cliente.getFlagBaja() == 1) {

					if (table.getValueAt(table.getSelectedRow(), 2).toString().equals(cliente.getRazonSocial())) {

						for (Contacto contacto : cliente.getContacto()) {

							if (contacto.getFlagBaja() == 1 && (contacto.getPermisoOtorgado().equals("Total")
									|| contacto.getPermisoOtorgado().equals("Pedido"))) {
								cBEmpleado.addItem(contacto.getApellido() + " - " + contacto.getNombre());
							}
						}
					}
				}
			}
		}
	}

	private void cargaModeloTabla() {
		// TODO Auto-generated method stub
		if (fac == null) {
			fac = new Fachada();
		}
		pres = fac.selectPresupuesto();
		modelo = new MyTableModel();
		modelo.addColums(new String[] { "Id", "Fecha", "Cliente", "Fecha fin", "Gasto", "Beneficio", "Total fact" });
		table.setModel(modelo);
		ordenador = new TableRowSorter<MyTableModel>(modelo);
		table.setRowSorter(ordenador);
		cargaDatos();
	}

	private void cargaDatos() {

		for (Presupuesto presupuesto : pres) {
			LinkedList<Object> name = new LinkedList<Object>();
			if (presupuesto.getFlagBaja() == 1) {
				name.add(presupuesto.getIdPresupuesto());
				name.add(presupuesto.getFechaAlta());
				name.add(presupuesto.getCliente().getRazonSocial());
				name.add(sumaDias(new Date(), presupuesto.getDias()));

				name.add(presupuesto.getCosto());
				name.add(totalBeneficio(presupuesto));
				name.add(totalFacturado(presupuesto));
				modelo.addRow(name);

			}
		}
		modelo.setCellEditing(false);

		table.setModel(modelo);
		table.repaint();
		table.setSelectionMode(JTable.WHEN_FOCUSED);

	}

	private float totalFacturado(Presupuesto presupuesto) {
		float total = presupuesto.getCosto() + totalBeneficio(presupuesto);
		float redondeo = (float) (Math.round(total * 100) / 100d);
		return redondeo;
	}

	private float totalBeneficio(Presupuesto presupuesto) {
		float tot = (presupuesto.getPorcentage() * presupuesto.getCosto()) / 100;
		float redondeo = (float) (Math.round(tot * 100) / 100d);
		return redondeo;
	}

	private Date sumaDias(Date fechaAlta, int dias) {
		Calendar sumaResta = Calendar.getInstance();
		sumaResta.setTime(fechaAlta);
		sumaResta.add(Calendar.DAY_OF_YEAR, dias);
		return sumaResta.getTime();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnSalir) {
			dispose();
		}
		if (e.getSource() == btnGenerarPedido) {
			if (table.getSelectedRow() >= 0) {

				if (validaFecha()) {
					generarPresupuesto();
				} else {
					Object[] options = { "Actualizar", "Generar", "Cancelar" };
					int i = JOptionPane.showOptionDialog(null, "Desea Actualizar o Generar",
							"Pedido  desactualizado", JOptionPane.YES_NO_OPTION, 2, null, options, options[0]);

					System.out.println(i);
					if (i == 0) {
						actualizarPres();
					} else if (i == 1) {
						generarPresupuesto();
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Seleccione un Pedido ", "No ha seleccionado Pedido ", 2);
			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (table == arg0.getSource()) {
			cargaAutorizado();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
