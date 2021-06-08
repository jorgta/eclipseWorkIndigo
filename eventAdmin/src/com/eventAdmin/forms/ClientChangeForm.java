package com.eventAdmin.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.eventAdmin.*;
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
public class ClientChangeForm extends ActionForm {

	private String rfc = null;
	private String address = null;
	private String name = null;
	private String phone = null;
	private String email = null;
	private String company = null;
	private String process = null;
	
	private int id;

	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		rfc = null;
		address = null;
		name = null;
		

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if ( address != null )
		  { 
			address = address.trim();
			if (address.length() == 0)
				errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));

		if (email != null)
		  {
			email = email.trim();
			
			if ( !(email.equals("")) )
			  if ( !Utils.isEmail( email ) )
				errors.add("email", new org.apache.struts.action.ActionError("error.field.invalid.format"));
		  }
		else
			email = "";

		return errors;

	}

	
	
	
	
	
	
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get rfc
	 * @return String
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * Set rfc
	 * @param <code>String</code>
	 */
	public void setRfc(String r) {
		this.rfc = r;
	}

	/**
	 * Get address
	 * @return String
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set address
	 * @param <code>String</code>
	 */
	public void setAddress(String a) {
		this.address = a;
	}

	/**
	 * Get name
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name
	 * @param <code>String</code>
	 */
	public void setName(String n) {
		this.name = n;
	}

	

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}

	/**
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param string
	 */
	public void setPhone(String string) {
		phone = string;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
	
	

}
