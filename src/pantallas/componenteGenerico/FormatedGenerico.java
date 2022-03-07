package pantallas.componenteGenerico;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFormattedTextField;

public class FormatedGenerico extends JFormattedTextField {
	private static JFormattedTextField form;

	public static void filtroFloat(KeyEvent e) {
		char c = e.getKeyChar();
		if ((c < '0' || c > '9') && (c < '.' || c > '.'))
			e.consume();

	}

	public static void formatoInteger(KeyEvent e) {
		char c = e.getKeyChar();
		
		if ((c < '0' || c > '9'))
			e.consume();
	}

	public static void addListenerKey(Object comp, JFormattedTextField jf,KeyEvent k) {
		form = jf;
		
		form.addKeyListener((KeyListener) comp);
	
	}

	public static boolean compruevaNoTieneLetras() {
		return false;
		// TODO Auto-generated method stub
		
	}
}
