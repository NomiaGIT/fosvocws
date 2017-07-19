package datatypes;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DataListarSolicitudes implements Serializable {
	private int numeroSolicitud;
	private int documento;
	private String apellido;
	private String nombre;
	private String departamento;
	private int puntaje;
	private double montoSolicitado;
	private String estado;

	/**
	 * @return the numeroSolicitud
	 */
	public int getNumeroSolicitud() {
		return numeroSolicitud;
	}

	/**
	 * @param numeroSolicitud
	 *            the numeroSolicitud to set
	 */
	public void setNumeroSolicitud(int numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}

	/**
	 * @return the documento
	 */
	public int getDocumento() {
		return documento;
	}

	/**
	 * @param documento
	 *            the documento to set
	 */
	public void setDocumento(int documento) {
		this.documento = documento;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido
	 *            the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento
	 *            the departamento to set
	 */
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	/**
	 * @return the puntaje
	 */
	public int getPuntaje() {
		return puntaje;
	}

	/**
	 * @param puntaje
	 *            the puntaje to set
	 */
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	/**
	 * @return the montoSolicitado
	 */
	public double getMontoSolicitado() {
		return montoSolicitado;
	}

	/**
	 * @param montoSolicitado
	 *            the montoSolicitado to set
	 */
	public void setMontoSolicitado(double montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}

	/**
	 * @param numeroSolicitud
	 * @param documento
	 * @param apellido
	 * @param nombre
	 * @param departamento
	 * @param puntaje
	 * @param montoSolicitado
	 */
	public DataListarSolicitudes(int numeroSolicitud, int documento, String apellido, String nombre, String departamento, int puntaje, double montoSolicitado,
			String estado) {
		this.numeroSolicitud = numeroSolicitud;
		this.documento = documento;
		this.apellido = apellido;
		this.nombre = nombre;
		this.departamento = departamento;
		this.puntaje = puntaje;
		this.montoSolicitado = (int)montoSolicitado;
		this.estado = estado;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
