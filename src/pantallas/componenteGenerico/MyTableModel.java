package pantallas.componenteGenerico;

import java.util.LinkedList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

// PAntalla presupuesto
public class MyTableModel extends AbstractTableModel {
	private String[] columnNames;
	private Object[][] data;
	private LinkedList<LinkedList<Boolean>> editor = new LinkedList<LinkedList<Boolean>>();
	private int valorDeEdicion = 0;

	public void addRow(Object[][] objeto) {
		data = objeto;

	}

	public void addRow(Object[] fila) {
		Object[][] data2 = null;
		boolean paso = false;
		if (data != null) {
			data2 = data;
			data = new Object[data2.length + 1][columnNames.length];
			paso = true;
			int i = 0;
			for (Object[] objects : data2) {
				data[i] = objects;
				i++;
			}
			data[i] = fila;
		} else {
			data = new Object[1][columnNames.length];
			data[0] = fila;
		}

	}

	public void addColums(String[] colunmsName) {
		this.columnNames = colunmsName;
	}

	public int getColumnCount() {
		if (columnNames == null) {
			return 0;
		}
		return columnNames.length;
	}

	public int getRowCount() {
		if (data != null) {
			return data.length;
		}
		return 0;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	/*
	 * JTable uses this method to determine the default renderer/ editor for each
	 * cell. If we didn't implement this method, then the last column would contain
	 * text ("true"/"false"), rather than a check box.
	 */
	public Class getColumnClass(int c) {
//		System.out.println(getValueAt(0, c).getClass().toString()+"    "+ c);

		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's editable.
	 */
	public boolean isCellEditable(int row, int col) {
		if (valorDeEdicion == 0) {

			if (editor == null || editor.isEmpty()) {
				return true;
			}
			return editor.get(row).get(col);
		}
		if (valorDeEdicion == 1) {
			return true;
		}
		return false;
	}

	public void setCellEditing(LinkedList<Boolean> rowEditable) {
		if (editor != null) {

			editor.addLast(rowEditable);

		} else {

			for (LinkedList<Boolean> row : editor) {
				editor.addLast(rowEditable);
			}
		}
	}

	public void setCellEditing(boolean valorAsignado) {
		if (valorAsignado) {
			valorDeEdicion = 1;

		}
		valorDeEdicion = -1;

	}

	/**
	 * recibe el objeto a modificar , la fila y la columna Don't need to implement
	 * this method unless your table's data can change.
	 */
	public void setValueAt(Object value, int row, int col) {
		// if (DEBUG) {
		// System.out.println("Setting value at " + row + "," + col
		// + " to " + value
		// + " (an instance of "
		// + value.getClass() + ")");
		// }

		data[row][col] = value;
		fireTableCellUpdated(row, col);

		// if (DEBUG) {
		// System.out.println("New value of data:");
		// printDebugData();
		// }
	}

	public void aniadeColumna(String nombreColumna) {
		if (columnNames == null) {
			columnNames = new String[1];
			columnNames[0] = nombreColumna;
		} else {
			Vector<String> name = new Vector<String>();
			for (int i = 0; i < columnNames.length; i++) {
				name.add(columnNames[i]);
			}
			name.add(nombreColumna);
			columnNames = new String[name.size()];
			int i = 0;
			for (String string : name) {
				columnNames[i] = string;
				i++;
			}
		}

	}

	private void printDebugData() {
		int numRows = getRowCount();
		int numCols = getColumnCount();

		for (int i = 0; i < numRows; i++) {
			System.out.print("    row " + i + ":");
			for (int j = 0; j < numCols; j++) {
				System.out.print("  " + data[i][j]);
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}

	public void addRow(LinkedList<Object> data) {
		Object[] fila = new Object[columnNames.length];

		if (this.data == null) {
			this.data = new Object[1][columnNames.length];
			int c = 0;
			for (Object object : data) {
				this.data[0][c] = object;
				c++;
			}
		} else {
			int c = 0;
			Object[][] dataDuplicado = this.data;
			this.data = new Object[dataDuplicado.length + 1][getColumnCount()];
			for (Object object : data) {
				fila[c] = object;
				c++;
			}
			for (int f = 0; f < dataDuplicado.length; f++) {
				this.data[f] = dataDuplicado[f];
			}
			this.data[this.data.length - 1] = fila;

		}

	}

	public void removeAll() {
		data = null;
		editor = null;
		this.listenerList=null;
	}

	/**
	 * elimina por valores contenidos en la columna seleccionada solo programado
	 * para enteros
	 */
	public void removeRow(Object dato, int columna) {
		Object[][] tabla = data;
		if (columna < getColumnCount()) {
			data = new Object[tabla.length - 1][getColumnCount()];
			int f = 0;
			for (int i = 0; i < tabla.length; i++) {

				if (tabla[i][columna] != dato) {
					data[f] = tabla[i];
					f++;
				}
			}

		}

	}

	/**
	 * remueve la fila indicada
	 * 
	 * @param row
	 */
	public void removeRow(int row) {
		Object[][] tabla = data;
		if (row < data.length) {
			int fi = 0;
			data = new Object[tabla.length - 1][getColumnCount()];
			for (int f = 0; f < data.length; f++) {
				if (f != row) {
					data[fi] = tabla[f];
					fi++;
				}
			}
		}
	}
}
