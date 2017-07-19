package prestamos;

import java.io.Serializable;
import java.util.Vector;

//
@SuppressWarnings("serial")
public class Credito implements Serializable {

	//
	private int id;

	//
	private double montoUI;

	private Vector<Cuota> cuotas;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the montoUI
	 */
	public double getMontoUI() {
		return montoUI;
	}

	/**
	 * @param montoUI
	 *            the montoUI to set
	 */
	public void setMontoUI(double montoUI) {
		this.montoUI = montoUI;
	}

	/**
	 * @return the cantCuotas70PorCiento
	 */
	public int getCantCuotas70PorCiento() {
		return cantCuotas70PorCiento;
	}

	/**
	 * @param cantCuotas70PorCiento
	 *            the cantCuotas70PorCiento to set
	 */
	public void setCantCuotas70PorCiento(int cantCuotas70PorCiento) {
		this.cantCuotas70PorCiento = cantCuotas70PorCiento;
	}

	/**
	 * @return the valorCuotas70PorCiento
	 */
	public double getValorCuotas70PorCiento() {
		return valorCuotas70PorCiento;
	}

	/**
	 * @param valorCuotas70PorCiento
	 *            the valorCuotas70PorCiento to set
	 */
	public void setValorCuotas70PorCiento(double valorCuotas70PorCiento) {
		this.valorCuotas70PorCiento = valorCuotas70PorCiento;
	}

	/**
	 * @return the cantCuotasSaldo
	 */
	public int getCantCuotasSaldo() {
		return cantCuotasSaldo;
	}

	/**
	 * @param cantCuotasSaldo
	 *            the cantCuotasSaldo to set
	 */
	public void setCantCuotasSaldo(int cantCuotasSaldo) {
		this.cantCuotasSaldo = cantCuotasSaldo;
	}

	/**
	 * @return the valorCuotasSaldo
	 */
	public double getValorCuotasSaldo() {
		return valorCuotasSaldo;
	}

	/**
	 * @param valorCuotasSaldo
	 *            the valorCuotasSaldo to set
	 */
	public void setValorCuotasSaldo(double valorCuotasSaldo) {
		this.valorCuotasSaldo = valorCuotasSaldo;
	}

	/**
	 * @return the cuotas
	 */
	public Vector<Cuota> getCuotas() {
		return this.cuotas;
	}

	/**
	 * @param cuotas
	 *            the cuotas to set
	 */
	public void setCuotas(Vector<Cuota> cuotas) {
		this.cuotas = cuotas;
	}

	//
	private int cantCuotas70PorCiento;

	//
	private double valorCuotas70PorCiento;

	//
	private int cantCuotasSaldo;

	//
	private double valorCuotasSaldo;

	//

	/**
	 * @param id
	 * @param montoUI
	 * @param cantCuotas70PorCiento
	 * @param valorCuotas70PorCiento
	 * @param cantCuotasSaldo
	 * @param valorCuotasSaldo
	 * @param cuotas
	 */
	public Credito(int id, double montoUI, int cantCuotas70PorCiento, double valorCuotas70PorCiento, int cantCuotasSaldo, double valorCuotasSaldo, Vector<Cuota> cuotas) {
		this.id = id;
		this.montoUI = montoUI;
		this.cantCuotas70PorCiento = cantCuotas70PorCiento;
		this.valorCuotas70PorCiento = valorCuotas70PorCiento;
		this.cantCuotasSaldo = cantCuotasSaldo;
		this.valorCuotasSaldo = valorCuotasSaldo;
		this.cuotas = cuotas;
	}

}