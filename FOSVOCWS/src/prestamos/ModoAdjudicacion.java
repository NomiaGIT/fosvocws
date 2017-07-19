package prestamos;

import java.io.Serializable;

//
@SuppressWarnings("serial")
public class ModoAdjudicacion implements Serializable {

	//
	private String tipo;

	/**
	 * 
	 */
	public ModoAdjudicacion() {

	}

	public ModoAdjudicacion(String tipoAdjudicacion) {
		this.tipo = tipoAdjudicacion;
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

}