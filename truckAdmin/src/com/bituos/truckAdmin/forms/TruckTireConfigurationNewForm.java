package com.bituos.truckAdmin.forms;
import java.util.ArrayList;
import java.util.List;

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
public class TruckTireConfigurationNewForm extends ActionForm {

	private String id_truck_tire;
	private String description;
	private String tire_count;
	private String axis_count;
	private String[] axis_type;
	private String[] tire_for_axis;// = new String[2];
	private String axis_side;
	
	private String process;
	 

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.
		HttpSession ses = request.getSession();
		/*List truckTireConfigurationDetailList = (List) ses.getAttribute( "truckTireConfigurationDetailList" );
		String axisSize =String.valueOf( truckTireConfigurationDetailList.size() );
		tire_axis_position = new String[Integer.valueOf( axisSize)];*/

		int axis_number = (Integer) ses.getAttribute( "axis_number" );
		 BeanTruckTireConfiguration beanTruckTireConfiguration = (BeanTruckTireConfiguration) ses.getAttribute( "beanTruckTireConfiguration" );
	     if(beanTruckTireConfiguration != null)
	     {
	    	 axis_number= beanTruckTireConfiguration.getAxis_count();
	    	 tire_for_axis = new String[Integer.valueOf( axis_number)];
	    	 axis_type  = new String[Integer.valueOf( axis_number)];
	     }
	    
	}
	
	public ActionErrors validate(
			ActionMapping mapping,
			HttpServletRequest request) {

			ActionErrors errors = new ActionErrors();
			
			if(process.equals("add"))
			{
			
				if ( description != null )
				  {
					description = description.trim().toUpperCase();
					if ( description.length() == 0 )
					  errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
					/*else
					  if (description.indexOf(" ") > 0)
						errors.add("description", new org.apache.struts.action.ActionError("error.field.required.no.space"));
					*/
				  }
				else
				  errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
				
				
				  if ( axis_count != null )
				  {
					  
						axis_count = axis_count.trim().toUpperCase();
						if ( axis_count.length() == 0 )
						  errors.add("axis_count", new org.apache.struts.action.ActionError("error.field.required"));
						else
						  if (axis_count.indexOf(" ") > 0)
							errors.add("axis_count", new org.apache.struts.action.ActionError("error.field.required.no.space"));
						  else
						  {
							  try{
							       int  result = Integer.valueOf( axis_count);
							       if ( Integer.valueOf( axis_count) <= 0)
								    {
									  errors.add("axis_count", new org.apache.struts.action.ActionError("error.field.integer"));
								    }  
							        
							    	   
							    	   
							     }catch(NumberFormatException the_input_string_isnt_an_integer){
							    	 errors.add("axis_count", new org.apache.struts.action.ActionError("error.field.integer3"));
										
							     }
						  }
					
					
						
						
				  }
				else
				  errors.add("axis_count", new org.apache.struts.action.ActionError("error.field.required"));
				
			}
		  else if(process.equals("addTirePosition"))
		   {
			  
			  
			  if ( description != null )
			  {
				description = description.trim().toUpperCase();
				if ( description.length() == 0 )
				  errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
				/*else
				  if (description.indexOf(" ") > 0)
					errors.add("description", new org.apache.struts.action.ActionError("error.field.required.no.space"));
					*/
			  }
			else
			  errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
	
			  
			  int axisTypeCountFront=0;
			  int axisTypeCountBottom=0;
			  if ( axis_count != null )
			  {
				axis_count = axis_count.trim().toUpperCase();
				if ( axis_count.length() == 0 )
				  errors.add("axis_count", new org.apache.struts.action.ActionError("error.field.required"));
				else if (axis_count.indexOf(" ") > 0)
					  errors.add("axis_count", new org.apache.struts.action.ActionError("error.field.required.no.space"));
				else if (Integer.valueOf(axis_count) == 0)
					 errors.add("axis_count", new org.apache.struts.action.ActionError("error.field.integer"));
					
				
				
				for(int i=0; i < Integer.valueOf( axis_count ); i++)
				  {
					  if(axis_type[i].equals("Y"))
						  axisTypeCountFront++;
					  else
						  axisTypeCountBottom++;
						  
				  }
				  
				  
				 if(axisTypeCountFront > 0 && axisTypeCountBottom < 1)
					  errors.add("axis_count", new org.apache.struts.action.ActionError("error.field.axisFrontOnly"));
				 else if(axisTypeCountFront > axisTypeCountBottom)
					  errors.add("axis_count", new org.apache.struts.action.ActionError("error.field.axisFrontHigher"));
						  /*else					
					    if ( Integer.valueOf( axis_count) < 2)
						  errors.add("axis_count", new org.apache.struts.action.ActionError("error.field.integer"));*/
			
				
				
			  }
			else
			  errors.add("axis_count", new org.apache.struts.action.ActionError("error.field.required"));

			  
		   }


			
			return errors;
	}

	public String getId_truck_tire() {
		return id_truck_tire;
	}

	public void setId_truck_tire(String id_truck_tire) {
		this.id_truck_tire = id_truck_tire;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTire_count() {
		return tire_count;
	}

	public void setTire_count(String tire_count) {
		this.tire_count = tire_count;
	}

	public String getAxis_count() {
		return axis_count;
	}

	public void setAxis_count(String axis_count) {
		this.axis_count = axis_count;
	}


    
	public String getTire_for_axis(int index) {
		 return tire_for_axis[index];
	}

	public void setTire_for_axis(int index, String value) {
		tire_for_axis[index] = value;
	}

        



	public String getAxis_side() {
		return axis_side;
	}

	public void setAxis_side(String axis_side) {
		this.axis_side = axis_side;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}


	public String  getAxis_type(int index) {
		return axis_type[index];
	}

	public void setAxis_type(int index, String value) {
		this.axis_type[index] = value;
	}


	





}
