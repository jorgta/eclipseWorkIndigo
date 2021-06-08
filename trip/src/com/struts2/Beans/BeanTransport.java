/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.struts2.Beans;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class BeanTransport {

	private int id;
	private BeanTypeTransport id_type_transport;	
	private String description;
	private int capacity;
	private double price_per_person;
	
	
	public double getPrice_per_person() {
		return price_per_person;
	}
	public void setPrice_per_person(double price_per_person) {
		this.price_per_person = price_per_person;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BeanTypeTransport getId_type_transport() {
		return id_type_transport;
	}
	public void setId_type_transport(BeanTypeTransport id_type_transport) {
		this.id_type_transport = id_type_transport;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
	
	
}
