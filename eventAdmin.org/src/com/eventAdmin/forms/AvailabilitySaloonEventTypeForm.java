package com.eventAdmin.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.bituos.Utils;

/**
 * @version 	1.0
 * @author     	Bituos Tools S de RL MI
 */

public class AvailabilitySaloonEventTypeForm extends ActionForm {

	private int id;
	private String process;
	private String id_list;
	private String id_saloon;
	private String date_event;
	private String checkList;
	private String checkSaloon;
	
	
	
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
			if( process.equals("showCalendar"))
			  {
				if(  id_list==null  )
					errors.add("id_list", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					id_list=id_list.trim();
					if (  id_list.length() == 0  )
						errors.add("id_list", new org.apache.struts.action.ActionError("error.field.required"));
				  }
				
				if(  id_saloon==null  )
					errors.add("id_saloon", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					id_saloon=id_saloon.trim();
					if (  id_saloon.length() == 0  )
						errors.add("id_saloon", new org.apache.struts.action.ActionError("error.field.required"));
				  }
				
				if( checkList == null  )
					checkList = "off";
				
				if( checkSaloon == null  )
					checkSaloon = "off";
			  }
		  }

		return errors;

	}



	public String getCheckList() {
		return checkList;
	}



	public void setCheckList(String checkList) {
		this.checkList = checkList;
	}



	public String getCheckSaloon() {
		return checkSaloon;
	}



	public void setCheckSaloon(String checkSaloon) {
		this.checkSaloon = checkSaloon;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getProcess() {
		return process;
	}



	public void setProcess(String process) {
		this.process = process;
	}



	public String getId_list() {
		return id_list;
	}



	public void setId_list(String id_list) {
		this.id_list = id_list;
	}



	public String getId_saloon() {
		return id_saloon;
	}



	public void setId_saloon(String id_saloon) {
		this.id_saloon = id_saloon;
	}



	public String getDate_event() {
		return date_event;
	}



	public void setDate_event(String date_event) {
		this.date_event = date_event;
	}


	
	









	
}
