package prestamos;

import java.io.Serializable;
import java.util.Calendar;

//
@SuppressWarnings("serial")
public class Cheque extends MedioDePago implements Serializable {

	//
	private int id;

	//
	private String emisor;

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
	 * @return the emisor
	 */
	public String getEmisor() {
		return emisor;
	}

	/**
	 * @param emisor
	 *            the emisor to set
	 */
	public void setEmisor(String emisor) {
		this.emisor = emisor;
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
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * @param id
	 * @param emisor
	 * @param monto
	 * @param fecha
	 * @param moneda
	 */
	public Cheque(int id, String emisor, double monto, Calendar fecha, String moneda) {
		super();
		this.id = id;
		this.emisor = emisor;
		this.monto = monto;
		this.fecha = fecha;
		this.moneda = moneda;
	}

	//
	private double monto;

	//
	private Calendar fecha;

	//
	private String moneda;

}