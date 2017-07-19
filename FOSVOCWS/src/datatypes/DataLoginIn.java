package datatypes;

import java.io.Serializable;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class DataLoginIn implements Serializable{
	private String usuario;
	private String pass;
	public DataLoginIn(String usuario, String pass) {
		super();
		this.usuario = usuario;
		this.pass = pass;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public DataLoginIn(String json)
	{
		Gson gson = new Gson();
		DataLoginIn d = gson.fromJson(json, DataLoginIn.class);		
		this.usuario = d.getUsuario();
		this.pass = d.getPass();
	}

}
