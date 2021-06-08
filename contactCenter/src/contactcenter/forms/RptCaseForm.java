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
public class RptCaseForm extends ActionForm {

	private String id_case = null;
	
	

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }

		if(  id_case==null  )
			errors.add("id_case", new org.apache.struts.action.ActionError("error.field.required"));
		else
		  {
			id_case=id_case.trim();
			if (  id_case.length() == 0  )
				errors.add("id_case", new org.apache.struts.action.ActionError("error.field.required"));
			else
				if(!(Utils.isInt(id_case)))
					errors.add("id_case", new org.apache.struts.action.ActionError("error.field.invalid.format"));
		  }

		return errors;

	}

	
	/**
	 * @return
	 */
	public String getId_case() {
		return id_case;
	}

	/**
	 * @param string
	 */
	public void setId_case(String string) {
		id_case = string;
	}

}
