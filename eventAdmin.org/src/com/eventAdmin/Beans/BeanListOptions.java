/*
 * Created on June 30, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.eventAdmin.Beans;

/**
 * @author cirm
*/

public class BeanListOptions{

	private int id;
	private BeanList id_list;
	private int min;
	private int max;
	private double price;
	private String active;



	public String getActive() {
		return active;
	}



	public void setActive(String active) {
		this.active = active;
	}



	public BeanList getId_list() {
		return id_list;
	}



	public void setId_list(BeanList id_list) {
		this.id_list = id_list;
	}



	public int getMax() {
		return max;
	}



	public void setMax(int max) {
		this.max = max;
	}



	public int getMin() {
		return min;
	}



	public void setMin(int min) {
		this.min = min;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
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
