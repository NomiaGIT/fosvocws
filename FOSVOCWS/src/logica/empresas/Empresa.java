package logica.empresas;

import java.io.Serializable;

public class Empresa implements Serializable {
	public int id;
	public String nombre;
	public String Direccion;
	public String Telefono;
	public long RUT;
	public String correoElectronico;

	public Empresa(String nombre, String direccion, String telefono, long rut) {
		/* 29 */this.nombre = nombre;
		/* 30 */this.Direccion = direccion;
		/* 31 */this.Telefono = telefono;
		/* 32 */this.RUT = rut;
	}

	public String getNombre() {
		/* 39 */return this.nombre;
	}

	public void setNombre(String nombre) {
		/* 47 */this.nombre = nombre;
	}

	public String getDireccion() {
		/* 54 */return this.Direccion;
	}

	public void setDireccion(String direccion) {
		/* 62 */this.Direccion = direccion;
	}

	public String getTelefono() {
		/* 69 */return this.Telefono;
	}

	public void setTelefono(String telefono) {
		/* 77 */this.Telefono = telefono;
	}

	public long getRUT() {
		/* 84 */return this.RUT;
	}

	public void setRUT(long rUT) {
		/* 92 */this.RUT = rUT;
	}

	public String getCorreoElectronico() {
		/* 99 */return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}

/*
 * Location: C:\Users\Sole\Desktop\servidor\ Qualified Name: logica.empresas.Empresa JD-Core Version: 0.6.0
 */