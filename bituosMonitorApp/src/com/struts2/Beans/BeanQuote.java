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


public class BeanQuote  {

	private int id;
	private Date date_begin;
	private Date date_end;
	private Date date_to_pay;
	private Date date_blocked;
	private String client_name;
	private String notes;
	//private BeanType_Reservation id_type_reservation;
	private double price;
	private BeanTransport id_transport;
	private String transport_description;
	private double transport_price_per_person;
	
	public String getTransport_description() {
		return transport_description;
	}
	public void setTransport_description(String transport_description) {
		this.transport_description = transport_description;
	}
	public double getTransport_price_per_person() {
		return transport_price_per_person;
	}
	public void setTransport_price_per_person(double transport_price_per_person) {
		this.transport_price_per_person = transport_price_per_person;
	}
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public java.util.Date getDate_begin() {
		return date_begin;
	}
	public void setDate_begin(Date date_begin) {
		this.date_begin = date_begin;
	}
	public Date getDate_end() {
		return date_end;
	}
	public void setDate_end(Date date_end) {
		this.date_end = date_end;
	}
	
	public Date getDate_to_pay() {
		return date_to_pay;
	}
	public void setDate_to_pay(Date date_to_pay) {
		this.date_to_pay = date_to_pay;
	}
	public Date getDate_blocked() {
		return date_blocked;
	}
	public void setDate_blocked(Date date_blocked) {
		this.date_blocked = date_blocked;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/*public BeanType_Reservation getId_type_reservation() {
		return id_type_reservation;
	}
	public void setId_type_reservation(BeanType_Reservation id_type_reservation) {
		this.id_type_reservation = id_type_reservation;
	}*/
	public BeanTransport getId_transport() {
		return id_transport;
	}
	public void setId_transport(BeanTransport id_transport) {
		this.id_transport = id_transport;
	}
	
	
	


}
