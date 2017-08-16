package datatypes.response;

import java.io.Serializable;
import java.util.Vector;

import com.google.gson.Gson;

import datatypes.DataListarSolicitudes;
import datatypes.DataMensaje;

@SuppressWarnings("serial")
public class DataListarSolicitudesResponse implements Serializable {
	private Vector<DataListarSolicitudes> datos;
	private DataMensaje dm;

	public DataListarSolicitudesResponse(Vector<DataListarSolicitudes> datos, DataMensaje dm) {
		super();
		this.datos = datos;
		this.dm = dm;
	}

	public Vector<DataListarSolicitudes> getDatos() {
		return datos;
	}

	public void setDatos(Vector<DataListarSolicitudes> datos) {
		this.datos = datos;
	}

	public DataMensaje getDm() {
		return dm;
	}

	public void setDm(DataMensaje dm) {
		this.dm = dm;
	}
public DataListarSolicitudesResponse(String json)
{
	Gson gson = new Gson();
	DataListarSolicitudesResponse d = gson.fromJson(json, DataListarSolicitudesResponse.class);		
	this.dm = d.getDm();
	this.datos = d.getDatos();
}
}
