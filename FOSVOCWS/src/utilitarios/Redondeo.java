package utilitarios;

public class Redondeo {
	public static double redondear(double numero) {
		return Math.rint(numero * 100) / 100;

	}

	public static int sacarDecimales(double numero) {
		int resu = (int) Math.round(numero);
		return resu;
	}
}
