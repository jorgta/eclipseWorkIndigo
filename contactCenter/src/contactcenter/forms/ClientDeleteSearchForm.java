package contactcenter.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 2 fields on this form:
 * <ul>
 * <li>filter - [your comment here]
 * <li>id - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class ClientDeleteSearchForm extends ActionForm {

	private String filter = null;
	private String id = null;

	/**
	 * Get filter
	 * @return String
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * Set filter
	 * @param <code>String</code>
	 */
	public void setFilter(String f) {
		this.filter = f;
	}

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

		filter = null;
		id = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }
		return errors;

	}
}
