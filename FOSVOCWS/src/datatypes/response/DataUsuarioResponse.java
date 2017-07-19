package datatypes.response;

import java.io.Serializable;

import datatypes.DataMensaje;
import datatypes.DataUsuario;

@SuppressWarnings("serial")
public class DataUsuarioResponse implements Serializable{
	private DataUsuario datau;
	private DataMensaje datam;
	public DataUsuarioResponse(DataUsuario datau, DataMensaje datam) {
		super();
		this.datau = datau;
		this.datam = datam;
	}
	public DataUsuario getDatau() {
		return datau;
	}
	public void setDatau(DataUsuario datau) {
		this.datau = datau;
	}
	public DataMensaje getDatam() {
		return datam;
	}
	public void setDatam(DataMensaje datam) {
		this.datam = datam;
	}
	

}
