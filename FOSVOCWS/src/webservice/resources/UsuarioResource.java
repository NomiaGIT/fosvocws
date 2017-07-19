package webservice.resources;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import logica.Fachada;

import com.google.gson.Gson;

import datatypes.DataContrasenia;
import datatypes.DataLoginIn;
import datatypes.DataMensaje;
import datatypes.DataUsuario;
import datatypes.response.DataUsuarioLoginResponse;
import datatypes.response.DataUsuarioResponse;


@Produces("application/json")
@Path(value = "usuario")
public class UsuarioResource {
	 private Fachada fachada ;
	 public  UsuarioResource() {
			   fachada = Fachada.getInstancia();
	   }

	@GET
	@Path("{id}")
	public DataUsuarioResponse getUsuario(@PathParam("id") int id) {
		return fachada.buscarUsuario(id);
	}
	@GET
	@Path("get/{nick}")
	public  DataUsuarioResponse getUsuario(@PathParam("nick") String nick) {
		return fachada.buscarPorNickUsuario(nick);
	}
	@POST
	@Path("loginAutogestion")
	public DataUsuarioLoginResponse loginTrabajador(DataLoginIn data) {			
		return fachada.loginTrabajador(data);
	}
	@POST
	@Path("android_login")
	public DataUsuarioLoginResponse login(String data) {
		 Gson gson = new Gson();
		 DataLoginIn r = gson.fromJson(data, DataLoginIn.class);
		return fachada.login(r);
	}
		
	@POST
	@Path("set")
	public  DataUsuarioResponse modificarUsuario(DataUsuario usuario) {		
		return fachada.modificarUsuario(usuario);		
	}

    @POST
	@Path("android_set")
	public  DataUsuarioResponse modificarUsuario(String usuario) {
		Gson gson = new Gson();
		DataUsuario usu = gson.fromJson(usuario, DataUsuario.class);
		return fachada.modificarUsuario(usu);	
	}

	@POST
	@Path("set_pass")
	public DataMensaje modificarContrasenia(DataContrasenia data) {			
		return fachada.modificarContrasenia(data);		
	}
	@POST
	@Path("android_set_pass")
	@Consumes("application/json")
	public DataMensaje modificarContrasenia(String data) {	
		Gson gson = new Gson();
		DataContrasenia d = gson.fromJson(data, DataContrasenia.class);
		return fachada.modificarContrasenia(d);		
	}
	
}