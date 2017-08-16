package datatypes;

import java.io.Serializable;
import java.util.List;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class DataAdjudicacion implements Serializable {
	private String fecha;
	private int plazoRetiro;
	private String modoAdjudicacion;
	private int numeroSolicitud;
	private String fechaAdjudicada;
	private int montoSolicitado;
	private int cantCuotas;
	private List<DataCuota> cuotas;
	private String nombreBarraca;
	private String estadoSolicitud;
	private double valorUI;
	public DataAdjudicacion(String fecha, int plazoRetiro, String modoAdjudicacion, int numeroSolicitud, String fechaAdjudicada, int montoSolicitado, int cantCuotas,
			List<DataCuota> cuotas, String nombreBarraca, String estadoSolicitud, double valorUI) {
		super();
		this.fecha = fecha;
		this.plazoRetiro = plazoRetiro;
		this.modoAdjudicacion = modoAdjudicacion;
		this.numeroSolicitud = numeroSolicitud;
		this.fechaAdjudicada = fechaAdjudicada;
		this.montoSolicitado = montoSolicitado;
		this.cantCuotas = cantCuotas;
		this.cuotas = cuotas;
		this.nombreBarraca = nombreBarraca;
		this.estadoSolicitud = estadoSolicitud;
		this.valorUI = valorUI;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getPlazoRetiro() {
		return plazoRetiro;
	}
	public void setPlazoRetiro(int plazoRetiro) {
		this.plazoRetiro = plazoRetiro;
	}
	public String getModoAdjudicacion() {
		return modoAdjudicacion;
	}
	public void setModoAdjudicacion(String modoAdjudicacion) {
		this.modoAdjudicacion = modoAdjudicacion;
	}
	public int getNumeroSolicitud() {
		return numeroSolicitud;
	}
	public void setNumeroSolicitud(int numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}
	public String getFechaAdjudicada() {
		return fechaAdjudicada;
	}
	public void setFechaAdjudicada(String fechaAdjudicada) {
		this.fechaAdjudicada = fechaAdjudicada;
	}
	public int getMontoSolicitado() {
		return montoSolicitado;
	}
	public void setMontoSolicitado(int montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}
	public int getCantCuotas() {
		return cantCuotas;
	}
	public void setCantCuotas(int cantCuotas) {
		this.cantCuotas = cantCuotas;
	}
	public List<DataCuota> getCuotas() {
		return cuotas;
	}
	public void setCuotas(List<DataCuota> cuotas) {
		this.cuotas = cuotas;
	}
	public String getNombreBarraca() {
		return nombreBarraca;
	}
	public void setNombreBarraca(String nombreBarraca) {
		this.nombreBarraca = nombreBarraca;
	}
	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}
	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}
	public double getValorUI() {
		return valorUI;
	}
	public void setValorUI(double valorUI) {
		this.valorUI = valorUI;
	}
	
	public DataAdjudicacion(String json)
	{
		Gson gson = new Gson();
		DataAdjudicacion da = gson.fromJson(json, DataAdjudicacion.class);
		this.fecha = da.getFecha();
		this.plazoRetiro = da.getPlazoRetiro();
		this.modoAdjudicacion = da.getModoAdjudicacion();
		this.numeroSolicitud = da.getNumeroSolicitud();
		this.fechaAdjudicada = da.getFechaAdjudicada();
		this.montoSolicitado = da.getMontoSolicitado();
		this.cantCuotas = da.getCantCuotas();
		this.cuotas = da.getCuotas();
		this.nombreBarraca = da.getNombreBarraca();
		this.estadoSolicitud = da.getEstadoSolicitud();
		this.valorUI = da.getValorUI();
	}

	

}
