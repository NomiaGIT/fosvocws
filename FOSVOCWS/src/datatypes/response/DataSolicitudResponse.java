package datatypes.response;

import com.google.gson.Gson;

import datatypes.DataMensaje;
import datatypes.DataSolicitudweb;

public class DataSolicitudResponse {
	private DataMensaje dm;
	private DataSolicitudweb ds;
	public DataSolicitudResponse(DataMensaje dm, DataSolicitudweb ds) {
		super();
		this.dm = dm;
		this.ds = ds;
	}
	public DataMensaje getDm() {
		return dm;
	}
	public void setDm(DataMensaje dm) {
		this.dm = dm;
	}
	public DataSolicitudweb getDs() {
		return ds;
	}
	public void setDs(DataSolicitudweb ds) {
		this.ds = ds;
	}
	public DataSolicitudResponse (String json)
	{
		Gson gson = new Gson();
		DataSolicitudResponse dsol = gson.fromJson(json, DataSolicitudResponse.class);
		this.dm = dsol.getDm();
		this.ds = dsol.getDs();
	}

}
