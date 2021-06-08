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
public class MaterialNewForm extends ActionForm {

	private String id;
	private String description;
	private String haslabel;
	private String active;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		/*description = null;
		haslabel = null;
		active = null;*/
		

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if ( description != null )
		  {
			description = description.trim();
			if (description.length() == 0)
				errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
			
		  }
		else
		  errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));



		return errors;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHaslabel() {
		return haslabel;
	}

	public void setHaslabel(String haslabel) {
		this.haslabel = haslabel;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	
	
	
	
	
	
}
