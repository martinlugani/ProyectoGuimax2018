package pantallas.materiales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableRowSorter;

import clases.coneccion.Fachada;
import clases.principales.productos.Material;
import pantallas.componenteGenerico.FormatedGenerico;
import pantallas.componenteGenerico.ModeloTable;

public class PantallaStock extends JDialog
		implements ActionListener, MouseListener, KeyListener, ChangeListener, CaretListener {
	private Fachada fac;
	private Vector<Material> materiales;
	private ModeloTable modelostock;
	private JTextField txtDescrip;
	private JTextField txtUnidad;
	private JTextField textField;
	private JLabel lblFecha;
	private JLabel lblHora;
	private JSpinner spinner_1;
	private JTextField txtDescripfiltro;
	private JTextField txtCodigo;
	private JTextField txtIdfiltro;
	private JButton button;
	private JTextField txtPreciocompra;
	private JButton btnCancelar;
	private JTextField txtFecha;
	private JTextField txHora;
	private TableRowSorter<ModeloTable> filtroTabla;
	private JPanel panel_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PantallaStock dialog = new PantallaStock();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PantallaStock() {
		setModal(true);
		modelostock = new ModeloTable();
		setTitle("Modificar Stock");
		setBounds(100, 100, 676, 647);
//		setVisible(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				"Modifique los valores de Stock", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 369, 646, 174);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblCodigoMaterial = new JLabel("Codigo material");
		lblCodigoMaterial.setBounds(16, 42, 116, 16);
		panel.add(lblCodigoMaterial);

		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(259, 42, 116, 16);
		panel.add(lblDescripcion);

		JLabel lblCantidadComprada = new JLabel("Cantidad comprada");
		lblCantidadComprada.setBounds(506, 42, 116, 16);
		panel.add(lblCantidadComprada);

		JLabel lblPrecioDeCompra = new JLabel("Precio de compra");
		lblPrecioDeCompra.setBounds(16, 104, 117, 16);
		panel.add(lblPrecioDeCompra);

		txtDescrip = new JTextField();
		txtDescrip.setEditable(false);
		txtDescrip.setBounds(259, 70, 116, 22);
		panel.add(txtDescrip);
		txtDescrip.setColumns(10);

		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(10)));
		spinner_1.setBounds(506, 71, 116, 20);
		panel.add(spinner_1);

		JLabel lblUnidadDeMedida = new JLabel("Unidad de medida");
		lblUnidadDeMedida.setBounds(259, 104, 116, 16);
		panel.add(lblUnidadDeMedida);

		txtUnidad = new JTextField();
		txtUnidad.setEditable(false);
		txtUnidad.setBounds(259, 132, 116, 22);
		panel.add(txtUnidad);
		txtUnidad.setColumns(10);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(506, 132, 116, 22);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblCosteTotal = new JLabel("Coste total");
		lblCosteTotal.setBounds(506, 104, 116, 16);
		panel.add(lblCosteTotal);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(16, 69, 116, 22);
		panel.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtPreciocompra = new JTextField();
		txtPreciocompra.setBounds(16, 132, 116, 22);
		panel.add(txtPreciocompra);
		txtPreciocompra.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 40, 646, 73);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Descripcion");
		lblNewLabel.setBounds(303, 25, 116, 16);
		panel_1.add(lblNewLabel);

		txtDescripfiltro = new JTextField();
		txtDescripfiltro.setBounds(467, 22, 116, 22);
		panel_1.add(txtDescripfiltro);
		txtDescripfiltro.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Codigo material");
		lblNewLabel_1.setBounds(23, 25, 116, 16);
		panel_1.add(lblNewLabel_1);

		txtIdfiltro = new JTextField();
		txtIdfiltro.setBounds(138, 22, 116, 22);
		panel_1.add(txtIdfiltro);
		txtIdfiltro.setColumns(10);

		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(379, 16, 56, 16);
		getContentPane().add(lblFecha);

		lblHora = new JLabel("Hora");
		lblHora.setBounds(519, 16, 41, 16);
		getContentPane().add(lblHora);

		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setText("");
		txtFecha.setBounds(434, 13, 75, 22);
		getContentPane().add(txtFecha);
		txtFecha.setColumns(10);

		filtroTabla = new TableRowSorter<ModeloTable>(modelostock);

		txHora = new JTextField();
		txHora.setEditable(false);
		txHora.setBounds(572, 13, 46, 22);
		getContentPane().add(txHora);
		txHora.setColumns(10);

		panel_2 = new JPanel();
		panel_2.setBorder(
				new TitledBorder(null, "Seleccione un material", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(22, 124, 646, 236);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 615, 191);
		panel_2.add(scrollPane);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3.setBounds(12, 556, 646, 51);
		getContentPane().add(panel_3);

		button = new JButton("Modificar Stock");

		btnCancelar = new JButton("Cancelar");
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel_3.createSequentialGroup().addContainerGap(243, Short.MAX_VALUE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addGap(26)));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3
						.createSequentialGroup().addContainerGap().addGroup(gl_panel_3
								.createParallelGroup(Alignment.BASELINE).addComponent(btnCancelar).addComponent(button))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_3.setLayout(gl_panel_3);

		fac = new Fachada();
		table = new JTable();
		scrollPane.setViewportView(table);
		listeners();
//nota corregir ultimo precio;
//		cargarMateriales();
		refrescaPantalla();
		setVisible(true);
	}

	private void listeners() {
		spinner_1.addChangeListener(this);
		button.addActionListener(this);
		txtPreciocompra.addKeyListener(this);
		txtIdfiltro.addKeyListener(this);
		txtIdfiltro.addCaretListener(this);
		txtDescripfiltro.addCaretListener(this);
		btnCancelar.addActionListener(this);
		table.addMouseListener(this);
		table.setRowSorter(filtroTabla);
		txtIdfiltro.addMouseListener(this);
		txtDescripfiltro.addMouseListener(this);
		txtPreciocompra.addCaretListener(this);
	}

	private void cargaMaterialaModificar() {
		// TODO Auto-generated method stub

//		if (txtIdfiltro.getText().equals("") && txtDescripfiltro.getText().equals("")) {
		txtCodigo.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
		txtDescrip.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
		cargaUnidadMedida();
		txtPreciocompra.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
//		} else {
		seleccionaConFiltro();
//		}

		multiplica();

	}

	private void seleccionaConFiltro() {

	}

	private void refrescaPantalla() {
		limpiaValores();
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		LocalDate fechaLocal = LocalDate.now();
		Date fecha = new Date();
		txtFecha.setText(formato.format(fecha));
		LocalDate horaActual = LocalDate.now(Clock.systemUTC());
		DateFormat formatoh = new SimpleDateFormat("HH:mm");
		Date hora = new Date();

		txHora.setText(formatoh.format(hora));
		modelostock.removeAll();
		cargarMateriales();

	}

	private void cargaUnidadMedida() {
		for (Material material : materiales) {
			if (material.getIdMaterial() == Integer.parseInt(txtCodigo.getText())) {
				txtUnidad.setText(material.getTipoMaterial().getCaracteristica().getUnidadMedida());
			}
		}

	}

	private void multiplica() {
		float tot = Float.valueOf(txtPreciocompra.getText().toString())
				* Integer.valueOf(spinner_1.getValue().toString());
		textField.setText(String.valueOf(tot));
	}

	private void cargarMateriales() {
		
		if (modelostock.getRowCount() <= 0) {

			materiales = (Vector<Material>) fac.selectMaterial();
		}

		if (modelostock.getColumnCount() <= 0) {
			modelostock.aniadeColumna("Codigo material");
			modelostock.aniadeColumna("Descripcion");
			modelostock.aniadeColumna("Ultimo precio");
			modelostock.aniadeColumna("Precio actual");
			modelostock.aniadeColumna("Stock ");
			modelostock.aniadeColumna("Stock minimo");
			modelostock.aniadeColumna("Alerta ");
		}
		modelostock.removeAllAll();
		if (modelostock.getRowCount()>0) {
		
		}

		for (Material material : materiales) {
			LinkedList<Object> fila = new LinkedList<>();
			fila.add(String.valueOf(material.getIdMaterial()));
			fila.add(material.getDescripcion());
			fila.add(material.getUltimoPrecio());
			fila.add(material.getPrecioActual());
			fila.add(material.getStockActual());
			fila.add(material.getPuntoPedido());
			if (material.getStockActual() < material.getPuntoPedido()) {
				fila.add("STOCK INSUFICIENTE");
			} else {
				fila.add("Stock normal");
			}

			modelostock.aniadeFila(fila);
			modelostock.bloqueaEdicion(false);
			table.setModel(modelostock);
		}
	}

	private Material obtieneMaterial() {
		for (Material material : materiales) {
			if (material.getIdMaterial() == Integer.parseInt(txtCodigo.getText())) {
				return material;
			}
		}
		return null;
	}

	private void modificar() {
		if (!txtCodigo.getText().equals("")) {

			if (spinner_1.getValue().toString().equals("0")) {
				int valor = JOptionPane.showConfirmDialog(null, "Desea continuar", "La cantidad comprada es 0",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (valor == 0) {
					System.out.println("paso");
					modificando();
				}
			} else {

				if (Float.parseFloat(txtPreciocompra.getText()) < obtieneMaterial().getUltimoPrecio()) {
					int valor = JOptionPane.showConfirmDialog(null, "Desea continuar",
							"El precio de compra es menor al precio anterior", JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);
					if (valor == 0) {
						System.out.println("precio");
						modificando();
					}
				}
				modificando();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Seleccione un material ", "Campos vacios", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void modificando() {
		if (!txtCodigo.getText().equals("")) {
			for (Material material : materiales) {
				if (material.getIdMaterial() == Integer.parseInt(txtCodigo.getText())) {

					if (material.getUltimoPrecio() < Float.parseFloat(txtPreciocompra.getText())
							&& material.getUltimoPrecio() < material.getPrecioActual()) {
						material.setUltimoPrecio(material.getPrecioActual());
					}
					material.setPrecioActual(Float.parseFloat(txtPreciocompra.getText()));
					System.out.println("id   "+material.getIdMaterial());
					material.setStockActual(
							material.getStockActual() + Integer.parseInt(spinner_1.getValue().toString()));
					if (fac.updateMaterial(material)) {
						JOptionPane.showMessageDialog(null, "La operacion se ha realizado con exito",
								"Operacion exitosa", JOptionPane.INFORMATION_MESSAGE);

					}
				}
			}
			refrescaPantalla();
		} else {
			JOptionPane.showMessageDialog(null, "Seleccione un material ", "Campos vacios", JOptionPane.ERROR_MESSAGE);
		}
	}

	private  void limpiaValores() {
		txtIdfiltro.setText("");
		txtDescripfiltro.setText("");
		spinner_1.setValue(0);
		txtCodigo.setText("");
		txtDescrip.setText("");
		txtPreciocompra.setText("0");
		txtUnidad.setText("");
		txtPreciocompra.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == button) {
			modificar();
			table.repaint();
		}
		if (e.getSource() == btnCancelar) {
			dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (txtDescripfiltro == e.getSource()) {
			txtIdfiltro.setText("");
			limpiaValores();
		}
		if (txtIdfiltro == e.getSource()) {
			txtDescripfiltro.setText("");
			limpiaValores();
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
		if (e.getSource() == table) {
			textField.setText("0");
			cargaMaterialaModificar();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == txtIdfiltro) {
			FormatedGenerico.formatoInteger(e);
		}
		if (e.getSource() == txtPreciocompra) {
			FormatedGenerico.filtroFloat(e);
		}
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == spinner_1) {
			multiplica();
		}
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		if (e.getSource() == txtIdfiltro) {
			repaint();
			filtroTabla.setRowFilter(RowFilter.regexFilter(txtIdfiltro.getText(), 0));
		}
		if (e.getSource() == txtDescripfiltro) {
			repaint();
			filtroTabla.setRowFilter(RowFilter.regexFilter("^" + txtDescripfiltro.getText(), 1));
		}
		if (e.getSource() == txtPreciocompra) {
			if (txtPreciocompra.getText().length() > 0) {
				multiplica();
			}
		}
	}
}
