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
public class RptForm extends ActionForm {

	private String process = null;
	
	

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
	 */
	public String getProcess() {
		return process;
	}

	/**
	 * @param string
	 */
	public void setProcess(String string) {
		process = string;
	}

}
