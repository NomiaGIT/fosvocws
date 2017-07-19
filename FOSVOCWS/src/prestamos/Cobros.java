package prestamos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import excepciones.PersistenciaException;
import persistencia.Transaccion;
import prestamos.datatypes.DataCobro;
import utilitarios.NumberToLetterConverter;


public class Cobros {

	
	public void registrarIdCobro(Transaccion t, int idCuota, String transaccion) throws PersistenciaException {
		try {
			String sql = "UPDATE Cobros SET idTransaccion = ? WHERE idCuota = ?";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(2, idCuota);
			ps.setString(1, transaccion);
			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

	}

	public static double darMontoEnPesos(Transaccion t, int idCuota) throws PersistenciaException {
		// TODO Auto-generated method stub
		double monto = 0;
		try {
			String sql = "SELECT monto FROM Cobros WHERE idCuota = ? AND anulado = 0";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idCuota);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				monto = rs.getDouble(1);
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return monto;
	}

	public static String darFechaCobro(Transaccion t, int idCuota) throws PersistenciaException {
		// TODO Auto-generated method stub
		// este metodo se invoca solo cuando la cuota ha sido cobrada
		// como al final del prestamo las cuotas aparecen como cobradas si son
		// exhoneradas
		// se pone un mensaje si no se encuentra el cobro
		String resu = "";
		Calendar fecha = null;
		try {
			String sql = "SELECT fecha FROM Cobros WHERE idCuota = ? AND anulado = 0";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idCuota);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				fecha = Calendar.getInstance();
				fecha.setTimeInMillis(rs.getDate(1).getTime());
				resu = NumberToLetterConverter.convertirCalendar(fecha);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

		return resu;
	}

	public static Calendar darFechaCobroC(Transaccion t, int idCuota) throws PersistenciaException {
		// TODO Auto-generated method stub
		Calendar fecha = null;
		try {
			String sql = "SELECT fecha FROM Cobros WHERE idCuota = ? AND anulado = 0;";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idCuota);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				fecha = Calendar.getInstance();
				fecha.setTimeInMillis(rs.getDate(1).getTime());
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return fecha;
	}

	public boolean estaRegistrado(Transaccion t, int idCuota) throws PersistenciaException {
		
		boolean siRegistrado = false;
		try {
			String sql = "SELECT idTransaccion FROM cobros WHERE idCuota = ? AND anulado = false;";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idCuota);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String num = rs.getString(1);
				if (num.equals("0"))
					siRegistrado = false;
				else
					siRegistrado = true;				
			} else
				// no hay un cobro asociado a esa cuota
				siRegistrado = false;

			rs.close();
			ps.close();
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return siRegistrado;
	}

	public boolean estaRegistradoSimplemente(Transaccion t, int idCuota) throws PersistenciaException {
		
		boolean siRegistrado = false;
		try {
			String sql = "SELECT idTransaccion FROM cobros WHERE idCuota = ? AND anulado = false;";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idCuota);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				siRegistrado = true;
			}
			rs.close();
			ps.close();
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return siRegistrado;
	}

	
	public int buscarMonto(Transaccion t, int id) throws PersistenciaException {
		// TODO Auto-generated method stub
		int resu = 0;

		return resu;
	}

	public void modificarFecha(Transaccion t, int idcuota, Calendar fechanueva) throws PersistenciaException {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE Cobros SET fecha = ? WHERE idCuota = ?";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(fechanueva.getTimeInMillis()));
			ps.setInt(2, idcuota);
			int i = ps.executeUpdate();
			if (i == 0) {
				throw new PersistenciaException("error al modificar la fecha");
			}
			ps.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public double darMontoCobrado(Transaccion t, int numSolicitud) throws PersistenciaException {
		double resu = 0.0;
		String sql = "SELECT SUM(monto) FROM cobros where anulado = 0 AND idcuota IN (SELECT id from cuotas where idcontrato = (select id from contratos where idsolicitud = ?));";
		try {
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, numSolicitud);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				resu = rs.getInt(1);
			rs.close();
			ps.close();
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}


	public DataCobro buscar(Transaccion t, int idCuota) throws PersistenciaException {
		DataCobro resu = null;
		try {
			String sql = "SELECT * FROM cobros where idcuota = ?;";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idCuota);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Calendar f = Calendar.getInstance();
				f.setTimeInMillis(rs.getDate(2).getTime());
				int monto = rs.getInt(3);
				resu = new DataCobro(f, monto, "", idCuota);
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

	public boolean estaRegistradoAntesDe(Transaccion t, int idCuota, Calendar fechaAsientoGestion) throws PersistenciaException {
		boolean resu = false;
		try {
			String sql = "SELECT * FROM cobros WHERE idCuota = ? AND anulado = false AND fecha < ?;";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idCuota);
			ps.setDate(2, new java.sql.Date(fechaAsientoGestion.getTimeInMillis()));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {				
				resu = true;
			} else
				// no hay un cobro asociado a esa cuota
				resu = false;

			rs.close();
			ps.close();
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public boolean estaDevuelto(Transaccion t, int idCuota) throws PersistenciaException {
		boolean resu = false;
		try {
			String sql = "SELECT * FROM cobros_devueltos WHERE idCuota = ?;";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idCuota);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				resu = true;

		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public boolean tienedevoluciones(Transaccion t, int idcontrato, Calendar fechaHasta) throws PersistenciaException {
		boolean resu = false;
		try {
			String sql = "SELECT * FROM cobros_devueltos WHERE idcuota IN (SELECT id from cuotas WHERE idcontrato = ?) AND devuelto = 1 AND fecha_devuelto <= ?;";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idcontrato);
			ps.setDate(2, new java.sql.Date(fechaHasta.getTimeInMillis()));
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				resu = true;

		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public Calendar buscarFechaFinalizacion(Transaccion t, int numeroSolicitud) throws PersistenciaException {
		// supongo que la solicitud esta finalizada y tomo la ultima fecha de
		// cobro
		Calendar resu = null;
		String sql = "SELECT fecha from cobros where idcuota IN (SELECT id from cuotas where idcontrato = (select id from contratos where idsolicitud = ?)) and anulado = 0 ORDER BY fecha ASC";
		try {
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, numeroSolicitud);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resu = Calendar.getInstance();
				Date f = rs.getDate(1);
				resu.setTimeInMillis(f.getTime());
			}
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	// lo mismo que el anterior pero hace la consulta directa
	public Calendar buscarFechaUltimoCobro(Transaccion t, int numeroSolicitud) throws PersistenciaException {
		// supongo que la solicitud esta finalizada y tomo la ultima fecha de
		// cobro
		Calendar resu = null;
		String sql = "SELECT MAX(fecha) from cobros where idcuota IN (SELECT id from cuotas where idcontrato = (select id from contratos where idsolicitud = ?)) and anulado = 0";
		try {
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, numeroSolicitud);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resu = Calendar.getInstance();
				Date f = rs.getDate(1);
				resu.setTimeInMillis(f.getTime());
			}
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

}