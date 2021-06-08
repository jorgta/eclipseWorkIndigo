package com.bituos.lca.forms;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.bituos.lca.*; 
import com.bituos.*;


/**
 * Form bean for a Struts application.
 * Users may access 4 fields on this form:
 * <ul>
 * <li>rfc - [your comment here]
 * <li>address - [your comment here]
 * <li>password - [your comment here]
 * <li>name - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class OrderNewForm extends ActionForm {

	private String id_test = null;
	private String id_patient = null;
	private String id_branch = null;
	private String id_testtemp = null;
	private String id_doctor = null;
	private String sent_by_doctor = null;

	private String password = null;

	private String process = null;
	private String haslabel = null;
	private String id_material = null;
	private String quantity = null;
	
	private String is_patient = null;
	private String date_delivery= null;
	private String id_company= null;
	private String id_pkg= null;
	private String notes= null;
	
	
	
	
	


	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		id_test = null;

		id_patient = null;
		id_branch = null;
		id_testtemp = null;
		id_doctor = null;
		sent_by_doctor = null;

		process = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		
		if ( process == null)
		  {
			errors.add("name", new org.apache.struts.action.ActionError("error.undefined.process"));
		  }
		else if ( process.equals( "testAdd" ) )
		  {
			if ( id_test == null )
				  errors.add("id_test", new org.apache.struts.action.ActionError("error.field.required"));
			
			

		  }
		else if ( process.equals( "testDelete" ) )
		  {
			if ( id_test == null )
				  errors.add("id_test", new org.apache.struts.action.ActionError("error.field.required"));

		  }
		else if ( process.equals( "testDeleteAll" ) )
		  {
			if ( id_test == null )
				  errors.add("id_test", new org.apache.struts.action.ActionError("error.field.required"));

		  }
		else if ( process.equals( "done" ) )
		  {
			if ( sent_by_doctor == null )
			  {
				sent_by_doctor = "off";
			  }

			if ( id_patient == null )
				  errors.add("id_patient", new org.apache.struts.action.ActionError("error.field.required"));

			if ( id_branch == null )
				  errors.add("id_branch", new org.apache.struts.action.ActionError("error.field.required"));
			
		  }
		else if ( process.equals( "addMaterial" ) )
		  {
			if ( quantity.equals(null) || quantity.equals( ""))
				  errors.add("quantity", new org.apache.struts.action.ActionError("error.field.required.quantity"));

		  }
		
		
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.trim().length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }
		return errors;

	}

	
	
	
	
	


	public String getDate_delivery() {
		return date_delivery;
	}

	public void setDate_delivery(String date_delivery) {
		this.date_delivery = date_delivery;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId_test() {
		return id_test;
	}

	public void setId_test(String id_test) {
		this.id_test = id_test;
	}

	public String getId_patient() {
		return id_patient;
	}

	public void setId_patient(String id_patient) {
		this.id_patient = id_patient;
	}

	public String getId_branch() {
		return id_branch;
	}

	public void setId_branch(String id_branch) {
		this.id_branch = id_branch;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getId_testtemp() {
		return id_testtemp;
	}

	public void setId_testtemp(String id_testtemp) {
		this.id_testtemp = id_testtemp;
	}

	public String getId_doctor() {
		return id_doctor;
	}

	public void setId_doctor(String id_doctor) {
		this.id_doctor = id_doctor;
	}

	public String getSent_by_doctor() {
		return sent_by_doctor;
	}

	public void setSent_by_doctor(String sent_by_doctor) {
		this.sent_by_doctor = sent_by_doctor;
	}

	public String getHaslabel() {
		return haslabel;
	}

	public void setHaslabel(String haslabel) {
		this.haslabel = haslabel;
	}

	public String getId_material() {
		return id_material;
	}

	public void setId_material(String id_material) {
		this.id_material = id_material;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getIs_patient() {
		return is_patient;
	}

	public void setIs_patient(String is_patient) {
		this.is_patient = is_patient;
	}

	public String getId_company() {
		return id_company;
	}

	public void setId_company(String id_company) {
		this.id_company = id_company;
	}

	public String getId_pkg() {
		return id_pkg;
	}

	public void setId_pkg(String id_pkg) {
		this.id_pkg = id_pkg;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}


	
	
	
	
	

}
