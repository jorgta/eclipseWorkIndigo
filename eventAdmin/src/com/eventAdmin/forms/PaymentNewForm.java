package com.eventAdmin.forms;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.bituos.Utils;
import com.eventAdmin.Beans.BeanCompany;
import com.eventAdmin.Beans.BeanSale;
import com.eventAdmin.Beans.BeanUser;

/**
 * @version 	1.0
 * @author     	Bituos Tools S de RL MI
 */

public class PaymentNewForm extends ActionForm {

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
	private String  id_type_payment;
	private String  id_payment_type_form;
	
	
	


	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();

		if( process != null )
		  {
 
			if( process.equals("payItMyAccount"))
			  {
				
				
				if(  date_reg==null  )
					errors.add("date_capture", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					date_reg=date_reg.trim();
					if (  date_reg.length() == 0  )
						errors.add("date_reg", new org.apache.struts.action.ActionError("error.field.required"));
					/*else
						if ( Utils.before1 ( Utils.StrToDate1(date_reg),  Utils.Today())  )
							errors.add("date_reg", new org.apache.struts.action.ActionError("error.field.lower.date"));*/
				  }
				
				
				if(  quantity==null  )
					errors.add("quantity", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					quantity=quantity.trim();
					if (  quantity.length() == 0  )
						errors.add("quantity", new org.apache.struts.action.ActionError("error.field.required"));
					else
						if( !Utils.isDouble(quantity) ) 
							errors.add("quantity", new org.apache.struts.action.ActionError("error.field.invalid.format"));
				  }
			  }
			
			
			
			
			
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


	
	
	public String getId_sale() {
		return id_sale;
	}







	public void setId_sale(String id_sale) {
		this.id_sale = id_sale;
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







	public String getId_type_payment() {
		return id_type_payment;
	}







	public void setId_type_payment(String id_type_payment) {
		this.id_type_payment = id_type_payment;
	}







	public String getId_payment_type_form() {
		return id_payment_type_form;
	}







	public void setId_payment_type_form(String id_payment_type_form) {
		this.id_payment_type_form = id_payment_type_form;
	}



	
}
