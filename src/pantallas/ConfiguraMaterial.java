package pantallas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ConfiguraMaterial extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JButton btnAceptar;
	private JButton btnCancelar;

	public ConfiguraMaterial() {
		setBounds(100, 100, 750, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setActionCommand("Aceptar");
		buttonPane.add(btnAceptar);
		getRootPane().setDefaultButton(btnAceptar);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setActionCommand("Cancelar");
		buttonPane.add(btnCancelar);
	}

}
