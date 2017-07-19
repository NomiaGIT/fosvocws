package logica.productos;

import excepciones.PersistenciaException;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import persistencia.Transaccion;

public class Prestamos {
	public void ingresarPrestamo(Transaccion t, Prestamo prestamo) throws PersistenciaException {
		/* 22 */lineaPrestamo linea = prestamo.getLinea();
		/* 23 */int idLineaPrestamo = linea.getId();

		/* 25 */PreparedStatement prep = t
		/* 26 */.prepareStatement("INSERT INTO Prestamos (tipo, nombre, idLineaPrestamo) VALUES (?,?,?);");
		try {
			/* 30 */prep.setString(1, prestamo.getTipo());
			/* 31 */prep.setString(2, prestamo.getNombre());
			/* 32 */prep.setInt(3, idLineaPrestamo);

			/* 34 */if (prep.executeUpdate() != 1)
				/* 35 */throw new PersistenciaException(
				/* 36 */"No ha sido posible realizar la operacion");
		} catch (SQLException e) {
			/* 40 */throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			/* 42 */throw new PersistenciaException(e.getMessage());
		}
	}

	public int ingresarPrestamoInt(Transaccion t, Prestamo prestamo) throws PersistenciaException {
		/* 49 */int key = 0;
		/* 50 */ResultSet rs = null;

		/* 52 */lineaPrestamo linea = prestamo.getLinea();
		/* 53 */int idLineaPrestamo = linea.getId();

		/* 55 */PreparedStatement prep = t
		/* 56 */.prepareStatement("INSERT INTO Prestamos (tipo, nombre, idLineaPrestamo) VALUES (?,?,?);");
		try {
			/* 60 */prep.setString(1, prestamo.getTipo());
			/* 61 */prep.setString(2, prestamo.getNombre());
			/* 62 */prep.setInt(3, idLineaPrestamo);

			/* 64 */if (prep.executeUpdate() != 1) {
				/* 65 */throw new PersistenciaException(
				/* 66 */"No ha sido posible realizar la operacion");
			}

			/* 69 */rs = prep.getGeneratedKeys();

			/* 71 */if (rs.next()) {
				/* 72 */key = rs.getInt(1);
			}
			/* 74 */rs.close();
			/* 75 */prep.close();
		} catch (SQLException e) {
			/* 78 */throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			/* 80 */throw new PersistenciaException(e.getMessage());
		}
		/* 82 */return key;
	}

	public Prestamo buscarPrestamo(Transaccion t, int idPrestamo) throws PersistenciaException {
		Prestamo prestamo = null;

		LineasPrestamo lineas = new LineasPrestamo();
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * FROM Prestamos WHERE id = ?;");

			prep.setInt(1, idPrestamo);
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				lineaPrestamo linea = lineas.buscarLineaPrestamo(t, rs.getInt(4));
				prestamo = new Prestamo(rs.getString(2), rs.getString(3), linea);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

		return prestamo;
	}

	public void eliminarPrestamo(Transaccion t, Prestamo prestamo) throws PersistenciaException {
		int idPrestamo = prestamo.getCodigo();
		PreparedStatement prep = t.prepareStatement("DELETE FROM Prestamos WHERE id = ?;");
		try {
			prep.setInt(1, idPrestamo);
			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operacion");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
	}

}

/*
 * Location: C:\Users\Sole\Desktop\servidor\ Qualified Name: logica.productos.Prestamos JD-Core Version: 0.6.0
 */