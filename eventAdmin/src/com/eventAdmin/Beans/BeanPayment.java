/*
 * Created on June 30, 2006
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

public class BeanPayment{

	private int id;	
	private BeanSale id_sale;	
	private Date date_reg; 
	private double total; 








	public Date getDate_reg() {
		return date_reg;
	}



	public void setDate_reg(Date date_reg) {
		this.date_reg = date_reg;
	}



	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}



	public BeanSale getId_sale() {
		return id_sale;
	}



	public void setId_sale(BeanSale id_sale) {
		this.id_sale = id_sale;
	}






	/**
	 * @return
	 */
	public int getId() {
		return id;
	}



	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}



}
