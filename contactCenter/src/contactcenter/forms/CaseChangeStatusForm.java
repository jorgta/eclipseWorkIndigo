package contactcenter.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class CaseChangeStatusForm extends ActionForm {

	private int id;
	private String id_client;
	private String notes;
	private String status;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

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
	 * @return
	* Obtiene el valor de la variable id_client
	 */
	public String getId_client() {
		return id_client;
	}

	/**
	 * @return
	* Obtiene el valor de la variable notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @return
	* Obtiene el valor de la variable status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param i
	* Asigna un el valor de i  a la variable id
	 */
	public void setId(int i) {
		id = i;
	}

	/**
	 * @param string
	* Asigna un valor a la variable id_client
	 */
	public void setId_client(String string) {
		id_client = string;
	}

	/**
	 * @param string
	* Asigna un valor a la variable notes
	 */
	public void setNotes(String string) {
		notes = string;
	}

	/**
	 * @param string
	* Asigna un valor a la variable status
	 */
	public void setStatus(String string) {
		status = string;
	}

}
