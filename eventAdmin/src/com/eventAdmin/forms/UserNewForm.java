package com.eventAdmin.forms;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.eventAdmin.*; 
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
public class UserNewForm extends ActionForm {

	private String address = null;
	private String name = null;
	private String password = null;
	private String email = null;

	/**
	 * Get address
	 * @return String
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set address
	 * @param <code>String</code>
	 */
	public void setAddress(String a) {
		this.address = a;
	}

	/**
	 * Get name
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name
	 * @param <code>String</code>
	 */
	public void setName(String n) {
		this.name = n;
	}

	/**
	 * Get password
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set password
	 * @param <code>String</code>
	 */
	public void setPassword(String p) {
		this.password = p;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		address = null;
		name = null;
		password = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if ( name != null )
		  {
			name = name.trim();
			if (name.length() == 0)
				errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
			if (name.indexOf(" ") > 0 )
				errors.add("name", new org.apache.struts.action.ActionError("error.field.required.no.space"));
		  }
		else
		  errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
		
		
		if ( password != null )
		  {
			password = password.trim();
			if ( password.length() == 0 )
			  errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));
			if (password.indexOf(" ") > 0)
				errors.add("password", new org.apache.struts.action.ActionError("error.field.required.no.space"));
		  }
		else
		  errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));

		 /* if ( address != null )
			{
			  address = address.trim();
			  if ( address.length() == 0 )
				errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));
			}
		  else
			errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));*/


		if (email != null)
		  email = email.trim();

		if ( !Utils.isEmail( email) )
			errors.add("email", new org.apache.struts.action.ActionError("error.field.invalid.format"));
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.trim().length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }
		return errors;

	}
	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

}
