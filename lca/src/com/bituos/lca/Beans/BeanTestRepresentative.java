/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.bituos.lca.Beans;

/**
 * @author cirm
*/

import java.util.Date;

public class BeanTestRepresentative{

	private int id;
	private BeanTestOrder id_order;
	private BeanRepresentative id_representative;
	private int paid;
	private Date date_paid;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public BeanRepresentative getId_representative() {
		return id_representative;
	}
	public void setId_representative(BeanRepresentative id_representative) {
		this.id_representative = id_representative;
	}
	public int getPaid() {
		return paid;
	}
	public void setPaid(int paid) {
		this.paid = paid;
	}
	public Date getDate_paid() {
		return date_paid;
	}
	public void setDate_paid(Date date_paid) {
		this.date_paid = date_paid;
	}
	public BeanTestOrder getId_order() {
		return id_order;
	}
	public void setId_order(BeanTestOrder id_order) {
		this.id_order = id_order;
	}





}
