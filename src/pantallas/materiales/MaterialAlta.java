package pantallas.materiales;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import clases.coneccion.Fachada;
import clases.principales.productos.Material;
import clases.principales.productos.TipoMaterial;
import pantallas.componenteGenerico.FormatedGenerico;
import pantallas.componenteGenerico.Manejador;

public class MaterialAlta extends JDialog implements ActionListener, KeyListener, FocusListener {

	private JPanel panel;

	private JComboBox<String> cbSeleccionarTipo;
	private Fachada fac;

	private Vector<TipoMaterial> tipos;
	private JComboBox<String> comboUnidadMedida;
	private Vector<Material> mate;
	private JLabel lblUnidadDeMedida;

	private JTextField textField;
	private JFormattedTextField stockMinimo, fmUtilmoPre, frPrecioact;
	private JButton cancelButton;
	private JFormattedTextField stockActual;
	private NumberFormat flotante;
	private JButton button;

	public MaterialAlta() throws ParseException {

		setTitle("Alta Materiales");

		getContentPane().setLayout(null);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(22, 274, 712, 35);
		getContentPane().add(buttonPane);
		fac = new Fachada();
		formateo();
		buttonPane.setLayout(null);
		setBounds(100, 100, 730, 367);
		button = new JButton("Guardar");
		button.setIcon(new ImageIcon(
				"C:\\Users\\martucho\\Desktop\\sincronizado\\WorkPP3\\ProyectoGuimax\\imagenes\\comprobado.png"));
		button.setSelectedIcon(new ImageIcon(
				"C:\\Users\\martucho\\Desktop\\sincronizado\\WorkPP3\\ProyectoGuimax\\imagenes\\comprobado.png"));
		button.setBounds(435, 5, 125, 25);
		button.setHorizontalTextPosition(SwingConstants.LEFT);
		buttonPane.add(button);
		cancelButton = new JButton("Cerrar");
		cancelButton.setBounds(587, 5, 98, 25);
		cancelButton.setHorizontalAlignment(SwingConstants.LEADING);
		cancelButton.setHorizontalTextPosition(SwingConstants.LEFT);
		cancelButton.setIcon(new ImageIcon(
				"C:\\Users\\martucho\\Desktop\\sincronizado\\WorkPP3\\ProyectoGuimax\\imagenes\\cancelar.png"));
		cancelButton.setActionCommand("Cancelar");
		buttonPane.add(cancelButton);

		panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Rellene todos los campos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 712, 248);
		getContentPane().add(panel);
		panel.setLayout(null);

		cbSeleccionarTipo = new JComboBox();
		cbSeleccionarTipo.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		cbSeleccionarTipo.setBounds(525, 46, 121, 24);
		panel.add(cbSeleccionarTipo);

		JLabel lblSeleccionTipo = new JLabel("Seleccion tipo");
		lblSeleccionTipo.setBounds(370, 50, 121, 16);
		panel.add(lblSeleccionTipo);

		lblUnidadDeMedida = new JLabel("Unidad de medida");
		lblUnidadDeMedida.setBounds(370, 106, 121, 16);
		panel.add(lblUnidadDeMedida);

		comboUnidadMedida = new JComboBox();
		comboUnidadMedida.setEnabled(false);
		comboUnidadMedida.setBounds(523, 102, 123, 24);
		panel.add(comboUnidadMedida);

		textField = new JTextField();
		textField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textField.setColumns(10);
		textField.setBounds(198, 46, 116, 22);
		panel.add(textField);

		JLabel label = new JLabel("Descripcion");
		label.setBounds(43, 50, 93, 16);
		panel.add(label);

		JLabel label_1 = new JLabel("Ultimo precio");
		label_1.setBounds(43, 102, 93, 16);
		panel.add(label_1);

		JLabel label_2 = new JLabel("Stock minimo");
		label_2.setBounds(367, 155, 93, 16);
		panel.add(label_2);

		JLabel label_3 = new JLabel("Stock actual");
		label_3.setBounds(367, 202, 93, 16);
		panel.add(label_3);

		stockMinimo = new JFormattedTextField();
		stockMinimo.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		stockMinimo.setValue(0);
		stockMinimo.setColumns(10);
		stockMinimo.setBounds(530, 152, 116, 22);
		panel.add(stockMinimo);

		stockActual = new JFormattedTextField();
		stockActual.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		stockActual.setValue(0);
		stockActual.setColumns(5);
		stockActual.setBounds(530, 199, 116, 22);
		panel.add(stockActual);

		JLabel label_4 = new JLabel("Precio actual");
		label_4.setBounds(43, 155, 93, 16);
		panel.add(label_4);

		fmUtilmoPre = new JFormattedTextField(flotante);
		fmUtilmoPre.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		fmUtilmoPre.setValue(0);
		fmUtilmoPre.setColumns(5);
		fmUtilmoPre.setBounds(198, 99, 116, 22);
		panel.add(fmUtilmoPre);

		frPrecioact = new JFormattedTextField(flotante);
		frPrecioact.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frPrecioact.setValue(0);
		frPrecioact.setColumns(5);
		frPrecioact.setBounds(198, 152, 116, 22);

		panel.add(frPrecioact);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { textField, stockMinimo, stockActual, fmUtilmoPre, frPrecioact }));
		comboUnidadMedida.setVisible(true);
		cbSeleccionarTipo.addActionListener(this);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { textField, fmUtilmoPre, frPrecioact,
				cbSeleccionarTipo, comboUnidadMedida, stockMinimo, stockActual }));
		lblUnidadDeMedida.setVisible(false);
		activaEscuchadores();
		cargacombo();
		mate = (Vector<Material>) fac.selectMaterial();
		paint(getGraphics());
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private boolean compruebaNumero() {
		return false;
	}

	private void formateo() {

		flotante = NumberFormat.getInstance(Locale.ENGLISH);
		flotante.setMaximumFractionDigits(2);
		flotante.setMaximumFractionDigits(2);
		flotante.setParseIntegerOnly(false);
		flotante.setMinimumFractionDigits(2);

	}

	private void activaEscuchadores() {
		cancelButton.addActionListener(this);
		frPrecioact.addActionListener(this);
		fmUtilmoPre.addActionListener(this);
		frPrecioact.addKeyListener(this);
		fmUtilmoPre.addKeyListener(this);
		stockActual.addKeyListener(this);
		stockMinimo.addKeyListener(this);
		cbSeleccionarTipo.addActionListener(this);
		button.addActionListener(this);
		stockMinimo.addFocusListener(this);
		stockActual.addFocusListener(this);
		stockActual.addFocusListener(this);
		fmUtilmoPre.addFocusListener(this);
		frPrecioact.addFocusListener(this);
		textField.addFocusListener(this);
		cbSeleccionarTipo.addFocusListener(this);

	}

	private void guardar() {
		try {
			if (!textField.getText().equals("")) {
				Material material = new Material();
				material.setDescripcion(textField.getText());
				for (TipoMaterial tipoMaterial : tipos) {
					if (cbSeleccionarTipo.getSelectedIndex() == tipoMaterial.getIdTipo() - 1) {
						material.setTipoMaterial(tipoMaterial);
					}
				}
				try {
					float numero = Float.parseFloat(fmUtilmoPre.getText());

				} catch (InputMismatchException e) {
					System.out.println("Error numero ");
				}
				material.setUnidadDeMedida(comboUnidadMedida.getSelectedItem().toString());
				material.setUltimoPrecio(Manejador.formatoMonedaDeValores(fmUtilmoPre.getValue()));
				material.setPrecioActual(Manejador.formatoMonedaDeValores(frPrecioact.getValue()));

				material.setPuntoPedido(Integer.parseInt(stockMinimo.getText()));
				material.setStockActual(Integer.parseInt(stockActual.getText()));
				material.setFlagBaja(1);
				if (fac.insertMaterial(material)) {

					JOptionPane.showMessageDialog(null, "Se cargó un nuevo material	", "Operación satisfactoria", 1);
				} else {
					JOptionPane.showMessageDialog(null, "El material ya existe", "Operación no satisfactoria", 0);
				}
				textField.setText("");
				textField.requestFocus();
				stockMinimo.setValue(0);
				stockActual.setValue(0);
				fmUtilmoPre.setValue(0);
				frPrecioact.setValue(0);

			} else {
				System.out.println(frPrecioact.getValue().toString());
				System.out.println(frPrecioact.getText());

				System.out.println(" Y ahora que    " + Manejador.formatoMonedaDeValores(frPrecioact.getValue()));
				JOptionPane.showMessageDialog(null, "La descripcion no puede estar vacia", "Operación no realizada", 0);
				textField.requestFocus();
			}
		} catch (NumberFormatException e) {
			System.out.println("entra errrrrrrrrrrrrrrrrrrrrrrrrrrrr");
		}

	}

	private void validaEntero(int num) {

	}

	private boolean compruebaPrecioAct() {
		try {
			float numero = Float.parseFloat(frPrecioact.getText());
			if (Float.isInfinite(numero) || numero < 0) {
				JOptionPane.showMessageDialog(null, "Ingrese un numero menor a 99999 y mayor a 0",
						"El numero es  demaciado grande", 0);
				frPrecioact.requestFocus();
				frPrecioact.setText("");
				return false;
			}
		} catch (NumberFormatException e2) {
			frPrecioact.requestFocus();
			frPrecioact.setText("");
			JOptionPane.showMessageDialog(null, "Ingrese un numero menor a 99999", "El numero es  demaciado grande", 0);

			return false;
		}
		return true;
		// TODO Auto-generated method stub

	}

	private boolean compruevaPrecioAnt() {
		try {
			float numero = Float.parseFloat(fmUtilmoPre.getText());
			if (Float.isInfinite(numero) || numero < 0) {
				fmUtilmoPre.requestFocus();
				fmUtilmoPre.setText("");
				JOptionPane.showMessageDialog(null, "Ingrese un numero menor a 99999 y mayor a 0",
						"El numero es  demaciado grande", 0);

				return false;
			}
		} catch (NumberFormatException e2) {
			fmUtilmoPre.requestFocus();
			fmUtilmoPre.setText("");
			JOptionPane.showMessageDialog(null, "Ingrese un numero menor a 99999", "El numero es  demaciado grande", 0);

			return false;
		}

		return true;

	}

	private boolean compruebaPuntoPed() {
		try {
			int numero = Integer.parseInt(stockMinimo.getText());
			if (Float.isInfinite(numero) || numero < 0) {
				stockMinimo.requestFocus();
				stockMinimo.setText("");
				JOptionPane.showMessageDialog(null, "Ingrese un numero menor a 99999 y mayor a 0",
						"El numero es  demaciado grande", 0);

				return false;

			}
		} catch (NumberFormatException e2) {
			stockMinimo.requestFocus();
			stockMinimo.setText("");
			JOptionPane.showMessageDialog(null, "Ingrese un numero menor a 99999", "El numero es  demaciado grande", 0);

			return false;
		}

		return rootPaneCheckingEnabled;

	}

	private boolean compruebaStockActual() {

		try {
			int numero = Integer.parseInt(stockActual.getText());
			if (Float.isInfinite(numero) || numero < 0) {
				stockActual.requestFocus();
				stockActual.setText("");
				JOptionPane.showMessageDialog(null, "Ingrese un numero menor a 99999 y mayor a 0",
						"El numero es  demaciado grande", 0);

				return false;
			}
		} catch (NumberFormatException e2) {
			stockActual.requestFocus();
			stockActual.setText("");
			JOptionPane.showMessageDialog(null, "Ingrese un numero menor a 99999", "El numero es  demaciado grande", 0);

			return false;
		}

		return true;

	}

	public void cargacombo() {
//		String[] items = { "one", "two", "three" };
//		SortedComboBoxModel<String> model = new SortedComboBoxModel<String>(items);
//		JComboBox<String> comboBox = new JComboBox<String>(model);
//		comboBox.addItem("four");
//		comboBox.addItem("five");
//		comboBox.setSelectedItem("one");
		tipos = fac.selectTiposMateriales();
		if (tipos == null) {

			JOptionPane.showMessageDialog(null, "Debe cargar los tipos", "Faltan cargar tipos",
					JOptionPane.QUESTION_MESSAGE);
		}

		cbSeleccionarTipo.setEnabled(true);
		if (cbSeleccionarTipo.getItemCount() != 0) {
			cbSeleccionarTipo.removeAllItems();
		}

		for (TipoMaterial tipoMaterial : tipos) {
			boolean existe = true;
			for (int i = 0; i < cbSeleccionarTipo.getItemCount(); i++) {
				if (tipoMaterial.getDescripcion().equals(String.valueOf(cbSeleccionarTipo.getItemAt(i)))) {
					existe = false;
				}
			}
			if (existe) {
				cbSeleccionarTipo.addItem(tipoMaterial.getDescripcion());
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == cancelButton) {
			int i = JOptionPane.showConfirmDialog(null, "Desea continuar", "Los cambios no seran guardados",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (i == 0) {

				dispose();
			} else {
				compruevaPrecioAnt();
				compruebaStockActual();
				compruebaPrecioAct();
				compruebaPuntoPed();
			}

		}
		if (arg0.getSource() == cbSeleccionarTipo) {
			if (tipos != null) {
				lblUnidadDeMedida.setVisible(true);
				comboUnidadMedida.setVisible(true);
				comboUnidadMedida.removeAllItems();
				for (TipoMaterial tipoMaterial : tipos) {
					if (tipoMaterial.getDescripcion().equals(String.valueOf(cbSeleccionarTipo.getSelectedItem()))) {
						comboUnidadMedida.addItem(tipoMaterial.getCaracteristica().getUnidadMedida());
					}

				}
			}

		}
		if (compruebaPrecioAct() && compruebaPuntoPed() && compruebaStockActual() && compruevaPrecioAnt()) {
			if (arg0.getSource() == button) {
				if (stockMinimo.getText().equals("0") || stockActual.getText().equals("0")
						|| fmUtilmoPre.getText().equals("0") || frPrecioact.getText().equals("0")) {
					int u = JOptionPane.showConfirmDialog(null, "Desea continuar", "Algunos campos se encuetran en 0",
							JOptionPane.YES_NO_OPTION, 1);
					if (u == 0) {
						guardar();
					}
				} else {
					guardar();
				}
			}
		}
	
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		int limite = 10;
		if (e.getSource() == frPrecioact) {
			System.out.println("Entraaaa");
			if (limite >= frPrecioact.getText().length()) {
				System.out.println(frPrecioact.getValue());

				FormatedGenerico.filtroFloat(e);
			}

		}
		if (e.getSource() == stockMinimo)
			FormatedGenerico.formatoInteger(e);
		if (e.getSource() == stockActual)
			FormatedGenerico.formatoInteger(e);
		if (e.getSource() == fmUtilmoPre)
//			filtroFloat(e);
			FormatedGenerico.filtroFloat(e);

	}

	@Override
	public void focusGained(FocusEvent e) {
		if (stockActual == e.getSource()) {
			compruebaPrecioAct();
			compruebaPuntoPed();
			compruevaPrecioAnt();
		}

		if (fmUtilmoPre == e.getSource()) {
			compruebaPrecioAct();
			compruebaStockActual();
			compruebaPuntoPed();
		}

		if (frPrecioact == e.getSource()) {
			compruevaPrecioAnt();
			compruebaStockActual();
			compruebaPuntoPed();
		}
		if (stockMinimo == e.getSource()) {
			compruevaPrecioAnt();
			compruebaStockActual();
			compruebaPrecioAct();
		}
		if (textField == e.getSource()) {
			compruevaPrecioAnt();
			compruebaStockActual();
			compruebaPrecioAct();
			compruebaPuntoPed();
		}
		if (cbSeleccionarTipo == e.getSource()) {
			compruevaPrecioAnt();
			compruebaStockActual();
			compruebaPrecioAct();
			compruebaPuntoPed();
		}

	}

	@Override
	public void focusLost(FocusEvent e) {

	}

}
