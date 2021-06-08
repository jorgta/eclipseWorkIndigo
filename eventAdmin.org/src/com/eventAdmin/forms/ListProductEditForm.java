package com.eventAdmin.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author     	Bituos Tools S de RL MI
 */

import com.bituos.Utils;

public class ListProductEditForm extends ActionForm {

	private String process;
	
	private String id_product;
	private String description;
	private String price;
	private String perPerson;
	
	
	
	
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
				if( process.equals("create") || process.equals("update")  )
				  {
					if( description==null  )
						errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						description=description.trim().toUpperCase();
						if (  description.length() == 0  )
							errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
					  }
					
					if( price ==null  )
						errors.add("price", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						price=price.trim();
						if (  price.length() == 0  )
							errors.add("price", new org.apache.struts.action.ActionError("error.field.required"));
						else
							if( !Utils.isDouble(price) )
								errors.add("price", new org.apache.struts.action.ActionError("error.field.invalid.format"));
					  }
					
					if( perPerson == null  )
						perPerson = "off";
					
					if( process.equals("update") )
						if( id_product==null  )
							errors.add("id_flower", new org.apache.struts.action.ActionError("error.field.required"));
						else
						  {
							id_product=id_product.trim();
							if (  id_product.length() == 0  )
								errors.add("id_product", new org.apache.struts.action.ActionError("error.field.required"));
						  }
				  }
				else if( process.equals("delete")   )
				  {
					if( id_product ==null  )
						errors.add("id_product", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						id_product=id_product.trim();
						if (  id_product.length() == 0  )
							errors.add("id_product", new org.apache.struts.action.ActionError("error.field.required"));
					  }
				  }
			  }

			return errors;

		}







	public String getProcess() {
		return process;
	}




	public void setProcess(String process) {
		this.process = process;
	}







	public String getId_product() {
		return id_product;
	}







	public void setId_product(String id_product) {
		this.id_product = id_product;
	}







	public String getDescription() {
		return description;
	}







	public void setDescription(String description) {
		this.description = description;
	}







	public String getPrice() {
		return price;
	}







	public void setPrice(String price) {
		this.price = price;
	}







	public String getPerPerson() {
		return perPerson;
	}







	public void setPerPerson(String perPerson) {
		this.perPerson = perPerson;
	}








	
	
	
	

	
	





	


	
}
