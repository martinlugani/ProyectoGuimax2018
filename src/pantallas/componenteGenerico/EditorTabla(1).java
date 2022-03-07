package pantallas.componenteGenerico;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.EventObject;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

import com.sun.prism.impl.ps.CachingEllipseRep;

public class EditorTabla extends JComboBox implements TableCellEditor {
	private LinkedList subscriptores = new LinkedList();

	public EditorTabla() {
//		Se le ponen las opciones al JComboBox
		super(new String[] { "Jov", "Maduro", "Anciano" });
//		Nos apuntamos a caundo de seleccione algo ,para avisar a la tabla
//		el cambio de datos
		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				editado(true);

			}
		});
//		Apuntamos la perdida de foco Avisaandole a la tabla 
//		que se ha dejado de editar la celda
		this.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				editado(false);
			}

			public void focusGained(FocusEvent e) {

			}
		});
	}

//	se aniade el subscriptor a la lista 
	public void addCellEditorListener(CellEditorListener arg0) {
		subscriptores.add(arg0);

	}

	public void cancelCellEditing() {

	}

	/**
	 * se obtiene la opcion de combobox elegida y debvuelve la edad solicitada
	 * 
	 * @author martucho
	 * @return object
	 */
	public Object getCellEditorValue() {
		switch (this.getSelectedIndex()) {
		case 0:
			return new Integer(15);
		case 1:
			return new Integer(33);

		case 2:
			return new Integer(70);
		}
		return null;
	}

	/**
	 * Asigna la edicion de una celda al clicar Sobre ella
	 * 
	 * @param EventObjet un evento sobre un objeto EventObjet permite editar la
	 *                   celda
	 * @return boolean siempre true
	 */
	public boolean isCellEditable(EventObject arg0) {

		return true;
	}

	/**
	 * Elimina el subscriptor
	 * 
	 * @param arg0 recive un escuchador
	 */

	public void removeCellEditorListener(CellEditorListener arg0) {
		subscriptores.remove(arg0);

	}

	/**
	 * Indica si al editar la celda , debemos seleccionar la fila que contiene
	 * 
	 * @param arg0 evento que dispara la edicion
	 * @return <code>true</code> si el editor desea que se selevcione la celda si
	 *         regresa <code>false</code>
	 * @see #isCellEditable
	 */
	public boolean shouldSelectCell(EventObject arg0) {

		return true;
	}

	/**
	 * Indica si se puede detener la edicion
	 * 
	 * @return <code>true</code> si se detuvo, <code>false</code>si no se detuvo
	 */

	public boolean stopCellEditing() {

		return true;
	}

	/**
	 * Devuelve el jComboBox del que heredamos
	 * 
	 * @param table      puede recibir null o un objeto JTable
	 * @param value      valora el valor de la celda a editar y dibujar el valor
	 *                   rcibe un Object
	 * @param isSelected true si la celda debe renderizarce
	 * @param row        valor que corresponde a unsa fila
	 * @param column     valor que corresponde a una columna
	 * @return Devuelve el componenter a editar
	 */

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		return this;
	}

	/**
	 * Avisa a los subscriptores que a terminado la edicion
	 * 
	 * @param cambiado Si es <code>true</code> fin de edicion; si es
	 *                 <code>false</code> avisa que se cancela la edicion
	 */
	protected void editado(boolean cambiado) {
		ChangeEvent evento = new ChangeEvent(this);

		for (int i = 0; i < subscriptores.size(); i++) {
			CellEditorListener aux = (CellEditorListener) subscriptores.get(i);
			if (cambiado) {
				aux.editingStopped(evento);
			} else {
				aux.editingCanceled(evento);
			}
		}
	}
}
