package pantallas.componenteGenerico;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventObject;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

import org.w3c.dom.views.AbstractView;

public class ModeloTable extends AbstractTableModel implements TableModel, TableCellEditor, TableModelListener {
	private LinkedList<LinkedList> datos = new LinkedList<LinkedList>();
	private LinkedList listeners = new LinkedList();
	private LinkedList columnas = new LinkedList();
	private LinkedList<LinkedList<Boolean>> editor = new LinkedList<LinkedList<Boolean>>();

	/**
	 * Aniade un escuchador a la lista de escuchadores
	 * 
	 * @param l Obtiene el escuchador correspondiente
	 */
	public void addTableModelListener(TableModelListener l) {
		listeners.add(l);

	}

	/**
	 * Devuelve la calse de la columna
	 * 
	 * @param columnIndex inresar el indice de la columna indicada
	 */

	public Class<?> getColumnClass(int columnIndex) {
		for (Iterator iterator = columnas.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			int count = 0;
			if (count == columnIndex) {
				if (object instanceof String) {
					return String.class;
				}
				if (object instanceof Integer) {
					return Integer.class;
				}
				if (object instanceof JComboBox) {
					return JComboBox.class;
				}
			}
		}
		return Object.class;
	}

	/**
	 * cuenta la cantidad de columnas
	 * 
	 * @return int cantidad de columnas cargadas
	 */
	public int getColumnCount() {

		return columnas.size();

	}

	/**
	 * devuelve el nombre de la columna
	 * 
	 * @param columnIndex indica la columna solicitada
	 * @return Devuelve un String con el nombre de ella
	 * 
	 */
	public String getColumnName(int columnIndex) {
		for (int i = 0; i < columnas.size(); i++) {
			if (i == columnIndex) {
				return (String) columnas.get(columnIndex);
			}
		}
		return "No hay columnas";
	}

	public int getRowCount() {

		return datos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		try {
			if (datos.size()>rowIndex) {
				return datos.get(rowIndex).get(columnIndex);
			}
			
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		return editor.get(rowIndex).get(columnIndex);
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		datos.get(rowIndex).add(columnIndex, aValue);
		TableModelEvent evento = new TableModelEvent(this, rowIndex, rowIndex, columnIndex);
		avisasubscriptores(evento);

	}

	private void avisasubscriptores(TableModelEvent evento) {
		for (int i = 0; i < listeners.size(); i++) {
			((TableModelListener) listeners.get(i)).tableChanged(evento);
		}

	}

	public void borraFila(int row) {
		datos.remove(row);
		TableModelEvent evento = new TableModelEvent(this, row, row, TableModelEvent.ALL_COLUMNS);
		avisasubscriptores(evento);
	}

	public void aniadeFila(LinkedList fila) {
		datos.add(fila);
		TableModelEvent evento = new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1,
				TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
		avisasubscriptores(evento);

	}

	public void aniadeColumna(Object o) {
		columnas.add(o);
		TableModelEvent evento = new TableModelEvent(this, TableModelEvent.HEADER_ROW);
		avisasubscriptores(evento);
	}

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub

	}

	public void setCellEditing() {

	}

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean stopCellEditing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Sirve para bloquear la edicion de todas las celdas de una vez
	 * 
	 * @param edit
	 */
	public void bloqueaEdicion(boolean edit) {
		LinkedList<Boolean> e = new LinkedList<Boolean>();
		for (int c = 0; c < columnas.size(); c++) {
			e.add(edit);
		}
		editor.add(e);
	}

	/**
	 * Toma un vector que representa las filas que determinan la condicion de
	 * edicion de cada una de las celdas. Esto se debe hacer sobre la ultima fila
	 * aniadida
	 * 
	 * @param bol es un Vector
	 */
	public void setCellEditable(Vector<Boolean> bol) {
		LinkedList<Boolean> bole = new LinkedList<Boolean>();
		for (Boolean boolean1 : bol) {
			bole.add(boolean1);
		}
		editor.add(bole);

	}

	public void setCellEditable(boolean valor, int columna) {
		editor.getLast().set(columna, valor);
	}

	@Override
	public void cancelCellEditing() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tableChanged(TableModelEvent e) {

	}
/**
 * Este metodo sirve para vaciar la tabla indicada
 */
	public void removeAll() {
			int dat=datos.size();
			for (int i = dat; i > 0; i--) {
				datos.remove(i-1);
			
		}
		
	}

}
