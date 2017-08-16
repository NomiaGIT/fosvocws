package logica.solicitudes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

import datatypes.DataListarSolicitudes;
import datatypes.DataSolicitud;
import datatypes.DataSolicitudweb;
import excepciones.PersistenciaException;
import logica.aportantes.Aportante;
import logica.aportantes.Aportantes;
import logica.productos.refaccion.Refaccion;
import logica.productos.refaccion.Refacciones;
import persistencia.Transaccion;
import utilitarios.Formato;

public class Solicitudes {
	
	public DataSolicitudweb buscarSolicitudRefaccionVersion2016web(Transaccion t, int idSolicitud) throws PersistenciaException {
		DataSolicitudweb dataSolicitud = null;
		Calendar fecha = Calendar.getInstance();
		Refacciones refacciones = new Refacciones();
		Refaccion refaccion = null;

		PreparedStatement prep = t.prepareStatement("SELECT * FROM Solicitudes WHERE id = ?;");
		try {
			prep.setInt(1, idSolicitud);
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				fecha.setTime(rs.getDate(2));
				String estado = rs.getString(3);
				int cedulaAportante = Integer.valueOf(rs.getInt(4));				
				refaccion = refacciones.buscarRefaccionVersion2016(t, rs.getInt(5), cedulaAportante, idSolicitud);		
				int idplan = getPlan(t, idSolicitud);
				Vector<String>descr = refaccion.getDetalle();
				String descripcion = "";
				if(!descr.isEmpty())
				{
					for(int i = 0; i< descr.size(); i++)
						descripcion = descripcion + descr.get(i)+" ";
				} 
				else
					descripcion = "Sin descripción";
				dataSolicitud = new DataSolicitudweb(idSolicitud, estado, Formato.convertirCalendarCorto(fecha),
						(int)refaccion.getTotalSolicitado(), idplan, refaccion.getCantCuotasSolicitadas(), refaccion.getCotizacion().getProveedor().getNombreBarraca(),
						descripcion, refaccion.getVivienda().getCalle(), 
						refaccion.getVivienda().getNumero(), refaccion.getVivienda().getCiudad(), refaccion.getVivienda().getDepartamento());
						
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return dataSolicitud;
	}
	public Solicitud buscarSolicitudVersion2016(Transaccion t, int idSolicitud) throws PersistenciaException {
		Solicitud dataSolicitud = null;
		Calendar fecha = Calendar.getInstance();
		Aportantes aportantes = new Aportantes();
		Refacciones refacciones = new Refacciones();
		Refaccion refaccion = null;
		Aportante aportante = null;

		PreparedStatement prep = t.prepareStatement("SELECT * FROM Solicitudes WHERE id = ?;");
		try {
			prep.setInt(1, idSolicitud);
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				fecha.setTime(rs.getDate(2));
				String estado = rs.getString(3);
				aportante = aportantes.buscarAportante(t, Integer.valueOf(rs.getInt(4)));
				refaccion = refacciones.buscarRefaccionVersion2016(t, rs.getInt(5), aportante.getCedula(), idSolicitud);
				int puntaje = rs.getInt(6);				
				dataSolicitud = new Solicitud(idSolicitud,fecha, estado, aportante, refaccion, puntaje);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return dataSolicitud;
	}
	public Vector<DataListarSolicitudes> listarDataListadoSolicitudes(Transaccion t) throws PersistenciaException {
		Vector<DataListarSolicitudes> lista = new Vector<DataListarSolicitudes>();
		try {
			String consulta = "SELECT Solicitudes.id, Solicitudes.puntaje, Usuarios.cedula, Usuarios.nombres, Usuarios.apellidos, Domicilios.departamento, Refacciones.totalSolicitado, Solicitudes.estado FROM Solicitudes, Usuarios, Refacciones, Prestamos, Domicilios WHERE Solicitudes.cedulaAportante = Usuarios.cedula AND usuarios.cedula = domicilios.cedulaAportante AND solicitudes.idPrestamo = Prestamos.id AND Prestamos.id = Refacciones.idPrestamo GROUP BY Solicitudes.id";
			PreparedStatement prep = t.prepareStatement(consulta);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int idSolicitud = rs.getInt(1);
				int cedula = rs.getInt(3);
				String nombre = rs.getString(4);
				String ape = rs.getString(5);
				String departamento = rs.getString(6);
				int puntaje = rs.getInt(2);
				double montoSolicitado = rs.getDouble(7);
                String estado = rs.getString(8);
				DataListarSolicitudes dls = new DataListarSolicitudes(idSolicitud, cedula, nombre, ape, departamento, puntaje, montoSolicitado, estado);
				lista.add(dls);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return lista;
	}
	public Vector<DataListarSolicitudes> listarDataListadoSolicitudesDeAportante(Transaccion t, int cedula) throws PersistenciaException{
		Vector<DataListarSolicitudes> lista = new Vector<DataListarSolicitudes>();
		try {
			String consulta = "SELECT Solicitudes.id, Solicitudes.puntaje, Usuarios.cedula, Usuarios.nombres, Usuarios.apellidos, Domicilios.departamento, Refacciones.totalSolicitado, Solicitudes.estado FROM Solicitudes, Usuarios, Refacciones, Prestamos, Domicilios WHERE Solicitudes.cedulaAportante = Usuarios.cedula AND usuarios.cedula = domicilios.cedulaAportante AND solicitudes.idPrestamo = Prestamos.id AND Prestamos.id = Refacciones.idPrestamo AND Solicitudes.cedulaAportante = ? GROUP BY Solicitudes.id";
			PreparedStatement prep = t.prepareStatement(consulta);
			prep.setInt(1, cedula);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int idSolicitud = rs.getInt(1);				
				String nombre = rs.getString(4);
				String ape = rs.getString(5);
				String departamento = rs.getString(6);
				int puntaje = rs.getInt(2);
				double montoSolicitado = rs.getDouble(7);
                String estado = rs.getString(8);
				DataListarSolicitudes dls = new DataListarSolicitudes(idSolicitud, cedula, nombre, ape, departamento, puntaje, montoSolicitado, estado);
				lista.add(dls);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return lista;
	}
	public Vector<DataListarSolicitudes> listarDataListadoSolicitudesActivasDeAportante(Transaccion t, int cedula) throws PersistenciaException{
		Vector<DataListarSolicitudes> lista = new Vector<DataListarSolicitudes>();
		try {
			String consulta = "SELECT Solicitudes.id, Solicitudes.puntaje, Usuarios.cedula, Usuarios.nombres, Usuarios.apellidos, Domicilios.departamento, Refacciones.totalSolicitado, Solicitudes.estado FROM Solicitudes, Usuarios, Refacciones, Prestamos, Domicilios WHERE Solicitudes.cedulaAportante = Usuarios.cedula AND usuarios.cedula = domicilios.cedulaAportante AND solicitudes.idPrestamo = Prestamos.id AND Prestamos.id = Refacciones.idPrestamo AND Solicitudes.cedulaAportante = ? AND solicitudes.estado IN ('ingresada', 'aprobada') GROUP BY Solicitudes.id";
			PreparedStatement prep = t.prepareStatement(consulta);
			prep.setInt(1, cedula);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int idSolicitud = rs.getInt(1);				
				String nombre = rs.getString(4);
				String ape = rs.getString(5);
				String departamento = rs.getString(6);
				int puntaje = rs.getInt(2);
				double montoSolicitado = rs.getDouble(7);
                String estado = rs.getString(8);
				DataListarSolicitudes dls = new DataListarSolicitudes(idSolicitud, cedula, nombre, ape, departamento, puntaje, montoSolicitado, estado);
				lista.add(dls);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return lista;
	}
	//OTROS METODOS AUXILIARES
	public int buscarIdPrestamo(Transaccion t, int idSolicitud) throws PersistenciaException {
		int resu = 0;
		PreparedStatement prep = t.prepareStatement("SELECT idPrestamo FROM Solicitudes WHERE id=?;");
		try {
			prep.setInt(1, idSolicitud);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				resu = rs.getInt(1);
			} else
				throw new PersistenciaException("No ha sido posible realizar la operacion");
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return resu;
	}	
	public int buscarCedula(Transaccion t, int idSolicitud) throws PersistenciaException {
		int cedula = 0;

		try {
			String sql = "SELECT cedulaAportante FROM Solicitudes WHERE id = ?;";
			PreparedStatement prep = t.prepareStatement(sql);
			prep.setInt(1, idSolicitud);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				cedula = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMensaje());
		}
		return cedula;
	}
	public int getPlan(Transaccion t, int idSolicitud) throws PersistenciaException {
		int idplan = 0;
		String sql = "SELECT idlineaprestamo from prestamos where id = (select idprestamo from solicitudes where id = ?);";
		try {
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, idSolicitud);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				idplan = rs.getInt(1);
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return idplan;
	}

}
