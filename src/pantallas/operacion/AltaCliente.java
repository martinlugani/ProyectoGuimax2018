package pantallas.operacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableRowSorter;

import clases.coneccion.Fachada;
import clases.principales.cliente.Cliente;
import clases.principales.cliente.Contacto;
import pantallas.componenteGenerico.FormatedGenerico;
import pantallas.componenteGenerico.MyTableModel;

public class AltaCliente extends JDialog implements ActionListener, MouseListener, KeyListener {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JTextField txtRsocial;
	private JTextField txtCuit;
	private JTextField txtDireccion;
	private JTextField txtTelef;
	private JTextField txtLocalidad;
	private JTextField txtCodpostal;
	private JTextField txtApellido;
	private JTextField txtTelefonocon;
	private JTextField txtCargo;
	private JTextField txtDni;
	private JComboBox<String> cbPermisos;
	private JTextField txtNombre;
	private MyTableModel model;
	private JButton btnAgregar;
	private JButton btnGuardar;
	private JButton btnSalir;
	private Cliente cliente;
	private Vector<Contacto> contactos;
	private JScrollPane scrollPane;
	private JPanel panel_2;
	private JTable table;
	private TableRowSorter<MyTableModel> sorter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaCliente dialog = new AltaCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaCliente() {
	cargaContexto();
	}
	private void cargaContexto() {
		setTitle("Alta Cliente");
		setBounds(100, 100, 725, 647);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		model = new MyTableModel();
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(12, 53, 693, 533);
		contentPanel.add(desktopPane);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null),
				"Ingrese datos de Empresa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 693, 533);
		desktopPane.add(panel);
		panel.setLayout(null);

		JLabel lblRazonSocial = new JLabel("Razon social");
		lblRazonSocial.setBounds(33, 31, 82, 16);
		panel.add(lblRazonSocial);

		JLabel lblCuit = new JLabel("C.U.I.T.");
		lblCuit.setBounds(33, 68, 56, 16);
		panel.add(lblCuit);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setBounds(33, 110, 56, 16);
		panel.add(lblDireccin);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(386, 31, 56, 16);
		panel.add(lblTelefono);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(386, 74, 56, 16);
		panel.add(lblLocalidad);

		JLabel lblCodigoPostal = new JLabel("Codigo postal");
		lblCodigoPostal.setBounds(386, 119, 82, 16);
		panel.add(lblCodigoPostal);

		txtRsocial = new JTextField();
		txtRsocial.setText("");
		txtRsocial.setBounds(155, 28, 116, 22);
		panel.add(txtRsocial);
		txtRsocial.setColumns(10);

		txtCuit = new JTextField();
		txtCuit.setText("");
		txtCuit.setBounds(155, 68, 116, 22);
		panel.add(txtCuit);
		txtCuit.setColumns(10);

		txtDireccion = new JTextField();
		txtDireccion.setText("");
		txtDireccion.setBounds(155, 107, 116, 22);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);

		txtTelef = new JTextField();
		txtTelef.setText("");
		txtTelef.setBounds(508, 28, 116, 22);
		panel.add(txtTelef);
		txtTelef.setColumns(10);

		txtLocalidad = new JTextField();
		txtLocalidad.setText("");
		txtLocalidad.setBounds(508, 71, 116, 22);
		panel.add(txtLocalidad);
		txtLocalidad.setColumns(10);

		txtCodpostal = new JTextField();
		txtCodpostal.setText("");
		txtCodpostal.setBounds(508, 116, 116, 22);
		panel.add(txtCodpostal);
		txtCodpostal.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Ingrese datos de contacto", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(0, 0, 0)));
		panel_1.setBounds(12, 151, 666, 371);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 34, 56, 16);
		panel_1.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(12, 75, 56, 16);
		panel_1.add(lblApellido);

		JLabel lblTelefono_1 = new JLabel("Telefono");
		lblTelefono_1.setBounds(12, 118, 56, 16);
		panel_1.add(lblTelefono_1);

		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(370, 34, 56, 16);
		panel_1.add(lblCargo);

		JLabel lblDni = new JLabel("D.N.I.");
		lblDni.setBounds(370, 74, 56, 16);
		panel_1.add(lblDni);

		JLabel lblPermiso = new JLabel("Permiso");
		lblPermiso.setBounds(370, 114, 56, 16);
		panel_1.add(lblPermiso);

		txtNombre = new JTextField();
		txtNombre.setText("");
		txtNombre.setBounds(145, 31, 116, 22);
		panel_1.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setText("");
		txtApellido.setBounds(145, 72, 116, 22);
		panel_1.add(txtApellido);
		txtApellido.setColumns(10);

		txtTelefonocon = new JTextField();
		txtTelefonocon.setText("");
		txtTelefonocon.setBounds(145, 115, 116, 22);
		panel_1.add(txtTelefonocon);
		txtTelefonocon.setColumns(10);

		txtCargo = new JTextField();
		txtCargo.setText("");
		txtCargo.setBounds(503, 31, 116, 22);
		panel_1.add(txtCargo);
		txtCargo.setColumns(10);

		txtDni = new JTextField();
		txtDni.setText("");
		txtDni.setBounds(503, 71, 116, 22);
		panel_1.add(txtDni);
		txtDni.setColumns(10);

		cbPermisos = new JComboBox();
		cbPermisos.setBounds(503, 110, 116, 24);
		panel_1.add(cbPermisos);

		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Contacto modificar/eliminar", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_2.setBounds(12, 175, 642, 183);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 27, 618, 156);
		panel_2.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(513, 147, 99, 25);
		panel_1.add(btnAgregar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(398, 591, 99, 25);
		contentPanel.add(btnGuardar);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(562, 591, 99, 25);
		contentPanel.add(btnSalir);
		
		cargaTabla();
		cargarListeners();
		setModal(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private void cargarListeners() {
		btnAgregar.addActionListener(this);
		btnGuardar.addActionListener(this);
		btnSalir.addActionListener(this);
		txtDni.addKeyListener(this);
		txtTelefonocon.addKeyListener(this);
		txtTelef.addKeyListener(this);
		txtCodpostal.addKeyListener(this);
		table.addMouseListener(this);
	}

	private void cargaTabla() {
		String[] columnas = { "Nombre", "Apellido", "Telefono", "Cargo", "D.N.I.", "Permiso" };
		model.addColums(columnas);
		model.setCellEditing(false);
		cbPermisos.addItem("Total");
		cbPermisos.addItem("Presupuesto");
		cbPermisos.addItem("Pedido");
		table.setModel(model);
	}

	private void agregarContacto() {
		// TODO Auto-generated method stub
		if (cliente == null) {
			cliente = new Cliente();
		}
		int contador = 0;
		Contacto cont = new Contacto();
		if (!compruebaCampo(txtNombre)) {
			JOptionPane.showMessageDialog(null, "Rellene el campo", "El campo esta vacio", 0);
			txtNombre.requestFocus();
			contador++;
		} else {
			if (!compruebaCampo(txtApellido)) {
				JOptionPane.showMessageDialog(null, "Rellene el campo", "El campo esta vacio", 0);
				txtApellido.requestFocus();
				contador++;
			} else {

				if (!compruebaCampo(txtDni)) {
					JOptionPane.showMessageDialog(null, "Rellene el campo", "El campo esta vacio", 0);
					txtDni.requestFocus();
					contador++;
				} else {
					if (!compruebaCampo(txtTelefonocon)) {
						JOptionPane.showMessageDialog(null, "Rellene el campo", "El campo esta vacio", 0);
						txtTelefonocon.requestFocus();
						contador++;
					} else {

						if (!compruebaCampo(txtCargo)) {
							JOptionPane.showMessageDialog(null, "Rellene el campo", "El campo esta vacio", 0);
							txtCargo.requestFocus();
							contador++;
						}
					}
				}
			}
		}
		if (contador == 0) {
			if (valoresEnterosContacto()) {

				cont.setNombre(txtNombre.getText());
				cont.setApellido(txtApellido.getText());
				try {
					int nu = Integer.valueOf(txtTelefonocon.getText());
					cont.setTelefono(nu);
					nu = Integer.valueOf(txtDni.getText());
					cont.setDni(nu);
				} catch (NumberFormatException e) {
					// TODO: handle exception
				}

				cont.setCargo(txtCargo.getText());
				cont.setPermisoOtorgado(cbPermisos.getSelectedItem().toString());

				if (contactos == null) {
					contactos = new Vector<Contacto>();
				}
				contactos.add(cont);
				cargaTablaCeldas();
			}
		}

	}

	private boolean valoresEnterosContacto() {
		try {
			int n1 = Integer.valueOf(txtTelefonocon.getText());

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ingrese un dato numerico", "Error en el tipo de dato", 0);
			txtTelefonocon.requestFocus();
			txtTelefonocon.setSelectionStart(0);
			txtTelefonocon.setSelectionEnd(txtTelefonocon.getText().length());
			return false;

		}
		try {
			int n1 = Integer.valueOf(txtDni.getText());

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ingrese un dato numerico", "Error en el tipo de dato", 0);
			txtDni.requestFocus();
			txtDni.setSelectionStart(0);
			txtDni.setSelectionEnd(txtDni.getText().length());
			return false;
		}
		return true;
	}

	private boolean valoresEnteros() {
		try {
			int i = Integer.valueOf(txtCuit.getText());
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(null, "Ingrese un numero", "Error en el tipo de dato", 0);
			txtCuit.requestFocus();
			return false;
		}
		try {
			int i = Integer.valueOf(txtTelef.getText());
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(null, "Ingrese un numero", "Error en el tipo de dato", 0);
			txtTelef.requestFocus();
			return false;
		}
		try {
			int i = Integer.valueOf(txtCodpostal.getText());
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(null, "Ingrese un numero", "Error en el tipo de dato", 0);
			txtCodpostal.requestFocus();
			return false;
		}
		return true;
	}

	private void cargaTablaCeldas() {
		if (contactos != null) {
			LinkedList<Object> celdas = new LinkedList<Object>();
			celdas.add(contactos.lastElement().getNombre());
			celdas.add(contactos.lastElement().getApellido());
			celdas.add(contactos.lastElement().getTelefono());
			celdas.add(contactos.lastElement().getCargo());
			celdas.add(contactos.lastElement().getDni());
			celdas.add(contactos.lastElement().getPermisoOtorgado());
			model.addRow(celdas);
			model.setCellEditing(false);

			table.setModel(model);
			table.repaint();
			System.out.println(table.getModel().getRowCount());
			sorter = new TableRowSorter<MyTableModel>(model);
			table.setRowSorter(sorter);
			// table.setSelectionMode(JTable.WHEN_FOCUSED);
			borraCamposContacto();
		}

	}

	private void borraCamposContacto() {
		txtApellido.setText("");
		txtNombre.setText("");
		txtTelefonocon.setText("");
		txtCargo.setText("");
		txtDni.setText("");

	}

	private JTextField seleccionFoco(JTextField txtNombre2) {
		// TODO Auto-generated method stub
		if (txtNombre2.isFocusOwner()) {
			txtNombre2.setSelectionStart(0);
			txtNombre2.setSelectionEnd(txtNombre2.getText().length());
		}
		return txtNombre2;
	}

	private boolean compruebaCampo(JTextField txtNombre2) {
		if (txtNombre2.getText().length() > 0) {
			return true;
		}
		return false;
	}

	private void guardarTodo() {
		boolean guardar= true;
		if (contactos != null && !contactos.isEmpty()) {
			
			int cuentatot = 0;
			int cuetapres = 0;
			int cuentapedido = 0;
			for (Contacto contacto : contactos) {
				if (contacto.getPermisoOtorgado().equals("Total")) {
					cuentatot = 2;
				}
				if (contacto.getPermisoOtorgado().equals("Presupuesto") && cuetapres == 0) {
					cuetapres = 1;
					cuentatot += cuetapres;
				}
				if (contacto.getPermisoOtorgado().equals("Pedido") && cuentapedido == 0) {
					cuentapedido = 1;
					cuentatot += cuentapedido;
				}

			}

			if (!(cuentatot >= 2)) {
				JOptionPane.showMessageDialog(null, "No se guardará si no abarca los permisos ", "Error al guardar", 0);
				guardar= false;
			}
		} else {
			guardar=false;
			JOptionPane.showMessageDialog(null, "Debe haber por lo menos un contacto	", "Error al guardar", 0);

		}
		
		if (guardar) {
			Fachada fac = new Fachada();
		//	fac.insertCliente(cliente)
			cargaDatosCliente();
//			if(fac.insertCliente(cliente)) {
			JOptionPane.showMessageDialog(null, "El cliente se ha registrado con exito", "Operación exitosa", 1);	
				limpiaTodo();
//			}else {
//				JOptionPane.showMessageDialog(null, "El cliente no se pudo registrar", "Operación exitosa", 0);	
//
//			}
		}

	}

	private void limpiaTodo() {
		borraCamposContacto();
		
		txtRsocial.setText("");
		txtCuit.setText("");
		txtDireccion.setText("");
		txtTelef.setText("");
		txtLocalidad.setText("");
		txtCodpostal.setText("");
		model.removeAll();
		cargaTabla();
		table.repaint();
		
	}

	private void cargaDatosCliente() {
		cliente.setCodPostal(Integer.parseInt(txtCodpostal.getText()));
		cliente.setRazonSocial(txtRsocial.getText());
		cliente.setCuit(Integer.parseInt(txtCuit.getText()));
		cliente.setDireccion(txtDireccion.getText());
		cliente.setLocalidad(txtLocalidad.getText());
		cliente.setTelefono(Integer.parseInt(txtTelef.getText()));
		cliente.setFlagBaja(1);
		
		for (Contacto contacto : contactos) {
			cliente.setContacto(contacto);
		}
		
	}

	private void borrarContactos() {
		if (table.getRowCount()>0) {
			model.removeRow(table.getSelectedRow());
			table.setModel(model);
			contactos.remove(table.getSelectedRow());
			table.repaint();
			System.out.println("Ingreso");
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnAgregar) {
			if (valoresEnterosContacto()) {

				agregarContacto();

			}

		}
		if (e.getSource() == btnGuardar) {
			if (valoresEnteros()) {
				int contador = 0;
				if (!compruebaCampo(txtRsocial)) {

					JOptionPane.showMessageDialog(null, "Rellene el campo", "El campo esta vacio", 0);
					txtRsocial.requestFocus();
					contador++;
				} else {
					if (!compruebaCampo(txtDireccion)) {
						JOptionPane.showMessageDialog(null, "Rellene el campo", "El campo esta vacio", 0);
						txtDireccion.requestFocus();
						contador++;
					} else {
						if (!compruebaCampo(txtLocalidad)) {
							JOptionPane.showMessageDialog(null, "Rellene el campo", "El campo esta vacio", 0);
							txtLocalidad.requestFocus();
							contador++;
						} else {

						}
					}
				}

				if (contador == 0) {
					guardarTodo();
				}
			}
		}
		if (btnSalir==e.getSource()) {
		int i=	JOptionPane.showConfirmDialog(null, "Los datos no guardados se perderán \n Desea salir igualmente?","Finalizando carga", JOptionPane.YES_NO_OPTION,3);
		System.out.println(i);
		if (i ==0) {
				dispose();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (table==arg0.getSource()) {

		int opcion=	JOptionPane.showConfirmDialog(null, "Desea eliminar", "Eliminar contacto", JOptionPane.OK_CANCEL_OPTION, 2);
			if (opcion==0) {
				borrarContactos();
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == txtDni) {
			FormatedGenerico.formatoInteger(arg0);
		}
		if (arg0.getSource() == txtTelefonocon) {
			FormatedGenerico.formatoInteger(arg0);
		}
		if (arg0.getSource() == txtTelef) {
			FormatedGenerico.formatoInteger(arg0);
		}
		if (arg0.getSource() == txtCodpostal) {
			FormatedGenerico.formatoInteger(arg0);
		}
		if (arg0.getSource() == txtCuit) {
			FormatedGenerico.formatoInteger(arg0);
		}
	}

}
