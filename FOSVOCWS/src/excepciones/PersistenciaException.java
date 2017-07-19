/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package excepciones;

/**
 * 
 * @author Sole
 */
@SuppressWarnings("serial")
public class PersistenciaException extends Exception {
	private String mensaje;

	public PersistenciaException(String mens) {
		this.mensaje = mens;
	}

	public String getMensaje() {
		return mensaje;
	}

}
