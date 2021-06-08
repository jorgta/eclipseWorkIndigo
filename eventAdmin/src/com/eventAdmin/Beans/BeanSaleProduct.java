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

public class BeanSaleProduct{

	private int id;	
	private BeanSale id_sale;	
	private BeanProduct id_product; 
	private String product_description; 
	private int qty;	
	private String perPerson; 
	private int personsQty;	
	private double product_price;




	public int getQty() {
		return qty;
	}



	public void setQty(int qty) {
		this.qty = qty;
	}



	public String getPerPerson() {
		return perPerson;
	}



	public void setPerPerson(String perPerson) {
		this.perPerson = perPerson;
	}



	public int getPersonsQty() {
		return personsQty;
	}



	public void setPersonsQty(int personsQty) {
		this.personsQty = personsQty;
	}



	public double getProduct_price() {
		return product_price;
	}



	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}



	public BeanProduct getId_product() {
		return id_product;
	}



	public void setId_product(BeanProduct id_product) {
		this.id_product = id_product;
	}



	public BeanSale getId_sale() {
		return id_sale;
	}



	public void setId_sale(BeanSale id_sale) {
		this.id_sale = id_sale;
	}



	public String getProduct_description() {
		return product_description;
	}



	public void setProduct_description(String product_description) {
		this.product_description = product_description;
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
