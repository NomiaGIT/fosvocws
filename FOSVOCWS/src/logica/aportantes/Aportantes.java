package logica.aportantes;

import excepciones.PersistenciaException;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.Calendar;

import java.util.Vector;

import datatypes.DataListarAportantes;
import logica.usuarios.Roles;
import logica.usuarios.Usuario;

import logica.usuarios.Usuarios;

import logica.viviendas.Domicilio;

import logica.viviendas.Domicilios;

import persistencia.Transaccion;


public class Aportantes {
	public void insertarAportante(Transaccion t, Aportante ap) throws PersistenciaException {
		java.sql.Date fechaNacimiento = new java.sql.Date(ap
		.getFechaNacimiento().getTime().getTime());

		PreparedStatement prep = t
		.prepareStatement("INSERT INTO Aportantes (cedula, categoria, fechaNacimiento, nacionalidad, estadoCivil, sexo) VALUES (?,?,?,?,?,?);");
		try {
			prep.setInt(1, ap.getCedula());
			prep.setString(2, ap.getCategoria());
			prep.setDate(3, fechaNacimiento);
			prep.setString(4, ap.getNacionalidad());
			prep.setString(5, ap.getEstadoCivil());
			prep.setString(6, ap.getSexo());

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException(
				"No ha sido posible realizar la operaci贸n");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public int insertarAportanteInt(Transaccion t, Aportante ap) throws PersistenciaException {
		ResultSet rs = null;
		int key = 0;

		java.sql.Date fechaNacimiento = new java.sql.Date(ap
		.getFechaNacimiento().getTime().getTime());

		PreparedStatement prep = t
		.prepareStatement("INSERT INTO Aportantes (cedula, categoria, fechaNacimiento, nacionalidad, estadoCivil, sexo) VALUES (?,?,?,?,?,?);");
		try {
			prep.setInt(1, ap.getCedula());
			prep.setString(2, ap.getCategoria());
			prep.setDate(3, fechaNacimiento);
			prep.setString(4, ap.getNacionalidad());
			prep.setString(5, ap.getEstadoCivil());
			prep.setString(6, ap.getSexo());

			if (prep.executeUpdate() != 1) {
				throw new PersistenciaException(
				"No ha sido posible realizar la operaci贸n");
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

	public void eliminarAportante(Transaccion t, Aportante ap) throws PersistenciaException {
		PreparedStatement prep = t.prepareStatement("DELETE FROM Aportantes WHERE cedula = ?;");
		try {
			prep.setInt(1, ap.getCedula());
			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operaci贸n");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
	}

	public void modificarAportante(Transaccion t, Aportante ap) throws PersistenciaException {
		java.sql.Date fechaNacimiento = new java.sql.Date(ap.getFechaNacimiento().getTime().getTime());

		PreparedStatement prep = t.prepareStatement("UPDATE Aportantes SET categoria = ?, fechaNacimiento = ?, nacionalidad = ?, estadoCivil = ?, sexo = ? WHERE cedula = ?;");
		try {
			prep.setString(1, ap.getCategoria());
			prep.setDate(2, fechaNacimiento);
			prep.setString(3, ap.getNacionalidad());
			prep.setString(4, ap.getEstadoCivil());
			prep.setString(5, ap.getSexo());
			prep.setInt(6, ap.getCedula());

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operaci贸n");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public Aportante buscarAportante(Transaccion t, Integer cedulaAportante) throws PersistenciaException {
		Domicilios domicilios = new Domicilios();
		Trabajos trabajos = new Trabajos();		
		Roles roles = new Roles();
		Aportante ap = null;
		String categoria = null;
		java.sql.Date fechaNacimiento = null;
		String nacionalidad = null;
		String estadoCivil = null;
		Calendar fechaNac = Calendar.getInstance();
		String sexo = null;
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * FROM (Aportantes, Usuarios) LEFT JOIN Conyuges "
					+ "on Aportantes.cedula = Conyuges.cedulaAportante "
					+ "LEFT JOIN AportanteFamilia ON Aportantes.cedula = AportanteFamilia.cedulaAportante "
					+ "WHERE Aportantes.cedula = Usuarios.cedula AND Aportantes.cedula = ?;");

			prep.setInt(1, cedulaAportante.intValue());
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				categoria = rs.getString(3);
				fechaNacimiento = rs.getDate(4);
				nacionalidad = rs.getString(5);
				estadoCivil = rs.getString(6);
				sexo = rs.getString(7);
				fechaNac.setTime(fechaNacimiento);

				//Usuario usu = usuarios.buscarUsuario(cedulaAportante.intValue(), t);	
				String email = rs.getString("email");
				if(rs.wasNull())
					email = "";
				Usuario usu = new Usuario(rs.getString(8), rs.getString(9).toCharArray(), 
						rs.getBoolean(10), cedulaAportante.intValue(),
						rs.getString(13), rs.getString(14),
						roles.buscarRol(t, rs.getInt(15)), email);

				//Conyuge con = conyuges.buscarConyuge(t, cedulaAportante);	
				Conyuge con = null;
				int cedulaConyuge = rs.getInt(18);
				if(!rs.wasNull()){            	   
					Calendar fechaNacimientoConyuge = Calendar.getInstance();
					fechaNacimientoConyuge.setTimeInMillis(rs.getDate(21).getTime());
					con = new Conyuge(cedulaConyuge, rs.getString(19), rs.getString(20),
							fechaNacimientoConyuge, rs.getString(22), rs.getString(23), rs.getString(24));
				}
				Familia fam;               
				//Familia fam = familias.buscarFamilia(t, cedulaAportante);	
				fam = new Familia(rs.getInt(26), rs.getInt(27), rs.getInt(28));
				Trabajo trab = trabajos.buscarTrabajo(t, cedulaAportante);				
				Domicilio dom = domicilios.buscarDomicilio(t, cedulaAportante);
				ap = new Aportante(usu, categoria, fechaNac, nacionalidad, estadoCivil, con, fam, trab, dom, estadoCivil, sexo);

			}
		} catch (SQLException e) {

			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

		return ap;
	}

	public Vector<DataListarAportantes> listarAportantes(Transaccion t) throws PersistenciaException {
		Domicilios domicilios = new Domicilios();
		Vector<DataListarAportantes> lista = new Vector<DataListarAportantes>();
		try {
			PreparedStatement prep = t
					.prepareStatement("SELECT Aportantes.cedula, Usuarios.nombres, Usuarios.apellidos FROM Aportantes, Usuarios WHERE Aportantes.cedula = Usuarios.cedula");

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				Domicilio objDomicilio = domicilios.buscarDomicilio(t, Integer.valueOf(rs.getInt(1)));
				String direccion = objDomicilio.getCalle() + " " + objDomicilio.getNumero() + ", " + objDomicilio.getCiudad() + ", " + objDomicilio.getDepartamento();
				DataListarAportantes dataLista = new DataListarAportantes(rs.getInt(1), rs.getString(2), rs.getString(3), direccion);
				lista.add(dataLista);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

		return lista;
	}

	

	public static String obtenerTelefono(Transaccion t, String dest) throws PersistenciaException{
		String resu = "";
		String sql = "SELECT telefonos FROM domicilios WHERE cedulaaportante = (Select cedulaAportante from solicitudes where id = ?)";
		try{
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(dest));
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				resu = rs.getString(1);
			}
			rs.close();
			ps.close();
		}catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}catch(NumberFormatException e) {
			throw new PersistenciaException("Error al parsear el n鷐ero de solicitud.");
		}
		return resu;
	}
}