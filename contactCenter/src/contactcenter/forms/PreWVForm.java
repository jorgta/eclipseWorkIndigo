package contactcenter.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 1 field on this form:
 * <ul>
 * <li>process - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class PreWVForm extends ActionForm {

	private String process = null;
	private String parameter1 = null;

	/**
	 * Get process
	 * @return String
	 */
	public String getProcess() {
		return process;
	}

	/**
	 * Set process
	 * @param <code>String</code>
	 */
	public void setProcess(String p) {
		this.process = p;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		process = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.trim().length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }

		if ( process.equals("viewFile") )
		  {
			if ( parameter1 != null )
			  { 
				parameter1 = parameter1.trim();
				if (parameter1.length() == 0)
					errors.add("parameter1", new org.apache.struts.action.ActionError("error.field.required"));
			  }
			else
			  errors.add("parameter1", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		  
		return errors;

	}

	/**
	 * @return
	 */
	public String getParameter1() {
		return parameter1;
	}

	/**
	 * @param string
	 */
	public void setParameter1(String string) {
		parameter1 = string;
	}

}
