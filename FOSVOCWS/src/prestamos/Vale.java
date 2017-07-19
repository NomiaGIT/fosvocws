package prestamos;

import java.io.Serializable;
import java.util.Calendar;

//
@SuppressWarnings("serial")
public class Vale implements Serializable {

	//
	private int id;

	//
	private Calendar fechaVencimiento;

	//
	// private double montoEn$ ;

	/**
	 * @param id
	 * @param fechaVencimiento
	 * @param montoUI
	 */
	public Vale(int id, Calendar fechaVencimiento, double montoUI) {
		this.id = id;
		this.fechaVencimiento = fechaVencimiento;
		this.montoUI = montoUI;
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
	 * @return the fechaVencimiento
	 */
	public Calendar getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento
	 *            the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Calendar fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
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

	//
	private double montoUI;

}