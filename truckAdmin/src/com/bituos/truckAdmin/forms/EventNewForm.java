package com.bituos.truckAdmin.forms;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.bituos.truckAdmin.*; 
import com.bituos.truckAdmin.Beans.BeanTire;
import com.bituos.truckAdmin.Beans.BeanTruck;
import com.bituos.truckAdmin.Beans.BeanTypeMeasure;
import com.bituos.truckAdmin.Beans.BeanTypePlace;
import com.bituos.truckAdmin.Beans.BeanTypeTireStatus;
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
public class EventNewForm extends ActionForm {


	private String id;
	private String id_tire;
	private String odometer;
	private String id_type_place;
	private String date_reg;
	private String comments;
	private String design;
	private String deep;
	private String id_type_event;

	
	private String process;
	

	public ActionErrors validate(
			ActionMapping mapping,
			HttpServletRequest request) {

			ActionErrors errors = new ActionErrors();
			
			if ( odometer != null )
			  {
				odometer = odometer.trim().toUpperCase();
				if ( odometer.length() == 0 )
				  errors.add("odometer", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  if (odometer.indexOf(" ") > 0)
					errors.add("odometer", new org.apache.struts.action.ActionError("error.field.required.no.space"));
			  }
			else
			  errors.add("odometer", new org.apache.struts.action.ActionError("error.field.required"));
			
			
			
			if ( comments != null )
			  {
				comments = comments.trim().toUpperCase();
				if ( comments.length() == 0 )
				  errors.add("comments", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  if (comments.indexOf(" ") > 0)
					errors.add("comments", new org.apache.struts.action.ActionError("error.field.required.no.space"));
			  }
			else
			  errors.add("comments", new org.apache.struts.action.ActionError("error.field.required"));
			
			
			
			if ( design != null )
			  {
				design = design.trim().toUpperCase();
				if ( design.length() == 0 )
				  errors.add("design", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  if (design.indexOf(" ") > 0)
					errors.add("design", new org.apache.struts.action.ActionError("error.field.required.no.space"));
			  }
			else
			  errors.add("design", new org.apache.struts.action.ActionError("error.field.required"));
			
			
			
			if ( deep != null )
			  {
				deep = deep.trim().toUpperCase();
				if ( deep.length() == 0 )
				  errors.add("deep", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  if (deep.indexOf(" ") > 0)
					errors.add("deep", new org.apache.struts.action.ActionError("error.field.required.no.space"));
			  }
			else
			  errors.add("deep", new org.apache.struts.action.ActionError("error.field.required"));
			
	
			
			return errors;
	}

	
	public String getId_tire() {
		return id_tire;
	}
	public void setId_tire(String id_tire) {
		this.id_tire = id_tire;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOdometer() {
		return odometer;
	}
	public void setOdometer(String odometer) {
		this.odometer = odometer;
	}
	public String getId_type_place() {
		return id_type_place;
	}
	public void setId_type_place(String id_type_place) {
		this.id_type_place = id_type_place;
	}
	public String getDate_reg() {
		return date_reg;
	}
	public void setDate_reg(String date_reg) {
		this.date_reg = date_reg;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDesign() {
		return design;
	}
	public void setDesign(String design) {
		this.design = design;
	}
	public String getDeep() {
		return deep;
	}
	public void setDeep(String deep) {
		this.deep = deep;
	}


	public String getId_type_event() {
		return id_type_event;
	}


	public void setId_type_event(String id_type_event) {
		this.id_type_event = id_type_event;
	}



	
	


}
