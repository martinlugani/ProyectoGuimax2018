package pantallas.componenteGenerico;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Timer;

public class Reloj {
private Timer timer;
private Date hora;
	public Reloj() {
		timer = new Timer(1000, new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sn = new SimpleDateFormat("HH:mm:ss");
				hora.setTime(System.currentTimeMillis());

			}
		});
		timer.start();
	}
	public Date getHora() {
		return hora;
	}

}
