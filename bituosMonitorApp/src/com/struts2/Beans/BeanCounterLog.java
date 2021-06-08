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


public class BeanCounterLog  {

	private int id;
	private BeanCounter id_counter;
	private Date date_reg;
	private int is_str;
	private String value_str;
	private int value_int;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BeanCounter getId_counter() {
		return id_counter;
	}
	public void setId_counter(BeanCounter id_counter) {
		this.id_counter = id_counter;
	}
	public Date getDate_reg() {
		return date_reg;
	}
	public void setDate_reg(Date date_reg) {
		this.date_reg = date_reg;
	}
	public int getIs_str() {
		return is_str;
	}
	public void setIs_str(int is_str) {
		this.is_str = is_str;
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
	
	
	
	
	
	
	
	
	
}
