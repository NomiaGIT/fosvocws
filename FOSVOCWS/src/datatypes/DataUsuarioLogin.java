package datatypes;

import java.io.Serializable;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class DataUsuarioLogin implements Serializable {
	private String nombres;
	private String apellidos;
	private String usuario;
	private String contrasenia;
	private String nombreRol;
	private String mail;
	private int documento;
	public DataUsuarioLogin(String nombres, String apellidos, String usuario, String contrasenia, String nombreRol, String mail, int documento) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.nombreRol = nombreRol;
		this.mail = mail;
		this.documento = documento;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public DataUsuarioLogin (String json)
	{
		Gson gson = new Gson();
		DataUsuarioLogin d = gson.fromJson(json, DataUsuarioLogin.class);	
		this.nombres = d.getNombres();
		this.apellidos = d.getApellidos();
		this.usuario = d.getUsuario();
		this.contrasenia = d.getContrasenia();
		this.nombreRol = d.getNombreRol();
		this.mail = d.getMail();
		this.documento = d.getDocumento();
	}
	public int getDocumento() {
		return documento;
	}
	public void setDocumento(int documento) {
		this.documento = documento;
	}

}
