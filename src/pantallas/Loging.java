package pantallas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clases.coneccion.Fachada;
import clases.principales.seguridad.Usuario;

import javax.swing.JButton;

public class Loging extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPasswordField txPassword;
	private JTextField txusuario;
	private Fachada fac;
	private JButton btnAceptar;
	private Usuario u;

	private void inicio() {
		if (fac == null) {
	
			fac = new Fachada();
		}
		Usuario u = new Usuario();
		u.setContrasenia(String.valueOf(txPassword.getPassword()));
		u.setNombreUsuario(txusuario.getText());
		switch (fac.inicioSecion(u)) {
		case 1:
			dispose();
			this.u=u;
			Principal pant = new Principal(this.u);
			
			pant.setVisible(true);
			pant.setLocationRelativeTo(null);
			break;
		case 0:
			JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error loging", JOptionPane.ERROR_MESSAGE);
	
			txPassword.requestFocus();
			break;
		case -1:
			JOptionPane.showMessageDialog(null, "Nombre de usuario no valido ", "Error loging",
					JOptionPane.ERROR_MESSAGE);
			txusuario.requestFocus();
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "Contacte a su administrador", "Se terminaron los intentos",
					JOptionPane.ERROR_MESSAGE);
			break;
		}
	
	}

	public Loging() {
		setTitle("Inicio de secion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txPassword = new JPasswordField();
		txPassword.setBounds(190, 128, 135, 20);
		contentPane.add(txPassword);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(66, 131, 78, 14);
		contentPane.add(lblPassword);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(66, 48, 46, 14);
		contentPane.add(lblUsuario);

		txusuario = new JTextField();
		txusuario.setBounds(187, 45, 135, 20);
		contentPane.add(txusuario);
		txusuario.setColumns(10);

		btnAceptar = new JButton("Ingresar");

		btnAceptar.setBounds(236, 204, 89, 23);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAceptar) {
			inicio();
		}

	}

	public Usuario getU() {
		return u;
	}

	public void setU(Usuario u) {
		this.u = u;
	}
}
