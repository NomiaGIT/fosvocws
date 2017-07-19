package logica.productos.refaccion;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DataCotizacion implements Serializable {
	private Cotizacion cot;
	private int id;

	public DataCotizacion(Cotizacion cot, int id) {
		this.cot = cot;
		this.id = id;
	}

	public Cotizacion getCot() {
		return this.cot;
	}

	public void setCot(Cotizacion cot) {
		this.cot = cot;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
