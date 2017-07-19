package logica.aportantes;

import excepciones.PersistenciaException;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.Calendar;

import logica.empresas.DataEmpresa;

import logica.empresas.Empresa;

import logica.empresas.Empresas;

import persistencia.Transaccion;

public class Trabajos {
	public Trabajo buscarTrabajo(Transaccion t, Integer cedulaAportante) throws PersistenciaException {
		/* 20 */Trabajo tra = null;
		/* 21 */String categoria = null;
		/* 22 */Calendar inicioEnEmpresa = Calendar.getInstance();
		/* 23 */Calendar inicioEnIndustria = Calendar.getInstance();
		/* 24 */Double ingresoMensual = null;
		/* 25 */Double otrosIngresos = null;
		/* 26 */int idEmpresa = 0;
		try {
			/* 30 */PreparedStatement prep = t
			/* 31 */.prepareStatement("SELECT * FROM Trabajos WHERE cedulaAportante = ?;");

			/* 33 */prep.setInt(1, cedulaAportante.intValue());
			/* 34 */ResultSet rs = prep.executeQuery();

			/* 37 */if (rs.next()) {
				/* 38 */categoria = rs.getString(2);
				/* 39 */inicioEnEmpresa.setTime(rs.getDate(3));
				/* 40 */inicioEnIndustria.setTime(rs.getDate(4));
				/* 41 */ingresoMensual = Double.valueOf(rs.getDouble(5));
				/* 42 */otrosIngresos = Double.valueOf(rs.getDouble(6));
				/* 43 */idEmpresa = rs.getInt(7);
				/* 44 */Empresas empres = new Empresas();
				/* 45 */Empresa empresa = empres.buscarEmpresaPorId(t, idEmpresa);
				/* 46 */tra = new Trabajo(cedulaAportante.intValue(), categoria, inicioEnEmpresa,
				/* 47 */inicioEnIndustria, ingresoMensual.doubleValue(), otrosIngresos.doubleValue(),
				/* 48 */empresa);
			}
		} catch (SQLException e) {
			/* 52 */throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			/* 54 */throw new PersistenciaException(e.getMensaje());
		}

		/* 57 */return tra;
	}

	public DataTrabajo buscarTrabajoConDataEmpresa(Transaccion t, Integer cedulaAportante) throws PersistenciaException {
		/* 63 */DataTrabajo dataTrabajo = null;
		/* 64 */String categoria = null;
		/* 65 */Calendar inicioEnEmpresa = Calendar.getInstance();
		/* 66 */Calendar inicioEnIndustria = Calendar.getInstance();
		/* 67 */Double ingresoMensual = null;
		/* 68 */Double otrosIngresos = null;
		/* 69 */int idEmpresa = 0;
		/* 70 */Empresa empresa = null;
		/* 71 */DataEmpresa dataEmpresa = null;
		try {
			/* 74 */PreparedStatement prep = t
			/* 75 */.prepareStatement("SELECT * FROM Trabajos WHERE cedulaAportante = ?;");

			/* 77 */prep.setInt(1, cedulaAportante.intValue());
			/* 78 */ResultSet rs = prep.executeQuery();

			/* 81 */if (rs.next()) {
				/* 82 */categoria = rs.getString(2);
				/* 83 */inicioEnEmpresa.setTime(rs.getDate(3));
				/* 84 */inicioEnIndustria.setTime(rs.getDate(4));
				/* 85 */ingresoMensual = Double.valueOf(rs.getDouble(5));
				/* 86 */otrosIngresos = Double.valueOf(rs.getDouble(6));
				/* 87 */idEmpresa = rs.getInt(7);
				/* 88 */Empresas empres = new Empresas();
				/* 89 */empresa = empres.buscarEmpresa(t, idEmpresa);
				/* 90 */dataEmpresa = new DataEmpresa(idEmpresa, empresa);
				/* 91 */dataTrabajo = new DataTrabajo(cedulaAportante, categoria,
				/* 92 */inicioEnEmpresa, inicioEnIndustria, ingresoMensual,
				/* 93 */otrosIngresos, dataEmpresa);
			}
		} catch (SQLException e) {
			/* 97 */throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			/* 99 */throw new PersistenciaException(e.getMensaje());
		}

		return dataTrabajo;
	}

	public void insertarTrabajo(Transaccion t, Trabajo trabajo) throws PersistenciaException {
		Empresa em = trabajo.getEmpresa();
		Calendar inicioEnEmpresaCalendar = trabajo.getInicioEnEmpresa();
		java.sql.Date inicioEnEmpresa = new java.sql.Date(inicioEnEmpresaCalendar.getTime().getTime());

		Calendar inicioEnIndustriaCalendar = trabajo.getInicioEnIndustria();
		java.sql.Date incioEnIndustria = new java.sql.Date(inicioEnIndustriaCalendar.getTime().getTime());

		PreparedStatement prep = t
				.prepareStatement("INSERT INTO Trabajos(cedulaAportante, categoria, antiguedadEmpresa, antiguedadIndustria, ingresoMensual, otrosIngresos, idEmpresa) VALUES (?,?,?,?,?,?,?);");
		try {
			prep.setInt(1, trabajo.getCedulaAportante());
			prep.setString(2, trabajo.getCategoria());
			prep.setDate(3, inicioEnEmpresa);
			prep.setDate(4, incioEnIndustria);
			prep.setDouble(5, trabajo.getIngresoMensual());
			prep.setDouble(6, trabajo.getOtrosIngresos());
			prep.setInt(7, em.getId());

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("Ha ha sido posible realizar la operacion");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public void eliminarTrabajo(Transaccion t, Trabajo trabajo) throws PersistenciaException {
		PreparedStatement prep = t.prepareStatement("DELETE FROM Trabajos WHERE cedulaAportante = ?;");
		try {
			prep.setInt(1, trabajo.getCedulaAportante());

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operacion");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
	}

	public void modificarTrabajo(Transaccion t, Trabajo trabajo) throws PersistenciaException {
		Empresa em = trabajo.getEmpresa();
		String nomEmpresa = em.getNombre();
		Empresas empresas = new Empresas();
		DataEmpresa dataEmpresa = empresas.buscarDataEmpresa(t, nomEmpresa);
		int clave = dataEmpresa.getId();

		Calendar inicioEnEmpresaCalendar = trabajo.getInicioEnEmpresa();
		java.sql.Date inicioEnEmpresa = new java.sql.Date(inicioEnEmpresaCalendar.getTime().getTime());

		Calendar inicioEnIndustriaCalendar = trabajo.getInicioEnIndustria();
		java.sql.Date inicioEnIndustria = new java.sql.Date(inicioEnIndustriaCalendar.getTime().getTime());

		PreparedStatement prep = t
				.prepareStatement("UPDATE Trabajos SET categoria = ?, antiguedadEmpresa = ?, antiguedadIndustria = ?, ingresoMensual = ?, otrosIngresos = ?, idEmpresa = ? WHERE cedulaAportante = ?;");
		try {
			prep.setString(1, trabajo.getCategoria());
			prep.setDate(2, inicioEnEmpresa);
			prep.setDate(3, inicioEnIndustria);
			prep.setDouble(4, trabajo.getIngresoMensual());
			prep.setDouble(5, trabajo.getOtrosIngresos());
			prep.setLong(6, clave);
			prep.setInt(7, trabajo.getCedulaAportante());

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operacion");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

}

/*
 * Location: C:\Users\Sole\Desktop\servidor\ Qualified Name: logica.aportantes.Trabajos JD-Core Version: 0.6.0
 */