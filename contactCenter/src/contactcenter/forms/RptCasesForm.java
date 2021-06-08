package contactcenter.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tecunsa.Utils;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class RptCasesForm extends ActionForm {

	private String concept = null;
	
	private String id_user = null;
	private String filter_id_user=null;
	
	private String id_client= null;
	private String filter_id_client =null;

	private String date_begin = null;
	private String date_end = null;
	private String filter_date=null;
	
	private String id_type_case_status=null;
	private String filter_id_type_case_status=null;
	
	private String address= null;
	private String filter_address=null;

	

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }

	    if ( filter_id_user.equals("1") )
			{
				if(  id_user==null  )
					errors.add("id_user", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					id_user=id_user.trim();
					if (  id_user.length() == 0  )
						errors.add("id_user", new org.apache.struts.action.ActionError("error.field.required"));
					else
						if(!(Utils.isInt(id_user)))
							errors.add("id_user", new org.apache.struts.action.ActionError("error.field.invalid.format"));
				  }
			}
			
			
		if ( filter_id_client.equals("1") )
			{
				if(  id_client==null  )
					errors.add("id_client", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					id_client=id_client.trim();
					if (  id_client.length() == 0  )
						errors.add("id_client", new org.apache.struts.action.ActionError("error.field.required"));
					else
						if(!(Utils.isInt(id_client)))
							errors.add("id_client", new org.apache.struts.action.ActionError("error.field.invalid.format"));
				  }
			}
		

		if(  filter_date.equals("1")  )
			{
				if(  date_begin==null  )
					errors.add("date_begin", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					date_begin=date_begin.trim();
					if (  date_begin.length() == 0  )
						errors.add("date_begin", new org.apache.struts.action.ActionError("error.field.required"));
					else
						if(!(Utils.IsDate(date_begin)))
							errors.add("date_begin", new org.apache.struts.action.ActionError("error.field.invalid.format"));
				  }
				  
				if(  date_end==null  )
					errors.add("date_end", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					date_end=date_end.trim();
					if (  date_end.length() == 0  )
						errors.add("date_end", new org.apache.struts.action.ActionError("error.field.required"));
					else
						if(!(Utils.IsDate(date_end)))
							errors.add("date_end", new org.apache.struts.action.ActionError("error.field.invalid.format"));
				  }
			  }
			
		if(  filter_id_type_case_status.equals("1")  )
		  {
			if(  id_type_case_status==null  )
				errors.add("id_type_case_status", new org.apache.struts.action.ActionError("error.field.required"));
			else
			  {
				id_type_case_status=id_type_case_status.trim();
				if (  id_type_case_status.length() == 0  )
					errors.add("id_type_case_status", new org.apache.struts.action.ActionError("error.field.required"));
			  }
		  }

		  if ( filter_address.equals("1") )
			  {
				  if(  address==null  )
					  errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));
				  else
					{
					  address=address.trim().toUpperCase();
					  if (  address.length() == 0  )
						  errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));
					}
			  }

		return errors;

	}

	/**
	 * @return
	 */
	public String getAddress() {
		return address;
	}



	/**
	 * @return
	 */
	public String getConcept() {
		return concept;
	}



	/**
	 * @return
	 */
	public String getFilter_address() {
		return filter_address;
	}

	/**
	 * @return
	 */
	public String getFilter_date() {
		return filter_date;
	}

	/**
	 * @return
	 */
	public String getFilter_id_client() {
		return filter_id_client;
	}

	/**
	 * @return
	 */
	public String getFilter_id_type_case_status() {
		return filter_id_type_case_status;
	}

	/**
	 * @return
	 */
	public String getFilter_id_user() {
		return filter_id_user;
	}

	/**
	 * @return
	 */
	public String getId_client() {
		return id_client;
	}

	/**
	 * @return
	 */
	public String getId_type_case_status() {
		return id_type_case_status;
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
	public void setAddress(String string) {
		address = string;
	}



	/**
	 * @param string
	 */
	public void setConcept(String string) {
		concept = string;
	}


	/**
	 * @param string
	 */
	public void setFilter_address(String string) {
		filter_address = string;
	}

	/**
	 * @param string
	 */
	public void setFilter_date(String string) {
		filter_date = string;
	}

	/**
	 * @param string
	 */
	public void setFilter_id_client(String string) {
		filter_id_client = string;
	}

	/**
	 * @param string
	 */
	public void setFilter_id_type_case_status(String string) {
		filter_id_type_case_status = string;
	}

	/**
	 * @param string
	 */
	public void setFilter_id_user(String string) {
		filter_id_user = string;
	}

	/**
	 * @param string
	 */
	public void setId_client(String string) {
		id_client = string;
	}

	/**
	 * @param string
	 */
	public void setId_type_case_status(String string) {
		id_type_case_status = string;
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
	public String getDate_begin() {
		return date_begin;
	}

	/**
	 * @return
	 */
	public String getDate_end() {
		return date_end;
	}

	/**
	 * @param string
	 */
	public void setDate_begin(String string) {
		date_begin = string;
	}

	/**
	 * @param string
	 */
	public void setDate_end(String string) {
		date_end = string;
	}

}
