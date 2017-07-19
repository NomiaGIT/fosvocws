package prestamos;

import java.io.Serializable;
import java.util.Calendar;

//
@SuppressWarnings("serial")
public class OrdenDeCompra implements Serializable {

	//
	private int id;

	//
	private Calendar fechaEmision;

	//
	private double montoPesos;

	//
	private String numeroFormulario;

	/**
	 * @param id
	 * @param fechaEmision
	 * @param montoPesos
	 * @param numeroFormulario
	 * @param fecha
	 */
	public OrdenDeCompra(int id, Calendar fechaEmision, double montoPesos, String numeroFormulario) {
		this.id = id;
		this.fechaEmision = fechaEmision;
		this.montoPesos = montoPesos;
		this.numeroFormulario = numeroFormulario;

	}

	/**
	 * @param fechaEmision
	 * @param montoPesos
	 * @param numeroFormulario
	 * @param fecha
	 */
	public OrdenDeCompra(Calendar fechaEmision, double montoPesos, String numeroFormulario) {
		this.fechaEmision = fechaEmision;
		this.montoPesos = montoPesos;
		this.numeroFormulario = numeroFormulario;

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
	 * @return the fechaEmision
	 */
	public Calendar getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision
	 *            the fechaEmision to set
	 */
	public void setFechaEmision(Calendar fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the montoPesos
	 */
	public double getMontoPesos() {
		return montoPesos;
	}

	/**
	 * @param montoPesos
	 *            the montoPesos to set
	 */
	public void setMontoPesos(double montoPesos) {
		this.montoPesos = montoPesos;
	}

	/**
	 * @return the numeroFormulario
	 */
	public String getNumeroFormulario() {
		return numeroFormulario;
	}

	/**
	 * @param numeroFormulario
	 *            the numeroFormulario to set
	 */
	public void setNumeroFormulario(String numeroFormulario) {
		this.numeroFormulario = numeroFormulario;
	}

}