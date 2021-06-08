package com.bituos.bar.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 1 field on this form:
 * <ul>
 * <li>process - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class DeleteProductForm extends ActionForm {  

	private String id_agent;
	private String id_table;
	private String id_producto;
	private String id_comanda;
	private String id_detail;
	

	private String process;

	

	

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		id_agent = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.trim().length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }
		return errors;

	}


	

	public String getId_agent() {
		return id_agent;
	}

	public void setId_agent(String id_agent) {
		this.id_agent = id_agent;
	}

	public String getId_table() {
		return id_table;
	}

	public void setId_table(String id_table) {
		this.id_table = id_table;
	}

	public String getId_producto() {
		return id_producto;
	}

	public void setId_producto(String id_producto) {
		this.id_producto = id_producto;
	}

	public String getId_comanda() {
		return id_comanda;
	}

	public void setId_comanda(String id_comanda) {
		this.id_comanda = id_comanda;
	}

	public String getId_detail() {
		return id_detail;
	}

	public void setId_detail(String id_detail) {
		this.id_detail = id_detail;
	}



	
	
}
