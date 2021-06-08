/*
 * Created on June 30, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.eventAdmin.Beans;

/**
 * @author dsa
*/

public class BeanProduct{

	private int id;
	private String description;
	private double price;
	private String perPerson;
	private BeanCompany id_company;
	private String active;

	
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public BeanCompany getId_company() {
		return id_company;
	}

	public void setId_company(BeanCompany id_company) {
		this.id_company = id_company;
	}

	public String getPerPerson() {
		return perPerson;
	}

	public void setPerPerson(String perPerson) {
		this.perPerson = perPerson;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Get description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set description
	 */
	public void setDescription(String string) {
		description = string;
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
