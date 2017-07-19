/*    */package logica.aportantes;

/*    */
/*    */import java.io.Serializable;
/*    */
import java.util.Calendar;
/*    */
import logica.empresas.DataEmpresa;

/*    */
/*    */public class DataTrabajo
/*    */implements Serializable
/*    */{
	/*    */private int cedulaAportante;
	/*    */private String categoria;
	/*    */private Calendar inicioEnEmpresa;
	/*    */private Calendar inicioEnIndustria;
	/*    */private double ingresoMensual;
	/*    */private double otrosIngresos;
	/*    */private DataEmpresa dataEmpresa;

	/*    */
	/*    */public DataTrabajo(Integer cedulaAportante, String categoria, Calendar inicioEnEmpresa, Calendar inicioEnIndustria, Double ingresoMensual, Double otrosIngresos,
			DataEmpresa dataEmpresa)
	/*    */{
		/*    */}

	/*    */
	/*    */public int getCedulaAportante()
	/*    */{
		/* 24 */return this.cedulaAportante;
		/*    */}

	/*    */
	/*    */public void setCedulaAportante(int cedulaAportante) {
		/* 28 */this.cedulaAportante = cedulaAportante;
		/*    */}

	/*    */
	/*    */public String getCategoria() {
		/* 32 */return this.categoria;
		/*    */}

	/*    */
	/*    */public void setCategoria(String categoria) {
		/* 36 */this.categoria = categoria;
		/*    */}

	/*    */
	/*    */public Calendar getInicioEnEmpresa() {
		/* 40 */return this.inicioEnEmpresa;
		/*    */}

	/*    */
	/*    */public void setInicioEnEmpresa(Calendar inicioEnEmpresa) {
		/* 44 */this.inicioEnEmpresa = inicioEnEmpresa;
		/*    */}

	/*    */
	/*    */public Calendar getInicioEnIndustria() {
		/* 48 */return this.inicioEnIndustria;
		/*    */}

	/*    */
	/*    */public void setInicioEnIndustria(Calendar inicioEnIndustria) {
		/* 52 */this.inicioEnIndustria = inicioEnIndustria;
		/*    */}

	/*    */
	/*    */public double getIngresoMensual() {
		/* 56 */return this.ingresoMensual;
		/*    */}

	/*    */
	/*    */public void setIngresoMensual(double ingresoMensual) {
		/* 60 */this.ingresoMensual = ingresoMensual;
		/*    */}

	/*    */
	/*    */public double getOtrosIngresos() {
		/* 64 */return this.otrosIngresos;
		/*    */}

	/*    */
	/*    */public void setOtrosIngresos(double otrosIngresos) {
		/* 68 */this.otrosIngresos = otrosIngresos;
		/*    */}

	/*    */
	/*    */public DataEmpresa getDataEmpresa() {
		/* 72 */return this.dataEmpresa;
		/*    */}

	/*    */
	/*    */public void setDataEmpresa(DataEmpresa dataEmpresa) {
		/* 76 */this.dataEmpresa = dataEmpresa;
		/*    */}
	/*    */
}

/*
 * Location: C:\Users\Sole\Desktop\servidor\ Qualified Name: logica.aportantes.DataTrabajo JD-Core Version: 0.6.0
 */