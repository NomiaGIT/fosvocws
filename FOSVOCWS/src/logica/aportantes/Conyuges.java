package logica.aportantes;

import excepciones.PersistenciaException;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.Calendar;

import persistencia.Transaccion;

public class Conyuges {
	public Conyuge buscarConyuge(Transaccion t, Integer cedulaAportante) throws PersistenciaException {
		Conyuge con = null;
		int cedula = 0;
		String nombres = null;
		String apellidos = null;
		java.sql.Date fechaNac = null;
		String nacionalidad = null;
		String estadoCivil = null;
		Calendar fechaNacimientoCalendar = Calendar.getInstance();
		String sexo = null;
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * FROM Conyuges WHERE cedulaAportante = ?;");

			prep.setInt(1, cedulaAportante.intValue());
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				  cedula = rs.getInt(2);
				  nombres = rs.getString(3);
				  apellidos = rs.getString(4);
				  fechaNac = rs.getDate(5);
				 nacionalidad = rs.getString(6);
				 estadoCivil = rs.getString(7);
				sexo = rs.getString(8);

				fechaNacimientoCalendar.setTime(fechaNac);
				con = new Conyuge(cedula, nombres, apellidos, fechaNacimientoCalendar, nacionalidad, estadoCivil, sexo);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return con;
	}

	public void insertarConyuge(Transaccion t, Conyuge con, int cedulaAportante) throws PersistenciaException {
		Calendar fechaNacimientoCalendar = con.getFechaNacimiento();
		java.sql.Date fechaNacimientoDate = new java.sql.Date(fechaNacimientoCalendar.getTime().getTime());

		PreparedStatement prep = t
				.prepareStatement("INSERT INTO Conyuges (cedulaAportante, cedula, nombres, apellidos, fechaNacimiento, nacionalidad, estadoCivil, sexo) VALUES (?,?,?,?,?,?,?,?);");
		try {
			prep.setInt(1, cedulaAportante);
			prep.setInt(2, con.getCedula());
			prep.setString(3, con.getNombres());
			prep.setString(4, con.getApellidos());
			prep.setDate(5, fechaNacimientoDate);
			prep.setString(6, con.getNacionalidad());
			prep.setString(7, con.getEstadoCivil());
			prep.setString(8, con.getSexo());

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operacion");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public void eliminarConyuge(Transaccion t, Conyuge con) throws PersistenciaException {
		PreparedStatement prep = t.prepareStatement("DELETE FROM Conyuges WHERE cedula = ?;");
		try {
			prep.setInt(1, con.getCedula());
			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operacion");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
	}

	public void modificarConyuge(Transaccion t, Conyuge con, int cedulaaportante) throws PersistenciaException {
		Calendar fechaNacimientoCalendar = con.getFechaNacimiento();
		java.sql.Date fechaNacimientoDate = new java.sql.Date(fechaNacimientoCalendar.getTime().getTime());

		PreparedStatement prep = t
				.prepareStatement("UPDATE Conyuges SET nombres = ?, apellidos = ?, fechaNacimiento = ?, nacionalidad = ?, estadoCivil = ?, sexo =?, cedula = ? WHERE cedulaAportante = ?;");
		try {
			prep.setString(1, con.getNombres());
			prep.setString(2, con.getApellidos());
			prep.setDate(3, fechaNacimientoDate);
			prep.setString(4, con.getNacionalidad());
			prep.setString(5, con.getEstadoCivil());
			prep.setString(6, con.getSexo());
			prep.setInt(7, con.getCedula());
			prep.setInt(8, cedulaaportante);

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operacion");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

}

/*
 * Location: C:\Users\Sole\Desktop\servidor\ Qualified Name: logica.aportantes.Conyuges JD-Core Version: 0.6.0
 */