package pantallas.componenteGenerico;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class ModeladoTabla extends AbstractTableModel implements TableModel,TableCellEditor {
	private Object[][] filas;
	private Object[] columnas;
	private boolean editable;

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public ModeladoTabla(String[][] filas, String[] columnas) {
		this.filas = filas;
		this.columnas = columnas;
		this.fireTableDataChanged();
		// this.setEditable(editable);

	}
	public ModeladoTabla(Object[][] filas, Object[] columnas) {
		this.filas = filas;
		this.columnas = columnas;
		this.fireTableDataChanged();
		// this.setEditable(editable);

	}
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnas.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return filas.length;
	}

	public Object getValueAt(int arg0, int arg1) {

		return filas[arg0][arg1];
	}

	public String getColumnName(int c) {
		
	String coll	=String.valueOf(columnas[c]);
		return coll;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return editable;
	}

	public void removeAll() {
		columnas = null;
		filas = null;
	}

	public void removeItem(int row) {
		Object duplicado[][] = new Object[filas.length - 1][];
		for (int i = 0; i < row; i++) {
			duplicado[i] = filas[i];
		}
		for (int i = row; i < duplicado.length; i++) {
			duplicado[i++] = filas[i];
		}
//		filas=new String[dupli]
		filas = duplicado;
		TableModelEvent evento = new TableModelEvent(this, row, row, TableModelEvent.ALL_COLUMNS);
	//	avisasubscriptores(evento);
		fireTableDataChanged();

	}

	@Override
	public void addCellEditorListener(CellEditorListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelCellEditing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCellEditable(EventObject arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeCellEditorListener(CellEditorListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean shouldSelectCell(EventObject arg0) {
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

}
