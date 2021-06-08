package com.bituos.truckAdmin.forms;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
public class TireMovementsForm extends ActionForm {

	private String id;
	private String design;
	private String deep;
	private String id_type_tire_status;
	private String id_truck;
    private String id_truck_description;
	private String id_truck_registration;
	private String id_truck_model;
	private String id_truck_configuration_description;
	private String id_truck_tire_configuration_detail;
	private String measurement;
	private String serial_number;
	private String id_place;
	private String id_type_place;
	
	private String id_type_unit_of_measure;
	private String id_type_unit_of_measure_description;
	private String km_int;
	
	private String truckHTML;

	private int  id_tire; 
	private Date date_reg; 
	private String id_type_measure;
	private String km_truck; 
	private String price; 
    private String  buy_of_reference;
	

	
	private String process;
	
	public ActionErrors validate(
			ActionMapping mapping,
			HttpServletRequest request) {

			ActionErrors errors = new ActionErrors();
			
			HttpSession ses = request.getSession();
			if( process.equals("add") )
			{

				
				
				
				/*if ( id_type_place != null )
				  {
					BeanTruck beanTruck = null; 
					if ( id_type_place.equals("3") || id_type_place.equals("4") )
					{
					    beanTruck =(BeanTruck)ses.getAttribute("beanTruck");
						if(beanTruck != null)
						{
							
						}	
					}
					
				  }*/
				
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
				
				
				
				
				if ( km_int != null )
				  {
					km_int = km_int.trim().toUpperCase();
					if ( km_int.length() == 0 )
					  errors.add("km_int", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  if (km_int.indexOf(" ") > 0)
						errors.add("km_int", new org.apache.struts.action.ActionError("error.field.required.no.space"));
				  }
				else
				  errors.add("km_int", new org.apache.struts.action.ActionError("error.field.required"));
				
				
				
				
				if ( km_truck != null )
				  {
					km_truck = km_truck.trim().toUpperCase();
					if ( km_truck.length() == 0 )
					  errors.add("km_truck", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  if (km_truck.indexOf(" ") > 0)
						errors.add("km_truck", new org.apache.struts.action.ActionError("error.field.required.no.space"));
				  }
				else
				  errors.add("km_truck", new org.apache.struts.action.ActionError("error.field.required"));
				
				
				if ( price != null )
				  {
					price = price.trim().toUpperCase();
					if ( price.length() == 0 )
					  errors.add("price", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  if (price.indexOf(" ") > 0)
						errors.add("price", new org.apache.struts.action.ActionError("error.field.required.no.space"));
				  }
				else
				  errors.add("price", new org.apache.struts.action.ActionError("error.field.required"));
				
				
	
				
			/*	HttpSession ses = request.getSession();
				BeanTruck beanTruck = (BeanTruck) ses.getAttribute( "selectedTruck" );
				
				if(beanTruck == null)
				{
					 errors.add("id_truck", new org.apache.struts.action.ActionError("error.field.required"));
						
				}*/
				
			}else if( process.equals("select") )
			{
			  
			}else if( process.equals("selectPlace") )
			{
			  
			}else if( process.equals("selectVehicle"))
			{
			  
			}else if( process.equals("selectPosition"))
			{
			  
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






	public int getId_tire() {
		return id_tire;
	}


	public void setId_tire(int id_tire) {
		this.id_tire = id_tire;
	}


	public Date getDate_reg() {
		return date_reg;
	}


	public void setDate_reg(Date date_reg) {
		this.date_reg = date_reg;
	}


 

 




 

	public String getKm_int() {
		return km_int;
	}


	public void setKm_int(String km_int) {
		this.km_int = km_int;
	}


	public String getKm_truck() {
		return km_truck;
	}


	public void setKm_truck(String km_truck) {
		this.km_truck = km_truck;
	}


 




	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getBuy_of_reference() {
		return buy_of_reference;
	}


	public void setBuy_of_reference(String buy_of_reference) {
		this.buy_of_reference = buy_of_reference;
	}


	public String getId_type_unit_of_measure_description() {
		return id_type_unit_of_measure_description;
	}


	public void setId_type_unit_of_measure_description(
			String id_type_unit_of_measure_description) {
		this.id_type_unit_of_measure_description = id_type_unit_of_measure_description;
	}


	public String getId_truck_configuration_description() {
		return id_truck_configuration_description;
	}


	public void setId_truck_configuration_description(
			String id_truck_configuration_description) {
		this.id_truck_configuration_description = id_truck_configuration_description;
	}


	public String getId_truck_description() {
		return id_truck_description;
	}


	public void setId_truck_description(String id_truck_description) {
		this.id_truck_description = id_truck_description;
	}


	public String getId_truck_registration() {
		return id_truck_registration;
	}


	public void setId_truck_registration(String id_truck_registration) {
		this.id_truck_registration = id_truck_registration;
	}


	public String getId_truck_model() {
		return id_truck_model;
	}


	public void setId_truck_model(String id_truck_model) {
		this.id_truck_model = id_truck_model;
	}


	public String getTruckHTML() {
		return truckHTML;
	}


	public void setTruckHTML(String truckHTML) {
		this.truckHTML = truckHTML;
	}


	public String getId_truck_tire_configuration_detail() {
		return id_truck_tire_configuration_detail;
	}


	public void setId_truck_tire_configuration_detail(
			String id_truck_tire_configuration_detail) {
		this.id_truck_tire_configuration_detail = id_truck_tire_configuration_detail;
	}


	public String getMeasurement() {
		return measurement;
	}


	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}


	public String getId_type_place() {
		return id_type_place;
	}


	public void setId_type_place(String id_type_place) {
		this.id_type_place = id_type_place;
	}




}
