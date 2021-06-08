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

import java.util.Date;

public class BeanContact{

	private int id;
	private String ip;
	private String company;
	private String name;
	private String email;
	private String phone;
	private Date date_reg;
	private String comments;


	

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}



	

	/**
	 * @return
	 */
	public String getIp() {
		return ip;
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
	public void setIp(String string) {
		ip = string;
	}



	/**
	 * @return
	 */
	public Date getDate_reg() {
		return date_reg;
	}

	/**
	 * @param date
	 */
	public void setDate_reg(Date date) {
		date_reg = date;
	}

	/**
	 * @return
	 */
	public String getCompany() {
		return company;
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
	public String getName() {
		return name;
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
	public void setCompany(String string) {
		company = string;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
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
	public void setPhone(String string) {
		phone = string;
	}

	/**
	 * @return
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param string
	 */
	public void setComments(String string) {
		comments = string;
	}

}
