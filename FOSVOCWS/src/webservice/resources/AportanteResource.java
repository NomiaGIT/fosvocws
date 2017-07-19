package webservice.resources;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import logica.Fachada;

import com.google.gson.Gson;

import datatypes.DataAportante;
import datatypes.DataMensaje;
import datatypes.response.DataAportanteResponse;
import datatypes.response.DataListarAportantesResponse;

@Produces("application/json")
@Path(value = "aportante")
public class AportanteResource {
	 private Fachada sistema ;
	 public  AportanteResource() {
			   sistema = Fachada.getInstancia();
	   }

	@GET
	@Path("{id}")
	public DataAportanteResponse getAportante(@PathParam("id") int documento) {
		return sistema.buscarAportante(documento);
	}
			
	@POST
	@Path("set")
	public  DataMensaje modificarAportante(DataAportante aportante) {		
		return sistema.modificarAportante(aportante);		
	}

   @POST
	@Path("android_set")
	public  DataMensaje modificarAportante(String aportante) {
		Gson gson = new Gson();
		DataAportante apo = gson.fromJson(aportante, DataAportante.class);
		return sistema.modificarAportante(apo);	
	}

   @GET
	@Path("getAll")
	public DataListarAportantesResponse listarAportantes(){
		return sistema.listarAportantes();
	}

}
