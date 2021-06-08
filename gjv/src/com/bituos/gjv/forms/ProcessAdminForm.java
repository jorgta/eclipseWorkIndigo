package com.bituos.gjv.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class ProcessAdminForm extends ActionForm {


	// user vs process
	private String user = null;
	private String action = null;
	private String process = null;

	//profile
	private String profileNew = null;
	private String profileDelete = null;

	// profile vs process
	private String profileAsignProcess_id_profile;
	private String profileAsignProcess_id_process;
	private String profileDeleteProcess_id_process;

	// user vs user

	private String profileUser_id_userFrom= null;
	private String profileUser_id_userTo = null;

	// profile vs user

	private String profileUser_id_profile = null;
	private String profileUser_id_user = null;


	/**
	* @return
	* Obtiene el valor de la variable user
	 */
	public String getUser() {
		return user;
	}

	/**

	  * @param string
	* Asigna un valor a la variable user
	 */
	public void setUser(String u) {
		this.user = u;
	}

	/**
	* @return
	* Obtiene el valor de la variable  action
	 */
	public String getAction() {
		return action;
	}

	/**
	  * @param string
	* Asigna un valor a la variable action
	 */
	public void setAction(String a) {
		this.action = a;
	}

	/**
	* @return
	* Obtiene el valor de la variable  process
	 */
	public String getProcess() {
		return process;
	}

	/**
	  * @param string
	* Asigna un valor a la variable process
	 */
	public void setProcess(String p) {
		this.process = p;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		user = null;
		action = null;
		process = null;
		

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
	/**
	* @return
	* Obtiene el valor de la variable profileAsignProcess_id_process
	 */
	public String getProfileAsignProcess_id_process() {
		return profileAsignProcess_id_process;
	}

	/**
	* @return
	* Obtiene el valor de la variable profileAsignProcess_id_profile
	 */
	public String getProfileAsignProcess_id_profile() {
		return profileAsignProcess_id_profile;
	}

	/**
	* @return
	* Obtiene el valor de la variable profileDelete
	 */
	public String getProfileDelete() {
		return profileDelete;
	}

	/**
	* @return
	* Obtiene el valor de la variable profileDeleteProcess_id_process
	 */
	public String getProfileDeleteProcess_id_process() {
		return profileDeleteProcess_id_process;
	}

	/**
	* @return
	* Obtiene el valor de la variable profileNew
	 */
	public String getProfileNew() {
		return profileNew;
	}

	/**
	* @return
	* Obtiene el valor de la variable profileUser_id_profile
	 */
	public String getProfileUser_id_profile() {
		return profileUser_id_profile;
	}

	/**
	* @return
	* Obtiene el valor de la variable profileUser_id_user
	 */
	public String getProfileUser_id_user() {
		return profileUser_id_user;
	}

	/**
	* @return
	* Obtiene el valor de la variable profileUser_id_userFrom
	 */
	public String getProfileUser_id_userFrom() {
		return profileUser_id_userFrom;
	}

	/**
	* @return
	* Obtiene el valor de la variable profileUser_id_userTo
	 */
	public String getProfileUser_id_userTo() {
		return profileUser_id_userTo;
	}

	/**
	  * @param string
	* Asigna un valor a la variable profileAsignProcess_id_process
	 */
	public void setProfileAsignProcess_id_process(String string) {
		profileAsignProcess_id_process = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable profileAsignProcess_id_profile
	 */
	public void setProfileAsignProcess_id_profile(String string) {
		profileAsignProcess_id_profile = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable profileDelete
	 */
	public void setProfileDelete(String string) {
		profileDelete = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable profileDeleteProcess_id_process
	 */
	public void setProfileDeleteProcess_id_process(String string) {
		profileDeleteProcess_id_process = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable profileNew
	 */
	public void setProfileNew(String string) {
		profileNew = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable profileUser_id_profile
	 */
	public void setProfileUser_id_profile(String string) {
		profileUser_id_profile = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable profileUser_id_user
	 */
	public void setProfileUser_id_user(String string) {
		profileUser_id_user = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable profileUser_id_userFrom
	 */
	public void setProfileUser_id_userFrom(String string) {
		profileUser_id_userFrom = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable profileUser_id_userTo
	 */
	public void setProfileUser_id_userTo(String string) {
		profileUser_id_userTo = string;
	}

}
