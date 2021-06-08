package contactcenter.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import java.io.*;
import java.util.*;

import com.tecunsa.Utils;

import com.tecunsa.Email;

/**
 * @version 	1.0
 * @author     	Bituos Tools S de RL MI
 */
public class CaseNewForm extends ActionForm {

	private int id;
	private String id_user_capture;
	private String id_supervisor;
	private String id_type_case;
	private String id_client;
	private String short_description;
	private String description;
	private String status;
	private String process;
	private String checkUserData;
	private String fileDescription;
	private FormFile file;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.
		short_description = null;
		description = "Numero de parte:\nCantidad:\nProveedor actual:\nMaterial\nContacto:\nProceso:\n\nComentarios:";
		id_client = null;
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

		if( process != null )
		  {
			if( process.equals("validate"))
			  {
				if(  id_client==null  )
					errors.add("id_client", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					id_client=id_client.trim();
					if (  id_client.length() == 0  )
						errors.add("id_client", new org.apache.struts.action.ActionError("error.field.required"));
					else
						if( !Utils.isInt(id_client) )
							errors.add("id_client", new org.apache.struts.action.ActionError("error.field.invalid.format"));
				  }
			  }
			else
			  {
				if(  description==null  )
					errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					description=description.trim();
					if (  description.length() == 0  )
						errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
				  }

				if(  short_description==null  )
					errors.add("short_description", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					short_description=short_description.trim();
					if (  short_description.length() == 0  )
						errors.add("short_description", new org.apache.struts.action.ActionError("error.field.required"));
				  }
				  
				if ( fileDescription != null )
					if (fileDescription.length() > 0)
					  {
						if ( file!= null )
						  {
							if ( file.getFileName().length() == 0)
								errors.add("file", new org.apache.struts.action.ActionError("error.field.required"));
						  }
						else
						  errors.add("file", new org.apache.struts.action.ActionError("error.field.required"));
					  }
				else
					if ( file!= null )
						if ( file.getFileName().length() > 0)
							errors.add("fileDescription", new org.apache.struts.action.ActionError("error.field.required"));
				  

			  }
		  }

		return errors;

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
	 * @return ç
	 * Obtiene el valor de la variable id_supervisor
	 */
	public String getId_supervisor() {
		return id_supervisor;
	}

	/**
	 * @return
	 * Obtiene el valor de la variable id_type_case
	 */
	public String getId_type_case() {
		return id_type_case;
	}

	/**
	 * @return
	 * Obtiene el valor de la variable id_user_capture
	 */
	public String getId_user_capture() {
		return id_user_capture;
	}

	/**
	 * @return
	 * Obtiene el valor de la variable short_description
	 */
	public String getShort_description() {
		return short_description;
	}

	/**
	 * @return
	 * Obtiene el valor de la variable status
	 */
	public String getStatus() {
		return status;
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
	* Asigna el valor de i a la variable id
	 */
	public void setId(int i) {
		id = i;
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
    	 * Asigna un valor a la variable id_type_case
	 */
	public void setId_type_case(String string) {
		id_type_case = string;
	}

	/**
	  * @param string
	 * Asigna un valor a la variable id_user_capture
	 */
	public void setId_user_capture(String string) {
		id_user_capture = string;
	}

	/**
	  * @param string
	 * Asigna un valor a la variable short_description
	 */
	public void setShort_description(String string) {
		short_description = string;
	}

	/**
	  * @param string
	  * Asigna un valor a la variable status
	 */
	public void setStatus(String string) {
		status = string;
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

	/**
	 * @return
	 */
	public String getCheckUserData() {
		return checkUserData;
	}

	/**
	 * @param string
	 */
	public void setCheckUserData(String string) {
		checkUserData = string;
	}

	/**
	 * @return
	 */
	public FormFile getFile() {
		return file;
	}

	

	/**
	 * @param file
	 */
	public void setFile(FormFile file) {
		this.file = file;
	}

	
	/**
	 * @return
	 */
	public String getFileDescription() {
		return fileDescription;
	}

	/**
	 * @param string
	 */
	public void setFileDescription(String string) {
		fileDescription = string;
	}

}
