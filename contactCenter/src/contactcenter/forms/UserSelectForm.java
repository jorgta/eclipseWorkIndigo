package contactcenter.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author  	Bituos Tools S de RL MI
 */
public class UserSelectForm extends ActionForm {

	private int id;


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

		// if ((field == null) || (field.length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }
		return errors;

	}
	/**
	* @return
	* Obtiene el valor de la variable id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param i
	* Asigna el valor de i a  la variable id
	 */
	public void setId(int i) {
		id = i;
	}

}
