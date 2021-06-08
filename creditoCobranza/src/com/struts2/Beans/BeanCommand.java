/*
 * Created on 
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.struts2.Beans;


/**
 * @author dsa@bituos.com
*/

public class BeanCommand{

	private int id;
	private BeanTypeCommand id_type_command;
	private String done;
	private BeanCompany id_company;
	
	public BeanCompany getId_company() {
		return id_company;
	}
	public void setId_company(BeanCompany id_company) {
		this.id_company = id_company;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getDone() {
		return done;
	}
	public void setDone(String done) {
		this.done = done;
	}
	public BeanTypeCommand getId_type_command() {
		return id_type_command;
	}
	public void setId_type_command(BeanTypeCommand id_type_command) {
		this.id_type_command = id_type_command;
	}
	
	
	






	
	
}
