package datatypes;

import java.io.Serializable;
import com.google.gson.Gson;


@SuppressWarnings("serial")
public class DataAportante implements Serializable {
	private String nick;
	private String pass;
	private boolean conectado;
	private int cedula;
	private String nombre;
	private String apellidos;
	private String rol;
	private boolean activo;
	private String sexo;	
	private String email;
	public String categoria;
	private String fechaNacimiento;
	private String nacionalidad;
	private String estadoCivil;
	
	//datos conyuge
	private int cedulaCon;
	private String nombresCon;
	private String apellidosCon;
	private String FechaNacimientoCon;
	private String NacionalidadCon;
	private String EstadoCivilCon;
	private String SexoCon;
	//datos familia
	private int CantMayoresFamilia;
	private int cantMenoresFamilia;
	private int cantDiscapacitadosFamilia;
	// datos trabajo
	private String CategoriaTrabajo;
	private String InicioEnEmpresa;
	private String InicioEnIndustria;
	private double IngresoMensualTrabajo;
	private double OtrosIngresosTrabajo;
	private String nombreEmpresaTrabajo;
	private String DireccionEmpresaTrabajo;
	private String TelefonoEmpresaTrabajo;
	private long RUTEmpresaTrabajo;
	//datos domicilio
	private String CalleDomicilio;
	private String NumeroDomicilio;
	private String BarrioDomicilio;
	private String CiudadDomicilio;
	private String DepartamentoDomicilio;
	private String TelefonoDomicilio;
	public DataAportante(String nick, String pass, boolean conectado, int cedula, String nombre, String apellidos, String rol, boolean activo, String sexo, String email,
			String categoria, String fechaNacimiento, String nacionalidad, String estadoCivil, int cedulaCon, String nombresCon, String apellidosCon, String fechaNacimientoCon,
			String nacionalidadCon, String estadoCivilCon, String sexoCon, int cantMayoresFamilia, int cantMenoresFamilia, int cantDiscapacitadosFamilia, String categoriaTrabajo,
			String inicioEnEmpresa, String inicioEnIndustria, double ingresoMensualTrabajo, double otrosIngresosTrabajo, String nombreEmpresaTrabajo,
			String direccionEmpresaTrabajo, String telefonoEmpresaTrabajo, long rUTEmpresaTrabajo, String calleDomicilio, String numeroDomicilio, String barrioDomicilio,
			String ciudadDomicilio, String departamentoDomicilio, String telefonoDomicilio) {
		super();
		this.nick = nick;
		this.pass = pass;
		this.conectado = conectado;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.rol = rol;
		this.activo = activo;
		this.sexo = sexo;
		this.email = email;
		this.categoria = categoria;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.estadoCivil = estadoCivil;
		this.cedulaCon = cedulaCon;
		this.nombresCon = nombresCon;
		this.apellidosCon = apellidosCon;
		FechaNacimientoCon = fechaNacimientoCon;
		NacionalidadCon = nacionalidadCon;
		EstadoCivilCon = estadoCivilCon;
		SexoCon = sexoCon;
		CantMayoresFamilia = cantMayoresFamilia;
		this.cantMenoresFamilia = cantMenoresFamilia;
		this.cantDiscapacitadosFamilia = cantDiscapacitadosFamilia;
		CategoriaTrabajo = categoriaTrabajo;
		InicioEnEmpresa = inicioEnEmpresa;
		InicioEnIndustria = inicioEnIndustria;
		IngresoMensualTrabajo = ingresoMensualTrabajo;
		OtrosIngresosTrabajo = otrosIngresosTrabajo;
		this.nombreEmpresaTrabajo = nombreEmpresaTrabajo;
		DireccionEmpresaTrabajo = direccionEmpresaTrabajo;
		TelefonoEmpresaTrabajo = telefonoEmpresaTrabajo;
		RUTEmpresaTrabajo = rUTEmpresaTrabajo;
		CalleDomicilio = calleDomicilio;
		NumeroDomicilio = numeroDomicilio;
		BarrioDomicilio = barrioDomicilio;
		CiudadDomicilio = ciudadDomicilio;
		DepartamentoDomicilio = departamentoDomicilio;
		TelefonoDomicilio = telefonoDomicilio;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public boolean isConectado() {
		return conectado;
	}
	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}
	public int getCedula() {
		return cedula;
	}
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public int getCedulaCon() {
		return cedulaCon;
	}
	public void setCedulaCon(int cedulaCon) {
		this.cedulaCon = cedulaCon;
	}
	public String getNombresCon() {
		return nombresCon;
	}
	public void setNombresCon(String nombresCon) {
		this.nombresCon = nombresCon;
	}
	public String getApellidosCon() {
		return apellidosCon;
	}
	public void setApellidosCon(String apellidosCon) {
		this.apellidosCon = apellidosCon;
	}
	public String getFechaNacimientoCon() {
		return FechaNacimientoCon;
	}
	public void setFechaNacimientoCon(String fechaNacimientoCon) {
		FechaNacimientoCon = fechaNacimientoCon;
	}
	public String getNacionalidadCon() {
		return NacionalidadCon;
	}
	public void setNacionalidadCon(String nacionalidadCon) {
		NacionalidadCon = nacionalidadCon;
	}
	public String getEstadoCivilCon() {
		return EstadoCivilCon;
	}
	public void setEstadoCivilCon(String estadoCivilCon) {
		EstadoCivilCon = estadoCivilCon;
	}
	public String getSexoCon() {
		return SexoCon;
	}
	public void setSexoCon(String sexoCon) {
		SexoCon = sexoCon;
	}
	public int getCantMayoresFamilia() {
		return CantMayoresFamilia;
	}
	public void setCantMayoresFamilia(int cantMayoresFamilia) {
		CantMayoresFamilia = cantMayoresFamilia;
	}
	public int getCantMenoresFamilia() {
		return cantMenoresFamilia;
	}
	public void setCantMenoresFamilia(int cantMenoresFamilia) {
		this.cantMenoresFamilia = cantMenoresFamilia;
	}
	public int getCantDiscapacitadosFamilia() {
		return cantDiscapacitadosFamilia;
	}
	public void setCantDiscapacitadosFamilia(int cantDiscapacitadosFamilia) {
		this.cantDiscapacitadosFamilia = cantDiscapacitadosFamilia;
	}
	public String getCategoriaTrabajo() {
		return CategoriaTrabajo;
	}
	public void setCategoriaTrabajo(String categoriaTrabajo) {
		CategoriaTrabajo = categoriaTrabajo;
	}
	public String getInicioEnEmpresa() {
		return InicioEnEmpresa;
	}
	public void setInicioEnEmpresa(String inicioEnEmpresa) {
		InicioEnEmpresa = inicioEnEmpresa;
	}
	public String getInicioEnIndustria() {
		return InicioEnIndustria;
	}
	public void setInicioEnIndustria(String inicioEnIndustria) {
		InicioEnIndustria = inicioEnIndustria;
	}
	public double getIngresoMensualTrabajo() {
		return IngresoMensualTrabajo;
	}
	public void setIngresoMensualTrabajo(double ingresoMensualTrabajo) {
		IngresoMensualTrabajo = ingresoMensualTrabajo;
	}
	public double getOtrosIngresosTrabajo() {
		return OtrosIngresosTrabajo;
	}
	public void setOtrosIngresosTrabajo(double otrosIngresosTrabajo) {
		OtrosIngresosTrabajo = otrosIngresosTrabajo;
	}
	public String getNombreEmpresaTrabajo() {
		return nombreEmpresaTrabajo;
	}
	public void setNombreEmpresaTrabajo(String nombreEmpresaTrabajo) {
		this.nombreEmpresaTrabajo = nombreEmpresaTrabajo;
	}
	public String getDireccionEmpresaTrabajo() {
		return DireccionEmpresaTrabajo;
	}
	public void setDireccionEmpresaTrabajo(String direccionEmpresaTrabajo) {
		DireccionEmpresaTrabajo = direccionEmpresaTrabajo;
	}
	public String getTelefonoEmpresaTrabajo() {
		return TelefonoEmpresaTrabajo;
	}
	public void setTelefonoEmpresaTrabajo(String telefonoEmpresaTrabajo) {
		TelefonoEmpresaTrabajo = telefonoEmpresaTrabajo;
	}
	public long getRUTEmpresaTrabajo() {
		return RUTEmpresaTrabajo;
	}
	public void setRUTEmpresaTrabajo(long rUTEmpresaTrabajo) {
		RUTEmpresaTrabajo = rUTEmpresaTrabajo;
	}
	public String getCalleDomicilio() {
		return CalleDomicilio;
	}
	public void setCalleDomicilio(String calleDomicilio) {
		CalleDomicilio = calleDomicilio;
	}
	public String getNumeroDomicilio() {
		return NumeroDomicilio;
	}
	public void setNumeroDomicilio(String numeroDomicilio) {
		NumeroDomicilio = numeroDomicilio;
	}
	public String getBarrioDomicilio() {
		return BarrioDomicilio;
	}
	public void setBarrioDomicilio(String barrioDomicilio) {
		BarrioDomicilio = barrioDomicilio;
	}
	public String getCiudadDomicilio() {
		return CiudadDomicilio;
	}
	public void setCiudadDomicilio(String ciudadDomicilio) {
		CiudadDomicilio = ciudadDomicilio;
	}
	public String getDepartamentoDomicilio() {
		return DepartamentoDomicilio;
	}
	public void setDepartamentoDomicilio(String departamentoDomicilio) {
		DepartamentoDomicilio = departamentoDomicilio;
	}
	public String getTelefonoDomicilio() {
		return TelefonoDomicilio;
	}
	public void setTelefonoDomicilio(String telefonoDomicilio) {
		TelefonoDomicilio = telefonoDomicilio;
	}
	public DataAportante (String json)
	{
		Gson gson = new Gson();
		DataAportante d = gson.fromJson(json, DataAportante.class);	
		this.nick = d.getNick();
		this.pass = d.getPass();
		this.conectado = d.isConectado();
		this.cedula = d.getCedula();
		this.nombre = d.getNombre();
		this.apellidos = d.getApellidos();
		this.rol = d.getRol();
		this.activo = d.isActivo();
		this.sexo = d.getSexo();
		this.email = d.getEmail();
		this.categoria = d.getCategoria();
		this.fechaNacimiento = d.getFechaNacimiento();
		this.nacionalidad = d.getNacionalidad();
		this.estadoCivil = d.getEstadoCivil();
		this.cedulaCon = d.getCedulaCon();
		this.nombresCon = d.getNombresCon();
		this.apellidosCon = d.getApellidosCon();
		FechaNacimientoCon = d.getFechaNacimientoCon();
		NacionalidadCon = d.getNacionalidadCon();
		EstadoCivilCon = d.getEstadoCivilCon();
		SexoCon = d.getSexoCon();
		CantMayoresFamilia = d.getCantMayoresFamilia();
		this.cantMenoresFamilia = d.getCantMenoresFamilia();
		this.cantDiscapacitadosFamilia = d.getCantDiscapacitadosFamilia();
		CategoriaTrabajo = d.getCategoriaTrabajo();
		InicioEnEmpresa = d.getInicioEnEmpresa();
		InicioEnIndustria = d.getInicioEnIndustria();
		IngresoMensualTrabajo = d.getIngresoMensualTrabajo();
		OtrosIngresosTrabajo = d.getOtrosIngresosTrabajo();
		this.nombreEmpresaTrabajo = d.getNombreEmpresaTrabajo();
		DireccionEmpresaTrabajo = d.getDireccionEmpresaTrabajo();
		TelefonoEmpresaTrabajo = d.getTelefonoEmpresaTrabajo();
		RUTEmpresaTrabajo = d.getRUTEmpresaTrabajo();
		CalleDomicilio = d.getCalleDomicilio();
		NumeroDomicilio = d.getNumeroDomicilio();
		BarrioDomicilio = d.getBarrioDomicilio();
		CiudadDomicilio = d.getCiudadDomicilio();
		DepartamentoDomicilio = d.getDepartamentoDomicilio();
		TelefonoDomicilio = d.getTelefonoDomicilio();

	}
	

}
