package contactcenter.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

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
public class ClientSelectForm extends ActionForm {

	private int id;

	/**
	 * Get id
	 * @return String
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set id
	 * @param <code>String</code>
	 */
	public void setId(int i) {
		this.id = i;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		id = 0;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		if (id == 0)
		   errors.add("id", new org.apache.struts.action.ActionError("error.field.required"));
		else
		   if (id < 0)
		     errors.add("id", new org.apache.struts.action.ActionError("error.field.invalid.format"));

		return errors;

	}
}
