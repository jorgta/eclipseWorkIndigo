package com.bituos.gjv.forms;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.bituos.Utils;

public class ContactChangeForm extends ActionForm {

	private String idContact;
	private String first_name;
	private String last_name;
	private String address;
	private String colony;	//null
	private String phone;
	private String cel_phone;
	private String active;
	private String married;
	private String yesmarried;
	private String nomarried;

	private String NSS;	//null
	private String wife_name;
	private String date_of_birth;
	private String starting_date;
	private String ending_date;
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
	
		if ( idContact != null )
		  {
			idContact = idContact.trim().toUpperCase();
		    if (idContact.length() == 0)
			    errors.add("id", new org.apache.struts.action.ActionError("error.field.required"));			  
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
		 
		 if ( address != null )
			{
			 address = address.trim().toUpperCase();
			  if (address.length() == 0)
				  errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));			  
			}
		  else
			errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));

		 if ( phone != null )
			{
			 phone = phone.trim().toUpperCase();
			  if (phone.length() == 0)
				  errors.add("phone", new org.apache.struts.action.ActionError("error.field.required"));			  
			}
		  else
			errors.add("phone", new org.apache.struts.action.ActionError("error.field.required"));
		
			 
		 if ( cel_phone != null )
			{
			 cel_phone = cel_phone.trim().toUpperCase();
			  if (cel_phone.length() == 0)
				  errors.add("cel_phone", new org.apache.struts.action.ActionError("error.field.required"));			  
			}
		  else
			errors.add("cel_phone", new org.apache.struts.action.ActionError("error.field.required")); 
		 
		
		 if ( married != null )
			{
			 married = married.trim();
			  if (married.length() == 0)
				  errors.add("married", new org.apache.struts.action.ActionError("error.field.required"));		
			  else if (married.equals("true"))
					{					
					  wife_name = wife_name.trim().toUpperCase();
				      if (wife_name.trim().length() == 0)
					    errors.add("wife_name", new org.apache.struts.action.ActionError("error.field.required"));
						
					}
			}
		  else
			errors.add("married", new org.apache.struts.action.ActionError("error.field.required"));
		 
	
		
		 if ( date_of_birth != null )
			{
			 date_of_birth = date_of_birth.trim().toUpperCase();
			  if (date_of_birth.length() == 0)
				  errors.add("date_of_birth", new org.apache.struts.action.ActionError("error.field.required"));
			  else if(!Utils.IsDate(date_of_birth))
						errors.add("date_of_birth", new org.apache.struts.action.ActionError("error.field.invalid.date"));	
			}
		  else
			errors.add("date_of_birth", new org.apache.struts.action.ActionError("error.field.required")); 
		 
		 
		 if ( starting_date != null )
			{
			 starting_date = starting_date.trim().toUpperCase();
			  if (starting_date.length() == 0)
				  errors.add("starting_date", new org.apache.struts.action.ActionError("error.field.required"));
			  else if(!Utils.IsDate(starting_date))
						errors.add("starting_date", new org.apache.struts.action.ActionError("error.field.invalid.date"));	
			}
		  else
			errors.add("starting_date", new org.apache.struts.action.ActionError("error.field.required")); 
		 
	
		 if ( ending_date != null )
			{
			 ending_date = ending_date.trim().toUpperCase();
			  if (ending_date.length() == 0)
				  errors.add("ending_date", new org.apache.struts.action.ActionError("error.field.required"));
			  else if(!Utils.IsDate(ending_date))
						errors.add("ending_date", new org.apache.struts.action.ActionError("error.field.invalid.date"));	
			}
		  else
			errors.add("ending_date", new org.apache.struts.action.ActionError("error.field.required")); 
	
			
	  }	      
	      return errors;
	}

		public String getActive() {
			return active;
		}

		public void setActive(String active) {
			this.active = active;
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

		public String getColony() {
			return colony;
		}

		public void setColony(String colony) {
			this.colony = colony;
		}

		public String getDate_of_birth() {
			return date_of_birth;
		}

		public void setDate_of_birth(String date_of_birth) {
			this.date_of_birth = date_of_birth;
		}

		public String getFirst_name() {
			return first_name;
		}

		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}

		public String getLast_name() {
			return last_name;
		}

		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}

		public String getMarried() {
			return married;
		}

		public void setMarried(String married) {
			this.married = married;
		}

		public String getNSS() {
			return NSS;
		}

		public void setNSS(String nss) {
			NSS = nss;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getWife_name() {
			return wife_name;
		}

		public void setWife_name(String wife_name) {
			this.wife_name = wife_name;
		}

		public String getEnding_date() {
			return ending_date;
		}

		public void setEnding_date(String ending_date) {
			this.ending_date = ending_date;
		}

		public String getStarting_date() {
			return starting_date;
		}

		public void setStarting_date(String starting_date) {
			this.starting_date = starting_date;
		}



		public String getIdContact() {
			return idContact;
		}



		public void setIdContact(String idContact) {
			this.idContact = idContact;
		}



		public String getNomarried() {
			return nomarried;
		}



		public void setNomarried(String nomarried) {
			this.nomarried = nomarried;
		}



		public String getYesmarried() {
			return yesmarried;
		}



		public void setYesmarried(String yesmarried) {
			this.yesmarried = yesmarried;
		}




	
	  



	}
