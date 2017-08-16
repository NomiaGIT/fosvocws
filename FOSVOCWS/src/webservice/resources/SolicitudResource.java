package webservice.resources;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import datatypes.response.DataListarSolicitudesResponse;
import datatypes.response.DataSolicitudResponse;
import logica.Fachada;

@Produces("application/json")
@Path(value = "solicitud")
public class SolicitudResource {
	private Fachada sistema ;
	 public  SolicitudResource() {
			   sistema = Fachada.getInstancia();
	   }
	   @GET
		@Path("{id}") 
		public DataSolicitudResponse getSolicitud(@PathParam("id") int id) {
			return sistema.buscarSolicitud(id);
		}

	   @GET
		@Path("getAll")
		public DataListarSolicitudesResponse listarSolicitudes(){
			return sistema.listarSolicitudes();
		}
	   @GET
		@Path("getAllFrom/{cedula}")
		public DataListarSolicitudesResponse listarSolicitudesdeAportante(@PathParam("cedula")int cedula){
			return sistema.listarSolicitudesdeAportante(cedula);
		}
	   @GET
		@Path("getActiveFrom/{cedula}") 
		public DataListarSolicitudesResponse getSolicitudesActivasDe(@PathParam("cedula") int cedula) {
			return sistema.buscarSolicitudesActivas(cedula);
		}
}
