package prestamos;

import java.sql.Date;
import java.sql.Timestamp;

public class CobroDB {
	private int id;
	private java.sql.Date fecha;
	private double monto;
	private int idcuota;
	private int idTransaccion;
	private int idCObroCOncepto;
	private boolean anulado;
	private java.sql.Timestamp fechaAnulado;

	public CobroDB(int id, Date fecha, double monto, int idcuota, int idTransaccion, int idCObroCOncepto, boolean anulado, Timestamp fechaAnulado) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.monto = monto;
		this.idcuota = idcuota;
		this.idTransaccion = idTransaccion;
		this.idCObroCOncepto = idCObroCOncepto;
		this.anulado = anulado;
		this.fechaAnulado = fechaAnulado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.sql.Date getFecha() {
		return fecha;
	}

	public void setFecha(java.sql.Date fecha) {
		this.fecha = fecha;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public int getIdcuota() {
		return idcuota;
	}

	public void setIdcuota(int idcuota) {
		this.idcuota = idcuota;
	}

	public int getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(int idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public int getIdCObroCOncepto() {
		return idCObroCOncepto;
	}

	public void setIdCObroCOncepto(int idCObroCOncepto) {
		this.idCObroCOncepto = idCObroCOncepto;
	}

	public boolean isAnulado() {
		return anulado;
	}

	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}

	public java.sql.Timestamp getFechaAnulado() {
		return fechaAnulado;
	}

	public void setFechaAnulado(java.sql.Timestamp fechaAnulado) {
		this.fechaAnulado = fechaAnulado;
	}

}
