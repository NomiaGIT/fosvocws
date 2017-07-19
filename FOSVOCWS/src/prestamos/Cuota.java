package prestamos;

import java.io.Serializable;
import java.util.Calendar;

//
@SuppressWarnings("serial")
public class Cuota implements Serializable {

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
	 * @return the mes
	 */
	public Calendar getFechaVencimiento() {
		return fechavencimiento;
	}

	/**
	 * @param mes
	 *            the mes to set
	 */
	public void setMes(Calendar fecha) {
		this.fechavencimiento = fecha;
	}

	/**
	 * @return the montoUI
	 */
	public double getMontoUI() {
		return montoUI;
	}

	/**
	 * @param montoUI
	 *            the montoUI to set
	 */
	public void setMontoUI(double montoUI) {
		this.montoUI = montoUI;
	}

	/**
	 * @param id
	 * @param mes
	 * @param montoUI
	 * 
	 * @param monto$
	 */
	public Cuota(int id, Calendar fecha, double montoUI) {
		this.id = id;
		this.fechavencimiento = fecha;
		this.montoUI = montoUI;

	}

	//
	private int id;

	//
	private Calendar fechavencimiento;

	//
	private double montoUI;

}