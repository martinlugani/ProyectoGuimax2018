package pantallas.componenteGenerico;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Manejador {
	public static float formatoMonedaDeValores(float moneda) {
		DecimalFormat dec = new DecimalFormat("0.##", new DecimalFormatSymbols(Locale.ENGLISH));

		dec.setMaximumFractionDigits(2);

		return Float.valueOf(dec.format(moneda));
	}

	public static float formatoMonedaDeValores(String string) {
		DecimalFormat dec = new DecimalFormat("0.##", new DecimalFormatSymbols(Locale.ENGLISH));

		dec.setMaximumFractionDigits(2);
		float numeroCast=Float.parseFloat(string);
		
		float numero=Float.parseFloat(dec.format(numeroCast));
		return numero ;
	}
	public static float formatoMonedaDeValores(Object moneda) { 
		DecimalFormat dec = new DecimalFormat("0.##", new DecimalFormatSymbols(Locale.ENGLISH));

		dec.setMaximumFractionDigits(2);
		float numeroCast=Float.parseFloat(String.valueOf(moneda));
		float numero=Float.parseFloat(dec.format(numeroCast));
		return numero ;
	}
}
