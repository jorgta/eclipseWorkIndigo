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
public class UserNewForm extends ActionForm {

	private String name;
	private String full_name;
	private String password;
	private String active;
	private String address;
//	private String rfc;
	private String email;
	private String telephone;
	private String id_supervisor;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.


	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		if ( name != null )
		  {
			if (name.length() == 0)
				errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
			if (name.indexOf(" ") > 0)
				errors.add("name", new org.apache.struts.action.ActionError("error.field.required.no.space"));
		  }
		else
		  errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));


		if ( password != null )
		  {
			if ( password.length() == 0 )
			  errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));
			if (password.indexOf(" ") > 0)
				errors.add("password", new org.apache.struts.action.ActionError("error.field.required.no.space"));
		  }
		else
		  errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));

		if (full_name.trim().length() == 0)
			errors.add("full_name", new org.apache.struts.action.ActionError("error.field.required"));

		if(email!=null)
		  {
			if (!Email.isEmail(email))
			  errors.add("email", new org.apache.struts.action.ActionError("error.field.invalid.format"));
		  }
		else
		  errors.add("email", new org.apache.struts.action.ActionError("error.field.required"));
		

		return errors;
	}

	/**
	* @return
	* Obtiene el valor de la variable active
	 */
	public String getActive() {
		return active;
	}

	/**
	* @return
	* Obtiene el valor de la variable address
	 */
	public String getAddress() {
		return address;
	}

	/**
	* @return
	* Obtiene el valor de la variable email
	 */
	public String getEmail() {
		return email;
	}

	/**
	* @return
	* Obtiene el valor de la variable full_name
	 */
	public String getFull_name() {
		return full_name;
	}

	/**
	* @return
	* Obtiene el valor de la variable name
	 */
	public String getName() {
		return name;
	}

	/**
	* @return
	* Obtiene el valor de la variable password
	 */
	public String getPassword() {
		return password;
	}



	/**
	  * @param string
	* Asigna un valor a la variable active
	 */
	public void setActive(String string) {
		active = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable address
	 */
	public void setAddress(String string) {
		address = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable email
	 */
	public void setEmail(String string) {
		email = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable full_name
	 */
	public void setFull_name(String string) {
		full_name = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable name
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable password
	 */
	public void setPassword(String string) {
		password = string;
	}



	/**
	* @return
	* Obtiene el valor de la variable telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	  * @param string
	* Asigna un valor a la variable telephone
	 */
	public void setTelephone(String string) {
		telephone = string;
	}

	/**
	 * @return
	 */
	public String getId_supervisor() {
		return id_supervisor;
	}

	/**
	 * @param string
	 */
	public void setId_supervisor(String string) {
		id_supervisor = string;
	}

}
