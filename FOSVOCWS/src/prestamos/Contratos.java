package prestamos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;
import persistencia.Transaccion;
import prestamos.datatypes.DataContrato;
import excepciones.PersistenciaException;

public class Contratos {

	// metodo que retorna el id del contrato recien ingresado
	public int ingresar(Transaccion t, Contrato contrato, int idSolicitud) throws PersistenciaException {
		ResultSet rs = null;
		int idContrato = 0;
		java.sql.Date fecha = new java.sql.Date(contrato.getFecha().getTime().getTime());
		String sql = "INSERT INTO Contratos (fecha, idSolicitud) VALUES (?,?);";
		try {
			PreparedStatement prep = t.prepareStatement(sql);

			prep.setDate(1, fecha);
			prep.setInt(2, idSolicitud);
			if (prep.executeUpdate() != 1) {
				throw new PersistenciaException("No ha sido posible realizar la operación");
			}
			rs = prep.getGeneratedKeys();
			if (rs.next()) {
				idContrato = rs.getInt(1);
			}
			rs.close();
			prep.close();

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje() + "CONTRATOS");
		}
		return idContrato;
	}

	public Contrato buscar(Transaccion t, int idSolicitud) throws PersistenciaException {

		Contrato contrato = null;
		int idContrato = 0;
		Calendar fecha = Calendar.getInstance();
		int cantCuotas = 0;
		int mesInicio = 0;
		int anioInicio = 0;

		Credito credito = null;
		Vales vales = new Vales();
		Vale vale = null;
		OrdenesDeCompra ordenesDeCompra = new OrdenesDeCompra();
		OrdenDeCompra ordenDeCompra = null;
		Cuotas cuotas = new Cuotas();
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * FROM Contratos WHERE idSolicitud = ?;");

			prep.setInt(1, idSolicitud);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				idContrato = rs.getInt(1);
				fecha.setTime(rs.getDate(2));
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		cantCuotas = cuotas.cantidadCuotasPorContrato(t, idContrato);
		mesInicio = cuotas.buscarMesInicio(t, idContrato);
		anioInicio = cuotas.buscarAnioInicio(t, idContrato);
		credito = cuotas.getCredito(t, idContrato);
		vale = vales.buscar(t, idContrato);
		ordenDeCompra = ordenesDeCompra.buscar(t, idContrato);
		contrato = new Contrato(idContrato, fecha, cantCuotas, mesInicio, anioInicio, credito, vale, ordenDeCompra);
		return contrato;
	}

	public int buscarIdSolicitud(Transaccion t, int idContrato) throws PersistenciaException {
		int idSolicitud = 0;
		try {
			PreparedStatement prep = t.prepareStatement("SELECT idSolicitud FROM Contratos WHERE id = ?;");
			prep.setInt(1, idContrato);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				idSolicitud = rs.getInt(1);
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return idSolicitud;
	}

	public static void eliminarContrato(Transaccion t, int idCOntrato) throws PersistenciaException {
		try {
			String sql = "delete from  contratos where id = ?;";
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

	public boolean estaAlDia(Transaccion t, int idContrato) throws PersistenciaException {
		boolean resu = false;
		try {
			String sql = "SELECT estaAlDia from  contratos where id = ?;";
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				resu = rs.getBoolean(1);
			}
			prep.close();

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return resu;
	}

	public void registrarDeudor(Transaccion t, int idContrato) throws PersistenciaException {
		try {
			String sql = "UPDATE contratos SET estaAlDia = false where id = ?;";
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);
			prep.executeUpdate();
			prep.close();

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

	}

	public Vector<Vector<Integer>> listarContratosAtrasados(Transaccion t, int cantCuotasAtrasadas, Calendar fecha) throws PersistenciaException {
		Cuotas cuotas = new Cuotas();
		Vector<Vector<Integer>> resu = new Vector<Vector<Integer>>();
		String sql = "SELECT id, idSolicitud FROM Contratos WHERE estaAlDia = 0";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				int idSol = rs.getInt(2);
				Vector<Integer> v = new Vector<Integer>();
				v.add(id);
				v.add(idSol);
				if (cuotas.cantidadCuotasVencidasPorContratoSegunFecha(t, id, fecha) == cantCuotasAtrasadas)
					resu.add(v);// se listan los que tengan exactamente x cuotas
								// atrasadas
			}
			rs.close();
			prep.close();

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return resu;
	}

	public Vector<Vector<Integer>> listarContratosAtrasados(Transaccion t, int cantCuotasAtrasadas, Calendar fecha, int desde, int hasta) throws PersistenciaException {
		Cuotas cuotas = new Cuotas();
		Vector<Vector<Integer>> resu = new Vector<Vector<Integer>>();
		String sql = "SELECT id, idSolicitud FROM Contratos WHERE estaAlDia = 0 AND (idSolicitud >= ? AND idSolicitud <=?);";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, desde);
			prep.setInt(2, hasta);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				int idSol = rs.getInt(2);
				Vector<Integer> v = new Vector<Integer>();
				v.add(id);
				v.add(idSol);
				if (cuotas.cantidadCuotasVencidasPorContratoSegunFecha(t, id, fecha) == cantCuotasAtrasadas)
					resu.add(v);// se listan los que tengan exactamente x cuotas
								// atrasadas
			}
			rs.close();
			prep.close();

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return resu;
	}

	public Vector<Vector<Integer>> listarContratosAtrasados(Transaccion t, Calendar fecha, int desde, int hasta) throws PersistenciaException {
		Vector<Vector<Integer>> resu = new Vector<Vector<Integer>>();
		String sql = "SELECT id, idSolicitud FROM Contratos WHERE estaAlDia = 0  AND (idSolicitud >= ? AND idSolicitud <=?)";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, desde);
			prep.setInt(2, hasta);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				int idSol = rs.getInt(2);
				Vector<Integer> v = new Vector<Integer>();
				v.add(id);
				v.add(idSol);
				resu.add(v);// se listan todos los atrasados
			}
			rs.close();
			prep.close();

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return resu;
	}

	public Vector<Vector<Integer>> listarContratosAtrasados(Transaccion t, Calendar fecha) throws PersistenciaException {
		Vector<Vector<Integer>> resu = new Vector<Vector<Integer>>();
		String sql = "SELECT id, idSolicitud FROM Contratos WHERE estaAlDia = 0;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				int idSol = rs.getInt(2);
				Vector<Integer> v = new Vector<Integer>();
				v.add(id);
				v.add(idSol);
				resu.add(v);// se listan todos los atrasados
			}
			rs.close();
			prep.close();

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return resu;
	}

	public Vector<DataContrato> listar(Transaccion t) throws PersistenciaException {
		Vector<DataContrato> resu = new Vector<DataContrato>();
		try {
			String sql = "SELECT * FROM contratos;";
			PreparedStatement prep = t.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				Calendar fecha = Calendar.getInstance();
				java.sql.Date f = rs.getDate(2);
				fecha.setTimeInMillis(f.getTime());
				int idSolicitud = rs.getInt(3);
				DataContrato c = new DataContrato(id, fecha, idSolicitud);
				resu.add(c);
			}
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return resu;
	}

	public void setEstaAldia(Transaccion t, int idContrato, boolean b) throws PersistenciaException {
		try {
			String sql = "UPDATE contratos SET estaAlDia = ? where id = ?;";
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setBoolean(1, b);
			prep.setInt(2, idContrato);
			prep.executeUpdate();
			prep.close();

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
	}

	public Vector<DataContrato> listarContratosRecienFirmados(Transaccion t, Calendar fechaInicio) throws PersistenciaException {
		Vector<DataContrato> vec = new Vector<DataContrato>();
		String sql = "SELECT * FROM Contratos where fecha > ?;";
		try {
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(fechaInicio.getTimeInMillis()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				java.sql.Date d = rs.getDate(2);
				Calendar fe = Calendar.getInstance();
				fe.setTimeInMillis(d.getTime());
				int idSol = rs.getInt(3);
				DataContrato dc = new DataContrato(id, fe, idSol);
				vec.add(dc);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return vec;
	}

	public int buscarId(Transaccion t, int idSolicitud) throws PersistenciaException {
		int resu = 0;
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * FROM Contratos WHERE idSolicitud = ?;");
			prep.setInt(1, idSolicitud);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				resu = rs.getInt(1);
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return resu;
	}

	public double calcularMontoImpago(Transaccion t, Calendar fecha, int idsolicitud) throws PersistenciaException {
		double resu = 0.0;
		Cuotas cuotas = new Cuotas();
		int idcontrato = this.buscarId(t, idsolicitud);
		Vector<Cuota> impagas = cuotas.listarCuotasSinPagarPorContratoAFechaVersion2(t, idcontrato, fecha);		
		for (Cuota c : impagas) {
			double monto = c.getMontoUI();
			resu = resu + monto;
		}
		return resu;
	}

	public Calendar buscarFecha(Transaccion t, int idsolicitud) throws PersistenciaException {
		Calendar fecha = Calendar.getInstance();
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * FROM Contratos WHERE idSolicitud = ?;");
			prep.setInt(1, idsolicitud);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				fecha.setTime(rs.getDate(2));
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return fecha;
	}
}
