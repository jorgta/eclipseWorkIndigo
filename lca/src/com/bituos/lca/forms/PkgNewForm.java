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
public class PkgNewForm extends ActionForm {

	private String description = null;
	private String id_test = null;
	private String test_name = null;
	private String test_price = null;
	private String id_test_pkg = null;
	private String id_testtemp = null;
	
	private String process = null;
	

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		test_name = null;
		test_price = null;
		id_test = null;
		id_test_pkg = null;

		
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
		if ( process.equals("testAdd"))
		  {
			test_price = test_price.trim().toUpperCase();
			if ( test_price.length() == 0 )
			  errors.add("test_price", new org.apache.struts.action.ActionError("error.field.required"));
			else
			  if (test_price.indexOf(" ") > 0)
				errors.add("test_price", new org.apache.struts.action.ActionError("error.field.required.no.space"));
		  }
		
		if ( process.equals("done"))
		  {
			 
			description = description.trim().toUpperCase();
			if ( description.length() == 0 )
			  errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
		  }
		
		
		
		
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.trim().length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }
		return errors;

	}

	public String getId_test() {
		return id_test;
	}

	public void setId_test(String id_test) {
		this.id_test = id_test;
	}

	public String getTest_name() {
		return test_name;
	}

	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}

	public String getTest_price() {
		return test_price;
	}

	public void setTest_price(String test_price) {
		this.test_price = test_price;
	}

	public String getId_test_pkg() {
		return id_test_pkg;
	}

	public void setId_test_pkg(String id_test_pkg) {
		this.id_test_pkg = id_test_pkg;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId_testtemp() {
		return id_testtemp;
	}

	public void setId_testtemp(String id_testtemp) {
		this.id_testtemp = id_testtemp;
	}
	
	



	

}
