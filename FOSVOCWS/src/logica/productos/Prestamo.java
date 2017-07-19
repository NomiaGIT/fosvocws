    package logica.productos;

    
    import java.io.Serializable;

    
    @SuppressWarnings("serial")
	public class Prestamo
    implements Serializable
    {
	    private String Tipo;
	    private String Nombre;
	    public int Codigo;
	    private lineaPrestamo linea;

	    
	    public Prestamo(String tipo, String nombre, int codigo, lineaPrestamo linea)
	    {
		  this.Tipo = tipo;
		  this.Nombre = nombre;
		  this.Codigo = codigo;
		  this.linea = linea;
		    }

	    
	    public Prestamo(String tipo, String nombre, lineaPrestamo linea) {
		  this.Tipo = tipo;
		  this.Nombre = nombre;
		  this.Codigo = 0;
		  this.linea = linea;
		    }

	    
	    public String getTipo()
	    {
		  return this.Tipo;
		    }

	    
	    public void setTipo(String tipo)
	    {
		  this.Tipo = tipo;
		    }

	    
	    public String getNombre()
	    {
		  return this.Nombre;
		    }

	    
	    public void setNombre(String nombre)
	    {
		  this.Nombre = nombre;
		    }

	    
	    public int getCodigo()
	    {
		  return this.Codigo;
		    }

	    
	    public void setCodigo(int codigo)
	    {
		  this.Codigo = codigo;
		    }

	    
	    public lineaPrestamo getLinea()
	    {
		  return this.linea;
		    }

	    
	    public void setLinea(lineaPrestamo linea)
	    {
		  this.linea = linea;
		    }
	    
}
