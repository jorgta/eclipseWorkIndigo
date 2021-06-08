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
public class UserActivateAction extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730294291529491868L;
	
	private String id;
	private String process;

	
	private HttpSession httpSession =null;
	private HttpServletRequest request = null;
	//private static final Log  log  = (Log) LogFactory.getLog(LoginAction.class);
	
	//@RequiredStringValidator(fieldName = "username", type = ValidatorType.FIELD, message = "Login User is required")
	 
	public String forward()
	{
		return ERROR;
	}
	
	
	public void validate() {	
        String fieldr="Campo requerido";
        String fieldnospaces ="El campo no tener espacion";
        String fieldinvalid ="Formato Invalido";
        
        HttpServletRequest request = ServletActionContext.getRequest();
        httpSession = request.getSession(); 
        httpSession.setAttribute("module","pre");
        httpSession =null;
    	request = null;
    	
        
		/*if ( id != null )
		  {
			id = id.trim();
			if (id.length() == 0)
				this.addFieldError("id",getText(fieldr));
			if (id.indexOf(" ") > 0 )
			this.addFieldError("id",getText(fieldnospaces));
		  }
		else
			this.addFieldError("id",getText(fieldr));
		*/
		 




		
         
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
					
					
					
					if (process.equals("usersActivate"))
					  {
						
						List<BeanUser> listUser2Activate = (ArrayList<BeanUser>)httpSession.getAttribute("listUser2Activate");
		                
						int users=listUser2Activate.size();
		                Iterator<BeanUser> iter = listUser2Activate.iterator();						
						while ( iter.hasNext() )
						  {
							BeanUser bean = (BeanUser) iter.next();		                
						    bean.setActive(1);
							tx = session.beginTransaction();
							session.update( bean );
							tx.commit();
							
						  }
						
						if(users > 1)
						  httpSession.setAttribute("done", new String("Usuarios activados"));
						else
						  httpSession.setAttribute("done", new String("Usuario activado"));
						
				
						
						if (new SessionUtil().prepareDataForUserActivate(request,beanUser,session) == 1) 
						 {
						   			
						   forward = "success";
						 }
					    else
						 {								  
	
						   forward = "error";
						 }
					
					  }
					else if (process.equals("userAdd2Activate"))				  
					  {
						//BeanUser bean = (BeanUser) session.load( BeanUser.class, new Integer( getId().toString() ) );
						String query =  " FROM BeanUser u" +
										" WHERE u.id = " + getId()+
										" AND u.active = 0";
		                
						
						List beanList = session.createQuery( query ).list();
						if(!beanList.isEmpty())
						{
							BeanUser bean = (BeanUser)beanList.get(0);
							bean.setActive(1);
			                
			                List listUser2Activate = (ArrayList<BeanUser>)httpSession.getAttribute("listUser2Activate");
			                
			                if(listUser2Activate==null)		
			                {	
			                  listUser2Activate = new ArrayList<BeanUser>();
			                  listUser2Activate.add(bean);
			                }else
			                {
			                	listUser2Activate.add(bean);
			                	 
			                }
		
			                httpSession.setAttribute("listUser2Activate", listUser2Activate);
						}else
						{
							query = " FROM BeanUser u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active = 0" +										
					  				" ORDER BY u.name";
						
							List list = session.createQuery( query ).list();
 
							httpSession.setAttribute("listUserActivate", list );				
			 
						}
		                 
		                
					  }
					else if (process.equals("userRemove2Activate"))				  
					  {
						//BeanUser bean = (BeanUser) session.load( BeanUser.class, new Integer( getId().toString() ) );		                
		                //bean.setActive("Y");
		                
		                List listUser2Activate = (ArrayList<BeanUser>)httpSession.getAttribute("listUser2Activate");
		                
		                if(listUser2Activate != null)
		                {
		                	Iterator iter = listUser2Activate.iterator();						
							while ( iter.hasNext() )
							  {
								BeanUser bean = (BeanUser) iter.next();
								if( bean.getId() == Integer.valueOf(getId()) ) 
								{
								   listUser2Activate.remove(bean);
								   break;
								}
							  }	
		                }
		                
		                
		                
		                	 
		                
		                httpSession.removeAttribute("listUser2Activate");
		                
		                httpSession.setAttribute("listUser2Activate", listUser2Activate);
					  }
	
				}

				
				
		  } 
		catch (Exception e) 
		  {
			e.printStackTrace();
			
			
		 
			httpSession.setAttribute("error", "Ocurrio un error: "+ e.getMessage() );
							
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
