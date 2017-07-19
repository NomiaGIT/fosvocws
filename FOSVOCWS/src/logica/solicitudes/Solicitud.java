package logica.solicitudes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import logica.aportantes.Aportante;

import logica.productos.Prestamo;

@SuppressWarnings("serial")
public class Solicitud implements Serializable {
	private int id;
	public Calendar fecha;
	public String estado;
	private Aportante aportante;
	private Prestamo prestamo;
	private int puntaje;

	public Solicitud(Calendar fecha, String estado, Aportante aportante, Prestamo prestamo, int puntaje) {
		  this.fecha = fecha;
		  this.estado = estado;
		  this.aportante = aportante;
		  this.prestamo = prestamo;
		  this.puntaje = puntaje;
		  this.id = 0;
	}

	public Solicitud(int id, Calendar fecha, String estado, Aportante aportante, Prestamo prestamo, int puntaje) {
		  this.id = id;
		  this.fecha = fecha;
		  this.estado = estado;
		  this.aportante = aportante;
		  this.prestamo = prestamo;
		  this.puntaje = puntaje;
	}

	public Solicitud(int id, int mes, int anio, String estado, Aportante aportante, Prestamo prestamo, int puntaje) {
		  this.id = id;
		  this.estado = estado;
		  this.aportante = aportante;
		  this.prestamo = prestamo;
		  this.puntaje = puntaje;

		  Calendar calendar = Calendar.getInstance();
		  int dia = calendar.get(5);
		  calendar.set(anio, mes, dia);
		  this.fecha = calendar;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public Calendar getFecha() {
		return this.fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Aportante getAportante() {
		return this.aportante;
	}

	public void setAportante(Aportante aportante) {
		this.aportante = aportante;
	}

	public Prestamo getPrestamo() {
		return this.prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public Calendar calcularFecha(double periodo, String medida) {
		Calendar resu = Calendar.getInstance();
		Calendar hoy = Calendar.getInstance();
		hoy.setTime(new Date());
		long tiempotranscurrido;
		if (medida.equalsIgnoreCase("Años")) {
			tiempotranscurrido = (long) (periodo * 60 * 60 * 1000 * 24 * 365);
		} else
			tiempotranscurrido = (long) (periodo * 60 * 60 * 1000 * 24 * 30);
		resu.setTimeInMillis(hoy.getTime().getTime() - tiempotranscurrido);

		return resu;
	}

}
