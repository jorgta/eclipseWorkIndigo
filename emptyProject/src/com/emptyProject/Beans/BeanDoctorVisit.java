/*
 * Created on June 29, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and 

Comments
 */
package com.bituos.lca.Beans;

import java.util.Date;

/**
 * @author cirm
*/

public class BeanDoctorVisit{

	private int id;
	private BeanDoctor id_doctor;
	private BeanRepresentative id_representative;
	private Date date_reg;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BeanDoctor getId_doctor() {
		return id_doctor;
	}
	public void setId_doctor(BeanDoctor id_doctor) {
		this.id_doctor = id_doctor;
	}
	public BeanRepresentative getId_representative() {
		return id_representative;
	}
	public void setId_representative(BeanRepresentative id_representative) {
		this.id_representative = id_representative;
	}
	public Date getDate_reg() {
		return date_reg;
	}
	public void setDate_reg(Date date_reg) {
		this.date_reg = date_reg;
	}
	
	
	
}
