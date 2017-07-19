package logica.viviendas;

import java.io.Serializable;

public class Domicilio implements Serializable {
	public String Calle;
	public String Numero;
	public String Barrio;
	public String Ciudad;
	public String Departamento;
	public String Telefono;

	public Domicilio(String calle, String numero, String barrio, String ciudad, String departamento, String telefono) {
		/* 35 */this.Calle = calle;
		/* 36 */this.Numero = numero;
		/* 37 */this.Barrio = barrio;
		/* 38 */this.Ciudad = ciudad;
		/* 39 */this.Departamento = departamento;
		/* 40 */this.Telefono = telefono;
	}

	public String getCalle() {
		/* 47 */return this.Calle;
	}

	public void setCalle(String calle) {
		/* 55 */this.Calle = calle;
	}

	public String getNumero() {
		/* 62 */return this.Numero;
	}

	public void setNumero(String numero) {
		/* 70 */this.Numero = numero;
	}

	public String getBarrio() {
		/* 77 */return this.Barrio;
	}

	public void setBarrio(String barrio) {
		/* 85 */this.Barrio = barrio;
	}

	public String getCiudad() {
		/* 92 */return this.Ciudad;
	}

	public void setCiudad(String ciudad) {
		this.Ciudad = ciudad;
	}

	public String getDepartamento() {
		return this.Departamento;
	}

	public void setDepartamento(String departamento) {
		this.Departamento = departamento;
	}

	public String getTelefono() {
		return this.Telefono;
	}

	public void setTelefono(String telefono) {
		this.Telefono = telefono;
	}

}

/*
 * Location: C:\Users\Sole\Desktop\servidor\ Qualified Name: logica.viviendas.Domicilio JD-Core Version: 0.6.0
 */