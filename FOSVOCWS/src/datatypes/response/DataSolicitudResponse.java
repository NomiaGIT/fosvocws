package datatypes.response;

import java.io.Serializable;

import datatypes.DataMensaje;
import datatypes.DataSolicitud;

@SuppressWarnings("serial")
public class DataSolicitudResponse implements Serializable{
	private DataSolicitud data;
	private DataMensaje dm;
	public DataSolicitudResponse(DataSolicitud data, DataMensaje dm) {
		super();
		this.data = data;
		this.dm = dm;
	}
	public DataSolicitud getData() {
		return data;
	}
	public void setData(DataSolicitud data) {
		this.data = data;
	}
	public DataMensaje getDm() {
		return dm;
	}
	public void setDm(DataMensaje dm) {
		this.dm = dm;
	}
	

}
