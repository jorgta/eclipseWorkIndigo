package com.struts2.actions;




import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class PreAction extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730294291529491868L;
	
	private String process = null;
	private String echo;
	private String userRoot ="AND u.name <> 'ROOT'";
	private HttpSession httpSession =null;
	private HttpServletRequest request = null;
	//private static final Log  log  = (Log) LogFactory.getLog(LoginAction.class);
	
	//@RequiredStringValidator(fieldName = "username", type = ValidatorType.FIELD, message = "Login User is required")
	 
	public String forward()
	{
		return ERROR;
	}
	
	
	public void validate() {	

        if (process.isEmpty()) 
          {
            this.addFieldError("process",getText("El proceso no es valido"));
          } 
       
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
					//sessionFactory = HibernateUtil.getSessionFactory();
					session = sessionFactory.openSession();	
					
					BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");
					if ( beanUser == null )
						  forward =  "logout";
					else if (process.equals("logout"))
						 {
							httpSession.removeAttribute("beanUser");
							forward =  "logout";
						 }
					else if (process.equals("userNew")) 
						 {
							if (new SessionUtil().prepareDataForUserNew(request,beanUser,session) == 1) 
							 {  				
								  forward = "userNew";
							 }
							else
							  {				 
								  
								  forward = "userNewError";
							  }
						 } 
					else if (process.equals("userDelete"))
						 {
							if (new SessionUtil().prepareDataForUserDelete(request,beanUser,session) == 1) 
							  {								   			
								   forward = "userDelete";
							  }
							 else
							  {
							    forward = "userDeleteError";
							  }
							 }
					else if (process.equals("userActivate"))
						 {								
			
						     if (new SessionUtil().prepareDataForUserActivate(request,beanUser,session) == 1) 
								 {
								   			
								   forward = "userActivate";
								}
							 else
								 {								  
			
								   forward = "userActivateError";
								 }
						 }
					else if (process.equals("userChange")) 
						 {
							if (new SessionUtil().prepareDataForUserChange(request,beanUser,session) == 1) 
							  {								   			
								   forward = "userChange";
							  }
							 else
							  {
							    forward = "userChangeError";
							  }
			
						 }	
					else if ( process.equals("companyNew") )  //new company
						 {
							if (new SessionUtil().prepareDataForCompanyNew(request,beanUser,session) == 1) 
							  {								   			
								forward = "companyNew";
							  }
							 else
							  {
								 forward = "companyNewError";
							  }
							 
						 }
					else if (process.equals("companyDelete"))
					 {
										
						if (new SessionUtil().prepareDataForCompanyDelete(request,beanUser,session) == 1) 
							{							  				   
							  
					          forward = "companyDelete";
							}
						else
							{
							  
							  	 
					          forward = "companyDeleteError";
							}

					 }
					else if (process.equals("companyActivate"))
					 {								
		
					     if (new SessionUtil().prepareDataForCompanyActivate(request,beanUser,session) == 1) 
							 {
							   			
							   forward = "companyActivate";
							}
						 else
							 {								  
		
							   forward = "companyActivateError";
							 }
					 }
					else if (process.equals("companyChange"))
					 {								
		
					     if (new SessionUtil().prepareDataForCompanyChange(request,beanUser,session) == 1) 
							 {
							   			
							   forward = "companyChange";
							}
						 else
							 {								  
		
							   forward = "companyChangeError";
							 }
					 }
					else if (process.equals("rptLog"))
					 {								
								
						if (new SessionUtil().prepareRptLog(request,beanUser,session) == 1) 
						 {
						   			
							forward = "rptLog";
						 }
						 else
						 {								  
	
						   forward = "rptLogError";
						 }
					
					
					 }	
					else if (process.equals("processAdmin"))
					 {								
								
						if (new SessionUtil().prepareProcessAdmin(request,beanUser,session) == 1) 
						 {
						   			
							forward = "processAdmin";
						 }
						else
						 {								  
	
						   forward = "processAdminNewError";
						 }
					
					
					
					 }	
					 else
					 {
						 httpSession.setAttribute("error", "Opción no programada");
						 forward = ERROR; 
					 }
					
					
		  } 
		catch (Exception e) 
		  {
			e.printStackTrace();
			httpSession.setAttribute("error", "Ocurrio un error: "+ e.getMessage());
								
			forward = ERROR;
						
		  }
		finally	
		  {
			if ( session != null )
				 session.close(); 
				  
			if ( sessionFactory != null )
				 sessionFactory.close();
		  }

		return (forward);

	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}


	public String getUserRoot() {
		return userRoot;
	}


	public void setUserRoot(String userRoot) {
		this.userRoot = userRoot;
	}
	
	
	
	

}
