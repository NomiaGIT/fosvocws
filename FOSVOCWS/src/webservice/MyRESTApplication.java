package webservice;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import webservice.resources.AportanteResource;
import webservice.resources.PrestamoResource;
import webservice.resources.SolicitudResource;
import webservice.resources.UsuarioResource;


public class MyRESTApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	public MyRESTApplication(){
	     singletons.add(new UsuarioResource());
	     singletons.add(new AportanteResource());
	     singletons.add(new SolicitudResource());
	     singletons.add(new PrestamoResource());	     
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
