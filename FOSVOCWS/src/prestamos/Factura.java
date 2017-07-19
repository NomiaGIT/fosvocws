package prestamos;

import java.util.Calendar;

public class Factura {
	private int id;
	private int idDeuda;
	private String numeroFactura;
	private Calendar fechaFactura;

	/**
	 * @param id
	 * @param idDeuda
	 * @param numeroFactura
	 */
	public Factura(int id, int idDeuda, String numeroFactura, Calendar fechaFactura) {
		this.id = id;
		this.idDeuda = idDeuda;
		this.numeroFactura = numeroFactura;
		this.fechaFactura = fechaFactura;
	}

	/**
	 * @return the fechaFactura
	 */
	public Calendar getFechaFactura() {
		return fechaFactura;
	}

	/**
	 * @param fechaFactura
	 *            the fechaFactura to set
	 */
	public void setFechaFactura(Calendar fechaFactura) {
		this.fechaFactura = fechaFactura;
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
	 * @return the idDeuda
	 */
	public int getIdDeuda() {
		return idDeuda;
	}

	/**
	 * @param idDeuda
	 *            the idDeuda to set
	 */
	public void setIdDeuda(int idDeuda) {
		this.idDeuda = idDeuda;
	}

	/**
	 * @return the numeroFactura
	 */
	public String getNumeroFactura() {
		return numeroFactura;
	}

	/**
	 * @param numeroFactura
	 *            the numeroFactura to set
	 */
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	/**
	 * @param idDeuda
	 * @param numeroFactura
	 */
	public Factura(int idDeuda, String numeroFactura, Calendar fechaFactura) {
		this.idDeuda = idDeuda;
		this.numeroFactura = numeroFactura;
		this.fechaFactura = fechaFactura;
		this.id = 0;
	}

}
