package prestamos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import persistencia.Transaccion;
import excepciones.PersistenciaException;

public class Comentarios {

	public String[] listarComentarios(Transaccion t, int numeroSolicitud) throws PersistenciaException {
		int cant = this.cantidadDecomentarios(t, numeroSolicitud);
		String[] resu = new String[cant];
		String sql = "SELECT Comentario FROM Comentarios WHERE idSolicitud = ?;";
		PreparedStatement ps = t.prepareStatement(sql);
		try {
			ps.setInt(1, numeroSolicitud);
			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				String a = rs.getString(1);
				resu[i] = a;
				i++;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public void agregarComentario(Transaccion t, int numeroSolicitud, String comentario) throws PersistenciaException {
		String sql = "INSERT INTO Comentarios  VALUES (?,?);";
		PreparedStatement ps = t.prepareStatement(sql);
		try {
			ps.setInt(1, numeroSolicitud);
			ps.setString(2, comentario);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new PersistenciaException(e.getMessage());
		}
	}

	public void borrarComentario(Transaccion t, int numeroSolicitud, String comentario) throws PersistenciaException {
		String sql = "DELETE FROM Comentarios  WHERE idSolicitud = ? AND Comentario = ?;";
		PreparedStatement ps = t.prepareStatement(sql);
		try {
			ps.setInt(1, numeroSolicitud);
			ps.setString(2, comentario);
			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new PersistenciaException(e.getMessage());
		}
	}

	public int cantidadDecomentarios(Transaccion t, int numeroSolicitud) throws PersistenciaException {
		int resu = 0;
		String sql = "SELECT COUNT(*) FROM Comentarios WHERE idSolicitud = ?;";
		PreparedStatement ps = t.prepareStatement(sql);
		try {
			ps.setInt(1, numeroSolicitud);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resu = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new PersistenciaException(e.getMessage());
		}

		return resu;
	}

	public Calendar buscarFechaComentarioMorosa(Transaccion t, int numeroSolicitud) throws PersistenciaException {
		Calendar resu = null;
		String sql = "SELECT * FROM comentarios where idsolicitud = ? AND comentario LIKE '%Adjudicacion determinada como morosa%'";
		try {
			PreparedStatement ps = t.prepareStatement(sql);
			ps.setInt(1, numeroSolicitud);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String comentario = rs.getString("Comentario");
				String[] partes = comentario.split(" ");
				String dia = partes[7];
				String mes = partes[9];
				String anio = partes[11];
				// System.out.println(dia+" "+mes+" "+anio);
				int diaI, anioI, mesI;
				try {
					diaI = Integer.parseInt(dia);
					anioI = Integer.parseInt(anio);
					mesI = 0;
					if (mes.equals("Enero"))
						mesI = 0;
					else if (mes.equals("Febrero"))
						mesI = 1;
					else if (mes.equals("Marzo"))
						mesI = 2;
					else if (mes.equals("Abril"))
						mesI = 3;
					else if (mes.equals("Mayo"))
						mesI = 4;
					else if (mes.equals("Junio"))
						mesI = 5;
					else if (mes.equals("Julio"))
						mesI = 6;
					else if (mes.equals("Agosto"))
						mesI = 7;
					else if (mes.equals("Setiembre"))
						mesI = 8;
					else if (mes.equals("Octubre"))
						mesI = 9;
					else if (mes.equals("Noviembre"))
						mesI = 10;
					else if (mes.equals("Diciembre"))
						mesI = 11;
					resu = Calendar.getInstance();
					resu.set(anioI, mesI, diaI);
				} catch (NumberFormatException ex) {
					System.out.println(comentario);
					throw new PersistenciaException("error en formato comentario " + comentario);

				}
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {

			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public void agregarComentarioExtra(Transaccion t, int cedula, String comentario) throws PersistenciaException {
		String sql = "INSERT INTO ComentariosExtra  VALUES (?,?);";
		PreparedStatement ps = t.prepareStatement(sql);
		try {
			ps.setInt(1, cedula);
			ps.setString(2, comentario);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new PersistenciaException(e.getMessage());
		}
	}

	public String[] listarComentariosExtra(Transaccion t, int cedula) throws PersistenciaException {
		int cant = this.cantidadDecomentariosExtra(t, cedula);
		String[] resu = new String[cant];
		String sql = "SELECT Comentario FROM ComentariosExtra WHERE cedula = ?;";
		PreparedStatement ps = t.prepareStatement(sql);
		try {
			ps.setInt(1, cedula);
			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				String a = rs.getString(1);
				resu[i] = a;
				i++;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new PersistenciaException(e.getMessage());
		}
		return resu;
	}

	public int cantidadDecomentariosExtra(Transaccion t, int cedula) throws PersistenciaException {
		int resu = 0;
		String sql = "SELECT COUNT(*) FROM ComentariosExtra WHERE cedula = ?;";
		PreparedStatement ps = t.prepareStatement(sql);
		try {
			ps.setInt(1, cedula);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resu = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new PersistenciaException(e.getMessage());
		}

		return resu;
	}
	// es dificil sacarlo por aca proque hay muchos comentarios diferentes
	/*
	 * public Calendar buscarFechaComentarioFinalizacion(Transaccion t, int numeroSolicitud) throws PersistenciaException{ Calendar resu =
	 * null; String sql = "SELECT * FROM comentarios where idsolicitud = ? AND comentario LIKE '%Adjudicacion determinada como morosa%'" ;
	 * try { PreparedStatement ps = t.prepareStatement(sql); ps.setInt(1, numeroSolicitud); ResultSet rs = ps.executeQuery(); if(rs.next())
	 * { String comentario = rs.getString("Comentario"); String[] partes = comentario.split(" "); String dia = partes[6]; String mes =
	 * partes[7]; String anio = partes[8]; int diaI, anioI, mesI; try { diaI = Integer.parseInt(dia); anioI = Integer.parseInt(anio); mesI =
	 * 0; if(mes.equals("Enero")) mesI = 0; else if(mes.equals("Febrero")) mesI = 1; else if(mes.equals("Marzo")) mesI = 2; else
	 * if(mes.equals("Abril")) mesI = 3; else if(mes.equals("Mayo")) mesI = 4; else if(mes.equals("Junio")) mesI = 5; else
	 * if(mes.equals("Julio")) mesI = 6; else if(mes.equals("Agosto")) mesI = 7; else if(mes.equals("Setiembre")) mesI = 8; else
	 * if(mes.equals("Octubre")) mesI = 9; else if(mes.equals("Noviembre")) mesI = 10; else if(mes.equals("Diciembre")) mesI = 11; resu =
	 * Calendar.getInstance(); resu.set(anioI, mesI, diaI); } catch(NumberFormatException ex) { throw new
	 * PersistenciaException("error en formato comentario "+comentario); } } rs.close(); ps.close(); } catch (SQLException e) {
	 * 
	 * throw new PersistenciaException(e.getMessage()); } return resu; }
	 */
}
