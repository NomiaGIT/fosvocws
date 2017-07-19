package prestamos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

import persistencia.Transaccion;
import excepciones.PersistenciaException;

public class Sorteos {

	public int ingresarSorteo(Transaccion t, Sorteo sort) throws PersistenciaException {
		int cantPorPuntaje = sort.getCantPorPuntaje();
		int cantSorteados = sort.getCantSorteados();
		// int toalParticipantes = sort.getCantTotal();
		String descripcion = sort.getDescripcion();
		Calendar fecha = sort.getFecha();
		String lugar = sort.getLugar();
		String modo = sort.getTipo();
		Vector<String> zona = sort.getZona();
		String stringZona = zona.toString();
		int cantidad = 0;
		int sorteo = 1000;

		ResultSet rs = null;
		// ResultSet rsPuntaje = null;

		java.sql.Date fechaAdjudicacion = new java.sql.Date(fecha.getTime().getTime());

		if (modo.equals("sorteo")) {
			cantidad = cantSorteados;
		} else {
			cantidad = cantPorPuntaje;
		}

		try {
			PreparedStatement prep = t.prepareStatement("INSERT INTO Adjudicaciones (fecha, descripcion, cantBeneficiarios, lugar, tipo) VALUES (?,?,?,?,?);");

			prep.setDate(1, fechaAdjudicacion);
			prep.setString(2, descripcion);
			prep.setInt(3, cantidad);
			prep.setString(4, lugar);
			prep.setString(5, modo);

			if (prep.executeUpdate() != 1) {
				throw new PersistenciaException("No ha sido posible realizar la operacion");
			}

			rs = prep.getGeneratedKeys();

			if (rs.next()) {
				sorteo = rs.getInt(1);
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		try {
			if (modo == "sorteo") {
				PreparedStatement prepS = t.prepareStatement("INSERT INTO Sorteos (departamentos, idAdjudicacion) VALUES (?,?);");

				prepS.setString(1, stringZona);
				prepS.setInt(2, sorteo);

				if (prepS.executeUpdate() != 1) {
					throw new PersistenciaException("No ha sido posible realizar la operacion");
				}

				prepS.close();
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		/*
		 * PreparedStatement prepPP = t.prepareStatement(
		 * "INSERT INTO Adjudicaciones (fecha, descripcion, cantBeneficiarios, lugar, tipo) VALUES (?,?,?,?,?);" );
		 * 
		 * prepPP.setDate(1, fechaAdjudicacion); prepPP.setString(2, descripcion); prepPP.setInt(3, cantidad); prepPP.setString(4, lugar);
		 * prepPP.setString(5, modo);
		 * 
		 * if (prepPP.executeUpdate() != 1) {throw new PersistenciaException("No ha sido posible realizar la operacion");}
		 * 
		 * rsPuntaje = prepPP.getGeneratedKeys();
		 * 
		 * if (rsPuntaje.next()) { puntaje = rsPuntaje.getInt(1); } rsPuntaje.close(); prepPP.close();
		 */
		try {
			if (modo == "puntaje") {

			}
			PreparedStatement prepP = t.prepareStatement("INSERT INTO Selecciones (idAdjudicacion) VALUES (?);");

			prepP.setInt(1, sorteo);

			if (prepP.executeUpdate() != 1) {
				throw new PersistenciaException("No ha sido posible realizar la operacion");
			}

			prepP.close();

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return sorteo;

	}

	public Sorteo buscarSorteo(Transaccion t, Calendar fecha) throws PersistenciaException {
		int numeroEvento = 0;
		int idAdjudicacion = 0;
		String descripcion = null;
		int cantSorteados = 0;
		String lugar = null;
		java.sql.Date fechaSorteo = new java.sql.Date(fecha.getTime().getTime());
		Sorteo sorteo = null;
		String[] zonaArray = null;
		Vector<String> zona = new Vector<String>();
		int cantPorPuntaje = 0;
		int cantTotal = 0;

		String sql = "SELECT * FROM Adjudicaciones WHERE fecha = ? and tipo = ?;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setDate(1, fechaSorteo);
			prep.setString(2, "sorteo");

			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				idAdjudicacion = rs.getInt(1);
				descripcion = rs.getString(3);
				cantSorteados = rs.getInt(4);
				lugar = rs.getString(5);
			} else {
				return sorteo;
			}
			rs.close();

			PreparedStatement prepP = t.prepareStatement(sql);
			prepP.setDate(1, fechaSorteo);
			prepP.setString(2, "puntaje");

			ResultSet rsP = prepP.executeQuery();

			if (rsP.next()) {
				cantPorPuntaje = rsP.getInt(4);
			}
			rsP.close();

			PreparedStatement prepS = t.prepareStatement("SELECT * FROM Sorteos WHERE idAdjudicacion = ?;");
			prepS.setInt(1, idAdjudicacion);

			ResultSet rsS = prepS.executeQuery();

			if (rsS.next()) {
				zonaArray = rsS.getString(2).split(" ");
				for (int i = 0; i < zonaArray.length; i++) {
					String departamento = zonaArray[i];
					zona.addElement(departamento);
				}
			}
			rsS.close();

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}

		sorteo = new Sorteo(fecha, lugar, zona, cantSorteados, cantPorPuntaje, cantTotal, numeroEvento, descripcion);
		return sorteo;
	}
}