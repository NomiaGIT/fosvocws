package persistencia;

import excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class PoolTransacciones {

	// Intancia �nica del Pool de Transacciones
	private static PoolTransacciones instance = null;

	// Conexiones disponibles para ser utilizadas
	private LinkedList<Connection> conexionesLibres;

	// url, user, password de la BD utilizadas para abrir las nuevas conexiones.
	private String url;
	private String user;
	private String password;

	// Cantidad maxima de conexiones permitidas. Si se carga con el -1 significa
	// infinitas conexiones.
	private int cantidadMaxima;

	// Cantidad actual de conexiones establecidas.
	private int cantidadActual;

	// Constructor privado del conjunto de transacciones.
	private PoolTransacciones(String url, String user, String password, String driver, int cantidadMaxima) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		this.cantidadMaxima = cantidadMaxima;
		this.url = url;
		this.user = user;
		this.password = password;
		conexionesLibres = new LinkedList<Connection>();
		Class.forName(driver).newInstance();
		cantidadActual = 0;
	}

	// Metodo encargado de generar las instacias del manejador de transacciones
	public synchronized static PoolTransacciones getInstance(String url, String user, String password, String driver, int cantidadMaxima) throws PersistenciaException {
		try {
			if (instance == null) {
				instance = new PoolTransacciones(url, user, password, driver, cantidadMaxima);
			}
			return instance;
		} catch (InstantiationException e) {
			throw new PersistenciaException("No se pudo crear la instancia del driver");
		} catch (IllegalAccessException e) {
			throw new PersistenciaException("El driver no tiene constructor por defecto");
		} catch (ClassNotFoundException e) {
			throw new PersistenciaException("No se pudo encontrar el driver driver");
		}
	}

	// Crea una nueva transaccion, si no hay conexiones libres se bloqueara
	// hasta que alguna se libere. El mismo se encarga de obtener la transaccion
	// seteando apropidadamente el nivel de transaccionalidad.
	public synchronized Transaccion obtenerTransaccion(int nivelTransaccionalidad) throws PersistenciaException {
		Connection conexion;
		Transaccion t = null;

		// mientras no haya conseguido una transacci�n
		while (t == null) {
			// si hay conexiones libres, devuelvo una de ellas en la nueva
			// transacci�n
			if (conexionesLibres.size() > 0) {
				conexion = (Connection) conexionesLibres.getFirst();
				conexionesLibres.removeFirst();
				t = new Transaccion(conexion, nivelTransaccionalidad);
			} else {
				// si a�n se permiten nuevas conexiones, creo una y la
				// devuelvo en la nueva transacci�n
				if (cantidadActual < cantidadMaxima) {
					try {
						conexion = DriverManager.getConnection(url, user, password);
						cantidadActual++;
						t = new Transaccion(conexion, nivelTransaccionalidad);
					} catch (SQLException e) {
						System.out.println(e.getMessage());
						throw new PersistenciaException("No se pudo crear la conexion a la BD " + e.getMessage());
					}
				} else {
					// espero hasta que alguien devuelva una transacci�n
					try {
						wait();
					} catch (InterruptedException e) {
						// No me importa quien me desperto lo importante es que
						// estoy despierto.
					}
				}
			}
		}

		return t;
	}

	/**
	 * Libera la transaccion para ser reutilizada por el conjunto de transacciones.
	 * 
	 * @param transaccion
	 *            Transaccion a liberar.
	 */
	public synchronized void liberarTransaccion(Transaccion transaccion) throws PersistenciaException {
		conexionesLibres.add(transaccion.getConexion());
		notify();
	}
}