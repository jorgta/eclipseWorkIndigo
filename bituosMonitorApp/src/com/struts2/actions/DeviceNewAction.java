package com.struts2.actions;




import java.util.*;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;




import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.ExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import com.struts2.Beans.*;
import com.bituos.*;

import org.hibernate.classic.Session;

/*
@Validations(requiredStrings = { 
	    @RequiredStringValidator(fieldName = "username", type = ValidatorType.FIELD, message = "Login User is required"), 
	    @RequiredStringValidator(fieldName = "password", type = ValidatorType.FIELD, message = "Password is required")
	}, expressions = {
	  @ExpressionValidator(expression = "company.trim().equals('bituos') == true", message = "Empresa debe ser bituos."),

	})*/
public class DeviceNewAction extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730294291529491868L;
	
	private String hostname;
	private String cpuID;
	private String mbID;

	
	private HttpSession httpSession =null;
	private HttpServletRequest request = null;
	//private static final Log  log  = (Log) LogFactory.getLog(LoginAction.class);
	
	//@RequiredStringValidator(fieldName = "username", type = ValidatorType.FIELD, message = "Login User is required")
	 

	
	
	public void validate() {	

        
        HttpServletRequest request = ServletActionContext.getRequest();
        httpSession = request.getSession(); 
        httpSession.setAttribute("module","pre");
        httpSession =null;
    	request = null;
        
		if ( hostname != null )
		  {
			hostname = hostname.trim().toUpperCase();
			if (hostname.length() == 0)
				this.addFieldError("hostname",getText("error.field.required"));
			if (hostname.indexOf(" ") > 0 )
			this.addFieldError("hostname",getText("error.field.required.no.space"));
		  }
		else
			this.addFieldError("hostname",getText("error.field.invalid.format"));
		
	
		if ( cpuID != null )
		  {
			cpuID = cpuID.trim().toUpperCase();
			if (cpuID.length() == 0)
			  this.addFieldError("cpuID",getText("error.field.required"));
			if (cpuID.indexOf(" ") > 0 )
			  this.addFieldError("cpuID",getText("error.field.required.no.space"));
		  }
		else
			this.addFieldError("cpuID",getText("error.field.required"));

		if ( mbID != null )
		  {
			mbID = mbID.trim().toUpperCase();
			if (mbID.length() == 0)
			  this.addFieldError("mbID",getText("error.field.required"));
			if (mbID.indexOf(" ") > 0 )
			  this.addFieldError("mbID",getText("error.field.required.no.space"));
		  }
		else
			this.addFieldError("mbID",getText("error.field.required"));


      
}

	 
	public String  execute() throws Exception  {
			//ActionErrors errors = new ActionErrors();
			//ActionForward forward = new ActionForward();
			 String forward=SUCCESS;
			// return value
	
			//LoginForm loginForm = (LoginForm) form;
	
		//	httpSession = request.getSession();
			HttpServletRequest request = ServletActionContext.getRequest();
			httpSession = request.getSession(); 
			 
			Config config = new Config( request );
			
			
			SessionFactory sessionFactory = null;
			
			Session    session = null;
			Transaction tx = null;
		
				
				
			try 
			  {

				                   
				sessionFactory = config.getConfiguration(request).buildSessionFactory();
				session = sessionFactory.openSession();	
				
				BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");
				if ( beanUser == null )
				{
					forward =  "logout";
				}
				else	  
				{
		 
					String query =   " FROM BeanDevice u" 
					               + " WHERE u.id_company.id = '" +  beanUser.getId_company().getId() + "'";
								    
						
					List list = session.createQuery(query).list();
					
					if (list.isEmpty()) 
					{  
						BeanDevice bean = new BeanDevice();
						
						tx = session.beginTransaction();
						
				        bean.setHostname( getHostname());
				        bean.setCpuid( getCpuID() );
				        bean.setMbid( getMbID() );
						bean.setId_company( beanUser.getId_company() );
					
						session.save( bean );
						
						tx.commit();
						

						if (new SessionUtil().prepareDataForUserNew(request,beanUser,session) != 1) 
						 {  				
							  forward = "ERROR";
						 }
						
						httpSession.setAttribute("done", "Usuario registrado correctamente!");
	
					}
					else
					  {
						  //errors.add("username", new org.apache.struts.action.ActionError("error.notexist.username"));
						httpSession.setAttribute("error", "Ya existe dispositivo con ese nombre");					 
						forward = ERROR;
					  }
				}
				
		  } 
		catch (Exception e) 
		  {
			e.printStackTrace();
			
			httpSession.setAttribute("module","pre"); 
			httpSession.setAttribute("error", "Ocurrio un error interno : "+ e.getMessage() + " consulte a soporte tecnico");	
							
			forward = ERROR;
						
			//errors.add("unknown", new org.apache.struts.action.ActionError("error.unknow"));
		  }
		finally	
		  {
			if ( session != null )
				 session.close(); 
				  
			if ( sessionFactory != null )
				 sessionFactory.close();
		  }

		// Write logic determining how the user should be forwarded.

		/*if (forward !="") {
			
			forward = ERROR;
		}
		else 
		   forward = "links";*/


		// Finish with
		return (forward);

	}


	public String getHostname() {
		return hostname;
	}


	public void setHostname(String hostname) {
		this.hostname = hostname;
	}


	public String getCpuID() {
		return cpuID;
	}


	public void setCpuID(String cpuID) {
		this.cpuID = cpuID;
	}


	public String getMbID() {
		return mbID;
	}


	public void setMbID(String mbID) {
		this.mbID = mbID;
	}





	
	
	
	

}
