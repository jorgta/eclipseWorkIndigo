package com.emptyProject.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.emptyProject.*;
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
public class UserChangeForm extends ActionForm {

	private String rfc = null;
	private String address = null;
	private String name = null;
	private String password = null;
	private String email = null;
	private int id;

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

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		rfc = null;
		address = null;
		name = null;
		password = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if ( password != null )
		  {
			password = password.trim();
			if ( password.length() == 0 )
			  errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));

	  if ( address != null )
		{
		  address = address.trim();
		  if ( address.length() == 0 )
			errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));
		}
	  else
		errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));

		if (email != null)
		  email = email.trim();

		if ( !Utils.isEmail( email) )
			errors.add("email", new org.apache.struts.action.ActionError("error.field.invalid.format"));

	   return errors;

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
	public String getEmail() {
		return email;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

}
