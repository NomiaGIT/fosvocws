package prestamos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RegistroContable implements Serializable {

	/**
	 * @param id
	 * @param tipo
	 * @param cuenta
	 * @param cobro
	 * @param pago
	 */
	public RegistroContable(int id, String tipo, String cuenta, CobroCuota cobro, Pago pago) {
		this.id = id;
		this.tipo = tipo;
		this.cuenta = cuenta;
		this.cobro = cobro;
		this.pago = pago;
	}

	private int id;
	private String tipo;
	private String cuenta;
	private CobroCuota cobro;
	private Pago pago;

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
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * @return the cobro
	 */
	public CobroCuota getCobro() {
		return cobro;
	}

	/**
	 * @param cobro
	 *            the cobro to set
	 */
	public void setCobro(CobroCuota cobro) {
		this.cobro = cobro;
	}

	/**
	 * @return the pago
	 */
	public Pago getPago() {
		return pago;
	}

	/**
	 * @param pago
	 *            the pago to set
	 */
	public void setPago(Pago pago) {
		this.pago = pago;
	}

}
