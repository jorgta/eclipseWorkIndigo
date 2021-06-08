package com.bituos.truckAdmin.forms;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.bituos.truckAdmin.*; 
import com.bituos.truckAdmin.Beans.BeanTruck;
import com.bituos.truckAdmin.Beans.BeanTypeMeasure;
import com.bituos.truckAdmin.Beans.BeanTypePlace;
import com.bituos.truckAdmin.Beans.BeanTypeTireStatus;
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
public class TireDeleteForm extends ActionForm {

	private String id_tire;

	
	private String process;
	

	
	
	public String getId_tire() {
		return id_tire;
	}
	public void setId_tire(String id_tire) {
		this.id_tire = id_tire;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	


}
