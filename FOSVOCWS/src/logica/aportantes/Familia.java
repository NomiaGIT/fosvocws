/*    */package logica.aportantes;

/*    */
/*    */import java.io.Serializable;

/*    */
/*    */public class Familia
/*    */implements Serializable
/*    */{
	private int CantMayores;
	private int cantMenores;
	private int cantDiscapacitados;

	/*    */
	/*    */public Familia(int cantMayores, int cantMenores, int cantDiscapacitados)
	/*    */{
		/* 23 */this.CantMayores = cantMayores;
		/* 24 */this.cantMenores = cantMenores;
		/* 25 */this.cantDiscapacitados = cantDiscapacitados;
		/*    */}

	/*    */
	/*    */public int getCantMayores()
	/*    */{
		/* 32 */return this.CantMayores;
		/*    */}

	/*    */
	/*    */public void setCantMayores(int cantMayores)
	/*    */{
		/* 40 */this.CantMayores = cantMayores;
		/*    */}

	/*    */
	/*    */public int getCantMenores()
	/*    */{
		/* 47 */return this.cantMenores;
		/*    */}

	/*    */
	/*    */public void setCantMenores(int cantMenores)
	/*    */{
		/* 55 */this.cantMenores = cantMenores;
		/*    */}

	/*    */
	/*    */public int getCantDiscapacitados()
	/*    */{
		/* 62 */return this.cantDiscapacitados;
		/*    */}

	/*    */
	/*    */public void setCantDiscapacitados(int cantDiscapacitados)
	/*    */{
		/* 70 */this.cantDiscapacitados = cantDiscapacitados;
		/*    */}

	/*    */
	/*    */public int getId()
	/*    */{
		/* 75 */return 0;
		/*    */}
	/*    */
}

/*
 * Location: C:\Users\Sole\Desktop\servidor\ Qualified Name: logica.aportantes.Familia JD-Core Version: 0.6.0
 */