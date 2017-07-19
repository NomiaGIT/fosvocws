package utilitarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Esta clase provee la funcionalidad de convertir un numero representado en digitos a una representacion en letras. Mejorado para leer
 * centavos
 * 
 * @author Camilo Nova
 * @version 1.0
 */
public abstract class NumberToLetterConverter {

	private static final String[] UNIDADES = { "", "UN ", "DOS ", "TRES ", "CUATRO ", "CINCO ", "SEIS ", "SIETE ", "OCHO ", "NUEVE ", "DIEZ ", "ONCE ", "DOCE ", "TRECE ",
			"CATORCE ", "QUINCE ", "DIECISEIS", "DIECISIETE", "DIECIOCHO", "DIECINUEVE", "VEINTE" };

	private static final String[] DECENAS = { "VENTI", "TREINTA ", "CUARENTA ", "CINCUENTA ", "SESENTA ", "SETENTA ", "OCHENTA ", "NOVENTA ", "CIEN " };

	private static final String[] CENTENAS = { "CIENTO ", "DOSCIENTOS ", "TRESCIENTOS ", "CUATROCIENTOS ", "QUINIENTOS ", "SEISCIENTOS ", "SETECIENTOS ", "OCHOCIENTOS ",
			"NOVECIENTOS " };

	/**
	 * Convierte a letras un numero de la forma $123,456.32 (StoreMath)
	 * <p>
	 * Creation date 5/06/2006 - 10:20:52 AM
	 * 
	 * @param number
	 *            Numero en representacion texto
	 * @return Numero en letras
	 * @since 1.0
	 */
	public static String convertNumberToLetter(String number) {
		return convertNumberToLetter(number + "");
	}

	/**
	 * Convierte un numero en representacion numerica a uno en representacion de texto. El numero es valido si esta entre 0 y 999'999.999
	 * <p>
	 * Creation date 3/05/2006 - 05:37:47 PM
	 * 
	 * @param number
	 *            Numero a convertir
	 * @return Numero convertido a texto
	 * @throws NumberFormatException
	 *             Si el numero esta fuera del rango
	 * @since 1.0
	 */
	public static String convertNumberToLetter(double number) throws NumberFormatException {
		String converted = new String();

		// Validamos que sea un numero legal
		double doubleNumber = StrictMath.round(number);
		if (doubleNumber > 999999999)
			throw new NumberFormatException("El numero es mayor de 999'999.999, " + "no es posible convertirlo");

		String splitNumber[] = String.valueOf(doubleNumber).replace('.', '#').split("#");

		// Descompone el trio de millones - ¡SGT!
		int millon = Integer
				.parseInt(String.valueOf(getDigitAt(splitNumber[0], 8)) + String.valueOf(getDigitAt(splitNumber[0], 7)) + String.valueOf(getDigitAt(splitNumber[0], 6)));
		if (millon == 1)
			converted = "UN MILLON ";
		if (millon > 1)
			converted = convertNumber(String.valueOf(millon)) + "MILLONES ";

		// Descompone el trio de miles - ¡SGT!
		int miles = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0], 5)) + String.valueOf(getDigitAt(splitNumber[0], 4)) + String.valueOf(getDigitAt(splitNumber[0], 3)));
		if (miles == 1)
			converted += " MIL ";
		if (miles > 1)
			converted += convertNumber(String.valueOf(miles)) + " MIL ";

		// Descompone el ultimo trio de unidades - ¡SGT!
		int cientos = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0], 2)) + String.valueOf(getDigitAt(splitNumber[0], 1))
				+ String.valueOf(getDigitAt(splitNumber[0], 0)));
		if (cientos == 1)
			converted += "UN";

		if (millon + miles + cientos == 0)
			converted += "CERO";
		if (cientos > 1)
			converted += convertNumber(String.valueOf(cientos));

		converted += " ";

		// Descompone los centavos - Camilo
		int centavos = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[1], 2)) + String.valueOf(getDigitAt(splitNumber[1], 1))
				+ String.valueOf(getDigitAt(splitNumber[1], 0)));
		if (centavos == 1)
			converted += " CON UN CENTAVO";
		if (centavos > 1)
			converted += " CON " + convertNumber(String.valueOf(centavos)) + "CENTAVOS";

		if (converted.endsWith(" "))
			converted = converted.substring(0, converted.length() - 1);

		return converted;
	}

	/**
	 * Convierte los trios de numeros que componen las unidades, las decenas y las centenas del numero.
	 * <p>
	 * Creation date 3/05/2006 - 05:33:40 PM
	 * 
	 * @param number
	 *            Numero a convetir en digitos
	 * @return Numero convertido en letras
	 * @since 1.0
	 */
	private static String convertNumber(String number) {
		if (number.length() > 3)
			throw new NumberFormatException("La longitud maxima debe ser 3 digitos");

		String output = new String();
		if (getDigitAt(number, 2) != 0)
			output = CENTENAS[getDigitAt(number, 2) - 1];

		int k = Integer.parseInt(String.valueOf(getDigitAt(number, 1)) + String.valueOf(getDigitAt(number, 0)));

		if (k <= 20)
			output += UNIDADES[k];
		else {
			if (k > 30 && getDigitAt(number, 0) != 0)
				output += DECENAS[getDigitAt(number, 1) - 2] + "Y " + UNIDADES[getDigitAt(number, 0)];
			else
				output += DECENAS[getDigitAt(number, 1) - 2] + UNIDADES[getDigitAt(number, 0)];
		}

		// Caso especial con el 100
		if (getDigitAt(number, 2) == 1 && k == 0)
			output = "CIEN";

		return output;
	}

	/**
	 * Retorna el digito numerico en la posicion indicada de derecha a izquierda
	 * <p>
	 * Creation date 3/05/2006 - 05:26:03 PM
	 * 
	 * @param origin
	 *            Cadena en la cual se busca el digito
	 * @param position
	 *            Posicion de derecha a izquierda a retornar
	 * @return Digito ubicado en la posicion indicada
	 * @since 1.0
	 */
	private static int getDigitAt(String origin, int position) {
		if (origin.length() > position && position >= 0)
			return origin.charAt(origin.length() - position - 1) - 48;
		return 0;
	}

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

	public static String getStringDouble(double valor) {
		String resu = "";
		String d = String.format("%.2f", valor);
		int i = 0;
		char c = d.charAt(i);
		while ((c != '.' && c != ',') && i < d.length())// antes de llegar al
														// punto
		{
			resu = resu + c;
			i++;
			c = d.charAt(i);
		}
		if (c == '.' || c == ',') {
			// lo salteo
			i++;//
			resu = resu + d.charAt(i);
			resu = resu + d.charAt(i + 1);
		}

		while (resu.length() < 10) {
			resu = "0" + resu;
		}
		return resu;
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
		String resu;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		int mes = (fecha.get(Calendar.MONTH)) + 1;
		int anio = fecha.get(Calendar.YEAR);
		resu = dia + "/" + mes + "/" + anio;
		return resu;
	}

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
}