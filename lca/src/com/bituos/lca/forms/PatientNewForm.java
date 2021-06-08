package com.bituos.lca.forms;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.bituos.lca.*; 
import com.bituos.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Form bean for a Struts application.
 * Users may access 4 fields on this form:
 * <ul>
 * <li>address - [your comment here]
 * <li>notes - [your comment here]
 * <li>name - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class PatientNewForm extends ActionForm {

	private String id = null;
	private String name = null;
	private String street = null;
	private String external_number = null;
	private String city = null;
	private String notes = null;
	private String ship_street = null;
	private String ship_external_number = null;
	private String ship_city = null;
	private String email = null;
	private String discount = null;
	private String process = null;
	
	private String invoice_street;
	private String invoice_external_number;
	private String invoice_city;

	private String age;
	private String phone;
	
	private String is_male = null;	
	
 
	
	
	
	

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIs_male() {
		return is_male;
	}

	public void setIs_male(String is_male) {
		this.is_male = is_male;
	}

	public String getInvoice_street() {
		return invoice_street;
	}

	public void setInvoice_street(String invoice_street) {
		this.invoice_street = invoice_street;
	}

	public String getInvoice_external_number() {
		return invoice_external_number;
	}

	public void setInvoice_external_number(String invoice_external_number) {
		this.invoice_external_number = invoice_external_number;
	}

	public String getInvoice_city() {
		return invoice_city;
	}

	public void setInvoice_city(String invoice_city) {
		this.invoice_city = invoice_city;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		name = null;
		notes = null;
	


	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		
		if ( name != null )
		  {
			name = name.trim().toUpperCase();
			if ( name.length() == 0 )
			  errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
		
		
		
		if ( street != null )
		  {
			street = street.trim().toUpperCase();
			if ( street.length() == 0 )
			  errors.add("street", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("street", new org.apache.struts.action.ActionError("error.field.required"));

		if ( external_number != null )
		  {
			external_number = external_number.trim().toUpperCase();
			if ( external_number.length() == 0 )
			  errors.add("external_number", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("external_number", new org.apache.struts.action.ActionError("error.field.required"));

		
		
		if ( city != null )
		  {
			city = city.trim().toUpperCase();
			if ( city.length() == 0 )
			  errors.add("city", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("city", new org.apache.struts.action.ActionError("error.field.required"));

		
		if (notes != null)
		  notes = notes.trim();

		if ( ship_street != null )
		  {
			ship_street = ship_street.trim().toUpperCase();
			if ( ship_street.length() == 0 )
			  errors.add("ship_street", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("ship_street", new org.apache.struts.action.ActionError("error.field.required"));

		
		if ( ship_external_number != null )
		  {
			ship_external_number = ship_external_number.trim().toUpperCase();
			if ( ship_external_number.length() == 0 )
			  errors.add("ship_external_number", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("ship_external_number", new org.apache.struts.action.ActionError("error.field.required"));

	   
		if ( ship_city != null )
		  {
			ship_city = ship_city.trim().toUpperCase();
			if ( ship_city.length() == 0 )
			  errors.add("ship_city", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("ship_city", new org.apache.struts.action.ActionError("error.field.required"));

		
		if ( email != null )
		  {
			email = email.trim();
			if ( email.length() != 0 )
			  if (email.indexOf(" ") > 0)
				errors.add("email", new org.apache.struts.action.ActionError("error.field.required.no.space"));
			  else
			  {
				  String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
				  Pattern pattern = Pattern.compile(PATTERN_EMAIL);
				  
			        // Match the given input against this pattern
			        Matcher matcher = pattern.matcher(email);
			        if(!matcher.matches())
			        	errors.add("email", new org.apache.struts.action.ActionError("error.field.invalid.format"));
			  }
		  }

		
		if ( discount != null )
		  {
			discount = discount.trim().toUpperCase();
			if ( discount.length() == 0 )
			  errors.add("discount", new org.apache.struts.action.ActionError("error.field.required"));
			else
			  {
			    try
			    {
			    	Double D = Double.valueOf(discount);
			    	
			    	if (D < 0 )
				      errors.add("discount", new org.apache.struts.action.ActionError("error.field.too.low"));
			    	else if ( D > 100 )
					   errors.add("discount", new org.apache.struts.action.ActionError("error.field.too.large"));
			    }
			    catch(Exception e)
			    {
			    	errors.add("discount", new org.apache.struts.action.ActionError("error.field.invalid.format"));
			    	
			    }
			  }
		  }
		else
		  errors.add("discount", new org.apache.struts.action.ActionError("error.field.required"));

		if ( is_male == null )
		  {
			is_male = "off";
		  }
		
		
	   
	  return errors;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getExternal_number() {
		return external_number;
	}

	public void setExternal_number(String external_number) {
		this.external_number = external_number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getShip_street() {
		return ship_street;
	}

	public void setShip_street(String ship_street) {
		this.ship_street = ship_street;
	}

	public String getShip_external_number() {
		return ship_external_number;
	}

	public void setShip_external_number(String ship_external_number) {
		this.ship_external_number = ship_external_number;
	}

	public String getShip_city() {
		return ship_city;
	}

	public void setShip_city(String ship_city) {
		this.ship_city = ship_city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	
	
	


}
