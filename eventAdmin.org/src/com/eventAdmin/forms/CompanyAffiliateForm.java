package com.eventAdmin.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.eventAdmin.*;
import com.bituos.*;

/**
 * Form bean for a Struts application.
 * Users may access 3 fields on this form:
 * <ul>
 * <li>amount - [your comment here]
 * <li>concept - [your comment here]
 * <li>id - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class CompanyAffiliateForm extends ActionForm {

	private String company = null;
	private String name = null;
	private String email = "dsa1972@yahoo.com";
	private String emailreenter = "dsa1972@yahoo.com";
	private String phone = null;
	private String comments = null;

	

	

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		company = null;
		name = null;
		company = null;
		email = null;
		emailreenter = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if ( company != null )
		  { 
			company = company.trim();
			if (company.length() == 0)
				errors.add("company", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("company", new org.apache.struts.action.ActionError("error.field.required"));



		if ( name != null )
		  { 
			name = name.trim();
			if (name.length() == 0)
				errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));


		if ( email != null )
		  { 
			email = email.trim();
			if (email.length() == 0)
				errors.add("email", new org.apache.struts.action.ActionError("error.field.required"));
			else
			  if ( !Utils.isEmail(email) )
			  	errors.add("email", new org.apache.struts.action.ActionError("error.field.invalid.format"));
			  else
				  if ( emailreenter == null )
				    errors.add("emailreenter", new org.apache.struts.action.ActionError("error.field.required"));
				  else
				    if ( !email.equals(emailreenter))
				      errors.add("emailreenter", new org.apache.struts.action.ActionError("error.field.email.reenter.equal"));
				    

		  }
		else
		  errors.add("email", new org.apache.struts.action.ActionError("error.field.required"));


		return errors;

	}
	/**
	 * @return
	 */
	public String getCompany() {
		return company;
	}

	/**
	
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param string
	 */
	public void setCompany(String string) {
		company = string;
	}

	

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}


	/**
	 * @return
	 */
	public String getEmail() {  
		return email;
	}

	/**
	 * @return
	 */
	public String getEmailreenter() {
		return emailreenter;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

	/**
	 * @param string
	 */
	public void setEmailreenter(String string) {
		emailreenter = string;
	}

	/**
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param string
	 */
	public void setPhone(String string) {
		phone = string;
	}

	/**
	 * @return
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param string
	 */
	public void setComments(String string) {
		comments = string;
	}

}
