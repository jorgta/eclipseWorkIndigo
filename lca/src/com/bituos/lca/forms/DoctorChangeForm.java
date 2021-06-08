package com.bituos.lca.forms;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class DoctorChangeForm extends ActionForm {

	private String name = null;
	private String address = null;
	private String email = null; 
	private String notes = null;
	private int active;
	private String phone;
	private String id_representative;
	private String phone_cel;

	
	
	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		name = null;
		address = null;
		email = null;
		notes = null;

	}
	
	
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if ( name != null )
		  {
			name = name.trim().toUpperCase();
			if (name.length() == 0)
				errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		else
		  errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
		
		
		  if ( address != null )
			{
			  address = address.trim().toUpperCase();
			  if ( address.length() == 0 )
				errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));
			}
		  else
			errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));


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

		//if ( !Utils.isEmail( email) )
		//	errors.add("email", new org.apache.struts.action.ActionError("error.field.invalid.format"));
		
		if (notes != null)
		  notes = notes.trim();
		else 
		  notes = "";

	    if (phone != null)
		  phone = phone.trim();
		else
		  phone = "";

		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.trim().length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }
		return errors;

	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public String getAddress() {
		return address;
	}





	public void setAddress(String address) {
		this.address = address;
	}





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public String getNotes() {
		return notes;
	}





	public void setNotes(String notes) {
		this.notes = notes;
	}





	public int getActive() {
		return active;
	}





	public void setActive(int active) {
		this.active = active;
	}


	public String getId_representative() {
		return id_representative;
	}


	public void setId_representative(String id_representative) {
		this.id_representative = id_representative;
	}


	public String getPhone_cel() {
		return phone_cel;
	}


	public void setPhone_cel(String phone_cel) {
		this.phone_cel = phone_cel;
	}
	
	
	
	
	
	
	
	
	
	

}
