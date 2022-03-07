//flata acentar fecha de generacion

package pantallas.operacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import clases.coneccion.Fachada;
import clases.principales.cliente.Cliente;
import clases.principales.cliente.Contacto;
import clases.principales.pedido.LineaPresupuesto;
import clases.principales.pedido.Presupuesto;
import clases.principales.productos.Producto;
import clases.principales.seguridad.OperacionEgreso;
import pantallas.componenteGenerico.FormatedGenerico;
import pantallas.componenteGenerico.IntegerEditor;
import pantallas.componenteGenerico.ModeloTable;

public class PresupuestoPantalla extends JDialog
		implements ActionListener, MouseListener, FocusListener, KeyListener, ChangeListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFecha;
	private JTextField txtHora;
	private JTextField txtIdpres;
	private JTable table;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane;
	private JSpinner spinner;
	private JComboBox comboBox_1;
	private JComboBox comboBox;
	private Fachada fac;
	private Vector<Producto> productos;
	private JTable table_1;
	private ModeloTable modeloEleccion, modelocarga;
	private JLabel lblPorcentaje;
	private JSpinner spinner_1;
	private JTextField txtTotal;
	private JLabel lblTotalPresupuestado;
	private int item;
	private IntegerEditor cnatidadFo = new IntegerEditor(1, 300);
	private float total;
	private JTextField txtBeneficio;
	private JTextField txtCantdedias;
	private JButton btnEliminarProducto;
	private JButton btnGeneraPresupuesto;
	private JButton btnCancelar;
	private JSpinner spinner_2;
	private int horasTotales;
	private int numeroPrees;
	private JFormattedTextField form = new JFormattedTextField();
	private Vector<Cliente> clientes;
	private Presupuesto presupuestoNuevo;
	private JTextField txtCostoprod;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PresupuestoPantalla dialog = new PresupuestoPantalla();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	/**
	 * Create the dialog.
	 */
	public PresupuestoPantalla() {
		setTitle("Presupuestos");

		total = 0;
		horasTotales = 0;
		setBounds(100, 100, 839, 656);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNumeroDePresuspuesto = new JLabel("Numero de presuspuesto");
		lblNumeroDePresuspuesto.setBounds(60, 31, 156, 16);
		contentPanel.add(lblNumeroDePresuspuesto);
		modeloEleccion = new ModeloTable();
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(449, 13, 56, 16);
		contentPanel.add(lblFecha);

		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(644, 13, 56, 16);
		contentPanel.add(lblHora);

		JLabel lblCliente = new JLabel("Cliente ");
		lblCliente.setBounds(60, 75, 145, 16);
		contentPanel.add(lblCliente);

		JLabel lblContacto = new JLabel("Contacto");
		lblContacto.setBounds(60, 124, 156, 16);
		contentPanel.add(lblContacto);

		JLabel lblCantidadOperarios = new JLabel("Cantidad operarios");
		lblCantidadOperarios.setBounds(60, 182, 156, 16);
		contentPanel.add(lblCantidadOperarios);

		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setBounds(516, 10, 78, 22);
		contentPanel.add(txtFecha);
		txtFecha.setColumns(10);

		txtHora = new JTextField();
		txtHora.setEditable(false);
		txtHora.setBounds(696, 10, 45, 22);
		contentPanel.add(txtHora);
		txtHora.setColumns(10);

		txtIdpres = new JTextField();
		txtIdpres.setEditable(false);
		txtIdpres.setBounds(234, 28, 116, 22);
		contentPanel.add(txtIdpres);
		txtIdpres.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setBounds(234, 71, 116, 24);
		contentPanel.add(comboBox);

		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(234, 120, 116, 24);
		contentPanel.add(comboBox_1);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		spinner.setBounds(234, 180, 116, 20);
		contentPanel.add(spinner);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 232, 445, 112);
		contentPanel.add(scrollPane);

		table_1 = new JTable(modeloEleccion);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table_1);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(60, 373, 738, 112);
		contentPanel.add(scrollPane_1);
		modelocarga = new ModeloTable();
		table = new JTable(modelocarga);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(table);

		lblPorcentaje = new JLabel("Margen de beneficio");
		lblPorcentaje.setBounds(468, 75, 156, 16);
		contentPanel.add(lblPorcentaje);

		spinner_1 = new JSpinner();
		spinner_1.setBounds(696, 73, 116, 20);
		contentPanel.add(spinner_1);

		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(682, 552, 116, 22);
		contentPanel.add(txtTotal);
		txtTotal.setColumns(10);

		lblTotalPresupuestado = new JLabel("Total presupuestado");
		lblTotalPresupuestado.setBounds(468, 555, 128, 16);
		contentPanel.add(lblTotalPresupuestado);

		JLabel lblBeneficioObtenido = new JLabel("Beneficio obtenido");
		lblBeneficioObtenido.setBounds(466, 523, 128, 16);
		contentPanel.add(lblBeneficioObtenido);

		txtBeneficio = new JTextField();
		txtBeneficio.setEditable(false);
		txtBeneficio.setBounds(682, 520, 116, 22);
		contentPanel.add(txtBeneficio);
		txtBeneficio.setColumns(10);

		txtCantdedias = new JTextField();
		txtCantdedias.setEditable(false);
		txtCantdedias.setBounds(696, 179, 116, 22);
		contentPanel.add(txtCantdedias);
		txtCantdedias.setColumns(10);

		JLabel lblDiasParaFinal = new JLabel("Dias para final");
		lblDiasParaFinal.setBounds(468, 182, 145, 16);
		contentPanel.add(lblDiasParaFinal);

		btnEliminarProducto = new JButton("Eliminar producto");
		btnEliminarProducto.setBounds(608, 319, 145, 25);
		contentPanel.add(btnEliminarProducto);

		btnGeneraPresupuesto = new JButton("Genera presupuesto");
		btnGeneraPresupuesto.setBounds(449, 587, 169, 25);
		contentPanel.add(btnGeneraPresupuesto);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(699, 587, 99, 25);
		contentPanel.add(btnCancelar);

		spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinner_2.setBounds(696, 122, 116, 20);
		contentPanel.add(spinner_2);

		JLabel lblDiasImprevistos = new JLabel("Dias imprevistos");
		lblDiasImprevistos.setBounds(468, 124, 133, 16);
		contentPanel.add(lblDiasImprevistos);
		contentPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { comboBox, comboBox_1, spinner,
				spinner_1, spinner_2, btnEliminarProducto, btnGeneraPresupuesto, btnCancelar }));

		carga();

		table.setDefaultEditor(Integer.class, cnatidadFo);
//		cnatidadFo.setEditable(true);
//		cnatidadFo.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
		cargaListener();
		cargaCombo();
		numeroPrees = fac.getUltimoPres();
		numeroPrees++;
		txtIdpres.setText(String.valueOf(numeroPrees));

		txtCostoprod = new JTextField();
		txtCostoprod.setEditable(false);
		txtCostoprod.setBounds(682, 488, 116, 22);
		contentPanel.add(txtCostoprod);
		txtCostoprod.setColumns(10);

		JLabel lblCostoProductos = new JLabel("Costo productos");
		lblCostoProductos.setBounds(468, 498, 145, 16);
		contentPanel.add(lblCostoProductos);
		try {
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);
		} catch (IndexOutOfBoundsException e2) {
			System.out.println("Error");
		}
	}

	private void cuentaItems() {
		// TODO Auto-generated method stub
		int items = 1;
		if (table.getRowCount() > 0) {
			while (items <= table.getRowCount()) {
				table.getModel().setValueAt(items, items - 1, 0);
				items++;
				table.repaint();
			}

			item = items;
		}
	}

	private void cargaListener() {
		table_1.addMouseListener(this);
//		cnatidadFo.addMouseListener(this);
//		cnatidadFo.addFocusListener(this);
//		cnatidadFo.addKeyListener(this);
		table.addKeyListener(this);
		spinner_1.addMouseListener(this);
		spinner_1.addChangeListener(this);
		spinner.addChangeListener(this);
		spinner_2.addChangeListener(this);
		btnEliminarProducto.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnGeneraPresupuesto.addActionListener(this);
		table.addMouseListener(this);
		comboBox.addActionListener(this);
		cnatidadFo.getTextFormater().addKeyListener(this);
		cnatidadFo.getTextFormater().addFocusListener(this);
		cnatidadFo.getTextFormater().addMouseListener(this);
		form.addKeyListener(this);
		this.addMouseListener(this);

	}

	private void cargarCombo2() {
		for (Cliente cliente : clientes) {
			if (cliente.getRazonSocial().equals(comboBox.getSelectedItem().toString())) {
				for (Contacto contacto : cliente.getContacto()) {
					if (contacto.getPermisoOtorgado().equals("Presupuesto")
							|| contacto.getPermisoOtorgado().equals("Total")) {
						comboBox_1.addItem(contacto.getApellido() + " - " + contacto.getNombre());
					}

				}
			}
		}

	}

	private void carga() {
		if (modeloEleccion.getRowCount() > 0) {
			modeloEleccion.removeAll();
			table_1.repaint();
		} else {
			fac = new Fachada();
			productos = (Vector<Producto>) fac.selectProducto();
			modeloEleccion.aniadeColumna("Id producto");
			modeloEleccion.aniadeColumna("Modelo");
			modeloEleccion.aniadeColumna("Voltamperios");
		}

		for (Producto producto : productos) {
			LinkedList<Object> prod = new LinkedList<Object>();
			prod.add(producto.getCodigoProducto());
			prod.add(producto.getModelo());
			prod.add(producto.getVoltamperios());
			modeloEleccion.aniadeFila(prod);
			modeloEleccion.bloqueaEdicion(false);
		}

		table_1.setModel(modeloEleccion);
		if (modelocarga.getColumnCount() == 0) {
			modelocarga.aniadeColumna("Item");
			modelocarga.aniadeColumna("Codigo producto");
			modelocarga.aniadeColumna("Modelo");
			modelocarga.aniadeColumna("Voltamperios");
			modelocarga.aniadeColumna("Costo producto");
			modelocarga.aniadeColumna("Canidad a fabricar");
			modelocarga.aniadeColumna("Total por producto");
		}

		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		LocalDate fechaLocal = LocalDate.now();
		Date fecha = new Date();
		txtFecha.setText(formato.format(fecha));
		LocalDate horaActual = LocalDate.now(Clock.systemUTC());
		DateFormat formatoh = new SimpleDateFormat("HH:mm");
		Date hora = new Date();

		txtHora.setText(formatoh.format(hora));
		SpinnerNumberModel mod = new SpinnerNumberModel();
		mod.setStepSize(10);
		mod.setMaximum(200);
		mod.setMinimum(0);
		spinner_1.setModel(mod);
		item = 1;
	}

	private void cargaCombo() {
		clientes = (Vector<Cliente>) fac.selectCliente();
		for (Cliente cliente : clientes) {
			comboBox.addItem(cliente.getRazonSocial());

		}
		cargarCombo2();

	}

	private void generarPresupuesto() {
		if (compruevaVacios()) {
			int idContacto = 0;
			presupuestoNuevo = new Presupuesto();
			presupuestoNuevo.setIdPresupuesto(numeroPrees);
			presupuestoNuevo.setFlagBaja(1);
			presupuestoNuevo.setOperariosFabricacion(Integer.parseInt(spinner.getValue().toString()));
			presupuestoNuevo.setFecha(txtFecha.getText());
			presupuestoNuevo.setHora(txtHora.getText());
			presupuestoNuevo.setPorcentage(Integer.valueOf(spinner_1.getValue().toString()));
			for (Cliente cliente : clientes) {
				if (cliente.getRazonSocial().equals(comboBox.getSelectedItem().toString())) {

					for (Contacto contacto : cliente.getContacto()) {
						String contact = contacto.getApellido() + " - " + contacto.getNombre();

						if (contact.equals(comboBox_1.getSelectedItem().toString())) {
							presupuestoNuevo.setContacto(contacto);
							idContacto = contacto.getIdpersona();
						}
					}
					presupuestoNuevo.setCliente(cliente);
				}
			}
			presupuestoNuevo.setDias(Integer.parseInt(txtCantdedias.getText()));
			System.out.println(txtCostoprod.getText());
			presupuestoNuevo.setCosto(Float.parseFloat(txtCostoprod.getText()));

			for (int i = 0; i < modelocarga.getRowCount(); i++) {
				for (Producto producto : productos) {
					if (producto.getCodigoProducto() == Integer.parseInt(modelocarga.getValueAt(i, 1).toString())) {
						LineaPresupuesto lin = new LineaPresupuesto();
						lin.setIdLinea(Integer.parseInt(modelocarga.getValueAt(i, 0).toString()));
						lin.setCantidad(Integer.parseInt(modelocarga.getValueAt(i, 5).toString()));
						lin.setProducto(producto);
						lin.setIdPresupuesto(numeroPrees);
						presupuestoNuevo.addProducto(producto, lin);
					}
				}

			}
			if (fac.insertPresupuesto(presupuestoNuevo)) {
				JOptionPane.showMessageDialog(null, "Presupuesto generado favorablemente ", "Generación de presupuesto",
						JOptionPane.INFORMATION_MESSAGE);
				OperacionEgreso name = new OperacionEgreso();
				name.setIdPresupuesto(Integer.valueOf(presupuestoNuevo.getIdPresupuesto()));
				name.setIdcontacto(idContacto);
				name.setFecha(presupuestoNuevo.getFechaAlta());

				fac.insertaAutoriza(name);
				reseteaPantalla();
			}
		}
	}

	private void reseteaPantalla() {
		spinner.setValue(1);
		spinner_1.setValue(0);
		spinner_2.setValue(0);

		modelocarga.removeAll();
//		modelocarga.removeCellEditorListener(table);
		table.repaint();
		table.clearSelection();
		carga();
		txtCostoprod.setText("");
		txtCantdedias.setText("");
		txtTotal.setText("");
		txtBeneficio.setText("");
		if (table.getModel().getRowCount() > 0) {
//			modelocarga.removeAllAll();
//			table.setModel(modelocarga);
			if (table.getSelectedRow() > 0) {
//				modelocarga= null;
//				modelocarga= new ModeloTable();

			}
		}

	}

	private boolean compruevaVacios() {
		if (modelocarga.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "No hay productos a presupuestar", "Error de carga",
					JOptionPane.ERROR_MESSAGE);
			return false;

		}
		if (spinner_1.getValue().toString().equals("0") && spinner_2.getValue().toString().equals("0")) {
			if (JOptionPane.showConfirmDialog(null, "Desea continuar",
					"No ha definido porcentage ni dias de imprevistos", JOptionPane.YES_NO_OPTION,
					JOptionPane.ERROR_MESSAGE) != 1) {
				return true;
			}
			return false;
		}
		if (spinner_1.getValue().toString().equals("0")) {
			if (JOptionPane.showConfirmDialog(null, "Desea continuar", "No ha definido porcentage",
					JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) != 1) {
				return true;
			}
			return false;
		}

		if (spinner_2.getValue().toString().equals("0")) {
			if (JOptionPane.showConfirmDialog(null, "Desea continuar", "No ha definido dias para imprevistos",
					JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) != 1) {
				return true;
			}
			return false;
		}
		return true;
	}

	private void calculoDias() {
//		try {
		int s = Integer.valueOf(cnatidadFo.getTextFormater().getText());

		int horas = 0;
		horasTotales = 0;
		for (Producto producto : productos) {
			for (int i = 0; i < modelocarga.getRowCount(); i++) {
				if (producto.getCodigoProducto() == Integer.parseInt(modelocarga.getValueAt(i, 1).toString())) {
					horasTotales += producto.getHorasHombre()
							* Integer.parseInt(modelocarga.getValueAt(i, 5).toString());
				}
			}
		}
		int spin = Integer.parseInt(spinner.getValue().toString());
		horas = horasTotales / spin;
		float redondeo = horas / 8;

		System.out.println("redondeo" + redondeo);
		int pr = Math.floorDiv(horas, 8);
		pr += Integer.parseInt(spinner_2.getValue().toString());
		if ((horasTotales % 100) > 0) {
			pr++;
		}
		txtCantdedias.setText(String.valueOf(pr));

//		} catch (NumberFormatException e) {
//			System.out.println("Error ");
//			JOptionPane.showMessageDialog(null, "Error cantidad imposible de procesar ", "Error tipo " + e, 0);
//			cnatidadFo.getTextFormater().setText("1");
//			cnatidadFo.getTextFormater().requestFocus();
//			table.getModel().setValueAt("1", table.getSelectedRow(), 5);
//			operacionDeSubtotales();
//			calculoDias();
//		}

	}

//carga en la tabla de presupuestos
	private void seleccionSet() {
		if (table_1.getRowCount() > 0) {
			LinkedList<Object> obj = new LinkedList<Object>();
			for (Producto producto : productos) {
				if (producto.getCodigoProducto() == Integer
						.parseInt(String.valueOf(modeloEleccion.getValueAt(table_1.getSelectedRow(), 0)))) {

					obj.add(item);
					obj.add(producto.getCodigoProducto());
					obj.add(producto.getModelo());
					obj.add(producto.getVoltamperios());
					obj.add(producto.getCostoTotal());
					form.setText("655");
					obj.add(new Integer(1));
					obj.add(new Integer(0));
				}
			}
//			cnatidadFo.getTextFormater().setText("1");
//			cnatidadFo.setValor("1");
			item++;
			modelocarga.aniadeFila(obj);
			table.setModel(modelocarga);

			modelocarga.bloqueaEdicion(false);
			modelocarga.setCellEditable(true, 5);
//			modelocarga.setValueAt(1, table.getRowCount() - 1, 5);
			modelocarga.setValueAt(multiplica(), table.getRowCount() - 1, 6);

		}

	}

	private float multiplica() {
		try {
			float num1 = 0;
			if (table.getSelectedRow() < 0) {
				num1 = Float.parseFloat(cnatidadFo.getTextFormater().getText());
//				num1 = Float.parseFloat(table.getModel().getValueAt(table.getRowCount() - 1, 5).toString());

			} else {

				num1 = Float.parseFloat(table.getModel().getValueAt(table.getSelectedRow(), 5).toString());

			}
			float num2 = Float.parseFloat(modelocarga.getValueAt(table.getRowCount() - 1, 4).toString());
			return num1 * num2;
		} catch (NullPointerException e) {
			// TODO: handle exception
			return 0;
		}
	}

	private void eliminaElemento() {
		if (modeloEleccion.getRowCount() > 0) {
			modeloEleccion.borraFila(table_1.getSelectedRow());

			table_1.repaint();
		}

	}

	private void operacionDeSubtotales() {
		// TODO Auto-generated method stub
		String valor = cnatidadFo.getTextFormater().toString();
//		try {	

		if (table.getRowCount() > 0 && table.getSelectedRow() > 0) {// corrigiendo
//				modelocarga.setValueAt(valor, table.getSelectedRow(), 5);
			if (table.getSelectedRow() > 0) {
				float subtotal = Float.valueOf(modelocarga.getValueAt(table.getSelectedRow(), 4).toString())
						* Integer.parseInt(modelocarga.getValueAt(table.getSelectedRow(), 5).toString());
				modelocarga.setValueAt(String.valueOf(subtotal), table.getSelectedRow(), 6);
				if (subtotal>999999) {
					JOptionPane.showMessageDialog(null, "Debe cargar una cantidad mas baja", "Numero demaciado grande", 2);
				}
				sumaTotal();
			} else {
				if (valor.length() > 0) {
					float subtotal = Float.valueOf(modelocarga.getValueAt(table.getRowCount() - 1, 4).toString())
							* Integer.parseInt(modelocarga.getValueAt(table.getRowCount() - 1, 5).toString());
					modelocarga.setValueAt(String.valueOf(subtotal), table.getRowCount() - 1, 6);

					sumaTotal();
				}
			}
		} else {

			for (int i = 0; i < modelocarga.getRowCount(); i++) {

				float subtotal = Float.valueOf(modelocarga.getValueAt(i, 4).toString())
						* Integer.parseInt(modelocarga.getValueAt(i, 5).toString());
				modelocarga.setValueAt(String.valueOf(subtotal), i, 6);
			}

			sumaTotal();

		}
		operacionPorcentage();
//		} catch (NumberFormatException e) {
//			System.out.println("capturo en 1");
//			cnatidadFo.getTextFormater().setText("1");
//			cnatidadFo.getTextFormater().requestFocus();
//		}
	}

	private void sumaTotal() {
		if (modelocarga.getRowCount() > 0) {
			total = 0;
			float totPro = 0;
			for (int i = 0; i < modelocarga.getRowCount(); i++) {
				total += Float.parseFloat(modelocarga.getValueAt(i, 6).toString());
				totPro += Float.parseFloat(modelocarga.getValueAt(i, 6).toString());
				txtCostoprod.setText(String.valueOf(totPro));
			}
		}
		txtTotal.setText(String.valueOf(total));
	}

	private void operacionPorcentage() {
		if (Integer.parseInt(spinner_1.getValue().toString()) >= 0) {
			System.out.println(spinner_1.getValue());
			float porcin = total * (Float.parseFloat(spinner_1.getValue().toString()) / 100);
			txtBeneficio.setText(String.valueOf(porcin));
			txtTotal.setText(String.valueOf(total + porcin));
		}
	}

	private void eliminaItemDeLista() {
		if (modelocarga.getRowCount() > 0) {
			modelocarga.borraFila(table.getSelectedRow());
			if (modelocarga.getRowCount() > 0) {// arreglado
				table.clearSelection();
				table.setModel(modelocarga);
				table.clearSelection();
				table.repaint();
				table.revalidate();
				item--;

			}
		}

	}

//lo hace en la tabla de productos a asignar 
	private void recargaElementoTabla1() {
		if (modelocarga.getRowCount() > 0) {
			for (Producto producto : productos) {
				if (producto.getCodigoProducto() == Integer
						.parseInt(modelocarga.getValueAt(table.getSelectedRow(), 1).toString())) {
					LinkedList<Object> ob = new LinkedList<Object>();
					ob.add(producto.getCodigoProducto());
					ob.add(producto.getModelo());
					ob.add(producto.getVoltamperios());
					modeloEleccion.aniadeFila(ob);

					table_1.repaint();
				}
			}
		}

	}

	private boolean validaCantidad() {
		boolean salir = true;
		if (modelocarga.getRowCount() > 0) {
			for (int f = 0; f < table.getModel().getRowCount(); f++) {
				try {
					int r = Integer.valueOf(table.getValueAt(f, 5).toString());
				} catch (NumberFormatException e) {
					return false;
				}
			}
		}
		return salir;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			dispose();
		}
		if (e.getSource() == btnGeneraPresupuesto) {
			try {
				int r = Integer.valueOf(cnatidadFo.getTextFormater().getText());
				if (validaCantidad()) {
					generarPresupuesto();
					int i = Integer.valueOf(txtIdpres.getText());
					i++;
					txtIdpres.setText(String.valueOf(i));
					numeroPrees = i;
					cnatidadFo.getTextFormater().transferFocus();
				}
			} catch (NumberFormatException e2) {
				System.out.println("errrroeeee");
				JOptionPane.showMessageDialog(null, "Ingrese un numero", "Error de tipo de dato", 0);

				cnatidadFo.getTextFormater().requestFocus();
				cnatidadFo.getTextFormater().setSelectionStart(0);
				cnatidadFo.getTextFormater().setSelectionEnd(cnatidadFo.getTextFormater().getText().length());

			}

		}

		// TODO Auto-generated method stub
		if (e.getSource() == btnEliminarProducto) {
			

				System.out.println("seleccion   " + table.getSelectedRow());
				if ((table.getSelectedRow() >= 0)) {
					if (table.isEditing()) {
						table.getCellEditor().stopCellEditing();

					}
					recargaElementoTabla1();
					eliminaItemDeLista();
					calculoDias();
					sumaTotal();// que onda
					cuentaItems();
					operacionPorcentage();
					table.clearSelection();
					if (table.getModel().getRowCount() == 0) {
						item = 1;
						txtCostoprod.setText("");
						txtTotal.setText("");
						txtBeneficio.setText("");
					}
				}

				repaint();
			
		}
		if (comboBox == e.getSource()) {
			if (comboBox_1.getItemCount() > 0) {

				comboBox_1.removeAllItems();
				comboBox_1.repaint();
				cargarCombo2();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table_1) {

			if (table.isEditing()) {
				table.getCellEditor().stopCellEditing();
				table.clearSelection();
			}
			System.out.println("paso");

//			if (table.getSelectedRow()>=0) {
//				table.getModel().setValueAt(String.valueOf(cnatidadFo.getValue()), table.getSelectedRow(), 5);
//				table.repaint();
//			}
//			cnatidadFo.setValue(1);
			seleccionSet();
			eliminaElemento();

			operacionDeSubtotales();
			calculoDias();
//			cuentaItems();

		}

		if (table == e.getSource()) {
			// cnatidadFo.setValue(Integer.valueOf(table.getModel().getValueAt(table.getSelectedRow(),
			// 5).toString()));
			// cnatidadFo.repaint();
//			cnatidadFo.setText(table.getModel().getValueAt(table.getSelectedRow(), 5).toString());
			if (table.isEditing()) {
				table.getCellEditor().stopCellEditing();

			}

			operacionDeSubtotales();
			calculoDias();
			System.out.println(table.getModel().getColumnClass(table.getSelectedColumn()));
		}
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
		if (e.getSource() == cnatidadFo.getTextFormater()) {

			try {
				int r = Integer.valueOf(cnatidadFo.getTextFormater().getText());

			} catch (NumberFormatException e2) {
				System.out.println("errrroeeee");
				JOptionPane.showMessageDialog(null, "Ingrese un numero", "Error de tipo de dato", 0);

				cnatidadFo.getTextFormater().requestFocus();
				cnatidadFo.getTextFormater().setSelectionStart(0);
				cnatidadFo.getTextFormater().setSelectionEnd(cnatidadFo.getTextFormater().getText().length());

			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == cnatidadFo) {
//			cnatidadFo.setValue(1);
//			cnatidadFo.setText(table.getModel().getValueAt(table.getSelectedRow(), 5).toString());
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
//		if (e.getSource() == cnatidadFo) {
//
//			modelocarga.setValueAt(cnatidadFo.getText(), table.getSelectedRow(), 5);
//
//		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == table) {
			System.out.println("tabla  ");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getSource() == cnatidadFo.getTextFormater()) {
			System.out.println("editor cantidad");

			if (!cnatidadFo.getTextFormater().getText().equals("")) {
				try {
					int f = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 5).toString());
					int r = Integer.valueOf(cnatidadFo.getTextFormater().getText());
					System.out.println("editor cantidad");
					String val;
					if (!cnatidadFo.getTextFormater().getText().equals("")) {

						val = cnatidadFo.getTextFormater().getText();

					} else {
						val = "0";

					}
					modelocarga.setValueAt(val, table.getSelectedRow(), 5);
					table.setModel(modelocarga);
					System.out.println("Estoy   " + modelocarga.getValueAt(table.getSelectedRow(), 5));
					operacionDeSubtotales();
					calculoDias();
				} catch (NumberFormatException t) {
					System.out.println("captura");
					cnatidadFo.getTextFormater().setText("1");
					modelocarga.setValueAt(1, table.getSelectedRow(), 5);
					operacionDeSubtotales();
					calculoDias();
					JOptionPane.showMessageDialog(null, "Debe ingresar un número", "Error en el tipo de dato", 0);

				}
			}

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == cnatidadFo.getTextFormater()) {
			FormatedGenerico.formatoInteger(e);

		}
		if (e.getSource() == form) {
			System.out.println("entra");
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == spinner_1) {
			operacionPorcentage();
			calculoDias();
		}
		if (e.getSource() == spinner) {
			operacionPorcentage();
			calculoDias();
		}
		if (e.getSource() == spinner_2) {
			operacionPorcentage();
			calculoDias();
		}
	}
}
