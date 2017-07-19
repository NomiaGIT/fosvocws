package prestamos;

//
public class SolicitudesParticipantes {

	private Sorteo sorteo;

	/**
	 * @param sorteo
	 */
	public SolicitudesParticipantes(Sorteo sorteo) {
		this.sorteo = sorteo;
	}

	/**
	 * @return the sorteo
	 */
	public Sorteo getSorteo() {
		return sorteo;
	}

	/**
	 * @param sorteo
	 *            the sorteo to set
	 */
	public void setSorteo(Sorteo sorteo) {
		this.sorteo = sorteo;
	}

}