package com.bituos.gjv.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class UserChangeForm extends ActionForm {

	private int idUser;
	private String name = null;
	private String full_name = null;
	private String password = null;
	private String confirmpassword=null;
	private String address = null;
	private String rfc = null;
	private String email = null;
	private String telephone = null;
	private String process = null;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

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
		if(process.equals("change"))
		{
			if ( name != null )
			{
				name = name.trim();
				if (name.length() == 0)
					errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
				if (name.indexOf(" ") > 0)
					errors.add("name", new org.apache.struts.action.ActionError("error.field.required.no.space"));
			}
			else
			  errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
			
			
			if ( password != null )
			  {
				password = password.trim();
				if ( password.length() == 0 )
				  errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));
				else
					if ( confirmpassword != null )
						{
						  confirmpassword = confirmpassword.trim();
						  if ( confirmpassword.length() == 0 )
							errors.add("confirmpassword", new org.apache.struts.action.ActionError("error.field.required"));
						  else
							  if ( !confirmpassword.equals( password ) )
							  	errors.add("confirmpassword", new org.apache.struts.action.ActionError("error.field.confirm"));
							  else
								  if (confirmpassword.indexOf(" ") > 0)
									  errors.add("confirmpassword", new org.apache.struts.action.ActionError("error.field.required.no.space"));
						}
					else
						errors.add("confirmpassword", new org.apache.struts.action.ActionError("error.field.required"));

				if (password.indexOf(" ") > 0)
					errors.add("password", new org.apache.struts.action.ActionError("error.field.required.no.space"));
			  }
			else
			  errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));
			

			if (full_name.trim().length() == 0)
				errors.add("full_name", new org.apache.struts.action.ActionError("error.field.required"));
			
			if ( email != null )
			{
			   email = email.trim();
			  // if (!email.isEmail(email))
				// errors.add("email", new org.apache.struts.action.ActionError("error.field.invalid.format"));
			}
			else
			  errors.add("email", new org.apache.struts.action.ActionError("error.field.required"));
					
	        
	    if(rfc != null)
	    {
	      rfc = rfc.trim();
	       if (rfc.length()==0)
	        errors.add("rfc", new org.apache.struts.action.ActionError("error.field.required"));
	    }
		}
	
			return errors;

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}



	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}


  

}
