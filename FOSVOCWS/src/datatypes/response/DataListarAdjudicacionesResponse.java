package datatypes.response;

import java.io.Serializable;
import java.util.Vector;

import datatypes.DataListarAdjudicaciones;
import datatypes.DataMensaje;

@SuppressWarnings("serial")
public class DataListarAdjudicacionesResponse implements Serializable {
	private Vector<DataListarAdjudicaciones> datos;
	private DataMensaje dm;
	public DataListarAdjudicacionesResponse(Vector<DataListarAdjudicaciones> datos, DataMensaje dm) {
		super();
		this.datos = datos;
		this.dm = dm;
	}
	public Vector<DataListarAdjudicaciones> getDatos() {
		return datos;
	}
	public void setDatos(Vector<DataListarAdjudicaciones> datos) {
		this.datos = datos;
	}
	public DataMensaje getDm() {
		return dm;
	}
	public void setDm(DataMensaje dm) {
		this.dm = dm;
	}
	

}
