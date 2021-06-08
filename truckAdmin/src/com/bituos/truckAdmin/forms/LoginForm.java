package com.bituos.truckAdmin.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 3 fields on this form:
 * <ul>
 * <li>company - Company
 * <li>password - User or Administrator password
 * <li>username - User name
 * </ul>
 * @version 	1.0
 * @author      cirm
 */
public class LoginForm extends ActionForm {

	private String company = null;
	private String password = null;
	private String username = null;
	

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.
		/*company  = null;
		password = null;
		username = null;*/
		
		String addr = request.getRemoteAddr();
		String host = request.getRemoteHost();
		
		
		if ( request.getRemoteAddr().equals("192.168.0.104") 
			  ||
			 request.getRemoteAddr().equals("192.168.0.99")
			  ||
			 request.getRemoteAddr().equals("192.168.1.99")
			  ||
			 request.getRemoteHost().equals("pc0") 
			  ||
			 request.getRemoteHost().equals("dsanchez") 
			  ||
			 request.getRemoteHost().equals("0:0:0:0:0:0:0:1") 
		   )
		  {
			company = "bituos";
			username = "david";
			password = "da";
			  
		  }
		else
		  {
			company = null;
			password = null;
			username = null;
		  }
			
			
	}

	
	
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.


		if ( company != null )
		  {
			company = company.trim();
			if ( company.length() == 0 )
			  errors.add("company", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("company", new org.apache.struts.action.ActionError("error.field.required"));

	  if ( username != null )
		{
		  username = username.trim();
		  if ( username.length() == 0 )
			errors.add("username", new org.apache.struts.action.ActionError("error.field.required"));
		}
	  else
		errors.add("username", new org.apache.struts.action.ActionError("error.field.required"));

		if ( password != null )
		  {
			password = password.trim();
			if ( password.length() == 0 )
			  errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));

		return errors;

	}
	
	
	
	/**
	 * Get company
	 * @return String
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * Set company
	 * @param <code>String</code>
	 */
	public void setCompany(String c) {
		this.company = c;
	}

	/**
	 * Get password
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set password
	 * @param <code>String</code>
	 */
	public void setPassword(String p) {
		this.password = p;
	}

	/**
	 * Get username
	 * @return String
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set username
	 * @param <code>String</code>
	 */
	public void setUsername(String u) {
		this.username = u;
	}


}