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
public class HotelActivateAction extends ActionSupport  {

	private static final long serialVersionUID = -6730294291529491868L;
	private String id;
	private String process;
	private HttpSession httpSession =null;
	private HttpServletRequest request = null;
 
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
   
          
}

	 
	public String  execute() throws Exception  {

		String forward=SUCCESS;
		
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
					  forward =  "logout";
				else
				{
					if (process.equals("hotelsActivate"))
					{
						if(request.getParameterValues("hotels")!= null)
						{
							String [] hotelSelected = request.getParameterValues("hotels");
							tx = session.beginTransaction();
							for (int i = 0; i < hotelSelected.length; i++) 
							{
								BeanHotel bean = (BeanHotel) session.load( BeanHotel.class, new Integer( hotelSelected[i] ) );
								bean.setActive(1);
								session.update( bean );
								
							}
							tx.commit();
							if(hotelSelected.length > 1)
								httpSession.setAttribute("done", new String("Hoteles activados"));
							else
								httpSession.setAttribute("done", new String("Hotel activado"));

							if (new SessionUtil().prepareDataForHotelActivate(request,beanUser,session) != 1) 
							{  				
						    	forward = "error";
							}
							else
							{  	
								forward = "success";
						
		             		}	
						}
						else
							httpSession.setAttribute("error", "Debe seleccionar el/los hotel(es) a activar");
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
