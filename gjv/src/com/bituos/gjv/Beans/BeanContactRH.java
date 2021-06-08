/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.bituos.gjv.Beans;

import java.util.Date;


/**
 * @author dsa@bituos.com
*/

public class BeanContactRH{

	private int id;
	private String first_name;
	private String last_name;
	private String address;
	private String colony;	
	private String phone;
	private String cel_phone;
	private char active;
	private char married;
	private String NSS;	
	private String wife_name;
	private Date date_of_birth;
	
	
	public char getActive() {
		return active;
	}
	public void setActive(char active) {
		this.active = active;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCel_phone() {
		return cel_phone;
	}
	public void setCel_phone(String cel_phone) {
		this.cel_phone = cel_phone;
	}
	public String getColony() {
		return colony;
	}
	public void setColony(String colony) {
		this.colony = colony;
	}


	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public char getMarried() {
		return married;
	}
	public void setMarried(char married) {
		this.married = married;
	}
	public String getNSS() {
		return NSS;
	}
	public void setNSS(String nss) {
		NSS = nss;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWife_name() {
		return wife_name;
	}
	public void setWife_name(String wife_name) {
		this.wife_name = wife_name;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}



	
	
}
