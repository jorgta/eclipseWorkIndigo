package com.bituos.gjv.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public class LoginForm extends ActionForm 
{

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) 
	{
		  this.username = "";
		  this.password = "";
	}
	 public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) 
	 {	  
		 ActionErrors errors = new ActionErrors();
		  
		 if(password!=null)
		  {
			if (password.length()==0)
			  errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));

		 if(username!=null)
		  {
			if (username.length()==0)
			  errors.add("username", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("username", new org.apache.struts.action.ActionError("error.field.required"));

			 
	     return errors;
	 }

		 
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
