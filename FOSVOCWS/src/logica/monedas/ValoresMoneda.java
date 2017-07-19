package logica.monedas;

import excepciones.PersistenciaException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import persistencia.Transaccion;

public class ValoresMoneda {
	public double getCotizacionUI(Transaccion t, int mes, int anio) throws PersistenciaException {
		return buscarValorUI(t, mes, anio);
	}

	public void ingresarValorUI(Transaccion t, double valor, int mes, int anio) throws PersistenciaException {
		int dia = 1;

		int idMoneda = 1;

		Calendar calendar = Calendar.getInstance();
		calendar.set(anio, mes, dia);

		java.sql.Date fecha = new java.sql.Date(calendar.getTime().getTime());

		PreparedStatement prep = t.prepareStatement("INSERT INTO ValoresMoneda (idMoneda, fecha, valorPesos) VALUES (?,?,?);");
		try {
			prep.setInt(1, idMoneda);
			prep.setDate(2, fecha);
			prep.setDouble(3, valor);

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operación");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public double buscarValorUI(Transaccion t, int mes, int anio) throws PersistenciaException {
		double valor = 0.0D;
		int dia = 1;
		Calendar calendar = Calendar.getInstance();
		calendar.set(anio, mes, dia);

		java.sql.Date fecha = new java.sql.Date(calendar.getTime().getTime());
		try {
			PreparedStatement prep = t.prepareStatement("SELECT valorPesos FROM ValoresMoneda WHERE fecha = ? AND idMoneda = ?;");

			prep.setDate(1, fecha);

			prep.setInt(2, 1);

			ResultSet rs = prep.executeQuery();

			if (rs.next())
				valor = rs.getDouble(1);

			if (valor == 0)// si no hay valor, busco el del mes anterior
			{
				calendar.add(Calendar.MONTH, -1);
				int mesA = calendar.get(Calendar.MONTH);
				int anioA = calendar.get(Calendar.YEAR);
				valor = this.buscarValorUI(t, mesA, anioA);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return valor;
	}

	public void modificarValorUI(Transaccion t, double nuevoValor, int mes, int anio) throws PersistenciaException {
		int dia = 1;
		Calendar calendar = Calendar.getInstance();
		calendar.set(anio, mes, dia);

		java.sql.Date fecha = new java.sql.Date(calendar.getTime().getTime());

		PreparedStatement prep = t.prepareStatement("UPDATE TipoMoneda SET valorPesos = ? WHERE fecha = ? AND idMoneda = ?;");
		try {
			prep.setDouble(1, nuevoValor);
			prep.setDate(2, fecha);

			prep.setInt(3, 1);

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operación");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	/**
	 * permite que el valor sea cero, en diferencia del otro que si es 0, busca el del mes anterior
	 */
	public double getCotizacionUIUnico(Transaccion t, int mes, int anio) throws PersistenciaException {
		// TODO Auto-generated method stub
		double valor = 0.0D;
		int dia = 1;
		Calendar calendar = Calendar.getInstance();
		calendar.set(anio, mes, dia);

		java.sql.Date fecha = new java.sql.Date(calendar.getTime().getTime());
		try {
			PreparedStatement prep = t.prepareStatement("SELECT valorPesos FROM ValoresMoneda WHERE fecha = ? AND idMoneda = ?;");

			prep.setDate(1, fecha);
			prep.setInt(2, 1);
			ResultSet rs = prep.executeQuery();

			if (rs.next())
				valor = rs.getDouble(1);

		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return valor;
	}
}
