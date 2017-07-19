package prestamos;

import java.io.Serializable;
import java.util.Calendar;

import logica.solicitudes.Solicitud;

//
@SuppressWarnings("serial")
public class Adjudicacion implements Serializable {

	//
	private Calendar fecha;

	//
	private int plazoRetiro;

	//
	private ModoAdjudicacion modo;

	//
	private Solicitud solicitud;

	//
	private Contrato contrato;

	/**
	 * @param fecha
	 * @param plazoRetiro
	 * @param modo
	 * @param solicitud
	 * @param contrato
	 */
	public Adjudicacion(Calendar fecha, int plazoRetiro, ModoAdjudicacion modo, Solicitud solicitud, Contrato contrato) {
		this.fecha = fecha;
		this.plazoRetiro = plazoRetiro;
		this.modo = modo;
		this.solicitud = solicitud;
		this.contrato = contrato;
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
	 * @return the plazoRetiro
	 */
	public int getPlazoRetiro() {
		return plazoRetiro;
	}

	/**
	 * @param plazoRetiro
	 *            the plazoRetiro to set
	 */
	public void setPlazoRetiro(int plazoRetiro) {
		this.plazoRetiro = plazoRetiro;
	}

	/**
	 * @return the modo
	 */
	public ModoAdjudicacion getModo() {
		return modo;
	}

	/**
	 * @param modo
	 *            the modo to set
	 */
	public void setModo(ModoAdjudicacion modo) {
		this.modo = modo;
	}

	/**
	 * @return the solicitud
	 */
	public Solicitud getSolicitud() {
		return solicitud;
	}

	/**
	 * @param solicitud
	 *            the solicitud to set
	 */
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	/**
	 * @return the contrato
	 */
	public Contrato getContrato() {
		return contrato;
	}

	/**
	 * @param contrato
	 *            the contrato to set
	 */
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

}