/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.eventAdmin.Beans;

import java.util.Date;

/**
 * @author cirm
*/

public class PaymentsRecord{

	private int id;
	private BeanSale id_sale;

	private int id_intern;
	private Date date_reg; 
	private char canceled;
	private double quantity;
	private Date date_canceled;
	private double currentCharge;



	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public BeanSale getId_sale() {
		return id_sale;
	}
	public void setId_sale(BeanSale id_sale) {
		this.id_sale = id_sale;
	}

	public int getId_intern() {
		return id_intern;
	}
	public void setId_intern(int id_intern) {
		this.id_intern = id_intern;
	}
	public Date getDate_reg() {
		return date_reg;
	}
	public void setDate_reg(Date date_reg) {
		this.date_reg = date_reg;
	}
	public char getCanceled() {
		return canceled;
	}
	public void setCanceled(char canceled) {
		this.canceled = canceled;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public Date getDate_canceled() {
		return date_canceled;
	}
	public void setDate_canceled(Date date_canceled) {
		this.date_canceled = date_canceled;
	}
	public double getCurrentCharge() {
		return currentCharge;
	}
	public void setCurrentCharge(double currentCharge) {
		this.currentCharge = currentCharge;
	}
	


	

}
