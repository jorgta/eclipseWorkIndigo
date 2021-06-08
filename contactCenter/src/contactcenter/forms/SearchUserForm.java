package contactcenter.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class SearchUserForm extends ActionForm {

	private String filter  = null;
	private String filterName = null;
	private String filterEmail = null;
	private String filterAddress = null;
	private String concept = null;
	private String forward = null;
	private String id      = null;

	/**
	* @return
	* Obtiene el valor de la variable filter
	 */
	public String getFilter() {
		return filter;
	}

	/**
	  * @param string
 	* Asigna un valor a la variable filter
	 */
	public void setFilter(String f) {
		this.filter = f;
	}



	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		filter = null;
		concept = null;
		forward = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.


		if (concept.equals("name"))
		{
			if(  filterName==null  )
							errors.add("filterName", new org.apache.struts.action.ActionError("error.field.required"));
			else
			{

				filterName=filterName.trim();
				if (  filterName.length() == 0  )
					errors.add("filterName", new org.apache.struts.action.ActionError("error.field.required"));
			}
		}

		if (concept.equals("email"))
		{
			if(  filterEmail==null  )
				errors.add("filterEmail", new org.apache.struts.action.ActionError("error.field.required"));
			else
			{
				filterEmail=filterEmail.trim();
				if (  filterEmail.length() == 0  )
					errors.add("filterEmail", new org.apache.struts.action.ActionError("error.field.required"));
			}

		}

		if (concept.equals("address"))
		{
			if(  filterAddress==null  )
				errors.add("filterAddress", new org.apache.struts.action.ActionError("error.field.required"));
			else
			{
				filterAddress=filterAddress.trim();
				if (  filterAddress.length() == 0  )
					errors.add("filterAddress", new org.apache.struts.action.ActionError("error.field.required"));
			}

		}


		return errors;

	}
	/**
	* @return
	* Obtiene el valor de la variable concept
	 */
	public String getConcept() {
		return concept;
	}

	/**
	* @return
	* Obtiene el valor de la variable forward
	 */
	public String getForward() {
		return forward;
	}

	/**
	  * @param string
	* Asigna un valor a la variable concept
	 */
	public void setConcept(String string) {
		concept = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable forward
	 */
	public void setForward(String string) {
		forward = string;
	}

	/**
	* @return
	* Obtiene el valor de la variable id
	 */
	public String getId() {
		return id;
	}

	/**
	  * @param string
	* Asigna un valor a la variable id
	 */
	public void setId(String string) {
		id = string;
	}



	/**
	* @return
	* Obtiene el valor de la variable filterAddress
	 */
	public String getFilterAddress() {
		return filterAddress;
	}

	/**
	* @return
	* Obtiene el valor de la variable filterEmail
	 */
	public String getFilterEmail() {
		return filterEmail;
	}

	/**
	* @return
	* Obtiene el valor de la variable filterName
	 */
	public String getFilterName() {
		return filterName;
	}

	/**
	  * @param string
	* Asigna un valor a la variable filterAddress
	 */
	public void setFilterAddress(String string) {
		filterAddress = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable filterEmail
	 */
	public void setFilterEmail(String string) {
		filterEmail = string;
	}

	/**
	  * @param string
	* Asigna un valor a la variable filterName
	 */
	public void setFilterName(String string) {
		filterName = string;
	}

}
