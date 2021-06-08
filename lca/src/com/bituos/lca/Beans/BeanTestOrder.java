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
import java.util.Date;

public class BeanTestOrder{

	private int id;
	private BeanBranch id_branch;
	private BeanPatient id_patient;
	private Date date_reg;
	
	private String patient_name;
	private Integer is_finished;
	private String email;
	private Date date_finished;
	private double discount;
	private double price;
	private BeanUser id_user;
	private BeanBranch id_branch_delivery;
	private Date date_delivery;
	private String password;
	private BeanCompany id_company;
	private String notes;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDate_delivery() {
		return date_delivery;
	}
	public void setDate_delivery(Date date_delivery) {
		this.date_delivery = date_delivery;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BeanBranch getId_branch() {
		return id_branch;
	}
	public void setId_branch(BeanBranch id_branch) {
		this.id_branch = id_branch;
	}
	public BeanPatient getId_patient() {
		return id_patient;
	}
	public void setId_patient(BeanPatient id_patient) {
		this.id_patient = id_patient;
	}
	public Date getDate_reg() {
		return date_reg;
	}
	public void setDate_reg(Date date_reg) {
		this.date_reg = date_reg;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}


	
	
	public Integer getIs_finished() {
		return is_finished;
	}
	public void setIs_finished(Integer is_finished) {
		this.is_finished = is_finished;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDate_finished() {
		return date_finished;
	}
	public void setDate_finished(Date date_finished) {
		this.date_finished = date_finished;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public BeanUser getId_user() {
		return id_user;
	}
	public void setId_user(BeanUser id_user) {
		this.id_user = id_user;
	}
	public BeanBranch getId_branch_delivery() {
		return id_branch_delivery;
	}
	public void setId_branch_delivery(BeanBranch id_branch_delivery) {
		this.id_branch_delivery = id_branch_delivery;
	}
	public BeanCompany getId_company() {
		return id_company;
	}
	public void setId_company(BeanCompany id_company) {
		this.id_company = id_company;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	



	
}
