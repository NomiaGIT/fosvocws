package utilitarios;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Formato {

	/**
	 * dado un entero, convierte el mismo en String con formato cedula con barra y separador de miles
	 * 
	 * @param cedula
	 * @return
	 */
	public static String convertirCedula(int cedula) {
		String cedu = "";
		String aux = String.valueOf(cedula);
		int tam = String.valueOf(cedula).length();
		if (tam == 8) {
			char c = aux.charAt(0);
			cedu = cedu + c;
			aux = aux.substring(1);
			cedu = cedu + ".";// agregue el numero de los millones
			for (int i = 0; i < 3; i++) {
				c = aux.charAt(0);
				cedu = cedu + c;
				aux = aux.substring(1);
			}
			cedu = cedu + ".";// agregue el numero de los miles

			for (int i = 0; i < 3; i++) {
				c = aux.charAt(0);
				cedu = cedu + c;
				aux = aux.substring(1);
			}
			cedu = cedu + "-";// agregue el numero de las unidades
			c = aux.charAt(0);
			cedu = cedu + c;
		} else if (tam == 7)// falta el digito verificador?
		{
			int primCifra = Integer.parseInt(aux.charAt(0) + "");
			if (primCifra < 6)// el primer numero es menor a 6, asumo que son
								// millones
			{// asumo que se olvidaron de el digito verificador
				char c = aux.charAt(0);
				cedu = cedu + c;
				aux = aux.substring(1);
				cedu = cedu + ".";// agregue el numero de los millones
				for (int i = 0; i < 3; i++) {
					c = aux.charAt(0);
					cedu = cedu + c;
					aux = aux.substring(1);
				}
				cedu = cedu + ".";// agregue el numero de los miles

				for (int i = 0; i < 3; i++) {
					c = aux.charAt(0);
					cedu = cedu + c;
					aux = aux.substring(1);
				}

			} else// el primer digito es 6 o mas, asumo que es una cedula sin
					// millones
			{
				char c = aux.charAt(0);
				for (int i = 0; i < 3; i++) {
					c = aux.charAt(0);
					cedu = cedu + c;
					aux = aux.substring(1);
				}
				cedu = cedu + ".";// agregue el numero de los miles

				for (int i = 0; i < 3; i++) {
					c = aux.charAt(0);
					cedu = cedu + c;
					aux = aux.substring(1);
				}
				cedu = cedu + "-";// agregue el numero de las unidades
				c = aux.charAt(0);
				cedu = cedu + c;
			}
		}
		return cedu;
	}

	public static String mesToString(int mes) {
		String resu = "";
		switch (mes) {
		case 1:
			resu = "Enero";
			break;
		case 2:
			resu = "Febrero";
			break;
		case 3:
			resu = "Marzo";
			break;
		case 4:
			resu = "Abril";
			break;
		case 5:
			resu = "Mayo";
			break;
		case 6:
			resu = "Junio";
			break;
		case 7:
			resu = "Julio";
			break;
		case 8:
			resu = "Agosto";
			break;
		case 9:
			resu = "Setiembre";
			break;
		case 10:
			resu = "Octubre";
			break;
		case 11:
			resu = "Noviembre";
			break;
		case 12:
			resu = "Diciembre";
			break;

		}
		return resu;
	}

	public static String mesToStringDesdeCero(int mes) {
		String resu = "";
		switch (mes) {
		case 0:
			resu = "Enero";
			break;
		case 1:
			resu = "Febrero";
			break;
		case 2:
			resu = "Marzo";
			break;
		case 3:
			resu = "Abril";
			break;
		case 4:
			resu = "Mayo";
			break;
		case 5:
			resu = "Junio";
			break;
		case 6:
			resu = "Julio";
			break;
		case 7:
			resu = "Agosto";
			break;
		case 8:
			resu = "Setiembre";
			break;
		case 9:
			resu = "Octubre";
			break;
		case 10:
			resu = "Noviembre";
			break;
		case 11:
			resu = "Diciembre";
			break;

		}
		return resu;
	}

	public static String convertirCalendar(Calendar fecha) {
		String resu;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		String mes = mesToString((fecha.get(Calendar.MONTH)) + 1);
		int anio = fecha.get(Calendar.YEAR);
		resu = dia + " de " + mes + " de " + anio;
		return resu;
	}

	public static String convertirCalendarCorto(Calendar fecha) {
		String resu = "";
		if (fecha != null) {
			int dia = fecha.get(Calendar.DAY_OF_MONTH);
			int mes = (fecha.get(Calendar.MONTH)) + 1;
			int anio = fecha.get(Calendar.YEAR);
			String d, m;
			if(dia < 10)
				d = "0"+dia;
			else
				d = dia+"";
			if(mes < 10)
			    m = "0"+mes;
			else
				m = mes+"";
			resu = d + "/" + m + "/" + anio;
		}
		return resu;
	}

	/**
	 * metodo que retorna el calendar de un String con formato dia/mes/anio, con cualquier cantidad de digitos en cada uno, pero siempre
	 * digitos y en ese orden
	 * 
	 * @param fecha
	 * @return
	 */
	public static Calendar tomarCalendarDeString(String fecha) {
		Calendar resu = Calendar.getInstance();
		int dia, mes, anio;
		String[] partes = fecha.split("/");
		dia = Integer.parseInt(partes[0]);
		mes = Integer.parseInt(partes[1]);
		anio = Integer.parseInt(partes[2]);
		// hay dos digitos para el anio?
		if (anio < 100)
			anio = anio + 2000;
		resu.set(anio, mes, dia);
		return resu;
	}

	/**
	 * retorna true si la fecah ingresada como parametro es valida
	 * 
	 * @param fecha
	 * @return
	 */
	public static boolean validarFecha(String fecha) {

		if (fecha == null)
			return false;

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // dia-mes-anio

		if (fecha.trim().length() != dateFormat.toPattern().length())
			return false;

		dateFormat.setLenient(false);

		try {
			dateFormat.parse(fecha.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	/**
	 * convierte un calendar en String con fecha y hora en formato dia/mes/anio h:m
	 * 
	 * @param fecha
	 * @return
	 */
	public static String convertirFechaYHora(Calendar fecha) {
		// TODO Auto-generated method stub
		String resu;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		int mes = (fecha.get(Calendar.MONTH)) + 1;
		int anio = fecha.get(Calendar.YEAR);
		int minutos = fecha.get(Calendar.MINUTE);
		String m, h;
		int hora = fecha.get(Calendar.HOUR);
		if (minutos < 10)
			m = "0" + minutos;
		else
			m = minutos + "";
		if (hora < 10)
			h = "0" + hora;
		else
			h = hora + "";
		resu = dia + "/" + mes + "/" + anio + "  " + h + ":" + m;
		return resu;
	}

	// otro para cedulas
	public static String darFormatoDeCedula(int cedula) {
		DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
		simbolo.setDecimalSeparator('-');
		simbolo.setGroupingSeparator('.');
		DecimalFormat formateador = new DecimalFormat("#.###.###-#", simbolo);
		return formateador.format(cedula);
	}

	public static Calendar convertirStringACalendar(String feinicio) {
		Calendar resu = Calendar.getInstance();
		String[] partes = feinicio.split("/");

		int dia = Integer.parseInt(partes[0]);
		int mes = Integer.parseInt(partes[1]) - 1;
		int anio = Integer.parseInt(partes[2]);
		resu.set(anio, mes, dia);
		return resu;
	}

	public static boolean mismaFecha(Calendar fechaInicio, Calendar fecha) {
		boolean resu = false;
		int diaI, mesI, anioI;
		int dia, mes, anio;
		diaI = fechaInicio.get(Calendar.DAY_OF_MONTH);
		mesI = fechaInicio.get(Calendar.MONTH);
		anioI = fechaInicio.get(Calendar.YEAR);
		dia = fecha.get(Calendar.DAY_OF_MONTH);
		mes = fecha.get(Calendar.MONTH);
		anio = fecha.get(Calendar.YEAR);
		if (diaI == dia && mesI == mes && anioI == anio)
			resu = true;
		return resu;
	}

	public static String redondearADosDecimales(double val) {
		// return String.format("%.2f", val);
		BigDecimal a = new BigDecimal(val);
		BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		// System.out.println("redondeando "+val +" a "+roundOff);
		return roundOff.toString();

	}
}
