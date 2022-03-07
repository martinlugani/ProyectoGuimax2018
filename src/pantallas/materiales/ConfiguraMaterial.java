package pantallas.materiales;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.coneccion.Fachada;
import clases.principales.productos.Caracteristica;
import clases.principales.productos.TipoMaterial;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;

public class ConfiguraMaterial extends JDialog implements ActionListener {
	private JPanel buttonPane;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private ButtonGroup radio;
	private Fachada fac;
	private JPanel panel;
	private JTextField textField;
	private JComboBox<String> cbCaracteristica;
	private JComboBox<String> cbUnidadMedida;
	private JLabel lblDeseaIngresarUn;
	private ButtonGroup radiomenu;
	private Vector<Caracteristica> caract;
	private JButton btnSi;
	private  MaterialAlta anterior;
	

	public MaterialAlta getAnterior() {
		return anterior;
	}

	public void setAnterior(MaterialAlta anterior) {
		this.anterior = anterior;
	}

	public ConfiguraMaterial() {
		setTitle("Tipo Material");
		setBounds(100, 100, 750, 302);
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		cargarElementos();

	}

	protected boolean cargarCombobox() {
		if (cbCaracteristica.getComponentCount() != 0 || cbUnidadMedida.getComponentCount() != 0) {
			cbCaracteristica.removeAllItems();
			cbUnidadMedida.removeAllItems();
		caract=null;
		}
		caract = fac.selectCaracteristica();
		if (caract != null) {
			for (Caracteristica caracteristica : caract) {
				boolean paso = true;
				for (int i = 0; i < cbCaracteristica.getItemCount(); i++) {
					if (caracteristica.getDescripcion().equals(String.valueOf(cbCaracteristica.getItemAt(i)))) {
						paso = false;
					}
				}
				if (paso) {
					cbCaracteristica.addItem(caracteristica.getDescripcion());
				}

			}
			return true;
		} else {
			

		}

		return false;

	}

	private void cierraPanttalla() {
		// TODO Auto-generated method stub
		this.dispose();
	}

	private void cargarElementos() {
		radiomenu = new ButtonGroup();
		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		radio = new ButtonGroup();
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setActionCommand("Aceptar");
		buttonPane.add(btnAceptar);
		getRootPane().setDefaultButton(btnAceptar);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setActionCommand("Cancelar");
		buttonPane.add(btnCancelar);

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		cbCaracteristica = new JComboBox();
		cbCaracteristica.setBounds(484, 137, 162, 24);
		panel.add(cbCaracteristica);

		JLabel label = new JLabel("Unidad de medida");
		label.setBounds(124, 195, 103, 16);
		panel.add(label);

		JLabel label_1 = new JLabel("Caracteristica");
		label_1.setBounds(126, 141, 132, 16);
		panel.add(label_1);

		cbUnidadMedida = new JComboBox();
		cbUnidadMedida.setBounds(484, 191, 162, 24);
		panel.add(cbUnidadMedida);

		JLabel label_2 = new JLabel("Descripcion del tipo");
		label_2.setBounds(124, 24, 176, 16);
		panel.add(label_2);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(484, 21, 162, 22);
		panel.add(textField);

		lblDeseaIngresarUn = new JLabel("Desea Ingresar un nuevo tipo?");
		lblDeseaIngresarUn.setBounds(124, 61, 269, 16);
		panel.add(lblDeseaIngresarUn);
		fac = new Fachada();
		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);

		cbUnidadMedida.setEnabled(false);

		btnSi = new JButton("Si");
		btnSi.setBounds(329, 90, 99, 25);
		panel.add(btnSi);
		btnSi.addActionListener(this);
		cbCaracteristica.addActionListener(this);
		if(!cargarCombobox()) {
			JOptionPane.showMessageDialog(null, "Debe cargar unidades de medida", "Lista de medidas vacia",JOptionPane.QUESTION_MESSAGE);
			cbCaracteristica.setEnabled(false);
			textField.setEditable(false);
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAceptar) {
			if (cbCaracteristica.getSelectedItem() == null || cbUnidadMedida.getSelectedItem() == null
					|| textField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Complete todos los campos", "Error en carga",
						JOptionPane.ERROR_MESSAGE);
			} else {
				TipoMaterial mat = new TipoMaterial();
				mat.setDescripcion(textField.getText());
				Caracteristica nueva = new Caracteristica();

				nueva.setDescripcion(String.valueOf(cbCaracteristica.getSelectedItem()));
				nueva.setUnidadMedida(String.valueOf(cbUnidadMedida.getSelectedItem()));
				mat.setCaracteristica(nueva);
				if (fac.insertTipoMaterial(mat)) {
					int opcion = JOptionPane.showConfirmDialog(null, "Desea cargar otro", "Carga correcta",
							JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					textField.setText("");
					textField.requestFocus();
					if (opcion != 0)
						this.getAnterior().cargacombo();
						this.dispose();
						
				} else {
					JOptionPane.showMessageDialog(null, "La configuracion ya existe", "Error de carga",
							JOptionPane.ERROR_MESSAGE);
					textField.setText("");
					textField.requestFocus();
				}

			}

		}
		if (e.getSource() == btnCancelar) {
			int opcion = JOptionPane.showConfirmDialog(null, "Desea finalizar la carga", "Cancelar",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (opcion == 0) {
				cierraPanttalla();

			}
		}
		if (e.getSource() == cbCaracteristica) {
			cbUnidadMedida.setEnabled(true);
			cbCaracteristica.setEnabled(true);
			textField.setEnabled(true);
			cbUnidadMedida.removeAllItems();
			for (Caracteristica caracteristica : caract) {
				if (String.valueOf(cbCaracteristica.getSelectedItem()).equals(caracteristica.getDescripcion())) {
					cbUnidadMedida.addItem(caracteristica.getUnidadMedida());
				}
			}
		}
		if (e.getSource() == btnSi) {
			cargaPantalla();
		}

	}

	private void cargaPantalla() {
		TipoMat tipo = new TipoMat();
		tipo.setConfi(this);
		tipo.setModal(isDisplayable());
		tipo.setLocationRelativeTo(null);
		tipo.setVisible(true);

	}
}
