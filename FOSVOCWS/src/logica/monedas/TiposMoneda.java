package logica.monedas;

import excepciones.PersistenciaException;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.List;

import java.util.Vector;

import persistencia.Transaccion;

public class TiposMoneda {
	public void ingresarTipoMoneda(Transaccion t, String descripcion) throws PersistenciaException {
		  PreparedStatement prep = t
		  .prepareStatement("INSERT INTO TipoMoneda (descripcion) VALUES (?);");
		try {
			  prep.setString(1, descripcion);

			  if (prep.executeUpdate() != 1)
				  throw new PersistenciaException(
				  "No ha sido posible realizar la operación");
		} catch (SQLException e) {
			  throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			  throw new PersistenciaException(e.getMessage());
		}
	}

	public void eliminarTipoMoneda(Transaccion t, String descripcion) throws PersistenciaException {
		  PreparedStatement prep = t
		  .prepareStatement("DELETE FROM TipoMoneda WHERE descripcion = ?;");
		try {
			  prep.setString(1, descripcion);
			  if (prep.executeUpdate() != 1)
				  throw new PersistenciaException(
				  "No ha sido posible realizar la operación");
		} catch (SQLException e) {
			  throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			  throw new PersistenciaException(e.getMessage());
		}
	}

	public int ingresarTipoMonedaInt(Transaccion t, String descripcion) throws PersistenciaException {
		  int key = 0;
		  ResultSet rs = null;
		  PreparedStatement prep = t
		  .prepareStatement("INSERT INTO TipoMoneda (descripcion) VALUES (?);");
		try {
			  prep.setString(1, descripcion);

			  if (prep.executeUpdate() != 1) {
				  throw new PersistenciaException(
				  "No ha sido posible realizar la operación");
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

	public void modificarTipoMoneda(Transaccion t, String descripcionVieja, String descripcionNueva) throws PersistenciaException {
		  PreparedStatement prep = t
		  .prepareStatement("UPDATE TipoMoneda SET descripcion = ? WHERE descripcion = ?;");
		try {
			  prep.setString(1, descripcionNueva);
			  prep.setString(2, descripcionVieja);

			  if (prep.executeUpdate() != 1)
				  throw new PersistenciaException(
				  "No ha sido posible realizar la operación");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public List<String> listarTiposMoneda(Transaccion t) throws PersistenciaException {
		Vector<String> lista = new Vector<String>();
		try {
			PreparedStatement prep = t.prepareStatement("SELECT descripcion FROM TipoMoneda;");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				String cadena = rs.getString(1);
				lista.add(cadena);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return lista;
	}

}
