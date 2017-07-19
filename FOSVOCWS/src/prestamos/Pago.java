package prestamos;

import java.io.Serializable;
import java.util.Calendar;

//
@SuppressWarnings("serial")
public class Pago implements Serializable {

	//
	private int id;

	//
	private Calendar fecha;

	//
	private double monto;

	private Deuda deuda;

	/**
	 * @param id
	 * @param fecha
	 * @param monto
	 * @param deuda
	 */
	public Pago(int id, Calendar fecha, double monto, Deuda deuda) {
		this.id = id;
		this.fecha = fecha;
		this.monto = monto;
		this.deuda = deuda;
	}

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
	 * @return the monto
	 */
	public double getMonto() {
		return monto;
	}

	/**
	 * @param monto
	 *            the monto to set
	 */
	public void setMonto(double monto) {
		this.monto = monto;
	}

	/**
	 * @return the deuda
	 */
	public Deuda getDeuda() {
		return deuda;
	}

	/**
	 * @param deuda
	 *            the deuda to set
	 */
	public void setDeuda(Deuda deuda) {
		this.deuda = deuda;
	}

}