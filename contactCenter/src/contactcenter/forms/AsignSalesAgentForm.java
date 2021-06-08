package contactcenter.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.tecunsa.Utils;

import com.tecunsa.Email;
/**
 * @version 	1.0
 * @author  	Bituos Tools S de RL MI
 */
public class AsignSalesAgentForm extends ActionForm {

	private String id_client;
	private String id_user;


	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.


	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.


		boolean clientOK = false;
		boolean userOK = false;
		
		if(  id_user==null  )
			errors.add("id_user", new org.apache.struts.action.ActionError("error.field.required"));
		else
		  {
			id_user=id_user.trim();
			if (  id_user.length() == 0  )
			  errors.add("id_user", new org.apache.struts.action.ActionError("error.field.required"));
			else if (!(Utils.isInt(id_user)))
			  errors.add("id_user", new org.apache.struts.action.ActionError("error.field.invalid.format"));
			else
			  userOK = true;
					
		  }
		
		if(  id_client==null  )
			errors.add("id_client", new org.apache.struts.action.ActionError("error.field.required"));
		else
		  {
			id_client=id_client.trim();
			if (  id_client.length() == 0  )
			  errors.add("id_client", new org.apache.struts.action.ActionError("error.field.required"));
			else if (!(Utils.isInt(id_client)))
			  errors.add("id_client", new org.apache.struts.action.ActionError("error.field.invalid.format"));
			else
			  clientOK = true;
		  }
		
		return errors;
	}




	

	/**
	 * @return
	* Obtiene el valor de la variable id_user
	 */
	public String getId_user() {
		return id_user;
	}

	

	/**
	 * @param string
	* Asigna un valor a la variable id_user
	 */
	public void setId_user(String string) {
		id_user = string;
	}

	/**
	 * @return
	 */
	public String getId_client() {
		return id_client;
	}

	/**
	 * @param string
	 */
	public void setId_client(String string) {
		id_client = string;
	}

}
