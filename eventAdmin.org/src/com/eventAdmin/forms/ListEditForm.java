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

public class ListEditForm extends ActionForm {

	private int id;

	private String id_list;
	private String listDescription;
	
	private String id_option;
	private String optionMin;
	private String optionMax;
	private String optionPrice;
	
	private String id_menu;
	private String menuName;
	private String menuDescription;

	private String date_reg;
	private String process;
	
	private String price1;
	private String price2;
	private String price3;
	private String price4;
	private String price5;
	private String price6;
	private String price7;
	
	
	
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
				
				if( process.equals("selectList"))
				  {
					if( id_list==null  )
					  errors.add("id_list", new org.apache.struts.action.ActionError("error.must.select.value"));
					else
					  {
						id_list=id_list.trim().toUpperCase();
						if (  id_list.length() == 0  )
							errors.add("id_list", new org.apache.struts.action.ActionError("error.must.select.value"));
					  }
				  }
				else if( process.equals("selectListOption"))
				  {
					if( id_option==null  )
					  errors.add("id_option", new org.apache.struts.action.ActionError("error.must.select.value"));
					else
					  {
						id_option=id_option.trim().toUpperCase();
						if (  id_option.length() == 0  )
							errors.add("id_option", new org.apache.struts.action.ActionError("error.must.select.value"));
					  }
				  }
				else if( process.equals("createList") || process.equals("updateList") )
				  {
					if( listDescription==null  )
						errors.add("listDescription", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						listDescription=listDescription.trim().toUpperCase();
						if (  listDescription.length() == 0  )
							errors.add("listDescription", new org.apache.struts.action.ActionError("error.field.required"));
					  }
					
					if ( process.equals("updateList") )
						if( id_list==null  )
							  errors.add("id_list", new org.apache.struts.action.ActionError("error.must.select.value"));
						else
						  {
							id_list=id_list.trim().toUpperCase();
							if (  id_list.length() == 0  )
								errors.add("id_list", new org.apache.struts.action.ActionError("error.must.select.value"));
						  }
				  }
				else if( process.equals("createOption") )
				  {
					boolean minOK = false;
					boolean maxOK = false;
					
					if( id_list==null  )
						  errors.add("id_list", new org.apache.struts.action.ActionError("error.must.select.value"));
					else
					  {
						id_list=id_list.trim().toUpperCase();
						if (  id_list.length() == 0  )
							errors.add("id_list", new org.apache.struts.action.ActionError("error.must.select.value"));
					  }

					if( optionMin ==null  )
						errors.add("optionMin", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						optionMin=optionMin.trim();
						if (  optionMin.length() == 0  )
							errors.add("optionMin", new org.apache.struts.action.ActionError("error.field.required"));
						else
							if( !Utils.isInt(optionMin) )
								errors.add("optionMin", new org.apache.struts.action.ActionError("error.field.invalid.format"));
							else
								minOK = true;
					  }

				  
					if( optionMax ==null  )
						errors.add("optionMax", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						optionMax=optionMax.trim();
						if (  optionMax.length() == 0  )
							errors.add("optionMax", new org.apache.struts.action.ActionError("error.field.required"));
						else
							if( !Utils.isInt(optionMax) )
								errors.add("optionMax", new org.apache.struts.action.ActionError("error.field.invalid.format"));
							else
								maxOK = true;
					  }

					if ( minOK && maxOK )
					  if ( new Integer( optionMin ).intValue() >= new Integer( optionMax ).intValue() )
					    errors.add("optionMin", new org.apache.struts.action.ActionError("error.field.lower.than.max"));
						 
				  
					if( optionPrice ==null  )
						errors.add("optionPrice", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						optionPrice=optionPrice.trim();
						if (  optionPrice.length() == 0  )
							errors.add("optionPrice", new org.apache.struts.action.ActionError("error.field.required"));
						else
							if( !Utils.isDouble(optionPrice) )
								errors.add("optionPrice", new org.apache.struts.action.ActionError("error.field.invalid.format"));
					  }
					
					
				  }
				else if( process.equals("updateOption") )
				  {
					if( id_option==null  )
						  errors.add("id_option", new org.apache.struts.action.ActionError("error.must.select.value"));
					else
					  {
						id_option=id_option.trim().toUpperCase();
						if (  id_option.length() == 0  )
							errors.add("id_option", new org.apache.struts.action.ActionError("error.must.select.value"));
					  }
					
					if( optionPrice ==null  )
						errors.add("optionPrice", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						optionPrice=optionPrice.trim();
						if (  optionPrice.length() == 0  )
							errors.add("optionPrice", new org.apache.struts.action.ActionError("error.field.required"));
						else
							if( !Utils.isDouble(optionPrice) )
								errors.add("optionPrice", new org.apache.struts.action.ActionError("error.field.invalid.format"));
					  }
				  }
				else if( process.equals("createMenu") || process.equals("updateMenu") )
				  {
					if( id_option==null  )
					  errors.add("id_option", new org.apache.struts.action.ActionError("error.must.select.value"));
					else
					  {
						id_option=id_option.trim().toUpperCase();
						if (  id_option.length() == 0  )
							errors.add("id_option", new org.apache.struts.action.ActionError("error.must.select.value"));
					  }
					
					if( menuName==null  )
						  errors.add("menuName", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						menuName=menuName.trim().toUpperCase();
						if (  menuName.length() == 0  )
							errors.add("menuName", new org.apache.struts.action.ActionError("error.field.required"));
					  }
					
					if( menuDescription==null  )
						  errors.add("menuDescription", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						menuDescription=menuDescription.trim().toUpperCase();
						if (  menuDescription.length() == 0  )
							errors.add("menuDescription", new org.apache.struts.action.ActionError("error.field.required"));
						if (  menuDescription.length() > 250  )
							errors.add("menuDescription", new org.apache.struts.action.ActionError("error.field.too.large"));
					  }

					if( price1 ==null  )
						errors.add("price1", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						price1=price1.trim();
						if (  price1.length() == 0  )
							errors.add("price1", new org.apache.struts.action.ActionError("error.field.required"));
						else
							if( !Utils.isDouble(price1) )
								errors.add("price1", new org.apache.struts.action.ActionError("error.field.invalid.format"));
					  }
				  
				  
					if( price2 ==null  )
						errors.add("price2", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						price2=price2.trim();
						if (  price2.length() == 0  )
							errors.add("price2", new org.apache.struts.action.ActionError("error.field.required"));
						else
							if( !Utils.isDouble(price2) )
								errors.add("price2", new org.apache.struts.action.ActionError("error.field.invalid.format"));
					  }
				  

					if( price3 ==null  )
						errors.add("price3", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						price3=price3.trim();
						if (  price3.length() == 0  )
							errors.add("price3", new org.apache.struts.action.ActionError("error.field.required"));
						else
							if( !Utils.isDouble(price3) )
								errors.add("price3", new org.apache.struts.action.ActionError("error.field.invalid.format"));
					  }
				  

					if( price4 ==null  )
						errors.add("price4", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						price4=price4.trim();
						if (  price4.length() == 0  )
							errors.add("price4", new org.apache.struts.action.ActionError("error.field.required"));
						else
							if( !Utils.isDouble(price4) )
								errors.add("price4", new org.apache.struts.action.ActionError("error.field.invalid.format"));
					  }
				  

					if( price5 ==null  )
						errors.add("price5", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						price5=price5.trim();
						if (  price5.length() == 0  )
							errors.add("price5", new org.apache.struts.action.ActionError("error.field.required"));
						else
							if( !Utils.isDouble(price5) )
								errors.add("price5", new org.apache.struts.action.ActionError("error.field.invalid.format"));
					  }
				  
					
					if( price6 ==null  )
						errors.add("price6", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						price6=price6.trim();
						if (  price6.length() == 0  )
							errors.add("price6", new org.apache.struts.action.ActionError("error.field.required"));
						else
							if( !Utils.isDouble(price6) )
								errors.add("price6", new org.apache.struts.action.ActionError("error.field.invalid.format"));
					  }
				  

					if( price7 ==null  )
						errors.add("price7", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						price7=price7.trim();
						if (  price7.length() == 0  )
							errors.add("price7", new org.apache.struts.action.ActionError("error.field.required"));
						else
							if( !Utils.isDouble(price7) )
								errors.add("price7", new org.apache.struts.action.ActionError("error.field.invalid.format"));
					  }
					
					if ( process.equals("updateMenu") )
						if( id_menu==null  )
							  errors.add("id_menu", new org.apache.struts.action.ActionError("error.must.select.value"));
						else
						  {
							id_menu=id_menu.trim().toUpperCase();
							if (  id_menu.length() == 0  )
								errors.add("id_menu", new org.apache.struts.action.ActionError("error.must.select.value"));
						  }
				  
				  }
				else if( process.equals("deleteOption"))
				  {
					if( id_option==null  )
					  errors.add("id_option", new org.apache.struts.action.ActionError("error.must.select.value"));
					else
					  {
						id_option=id_option.trim().toUpperCase();
						if (  id_option.length() == 0  )
							errors.add("id_option", new org.apache.struts.action.ActionError("error.must.select.value"));
					  }
				  }
				
				else if( process.equals("deleteMenu"))
				  {
					if( id_menu==null  )
					  errors.add("id_menu", new org.apache.struts.action.ActionError("error.must.select.value"));
					else
					  {
						id_menu=id_menu.trim().toUpperCase();
						if (  id_menu.length() == 0  )
							errors.add("id_menu", new org.apache.struts.action.ActionError("error.must.select.value"));
					  }
				  }
					

			  }

			return errors;

		}

	
	
	
	public String getMenuDescription() {
		return menuDescription;
	}




	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}




	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}



	public String getOptionMin() {
		return optionMin;
	}

	public void setOptionMin(String optionMin) {
		this.optionMin = optionMin;
	}

	public String getOptionMax() {
		return optionMax;
	}

	public void setOptionMax(String optionMax) {
		this.optionMax = optionMax;
	}

	public String getOptionPrice() {
		return optionPrice;
	}

	public void setOptionPrice(String optionPrice) {
		this.optionPrice = optionPrice;
	}

	
	public String getListDescription() {
		return listDescription;
	}

	public void setListDescription(String listDescription) {
		this.listDescription = listDescription;
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

	
	
	

	
	public String getPrice1() {
		return price1;
	}

	public void setPrice1(String price1) {
		this.price1 = price1;
	}

	public String getPrice2() {
		return price2;
	}

	public void setPrice2(String price2) {
		this.price2 = price2;
	}

	public String getPrice3() {
		return price3;
	}

	public void setPrice3(String price3) {
		this.price3 = price3;
	}

	public String getPrice4() {
		return price4;
	}

	public void setPrice4(String price4) {
		this.price4 = price4;
	}

	public String getPrice5() {
		return price5;
	}

	public void setPrice5(String price5) {
		this.price5 = price5;
	}

	public String getPrice6() {
		return price6;
	}

	public void setPrice6(String price6) {
		this.price6 = price6;
	}

	public String getPrice7() {
		return price7;
	}

	public void setPrice7(String price7) {
		this.price7 = price7;
	}

	public String getId_list() {
		return id_list;
	}

	public void setId_list(String id_list) {
		this.id_list = id_list;
	}

	

	public String getDate_reg() {
		return date_reg;
	}

	public void setDate_reg(String date_reg) {
		this.date_reg = date_reg;
	}


	




	/**
	 * @return
	 * Obtiene el valor de la variable id
	 */
	public int getId() {
		return id;
	}

	

	/**
	 * @return
	 * Obtiene el valor de la variable id_type_case
	 */

	






	/**
	 * @param i
	* Asigna el valor de i a la variable id
	 */
	public void setId(int i) {
		id = i;
	}

	
	/**
	  * @param string
    	 * Asigna un valor a la variable id_type_case
	 */

	





	/**
	 * @return
 	 * Obtiene el valor de la variable process
	 */
	public String getProcess() {
		return process;
	}

	/**
	  * @param string
 	 * Asigna un valor a la variable process
	 */
	public void setProcess(String string) {
		process = string;
	}



	


	
}
