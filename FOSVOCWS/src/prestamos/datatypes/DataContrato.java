package prestamos.datatypes;

import java.util.Calendar;

public class DataContrato {
	private int id;
	private Calendar fecha;
	private int idsolicitud;

	/**
	 * @param id
	 * @param fecha
	 * @param idsolicitud
	 */
	public DataContrato(int id, Calendar fecha, int idsolicitud) {
		this.id = id;
		this.fecha = fecha;
		this.idsolicitud = idsolicitud;
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
	 * @return the idsolicitud
	 */
	public int getIdsolicitud() {
		return idsolicitud;
	}

	/**
	 * @param idsolicitud
	 *            the idsolicitud to set
	 */
	public void setIdsolicitud(int idsolicitud) {
		this.idsolicitud = idsolicitud;
	}

}
