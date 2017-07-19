package prestamos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import excepciones.PersistenciaException;
import persistencia.Transaccion;

public class OrdenesDeCompra {

	public OrdenDeCompra buscar(Transaccion t, int idContrato) throws PersistenciaException {
		OrdenDeCompra ordenDeCompra = null;
		int id = 0;
		double montoPesos = 0;
		String numeroFormulario = null;
		Calendar fechaEmision = Calendar.getInstance();
		String sql = "SELECT * FROM OrdenesDeCompra WHERE idContrato = ?;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
				montoPesos = rs.getDouble(3);
				fechaEmision.setTime(rs.getDate(2));
				numeroFormulario = rs.getString(5);
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		ordenDeCompra = new OrdenDeCompra(id, fechaEmision, montoPesos, numeroFormulario);
		return ordenDeCompra;
	}

}
