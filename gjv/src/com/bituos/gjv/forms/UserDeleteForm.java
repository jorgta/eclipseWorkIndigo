package com.bituos.gjv.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.bituos.gjv.*;

/**
 * Form bean for a Struts application.
 * Users may access 2 fields on this form:
 * <ul>
 * <li>finish - [your comment here]
 * <li>id - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class UserDeleteForm extends ActionForm {

	private String id = null;
  private String process="";

	/**
	 * Get id
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set id
	 * @param <code>String</code>
	 */
	public void setId(String i) {
		this.id = i;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		id = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		
		if ( id != null )
		{
			id = id.trim();
			if (id.length() == 0)
				errors.add("id", new org.apache.struts.action.ActionError("error.field.required"));
			if (id.indexOf(" ") > 0)
				errors.add("id", new org.apache.struts.action.ActionError("error.field.required.no.space"));
		}
		else
		  errors.add("id", new org.apache.struts.action.ActionError("error.field.required"));
		
		return errors;

	}


  public void setProcess(String process)
  {
    this.process = process;
  }


  public String getProcess()
  {
    return process;
  }
}
