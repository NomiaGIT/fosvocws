package datatypes;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DataListarAdjudicaciones implements Serializable {
	private int numeroSolicitud;
	private String nombre;
	private String apellido;
	private int documento;
	private double montoSolicitado;
	private String fechaAprobacion;
	private String nombreBarraca;
	private int montoEnPesos;
	private String telefono;

	/**
	 * @param numeroSolicitud
	 * @param nombre
	 * @param apellido
	 * @param documento
	 * @param montoSolicitado
	 * @param fechaAprobacion
	 * @param nombreBarraca
	 */
	public DataListarAdjudicaciones(int numeroSolicitud, String nombre, String apellido, int documento, double montoSolicitado, String fechaAprobacion, String nombreBarraca,
			int montoEnPesos) {
		this.numeroSolicitud = numeroSolicitud;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
		this.montoSolicitado = montoSolicitado;
		this.fechaAprobacion = fechaAprobacion;
		this.nombreBarraca = nombreBarraca;
		this.montoEnPesos = montoEnPesos;
	}

	public DataListarAdjudicaciones(int numeroSolicitud, String nombre, String apellido, int documento, double montoSolicitado, String fechaAprobacion, String nombreBarraca,
			int montoEnPesos, String telefono) {
		this.numeroSolicitud = numeroSolicitud;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
		this.montoSolicitado = montoSolicitado;
		this.fechaAprobacion = fechaAprobacion;
		this.nombreBarraca = nombreBarraca;
		this.montoEnPesos = montoEnPesos;
		this.telefono = telefono;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the montoEnPesos
	 */
	public int getMontoEnPesos() {
		return montoEnPesos;
	}

	/**
	 * @param montoEnPesos
	 *            the montoEnPesos to set
	 */
	public void setMontoEnPesos(int montoEnPesos) {
		this.montoEnPesos = montoEnPesos;
	}

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
	 * @return the fechaAprobacion
	 */
	public String getFechaAprobacion() {
		return fechaAprobacion;
	}

	/**
	 * @param fechaAprobacion
	 *            the fechaAprobacion to set
	 */
	public void setFechaAprobacion(String fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	/**
	 * @return the nombreBarraca
	 */
	public String getNombreBarraca() {
		return nombreBarraca;
	}

	/**
	 * @param nombreBarraca
	 *            the nombreBarraca to set
	 */
	public void setNombreBarraca(String nombreBarraca) {
		this.nombreBarraca = nombreBarraca;
	}
}
