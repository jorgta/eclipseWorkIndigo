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

public class SaleChangeForm extends ActionForm {

	private int id;
	
	private String id_user_capture;
	private String id_supervisor;
	private String id_list;
	private String id_option;
	private String id_menu;
	private String id_client;

	private String id_flower;
	private String id_saloon;
	private String id_music;
	
	private String status;
	private String process;
	private String checkUserData;
	private String personsCount;
	
	private String date_event;
	private String id_hour;
	private String hourQuantity;
	
	private String id_product;
	
	private String productQuantity;
	private String productUpdateQuantity;
	
	private String anotherEmail;
	
	
	private String id_sale;
	
	
	







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
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }

		if( process != null )
		  {
//			if( process.equals("selectOption"))
//			  {
//				if(  personsCount==null  )
//					errors.add("personsCount", new org.apache.struts.action.ActionError("error.field.required"));
//				else
//				  {
//					personsCount=personsCount.trim();
//					if (  personsCount.length() == 0  )
//						errors.add("personsCount", new org.apache.struts.action.ActionError("error.field.required"));
//					else
//						if( !Utils.isInt(personsCount) )
//							errors.add("personsCount", new org.apache.struts.action.ActionError("error.field.invalid.format"));
//				  }
//			  }
//			else if( process.equals("selectMenu"))
//			  {
//				if(  personsCount==null  )
//					errors.add("personsCount", new org.apache.struts.action.ActionError("error.field.required"));
//				else
//				  {
//					personsCount=personsCount.trim();
//					if (  personsCount.length() == 0  )
//						errors.add("personsCount", new org.apache.struts.action.ActionError("error.field.required"));
//					else
//						if( !Utils.isInt(personsCount) )
//							errors.add("personsCount", new org.apache.struts.action.ActionError("error.field.invalid.format"));
//				  }
//
//				if( id_menu==null  )
//					errors.add("id_menu", new org.apache.struts.action.ActionError("error.field.required"));
//				else
//				  {
//					id_menu=id_menu.trim();
//					if (  id_menu.length() == 0  )
//						errors.add("id_menu", new org.apache.struts.action.ActionError("error.field.required"));
//				  }
//			  
//			  }
//			else 
				if( process.equals("validateClient"))
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
			  }
			else if ( process.equals("updateTotal") || process.equals("register") )
			  {
				if(  date_event==null  )
					errors.add("date_capture", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					date_event=date_event.trim();
					if (  date_event.length() == 0  )
						errors.add("date_event", new org.apache.struts.action.ActionError("error.field.required"));
					else
						if ( Utils.before1( Utils.StrToDate1(date_event),  Utils.Today())  )
							errors.add("date_event", new org.apache.struts.action.ActionError("error.field.lower.date"));
				  }

				if(  hourQuantity==null  )
					errors.add("hourQuantity", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					hourQuantity=hourQuantity.trim();
					if (  hourQuantity.length() == 0  )
						errors.add("hourQuantity", new org.apache.struts.action.ActionError("error.field.required"));
					else
						if( !Utils.isInt(hourQuantity) )
							errors.add("hourQuantity", new org.apache.struts.action.ActionError("error.field.invalid.format"));
				  }

				if(  personsCount==null  )
					errors.add("personsCount", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					personsCount=personsCount.trim();
					if (  personsCount.length() == 0  )
						errors.add("personsCount", new org.apache.struts.action.ActionError("error.field.required"));
					else
						if( !Utils.isInt(personsCount) )
							errors.add("personsCount", new org.apache.struts.action.ActionError("error.field.invalid.format"));
				  }
				
				
				if(  id_menu==null  )
					errors.add("id_menu", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					id_menu=id_menu.trim();
					if (  id_menu.length() == 0  )
						errors.add("id_menu", new org.apache.struts.action.ActionError("error.field.required"));
				  }

				if(  id_saloon==null  )
					errors.add("id_saloon", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					id_saloon=id_saloon.trim();
					if (  id_saloon.length() == 0  )
						errors.add("id_saloon", new org.apache.struts.action.ActionError("error.field.required"));
				  }

			  
				if(  id_flower==null  )
					errors.add("id_flower", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					id_flower=id_flower.trim();
					if (  id_flower.length() == 0  )
						errors.add("id_flower", new org.apache.struts.action.ActionError("error.field.required"));
				  }

			  
				if(  id_music==null  )
					errors.add("id_music", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					id_music=id_music.trim();
					if (  id_music.length() == 0  )
						errors.add("id_music", new org.apache.struts.action.ActionError("error.field.required"));
				  }
				
				if (anotherEmail != null)
				  {
					anotherEmail = anotherEmail.trim();
					
					if ( !(anotherEmail.equals("")) )
					  if ( !Utils.isEmail( anotherEmail ) )
						errors.add("anotherEmail", new org.apache.struts.action.ActionError("error.field.invalid.format"));
				  }
				else
					anotherEmail = "";
				
			  }
			else if( process.equals("addProduct"))
			  {
				if(  productQuantity==null  )
					errors.add("productQuantity", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					productQuantity=productQuantity.trim();
					if (  productQuantity.length() == 0  )
						errors.add("productQuantity", new org.apache.struts.action.ActionError("error.field.required"));
					else
						if( !Utils.isInt(productQuantity) )
							errors.add("productQuantity", new org.apache.struts.action.ActionError("error.field.invalid.format"));
				  }
			  }
			else if( process.equals("updateProduct"))
			  {
				if(  productUpdateQuantity==null  )
					errors.add("productUpdateQuantity", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					productUpdateQuantity=productUpdateQuantity.trim();
					if (  productUpdateQuantity.length() == 0  )
						errors.add("productUpdateQuantity", new org.apache.struts.action.ActionError("error.field.required"));
					else
						if( !Utils.isInt(productUpdateQuantity) )
							errors.add("productUpdateQuantity", new org.apache.struts.action.ActionError("error.field.invalid.format"));
				  }
			  }
			
			
			
			
		  }

		return errors;

	}


	
	
	
	

	public String getAnotherEmail() {
		return anotherEmail;
	}







	public void setAnotherEmail(String anotherEmail) {
		this.anotherEmail = anotherEmail;
	}







	public String getProductUpdateQuantity() {
		return productUpdateQuantity;
	}







	public void setProductUpdateQuantity(String productUpdateQuantity) {
		this.productUpdateQuantity = productUpdateQuantity;
	}







	public String getProductQuantity() {
		return productQuantity;
	}







	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}







	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getId_user_capture() {
		return id_user_capture;
	}



	public void setId_user_capture(String id_user_capture) {
		this.id_user_capture = id_user_capture;
	}



	public String getId_supervisor() {
		return id_supervisor;
	}



	public void setId_supervisor(String id_supervisor) {
		this.id_supervisor = id_supervisor;
	}



	public String getId_list() {
		return id_list;
	}



	public void setId_list(String id_list) {
		this.id_list = id_list;
	}



	public String getId_option() {
		return id_option;
	}



	public void setId_option(String id_option) {
		this.id_option = id_option;
	}



	public String getId_menu() {
		return id_menu;
	}



	public void setId_menu(String id_menu) {
		this.id_menu = id_menu;
	}



	public String getId_client() {
		return id_client;
	}



	public void setId_client(String id_client) {
		this.id_client = id_client;
	}



	public String getId_flower() {
		return id_flower;
	}



	public void setId_flower(String id_flower) {
		this.id_flower = id_flower;
	}



	public String getId_saloon() {
		return id_saloon;
	}



	public void setId_saloon(String id_saloon) {
		this.id_saloon = id_saloon;
	}



	public String getId_music() {
		return id_music;
	}



	public void setId_music(String id_music) {
		this.id_music = id_music;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getProcess() {
		return process;
	}



	public void setProcess(String process) {
		this.process = process;
	}



	public String getCheckUserData() {
		return checkUserData;
	}



	public void setCheckUserData(String checkUserData) {
		this.checkUserData = checkUserData;
	}



	public String getPersonsCount() {
		return personsCount;
	}



	public void setPersonsCount(String personsCount) {
		this.personsCount = personsCount;
	}



	public String getDate_event() {
		return date_event;
	}



	public void setDate_event(String date_event) {
		this.date_event = date_event;
	}



	public String getId_hour() {
		return id_hour;
	}



	public void setId_hour(String id_hour) {
		this.id_hour = id_hour;
	}



	public String getHourQuantity() {
		return hourQuantity;
	}



	public void setHourQuantity(String hourQuantity) {
		this.hourQuantity = hourQuantity;
	}







	public String getId_product() {
		return id_product;
	}







	public void setId_product(String id_product) {
		this.id_product = id_product;
	}





	
	
	
	
	
	
	
	
	
	


















































































































	
}
