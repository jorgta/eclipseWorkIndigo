package com.bituos.gjv.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class UserNewForm extends ActionForm 
{

	private String name;
	private String full_name;
	private String password;
	private String confirmpassword;
	private String active;
	private String address;
    private String rfc;
	private String email;

	/*private String street;
	private String number;
	private String colony;
	private String city;
	private String state;
	private String zip_code;
	private String id_country;*/

	private String phone_home = null;
	private String phone_work = null;
	private String phone_mobile = null;

	public void reset(ActionMapping mapping, HttpServletRequest request) 
  {
		
	}

	public ActionErrors validate  ( ActionMapping mapping, HttpServletRequest request )   
  {
		ActionErrors errors = new ActionErrors();


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
      
      return errors;
    }
  
  
    public void setName(String name)
    {
      this.name = name;
    }
  
  
    public String getName()
    {
      return name;
    }
  
  
    public void setFull_name(String full_name)
    {
      this.full_name = full_name;
    }
  
  
    public String getFull_name()
    {
      return full_name;
    }
  
  
    public void setPassword(String password)
    {
      this.password = password;
    }
  
  
    public String getPassword()
    {
      return password;
    }
  
  
    public void setActive(String active)
    {
      this.active = active;
    }
  
  
    public String getActive()
    {
      return active;
    }
  
  
    public void setAddress(String address)
    {
      this.address = address;
    }
  
  
    public String getAddress()
    {
      return address;
    }
  
  
    public void setRfc(String rfc)
    {
      this.rfc = rfc;
    }
  
  
    public String getRfc()
    {
      return rfc;
    }
  
  
    public void setEmail(String email)
    {
      this.email = email;
    }
  
  
    public String getEmail()
    {
      return email;
    }

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getPhone_home() {
		return phone_home;
	}

	public void setPhone_home(String phone_home) {
		this.phone_home = phone_home;
	}

	public String getPhone_mobile() {
		return phone_mobile;
	}

	public void setPhone_mobile(String phone_mobile) {
		this.phone_mobile = phone_mobile;
	}

	public String getPhone_work() {
		return phone_work;
	}

	public void setPhone_work(String phone_work) {
		this.phone_work = phone_work;
	}



}
