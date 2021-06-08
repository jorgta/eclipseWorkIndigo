package contactcenter.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class LoginForm extends ActionForm {

	private int id_card;
	private String password = null;
	private String name = null;

	/**
	* @return * Obtiene el valor de la variable
	 */
	public String getPassword() {
		return password;
	}

	/**
	  * @param string  * Asigna un valor a la variable
	 */
	public void setPassword(String p) {
		this.password = p;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		password = null;
		name = null;

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

		if(  name==null  )
			errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
		else
			name=name.trim();
			if (  name.length() == 0  )
				errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));

		if(  password==null  )
			errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));
		else
			password=password.trim();
			if (  password.length() == 0  )
				errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));



	return errors;
	}

	/**
	* @return
	* Obtiene el valor de la variable name
	 */
	public String getName() {
		return name;
	}

	/**
	  * @param string
	* Asigna un valor a la variable name
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	* @return
	* Obtiene el valor de la variable id_card
	 */
	public int getId_card() {
		return id_card;
	}

	/**
	 * @param i
	* Asigna el valor de i a  la variable id
	 */
	public void setId_card(int i) {
		id_card = i;
	}

}
