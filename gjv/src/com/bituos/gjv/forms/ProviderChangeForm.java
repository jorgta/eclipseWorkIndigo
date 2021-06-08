package com.bituos.gjv.forms;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.bituos.Utils;

public class ProviderChangeForm extends ActionForm {

	private String idProvider;
	private String first_name;
	private String last_name;
	private String enterprise_name;
	private String address;
	private String city;	
	private String country;	
	private String office_phone;
	private String home_phone;
	private String cel_phone;	
	private String process = null;

	public String getProcess() {
		return process;
	}



	public void setProcess(String process) {
		this.process = process;
	}



	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.
		
	}

	

	public ActionErrors validate  ( ActionMapping mapping, HttpServletRequest request )   
	{
		ActionErrors errors = new ActionErrors();
        
	  if(process.equals("change"))
	  {
	
			if ( idProvider != null )
			  {
				idProvider = idProvider.trim().toUpperCase();
			    if (idProvider.length() == 0)
				    errors.add("idProvider", new org.apache.struts.action.ActionError("error.field.required"));			  
			  }
		    else
			  errors.add("first_name", new org.apache.struts.action.ActionError("error.field.required"));
			
			if ( first_name != null )
				{
				  first_name = first_name.trim().toUpperCase();
				  if (first_name.length() == 0)
					  errors.add("first_name", new org.apache.struts.action.ActionError("error.field.required"));			  
				}
			  else
				errors.add("first_name", new org.apache.struts.action.ActionError("error.field.required"));
			 
			 
			 if ( last_name != null )
				{
				 last_name = last_name.trim().toUpperCase();
				  if (last_name.length() == 0)
					  errors.add("last_name", new org.apache.struts.action.ActionError("error.field.required"));
				//  if (last_name.indexOf(" ") > 0)
					  //errors.add("last_name", new org.apache.struts.action.ActionError("error.field.required.no.space"));
				}
			  else
				errors.add("last_name", new org.apache.struts.action.ActionError("error.field.required"));
			 
			 
			 if ( enterprise_name != null )
				{
				 enterprise_name = enterprise_name.trim().toUpperCase();
				  if (enterprise_name.length() == 0)
					  errors.add("enterprise_name", new org.apache.struts.action.ActionError("error.field.required"));			  
				}
			  else
				errors.add("enterprise_name", new org.apache.struts.action.ActionError("error.field.required"));
			 
			 
			 
			 if ( address != null )
				{
				 address = address.trim().toUpperCase();
				  if (address.length() == 0)
					  errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));			  
				}
			  else
				errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));
			 
			 
			 if ( city != null )
				{
				 city = city.trim().toUpperCase();
				  if (city.length() == 0)
					  errors.add("city", new org.apache.struts.action.ActionError("error.field.required"));			  
				}
			  else
				errors.add("city", new org.apache.struts.action.ActionError("error.field.required"));
			 
			 if ( country != null )
				{
				 country = country.trim().toUpperCase();
				  if (country.length() == 0)
					  errors.add("country", new org.apache.struts.action.ActionError("error.field.required"));			  
				}
			  else
				errors.add("country", new org.apache.struts.action.ActionError("error.field.required"));
			 
			 
			 

			 if ( office_phone != null )
				{
				 office_phone = office_phone.trim().toUpperCase();
				  if (office_phone.length() == 0)
					  errors.add("office_phone", new org.apache.struts.action.ActionError("error.field.required"));			  
				}
			  else
				errors.add("office_phone", new org.apache.struts.action.ActionError("error.field.required"));
			
			 if ( home_phone != null )
				{
				 home_phone = home_phone.trim().toUpperCase();
				  if (home_phone.length() == 0)
					  errors.add("home_phone", new org.apache.struts.action.ActionError("error.field.required"));			  
				}
			  else
				errors.add("home_phone", new org.apache.struts.action.ActionError("error.field.required"));
			
				 
			 if ( cel_phone != null )
				{
				 cel_phone = cel_phone.trim().toUpperCase();
				  if (cel_phone.length() == 0)
					  errors.add("cel_phone", new org.apache.struts.action.ActionError("error.field.required"));			  
				}
			  else
				errors.add("cel_phone", new org.apache.struts.action.ActionError("error.field.required")); 
			 
			
			
	  }	      
	      return errors;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getCel_phone() {
		return cel_phone;
	}



	public void setCel_phone(String cel_phone) {
		this.cel_phone = cel_phone;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getEnterprise_name() {
		return enterprise_name;
	}



	public void setEnterprise_name(String enterprise_name) {
		this.enterprise_name = enterprise_name;
	}



	public String getFirst_name() {
		return first_name;
	}



	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}



	public String getHome_phone() {
		return home_phone;
	}



	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}



	


	public String getIdProvider() {
		return idProvider;
	}



	public void setIdProvider(String idProvider) {
		this.idProvider = idProvider;
	}



	public String getLast_name() {
		return last_name;
	}



	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}



	public String getOffice_phone() {
		return office_phone;
	}



	public void setOffice_phone(String office_phone) {
		this.office_phone = office_phone;
	}

		


	
	  



	}
