package com.eventAdmin.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 1 field on this form:
 * <ul>
 * <li>user - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class ProcessAdminForm extends ActionForm {

	private String user = null;
	private String action = null;
	private String process = null;

	/**
	 * Get user
	 * @return String
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Set user
	 * @param <code>String</code>
	 */
	public void setUser(String u) {
		this.user = u;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		user = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

 		
		return errors;

	}
	/**
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @return
	 */
	public String getProcess() {
		return process;
	}

	/**
	 * @param string
	 */
	public void setAction(String string) {
		action = string;
	}

	/**
	 * @param string
	 */
	public void setProcess(String string) {
		process = string;
	}

}
