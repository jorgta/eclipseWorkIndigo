package contactcenter.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tecunsa.Utils;

import com.tecunsa.Email;

/**
 * @version 	1.0
 * @author      Bituos Tools S de RL MI
 */
public class CaseCloseForm extends ActionForm {

	private int id;
	private String id_case;
	private String process;
	private String case_description;
	private String successful;



	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.
		//id_case=null;
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

		/*
		if(  description==null  )
			errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
		else
		{
			description=description.trim();
			if (  description.length() == 0  )
				errors.add("description", new org.apache.struts.action.ActionError("error.field.invalid.format"));
		}
		if(  short_description==null  )
			errors.add("short_description", new org.apache.struts.action.ActionError("error.field.required"));
		else
		{
			short_description=short_description.trim();
			if (  short_description.length() == 0  )
				errors.add("short_description", new org.apache.struts.action.ActionError("error.field.invalid.format"));
		}
		if(  id_user_afected==null  )
			errors.add("id_user_afected", new org.apache.struts.action.ActionError("error.field.required"));
		else
		{
			id_user_afected=id_user_afected.trim();
			if (  id_user_afected.length() == 0  )
				errors.add("id_user_afected", new org.apache.struts.action.ActionError("error.field.required"));
			else
				if( !Utils.isInt(id_user_afected) )
					errors.add("id_user_afected", new org.apache.struts.action.ActionError("error.field.invalid.format"));
		}
		*/
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
	 * @return
	 * Obtiene el valor de la variable id_case
	 */
	public String getId_case() {
		return id_case;
	}

	/**
	 * @return
	 * Obtiene el valor de la variable process
	 */
	public String getProcess() {
		return process;
	}



	/**
	 * @param i
	 * Asigna un el  valor de i a la variable id
	 */
	public void setId(int i) {
		id = i;
	}

	/**
	  * @param string
	  * Asigna un valor a la variable id_case
	 */
	public void setId_case(String string) {
		id_case = string;
	}

	/**
	  * @param string
	  * Asigna un valor a la variable process
	 */
	public void setProcess(String string) {
		process = string;
	}


	/**
	  * @return
	  * Obtiene el valor de la variable case_description
	 */
	public String getCase_description() {
		return case_description;
	}

	/**
	  * @param string
	  * Asigna un valor a la variable case_description
	 */
	public void setCase_description(String string) {
		case_description = string;
	}

	/**
	 * @return
	 */
	public String getSuccessful() {
		return successful;
	}

	/**
	 * @param string
	 */
	public void setSuccessful(String string) {
		successful = string;
	}

}
