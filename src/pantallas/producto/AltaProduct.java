package pantallas.producto;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import clases.coneccion.Fachada;
import clases.principales.productos.LineaMaterial;
import clases.principales.productos.Material;
import clases.principales.productos.Producto;
import pantallas.componenteGenerico.EditorFromated;
import pantallas.componenteGenerico.FormatedGenerico;
import pantallas.componenteGenerico.IntegerEditor;
import pantallas.componenteGenerico.ModeloTable;

@SuppressWarnings("serial")
public class AltaProduct extends JDialog implements KeyListener, MouseListener, ActionListener, FocusListener {
	private Vector<Vector<String>> matriz;
	private JPanel panel;
	private JTextField txtCodigo;
	private JTextField txtModelo;
	private JTextField txtVolt;
	private JFormattedTextField frmtdtxtfldCantidadHors;
	private JButton btnEliminarLinea;
	private JComboBox<Integer> comboBox_1;
	private JComboBox<String> comboBox;
	private JButton btnSalir;
	private ButtonGroup grupoRadio;
	private JRadioButton rdbtnUsoInterior;
	private JRadioButton rdbtnUsoExterior;
	private JButton btnGuardar;
	private JPanel panel_1;
	private Fachada fac;
	private Vector<LineaMaterial> lineamat;
	private Vector<Material> mat;
	private Vector<Producto> productos;
	private JScrollPane scrollPane;
	private ModeloTable modelo;
	private JTable table_1;
	private int item;
//	private JSpinner cantidad;
	private Producto producto;
	private JTextField txtSumatotal;
	private int remover;
	private JButton btnAgregarElemento;
	private String rmueve;
	private JRadioButton rdbtnSeleccionPorId;
	private JRadioButton rdbtnSeleccionPorDescripcion;
	private ButtonGroup option2;
	private EditorFromated cantidad;
//	private EditorFromated edit;
	private JFormattedTextField ed;
	private double total;
	private JComboBox<String> comboBox_2;
	private IntegerEditor enteritos = new IntegerEditor(1, 300);
	private int seleccion = 0;
	private Vector<Integer> valores = new Vector<Integer>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaProduct dialog = new AltaProduct();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public AltaProduct() {
		cantidad = new EditorFromated();
		producto = new Producto();
		item = 0;
		modelo = new ModeloTable();
		fac = new Fachada();
		setTitle("Alta producto");
		setBounds(100, 100, 712, 714);
		getContentPane().setLayout(null);
		matriz = fac.selecthsHombre();
		JLabel lblIdProducto = new JLabel("Id producto");
		lblIdProducto.setBounds(56, 13, 139, 16);
		getContentPane().add(lblIdProducto);

		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(56, 58, 123, 16);
		getContentPane().add(lblModelo);

		JLabel lblVoltamperios = new JLabel("Voltamperios");
		lblVoltamperios.setBounds(56, 103, 123, 16);
		getContentPane().add(lblVoltamperios);

		JLabel lblHorasHombre = new JLabel("Horas Hombre");
		lblHorasHombre.setBounds(56, 150, 123, 16);
		getContentPane().add(lblHorasHombre);

		JLabel lblCostoHoraHombre = new JLabel("Costo hora hombre");
		lblCostoHoraHombre.setBounds(56, 196, 110, 16);
		getContentPane().add(lblCostoHoraHombre);
		grupoRadio = new ButtonGroup();
		grupoRadio.add(rdbtnUsoExterior);
		grupoRadio.add(rdbtnUsoInterior);
		panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(138, 245, 375, 59);
		getContentPane().add(panel);
		panel.setLayout(null);

		rdbtnUsoExterior = new JRadioButton("Uso exterior");
		grupoRadio.add(rdbtnUsoExterior);
		rdbtnUsoExterior.setBounds(231, 25, 123, 25);
		panel.add(rdbtnUsoExterior);

		rdbtnUsoInterior = new JRadioButton("Uso interior");
		grupoRadio.add(rdbtnUsoInterior);
		rdbtnUsoInterior.setBounds(64, 25, 123, 25);
		panel.add(rdbtnUsoInterior);

		JLabel lblLugarDeUso = new JLabel("");
		lblLugarDeUso.setBounds(12, 0, 114, 16);
		panel.add(lblLugarDeUso);
		rdbtnUsoInterior.setSelected(true);
		txtCodigo = new JTextField();
		txtCodigo.setText("");
		txtCodigo.setBounds(304, 10, 116, 22);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);

		txtModelo = new JTextField();
		txtModelo.setText("");
		txtModelo.setBounds(304, 55, 116, 22);
		getContentPane().add(txtModelo);
		txtModelo.setColumns(10);

		txtVolt = new JTextField();
		txtVolt.setText("");
		txtVolt.setBounds(304, 100, 116, 22);
		getContentPane().add(txtVolt);
		txtVolt.setColumns(10);

		frmtdtxtfldCantidadHors = new JFormattedTextField();
		frmtdtxtfldCantidadHors.setText("");

		frmtdtxtfldCantidadHors.setBounds(304, 150, 116, 22);
		getContentPane().add(frmtdtxtfldCantidadHors);

		panel_1 = new JPanel();

		panel_1.setBounds(12, 319, 677, 313);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblSeleccionId = new JLabel("Seleccion id");
		lblSeleccionId.setBounds(12, 69, 111, 16);
		panel_1.add(lblSeleccionId);

		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(295, 69, 111, 16);
		panel_1.add(lblDescripcion);

		comboBox = new JComboBox();

		comboBox.setEnabled(false);
		comboBox.setBounds(418, 65, 148, 24);
		panel_1.add(comboBox);

		comboBox_1 = new JComboBox();

		comboBox_1.setEnabled(false);
		comboBox_1.setBounds(135, 65, 148, 24);
		panel_1.add(comboBox_1);

		btnEliminarLinea = new JButton("Eliminar material");
		btnEliminarLinea.setEnabled(false);
		btnEliminarLinea.setBounds(390, 98, 176, 25);
		panel_1.add(btnEliminarLinea);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 136, 653, 119);
		panel_1.add(scrollPane);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(579, 645, 99, 25);
		getContentPane().add(btnSalir);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(463, 645, 99, 25);
		getContentPane().add(btnGuardar);

		cargaTabla();
		txtCodigo.setEditable(false);

		table_1 = new JTable();

		table_1.setModel(modelo);
		scrollPane.setViewportView(table_1);
		scrollPane.setColumnHeaderView(table_1.getTableHeader());
		txtSumatotal = new JTextField();
		txtSumatotal.setEditable(false);
		txtSumatotal.setText("0");
		txtSumatotal.setBounds(512, 268, 116, 22);
		panel_1.add(txtSumatotal);
		txtSumatotal.setColumns(10);

		JLabel lblCosteTotal = new JLabel("Coste total");
		lblCosteTotal.setBounds(378, 274, 90, 16);
		panel_1.add(lblCosteTotal);

		btnAgregarElemento = new JButton("Agregar elemento");
		btnAgregarElemento.setEnabled(false);
		btnAgregarElemento.setBounds(107, 98, 176, 25);
		panel_1.add(btnAgregarElemento);

		rdbtnSeleccionPorId = new JRadioButton("Seleccion por Id");
		rdbtnSeleccionPorId.setSelected(true);
		rdbtnSeleccionPorId.setBounds(135, 24, 123, 25);
		panel_1.add(rdbtnSeleccionPorId);

		rdbtnSeleccionPorDescripcion = new JRadioButton("Seleccion por descripcion");
		rdbtnSeleccionPorDescripcion.setBounds(418, 24, 182, 25);
		panel_1.add(rdbtnSeleccionPorDescripcion);
//		comboBox.addActionListener(this);
		option2 = new ButtonGroup();
		option2.add(rdbtnSeleccionPorId);
		option2.add(rdbtnSeleccionPorDescripcion);

		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(304, 192, 116, 24);
		cargaProdMat();
		getContentPane().add(comboBox_2);
//		comboBox_1.addActionListener(this);
		// cantidad.setValue(1);
//		edit = new EditorFromated();

		table_1.setDefaultEditor(Integer.class, enteritos);
//		table_1.setDefaultEditor(Object.class, cantidad);

//		edit.addKeyListener(this);
		cargaListeners();
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void vaciaTabla() {
		if (table_1.getModel().getRowCount() > 0) {
			modelo.removeAll();
			table_1.setModel(modelo);
		}
	}

	private void recargaCombos() {
		if (modelo.getRowCount() >= 0) {

			for (int m = 0; m < mat.size(); m++) {
				boolean paso = true;
				int f = modelo.getRowCount() - 1;
				while (paso && f >= 0) {
					if (mat.get(m).getIdMaterial() == Integer.valueOf(modelo.getValueAt(f, 1).toString())) {
						paso = false;

					}
					f--;
				}

				if (paso) {
					comboBox.addItem(mat.get(m).getDescripcion());
					comboBox_1.addItem(mat.get(m).getIdMaterial());
				}

			}

		}
	}

	private void cargaListeners() {
		// TODO Auto-generated method stub
		frmtdtxtfldCantidadHors.addKeyListener(this);
		txtVolt.addKeyListener(this);
		panel_1.addMouseListener(this);
		btnEliminarLinea.addActionListener(this);
		btnGuardar.addActionListener(this);
		btnSalir.addActionListener(this);
//		comboBox_1.addActionListener(this);
//		comboBox.addActionListener(this);
		btnAgregarElemento.addActionListener(this);
		rdbtnSeleccionPorDescripcion.addMouseListener(this);
		rdbtnSeleccionPorId.addMouseListener(this);
//		table_1.addKeyListeners(this);
//		edit.addFocusListener(this);
		table_1.addMouseListener(this);
//		edit.addActionListener(this);
//		edit.addMouseListener(this);
		comboBox_2.addActionListener(this);
		txtVolt.addFocusListener(this);
		frmtdtxtfldCantidadHors.addFocusListener(this);
		table_1.addKeyListener(this);
		cantidad.addKeyListener(this);
		enteritos.getTextFormater().addKeyListener(this);
		enteritos.getTextFormater().addMouseListener(this);
		enteritos.getTextFormater().addFocusListener(this);
		btnAgregarElemento.addMouseListener(this);
		btnGuardar.addMouseListener(this);
		table_1.addKeyListener(this);
	}

	private void cargaTabla() {

		modelo.aniadeColumna("Item");
		modelo.aniadeColumna("Id material");
		modelo.aniadeColumna("Descripcion");
		modelo.aniadeColumna("Precio actual");
		modelo.aniadeColumna("Unidad de  medida");
		modelo.aniadeColumna("Cantidad");
	}

	private void cargaProdMat() {
		productos = (Vector<Producto>) fac.selectProducto();
		mat = (Vector<Material>) fac.selectMaterial();
		txtCodigo.setText(Integer.toString(productos.size() + 1));
		cargaCombos();
	}

	private void cargaCombos() {
		int count = 0;

		for (Material material : mat) {

			comboBox.addItem(material.getDescripcion());
			comboBox_1.addItem(material.getIdMaterial());

//			comboBox_1.insertItemAt(String.valueOf(material.getIdMaterial()), material.getIdMaterial());
			count++;
		}
		comboBox_1.setSelectedIndex(0);
		if (comboBox_2.getItemCount() == 0) {
			for (int i = 0; i < matriz.size(); i++) {
				String pala = matriz.get(i).get(2);
				System.out.println(pala + "    " + i);
				comboBox_2.addItem(pala);
			}
		}

	}

	private void suma2(int valor) {
		// TODO Auto-generated method stub
		sumaTotal();
		try {

			if (modelo.getRowCount() > 0) {

				for (int f = 0; f < modelo.getRowCount(); f++) {

					total += Integer.parseInt(modelo.getValueAt(f, 5).toString())
							* Float.parseFloat(modelo.getValueAt(f, 3).toString());
					System.out.println(total);

				}
			} else {
				total = valor + total;
			}
			txtSumatotal.setText(String.valueOf(total));
		} catch (NumberFormatException e) {
			System.out.println(" errrrooooooooeeeeeoororror");
		}
	}

	private boolean isEmpty() {
		if (!frmtdtxtfldCantidadHors.getText().equals("")) {
			return true;
		}
		return false;
	}

	private void asignaLineasProducto() throws NumberFormatException {
		if (producto.getLineas().size() > 0) {
			for (int e = 0; e < modelo.getRowCount(); e++) {

				producto.getItems().get(e).setCantidadNecesaria(modelo.getValueAt(e, 5).toString());
				producto.getItems().get(e).setMaterial(modelo.getValueAt(e, 1).toString());
				producto.getItems().get(e).setProducto(txtCodigo.getText());

			}
		}

	}

	private boolean faltanCamposPorRellenar() {
		if (modelo.getRowCount() > 0) {
			for (int f = 0; f < modelo.getRowCount(); f++) {
				if (modelo.getValueAt(f, 5).toString().equals("0")) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean validaCelda() {
		boolean salir = false;
		if (modelo.getRowCount() >= 0) {
			salir = true;
			for (int f = 0; f < modelo.getRowCount(); f++) {
				if (0 >= Integer.parseInt(modelo.getValueAt(f, 5).toString())
						|| 301 < Integer.parseInt(modelo.getValueAt(f, 5).toString())) {
					System.out.println(modelo.getValueAt(f, 5).toString());

					salir = false;

				}
			}
		}
		return salir;
	}

	private boolean activarElementos() {
		if (!txtCodigo.getText().equals("") && !txtModelo.getText().equals("") && !txtVolt.getText().equals("")
				&& !frmtdtxtfldCantidadHors.getText().equals("")) {

			btnGuardar.setEnabled(true);
			btnEliminarLinea.setEnabled(true);

			if (rdbtnSeleccionPorId.isSelected()) {
				comboBox_1.setEnabled(true);
			}

			btnAgregarElemento.setEnabled(true);

			return true;
		} else {

			btnGuardar.setEnabled(false);
			btnEliminarLinea.setEnabled(false);
			comboBox.setEnabled(false);
			comboBox_1.setEnabled(false);
			btnAgregarElemento.setEnabled(false);
		}
		return false;
	}

	private void seleccionarMaterial() {

		if (comboBox.getItemCount() != 0 && comboBox_1.getItemCount() != 0) {
			Material mater = null;

			for (Material material : mat) {
				boolean paso = false;

				if (comboBox.getItemCount() > 0) {
					if (rdbtnSeleccionPorDescripcion.isSelected()) {
						if (material.getDescripcion().equals(comboBox.getSelectedItem().toString())) {
							paso = true;
						}
					} else {
						if (material.getIdMaterial() == Integer.valueOf(comboBox_1.getSelectedItem().toString())) {
							paso = true;
						}
					}
				}

				if (paso) {
					mater = material;
					LinkedList lista = new LinkedList();
					item++;
					lista.add(String.valueOf(item));
					lista.add(String.valueOf(material.getIdMaterial()));
					lista.add(material.getDescripcion());
					lista.add(String.valueOf(material.getPrecioActual()));
					lista.add(material.getTipoMaterial().getCaracteristica().getUnidadMedida());
					lista.add(new Integer(1));

					Vector<Boolean> bol = new Vector<Boolean>();
					bol.add(false);
					bol.add(false);
					bol.add(false);
					bol.add(false);
					bol.add(false);
					bol.add(true);

					// cantidad.setText("1");

					modelo.aniadeFila(lista);
					modelo.setCellEditable(bol);
					sumaTotal();
					producto.agregaMaterial(mater, 0, item);
					producto.setCategoria(comboBox_2.getSelectedIndex());
					comboBox.removeAllItems();
					comboBox_1.removeAllItems();

					suma2(0);
				}

			}

		}

	}

	private void cuentaModelo() {
		if (modelo.getRowCount() > 0) {
			for (int i = 0; i < modelo.getRowCount(); i++) {
//				modelo.setValueAt(i+1, i, 0);
				modelo.modificaCelda(i + 1, i, 0);
				item = i + 1;
			}

		} else {
			item = 0;
		}

	}

	private void sumaTotal() {
		try {
			double hs = Float.valueOf(comboBox_2.getSelectedItem().toString());
			double cantidad = Float.parseFloat(frmtdtxtfldCantidadHors.getText());

			double suma = hs * cantidad;
			if (suma > 999999999) {
				JOptionPane.showMessageDialog(null, "Numero demaciado grande", "Fallo en la ejecucion ", 0);
				frmtdtxtfldCantidadHors.setSelectionStart(0);
				frmtdtxtfldCantidadHors.setSelectionEnd(frmtdtxtfldCantidadHors.getText().length());
				txtSumatotal.setText("0");
			} else {
				txtSumatotal.setText(String.valueOf(suma));
				this.total = suma;
			}
		} catch (InputMismatchException | NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Formato numerico invalido", "Fallo en la ejecucion " + e, 0);
			frmtdtxtfldCantidadHors.getText().length();
			frmtdtxtfldCantidadHors.setSelectionStart(0);
			frmtdtxtfldCantidadHors.setSelectionEnd(frmtdtxtfldCantidadHors.getText().length());
			frmtdtxtfldCantidadHors.requestFocus();
		} catch (ArithmeticException e) {
			System.out.println("numero demaciado grande");
		}
	}

	private void vaciaCombo2() {
		// TODO Auto-generated method stub
		if (comboBox_2.getItemCount() > 0) {
			for (int i = comboBox_2.getItemCount() - 1; i > 0; i--) {
				comboBox_2.remove(i);
			}
		}
	}

	private void resFormTes() {
		// TODO Auto-generated method stub
//		enteritos.getTextFormater().setText("1");
//		cantidad= null;
//		cantidad=new EditorFromated();
//		cantidad.setText("1");
//		ed= null;
//		ed = new JFormattedTextField();
//		ed.setText("1");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (table_1 == e.getSource()) {
			System.out.println("presiono  sisisisiss");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == frmtdtxtfldCantidadHors) {
			if (isEmpty()) {

				sumaTotal();
			}
		}
//	
		if (e.getSource() == enteritos.getTextFormater()) {
			if (!enteritos.getTextFormater().getText().equals("")) {
				try {
					if (table_1.getSelectedRow() >= 0) {

						int enter = Integer.valueOf(enteritos.getTextFormater().getText());
						valores.set(table_1.getSelectedRow(), enter);
						modelo.setValueAt(enter, table_1.getSelectedRow(), 5);
						suma2(enter);
						System.out.println(valores.get(table_1.getSelectedRow()));
					}

				} catch (NumberFormatException t) {
					System.out.println("captura");
					JOptionPane.showMessageDialog(null, "Debe ingresar un número", "Error en el tipo de dato", 0);
					enteritos.getTextFormater()
							.setValue(modelo.getValueAt(table_1.getSelectedRow(), table_1.getSelectedColumn()));
					;
				}
			}

		}

		if (e.getSource() == table_1) {
			System.out.println("toca tabla");
//			table_1.getModel().setValueAt(edit.getValue().toString(), table_1.getSelectedRowCount(), 5);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
//		if (e.getSource() == edit) {
//			FormatedGenerico.formatoInteger(e);
//			if (edit.getText().length() == 4) {
//				e.consume();
//				JOptionPane.showMessageDialog(null, "No se puede ingresar mas horas", "Numero demaciado grande", 0);
//			}
//		}

		if (e.getSource() == frmtdtxtfldCantidadHors) {
			FormatedGenerico.formatoInteger(e);
			if (frmtdtxtfldCantidadHors.getText().length() == 4) {
				e.consume();
				JOptionPane.showMessageDialog(null, "No se puede ingresar mas horas", "Numero demaciado grande", 0);
			}
		}
		if (e.getSource() == txtVolt)
			FormatedGenerico.formatoInteger(e);
//		if (e.getSource() ==table_1.getInputMethodListeners()) {
// 			FormatedGenerico.formatoInteger(e);
//		}
		if (e.getSource() == enteritos.getTextFormater()) {

			FormatedGenerico.formatoInteger(e);
		}
		if (table_1==e.getSource()) {
			FormatedGenerico.formatoInteger(e);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == rdbtnSeleccionPorDescripcion) {
			comboBox.setEnabled(true);
			comboBox_1.setEnabled(false);
		}
		if (e.getSource() == rdbtnSeleccionPorId) {
			comboBox.setEnabled(false);
			comboBox_1.setEnabled(true);
		}
		if (e.getSource() == table_1) {
			if (modelo.getRowCount() != 0) {
//				edit.setText(modelo.getValueAt(table_1.getSelectedRow(), 5).toString());
				try {
					suma2(Integer.parseInt(enteritos.getTextFormater().getText()));
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Debe ingresar un numero", "Error en el tipo de dato", 1);

				}
				
			}

		}
		if (enteritos.getTextFormater() == e.getSource()) {
			enteritos.getTextFormater().select(0, enteritos.getTextFormater().getText().length());
		}
		if (e.getSource() == btnAgregarElemento) {
			if (table_1.getRowCount() > 0) {
				if (table_1.getSelectedRow() >= 0) {
					seleccion = table_1.getSelectedRow();

					if (enteritos.getTextFormater().isFocusOwner()) {
						System.out.println("Aun tiene foco");
					}
					table_1.clearSelection();
					suma2(Integer.valueOf(enteritos.getTextFormater().getText()));
				}
			}

		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == panel_1) {

			activarElementos();
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnEliminarLinea) {
			if (table_1.getSelectedRow() > -1 && table_1.getSelectedRowCount() == 1
					&& table_1.getSelectedRow() < table_1.getRowCount()) {
				if (modelo.getRowCount() > 0) {
					if (table_1.isEditing()) {
						table_1.getCellEditor().stopCellEditing();
					}
					int filaselec = table_1.getSelectedRow();
					System.out.println();
					producto.eliminaMaterial(Integer.parseInt(modelo.getValueAt(filaselec, 1).toString()));
					modelo.borraFila(filaselec);

					table_1.setModel(modelo);
//					table_1.setValueAt(String.valueOf(producto.cantidadItems()), producto.cantidadItems()-1, 0);
					table_1.repaint();

					comboBox.removeAllItems();
					comboBox_1.removeAllItems();

					recargaCombos();
					suma2(0);
					cuentaModelo();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un item", "Operacion no realizada", 0);
			}

		}

		if (e.getSource() == btnGuardar) {
			if (modelo.getRowCount() > 0) {
				if (table_1.isEditing()) {
					table_1.getCellEditor().stopCellEditing();
				}
				if (validaCelda()) {
				//	enteritos.getTextFormater().transferFocus();
					if (table_1.isEditing()) {
						// table_1.getCellEditor().stopCellEditing();
						System.out.println("Funciona");
						FormatedGenerico.compruevaNoTieneLetras();
					}

					try {
						asignaLineasProducto();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Debe ingresar un numero", "Error en el tipo de dato", 1);
					}

					producto.setCodigoProducto(Integer.parseInt(txtCodigo.getText()));
					producto.setModelo(txtModelo.getText());
					producto.setVoltamperios(Integer.parseInt(txtVolt.getText()));
					producto.setFlagBaja(1);
					producto.setHorasHombre(Integer.parseInt(frmtdtxtfldCantidadHors.getText()));
					producto.setPrecioHoraHombre(Float.parseFloat(comboBox_2.getSelectedItem().toString()));

					modelo.removeAll();
					comboBox.removeAllItems();
					comboBox_1.removeAllItems();
					recargaCombos();
					if (rdbtnUsoInterior.isSelected()) {
						producto.setLugarUtilizacion("IN");
					} else {
						producto.setLugarUtilizacion("EX");
					}
					fac.insertProducto(producto);

					txtCodigo.setText(String.valueOf(Integer.valueOf(txtCodigo.getText()) + 1));
					txtModelo.setText("");
					txtSumatotal.setText("0");
					txtVolt.setText("");
					frmtdtxtfldCantidadHors.setValue(0);

					vaciaTabla();
					resFormTes();
					JOptionPane.showMessageDialog(null, "Se ha guardado el producto	", "Guardado exitoso	",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "El valor de cantidad debe ser entre 1 y 300",
							"Los campos deben rellenarse", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Debe haber materiales cargados", "Operación no realizada",
						JOptionPane.ERROR_MESSAGE);
			}

		}

		if (e.getSource() == btnSalir) {
			dispose();
		}

		if (e.getSource() == comboBox) {
			if (rdbtnSeleccionPorDescripcion.isSelected()) {
				rmueve = comboBox.getItemAt(comboBox.getSelectedIndex()).toString();

			}
		}

		if (e.getSource() == comboBox_1) {
			if (rdbtnSeleccionPorId.isSelected()) {
				rmueve = comboBox_1.getItemAt(comboBox_1.getSelectedIndex()).toString();
			}
		}

		if (e.getSource() == btnAgregarElemento) {
			valores.add(1);
			if (table_1.getRowCount() > 0) {
				if (table_1.isEditing()) {
					table_1.getCellEditor().stopCellEditing();
				}
			}

			seleccionarMaterial();

			recargaCombos();

		}
		if (e.getSource() == comboBox_2) {
			if (table_1.getModel().getRowCount() > 0 || !frmtdtxtfldCantidadHors.getText().equals(""))
				sumaTotal();
		}

	}

	@Override
	public void focusGained(FocusEvent e) {
		if (enteritos.getTextFormater() == e.getSource()) {
			enteritos.getTextFormater().select(0, enteritos.getTextFormater().getText().length());
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (txtVolt == e.getSource()) {
			if (txtVolt.getText().length() > 0) {

				try {

					int i = Integer.valueOf(txtVolt.getText());

				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Formato numerico invalido", "Fallo en la ejecucion " + e, 0);
					txtVolt.requestFocus();
					txtVolt.setSelectionStart(0);
					txtVolt.setSelectionEnd(txtVolt.getText().length());
				}

			}
		}
		if (frmtdtxtfldCantidadHors == e.getSource()) {
			if (frmtdtxtfldCantidadHors.getText().length() > 0 || frmtdtxtfldCantidadHors.isFocusOwner()) {

				try {

					int i = Integer.valueOf(frmtdtxtfldCantidadHors.getText());

				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Formato numerico invalido", "Fallo en la ejecucion " + e, 0);
					frmtdtxtfldCantidadHors.requestFocus();
					frmtdtxtfldCantidadHors.setSelectionStart(0);
					frmtdtxtfldCantidadHors.setSelectionEnd(frmtdtxtfldCantidadHors.getText().length());
				}

			}
		}
		if (enteritos.getTextFormater() == e.getSource()) {
//			enteritos.getTextFormater().setText("1");
		}
	}

}
