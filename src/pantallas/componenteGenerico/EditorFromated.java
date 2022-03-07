package pantallas.componenteGenerico;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.EventObject;

import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

public class EditorFromated extends JFormattedTextField implements TableCellEditor {
	private Object edicion;
	private String valor;

	public EditorFromated() {
		this.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				editado(false);
				setValor("0");
			}

			public void focusGained(FocusEvent e) {

			}
		});
		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				editado(true);
				setValor("0");
			}
		});
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void addCellEditorListener(CellEditorListener l) {
		edicion = l;

	}

	@Override
	public void cancelCellEditing() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getCellEditorValue() {

		return valor;
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean stopCellEditing() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		return this;
	}

	public void editado(boolean cambio) {
		ChangeEvent evento = new ChangeEvent(this);
		CellEditorListener aux = (CellEditorListener) edicion;
		if (cambio) {
			aux.editingStopped(evento);
			getCellEditorValue();
		} else {
			aux.editingCanceled(evento);
			getCellEditorValue();
		}
	}

}
