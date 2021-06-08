package com.bituos.lca.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 1 field on this form:
 * <ul>
 * <li>id_company - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */

import com.bituos.Utils;



public class UserCreateRoot extends ActionForm {

	private String id_company = null;
	private String email = null;
	private String password = null;

	/**
	 * Get id_company
	 * @return String
	 */
	public String getId_company() {
		return id_company;
	}

	/**
	 * Set id_company
	 * @param <code>String</code>
	 */
	public void setId_company(String i) {
		this.id_company = i;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		id_company = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }
		
		
		if (email != null)
		  email = email.trim();

		if ( !Utils.isEmail( email) )
			errors.add("email", new org.apache.struts.action.ActionError("error.field.invalid.format"));

		if ( password!= null )
		  { 
			password = password.trim();
			if (password.length() == 0)
				errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));
	
		return errors;

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

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param string
	 */
	public void setPassword(String string) {
		password = string;
	}

}
