package datatypes.response;

import java.io.Serializable;

import com.google.gson.Gson;

import datatypes.DataMensaje;
import datatypes.DataUsuarioLogin;

@SuppressWarnings("serial")
public class DataUsuarioLoginResponse implements Serializable{
	private DataUsuarioLogin du;
	private DataMensaje dm;
	public DataUsuarioLoginResponse(DataUsuarioLogin du, DataMensaje dm) {
		super();
		this.du = du;
		this.dm = dm;
	}
	public DataUsuarioLogin getDu() {
		return du;
	}
	public void setDu(DataUsuarioLogin du) {
		this.du = du;
	}
	public DataMensaje getDm() {
		return dm;
	}
	public void setDm(DataMensaje dm) {
		this.dm = dm;
	}
	
	public DataUsuarioLoginResponse(String json)
	{
		Gson gson = new Gson();
		DataUsuarioLoginResponse d = gson.fromJson(json, DataUsuarioLoginResponse.class);	
		this.dm = d.getDm();
		this.du = d.getDu();
	}

}
