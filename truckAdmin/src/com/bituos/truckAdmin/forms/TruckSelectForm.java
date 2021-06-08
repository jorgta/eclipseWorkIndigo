package com.bituos.truckAdmin.forms;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.bituos.truckAdmin.*; 
import com.bituos.*;


/**
 * Form bean for a Struts application.
 * Users may access 4 fields on this form:
 * <ul>
 * <li>rfc - [your comment here]
 * <li>address - [your comment here]
 * <li>password - [your comment here]
 * <li>name - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class TruckSelectForm extends ActionForm {

	private String id_truck = null;
	private String description = null;
	private String registration= null;
	private String model= null;
	private String process = null;
	
	
	
	public String getId_truck() {
		return id_truck;
	}
	public void setId_truck(String id_truck) {
		this.id_truck = id_truck;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	



}
