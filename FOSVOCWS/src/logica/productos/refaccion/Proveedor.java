package logica.productos.refaccion;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Proveedor implements Serializable {
	public String NombreBarraca;
	public String Direccion;
	public String Telefono;
	public String Razonsocial;
	private long RUT;
	private String localidad, departamento;
	private boolean activa;

	public Proveedor(String nombreBarraca, String direccion, String telefono, String razonsocial, long rut, boolean activa) {
		this.NombreBarraca = nombreBarraca;
		this.Direccion = direccion;
		this.Telefono = telefono;
		this.Razonsocial = razonsocial;
		this.RUT = rut;
		this.localidad = "";
		this.departamento = "";
		this.activa = activa;
	}

	public Proveedor(String nombreBarraca, String direccion, String telefono, String razonsocial, long rUT, String localidad, String departamento, boolean activa) {
		super();
		NombreBarraca = nombreBarraca;
		Direccion = direccion;
		Telefono = telefono;
		Razonsocial = razonsocial;
		RUT = rUT;
		this.localidad = localidad;
		this.departamento = departamento;
		this.activa = activa;
	}

	public String getRazonsocial() {
		return this.Razonsocial;
	}

	public void setRazonsocial(String razonsocial) {
		this.Razonsocial = razonsocial;
	}

	public String getNombreBarraca() {
		return this.NombreBarraca;
	}

	public void setNombreBarraca(String nombreBarraca) {
		this.NombreBarraca = nombreBarraca;
	}

	public String getDireccion() {
		return this.Direccion;
	}

	public void setDireccion(String direccion) {
		this.Direccion = direccion;
	}

	public String getTelefono() {
		return this.Telefono;
	}

	public void setTelefono(String telefono) {
		this.Telefono = telefono;
	}

	public long getRUT() {
		return this.RUT;
	}

	public void setRUT(long rUT) {
		this.RUT = rUT;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

}
