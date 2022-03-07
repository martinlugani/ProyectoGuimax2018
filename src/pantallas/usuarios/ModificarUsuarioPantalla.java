package pantallas.usuarios;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.sun.xml.internal.ws.api.client.ServiceInterceptor;

import clases.coneccion.Fachada;
import clases.principales.seguridad.Usuario;
import pantallas.componenteGenerico.FormatedGenerico;

public class ModificarUsuarioPantalla extends JDialog implements ActionListener, FocusListener, KeyListener {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JPanel panel_1;
	private JComboBox<String> comboBox;
	private JTextField textField;
	private JSpinner spinner;
	private JComboBox<String> comboBox_1;
	private JButton btnGuardar;
	private JButton btnSalir;
	private JComboBox<String> comboBoxUsuario;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JLabel lblTelefono;
	private JLabel lblUsuario;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private Vector<Usuario> usuarios;
	private Fachada fac;
	private Usuario usua;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ModificarUsuarioPantalla dialog = new ModificarUsuarioPantalla();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ModificarUsuarioPantalla() {
		setTitle("Modificar Usuario");
		setBounds(100, 100, 616, 471);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		fac = new Fachada();
		panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Seleccione un Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setToolTipText("");
		panel.setBounds(25, 37, 570, 140);
		contentPanel.add(panel);
		panel.setLayout(null);

		comboBoxUsuario = new JComboBox();
		comboBoxUsuario.setBounds(372, 33, 116, 24);
		panel.add(comboBoxUsuario);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setText("Nombre");
		txtNombre.setBounds(372, 70, 116, 22);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setText("Apellido");
		txtApellido.setBounds(372, 105, 116, 22);
		panel.add(txtApellido);
		txtApellido.setColumns(10);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(62, 37, 56, 16);
		panel.add(lblUsuario);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(62, 73, 56, 16);
		panel.add(lblNombre);

		lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(62, 108, 56, 16);
		panel.add(lblApellido);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				"Datos a cambiar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(25, 190, 570, 200);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		textField = new JTextField();
		textField.setText("cargo");
		textField.setColumns(10);
		textField.setBounds(378, 19, 116, 22);
		panel_1.add(textField);

		comboBox = new JComboBox();
		comboBox.setBounds(378, 54, 116, 24);
		panel_1.add(comboBox);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 3, 1));
		spinner.setBounds(378, 91, 116, 20);
		panel_1.add(spinner);

		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(378, 124, 116, 24);
		panel_1.add(comboBox_1);

		JLabel lblNuevoCargo = new JLabel("Nuevo Cargo");
		lblNuevoCargo.setBounds(66, 22, 131, 16);
		panel_1.add(lblNuevoCargo);

		JLabel lblSeleccioneOpcion = new JLabel("Seleccione opcion");
		lblSeleccioneOpcion.setBounds(66, 58, 131, 16);
		panel_1.add(lblSeleccioneOpcion);

		JLabel lblCantidadDeIntentos = new JLabel("Cantidad de intentos");
		lblCantidadDeIntentos.setBounds(66, 93, 131, 16);
		panel_1.add(lblCantidadDeIntentos);

		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(66, 128, 131, 16);
		panel_1.add(lblRol);

		txtTelefono = new JTextField();
		txtTelefono.setText("telefono");
		txtTelefono.setBounds(378, 161, 116, 22);
		panel_1.add(txtTelefono);
		txtTelefono.setColumns(10);

		lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(66, 164, 131, 16);
		panel_1.add(lblTelefono);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(362, 403, 99, 25);
		contentPanel.add(btnGuardar);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(483, 403, 99, 25);
		contentPanel.add(btnSalir);
		usuarios = fac.selectUsuario();

		carga();

		cargaListeners();
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void cargaListeners() {
		// TODO Auto-generated method stub
		comboBoxUsuario.addActionListener(this);
		textField.addFocusListener(this);
		comboBox.addActionListener(this);
		comboBox_1.addActionListener(this);
		txtTelefono.addFocusListener(this);
		txtTelefono.addKeyListener(this);
		btnGuardar.addActionListener(this);
		btnSalir.addActionListener(this);

	}

	private void carga() {
		for (Usuario usuario : usuarios) {
			comboBoxUsuario.addItem(String.valueOf(usuario.getNombreUsuario()));
		}
		comboBox.addItem("Activo");
		comboBox.addItem("Inactivo");
		comboBox_1.addItem("Administrador");
		comboBox_1.addItem("Empleado");
		cargaResto();

	}

	private void cargaResto() {

		for (Usuario usuario : usuarios) {
			if (comboBoxUsuario.getSelectedItem().toString().equals(usuario.getNombreUsuario())) {
				txtNombre.setText(usuario.getNombre());
				txtApellido.setText(usuario.getApellido());
				textField.setText(usuario.getCargo());
				if (usuario.getFlagBaja() != 0) {
					comboBox.setSelectedIndex(0);
				} else {
					comboBox.setSelectedIndex(1);

				}
				System.out.println(comboBox.getSelectedItem().toString());
				spinner.setValue(usuario.getIntentos());
				if (usuario.getRol().getIdRol() == 1) {
					comboBox_1.setSelectedIndex(0);
				} else if (usuario.getRol().getIdRol() == 2) {
					comboBox_1.setSelectedIndex(1);
				}
				txtTelefono.setText(String.valueOf(usuario.getTelefono()));
				usua = usuario;
			}

		}

	}

	private void guardar() {
		try {
			int i =Integer.parseInt(txtTelefono.getText());
			// TODO Auto-generated method stub
			if (txtTelefono.getText().equals("") || textField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "No puede haber campos vacios", "Opeacion no realizada", 2);
				if (txtTelefono.getText().equals("")) {
					txtTelefono.requestFocus();
				}
				if (textField.getText().equals("")) {
					textField.requestFocus();
				}
			} else {
				usua.setIntentos(Integer.valueOf(spinner.getValue().toString()));
				JOptionPane.showMessageDialog(null, "Guardado satisfactorio", "Opeacion realizada", 1);
				fac.updateUsuario(usua);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Formato numerico invalido", "Fallo en la ejecucion", 0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == comboBoxUsuario) {
			usuarios = fac.selectUsuario();
			cargaResto();
		}
		if (e.getSource() == comboBox_1) {
			if (comboBox_1.getSelectedItem().toString().equals("Administrador")) {
				usua.getRol().setIdRol(1);
				usua.getRol().setDescripcion("Administrador");
			} else {
				if (comboBox_1.getSelectedItem().toString().equals("Empleado")) {
					usua.getRol().setIdRol(2);
					usua.getRol().setDescripcion("Empleado");
				}
			}
			System.out.println(usua.getRol().getDescripcion());
		}
		if (e.getSource() == comboBox) {
			if (comboBox.getSelectedItem().toString().equals("Activo")) {
				usua.setFlagBaja(1);
			} else {
				if (comboBox.getSelectedItem().toString().equals("Inactivo")) {
					usua.setFlagBaja(0);
				}
			}
		}
		if (e.getSource() == btnSalir) {
			this.dispose();
		}
		if (e.getSource() == btnGuardar) {
			guardar();
		}
		if (e.getSource() == spinner) {
			usua.setIntentos(Integer.parseInt(spinner.getValue().toString()));
			System.out.println(usua.getIntentos());
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == textField) {
			usua.setCargo(textField.getText());
			System.out.println(usua.getCargo());
		}
		if (e.getSource() == spinner) {
			usua.setIntentos(Integer.parseInt(spinner.getValue().toString()));
		}
		if (e.getSource() == txtTelefono) {
			try {
				if (!txtTelefono.getText().equals(""))
					usua.setTelefono(Integer.parseInt(txtTelefono.getText().toString()));
			} catch (NumberFormatException r) {
				JOptionPane.showMessageDialog(null, "Fallo en la ejecucion", "El numero es demaciado grande", 0);
			}
		}
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
		if (e.getSource() == txtTelefono) {
			FormatedGenerico.formatoInteger(e);
		}
	}

}
