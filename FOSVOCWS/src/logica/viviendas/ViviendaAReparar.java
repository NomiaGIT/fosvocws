package logica.viviendas;

import java.io.Serializable;

public class ViviendaAReparar extends Domicilio implements Serializable {
	public boolean asentamiento;
	public String tenencia;
	private String tipoAsentamiento;
	private String zona;
	private int cantHabitaciones;
	private boolean banioInterior;

	public ViviendaAReparar(String calle, String numero, String barrio, String ciudad, String departamento, String telefono, boolean asent, String tipoAsentamiento,
			String tenencia, String zona, int cantHabitaciones, boolean banioInterior) {
		/* 21 */super(calle, numero, barrio, ciudad, departamento, telefono);
		/* 22 */this.asentamiento = asent;
		/* 23 */this.tenencia = tenencia;
		/* 24 */this.tipoAsentamiento = tipoAsentamiento;
		/* 25 */this.zona = zona;
		/* 26 */this.cantHabitaciones = cantHabitaciones;
		/* 27 */this.banioInterior = banioInterior;
	}

	public ViviendaAReparar(Domicilio dom, boolean asent, String tipoAsentamiento, String tenencia, String zona, int cantHabitaciones, boolean banioInterior) {
		/* 35 */super(dom.getCalle(), dom.getNumero(), dom.getBarrio(),
		/* 35 */dom.getCiudad(), dom.getDepartamento(), dom.getTelefono());
		/* 36 */this.asentamiento = asent;
		/* 37 */this.tenencia = tenencia;
		/* 38 */this.tipoAsentamiento = tipoAsentamiento;
		/* 39 */this.zona = zona;
		/* 40 */this.cantHabitaciones = cantHabitaciones;
		/* 41 */this.banioInterior = banioInterior;
	}

	public boolean isAsentamiento() {
		/* 48 */return this.asentamiento;
	}

	public String getTipoAsentamiento() {
		/* 55 */return this.tipoAsentamiento;
	}

	public void setTipoAsentamiento(String tipoAsentamiento) {
		/* 63 */this.tipoAsentamiento = tipoAsentamiento;
	}

	public String getZona() {
		/* 70 */return this.zona;
	}

	public void setZona(String zona) {
		/* 78 */this.zona = zona;
	}

	public int getCantHabitaciones() {
		/* 85 */return this.cantHabitaciones;
	}

	public void setCantHabitaciones(int cantHabitaciones) {
		/* 93 */this.cantHabitaciones = cantHabitaciones;
	}

	public boolean isBanioInterior() {
		return this.banioInterior;
	}

	public void setBanioInterior(boolean banioInterior) {
		this.banioInterior = banioInterior;
	}

	public void setAsentamiento(boolean asentamiento) {
		this.asentamiento = asentamiento;
	}

	public String getTenencia() {
		return this.tenencia;
	}

	public void setTenencia(String tenencia) {
		this.tenencia = tenencia;
	}

	public Domicilio getDomicilio() {
		Domicilio dom = new Domicilio(super.getCalle(), super.getNumero(), super.getBarrio(), super.getCiudad(), super.getDepartamento(), super.getTelefono());

		return dom;
	}

}

/*
 * Location: C:\Users\Sole\Desktop\servidor\ Qualified Name: logica.viviendas.ViviendaAReparar JD-Core Version: 0.6.0
 */