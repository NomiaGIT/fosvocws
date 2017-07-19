package prestamos.datatypes;

import java.io.Serializable;
import java.util.Calendar;

@SuppressWarnings("serial")
public class DataCobro implements Serializable {
	private Calendar fecha;
	private int monto;
	private String transaccion;
	private int idCuota;

	/**
	 * @param fecha
	 * @param monto
	 * @param transaccion
	 * @param idCuota
	 */
	public DataCobro(Calendar fecha, int monto, String transaccion, int idCuota) {
		this.fecha = fecha;
		this.monto = monto;
		this.transaccion = transaccion;
		this.idCuota = idCuota;
	}

	/**
	 * @return the fecha
	 */
	public Calendar getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
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
	 * @return the transaccion
	 */
	public String getTransaccion() {
		return transaccion;
	}

	/**
	 * @param transaccion
	 *            the transaccion to set
	 */
	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
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

}
