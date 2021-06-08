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
public class UserChangeForm extends ActionForm {

	private int id;
	private String name = null;
	private String full_name = null;
	private String password = null;
	private String address = null;
//	private String rfc = null;
	private String email = null;
	private String telephone=null;

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
			if(  full_name==null  )
				errors.add("full_name", new org.apache.struts.action.ActionError("error.field.required"));
			else
				full_name=full_name.trim();
				if (  full_name.length() == 0  )
					errors.add("full_name", new org.apache.struts.action.ActionError("error.field.required"));

			if(  password==null  )
				errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));
			else
				password=password.trim();
				if (  password.length() == 0  )
					errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));
			if(email!=null)
				if (email.length()!=0)
						if (!Email.isEmail(email))
						errors.add("email", new org.apache.struts.action.ActionError("error.field.invalid.format"));

			return errors;

	}

	/**
	 * @return * Obtiene el valor de la variable address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return * Obtiene el valor de la variable email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return * Obtiene el valor de la variable full_name
	 */
	public String getFull_name() {
		return full_name;
	}

	/**
	 * @return * Obtiene el valor de la variable password
	 */
	public String getPassword() {
		return password;
	}



	/**
	  * @param string  * Asigna un valor a la variable address
	 */
	public void setAddress(String string) {
		address = string;
	}

	/**
	  * @param string  * Asigna un valor a la variable email
	 */
	public void setEmail(String string) {
		email = string;
	}

	/**
	  * @param string  * Asigna un valor a la variable full_name
	 */
	public void setFull_name(String string) {
		full_name = string;
	}

	/**
	  * @param string  * Asigna un valor a la variable password
	 */
	public void setPassword(String string) {
		password = string;
	}



	/**
	 * @return * Obtiene el valor de la variable id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param i
	* Asigna el valor de i a la variable id
	 */
	public void setId(int i) {
		id = i;
	}

	/**
	 * @return * Obtiene el valor de la variable  name
	 */
	public String getName() {
		return name;
	}

	/**
	  * @param string  * Asigna un valor a la variable  name
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @return * Obtiene el valor de la variable telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	  * @param string  * Asigna un valor a la variable telephone
	 */
	public void setTelephone(String string) {
		telephone = string;
	}

	
}
