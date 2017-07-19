package logica;


import excepciones.PersistenciaException;


import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/*    */
/*    */public class LectorParametros
/*    */{
	private String rutaArchivo;
	private String urlBase;
	private String usuarioBase;
	private String contraseniaBase;
	private String driverBase;
	private int cantConexiones;
	private String nombreObjetoRMI;
	private String rutaRMI;
	private String idEmpresaRedPagos;
	private String nombreObjetoRMIPrestamos;
	private String nombreObjetoRMIParametros;
	private int version;

	public LectorParametros(String ruta) throws PersistenciaException {

		//		 /*REST SERVER
		try {

			this.rutaArchivo = ruta;
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("config/datos.properties");
			Properties prop = new Properties();
			prop.load(input);	
			
			this.rutaArchivo = prop.getProperty("rutaArchivo");
			this.urlBase = prop.getProperty("urlBase");
			this.usuarioBase = prop.getProperty("usuarioBase");
			this.contraseniaBase = prop.getProperty("contraseniaBase");
			this.driverBase = prop.getProperty("driverBase");
			this.cantConexiones = Integer.parseInt(prop.getProperty("cantConexiones"));
			this.nombreObjetoRMI = prop.getProperty("nombreObjetoRMI");
			this.rutaRMI = prop.getProperty("rutaRMI");
			this.idEmpresaRedPagos = prop.getProperty("idEmpresaRedPagos");
			this.nombreObjetoRMIPrestamos = prop.getProperty("nombreObjetoRMIPrestamos");
			this.nombreObjetoRMIParametros = prop.getProperty("nombreObjetoRMIParametros");
			this.version = Integer.parseInt(prop.getProperty("version"));

		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
			throw new PersistenciaException(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			throw new PersistenciaException(ex.getMessage());
		}
	}

	


	/**
	 * @return the idEmpresaRedPagos
	 */
	public String getIdEmpresaRedPagos() {
		return idEmpresaRedPagos;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void setVersion(int version) throws FileNotFoundException, IOException {
		this.version = version;
		Properties prop = new Properties();
		prop.load(new FileInputStream("C:\\fosvoc\\datos.txt"));
		prop.setProperty("version", version + "");
		prop.store(new FileOutputStream("C:\\fosvoc\\datos.txt"), null);
	}

	/**
	 * @param idEmpresaRedPagos
	 *            the idEmpresaRedPagos to set
	 */
	public void setIdEmpresaRedPagos(String idEmpresaRedPagos) {
		this.idEmpresaRedPagos = idEmpresaRedPagos;
	}

	
	public int getCantConexiones() {
		return this.cantConexiones;
	}

	
	public String getContraseniaBase() {
		return this.contraseniaBase;
	}

public String getDriverBase() {
		return this.driverBase;
	}

	public String getNombreObjetoRMI() {
		return this.nombreObjetoRMI;
	}

	
	public String getRutaArchivo() {
		return this.rutaArchivo;
	}

	public String getRutaRMI() {
		return this.rutaRMI;
	}

	public String getUrlBase() {
		return this.urlBase;
}

	
	public String getUsuarioBase() {
		return this.usuarioBase;
	}

	public String getNombreObjetoRMIPrestamos() {
		return this.nombreObjetoRMIPrestamos;
	}

	/**
	 * @return the nombreObjetoRMIParametros
	 */
	public String getNombreObjetoRMIParametros() {
		return nombreObjetoRMIParametros;
	}

	/**
	 * @param nombreObjetoRMIParametros
	 *            the nombreObjetoRMIParametros to set
	 */
	public void setNombreObjetoRMIParametros(String nombreObjetoRMIParametros) {
		this.nombreObjetoRMIParametros = nombreObjetoRMIParametros;
	}

}

