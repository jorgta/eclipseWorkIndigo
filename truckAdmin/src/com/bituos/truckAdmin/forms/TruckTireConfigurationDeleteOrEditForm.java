package com.bituos.truckAdmin.forms;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.bituos.truckAdmin.*; 
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
public class TruckTireConfigurationDeleteOrEditForm extends ActionForm {

	private int id_truck_tire_configuration;
	private String description;
	private int tire_count;
	private String image;
	private String process;
	 

	
	
	public ActionErrors validate(
			ActionMapping mapping,
			HttpServletRequest request) {

			ActionErrors errors = new ActionErrors();
			
			if(process.equals("edit") )
			{			
				/*if ( description != null )
				  {
					description = description.trim().toUpperCase();
					if ( description.length() == 0 )
					  errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  if (description.indexOf(" ") > 0)
						errors.add("description", new org.apache.struts.action.ActionError("error.field.required.no.space"));
				  }
				else
				  errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
			*/
			
			}
			
			

			
			return errors;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}



	

	public int getId_truck_tire_configuration() {
		return id_truck_tire_configuration;
	}

	public void setId_truck_tire_configuration(int id_truck_tire_configuration) {
		this.id_truck_tire_configuration = id_truck_tire_configuration;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public int getTire_count() {
		return tire_count;
	}

	public void setTire_count(int tire_count) {
		this.tire_count = tire_count;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}






}
