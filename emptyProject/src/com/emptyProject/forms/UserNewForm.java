package com.emptyProject.forms;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.emptyProject.*; 
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
public class UserNewForm extends ActionForm {

	private String id;
	private String rfc = null;
	private String address = null;
	private String name = null;
	private String password = null;
	private String email = null;
	private String active;
	private String full_name;
	
	

	

	
	
	
	
	private String process;
	
	//paginacion
	private String pagina;
	
	//tabs
	private String tab;
	
	//rows to show
	private String rowtoshow;
	

	/**
	 * Get rfc
	 * @return String
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * Set rfc
	 * @param <code>String</code>
	 */
	public void setRfc(String r) {
		this.rfc = r;
	}

	/**
	 * Get address
	 * @return String
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set address
	 * @param <code>String</code>
	 */
	public void setAddress(String a) {
		this.address = a;
	}

	/**
	 * Get name
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name
	 * @param <code>String</code>
	 */
	public void setName(String n) {
		this.name = n;
	}

	/**
	 * Get password
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set password
	 * @param <code>String</code>
	 */
	public void setPassword(String p) {
		this.password = p;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		rfc = null;
		address = null;
		name = null;
		password = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		HttpSession httpSession = request.getSession();
        if (process.equals("addUser")  ||  process.equals("addUserToList") )
          {
			if ( name != null )
			  {
				name = name.trim();
				if (name.length() == 0)
					errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
				if (name.indexOf(" ") > 0 )
					errors.add("name", new org.apache.struts.action.ActionError("error.field.required.no.space"));
			  }
			else
			  errors.add("name", new org.apache.struts.action.ActionError("error.field.required"));
			
			
			if ( password != null )
			  {
				password = password.trim();
				if ( password.length() == 0 )
				  errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));
				if (password.indexOf(" ") > 0)
					errors.add("password", new org.apache.struts.action.ActionError("error.field.required.no.space"));
			  }
			else
			  errors.add("password", new org.apache.struts.action.ActionError("error.field.required"));
	
			  if ( address != null )
				{
				  address = address.trim();
				  if ( address.length() == 0 )
					errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));
				}
			  else
				errors.add("address", new org.apache.struts.action.ActionError("error.field.required"));
	
	
			if (email != null)
			  email = email.trim();
	
			if ( !Utils.isEmail( email) )
				errors.add("email", new org.apache.struts.action.ActionError("error.field.invalid.format"));

			
			 if ( full_name != null )
				{
				 full_name = full_name.trim();
				  if ( full_name.length() == 0 )
					errors.add("full_name", new org.apache.struts.action.ActionError("error.field.required"));
				}
			  else
				errors.add("full_name", new org.apache.struts.action.ActionError("error.field.required"));
	
			
			
			
			
			
			if ( process.equals("addUserToList") )
			  {
		
				
				String notification="";
				if (errors.isEmpty())
					httpSession.setAttribute("foundErrors", true );				  
				else 
				{
					String currentTab= "#tab" + tab;					
				httpSession.setAttribute("defaultTab", currentTab  );
					httpSession.setAttribute("foundErrors", false );
					notification= "attention";
				    httpSession.setAttribute("notification", notification );
				    com.bituos.Notification e = new com.bituos.Notification("Verifique que los datos esten correctos", request);
				
				}
				
			  }
			
			
			
          }
        else if (process.equals("changeRowToShow"))
        {
    	  String s = rowtoshow;
        }
        else if (process.equals("pagination"))
          {
      	
          }
        
        
        
		return errors;

	}
	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string) {
		email = string;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}


	
	

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRowtoshow() {
		return rowtoshow;
	}

	public void setRowtoshow(String rowtoshow) {
		this.rowtoshow = rowtoshow;
	}




	

}
