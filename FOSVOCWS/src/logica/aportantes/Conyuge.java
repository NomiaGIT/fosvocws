package logica.aportantes;

import java.io.Serializable;

import java.util.Calendar;

public class Conyuge implements Serializable {
	private int cedula;
	private String nombres;
	private String apellidos;
	private Calendar FechaNacimiento;
	private String Nacionalidad;
	private String EstadoCivil;
	private String Sexo;

	public Conyuge(int cedula, String nombres, String apellidos, Calendar fechaNacimiento, String nacionalidad, String estadoCivil, String sexo) {
		/* 40 */this.cedula = cedula;
		/* 41 */this.nombres = nombres;
		/* 42 */this.apellidos = apellidos;
		/* 43 */this.FechaNacimiento = fechaNacimiento;
		/* 44 */this.Nacionalidad = nacionalidad;
		/* 45 */this.EstadoCivil = estadoCivil;
		/* 46 */this.Sexo = sexo;
	}

	public int getCedula() {
		/* 53 */return this.cedula;
	}

	public void setCedula(int cedula) {
		/* 61 */this.cedula = cedula;
	}

	public String getNombres() {
		/* 68 */return this.nombres;
	}

	public void setNombres(String nombres) {
		/* 76 */this.nombres = nombres;
	}

	public String getApellidos() {
		/* 83 */return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		/* 91 */this.apellidos = apellidos;
	}

	public Calendar getFechaNacimiento() {
		/* 98 */return this.FechaNacimiento;
	}

	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.FechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return this.Nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.Nacionalidad = nacionalidad;
	}

	public String getEstadoCivil() {
		return this.EstadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.EstadoCivil = estadoCivil;
	}

	public void setSexo(String sexo) {
		this.Sexo = sexo;
	}

	public String getSexo() {
		return this.Sexo;
	}

}

/*
 * Location: C:\Users\Sole\Desktop\servidor\ Qualified Name: logica.aportantes.Conyuge JD-Core Version: 0.6.0
 */