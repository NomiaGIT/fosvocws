package logica.usuarios;

import excepciones.PersistenciaException;

import java.io.Serializable;

import persistencia.Transaccion;

@SuppressWarnings("serial")
public class Usuario implements Serializable {
	private String nick;
	private char[] pass;
	private boolean conectado;
	private int cedula;
	private String nombre;
	private String apellidos;
	private Acciones ColAcciones;
	private Rol rol;
	private boolean activo;
	private String email;

	public Usuario(String nick, char[] pass, boolean conectado, int cedula, String nombre, String apellidos, Rol rol, boolean activo, String email) {
		this.nick = nick;
		this.pass = pass;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.rol = rol;
		this.ColAcciones = new Acciones(cedula);
		this.activo = activo;
		this.conectado = conectado;
		this.activo = activo;
		this.email = email;
	}

	public Usuario(String nick, char[] pass, int cedula, String nombre, String apellidos, Rol rol, String email) {
		this.nick = nick;
		this.pass = pass;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.rol = rol;
		this.ColAcciones = new Acciones(cedula);
		this.activo = true;
		this.conectado = false;
		this.email = email;
	}

	public Usuario(String nick, char[] pass, boolean activo, int cedula, String nombre, String apellidos, Rol rol, String email) {
		this.nick = nick;
		this.pass = pass;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.rol = rol;
		this.ColAcciones = new Acciones(cedula);
		this.activo = activo;
		this.conectado = false;
		this.email = email;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public char[] getPass() {
		return this.pass;
	}

	public void setPass(char[] pass) {
		this.pass = pass;
	}

	public boolean isConectado() {
		return this.conectado;
	}

	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}

	public int getCedula() {
		return this.cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Acciones getColAcciones() {
		return this.ColAcciones;
	}

	public void setColAcciones(Acciones colAcciones) {
		this.ColAcciones = colAcciones;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public boolean isActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void agregarAccion(Accion ac, Transaccion t) throws PersistenciaException {
		this.ColAcciones.agregarAccion(ac, t);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



}
