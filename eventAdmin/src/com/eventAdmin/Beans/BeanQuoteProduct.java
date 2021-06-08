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

public class BeanQuoteProduct{

	private int id;	
	private BeanQuote id_quote;	
	private BeanProduct id_product; 
	private String product_description; 
	private int qty;	
	private String perPerson; 
	private int personsQty;	
	private double product_price;
	
	
	

	public double getProduct_price() {
		return product_price;
	}



	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}



	public int getPersonsQty() {
		return personsQty;
	}



	public void setPersonsQty(int personsQty) {
		this.personsQty = personsQty;
	}



	public BeanProduct getId_product() {
		return id_product;
	}



	public void setId_product(BeanProduct id_product) {
		this.id_product = id_product;
	}



	public BeanQuote getId_quote() {
		return id_quote;
	}



	public void setId_quote(BeanQuote id_quote) {
		this.id_quote = id_quote;
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



}
