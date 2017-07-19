package webservice.resources;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import datatypes.response.DataAdjudicacionResponse;
import datatypes.response.DataListarAdjudicacionesResponse;
import logica.Fachada;

@Produces("application/json")
@Path(value = "prestamo")
public class PrestamoResource {
	private Fachada sistema ;
	 public  PrestamoResource() {
			   sistema = Fachada.getInstancia();
	   }
	 @GET
		@Path("{id}") 
		public DataAdjudicacionResponse getSolicitud(@PathParam("id") int id) {
			return sistema.buscarAdjudicacion(id);
		}

	   @GET
		@Path("getAll")
		public DataListarAdjudicacionesResponse listarAdjudicaciones(){
			return sistema.listarAdjudicaciones();
		}
	   @GET
		@Path("getAllFrom/{cedula}")
		public DataListarAdjudicacionesResponse listarAdjudicacionesdeAportante(@PathParam("cedula")int cedula){
			return sistema.listarAdjudicacionesdeAportante(cedula);
		}

}
