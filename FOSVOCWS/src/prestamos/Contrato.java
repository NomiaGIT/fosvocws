package prestamos;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Vector;

//
@SuppressWarnings("serial")
public class Contrato implements Serializable {

	/**
	 * @param id
	 * @param fecha
	 * @param cantCuotasTotal
	 * @param mesInicio
	 * @param anioInicio
	 * @param credito
	 * @param vale
	 * @param ordenCompra
	 */
	public Contrato(int id, Calendar fecha, int cantCuotasTotal, int mesInicio, int anioInicio, Credito credito, Vale vale, OrdenDeCompra ordenCompra) {
		this.id = id;
		this.fecha = fecha;
		this.cantCuotasTotal = cantCuotasTotal;
		this.mesInicio = mesInicio;
		this.anioInicio = anioInicio;
		this.credito = credito;
		this.vale = vale;
		this.ordenCompra = ordenCompra;
	}

	//
	private int id;

	//
	private Calendar fecha;

	//
	private int cantCuotasTotal;

	//
	private int mesInicio;

	//
	private int anioInicio;

	//
	private Credito credito;

	//
	private Vale vale;

	//
	private OrdenDeCompra ordenCompra;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the fecha
	 */
	public Calendar getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the cantCuotasTotal
	 */
	public int getCantCuotasTotal() {
		return cantCuotasTotal;
	}

	/**
	 * @param cantCuotasTotal
	 *            the cantCuotasTotal to set
	 */
	public void setCantCuotasTotal(int cantCuotasTotal) {
		this.cantCuotasTotal = cantCuotasTotal;
	}

	/**
	 * @return the mesInicio
	 */
	public int getMesInicio() {
		return mesInicio;
	}

	/**
	 * @param mesInicio
	 *            the mesInicio to set
	 */
	public void setMesInicio(int mesInicio) {
		this.mesInicio = mesInicio;
	}

	/**
	 * @return the anioInicio
	 */
	public int getAnioInicio() {
		return anioInicio;
	}

	/**
	 * @param anioInicio
	 *            the anioInicio to set
	 */
	public void setAnioInicio(int anioInicio) {
		this.anioInicio = anioInicio;
	}

	/**
	 * @return the credito
	 */
	public Credito getCredito() {
		return credito;
	}

	/**
	 * @param credito
	 *            the credito to set
	 */
	public void setCredito(Credito credito) {
		this.credito = credito;
	}

	/**
	 * @return the vale
	 */
	public Vale getVale() {
		return vale;
	}

	/**
	 * @param vale
	 *            the vale to set
	 */
	public void setVale(Vale vale) {
		this.vale = vale;
	}

	/**
	 * @return the ordenCompra
	 */
	public OrdenDeCompra getOrdenCompra() {
		return ordenCompra;
	}

	/**
	 * @param ordenCompra
	 *            the ordenCompra to set
	 */
	public void setOrdenCompra(OrdenDeCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public double getMontoActivo(int mesActual, int anioActual) {
		double resu = 0;
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(anioActual, mesActual, 1);// seteo una fecha como el
													// primer dia del mes actual
		fechaActual.add(Calendar.MONTH, 12);// fijo la fecha 12 meses despues de
											// la fecha actual
		// System.out.println("la fecha actual es "+
		// fechaActual.get(Calendar.MONTH)+" / "+fechaActual.get(Calendar.YEAR));
		Credito cred = this.getCredito();
		Vector<Cuota> cuotas = cred.getCuotas();
		for (Cuota cuota : cuotas) {
			Calendar fechaVenc = cuota.getFechaVencimiento();// tomo l;a fecha
																// de
																// vencimiento
																// de la cuota

			// si la fecha de vencimiento de la cuota es anterior a la fecha
			// definida
			if (fechaVenc.before(fechaActual)) {
				resu = resu + cuota.getMontoUI();

			}
		}
		return resu;
	}

	public double getMontoNoActivo(int mesActual, int anioActual) {
		double resu = 0;
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(anioActual, mesActual, 1);// seteo una fecha como el
													// primer dia del mes actual
		fechaActual.add(Calendar.MONTH, 12);// fijo la fecha 12 meses despues de
											// la fecha actual
		// System.out.println("la fecha actual es "+
		// fechaActual.get(Calendar.MONTH)+" / "+fechaActual.get(Calendar.YEAR));

		Credito cred = this.getCredito();
		Vector<Cuota> cuotas = cred.getCuotas();
		for (Cuota cuota : cuotas) {
			Calendar fechaVenc = Calendar.getInstance();
			fechaVenc.setTime(cuota.getFechaVencimiento().getTime());// tomo la
																		// fecha
																		// de
																		// vencimiento
																		// de la
																		// cuota
			// si la fecha de vencimiento de la cuota es posterior a la fecha
			// definida
			if (fechaVenc.after(fechaActual)) {
				resu = resu + cuota.getMontoUI();
			}

		}
		return resu;
	}

}