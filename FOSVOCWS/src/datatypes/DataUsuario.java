package datatypes;

import java.io.Serializable;

import com.google.gson.Gson;


@SuppressWarnings("serial")
public class DataUsuario implements Serializable{
	private String nombres;
	private String apellidos;
	private int cedula;
	private String usuario;
	private String contrasenia;
	private String nombreRol;
	private boolean estaConectado;
	private boolean esActivo;
	private String mail;

	/**
	 * @param nombres
	 * @param apellidos
	 * @param cedula
	 * @param usuario
	 * @param contrasenia
	 * @param nombreRol
	 */
	public DataUsuario(String nombres, String apellidos, int cedula, String usuario, String contrasenia, String nombreRol, boolean siConectado, boolean siActivo, String mail) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.cedula = cedula;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.nombreRol = nombreRol;
		this.estaConectado = siConectado;
		this.esActivo = siActivo;
		this.mail = mail;
	}

	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * @param nombres
	 *            the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos
	 *            the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the cedula
	 */
	public int getCedula() {
		return cedula;
	}

	/**
	 * @param cedula
	 *            the cedula to set
	 */
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}

	/**
	 * @param contrasenia
	 *            the contrasenia to set
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	/**
	 * @return the nombreRol
	 */
	public String getNombreRol() {
		return nombreRol;
	}

	/**
	 * @param nombreRol
	 *            the nombreRol to set
	 */
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	/**
	 * @return the estaConectado
	 */
	public boolean isEstaConectado() {
		return estaConectado;
	}

	/**
	 * @param estaConectado
	 *            the estaConectado to set
	 */
	public void setEstaConectado(boolean estaConectado) {
		this.estaConectado = estaConectado;
	}

	/**
	 * @return the esActivo
	 */
	public boolean isEsActivo() {
		return esActivo;
	}

	/**
	 * @param esActivo
	 *            the esActivo to set
	 */
	public void setEsActivo(boolean esActivo) {
		this.esActivo = esActivo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	public DataUsuario (String json)
	{
		Gson gson = new Gson();
		DataUsuario d = gson.fromJson(json, DataUsuario.class);		
		this.nombres = d.getNombres();
		this.apellidos = d.getApellidos();
		this.cedula = d.getCedula();
		this.usuario = d.getUsuario();
		this.contrasenia = d.getContrasenia();
		this.nombreRol = d.getNombreRol();
		this.estaConectado = d.isEstaConectado();
		this.esActivo = d.isEsActivo();
		this.mail = d.getMail();
	
	}
}
