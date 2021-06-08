package com.bituos.gjv.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public class PreForm extends ActionForm 
{

	private String process;

	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{
	}
	
	 public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) 
	 {	  
		 ActionErrors errors = new ActionErrors();
		  
		/*	if ( process != null )
			  {
				process = process.trim().toUpperCase();
			    if (process.length() == 0)
				    errors.add("process", new org.apache.struts.action.ActionError("error.field.required"));			  
			  }
		    else
			  errors.add("process", new org.apache.struts.action.ActionError("error.field.required"));
			*/
			 
		
			 
	     return errors;
	 }

	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	

}
