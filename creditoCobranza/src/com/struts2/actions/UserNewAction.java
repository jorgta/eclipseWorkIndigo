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
public class UserNewAction extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730294291529491868L;
	
	private String name;
	private String password;
	private String address;
	private String email;
	private String active;
	
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
        
		if ( name != null )
		  {
			name = name.trim();
			if (name.length() == 0)
				this.addFieldError("name",getText("error.field.required"));
			if (name.indexOf(" ") > 0 )
			this.addFieldError("name",getText("error.field.required.no.space"));
		  }
		else
			this.addFieldError("name",getText("error.field.invalid.format"));
		
	
		if ( password != null )
		  {
			password = password.trim();
			if (password.length() == 0)
				this.addFieldError("password",getText("error.field.required"));
			if (password.indexOf(" ") > 0 )
			this.addFieldError("password",getText("error.field.required.no.space"));
		  }
		else
			this.addFieldError("password",getText("error.field.required"));


		if ( address != null )
		  {
			address = address.trim();
			if (address.length() == 0)
				this.addFieldError("address",getText("error.field.required"));
			if (address.indexOf(" ") > 0 )
			this.addFieldError("address",getText("error.field.required.no.space"));
		  }
		else
			this.addFieldError("password",getText("error.field.required"));
		
		if ( email != null )
		  {
			email = email.trim();
			if (email.length() == 0)
				this.addFieldError("email",getText("error.field.required"));
			if (email.indexOf(" ") > 0 )
			this.addFieldError("email",getText("error.field.required.no.space"));
		  }
		else
			this.addFieldError("email",getText("error.field.required"));
		
		
       
		if ( !Utils.isEmail( email) )
			this.addFieldError("email",getText("error.field.invalid.format"));
		
         
         //addActionMessage("Bienvenido " + name + "");
        
        /*if (active.equals("")) {
            this.addFieldError("address",getText("Company can't be blanked"));
            }*/
  
        
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
		 
					String query =   " FROM BeanUser u" 
					               + " WHERE u.id_company.name = '" +  beanUser.getId_company().getName() + "'" 
					               + " AND u.name='" + getName() + "'";
								    
						
					List list = session.createQuery(query).list();
					
					if (list.isEmpty()) 
					{  
						BeanUser bean = new BeanUser();
						tx = session.beginTransaction();
						bean.setId_company( beanUser.getId_company() );
						bean.setName( getName().toUpperCase() );
						bean.setPassword( getPassword() );
						
						bean.setAddress( getAddress() );
						bean.setEmail( getEmail() );
						if(getActive().equals("true"))
						  bean.setActive( 1 );
						else
						  bean.setActive( 0 );
					
						session.save( bean );
						tx.commit();
						
						
						
						//addActionMessage("Usuario registrado correctamente!");	
						 
						//forward = "s";
						
						
						if (new SessionUtil().prepareDataForUserNew(request,beanUser,session) != 1) 
						 {  				
							  forward = "ERROR";
						 }
						httpSession.setAttribute("done", "Usuario registrado correctamente!");
	
					}
					else
					  {
						  //errors.add("username", new org.apache.struts.action.ActionError("error.notexist.username"));
						httpSession.setAttribute("error", "Usuario registrado anteriormente!");					 
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getActive() {
		return active;
	}


	public void setActive(String active) {
		this.active = active;
	}



	
	

}
