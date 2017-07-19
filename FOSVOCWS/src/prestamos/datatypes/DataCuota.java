package prestamos.datatypes;

import java.io.Serializable;
import java.util.Calendar;

@SuppressWarnings("serial")
public class DataCuota implements Serializable {
	private int idcuota;
	private int numerocuota;
	private double monto;
	private Calendar vencimiento;

	/**
	 * @param idcuota
	 * @param numerocuota
	 * @param monto
	 * @param vencimiento
	 */
	public DataCuota(int idcuota, int numerocuota, double monto, Calendar vencimiento) {
		this.idcuota = idcuota;
		this.numerocuota = numerocuota;
		this.monto = monto;
		this.vencimiento = vencimiento;
	}

	/**
	 * @return the idcuota
	 */
	public int getIdcuota() {
		return idcuota;
	}

	/**
	 * @param idcuota
	 *            the idcuota to set
	 */
	public void setIdcuota(int idcuota) {
		this.idcuota = idcuota;
	}

	/**
	 * @return the numerocuota
	 */
	public int getNumerocuota() {
		return numerocuota;
	}

	/**
	 * @param numerocuota
	 *            the numerocuota to set
	 */
	public void setNumerocuota(int numerocuota) {
		this.numerocuota = numerocuota;
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
	 * @return the vencimiento
	 */
	public Calendar getVencimiento() {
		return vencimiento;
	}

	/**
	 * @param vencimiento
	 *            the vencimiento to set
	 */
	public void setVencimiento(Calendar vencimiento) {
		this.vencimiento = vencimiento;
	}

}
