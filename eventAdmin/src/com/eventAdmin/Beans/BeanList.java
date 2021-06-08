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

public class BeanList{

	private int id;
	private String description;
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
