package prestamos;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Vector;

import logica.solicitudes.Solicitudes;

//
@SuppressWarnings("serial")
public class Sorteo extends ModoAdjudicacion implements Serializable {

	/**
	 * @param fecha
	 * @param lugar
	 * @param zona
	 * @param cantSorteados
	 * @param cantPorPuntaje
	 * @param cantTotal
	 * @param numeroEvento
	 * @param id
	 */
	public Sorteo(Calendar fecha, String lugar, Vector<String> zona, int cantSorteados, int cantPorPuntaje, int cantTotal, int numeroEvento, String descripcion) {
		super();
		this.fecha = fecha;
		this.lugar = lugar;
		this.zona = zona;
		this.cantSorteados = cantSorteados;
		this.cantPorPuntaje = cantPorPuntaje;
		this.cantTotal = cantTotal;
		this.numeroEvento = numeroEvento;
		this.descripcion = descripcion;
	}

	//
	private Calendar fecha;

	//
	private String lugar;

	//
	private Vector<String> zona;

	//
	private int cantSorteados;

	//
	private int cantPorPuntaje;

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
	 * @return the lugar
	 */
	public String getLugar() {
		return lugar;
	}

	/**
	 * @param lugar
	 *            the lugar to set
	 */
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	/**
	 * @return the zona
	 */
	public Vector<String> getZona() {
		return zona;
	}

	/**
	 * @param zona
	 *            the zona to set
	 */
	public void setZona(Vector<String> zona) {
		this.zona = zona;
	}

	/**
	 * @return the cantSorteados
	 */
	public int getCantSorteados() {
		return cantSorteados;
	}

	/**
	 * @param cantSorteados
	 *            the cantSorteados to set
	 */
	public void setCantSorteados(int cantSorteados) {
		this.cantSorteados = cantSorteados;
	}

	/**
	 * @return the cantPorPuntaje
	 */
	public int getCantPorPuntaje() {
		return cantPorPuntaje;
	}

	/**
	 * @param cantPorPuntaje
	 *            the cantPorPuntaje to set
	 */
	public void setCantPorPuntaje(int cantPorPuntaje) {
		this.cantPorPuntaje = cantPorPuntaje;
	}

	/**
	 * @return the cantTotal
	 */
	public int getCantTotal() {
		return cantTotal;
	}

	/**
	 * @param cantTotal
	 *            the cantTotal to set
	 */
	public void setCantTotal(int cantTotal) {
		this.cantTotal = cantTotal;
	}

	/**
	 * @return the numeroEvento
	 */
	public int getNumeroEvento() {
		return numeroEvento;
	}

	/**
	 * @param numeroEvento
	 *            the numeroEvento to set
	 */
	public void setNumeroEvento(int numeroEvento) {
		this.numeroEvento = numeroEvento;
	}

	/**
	 * @return the id
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the solicitudesParticipantes
	 */
	public Solicitudes getSolicitudesParticipantes() {
		return solicitudesParticipantes;
	}

	/**
	 * @param solicitudesParticipantes
	 *            the solicitudesParticipantes to set
	 */
	public void setSolicitudesParticipantes(Solicitudes solicitudesParticipantes) {
		this.solicitudesParticipantes = solicitudesParticipantes;
	}

	//
	private int cantTotal;

	//
	private int numeroEvento;

	//
	private String descripcion;

	//
	private Solicitudes solicitudesParticipantes;

}