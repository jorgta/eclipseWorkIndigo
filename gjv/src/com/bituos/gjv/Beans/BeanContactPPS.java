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

public class BeanContactPPS{

	private int id;
	private String first_name;
	private String last_name;
	private String enterprise_name;
	private String address;
	private String city;	
	private String country;	
	private String office_phone;
	private String home_phone;
	private String cel_phone;
	private char active;


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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEnterprise_name() {
		return enterprise_name;
	}
	public void setEnterprise_name(String enterprise_name) {
		this.enterprise_name = enterprise_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getHome_phone() {
		return home_phone;
	}
	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
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
	public String getOffice_phone() {
		return office_phone;
	}
	public void setOffice_phone(String office_phone) {
		this.office_phone = office_phone;
	}
	
	public char getActive() {
		return active;
	}
	public void setActive(char active) {
		this.active = active;
	}
	
	


	
	
}
