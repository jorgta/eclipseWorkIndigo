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
public class LoginAction extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730294291529491868L;
	
	private String company = null;
	private String password = null;
	private String username = null;
	private String echo;
	private HttpSession httpSession =null;
	private HttpServletRequest request = null;
	//private static final Log  log  = (Log) LogFactory.getLog(LoginAction.class);
	
	//@RequiredStringValidator(fieldName = "username", type = ValidatorType.FIELD, message = "Login User is required")
	 
	public String forward()
	{
		return ERROR;
	}
	
	
	public void validate() {	

        if (username.isEmpty()) {
        this.addFieldError("username",getText("Username can't be blanked"));
        } else {
            addActionMessage("Welcome " + username + ", You have been Successfully Logged in");
          }
        
        if (password.isEmpty()) {
            this.addFieldError("password",getText("Password can't be blanked"));
            }   
        
        
        if (company.isEmpty()) {
           this.addFieldError("company",getText("Company can't be blanked"));
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
				
				String query =   " FROM BeanUser u" 
				               + " WHERE u.id_company.name = '" +  getCompany() + "'"  
							   + " AND   u.name = '" +  getUsername().toUpperCase() + "'"  
	                           + " AND   u.password = '" +  getPassword() + "'" 
							   + " AND   u.id_company.active = 'Y'";  
					
				List list = session.createQuery(query).list();
					
				if (!list.isEmpty()){   
					
					BeanUser beanUser = new BeanUser ();
					
					beanUser = (BeanUser) list.get(0);	
					
					if (beanUser.getActive().equals("Y")) 
					  {  
						SecurityUtil util = new SecurityUtil();
					
						//util.createProcessList(request, beanUser.getId() );
						if ( util.createProcessList(request, beanUser.getId()) != 0 )
						  {
							com.bituos.Error error = new com.bituos.Error( "Error aplicando las directivas de seguridad", request);
									
							//errors.add("unknown", new org.apache.struts.action.ActionError("error.unknow"));
							httpSession.setAttribute("done", new String("login.jsp"));
						  }
						else
						  {
							httpSession.setAttribute("beanUser", beanUser ); 
							new Log().registerEvent("login", 1, request, session);

						/*	new com.bituos.Mail( "sanchez_aroche@yahoo.es",
													"", 
													"eventAdmin: Login: " + beanUser.getName() + " (" + beanUser.getEmail() + ")",
													request);*/
                             forward = SUCCESS;
						  }
						
					  } 
				    else
				      { 
						//errors.add("username", new org.apache.struts.action.ActionError("error.notactive.user"));    
						com.bituos.Error error = new com.bituos.Error("Usuario inactivo.", request);
						httpSession.setAttribute("done", new String("login.jsp"));
				      }
	
				}
				else
				  {
					  //errors.add("username", new org.apache.struts.action.ActionError("error.notexist.username"));
					  com.bituos.Error error = new com.bituos.Error("Usuario inexistente o contrase?a incorrecta.", request);
					  httpSession.setAttribute("done", new String("login.jsp"));
					  forward = ERROR;
				  }
		  } 
		catch (Exception e) 
		  {
			e.printStackTrace();
			
			com.bituos.Error error = new com.bituos.Error( e.getMessage(), request);
							
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
	
	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

}
