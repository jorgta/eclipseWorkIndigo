package com.bituos.truckAdmin.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 1 field on this form:
 * <ul>
 * <li>process - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class PreWVForm extends ActionForm {

	private String process = null;
	private String id_card = null;
	private String nip = null;

	/**
	 * Get process
	 * @return String
	 */
	public String getProcess() {
		return process;
	}

	/**
	 * Set process
	 * @param <code>String</code>
	 */
	public void setProcess(String p) {
		this.process = p;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

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

		if ( process.equals("rpt.cardCredit") )
		  {
			if ( id_card != null )
			  { 
				id_card = id_card.trim();
				if (id_card.length() == 0)
					errors.add("id_card", new org.apache.struts.action.ActionError("error.field.required"));
			  }
			else
			  errors.add("id_card", new org.apache.struts.action.ActionError("error.field.required"));
		  
			if ( nip != null )
			  { 
				nip = nip.trim();
				if (nip.length() == 0)
					errors.add("nip", new org.apache.struts.action.ActionError("error.field.required"));
			  }
			else
			  errors.add("nip", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		  
		return errors;

	}
	/**
	 * @return
	 */
	public String getId_card() {
		return id_card;
	}

	/**
	 * @param string
	 */
	public void setId_card(String string) {
		id_card = string;
	}

	/**
	 * @return
	 */
	public String getNip() {
		return nip;
	}

	/**
	 * @param string
	 */
	public void setNip(String string) {
		nip = string;
	}

	
	
	
	
	

}
