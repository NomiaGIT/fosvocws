package datatypes;

import java.io.Serializable;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class DataMensaje implements Serializable {
	private String mensaje;
	private boolean ok;
	public DataMensaje(String mensaje, boolean ok) {		
		this.mensaje = mensaje;
		this.ok = ok;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public DataMensaje(String json)
	{
		Gson gson = new Gson();
		DataMensaje d = gson.fromJson(json, DataMensaje.class);		
		this.ok = d.isOk();
		this.mensaje = d.getMensaje();
	}


}
