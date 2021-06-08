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
public class AsignSupervisorForm extends ActionForm {

	private String id_user;
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
		boolean clientOK = false;
		boolean supervisorOK = false;

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
			  clientOK = true;
			
		  }

		if(  id_supervisor==null  )
			errors.add("id_supervisor", new org.apache.struts.action.ActionError("error.field.required"));
		else
		  {
			id_supervisor=id_supervisor.trim();
			if (  id_supervisor.length() == 0  )
			  errors.add("id_supervisor", new org.apache.struts.action.ActionError("error.field.required"));
			else if (!(Utils.isInt(id_supervisor)))
			  errors.add("id_supervisor", new org.apache.struts.action.ActionError("error.field.invalid.format"));
			else
			  supervisorOK = true;
		  }


	    if( clientOK && supervisorOK)
	      if ( id_user.equals(id_supervisor) ) 
		   errors.add("id_supervisor", new org.apache.struts.action.ActionError("error.field.not.equal"));

		/*
		if ( id_user != null )
		  {
			if(id_supervisor!=null)
			{
				if(id_user.equals(id_supervisor))
					errors.add("id_supervisor", new org.apache.struts.action.ActionError("error.field.not.equal"));
				id_user=id_user.trim();
				if (id_user.length() == 0)
					errors.add("id_user", new org.apache.struts.action.ActionError("error.field.required"));
				if (!(Utils.isInt(id_user)))
					errors.add("id_user", new org.apache.struts.action.ActionError("error.field.invalid.format"));

				id_supervisor=id_supervisor.trim();
				if (id_supervisor.length() == 0)
				    errors.add("id_supervisor", new org.apache.struts.action.ActionError("error.field.required"));
				if (!(Utils.isInt(id_supervisor)))
				    errors.add("id_supervisor", new org.apache.struts.action.ActionError("error.field.invalid.format"));
			}
			else
				errors.add("id_supervisor", new org.apache.struts.action.ActionError("error.field.required"));

		  }
		else
		  errors.add("id_user", new org.apache.struts.action.ActionError("error.field.required"));
		  */



		return errors;
	}




	/**
	 * @return
	 * Obtiene el valor de la variable id_supervisor
	 */
	public String getId_supervisor() {
		return id_supervisor;
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
	* Asigna un valor a la variable id_supervisor
	 */
	public void setId_supervisor(String string) {
		id_supervisor = string;
	}

	/**
	 * @param string
	* Asigna un valor a la variable id_user
	 */
	public void setId_user(String string) {
		id_user = string;
	}

}
