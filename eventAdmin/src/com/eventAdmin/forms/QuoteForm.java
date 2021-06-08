package com.eventAdmin.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.bituos.Utils;

/**
 * @version 	1.0
 * @author     	Bituos Tools S de RL MI
 */

public class QuoteForm extends ActionForm {

	private String process;
	private String id_quote;
	private String anotherEmail;

	
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }


		return errors;

	}


	public String getAnotherEmail() {
		return anotherEmail;
	}


	public void setAnotherEmail(String anotherEmail) {
		this.anotherEmail = anotherEmail;
	}


	public String getProcess() {
		return process;
	}


	public void setProcess(String process) {
		this.process = process;
	}


	public String getId_quote() {
		return id_quote;
	}


	public void setId_quote(String id_quote) {
		this.id_quote = id_quote;
	}



	
}
