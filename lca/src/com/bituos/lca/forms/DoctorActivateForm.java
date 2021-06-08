package com.bituos.lca.forms;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.bituos.lca.*; 
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
public class DoctorActivateForm extends ActionForm {
	private String id_doctor = null;
	private String name = null;
	private String address = null;
	private String email = null; 
	private String notes = null;
	private int active;
	private String phone;
	private String id_representative;
	private String phone_cel;

	
	
	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		name = null;
		address = null;
		email = null;
		notes = null;

	}
	
	
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		
		return errors;

	}




	public String getId_doctor() {
		return id_doctor;
	}


	public void setId_doctor(String id_doctor) {
		this.id_doctor = id_doctor;
	}
	
	
	
	
	
	
	
	
	
	

}
