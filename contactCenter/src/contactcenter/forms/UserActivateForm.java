package contactcenter.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tecunsa.Utils;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class UserActivateForm extends ActionForm {

	private String id = null;

	/**
	* @return
	* Obtiene el valor de la variable id
	 */
	public String getId() {
		return id;
	}

	/**
	  * @param string
	* Asigna un valor a la variable id
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
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.


		if ( id == null)
		 {
			errors.add("id", new org.apache.struts.action.ActionError("error.field.required"));
		 }
		else
		 {
			id=id.trim();
			if(id.length()==0)
				errors.add("id", new org.apache.struts.action.ActionError("error.field.required"));
			else
				if (!Utils.isInt(id))
		    		errors.add("id", new org.apache.struts.action.ActionError("error.field.invalid.format"));
		 }

		return errors;

	}
}
