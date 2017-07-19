    package logica.usuarios;

    
    import java.io.Serializable;

    
    @SuppressWarnings("serial")
	public class Rol
    implements Serializable
    {
	    private String tipo;
	    private String permisos;
	    private int id;

	    
	    public Rol(String tipo, String permisos, int id)
	    {
		  this.tipo = tipo;
		  this.permisos = permisos;
		  this.id = id;
		    }

	    
	    public String getTipo()
	    {
		  return this.tipo;
		    }

	    
	    public void setTipo(String tipo)
	    {
		  this.tipo = tipo;
		    }

	    
	    public String getPermisos()
	    {
		  return this.permisos;
		    }

	    
	    public void setPermisos(String permisos)
	    {
		  this.permisos = permisos;
		    }

	    
	    public int getId()
	    {
		  return this.id;
		    }

	    
	    public void setId(int id)
	    {
		  this.id = id;
		    }
	    
}


