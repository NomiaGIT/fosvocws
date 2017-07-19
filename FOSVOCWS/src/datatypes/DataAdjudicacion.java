package datatypes;

import java.io.Serializable;
import java.util.Calendar;

import logica.solicitudes.Solicitud;
import prestamos.Contrato;
import prestamos.ModoAdjudicacion;

@SuppressWarnings("serial")
public class DataAdjudicacion implements Serializable {
	private Calendar fecha;
	private int plazoRetiro;
	private ModoAdjudicacion modo;
	private Solicitud solicitud;
	private Contrato contrato;
	public DataAdjudicacion(Calendar fecha, int plazoRetiro, ModoAdjudicacion modo, Solicitud solicitud, Contrato contrato) {
		super();
		this.fecha = fecha;
		this.plazoRetiro = plazoRetiro;
		this.modo = modo;
		this.solicitud = solicitud;
		this.contrato = contrato;
	}
	
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public int getPlazoRetiro() {
		return plazoRetiro;
	}
	public void setPlazoRetiro(int plazoRetiro) {
		this.plazoRetiro = plazoRetiro;
	}
	public ModoAdjudicacion getModo() {
		return modo;
	}
	public void setModo(ModoAdjudicacion modo) {
		this.modo = modo;
	}
	public Solicitud getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}
	public Contrato getContrato() {
		return contrato;
	}
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	

}
