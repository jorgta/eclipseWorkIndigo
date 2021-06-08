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

public class PaymentCanceledForm extends ActionForm {

	private int id;
	

	
	
	private String process;
	
	
	
	private String id_user;	
	private String id_sale;
	private String id_company;
	private String id_intern;
	private String date_reg; 
	private String canceled;
	private String quantity;
	private String date_canceled;
	
	private String id_payment;







	public String getId_sale() {
		return id_sale;
	}







	public void setId_sale(String id_sale) {
		this.id_sale = id_sale;
	}







	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if( process != null )
		  {

			/*	if( process.equals("validateClient"))
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
			  }*/
			


			
			
			
		  }

		return errors;

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







	public String getId_user() {
		return id_user;
	}







	public void setId_user(String id_user) {
		this.id_user = id_user;
	}







	public String getId_company() {
		return id_company;
	}







	public void setId_company(String id_company) {
		this.id_company = id_company;
	}







	public String getId_intern() {
		return id_intern;
	}







	public void setId_intern(String id_intern) {
		this.id_intern = id_intern;
	}







	public String getDate_reg() {
		return date_reg;
	}







	public void setDate_reg(String date_reg) {
		this.date_reg = date_reg;
	}







	public String getCanceled() {
		return canceled;
	}







	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}







	public String getQuantity() {
		return quantity;
	}







	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}







	public String getDate_canceled() {
		return date_canceled;
	}







	public void setDate_canceled(String date_canceled) {
		this.date_canceled = date_canceled;
	}







	public String getId_payment() {
		return id_payment;
	}







	public void setId_payment(String id_payment) {
		this.id_payment = id_payment;
	}


	
	
	
	
}
