package datatypes;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DataListarAportantes implements Serializable {
	private int documento;

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
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @param documento
	 * @param nombre
	 * @param apellido
	 * @param direccion
	 */
	public DataListarAportantes(int documento, String nombre, String apellido, String direccion) {
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
	}

	private String nombre;
	private String apellido;
	private String direccion;

}
