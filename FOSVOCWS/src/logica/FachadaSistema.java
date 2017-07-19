package logica;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import datatypes.DataAdjudicacion;
import datatypes.DataAportante;
import datatypes.DataContrasenia;
import datatypes.DataListarAdjudicaciones;
import datatypes.DataListarAportantes;
import datatypes.DataListarSolicitudes;
import datatypes.DataLoginIn;
import datatypes.DataMensaje;
import datatypes.DataSolicitud;
import datatypes.DataUsuario;
import datatypes.DataUsuarioLogin;
import datatypes.response.DataAdjudicacionResponse;
import datatypes.response.DataAportanteResponse;
import datatypes.response.DataListarAdjudicacionesResponse;
import datatypes.response.DataListarAportantesResponse;
import datatypes.response.DataListarSolicitudesResponse;
import datatypes.response.DataSolicitudResponse;
import datatypes.response.DataUsuarioLoginResponse;
import datatypes.response.DataUsuarioResponse;
import excepciones.PersistenciaException;
import logica.aportantes.Aportante;
import logica.aportantes.Aportantes;
import logica.aportantes.Conyuge;
import logica.aportantes.Conyuges;
import logica.aportantes.Familia;
import logica.aportantes.Familias;
import logica.aportantes.Trabajo;
import logica.aportantes.Trabajos;
import logica.empresas.DataEmpresa;
import logica.empresas.Empresa;
import logica.empresas.Empresas;
import logica.solicitudes.Solicitudes;
import logica.usuarios.Accion;
import logica.usuarios.Acciones;
import logica.usuarios.Rol;
import logica.usuarios.Roles;
import logica.usuarios.Usuario;
import logica.usuarios.Usuarios;
import logica.viviendas.Domicilio;
import logica.viviendas.Domicilios;
import persistencia.PoolTransacciones;
import persistencia.Transaccion;
import prestamos.Adjudicaciones;
import utilitarios.Formato;

public class FachadaSistema {
	PoolTransacciones pool;
	private Usuarios usuarios;
	private Acciones acciones;
	private Aportantes aportantes;
	private Solicitudes solicitudes;
	private Adjudicaciones adjudicaciones;
	private static FachadaSistema instancia = null;

	private FachadaSistema() {
		LectorParametros lector;
		try {
			lector = new LectorParametros("datos.txt");

			String url = lector.getUrlBase();
			String user = lector.getUsuarioBase();
			String password = lector.getContraseniaBase();
			String driver = lector.getDriverBase();
			pool = PoolTransacciones.getInstance(url, user, password, driver, 30);
			usuarios = new Usuarios();
			acciones = new Acciones();
			aportantes = new Aportantes();
			solicitudes = new Solicitudes();
			adjudicaciones = new Adjudicaciones();
		} catch (PersistenciaException e) {
			e.printStackTrace();
		}
	}

	public static FachadaSistema getInstancia() {
		if (instancia == null)
			instancia = new FachadaSistema();
		return instancia;
	}

	public DataUsuarioResponse buscarUsuario(int id) throws PersistenciaException {
		Transaccion t = null;
		DataUsuarioResponse resu = null;
		DataUsuario data = null;
		String msj = null;
		boolean ok;
		DataMensaje dm = null;
		try {
			t = pool.obtenerTransaccion(Connection.TRANSACTION_SERIALIZABLE);
			Usuario usu = usuarios.buscarUsuario(id, t);
			if (usu == null) {
				t.finalizarTransaccion(false);
				msj = "No se encuentra un usuario con ese documento";
				ok = false;
				dm = new DataMensaje(msj, ok);
				t.finalizarTransaccion(false);
				pool.liberarTransaccion(t);
			} else {
				data = new DataUsuario(usu.getNombre(), usu.getApellidos(), usu.getCedula(), usu.getNick(), new String(usu.getPass()), usu.getRol().getTipo(), usu.isConectado(),
						usu.isActivo(), usu.getEmail());
				msj = "OK";
				ok = true;
				dm = new DataMensaje(msj, ok);
				t.finalizarTransaccion(true);
				pool.liberarTransaccion(t);
			}
		} catch (PersistenciaException ex) {
			msj = "Error al acceder a los datos. Intentelo nuevamente. \n Si persiste, consulte la ayuda";
			ok = false;
			dm = new DataMensaje(msj, ok);
			t.finalizarTransaccion(false);
			pool.liberarTransaccion(t);
		}
		resu = new DataUsuarioResponse(data, dm);
		return resu;
	}

	public DataUsuarioResponse buscarPorNickUsuario(String nick) throws PersistenciaException {
		Transaccion t = null;
		DataUsuarioResponse resu = null;
		DataUsuario data = null;
		String msj = null;
		boolean ok;
		DataMensaje dm = null;
		try {
			t = pool.obtenerTransaccion(Connection.TRANSACTION_SERIALIZABLE);
			Usuario usu = usuarios.buscarUsuario(nick, t);
			if (usu == null) {
				t.finalizarTransaccion(false);
				msj = "No se encuentra un usuario con ese documento";
				ok = false;
				dm = new DataMensaje(msj, ok);
				pool.liberarTransaccion(t);
			} else {
				data = new DataUsuario(usu.getNombre(), usu.getApellidos(), usu.getCedula(), usu.getNick(), new String(usu.getPass()), usu.getRol().getTipo(), usu.isConectado(),
						usu.isActivo(), usu.getEmail());
				msj = "OK";
				ok = true;
				dm = new DataMensaje(msj, ok);
				t.finalizarTransaccion(true);
				pool.liberarTransaccion(t);
			}
		} catch (PersistenciaException ex) {
			msj = "Error al acceder a los datos. Intentelo nuevamente. \n Si persiste, consulte la ayuda";
			ok = false;
			dm = new DataMensaje(msj, ok);
			t.finalizarTransaccion(false);
			pool.liberarTransaccion(t);
		}
		resu = new DataUsuarioResponse(data, dm);
		return resu;
	}

	public DataUsuarioLoginResponse login(DataLoginIn data) throws PersistenciaException {
		Usuario usuario = null;
		DataUsuarioLogin datausuario = null;
		DataUsuarioLoginResponse resu = null;
		DataMensaje dm = null;
		String msj;
		boolean ok;
		Transaccion t = null;

		try {
			t = pool.obtenerTransaccion(java.sql.Connection.TRANSACTION_SERIALIZABLE);			
			usuario = usuarios.buscarUsuario(data.getUsuario(), t);
			if (usuario != null) {
				char[] contra = usuario.getPass();
				String contraS = new String(contra);
				String passS = data.getPass();
				if (contraS.equals(passS)) {
					if (usuario.isActivo()) {
						datausuario = new DataUsuarioLogin(usuario.getNombre(), usuario.getApellidos(), usuario.getNick(), passS, usuario.getRol().getTipo(),
								usuario.getEmail(), usuario.getCedula());
						usuario.setConectado(true);
						usuarios.login(t, usuario);
						// ** ACCION
						acciones.setCedulaUsuario(usuario.getCedula());
						Accion ac = this.generarAccion("Login", usuario.getNick(), usuario.getCedula());
						acciones.agregarAccion(ac, t);
						// response
						msj = "OK.";
						ok = true;
						dm = new DataMensaje(msj, ok);
						t.finalizarTransaccion(true);
						pool.liberarTransaccion(t);
					} else {
						t.finalizarTransaccion(false);
						pool.liberarTransaccion(t);
						msj = "El usuario ya está conectado o su estado es inactivo.";
						ok = false;
						dm = new DataMensaje(msj, ok);
					}

				} else {
					t.finalizarTransaccion(false);
					pool.liberarTransaccion(t);
					msj = "No coincide la contraseña ingresada con la registrada en el sistema.";
					ok = false;
					dm = new DataMensaje(msj, ok);
				}
			}
			else {
				t.finalizarTransaccion(false);
				pool.liberarTransaccion(t);
				msj = "No se encuentra un usuario con esos datos en el sistema.";
				ok = false;
				dm = new DataMensaje(msj, ok);
			}
		} catch (PersistenciaException e) {
			msj = "Error al acceder a los datos. Intentelo nuevamente. \n Si persiste, consulte la ayuda";
			ok = false;
			dm = new DataMensaje(msj, ok);
			t.finalizarTransaccion(false);
			pool.liberarTransaccion(t);
		}
		resu = new DataUsuarioLoginResponse(datausuario, dm);
		return resu;
	}

	public DataUsuarioLoginResponse loginTrabajador(DataLoginIn data) throws PersistenciaException {
		Usuario usuario = null;
		DataUsuarioLogin datausuario = null;
		DataUsuarioLoginResponse resu = null;
		DataMensaje dm = null;
		String msj;
		boolean ok;
		Transaccion t = null;

		try {
			t = pool.obtenerTransaccion(java.sql.Connection.TRANSACTION_SERIALIZABLE);
			usuario = usuarios.buscarUsuario(data.getUsuario(), t);
			if (usuario != null) {
				char[] contra = usuario.getPass();
				String contraS = new String(contra);
				String passS = data.getPass();
				if (contraS.equals(passS)) {
					//String tipo = usuario.getRol().getTipo();
				
						if (usuario.isActivo()) {
							datausuario = new DataUsuarioLogin(usuario.getNombre(), usuario.getApellidos(), usuario.getNick(), passS, usuario.getRol().getTipo(),
									usuario.getEmail(), usuario.getCedula());
							usuario.setConectado(true);
							usuarios.login(t, usuario);
							// ** ACCION
							acciones.setCedulaUsuario(usuario.getCedula());
							Accion ac = this.generarAccion("Login", usuario.getNick(), usuario.getCedula());
							acciones.agregarAccion(ac, t);
							// response
							msj = "OK.";
							ok = true;
							dm = new DataMensaje(msj, ok);
							t.finalizarTransaccion(true);
							pool.liberarTransaccion(t);
						} else {
							t.finalizarTransaccion(false);
							pool.liberarTransaccion(t);
							msj = "El usuario ya está conectado o su estado es inactivo.";
							ok = false;
							dm = new DataMensaje(msj, ok);
						}	

				} else {
					t.finalizarTransaccion(false);
					pool.liberarTransaccion(t);
					msj = "No coincide la contraseña ingresada con la registrada en el sistema.";
					ok = false;
					dm = new DataMensaje(msj, ok);
				}
			} else {
				t.finalizarTransaccion(false);
				pool.liberarTransaccion(t);
				msj = "No se encuentra un usuario con esos datos en el sistema.";
				ok = false;
				dm = new DataMensaje(msj, ok);
			}
		} catch (PersistenciaException e) {
			msj = "Error al acceder a los datos. Intentelo nuevamente. \n Si persiste, consulte la ayuda";
			ok = false;
			dm = new DataMensaje(msj, ok);
			t.finalizarTransaccion(false);
			pool.liberarTransaccion(t);
		}
		resu = new DataUsuarioLoginResponse(datausuario, dm);
		return resu;
	}

	public DataUsuarioResponse modificarUsuario(DataUsuario usuario) throws PersistenciaException {
		DataUsuarioResponse resu = null;
		DataUsuario du = null;
		DataMensaje dm = null;
		String msj;
		boolean ok;
		Transaccion t = null;
		try {
			t = pool.obtenerTransaccion(Connection.TRANSACTION_SERIALIZABLE);
			Usuario usu = usuarios.buscarUsuario(usuario.getCedula(), t);
			if (usu == null) {
				msj = "No existe un usuario con ese nombre. ";
				ok = false;
				dm = new DataMensaje(msj, ok);
				t.finalizarTransaccion(false);
			} else {
				du = usuarios.modificar(usuario, t);
				// ** ACCION
				// supongo para esta funcionalidad que el propio usuario es quien modifica sus datos
				acciones.setCedulaUsuario(usuario.getCedula());
				Accion ac = this.generarAccion("Modificar Usuario",
						usuario.getCedula() + "-" + usuario.getUsuario() + "-" + usuario.getNombres() + "-" + usuario.getApellidos() + "-" + usuario.getNombreRol(),
						usuario.getCedula());
				acciones.agregarAccion(ac, t);
				msj = "OK";
				ok = true;
				dm = new DataMensaje(msj, ok);
				t.finalizarTransaccion(true);
			}

		} catch (PersistenciaException ex) {
			msj = "Error al acceder a los datos. Intentelo nuevamente. \n Si persiste, consulte la ayuda";
			ok = false;
			dm = new DataMensaje(msj, ok);
			t.finalizarTransaccion(false);
		} finally {
			pool.liberarTransaccion(t);
		}
		resu = new DataUsuarioResponse(du, dm);
		return resu;
	}

	public DataMensaje modificarContrasenia(DataContrasenia data) throws PersistenciaException {
		DataMensaje resu = null;
		String msj;
		boolean ok;
		Transaccion t = null;
		try {
			t = pool.obtenerTransaccion(Connection.TRANSACTION_SERIALIZABLE);
			Usuario usu = usuarios.buscarUsuario(data.getUsuario(), t);
			if (usu == null) {
				msj = "Error en el nombre de usuario. \n No se encuentra el dato en el sistema. \n Verifique o consulte la ayuda.";
				ok = false;
				resu = new DataMensaje(msj, ok);
				t.finalizarTransaccion(false);
			} else if (!usuarios.coincideContrasenia(t, data.getUsuario(), data.getContraseniaAnterior())) {
				t.finalizarTransaccion(false);
				msj = " No coinciden los datos de la contraseña ingresada \n con la que está registrada en el sistema.";
				ok = false;
				resu = new DataMensaje(msj, ok);
			} else {
				usuarios.modificarContrasenia(data, usu, t);
				msj = "OK";
				ok = true;
				resu = new DataMensaje(msj, ok);
				// ** ACCION
				acciones.setCedulaUsuario(usu.getCedula());
				Accion ac = this.generarAccion("Cambio de contraseña", data.getUsuario() + "-" + String.valueOf(data.getContraseniaNueva()), 0);
				acciones.agregarAccion(ac, t);
				t.finalizarTransaccion(true);
			}
		} catch (PersistenciaException ex) {
			t.finalizarTransaccion(false);
			msj = "Error al acceder a los datos. Intentelo nuevamente. \n Si persiste, consulte la ayuda";
			ok = false;
			resu = new DataMensaje(msj, ok);
		} finally {
			pool.liberarTransaccion(t);
		}
		return resu;
	}

	public DataListarAportantesResponse listarAportantes() throws PersistenciaException {
		DataListarAportantesResponse resu = null;
		Vector<DataListarAportantes> vec = null;
		DataMensaje dm = null;
		String msj;
		boolean ok;
		Transaccion t = pool.obtenerTransaccion(Connection.TRANSACTION_SERIALIZABLE);
		try {
			vec = aportantes.listarAportantes(t);
			msj = "OK";
			ok = true;
			dm = new DataMensaje(msj, ok);
			t.finalizarTransaccion(true);
		} catch (PersistenciaException e) {
			msj = "Error al acceder a los datos. Intentelo nuevamente. \n Si persiste, consulte la ayuda";
			ok = false;
			dm = new DataMensaje(msj, ok);
			t.finalizarTransaccion(false);
		} finally {
			pool.liberarTransaccion(t);
		}
		resu = new DataListarAportantesResponse(vec, dm);
		return resu;
	}

	public DataAportanteResponse buscarAportante(int documento) throws PersistenciaException {
		DataAportanteResponse resu = null;
		DataAportante data = null;
		DataMensaje dm = null;
		String msj = null;
		boolean ok;

		Aportante ap = null;
		Transaccion t = pool.obtenerTransaccion(Connection.TRANSACTION_SERIALIZABLE);
		try {
			ap = aportantes.buscarAportante(t, documento);
			if (ap != null) {
				data = new DataAportante(ap.getNick(), new String(ap.getPass()), ap.isConectado(), ap.getCedula(), ap.getNombre(), ap.getApellidos(),
						ap.getRol().getTipo(), ap.isActivo(), ap.getSexo(), ap.getEmail(), ap.getCategoria(), Formato.convertirCalendarCorto(ap.getFechaNacimiento()), ap.getNacionalidad(),
						ap.getEstadoCivil(), ap.getConyuge().getCedula(), ap.getConyuge().getNombres(), ap.getConyuge().getApellidos(), Formato.convertirCalendarCorto(ap.getConyuge().getFechaNacimiento()), 
						ap.getConyuge().getNacionalidad(), ap.getConyuge().getEstadoCivil(), ap.getConyuge().getSexo(), ap.getFamilia().getCantMayores(), ap.getFamilia().getCantMenores(), 
						ap.getFamilia().getCantDiscapacitados(), ap.getTrabajo().getCategoria(), Formato.convertirCalendarCorto(ap.getTrabajo().getInicioEnEmpresa()), Formato.convertirCalendarCorto(ap.getTrabajo().getInicioEnIndustria()), 
						ap.getTrabajo().getIngresoMensual(), ap.getTrabajo().getOtrosIngresos(), ap.getTrabajo().getEmpresa().getNombre(), ap.getTrabajo().getEmpresa().getDireccion(), 
						ap.getTrabajo().getEmpresa().getTelefono(), ap.getTrabajo().getEmpresa().getRUT(), ap.getDomicilio().getCalle(), ap.getDomicilio().getNumero(), ap.getDomicilio().getBarrio(),
						ap.getDomicilio().getBarrio(), ap.getDomicilio().getDepartamento(), ap.getDomicilio().getTelefono());
				msj = "OK";
				ok = true;
				dm = new DataMensaje(msj, ok);
				t.finalizarTransaccion(true);
			} else {
				msj = "No se encuentra el aportante con el documento ingresado";
				ok = false;
				dm = new DataMensaje(msj, ok);
				t.finalizarTransaccion(false);
			}

		} catch (PersistenciaException e) {
			msj = "Error al acceder a los datos. Intentelo nuevamente. \n Si persiste, consulte la ayuda";
			ok = false;
			dm = new DataMensaje(msj, ok);
			t.finalizarTransaccion(false);
		} finally {
			pool.liberarTransaccion(t);
		}
		resu = new DataAportanteResponse(data, dm);
		return resu;

	}

	public DataMensaje modificarAportante(DataAportante apo) throws PersistenciaException {
		DataMensaje resu = null;
		Transaccion t = null;
		Conyuge con = null;
		Familia fam = null;
		Trabajo trab = null;
		Empresa emp = null;
		String msj = null;
		boolean ok;
		try {
			t = this.pool.obtenerTransaccion(Connection.TRANSACTION_SERIALIZABLE);
			Aportante aux = this.aportantes.buscarAportante(t, Integer.valueOf(apo.getCedula()));

			if (aux == null) {
				msj = "No se encuentra un aportante con ese número de documento.";
				ok = false;
				t.finalizarTransaccion(false);
			} else {
				String cedu = apo.getCedula() + "";
				char[] cont = cedu.toCharArray();
				Roles roles = new Roles();
				Rol rol = roles.buscar("Aportante", t);

				Usuario u = new Usuario(cedu, cont, apo.getCedula(), apo.getNombre(), apo.getApellidos(), rol, apo.getEmail());
				Usuario auxU = this.usuarios.buscarUsuario(cedu, t);
				if (auxU == null) {
					msj = "No existe un usuario con el numero de cedula igual al ingresado.";
					ok = false;
					t.finalizarTransaccion(false);
				} else {
					this.usuarios.modificarUsuario(u, t);

					Conyuges conyuges = new Conyuges();
					Conyuge auxCon = conyuges.buscarConyuge(t, apo.getCedula());
					if (apo.getNombresCon() != null) {
						con = new Conyuge(apo.getCedulaCon(), apo.getNombresCon(), apo.getApellidosCon(), Formato.tomarCalendarDeString(apo.getFechaNacimientoCon()),
								apo.getNacionalidadCon(), apo.getEstadoCivilCon(), cedu);
						if (auxCon == null) {
							conyuges.insertarConyuge(t, con, apo.getCedula());
						} else {
							conyuges.modificarConyuge(t, con, apo.getCedula());
						}
					}
					Familias familias = new Familias();
					fam = new Familia(apo.getCantMayoresFamilia(), apo.getCantMenoresFamilia(), apo.getCantDiscapacitadosFamilia());
					familias.modificarFamilia(t, fam, apo.getCedula());

					Empresas empresas = new Empresas();
					DataEmpresa auxEmp = empresas.buscarDataEmpresa(t, apo.getNombreEmpresaTrabajo());
					emp = new Empresa(apo.getNombreEmpresaTrabajo(), apo.getDireccionEmpresaTrabajo(), apo.getTelefonoEmpresaTrabajo(),
							apo.getRUTEmpresaTrabajo());
					if (auxEmp == null) {
						empresas.insertarEmpresa(t, emp);
					} else {
						empresas.modificarEmpresa(t, emp);
					}

					Trabajos trabajos = new Trabajos();
					Calendar inicioEnEmpresa = Formato.tomarCalendarDeString(apo.getInicioEnEmpresa());
					Calendar inicioEnIndustria = Formato.tomarCalendarDeString(apo.getInicioEnIndustria());

					trab = new Trabajo(apo.getCedula(), apo.getCategoriaTrabajo(), inicioEnEmpresa, inicioEnIndustria, apo.getIngresoMensualTrabajo(),
							apo.getOtrosIngresosTrabajo(), emp);
					Trabajo trabAux = trabajos.buscarTrabajo(t, Integer.valueOf(apo.getCedula()));

					if (trabAux == null) {
						trabajos.insertarTrabajo(t, trab);
					} else {
						trabajos.modificarTrabajo(t, trab);
					}

					Domicilios domicilios = new Domicilios();
					Domicilio dom = new Domicilio(apo.getCalleDomicilio(), apo.getNumeroDomicilio(), apo.getBarrioDomicilio(), apo.getCiudadDomicilio(),
							apo.getDepartamentoDomicilio(), apo.getTelefonoDomicilio());
					domicilios.modificarDomicilios(t, dom, apo.getCedula());

					msj = "OK";
					ok = true;
					t.finalizarTransaccion(true);
				}
			}
		} catch (PersistenciaException e) {
			t.finalizarTransaccion(false);
			msj = "Error al acceder a los datos. Intentelo nuevamente. \n Si persiste, consulte la ayuda";
			ok = false;
		} finally {
			this.pool.liberarTransaccion(t);
		}
		resu = new DataMensaje(msj, ok);
		return resu;
	}

	public DataSolicitudResponse buscarSolicitud(int id) throws PersistenciaException{
		DataSolicitudResponse resu = null;
		DataSolicitud data = null;
		DataMensaje dm = null;
		String msj = null;
		boolean ok;
		Transaccion t = pool.obtenerTransaccion(Connection.TRANSACTION_SERIALIZABLE);
		try {
			data = solicitudes.buscarSolicitudRefaccionVersion2016(t, id);
			if (data != null) {
				msj = "OK";
				ok = true;
				dm = new DataMensaje(msj, ok);
				t.finalizarTransaccion(true);
			} else {
				msj = "No se encuentra el aportante con el documento ingresado";
				ok = false;
				dm = new DataMensaje(msj, ok);
				t.finalizarTransaccion(false);
			}

		} catch (PersistenciaException e) {
			msj = "Error al acceder a los datos. Intentelo nuevamente. \n Si persiste, consulte la ayuda";
			ok = false;
			dm = new DataMensaje(msj, ok);
			t.finalizarTransaccion(false);
		} finally {
			pool.liberarTransaccion(t);
		}
		resu = new DataSolicitudResponse(data, dm);
		return resu;
	}

	public DataListarSolicitudesResponse listarSolicitudes() throws PersistenciaException{
		DataListarSolicitudesResponse resu = null;
		Vector<DataListarSolicitudes> vec = null;
		DataMensaje dm = null;
		String msj;
		boolean ok;
		Transaccion t = pool.obtenerTransaccion(Connection.TRANSACTION_SERIALIZABLE);
		try {
			vec = solicitudes.listarDataListadoSolicitudes(t);
			msj = "OK";
			ok = true;
			dm = new DataMensaje(msj, ok);
			t.finalizarTransaccion(true);
		} catch (PersistenciaException e) {
			msj = "Error al acceder a los datos. Intentelo nuevamente. \n Si persiste, consulte la ayuda";
			ok = false;
			dm = new DataMensaje(msj, ok);
			t.finalizarTransaccion(false);
		} finally {
			pool.liberarTransaccion(t);
		}
		resu = new DataListarSolicitudesResponse(vec, dm);
		return resu;
	}
	public DataListarSolicitudesResponse listarSolicitudesDeAportante(int cedula) throws PersistenciaException{
		DataListarSolicitudesResponse resu = null;
		Vector<DataListarSolicitudes> vec = null;
		DataMensaje dm = null;
		String msj;
		boolean ok;
		Transaccion t = pool.obtenerTransaccion(Connection.TRANSACTION_SERIALIZABLE);
		try {
			vec = solicitudes.listarDataListadoSolicitudesDeAportante(t, cedula);
			if(!vec.isEmpty())
			{
				msj = "OK";
			    ok = true;
			    dm = new DataMensaje(msj, ok);
			}else{
				msj = "No hay solicitudes de ese aportante";
			    ok = false;
			    dm = new DataMensaje(msj, ok);
			}
			
			t.finalizarTransaccion(true);
		} catch (PersistenciaException e) {
			msj = "Error al acceder a los datos. Intentelo nuevamente. \n Si persiste, consulte la ayuda";
			ok = false;
			dm = new DataMensaje(msj, ok);
			t.finalizarTransaccion(false);
		} finally {
			pool.liberarTransaccion(t);
		}
		resu = new DataListarSolicitudesResponse(vec, dm);
		return resu;
	}

	public DataAdjudicacionResponse buscarAdjudicacion(int id) throws PersistenciaException {
		DataAdjudicacionResponse resu = null;
		DataAdjudicacion data = null;
		DataMensaje dm = null;
		String msj = null;
		boolean ok;
		Transaccion t = pool.obtenerTransaccion(Connection.TRANSACTION_SERIALIZABLE);
		try {
			data = adjudicaciones.buscarAdjudicacion(t, id);
			if (data != null) {
				msj = "OK";
				ok = true;
				dm = new DataMensaje(msj, ok);
				t.finalizarTransaccion(true);
			} else {
				msj = "No se encuentra el aportante con el documento ingresado";
				ok = false;
				dm = new DataMensaje(msj, ok);
				t.finalizarTransaccion(false);
			}
		} catch (PersistenciaException e) {
			msj = "Error al acceder a los datos. Intentelo nuevamente. \n Si persiste, consulte la ayuda";
			ok = false;
			dm = new DataMensaje(msj, ok);
			t.finalizarTransaccion(false);
		} finally {
			pool.liberarTransaccion(t);
		}
		resu = new DataAdjudicacionResponse(data, dm);
		return resu;
	}

	public DataListarAdjudicacionesResponse listarAdjudicaciones() throws PersistenciaException {
		DataListarAdjudicacionesResponse resu = null;
		Vector<DataListarAdjudicaciones> vec = null;
		DataMensaje dm = null;
		String msj;
		boolean ok;
		Transaccion t = pool.obtenerTransaccion(Connection.TRANSACTION_SERIALIZABLE);
		try {
			vec = adjudicaciones.listarAdjudicaciones(t);
			msj = "OK";
			ok = true;
			dm = new DataMensaje(msj, ok);
			t.finalizarTransaccion(true);
		} catch (PersistenciaException e) {
			msj = "Error al acceder a los datos. Intentelo nuevamente. \n Si persiste, consulte la ayuda";
			ok = false;
			dm = new DataMensaje(msj, ok);
			t.finalizarTransaccion(false);
		} finally {
			pool.liberarTransaccion(t);
		}
		resu = new DataListarAdjudicacionesResponse(vec, dm);
		return resu;
	}

	public DataListarAdjudicacionesResponse listarAdjudicacionesDeAportante(int cedula) throws PersistenciaException {
		DataListarAdjudicacionesResponse resu = null;
		Vector<DataListarAdjudicaciones> vec = null;
		DataMensaje dm = null;
		String msj;
		boolean ok;
		Transaccion t = pool.obtenerTransaccion(Connection.TRANSACTION_SERIALIZABLE);
		try {
			vec = adjudicaciones.listarAdjudicacionesDeAportante(t, cedula);
			if(!vec.isEmpty())
			{
				msj = "OK";
				ok = true;
				dm = new DataMensaje(msj, ok);
			}else
			{
				msj = "No hay adjudicaciones de ese aportante";
				ok = false;
				dm = new DataMensaje(msj, ok);
			}
			
			t.finalizarTransaccion(true);
		} catch (PersistenciaException e) {
			msj = "Error al acceder a los datos. Intentelo nuevamente. \n Si persiste, consulte la ayuda";
			ok = false;
			dm = new DataMensaje(msj, ok);
			t.finalizarTransaccion(false);
		} finally {
			pool.liberarTransaccion(t);
		}
		resu = new DataListarAdjudicacionesResponse(vec, dm);
		return resu;
	}


	// METODOS PRIVADOS
	public Accion generarAccion(String nombreMetodo, String descripcion, int clave) {
		Accion accion = null;
		Calendar estemomento = Calendar.getInstance();
		estemomento.setTime(new Date());
		accion = new Accion(estemomento, nombreMetodo, descripcion, clave);
		return accion;
	}


}
