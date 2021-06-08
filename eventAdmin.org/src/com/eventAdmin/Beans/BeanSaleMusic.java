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

public class BeanSaleMusic{

	private int id;	
	private BeanSale id_sale;	

	private BeanMusic id_music; 
	private String music_description; 
	private double music_price; 
	private String music_perPerson; 
	private int personsQty;	








	public int getPersonsQty() {
		return personsQty;
	}



	public void setPersonsQty(int personsQty) {
		this.personsQty = personsQty;
	}



	public String getMusic_perPerson() {
		return music_perPerson;
	}



	public void setMusic_perPerson(String music_perPerson) {
		this.music_perPerson = music_perPerson;
	}



	public BeanMusic getId_music() {
		return id_music;
	}



	public void setId_music(BeanMusic id_music) {
		this.id_music = id_music;
	}



	public String getMusic_description() {
		return music_description;
	}



	public void setMusic_description(String music_description) {
		this.music_description = music_description;
	}



	public double getMusic_price() {
		return music_price;
	}



	public void setMusic_price(double music_price) {
		this.music_price = music_price;
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
