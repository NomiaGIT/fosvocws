package logica.viviendas;

import excepciones.PersistenciaException;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.Collection;

import java.util.Iterator;

import java.util.List;

import java.util.Vector;

import logica.viviendas.Domicilio;
import logica.viviendas.ViviendaAReparar;
import persistencia.Transaccion;

public class Domicilios {
	public Domicilio buscarDomicilio2(Transaccion t, Integer cedulaAportante) throws PersistenciaException {
		/* 21 */Domicilio dom = null;
		try {
			/* 23 */PreparedStatement prep = t
					.prepareStatement("SELECT * FROM fosvoc.Domicilios  WHERE fosvoc.Domicilios.cedulaAportante = ? AND  fosvoc.Domicilios.id NOT IN ( SELECT idDomicilio FROM  fosvoc.DomicilioReparar);");
			/* 24 */prep.setInt(1, cedulaAportante.intValue());
			/* 25 */ResultSet rs = prep.executeQuery();

			/* 27 */if (rs.next()) {
				/* 29 */String calle = rs.getString(3);
				/* 30 */String numero = rs.getString(4);
				/* 31 */String barrio = rs.getString(5);
				/* 32 */String ciudad = rs.getString(6);
				/* 33 */String departamento = rs.getString(7);
				/* 34 */String telefono = rs.getString(8);

				/* 36 */dom = new Domicilio(calle, numero, barrio, ciudad, departamento, telefono);
			}
		} catch (SQLException e) {
			/* 39 */throw new PersistenciaException("Ha ocurrido un error");
		}
		/* 41 */return dom;
	}

	public Domicilio buscarDomicilio(Transaccion t, Integer cedulaAportante) throws PersistenciaException {
		  Domicilio dom = null;
		try {
			  PreparedStatement prep = t.prepareStatement("SELECT * FROM Domicilios  WHERE Domicilios.cedulaAportante = ? ;");
			  prep.setInt(1, cedulaAportante.intValue());
			  ResultSet rs = prep.executeQuery();

			  if (rs.next()) {
				  String calle = rs.getString(3);
				  String numero = rs.getString(4);
				  String barrio = rs.getString(5);
				  String ciudad = rs.getString(6);
				  String departamento = rs.getString(7);
				  String telefono = rs.getString(8);

				  dom = new Domicilio(calle, numero, barrio, ciudad, departamento, telefono);
			}
		} catch (SQLException e) {
			  throw new PersistenciaException("Ha ocurrido un error");
		}
		  return dom;
	}

	public Domicilio buscarDomicilioVivienda(Transaccion t, Integer cedulaAportante) throws PersistenciaException {
		/* 72 */Domicilio dom = null;
		try {
			/* 74 */PreparedStatement prep = t
					.prepareStatement("SELECT * FROM fosvoc.Domicilios  WHERE fosvoc.Domicilios.cedulaAportante = ? AND  fosvoc.Domicilios.id IN ( SELECT idDomicilio FROM  fosvoc.DomicilioReparar);");
			/* 75 */prep.setInt(1, cedulaAportante.intValue());
			/* 76 */ResultSet rs = prep.executeQuery();

			/* 78 */if (rs.next()) {
				/* 80 */String calle = rs.getString(3);
				/* 81 */String numero = rs.getString(4);
				/* 82 */String barrio = rs.getString(5);
				/* 83 */String ciudad = rs.getString(6);
				/* 84 */String departamento = rs.getString(7);
				/* 85 */String telefono = rs.getString(8);

				/* 87 */dom = new Domicilio(calle, numero, barrio, ciudad, departamento, telefono);
			}
		} catch (SQLException e) {
			/* 90 */throw new PersistenciaException("Ha ocurrido un error");
		}
		/* 92 */return dom;
	}

	public List<Domicilio> buscarDomicilios(Transaccion t, Integer cedulaAportante) throws PersistenciaException {
		/* 97 */Vector<Domicilio> lista = new Vector<Domicilio>();
		/* 98 */Domicilio dom = null;
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * FROM Domicilios WHERE cedulaAportante = ?;");
			prep.setInt(1, cedulaAportante.intValue());
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				String calle = rs.getString(3);
				String numero = rs.getString(4);
				String barrio = rs.getString(5);
				String ciudad = rs.getString(6);
				String departamento = rs.getString(7);
				String telefono = rs.getString(8);

				dom = new Domicilio(calle, numero, barrio, ciudad, departamento, telefono);
				lista.add(dom);
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Ha ocurrido un error");
		}
		return lista;
	}

	public ViviendaAReparar buscarViviendaAReparar(Transaccion t, Domicilio domicilio, int cedulaAportante) throws PersistenciaException {
		int idDomicilio = buscarDomicilioId(t, domicilio, cedulaAportante);

		ViviendaAReparar vivienda = null;
		String sql = "SELECT * FROM DomicilioReparar WHERE idDomicilio = ?;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idDomicilio);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				vivienda = new ViviendaAReparar(domicilio, rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getInt(5), rs.getBoolean(7));
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Ha ocurrido un error");
		}
		return vivienda;
	}

	public ViviendaAReparar buscarViviendaARepararPorIdDomicilio(Transaccion t, int idDomicilio) throws PersistenciaException {
		ViviendaAReparar vivienda = null;
		Domicilio domicilio = buscarDomiciliosPorId(t, idDomicilio);

		String sql = "SELECT * FROM DomicilioReparar WHERE idDomicilio = ?;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idDomicilio);
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				vivienda = new ViviendaAReparar(domicilio, rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getInt(5), rs.getBoolean(7));
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Ha ocurrido un error");
		}
		return vivienda;
	}

	public Domicilio buscarDomiciliosPorId(Transaccion t, int idDomicilio) throws PersistenciaException {
		Domicilio dom = null;
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * FROM Domicilios WHERE id = ?;");
			prep.setInt(1, idDomicilio);
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				String calle = rs.getString(3);
				String numero = rs.getString(4);
				String barrio = rs.getString(5);
				String ciudad = rs.getString(6);
				String departamento = rs.getString(7);
				String telefono = rs.getString(8);

				dom = new Domicilio(calle, numero, barrio, ciudad, departamento, telefono);
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Ha ocurrido un error");
		}
		return dom;
	}

	public boolean isViviendaReparar(Transaccion t, Domicilio domicilio, int cedulaAportante) throws PersistenciaException {
		boolean es = false;
		int idDomicilio = buscarDomicilioId(t, domicilio, cedulaAportante);
		try {
			PreparedStatement prep = t.prepareStatement("SELECT  * FROM DomicilioReparar WHERE idDomicilio = ?;");
			prep.setInt(1, idDomicilio);
			ResultSet rs = prep.executeQuery();
			if (rs.next())
				es = true;
		} catch (SQLException e) {
			throw new PersistenciaException("Ha ocurrido un error");
		}

		return es;
	}

	public ViviendaAReparar buscarViviendaAReparar(Transaccion t, int cedulaAportante) throws PersistenciaException {

		ViviendaAReparar vivienda = null;

		List<Domicilio> lista = buscarDomicilios(t, cedulaAportante);
		Iterator<Domicilio> iterator = lista.iterator();
		while (iterator.hasNext()) {
			Domicilio domicilio = (Domicilio) iterator.next();
			if (isViviendaReparar(t, domicilio, cedulaAportante)) {
				vivienda = this.buscarViviendaAReparar(t, domicilio, cedulaAportante);
			}
		}
		return vivienda;
	}

	public int buscarDomicilioId(Transaccion t, Domicilio dom, int cedulaAportante) throws PersistenciaException {
		int idDomicilio = 0;
		try {
			PreparedStatement prep = t.prepareStatement("SELECT id FROM Domicilios WHERE calle = ? AND numero = ? AND cedulaAportante = ?;");
			prep.setString(1, dom.getCalle());
			prep.setString(2, dom.getNumero());
			prep.setInt(3, cedulaAportante);

			ResultSet rs = prep.executeQuery();
			while (rs.next())
				idDomicilio = rs.getInt(1);
		} catch (SQLException e) {
			throw new PersistenciaException("Ha ocurrido un error");
		}
		return idDomicilio;
	}

	/*
	 * public int buscarDomicilioId(Transaccion t, Domicilio dom) throws PersistenciaException { int idDomicilio = 0; try {
	 * PreparedStatement prep = t.prepareStatement(
	 * "SELECT id FROM Domicilios WHERE calle = ? AND numero = ? AND barrio = ? AND departamento = ? AND telefonos = ?;" );
	 * prep.setString(1, dom.getCalle()); prep.setString(2, dom.getNumero()); prep.setString(3, dom.getBarrio()); prep.setString(4,
	 * dom.getDepartamento()); prep.setString(5, dom.getTelefono());
	 * 
	 * ResultSet rs = prep.executeQuery(); if (rs.next()) idDomicilio = rs.getInt(1); } catch (SQLException e) { throw new
	 * PersistenciaException("Ha ocurrido un error"); } return idDomicilio; }
	 */
	public void insertarDomicilio(Transaccion t, Domicilio dom, int cedulaAportante) throws PersistenciaException {
		PreparedStatement prep = t.prepareStatement("INSERT INTO Domicilios (cedulaAportante, calle, numero, barrio, localidad, departamento, telefonos) VALUES (?,?,?,?,?,?,?);");
		try {
			prep.setInt(1, cedulaAportante);
			prep.setString(2, dom.getCalle());
			prep.setString(3, dom.getNumero());
			prep.setString(4, dom.getBarrio());
			prep.setString(5, dom.getCiudad());
			prep.setString(6, dom.getDepartamento());
			prep.setString(7, dom.getTelefono());

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operacion");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
	}

	public int insertarDomicilioInt(Transaccion t, Domicilio dom, int cedulaAportante) throws PersistenciaException {
		int key = 0;
		ResultSet rs = null;

		PreparedStatement prep = t.prepareStatement("INSERT INTO Domicilios (cedulaAportante, calle, numero, barrio, localidad, departamento, telefonos) VALUES (?,?,?,?,?,?,?);");
		try {
			prep.setInt(1, cedulaAportante);
			prep.setString(2, dom.getCalle());
			prep.setString(3, dom.getNumero());
			prep.setString(4, dom.getBarrio());
			prep.setString(5, dom.getCiudad());
			prep.setString(6, dom.getDepartamento());
			prep.setString(7, dom.getTelefono());

			if (prep.executeUpdate() != 1) {
				throw new PersistenciaException("No ha sido posible realizar la operacion");
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
			throw new PersistenciaException(e.getMensaje());
		}
		return key;
	}

	public void modificarDomicilio(Transaccion t, Domicilio dom, int cedulaAportante) throws PersistenciaException {
		Domicilios domicilios = new Domicilios();

		int idDomicilio = domicilios.buscarDomicilioIdPorCedula(t, dom, cedulaAportante);
		PreparedStatement prep = t.prepareStatement("UPDATE Domicilios SET calle = ?, numero = ?, barrio = ?, localidad = ?, departamento = ?, telefonos = ? WHERE id = ?;");
		try {
			prep.setString(1, dom.getCalle());
			prep.setString(2, dom.getNumero());
			prep.setString(3, dom.getBarrio());
			prep.setString(4, dom.getCiudad());
			prep.setString(5, dom.getDepartamento());
			prep.setString(6, dom.getTelefono());
			prep.setInt(7, idDomicilio);
			prep.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public void modificarDomicilios(Transaccion t, Domicilio dom, int cedulaAportante) // agregado
																						// set
																						// 2015
																						// proque
																						// solo
																						// modificaba
																						// un
																						// domicilio
			throws PersistenciaException {

		PreparedStatement prep = t
				.prepareStatement("UPDATE Domicilios SET calle = ?, numero = ?, barrio = ?, localidad = ?, departamento = ?, telefonos = ? WHERE cedulaAportante = ?;");
		try {
			prep.setString(1, dom.getCalle());
			prep.setString(2, dom.getNumero());
			prep.setString(3, dom.getBarrio());
			prep.setString(4, dom.getCiudad());
			prep.setString(5, dom.getDepartamento());
			prep.setString(6, dom.getTelefono());
			prep.setInt(7, cedulaAportante);
			prep.executeUpdate();
			if (prep != null)
				prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	private int buscarDomicilioIdPorCedula(Transaccion t, Domicilio dom, int cedulaAportante) throws PersistenciaException {
		// TODO Auto-generated method stub
		int resu = 0;
		try {
			PreparedStatement prep = t
					.prepareStatement("SELECT * FROM fosvoc.Domicilios  WHERE fosvoc.Domicilios.cedulaAportante = ? AND  fosvoc.Domicilios.id IN ( SELECT idDomicilio FROM  fosvoc.DomicilioReparar);");
			prep.setInt(1, cedulaAportante);
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				resu = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Ha ocurrido un error");
		}
		return resu;
	}

	public void insertarDomicilioAReparar(Transaccion t, ViviendaAReparar vivienda, int idDomicilio) throws PersistenciaException {
		PreparedStatement prep = t
				.prepareStatement("INSERT INTO DomicilioReparar (idDomicilio, asentamiento, tipoAsentamiento, tenencia, cantHabitaciones, zona, banioInterior) VALUES (?,?,?,?,?,?,?);");
		try {
			prep.setInt(1, idDomicilio);
			prep.setBoolean(2, vivienda.isAsentamiento());
			prep.setString(3, vivienda.getTipoAsentamiento());
			prep.setString(4, vivienda.getTenencia());
			prep.setInt(5, vivienda.getCantHabitaciones());
			prep.setString(6, vivienda.getZona());
			prep.setBoolean(7, vivienda.isBanioInterior());

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operacion");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
	}

	public void eliminarDomicilio(Transaccion t, Domicilio dom, int cedulaAportante) throws PersistenciaException {
		Domicilios domicilios = new Domicilios();
		int idDomicilio = domicilios.buscarDomicilioId(t, dom, cedulaAportante);

		PreparedStatement prep = t.prepareStatement("DELETE FROM Domicilios WHERE id = ?;");
		try {
			prep.setInt(1, idDomicilio);
			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operacion");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
	}

	public void eliminarDomicilio(Transaccion t, int id) throws PersistenciaException {

		PreparedStatement prep = t.prepareStatement("DELETE FROM Domicilios WHERE id = ?;");
		try {
			prep.setInt(1, id);
			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operacion");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
	}

	public void eliminarViviendaAReparar(Transaccion t, ViviendaAReparar vivienda, int cedulaAportante) throws PersistenciaException {
		Domicilios domicilios = new Domicilios();
		int idDomicilio = domicilios.buscarDomicilioId(t, vivienda.getDomicilio(), cedulaAportante);

		PreparedStatement prep = t.prepareStatement("DELETE FROM DomicilioReparar WHERE idDomicilio = ?;");
		try {
			prep.setInt(1, idDomicilio);

			if (prep.executeUpdate() != 1) {
				throw new PersistenciaException("No ha sido posible realizar la operacion");
			}
			eliminarDomicilio(t, idDomicilio);
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException("C " + e.getMensaje());
		}
	}

	public int insertarDomicilioARepararInt(Transaccion t, ViviendaAReparar vivienda, int idDomicilio) throws PersistenciaException {
		int key = 0;
		ResultSet rs = null;

		PreparedStatement prep = t
				.prepareStatement("INSERT INTO DomicilioReparar (idDomicilio, asentamiento, tipoAsentamiento, tenencia, cantHabitaciones, zona, banioInterior) VALUES (?,?,?,?,?,?,?);");
		try {
			prep.setInt(1, idDomicilio);
			prep.setBoolean(2, vivienda.isAsentamiento());
			prep.setString(3, vivienda.getTipoAsentamiento());
			prep.setString(4, vivienda.getTenencia());
			prep.setInt(5, vivienda.getCantHabitaciones());
			prep.setString(6, vivienda.getZona());
			prep.setBoolean(7, vivienda.isBanioInterior());

			if (prep.executeUpdate() != 1) {
				throw new PersistenciaException("No ha sido posible realizar la operacion");
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
			throw new PersistenciaException("B " + e.getMensaje());
		}

		return key;
	}

	public void modificarViviendaAReparar(Transaccion t, ViviendaAReparar vivienda, int cedulaAportante) throws PersistenciaException {

		int idDomicilio = 0;
		Domicilios domicilios = new Domicilios();
		List<Domicilio> listaDomicilios = domicilios.buscarDomicilios(t, cedulaAportante);

		if (listaDomicilios.size() > 1) {
			Iterator<Domicilio> it = listaDomicilios.iterator();
			while (it.hasNext()) {
				Domicilio domicilio = it.next();
				if (domicilios.isViviendaReparar(t, domicilio, cedulaAportante)) {
					idDomicilio = domicilios.buscarDomicilioId(t, domicilio, cedulaAportante);
				}
			}
		} else {
			idDomicilio = domicilios.buscarDomicilioId(t, vivienda.getDomicilio(), cedulaAportante);
		}

		PreparedStatement prep = t
				.prepareStatement("UPDATE DomicilioReparar SET asentamiento = ?, tipoAsentamiento = ?, tenencia = ?, cantHabitaciones = ?, zona = ?, banioInterior = ? WHERE idDomicilio = ?;");
		try {
			prep.setBoolean(1, vivienda.isAsentamiento());
			prep.setString(2, vivienda.getTipoAsentamiento());
			prep.setString(3, vivienda.getTenencia());
			prep.setInt(4, vivienda.getCantHabitaciones());
			prep.setString(5, vivienda.getZona());
			prep.setBoolean(6, vivienda.isBanioInterior());
			prep.setInt(7, idDomicilio);

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operacion");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException("A" + e.getMensaje());
		}
	}
}
