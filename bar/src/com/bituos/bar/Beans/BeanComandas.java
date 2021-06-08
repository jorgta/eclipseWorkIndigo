/*
 * Created on Feb 24, 2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.bituos.bar.Beans;

import java.util.Date;



/**
 * @author dsa
*/

public class BeanComandas{

	private int id_comanda;
	private BeanAgente id_agente;
	private BeanMesa id_mesa;
	private Date fecha;
	private char cerrada;
	private double total;
	private String notas;
	
	
	private Integer hora_cierre;
	private Integer minuto_cierre;
	private Date fecha_cierre;
	private Integer hora_apertura;
	private Integer minuto_apertura;
	private String agente_nombre;
	public int getId_comanda() {
		return id_comanda;
	}
	public void setId_comanda(int id_comanda) {
		this.id_comanda = id_comanda;
	}
	public BeanAgente getId_agente() {
		return id_agente;
	}
	public void setId_agente(BeanAgente id_agente) {
		this.id_agente = id_agente;
	}
	public BeanMesa getId_mesa() {
		return id_mesa;
	}
	public void setId_mesa(BeanMesa id_mesa) {
		this.id_mesa = id_mesa;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public char getCerrada() {
		return cerrada;
	}
	public void setCerrada(char cerrada) {
		this.cerrada = cerrada;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getNotas() {
		return notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	public Integer getHora_cierre() {
		return hora_cierre;
	}
	public void setHora_cierre(Integer hora_cierre) {
		this.hora_cierre = hora_cierre;
	}
	public Integer getMinuto_cierre() {
		return minuto_cierre;
	}
	public void setMinuto_cierre(Integer minuto_cierre) {
		this.minuto_cierre = minuto_cierre;
	}
	public Date getFecha_cierre() {
		return fecha_cierre;
	}
	public void setFecha_cierre(Date fecha_cierre) {
		this.fecha_cierre = fecha_cierre;
	}
	public Integer getHora_apertura() {
		return hora_apertura;
	}
	public void setHora_apertura(Integer hora_apertura) {
		this.hora_apertura = hora_apertura;
	}
	public Integer getMinuto_apertura() {
		return minuto_apertura;
	}
	public void setMinuto_apertura(Integer minuto_apertura) {
		this.minuto_apertura = minuto_apertura;
	}
	public String getAgente_nombre() {
		return agente_nombre;
	}
	public void setAgente_nombre(String agente_nombre) {
		this.agente_nombre = agente_nombre;
	}
	
	

	

	
}
