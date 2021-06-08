/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.struts2.Beans;



import java.io.Serializable;
//import java.sql.Date;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


public class BeanHotel  {

	private int id;
	private BeanCompany id_company;
	private String description;
	private Date date_reg;
	private String address;
	private int active;
	private String userName;
	private String image1;
	private BeanType_Reservation id_type_reservation;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate_reg() {
		return date_reg;
	}
	public void setDate_reg(Date date_reg) {
		this.date_reg = date_reg;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public BeanCompany getId_company() {
		return id_company;
	}
	public void setId_company(BeanCompany id_company) {
		this.id_company = id_company;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public BeanType_Reservation getId_type_reservation() {
		return id_type_reservation;
	}
	public void setId_type_reservation(BeanType_Reservation id_type_reservation) {
		this.id_type_reservation = id_type_reservation;
	}
}
