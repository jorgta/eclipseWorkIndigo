package com.struts2.actions;




 
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.classic.Session;
import java.util.Calendar;

/*
@Validations(requiredStrings = { 
	    @RequiredStringValidator(fieldName = "username", type = ValidatorType.FIELD, message = "Login User is required"), 
	    @RequiredStringValidator(fieldName = "password", type = ValidatorType.FIELD, message = "Password is required")
	}, expressions = {
	  @ExpressionValidator(expression = "company.trim().equals('bituos') == true", message = "Empresa debe ser bituos."),

	})*/
public class ReservationDeleteAction extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730294291529491868L;
	
    
	private int id;
	private String process;
	private HttpSession httpSession =null;
	private HttpServletRequest request = null;
	

	public String forward()
	{
		return ERROR;
	}
	
	public void validate() {	
                
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
			
				if (process.equals("CancelReservation"))
				{
					String reservation_room =   " FROM BeanReservation_Rooms rr"+
					 				 			" WHERE rr.id = " + getId();
		
					java.util.List room_reservation = session.createQuery(reservation_room).list();
					if ( !room_reservation.isEmpty() )
					{
						BeanReservation_Rooms beanReservation_Rooms= (BeanReservation_Rooms)room_reservation.get(0);
						Calendar date_actual = Calendar.getInstance();
						GregorianCalendar c = new GregorianCalendar();
						GregorianCalendar date_cancel = new GregorianCalendar();
						Date date_begin = beanReservation_Rooms.getId_reservation().getDate_begin();
						c.setTime(date_begin);
						int hour2CancelReservation = beanReservation_Rooms.getId_room().getId_hotel().getId_company().getRoomCannotBeCanceledBeforeReserv();
						
						c.add(Calendar.HOUR, -hour2CancelReservation);
						date_cancel = c;
						
						if(date_cancel.after(date_actual) || date_cancel.equals(date_actual))
						{
							BeanType_Status_Room type_status = (BeanType_Status_Room)session.get(BeanType_Status_Room.class,1);
							beanReservation_Rooms = (BeanReservation_Rooms)session.get(BeanReservation_Rooms.class,beanReservation_Rooms.getId());
							BeanReservation beanReservation = (BeanReservation)session.get(BeanReservation.class,beanReservation_Rooms.getId_reservation().getId());
							BeanRoom beanRoom = (BeanRoom)session.get(BeanRoom.class,beanReservation_Rooms.getId_room().getId());
							beanRoom.setId_type_status_room(type_status);
							
							tx = session.beginTransaction();
							
							String query = " FROM BeanReservation_Person rp"+
		           			   			   " WHERE rp.id_reservation_room.id = " + beanReservation_Rooms.getId();
		           			   
						    List reservation_person = session.createQuery(query).list();
						    
						    if (!reservation_person.isEmpty()) 
							{ 
						    	for (int j = 0; j < reservation_person.size(); j++) 
						    	{
						    		BeanReservation_Person beanReservation_Person = (BeanReservation_Person)reservation_person.get(j);
									beanReservation_Person = (BeanReservation_Person)session.get(BeanReservation_Person.class,beanReservation_Person.getId());
									session.delete(beanReservation_Person);
						    	}
							}
							session.update(beanRoom);
							session.delete(beanReservation_Rooms);
							//session.delete(beanReservation);
							tx.commit();
							
							if (new SessionUtil().prepareDataForDeleteReservation(request,beanUser,session) == 1) 
							 {  
								httpSession.setAttribute("done", "Reservación cancelada con éxito");
								forward = "success";
							 }
							else
							 {				 
							    forward = "error";
							 }
							
							
						}
						else
						{
							httpSession.setAttribute("error", "Solo se puede cancelar 48h antes de la salida");
						}
						
					}
					else
					{
						httpSession.setAttribute("error", "No ha seleccionado la reservación a cancelar");
					}
								    
			    }
				else
					if (process.equals("cancelSomeRooms"))
					{
						//Falta poner el id_room en la tabla relación reservation_person para poder eliminar solamente las personas de esa reservacion pero de ese room en especifico
						if(request.getParameterValues("rooms")!= null)
						{
							String [] roomsSelected = request.getParameterValues("rooms");
							List<BeanReservation_Rooms> reservationsNotCancel = new ArrayList<BeanReservation_Rooms>();
							
							for (int i = 0; i < roomsSelected.length; i++) 
							{
								String reservation_room =   " FROM BeanReservation_Rooms rr"+
	 				 										" WHERE rr.id = " + roomsSelected[i];
							  
								java.util.List room_reservation = session.createQuery(reservation_room).list();
								if ( !room_reservation.isEmpty() )
								{
									BeanReservation_Rooms beanReservation_Rooms= (BeanReservation_Rooms)room_reservation.get(0);
									Calendar date_actual = Calendar.getInstance();
									GregorianCalendar c = new GregorianCalendar();
									GregorianCalendar date_cancel = new GregorianCalendar();
									Date date_begin = beanReservation_Rooms.getId_reservation().getDate_begin();
									c.setTime(date_begin);
									int hour2CancelReservation = beanReservation_Rooms.getId_room().getId_hotel().getId_company().getRoomCannotBeCanceledBeforeReserv();
									
									c.add(Calendar.HOUR, -hour2CancelReservation);
									date_cancel = c;
									
									if(date_cancel.after(date_actual) || date_cancel.equals(date_actual))
									{
										BeanType_Status_Room type_status = (BeanType_Status_Room)session.get(BeanType_Status_Room.class,1);
										beanReservation_Rooms = (BeanReservation_Rooms)session.get(BeanReservation_Rooms.class,beanReservation_Rooms.getId());
										BeanReservation beanReservation = (BeanReservation)session.get(BeanReservation.class,beanReservation_Rooms.getId_reservation().getId());
										BeanRoom beanRoom = (BeanRoom)session.get(BeanRoom.class,beanReservation_Rooms.getId_room().getId());
										beanRoom.setId_type_status_room(type_status);
										
										tx = session.beginTransaction();
										
										String query = " FROM BeanReservation_Person rp"+
					           			   			   " WHERE rp.id_reservation_room.id = " + beanReservation_Rooms.getId();
					           			   
									    List reservation_person = session.createQuery(query).list();
									    
									    if (!reservation_person.isEmpty()) 
										{ 
									    	for (int j = 0; j < reservation_person.size(); j++) 
									    	{
									    		BeanReservation_Person beanReservation_Person = (BeanReservation_Person)reservation_person.get(j);
												beanReservation_Person = (BeanReservation_Person)session.get(BeanReservation_Person.class,beanReservation_Person.getId());
												session.delete(beanReservation_Person);
									    	}
										}
										session.update(beanRoom);
										session.delete(beanReservation_Rooms);
										String reservation_room2 =   " FROM BeanReservation_Rooms rr"+
										 							 " WHERE rr.id_reservation.id = " + beanReservation.getId();

									    List reservations = session.createQuery(reservation_room2).list();
									    
									    if (reservations.isEmpty()) 
										{ 
									    	session.delete(beanReservation);
										}
									   										
										tx.commit();
											
								}
								else
								{
									reservationsNotCancel.add(beanReservation_Rooms);
									
								}	
							 }
							}
							
							if(reservationsNotCancel.size()> 0)
							{
								String first = "La reservación de la(s) habitación(es)";
								String description= "";
								String last = "";
								for (int i = 0; i < reservationsNotCancel.size(); i++) {
									
									description+=reservationsNotCancel.get(i).getId_room().getDescription();
									
								}
								last = "no se puede cancelar quedan menos de 48h para la salida";
								
								httpSession.setAttribute("error", first + description + last);
								   
							}
							if (new SessionUtil().prepareDataForDeleteReservation(request,beanUser,session) == 1) 
							 {  
								forward = "success";
								httpSession.setAttribute("done", "Reservación(es) cancelada(s) con éxito");
							 }
							else
							 {				 
							    forward = "error";
							 }
							
							
							
						}
						else
						{
							httpSession.setAttribute("error", "No ha seleccionado la reservación a cancelar");
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
		
	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	
		
}
