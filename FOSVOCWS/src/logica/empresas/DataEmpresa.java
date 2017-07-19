/*    */package logica.empresas;

/*    */
/*    */public class DataEmpresa
/*    */{
	/*    */private int id;
	/*    */private Empresa empresa;

	/*    */
	/*    */public DataEmpresa(int id, Empresa empresa)
	/*    */{
		/* 10 */this.id = id;
		/* 11 */this.empresa = empresa;
		/*    */}

	/*    */
	/*    */public int getId() {
		/* 15 */return this.id;
		/*    */}

	/*    */
	/*    */public void setId(int id) {
		/* 19 */this.id = id;
		/*    */}

	/*    */
	/*    */public Empresa getEmpresa() {
		/* 23 */return this.empresa;
		/*    */}

	/*    */
	/*    */public void setEmpresa(Empresa empresa) {
		/* 27 */this.empresa = empresa;
		/*    */}
	/*    */
}

/*
 * Location: C:\Users\Sole\Desktop\servidor\ Qualified Name: logica.empresas.DataEmpresa JD-Core Version: 0.6.0
 */