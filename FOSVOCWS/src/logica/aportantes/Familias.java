package logica.aportantes;

import excepciones.PersistenciaException;
    
import java.sql.PreparedStatement;
    
import java.sql.ResultSet;
    
import java.sql.SQLException;
    
import persistencia.Transaccion;

    
    public class Familias
    {
	    public Familia buscarFamilia(Transaccion t, Integer cedulaAportante)
	    throws PersistenciaException
	    {
		Familia fam = null;
		 int cantMayores = 0;
		  int cantMenores = 0;
		 int cantDiscapacitados = 0;
		ResultSet rs = null;
		  PreparedStatement prep = null;
		    try
		    {
			 prep = t.prepareStatement("SELECT * FROM AportanteFamilia WHERE cedulaAportante = ?;");
			 prep.setInt(1, cedulaAportante.intValue());
			  rs = prep.executeQuery();
			    
			  if (rs.next()) {
				  cantMayores = rs.getInt(2);
				  cantMenores = rs.getInt(3);
				  cantDiscapacitados = rs.getInt(4);
				  fam = new Familia(cantMayores, cantMenores, cantDiscapacitados);
				    }
			    } catch (SQLException e) {
			 throw new PersistenciaException(e.getMessage());
			    } catch (PersistenciaException e) {
			  throw new PersistenciaException(e.getMensaje());
			    }
		    
		  return fam;
		    }

	    
	    public void insertarFamilia(Transaccion t, Familia fam, Integer cedulaAportante)
	    throws PersistenciaException
	    {
		  PreparedStatement prep = null;
		    try
		    {
			  prep = t.prepareStatement("INSERT INTO AportanteFamilia (cedulaAportante, cantMayores, cantMenores, cantDiscapacitados) VALUES (?,?,?,?);");
			  prep.setInt(1, cedulaAportante.intValue());
			  prep.setInt(2, fam.getCantMayores());
			  prep.setInt(3, fam.getCantMenores());
			  prep.setInt(4, fam.getCantDiscapacitados());
			    
			  if (prep.executeUpdate() != 1)
				  throw new PersistenciaException(
				  "No ha sido posible realizar la operación");
			    }
		    catch (SQLException e)
		    {
			  throw new PersistenciaException(e.getMessage());
			    } catch (PersistenciaException e) {
			  throw new PersistenciaException(e.getMessage());
			    }
		    }

	    
	    public void eliminarFamilia(Transaccion t, Integer cedulaAportante)
	    throws PersistenciaException
	    {
		  PreparedStatement prep = t.prepareStatement("DELETE FROM AportanteFamilia WHERE cedulaAportante = ?;");
		    try
		    {
			  prep.setInt(1, cedulaAportante.intValue());
			    
			  if (prep.executeUpdate() != 1)
				  throw new PersistenciaException(
				  "No ha sido posible realizar la operación");
			    }
		    catch (SQLException e) {
			  throw new PersistenciaException(e.getMessage());
			    } catch (PersistenciaException e) {
			  throw new PersistenciaException(e.getMensaje());
			    }
		    }

	    
	    public void modificarFamilia(Transaccion t, Familia fam, Integer cedulaAportante)
	    throws PersistenciaException
	    {
		  PreparedStatement prep = t.prepareStatement("UPDATE AportanteFamilia SET cantMayores = ?, cantMenores = ?, cantDiscapacitados = ? WHERE cedulaAportante = ?;");
		    try
		    {
			  prep.setInt(1, fam.getCantMayores());
			  prep.setInt(2, fam.getCantMenores());
			  prep.setInt(3, fam.getCantDiscapacitados());
			  prep.setInt(4, cedulaAportante.intValue());
			    
			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("Ha ha sido posible realizar la operación");
			    }
		    catch (SQLException e)
		    {
			throw new PersistenciaException(e.getMessage());
			    } catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
			    }
		    }
	    
}

 