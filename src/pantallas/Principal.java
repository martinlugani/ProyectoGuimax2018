package pantallas;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import clases.coneccion.Fachada;
import clases.principales.seguridad.Rol;
import clases.principales.seguridad.Usuario;
import pantallas.materiales.MaterialAlta;
import pantallas.materiales.PantallaBaja;
import pantallas.materiales.PantallaStock;
import pantallas.operacion.AltaCliente;
import pantallas.operacion.GenerarPedido;
import pantallas.operacion.PantallaPresListado;
import pantallas.operacion.PresupuestoPantalla;
import pantallas.producto.AltaProduct;
import pantallas.usuarios.AltaUsuario;
import pantallas.usuarios.CambiaContrasenia;
import pantallas.usuarios.ModificarUsuarioPantalla;
import reportes.ListaPedidos;

@SuppressWarnings("serial")
public class Principal extends JFrame implements ActionListener {
	private Usuario usuario;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnAdministracion;
	private JMenuItem mntmAltaProducto;
	private JDesktopPane desktopPane;
	private JMenu mnMateriales;
	private JMenuItem mntmAltaMaterial;
	private JMenuItem mntmBaja;
	private JMenuItem mntmModificacion;
	private JMenu mnProductos;
	private JMenuItem mntmCrearNuevoUsuario;
	private JMenuItem mntmCambiarContrasea;
	private JMenu mnPresupuestos;
	private JMenuItem mntmGenerarPresupuesto;
	private JMenuItem mntmAltaProveedor;
	private JMenuItem mntmModificacionProveedor;
	private JMenuItem mntmBajaProveedor;
	private JMenuItem mntmAltaCliente;
	private JMenuItem mntmModificacionCliente;
	private int cont = 0;
	private JMenuItem mntBajaProducto;
	private JMenuItem mntModificacionProducto;
	private JMenuItem mntmStock;
	private JMenuItem mntmListarMateriales;
	private JMenuItem mntmPedidos;
	private JMenuItem mntmListarPedidos;
	private JMenuItem mntmListarPresupuestos;
	private JMenu mnClientes;
	private JMenuItem mntmBajaCliente;
	private JMenuItem mntmListarClientes;
	private JMenu mnProveedor;
	private Fachada fac;
	private JMenuItem mntmModificarUsuario;
	private Timer timer;
	private JMenu mnConfiguracion;
	private JMenuItem mntmCambiarUsuario;
	private JMenuItem mntmAcercaDe;
	private JMenuItem mntmSalir;
	private JButton btnCambiarUsuario;
	private Date hora;
	private Image imag;
	private URL fondo;

	public Principal(Usuario u) {
		SimpleDateFormat sn = new SimpleDateFormat("HH:mm:ss");
		hora = new Date();

		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sn = new SimpleDateFormat("dd/MM/yyyy   HH:mm:ss");
				hora.setTime(System.currentTimeMillis());
				// txthora.setText(sn.format(hora));
				// System.out.println(sn.format(hora));
				setTitle("Menu Principal       " + sn.format(hora));
			}
		});
		timer.start();
		// Image imagenInterna = new ImageIcon(
//				   getClass().getResource("imagenes/industrial.jpg")
//				).getImage();
		// setTitle("Menu Principal "+hora.toString());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 650);
		usuario = u;
		cargarComplementos();
		// mntmAltaProducto.addActionListener(this);

	}

	private void cargarComplementos() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		fondo = this.getClass().getResource("/imagenes/industrial.jpg");
		imag = new ImageIcon(fondo).getImage();
		Container cont = getContentPane();

		mnAdministracion = new JMenu("Administracion");
		menuBar.add(mnAdministracion);

		mntmCrearNuevoUsuario = new JMenuItem("Crear nuevo Usuario");
		mnAdministracion.add(mntmCrearNuevoUsuario);

		mntmCambiarContrasea = new JMenuItem("Cambiar contrase\u00F1a");
		mntmCambiarContrasea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CambiaContrasenia name = new CambiaContrasenia(usuario);
			}
		});
		mntmCambiarContrasea.setToolTipText("");
		mnAdministracion.add(mntmCambiarContrasea);

		mntmModificarUsuario = new JMenuItem("Modificar usuario");
		mnAdministracion.add(mntmModificarUsuario);

		mnProductos = new JMenu("Productos");
		menuBar.add(mnProductos);

		mntmAltaProducto = new JMenuItem("Alta");
		mnProductos.add(mntmAltaProducto);

		mntBajaProducto = new JMenuItem("Baja");
		mntBajaProducto.setToolTipText("No disponible para la version");
		mntBajaProducto.setEnabled(false);
		mnProductos.add(mntBajaProducto);

		mntModificacionProducto = new JMenuItem("Modificacion");
		mntModificacionProducto.setToolTipText("No disponible para la version");
		mntModificacionProducto.setEnabled(false);
		mnProductos.add(mntModificacionProducto);

		mnMateriales = new JMenu("Materiales");
		menuBar.add(mnMateriales);

		mntmAltaMaterial = new JMenuItem("Alta ");
		mnMateriales.add(mntmAltaMaterial);

		mntmBaja = new JMenuItem("Baja");
		mnMateriales.add(mntmBaja);

		mntmModificacion = new JMenuItem("Modificacion");
		mntmModificacion.setToolTipText("No disponible para la version");
		mnMateriales.add(mntmModificacion);

		mntmStock = new JMenuItem("Stock Modificar");
		mnMateriales.add(mntmStock);

		mntmListarMateriales = new JMenuItem("Listar Materiales");
		mntmListarMateriales.setEnabled(false);
		mntmListarMateriales.setToolTipText("No disponible para la version");
		mnMateriales.add(mntmListarMateriales);

		mnPresupuestos = new JMenu("Generar");
		menuBar.add(mnPresupuestos);

		mntmGenerarPresupuesto = new JMenuItem("Generar Presupuesto");
		mnPresupuestos.add(mntmGenerarPresupuesto);

		mntmPedidos = new JMenuItem("Generar Pedidos");
		mnPresupuestos.add(mntmPedidos);

		mntmListarPedidos = new JMenuItem("Listar Pedidos");
		mntmListarPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaPedidos ped = new ListaPedidos(usuario);
			}
		});
		mnPresupuestos.add(mntmListarPedidos);

		mntmListarPresupuestos = new JMenuItem("Listar Presupuestos");
		mnPresupuestos.add(mntmListarPresupuestos);

		mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);

		mntmAltaCliente = new JMenuItem("Alta Cliente");
		mntmAltaCliente.setSelected(true);
		mntmAltaCliente.setToolTipText("");
		mntmAltaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AltaCliente name = new AltaCliente();
			}
		});
		mnClientes.add(mntmAltaCliente);

		mntmModificacionCliente = new JMenuItem("Modificacion Cliente");
		mntmModificacionCliente.setToolTipText("No disponible para la version");
		mntmModificacionCliente.setEnabled(false);
		mnClientes.add(mntmModificacionCliente);

		mntmBajaCliente = new JMenuItem("Baja Cliente");
		mntmBajaCliente.setEnabled(false);
		mntmBajaCliente.setToolTipText("No disponible para la version");
		mnClientes.add(mntmBajaCliente);

		mntmListarClientes = new JMenuItem("Listar Clientes");
		mntmListarClientes.setToolTipText("No disponible para la version");
		mntmListarClientes.setEnabled(false);
		mnClientes.add(mntmListarClientes);

		mnProveedor = new JMenu("Proveedor");
		menuBar.add(mnProveedor);

		mntmAltaProveedor = new JMenuItem("Alta Proveedor");
		mntmAltaProveedor.setToolTipText("No disponible para la version");
		mntmAltaProveedor.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mntmAltaProveedor.setEnabled(false);
		mnProveedor.add(mntmAltaProveedor);

		mntmModificacionProveedor = new JMenuItem("Modificacion Proveedor");
		mntmModificacionProveedor.setToolTipText("No disponible para la version");
		mntmModificacionProveedor.setEnabled(false);
		mnProveedor.add(mntmModificacionProveedor);

		mntmBajaProveedor = new JMenuItem("Baja Proveedor");
		mntmBajaProveedor.setEnabled(false);
		mntmBajaProveedor.setToolTipText("No disponible para la version");
		mnProveedor.add(mntmBajaProveedor);

		mnConfiguracion = new JMenu("Configuracion");
		menuBar.add(mnConfiguracion);

		mntmCambiarUsuario = new JMenuItem("Cambiar Usuario");
		mntmCambiarUsuario.setVisible(false);
		mnConfiguracion.add(mntmCambiarUsuario);

		mntmAcercaDe = new JMenuItem("Acerca de... ");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Desarrollado por Martín Lugani\nPara Ifts nº 11\n P.P. 3\nProf Fernando Larrosa",
						"Verción 1.0 ", 1);
			}
		});
		mnConfiguracion.add(mntmAcercaDe);

		mntmSalir = new JMenuItem("Salir");
		mnConfiguracion.add(mntmSalir);
		contentPane = new JPanel() {
//			public void paintComponent(Graphics g){
//			g.drawImage(imag, 0,0, getWidth(), getHeight(), this);

//		}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setBounds(691, 502, -691, -500);

		contentPane.add(desktopPane);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salir();
			}
		});

		btnCambiarUsuario = new JButton("Cambiar usuario");
		btnCambiarUsuario.setVisible(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(512, Short.MAX_VALUE)
						.addComponent(btnCambiarUsuario).addGap(34).addComponent(btnSalir).addGap(26)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap(523, Short.MAX_VALUE).addGroup(gl_contentPane
						.createParallelGroup(Alignment.BASELINE).addComponent(btnSalir).addComponent(btnCambiarUsuario))
				.addGap(35)));
		contentPane.setLayout(gl_contentPane);
		activaEscuchadores();

		validaBotones();

	}

	private void activaEscuchadores() {
		// TODO Auto-generated method stub
		mntmBaja.addActionListener(this);
		mntmAltaProducto.addActionListener(this);
		mntmCrearNuevoUsuario.addActionListener(this);
		mntmAltaMaterial.addActionListener(this);
		mntmGenerarPresupuesto.addActionListener(this);
		mntmStock.addActionListener(this);
		mntmModificarUsuario.addActionListener(this);
		mntmListarPresupuestos.addActionListener(this);
		mntmCambiarUsuario.addActionListener(this);
		mntmPedidos.addActionListener(this);
		btnCambiarUsuario.addActionListener(this);
		mntmSalir.addActionListener(this);
	}

	private void validaBotones() {
		// TODO Auto-generated method stub
		mntmBaja.setEnabled(true);
		fac = new Fachada();
		mntmModificacion.setEnabled(false);
		Vector<String> columnas = new Vector<String>();
		Vector<String> clausula = new Vector<String>();
		Vector<String> busqueda = new Vector<String>();
		Vector<String> tablas = new Vector<String>();
		columnas.add("rol");
		clausula.add("nommbreUsuario");
		tablas.add("usuario");
		busqueda.add("'" + usuario.getNombreUsuario() + "'");
		Vector<Vector<String>> consulta = fac.selectGenerica(tablas, columnas, clausula, busqueda);
		System.out.println(consulta.get(0));
		Rol rol = new Rol();
		rol.setIdRol(Integer.parseInt(consulta.get(0).get(0).toString()));
		usuario.setRol(rol);

		if (usuario.getRol().getIdRol() == 2) {
			mntmCrearNuevoUsuario.setEnabled(false);
			mntmCrearNuevoUsuario.setToolTipText("No tiene permisos ");

			mntmAltaProducto.setEnabled(false);
			mntmAltaProducto.setToolTipText("No tiene permisos");
			mntmStock.setEnabled(false);
			mntmStock.setToolTipText("No tiene permisos");
			mntmModificarUsuario.setEnabled(false);
			mntmModificarUsuario.setToolTipText("No tiene permisos");

		}
	}

	public void salir() {
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == mntmAltaMaterial) {
			MaterialAlta materialA;
			try {
				materialA = new MaterialAlta();

			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (e.getSource() == mntmBaja) {
			PantallaBaja pant;
			pant = new PantallaBaja();

		}
		if (e.getSource() == mntmCrearNuevoUsuario) {
			AltaUsuario alta;
			alta = new AltaUsuario();
		}
		if (e.getSource() == mntmGenerarPresupuesto) {
			PresupuestoPantalla pant = new PresupuestoPantalla();
		}
		if (e.getSource() == mntmAltaProducto) {

			AltaProduct alta = new AltaProduct();
			System.out.println(cont);
			cont++;
			// alta.setLocationRelativeTo(null);

		}
		if (e.getSource() == mntmStock) {
			PantallaStock pant = new PantallaStock();
		}
		if (e.getSource() == mntmModificarUsuario) {
			ModificarUsuarioPantalla modif = new ModificarUsuarioPantalla();
		}
		if (e.getSource() == mntmListarPresupuestos) {
			PantallaPresListado listPres = new PantallaPresListado(this.usuario);
//			listPres.setVisible(true);
		}
		if (e.getSource() == mntmPedidos) {
			GenerarPedido name = new GenerarPedido(timer, usuario);
		}
		if (e.getSource() == mntmSalir) {
			salir();
		}
		if (e.getSource() == btnCambiarUsuario) {

			Loging name = new Loging();
			this.dispose();
		}
	}

	public void setUsuario(Usuario u) {
		usuario = u;

	}

}
