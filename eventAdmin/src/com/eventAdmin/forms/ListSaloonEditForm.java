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

public class ListSaloonEditForm extends ActionForm {

	private int id;
	
	private String process;
	

	private String id_saloon;
	private String description;
	private String min;
	private String max;
	private String h0;
	private String h1;
	private String h2;
	private String h3;
	private String h4;
	private String h5;
	private String h6;
	private String h7;
	private String h8;
	private String h9;
	private String h10;
	private String h11;
	private String h12;
	private String h13;
	private String h14;
	private String h15;
	private String h16;
	private String h17;
	private String h18;
	private String h19;
	private String h20;
	private String h21;
	private String h22;
	private String h23;
	
	
	
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
				
				if( process.equals("createSaloon") || process.equals("updateSaloon") )
				  {
					boolean minOK = false;
					boolean maxOK = false;

					if ( description==null  )
						errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						description=description.trim().toUpperCase();
						if (  description.length() == 0  )
							errors.add("description", new org.apache.struts.action.ActionError("error.field.required"));
					  }
					
					if( min ==null  )
						errors.add("min", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						min=min.trim();
						if (  min.length() == 0  )
							errors.add("min", new org.apache.struts.action.ActionError("error.field.required"));
						else
							if( !Utils.isInt(min) )
								errors.add("min", new org.apache.struts.action.ActionError("error.field.invalid.format"));
							else
								minOK = true;
					  }

				  
					if( max ==null  )
						errors.add("max", new org.apache.struts.action.ActionError("error.field.required"));
					else
					  {
						max=max.trim();
						if (  max.length() == 0  )
							errors.add("max", new org.apache.struts.action.ActionError("error.field.required"));
						else
							if( !Utils.isInt(max) )
								errors.add("max", new org.apache.struts.action.ActionError("error.field.invalid.format"));
							else
								maxOK = true;
					  }

					if ( minOK && maxOK )
					  if ( new Integer( min ).intValue() >= new Integer( max ).intValue() )
					    errors.add("min", new org.apache.struts.action.ActionError("error.field.lower.than.max"));
						 

					if ( process.equals("updateList") )
						if( id_saloon==null  )
							  errors.add("id_saloon", new org.apache.struts.action.ActionError("error.must.select.value"));
						else
						  {
							id_saloon=id_saloon.trim().toUpperCase();
							if (  id_saloon.length() == 0  )
								errors.add("id_saloon", new org.apache.struts.action.ActionError("error.must.select.value"));
						  }
					
					
					if(  h0 == null  )
						h0 = "off";
					
					if(  h1 == null  )
						h1 = "off";
					
					if(  h2 == null  )
						h2 = "off";
					
					if(  h3 == null  )
						h3 = "off";
					
					if(  h4 == null  )
						h4 = "off";
					
					if(  h5 == null  )
						h5 = "off";
					
					if(  h6 == null  )
						h6 = "off";
					
					if(  h7 == null  )
						h7 = "off";
					
					if(  h8 == null  )
						h8 = "off";
					
					if(  h9 == null  )
						h9 = "off";
					
					if(  h10 == null  )
						h10 = "off";
					
					if(  h11 == null  )
						h11 = "off";
					
					if(  h12 == null  )
						h12 = "off";
					
					if(  h13 == null  )
						h13 = "off";
					
					if(  h14 == null  )
						h14 = "off";
					
					if(  h15 == null  )
						h15 = "off";
					
					if(  h16 == null  )
						h16 = "off";
					
					if(  h17 == null  )
						h17 = "off";
					
					if(  h18 == null  )
						h18 = "off";
					
					if(  h19 == null  )
						h19 = "off";
					
					if(  h20 == null  )
						h20 = "off";
					
					if(  h21 == null  )
						h21 = "off";
					
					if(  h22 == null  )
						h22 = "off";
					
					if(  h23 == null  )
						h23 = "off";
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



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getId_saloon() {
		return id_saloon;
	}



	public void setId_saloon(String id_saloon) {
		this.id_saloon = id_saloon;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getMin() {
		return min;
	}



	public void setMin(String min) {
		this.min = min;
	}



	public String getMax() {
		return max;
	}



	public void setMax(String max) {
		this.max = max;
	}



	public String getH0() {
		return h0;
	}



	public void setH0(String h0) {
		this.h0 = h0;
	}



	public String getH1() {
		return h1;
	}



	public void setH1(String h1) {
		this.h1 = h1;
	}



	public String getH2() {
		return h2;
	}



	public void setH2(String h2) {
		this.h2 = h2;
	}



	public String getH3() {
		return h3;
	}



	public void setH3(String h3) {
		this.h3 = h3;
	}



	public String getH4() {
		return h4;
	}



	public void setH4(String h4) {
		this.h4 = h4;
	}



	public String getH5() {
		return h5;
	}



	public void setH5(String h5) {
		this.h5 = h5;
	}



	public String getH6() {
		return h6;
	}



	public void setH6(String h6) {
		this.h6 = h6;
	}



	public String getH7() {
		return h7;
	}



	public void setH7(String h7) {
		this.h7 = h7;
	}



	public String getH8() {
		return h8;
	}



	public void setH8(String h8) {
		this.h8 = h8;
	}



	public String getH9() {
		return h9;
	}



	public void setH9(String h9) {
		this.h9 = h9;
	}



	public String getH10() {
		return h10;
	}



	public void setH10(String h10) {
		this.h10 = h10;
	}



	public String getH11() {
		return h11;
	}



	public void setH11(String h11) {
		this.h11 = h11;
	}



	public String getH12() {
		return h12;
	}



	public void setH12(String h12) {
		this.h12 = h12;
	}



	public String getH13() {
		return h13;
	}



	public void setH13(String h13) {
		this.h13 = h13;
	}



	public String getH14() {
		return h14;
	}



	public void setH14(String h14) {
		this.h14 = h14;
	}



	public String getH15() {
		return h15;
	}



	public void setH15(String h15) {
		this.h15 = h15;
	}



	public String getH16() {
		return h16;
	}



	public void setH16(String h16) {
		this.h16 = h16;
	}



	public String getH17() {
		return h17;
	}



	public void setH17(String h17) {
		this.h17 = h17;
	}



	public String getH18() {
		return h18;
	}



	public void setH18(String h18) {
		this.h18 = h18;
	}



	public String getH19() {
		return h19;
	}



	public void setH19(String h19) {
		this.h19 = h19;
	}



	public String getH20() {
		return h20;
	}



	public void setH20(String h20) {
		this.h20 = h20;
	}



	public String getH21() {
		return h21;
	}



	public void setH21(String h21) {
		this.h21 = h21;
	}



	public String getH22() {
		return h22;
	}



	public void setH22(String h22) {
		this.h22 = h22;
	}



	public String getH23() {
		return h23;
	}



	public void setH23(String h23) {
		this.h23 = h23;
	}

	
	



	


	
}
