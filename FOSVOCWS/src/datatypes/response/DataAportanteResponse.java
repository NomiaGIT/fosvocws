package datatypes.response;

import java.io.Serializable;

import com.google.gson.Gson;

import datatypes.DataAportante;
import datatypes.DataMensaje;

@SuppressWarnings("serial")
public class DataAportanteResponse implements Serializable{
	private DataAportante da;
	private DataMensaje dm;
	public DataAportanteResponse(DataAportante da, DataMensaje dm) {
		super();
		this.da = da;
		this.dm = dm;
	}
	public DataAportante getDa() {
		return da;
	}
	public void setDa(DataAportante da) {
		this.da = da;
	}
	public DataMensaje getDm() {
		return dm;
	}
	public void setDm(DataMensaje dm) {
		this.dm = dm;
	}
	public DataAportanteResponse(String json)
	{
		Gson gson = new Gson();
		DataAportanteResponse d = gson.fromJson(json, DataAportanteResponse.class);	
		this.dm = d.getDm();
		this.da = d.getDa();
	}

}
