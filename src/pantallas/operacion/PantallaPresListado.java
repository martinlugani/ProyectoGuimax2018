package pantallas.operacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.swing.JToggleButton;
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

import com.toedter.calendar.JDateChooser;

import clases.coneccion.Fachada;
import clases.principales.cliente.Cliente;
import clases.principales.pedido.Presupuesto;
import clases.principales.seguridad.Usuario;
import pantallas.componenteGenerico.EliminaCientifica;
import pantallas.componenteGenerico.MyTableModel;
import reportes.GeneraReporte;

@SuppressWarnings("serial")
public class PantallaPresListado extends JDialog
		implements ActionListener, PropertyChangeListener, MouseListener, ChangeListener {

	private final JPanel contentPanel = new JPanel();
	private JDateChooser desde;
	private JDateChooser hasta;
	private JSpinner margenBenMin;
	private JComboBox<String> empresa;
	private Fachada fac;
	private Iterable<Cliente> carga;
	private JSpinner cantidadPresupuestada;
	private Vector<Presupuesto> presupuestos;

	private JCheckBox chckbxEntre;
	private JCheckBox chckbxRaznSocial;
	private JCheckBox chckbxBeneficio;
	private JCheckBox chckbxTotalPresupuestado;

	private JSpinner spinnerMinOp;
	private JSpinner spinnerDiasMax;
	private JSpinner spinnerBenMax;
	private JSpinner spinner_1;
	private JSpinner spinnerMaxop;
	private MyTableModel myModelo;
	private TableRowSorter<MyTableModel> sorter;
	private JToggleButton tglbtnActivo;
	private JCheckBox chckbxPorEstado;
	private JButton btnSalir;
	private JButton btnImprimir;
	private JCheckBox chckbxOperarios;
	private JCheckBox chckbxDiasDeProduccion;
	private JSpinner spinnerDiasMin;
	private JPanel panel_1;
	private JDateChooser dateChooser;
	private JLabel label;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private JTextField txtCostoprodtot;
	private JTextField txtBeneficio;
	private JTextField txtTotalfacturado;
	private JTable table;
	private JDesktopPane desktopPane;
	private int instanciasJinternal = 0;
	private java.util.Date hora;
	private JTextField txthora;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private Usuario usuario;
	private Timer timer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			 Usuario usu=null;
			PantallaPresListado dialog = new PantallaPresListado(usu);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("serial")
	public PantallaPresListado(Usuario usuario) {
		hora = new java.util.Date();

		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sn = new SimpleDateFormat("HH:mm:ss");
				hora.setTime(System.currentTimeMillis());

				txthora.setText(sn.format(hora));
				// System.out.println(sn.format(hora));
				dateChooser.setDate(hora);
			}
		});
		timer.start();

//		System.out.println(name.getHoraE().toString());

		setTitle("Listado de presuspuestos");
		setBounds(100, 100, 896, 770);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		fac = new Fachada();
		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Seleccione filtro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 867, 238);
		contentPanel.add(panel);
		panel.setLayout(null);

		desde = new JDateChooser();
		desde.setOpaque(false);

		desde.getCalendarButton().setToolTipText("Fecha inferior");
		desde.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

		desde.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

		desde.setDateFormatString("dd-MM-yyyy");
		desde.setBounds(161, 26, 107, 22);
		panel.add(desde);

		hasta = new JDateChooser();
		hasta.setOpaque(false);
		hasta.getCalendarButton().setToolTipText("Fecha Superior");
		hasta.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		hasta.setDateFormatString("dd-MM-yyyy");
		hasta.setBounds(280, 26, 107, 22);
		panel.add(hasta);

		empresa = new JComboBox();
		empresa.setToolTipText("Seleccione empresa");
		empresa.setBounds(161, 80, 107, 24);
		panel.add(empresa);

		margenBenMin = new JSpinner();

		margenBenMin.setToolTipText("Ingrese beneficio minimo");
		margenBenMin.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(10000)));
		margenBenMin.setBounds(161, 133, 107, 20);

		panel.add(margenBenMin);

		cantidadPresupuestada = new JSpinner();
		cantidadPresupuestada.setToolTipText("Ingrese cantidad minima");
		cantidadPresupuestada
				.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(10000)));
		cantidadPresupuestada.setBounds(547, 82, 107, 20);
		panel.add(cantidadPresupuestada);

		chckbxEntre = new JCheckBox("Entre");
		chckbxEntre.setBounds(18, 26, 111, 25);
		panel.add(chckbxEntre);

		chckbxRaznSocial = new JCheckBox("Raz\u00F3n social");
		chckbxRaznSocial.setBounds(18, 80, 111, 25);
		panel.add(chckbxRaznSocial);

		chckbxBeneficio = new JCheckBox("Beneficio");
		chckbxBeneficio.setBounds(18, 131, 111, 25);
		panel.add(chckbxBeneficio);

		chckbxTotalPresupuestado = new JCheckBox("Total presupuestado");
		chckbxTotalPresupuestado.setBounds(399, 80, 152, 25);
		panel.add(chckbxTotalPresupuestado);

		chckbxOperarios = new JCheckBox("Operarios");
		chckbxOperarios.setBounds(401, 26, 111, 25);
		panel.add(chckbxOperarios);

		spinnerMinOp = new JSpinner();
		spinnerMinOp.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinnerMinOp.setToolTipText("Ingrese la cantidad minima");
		spinnerMinOp.setBounds(547, 26, 107, 20);
		panel.add(spinnerMinOp);

		spinnerMaxop = new JSpinner();
		spinnerMaxop.setToolTipText("Ingrese cantidad maxima");
		spinnerMaxop.setModel(new SpinnerNumberModel(10, 0, 10, 1));
		spinnerMaxop.setBounds(696, 26, 107, 20);
		panel.add(spinnerMaxop);

		spinner_1 = new JSpinner();
		spinner_1.setToolTipText("Ingrese cantidad maxima");
		spinner_1.setModel(new SpinnerNumberModel(new Integer(1000000), new Integer(0), null, new Integer(10000)));
		spinner_1.setBounds(696, 82, 107, 20);
		panel.add(spinner_1);

		spinnerBenMax = new JSpinner();
		spinnerBenMax.setToolTipText("Ingrese beneficio maximo");
		spinnerBenMax.setModel(new SpinnerNumberModel(new Integer(500000), new Integer(0), null, new Integer(10000)));
		spinnerBenMax.setBounds(280, 133, 107, 20);
		panel.add(spinnerBenMax);

		chckbxDiasDeProduccion = new JCheckBox("Dias de produccion");
		chckbxDiasDeProduccion.setBounds(399, 131, 152, 25);
		panel.add(chckbxDiasDeProduccion);

		spinnerDiasMin = new JSpinner();
		spinnerDiasMin.setToolTipText("Ingrese cantidad minima");
		spinnerDiasMin.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerDiasMin.setBounds(547, 133, 107, 20);
		panel.add(spinnerDiasMin);

		spinnerDiasMax = new JSpinner();
		spinnerDiasMax.setToolTipText("Ingrese cantidad maxima");
		spinnerDiasMax.setModel(new SpinnerNumberModel(new Integer(100), new Integer(0), null, new Integer(5)));
		spinnerDiasMax.setBounds(696, 133, 107, 20);
		panel.add(spinnerDiasMax);

		chckbxPorEstado = new JCheckBox("Por estado");
		chckbxPorEstado.setBounds(18, 187, 111, 25);
		panel.add(chckbxPorEstado);

		tglbtnActivo = new JToggleButton("Activo");
		tglbtnActivo.setBounds(163, 187, 107, 25);
		panel.add(tglbtnActivo);
		myModelo = new MyTableModel();
		myModelo.addColums(new String[] { "Id", "Fecha", "Cliente", "Contacto", "Dias de fin", "Operarios",
				"Costo prod", "Beneficio", "Total", "Estado" });

		btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(666, 701, 99, 25);
		contentPanel.add(btnImprimir);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(777, 701, 99, 25);
		contentPanel.add(btnSalir);

		desktopPane = new JDesktopPane();
		desktopPane.setBounds(12, 264, 864, 424);
		contentPanel.add(desktopPane);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(
				new TitledBorder(null, "Datos a imprimir", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(0, 0, 867, 433);
		desktopPane.add(panel_1);

		dateChooser = new JDateChooser();
		dateChooser.setOpaque(false);
		dateChooser.setEnabled(false);
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setBounds(550, 29, 107, 22);
		panel_1.add(dateChooser);

		label = new JLabel("Fecha");
		label.setBounds(482, 35, 56, 16);
		panel_1.add(label);

		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(12, 58, 843, 268);
		panel_1.add(panel_2);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 843, 265);
		panel_2.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		txtCostoprodtot = new JTextField();
		txtCostoprodtot.setOpaque(false);
		txtCostoprodtot.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCostoprodtot.setForeground(new Color(204, 0, 0));
		txtCostoprodtot.setEditable(false);
		txtCostoprodtot.setColumns(10);
		txtCostoprodtot.setBackground(Color.WHITE);
		txtCostoprodtot.setBounds(712, 328, 131, 22);
		panel_1.add(txtCostoprodtot);

		txtBeneficio = new JTextField();
		txtBeneficio.setOpaque(false);
		txtBeneficio.setHorizontalAlignment(SwingConstants.RIGHT);
		txtBeneficio.setForeground(new Color(0, 128, 0));
		txtBeneficio.setEditable(false);
		txtBeneficio.setColumns(10);
		txtBeneficio.setBackground(Color.WHITE);
		txtBeneficio.setBounds(712, 363, 131, 22);
		panel_1.add(txtBeneficio);

		txthora = new JTextField();
		txthora.setOpaque(false);
		txthora.setEditable(false);
		txthora.setBounds(732, 29, 83, 22);
		panel_1.add(txthora);
		txthora.setColumns(10);

		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(669, 35, 56, 16);
		panel_1.add(lblHora);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(49, 35, 56, 16);
		panel_1.add(lblUsuario);

		txtUsuario = new JTextField();

		txtUsuario.setBounds(130, 29, 116, 22);
		panel_1.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtTotalfacturado = new JTextField();
		txtTotalfacturado.setBounds(712, 398, 131, 22);
		panel_1.add(txtTotalfacturado);
		txtTotalfacturado.setOpaque(false);
		txtTotalfacturado.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotalfacturado.setForeground(SystemColor.activeCaption);
		txtTotalfacturado.setEditable(false);
		txtTotalfacturado.setColumns(10);
		txtTotalfacturado.setBackground(Color.WHITE);

		JLabel lblGastoPrevisto = new JLabel("Gasto previsto");
		lblGastoPrevisto.setBounds(579, 331, 121, 16);
		panel_1.add(lblGastoPrevisto);

		JLabel lblBeneficioPrevisto = new JLabel("Beneficio previsto");
		lblBeneficioPrevisto.setBounds(579, 366, 107, 16);
		panel_1.add(lblBeneficioPrevisto);

		JLabel lblTotalPresupuestado = new JLabel("Total presupuestado");
		lblTotalPresupuestado.setBounds(579, 401, 121, 16);
		panel_1.add(lblTotalPresupuestado);
		if (usuario != null) {
			this.usuario = usuario;
			txtUsuario.setText(usuario.getNombreUsuario());
		} else {
			this.usuario=new Usuario();
			this.usuario.setNombreUsuario("Default");
			txtUsuario.setText(this.usuario.getNombreUsuario());
		}
		cargaListeners();
		cargaComponentes();
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void cargaListeners() {
		hasta.getCalendarButton().addActionListener(this);
		desde.addPropertyChangeListener(this);
		hasta.addPropertyChangeListener(this);

		margenBenMin.addMouseListener(this);
		table.addMouseListener(this);
		btnSalir.addActionListener(this);
		btnImprimir.addActionListener(this);

//		checkbox
		tglbtnActivo.addActionListener(this);
		empresa.addActionListener(this);
		chckbxOperarios.addActionListener(this);
		chckbxPorEstado.addActionListener(this);
		chckbxBeneficio.addActionListener(this);
		chckbxEntre.addActionListener(this);
		chckbxRaznSocial.addActionListener(this);
		chckbxDiasDeProduccion.addActionListener(this);
		;
		// spiners
		margenBenMin.addChangeListener(this);
		spinnerBenMax.addChangeListener(this);
		margenBenMin.addPropertyChangeListener(this);
		spinnerBenMax.addPropertyChangeListener(this);
		spinnerMinOp.addChangeListener(this);
		spinnerMaxop.addChangeListener(this);
		cantidadPresupuestada.addChangeListener(this);
		spinner_1.addChangeListener(this);
		spinnerDiasMin.addChangeListener(this);
		spinnerDiasMax.addChangeListener(this);
	}

	public int getInstanciasJinternal() {
		return instanciasJinternal;
	}

	public void setInstanciasJinternal(int instanciasJinternal) {
		this.instanciasJinternal = instanciasJinternal;
	}

	private void seleccionPresupuetoDetalle() {
		System.out.println("entra aca si");
		System.out.println("Id de row " + table.getValueAt(table.getSelectedRow(), 0));
		if (instanciasJinternal == 0) {
			DetallePresupuesto name = new DetallePresupuesto(this.usuario);
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
			enviaPresupuesto(name);
		}

	}

	private void enviaPresupuesto(DetallePresupuesto name) {
		// TODO Auto-generated method stub
		for (Presupuesto presupuesto : presupuestos) {
			if (table.getValueAt(table.getSelectedRow(), 0) != null) {
				if (Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()) == presupuesto
						.getIdPresupuesto()) {
					name.addPresupuesto(presupuesto, this);

				}
			}
		}

	}

	private void cargaComponentes() {
		// TODO Auto-generated method stub
		if (usuario != null) {
			txtUsuario.setText(usuario.getNombreUsuario());
			txtUsuario.setEditable(false);
		}
//		if (fac.selectPresVencidos()) {
//			int selec = JOptionPane.showConfirmDialog(null, "Desea eliminar los presupuestos ",
//					"Presupuestos con mas de 15 dias", JOptionPane.YES_NO_OPTION, 2);
//			if (selec == 0) {
//				boolean b = fac.eliminarpresVencidos();
//			}
//		}
		LocalDate ahora = LocalDate.now();
		Date fechaHasta = Date.valueOf(ahora);
		desde.setMaxSelectableDate(fechaHasta);
		desde.getDateEditor().setEnabled(false);

		desde.getDateEditor().getUiComponent().setBackground(Color.WHITE);
		// desde.getDateEditor().getUiComponent().setForeground(Color.RED);
		desde.setDate(fechaHasta);
		hasta.setMaxSelectableDate(fechaHasta);
		hasta.setDate(fechaHasta);
		hasta.getDateEditor().setEnabled(false);
		hasta.getDateEditor().getUiComponent().setBackground(Color.WHITE);
		hasta.setMinSelectableDate(desde.getDate());
		carga = fac.selectCliente();
		for (Cliente cliente : carga) {
			empresa.addItem(cliente.getRazonSocial());
		}

		presupuestos = fac.selectPresupuesto();
		System.out.println(presupuestos.toString());
		if (presupuestos.size() > 0) {
			for (Presupuesto presupuesto : presupuestos) {
				DateTimeFormatter name = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				float por = presupuesto.getPorcentage();
				float beneficio = presupuesto.getCosto() * (por / 100);
				float subtotal = beneficio + presupuesto.getCosto();
				String estado = "ACT";
				if (presupuesto.getFlagBaja() == 0) {
					estado = "BOR";
				}
				Integer id = presupuesto.getIdPresupuesto();
				Object[] fila = { id, presupuesto.getFechaAlta(), presupuesto.getCliente().getRazonSocial(),
						presupuesto.getContacto().getApellido() + " - " + presupuesto.getContacto().getNombre(),
						presupuesto.getDias(), presupuesto.getOperariosFabricacion(), presupuesto.getCosto(), beneficio,
						subtotal, estado };
				myModelo.addRow(fila);

			}
			myModelo.setCellEditing(false);
			table.setModel(myModelo);
		} else {
			JOptionPane.showMessageDialog(null, "La pantalla se habrira", "No hay presupuestos ", 0);
			this.dispose();
		}

		sorter = new TableRowSorter<MyTableModel>(myModelo);
		table.setRowSorter(sorter);
		sumas();
	}

	private void filtro() {
		// table.setAutoCreateRowSorter(true);

		List<RowFilter<Object, Object>> lista = new ArrayList<RowFilter<Object, Object>>();
		if (chckbxRaznSocial.isSelected()) {
			// agregaraFiltro();

			lista.add(RowFilter.regexFilter(empresa.getSelectedItem().toString(), 2));
			System.out.println("paso1111111");
		}
		if (chckbxBeneficio.isSelected()) {
			lista.add(RowFilter.numberFilter(ComparisonType.BEFORE,
					Integer.parseInt(spinnerBenMax.getValue().toString()), 7));
			lista.add(RowFilter.numberFilter(ComparisonType.AFTER, Integer.parseInt(margenBenMin.getValue().toString()),
					7));

		}
		SimpleDateFormat name = new SimpleDateFormat("dd-MM-yyyy");
		if (chckbxEntre.isSelected()) {

			Date startDate = new Date(desde.getDate().getTime());

			Date endDate = new Date(hasta.getDate().getTime());

			lista.add(RowFilter.dateFilter(ComparisonType.AFTER, fecha(startDate, -1), 1));
			lista.add(RowFilter.dateFilter(ComparisonType.BEFORE, fecha(endDate, 1), 1));

		}
		if (chckbxPorEstado.isSelected()) {
			String activo = "";
			if (tglbtnActivo.isSelected()) {
				activo = "BOR";
			} else {
				activo = "ACT";
			}
			lista.add(RowFilter.regexFilter(activo, 9));
		}
		if (chckbxOperarios.isSelected()) {
			lista.add(RowFilter.numberFilter(ComparisonType.AFTER, Integer.valueOf(spinnerMinOp.getValue().toString()),
					5));
			lista.add(RowFilter.numberFilter(ComparisonType.BEFORE, Integer.valueOf(spinnerMaxop.getValue().toString()),
					5));
		}
		if (chckbxTotalPresupuestado.isSelected()) {
			lista.add(RowFilter.numberFilter(ComparisonType.AFTER,
					Integer.valueOf(cantidadPresupuestada.getValue().toString()), 8));
			lista.add(
					RowFilter.numberFilter(ComparisonType.BEFORE, Integer.valueOf(spinner_1.getValue().toString()), 8));
		}
		if (chckbxDiasDeProduccion.isSelected()) {
			lista.add(RowFilter.numberFilter(ComparisonType.AFTER,
					Integer.valueOf(spinnerDiasMin.getValue().toString()), 4));
			lista.add(RowFilter.numberFilter(ComparisonType.BEFORE,
					Integer.valueOf(spinnerDiasMax.getValue().toString()), 4));
		}
		RowFilter filtroAnd = RowFilter.andFilter(lista);

		sorter.setRowFilter(filtroAnd);
		table.setRowSorter(sorter);
		sumas();
	}

	private java.util.Date fecha(Date startDate, int i) {
		Calendar sumaResta = Calendar.getInstance();
		sumaResta.setTime(startDate);
		sumaResta.add(Calendar.DAY_OF_YEAR, i);
		return sumaResta.getTime();
	}

	private boolean validaNumeros(int superior, int inferior) {
		if (superior >= inferior) {
			return true;
		}
		return false;
	}

	private void sumas() {
		if (table != null) {

			txtCostoprodtot.setText(EliminaCientifica.elimina(costototal()));
			txtBeneficio.setText(EliminaCientifica.elimina(beneficioTotal()));
			txtTotalfacturado.setText(EliminaCientifica.elimina(totalFacturado()));

		}

	}

	private double totalFacturado() {
		double beneficio = 0;
		for (int f = 0; f < table.getRowCount(); f++) {
			beneficio = beneficio + Double.parseDouble(table.getValueAt(f, 8).toString());
		}

		beneficio = Math.round(beneficio * 100d);
		;
		return beneficio;
	}

	private double beneficioTotal() {
		double beneficio = 0f;
		for (int f = 0; f < table.getRowCount(); f++) {
			beneficio = beneficio + Float.parseFloat(table.getValueAt(f, 7).toString());
		}
		beneficio = Math.round(beneficio * 100d);
		return beneficio;
	}

	private double costototal() {
		double costo = 0f;
		for (int f = 0; f < table.getRowCount(); f++) {
			costo = costo + Float.parseFloat(table.getValueAt(f, 6).toString());
		}
		costo = Math.round(costo * 100d);
		return costo;

	}

	private void imprimir() {
		GeneraReporte reporte = new GeneraReporte();

		DateFormat formatoh = new SimpleDateFormat("HHmmss");
		java.util.Date hora = new java.util.Date();
		String nombre = "Presupuestos";
		nombre += formatoh.format(hora).toString();
		System.out.println(nombre);
		reporte.createPDF(nombre, "Lista de presupuestos");
		if (table.getModel() != null) {
			float[] tamanio = { 2f, 4.5f, 5f, 7f, 3f, 3f, 5f, 5f, 5f, 3f };
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
			if (chckbxEntre.isSelected()) {
				fila.add("Por fecha");
				fila.add("Si");
				fila.add("Desde " + DateFormat.getInstance().format(desde.getDate()));
				fila.add("Hasta" + DateFormat.getInstance().format(hasta.getDate()));
			} else {
				fila.add("Entre");
				fila.add("No");
				fila.add("");
				fila.add("");
			}
			if (chckbxRaznSocial.isSelected()) {
				fila.add("Por empresa");
				fila.add("Si");
				fila.add("");
				fila.add("" + empresa.getSelectedItem().toString());
			} else {
				fila.add("Por empresa");
				fila.add("No");
				fila.add("");
				fila.add("");
			}
			if (chckbxDiasDeProduccion.isSelected()) {
				fila.add("Por dias de prod");
				fila.add("Si");
				fila.add("Desde " + spinnerDiasMin.getValue().toString());
				fila.add("Hasta" + spinnerDiasMax.getValue().toString());
			} else {
				fila.add("Por dias de prod");
				fila.add("No");
				fila.add("");
				fila.add("");
			}
			fila.add("Estado");
			if (chckbxPorEstado.isSelected()) {

				fila.add("Si");
				fila.add("" + tglbtnActivo.getText());
				fila.add("");
			} else {

				fila.add("No");
				fila.add("");
				fila.add("");
			}
			fila.add("Por operarios");
			if (chckbxOperarios.isSelected()) {

				fila.add("Si");
				fila.add("Entre " + spinnerMinOp.getValue().toString());
				fila.add(" y " + spinnerMaxop.getValue().toString());
			} else {

				fila.add("No");
				fila.add("");
				fila.add("");
			}
			fila.add("Total facturado");
			if (chckbxTotalPresupuestado.isSelected()) {

				fila.add("Si");
				fila.add("Entre " + cantidadPresupuestada.getValue().toString());
				fila.add(" y " + spinner_1.getValue().toString());
			} else {

				fila.add("No");
				fila.add("");
				fila.add("");
			}
			fila.add("Beneficio");
			if (chckbxBeneficio.isSelected()) {

				fila.add("Si");
				fila.add("Entre " + margenBenMin.getValue().toString());
				fila.add(" y " + spinnerBenMax.getValue().toString());
			} else {

				fila.add("No");
				fila.add("");
				fila.add("");
			}
			Object[][] name = new Object[8][4];
			name[0][0] = "Usuario";
			name[0][1] = txtUsuario.getText();
			name[0][2] = "Fecha";
			name[0][3] = DateFormat.getDateInstance().format(dateChooser.getDate());
			int cont = 0;
			for (int f = 1; f < name.length; f++) {
				for (int c = 0; c < name[f].length; c++) {
					name[f][c] = fila.get(cont);
					cont++;
				}
			}
			reporte.setEncabezado("Listado presupuesto", name, 0);
			reporte.setTabla(columnas, filas, tamanio, -1);
			reporte.setPie("Costo Produc", txtCostoprodtot.getText(), 1);
			reporte.setPie("Beneficio", txtBeneficio.getText(), 1);
			reporte.setPie("Total", txtTotalfacturado.getText(), 1);
		}

		if (!reporte.print()) {
			JOptionPane.showMessageDialog(null, "No se pudo realizar", "Error en impresión", 0);
		} else {
			JOptionPane.showMessageDialog(null, "Operación satisfactoria ", "Impresión realizada", 2);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == chckbxOperarios) {
			filtro();
		}
		if (e.getSource() == hasta.getCalendarButton()) {
			System.out.println("Estoy apretado");
			if (hasta.getDate().before(desde.getDate())) {

			}
		}
		if (e.getSource() == chckbxRaznSocial) {
			filtro();
			for (int i = 0; i < 6; i++) {
			}

		}
		if (chckbxBeneficio == e.getSource()) {
			filtro();
		}
		if (chckbxEntre == e.getSource()) {
			filtro();
		}
		if (chckbxPorEstado == e.getSource()) {
			filtro();
		}
		if (tglbtnActivo == e.getSource()) {
			System.out.println("pasotogle");
			if (!tglbtnActivo.isSelected()) {
				tglbtnActivo.setText("Activo");
			} else {
				tglbtnActivo.setText("Borrado");
			}
			filtro();

		}
		if (empresa == e.getSource()) {
			if (chckbxRaznSocial.isSelected()) {
				filtro();
			}

		}
		if (e.getSource() == chckbxTotalPresupuestado) {
			filtro();
		}
		if (e.getSource() == chckbxDiasDeProduccion) {
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

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == desde) {
			hasta.setMinSelectableDate(desde.getDate());
			if (chckbxEntre.isSelected()) {
				filtro();
			}
		}
		if (arg0.getSource() == hasta) {
			desde.setMaxSelectableDate(hasta.getDate());
			if (chckbxEntre.isSelected()) {
				filtro();
			}
		}

		if (arg0.getSource() == spinnerBenMax) {

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (table == e.getSource()) {
			seleccionPresupuetoDetalle();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == margenBenMin) {
			if (Integer.parseInt(spinnerBenMax.getValue().toString()) < Integer
					.valueOf(margenBenMin.getValue().toString())) {
				margenBenMin.setValue(Integer.parseInt(spinnerBenMax.getValue().toString()));

			}
			filtro();
			System.out.println("valor no va" + margenBenMin.getValue().toString());

		}
		if (e.getSource() == spinnerBenMax) {
			if (Integer.parseInt(spinnerBenMax.getValue().toString()) < Integer
					.parseInt(margenBenMin.getValue().toString())) {
				spinnerBenMax.setValue(Integer.parseInt(margenBenMin.getValue().toString()));
				System.out.println("el valor va" + spinnerBenMax.getValue().toString());

			}
			filtro();
			System.out.println("valor no va" + spinnerBenMax.getValue().toString());

		}
		if (e.getSource() == spinnerMaxop) {
			if (Integer.parseInt(spinnerMaxop.getValue().toString()) < Integer
					.parseInt(spinnerMinOp.getValue().toString())) {
				spinnerMaxop.setValue(Integer.parseInt(spinnerMinOp.getValue().toString()));
				System.out.println("el valor va" + spinnerMaxop.getValue().toString());

			}
			if (chckbxOperarios.isSelected()) {

				filtro();
			}
		}
		if (e.getSource() == spinnerMinOp) {
			if (Integer.parseInt(spinnerMaxop.getValue().toString()) < Integer
					.parseInt(spinnerMinOp.getValue().toString())) {
				spinnerMinOp.setValue(Integer.parseInt(spinnerMaxop.getValue().toString()));

			}
			if (chckbxOperarios.isSelected()) {
				filtro();
			}

		}
		if (e.getSource() == cantidadPresupuestada) {
			if (Integer.parseInt(cantidadPresupuestada.getValue().toString()) > Integer
					.valueOf(spinner_1.getValue().toString())) {
				cantidadPresupuestada.setValue(Integer.parseInt(spinner_1.getValue().toString()));

			}
			if (chckbxTotalPresupuestado.isSelected()) {
				filtro();
			}
		}
		if (e.getSource() == spinner_1) {
			if (Integer.parseInt(cantidadPresupuestada.getValue().toString()) > Integer
					.valueOf(spinner_1.getValue().toString())) {
				spinner_1.setValue(Integer.parseInt(cantidadPresupuestada.getValue().toString()));

			}
			if (chckbxTotalPresupuestado.isSelected()) {
				filtro();
			}
		}
		if (e.getSource() == spinnerDiasMin) {
			if (Integer.parseInt(spinnerDiasMin.getValue().toString()) > Integer
					.valueOf(spinnerDiasMax.getValue().toString())) {
				spinnerDiasMin.setValue(Integer.parseInt(spinnerDiasMax.getValue().toString()));

			}
			if (chckbxDiasDeProduccion.isSelected()) {
				filtro();
			}
		}
		if (e.getSource() == spinnerDiasMax) {
			if (Integer.parseInt(spinnerDiasMin.getValue().toString()) > Integer
					.valueOf(spinnerDiasMax.getValue().toString())) {
				spinnerDiasMax.setValue(Integer.parseInt(spinnerDiasMin.getValue().toString()));

			}
			if (chckbxDiasDeProduccion.isSelected()) {
				filtro();
			}
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;

	}
}