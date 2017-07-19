package datatypes.response;

import java.io.Serializable;
import java.util.Vector;

import datatypes.DataListarAportantes;
import datatypes.DataMensaje;

@SuppressWarnings("serial")
public class DataListarAportantesResponse implements Serializable{
	private Vector<DataListarAportantes> lista;
	private DataMensaje dm;
	public DataListarAportantesResponse(Vector<DataListarAportantes> lista, DataMensaje dm) {
		super();
		this.lista = lista;
		this.dm = dm;
	}
	public Vector<DataListarAportantes> getLista() {
		return lista;
	}
	public void setLista(Vector<DataListarAportantes> lista) {
		this.lista = lista;
	}
	public DataMensaje getDm() {
		return dm;
	}
	public void setDm(DataMensaje dm) {
		this.dm = dm;
	}
	

}
