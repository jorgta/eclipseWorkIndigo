package com.bituos.lca.forms;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.bituos.lca.*; 
import com.bituos.*;


/**
 * Form bean for a Struts application.
 * Users may access 4 fields on this form:
 * <ul>
 * <li>address - [your comment here]
 * <li>notes - [your comment here]
 * <li>name - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class RepresentativeNewForm extends ActionForm {

	private String id = null;
	private String name = null;
	private String notes = null;
	private String address = null;
	private String process = null; 
	private String email = null; 
	private String phone = null;
	

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		name = null;
		notes = null;
		address = null;
		email = null;


	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		
		if ( name != null )
		  {
			name = name.trim().toUpperCase();
			if ( name.length() == 0 )
			  errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));

		
		if ( address != null )
		  {
			address = address.trim().toUpperCase();
			if ( address.length() == 0 )
			  errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));

		
	  if (notes != null)
		notes = notes.trim();
	  else 
		notes = "";
		
	  if (phone != null)
		 phone = phone.trim();
	  else
	    phone = "";
	
	  return errors;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	
	


}
