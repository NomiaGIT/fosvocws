package datatypes.response;

import java.io.Serializable;

import datatypes.DataAdjudicacion;
import datatypes.DataMensaje;

@SuppressWarnings("serial")
public class DataAdjudicacionResponse implements Serializable {
	private DataAdjudicacion data;
	private DataMensaje dm;
	public DataAdjudicacionResponse(DataAdjudicacion data, DataMensaje dm) {
		super();
		this.data = data;
		this.dm = dm;
	}
	public DataAdjudicacion getData() {
		return data;
	}
	public void setData(DataAdjudicacion data) {
		this.data = data;
	}
	public DataMensaje getDm() {
		return dm;
	}
	public void setDm(DataMensaje dm) {
		this.dm = dm;
	}
	

}
