package prestamos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import datatypes.DataAdjudicacion;
import datatypes.DataCuota;
import datatypes.DataListarAdjudicaciones;
import excepciones.PersistenciaException;
import logica.monedas.ValoresMoneda;
import logica.productos.refaccion.Refaccion;
import logica.solicitudes.Solicitud;
import logica.solicitudes.Solicitudes;
import persistencia.Transaccion;
import prestamos.datatypes.DataListadoCuotasAPagar;
import utilitarios.Formato;

public class Adjudicaciones {

	public DataAdjudicacion buscarAdjudicacion(Transaccion t, int id) throws PersistenciaException{
		DataAdjudicacion resu = null;
		Solicitudes solicitudes = new Solicitudes();
		SolicitudesAdjudicadas solicitudesAdjudicadas = new SolicitudesAdjudicadas();
		Contratos contratos = new Contratos();
		int plazo = 0;
		String tipo = null;
		Calendar fecha = Calendar.getInstance();
		Adjudicacion adjudicacion = null;
		int idAdjudicacion = solicitudesAdjudicadas.buscarIdAdjudicacion(t, id);
		if (idAdjudicacion != 0) {
			try {
				PreparedStatement prep = t.prepareStatement("SELECT * FROM Adjudicaciones WHERE id = ?;");
				prep.setInt(1, idAdjudicacion);
				ResultSet rs = prep.executeQuery();
				if (rs.next()) {
					fecha.setTime(rs.getDate(2));
					tipo = rs.getString(6);
					plazo = rs.getInt(7);
				}
				Solicitud solicitud = solicitudes.buscarSolicitudVersion2016(t, id);
				Contrato contrato = contratos.buscar(t, id);		
				ValoresMoneda v = new ValoresMoneda();
				Calendar hoy = Calendar.getInstance();
				double valorUI = v.getCotizacionUI(t, hoy.get(Calendar.MONTH), hoy.get(Calendar.YEAR));
				Cuotas c = new Cuotas();
				Vector<DataListadoCuotasAPagar> cuot = c.listarCuotasPorSolicitudVersion2(t, id);
				List<DataCuota> cuotas = new ArrayList<DataCuota>();				
				for(DataListadoCuotasAPagar cc : cuot)
				{
					DataCuota dc = new DataCuota(cc.getIdcuota(), cc.getNumeroCuota(), cc.getFechaVencimiento(),
							(int)cc.getMontoUI(), true, (int)cc.getMonto$(), cc.getFechaPago());
					cuotas.add(dc);
				}
				ModoAdjudicacion modoAdjudicacion = new ModoAdjudicacion(tipo);
				adjudicacion = new Adjudicacion(fecha, plazo, modoAdjudicacion, solicitud, contrato);
				resu = new DataAdjudicacion(Formato.convertirCalendarCorto(adjudicacion.getFecha()),0, modoAdjudicacion.getTipo(),
						solicitud.getId(), Formato.convertirCalendarCorto(fecha), (int)contrato.getCredito().getMontoUI(), contrato.getCantCuotasTotal(), 
						cuotas, ((Refaccion)solicitud.getPrestamo()).getCotizacion().getProveedor().getNombreBarraca(),
						solicitud.getEstado(), valorUI);			

			} catch (SQLException e) {
				throw new PersistenciaException("Error al acceder a los datos");
			} catch (PersistenciaException e) {
				e.printStackTrace();
				throw new PersistenciaException("Error al acceder al sistema");
			}
		}
		return resu;
	}

	public Vector<DataListarAdjudicaciones> listarAdjudicaciones(Transaccion t) throws PersistenciaException{
		Vector<DataListarAdjudicaciones> lista = new Vector<DataListarAdjudicaciones>();
		String sql = " select solicitudes.id, aportantes.cedula, usuarios.nombres, usuarios.apellidos, refacciones.totalsolicitado, contratos.fecha, proveedores.nombre, ordenesdecompra.monto, domicilios.telefonos"
				+ " from solicitudes, aportantes,usuarios,refacciones,adjudicaciones, proveedores, ordenesdecompra, solicitudesadjudicadas, prestamos, cotizaciones, contratos, domicilios"
				+ " where solicitudes.cedulaAportante = aportantes.cedula AND"
				+ " aportantes.cedula = usuarios.cedula"
				+ " AND solicitudes.id = solicitudesadjudicadas.idSolicitud"
				+ " AND solicitudesadjudicadas.idAdjudicacion = adjudicaciones.id"
				+ " AND solicitudes.idprestamo = prestamos.id"
				+ " AND prestamos.id = refacciones.idprestamo"
				+ " AND refacciones.idCotizacion = cotizaciones.id"
				+ " AND cotizaciones.idProveedor = proveedores.id"
				+ " AND contratos.idsolicitud = solicitudes.id"
				+ " AND domicilios.cedulaAportante = aportantes.cedula"
				+ " AND ordenesdecompra.idcontrato = contratos.id group by solicitudes.id" + " order by solicitudes.id;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int idSolicitud = rs.getInt(1);
				Calendar fecha = Calendar.getInstance();
				fecha.setTimeInMillis((rs.getDate(6)).getTime());
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
				String fechaS = sdf.format(fecha.getTime());
				int cedula = rs.getInt(2);
				String nombres = rs.getString(3);
				String apellidos = rs.getString(4);
				int monto = (int) Math.round(rs.getDouble(5));
				String proveedor = rs.getString(7);
				int montoPesos = rs.getInt(8);
				String telefonos = rs.getString(9);
				DataListarAdjudicaciones dataListadoAdjudicacion = new DataListarAdjudicaciones(idSolicitud, nombres, apellidos, cedula, monto, fechaS, proveedor, montoPesos,
						telefonos);
				lista.add(dataListadoAdjudicacion);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());

		}
		return lista;
	}

	public Vector<DataListarAdjudicaciones> listarAdjudicacionesDeAportante(Transaccion t, int cedula) throws PersistenciaException{
		Vector<DataListarAdjudicaciones> lista = new Vector<DataListarAdjudicaciones>();
		String sql = " select solicitudes.id, aportantes.cedula, usuarios.nombres, usuarios.apellidos, refacciones.totalsolicitado, contratos.fecha, proveedores.nombre, ordenesdecompra.monto, domicilios.telefonos, solicitudes.estado"
				+ " from solicitudes, aportantes,usuarios,refacciones,adjudicaciones, proveedores, ordenesdecompra, solicitudesadjudicadas, prestamos, cotizaciones, contratos, domicilios"
				+ " where solicitudes.cedulaAportante = aportantes.cedula AND"
				+ " aportantes.cedula = usuarios.cedula"
				+ " AND solicitudes.id = solicitudesadjudicadas.idSolicitud"
				+ " AND solicitudesadjudicadas.idAdjudicacion = adjudicaciones.id"
				+ " AND solicitudes.idprestamo = prestamos.id"
				+ " AND prestamos.id = refacciones.idprestamo"
				+ " AND refacciones.idCotizacion = cotizaciones.id"
				+ " AND cotizaciones.idProveedor = proveedores.id"
				+ " AND contratos.idsolicitud = solicitudes.id"
				+ " AND domicilios.cedulaAportante = aportantes.cedula"
				+ " AND ordenesdecompra.idcontrato = contratos.id"
				+ " AND aportantes.cedula = ? group by solicitudes.id" + " order by solicitudes.id;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1,  cedula);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int idSolicitud = rs.getInt(1);
				Calendar fecha = Calendar.getInstance();
				fecha.setTimeInMillis((rs.getDate(6)).getTime());
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
				String fechaS = sdf.format(fecha.getTime());				
				String nombres = rs.getString(3);
				String apellidos = rs.getString(4);
				int monto = (int) Math.round(rs.getDouble(5));
				String proveedor = rs.getString(7);
				int montoPesos = rs.getInt(8);
				String telefonos = rs.getString(9);
				String estado = rs.getString(10);
				DataListarAdjudicaciones dataListadoAdjudicacion = new DataListarAdjudicaciones(idSolicitud, nombres, apellidos, cedula, monto, fechaS, proveedor, montoPesos,
						telefonos, estado);
				lista.add(dataListadoAdjudicacion);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());

		}
		return lista;
	}
	public int buscar(Transaccion t, Adjudicacion adj) throws PersistenciaException {

		java.sql.Date fecha = new java.sql.Date(adj.getFecha().getTime().getTime());
		int idAdjudicacion = 0;
		String tipo = adj.getModo().getTipo();
		String sql = "select adjudicaciones.id from adjudicaciones where fecha = ? and tipo = ?;";
		try {
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setDate(1, fecha);
			prep.setString(2, tipo);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				idAdjudicacion = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return idAdjudicacion;
	}

}
