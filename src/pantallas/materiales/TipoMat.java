package pantallas.materiales;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import clases.coneccion.Fachada;
import clases.principales.productos.Caracteristica;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TipoMat extends JDialog implements ActionListener {
	private JTextField txCarac;
	private JTextField txUnidad;
	private JButton cancelar;
	private Fachada fac;
	private JButton aceptar;
	private ConfiguraMaterial confi;
	public TipoMat() {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setTitle("Carga Tipo");
		setBounds(100, 100, 451, 224);
		getContentPane().setLayout(null);
		fac = new Fachada();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(12, 13, 418, 111);
		getContentPane().add(panel);

		JLabel label = new JLabel("Caracteristicas");
		label.setBounds(47, 25, 132, 16);
		panel.add(label);

		JLabel label_1 = new JLabel("Unidad de medida");
		label_1.setBounds(47, 60, 132, 16);
		panel.add(label_1);

		txCarac = new JTextField();
		txCarac.setColumns(10);
		txCarac.setBounds(258, 19, 116, 22);
		panel.add(txCarac);

		txUnidad = new JTextField();
		txUnidad.setColumns(10);
		txUnidad.setBounds(258, 57, 116, 22);
		panel.add(txUnidad);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(12, 137, 420, 39);
		getContentPane().add(panel_1);

		cancelar = new JButton("Cancelar");
		cancelar.setBounds(321, 13, 99, 25);
		panel_1.add(cancelar);

		aceptar = new JButton("Aceptar");
		aceptar.setBounds(178, 13, 99, 25);
		panel_1.add(aceptar);
		aceptar.addActionListener(this);
		cancelar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == aceptar) {
			if (txUnidad.getText().equals("") || txCarac.getText().equals("")) {
//				JOptionPane.showMessageDialog(null, "Desea continuar sin realizar la carga",
//						"Uno de los campos esta vacio", JOptionPane.INFORMATION_MESSAGE);
				int i = JOptionPane.showConfirmDialog(null, "Desea continuar sin realizar la carga",
						"Uno de los campos esta vacio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (i == 0) {
					System.out.println("Acceptar");
					this.dispose();
				}
			} else {

				int i = JOptionPane.showConfirmDialog(null, "Carga tipo", "Desea continuar",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (i == 0) {
					Caracteristica cara = new Caracteristica();
					cara.setDescripcion(txCarac.getText());
					cara.setUnidadMedida(txUnidad.getText());
					fac.cargaCaracteristica(cara);
					this.getConfi().cargarCombobox();
					this.dispose();
					
				}
			}
		}
		if (e.getSource() == cancelar) {
			this.getConfi().cargarCombobox();
			this.dispose();
		}

	}

	public ConfiguraMaterial getConfi() {
		return confi;
	}

	public void setConfi(ConfiguraMaterial confi) {
		this.confi = confi;
	}

}
