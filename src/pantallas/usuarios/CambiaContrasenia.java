package pantallas.usuarios;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import clases.coneccion.Fachada;
import clases.principales.seguridad.Usuario;

public class CambiaContrasenia extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField pwdAntigua;
	private JPasswordField pwdNueva;
	private JPasswordField pwdConfirmacion;
	private JButton btnVer;
	private JButton btnGuardar;
	private JButton btnSalir;
	private boolean visible = false;
	private Usuario usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CambiaContrasenia dialog = new CambiaContrasenia(new Usuario());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CambiaContrasenia(Usuario usuario) {
		this.usuario = usuario;
		setTitle("Cambiar contrase\u00F1a  " + usuario.getNombreUsuario());
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		pwdAntigua = new JPasswordField();
		pwdAntigua.setBounds(259, 61, 96, 22);
		contentPanel.add(pwdAntigua);

		pwdNueva = new JPasswordField();
		pwdNueva.setBounds(259, 106, 96, 22);
		contentPanel.add(pwdNueva);

		pwdConfirmacion = new JPasswordField();
		pwdConfirmacion.setBounds(259, 156, 96, 22);
		contentPanel.add(pwdConfirmacion);

		JLabel lblIngreseAntiguaContrasea = new JLabel("Ingrese antigua contrase\u00F1a");
		lblIngreseAntiguaContrasea.setBounds(12, 64, 188, 16);
		contentPanel.add(lblIngreseAntiguaContrasea);

		JLabel lblNuevaContrasea = new JLabel("Nueva contrase\u00F1a");
		lblNuevaContrasea.setBounds(12, 109, 188, 16);
		contentPanel.add(lblNuevaContrasea);

		JLabel lblConfirmarContrasea = new JLabel("Confirmar contrase\u00F1a");
		lblConfirmarContrasea.setBounds(12, 159, 188, 16);
		contentPanel.add(lblConfirmarContrasea);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(148, 231, 99, 25);
		contentPanel.add(btnGuardar);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(308, 231, 99, 25);
		contentPanel.add(btnSalir);

		btnVer = new JButton("");
		btnVer.setBackground(UIManager.getColor("Button.background"));
		ImageIcon ojo = new ImageIcon("imagenes/redEge.png");
		btnVer.setIcon(ojo);
		btnVer.setIconTextGap(1);
		btnVer.setHorizontalTextPosition(SwingConstants.LEFT);
		btnVer.setBorderPainted(false);
		btnVer.setRolloverEnabled(true);
		btnVer.setToolTipText("Ver contrase\u00F1a");
		btnVer.setBounds(367, 100, 35, 30);
		contentPanel.add(btnVer);

		cargalisteners();
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void cargalisteners() {
		btnGuardar.addActionListener(this);
		btnSalir.addActionListener(this);
		btnVer.addActionListener(this);

	}

	private boolean guardar() {
		// TODO Auto-generated method stub
		if ((pwdNueva.getPassword().length > 0) && (pwdConfirmacion.getPassword().length > 0)) {
			System.out.println(
					String.valueOf(pwdConfirmacion.getPassword()) + "   " + String.valueOf(pwdNueva.getPassword()));
			if (String.valueOf(pwdNueva.getPassword()).equals(String.valueOf(pwdConfirmacion.getPassword()))) {
				if (compruevaContraseña()) {
					tomaDatos();
					return true;
				}

			} else {
				JOptionPane.showMessageDialog(null, "Ingrese dos contraseñas iguales", "Contraseñas diferentes", 2);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Debe escribir una contraseña ", "Error al ingresar la contraseña", 0);
			pwdNueva.requestFocus();
			pwdNueva.setText("");
		}
		return false;
	}

	private boolean compruevaContraseña() {
		Fachada fac = new Fachada();
		Vector<Usuario> usuarios = fac.selectUsuario();
		for (Usuario usuario : usuarios) {
			if (this.usuario.getNombreUsuario().equals(usuario.getNombreUsuario())
					&& usuario.getContrasenia().equals(String.valueOf(pwdAntigua.getPassword()))) {
				usuario.setContrasenia(String.valueOf(pwdConfirmacion.getPassword()));
				fac.updateUsuario(usuario);
				return true;
			}
		}
		return false;
	}

	private void tomaDatos() {
		// TODO Auto-generated method stub
		usuario.setContrasenia(String.valueOf(pwdNueva.getPassword()));
		pwdAntigua.setText("");
		pwdNueva.setText("");
		pwdConfirmacion.setText("");
	}

	private void cambiaEstadotext() {
		// TODO Auto-generated method stub
		if (visible) {
			pwdConfirmacion.setEchoChar('•');
			pwdNueva.setEchoChar('•');
			visible = false;
		} else {
			pwdConfirmacion.setEchoChar((char) 0);
			pwdNueva.setEchoChar((char) 0);
			visible = true;
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (btnGuardar == arg0.getSource()) {
			if (usuario.getContrasenia().equals(String.valueOf(pwdAntigua.getPassword()))&& pwdAntigua.getPassword().length>0) {

				if (guardar()) {
					JOptionPane.showMessageDialog(null, "Se ha guardado correctamente ", "Guarda contraseña", 1);

				}
			}else {
				JOptionPane.showMessageDialog(null, "Debe ingresar su contraseña correctamente", "Cambio decontraseña", 1);
				pwdAntigua.requestFocus();
			}
		}
		if (btnSalir == arg0.getSource()) {
			System.out.println("pasdosdsa");
			int i = JOptionPane.showConfirmDialog(null, "Desea continuar", "No se guardaran los cambios",
					JOptionPane.YES_NO_OPTION, 2);
			if (i != 1) {
				dispose();
			}
		}
		if (btnVer == arg0.getSource()) {
			if (visible) {
				ImageIcon ojo = new ImageIcon("imagenes/redEge.png");
				btnVer.setIcon(ojo);
			}else {
				ImageIcon ojo = new ImageIcon("imagenes/ojoTachado.png");
				btnVer.setIcon(ojo);
			}
			cambiaEstadotext();
		}

	}
}
