/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.bituos.lca.Beans;

/**
 * @author cirm
*/

public class BeanTestDoctor{

	private int id;
	private BeanTestOrder id_order;
	private BeanDoctor id_doctor;
	private String doctor_name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BeanTestOrder getId_order() {
		return id_order;
	}
	public void setId_order(BeanTestOrder id_order) {
		this.id_order = id_order;
	}
	public BeanDoctor getId_doctor() {
		return id_doctor;
	}
	public void setId_doctor(BeanDoctor id_doctor) {
		this.id_doctor = id_doctor;
	}
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	
	

}
