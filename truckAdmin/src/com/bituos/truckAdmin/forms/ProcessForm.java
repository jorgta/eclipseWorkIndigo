package com.bituos.truckAdmin.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 3 fields on this form:
 * <ul>
 * <li>company - [your comment here]
 * <li>password - [your comment here]
 * <li>username - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class ProcessForm extends ActionForm {

	private String company = null;
	private String password = null;
	private String username = null;

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

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		company = null;
		password = null;
		username = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.trim().length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }
		return errors;

	}
}
