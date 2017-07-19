package logica.productos.refaccion;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DataProveedor implements Serializable {
	private int id;
	private String nombreProveedor;

	public DataProveedor(int id, String nombreProveedor) {
		this.id = id;
		this.nombreProveedor = nombreProveedor;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreProveedor() {
		return this.nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

}
