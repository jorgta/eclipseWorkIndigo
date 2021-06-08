package com.emptyProject.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.bituos.Utils;

/**
 * Form bean for a Struts application.
 * Users may access 1 field on this form:
 * <ul>
 * <li>process - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class RptForm extends ActionForm {

	private String process = null;
	private String p1 = null;
	private String p2 = null;
	private String p3 = null;
	private String p4 = null;
	private String p5 = null;
	private String p6 = null;
	private String p7 = null;
	private String p8 = null;
	private String p9 = null;
	private String p10 = null;


	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		process = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.trim().length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }
		
		
		if( process != null )
		  {
			/*if ( process.equals("sales") )
			  {
				if(  p1==null  )
					errors.add("p1", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					p1=p1.trim();
					if (  p1.length() == 0  )
						errors.add("p1", new org.apache.struts.action.ActionError("error.field.required"));
					else
						if ( Utils.before1( Utils.StrToDate1(p1),  Utils.Today())  )
							errors.add("p1", new org.apache.struts.action.ActionError("error.field.lower.date"));
				  }
				
				if(  p2==null  )
					errors.add("p2", new org.apache.struts.action.ActionError("error.field.required"));
				else
				  {
					p2=p2.trim();
					if (  p2.length() == 0  )
						errors.add("p2", new org.apache.struts.action.ActionError("error.field.required"));
					else
						if ( Utils.before1( Utils.StrToDate1(p2),  Utils.Today())  )
							errors.add("p2", new org.apache.struts.action.ActionError("error.field.lower.date"));
				  }
				
			  }*/
			
			
		  }
		
		
		
		
		return errors;

	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getP3() {
		return p3;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}

	public String getP4() {
		return p4;
	}

	public void setP4(String p4) {
		this.p4 = p4;
	}

	public String getP5() {
		return p5;
	}

	public void setP5(String p5) {
		this.p5 = p5;
	}

	public String getP6() {
		return p6;
	}

	public void setP6(String p6) {
		this.p6 = p6;
	}

	public String getP7() {
		return p7;
	}

	public void setP7(String p7) {
		this.p7 = p7;
	}

	public String getP8() {
		return p8;
	}

	public void setP8(String p8) {
		this.p8 = p8;
	}

	public String getP9() {
		return p9;
	}

	public void setP9(String p9) {
		this.p9 = p9;
	}

	public String getP10() {
		return p10;
	}

	public void setP10(String p10) {
		this.p10 = p10;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
