/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.eventAdmin.Beans;

/**
 * @author cirm
*/

public class BeanClient{

	private int id;
	private String name;
	private String rfc;
	private String address;
	private String active;
	private BeanCompany id_company;
	private String phone;
	private String email;
	private String company;
	private int id_intern;

	
	
	
	

	public int getId_intern() {
		return id_intern;
	}

	public void setId_intern(int id_intern) {
		this.id_intern = id_intern;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set id
	 */
	public void setId(int integer) {
		id = integer;
	}

	/**
	 * Get name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * Get rfc
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * Set rfc
	 */
	public void setRfc(String string) {
		rfc = string;
	}
	
	/**
	 * Get address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set address
	 */
	public void setAddress(String string) {
		address = string;
	}	
	
	/**
	 * Get active
	 */
	public String getActive() {
		return active;
	}

	/**
	 * Set active
	 */
	public void setActive(String string) {
		active = string;
	}
	

	/**
	 * @return
	 */
	public BeanCompany getId_company() {
		return id_company;
	}

	/**
	 * @param company
	 */
	public void setId_company(BeanCompany company) {
		id_company = company;
	}

	/**
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param string
	 */
	public void setPhone(String string) {
		phone = string;
	}

}
