package com.eventAdmin.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author     	Bituos Tools S de RL MI
 */

public class QuoteDeleteForm extends ActionForm {

	private int id;
	private String id_quote;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.
		id_quote = null;
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

//		if( process != null )
//		  {
//			if( process.equals("validate"))
//			  {
//				if(  id_client==null  )
//					errors.add("id_client", new org.apache.struts.action.ActionError("error.field.required"));
//				else
//				  {
//					id_client=id_client.trim();
//					if (  id_client.length() == 0  )
//						errors.add("id_client", new org.apache.struts.action.ActionError("error.field.required"));
//		  }

		return errors;

	}




	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getId_quote() {
		return id_quote;
	}

	public void setId_quote(String id_quote) {
		this.id_quote = id_quote;
	}






	
}
