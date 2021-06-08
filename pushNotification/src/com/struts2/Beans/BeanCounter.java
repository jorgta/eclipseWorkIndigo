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


public class BeanCounter  {

	private int id;
	private int timeToReport;
	private BeanType_Counter_Detail id_type_counter_detail;
	private BeanDevice id_device;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTimeToReport() {
		return timeToReport;
	}
	public void setTimeToReport(int timeToReport) {
		this.timeToReport = timeToReport;
	}
	public BeanType_Counter_Detail getId_type_counter_detail() {
		return id_type_counter_detail;
	}
	public void setId_type_counter_detail(
			BeanType_Counter_Detail id_type_counter_detail) {
		this.id_type_counter_detail = id_type_counter_detail;
	}
	
	public BeanDevice getId_device() {
		return id_device;
	}
	public void setId_device(BeanDevice id_device) {
		this.id_device = id_device;
	}
	
	
	
	
	
	
	
	
}
