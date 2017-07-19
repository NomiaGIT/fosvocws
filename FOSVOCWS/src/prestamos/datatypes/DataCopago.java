package prestamos.datatypes;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DataCopago implements Serializable {
	private int idCuota;
	private int monto;
	private int idSolicitud;
	private int cedulaAportante;
	private String nombresAportante;
	private String apellidoAportante;

	/**
	 * @param idCuota
	 * @param monto
	 * @param idSolicitud
	 * @param cedulaAportante
	 * @param nombresAportante
	 * @param apellidoAportante
	 */
	public DataCopago(int idCuota, int monto, int idSolicitud, int cedulaAportante, String nombresAportante, String apellidoAportante) {
		this.idCuota = idCuota;
		this.monto = monto;
		this.idSolicitud = idSolicitud;
		this.cedulaAportante = cedulaAportante;
		this.nombresAportante = nombresAportante;
		this.apellidoAportante = apellidoAportante;
	}

	/**
	 * @return the idCuota
	 */
	public int getIdCuota() {
		return idCuota;
	}

	/**
	 * @param idCuota
	 *            the idCuota to set
	 */
	public void setIdCuota(int idCuota) {
		this.idCuota = idCuota;
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
	 * @return the idSolicitud
	 */
	public int getIdSolicitud() {
		return idSolicitud;
	}

	/**
	 * @param idSolicitud
	 *            the idSolicitud to set
	 */
	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	/**
	 * @return the cedulaAportante
	 */
	public int getCedulaAportante() {
		return cedulaAportante;
	}

	/**
	 * @param cedulaAportante
	 *            the cedulaAportante to set
	 */
	public void setCedulaAportante(int cedulaAportante) {
		this.cedulaAportante = cedulaAportante;
	}

	/**
	 * @return the nombresAportante
	 */
	public String getNombresAportante() {
		return nombresAportante;
	}

	/**
	 * @param nombresAportante
	 *            the nombresAportante to set
	 */
	public void setNombresAportante(String nombresAportante) {
		this.nombresAportante = nombresAportante;
	}

	/**
	 * @return the apellidoAportante
	 */
	public String getApellidoAportante() {
		return apellidoAportante;
	}

	/**
	 * @param apellidoAportante
	 *            the apellidoAportante to set
	 */
	public void setApellidoAportante(String apellidoAportante) {
		this.apellidoAportante = apellidoAportante;
	}

}
