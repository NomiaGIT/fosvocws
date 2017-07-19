package logica.usuarios;

import excepciones.PersistenciaException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datatypes.DataContrasenia;
import datatypes.DataUsuario;
import persistencia.Transaccion;

public class Usuarios {
	


	public Usuario buscarUsuario(int cedula, Transaccion t) throws PersistenciaException {
		Usuario usu = null;

		Roles roles = new Roles();
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * FROM Usuarios WHERE cedula = ?;");

			prep.setInt(1, cedula);
			ResultSet rs = prep.executeQuery();

			if (rs.next())
				usu = new Usuario(rs.getString(1), rs.getString(2).toCharArray(), rs.getBoolean(3), rs.getInt(5), rs.getString(6), rs.getString(7),
						roles.buscarRol(t, rs.getInt(8)), rs.getString(9));
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

		return usu;
	}

	public Usuario buscarUsuario(String nombreUsuario, Transaccion t) throws PersistenciaException {
		Usuario usu = null;
		Roles roles = new Roles();
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * FROM Usuarios WHERE nick = ?;");
			prep.setString(1, nombreUsuario);
			ResultSet rs = prep.executeQuery();			
			if (rs.next())
				usu = new Usuario(rs.getString(1), rs.getString(2).toCharArray(), rs.getBoolean(3), rs.getInt(5), rs.getString(6), rs.getString(7),
						roles.buscarRol(t, rs.getInt(8)), rs.getString(9));
		
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		} 
		return usu;
	}

	public Usuario buscarUsuarioConectado(String nombreUsuario, Transaccion t) throws PersistenciaException {
		Usuario usu = null;

		Roles roles = new Roles();
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * FROM Usuarios WHERE nick = ? AND conectado = ?;");

			prep.setString(1, nombreUsuario);
			prep.setBoolean(2, true);
			ResultSet rs = prep.executeQuery();
			if (rs.next())
				usu = new Usuario(rs.getString(1), rs.getString(2).toCharArray(), rs.getBoolean(3), rs.getInt(5), rs.getString(6), rs.getString(7),
						roles.buscarRol(t, rs.getInt(8)), rs.getString(9));
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

		return usu;
	}

	
	public void modificarUsuario(Usuario usu, Transaccion t) throws PersistenciaException {
		PreparedStatement prep = t.prepareStatement("UPDATE Usuarios SET nick = ?, pass = ?, activo =?, nombres = ?, apellidos = ?, idRol = ? WHERE cedula = ?;");
		try {
			prep.setString(1, usu.getNick());
			prep.setString(2, String.valueOf(usu.getPass()));
			prep.setBoolean(3, usu.isActivo());
			prep.setString(4, usu.getNombre());
			prep.setString(5, usu.getApellidos());
			prep.setInt(6, usu.getRol().getId());
			prep.setInt(7, usu.getCedula());

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operación");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public void modificarUsuarioConectado(Usuario usu, Transaccion t) throws PersistenciaException {
		PreparedStatement prep = t.prepareStatement("UPDATE Usuarios SET nick = ?, pass = ?, activo =?, conectado = ?, nombres = ?, apellidos = ?, idRol = ? WHERE cedula = ?;");
		try {
			prep.setString(1, usu.getNick());
			prep.setString(2, String.valueOf(usu.getPass()));
			prep.setBoolean(3, usu.isActivo());
			prep.setBoolean(4, usu.isConectado());
			prep.setString(5, usu.getNombre());
			prep.setString(6, usu.getApellidos());
			prep.setInt(7, usu.getRol().getId());
			prep.setInt(8, usu.getCedula());

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operación");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public void modificarContrasenia(DataContrasenia data, Usuario usuario, Transaccion t) throws PersistenciaException {
		PreparedStatement prep = t.prepareStatement("UPDATE Usuarios SET pass = ? WHERE nick = ?;");
		try {
			prep.setString(1, data.getContraseniaNueva());
			prep.setString(2, usuario.getNick());

			if (coincideContrasenia(t, usuario.getNick(), data.getContraseniaAnterior()))
				if (prep.executeUpdate() != 1)
					throw new PersistenciaException("No ha sido posible realizar la operación");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public DataUsuario modificar(DataUsuario data, Transaccion t) throws PersistenciaException {
		DataUsuario resu = null;
		Roles roles = new Roles();
		PreparedStatement prep = t.prepareStatement("UPDATE Usuarios SET nombres = ?, apellidos = ?, idRol = ?, email = ? WHERE cedula = ?;");
		try {
			prep.setString(1, data.getNombres());
			prep.setString(2, data.getApellidos());
			prep.setInt(3, roles.buscar(data.getNombreRol(), t).getId());
			prep.setString(4, data.getMail());
			prep.setInt(5, data.getCedula());
			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operación");
			else
				resu = data;
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public boolean coincideContrasenia(Transaccion t, String nombreUsuario, String contraseniaIngresada) throws PersistenciaException {
		boolean resultado = false;
		//String contrasenia = String.valueOf(contraseniaIngresada);
		try {
			PreparedStatement prep = t.prepareStatement("SELECT pass FROM Usuarios WHERE nick = ?;");
			prep.setString(1, nombreUsuario);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(contraseniaIngresada));
				resultado = true;
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return resultado;
	}

	private void setConectado(Transaccion t, Usuario usuario) throws PersistenciaException {
		PreparedStatement prep = t.prepareStatement("UPDATE Usuarios SET conectado = ? WHERE cedula = ?;");
		try {
			prep.setBoolean(1, usuario.isConectado());
			prep.setInt(2, usuario.getCedula());
			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operación");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public void login(Transaccion t, Usuario usuario) throws PersistenciaException {
		usuario.setConectado(true);
		setConectado(t, usuario);
	}

	public void logout(Transaccion t, Usuario usuario) throws PersistenciaException {
		usuario.setConectado(false);
		setConectado(t, usuario);
	}

	public void resetearContrasenia(Transaccion t, String usuario) throws PersistenciaException {
		PreparedStatement prep = t.prepareStatement("UPDATE Usuarios SET pass = ? WHERE nick = ?;");
		try {
			prep.setString(1, usuario);
			prep.setString(2, usuario);
			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operación");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public static String buscarMail(Transaccion t, DataUsuario usuario) throws PersistenciaException {
		String resu = "";
		try {
			PreparedStatement prep = t.prepareStatement("SELECT email FROM Usuarios WHERE nick = ? AND apellidos = ?;");
			prep.setString(1, usuario.getUsuario());	
			prep.setString(2, usuario.getApellidos());
			ResultSet rs = prep.executeQuery();
			if (rs.next())
				resu = rs.getString(1);
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {			
			throw new PersistenciaException(e.getMensaje());
		}
		return resu;
	}

}
