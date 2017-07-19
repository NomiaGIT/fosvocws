/*    */package logica.aportantes;

/*    */
/*    */import java.io.Serializable;

/*    */
/*    */public class Categoria
/*    */implements Serializable
/*    */{
	/*    */private int id;
	/*    */private String descripcion;

	/*    */
	/*    */public Categoria(int id, String descripcion)
	/*    */{
		/* 19 */this.id = id;
		/* 20 */this.descripcion = descripcion;
		/*    */}

	/*    */
	/*    */public int getId()
	/*    */{
		/* 27 */return this.id;
		/*    */}

	/*    */
	/*    */public void setId(int id)
	/*    */{
		/* 35 */this.id = id;
		/*    */}

	/*    */
	/*    */public String getDescripcion()
	/*    */{
		/* 42 */return this.descripcion;
		/*    */}

	/*    */
	/*    */public void setDescripcion(String descripcion)
	/*    */{
		/* 50 */this.descripcion = descripcion;
		/*    */}
	/*    */
}

/*
 * Location: C:\Users\Sole\Desktop\servidor\ Qualified Name: logica.aportantes.Categoria JD-Core Version: 0.6.0
 */