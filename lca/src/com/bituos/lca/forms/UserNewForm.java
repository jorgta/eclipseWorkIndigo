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
public class UserNewForm extends ActionForm {

	private String rfc = null;
	private String address = null;
	private String name = null;
	private String password = null;
	private String email = null;
	private String id_branch = null;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		rfc = null;
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

		  if ( address != null )
			{
			  address = address.trim();
			  if ( address.length() == 0 )
				errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));
			}
		  else
			errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));


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

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId_branch() {
		return id_branch;
	}

	public void setId_branch(String id_branch) {
		this.id_branch = id_branch;
	}
	
	
	
	
	
	
	
	
}
