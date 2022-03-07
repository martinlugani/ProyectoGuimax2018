package reportes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableRowSorter;

import com.itextpdf.text.Rectangle;
import com.toedter.calendar.JDateChooser;

import clases.coneccion.Fachada;
import clases.principales.cliente.Cliente;
import clases.principales.cliente.Contacto;
import clases.principales.pedido.Pedido;
import clases.principales.seguridad.Usuario;
import pantallas.componenteGenerico.EliminaCientifica;
import pantallas.componenteGenerico.MyTableModel;
import pantallas.operacion.DetallePresupuesto;
import java.awt.ComponentOrientation;

public class ListaPedidos extends JDialog
		implements ActionListener, MouseListener, ChangeListener, PropertyChangeListener {

	private final JPanel contentPanel = new JPanel();
	private JSpinner totHasta;
	private JCheckBox chentre;
	private JCheckBox chRazon;
	private JCheckBox chdias;
	private JCheckBox chEstado;
	private JDateChooser dtDEsde;
	private JDateChooser dtHasta;
	private JComboBox cbRazon;
	private JSpinner diasDesde;
	private JSpinner diasHasta;
	private JCheckBox chOpe;
	private JSpinner opDesde;
	private JSpinner opHasta;
	private JSpinner totDesde;
	private JSpinner benHasta;
	private JButton btnImprimir;
	private JButton btnSalir;
	private JCheckBox chBenef;
	private JCheckBox chTotal;
	private JSpinner benDesde;
	private JTextField usuario;
	private JTextField fecha;
	private JTextField txthora;
	private JTextField costo;
	private JTextField beneficio;
	private JTextField total;
	private JTable table;
	private Fachada fac;
	private Date hora;
	private Timer timer;
	private MyTableModel model;
	private TableRowSorter<MyTableModel> sorter;
	private JLabel lblPendiente;
	private int esta = 0;
	private Iterable<Cliente> carga;
	private int instanciasJinternal = 0;
	private JDesktopPane desktopPane;
	private Vector<Pedido> pedidos;
	private Usuario usu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Usuario usua = null;
			ListaPedidos dialog = new ListaPedidos(usua);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaPedidos(Usuario usua) {
		hora = new Date();
		model = new MyTableModel();
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sn = new SimpleDateFormat("HH:mm:ss");
				hora.setTime(System.currentTimeMillis());
				SimpleDateFormat fechafor = new SimpleDateFormat("dd/MM/yyyy");
				txthora.setText(sn.format(hora).toString());
				// System.out.println(sn.format(hora));
				fecha.setText(fechafor.format(hora).toString());
			}
		});
		if (usua == null) {
			usu = new Usuario();
			usu.setNombreUsuario("Default");

		} else {
			usu = usua;
		}
		timer.start();
		fac = new Fachada();
		setTitle("Listado pedido");
		setBounds(100, 100, 842, 748);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Seleccione filtro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 805, 279);
		contentPanel.add(panel);
		panel.setLayout(null);

		chentre = new JCheckBox("Entre");
		chentre.setBounds(19, 31, 55, 25);
		panel.add(chentre);

		dtDEsde = new JDateChooser();
		dtDEsde.setOpaque(false);
		dtDEsde.getCalendarButton().setToolTipText("Fecha Superior");
		dtDEsde.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		dtDEsde.setDateFormatString("dd-MM-yyyy");
		dtDEsde.setBounds(186, 34, 107, 22);
		panel.add(dtDEsde);

		dtHasta = new JDateChooser();
		dtHasta.setOpaque(false);
		dtHasta.getCalendarButton().setToolTipText("Fecha Superior");
		dtHasta.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		dtHasta.setDateFormatString("dd-MM-yyyy");
		dtHasta.setBounds(299, 34, 107, 22);
		panel.add(dtHasta);

		chRazon = new JCheckBox("Razon social");
		chRazon.setBounds(19, 94, 97, 25);
		panel.add(chRazon);

		cbRazon = new JComboBox();
		cbRazon.setBounds(186, 94, 107, 24);
		panel.add(cbRazon);

		chdias = new JCheckBox("Dias de produccion");
		chdias.setBounds(19, 164, 133, 25);
		panel.add(chdias);

		diasDesde = new JSpinner();
		diasDesde.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(5)));
		diasDesde.setBounds(186, 166, 107, 20);
		panel.add(diasDesde);

		diasHasta = new JSpinner();
		diasHasta.setModel(new SpinnerNumberModel(new Integer(100), new Integer(0), null, new Integer(5)));
		diasHasta.setBounds(299, 166, 105, 20);
		panel.add(diasHasta);

		chEstado = new JCheckBox("Por estado");
		chEstado.setBounds(19, 229, 87, 25);
		panel.add(chEstado);

		chOpe = new JCheckBox("Operarios");
		chOpe.setBounds(420, 31, 144, 25);
		panel.add(chOpe);

		opHasta = new JSpinner();
		opHasta.setModel(new SpinnerNumberModel(10, 0, 10, 1));
		opHasta.setBounds(682, 33, 107, 20);
		panel.add(opHasta);

		opDesde = new JSpinner();
		opDesde.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		opDesde.setBounds(569, 33, 107, 20);
		panel.add(opDesde);

		chTotal = new JCheckBox("Total presupestado");
		chTotal.setBounds(420, 94, 149, 25);
		panel.add(chTotal);

		totDesde = new JSpinner();
		totDesde.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(10000)));
		totDesde.setBounds(569, 96, 107, 20);
		panel.add(totDesde);

		totHasta = new JSpinner();
		totHasta.setModel(new SpinnerNumberModel(new Integer(1000000), new Integer(0), null, new Integer(10000)));
		totHasta.setBounds(682, 96, 107, 20);
		panel.add(totHasta);

		benHasta = new JSpinner();
		benHasta.setModel(new SpinnerNumberModel(new Integer(500000), new Integer(0), null, new Integer(10000)));
		benHasta.setBounds(682, 166, 107, 20);
		panel.add(benHasta);

		benDesde = new JSpinner();
		benDesde.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(10000)));
		benDesde.setBounds(569, 166, 107, 20);
		panel.add(benDesde);

		chBenef = new JCheckBox("Beneficio");
		chBenef.setBounds(420, 164, 135, 25);
		panel.add(chBenef);

		lblPendiente = new JLabel("Todos");
		lblPendiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				switch (esta) {
				case -1:
					esta = 0;
					lblPendiente.setText("Pendiente");
					break;
				case 0:
					esta = 1;
					lblPendiente.setText("Produccion");
					break;
				case 1:
					esta = 2;
					lblPendiente.setText("Terminados");
					break;
				case 2:
					esta = -1;
					lblPendiente.setText("Todos");
					break;

				}
				filtro();

			}
		});
		lblPendiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblPendiente.setBounds(186, 233, 107, 16);
		panel.add(lblPendiente);

		btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(561, 679, 99, 25);
		contentPanel.add(btnImprimir);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(691, 679, 99, 25);
		contentPanel.add(btnSalir);

		desktopPane = new JDesktopPane();
		desktopPane.setBounds(12, 303, 805, 363);
		contentPanel.add(desktopPane);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(
				new TitledBorder(null, "Datos a imprimir", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 0, 805, 366);
		desktopPane.add(panel_1);

		JLabel label = new JLabel("Usuario");
		label.setBounds(12, 28, 56, 16);
		panel_1.add(label);

		usuario = new JTextField();
		usuario.setEditable(false);
		usuario.setText("Usuario");
		usuario.setColumns(10);
		usuario.setBounds(172, 25, 116, 22);
		panel_1.add(usuario);

		JLabel label_1 = new JLabel("Fecha");
		label_1.setBounds(429, 28, 56, 16);
		panel_1.add(label_1);

		fecha = new JTextField();
		fecha.setEditable(false);
		fecha.setText("fecha");
		fecha.setColumns(10);
		fecha.setBounds(497, 25, 71, 22);
		panel_1.add(fecha);

		JLabel label_2 = new JLabel("Hora");
		label_2.setBounds(603, 28, 56, 16);
		panel_1.add(label_2);

		txthora = new JTextField();
		txthora.setEditable(false);
		txthora.setText("hora");
		txthora.setColumns(10);
		txthora.setBounds(671, 25, 62, 22);
		panel_1.add(txthora);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(12, 78, 771, 177);
		panel_1.add(panel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 771, 177);
		panel_2.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		costo = new JTextField();
		costo.setHorizontalAlignment(SwingConstants.RIGHT);
		costo.setOpaque(false);
		costo.setEditable(false);
		costo.setColumns(10);
		costo.setBounds(667, 268, 116, 22);
		panel_1.add(costo);

		beneficio = new JTextField();
		beneficio.setHorizontalAlignment(SwingConstants.RIGHT);
		beneficio.setOpaque(false);
		beneficio.setEditable(false);
		beneficio.setColumns(10);
		beneficio.setBounds(667, 303, 116, 22);
		panel_1.add(beneficio);

		total = new JTextField();
		total.setHorizontalAlignment(SwingConstants.RIGHT);
		total.setOpaque(false);
		total.setEditable(false);
		total.setColumns(10);
		total.setBounds(667, 337, 116, 22);
		panel_1.add(total);

		JLabel label_3 = new JLabel("Costo");
		label_3.setBounds(557, 268, 56, 16);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel("Beneficio");
		label_4.setBounds(557, 306, 56, 16);
		panel_1.add(label_4);

		JLabel label_5 = new JLabel("Total");
		label_5.setBounds(557, 343, 56, 16);
		panel_1.add(label_5);
		agregalsiteners();
		cargaTabla();
		cargaFecha();
		cargaCombo();
		usuario.setText(usu.getNombreUsuario());
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void cargaCombo() {
		// TODO Auto-generated method stub
		carga = fac.selectCliente();
		for (Cliente cliente : carga) {
			cbRazon.addItem(cliente.getRazonSocial());
		}
	}

	public void setInstanciasJinternal(int instanciasJinternal) {
		this.instanciasJinternal = instanciasJinternal;
	}

	private void cargaFecha() {

		Date fechaHasta = new Date();

		dtDEsde.getDateEditor().setEnabled(false);
		dtDEsde.getDateEditor().getUiComponent().setBackground(Color.WHITE);
		dtDEsde.setDate(fechaHasta);
		dtHasta.setDate(fechaHasta);
		dtHasta.getDateEditor().setEnabled(false);
		dtHasta.getDateEditor().getUiComponent().setBackground(Color.WHITE);
		seteoDesdeHasta(fechaHasta);

	}

	private void seteoDesdeHasta(Date fechaHasta) {
		dtDEsde.setMaxSelectableDate(fechaHasta);
		dtHasta.setMaxSelectableDate(fechaHasta);
		dtHasta.setMinSelectableDate(dtDEsde.getDate());

	}

	private void cargaTabla() {
		// TODO Auto-generated method stub
		if (fac == null) {
			fac = new Fachada();
		}

		String[] columnas = { "Id", "Fecha Alta", "Cliente", "Contacto", "Fecha Fin", "Operarios", "Costo prod",
				"Beneficio", "Total", "Estado", "Dias fin" };
		model.addColums(columnas);
		cargaCeldas();

	}

	private void cargaCeldas() {
		// TODO Auto-generated method stub
		Vector<Contacto> contacs = fac.selectContacto();
		pedidos = fac.selectPedidos();
		LinkedList<Object> celdas = new LinkedList<Object>();
		for (Pedido pedido : pedidos) {
			float por = pedido.getPresupuesto().getPorcentage();
			String con = null;
			Vector<Vector<Object>> contacto = fac.selectString(
					"SELECT idcontacto FROM operacionautoriza WHERE idpedido = " + pedido.getIdPedido() + " ;");
			for (Contacto contacto2 : contacs) {
				if (contacto2.getIdpersona() == Integer.valueOf(contacto.get(0).get(0).toString())) {
					con = contacto2.getApellido() + " - " + contacto2.getNombre();
				}
			}

			double beneficio = pedido.getPresupuesto().getCosto() * (por / 100);
			double subtotal = beneficio + pedido.getPresupuesto().getCosto();
//		beneficio= EliminaCientifica.eliminaDoble(beneficio);
//			subtotal=EliminaCientifica.eliminaDoble(subtotal);
			Object[] fila = { pedido.getIdPedido(), pedido.getFechaAlta(),
					pedido.getPresupuesto().getCliente().getRazonSocial(), con, pedido.getFechaEntrega(),
					pedido.getPresupuesto().getOperariosFabricacion(),
					pedido.getPresupuesto().getCosto(),
					beneficio, subtotal, estado(pedido),
					pedido.getPresupuesto().getDias() };

			model.addRow(fila);
		}
		model.setCellEditing(false);
		table.setModel(model);

		sorter = new TableRowSorter<MyTableModel>(model);
		table.setRowSorter(sorter);
		table.setRowSelectionAllowed(false);
		sumas();
	}

	private void sumas() {
		if (table != null) {
			costo.setText(EliminaCientifica.elimina(costototal()));
			beneficio.setText(EliminaCientifica.elimina(beneficioTotal()));
			total.setText(EliminaCientifica.elimina(totalFacturado()));

		}

	}

	private void filtro() {
		// table.setAutoCreateRowSorter(true);

		List<RowFilter<Object, Object>> lista = new ArrayList<RowFilter<Object, Object>>();
		if (chRazon.isSelected()) {
			// agregaraFiltro();

			lista.add(RowFilter.regexFilter(cbRazon.getSelectedItem().toString(), 2));
			System.out.println("paso1111111");
		}
		if (chBenef.isSelected()) {
			lista.add(
					RowFilter.numberFilter(ComparisonType.BEFORE, Integer.parseInt(benDesde.getValue().toString()), 7));
			lista.add(
					RowFilter.numberFilter(ComparisonType.AFTER, Integer.parseInt(benHasta.getValue().toString()), 7));

		}
		new SimpleDateFormat("dd-MM-yyyy");
		if (chentre.isSelected()) {

			Date startDate = new Date(dtDEsde.getDate().getTime());

			Date endDate = new Date(dtHasta.getDate().getTime());

			lista.add(RowFilter.dateFilter(ComparisonType.AFTER, fecha(startDate, -1), 1));
			lista.add(RowFilter.dateFilter(ComparisonType.BEFORE, fecha(endDate, 1), 1));

		}

		if (chOpe.isSelected()) {
			lista.add(RowFilter.numberFilter(ComparisonType.AFTER, Integer.valueOf(opDesde.getValue().toString()), 5));
			lista.add(RowFilter.numberFilter(ComparisonType.BEFORE, Integer.valueOf(opHasta.getValue().toString()), 5));
		}
		if (chTotal.isSelected()) {
			lista.add(RowFilter.numberFilter(ComparisonType.AFTER, Integer.valueOf(totDesde.getValue().toString()), 8));
			lista.add(
					RowFilter.numberFilter(ComparisonType.BEFORE, Integer.valueOf(totHasta.getValue().toString()), 8));
		}
		if (chdias.isSelected()) {
			lista.add(
					RowFilter.numberFilter(ComparisonType.AFTER, Integer.valueOf(diasDesde.getValue().toString()), 10));
			lista.add(RowFilter.numberFilter(ComparisonType.BEFORE, Integer.valueOf(diasHasta.getValue().toString()),
					10));
		}
		if (chEstado.isSelected()) {
			if (esta != -1) {

				String activo = "";
				if (esta == 0) {
					activo = "Fuera de plazo";
				}
				if (esta == 1) {
					activo = "Prod";
				}
				if (esta == 2) {
					activo = "Terminado";

				}
				lista.add(RowFilter.regexFilter(activo, 9));
			}
		}
		if (chOpe.isSelected()) {
			lista.add(RowFilter.numberFilter(ComparisonType.AFTER, Integer.valueOf(opDesde.getValue().toString()), 5));
			lista.add(RowFilter.numberFilter(ComparisonType.BEFORE, Integer.valueOf(opHasta.getValue().toString()), 5));
		}
		if (chTotal.isSelected()) {
			lista.add(RowFilter.numberFilter(ComparisonType.AFTER, Integer.valueOf(totDesde.getValue().toString()), 8));
			lista.add(
					RowFilter.numberFilter(ComparisonType.BEFORE, Integer.valueOf(totHasta.getValue().toString()), 8));
		}
		if (chBenef.isSelected()) {
			lista.add(RowFilter.numberFilter(ComparisonType.AFTER, Integer.valueOf(benDesde.getValue().toString()), 4));
			lista.add(
					RowFilter.numberFilter(ComparisonType.BEFORE, Integer.valueOf(benHasta.getValue().toString()), 4));
		}
		RowFilter filtroAnd = RowFilter.andFilter(lista);

		sorter.setRowFilter(filtroAnd);
		table.setRowSorter(sorter);
		sumas();
	}

	private Date fecha(Date startDate, int i) {
		Calendar sumaResta = Calendar.getInstance();
		sumaResta.setTime(startDate);
		sumaResta.add(Calendar.DAY_OF_YEAR, i);
		return sumaResta.getTime();
	}

	private double totalFacturado() {
		double beneficio = 0;
		for (int f = 0; f < table.getRowCount(); f++) {
			beneficio = beneficio + Double.parseDouble(table.getValueAt(f, 8).toString());
		}
		return beneficio;
	}

	private double beneficioTotal() {
		double beneficio = 0;
		for (int f = 0; f < table.getRowCount(); f++) {
			beneficio = beneficio + Double.parseDouble(table.getValueAt(f, 7).toString());
		}
		return beneficio;
	}

	private double costototal() {
		double costo = 0;
		for (int f = 0; f < table.getRowCount(); f++) {
			costo = costo + Double.parseDouble(table.getValueAt(f, 6).toString());
		}
		return costo;

	}

	private String estado(Pedido pedido) {
		Date name = new Date();

		if (pedido.getFlagBaja() == 1) {
			if (name.after(pedido.getFechaEntrega())) {

				return "Fuera de plazo";
			} else {
				return "Prod";
			}
		} else {
			return "Terminado";
		}
	}

	private void agregalsiteners() {
		// TODO Auto-generated method stub
		btnImprimir.addActionListener(this);
		btnSalir.addActionListener(this);
		table.addMouseListener(this);
		// lblPendiente.addMouseListener(this);
		dtDEsde.addPropertyChangeListener(this);
		dtHasta.addPropertyChangeListener(this);
		chBenef.addActionListener(this);
		chdias.addActionListener(this);
		chentre.addActionListener(this);
		chEstado.addActionListener(this);
		chOpe.addActionListener(this);
		chRazon.addActionListener(this);
		chTotal.addActionListener(this);
		benDesde.addChangeListener(this);
		benHasta.addChangeListener(this);
		diasDesde.addChangeListener(this);
		diasHasta.addChangeListener(this);
		opHasta.addChangeListener(this);
		opDesde.addChangeListener(this);
		totHasta.addChangeListener(this);
		totDesde.addChangeListener(this);
		cbRazon.addActionListener(this);

	}

	private void seleccionPedidoDetalle() {
		// TODO Auto-generated method stub
		System.out.println("entra aca si");
		System.out.println("Id de row " + table.getValueAt(table.getSelectedRow(), 0));
		if (instanciasJinternal == 0) {
			DetallePresupuesto name = new DetallePresupuesto(this.usu);
			int x = (desktopPane.getWidth() / 2) - name.getWidth() / 2;
			int y = (desktopPane.getHeight() / 2) - name.getHeight() / 2;
			if (name.isShowing()) {
				name.setLocation(x, y);
			} else {
				desktopPane.add(name);
				name.setLocation(x, y);
				name.toFront();
				name.setVisible(true);
			}
			instanciasJinternal = 1;
			name.setUsuario(usu);
			enviaPedido(name);
		}
	}

	private void enviaPedido(DetallePresupuesto name) {
		// TODO Auto-generated method stub
		for (Pedido pedido : pedidos) {
			if (table.getValueAt(table.getSelectedRow(), 0) != null) {
				if (Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()) == pedido.getIdPedido()) {
					name.addPedido(pedido, this);

				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == chOpe) {
			filtro();
		}
		if (e.getSource() == dtDEsde.getCalendarButton()) {
			System.out.println("Estoy apretado");

		}
		if (e.getSource() == chRazon) {
			filtro();

		}
		if (chBenef == e.getSource()) {
			filtro();
		}
		if (chentre == e.getSource()) {
			filtro();
		}
		if (chEstado == e.getSource()) {
			filtro();
		}

		if (cbRazon == e.getSource()) {
			if (chRazon.isSelected()) {
				filtro();
			}

		}
		if (e.getSource() == chTotal) {
			filtro();
		}
		if (e.getSource() == chdias) {
			filtro();
		}
		if (e.getSource() == btnSalir) {
			timer.stop();
			this.dispose();

		}
		if (e.getSource() == btnImprimir) {
			imprimir();

		}
	}

	private void imprimir() {
		// TODO Auto-generated method stub
		GeneraReporte reporte = new GeneraReporte();

		DateFormat formatoh = new SimpleDateFormat("HHmmss");
		java.util.Date hora = new java.util.Date();
		String nombre = "Pedidos";
		nombre += formatoh.format(hora).toString();
		System.out.println(nombre);
		reporte.createPDF(nombre, "Lista de pedidos");
		if (table.getModel() != null) {
			float[] tamanio = { 1.6f, 4.5f, 5f, 7f, 5f, 3f, 5f, 5f, 5f, 3f, 2f };
			LinkedList<String> columnas = new LinkedList<String>();
			LinkedList<LinkedList<String>> filas = new LinkedList<LinkedList<String>>();
			for (int i = 0; i < table.getColumnCount(); i++) {
				columnas.add(table.getColumnName(i));
			}
			for (int f = 0; f < table.getRowCount(); f++) {
				LinkedList<String> celdas = new LinkedList<String>();
				for (int c = 0; c < table.getColumnCount(); c++) {
//					if (table.getValueAt(f, c) instanceof Date) {
//					
//					} else {
					celdas.add(table.getValueAt(f, c).toString());
//					}
				}
				filas.add(celdas);
			}
			Vector<Object> fila = new Vector<Object>();
			if (chentre.isSelected()) {
				fila.add("Por fecha");
				fila.add("Si");
				fila.add("Desde " + DateFormat.getInstance().format(dtDEsde));
				fila.add("Hasta" + DateFormat.getInstance().format(dtHasta));
			} else {
				fila.add("Entre");
				fila.add("No");
				fila.add("");
				fila.add("");
			}
			if (chRazon.isSelected()) {
				fila.add("Por empresa");
				fila.add("Si");
				fila.add("");
				fila.add("" + cbRazon.getSelectedItem().toString());
			} else {
				fila.add("Por empresa");
				fila.add("No");
				fila.add("");
				fila.add("");
			}
			if (chdias.isSelected()) {
				fila.add("Por dias de prod");
				fila.add("Si");
				fila.add("Desde " + diasDesde.getValue().toString());
				fila.add("Hasta" + diasHasta.getValue().toString());
			} else {
				fila.add("Por dias de prod");
				fila.add("No");
				fila.add("");
				fila.add("");
			}
			fila.add("Estado");
			if (chEstado.isSelected()) {

				fila.add("Si");
				fila.add("" + lblPendiente.getText());
				fila.add("");
			} else {

				fila.add("No");
				fila.add("");
				fila.add("");
			}
			fila.add("Por operarios");
			if (chOpe.isSelected()) {

				fila.add("Si");
				fila.add("Entre " + opDesde.getValue().toString());
				fila.add(" y " + opHasta.getValue().toString());
			} else {

				fila.add("No");
				fila.add("");
				fila.add("");
			}
			fila.add("Total facturado");
			if (chTotal.isSelected()) {

				fila.add("Si");
				fila.add("Entre " + totDesde.getValue().toString());
				fila.add(" y " + totHasta.getValue().toString());
			} else {

				fila.add("No");
				fila.add("");
				fila.add("");
			}
			fila.add("Beneficio");
			if (chBenef.isSelected()) {

				fila.add("Si");
				fila.add("Entre " + benDesde.getValue().toString());
				fila.add(" y " + benHasta.getValue().toString());
			} else {

				fila.add("No");
				fila.add("");
				fila.add("");
			}
			Object[][] name = new Object[8][4];
			name[0][0] = "Usuario";
			name[0][1] = usuario.getText();
			name[0][2] = "Fecha";
			name[0][3] = fecha.getText();
			int cont = 0;
			for (int f = 1; f < name.length; f++) {
				for (int c = 0; c < name[f].length; c++) {
					name[f][c] = fila.get(cont);
					cont++;
				}
			}

			reporte.setEncabezado("Listado presupuesto", name, 0);
			reporte.setTabla(columnas, filas, tamanio, -1);
			reporte.setPie("Costo Produc", costo.getText(), 1);
			reporte.setPie("Beneficio", beneficio.getText(), 1);
			reporte.setPie("Total", total.getText(), 1);
		}

		if (!reporte.print()) {
			JOptionPane.showMessageDialog(null, "No se pudo realizar", "Error en impresión", 0);
		} else {
			JOptionPane.showMessageDialog(null, "Operación satisfactoria ", "Impresión realizada", 2);

		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		if (lblPendiente!=null) {
//			if ((lblPendiente=arg0.getSource()) != null) {
//				
//			}
//		}

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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (table == e.getSource()) {
			seleccionPedidoDetalle();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == benDesde) {
			if (Integer.parseInt(benHasta.getValue().toString()) < Integer.valueOf(benDesde.getValue().toString())) {
				benDesde.setValue(Integer.parseInt(benHasta.getValue().toString()));

			}
			filtro();
			System.out.println("valor no va" + benDesde.getValue().toString());

		}
		if (e.getSource() == benHasta) {
			if (Integer.parseInt(benHasta.getValue().toString()) < Integer.parseInt(benDesde.getValue().toString())) {
				benHasta.setValue(Integer.parseInt(benDesde.getValue().toString()));
				System.out.println("el valor va" + benHasta.getValue().toString());

			}
			filtro();
			System.out.println("valor no va" + benHasta.getValue().toString());

		}
		if (e.getSource() == opDesde) {
			if (Integer.parseInt(opDesde.getValue().toString()) > Integer.parseInt(opHasta.getValue().toString())) {
				int hasta = Integer.parseInt(opHasta.getValue().toString()) - 1;
				opDesde.setValue(hasta);
				System.out.println("el valor va" + hasta);

			}
			if (chOpe.isSelected()) {

				filtro();
			}
		}
		if (e.getSource() == opHasta) {
			if (Integer.parseInt(opDesde.getValue().toString()) > Integer.parseInt(opHasta.getValue().toString())) {
				int desde = Integer.parseInt(opDesde.getValue().toString()) + 1;
				opHasta.setValue(desde);
				System.out.println("el valor va" + desde);
			}
			if (chOpe.isSelected()) {
				filtro();
			}

		}
		if (e.getSource() == totDesde) {
			if (Integer.parseInt(totDesde.getValue().toString()) > Integer.valueOf(totHasta.getValue().toString())) {
				totDesde.setValue(Integer.parseInt(totHasta.getValue().toString()));

			}
			if (chTotal.isSelected()) {
				filtro();
			}
		}
		if (e.getSource() == totHasta) {
			if (Integer.parseInt(totDesde.getValue().toString()) > Integer.valueOf(totHasta.getValue().toString())) {
				totHasta.setValue(Integer.parseInt(totDesde.getValue().toString()));

			}
			if (chTotal.isSelected()) {
				filtro();
			}
		}
		if (e.getSource() == diasDesde) {
			if (Integer.parseInt(diasDesde.getValue().toString()) > Integer.valueOf(diasHasta.getValue().toString())) {
				diasDesde.setValue(Integer.parseInt(diasHasta.getValue().toString()));

			}
			if (chdias.isSelected()) {
				filtro();
			}
		}
		if (e.getSource() == diasHasta) {
			if (Integer.parseInt(diasDesde.getValue().toString()) > Integer.valueOf(diasHasta.getValue().toString())) {
				diasHasta.setValue(Integer.parseInt(diasDesde.getValue().toString()));

			}
			if (chdias.isSelected()) {
				filtro();
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getSource() == dtDEsde) {
			dtHasta.setMinSelectableDate(dtDEsde.getDate());
			if (chentre.isSelected()) {
				filtro();
			}
		}
		if (evt.getSource() == dtHasta) {
			dtDEsde.setMaxSelectableDate(dtHasta.getDate());
			if (chentre.isSelected()) {
				filtro();
			}
		}
	}
}
