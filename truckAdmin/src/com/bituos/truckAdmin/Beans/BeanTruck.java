/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.bituos.truckAdmin.Beans;

import java.util.*;

/**
 * @author cirm
*/

public class BeanTruck{

	private int id;
	private String description;
	private String registration;
	private String model;
	private Date date_reg;
	private BeanTruckTireConfiguration id_truck_configuration;
	private String active;
	
	
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
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Date getDate_reg() {
		return date_reg;
	}
	public void setDate_reg(Date date_reg) {
		this.date_reg = date_reg;
	}
	public BeanTruckTireConfiguration getId_truck_configuration() {
		return id_truck_configuration;
	}
	public void setId_truck_configuration(
			BeanTruckTireConfiguration id_truck_configuration) {
		this.id_truck_configuration = id_truck_configuration;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}



}
