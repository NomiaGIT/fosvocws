package logica;

import datatypes.DataAportante;
import datatypes.DataContrasenia;
import datatypes.DataLoginIn;
import datatypes.DataMensaje;
import datatypes.DataUsuario;
import datatypes.response.DataAdjudicacionResponse;
import datatypes.response.DataAportanteResponse;
import datatypes.response.DataListarAdjudicacionesResponse;
import datatypes.response.DataListarAportantesResponse;
import datatypes.response.DataListarSolicitudesResponse;
import datatypes.response.DataSolicitudResponse;
import datatypes.response.DataUsuarioLoginResponse;
import datatypes.response.DataUsuarioResponse;
import excepciones.PersistenciaException;

public class Fachada {
	
	private FachadaSistema sistema;
	private static Fachada instancia = null;
	
	private Fachada()
	{
		sistema = FachadaSistema.getInstancia();
	}
	public static Fachada getInstancia()
	{
		if(instancia == null)
			instancia = new Fachada();
		return instancia;
	}
	public DataUsuarioResponse buscarUsuario(int id) {
		DataUsuarioResponse resu = null;
		try {
			resu = sistema.buscarUsuario(id);
		} catch (PersistenciaException e) {
			resu = new DataUsuarioResponse(null, new DataMensaje("Error de acceso a datos", false));
		}
		return resu;
	}
	public DataUsuarioResponse buscarPorNickUsuario(String nick) {
		DataUsuarioResponse resu;
		try {
			resu = sistema.buscarPorNickUsuario(nick);
		} catch (PersistenciaException e) {
			resu = new DataUsuarioResponse(null, new DataMensaje("Error de acceso a datos", false));
		}
		return resu;
	}
	public DataUsuarioLoginResponse login(DataLoginIn data) {
		DataUsuarioLoginResponse resu;
		try {
			resu = sistema.login(data);
		} catch (PersistenciaException e) {
			resu = new DataUsuarioLoginResponse(null, new DataMensaje("Error de acceso a datos", false));
		}		
		return resu;
	}
	/**
	 * metodo invocado desde autogestion para login de trabajador
	 * @param data
	 * @return
	 */
	public DataUsuarioLoginResponse loginTrabajador(DataLoginIn data) {
		DataUsuarioLoginResponse resu;
		try {
			resu = sistema.loginTrabajador(data);
		} catch (PersistenciaException e) {
			resu = new DataUsuarioLoginResponse(null, new DataMensaje("Error de acceso a datos", false));
		}		
		return resu;
	}
	public DataUsuarioResponse modificarUsuario(DataUsuario usuario) {
		DataUsuarioResponse resu;
		try {
			resu = sistema.modificarUsuario(usuario);
		} catch (PersistenciaException e) {
			resu = new DataUsuarioResponse(null, new DataMensaje("Error deacceso a datos", false));
		}
		return resu;
	}
	public DataMensaje modificarContrasenia(DataContrasenia data) {
		DataMensaje resu;
		try {
			resu = sistema.modificarContrasenia(data);
		} catch (PersistenciaException e) {
			resu = new DataMensaje("Error de acceso a datos", false);
		}
		return resu;
	}
	public DataAportanteResponse buscarAportante(int documento) {
		DataAportanteResponse resu = null;
		try {
			resu = sistema.buscarAportante(documento);
		} catch (PersistenciaException e) {
			resu = new DataAportanteResponse(null, new DataMensaje("Error de acceso a datos", false));
		}
		return resu;
	}
	public DataMensaje modificarAportante(DataAportante aportante) {
		DataMensaje resu = null;
		try {
			resu = sistema.modificarAportante(aportante);
		} catch (PersistenciaException e) {
			resu = new DataMensaje("Error de acceso a datos", false);
		}
		return resu;
	}
	public DataListarAportantesResponse listarAportantes() {
		DataListarAportantesResponse resu = null;
		try {
			resu = sistema.listarAportantes();
		} catch (PersistenciaException e) {
			resu = new DataListarAportantesResponse(null, new DataMensaje("Error de acceso a datos", false));
		}
		return resu;
	}
	public DataSolicitudResponse buscarSolicitud(int id) {
		DataSolicitudResponse resu = null;
		try {
			resu = sistema.buscarSolicitud(id);
		} catch (PersistenciaException e) {
			resu = new DataSolicitudResponse(null, new DataMensaje("Error de acceso a datos", false));
		}
		return resu;
	}
	public DataListarSolicitudesResponse listarSolicitudes() {
		DataListarSolicitudesResponse resu = null;
		try{
			resu = sistema.listarSolicitudes();
		}catch ( PersistenciaException e)
		{
			resu = new DataListarSolicitudesResponse(null, new DataMensaje("Error de acceso a datos", false));
		}
		return resu;
	}
	public DataListarSolicitudesResponse listarSolicitudesdeAportante(int cedula) {
		DataListarSolicitudesResponse resu = null;
		try{
			resu = sistema.listarSolicitudesDeAportante(cedula);
		}catch ( PersistenciaException e)
		{
			resu = new DataListarSolicitudesResponse(null, new DataMensaje("Error de acceso a datos", false));
		}
		return resu;
	}
	public DataAdjudicacionResponse buscarAdjudicacion(int id) {
		DataAdjudicacionResponse resu = null;
		try {
			resu = sistema.buscarAdjudicacion(id);
		} catch (PersistenciaException e) {
			resu = new DataAdjudicacionResponse(null, new DataMensaje("Error de acceso a datos", false));
		}
		return resu;
	}
	public DataListarAdjudicacionesResponse listarAdjudicaciones() {
		DataListarAdjudicacionesResponse resu = null;
		try{
			resu = sistema.listarAdjudicaciones();
		}catch ( PersistenciaException e)
		{
			resu = new DataListarAdjudicacionesResponse(null, new DataMensaje("Error de acceso a datos", false));
		}
		return resu;
	}
	public DataListarAdjudicacionesResponse listarAdjudicacionesdeAportante(int cedula) {
		DataListarAdjudicacionesResponse resu = null;
		try{
			resu = sistema.listarAdjudicacionesDeAportante(cedula);
		}catch ( PersistenciaException e)
		{
			resu = new DataListarAdjudicacionesResponse(null, new DataMensaje("Error de acceso a datos", false));
		}
		return resu;
	}


}
