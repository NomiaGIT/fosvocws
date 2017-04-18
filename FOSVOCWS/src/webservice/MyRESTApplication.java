package webservice;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import webservice.resources.DestinoTourResource;
import webservice.resources.EnumeradosResource;
import webservice.resources.FileResource;
import webservice.resources.LugarResource;
import webservice.resources.ReservaResource;
import webservice.resources.TipoTourResource;
import webservice.resources.TourResource;
import webservice.resources.TransporteResurce;
import webservice.resources.UsuarioResource;

public class MyRESTApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	public MyRESTApplication(){
	     singletons.add(new UsuarioResource());
	     singletons.add(new TipoTourResource());
	     singletons.add(new TourResource());
	     singletons.add(new EnumeradosResource());
	     singletons.add(new DestinoTourResource());
	     singletons.add(new ReservaResource());
	     singletons.add(new TransporteResurce());
	     singletons.add(new LugarResource());
	     singletons.add(new FileResource());
	}
	@Override
	public Set<Class<?>> getClasses() {
	     return empty;
	}
	@Override
	public Set<Object> getSingletons() {
	     return singletons;
	}
}
