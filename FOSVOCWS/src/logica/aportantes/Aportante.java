package logica.aportantes;

import java.io.Serializable;

import java.util.Calendar;

import logica.usuarios.Rol;

import logica.usuarios.Usuario;

import logica.viviendas.Domicilio;

@SuppressWarnings("serial")
public class Aportante extends Usuario implements Serializable {
	public int puntaje;
	public String categoria;
	private Calendar fechaNacimiento;
	private String nacionalidad;
	private String estadoCivil;
	private Conyuge conyuge;
	private Familia familia;
	private Trabajo trabajo;
	private Domicilio domicilio;
	private String sexo;

	public Aportante(String nick, char[] pass, int cedula, String nombre, String apellidos, Rol rol, String email, String cat, Calendar fechaNac, String nacionalidad, String estadoCivil,
			Conyuge con, Familia fam, Trabajo trab, Domicilio dom, String sexo) {
		  super(nick, pass, cedula, nombre, apellidos, rol, email);
		  this.categoria = cat;
		  this.fechaNacimiento = fechaNac;
		  this.nacionalidad = nacionalidad;
		  this.estadoCivil = estadoCivil;
		  this.conyuge = con;
		  this.familia = fam;
		  this.trabajo = trab;
		  this.domicilio = dom;
		  this.sexo = sexo;
	}

	public Aportante(Usuario usu, String cat, Calendar fechaNac, String nacionalidad, String estadoCivil, Conyuge con, Familia fam, Trabajo trab, Domicilio dom, String sexo) {
		  super(usu.getNick(), usu.getPass(), usu.getCedula(), usu.getNombre(),
		  usu.getApellidos(), usu.getRol(), usu.getEmail());
		  this.categoria = cat;
		  this.fechaNacimiento = fechaNac;
		  this.nacionalidad = nacionalidad;
		  this.estadoCivil = estadoCivil;
		  this.conyuge = con;
		  this.familia = fam;
		  this.trabajo = trab;
		  this.domicilio = dom;
		  this.sexo = sexo;
	}

	public Aportante(Usuario usu, String categoria, Calendar fechaNac, String nacionalidad, String estadoCivil2, Conyuge con, Familia fam, Trabajo trab, Domicilio dom,
			String estadoCivil, String sexo) {
		  super(usu.getNick(), usu.getPass(), usu.getCedula(), usu.getNombre(),
		  usu.getApellidos(), usu.getRol(), usu.getEmail());
		  this.categoria = categoria;
		  this.fechaNacimiento = fechaNac;
		  this.nacionalidad = nacionalidad;
		  this.estadoCivil = estadoCivil;
		  this.conyuge = con;
		  this.familia = fam;
		  this.trabajo = trab;
		  this.domicilio = dom;
		  this.sexo = sexo;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Calendar getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Conyuge getConyuge() {
		return this.conyuge;
	}

	public void setConyuge(Conyuge conyuge) {
		this.conyuge = conyuge;
	}

	public Familia getFamilia() {
		return this.familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public Trabajo getTrabajo() {
		return this.trabajo;
	}

	public void setTrabajo(Trabajo trabajo) {
		this.trabajo = trabajo;
	}

	public Domicilio getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

}
