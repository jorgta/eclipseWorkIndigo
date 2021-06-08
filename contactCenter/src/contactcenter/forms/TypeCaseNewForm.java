package contactcenter.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tecunsa.Utils;

import com.tecunsa.Email;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class TypeCaseNewForm extends ActionForm {

	private int id;
	private String description;
	private String days;
	private String process;
	private String id_type_case;

	private String chekUserData;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.
		description = null;

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

		if(process.equals("add"))
		{
			 if(  description==null  )
				 errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
			 else
			 {
				 description=description.trim();
				 if (  description.length() == 0  )
					 errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
			 }
			 if(  days==null  )
				 errors.add("days", new org.apache.struts.action.ActionError("error.field.required"));
			 else
			 {
				 days=days.trim();
				 if (  days.length() == 0  )
					 errors.add("days", new org.apache.struts.action.ActionError("error.field.required"));
				 else
					 if( !Utils.isInt(days) )
						 errors.add("days", new org.apache.struts.action.ActionError("error.field.invalid.format"));
			 }
		}
		if(process.equals("delete"))
		{
			if(  id_type_case==null  )
				  errors.add("id_type_case", new org.apache.struts.action.ActionError("error.field.required"));

		}
		return errors;

	}


	/**
	* @return
	* Obtiene el valor de la variable chekUserData
	 */
	public String getChekUserData() {
		return chekUserData;
	}


	/**
	* @return
	* Obtiene el valor de la variable description
	 */
	public String getDescription() {
		return description;
	}

	/**
	* @return
	* Obtiene el valor de la variable id
	 */
	public int getId() {
		return id;
	}

	/**
	  * @param string
	* Asigna un valor a la variable chekUserData
	 */
	public void setChekUserData(String string) {
		chekUserData = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable description
	 */
	public void setDescription(String string) {
		description = string;
	}

	/**
	 * @param i
	 * Asigna el valor de a la variable id
	 */
	public void setId(int i) {
		id = i;
	}

	/**
	* @return
	* Obtiene el valor de la variable days
	 */
	public String getDays() {
		return days;
	}

	/**
	  * @param string
	* Asigna un valor a la variable days
	 */
	public void setDays(String string) {
		days = string;
	}

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
	public void setProcess(String string) {
		process = string;
	}

	/**
	* @return
	* Obtiene el valor de la variable id_type_case
	 */
	public String getId_type_case() {
		return id_type_case;
	}

	/**
	  * @param string
	* Asigna un valor a la variable id_type_case
	 */
	public void setId_type_case(String string) {
		id_type_case = string;
	}

}
