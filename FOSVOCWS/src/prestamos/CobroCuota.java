package prestamos;

import java.io.Serializable;
import java.util.Calendar;

//
@SuppressWarnings("serial")
public class CobroCuota implements Serializable {

	//
	private Calendar fechaPago;

	//
	private boolean enFecha;

	private Cuota cuota;
	private INTERES interes;

	/**
	 * @return the interes
	 */
	public INTERES getInteres() {
		return interes;
	}

	/**
	 * @param interes
	 *            the interes to set
	 */
	public void setInteres(INTERES interes) {
		this.interes = interes;
	}

	/**
	 * @param fechaPago
	 * @param enFecha
	 * @param cuota
	 */
	public CobroCuota(Calendar fechaPago, boolean enFecha, Cuota cuota) {
		this.fechaPago = fechaPago;
		this.enFecha = enFecha;
		this.cuota = cuota;
		this.interes = null;
	}

	/**
	 * @param fechaPago
	 * @param enFecha
	 * @param cuota
	 * @param interes
	 */
	public CobroCuota(Calendar fechaPago, boolean enFecha, Cuota cuota, INTERES interes) {
		this.fechaPago = fechaPago;
		this.enFecha = enFecha;
		this.cuota = cuota;
		this.interes = interes;
	}

	/**
	 * @return the fechaPago
	 */
	public Calendar getFechaPago() {
		return fechaPago;
	}

	/**
	 * @param fechaPago
	 *            the fechaPago to set
	 */
	public void setFechaPago(Calendar fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * @return the enFecha
	 */
	public boolean isEnFecha() {
		return enFecha;
	}

	/**
	 * @param enFecha
	 *            the enFecha to set
	 */
	public void setEnFecha(boolean enFecha) {
		this.enFecha = enFecha;
	}

	/**
	 * @return the cuota
	 */
	public Cuota getCuota() {
		return cuota;
	}

	/**
	 * @param cuota
	 *            the cuota to set
	 */
	public void setCuota(Cuota cuota) {
		this.cuota = cuota;
	}

}