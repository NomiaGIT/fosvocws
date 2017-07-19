package prestamos;

import java.io.Serializable;

//
@SuppressWarnings("serial")
public class SelecciónPorPuntaje implements Serializable {

	//
	private String descripcion;

	//
	private int cantBeneficiarios;

	/**
	 * @param descripcion
	 * @param cantBeneficiarios
	 */
	public SelecciónPorPuntaje(String descripcion, int cantBeneficiarios) {
		this.descripcion = descripcion;
		this.cantBeneficiarios = cantBeneficiarios;
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
	 * @return the cantBeneficiarios
	 */
	public int getCantBeneficiarios() {
		return cantBeneficiarios;
	}

	/**
	 * @param cantBeneficiarios
	 *            the cantBeneficiarios to set
	 */
	public void setCantBeneficiarios(int cantBeneficiarios) {
		this.cantBeneficiarios = cantBeneficiarios;
	}

}