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
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;
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
public class ReservationNewAction extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730294291529491868L;
	
    
	private int id;
	private BeanReservation_Person guest;
	private String process;
	private HttpSession httpSession =null;
	private HttpServletRequest request = null;
	private String date_to_pay;
	private Date date_blocked;
	private String name_client;
	private String notes;
	private String guest_name;
	private String guest_age;
	private String types_status_reservation;
	private String types_transportation;
	private String cotizacion;
	private String cant_adults;
	private String cant_childrens;
	private String rooms;
	private String p1;
	
	
	
	

	public String forward()
	{
		return ERROR;
	}
	
	public void validate() {	
       
        HttpServletRequest request = ServletActionContext.getRequest();
        httpSession = request.getSession(); 
        httpSession.setAttribute("module","pre");
        Config config = new Config( request );
	    SessionFactory sessionFactory = null;
		
		Session    session = null;
        
    	
    	if(process.equals("addListGuest"))
    	{
    		
    	     if (cant_adults.length()!= 0 && cant_childrens.length()!= 0)
 	    	 {
     			try {
     				sessionFactory = config.getConfiguration(request).buildSessionFactory();
     				session = sessionFactory.openSession();
     				
     				BeanControlPanel beanControlPanel =(BeanControlPanel)session.get(BeanControlPanel.class,1);
     				int max_persons_to_room = beanControlPanel.getMax_persons_per_room();
     				int min_adults_per_room = beanControlPanel.getMin_adults_per_room();
     				int max_adults_per_room = beanControlPanel.getMax_adults_per_room();
     				
     				int cant_adult = Integer.parseInt(cant_adults);
     				int cant_children = Integer.parseInt(cant_childrens);
     				int total_guest = cant_adult + cant_children;
     				
     				if(cant_adult < min_adults_per_room )
 					 {
 				    	 this.addFieldError("cant_adults",getText("error.min.adults.per.room"));
 					 }
 					 else
 					 if(cant_adult > max_adults_per_room )
 				     {
 						this.addFieldError("cant_adults",getText("error.max.adults.per.room"));
 					 }
 					 else
 					 if(total_guest > max_persons_to_room)
 					 {
 						
 						this.addFieldError("cant_adults",getText("error.max.person.per.room"));
 					 }
 					 else
 					 {
 						 httpSession.setAttribute("cant_adult", cant_adults);
 						 httpSession.setAttribute("cant_children", cant_childrens);
 					 }
     			 } 
     			catch (Exception e) 
 			    {
 					e.printStackTrace();
 					
 					httpSession.setAttribute("module","pre"); 
 					httpSession.setAttribute("error", "Ocurrio un error interno : "+ e.getMessage() + " consulte a soporte tecnico");	
 				 
                 }
     			
 	    	
    	  }
    	}
    	  else if(process.equals("addNewGuest"))
    	  {
    		  if (guest_name.length() != 0 )
       		  { 
        		  guest_name = guest_name.trim().toUpperCase();
        		  if(guest_name.length() == 0)
        			  this.addFieldError("guest_name",getText("error.field.required"));
        		 
        	  }
        	  else
        		  this.addFieldError("guest_name",getText("error.field.required"));
       		 
        	  if ( guest_age.length() != 0)
    		  {
        		guest_age = guest_age.trim();
        		if(guest_age.length() == 0)
        			this.addFieldError("guest_age",getText("error.field.required"));
        		else
    			if (!Utils.isInt(guest_age) || Integer.parseInt(guest_age)< 0)
    				this.addFieldError("guest_age",getText("error.field.invalid.format"));
    		}
    		else
    		    this.addFieldError("guest_age",getText("error.field.required"));
    	  }  		
    	else
    	if(process.equals("addReservation"))
    	{
    		httpSession.removeAttribute("type_transportation");
            httpSession.removeAttribute("type_reservation");
            httpSession.removeAttribute("type_status_reservation");
        	
    		  if (date_to_pay.length() == 0 )
	 		  {
	    		this.addFieldError("date_to_pay",getText("error.field.required"));
	    	  }
    		  else
    		  {
    			  BeanRoom beanRoom = (BeanRoom)httpSession.getAttribute("roomSelected");
				  
    			  Date date_begin = beanRoom.getDate_begin();
    			  Date date_to_pay = Utils.StrToDate( getDate_to_pay(),"dd/mm/yy") ;
				  if( date_to_pay.after(date_begin) )
				  {
					  this.addFieldError("date_to_pay",getText("error.date.to.pay"));
				  }
				  
    		  }
	    	  if (name_client.length() != 0 )
	 		  {
	    		  name_client = name_client.trim().toUpperCase();
	    		  if(name_client.length() == 0)
	    		  {
	    			  this.addFieldError("name_client",getText("error.field.required"));
	    		  }	    			 
	    	 }
	    	  else
	    	    {
	    		  this.addFieldError("name_client",getText("error.field.required"));
	    	    }
	    		  
	    	 
	    	  if (types_transportation.length()!= 0)
	    	  {
	    	    if(types_transportation.equals("0"))
	    	    {
	    	    	this.addFieldError("types_transportation",getText("error.field.required"));
	    	    }
	    	    else
	    	    {
	    	        httpSession.setAttribute("type_transportation", types_transportation);
  		    	}
	    	    	
	    	  }
	    	  
	    	  if (types_status_reservation.length()!= 0)
	    	  {
	    	    if(types_status_reservation.equals("0"))
	    	    {
	    	    	this.addFieldError("types_status_reservation",getText("error.field.required"));
	    	    }
	    	    else
	    	    {
	    	    	httpSession.setAttribute("type_status_reservation", types_status_reservation);
	    	    }
	    	    	
	    	   }
	    	  
	    	
	    	  
	    }
    	
    	
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
			{
			  forward =  "logout";	
			}
			else
			{

			     if(process.equals("returnReservation"))
			     {
			    	 httpSession.setAttribute("cant_adult", getCant_adults());
					 httpSession.setAttribute("cant_children", getCant_childrens());
			    	 httpSession.removeAttribute("showViewSelectedRoom");
			    	 httpSession.removeAttribute("showViewGuest");
			    	 httpSession.setAttribute("showViewDataReservation", "showViewDataReservation");
			     }
			     else
		    	  if (process.equals("roomSelected"))
				  {
		    		  
		    		  List<BeanRoom> listroomSelected = (List<BeanRoom>)httpSession.getAttribute("listroomSelected");
		    		  BeanRoom roomSelected = null;
		    		  
					  if(rooms != null)  
					  {   
						for (int i = 0; i < listroomSelected.size(); i++)
						{
							if(listroomSelected.get(i).getId() == Integer.parseInt(rooms))
							{
								roomSelected = (BeanRoom)listroomSelected.get(i);
								break;
							}
						}
			
						if(roomSelected != null)
						{
                          httpSession.removeAttribute("roomSelected");
			    		  httpSession.setAttribute("roomSelected",roomSelected);
						}
					  }
					  httpSession.removeAttribute("listGuestAdd");
					  httpSession.removeAttribute("cant_adult");
					  httpSession.removeAttribute("cant_children");
					  httpSession.removeAttribute("beanReservation_Person");
					  
					 
				}
			    else
			     if (process.equals("selectedRooms"))
				 {
			    	String [] roomsSelected = request.getParameterValues("rooms");
				    if(roomsSelected != null)
				    {
				    	if(roomsSelected.length != 0)
					    {
					    	List<BeanRoom> rooms2Reserved = new ArrayList<BeanRoom>();
					    	BeanRoom first_beanRoom = null;
					    	String query =   " FROM BeanRoom r"+
					    					 " WHERE r.id = " +  roomsSelected[0]; 

							java.util.List list = session.createQuery(query).list();
							
							if ( !list.isEmpty() )
							{
								first_beanRoom = (BeanRoom)list.get(0);
							}
					    	if(roomsSelected.length > 1)
					    	{
						    	for (int i = 1; i < roomsSelected.length; i++)
						    	{
						    		String query2 =   " FROM BeanRoom r"+
					    						  	  " WHERE r.id = " +  roomsSelected[i]; 
				
								java.util.List list2 = session.createQuery(query2).list();
								
								if ( !list2.isEmpty() )
								{
								     BeanRoom room = (BeanRoom)list2.get(0);
								     if(room.getId_hotel().getId()== first_beanRoom.getId_hotel().getId())
								     {
								    	if(room.getDate_begin().equals(first_beanRoom.getDate_begin()) && room.getDate_end().equals(first_beanRoom.getDate_end())) 
								    	{
								    		rooms2Reserved.add(room);
								    		
								    	}
								    	else
								    	{
								    		httpSession.setAttribute("error","Seleccion? las habitaciones en fechas distintas");
								    	    forward = "error";
								    	    break;
								    	}
								     }
								     else
								     {
								    	 httpSession.setAttribute("error","Seleccion? las habitaciones en distintos hoteles");
								    	 forward = "error";
								    	 break;
								     }
									 
								}
								
								}
						    	if(forward != "error")
						    	{
						    		rooms2Reserved.add(0, first_beanRoom);
							    	
							    	httpSession.setAttribute("listroomSelected",rooms2Reserved);
						    		httpSession.setAttribute("roomSelected",rooms2Reserved.get(0));
						    		httpSession.setAttribute("showTabReservation", "showTabReservation");
						    		httpSession.setAttribute("showViewDataReservation", "showViewDataReservation");
						    		
						    		 Date dateBegin = rooms2Reserved.get(0).getDate_begin();
									 DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm");
									 String date_begin2 = date.format(dateBegin);
									 httpSession.setAttribute("date_begin", date_begin2);
									 Date dateEnd = rooms2Reserved.get(0).getDate_end();
									 DateFormat date2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
									 String date_end2 = date2.format(dateEnd);
									 httpSession.setAttribute("date_end", date_end2);
							    }
					    	}
					    	else
					    	{
					    		rooms2Reserved.add(first_beanRoom);
					    		httpSession.setAttribute("listroomSelected",rooms2Reserved);
					    		httpSession.setAttribute("roomSelected",rooms2Reserved.get(0));
					    		httpSession.setAttribute("showTabReservation", "showTabReservation");
					    		httpSession.setAttribute("showViewDataReservation", "showViewDataReservation");
					    		
					    		Date dateBegin = rooms2Reserved.get(0).getDate_begin();
								DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm");
								String date_begin2 = date.format(dateBegin);
								httpSession.setAttribute("date_begin", date_begin2);
								Date dateEnd = rooms2Reserved.get(0).getDate_end();
								DateFormat date2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
								String date_end2 = date2.format(dateEnd);
								httpSession.setAttribute("date_end", date_end2);
								
					    	}
					    	
					    	
					    }
					   
				      }
				    else
					{  
						httpSession.setAttribute("error","No seleccion? las habitaciones");
					}
				 }
				else
				 if(process.equals("addReservation"))
				 {
					    BeanRoom beanRoom = (BeanRoom)httpSession.getAttribute("roomSelected");
					    Date date_begin = beanRoom.getDate_begin();
						Date date_end = beanRoom.getDate_end();
						Date date_to_pay = Utils.StrToDate( getDate_to_pay(),"dd/mm/yy") ;
						httpSession.setAttribute("type_status_reservation", types_status_reservation);
						httpSession.setAttribute("type_transportation", types_transportation);
						httpSession.setAttribute("name_client", getName_client());
						httpSession.setAttribute("notes", getNotes());
						httpSession.setAttribute("cant_adults", getCant_adults());
						httpSession.setAttribute("cant_childrens", getCant_childrens());
						DateFormat date2 = new SimpleDateFormat("dd/MM/yyyy");
						String date_to_pay2 = date2.format(date_to_pay);
						httpSession.setAttribute("date_to_pay2", date_to_pay2);
					    
						if( date_to_pay.getTime() > date_begin.getTime() )
						{
							httpSession.setAttribute("error", getText("error.date.to.pay"));
							forward = "error";
						}
						else
						{   
							    BeanType_Status_Reservation beanStatus = null;
							    BeanTransport beanTransport = null;
							    
							    String query =   " FROM BeanType_Status_Reservation s"+
											     " WHERE s.id = " +  types_status_reservation; 

								java.util.List list = session.createQuery(query).list();
								
								if ( !list.isEmpty() )
								{
								  beanStatus = (BeanType_Status_Reservation)list.get(0);
								 
								}
								
								String query3 =   " FROM BeanTransport t"+
												  " WHERE t.id = " +  types_transportation; 
					
								java.util.List list3 = session.createQuery(query3).list();
								
								if ( !list3.isEmpty() )
								{
									beanTransport = (BeanTransport)list3.get(0);
								}
							
								BeanReservation beanReservation = new BeanReservation();
						    	beanReservation.setId_type_status_reservation(beanStatus);
						    	beanReservation.setId_transport(beanTransport);
						    	beanReservation.setTransport_description(beanTransport.getDescription());
						    	beanReservation.setTransport_price_per_person(beanTransport.getPrice_per_person());
						    	beanReservation.setClient_name(getName_client().toUpperCase());
								beanReservation.setDate_to_pay( date_to_pay);
								beanReservation.setNotes(getNotes());
								beanReservation.setPrice(0);
								beanReservation.setDate_begin(date_begin);
								beanReservation.setDate_end(date_end);
									
								int hour2UnblockedRoomWhithPayComplete = beanUser.getId_company().getRoomMustPayAllBeforeReserv();
								int hour2UnblockedwhitoutPay = beanUser.getId_company().getRoomFirstPayment();
								
								if(beanReservation.getId_type_status_reservation().getId() == 2)
								{
									
										Calendar fecha = Calendar.getInstance();
										fecha.add(Calendar.HOUR, hour2UnblockedRoomWhithPayComplete);
										Calendar blocked= fecha;
										
										if(blocked.getTime().after(date_begin))
										{
											blocked.setTime(date_begin);
											blocked.add(Calendar.HOUR, -24);
										}
									    beanReservation.setDate_blocked(blocked.getTime());
									
								}
								else
								if(beanReservation.getId_type_status_reservation().getId() == 1)
								{
									    Calendar fecha = Calendar.getInstance();
										fecha.add(Calendar.HOUR, hour2UnblockedwhitoutPay);
										Calendar blocked= fecha;
										beanReservation.setDate_blocked(blocked.getTime());
										
								}
								else
								if(beanReservation.getId_type_status_reservation().getId() == 3)
								{
									    Calendar blocked = Calendar.getInstance();
										beanReservation.setDate_blocked(blocked.getTime());
										
								 }
									
						       String cotizacion = request.getParameter("cotizacion");
					  	
					  	      if(cotizacion != null)
					  	      {
						  		 BeanQuote beanQuote = new BeanQuote();
								 beanQuote.setClient_name(beanReservation.getClient_name());
								 beanQuote.setDate_begin(beanReservation.getDate_begin());
								 beanQuote.setDate_blocked(beanReservation.getDate_blocked());
								 beanQuote.setDate_end(beanReservation.getDate_end());
								 beanQuote.setDate_to_pay(beanReservation.getDate_to_pay());
								 beanQuote.setId_transport(beanReservation.getId_transport());
								 beanQuote.setNotes(beanReservation.getNotes());
								 beanQuote.setTransport_description(beanReservation.getTransport_description());
								 beanQuote.setTransport_price_per_person(beanReservation.getTransport_price_per_person());
								 
								 httpSession.setAttribute("beanQuote", beanQuote);
								 httpSession.setAttribute("beanReservation", beanReservation);
					  	      }
					  	      else
					  	      {
					  	    	 httpSession.setAttribute("beanReservation", beanReservation);
								     
					  	      }
							  httpSession.removeAttribute("showViewDataReservation");
							  httpSession.setAttribute("showViewSelectedRoom", "showViewSelectedRoom");
							  httpSession.setAttribute("showViewGuest", "showViewGuest");
							  BeanControlPanel beanControlPanel =(BeanControlPanel)session.get(BeanControlPanel.class,1);
			    			  int max_adults_per_room = beanControlPanel.getMax_adults_per_room();
			    			  httpSession.setAttribute("max_adults_per_room", max_adults_per_room);
									   
						}
				 }
				else
			    if(process.equals("addListGuest"))
			    {
			    	    BeanRoom beanRoom = (BeanRoom)httpSession.getAttribute("roomSelected");
				    	BeanReservation beanReservation = (BeanReservation)httpSession.getAttribute("beanReservation");
				    	int cant_adults = Integer.parseInt(getCant_adults());
						int cant_childrens = Integer.parseInt(getCant_childrens());
						
						List<BeanReservation_Person> listGuestAdd;
						listGuestAdd = (List<BeanReservation_Person> )httpSession.getAttribute("listGuestAdd");
						 
						if(listGuestAdd != null)
						{
							for (int i = 0; i < cant_adults; i++) 
							{
								   String name = "Adulto "+(i+1);
								   BeanReservation_Person beanReservation_Person = new BeanReservation_Person();
								   beanReservation_Person.setName(name.toUpperCase());
								   beanReservation_Person.setPrice(beanRoom.getPrice1());
								   beanReservation_Person.setAge(40);
				                   listGuestAdd.add(beanReservation_Person);
							       
							 }
							
						    for (int i = 0; i < cant_childrens; i++) 
						    {
							   String name = "Menor "+(i+1);
							   BeanReservation_Person beanReservation_Person = new BeanReservation_Person();
							   beanReservation_Person.setName(name.toUpperCase());
							   beanReservation_Person.setAge(10);
							   beanReservation_Person.setPrice(beanRoom.getPriceJr());
			                   listGuestAdd.add(beanReservation_Person);
						     }
							
						}
						else
						{
							listGuestAdd = new ArrayList<BeanReservation_Person>() ;
							for (int i = 0; i < cant_adults; i++) 
							{
								   String name = "Adulto "+(i+1);
								   BeanReservation_Person beanReservation_Person = new BeanReservation_Person();
								   beanReservation_Person.setName(name.toUpperCase());
								   beanReservation_Person.setPrice(beanRoom.getPrice1());
								   beanReservation_Person.setAge(40);
				                   listGuestAdd.add(beanReservation_Person);
							       
							 }
							
						    for (int i = 0; i < cant_childrens; i++) 
						    {
							   String name = "Menor "+(i+1);
							   BeanReservation_Person beanReservation_Person = new BeanReservation_Person();
							   beanReservation_Person.setName(name.toUpperCase());
							   beanReservation_Person.setAge(10);
							   beanReservation_Person.setPrice(beanRoom.getPriceJr());
			                   listGuestAdd.add(beanReservation_Person);
						     }
						}
						  httpSession.removeAttribute("listGuestAdd");
						  httpSession.setAttribute("listGuestAdd",listGuestAdd);
						  httpSession.setAttribute("showViewGuest","showViewGuest");
				}
			    
			    else
			    if(process.equals("addNewGuest"))
			    {
			    	BeanRoom beanRoom = (BeanRoom)httpSession.getAttribute("roomSelected");
			    	BeanReservation beanReservation = (BeanReservation)httpSession.getAttribute("beanReservation");
			    	double price = 0;
			        boolean exist = false;
			        BeanControlPanel beanControlPanel =(BeanControlPanel)session.get(BeanControlPanel.class,1);
			        int max_persons_to_room = beanControlPanel.getMax_persons_per_room();
			        int min_adults_per_room = beanControlPanel.getMin_adults_per_room();
			        int max_adults_per_room = beanControlPanel.getMax_adults_per_room();
			        //int cant_adults = Integer.parseInt(httpSession.getAttribute("cant_adult").toString());
			        //int cant_childrens = Integer.parseInt(httpSession.getAttribute("cant_children").toString());
			        //int total_guest = cant_adults + cant_childrens;
			        int cantAdults= 0;
					int cantBaby = 0;   // 0 - 24
					int cantBasic = 0;  //2 - 5
					int cantChild = 0; //  5- 12
					int cantjr = 0;    // 13 - 15 
					
					 BeanReservation_Person reservation_Person = (BeanReservation_Person)httpSession.getAttribute("beanReservation_Person");
					     
					 List<BeanReservation_Person> listGuestAdd;
					 listGuestAdd = (List<BeanReservation_Person> )httpSession.getAttribute("listGuestAdd");
					 //BeanReservation_Person beanReservation_Person = new BeanReservation_Person();
					
					 
						 if(Integer.parseInt(guest_age) > 15)
						 {
							   price = beanRoom.getPrice1();
						 }
					     else
						 if(Integer.parseInt(guest_age) == 0)
						 {
							price = beanRoom.getPriceBaby();
						   
						 }
					   else
						if(Integer.parseInt(guest_age) > 2 && Integer.parseInt(guest_age)< 5)
						{
							price = beanRoom.getPriceBasic() ;
							
						}
						else
						if(Integer.parseInt(guest_age)>= 5  && Integer.parseInt(guest_age)<= 12)
						{
							price = beanRoom.getPriceChild();
							
						}
						else
						{
							price = beanRoom.getPriceJr();
							
						}
					 
					 if(listGuestAdd != null)
					 {
						 for (int i = 0; i < listGuestAdd.size(); i++) 
						 {
								BeanReservation_Person temp = (BeanReservation_Person)listGuestAdd.get(i) ;
								if(temp.getAge() > 15)
								 {
									cantAdults++;
								 }
							     else
								 if(temp.getAge() == 0)
								 {
									 cantBaby++;
								 }
							   else
								if(temp.getAge() >= 2 && temp.getAge()< 5)
								{
									cantBasic++;
								}
								else
								if(temp.getAge()>= 5  && temp.getAge()<= 12)
								{
									cantChild++;
								}
								else
								{
									cantjr++;
								}
						    }
							
							if(cantAdults < min_adults_per_room )
							 {
						    	 httpSession.setAttribute("error", getText("error.min.adults.per.room"));
								 forward = "error";
							 }
							 else
							 if(cantAdults > max_adults_per_room )
						     {
								 httpSession.setAttribute("error", getText("error.max.adults.per.room"));
								 forward = "error";
							 }
							 else
							 if(listGuestAdd.size() > max_persons_to_room)
							 {
								httpSession.setAttribute("error", getText("error.max.person.per.room"));
								forward = "error";
							 }
							 else
							 {
								 if(reservation_Person != null)
								 {
									
								         String name = getGuest_name().toUpperCase();  
										 if(!reservation_Person.getName().equals(guest_name) || reservation_Person.getAge()!= Integer.parseInt(guest_age))
										 {
										   for (int i = 0; i < listGuestAdd.size(); i++) 
										   {
										    	
												if(listGuestAdd.get(i).getName().equals(name))
												{
													if(listGuestAdd.get(i).getAge()== Integer.parseInt(getGuest_age()))
													{
															exist = true;
														    break;
														
													}
												}
											}
										    if(exist)
											{
												 httpSession.setAttribute("error", getText("error.exist.guest"));
												 forward = "error"; 
											}
											else
											{
											    	int pos = 0;
											    	BeanReservation_Person beanReservation_Person = null;
											    	for (int i = 0; i < listGuestAdd.size(); i++) 
													{
														 if(listGuestAdd.get(i).getName().equals(reservation_Person.getName()))
														 {
															 if(listGuestAdd.get(i).getAge()== reservation_Person.getAge())
															 {
																 pos = i;
																 beanReservation_Person=listGuestAdd.get(i);
																 break;
															 }
														 }
														 else
															 continue;
														 
													  }
											    	beanReservation_Person.setAge(Integer.parseInt(getGuest_age()));
									                beanReservation_Person.setName(name);
									                beanReservation_Person.setPrice(price);
									                listGuestAdd.set(pos, beanReservation_Person);
									                
											    	httpSession.removeAttribute("beanReservation_Person");
											    	httpSession.removeAttribute("reservation_Person");
													httpSession.setAttribute("listGuestAdd",listGuestAdd);
													httpSession.setAttribute("done","El hu?sped se ha modificado con ?xito");
											   }
										 }
										 else
										 {
											 httpSession.setAttribute("done","Los datos se mantienen igual");
											 httpSession.removeAttribute("beanReservation_Person");
										 }
											
								 }
								else
								{
									for (int i = 0; i < listGuestAdd.size(); i++) 
									   {
									    	
											if(listGuestAdd.get(i).getName().equals(guest_name.toUpperCase()))
											{
												if(listGuestAdd.get(i).getAge()== Integer.parseInt(getGuest_age()))
												{
														exist = true;
													    break;
													
												}
											}
										}
									    if(exist)
										{
											 httpSession.setAttribute("error", getText("error.exist.guest"));
											 forward = "error"; 
										}
										else
										{
											BeanReservation_Person beanReservation_Person = new BeanReservation_Person();
							                beanReservation_Person.setAge(Integer.parseInt(getGuest_age()));
							                beanReservation_Person.setName(guest_name.toUpperCase());
							                beanReservation_Person.setPrice(price);
							                listGuestAdd.add(beanReservation_Person);
									    	httpSession.removeAttribute("listGuestAdd");
											httpSession.setAttribute("listGuestAdd",listGuestAdd);
											//httpSession.setAttribute("done","El hu?sped se ha agregado con ?xito");
										    	
										 }
								     }
								 }
							 }
					 else
					 {
						    listGuestAdd = new ArrayList<BeanReservation_Person>();
						    BeanReservation_Person beanReservation_Person = new BeanReservation_Person();
			                beanReservation_Person.setAge(Integer.parseInt(getGuest_age()));
			                beanReservation_Person.setName(guest_name.toUpperCase());
			                beanReservation_Person.setPrice(price);
			                listGuestAdd.add(beanReservation_Person);
					    	httpSession.removeAttribute("listGuestAdd");
					    	httpSession.removeAttribute("listGuestAdd");
							httpSession.setAttribute("listGuestAdd",listGuestAdd);
							//httpSession.setAttribute("done","El hu?sped se ha agregado con ?xito");
						 
					 }
								 
					
			    }
		        else
				 if(process.equals("confirmReservation"))
				 {
				    BeanReservation beanReservation = (BeanReservation)httpSession.getAttribute("beanReservation");
					BeanRoom beanRoom = (BeanRoom)httpSession.getAttribute("roomSelected");
					List<BeanRoom> listroomSelected = (List<BeanRoom>)httpSession.getAttribute("listroomSelected");
					List<BeanReservation_Person> listGuestAdd;
					listGuestAdd = (List<BeanReservation_Person> )httpSession.getAttribute("listGuestAdd");
					//int cant_adults = Integer.parseInt(httpSession.getAttribute("cant_adult").toString());
				    //int cant_childrens = Integer.parseInt(httpSession.getAttribute("cant_children").toString());
				    //int total_guest = cant_adults + cant_childrens;
				    //BeanReservation firstReservation = (BeanReservation)httpSession.getAttribute("firstReservation");
				    BeanQuote beanQuote = (BeanQuote)httpSession.getAttribute("beanQuote");
				    int cantAdults= 0;
					int cantBaby = 0;   // 0 - 24
					int cantBasic = 0;  //2 - 5
					int cantChild = 0; //  5- 12
					int cantjr = 0;    // 13 - 15 
					double price_total = 0;
					double price_adults = 0;			  
					
					BeanControlPanel beanControlPanel =(BeanControlPanel)session.get(BeanControlPanel.class,1);
					int max_persons_to_room = beanControlPanel.getMax_persons_per_room();
					int min_adults_per_room = beanControlPanel.getMin_adults_per_room();
					int max_adults_per_room = beanControlPanel.getMax_adults_per_room();
							
								//obtener cantidad de adultos y los ni?os por grupo de edades
					 if(listGuestAdd != null)
					 {
						 for (int i = 0; i < listGuestAdd.size(); i++) 
						 {
								BeanReservation_Person temp = (BeanReservation_Person)listGuestAdd.get(i) ;
								if(temp.getAge() > 15)
								 {
									cantAdults++;
								 }
							     else
								 if(temp.getAge() == 0)
								 {
									 cantBaby++;
								 }
							   else
								if(temp.getAge() >= 2 && temp.getAge()< 5)
								{
									cantBasic++;
								}
								else
								if(temp.getAge()>= 5  && temp.getAge()<= 12)
								{
									cantChild++;
								}
								else
								{
									cantjr++;
								}
						    }
							
							if(cantAdults < min_adults_per_room )
							 {
						    	 httpSession.setAttribute("error", getText("error.min.adults.per.room"));
								 forward = "error";
							 }
							 else
							 if(cantAdults > max_adults_per_room )
						     {
								 httpSession.setAttribute("error", getText("error.max.adults.per.room"));
								 forward = "error";
							 }
							 else
							 if(listGuestAdd.size() > max_persons_to_room)
							 {
								httpSession.setAttribute("error", getText("error.max.person.per.room"));
								forward = "error";
							 }
							 else
							 {
								if(cantAdults == 1)
								{
									price_total+=beanRoom.getPrice1();
									price_adults= beanRoom.getPrice1();
								}
								else
								if(cantAdults == 2)
								{
									price_total+=beanRoom.getPrice2();
									price_adults= beanRoom.getPrice2();
								}
								else
								if(cantAdults == 3)
								{
									price_total+=beanRoom.getPrice3();
									price_adults= beanRoom.getPrice3();
								}
								else
								{
									price_total+=beanRoom.getPrice4();
									price_adults= beanRoom.getPrice4();
								}
											
										 
											
								//sumar al precio total la cantidad de ni?os por grupos de edades por tarifa
								
								price_total+=(cantBaby*beanRoom.getPriceBaby())+(cantBasic*beanRoom.getPriceBasic())+(cantChild*beanRoom.getPriceChild())+(cantjr*beanRoom.getPriceJr());
								
								
								tx = session.beginTransaction();
								BeanType_Status_Room bean_status = null;
								if(beanReservation !=null)
								{
								     if(beanReservation.getId_type_status_reservation().getId() == 2)
								     {
										String status =   " FROM BeanType_Status_Room r"+
										  				  " WHERE r.id = " +  3;
										
										java.util.List status_room = session.createQuery(status).list();
										if ( !status_room.isEmpty() )
										{
											bean_status = (BeanType_Status_Room)status_room.get(0);
										}
									}
									else
									if(beanReservation.getId_type_status_reservation().getId() == 1)
									{
										String status1 =   " FROM BeanType_Status_Room r"+
															" WHERE r.id = " +  2;
										java.util.List status_room1 = session.createQuery(status1).list();
										if ( !status_room1.isEmpty() )
										{
											bean_status = (BeanType_Status_Room)status_room1.get(0);
										}
									}
									else
									if(beanReservation.getId_type_status_reservation().getId() == 3)
									{
										String status2 =   " FROM BeanType_Status_Room r"+
										  				   " WHERE r.id = " +  4;
										java.util.List status_room2 = session.createQuery(status2).list();
										if ( !status_room2.isEmpty() )
										{
											bean_status = (BeanType_Status_Room)status_room2.get(0);
										}
									}
								}
								 Date dateBegin = beanReservation.getDate_begin();
								 DateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm");
								 String date_begin = date.format(dateBegin);
								 String transport_person =   " FROM BeanReservation_Person rp"+
															" WHERE rp.id_reservation_room.id_reservation.id_transport.id = " +  beanReservation.getId_transport().getId()+
															" AND rp.id_reservation_room.id_room.date_begin = '"+ date_begin+"'";
								java.util.List transport_persons = session.createQuery(transport_person).list();
								if ( transport_persons != null )
								{
									int capacity_transport = beanReservation.getId_transport().getCapacity();
									int cant_persons_in_transport = transport_persons.size();
									
									int capacity_free = capacity_transport - cant_persons_in_transport;
									
									if(capacity_free > 0)
									{
										if(capacity_free >= listGuestAdd.size())
										{
											if(beanQuote != null)
											{
												 if(beanQuote.getId() == 0)
												 {
													 double price_transport = beanQuote.getTransport_price_per_person()*listGuestAdd.size();
													 beanQuote.setPrice(price_total+ price_transport);
													 session.save(beanQuote);
													 
													 BeanRoom room = (BeanRoom)(BeanRoom)session.get(BeanRoom.class,beanRoom.getId());
													 room.setId_type_status_room(bean_status);
													 session.update(room);
													 
													 BeanQuote_Room beanQuote_Room = new BeanQuote_Room();
													 beanQuote_Room.setId_quote(beanQuote);
													 beanQuote_Room.setId_room(beanRoom);
													 beanQuote_Room.setHotel_description(room.getId_hotel().getDescription());
													 beanQuote_Room.setRoom_description(room.getDescription());
													 
													 session.save(beanQuote_Room);
													 
													 for (BeanReservation_Person beanReservation_Person : listGuestAdd) 
													 {
														    BeanQuote_Person beanQuote_Person = new BeanQuote_Person();
														    beanQuote_Person.setAge(beanReservation_Person.getAge());
														    beanQuote_Person.setName(beanReservation_Person.getName());
														    beanQuote_Person.setPrice(beanReservation_Person.getPrice());
														    beanQuote_Person.setId_quote_room(beanQuote_Room);
														     
														    session.save(beanQuote_Person);
														    
													 }
													 tx.commit();
													 for (int j = 0; j < listroomSelected.size(); j++) 
										    		 {
														if(listroomSelected.get(j).getId()== beanRoom.getId())
														{
															listroomSelected.remove(j);
															break;
														}
										    		 }
													if(listroomSelected.size()!= 0)
										    		 {
										    			 httpSession.removeAttribute("listroomSelected");
										    			 httpSession.setAttribute("listroomSelected",listroomSelected);
										    			 httpSession.removeAttribute("roomSelected");
														 httpSession.setAttribute("roomSelected",listroomSelected.get(0));
														 httpSession.removeAttribute("listGuestAdd");
														 httpSession.removeAttribute("cant_adult");
														 httpSession.removeAttribute("cant_children");
														 httpSession.removeAttribute("beanQuote");
														 httpSession.setAttribute("beanQuote",beanQuote);
														 httpSession.removeAttribute("guest_name");
														 httpSession.removeAttribute("guest_age");
														 httpSession.setAttribute("showResults", "showResults");
														 httpSession.setAttribute("Prices_formule", "Precio total=(Cantidad de Adultos * Tarifa de adultos)+ (Cantidad de babies*Tarifa de babies)+(Cantidad de b?sicos*Tarifa de b?sicos)+(Cantidad de Child*Tarifa de child)+(Cantidad de Junior*Tarifa de Junior)");
														 httpSession.setAttribute("Prices_result", "Precio_total = (" +price_adults+"+("+ cantBaby+"*"+beanRoom.getPriceBaby()+")+"+"("+cantBasic+"*"+beanRoom.getPriceBaby()+")+"+"("+cantChild+"*"+beanRoom.getPriceChild()+")+("+cantjr+"*"+ beanRoom.getPriceJr()+")");
														 httpSession.setAttribute("Result",beanQuote.getPrice());
														 
														 httpSession.setAttribute("done", getText("done.reservation.new"));
														 forward = "success";
										    		 }
													else
													{
														if (new SessionUtil().prepareDataForNewReservation(request, beanUser, session)==1) 
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
															 httpSession.removeAttribute("beanReservation");
															 httpSession.setAttribute("beanReservation",beanReservation);
															 httpSession.removeAttribute("beanQuote");
															 httpSession.setAttribute("beanQuote",beanQuote);
															 httpSession.removeAttribute("guest_name");
															 httpSession.removeAttribute("guest_age");
															
															 	 /*httpSession.setAttribute("done", getText("done.reservation.new"));
																 forward = "success";*/
																 setProcess("rptQuote");
																 setP1(String.valueOf( beanQuote_Room.getId_quote().getId() ));								
																 forward = "successGlobal";
															 }
											    			 else
															 {				 
																  forward = "error";
															 }
												 
											         }
												 }
												 else
												 {
													 
													 BeanQuote quote = (BeanQuote)(BeanQuote)session.get(BeanQuote.class,beanQuote.getId());
													 double price_transport = beanQuote.getTransport_price_per_person()*listGuestAdd.size();
													 quote.setPrice(beanQuote.getPrice()+ price_total+price_transport);
													 session.update(quote);
													 
													 BeanRoom room = (BeanRoom)(BeanRoom)session.get(BeanRoom.class,beanRoom.getId());
													 room.setId_type_status_room(bean_status);
													 session.update(room);
													 
													 BeanQuote_Room beanQuote_Room = new BeanQuote_Room();
													 beanQuote_Room.setId_quote(beanQuote);
													 beanQuote_Room.setId_room(beanRoom);
													 beanQuote_Room.setHotel_description(room.getId_hotel().getDescription());
													 beanQuote_Room.setRoom_description(room.getDescription());
													 
													 
													 session.save(beanQuote_Room);
													 
													 for (BeanReservation_Person beanReservation_Person : listGuestAdd) 
													 {
														    BeanQuote_Person beanQuote_Person = new BeanQuote_Person();
														    beanQuote_Person.setAge(beanReservation_Person.getAge());
														    beanQuote_Person.setName(beanReservation_Person.getName());
														    beanQuote_Person.setPrice(beanReservation_Person.getPrice());
														    beanQuote_Person.setId_quote_room(beanQuote_Room);
														     
														    session.save(beanQuote_Person);
													 }
													 tx.commit();
													 for (int j = 0; j < listroomSelected.size(); j++) 
										    		 {
														if(listroomSelected.get(j).getId()== beanRoom.getId())
														{
															listroomSelected.remove(j);
															break;
														}
										    		 }
													if(listroomSelected.size()!= 0)
										    		 {
										    			 httpSession.removeAttribute("listroomSelected");
										    			 httpSession.setAttribute("listroomSelected",listroomSelected);
										    			 httpSession.removeAttribute("roomSelected");
														 httpSession.setAttribute("roomSelected",listroomSelected.get(0));
														 httpSession.removeAttribute("listGuestAdd");
														 httpSession.removeAttribute("cant_adult");
														 httpSession.removeAttribute("cant_children");
														 httpSession.removeAttribute("beanReservation");
														 httpSession.setAttribute("beanReservation",beanReservation);
														 httpSession.removeAttribute("beanQuote");
														 httpSession.setAttribute("beanQuote",beanQuote);
														 httpSession.removeAttribute("guest_name");
														 httpSession.removeAttribute("guest_age");
														
														 
														httpSession.setAttribute("done", getText("done.reservation.new"));
														 
														/* setProcess("rptQuote");
														 setP1(String.valueOf( beanQuote_Room.getId_quote().getId() ));								
														 forward = "successGlobal";
														 */
														 forward = "success";
														 
										    		 }
													else
													{
														if (new SessionUtil().prepareDataForNewReservation(request,beanUser,session) == 1) 
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
															 httpSession.removeAttribute("beanReservation");
															 httpSession.setAttribute("beanReservation",beanReservation);
															 httpSession.removeAttribute("beanQuote");
															 httpSession.setAttribute("beanQuote",beanQuote);
															 httpSession.removeAttribute("guest_name");
															 httpSession.removeAttribute("guest_age");
															 
																 /*httpSession.setAttribute("done", getText("done.reservation.new"));
																 forward = "success";*/
															 setProcess("rptQuote");
															 setP1(String.valueOf( beanQuote_Room.getId_quote().getId() ));								
															 forward = "successGlobal";
														 }
										    			 else
														 {				 
															  forward = "error";
														 }
													}
												 }
												 
											 
										     }
											 else
											 {
												if(beanReservation.getId()== 0)
												{
													 double price_transport = beanReservation.getTransport_price_per_person()*listGuestAdd.size();
													 beanReservation.setPrice(price_total + price_transport);
													 session.save(beanReservation);
													 
													 BeanRoom room = (BeanRoom)(BeanRoom)session.get(BeanRoom.class,beanRoom.getId());
													 room.setId_type_status_room(bean_status);
													 session.update(room);
													 
													 
													 BeanReservation_Rooms beanReservation_Rooms = new BeanReservation_Rooms();
													 beanReservation_Rooms.setId_reservation(beanReservation);
													 beanReservation_Rooms.setId_room(beanRoom);
													 beanReservation_Rooms.setHotel_description(room.getId_hotel().getDescription());
													 beanReservation_Rooms.setRoom_description(room.getDescription());
													 
													 session.save(beanReservation_Rooms);
													 
													 for (BeanReservation_Person beanReservation_Person : listGuestAdd) 
													 {
														    beanReservation_Person.setId_reservation_room(beanReservation_Rooms);
														    session.save(beanReservation_Person);
													 }
													 tx.commit();
					
													 for (int j = 0; j < listroomSelected.size(); j++) 
										    		 {
														if(listroomSelected.get(j).getId()== beanRoom.getId())
														{
															listroomSelected.remove(j);
															break;
														}
										    		 }
													if(listroomSelected.size()!= 0)
										    		 {
										    			 httpSession.removeAttribute("listroomSelected");
										    			 httpSession.setAttribute("listroomSelected",listroomSelected);
										    			 httpSession.removeAttribute("roomSelected");
														 httpSession.setAttribute("roomSelected",listroomSelected.get(0));
														 httpSession.removeAttribute("listGuestAdd");
														 httpSession.removeAttribute("cant_adult");
														 httpSession.removeAttribute("cant_children");
														 httpSession.removeAttribute("beanReservation");
														 httpSession.setAttribute("beanReservation",beanReservation);
														 httpSession.removeAttribute("beanQuote");
														 httpSession.setAttribute("beanQuote",beanQuote);
														 httpSession.removeAttribute("guest_name");
														 httpSession.removeAttribute("guest_age");
														 
														 httpSession.setAttribute("done", getText("done.reservation.new"));
														 forward = "success";
														 //<a href="rpt.action?p1=<s:property value="#currentRoom_Reservation.id_reservation.id"/>&process=rptReservation&type=" title="Reporte de reservacion" title="Reporte de reservacion"><img src="resources/images/icons/report.png" alt="Reporte de reservacion" /></a> 
														// httpSession.setAttribute("print", "/trip/rpt.action?p1="+beanReservation_Rooms.getId_reservation().getId() +"&process=rptReservation&type=");
														 
														 /*
														 setProcess("rptReservation");
														 setP1(String.valueOf( beanReservation_Rooms.getId_reservation().getId() ));
														
														 
														 forward = "successGlobal";*/
										    		 }
													else
													{
														if (new SessionUtil().prepareDataForNewReservation(request,beanUser,session) == 1) 
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
															 httpSession.removeAttribute("beanReservation");
															 httpSession.setAttribute("beanReservation",beanReservation);
															 httpSession.removeAttribute("beanQuote");
															 httpSession.setAttribute("beanQuote",beanQuote);
															 httpSession.removeAttribute("guest_name");
															 httpSession.removeAttribute("guest_age");
															
															//httpSession.setAttribute("done", getText("done.reservation.new"));
															setProcess("rptReservation");
															setP1(String.valueOf( beanReservation_Rooms.getId_reservation().getId() ));
															forward = "successGlobal";
														 }
										    			 else
														 {				 
															  forward = "error";
														 }
													}
															
												}
												else
												{
													 BeanReservation reservation = (BeanReservation)(BeanReservation)session.get(BeanReservation.class,beanReservation.getId());
													 double price_transport = beanReservation.getTransport_price_per_person()*listGuestAdd.size();
													 reservation.setPrice(beanReservation.getPrice()+ price_total + price_transport);
													 session.update(reservation);
													 
													 BeanRoom room2 = (BeanRoom)(BeanRoom)session.get(BeanRoom.class,beanRoom.getId());
													 room2.setId_type_status_room(bean_status);
													 session.update(room2);
													 
													 BeanReservation_Rooms beanReservation_Rooms = new BeanReservation_Rooms();
													 beanReservation_Rooms.setId_reservation(beanReservation);
													 beanReservation_Rooms.setId_room(beanRoom);
													 beanReservation_Rooms.setHotel_description(room2.getId_hotel().getDescription());
													 beanReservation_Rooms.setRoom_description(room2.getDescription());
													 
													 
													 session.save(beanReservation_Rooms);
													 
													 for (BeanReservation_Person beanReservation_Person : listGuestAdd) 
													 {
														    beanReservation_Person.setId_reservation_room(beanReservation_Rooms);
															session.save(beanReservation_Person);
													 }
													 //tx.commit();
													 
													 beanReservation_Rooms.getId_reservation();
													 
													 //<a href="rpt.action?p1=<s:property value="#currentRoom_Reservation.id_reservation.id"/>&process=rptReservation&type=" title="Reporte de reservacion" title="Reporte de reservacion"><img src="resources/images/icons/report.png" alt="Reporte de reservacion" /></a> 
														
													 
													 
													 for (int x = 0; x < listroomSelected.size(); x++) 
										    		 {
														if(listroomSelected.get(x).getId()== beanRoom.getId())
														{
															listroomSelected.remove(x);
															break;
														}
										    		 }
													if(listroomSelected.size()!= 0)
										    		 {
										    			 httpSession.removeAttribute("listroomSelected");
										    			 httpSession.setAttribute("listroomSelected",listroomSelected);
										    			 httpSession.removeAttribute("roomSelected");
														 httpSession.setAttribute("roomSelected",listroomSelected.get(0));
														 httpSession.removeAttribute("listGuestAdd");
														 httpSession.removeAttribute("cant_adult");
														 httpSession.removeAttribute("cant_children");
														 httpSession.removeAttribute("beanReservation");
														 httpSession.setAttribute("beanReservation",beanReservation);
														 httpSession.removeAttribute("beanQuote");
														 httpSession.setAttribute("beanQuote",beanQuote);
														 httpSession.removeAttribute("guest_name");
														 httpSession.removeAttribute("guest_age");
														
														 httpSession.setAttribute("done", getText("done.reservation.new"));
														 forward = "success";
														 
														 /*setProcess("rptReservation");
														 setP1(String.valueOf( beanReservation_Rooms.getId_reservation().getId() ));
														 forward = "successGlobal";*/
										    		 }
													else
													 {
														if (new SessionUtil().prepareDataForNewReservation(request,beanUser,session) == 1) 
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
															 httpSession.removeAttribute("beanReservation");
															 httpSession.setAttribute("beanReservation",beanReservation);
															 httpSession.removeAttribute("beanQuote");
															 httpSession.setAttribute("beanQuote",beanQuote);
															 httpSession.removeAttribute("guest_name");
															 httpSession.removeAttribute("guest_age");
															  
															 /*httpSession.setAttribute("done", getText("done.reservation.new"));
															 forward = "success";*/
															 setProcess("rptReservation");
															 setP1(String.valueOf( beanReservation_Rooms.getId_reservation().getId() ));
															 forward = "successGlobal";
														  }
											    		 else
														  {				 
															 forward = "error";
														  }
													 
												      }
													 
												}
											
										}
									}
									else
									{
										httpSession.setAttribute("error", getText("No hay capacidad en el ?mnibus para la fecha de la reservaci?n"));
										forward = "error";
										
									}
								}
								else
								{
									httpSession.setAttribute("error", getText("No hay capacidad en el ?mnibus para la fecha de la reservaci?n"));
									forward = "error";
								}
								
							   }
									 
						        }
					          }
							else
							{
								httpSession.setAttribute("error", getText("No existen hu?spedes para la habitaci?n"));
								forward = "error";
							}
								
				      }
					else
					if (process.equals("DeleteGuest"))
					 {
						int cant_adults = Integer.parseInt(httpSession.getAttribute("cant_adult").toString());
						int cant_childrens = Integer.parseInt(httpSession.getAttribute("cant_children").toString());;
						List<BeanReservation_Person> listGuestAdd;
						listGuestAdd = (List<BeanReservation_Person> )httpSession.getAttribute("listGuestAdd");
						
						 for (int i = 0; i < listGuestAdd.size(); i++)
					     {
							if (listGuestAdd.get(i).getName().equals(getGuest_name()))
							{
								if(listGuestAdd.get(i).getAge()== Integer.parseInt(getGuest_age()))
								{
									if(listGuestAdd.get(i).getAge()> 15)
									{
										 cant_adults--;
									}
									else
									{
										cant_childrens--;
									}
										
									listGuestAdd.remove(i);
									break;
								}
								
							}
							else
							{
								continue;
							}
						 }
						 httpSession.removeAttribute("cant_adult");
						 httpSession.setAttribute("cant_adult", cant_adults);
						 httpSession.removeAttribute("cant_children");
						 httpSession.setAttribute("cant_children", cant_childrens);
					     httpSession.removeAttribute("listGuestAdd");
					     httpSession.setAttribute("listGuestAdd", listGuestAdd);
					     httpSession.removeAttribute("beanReservation_Person");
			
					 }
					else
					if (process.equals("ModifyGuest"))
					{
						List<BeanReservation_Person> listGuestAdd = (List<BeanReservation_Person> )httpSession.getAttribute("listGuestAdd");
							 
						BeanReservation_Person beanReservation_Person = null;
					    for (int i = 0; i < listGuestAdd.size(); i++)
					     {
							if (listGuestAdd.get(i).getName().equals(getGuest_name()))
							{
								if(listGuestAdd.get(i).getAge()== Integer.parseInt(getGuest_age()))
								{
									beanReservation_Person= listGuestAdd.get(i);
									break;
								}
								
							}
							else
							{
								continue;
							}
						 }
						httpSession.setAttribute("beanReservation_Person", beanReservation_Person);
				
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

	public String getDate_to_pay() {
		return date_to_pay;
	}

	public void setDate_to_pay(String date_to_pay) {
		this.date_to_pay = date_to_pay;
	}

	public Date getDate_blocked() {
		return date_blocked;
	}

	public void setDate_blocked(Date date_blocked) {
		this.date_blocked = date_blocked;
	}
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	
	public String getGuest_name() {
		return guest_name;
	}

	public void setGuest_name(String guest_name) {
		this.guest_name = guest_name;
	}

	public String getGuest_age() {
		return guest_age;
	}

	public void setGuest_age(String guest_age) {
		this.guest_age = guest_age;
	}

	public String getName_client() {
		return name_client;
	}

	public void setName_client(String name_client) {
		this.name_client = name_client;
	}

	
    public BeanReservation_Person getGuest() {
		return guest;
	}

	public void setGuest(BeanReservation_Person guest) {
		this.guest = guest;
	}
    
	public String getTypes_status_reservation() {
		return types_status_reservation;
	}

	public void setTypes_status_reservation(String types_status_reservation) {
		this.types_status_reservation = types_status_reservation;
	}

	public String getTypes_transportation() {
		return types_transportation;
	}

	public void setTypes_transportation(String types_transportation) {
		this.types_transportation = types_transportation;
	}
	
	public String getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}
	
	public String getCant_adults() {
		return cant_adults;
	}

	public void setCant_adults(String cant_adults) {
		this.cant_adults = cant_adults;
	}

	public String getCant_childrens() {
		return cant_childrens;
	}

	public void setCant_childrens(String cant_childrens) {
		this.cant_childrens = cant_childrens;
	}

	public String getRooms() {
		return rooms;
	}

	public void setRooms(String rooms) {
		this.rooms = rooms;
	}

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}	
	
	
	
	
	
}
