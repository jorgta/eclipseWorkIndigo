/*
 * Created on June 25, 2018
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


public class BeanCounterLogDetail  {

	private int id;
	private BeanCounterLog id_counter_log;
	private String value_str;
	private int value_int;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getValue_str() {
		return value_str;
	}
	public void setValue_str(String value_str) {
		this.value_str = value_str;
	}
	public int getValue_int() {
		return value_int;
	}
	public void setValue_int(int value_int) {
		this.value_int = value_int;
	}
	public BeanCounterLog getId_counter_log() {
		return id_counter_log;
	}
	public void setId_counter_log(BeanCounterLog id_counter_log) {
		this.id_counter_log = id_counter_log;
	}
	
	
	
	
	
	
	
	
	
}
