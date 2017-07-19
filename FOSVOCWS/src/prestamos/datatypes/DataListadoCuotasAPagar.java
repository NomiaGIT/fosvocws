package prestamos.datatypes;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DataListadoCuotasAPagar implements Serializable {

	private int solicitud;
	private long cedula;
	private String Apellido;
	private int numeroCuota;// el numero de cuota de ese credito
	private double monto$;
	private double montoUI;
	private String fechaVencimiento;
	private String fechaPago;
	private int idcuota;

	/**
	 * @param solicitud
	 * @param cedula
	 * @param apellido
	 * @param numeroCuota
	 * @param monto$
	 * @param montoUI
	 * @param fechaVencimiento
	 * @param fechaPago
	 */
	public DataListadoCuotasAPagar(int solicitud, long cedula, String apellido, int numeroCuota, double monto$, double montoUI, String fechaVencimiento, String fechaPago,
			int idcuota) {
		this.solicitud = solicitud;
		this.cedula = cedula;
		Apellido = apellido;
		this.numeroCuota = numeroCuota;
		this.monto$ = monto$;
		this.montoUI = montoUI;
		this.fechaVencimiento = fechaVencimiento;
		this.fechaPago = fechaPago;
		this.idcuota = idcuota;
	}

	/**
	 * @return the solicitud
	 */
	public int getSolicitud() {
		return solicitud;
	}

	/**
	 * @param solicitud
	 *            the solicitud to set
	 */
	public void setSolicitud(int solicitud) {
		this.solicitud = solicitud;
	}

	/**
	 * @return the cedula
	 */
	public long getCedula() {
		return cedula;
	}

	/**
	 * @param cedula
	 *            the cedula to set
	 */
	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return Apellido;
	}

	/**
	 * @param apellido
	 *            the apellido to set
	 */
	public void setApellido(String apellido) {
		Apellido = apellido;
	}

	/**
	 * @return the numeroCuota
	 */
	public int getNumeroCuota() {
		return numeroCuota;
	}

	/**
	 * @param numeroCuota
	 *            the numeroCuota to set
	 */
	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	/**
	 * @return the monto$
	 */
	public double getMonto$() {
		return monto$;
	}

	/**
	 * @param monto$
	 *            the monto$ to set
	 */
	public void setMonto$(double monto$) {
		this.monto$ = monto$;
	}

	/**
	 * @return the montoUI
	 */
	public double getMontoUI() {
		return montoUI;
	}

	/**
	 * @param montoUI
	 *            the montoUI to set
	 */
	public void setMontoUI(double montoUI) {
		this.montoUI = montoUI;
	}

	/**
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento
	 *            the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return the fechaPago
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * @param fechaPago
	 *            the fechaPago to set
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public int getIdcuota() {
		return idcuota;
	}

	public void setIdcuota(int idcuota) {
		this.idcuota = idcuota;
	}

}
