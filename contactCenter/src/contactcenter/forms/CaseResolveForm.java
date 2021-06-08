package contactcenter.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.tecunsa.Utils;
import com.tecunsa.Email;
import org.apache.struts.upload.FormFile;
import com.tecunsa.Utils;

/**
 * @version 	1.0
 * @author  	Bituos Tools S de RL MI
 */
public class CaseResolveForm extends ActionForm {

	private String id_case;
	private String id_note;
	private String notes;
	private String note_readed;
	private String date_reg;
	private String process;
	private String isPrivate;
	private String id_user;
	private String fileDescription;
	private FormFile file;
	private String id_type_case;
	private int id_type_case_order;
	
	private String notes_char_count;


	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		notes=null;
		note_readed=null;
	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
				// adding each error to this.errors as found, e.g.

		int MAX_NOTE_LENGTH = 250;
						
		if(process.equals("addNote"))
		  {
			if(  id_case==null  )
				errors.add("id_case", new org.apache.struts.action.ActionError("error.field.required"));

			if(  notes==null  )
				errors.add("notes", new org.apache.struts.action.ActionError("error.field.required"));
			else
			 {
				notes=notes.trim();
				if (  notes.length() == 0  )
					errors.add("notes", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  if (notes.length()>MAX_NOTE_LENGTH)
				    errors.add("notes", new org.apache.struts.action.ActionError("error.field.max.length.250"));
				  
			 }
		  }
		else if(process.equals("forward"))
		  {
			if(  id_case==null  )
				errors.add("id_case", new org.apache.struts.action.ActionError("error.field.required"));

			if(  notes==null  )
				errors.add("notes", new org.apache.struts.action.ActionError("error.field.required"));
			else
			  {
				notes=notes.trim();
				if (  notes.length() == 0  )
					errors.add("notes", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  if (notes.length()>MAX_NOTE_LENGTH)
					errors.add("notes", new org.apache.struts.action.ActionError("error.field.max.length.250"));
			  }
			
			if ( id_user == null)	
  			  errors.add("id_user", new org.apache.struts.action.ActionError("error.field.required"));
  			else
  			  {
  			  	id_user = id_user.trim();
				if( id_user.equals("") )
				  errors.add("id_user", new org.apache.struts.action.ActionError("error.field.required"));
				else if( !Utils.isInt(id_user) )
				  errors.add("id_user", new org.apache.struts.action.ActionError("error.field.invalid.format"));
  			  	
  			  }
			
		  }
		else if(process.equals("resolveCase"))
		  {
			if(  id_case==null  )
				errors.add("id_case", new org.apache.struts.action.ActionError("error.field.required"));

			if(  notes==null  )
				errors.add("notes", new org.apache.struts.action.ActionError("error.field.required"));
			else
			  {
				notes=notes.trim();
				if (  notes.length() == 0  )
					errors.add("notes", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  if (notes.length()>MAX_NOTE_LENGTH)
					errors.add("notes", new org.apache.struts.action.ActionError("error.field.max.length.250"));
			  }
		  }
		
		if(isPrivate==null)
		  {
			isPrivate="Y";
		  }
		  
		if( fileDescription != null )
		  {
		  	fileDescription = fileDescription.trim();
		  	
		  	if ( !fileDescription.equals("") )
		  	  if ( file.getFileName() != null )
		  	    {
					if ( file.getFileName().equals("") )
					  errors.add("file", new org.apache.struts.action.ActionError("error.field.required"));
		  	    }
		  	   else
				 errors.add("file", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		  
		  if( file.getFileName()!= null )
			  if( !file.getFileName().equals("") )
				{
				  if ( fileDescription != null )
				    {
				   	  fileDescription = fileDescription.trim();
					  if ( fileDescription.equals("") )
					  	errors.add("fileDescription", new org.apache.struts.action.ActionError("error.field.required"));
				    }
				  else
				    errors.add("fileDescription", new org.apache.struts.action.ActionError("error.field.required"));
				}
		  
		return errors;
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
	 * Obtiene el valor de la variable id_note
	 */
	public String getId_note() {
		return id_note;
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
	 * Obtiene el valor de la variable process
	 */
	public String getProcess() {
		return process;
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
	  * Asigna un valor a la variable id_note
	 */
	public void setId_note(String string) {
		id_note = string;
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
	 * Asigna un valor a la variable process
	 */
	public void setProcess(String string) {
		process = string;
	}

	/**
	* @return
	* Obtiene el valor de la variable note_readed
	 */
	public String getNote_readed() {
		return note_readed;
	}

	/**
	  * @param string
	* Asigna un valor a la variable note_readed
	 */
	public void setNote_readed(String string) {
		note_readed = string;
	}

	/**
	 * @return
	* Obtiene el valor de la variable date_reg
	 */
	public String getDate_reg() {
		return date_reg;
	}

	/**
	  * @param string
	 * Asigna un valor a la variable date_reg
	 */
	public void setDate_reg(String string) {
		date_reg = string;
	}

	/**
	 * @return
	 * Obtiene el valor de la variable isPrivate
	 */
	public String getIsPrivate() {
		return isPrivate;
	}

	/**
	  * @param string
	 * Asigna un valor a la variable isPrivate
	 */
	public void setIsPrivate(String string) {
		isPrivate = string;
	}

	/**
	 * @return
	 */
	public String getId_user() {
		return id_user;
	}

	/**
	 * @param string
	 */
	public void setId_user(String string) {
		id_user = string;
	}

	/**
	 * @return
	 */
	public FormFile getFile() {
		return file;
	}

	/**
	 * @return
	 */
	public String getFileDescription() {
		return fileDescription;
	}

	/**
	 * @param file
	 */
	public void setFile(FormFile file) {
		this.file = file;
	}

	/**
	 * @param string
	 */
	public void setFileDescription(String string) {
		fileDescription = string;
	}

	/**
	 * @return
	 */
	public String getId_type_case() {
		return id_type_case;
	}

	/**
	 * @param string
	 */
	public void setId_type_case(String string) {
		id_type_case = string;
	}

	/**
	 * @return
	 */
	public String getNotes_char_count() {
		return notes_char_count;
	}

	/**
	 * @param string
	 */
	public void setNotes_char_count(String string) {
		notes_char_count = string;
	}


	/**
	 * @return
	 */
	public int getId_type_case_order() {
		return id_type_case_order;
	}

	/**
	 * @param i
	 */
	public void setId_type_case_order(int i) {
		id_type_case_order = i;
	}

}
