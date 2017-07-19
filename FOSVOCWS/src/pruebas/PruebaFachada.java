package pruebas;

import java.util.Vector;

import datatypes.DataListarAportantes;
import datatypes.response.DataListarAportantesResponse;
import logica.Fachada;

public class PruebaFachada {

	public static void main(String[] args) {
		Fachada fachada = Fachada.getInstancia();
		DataListarAportantesResponse data = fachada.listarAportantes();
		System.out.println(data.getDm().getMensaje()+data.getDm().isOk());
		
		Vector<DataListarAportantes> d = data.getLista();
		System.out.println("***********");
        for( DataListarAportantes dla : d)
        {
        	System.out.println(dla.getApellido());
        }
	}

}
