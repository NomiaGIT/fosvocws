package prestamos;

import java.io.Serializable;
import java.util.Calendar;

@SuppressWarnings("serial")
public class OrdenDeCompraPorIVA implements Serializable {
	private int id;
	private Calendar fechaEmision;
	private int monto;
	private int idcontrato;
	private String nroFormulario;
	private int idProveedor;

	/**
	 * @param id
	 * @param fechaEmision
	 * @param monto
	 * @param idcontrato
	 * @param nroFormulario
	 * @param idProveedor
	 */
	public OrdenDeCompraPorIVA(int id, Calendar fechaEmision, int monto, int idcontrato, String nroFormulario, int idProveedor) {
		this.id = id;
		this.fechaEmision = fechaEmision;
		this.monto = monto;
		this.idcontrato = idcontrato;
		this.nroFormulario = nroFormulario;
		this.idProveedor = idProveedor;
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
	 * @return the monto
	 */
	public int getMonto() {
		return monto;
	}

	/**
	 * @param monto
	 *            the monto to set
	 */
	public void setMonto(int monto) {
		this.monto = monto;
	}

	/**
	 * @return the idcontrato
	 */
	public int getIdcontrato() {
		return idcontrato;
	}

	/**
	 * @param idcontrato
	 *            the idcontrato to set
	 */
	public void setIdcontrato(int idcontrato) {
		this.idcontrato = idcontrato;
	}

	/**
	 * @return the nroFormulario
	 */
	public String getNroFormulario() {
		return nroFormulario;
	}

	/**
	 * @param nroFormulario
	 *            the nroFormulario to set
	 */
	public void setNroFormulario(String nroFormulario) {
		this.nroFormulario = nroFormulario;
	}

	/**
	 * @return the idProveedor
	 */
	public int getIdProveedor() {
		return idProveedor;
	}

	/**
	 * @param idProveedor
	 *            the idProveedor to set
	 */
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

}
