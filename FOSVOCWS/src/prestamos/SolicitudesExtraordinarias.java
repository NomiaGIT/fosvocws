package prestamos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import excepciones.PersistenciaException;

import persistencia.Transaccion;

public class SolicitudesExtraordinarias {

	public void insertar(Transaccion t, String observaciones, int idAdjudicacion) throws PersistenciaException {

		String sql = "insert into extraordinarias (idAdjudicacion, Observaciones) values (?,?);";

		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idAdjudicacion);
			prep.setString(2, observaciones);

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operación");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

}
