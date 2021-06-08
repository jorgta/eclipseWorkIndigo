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
public class UserDeleteAction extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730294291529491868L;
	
	private String id;
	private String process;
	

	
	private HttpSession httpSession =null;
	private HttpServletRequest request = null;

	
	public void validate() {	
        String fieldr="Campo requerido";
        String fieldnospaces ="El campo no debe tener espacio";
        String fieldinvalid ="Formato Invalido";
        
        HttpServletRequest request = ServletActionContext.getRequest();
        httpSession = request.getSession(); 
        httpSession.setAttribute("module","pre");
        httpSession =null;
    	request = null;
        
		if ( id != null )
		  {
			id = id.trim();
			if (id.length() == 0)
				this.addFieldError("id",getText(fieldr));
			if (id.indexOf(" ") > 0 )
			this.addFieldError("id",getText(fieldnospaces));
		  }
		else
			this.addFieldError("id",getText(fieldr));


        
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
					 
					
					if (process.equals("userDelete"))
					  {
						
		                
		                BeanUser bean = (BeanUser) session.load( BeanUser.class, new Integer( getId().toString() ) );
		                
		                bean.setActive("N");
						tx = session.beginTransaction();
						session.update( bean );
						tx.commit();
						httpSession.setAttribute("done", new String("Usuario borrado"));
						
						if (new SessionUtil().prepareDataForUserNew(request,beanUser,session) != 1) 
						 {  				
							  forward = "ERROR";
						 }
						
					  }
					else if (process.equals("userAdd2Delete"))				  
					  {
						BeanUser bean = (BeanUser) session.load( BeanUser.class, new Integer( getId().toString() ) );		                
		                bean.setActive("N");
		                
		                List listUser2Delete = (ArrayList<BeanUser>)httpSession.getAttribute("listUser2Delete");
		                
		                if(listUser2Delete==null)		
		                {	
		                	listUser2Delete = new ArrayList<BeanUser>();
		                	listUser2Delete.add(bean);
		                }else
		                {
		                	listUser2Delete.add(bean);
		                }
		                
		                
		                httpSession.setAttribute("listUser2Delete", listUser2Delete);
		                
					  }
	
				} 

				
				
		  } 
		catch (Exception e) 
		  {
			e.printStackTrace();
			
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ e.getMessage()));	
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


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getProcess() {
		return process;
	}


	public void setProcess(String process) {
		this.process = process;
	}




	
	

}
