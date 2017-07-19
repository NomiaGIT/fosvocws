    package logica.usuarios;

    
    import java.io.Serializable;
    
import java.util.Calendar;

    
    @SuppressWarnings("serial")
public class Accion
    implements Serializable
    {
	    public Calendar fecha;
	    public String tipo;
	    public String descripcion;
	    public int clave;

	    
	    public Accion(Calendar fecha, String tipo, String descripcion, int clave)
	    {
		  this.fecha = fecha;
		  this.tipo = tipo;
		  this.descripcion = descripcion;
		  this.clave = clave;
		    }

	    
	    public Calendar getFecha()
	    {
		  return this.fecha;
		    }

	    
	    public void setFecha(Calendar fecha)
	    {
		  this.fecha = fecha;
		    }

	    
	    public String getTipo()
	    {
		  return this.tipo;
		    }

	    
	    public void setTipo(String tipo)
	    {
		  this.tipo = tipo;
		    }

	    
	    public String getDescripcion()
	    {
		  return this.descripcion;
		    }

	    
	    public void setDescripcion(String descripcion)
	    {
		  this.descripcion = descripcion;
		    }

	    
	    public int getClave()
	    {
		  return this.clave;
		    }

	    
	    public void setClave(int clave)
	    {
		  this.clave = clave;
		    }
	
}

