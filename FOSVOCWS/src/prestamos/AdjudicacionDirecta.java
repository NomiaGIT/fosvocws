package prestamos;

import java.io.Serializable;
import java.util.Calendar;

//
@SuppressWarnings("serial")
public class AdjudicacionDirecta extends ModoAdjudicacion implements Serializable {

	//
	private String usuarioAutoriza;

	//
	private Calendar fecha;

	//
	private String motivo;

	/**
	 * @param usuarioAutoriza
	 * @param fecha
	 * @param motivo
	 */
	public AdjudicacionDirecta(String usuarioAutoriza, Calendar fecha, String motivo) {
		this.usuarioAutoriza = usuarioAutoriza;
		this.fecha = fecha;
		this.motivo = motivo;
	}

	/**
	 * @return the usuarioAutoriza
	 */
	public String getUsuarioAutoriza() {
		return usuarioAutoriza;
	}

	/**
	 * @param usuarioAutoriza
	 *            the usuarioAutoriza to set
	 */
	public void setUsuarioAutoriza(String usuarioAutoriza) {
		this.usuarioAutoriza = usuarioAutoriza;
	}

	/**
	 * @return the fecha
	 */
	public Calendar getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * @param motivo
	 *            the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}