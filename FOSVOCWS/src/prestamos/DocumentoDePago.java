package prestamos;

import java.util.Calendar;

public class DocumentoDePago {

	private String emisor;
	private double monto;
	private Calendar fecha;
	private int moneda;
	private int medioDePago;

	public DocumentoDePago(String emisor, double monto, Calendar fecha, int moneda, int medioDePago) {
		super();
		this.emisor = emisor;
		this.monto = monto;
		this.fecha = fecha;
		this.moneda = moneda;
		this.medioDePago = medioDePago;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public int getMoneda() {
		return moneda;
	}

	public void setMoneda(int moneda) {
		this.moneda = moneda;
	}

	public int getMedioDePago() {
		return medioDePago;
	}

	public void setMedioDePago(int medioDePago) {
		this.medioDePago = medioDePago;
	}

}
