package prestamos;

import java.io.Serializable;

//
@SuppressWarnings("serial")
public class SeleccionPorSorteo implements Serializable {

	//
	private int cantSorteados;

	//
	private String descripcion;

	/**
	 * @param cantSorteados
	 * @param descripcion
	 */
	public SeleccionPorSorteo(int cantSorteados, String descripcion) {
		this.cantSorteados = cantSorteados;
		this.descripcion = descripcion;
	}

	/**
	 * @return the cantSorteados
	 */
	public int getCantSorteados() {
		return cantSorteados;
	}

	/**
	 * @param cantSorteados
	 *            the cantSorteados to set
	 */
	public void setCantSorteados(int cantSorteados) {
		this.cantSorteados = cantSorteados;
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