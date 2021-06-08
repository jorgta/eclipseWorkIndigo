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

public class BeanPayments{

	private int id;	
	private BeanUser id_user;	
	private BeanSale id_sale;
	private BeanCompany id_company;
	private int id_intern;
	private Date date_reg; 
	private char canceled;
	private double quantity;
	private Date date_canceled;	
	private BeanTypePayment id_tipo_pago;
	private BeanPaymentTypeForm id_tipo_forma_pago;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BeanUser getId_user() {
		return id_user;
	}
	public void setId_user(BeanUser id_user) {
		this.id_user = id_user;
	}
	public BeanSale getId_sale() {
		return id_sale;
	}
	public void setId_sale(BeanSale id_sale) {
		this.id_sale = id_sale;
	}
	public BeanCompany getId_company() {
		return id_company;
	}
	public void setId_company(BeanCompany id_company) {
		this.id_company = id_company;
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
	public BeanTypePayment getId_tipo_pago() {
		return id_tipo_pago;
	}
	public void setId_tipo_pago(BeanTypePayment id_tipo_pago) {
		this.id_tipo_pago = id_tipo_pago;
	}
	public BeanPaymentTypeForm getId_tipo_forma_pago() {
		return id_tipo_forma_pago;
	}
	public void setId_tipo_forma_pago(BeanPaymentTypeForm id_tipo_forma_pago) {
		this.id_tipo_forma_pago = id_tipo_forma_pago;
	}
	


	

}
