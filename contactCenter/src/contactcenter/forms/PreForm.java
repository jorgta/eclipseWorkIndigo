package contactcenter.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class PreForm extends ActionForm {

	private String process = null;

	/**
	* @return
	* Obtiene el valor de la variable process
	 */
	public String getProcess() {
		return process;
	}

	/**
	  * @param string
	* Asigna un valor a la variable process
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

		return errors;

	}
}
