package prestamos;

import java.io.Serializable;

//
@SuppressWarnings("serial")
public class MedioDePago implements Serializable {

	//
	private int id;

	//
	private String descripcion;

	//
	private String tipo;

	/**
	 * @param id
	 * @param descripcion
	 * @param tipo
	 */
	public MedioDePago(int id, String descripcion, String tipo) {
		this.id = id;
		this.descripcion = descripcion;
		this.tipo = tipo;
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

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * 
	 */
	public MedioDePago() {
	}

}