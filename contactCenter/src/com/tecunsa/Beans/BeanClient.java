/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.tecunsa.Beans;

import java.util.Date;

/**
 * @author cirm
*/

public class BeanClient{

	private int id;
	private String name;
	private String active;
	private String address;
	private String rfc;
	private String email;
	private String telephone;
	private String email1;
	private String contact;


	/**
	 * @return
	 */
	public String getActive() {
		return active;
	}

	/**
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * @return
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param string
	 */
	public void setActive(String string) {
		active = string;
	}

	/**
	 * @param string
	 */
	public void setAddress(String string) {
		address = string;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}


	/**
	 * @param string
	 */
	public void setRfc(String string) {
		rfc = string;
	}

	/**
	 * @param string
	 */
	public void setTelephone(String string) {
		telephone = string;
	}



	/**
	 * @return
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @return
	 */
	public String getEmail1() {
		return email1;
	}

	/**
	 * @param string
	 */
	public void setContact(String string) {
		contact = string;
	}

	/**
	 * @param string
	 */
	public void setEmail1(String string) {
		email1 = string;
	}

}
