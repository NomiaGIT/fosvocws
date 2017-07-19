package logica.productos;

import excepciones.PersistenciaException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import persistencia.Transaccion;

public class LineasPrestamo {
	public lineaPrestamo buscarLineaPrestamo(Transaccion t, int id) throws PersistenciaException {
		  lineaPrestamo linea = null;
		try {
			  PreparedStatement prep = t
			  .prepareStatement("SELECT * FROM lineasPrestamo WHERE id = ?;");

			  prep.setInt(1, id);
			  ResultSet rs = prep.executeQuery();

			  if (rs.next()) {
				  String descripcion = rs.getString(2);
				  int cantCuotas = rs.getInt(3);
				  String destino = rs.getString(4);
				  double tasa = rs.getDouble(5);
				  String tipo = rs.getString(6);
				  String nombre = rs.getString(7);
				  String moneda = rs.getString(8);
				  int valorGastosAdmin = rs.getInt(9);
				  double interes = rs.getDouble(10);
				  int topeCuota = rs.getInt(11);

				  linea = new lineaPrestamo(id, descripcion, cantCuotas, destino,
				  tasa, tipo, nombre, moneda, interes, valorGastosAdmin,
				  topeCuota);
			}
		} catch (SQLException e) {
			  throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			  throw new PersistenciaException(e.getMessage());
		}

		  return linea;
	}

	public lineaPrestamo buscarLineaPrestamo(Transaccion t, String descripcion) throws PersistenciaException {
		  lineaPrestamo linea = null;
		try {
			  PreparedStatement prep = t
			  .prepareStatement("SELECT * FROM lineasPrestamo WHERE descripcion = ?;");

			  prep.setString(1, descripcion);
			  ResultSet rs = prep.executeQuery();

			  if (rs.next()) {
				  int id = rs.getInt(1);
				  int cantCuotas = rs.getInt(3);
				  String destino = rs.getString(4);
				  double tasa = rs.getDouble(5);
				  String tipo = rs.getString(6);
				  String nombre = rs.getString(7);
				  String moneda = rs.getString(8);
				  int valorGastosAdmin = rs.getInt(9);
				  double interes = rs.getDouble(10);
				  int topeCuota = rs.getInt(11);

				  linea = new lineaPrestamo(id, descripcion, cantCuotas, destino,
				  tasa, tipo, nombre, moneda, interes, valorGastosAdmin,
				  topeCuota);
			}
		} catch (SQLException e) {
			  throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			  throw new PersistenciaException(e.getMessage());
		}

		  return linea;
	}

	public void insertar(Transaccion t, lineaPrestamo linea) throws PersistenciaException {
		  PreparedStatement prep = t
				  .prepareStatement("INSERT INTO lineasPrestamo (descripcion, cantCuotas, destino, tasa, tipo, nombre, moneda, gastosAdministrativos, intereses, topeCuota) VALUES (?,?,?,?,?,?,?,?,?,?);");
		try {
			prep.setString(1, linea.getDescripcion());
			prep.setInt(2, linea.getCantCuotas());
			prep.setString(3, linea.getDestino());
			prep.setDouble(4, linea.getTasa());
			prep.setString(5, linea.getTipo());
			prep.setString(6, linea.getNombre());
			prep.setString(7, linea.getMoneda());
			prep.setInt(8, linea.getValorGastosAdmin());
			prep.setDouble(9, linea.getInteres());
			prep.setInt(10, linea.getTopeCuota());

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operación");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public int insertarInt(Transaccion t, lineaPrestamo linea) throws PersistenciaException {
		int key = 0;
		ResultSet rs = null;

		PreparedStatement prep = t
				.prepareStatement("INSERT INTO lineasPrestamo (descripcion, cantCuotas, destino, tasa, tipo, nombre, moneda, gastosAdministrativos, intereses, topeCuota) VALUES (?,?,?,?,?,?,?,?,?,?);");
		try {
			prep.setString(1, linea.getDescripcion());
			prep.setInt(2, linea.getCantCuotas());
			prep.setString(3, linea.getDestino());
			prep.setDouble(4, linea.getTasa());
			prep.setString(5, linea.getTipo());
			prep.setString(6, linea.getNombre());
			prep.setString(7, linea.getMoneda());
			prep.setInt(8, linea.getValorGastosAdmin());
			prep.setDouble(9, linea.getInteres());
			prep.setInt(10, linea.getTopeCuota());

			if (prep.executeUpdate() != 1) {
				throw new PersistenciaException("No ha sido posible realizar la operación");
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

	public Vector<String> listarMotivos(Transaccion t) throws PersistenciaException {
		Vector<String> resu = new Vector<String>();
		try {
			String sql = "SELECT descripcion FROM lineasPrestamo WHERE id > 2;";
			PreparedStatement ps = t.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String motivo = rs.getString(1);
				resu.add(motivo);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public int getTope(Transaccion t, String motivo) throws PersistenciaException {
		int resu = 0;
		try {
			String sql = "SELECT topeCuota FROM lineasPrestamo WHERE descripcion = ?;";
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setString(1, motivo);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resu = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}
}

