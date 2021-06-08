/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.bituos.truckAdmin.Beans;

import java.util.*;;

/**
 * @author cirm
*/

public class BeanEvent{

	private int id;
	private BeanTire id_tire;
	private String odometer;
	private BeanTypePlace id_type_place;
	private Date date_reg;
	private String comments;
	private String design;
	private int deep;
	private BeanTypeEvent id_type_event;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BeanTire getId_tire() {
		return id_tire;
	}
	public void setId_tire(BeanTire id_tire) {
		this.id_tire = id_tire;
	}
	public String getOdometer() {
		return odometer;
	}
	public void setOdometer(String odometer) {
		this.odometer = odometer;
	}
	public BeanTypePlace getId_type_place() {
		return id_type_place;
	}
	public void setId_type_place(BeanTypePlace id_type_place) {
		this.id_type_place = id_type_place;
	}
	public Date getDate_reg() {
		return date_reg;
	}
	public void setDate_reg(Date date_reg) {
		this.date_reg = date_reg;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDesign() {
		return design;
	}
	public void setDesign(String design) {
		this.design = design;
	}
	public int getDeep() {
		return deep;
	}
	public void setDeep(int deep) {
		this.deep = deep;
	}
	public BeanTypeEvent getId_type_event() {
		return id_type_event;
	}
	public void setId_type_event(BeanTypeEvent id_type_event) {
		this.id_type_event = id_type_event;
	}



	
}
