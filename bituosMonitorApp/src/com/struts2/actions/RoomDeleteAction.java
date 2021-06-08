package com.struts2.actions;




import java.sql.Date;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import java.io.IOException; 


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.ExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import com.struts2.Beans.*;
import com.bituos.*;
import com.crystaldecisions.sdk.prompting.report.Util;

import org.hibernate.classic.Session;

/*
@Validations(requiredStrings = { 
	    @RequiredStringValidator(fieldName = "username", type = ValidatorType.FIELD, message = "Login User is required"), 
	    @RequiredStringValidator(fieldName = "password", type = ValidatorType.FIELD, message = "Password is required")
	}, expressions = {
	  @ExpressionValidator(expression = "company.trim().equals('bituos') == true", message = "Empresa debe ser bituos."),

	})*/
public class RoomDeleteAction extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730294291529491868L;
	
    
	private int id;
	private String process;
	private HttpSession httpSession =null;
	private HttpServletRequest request = null;
	private String [] rooms2Delete;
	
	
	
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
			
			   if (process.equals("DeleteRooms"))
		       {
				   if(request.getParameterValues("rooms")!= null)
					{
						String [] roomsSelected = request.getParameterValues("rooms");
						tx = session.beginTransaction();
						for (int i = 0; i < roomsSelected.length; i++) 
						{
							BeanRoom bean = (BeanRoom) session.load( BeanRoom.class, new Integer( roomsSelected[i] ) );
							bean.setActive(0);
							session.update( bean );
							
						}
						tx.commit();
						if(roomsSelected.length > 1)
							httpSession.setAttribute("done", new String("Habitaciones eliminadas"));
						else
							httpSession.setAttribute("done", new String("Habitación eliminada"));

						if (new SessionUtil().prepareDataForRoomDelete(request,beanUser,session) != 1) 
						{  				
					    	forward = "error";
						}
						else
						{  	
							forward = "success";
					
	             		}	
							
					}
					else
						httpSession.setAttribute("error", "Debe seleccionar las habitaciones a eliminar");

		       }
			   else if (process.equals("deleteOneRoom"))
		       {
				   //httpSession.removeAttribute("listRoomsHability");
				   String query =    " FROM BeanRoom r" 
					                + " WHERE r.id = " + getId();
					                
					List list = session.createQuery(query).list();
					
					if (!list.isEmpty()) 
					{  
						tx = session.beginTransaction();
						BeanRoom bean = (BeanRoom)list.get(0);							
						bean.setActive(0);
						session.save( bean );
						session.update( bean );
						tx.commit();
						httpSession.setAttribute("done", new String("Habitación eliminada"));
                       if (new SessionUtil().prepareDataForRoomDelete(request,beanUser,session) != 1) 
						{  				
					    	forward = "error";
						}
						else
						{  	
							forward = "success";
					
	             		}	
							
						
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


	// Finish with
	return (forward);

   }
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*public String getId_room() {
		return id_room;
	}

	public void setId_room(String id_room) {
		this.id_room = id_room;
	}*/

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
	public String[] getRooms2Delete() {
		return rooms2Delete;
	}

	public void setRooms2Delete(String[] rooms2Delete) {
		this.rooms2Delete = rooms2Delete;
	}


	


		
}
