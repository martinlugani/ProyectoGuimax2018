package pantallas.materiales;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.TableRowSorter;

import clases.coneccion.Fachada;
import clases.principales.productos.Material;
import clases.principales.productos.Producto;
import pantallas.componenteGenerico.FormatedGenerico;
import pantallas.componenteGenerico.MyTableModel;

public class PantallaBaja extends JDialog implements KeyListener, ActionListener, CaretListener, MouseListener {

	private final JPanel contentPanel = new JPanel();

//	private JTable table;

	private Fachada fac;
	private Vector<Material> materiales;
	private TableRowSorter<MyTableModel> busqueda;
	private RowFilter<MyTableModel, Integer> ordena;
	private ButtonGroup radios;
	private JButton btnEliminar, btnSalir;
	private MyTableModel modelo;
	private JTextField txtDescripcion;
	private JFormattedTextField frmtdtxtfldId;
	private JScrollPane scrollPane;
	private JRadioButton rdbtnPorDescripcion;
	private JRadioButton porCodigo;
	private JPanel panel_2;
	private JPanel panel;
	private JPanel panel_1;
	private JTable table;

	private MyTableModel mioModelo;

	public PantallaBaja() {

		setTitle("Eliminar Material");
		setBounds(100, 100, 757, 458);

		getContentPane().setLayout(new BorderLayout());
		setModal(isDisplayable());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		fac = new Fachada();
		cargaTabla();

//		table.setRowSorter(busqueda);
		radios = new ButtonGroup();
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setSelectedIcon(new ImageIcon(
				"C:\\Users\\martucho\\Desktop\\sincronizado\\WorkPP3\\ProyectoGuimax\\imagenes\\basura.png"));
		btnEliminar.setBounds(493, 389, 99, 25);
		contentPanel.add(btnEliminar);

		btnSalir = new JButton("Atras");
		btnSalir.setBounds(620, 389, 99, 25);
		contentPanel.add(btnSalir);

		panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Seleccione material", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 142, 709, 234);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 23, 685, 198);
		panel_1.add(scrollPane);

//		table = new JTable();
		scrollPane.setViewportView(table);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Busqueda",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(22, 13, 697, 106);
		contentPanel.add(panel);
		panel.setLayout(null);

		txtDescripcion = new JTextField();
		txtDescripcion.setEnabled(false);
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(424, 73, 86, 20);
		panel.add(txtDescripcion);

		JLabel label = new JLabel("Introduzca descripcion");
		label.setBounds(274, 76, 131, 14);
		panel.add(label);

		frmtdtxtfldId = new JFormattedTextField();
		frmtdtxtfldId.setBounds(176, 73, 86, 20);
		panel.add(frmtdtxtfldId);

		JLabel label_1 = new JLabel("Ingrese codigo");
		label_1.setBounds(56, 76, 108, 14);
		panel.add(label_1);

		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setToolTipText("");
		panel_2.setBounds(168, 35, 379, 28);
		panel.add(panel_2);

		rdbtnPorDescripcion = new JRadioButton("Por codigo");
		rdbtnPorDescripcion.setSelected(true);
		rdbtnPorDescripcion.setBounds(8, 0, 145, 25);
		panel_2.add(rdbtnPorDescripcion);

		porCodigo = new JRadioButton("Por descripcion");
		porCodigo.setBounds(248, 0, 123, 25);
		panel_2.add(porCodigo);
		addListeners();
//		busqueda =new TableRowSorter<MyTableModel>(modelo);
		table.setRowSorter(busqueda);
		table.setAutoCreateRowSorter(false);
		radios.add(rdbtnPorDescripcion);
		radios.add(porCodigo);
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);

	}

	private void addListeners() {
		// TODO Auto-generated method stub
		txtDescripcion.addCaretListener(this);
		frmtdtxtfldId.addKeyListener(this);
		frmtdtxtfldId.addCaretListener(this);
		porCodigo.addActionListener(this);
		rdbtnPorDescripcion.addActionListener(this);
		porCodigo.addActionListener(this);
		btnEliminar.addActionListener(this);
		btnSalir.addActionListener(this);
		table.addMouseListener(this);
		;
	}

	private void cargaTabla() {

		if (modelo != null) {
			modelo = null;
			table = null;
			busqueda = null;
		}

		modelo = new MyTableModel();
		modelo.aniadeColumna("Codigo");
		modelo.aniadeColumna("Descripcion");
		modelo.aniadeColumna("Stock");
		modelo.aniadeColumna("Punto pedido");
		modelo.aniadeColumna("Precio");
		modelo.aniadeColumna("Ultimo pagado");
		modelo.aniadeColumna("Tipo");
		modelo.aniadeColumna("Unidad de medida");

		cargaMateriales();
		modelo.fireTableDataChanged();
		busqueda = new TableRowSorter<MyTableModel>(modelo);
//		modelo.bloqueaEdicion(false);
		if (table != null) {
			table = new JTable(modelo);
//			table.setModel(modelo);

		} else {
			table = new JTable();
			table.setModel(modelo);

		}

		table.setVisible(false);

		table.setVisible(true);
		table.setRowSorter(busqueda);

	}

	private void filtro() {

		busqueda.setRowFilter(RowFilter.regexFilter("^" + txtDescripcion.getText(), 1));
	}

	private void filtroId() {
		busqueda.setRowFilter(RowFilter.regexFilter(frmtdtxtfldId.getText(), 0));
	}

	private void cargaMateriales() {
		materiales = (Vector<Material>) fac.selectMaterial();
		for (Material material : materiales) {
			if (material.getFlagBaja() == 1) {
				LinkedList<Object> name = new LinkedList<Object>();
				name.add(material.getIdMaterial());
				name.add(material.getDescripcion());
				name.add(material.getStockActual());
				name.add(material.getPuntoPedido());
				name.add(material.getPrecioActual());
				name.add(material.getUltimoPrecio());
				name.add(material.getTipoMaterial().getDescripcion());
				name.add(material.getTipoMaterial().getCaracteristica().getUnidadMedida());
				modelo.addRow(name);
				modelo.setCellEditing(false);
				;
			}
		}
	}

	private String[][] cargaMateriales1() {
		materiales = (Vector<Material>) fac.selectMaterial();
		String[][] mates = new String[cuentaElementos()][8];
		int count = 0;
		for (Material material : materiales) {
			if (material.getFlagBaja() == 1) {

				mates[count][0] = String.valueOf(material.getIdMaterial());
				mates[count][1] = material.getDescripcion();
				mates[count][2] = String.valueOf(material.getStockActual());
				mates[count][3] = String.valueOf(material.getPuntoPedido());
				mates[count][4] = String.valueOf(material.getPrecioActual());
				mates[count][5] = String.valueOf(material.getUltimoPrecio());
				mates[count][6] = material.getTipoMaterial().getDescripcion();
				mates[count][7] = material.getTipoMaterial().getCaracteristica().getUnidadMedida();
				count++;
			}

		}

		return mates;
	}

	private int cuentaElementos() {
		int cont = 0;
		for (Material material : materiales) {
			if (material.getFlagBaja() == 1) {
				cont++;
			}
		}
		return cont;
	}

	private Material eliminar(int row) {
		Material mate = new Material();
		mate.setIdMaterial(Integer.parseInt(String.valueOf(table.getValueAt(row, 0))));
		mate.setDescripcion(String.valueOf(table.getValueAt(row, 1)));
		mate.setStockActual(Integer.parseInt(String.valueOf(table.getValueAt(row, 2))));
		mate.setPuntoPedido(Integer.parseInt(String.valueOf(table.getValueAt(row, 3))));
		mate.setPrecioActual(Float.parseFloat(String.valueOf(table.getValueAt(row, 4))));
		mate.setUltimoPrecio(Float.parseFloat(String.valueOf(table.getValueAt(row, 5))));
		mate.setDescripcionMaterial(String.valueOf(table.getValueAt(row, 6)));
		mate.setFlagBaja(0);
		return mate;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == porCodigo) {
			if (porCodigo.isSelected()) {
				frmtdtxtfldId.setEnabled(false);
				txtDescripcion.setEnabled(true);
				txtDescripcion.requestFocus(true);
				frmtdtxtfldId.setText("");
			}
		}
		if (e.getSource() == rdbtnPorDescripcion) {
			if (rdbtnPorDescripcion.isSelected()) {
				txtDescripcion.setText("");
				frmtdtxtfldId.setEnabled(true);
				frmtdtxtfldId.requestFocus();
				txtDescripcion.setEnabled(false);
			}
		}
		if (e.getSource() == btnSalir) {
			this.setVisible(false);
			this.dispose();
		}
		if (e.getSource() == btnEliminar) {
			if (table.getValueAt(table.getSelectedRow(), 2).toString().equals("0")) {

				if (table.getSelectedRow() >= 0) {
					System.out.println(table.getSelectedRow());

					boolean paso = true;
					Vector<Producto> name = (Vector<Producto>) fac.selectProducto();
					for (Producto producto : name) {
						for (int i = 0; i < producto.getLineas().size(); i++) {
							if (Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()) == producto
									.getLineas().get(i).getIdMaterial()) {
								System.out.println(table.getSelectedRow());
								paso = false;
							}
						}
					}
					if (paso) {

						if (frmtdtxtfldId.getText().equals("") && txtDescripcion.getText().equals("")) {
							if (table.getSelectedRows().length == 1) {
								if (table.getSelectedRow() >= 0) {

									int row = table.getSelectedRow();
									if (fac.updateMaterial(eliminar(row)))
										JOptionPane.showMessageDialog(null, "Elemento eliminado", "Eliminar elemento",
												JOptionPane.INFORMATION_MESSAGE);

									// cargaTabla();
									// table.repaint();

									int colum = 0;
									row = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
									busqueda.getModel().removeRow(row, colum);
//								modelo.removeRow(row);
									modelo.fireTableDataChanged();
//								table.setModel(modelo);
									busqueda.allRowsChanged();
//								table.setRowSorter(busqueda);
									table.repaint();

								} else {
									JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento", "Error",
											JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento", "Error",
										JOptionPane.ERROR_MESSAGE);
							}

						} else {

							if (table.getSelectedRow() >= 0) {
								System.out.println(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
								int[] selection = table.getSelectedRows();
								int sele = 0;
								if (selection.length == 1) {
									for (int i = 0; i < selection.length; i++) {
										selection[i] = table.convertRowIndexToModel(selection[i]);
										sele = selection[i];
									}

									for (int i = 0; i < selection.length; i++) {

										table.setAutoCreateRowSorter(true);
										System.out.println(table.getValueAt(sele, 1) + "   " + i);

									}
									Material mat = null;
									for (Material material : materiales) {
										if (material.getIdMaterial() == Integer
												.parseInt(String.valueOf(table.getValueAt(sele, 0)))) {
											mat = material;
											System.out.println(mat);
										}

									}
									mat.setFlagBaja(0);
									if (fac.updateMaterial(mat))
										JOptionPane.showMessageDialog(null, "Elemento eliminado", "Eliminar elemento",
												JOptionPane.INFORMATION_MESSAGE);
									System.out.println(table.getSelectedRow());
									dispose();
									PantallaBaja pa = new PantallaBaja();

								} else {
									JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento", "Error",
											JOptionPane.ERROR_MESSAGE);
								}

							}

						}
					} else {
						JOptionPane.showMessageDialog(null, "El material se encuentra en un producto",
								"Operación no realizada", 0);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un material", "Operación no realizada", 0);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Aun quedan materiales", "No se puede eliminar", 0);
			}
//			repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == frmtdtxtfldId)
			FormatedGenerico.formatoInteger(arg0);
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == txtDescripcion) {
//			System.out.println("funciona");
			repaint();
			filtro();
		}
		if (e.getSource() == frmtdtxtfldId) {
//			System.out.println("funciona3333");
			repaint();
			filtroId();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (table == arg0.getSource()) {
			System.out.println(table.getSelectedRow());
			System.out.println(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
			System.out.println(table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

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
}
