package prestamos;

import java.io.Serializable;

//
@SuppressWarnings("serial")
public class INTERES implements Serializable {

	private double monto;
	private String descripcion;

	/**
	 * @param monto
	 * @param descripcion
	 */
	public INTERES(double monto, String descripcion) {
		this.monto = monto;
		this.descripcion = descripcion;
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
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}