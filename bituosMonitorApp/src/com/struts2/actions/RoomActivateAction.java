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
public class RoomActivateAction extends ActionSupport  {

	private static final long serialVersionUID = -6730294291529491868L;
	private String id;
	private String process;
	private HttpSession httpSession =null;
	private HttpServletRequest request = null;
	private String [] options;
 
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
				httpSession.removeAttribute("information");
				httpSession.removeAttribute("error");
				httpSession.removeAttribute("done");
				
				BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");
				if ( beanUser == null )
					  forward =  "logout";
				else
				{
					 if (process.equals("roomsActivate"))
					  {
						 if(request.getParameterValues("rooms")!= null)
							{
								String [] roomsSelected = request.getParameterValues("rooms");
								tx = session.beginTransaction();
								for (int i = 0; i < roomsSelected.length; i++) 
								{
									BeanRoom bean = (BeanRoom) session.load( BeanRoom.class, new Integer( roomsSelected[i] ) );
									bean.setActive(1);
									session.update( bean );
									
								}
								tx.commit();
								if(roomsSelected.length > 1)
									httpSession.setAttribute("done", new String("Habitaciones activadas"));
								else
									httpSession.setAttribute("done", new String("Habitaci?n activada"));

								if (new SessionUtil().prepareDataForRoomActivate(request,beanUser,session) != 1) 
								{  				
							    	forward = "error";
								}
								else
								{  	
									forward = "success";
							
			             		}	
									
							}
							else
								httpSession.setAttribute("error", "Debe seleccionar las habitaciones a activar");

					  }
				} 
		  } 
		catch (Exception e) 
		  {
			e.printStackTrace();
			
			httpSession.setAttribute("error", "Ocurrio un error: "+ e.getMessage() );
							
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

	public String[] getOptions() {
		return options;
	}


	public void setOptions(String[] options) {
		this.options = options;
	}



	
	

}
