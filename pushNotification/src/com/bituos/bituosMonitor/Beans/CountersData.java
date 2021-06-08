/*
 * Created on June 25, 2018
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.bituos.bituosMonitor.Beans;


/**
 * @author dsa@bituos.com
*/

public class CountersData{

	private String className;
	private String propertyName;
	private int id_counter;
    private int dataCount;
    private String[] data;
    
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public int getDataCount() {
		return dataCount;
	}
	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}
	public int getId_counter() {
		return id_counter;
	}
	public void setId_counter(int id_counter) {
		this.id_counter = id_counter;
	}
	
	
	
	
}
