package prestamos;

import java.util.Calendar;

public class FacturaConIva extends Factura {

	private double monto, iva;

	public FacturaConIva(int id, int idDeuda, String numeroFactura, Calendar fechaFactura, double monto, double iva) {
		super(id, idDeuda, numeroFactura, fechaFactura);
		this.monto = monto;
		this.iva = iva;
	}

	public FacturaConIva(Factura f, double monto, double iva) {
		super(f.getId(), f.getIdDeuda(), f.getNumeroFactura(), f.getFechaFactura());
		this.monto = monto;
		this.iva = iva;
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
	 * @return the iva
	 */
	public double getIva() {
		return iva;
	}

	/**
	 * @param iva
	 *            the iva to set
	 */
	public void setIva(double iva) {
		this.iva = iva;
	}

}
