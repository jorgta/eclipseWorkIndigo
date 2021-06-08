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
					else if (process.equals("deviceNew")) 
					 {
						if (new SessionUtil().prepareDataForDeviceNew(request,beanUser,session) == 1) 
						 {  				
							  forward = "deviceNew";
						 }
						else
						  {				 
							  
							  forward = "deviceNewError";
						  }
					 } 
					else if (process.equals("deviceDelete"))
						 {
							if (new SessionUtil().prepareDataForDeviceDelete(request,beanUser,session) == 1) 
							  {								   			
								   forward = "deviceDelete";
							  }
							 else
							  {
							    forward = "deviceDeleteError";
							  }
							 }
					else if (process.equals("deviceActivate"))
						 {								
			
						     if (new SessionUtil().prepareDataForDeviceActivate(request,beanUser,session) == 1) 
								 {
								   			
								   forward = "deviceActivate";
								}
							 else
								 {								  
			
								   forward = "deviceActivateError";
								 } 
						 }
					else if (process.equals("deviceChange")) 
						 {
							if (new SessionUtil().prepareDataForDeviceChange(request,beanUser,session) == 1) 
							  {								   			
								   forward = "deviceChange";
							  }
							 else
							  {
							    forward = "deviceChangeError";
							  }
			
						 }		
					else if (process.equals("deviceMonitoring")) 
					 {
						if (new SessionUtil().prepareDataForDeviceChange(request,beanUser,session) == 1) 
						  {								   			
							   forward = "deviceMonitoring";
						  }
						 else
						  {
						    forward = "deviceMonitoringError";
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
					else if (process.equals("rptReservation"))
					 {								
								
						if (new SessionUtil().prepareRptReservation(request,beanUser,session) == 1) 
						 {
						   			
							forward = "rptReservation";
						 }
						 else
						 {								  
	
						   forward = "rptReservationError";
						 }
					
					
					 }
					else if (process.equals("rptQuote"))
					 {								
								
						if (new SessionUtil().prepareRptQuote(request,beanUser,session) == 1) 
						 {
						   			
							forward = "rptQuote";
						 }
						else
						 {								  
	
						   forward = "rptQuoteError";
						 }
					
					
					
					 }
					else if ( process.equals("hotelsNew") )  //new hotel
					 {
						httpSession.removeAttribute("done");
						httpSession.removeAttribute("error");
						
						String query =   " FROM BeanType_Reservation r"; 
						
						List list = session.createQuery(query).list();
						
						if ( !list.isEmpty() )
						{
							httpSession.setAttribute("types_reservation", list);
					    }
						
						if (new SessionUtil().prepareDataForHotelNew(request,beanUser,session) == 1) 
						 {  				
							  forward = "hotelsNew";
						 }
						else
						  {				 
							  
							  forward = "hotelsNewError";
						  }
					 }
					
					else if ( process.equals("roomNew") )  //new room
					 {
						httpSession.removeAttribute("done");
						httpSession.removeAttribute("error");
						httpSession.removeAttribute("beanHotel2AddRoom");
						httpSession.removeAttribute("beanRoom2Add");
						
						
						if (new SessionUtil().prepareDataForRoomNew(request,beanUser,session) == 1) 
						 {  				
							  forward = "roomNew";
						 }
						else
						  {				 
							  
							  forward = "roomNewError";
						  }
					 }
					 else if ( process.equals("roomDelete") )  //delete room
					 {
						     httpSession.removeAttribute("listRoomsHability");
						     httpSession.removeAttribute("done");
							 httpSession.removeAttribute("error");
							     
						    					    
							if (new SessionUtil().prepareDataForRoomDelete(request,beanUser,session) == 1) 
							 {  				
								  forward = "roomDelete";
							 }
							else
							  {				 
								  
								  forward = "roomDeleteError";
							  }
					 }
					 else if ( process.equals("roomActivate") )  //activate room
					 {
						     httpSession.removeAttribute("listRooms2Activate");
						     httpSession.removeAttribute("done");
							 httpSession.removeAttribute("error");
						    					    
							if (new SessionUtil().prepareDataForRoomActivate(request,beanUser,session) == 1) 
							 {  				
								  forward = "roomActivate";
							 }
							else
							  {				 
								  
								  forward = "roomActivateError";
							  }
					 }
					 else if ( process.equals("roomChange") )  //activate room
					 {
						     httpSession.removeAttribute("beanRoom2Change");
						     httpSession.removeAttribute("done");
						     httpSession.removeAttribute("error");
						    					    
							if (new SessionUtil().prepareDataForRoomChange(request,beanUser,session) == 1) 
							 {  				
								  forward = "roomChange";
							 }
							else
							 {				 
								  
								  forward = "roomChangeError";
							 }
					 }
					 else if ( process.equals("reservationNew") )  //new reservation
					 {
						 httpSession.removeAttribute("listroomSelected");
		    			 httpSession.removeAttribute("roomSelected"); 
	    				 httpSession.removeAttribute("listGuestAdd");
						 httpSession.removeAttribute("addReservation");
						 httpSession.removeAttribute("beanReservation");
						 httpSession.removeAttribute("addGuest");
						 httpSession.removeAttribute("beanReservation_Rooms");
						 httpSession.removeAttribute("type_reservation");
						 httpSession.removeAttribute("type_status_reservation");
						 httpSession.removeAttribute("type_transportation");
						 httpSession.removeAttribute("name_client");
						 httpSession.removeAttribute("notes");
						 httpSession.removeAttribute("date_to_pay2");
						 httpSession.removeAttribute("cant_adult");
						 httpSession.removeAttribute("cant_children");
						 httpSession.removeAttribute("showViewSelectedRoom");
						 httpSession.removeAttribute("showViewDataReservation");
						 httpSession.removeAttribute("showTabReservation");
						 httpSession.removeAttribute("showResults");
						 httpSession.removeAttribute("beanReservation");
						 httpSession.removeAttribute("beanQuote");
						 httpSession.removeAttribute("done");
					     httpSession.removeAttribute("error");
						 
						 
						 
						String query2 =   " FROM BeanType_Status_Reservation status"; 
						
						List list2 = session.createQuery(query2).list();
						
						if ( !list2.isEmpty() )
						{
							httpSession.setAttribute("types_status_reservation", list2);
					    }
						String query3 =   " FROM BeanTransport transport"; 
						
						List list3 = session.createQuery(query3).list();
						
						if ( !list3.isEmpty() )
						{
							httpSession.setAttribute("types_transportation", list3);
					    }
		
											
						if (new SessionUtil().prepareDataForNewReservation(request,beanUser,session) == 1) 
						 {  
							forward = "reservationNew";
						 }
						else
						 {				 
							  
							  forward = "reservationNewError";
						 }
					 }
					 else if ( process.equals("reservationDelete") )  //delete reservation
					 {
						httpSession.removeAttribute("done");
						httpSession.removeAttribute("error");
												
						if (new SessionUtil().prepareDataForDeleteReservation(request,beanUser,session) == 1) 
						 {  
							forward = "reservationDelete";
						 }
						else
						 {				 
						    forward = "reservationDeleteError";
						 }
					 }
					 else if ( process.equals("changeReservation") )  //change reservation
					 {

						 httpSession.removeAttribute("addReservation");
						 httpSession.removeAttribute("beanReservation_Rooms");
						 httpSession.removeAttribute("roomSelected");
						 httpSession.removeAttribute("listGuestAdd");
						 httpSession.removeAttribute("done");
						 httpSession.removeAttribute("error");
						 httpSession.removeAttribute("beanReservation");
						 httpSession.removeAttribute("beanReservation_Rooms");
						 httpSession.removeAttribute("type_status_reservation");
						 httpSession.removeAttribute("type_transportation");
						 httpSession.removeAttribute("name_client");
						 httpSession.removeAttribute("notes");
						 httpSession.removeAttribute("date_to_pay2");
						 httpSession.removeAttribute("addGuest");
						 httpSession.removeAttribute("beanReservation_person");
						 httpSession.removeAttribute("showViewListGuest");
							
						
						String query2 =   " FROM BeanType_Status_Reservation status"; 
						
						List list2 = session.createQuery(query2).list();
						
						if ( !list2.isEmpty() )
						{
							httpSession.setAttribute("types_status_reservation", list2);
					    }
						String query3 =   " FROM BeanTransport transport"; 
						
						List list3 = session.createQuery(query3).list();
						
						if ( !list3.isEmpty() )
						{
							httpSession.setAttribute("types_transportation", list3);
					    }
						
						if (new SessionUtil().prepareDataForChangeReservation(request,beanUser,session) == 1) 
						 {  
							forward = "changeReservation";
						 }
						else
						 {				 
						    forward = "changeReservationError";
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
