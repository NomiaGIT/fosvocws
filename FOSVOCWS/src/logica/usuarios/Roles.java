package logica.usuarios;

import excepciones.PersistenciaException;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.List;

import java.util.Vector;

import persistencia.Transaccion;

public class Roles {
	public List<String> listarRoles(Transaccion t) throws PersistenciaException {
		  Vector<String> lista = new Vector<String>();
		try {
			  PreparedStatement prep = t.prepareStatement("SELECT * FROM Roles;");

			  ResultSet rs = prep.executeQuery();

			  while (rs.next()) {
				  String cadena = new String(rs.getString(3));
				  lista.add(cadena);
			}
		} catch (SQLException e) {
			  throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			  throw new PersistenciaException(e.getMessage());
		}

		  return lista;
	}

	public void agregarRol(Rol rol, Transaccion t) throws PersistenciaException {
		  PreparedStatement prep = t
		  .prepareStatement("INSERT INTO Roles (permisos, tipo) VALUES (?,?);");
		try {
			  prep.setString(1, rol.getPermisos());
			  prep.setString(2, rol.getTipo());

			  if (prep.executeUpdate() != 1)
				  throw new PersistenciaException(
				  "No ha sido posible realizar la operación");
		} catch (SQLException e) {
			  throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			  throw new PersistenciaException(e.getMessage());
		}
	}

	public int agregarRolInt(Rol rol, Transaccion t) throws PersistenciaException {
		  int key = 0;
		  ResultSet rs = null;

		  PreparedStatement prep = t
		  .prepareStatement("INSERT INTO Roles (permisos, tipo) VALUES (?,?);");
		try {
			  prep.setString(1, rol.getPermisos());
			  prep.setString(2, rol.getTipo());

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

	public Rol buscar(String tipo, Transaccion t) throws PersistenciaException {
		  Rol rol = null;
		try {
			  PreparedStatement prep = t
			  .prepareStatement("SELECT * FROM Roles WHERE tipo = ?;");

			  prep.setString(1, tipo);
			  ResultSet rs = prep.executeQuery();

			  if (rs.next())
				  rol = new Rol(rs.getString(3), rs.getString(2), rs.getInt(1));
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return rol;
	}

	public Rol buscarRol(Transaccion t, int idRol) throws PersistenciaException {
		Rol rol = null;
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * FROM Roles WHERE id = ?;");

			prep.setInt(1, idRol);
			ResultSet rs = prep.executeQuery();

			if (rs.next())
				rol = new Rol(rs.getString(3), rs.getString(2), rs.getInt(1));
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return rol;
	}

}

 