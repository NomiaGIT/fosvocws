package prestamos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;
import persistencia.Transaccion;
import prestamos.datatypes.DataCopago;
import prestamos.datatypes.DataCuota;
import prestamos.datatypes.DataListadoCuotasAPagar;
import utilitarios.Formato;
import utilitarios.NumberToLetterConverter;
import utilitarios.Redondeo;
import logica.monedas.ValoresMoneda;
import logica.productos.refaccion.Refaccion;
import logica.productos.refaccion.Refacciones;
import logica.solicitudes.Solicitud;
import logica.solicitudes.Solicitudes;
import excepciones.PersistenciaException;

public class Cuotas {
	public void ingresar(Transaccion t, Cuota cuotaB, int contratoId) throws PersistenciaException {
		this.insertarCuota(t, cuotaB, contratoId);
	}

	public int cantidadCuotasPorContrato(Transaccion t, int idContrato) throws PersistenciaException {
		int cantCuotas = 0;
		String sql = "SELECT COUNT(id) FROM cuotas WHERE idContrato = ?;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				cantCuotas = rs.getInt(1);
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return cantCuotas;
	}

	public int buscarMesInicio(Transaccion t, int idContrato) throws PersistenciaException {
		int mes = 0;
		Calendar fecha = Calendar.getInstance();
		Calendar fechaAuxiliar = Calendar.getInstance();
		String sql = "SELECT fechaVencimiento FROM cuotas WHERE idContrato =?;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				fechaAuxiliar.setTime(rs.getDate(1));
				if (fechaAuxiliar.before(fecha)) {
					fecha.setTime(fechaAuxiliar.getTime());
				}
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		mes = fecha.get(Calendar.MONTH) + 1;
		return mes;
	}

	public int buscarAnioInicio(Transaccion t, int idContrato) throws PersistenciaException {
		int mes = 0;
		Calendar fecha = Calendar.getInstance();
		Calendar fechaAuxiliar = Calendar.getInstance();
		String sql = "SELECT fechaVencimiento FROM cuotas WHERE idContrato =?;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				fechaAuxiliar.setTime(rs.getDate(1));
				if (fechaAuxiliar.before(fecha)) {
					fecha.setTime(fechaAuxiliar.getTime());
				}
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		mes = fecha.get(Calendar.YEAR);
		return mes;
	}

	public Credito getCredito(Transaccion t, int idContrato) throws PersistenciaException {
		Credito credito = null;
		double montoUI = 0;
		int cantCuotas70PorCiento = 0;
		// Obtengo montoUI
		Vector<Cuota> cuotas = this.buscarCuotasPorIdContrato(t, idContrato);
		for (Iterator<Cuota> iterator = cuotas.iterator(); iterator.hasNext();) {
			Cuota cuota = (Cuota) iterator.next();
			montoUI = cuota.getMontoUI() + montoUI;
		}
		// obtengo cantCuotas70PorCiento
		Refacciones refacciones = new Refacciones();
		Contratos contratos = new Contratos();
		int idSolicitud = contratos.buscarIdSolicitud(t, idContrato);
		Solicitudes solicitudes = new Solicitudes();
		int idPrestamo = solicitudes.buscarIdPrestamo(t, idSolicitud);	
		int cedulaAportante = solicitudes.buscarCedula(t, idSolicitud);
		Refaccion refaccion = refacciones.buscarRefaccion(t, idPrestamo, cedulaAportante);
		cantCuotas70PorCiento = refaccion.getCantCuotasSolicitadas();
		// obtengo valorCuotas70PorCiento
		Cuota primeraCuota = cuotas.firstElement();
		double valorCuotas70PorCiento = primeraCuota.getMontoUI();		
		int idplan = solicitudes.getPlan(t, idSolicitud);
		if (idplan == 1)
			valorCuotas70PorCiento = valorCuotas70PorCiento - 200;
		else if (idplan == 2)
			valorCuotas70PorCiento = valorCuotas70PorCiento - 300;
		// obtengo cantCuotasSaldo
		int totalCuotas = this.cantidadCuotasPorContrato(t, idContrato);
		int cantCuotasSaldo = totalCuotas - cantCuotas70PorCiento;
		// obtengo valorCuotasSaldo
		Cuota ultimaCuota = cuotas.lastElement();
		double valorCuotasSaldo = ultimaCuota.getMontoUI();
		credito = new Credito(idContrato, montoUI, cantCuotas70PorCiento, valorCuotas70PorCiento, cantCuotasSaldo, valorCuotasSaldo, cuotas);
		return credito;
	}

	private Vector<Cuota> buscarCuotasPorIdContrato(Transaccion t, int idContrato) throws PersistenciaException {
		Vector<Cuota> cuotas = new Vector<Cuota>();
		Calendar fechaVencimiento = Calendar.getInstance();
		String sql = "SELECT * FROM cuotas WHERE idContrato = ? ORDER BY fechaVencimiento ASC;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				fechaVencimiento = Calendar.getInstance();
				fechaVencimiento.setTime(rs.getDate(4));
				double monto = rs.getDouble(2);
				Cuota cuota = new Cuota(id, fechaVencimiento, monto);
				cuotas.add(cuota);
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return cuotas;
	}

	public void insertar(Transaccion t, int idContrato, Credito credito) throws PersistenciaException {
		Vector<Cuota> cuotas = credito.getCuotas();
		Iterator<Cuota> iterator = cuotas.iterator();
		while (iterator.hasNext()) {
			Cuota cuota = (Cuota) iterator.next();
			this.insertarCuota(t, cuota, idContrato);			
		}
	}

	public void insertarCuota(Transaccion t, Cuota cuota, int idContrato) throws PersistenciaException {
		String sql = "INSERT INTO cuotas (monto, idContrato, fechaVencimiento, numero) VALUES (?,?,?,?);";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setDouble(1, cuota.getMontoUI());
			prep.setInt(2, idContrato);
			prep.setDate(3, new java.sql.Date(cuota.getFechaVencimiento().getTime().getTime()));
			prep.setInt(4, cuota.getId());
			if (prep.executeUpdate() != 1) {
				throw new PersistenciaException("No ha sido posible realizar la operacion");
			}
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}


	public int cantidadCuotasVencidasPorContrato(Transaccion t, int idContrato) throws PersistenciaException {
		int vencidas = 0;
		Calendar fechaVencimiento = Calendar.getInstance();

		String sql = "SELECT COUNT(id) FROM cuotas WHERE idContrato = ? AND fechaVencimiento < ? AND paga = false;";

		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);
			prep.setDate(2, new java.sql.Date(fechaVencimiento.getTime().getTime()));
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				vencidas = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return vencidas;
	}
	public int cantidadMesesDeAtraso(Transaccion t, int idContrato) throws PersistenciaException {
		int cuantosmeses = 0;
		Calendar fecha = Calendar.getInstance();
		String sql = "SELECT * FROM cuotas WHERE idContrato = ? AND fechaVencimiento < ? AND paga = false;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);
			prep.setDate(2, new java.sql.Date(fecha.getTime().getTime()));
			
			ResultSet rs = prep.executeQuery();
			if (rs.next()) 
			{// solo la primera
				Calendar fechaVencimiento = Calendar.getInstance();
				Date f = rs.getDate("fechaVencimiento");
				fechaVencimiento.setTimeInMillis(f.getTime());
			while(fechaVencimiento.before(fecha))
				{
					fechaVencimiento.add(Calendar.MONTH, 1);
					cuantosmeses++;
				}		
			}
			rs.close();
			prep.close();

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return cuantosmeses;
	}

	public int cantidadCuotasVencidasPorContratoSegunFecha(Transaccion t, int idContrato, Calendar fecha) throws PersistenciaException {
		int vencidas = 0;
		String sql = "SELECT COUNT(id) FROM cuotas WHERE idContrato = ? AND fechaVencimiento < ? AND paga = false;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);
			prep.setDate(2, new java.sql.Date(fecha.getTime().getTime()));
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				vencidas = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return vencidas;
	}

	/**
	 * retorna el vector con cuotas atrasadas, en las que para cada una se calculo \n y se agrego al valor, el monto del recargo a la fecha
	 * ingresada como parametro
	 * 
	 * @param t
	 *            : Transaccion
	 * @param idContrato
	 *            : el id del contrato
	 * @param fecha
	 *            : la fecha para el calculo
	 * @return vector de Cuota. En cada cuota, el monto esta en pesos
	 * @throws PersistenciaException
	 */
	public Vector<Cuota> listarCuotasVencidasPorContratoSegunFecha(Transaccion t, int idContrato, Calendar fecha) throws PersistenciaException {
		Vector<Cuota> vencidas = new Vector<Cuota>();
		String sql = "SELECT * FROM cuotas WHERE idContrato = ? AND fechaVencimiento < ? AND paga = false;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);
			prep.setDate(2, new java.sql.Date(fecha.getTime().getTime()));
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				Calendar fe = Calendar.getInstance();
				fe.setTimeInMillis(rs.getDate(4).getTime());
				int aPesos = this.calcularInteresPorAtraso(t, id, fecha);
				Cuota c = new Cuota(id, fe, aPesos);
				vencidas.add(c);
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return vencidas;
	}

	public Vector<Cuota> listarCuotasSinPagarPorContrato(Transaccion t, int idContrato, Calendar fecha) throws PersistenciaException {
		Vector<Cuota> vencidas = new Vector<Cuota>();
		String sql = "SELECT * FROM cuotas WHERE idContrato = ? AND paga = false;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				Calendar fe = Calendar.getInstance();
				fe.setTimeInMillis(rs.getDate(4).getTime());
				int aPesos = this.calcularInteresPorAtraso(t, id, fecha);
				Cuota c = new Cuota(id, fe, aPesos);
				vencidas.add(c);
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return vencidas;
	}

	// 1/2016 deje de usar este metodo porque no traia bien lso regsitros para
	// dif de cambio. No lo modifico directamente porque no se si afecta otros
	// procesos
	// sustituyo pro el siguiente
	public Vector<Cuota> listarCuotasSinPagarPorContratoAFecha(Transaccion t, int idContrato, Calendar fecha) throws PersistenciaException {
		Vector<Cuota> impagas = new Vector<Cuota>();
		String sql = "SELECT cuotas.* FROM cuotas LEFT JOIN cobros ON  cuotas.id = cobros.idcuota" + " WHERE cuotas.idContrato = ?"
				+ " AND (cuotas.paga = 0 OR (cuotas.paga = 1 AND cobros.fecha >= ?))";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);
			prep.setDate(2, new java.sql.Date(fecha.getTimeInMillis()));
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				Calendar fe = Calendar.getInstance();
				fe.setTimeInMillis(rs.getDate(4).getTime());
				double montoUI = rs.getDouble(2);
				Cuota c = new Cuota(id, fe, montoUI);
				impagas.add(c);
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return impagas;
	}

	// sustituye al anterior 1/12016
	public Vector<Cuota> listarCuotasSinPagarPorContratoAFechaVersion2(Transaccion t, int idContrato, Calendar fecha) throws PersistenciaException {
		Vector<Cuota> impagas = new Vector<Cuota>();
		String sql = "SELECT cuotas.*, cobros.* FROM cuotas LEFT JOIN cobros ON  cuotas.id = cobros.idcuota " + "HAVING cuotas.idContrato = ?  " + "AND (cuotas.paga = 0  "
				+ "OR (cuotas.paga = 1 AND ((cobros.fecha > ? AND cobros.anulado = 0) " + "OR cobros.id IS NULL " + ")))";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);
			prep.setDate(2, new java.sql.Date(fecha.getTimeInMillis()));
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				Calendar fe = Calendar.getInstance();
				fe.setTimeInMillis(rs.getDate(4).getTime());
				double montoUI = rs.getDouble(2);
				Cuota c = new Cuota(id, fe, montoUI);
				impagas.add(c);
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return impagas;
	}

	public Vector<DataListadoCuotasAPagar> listarCuotasPorSolicitud(Transaccion t, int idSolicitud) throws PersistenciaException {
		String consulta = "SELECT * FROM fosvoc.Cuotas WHERE idContrato=?;";
		Vector<DataListadoCuotasAPagar> vector = new Vector<DataListadoCuotasAPagar>();
		Contratos contratos = new Contratos();
		Solicitudes solicitudes = new Solicitudes();
		int idContrato = contratos.buscar(t, idSolicitud).getId();
		ValoresMoneda valores = new ValoresMoneda();
		double valorUI = 0.0;
		try {
			PreparedStatement prep = t.prepareStatement(consulta);
			prep.setInt(1, idContrato);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Calendar fechaVencimientoCuota = Calendar.getInstance();
				String fechaPago;
				idContrato = rs.getInt(3);
				fechaVencimientoCuota.setTime(rs.getDate(4));
				int solicitud = contratos.buscarIdSolicitud(t, idContrato);
				Solicitud dataSolicitud = solicitudes.buscarSolicitudVersion2016(t, solicitud);
				long cedula = dataSolicitud.getAportante().getCedula();
				String nombre = dataSolicitud.getAportante().getNombre();
				String apellido = dataSolicitud.getAportante().getApellidos();
				String Apellido = nombre + " " + apellido;
				double montoUI = rs.getDouble(2);
				String fechaVen = NumberToLetterConverter.convertirCalendar(fechaVencimientoCuota);
				int mes = fechaVencimientoCuota.get(Calendar.MONTH);
				int anio = fechaVencimientoCuota.get(Calendar.YEAR);
				valorUI = valores.buscarValorUI(t, mes, anio);
				double monto$ = Math.round(montoUI * valorUI);
				int numeroCuota = rs.getInt(5);
				boolean pagada = rs.getBoolean(7);
				int idcuota = rs.getInt(1);
				if (pagada)
					fechaPago = "Abonada";
				else
					fechaPago = "No abonada";
				DataListadoCuotasAPagar dataCuota = new DataListadoCuotasAPagar(solicitud, cedula, Apellido, numeroCuota, monto$, montoUI, fechaVen, fechaPago, idcuota);
				vector.add(dataCuota);
			}

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return vector;
	}

	public Vector<DataListadoCuotasAPagar> listarCuotasPorSolicitudVersion2(Transaccion t, int idSolicitud) throws PersistenciaException {

		String consulta = "SELECT * FROM fosvoc.Cuotas WHERE idContrato=?;";
		Vector<DataListadoCuotasAPagar> vector = new Vector<DataListadoCuotasAPagar>();
		Contratos contratos = new Contratos();
		Solicitudes solicitudes = new Solicitudes();
		int idContrato = contratos.buscar(t, idSolicitud).getId();
		try {

			PreparedStatement prep = t.prepareStatement(consulta);
			prep.setInt(1, idContrato);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {

				Calendar fechaVencimientoCuota = Calendar.getInstance();
				String fechaPago;
				idContrato = rs.getInt(3);
				fechaVencimientoCuota.setTime(rs.getDate(4));
				int solicitud = contratos.buscarIdSolicitud(t, idContrato);
				Solicitud dataSolicitud = solicitudes.buscarSolicitudVersion2016(t, solicitud);
				long cedula = dataSolicitud.getAportante().getCedula();
				String nombre = dataSolicitud.getAportante().getNombre();
				String apellido = dataSolicitud.getAportante().getApellidos();
				String Apellido = nombre + " " + apellido;
				double montoUI = rs.getDouble(2);
				String fechaVen = NumberToLetterConverter.convertirCalendar(fechaVencimientoCuota);
				double monto$ = 0;
				int numeroCuota = rs.getInt(5);
				boolean pagada = rs.getBoolean(7);
				int idCuota = rs.getInt(1);
				if (pagada) {

					monto$ = Cobros.darMontoEnPesos(t, idCuota);
					Cuotas cuotas = new Cuotas();
					boolean hayCopa = cuotas.hayCopago(t, idCuota);
					boolean esDeFocer = cuotas.seCobroPorFocer(t, idCuota);
					if (hayCopa && !esDeFocer)
						monto$ = monto$ + cuotas.buscarMontoPorCopago(t, idCuota);
					fechaPago = Cobros.darFechaCobro(t, idCuota);
					if (fechaPago.equals(""))
						fechaPago = "cancelada";
				}

				else
					fechaPago = "No abonada";
				DataListadoCuotasAPagar dataCuota = new DataListadoCuotasAPagar(solicitud, cedula, Apellido, numeroCuota, monto$, montoUI, fechaVen, fechaPago, idCuota);
				vector.add(dataCuota);
			}

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

		return vector;
	}

	public boolean seCobroPorFocer(Transaccion t, int idCuota) throws PersistenciaException {		
		boolean resu = false;
		try {
			String sql = "SELECT idTransaccion FROM cobros where idCuota = ?;";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idCuota);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String que = rs.getString(1);
				if (que.equals("FOCER"))
					resu = true;
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

		return resu;
	}

	public Calendar buscarMesFinal(Transaccion t, int idContrato) throws PersistenciaException {

		Calendar fecha = Calendar.getInstance();
		Calendar fechaAuxiliar = Calendar.getInstance();

		String sql = "SELECT fechaVencimiento FROM cuotas WHERE idContrato =?;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				fechaAuxiliar.setTime(rs.getDate(1));
				if (fechaAuxiliar.after(fecha)) {
					fecha.setTime(fechaAuxiliar.getTime());
				}
			}

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return fecha;
	}

	public static void modificarFechaVencimiento(Transaccion t, int idContrato, Calendar fecha, int numero) throws PersistenciaException {
		String sql = "UPDATE cuotas SET fechaVencimiento = ? where idContrato = ? AND numero = ?;";

		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(2, idContrato);
			prep.setDate(1, new java.sql.Date(fecha.getTime().getTime()));
			prep.setInt(3, numero);

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("error al update");

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

	}

	public static void modificarMonto(Transaccion t, double monto, int idContrato, int numero) throws PersistenciaException {
		String sql = "UPDATE cuotas SET monto = ? where idContrato = ? AND numero = ?;";

		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setDouble(1, monto);
			prep.setInt(2, idContrato);
			prep.setInt(3, numero);

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("error al update");

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

	}

	public static Vector<Integer> listarContratos(Transaccion t) throws PersistenciaException {
		Vector<Integer> vec = new Vector<Integer>();
		String sql = "select distinct idContrato from cuotas;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int a = rs.getInt(1);
				vec.add(a);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

		return vec;
	}

	public static void eliminarCuotas(Transaccion t, int idCOntrato) throws PersistenciaException {
		try {
			String sql = "delete from  cuotas where idContrato = ?;";
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idCOntrato);
			if (prep.executeUpdate() == 0)
				throw new PersistenciaException("No ha sido posible realizar la operación");
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
	}

	public boolean hayPagas(Transaccion t, int idSolicitud) throws PersistenciaException {
		boolean existe = false;
		String sql = "select cobros.id from contratos, cuotas, cobros where contratos.idSolicitud = ? and contratos.id = cuotas.idContrato and cuotas.id = cobros.idCuota;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idSolicitud);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				existe = true;
			}

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

		return existe;
	}

	public boolean obtenerCuotaPagadaPorIdCuota(Transaccion t, int idCuota) throws PersistenciaException {
		boolean resu = false;
		String sql = "Select paga from cuotas where id = ? AND paga IS NOT null;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idCuota);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				resu = rs.getBoolean(1);
			} else
				resu = false;
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return resu;
	}

	public int buscarIdContrato(Transaccion t, int idCuota) throws PersistenciaException {
		int resu = 0;
		String sql = "Select idContrato FROM cuotas WHERE id = ?;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idCuota);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				resu = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return resu;
	}

	public Cuota buscarCuota(Transaccion t, int idCuota) throws PersistenciaException {
		Cuota resu = null;// id, fecha, monto
		String sql = "Select fechaVencimiento, monto FROM cuotas WHERE id = ?;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idCuota);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				Calendar fecha = Calendar.getInstance();
				fecha.setTime(rs.getDate(1));
				double monto = rs.getDouble(2);
				resu = new Cuota(idCuota, fecha, monto);
			}

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

		return resu;
	}

	public Cuota buscarCuota(Transaccion t, int idContrato, int numeroCuota) throws PersistenciaException {
		Cuota resu = null;// id, fecha, monto
		String sql = "Select id, fechaVencimiento, monto FROM cuotas WHERE idContrato = ? AND numero = ?;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);
			prep.setInt(2, numeroCuota);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				int idCuota = rs.getInt(1);
				Calendar fecha = Calendar.getInstance();
				fecha.setTime(rs.getDate(2));
				double monto = rs.getDouble(3);
				resu = new Cuota(idCuota, fecha, monto);
			}

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

		return resu;
	}

	public boolean esUltimaBasica(Transaccion t, int idCuota) throws PersistenciaException {
		boolean resu = false;
		int idContrato;
		try {
			idContrato = this.buscarIdContrato(t, idCuota);
			int cant = this.cantidadCuotasPorContrato(t, idContrato);
			int cantBasica = cant - 6;
			String sql = " SELECT numero FROM cuotas WHERE id = ?;";
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idCuota);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				int cuotaNumero = rs.getInt(1);
				if (cuotaNumero == cantBasica)
					resu = true;
			}

		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());

		}
		return resu;
	}

	public boolean esUltima(Transaccion t, int idCuota) throws PersistenciaException {
		boolean resu = false;
		int idContrato;
		try {
			idContrato = this.buscarIdContrato(t, idCuota);
			int cant = this.cantidadCuotasPorContrato(t, idContrato);

			String sql = " SELECT numero FROM cuotas WHERE id = ?;";
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idCuota);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				int cuotaNumero = rs.getInt(1);
				if (cuotaNumero == cant)
					resu = true;
			}
			rs.close();
			prep.close();
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());

		}
		return resu;
	}

	public boolean esPrimera(Transaccion t, int idCuota) throws PersistenciaException {
		boolean resu = false;

		try {

			String sql = " SELECT numero FROM cuotas WHERE id = ?;";
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idCuota);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				int cuotaNumero = rs.getInt(1);
				if (cuotaNumero == 1)
					resu = true;
			}

		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());

		}
		return resu;
	}

	public double obtenerMontoUltimasCuotas(Transaccion t, int idContrato) throws PersistenciaException {
		int cantCuotas = this.cantidadCuotasPorContrato(t, idContrato);
		Cuota ultima = this.buscarCuota(t, idContrato, cantCuotas);
		double resu = ultima.getMontoUI();
		return resu;
	}

	public double obtenerMontoSubsidio(Transaccion t, int idContrato) throws PersistenciaException {
		int cantCuotas = this.cantidadCuotasPorContrato(t, idContrato);
		Cuota ultima = this.buscarCuota(t, idContrato, cantCuotas);
		double resu = ultima.getMontoUI() * 6;
		return resu;
	}

	public double obtenerMontoSubsidioSacandoCuotasYaVencidas(Transaccion t, int idContrato, Calendar fecha) throws PersistenciaException {
		int cantCuotas = this.cantidadCuotasPorContrato(t, idContrato);
		Cuota ultima = this.buscarCuota(t, idContrato, cantCuotas);
		
		double resu = ultima.getMontoUI() * 6;
		// sacar las que ya estan vencidas
		Cuota primeraSubsidio = this.buscarCuota(t, idContrato, cantCuotas - 5);
		if(primeraSubsidio != null)//agregado 5/16 porque contrato plan 2014 con solo 5 cuotas no cargaba el listado
		{
			for (int i = primeraSubsidio.getId(); i <= ultima.getId(); i++) {
				if (this.estaAtrasada(t, i, fecha))
					resu = resu - this.buscarCuota(t, i).getMontoUI();
			}			
		}else 
		{
            
			resu = 0;
		}
		return resu;
	}
	public double obtenerMontoRestanteSacandoCuotasYaVencidasPlan2014(Transaccion t, int idContrato, Calendar fecha) throws PersistenciaException {
		int cantCuotas = this.cantidadCuotasPorContrato(t, idContrato);
		Cuota primera = this.buscarCuota(t, idContrato, 1);
		Cuota ultima = this.buscarCuota(t, idContrato, cantCuotas);
		System.out.println("contrato "+idContrato+" cant cuotas "+cantCuotas);
		double resu = 0;
		// sacar las que ya estan vencidas
		
		if(primera != null)//agregado 5/16 porque contrato plan 2014 con solo 5 cuotas no cargaba el listado
		{
			for (int i = primera.getId(); i <= ultima.getId(); i++) {
				if (!this.estaAtrasada(t, i, fecha)&& !this.estaPagada(t, i))
					resu = resu + this.buscarCuota(t, i).getMontoUI();
			}			
		}
		return resu;
	}
	public int obtenerMontoSubsidioEnPesos(Transaccion t, int idContrato, Calendar fechaParaCalcular) throws PersistenciaException {
		double cant = this.obtenerMontoSubsidio(t, idContrato);
		ValoresMoneda val = new ValoresMoneda();
		int mes, anio;
		mes = fechaParaCalcular.get(Calendar.MONTH);
		anio = fechaParaCalcular.get(Calendar.YEAR);
		double valorUi = val.buscarValorUI(t, mes, anio);
		int resu = Redondeo.sacarDecimales(cant * valorUi);
		return resu;
	}

	public int obtenerMontoSubsidioEnPesosSacandoCuotasAtrasadas(Transaccion t, int idContrato, Calendar fechaParaCalcular) throws PersistenciaException {
		double cant = this.obtenerMontoSubsidioSacandoCuotasYaVencidas(t, idContrato, fechaParaCalcular);
		ValoresMoneda val = new ValoresMoneda();
		int mes, anio;
		mes = fechaParaCalcular.get(Calendar.MONTH);
		anio = fechaParaCalcular.get(Calendar.YEAR);
		double valorUi = val.buscarValorUI(t, mes, anio);
		int resu = Redondeo.sacarDecimales(cant * valorUi);
		return resu;
	}
	//idem anterior, peor no busca subsidio sino todo lo que no ha vencido
	public int obtenerMontoSubsidioEnPesosSacandoCuotasAtrasadasPlan2014(Transaccion t, int idContrato, Calendar fechaParaCalcular) throws PersistenciaException {
		double cant = this.obtenerMontoRestanteSacandoCuotasYaVencidasPlan2014(t, idContrato, fechaParaCalcular);
		ValoresMoneda val = new ValoresMoneda();
		int mes, anio;
		mes = fechaParaCalcular.get(Calendar.MONTH);
		anio = fechaParaCalcular.get(Calendar.YEAR);
		double valorUi = val.buscarValorUI(t, mes, anio);
		int resu = Redondeo.sacarDecimales(cant * valorUi);
		return resu;
	}

	public void terminarPrestamo(Transaccion t, int idContrato) throws PersistenciaException {
		try {
			String sql = "UPDATE cuotas SET Paga = true WHERE idContrato = ?;";
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);
			prep.executeUpdate();
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public int obtenerIdCuotaNoviembre(Transaccion t, int cedula) throws PersistenciaException {
		int resu = 0;
		try {
			String sql = "select cuotas.id from Cuotas, Solicitudes, Contratos "
					+ "where solicitudes.cedulaAportante = ? and cuotas.fechaVencimiento BETWEEn '2011-11-01' AND '2011-11-30' "
					+ "and contratos.idSolicitud = Solicitudes.id and contratos.id = cuotas.idContrato;";
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, cedula);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				resu = rs.getInt(1);
			}
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public int obtenerIdCuota(Transaccion t, int cedula, int mes, int anio) throws PersistenciaException {
		int resu = 0;
		try {
			Calendar fecha = Calendar.getInstance();
			fecha.set(anio, mes, 1);
			Calendar fechaf = Calendar.getInstance();
			fechaf.set(anio, mes, 25);
			String sql = "select cuotas.id from Cuotas, Solicitudes, Contratos " + "where solicitudes.cedulaAportante = ? and cuotas.fechaVencimiento BETWEEn ? AND ? "
					+ "and contratos.idSolicitud = Solicitudes.id and contratos.id = cuotas.idContrato;";
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, cedula);
			prep.setDate(2, new java.sql.Date(fecha.getTime().getTime()));
			prep.setDate(3, new java.sql.Date(fechaf.getTime().getTime()));
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				resu = rs.getInt(1);
			}
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public void registrarCuotaPagadaDesdeExcel(Transaccion t, int idCuota, double monto, Calendar fecha, String transaccion) throws PersistenciaException {
		String sql = "insert into cobros (fecha, monto, idCuota, idTransaccion,idCobroConcepto) values (?,?,?,?,?);";
		this.marcarComoPagada(t, idCuota);
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			java.sql.Date date = new Date(fecha.getTime().getTime());
			prep.setDate(1, date);
			prep.setDouble(2, monto);
			prep.setInt(3, idCuota);
			prep.setString(4, transaccion);
			prep.setInt(5, 1);
			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No se pudo efectuar el registro de la cuota");
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public void marcarComoPagada(Transaccion t, int idCuota) throws PersistenciaException {
		String sql = "UPDATE cuotas set paga = true WHERE id=?;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idCuota);
			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No se pudo efectuar el registro de la cuota");
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public int getNumeroPorId(Transaccion t, int idCuota) throws PersistenciaException {
		int resu = 0;
		try {
			String sql = "SELECT numero FROM Cuotas WHERE id=?;";
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idCuota);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				resu = rs.getInt(1);
			}
			prep.close();
			rs.close();
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public boolean hayCopago(Transaccion t, int idCuota) throws PersistenciaException {
		boolean resu = false;
		try {
			String sql = "SELECT * FROM Copagos WHERE idCuota=?;";
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idCuota);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				resu = true;
			}
			rs.close();
			prep.close();

		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public double buscarMontoPorCopago(Transaccion t, int idCuota) throws PersistenciaException {
		double resu = 0.0;
		try {
			String sql = "SELECT SUM(monto) FROM Copagos WHERE idCuota=?;";
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idCuota);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				resu = rs.getDouble(1);
			}
			rs.close();
			prep.close();

		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public void agregarCopago(Transaccion t, int idCuota, double monto) throws PersistenciaException {

		try {
			String sql = "INSERT INTO Copagos VALUES (?,?);";
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idCuota);
			prep.setDouble(2, monto);
			prep.executeUpdate();

			prep.close();

		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}

	}

	public boolean estaPagada(Transaccion t, int idCuota) throws PersistenciaException {
		// TODO Auto-generated method stub
		boolean siPagada = false;

		try {
			String sql = "SELECT paga FROM cuotas WHERE id = ?;";

			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idCuota);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				siPagada = rs.getBoolean(1);

			}
			rs.close();
			ps.close();
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return siPagada;
	}

	public int buscarSiguienteSinPagar(Transaccion t, int idCuota) throws PersistenciaException {
		// TODO Auto-generated method stub
		int resu = 0;
		int idContrato = this.buscarIdContrato(t, idCuota);
		String sql = "SELECT id FROM cuotas WHERE idContrato = ? AND paga = 0;";
		try {
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idContrato);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resu = rs.getInt(1);// tomo solo el primero
			}
			rs.close();
			ps.close();
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public boolean esDeSubsidio(Transaccion t, int idCuota) throws PersistenciaException {
		// TODO Auto-generated method stub
		boolean es = false;		
		int idContrato = this.buscarIdContrato(t, idCuota);
		int idsolicitud = this.buscarSolicitud(t, idCuota);
		if(idsolicitud < 9000)
		{
		int cantCuotas = this.cantidadCuotasPorContrato(t, idContrato);
		int numeroCuota = this.getNumeroPorId(t, idCuota);
		int dif = cantCuotas - numeroCuota;
		if (dif < 6)
			es = true;
		}
		return es;
	}

	public boolean estaAtrasada(Transaccion t, int idCuota, Calendar fechaParaComparar) throws PersistenciaException {
		boolean es = false;
		Cuota c = this.buscarCuota(t, idCuota);
		Calendar fechaCuota = c.getFechaVencimiento();
		if (fechaCuota.before(fechaParaComparar))
			es = true;
		return es;
	}

	/**
	 * calcula el monto exacto de una cuota atrasada mas los intereses, en pesos
	 * 
	 * @param t
	 * @param idCuota
	 * @param fechaParaCalcular
	 * @return los pesos de la cuota mas los intereses, al dia de la fecha
	 * @throws PersistenciaException
	 */
	public int calcularInteresPorAtraso(Transaccion t, int idCuota, Calendar fechaParaCalcular) throws PersistenciaException// precondicion:
	// esta
	// atrasada
	{
		double resu = 0;
		int cantidadDiasAtraso = 0;
		Cuota c = this.buscarCuota(t, idCuota);
		double valorCuota = c.getMontoUI();//

		ValoresMoneda val = new ValoresMoneda();
		int mes, anio;
		mes = fechaParaCalcular.get(Calendar.MONTH);
		anio = fechaParaCalcular.get(Calendar.YEAR);

		double valorUi = val.buscarValorUI(t, mes, anio);
		double valorCuotaEnPesos = valorCuota * valorUi;

		Calendar fechaCuota = c.getFechaVencimiento();
		Calendar fechaCuota2 = Calendar.getInstance();
		fechaCuota2.setTime(fechaCuota.getTime());

		while (fechaCuota2.before(fechaParaCalcular)) {
			cantidadDiasAtraso++;
			fechaCuota2.add(Calendar.DAY_OF_MONTH, 1);
		}

		// long diferencia =
		// fechaParaCalcular.getTimeInMillis()-fechaCuota.getTimeInMillis();
		// cantidadDiasAtraso = (int) (diferencia/1000*60*60*24);
		if (cantidadDiasAtraso != 0) {
			resu = valorCuotaEnPesos + (valorCuotaEnPesos * 0.06 / 360) * cantidadDiasAtraso;
		} else
			resu = valorCuotaEnPesos;
		int cant = Redondeo.sacarDecimales(resu);
		return cant;
	}

	public Vector<DataCopago> listarCopagosPendientes(Transaccion t) throws PersistenciaException {
		Vector<DataCopago> resu = new Vector<DataCopago>();
		String consulta = "SELECT copagos.idCuota, copagos.monto,  solicitudes.id, solicitudes.cedulaAportante, " + "usuarios.nombres, usuarios.apellidos"
				+ " FROM copagos, solicitudes, cuotas, contratos, aportantes, usuarios" + " WHERE copagos.idCuota = cuotas.id" + " AND cuotas.idContrato = contratos.id"
				+ " AND contratos.idSolicitud = solicitudes.id" + " AND solicitudes.cedulaAportante = aportantes.cedula" + " AND aportantes.cedula = usuarios.cedula"
				+ " AND cuotas.paga = 0;";
		try {
			PreparedStatement ps = t.prepareStatement(consulta);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int idCuota = rs.getInt(1);
				int monto = rs.getInt(2);
				int idSolicitud = rs.getInt(3);
				int cedulaAportante = rs.getInt(4);
				String nombresAportante = rs.getString(5);
				String apellidoAportante = rs.getString(6);
				DataCopago data = new DataCopago(idCuota, monto, idSolicitud, cedulaAportante, nombresAportante, apellidoAportante);
				resu.add(data);
			}

		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public DataCopago buscarCopagosPendientes(Transaccion t, int idSolicitud) throws PersistenciaException {
		DataCopago resu = null;
		String consulta = "SELECT copagos.idCuota, copagos.monto,  solicitudes.id, solicitudes.cedulaAportante, " + "usuarios.nombres, usuarios.apellidos"
				+ " FROM copagos, solicitudes, cuotas, contratos, aportantes, usuarios" + " WHERE copagos.idCuota = cuotas.id" + " AND cuotas.idContrato = contratos.id"
				+ " AND contratos.idSolicitud = solicitudes.id" + " AND solicitudes.cedulaAportante = aportantes.cedula" + " AND aportantes.cedula = usuarios.cedula"
				+ " AND cuotas.paga = 0 AND solicitudes.id = ?;";
		try {
			PreparedStatement ps = t.prepareStatement(consulta);
			ps.setInt(1, idSolicitud);
			ResultSet rs = ps.executeQuery();
			int monto = 0;

			while (rs.next()) {
				int idCuota = rs.getInt(1);
				monto = monto + rs.getInt(2);
				int idSol = rs.getInt(3);
				int cedulaAportante = rs.getInt(4);
				String nombresAportante = rs.getString(5);
				String apellidoAportante = rs.getString(6);
				resu = new DataCopago(idCuota, monto, idSol, cedulaAportante, nombresAportante, apellidoAportante);
			}

		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public boolean tieneCopagosPendientes(Transaccion t, int idSolicitud) throws PersistenciaException {
		boolean resu = false;
		String consulta = "SELECT copagos.idCuota, copagos.monto,  solicitudes.id, solicitudes.cedulaAportante, " + "usuarios.nombres, usuarios.apellidos"
				+ " FROM copagos, solicitudes, cuotas, contratos, aportantes, usuarios" + " WHERE copagos.idCuota = cuotas.id" + " AND cuotas.idContrato = contratos.id"
				+ " AND contratos.idSolicitud = solicitudes.id" + " AND solicitudes.cedulaAportante = aportantes.cedula" + " AND aportantes.cedula = usuarios.cedula"
				+ " AND cuotas.paga = 0 AND solicitudes.id = ?;";
		try {
			PreparedStatement ps = t.prepareStatement(consulta);
			ps.setInt(1, idSolicitud);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				resu = true;

			}

		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public boolean pagoTodasLasCuotasBasicas(Transaccion t, int idCont) throws PersistenciaException {
		// TODO Auto-generated method stub
		boolean resu = true;
		try {
			Vector<Cuota> cuotas = this.buscarCuotasPorIdContrato(t, idCont);
			int cantBasicas = this.cantidadCuotasPorContrato(t, idCont) - 6;
			int i = 0;
			while (resu && i < cantBasicas) {
				Cuota c = cuotas.elementAt(i);
				if (this.estaPagada(t, c.getId()))
					resu = true;
				else if (!this.estaPagada(t, c.getId()))
					resu = false;
				i++;
			}
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return resu;
	}

	public boolean pagoAlgunaSubsidio(Transaccion t, int idContrato) throws PersistenciaException {
		// TODO Auto-generated method stub
		boolean resu = false;
		int cantCuotas = this.cantidadCuotasPorContrato(t, idContrato);
		Cuota primeraDeSubsidio = this.buscarCuota(t, idContrato, (cantCuotas - 5));
		if (this.estaPagada(t, primeraDeSubsidio.getId()))
			resu = true;
		return resu;
	}

	public double obtenerMontoPagadoPorCuotasSubsidio(Transaccion t, int idContrato) throws PersistenciaException {
		// TODO Auto-generated method stub
		double resu = 0;
		Vector<Cuota> vec = this.listarCuotasDeSubsidio(t, idContrato);
		for (Cuota c : vec) {
			if (this.estaPagada(t, c.getId())) {
				double cantidad = Cobros.darMontoEnPesos(t, c.getId());
				resu = resu + cantidad;
			}
		}
		return resu;
	}

	private Vector<Cuota> listarCuotasDeSubsidio(Transaccion t, int idContrato) throws PersistenciaException {
		// TODO Auto-generated method stub
		Vector<Cuota> resu = new Vector<Cuota>();
		Vector<Cuota> todas = this.buscarCuotasPorIdContrato(t, idContrato);
		for (Cuota cuo : todas) {
			if (this.esDeSubsidio(t, cuo.getId()))
				resu.add(cuo);
		}
		return resu;
	}

	public Cuota buscarSiguienteAPagar(Transaccion t, int idContrato) throws PersistenciaException {
		// TODO Auto-generated method stub
		Cuota resu = null;
		String sql = "SELECT * FROM cuotas where idContrato = ? and paga = false;";
		try {
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idContrato);
			ResultSet rs = ps.executeQuery();
			if (rs.next())// tomo solo la primera
			{
				int id = rs.getInt(1);
				int monto = (int) rs.getDouble(2);
				Calendar fecha = Calendar.getInstance();
				Date f = rs.getDate(4);
				fecha.setTimeInMillis(f.getTime());
				resu = new Cuota(id, fecha, monto);
			}

		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public int obtenerIdUltimaPagada(Transaccion t, int idcontrato) throws PersistenciaException {
		// TODO Auto-generated method stub
		// obtiene el id de la ultima cuota efectivamente pagada, no cancelada
		int id = 0;
		try {
			String consulta = "Select idCuota from cobros where idcuota in (select id from cuotas where idcontrato = ?) ORDER by idcuota desc;";
			PreparedStatement ps = t.prepareStatement(consulta);
			ps.setInt(1, idcontrato);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);// solo el primero me sirve
			}
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return id;
	}

	public Vector<DataListadoCuotasAPagar> buscarCuotas(Transaccion t, int idsol) throws PersistenciaException {
		// TODO Auto-generated method stub
		Vector<DataListadoCuotasAPagar> resu = new Vector<DataListadoCuotasAPagar>();
		/**
		 * private int solicitud; private long cedula; private String Apellido; private int numeroCuota;// el numero de cuota de ese credito
		 * private double monto$; private double montoUI; private String fechaVencimiento; private String fechaPago; PARA ESTA CONSULTA SOLO
		 * PRECISO ALGUNOS DATOS
		 */
		try {
			Calendar hoy = Calendar.getInstance();
			String sql = "SELECT id, numero, monto, fechavencimiento FROM cuotas WHERE idcontrato = (SELECT id from contratos WHERE idsolicitud = ?)";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idsol);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Calendar fecha = Calendar.getInstance();
				int idcuota = rs.getInt(1);
				int numero = rs.getInt(2);
				int monto = (int) Math.round(rs.getDouble(3));
				Date f = rs.getDate(4);
				fecha.setTime(f);
				if (hoy.before(fecha)) {
					DataListadoCuotasAPagar data = new DataListadoCuotasAPagar(idsol, 0, "", numero, 0, monto, Formato.convertirCalendar(fecha), Formato.convertirCalendar(fecha),
							idcuota);
					resu.add(data);
				}
			}
			rs.close();
			ps.close();
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public DataCuota buscarDatosCuota(Transaccion t, int idsolicitud, int numcuota) throws PersistenciaException {
		// TODO Auto-generated method stub
		DataCuota resu = null;
		try {
			String sql = "SELECT * FROM cuotas WHERE numero = ? AND idcontrato = (SELECT id from contratos WHERE idsolicitud = ?)";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, numcuota);
			ps.setInt(2, idsolicitud);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int idcuota = rs.getInt("id");
				int monto = (int) Math.round(rs.getDouble("monto"));
				Date fv = rs.getDate("fechaVencimiento");
				Calendar vencimiento = Calendar.getInstance();
				vencimiento.setTime(fv);
				resu = new DataCuota(idcuota, numcuota, monto, vencimiento);
			}
			rs.close();
			ps.close();
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	// modifica monto y fecha venc
	public void modificar(Transaccion t, DataCuota dc) throws PersistenciaException {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE cuotas SET monto = ?, fechavencimiento = ? WHERE id = ?;";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setDouble(1, dc.getMonto());
			java.sql.Date fd = new java.sql.Date(dc.getVencimiento().getTimeInMillis());
			ps.setDate(2, fd);
			ps.setInt(3, dc.getIdcuota());
			ps.executeUpdate();
			ps.close();
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public int obteneridcontratoporidcuota(Transaccion t, int idcuota) throws PersistenciaException {
		// TODO Auto-generated method stub
		int resu = 0;
		try {
			String sql = "SELECT idcontrato FROM cuotas WHERE id = ?;";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idcuota);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resu = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public int buscarSolicitud(Transaccion t, int idcuota) throws PersistenciaException {
		// TODO Auto-generated method stub
		int idsolicitud = 0;
		try {
			String sql = "SELECT idsolicitud from contratos where id = (Select idcontrato FROM cuotas WHERE id = ?)";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idcuota);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				idsolicitud = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return idsolicitud;
	}

	public double cuantoQuedaPorPagar(Transaccion t, int numSolicitud) throws PersistenciaException {
		double resu = 0.0;
		try {
			String sql = "SELECT SUM(monto) from cuotas Where paga = 0 AND  idcontrato = (select id from contratos where idsolicitud = ?)";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, numSolicitud);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resu = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public boolean existenCopagosAntesDe(Transaccion t, int idcontrato, Calendar fechaHasta) throws PersistenciaException {
		// TODO Auto-generated method stub
		boolean resu = false;
		try {
			String sql = "SELECT * FROM copagos WHERE idcuota IN (Select id from cuotas where idcontrato = ? And idcuota IN (select idcuota FROM cobros WHERE fecha <= ?))";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idcontrato);
			ps.setDate(2, new java.sql.Date(fechaHasta.getTimeInMillis()));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resu = true;
			}

		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return resu;
	}

	public boolean existenCopagosAnterioresSinAsignar(Transaccion t, int idcontrato, Calendar fechaHasta) throws PersistenciaException {
		// TODO Auto-generated method stub
		boolean resu = false;
		try {
			String sql = "SELECT * FROM asientos  where comentario LIKE 'Asiento por copago de cuota%' AND fecha <= ? AND identificador = (select idsolicitud from contratos where id = ?)";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(2, idcontrato);
			ps.setDate(1, new java.sql.Date(fechaHasta.getTimeInMillis()));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resu = true;
			}
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return resu;
	}

	public int buscarTotalCopagos(Transaccion t, int idcontrato, Calendar fechaHasta) throws PersistenciaException {
		// TODO Auto-generated method stub
		int resu = 0;
		try {
			String sql = "SELECT * FROM copagos WHERE idcuota IN (Select id from cuotas where idcontrato = ? And idcuota IN (select idcuota FROM cobros WHERE fecha <= ?)) ";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idcontrato);
			ps.setDate(2, new java.sql.Date(fechaHasta.getTimeInMillis()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resu = resu + rs.getInt("monto");
			}
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return resu;
	}

	public Vector<DataCuota> listarCobrosDespuesDe(Transaccion t, Calendar fechaHasta, int idcontrato) throws PersistenciaException {
		Vector<DataCuota> resu = new Vector<DataCuota>();
		String sql = "SELECT * FROM cuotas WHERE idcontrato = ? AND (paga = 0  OR ( id IN (Select idcuota from cobros WHERE fecha > ?) OR (id NOT IN (SELECT idcuota from cobros))))";
		// "Select * from cobros WHERE fecha <= ? AND idcuota IN (select id from cuotas where idcontrato = ?)";
		try {
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idcontrato);
			java.sql.Date fecha = new java.sql.Date(fechaHasta.getTimeInMillis());
			ps.setDate(2, fecha);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int idcuota = rs.getInt(1);
				int numerocuota = rs.getInt(5);
				double monto = rs.getDouble(2);
				Calendar f = Calendar.getInstance();
				f.setTimeInMillis(rs.getDate(4).getTime());
				DataCuota dc = new DataCuota(idcuota, numerocuota, monto, f);
				resu.add(dc);
			}

		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return resu;
	}

	// metodo accesorio para listar las cuotas impagas a determinada fecha, pero
	// para listado de expectativas decobranza
	// EN ESTE SOLO LAS IMPAGAS A LA FECHA Y CON FECHA DE VENCIMIENTO ANTERIOR A
	// LA DADA
	public Vector<DataListadoCuotasAPagar> listarVencimientosHastaMesSINPAGARHASTAAHORA(Transaccion t, int dia, int mes, int anio) throws PersistenciaException {

		Vector<DataListadoCuotasAPagar> lista = new Vector<DataListadoCuotasAPagar>();
		Calendar fechaVencimiento = Calendar.getInstance();
		fechaVencimiento.set(Calendar.DAY_OF_MONTH, dia);
		fechaVencimiento.set(Calendar.MONTH, mes);
		fechaVencimiento.set(Calendar.YEAR, anio);

		String sql = "SELECT solicitudes.id, UPPER(usuarios.nombres), " + "UPPER(usuarios.apellidos), usuarios.cedula, solicitudes.estado, "
				+ " cuotas.id as cuota, cuotas.fechaVencimiento, contratos.id , cuotas.monto, cuotas.numero" + " FROM cuotas, contratos, solicitudes, usuarios "
				+ " WHERE (cuotas.paga = '0' " + " AND cuotas.fechaVencimiento < ? )" + " AND cuotas.idContrato = contratos.id " + " AND contratos.idSolicitud = solicitudes.id "
				+ " AND solicitudes.cedulaAportante = usuarios.cedula " + " GROUP BY cuotas.id " + " ORDER BY solicitudes.id ASC, cuotas.id ASC ;";

		int idContrato = 0;
		Calendar fechaVencimientoCuota = Calendar.getInstance();
		// int numero = 0;
		int numeroSolicitud = 0;
		Contratos contratos = new Contratos();
		int documento = 0;
		String nombre = null;
		String apellido = null;
		String mesVencido = "";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setDate(1, new java.sql.Date(fechaVencimiento.getTime().getTime()));

			ResultSet rs = prep.executeQuery();
			while (rs.next()) {

				idContrato = rs.getInt(8);
				fechaVencimientoCuota.setTime(rs.getDate(7));
				numeroSolicitud = contratos.buscarIdSolicitud(t, idContrato);

				documento = rs.getInt(4);
				nombre = rs.getString(2);
				apellido = rs.getString(3);
				int idcuota = rs.getInt(6);
				double montoUi = rs.getDouble(9);
				int numeroCuota = rs.getInt(10);

				DecimalFormat df = new DecimalFormat("#.##");
				df.format(montoUi);
				montoUi = Redondeo.redondear(montoUi);
				mesVencido = (fechaVencimientoCuota.get(Calendar.MONTH) + 1) + " / " + fechaVencimientoCuota.get(Calendar.YEAR);
				double monto$ = this.calcularInteresPorAtraso(t, idcuota, fechaVencimiento);
				DataListadoCuotasAPagar dataCuota = new DataListadoCuotasAPagar(numeroSolicitud, documento, nombre + " " + apellido, numeroCuota, monto$, montoUi, mesVencido,
						"sin abonar", idcuota);
				/*
				 * numeroSolicitud, documento, nombre, apellido, mesVencido, cantidadCuotasVencidas, estadoSolicitud);
				 */
				lista.add(dataCuota);
			}

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

		return lista;
	}

	// metodo accesorio para listar las cuotas impagas a determianda fecha, pero
	// para listado de expectativas d ecobranza
	// EN ESTE SOLO LAS PAGADAS DESPUES Y CON FECHA DE VENCIMIENTO ANTERIOR A LA
	// DADA
	public Vector<DataListadoCuotasAPagar> listarVencimientosHastaMesPAGADASDESPUES(Transaccion t, int dia, int mes, int anio) throws PersistenciaException {

		Vector<DataListadoCuotasAPagar> lista = new Vector<DataListadoCuotasAPagar>();
		Calendar fechaVencimiento = Calendar.getInstance();
		fechaVencimiento.set(Calendar.DAY_OF_MONTH, dia);
		fechaVencimiento.set(Calendar.MONTH, mes);
		fechaVencimiento.set(Calendar.YEAR, anio);

		String sql = "SELECT solicitudes.id, UPPER(usuarios.nombres), " + "UPPER(usuarios.apellidos), usuarios.cedula, solicitudes.estado, "
				+ " cuotas.id as cuota, cuotas.fechaVencimiento, contratos.id , cuotas.monto, cuotas.numero" + " FROM cuotas, contratos, solicitudes, usuarios, cobros "
				+ " WHERE (cuotas.fechaVencimiento < ? AND cuotas.paga = 1 AND cuotas.id = cobros.idcuota AND cobros.fecha > ?)" + " AND cuotas.idContrato = contratos.id "
				+ " AND contratos.idSolicitud = solicitudes.id " + " AND solicitudes.cedulaAportante = usuarios.cedula " + " GROUP BY cuotas.id "
				+ " ORDER BY solicitudes.id ASC, cuotas.id ASC ;";

		int idContrato = 0;
		Calendar fechaVencimientoCuota = Calendar.getInstance();
		// int numero = 0;
		int numeroSolicitud = 0;
		Contratos contratos = new Contratos();
		int documento = 0;
		String nombre = null;
		String apellido = null;
		String mesVencido = "";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setDate(1, new java.sql.Date(fechaVencimiento.getTime().getTime()));
			prep.setDate(2, new java.sql.Date(fechaVencimiento.getTime().getTime()));

			ResultSet rs = prep.executeQuery();
			while (rs.next()) {

				idContrato = rs.getInt(8);
				fechaVencimientoCuota.setTime(rs.getDate(7));
				numeroSolicitud = contratos.buscarIdSolicitud(t, idContrato);

				documento = rs.getInt(4);
				nombre = rs.getString(2);
				apellido = rs.getString(3);
				int idcuota = rs.getInt(6);
				double montoUi = rs.getDouble(9);
				int numeroCuota = rs.getInt(10);
				DecimalFormat df = new DecimalFormat("#.##");
				df.format(montoUi);
				montoUi = Redondeo.redondear(montoUi);
				mesVencido = (fechaVencimientoCuota.get(Calendar.MONTH) + 1) + " / " + fechaVencimientoCuota.get(Calendar.YEAR);
				double monto$ = this.calcularInteresPorAtraso(t, idcuota, fechaVencimiento);
				DataListadoCuotasAPagar dataCuota = new DataListadoCuotasAPagar(numeroSolicitud, documento, nombre + " " + apellido, numeroCuota, monto$, montoUi, mesVencido,
						"abonada despues", idcuota);
				/*
				 * numeroSolicitud, documento, nombre, apellido, mesVencido, cantidadCuotasVencidas, estadoSolicitud);
				 */
				lista.add(dataCuota);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return lista;
	}

	public boolean tieneCopagosPendientesPorIdCuota(Transaccion t, int idprimeracuotasinpagar) throws PersistenciaException {
		boolean resu = false;
		String consulta = "SELECT copagos.* FROM copagos WHERE idcuota = ?;";
		try {
			PreparedStatement ps = t.prepareStatement(consulta);
			ps.setInt(1, idprimeracuotasinpagar);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resu = true;
			}
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}
	
	public int buscarCopagosPendientesPorIdCuota(Transaccion t, int idprimeracuotasinpagar) throws PersistenciaException {

		int resu = 0;
		String consulta = "SELECT SUM(copagos.monto) FROM copagos WHERE copagos.idCuota = ?;";
		try {
			PreparedStatement ps = t.prepareStatement(consulta);
			ps.setInt(1, idprimeracuotasinpagar);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resu = rs.getInt(1);
			}
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public static int contarCuantasVencenEsteMes(Transaccion t) throws PersistenciaException{
		int resu = 0;
		try
		{
			String sql = "SELECT COUNT(*) FROM cuotas WHERE YEAR(fechavencimiento) = YEAR(CURRENT_DATE()) AND "+
                         "MONTH(fechavencimiento) = MONTH(CURRENT_DATE())";
			PreparedStatement ps = t.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				resu = rs.getInt(1);
		}catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public static Vector<String> listarIdVencenEsteMes(Transaccion t)throws PersistenciaException {
        Vector<String> resu = new Vector<String>();
        try
		{
			String sql = "SELECT contratos.idsolicitud FROM contratos, cuotas WHERE contratos.id = cuotas.idcontrato AND "
					+ "YEAR(cuotas.fechavencimiento) = YEAR(CURRENT_DATE()) AND "+
                         "MONTH(cuotas.fechavencimiento) = MONTH(CURRENT_DATE())";
			PreparedStatement ps = t.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				resu.add( rs.getInt(1)+"");
		}catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}
}