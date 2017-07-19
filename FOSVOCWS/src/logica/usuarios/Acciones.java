package logica.usuarios;

import excepciones.PersistenciaException;

import java.io.Serializable;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Timestamp;

import persistencia.Transaccion;

@SuppressWarnings("serial")
public class Acciones implements Serializable {
	private int cedulaUsuario;

	public int getCedulaUsuario() {
		return this.cedulaUsuario;
	}

	public void setCedulaUsuario(int cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
	}

	public Acciones(int cedula) {
		this.cedulaUsuario = cedula;
	}

	public Acciones() {
	}

	public int agregarAccionInt(Accion accion, Transaccion t) throws PersistenciaException {
		ResultSet rs = null;
		int key = 0;
		java.sql.Date fecha = new java.sql.Date(accion.getFecha().getTime()
		.getTime());
		Timestamp momentoTimestamp = new Timestamp(
		fecha.getTime());

		PreparedStatement prep = t
		.prepareStatement("INSERT INTO Acciones (cedulaUsuario, fecha, descripcion, clave, tipo) VALUES (?,?,?,?,?);");
		try {
			prep.setInt(1, this.cedulaUsuario);

			prep.setTimestamp(2, momentoTimestamp);
			prep.setString(3, accion.getDescripcion());
			prep.setInt(4, accion.getClave());
			prep.setString(5, accion.getTipo());

			if (prep.executeUpdate() != 1) {
				throw new PersistenciaException(
				"No ha sido posible realizar la operacion - ");
			}
			rs = prep.getGeneratedKeys();

			if (rs.next()) {
				key = rs.getInt(1);
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return key;
	}

	public void agregarAccion(Accion accion, Transaccion t) throws PersistenciaException {
		java.sql.Date fecha = new java.sql.Date(accion.getFecha().getTime()
		.getTime());
		Timestamp momentoTimestamp = new Timestamp(
		fecha.getTime());
		PreparedStatement prep = t
		.prepareStatement("INSERT INTO Acciones (cedulaUsuario, fecha, descripcion, clave, tipo) VALUES (?,?,?,?,?);");
		try {
			prep.setInt(1, this.cedulaUsuario);

			prep.setTimestamp(2, momentoTimestamp);
			prep.setString(3, accion.getDescripcion());
			prep.setInt(4, accion.getClave());
			prep.setString(5, accion.getTipo());
			if (prep.executeUpdate() != 1) {
				throw new PersistenciaException("No ha sido posible realizar la operación");
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}
	

}
