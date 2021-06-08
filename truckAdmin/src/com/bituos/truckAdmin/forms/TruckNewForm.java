package com.bituos.truckAdmin.forms;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.bituos.truckAdmin.*; 
import com.bituos.truckAdmin.Beans.BeanTruckTireConfiguration;
import com.bituos.*;


/**
 * Form bean for a Struts application.
 * Users may access 4 fields on this form:
 * <ul>
 * <li>rfc - [your comment here]
 * <li>address - [your comment here]
 * <li>password - [your comment here]
 * <li>name - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class TruckNewForm extends ActionForm {

	private String description = null;
	private String registration = null;
	private String model = null;
	private String date_reg = null;
	private String process = null;
	private String id_truck_tire_configuration= null;
	
	
	public ActionErrors validate(
			ActionMapping mapping,
			HttpServletRequest request) {

			ActionErrors errors = new ActionErrors();
			
			if(process.equals("save"))
			{
			
				if ( description != null )
				  {
					description = description.trim().toUpperCase();
					if ( description.length() == 0 )
					  errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
					/*else
					  if (description.indexOf(" ") > 0)
						errors.add("description", new org.apache.struts.action.ActionError("error.field.required.no.space"));*/
				  }
				else
				  errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
				
				
				
				if ( registration != null )
				  {
					registration = registration.trim().toUpperCase();
					if ( registration.length() == 0 )
					  errors.add("registration", new org.apache.struts.action.ActionError("error.field.required"));
					/*else
					  if (description.indexOf(" ") > 0)
						errors.add("registration", new org.apache.struts.action.ActionError("error.field.required.no.space"));*/
				  }
				else
				  errors.add("registration", new org.apache.struts.action.ActionError("error.field.required"));
				
				
				if ( model != null )
				  {
					model = model.trim().toUpperCase();
					if ( model.length() == 0 )
					  errors.add("model", new org.apache.struts.action.ActionError("error.field.required"));
					else
						try
						  {
							int i = new Integer( model ).intValue();
							
							if ( i < 1960)
							  errors.add("model", new org.apache.struts.action.ActionError("error.field.too.low"));
							if ( i > 2020)
							  errors.add("model", new org.apache.struts.action.ActionError("error.field.too.large"));
						  }
						catch( Exception E)
						  {
							errors.add("model", new org.apache.struts.action.ActionError("error.field.invalid.format"));
						  }
				  }
				else
				  errors.add("model", new org.apache.struts.action.ActionError("error.field.required"));
				
				HttpSession ses = request.getSession();
				 BeanTruckTireConfiguration beanTruckTireConfiguration = (BeanTruckTireConfiguration) ses.getAttribute( "beanTruckTireConfiguration" );
				    
				if(beanTruckTireConfiguration == null)
				{
					errors.add("id_truck_tire_configuration", new org.apache.struts.action.ActionError("error.field.truck.configuration"));
				}
				
				
			}
		  else if(process.equals("select"))
			{
				
			}
			
			return errors;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDate_reg() {
		return date_reg;
	}
	public void setDate_reg(String date_reg) {
		this.date_reg = date_reg;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getId_truck_tire_configuration() {
		return id_truck_tire_configuration;
	}

	public void setId_truck_tire_configuration(String id_truck_tire_configuration) {
		this.id_truck_tire_configuration = id_truck_tire_configuration;
	}




}
