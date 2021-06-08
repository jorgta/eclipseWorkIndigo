package com.bituos.gjv.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


import com.bituos.gjv.*;

public class ContactGroupActivateForm extends ActionForm {

	private String id = null;
    private String process="";


	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		id = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if ( id != null )
		  {
			id = id.trim().toUpperCase();
		    if (id.length() == 0)
			    errors.add("id", new org.apache.struts.action.ActionError("error.field.required"));			  
		  }
	    else
		  errors.add("id", new org.apache.struts.action.ActionError("error.field.required"));
		
		 
		return errors;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}


}
