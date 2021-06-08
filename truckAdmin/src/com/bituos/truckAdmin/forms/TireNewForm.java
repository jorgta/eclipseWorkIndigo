package com.bituos.truckAdmin.forms;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.bituos.truckAdmin.*; 
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
public class TireNewForm extends ActionForm {

	private String id;
	private String design;
	private String deep;
	private String id_type_tire_status;
	private String id_truck;
	//private String position;
	private String serial_number;
	private String id_place;
	private String id_type_measure;
	private String id_type_unit_of_measure;
	private int km_int;
	
	
	private String process;
	
	public ActionErrors validate(
			ActionMapping mapping,
			HttpServletRequest request) {

			ActionErrors errors = new ActionErrors();
			
			
			if( process.equals("add") )
			{
				/*if ( position != null )
				  {
					position = position.trim().toUpperCase();
					if ( position.length() == 0 )
					  errors.add("position", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  if (position.indexOf(" ") > 0)
						errors.add("position", new org.apache.struts.action.ActionError("error.field.required.no.space"));
				  }
				else
				  errors.add("position", new org.apache.struts.action.ActionError("error.field.required"));
				
				*/
				
				if ( id_type_tire_status != null )
				  {
					id_type_tire_status = id_type_tire_status.trim().toUpperCase();					
					 if ( id_type_tire_status.equals("2") && km_int > 0)
						errors.add("km_int", new org.apache.struts.action.ActionError("error.field.tire.id_type_tire_statusNew"));
					 
					 
					 if ( id_type_tire_status.equals("1") && km_int <= 0)
						 errors.add("km_int", new org.apache.struts.action.ActionError("error.field.tire.id_type_tire_statusUsed"));
					 
					 
				  }
				else
				  errors.add("id_type_tire_status", new org.apache.struts.action.ActionError("error.field.required"));
				
				
				if ( serial_number != null )
				  {
					serial_number = serial_number.trim().toUpperCase();
					if ( serial_number.length() == 0 )
					  errors.add("serial_number", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  if (serial_number.indexOf(" ") > 0)
						errors.add("serial_number", new org.apache.struts.action.ActionError("error.field.required.no.space"));
				  }
				else
				  errors.add("serial_number", new org.apache.struts.action.ActionError("error.field.required"));
				
				
				
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
					  try
						  {
							double f = new Float( deep ).floatValue();
							
							if ( f < 0)
							  errors.add("deep", new org.apache.struts.action.ActionError("error.field.too.low"));
							if ( f > 30)
							  errors.add("deep", new org.apache.struts.action.ActionError("error.field.too.large"));
						  }
						catch( Exception E)
						  {
							errors.add("deep", new org.apache.struts.action.ActionError("error.field.invalid.format"));
						  }
				  }
				else
				  errors.add("deep", new org.apache.struts.action.ActionError("error.field.required"));
				
				
			/*	HttpSession ses = request.getSession();
				BeanTruck beanTruck = (BeanTruck) ses.getAttribute( "selectedTruck" );
				
				if(beanTruck == null)
				{
					 errors.add("id_truck", new org.apache.struts.action.ActionError("error.field.required"));
						
				}*/
				
			}
	
			
			return errors;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getId_type_tire_status() {
		return id_type_tire_status;
	}
	public void setId_type_tire_status(String id_type_tire_status) {
		this.id_type_tire_status = id_type_tire_status;
	}
	public String getId_truck() {
		return id_truck;
	}
	public void setId_truck(String id_truck) {
		this.id_truck = id_truck;
	}
	/*public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}*/
	public String getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}
	public String getId_place() {
		return id_place;
	}
	public void setId_place(String id_place) {
		this.id_place = id_place;
	}
	public String getId_type_measure() {
		return id_type_measure;
	}
	public void setId_type_measure(String id_type_measure) {
		this.id_type_measure = id_type_measure;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}


	public String getId_type_unit_of_measure() {
		return id_type_unit_of_measure;
	}


	public void setId_type_unit_of_measure(String id_type_unit_of_measure) {
		this.id_type_unit_of_measure = id_type_unit_of_measure;
	}


	public int getKm_int() {
		return km_int;
	}


	public void setKm_int(int km_int) {
		this.km_int = km_int;
	}







}
