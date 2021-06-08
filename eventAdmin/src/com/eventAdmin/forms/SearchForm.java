package com.eventAdmin.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.bituos.Utils;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class SearchForm extends ActionForm {

	private String filter  = null;
	private String filter2 = null;
	private String filter3 = null;
	private String filter4 = null;
	private String concept = null;
	private String forward = null;
	//private String id      = null;
	private String aditional1 = null;
	private String aditional2 = null;

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


		/*
		if(  filter==null  )
			errors.add("filter", new org.apache.struts.action.ActionError("error.field.required"));
		else
		{
			filter=filter.trim();
			if (  filter.length() == 0  )
				errors.add("filter", new org.apache.struts.action.ActionError("error.field.required"));
			else
				if(!Utils.isInt(filter))
					errors.add("filter", new org.apache.struts.action.ActionError("error.field.invalid.format"));

		}

		if ((concept == null) || (concept.length() == 0)) {
		  errors.add("filter", new org.apache.struts.action.ActionError("error.internal"));
		}
		if ((forward == null) || (forward.length() == 0)) {
		  errors.add("filter", new org.apache.struts.action.ActionError("error.internal"));
		}
		*/

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
	* Obtiene el valor de la variable filter2
	 */
	public String getFilter2() {
		return filter2;
	}

	/**
	  * @param string
	* Asigna un valor a la variable filter2
	 */
	public void setFilter2(String string) {
		filter2 = string;
	}

	/**
	 * @return
	 */
	public String getAditional1() {
		return aditional1;
	}

	/**
	 * @return
	 */
	public String getAditional2() {
		return aditional2;
	}

	/**
	 * @param string
	 */
	public void setAditional1(String string) {
		aditional1 = string;
	}

	/**
	 * @param string
	 */
	public void setAditional2(String string) {
		aditional2 = string;
	}

	/**
	 * @return
	 */
	public String getFilter3() {
		return filter3;
	}

	/**
	 * @param string
	 */
	public void setFilter3(String string) {
		filter3 = string;
	}

	/**
	 * @return
	 */
	public String getFilter4() {
		return filter4;
	}

	/**
	 * @param string
	 */
	public void setFilter4(String string) {
		filter4 = string;
	}

}
