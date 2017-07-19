package logica.productos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class lineaPrestamo implements Serializable {
	private int id;
	private String descripcion;
	private int cantCuotas;
	private String destino;
	private double tasa;
	private String tipo;
	private String nombre;
	private String moneda;
	private double interes;
	private int valorGastosAdmin;
	private int topeCuota;

	public lineaPrestamo(int id, String descripcion, int cantCuotas, String destino, double tasa, String tipo, String nombre, String moneda, double interes, int valorGastosAdmin,
			int topeCuota) {
		  this.id = id;
		  this.descripcion = descripcion;
		  this.cantCuotas = cantCuotas;
		  this.destino = destino;
		  this.tasa = tasa;
		  this.tipo = tipo;
		  this.nombre = nombre;
		  this.moneda = moneda;
		  this.interes = interes;
		  this.valorGastosAdmin = valorGastosAdmin;
		  this.topeCuota = topeCuota;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		 this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		  this.descripcion = descripcion;
	}

	public int getCantCuotas() {
		return this.cantCuotas;
	}

	public void setCantCuotas(int cantCuotas) {
		this.cantCuotas = cantCuotas;
	}

	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public double getTasa() {
		return this.tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMoneda() {
		return this.moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public double getInteres() {
		return this.interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}

	public int getValorGastosAdmin() {
		return this.valorGastosAdmin;
	}

	public void setValorGastosAdmin(int valorGastosAdmin) {
		this.valorGastosAdmin = valorGastosAdmin;
	}

	public int getTopeCuota() {
		return this.topeCuota;
	}

	public void setTopeCuota(int topeCuota) {
		this.topeCuota = topeCuota;
	}

}

