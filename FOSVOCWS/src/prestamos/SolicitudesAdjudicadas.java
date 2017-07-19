package prestamos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import excepciones.PersistenciaException;
import persistencia.Transaccion;

public class SolicitudesAdjudicadas {

	public int buscarIdAdjudicacion(Transaccion t, int idSolicitud) throws PersistenciaException {

		int idAdjudicacion = 0;

		try {
			PreparedStatement prep = t.prepareStatement("SELECT idAdjudicacion FROM SolicitudesAdjudicadas WHERE idSolicitud = ?;");

			prep.setInt(1, idSolicitud);

			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				idAdjudicacion = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

		return idAdjudicacion;
	}

	public void insertar(Transaccion t, int idSolicitud, Adjudicacion adj) throws PersistenciaException {

		Adjudicaciones adjudicaciones = new Adjudicaciones();
		int idAdjudicacion = adjudicaciones.buscar(t, adj);
		PreparedStatement prep = t.prepareStatement("INSERT INTO SolicitudesAdjudicadas (idSolicitud, idAdjudicacion) VALUES (?,?);");
		try {
			prep.setInt(1, idSolicitud);
			prep.setInt(2, idAdjudicacion);

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

	public void insertar(Transaccion t, int idSolicitud, int idadj) throws PersistenciaException {

		PreparedStatement prep = t.prepareStatement("INSERT INTO SolicitudesAdjudicadas (idSolicitud, idAdjudicacion) VALUES (?,?);");
		try {
			prep.setInt(1, idSolicitud);
			prep.setInt(2, idadj);

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

	public void insertarExtraordinaria(Transaccion t, int idSolicitud, int idAdjudicacion) throws PersistenciaException {

		PreparedStatement prep = t.prepareStatement("INSERT INTO SolicitudesAdjudicadas (idSolicitud, idAdjudicacion) VALUES (?,?);");
		try {
			prep.setInt(1, idSolicitud);
			prep.setInt(2, idAdjudicacion);

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

	public static void eliminarSolicitudesAdjudicadas(Transaccion t, int idSolicitud) throws PersistenciaException {
		try {

			String sql = "delete from  solicitudesadjudicadas where idSolicitud = ?;";
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idSolicitud);

			if (prep.executeUpdate() == 0)
				throw new PersistenciaException("No ha sido posible realizar la operación");
			prep.close();

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
	}
}
