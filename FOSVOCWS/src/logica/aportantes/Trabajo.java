package logica.aportantes;

import java.io.Serializable;

import java.util.Calendar;

import logica.empresas.Empresa;

public class Trabajo implements Serializable {
	public int CedulaAportante;
	public String Categoria;
	public Calendar InicioEnEmpresa;
	public Calendar InicioEnIndustria;
	public double IngresoMensual;
	public double OtrosIngresos;
	private Empresa Empresa;

	public Trabajo(int cedulaAportante, String categoria, Calendar inicioEnEmpresa, Calendar inicioEnIndustria, double ingresoMensual, double otrosIngresos, Empresa empresa) {
		/* 43 */this.CedulaAportante = cedulaAportante;
		/* 44 */this.Categoria = categoria;
		/* 45 */this.InicioEnEmpresa = inicioEnEmpresa;
		/* 46 */this.InicioEnIndustria = inicioEnIndustria;
		/* 47 */this.IngresoMensual = ingresoMensual;
		/* 48 */this.OtrosIngresos = otrosIngresos;
		/* 49 */this.Empresa = empresa;
	}

	public String getCategoria() {
		/* 56 */return this.Categoria;
	}

	public void setCategoria(String categoria) {
		/* 64 */this.Categoria = categoria;
	}

	public Calendar getInicioEnEmpresa() {
		/* 71 */return this.InicioEnEmpresa;
	}

	public void setInicioEnEmpresa(Calendar inicioEnEmpresa) {
		/* 79 */this.InicioEnEmpresa = inicioEnEmpresa;
	}

	public Calendar getInicioEnIndustria() {
		/* 86 */return this.InicioEnIndustria;
	}

	public void setInicioEnIndustria(Calendar inicioEnIndustria) {
		/* 94 */this.InicioEnIndustria = inicioEnIndustria;
	}

	public double getIngresoMensual() {
		return this.IngresoMensual;
	}

	public void setIngresoMensual(double ingresoMensual) {
		this.IngresoMensual = ingresoMensual;
	}

	public double getOtrosIngresos() {
		return this.OtrosIngresos;
	}

	public void setOtrosIngresos(double otrosIngresos) {
		this.OtrosIngresos = otrosIngresos;
	}

	public Empresa getEmpresa() {
		return this.Empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.Empresa = empresa;
	}

	public int getCedulaAportante() {
		return this.CedulaAportante;
	}

}

/*
 * Location: C:\Users\Sole\Desktop\servidor\ Qualified Name: logica.aportantes.Trabajo JD-Core Version: 0.6.0
 */