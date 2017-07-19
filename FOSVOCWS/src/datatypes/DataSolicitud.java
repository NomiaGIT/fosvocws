package datatypes;

import java.io.Serializable;
import java.util.Calendar;

import logica.aportantes.Aportante;
import logica.productos.Prestamo;

@SuppressWarnings("serial")
public class DataSolicitud implements Serializable{

	private int id;
	public Calendar fecha;
	public String estado;
	private Aportante aportante;
	private Prestamo prestamo;
	private int puntaje;
	public DataSolicitud(int id, Calendar fecha, String estado, Aportante aportante, Prestamo prestamo, int puntaje) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.estado = estado;
		this.aportante = aportante;
		this.prestamo = prestamo;
		this.puntaje = puntaje;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Aportante getAportante() {
		return aportante;
	}
	public void setAportante(Aportante aportante) {
		this.aportante = aportante;
	}
	public Prestamo getPrestamo() {
		return prestamo;
	}
	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
}
