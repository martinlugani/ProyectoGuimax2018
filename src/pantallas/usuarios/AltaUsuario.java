package pantallas.usuarios;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import clases.abstractas.Persona;
import clases.coneccion.Fachada;
import clases.principales.seguridad.Rol;
import clases.principales.seguridad.Usuario;
import pantallas.componenteGenerico.FormatedGenerico;

import javax.swing.JComboBox;

@SuppressWarnings("unused")
public class AltaUsuario extends JDialog implements ActionListener, KeyListener {
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JTextField txtContrasenia;
	private JTextField txtNombreusuario;
	private JComboBox comboBox;
	private JTextField txtTelefono;
	private JTextField txtApellido;
	private JTextField txtNombre;
	private JTextField txtId;
	private Persona personas;
	private JTextField txtDni;
	private int idUsu;
	private Fachada fac;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaUsuario dialog = new AltaUsuario();
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
	public AltaUsuario() {
		// setVisible(true);
		setTitle("Alta Usuario");
		setBounds(100, 100, 617, 510);
		fac = new Fachada();
		getContentPane().setLayout(null);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(358, 439, 99, 25);
		getContentPane().add(btnGuardar);

		btnCancelar = new JButton("Atras");
		btnCancelar.setBounds(501, 439, 99, 25);
		getContentPane().add(btnCancelar);

		JLabel lblId = new JLabel("Id");
		lblId.setBounds(28, 43, 56, 16);
		getContentPane().add(lblId);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(28, 84, 56, 16);
		getContentPane().add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(28, 131, 56, 16);
		getContentPane().add(lblApellido);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(28, 225, 56, 16);
		getContentPane().add(lblTelefono);

		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(28, 276, 56, 16);
		getContentPane().add(lblRol);

		JLabel lblNombreUsuario = new JLabel("Nombre usuario");
		lblNombreUsuario.setBounds(28, 327, 141, 16);
		getContentPane().add(lblNombreUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(28, 380, 141, 16);
		getContentPane().add(lblContrasea);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setText("");
		txtId.setBounds(287, 40, 116, 22);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setText("");
		txtNombre.setBounds(287, 84, 116, 22);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setText("");
		txtApellido.setBounds(287, 128, 116, 22);
		getContentPane().add(txtApellido);
		txtApellido.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setText("");
		txtTelefono.setBounds(287, 222, 116, 22);
		getContentPane().add(txtTelefono);
		txtTelefono.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setBounds(287, 272, 116, 24);
		getContentPane().add(comboBox);

		txtNombreusuario = new JTextField();
		txtNombreusuario.setText("");
		txtNombreusuario.setBounds(287, 324, 116, 22);
		getContentPane().add(txtNombreusuario);
		txtNombreusuario.setColumns(10);

		txtContrasenia = new JTextField();
		txtContrasenia.setText("");
		txtContrasenia.setBounds(287, 377, 116, 22);
		getContentPane().add(txtContrasenia);
		txtContrasenia.setColumns(10);
		comboBox.addItem("Administrador");
		comboBox.addItem("Empleado");

		txtDni = new JTextField();
		txtDni.setBounds(287, 175, 116, 22);
		getContentPane().add(txtDni);
		txtDni.setColumns(10);

		JLabel lblDni = new JLabel("D.N.I.");
		lblDni.setBounds(28, 178, 56, 16);
		getContentPane().add(lblDni);
		cargaListeners();
		cargaId();

		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void cargaId() {

		boolean fin = true;
		idUsu = fac.cuentaIdPersona();
		if (fac.selectPersonaId(idUsu)) {
			while (fin) {

				if (fac.selectPersonaId(idUsu)) {
					idUsu++;
				} else {
					fin = false;

				}

			}
		}
		txtId.setText(String.valueOf(idUsu));
	}

	private void cargaListeners() {
		btnCancelar.addActionListener(this);
		btnGuardar.addActionListener(this);
		txtTelefono.addKeyListener(this);
		txtDni.addKeyListener(this);
	}

	private void guardar() {
		try {
		if (comprobarVacios()) {
			System.out.println("pasaa");
//			JOptionPane.showMessageDialog(null, "El usuario se ha ingresado correctamente", "Alta satisfactoria",
//					JOptionPane.ERROR_MESSAGE);
			Usuario usu = new Usuario();
			usu.setApellido(txtApellido.getText());
			usu.setNombre(txtNombre.getText());
			usu.setContrasenia(txtContrasenia.getText());
			usu.setTelefono(Integer.valueOf(txtTelefono.getText()));
			usu.setDni(Integer.parseInt(txtDni.getText()));
			usu.setFlagBaja(1);
			usu.setFechaAlta(Date.valueOf(LocalDate.now()));
			usu.setFechaUltimoLogin(Date.valueOf(LocalDate.now()));
			usu.setIntentos(3);
			usu.setNombreUsuario(txtNombreusuario.getText());
			usu.setCargo(String.valueOf(comboBox.getSelectedItem().toString()));
			usu.setRol(new Rol());
			usu.getRol().setRolPermisoTodoUno(comboBox.getSelectedIndex() + 1);
			usu.setIdpersona(idUsu);
			Fachada fac = new Fachada();
			switch (fac.insertUsuario(usu)) {
			case -2:
				JOptionPane.showMessageDialog(null, "El DNI ya existe", "Operación no realizada",
						JOptionPane.ERROR_MESSAGE);
				txtDni.requestFocus();
				txtDni.selectAll();
				break;
			case -1:
				JOptionPane.showMessageDialog(null, "Ya hay un usuario con el mismo nombre y apellido",
						"Operación no realizada", JOptionPane.ERROR_MESSAGE);
				txtNombre.requestFocus();
				txtNombre.selectAll();
				break;
			case 0:
				JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe", "Operación no realizada",
						JOptionPane.ERROR_MESSAGE);
				txtNombreusuario.requestFocus();
				txtNombreusuario.selectAll();
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Se guardó un nuevo usuario", "Operación satisfactoria",
						JOptionPane.INFORMATION_MESSAGE);
				limpiarCampos();
				txtNombre.requestFocus();
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Fallo en la ejecucion", "Operación no satisfactoria", 0);
				break;
			default:

				break;
			}

		} else {
			JOptionPane.showMessageDialog(null, "Todos los campos deben contener datos", "Alerta", 2);
		}
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Formato numerico invalido","Fallo en la ejecucion" , 0);
		}
	}

	private void limpiarCampos() {
		txtContrasenia.setText("");
		txtNombreusuario.setText("");
		txtTelefono.setText("");
		txtApellido.setText("");
		txtNombre.setText("");
		txtDni.setText("");
		cargaId();
	}

	private boolean comprobarVacios() {
		if (txtApellido.getText().equals("") || txtContrasenia.getText().equals("") || txtNombre.getText().equals("")
				|| txtTelefono.getText().equals("") || txtNombreusuario.getText().equals("")
				|| txtDni.getText().equals("")) {
			return false;
		}
		return true;
	}

	private void cancelar() {
		this.dispose();
	}
	private int numeroEntero(int i) {
		try {
			return i;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Formato numerico invalido","Fallo en la ejecucion" , 0);
		}
		return 0;
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
		// TODO Auto-generated method stub
		if (e.getSource() == txtTelefono) {
			FormatedGenerico.formatoInteger(e);
		}
		if (e.getSource() == txtDni) {
			FormatedGenerico.formatoInteger(e);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			cancelar();
		}
		if (e.getSource() == btnGuardar) {
			guardar();
		}
	}
}
