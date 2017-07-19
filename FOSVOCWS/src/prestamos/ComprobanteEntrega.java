package prestamos;

import java.io.Serializable;
import java.util.Calendar;

//
@SuppressWarnings("serial")
public class ComprobanteEntrega implements Serializable {

	//
	private Calendar fecha;

	//
	private int idEmpresa;

	//
	private double monto;
	private OrdenDeCompra orden;
	private OrdenDeCompraPorIVA ordencomp;

	/**
	 * @param fecha
	 * @param idEmpresa
	 * @param monto
	 * @param orden
	 */
	public ComprobanteEntrega(Calendar fecha, int idEmpresa, double monto, OrdenDeCompra orden) {
		this.fecha = fecha;
		this.idEmpresa = idEmpresa;
		this.monto = monto;
		this.orden = orden;
	}

	public ComprobanteEntrega(Calendar fecha, int idEmpresa, double monto, OrdenDeCompraPorIVA orden) {
		this.fecha = fecha;
		this.idEmpresa = idEmpresa;
		this.monto = monto;
		this.ordencomp = orden;
	}

	/**
	 * @return the ordencomp
	 */
	public OrdenDeCompraPorIVA getOrdencomp() {
		return ordencomp;
	}

	/**
	 * @param ordencomp
	 *            the ordencomp to set
	 */
	public void setOrdencomp(OrdenDeCompraPorIVA ordencomp) {
		this.ordencomp = ordencomp;
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
	 * @return the idEmpresa
	 */
	public int getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param idEmpresa
	 *            the idEmpresa to set
	 */
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * @return the monto
	 */
	public double getMonto() {
		return monto;
	}

	/**
	 * @param monto
	 *            the monto to set
	 */
	public void setMonto(double monto) {
		this.monto = monto;
	}

	/**
	 * @return the orden
	 */
	public OrdenDeCompra getOrden() {
		return orden;
	}

	/**
	 * @param orden
	 *            the orden to set
	 */
	public void setOrden(OrdenDeCompra orden) {
		this.orden = orden;
	}

}