package logica.productos.refaccion;

import java.io.Serializable;
import java.util.Calendar;

@SuppressWarnings("serial")
public class Cotizacion
implements Serializable
{
	public Calendar Fecha;
	public double MontoPresupuesto;
	public String DetalleMateriales;
	private Proveedor proveedor;


	public Cotizacion(Calendar fecha, double montoPresupuesto, String detalleMateriales, Proveedor proveedor)
	{
		this.Fecha = fecha;
		this.MontoPresupuesto = montoPresupuesto;
		this.DetalleMateriales = detalleMateriales;
		this.proveedor = proveedor;
	}


	public Calendar getFecha()
	{
		return this.Fecha;
	}


	public void setFecha(Calendar fecha)
	{
		this.Fecha = fecha;
	}


	public double getMontoPresupuesto()
	{
		return this.MontoPresupuesto;
	}


	public void setMontoPresupuesto(double montoPresupuesto)
	{
		this.MontoPresupuesto = montoPresupuesto;
	}


	public String getDetalleMateriales()
	{
		return this.DetalleMateriales;
	}


	public void setDetalleMateriales(String detalleMateriales)
	{
		this.DetalleMateriales = detalleMateriales;
	}


	public Proveedor getProveedor()
	{
		return this.proveedor;
	}


	public void setProveedor(Proveedor proveedor)
	{
		this.proveedor = proveedor;
	}

}
