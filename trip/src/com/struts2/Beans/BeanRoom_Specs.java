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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


public class BeanRoom_Specs  {

	private int id;
	private BeanRoom id_room;
	private BeanSpec id_spec;
	private String spec_description;
	private java.util.Date date_reg;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BeanRoom getId_room() {
		return id_room;
	}
	public void setId_room(BeanRoom id_room) {
		this.id_room = id_room;
	}
	public BeanSpec getId_spec() {
		return id_spec;
	}
	public void setId_spec(BeanSpec id_spec) {
		this.id_spec = id_spec;
	}
	public String getSpec_description() {
		return spec_description;
	}
	public void setSpec_description(String spec_description) {
		this.spec_description = spec_description;
	}
	public java.util.Date getDate_reg() {
		return date_reg;
	}
	public void setDate_reg(java.util.Date date_reg) {
		this.date_reg = date_reg;
	}
		
	

}
