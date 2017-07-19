package datatypes;

import java.io.Serializable;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class DataContrasenia implements Serializable{
	private String usuario;
	private String contraseniaAnterior;
	private String contraseniaNueva;

	/**
	 * @param usuario
	 * @param contraseniaAnterior
	 * @param contraseniaNueva
	 */
	public DataContrasenia(String usuario, String contraseniaAnterior, String contraseniaNueva) {
		this.usuario = usuario;
		this.contraseniaAnterior = contraseniaAnterior;
		this.contraseniaNueva = contraseniaNueva;
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
	 * @return the contraseniaAnterior
	 */
	public String getContraseniaAnterior() {
		return contraseniaAnterior;
	}

	/**
	 * @param contraseniaAnterior
	 *            the contraseniaAnterior to set
	 */
	public void setContraseniaAnterior(String contraseniaAnterior) {
		this.contraseniaAnterior = contraseniaAnterior;
	}

	/**
	 * @return the contraseniaNueva
	 */
	public String getContraseniaNueva() {
		return contraseniaNueva;
	}

	/**
	 * @param contraseniaNueva
	 *            the contraseniaNueva to set
	 */
	public void setContraseniaNueva(String contraseniaNueva) {
		this.contraseniaNueva = contraseniaNueva;
	}
	public DataContrasenia(String json)
	{
	Gson gson = new Gson();
	DataContrasenia d = gson.fromJson(json, DataContrasenia.class);		
	this.usuario = d.getUsuario();
	this.contraseniaAnterior = d.getContraseniaAnterior();
	this.contraseniaNueva = d.getContraseniaNueva();
	}

}
