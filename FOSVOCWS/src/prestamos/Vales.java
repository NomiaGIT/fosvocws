package prestamos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import persistencia.Transaccion;
import excepciones.PersistenciaException;

public class Vales {
	// retorna la cantidad de vales hechos
	public int getCantidad(Transaccion t) throws PersistenciaException {
		int cantidad = 0;
		String sql = "SELECT COUNT(id) FROM vales;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				cantidad = rs.getInt(1);
			}

			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return cantidad;
	}

	public void ingresar(Transaccion t, Vale vale, int contratoId, int idSolicitud) throws PersistenciaException {
		this.insertar(t, vale, contratoId, idSolicitud);

	}

	public Vale buscar(Transaccion t, int idContrato) throws PersistenciaException {
		Vale vale = null;
		String sql = "SELECT * FROM vales WHERE idContrato = ?;";
		Calendar fechaVencimiento = Calendar.getInstance();
		int id = 0;
		double montoUI = 0;
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idContrato);
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				id = rs.getInt(1);
				fechaVencimiento.setTime(rs.getDate(2));
				montoUI = rs.getDouble(3);

				vale = new Vale(id, fechaVencimiento, montoUI);
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return vale;
	}

	public void insertar(Transaccion t, Vale vale, int idContrato, int idSolicitud) throws PersistenciaException {

		String sql = "INSERT INTO vales (fechaVencimiento, monto, idContrato, numero) VALUES (?,?,?,?);";
		java.sql.Date fecha = new java.sql.Date(vale.getFechaVencimiento().getTime().getTime());
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setDate(1, fecha);
			prep.setDouble(2, vale.getMontoUI());
			prep.setInt(3, idContrato);
			prep.setInt(4, idSolicitud);

			if (prep.executeUpdate() != 1) {
				throw new PersistenciaException("No ha sido posible realizar la operacion");
			}
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje() + "VALES");
		}

	}

	public static void eliminarVale(Transaccion t, int idCOntrato) throws PersistenciaException {
		try {

			String sql = "delete from  vales where idContrato = ?;";
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
}
