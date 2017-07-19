package logica.productos.refaccion;

import excepciones.PersistenciaException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import persistencia.Transaccion;


public class Proveedores {
	public Proveedor buscar(Transaccion t, String nombrebarraca) throws PersistenciaException {
		  Proveedor prov = null;
		  String nombreBarraca = null;
		  String razonsocial = null;
		  String direccion = null;
		  String telefono = null;
		  long rut = 0L;
		try {
			  PreparedStatement prep = t
			  .prepareStatement("SELECT * FROM Proveedores WHERE nombre = ? AND id > 800;");

			  prep.setString(1, nombrebarraca);
			  ResultSet rs = prep.executeQuery();

			  if (rs.next()) {
				  nombreBarraca = rs.getString(2);
				  razonsocial = rs.getString(3);
				  direccion = rs.getString(4);
				  telefono = rs.getString(5);
				  rut = rs.getLong(6);
				String loc = rs.getString(7);
				String depto = rs.getString(8);
				boolean activa = rs.getBoolean("activa");
				prov = new Proveedor(nombreBarraca, direccion, telefono, razonsocial, rut, loc, depto, activa);
			}
		} catch (SQLException e) {
			  throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			  throw new PersistenciaException(e.getMessage());
		}

		  return prov;
	}

	public int buscarIdProveedor(Transaccion t, String nombrebarraca) throws PersistenciaException {
		int prov = 0;
		try {
			PreparedStatement prep = t.prepareStatement("SELECT id FROM Proveedores WHERE nombre = ?;");

			prep.setString(1, nombrebarraca);
			ResultSet rs = prep.executeQuery();

			while (rs.next())
				prov = rs.getInt(1);
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return prov;
	}

	public Proveedor buscar(Transaccion t, long RUT) throws PersistenciaException {
		  Proveedor prov = null;
		  String nombreBarraca = null;
		  String razonsocial = null;
		  String direccion = null;
		  String telefono = null;
		  long rut = 0L;
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * FROM Proveedores WHERE RUT = ?;");

			prep.setLong(1, RUT);
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				nombreBarraca = rs.getString(2);
				razonsocial = rs.getString(3);
				direccion = rs.getString(4);
				telefono = rs.getString(5);
				rut = rs.getLong(6);
				String loc, depto;
				loc = rs.getString(7);
				depto = rs.getString(8);
				boolean activa = rs.getBoolean(9);
				  prov = new Proveedor(nombreBarraca, direccion, telefono, razonsocial, rut, loc, depto, activa);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return prov;
	}

	
	public int getId(Transaccion t, String nombre) throws PersistenciaException {
		int id = 0;
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * id FROM Proveedores WHERE nombre = ?;");

			prep.setString(1, nombre);
			ResultSet rs = prep.executeQuery();

			if (rs.next())
				id = rs.getInt(1);
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return id;
	}

	public Proveedor buscarPorId(Transaccion t, int idProveedor) throws PersistenciaException {
		Proveedor pro = null;
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * FROM Proveedores WHERE id = ?;");

			prep.setInt(1, idProveedor);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				pro = new Proveedor(rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(3), rs.getLong(6), rs.getBoolean("activa"));
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		// System.out.println("el proveedor es "+pro.getNombreBarraca());
		return pro;
	}

	public Vector<DataProveedor> buscarDataProveedores(Transaccion t, String nombreBarraca) throws PersistenciaException {
		Vector<DataProveedor> lista = new Vector<DataProveedor>();
		String parametroNombreBarraca = "%" + nombreBarraca + "%";
		try {
			PreparedStatement prep = t// 21 jul 2014 agregue id mayor a 800
										// porque Seba cargo la lista depurada
					.prepareStatement("SELECT Proveedores.id, Proveedores.nombre FROM Proveedores WHERE nombre LIKE ? AND id > 800 ORDER BY nombre ;");
			prep.setString(1, parametroNombreBarraca);

			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				DataProveedor d = new DataProveedor(rs.getInt(1), rs.getString(2));
				lista.add(d);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return lista;
	}
}
