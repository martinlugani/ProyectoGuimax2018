package pantallas.componenteGenerico;

import java.text.DecimalFormat;
import java.text.ParseException;

public class EliminaCientifica {
	public static String elimina(double numero) {
		return new DecimalFormat("###,###,###,###,###.00").format(numero);
	}
	public static double eliminaDoble(double numero) {
		DecimalFormat dc =new DecimalFormat("###,###,###,###,###.00");
		Number nu=0;
		try {
			nu = dc.parse(String.valueOf(numero));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double num =nu.doubleValue();
		return num;
	}
}
