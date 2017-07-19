package logica.productos.refaccion;

import java.io.Serializable;

import java.util.Vector;

import logica.productos.Prestamo;

import logica.productos.lineaPrestamo;

import logica.viviendas.ViviendaAReparar;

@SuppressWarnings("serial")
public class Refaccion extends Prestamo implements Serializable {
	public double totalSolicitado;
	private ViviendaAReparar vivienda;
	private Cotizacion cotizacion;
	private Vector<String> detalle;
	private int cantCuotasSolicitadas;

	public Refaccion(String tipo, String nombre, int codigo, lineaPrestamo linea, double total, ViviendaAReparar viv, Cotizacion cot, Vector<String> detalle, int cantCuotas) {
		  super(tipo, nombre, codigo, linea);
		  this.totalSolicitado = total;
		  this.vivienda = viv;
		  this.cotizacion = cot;
		  this.detalle = detalle;
		  this.cantCuotasSolicitadas = cantCuotas;
	}

	public Refaccion(Prestamo prestamo, double total, ViviendaAReparar viv, Cotizacion cot, Vector<String> detalle, int cantCuotas) {
		  super(prestamo.getTipo(), prestamo.getNombre(), prestamo.getCodigo(),
		  prestamo.getLinea());
		  this.totalSolicitado = total;
		  this.vivienda = viv;
		  this.cotizacion = cot;
		  this.detalle = detalle;
		  this.cantCuotasSolicitadas = cantCuotas;
	}

	public double getTotalSolicitado() {
		  return this.totalSolicitado;
	}

	public void setTotalSolicitado(double totalSolicitado) {
		  this.totalSolicitado = totalSolicitado;
	}

	public ViviendaAReparar getVivienda() {
		  return this.vivienda;
	}

	public void setVivienda(ViviendaAReparar vivienda) {
		  this.vivienda = vivienda;
	}

	public Cotizacion getCotizacion() {
		  return this.cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		  this.cotizacion = cotizacion;
	}

	public Vector<String> getDetalle() {
		return this.detalle;
	}

	public void setDetalle(Vector<String> detalle) {
		this.detalle = detalle;
	}

	public int getCantCuotasSolicitadas() {
		return this.cantCuotasSolicitadas;
	}

	public void setCantCuotasSolicitadas(int cantCuotasSolicitadas) {
		this.cantCuotasSolicitadas = cantCuotasSolicitadas;
	}

	public Prestamo getPrestamo() {
		Prestamo pre = new Prestamo(super.getTipo(), super.getNombre(), super.getCodigo(), super.getLinea());
		return pre;
	}

	public String getString(Vector<String> detalle) {
		String resu = new String();
		for (String pal : detalle) {
			resu.concat(pal + ",");
		}
		return resu;
	}

}
