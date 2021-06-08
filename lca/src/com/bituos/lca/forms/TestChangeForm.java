package com.bituos.lca.forms;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.bituos.lca.*; 
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
public class TestChangeForm extends ActionForm {

	private String id_test = null;
	private String test_name = null;
	private String test_price = null;
	private String test_indications = null;
	private String test_materials_to_patient = null;

	private String id_test_parameter = null;
	private String test_parameter_name = null;
	private String test_parameter_measurement_unit = null;
	private String test_parameter_is_numeric = null;	

	private String current_id_test = null;
	private String current_id_test_parameter = null;
	private String test_parameter_is_free_text = null;	
	
	private String process = null;

	private String test_method = null;
	
	private String parameter_limit_description = null;	
	private String parameter_limit_from = null;	
	private String parameter_limit_to = null;	
	private String parameter_limit_show_in_report = null;
	
	private String id_parameter_limits= null;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		test_name = null;
		test_price = null;
		test_indications = null;
		test_materials_to_patient = null;


		id_test = null;
		id_test_parameter = null;
		test_parameter_name = null;

		current_id_test = null;
		current_id_test_parameter = null;

		test_method = null;
		
		parameter_limit_description = null;	
		parameter_limit_from = null;	
		parameter_limit_to= null;	
		test_parameter_is_numeric = null;	
		parameter_limit_show_in_report = null;
		
		id_parameter_limits  = null;
		
		process = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if ( test_parameter_is_numeric == null )
		  {
			test_parameter_is_numeric = "off";
		  }
		
		
		if ( process == null)
		  {
			errors.add("name", new org.apache.struts.action.ActionError("error.undefined.process"));
		  }
		else if ( process.equals( "testNew" ) )
		  {
			/*
			test_name = null;
			test_price = null;
			test_indications = null;
			test_materials_to_patient = null;
			*/

			if ( test_name != null )
			  {
				test_name = test_name.trim().toUpperCase();
				if (test_name.length() == 0)
					errors.add("test_name", new org.apache.struts.action.ActionError("error.field.required"));
			  }
			else
			  errors.add("test_name", new org.apache.struts.action.ActionError("error.field.required"));
			
			if ( test_price != null )
			  {
				test_price = test_price.trim().toUpperCase();
				if ( test_price.length() == 0 )
				  errors.add("test_price", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
				    try
				    {
				    	Double D = Double.valueOf(test_price);
				    	
				    	if (D < 0 )
					      errors.add("test_price", new org.apache.struts.action.ActionError("error.field.too.low"));
				    }
				    catch(Exception e)
				    {
				    	errors.add("test_price", new org.apache.struts.action.ActionError("error.field.invalid.format"));
				    	
				    }
				  }
			  }
			else
			  errors.add("test_price", new org.apache.struts.action.ActionError("error.field.required"));
			
			
			if ( test_indications != null )
			  {
				test_indications = test_indications.trim().toUpperCase();
				if (test_indications.length() == 0)
					errors.add("test_indications", new org.apache.struts.action.ActionError("error.field.required"));
			  }
			else
			  errors.add("test_indications", new org.apache.struts.action.ActionError("error.field.required"));
			
			
			if ( test_materials_to_patient != null )
			  {
				test_materials_to_patient = test_materials_to_patient.trim().toUpperCase();
				if (test_materials_to_patient.length() == 0)
					errors.add("test_materials_to_patient", new org.apache.struts.action.ActionError("error.field.required"));
			  }
			else
			  errors.add("test_materials_to_patient", new org.apache.struts.action.ActionError("error.field.required"));
			

			if ( test_method != null )
			  {
				test_method = test_method.trim().toUpperCase();
				if (test_method.length() == 0)
					errors.add("test_method", new org.apache.struts.action.ActionError("error.field.required"));
			  }
			else
			  errors.add("test_method", new org.apache.struts.action.ActionError("error.field.required"));
			

		  }
		else if ( process.equals( "link" ) )
		  {
			/*
			id_test = null;
			id_test_parameter = null;
			id_test_parameter = null;
			test_parameter_is_numeric = null;
			test_parameter_min = null;
			test_parameter_max = null;
			*/
			
			if ( id_test == null )
			  errors.add("id_test", new org.apache.struts.action.ActionError("error.field.required"));

			/*
			if ( test_parameter_name != null )
			  {
				test_parameter_name = test_parameter_name.trim().toUpperCase();

				if (test_parameter_name.length() == 0)
				  errors.add("test_parameter_name", new org.apache.struts.action.ActionError("error.field.required"));
			  }
			else 
			  errors.add("test_parameter_name", new org.apache.struts.action.ActionError("error.field.required"));
			*/
			
		  	
		  }
		else if ( process.equals( "ParameterLimitAdd" ) )
		  {
			if ( parameter_limit_description != null )
			  {
				parameter_limit_description = parameter_limit_description.trim().toUpperCase();

				if (parameter_limit_description.length() == 0)
				  errors.add("parameter_limit_description", new org.apache.struts.action.ActionError("error.field.required"));
			  }
			else 
			  errors.add("parameter_limit_description", new org.apache.struts.action.ActionError("error.field.required"));
			
			
		  	if ( test_parameter_is_numeric.equals("on") == true )
		  	  {
		  		Double min = 0.0, max = 0.0;
		  		
			    try
			      {
			    	min = Double.valueOf( parameter_limit_from );
			      }
			    catch(Exception e)
			      {
			    	errors.add("parameter_limit_from", new org.apache.struts.action.ActionError("error.field.invalid.format"));
			      }
				
			    try
			      {
			    	max = Double.valueOf( parameter_limit_to );
			      }
			    catch(Exception e)
			      {
			    	errors.add("parameter_limit_to", new org.apache.struts.action.ActionError("error.field.invalid.format"));
			      }
		  	  }
		  	else
		  	  {
				
		  		/*Double min = 0.0, max = 0.0;
		  		
			    try
			      {
			    	min = Double.valueOf( parameter_limit_from );
			      }
			    catch(Exception e)
			      {
			    	errors.add("parameter_limit_from", new org.apache.struts.action.ActionError("error.field.invalid.format"));
			      }
			    
			    try
			      {
			    	max = Double.valueOf( parameter_limit_to );
			      }
			    catch(Exception e)
			      {
			    	errors.add("parameter_limit_to", new org.apache.struts.action.ActionError("error.field.invalid.format"));
			      }*/
		  		
		  		if ( parameter_limit_from != null )
				  {
					parameter_limit_from = parameter_limit_from.trim().toUpperCase();

					if (parameter_limit_from.length() == 0)
					  errors.add("parameter_limit_from", new org.apache.struts.action.ActionError("error.field.required"));
				  }
				else 
				  errors.add("parameter_limit_from", new org.apache.struts.action.ActionError("error.field.required"));
		  		
				if ( parameter_limit_to != null )
				  {
					parameter_limit_to = parameter_limit_to.trim().toUpperCase();

					if (parameter_limit_to.length() == 0)
					  errors.add("parameter_limit_to", new org.apache.struts.action.ActionError("error.field.required"));
				  }
				else 
				  errors.add("parameter_limit_to", new org.apache.struts.action.ActionError("error.field.required"));
		  	  }
			
			if ( test_parameter_measurement_unit != null )
			  {
				test_parameter_measurement_unit = test_parameter_measurement_unit.trim().toUpperCase();

				if (test_parameter_measurement_unit.length() == 0)
				  errors.add("test_parameter_measurement_unit", new org.apache.struts.action.ActionError("error.field.required"));
			  }
			else 
			  errors.add("test_parameter_measurement_unit", new org.apache.struts.action.ActionError("error.field.required"));
		  	

			if ( parameter_limit_show_in_report == null )
			  {
				parameter_limit_show_in_report = "off";
			  }

		  }
		else if ( process == "selectParameter")
		{
		  
	    }
		
		
		
		
		
		
		
		
		
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.trim().length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }
		return errors;

	}
	
	

	public String getParameter_limit_show_in_report() {
		return parameter_limit_show_in_report;
	}

	public void setParameter_limit_show_in_report(
			String parameter_limit_show_in_report) {
		this.parameter_limit_show_in_report = parameter_limit_show_in_report;
	}

	public String getParameter_limit_description() {
		return parameter_limit_description;
	}

	public void setParameter_limit_description(String parameter_limit_description) {
		this.parameter_limit_description = parameter_limit_description;
	}

	public String getParameter_limit_from() {
		return parameter_limit_from;
	}

	public void setParameter_limit_from(String parameter_limit_from) {
		this.parameter_limit_from = parameter_limit_from;
	}

	public String getParameter_limit_to() {
		return parameter_limit_to;
	}

	public void setParameter_limit_to(String parameter_limit_to) {
		this.parameter_limit_to = parameter_limit_to;
	}

	

	public String getTest_method() {
		return test_method;
	}

	public void setTest_method(String test_method) {
		this.test_method = test_method;
	}

	public String getId_test() {
		return id_test;
	}

	public void setId_test(String id_test) {
		this.id_test = id_test;
	}

	public String getId_test_parameter() {
		return id_test_parameter;
	}

	public void setId_test_parameter(String id_test_parameter) {
		this.id_test_parameter = id_test_parameter;
	}

	

	public String getCurrent_id_test() {
		return current_id_test;
	}

	public void setCurrent_id_test(String current_id_test) {
		this.current_id_test = current_id_test;
	}

	public String getCurrent_id_test_parameter() {
		return current_id_test_parameter;
	}

	public void setCurrent_id_test_parameter(String current_id_test_parameter) {
		this.current_id_test_parameter = current_id_test_parameter;
	}

	public String getTest_name() {
		return test_name;
	}

	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}

	public String getTest_price() {
		return test_price;
	}

	public void setTest_price(String test_price) {
		this.test_price = test_price;
	}

	public String getTest_indications() {
		return test_indications;
	}

	public void setTest_indications(String test_indications) {
		this.test_indications = test_indications;
	}

	public String getTest_materials_to_patient() {
		return test_materials_to_patient;
	}

	public void setTest_materials_to_patient(String test_materials_to_patient) {
		this.test_materials_to_patient = test_materials_to_patient;
	}

	public String getTest_parameter_name() {
		return test_parameter_name;
	}

	public void setTest_parameter_name(String test_parameter_name) {
		this.test_parameter_name = test_parameter_name;
	}


	
	
	
	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getTest_parameter_measurement_unit() {
		return test_parameter_measurement_unit;
	}

	public void setTest_parameter_measurement_unit(
			String test_parameter_measurement_unit) {
		this.test_parameter_measurement_unit = test_parameter_measurement_unit;
	}

	public String getTest_parameter_is_numeric() {
		return test_parameter_is_numeric;
	}

	public void setTest_parameter_is_numeric(String test_parameter_is_numeric) {
		this.test_parameter_is_numeric = test_parameter_is_numeric;
	}

	public String getId_parameter_limits() {
		return id_parameter_limits;
	}

	public void setId_parameter_limits(String id_parameter_limits) {
		this.id_parameter_limits = id_parameter_limits;
	}

	public String getTest_parameter_is_free_text() {
		return test_parameter_is_free_text;
	}

	public void setTest_parameter_is_free_text(String test_parameter_is_free_text) {
		this.test_parameter_is_free_text = test_parameter_is_free_text;
	}


	

}
