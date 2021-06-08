/*
 * Created on June 30, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.eventAdmin.Beans;


import java.util.Date;


public class BeanQuoteFlower{

	private int id;	
	private BeanQuote id_quote;
	
	private BeanFlower id_flower; 
	private String flower_description; 
	private double flower_price; 
	private String flower_perPerson; 
	private int personsQty;	
	


	public int getPersonsQty() {
		return personsQty;
	}



	public void setPersonsQty(int personsQty) {
		this.personsQty = personsQty;
	}



	public BeanFlower getId_flower() {
		return id_flower;
	}



	public void setId_flower(BeanFlower id_flower) {
		this.id_flower = id_flower;
	}



	public String getFlower_perPerson() {
		return flower_perPerson;
	}



	public void setFlower_perPerson(String flower_perPerson) {
		this.flower_perPerson = flower_perPerson;
	}





	public String getFlower_description() {
		return flower_description;
	}



	public void setFlower_description(String flower_description) {
		this.flower_description = flower_description;
	}




	public double getFlower_price() {
		return flower_price;
	}



	public void setFlower_price(double flower_price) {
		this.flower_price = flower_price;
	}








	public BeanQuote getId_quote() {
		return id_quote;
	}



	public void setId_quote(BeanQuote id_quote) {
		this.id_quote = id_quote;
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
