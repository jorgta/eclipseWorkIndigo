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
public class NewReservationAction extends ActionSupport  {

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
	private String types_reservation;
	private String types_status_reservation;
	private String types_transportation;
	private String cotizacion;
	
	
	
	
	public String forward()
	{
		return ERROR;
	}
	
	public void validate() {	
       
        HttpServletRequest request = ServletActionContext.getRequest();
        httpSession = request.getSession(); 
        httpSession.setAttribute("module","pre");
        
    	if(process.equals("addGuest"))
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
	    	  if (types_reservation.length()!= 0)
	    	  {
	    	    if(types_reservation.equals("0"))
	    	    {
	    	    	this.addFieldError("types_reservation",getText("error.field.required"));
	    	    }
	    	    else
	    	    {
	    	    	httpSession.setAttribute("type_reservation", types_reservation);
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
				  forward =  "logout";
			
				
			     if(process.equals("returReservation"))
			     {
			    	 httpSession.removeAttribute("addGuest");
			     }
			     else
			     if (process.equals("SelectedRoom2Reservation"))
				 {
				    
				    String query =   " FROM BeanRoom r"+
					                 " WHERE r.id = " +  getId(); 
					
					java.util.List list = session.createQuery(query).list();
					
					if ( !list.isEmpty() )
					{
						 BeanRoom room = (BeanRoom)list.get(0);
						 httpSession.setAttribute("roomSelected",room);
						 Date dateBegin = room.getDate_begin();
						 DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm");
						 String date_begin2 = date.format(dateBegin);
						 httpSession.setAttribute("date_begin", date_begin2);
						 Date dateEnd = room.getDate_end();
						 DateFormat date2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
						 String date_end2 = date2.format(dateEnd);
						 httpSession.setAttribute("date_end", date_end2);
	     			}
					else
					{  
						httpSession.setAttribute("attention",getText("label.reservation.selectRoom"));
					}
				 }
				else
				 if(process.equals("addReservation"))
				 {
					    BeanRoom beanRoom = (BeanRoom)httpSession.getAttribute("roomSelected");
					    Date date_begin = beanRoom.getDate_begin();
						Date date_end = beanRoom.getDate_end();
						Date date_to_pay = Utils.StrToDate( getDate_to_pay(),"dd/mm/yy") ;
						httpSession.setAttribute("type_reservation", types_reservation);
						httpSession.setAttribute("type_status_reservation", types_status_reservation);
						httpSession.setAttribute("type_transportation", types_transportation);
						httpSession.setAttribute("name_client", getName_client());
						httpSession.setAttribute("notes", getNotes());
						//httpSession.setAttribute("date_to_pay2", date_to_pay.toString());
						DateFormat date2 = new SimpleDateFormat("dd/MM/yyyy");
						String date_to_pay2 = date2.format(date_to_pay);
						httpSession.setAttribute("date_to_pay2", date_to_pay2);
					    
						if(cotizacion != null)
							httpSession.setAttribute("cotizacion", cotizacion);
						
						if( date_to_pay.getTime() > date_begin.getTime() )
						{
							httpSession.setAttribute("error", getText("error.date.to.pay"));
							forward = "error";
						}
						else
						{   
							    BeanType_Status_Reservation beanStatus = null;
							    BeanType_Reservation beanType = null;
							    BeanTransport beanTransport = null;
							    
							    String query =   " FROM BeanType_Status_Reservation s"+
											     " WHERE s.id = " +  types_status_reservation; 

								java.util.List list = session.createQuery(query).list();
								
								if ( !list.isEmpty() )
								{
								  beanStatus = (BeanType_Status_Reservation)list.get(0);
								 
								}
								String query2 =   " FROM BeanType_Reservation t"+
												  " WHERE t.id = " +  types_reservation; 
					
								java.util.List list2 = session.createQuery(query2).list();
								
								if ( !list2.isEmpty() )
								{
									 beanType = (BeanType_Reservation)list2.get(0);
							    }
								String query3 =   " FROM BeanTransport t"+
												  " WHERE t.id = " +  types_transportation; 
					
								java.util.List list3 = session.createQuery(query3).list();
								
								if ( !list3.isEmpty() )
								{
									beanTransport = (BeanTransport)list3.get(0);
								}
							
								
							        BeanReservation beanReservation = new BeanReservation();
							    	BeanReservation_Rooms beanReservation_Rooms = new BeanReservation_Rooms();
							    	beanReservation.setId_type_status_reservation(beanStatus);
							    	beanReservation.setId_type_reservation(beanType);
							    	beanReservation.setId_transport(beanTransport);
							    	beanReservation.setClient_name(getName_client().toUpperCase());
									beanReservation.setDate_to_pay( date_to_pay);
									beanReservation.setNotes(getNotes());
									beanReservation.setPrice(0);
									beanReservation.setDate_begin(date_begin);
									beanReservation.setDate_end(date_end);
									
									int hour2UnblockedRoomWhithPayComplete = beanRoom.getId_hotel().getId_company().getRoomMustPayAllBeforeReserv();
									int hour2UnblockedwhitoutPay = beanRoom.getId_hotel().getId_company().getRoomFirstPayment();
									
									
									if(beanReservation.getId_type_status_reservation().getId() == 2)
									{
										String status =   " FROM BeanType_Status_Room r"+
										  				  " WHERE r.id = " +  3;
										java.util.List status_room = session.createQuery(status).list();
										if ( !status_room.isEmpty() )
										{
											BeanRoom room =(BeanRoom)session.get(BeanRoom.class,beanRoom.getId());
											room.setId_type_status_room((BeanType_Status_Room)status_room.get(0));
											httpSession.removeAttribute("roomSelected");
											httpSession.setAttribute("roomSelected", room);
											beanReservation_Rooms.setId_room(room);
											
											Calendar fecha = Calendar.getInstance();
											fecha.add(Calendar.HOUR, hour2UnblockedRoomWhithPayComplete);
											Calendar blocked= fecha;
											
											if(blocked.getTime().after(date_begin))
											{
												blocked.setTime(date_begin);
												blocked.add(Calendar.HOUR, -24);
											}
										beanReservation.setDate_blocked(blocked.getTime());
										beanReservation_Rooms.setId_room(room);
										beanReservation_Rooms.setId_reservation(beanReservation);
										
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
										BeanRoom room =(BeanRoom)session.get(BeanRoom.class,beanRoom.getId());
										room.setId_type_status_room((BeanType_Status_Room)status_room1.get(0));
										httpSession.removeAttribute("roomSelected");
										httpSession.setAttribute("roomSelected", room);
										beanReservation_Rooms.setId_room(room);
										Calendar fecha = Calendar.getInstance();
										fecha.add(Calendar.HOUR, hour2UnblockedwhitoutPay);
										Calendar blocked= fecha;
										beanReservation.setDate_blocked(blocked.getTime());
										beanReservation_Rooms.setId_room(room);
										beanReservation_Rooms.setId_reservation(beanReservation);
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
										BeanRoom room =(BeanRoom)session.get(BeanRoom.class,beanRoom.getId());
										room.setId_type_status_room((BeanType_Status_Room)status_room2.get(0));
										httpSession.removeAttribute("roomSelected");
										httpSession.setAttribute("roomSelected", room);
										beanReservation_Rooms.setId_room(room);
										Calendar blocked = Calendar.getInstance();
										beanReservation.setDate_blocked(blocked.getTime());
										beanReservation_Rooms.setId_room(room);
										beanReservation_Rooms.setId_reservation(beanReservation);
									}
								}
							
								
								httpSession.setAttribute("beanReservation", beanReservation);
								httpSession.setAttribute("beanReservation_Rooms", beanReservation_Rooms);
								
						}
						httpSession.setAttribute("addGuest", "addGuest");
						
				  }
				else
			    if(process.equals("addGuest"))
			    {
			    	
			    	BeanRoom beanRoom = (BeanRoom)httpSession.getAttribute("roomSelected");
			    	BeanReservation beanReservation = (BeanReservation)httpSession.getAttribute("beanReservation");
			    	
			    	double price = 0;
			        int cantAdults= 0;
			        boolean exist = false;
			        BeanControlPanel beanControlPanel =(BeanControlPanel)session.get(BeanControlPanel.class,1);
			        int max_persons_to_room = beanControlPanel.getMax_persons_per_room();
			        int min_adults_per_room = beanControlPanel.getMin_adults_per_room();
			        int max_adults_per_room = beanControlPanel.getMax_adults_per_room();
						   							
							if(Integer.parseInt(guest_age) > 15)
								   price = beanRoom.getPrice1();
							   else
								if(Integer.parseInt(guest_age) == 0)
									price = beanRoom.getPriceBaby();
							   else
								if(Integer.parseInt(guest_age) > 2 && Integer.parseInt(guest_age)< 5)
									price = beanRoom.getPriceBasic() ;
								else
								if(Integer.parseInt(guest_age)>= 5  && Integer.parseInt(guest_age)<= 12)
									price = beanRoom.getPriceChild();
								else
									price = beanRoom.getPriceJr();
							     BeanReservation_Person reservation_Person = (BeanReservation_Person)httpSession.getAttribute("beanReservation_Person");
							
								 List<BeanReservation_Person> listGuestAdd;
								 listGuestAdd = (List<BeanReservation_Person> )httpSession.getAttribute("listGuestAdd");
								 BeanReservation_Person beanReservation_Person = new BeanReservation_Person();
								 if(listGuestAdd == null)
								 {
									   listGuestAdd = new ArrayList<BeanReservation_Person>() ;
									   beanReservation_Person = new BeanReservation_Person();
					                   beanReservation_Person.setAge(Integer.parseInt(getGuest_age()));
					                   String name = getGuest_name().toUpperCase();
					                   beanReservation_Person.setName(name);
					                   beanReservation_Person.setPrice(price);
					                   beanReservation_Person.setId_reservation(beanReservation);
					                   listGuestAdd.add(beanReservation_Person);
								       httpSession.setAttribute("listGuestAdd",listGuestAdd);
								       httpSession.removeAttribute("beanReservation_Person");
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
											    	for (int i = 0; i < listGuestAdd.size(); i++) 
													{
														 if(listGuestAdd.get(i).getName().equals(reservation_Person.getName()))
														 {
															 if(listGuestAdd.get(i).getAge()== reservation_Person.getAge())
															 {
																 pos = i;
																 // listGuestAdd.remove(i);
																 break;
															 }
														 }
														 else
															 continue;
														 
													  }
											    	beanReservation_Person = new BeanReservation_Person();
									                beanReservation_Person.setAge(Integer.parseInt(getGuest_age()));
									                //String name = getGuest_name().toUpperCase();
									                beanReservation_Person.setName(name);
									                beanReservation_Person.setPrice(price);
									                beanReservation_Person.setId_reservation(beanReservation);
									                listGuestAdd.set(pos, beanReservation_Person);
									                //listGuestAdd.add(beanReservation_Person);
											    	
											    	httpSession.removeAttribute("beanReservation_Person");
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
										if(listGuestAdd.size()== max_persons_to_room)
										{
											httpSession.setAttribute("error", getText("error.max.person.per.room"));
											forward = "error";
										}
										else
										{
										    for (int i = 0; i < listGuestAdd.size(); i++) 
											 {
										    	String name = getGuest_name().toUpperCase();
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
											   for (int i = 0; i < listGuestAdd.size(); i++) 
												{
													BeanReservation_Person temp = (BeanReservation_Person)listGuestAdd.get(i);
												
													if(temp.getAge() > 15)
													{
														cantAdults++;
													}
												}
											   if(cantAdults > max_adults_per_room )
												{
													httpSession.setAttribute("error", getText("error.max.adults.per.room"));
													forward = "error";
												}
												else
												{
												   beanReservation_Person.setAge(Integer.parseInt(getGuest_age()));
								                   String name = getGuest_name().toUpperCase();
												   beanReservation_Person.setName(name);
								                   beanReservation_Person.setPrice(price);
								                   beanReservation_Person.setId_reservation(beanReservation);
								                   listGuestAdd.add(beanReservation_Person);
											       httpSession.setAttribute("listGuestAdd",listGuestAdd);
											       httpSession.removeAttribute("beanReservation_Person");
											    }
											}
											
		
										}
									
								  }
			                    }
					    
						
				   }
			     else
				   if(process.equals("confirmReservation"))
				   {
					    BeanReservation beanReservation = (BeanReservation)httpSession.getAttribute("beanReservation");
						BeanRoom beanRoom = (BeanRoom)httpSession.getAttribute("roomSelected");
						BeanReservation_Rooms beanReservation_Rooms = (BeanReservation_Rooms)httpSession.getAttribute("beanReservation_Rooms");
						List<BeanReservation_Person> listGuestAdd;
						listGuestAdd = (List<BeanReservation_Person> )httpSession.getAttribute("listGuestAdd");
								
						int cantAdults= 0;
						int cantBaby = 0;   // 0 - 24
						int cantBasic = 0;  //2 - 5
						int cantChild = 0; //  5- 12
						int cantjr = 0;    // 13 - 15 
						double price_total = 0;
							  
						BeanControlPanel beanControlPanel =(BeanControlPanel)session.get(BeanControlPanel.class,1);
						int max_persons_to_room = beanControlPanel.getMax_persons_per_room();
						int min_adults_per_room = beanControlPanel.getMin_adults_per_room();
						int max_adults_per_room = beanControlPanel.getMax_adults_per_room();
							 
							  if(listGuestAdd.size()!= 0)
							  {
								   if (listGuestAdd.size() > max_persons_to_room)
								   {
									  httpSession.setAttribute("error", getText("error.max.person.per.room"));
									  forward = "error";
								   }
								   else
								   {
									  for (int i = 0; i < listGuestAdd.size(); i++) 
									  {
											BeanReservation_Person temp = (BeanReservation_Person)listGuestAdd.get(i) ;
										
											if(temp.getAge() > 15)
											{
												cantAdults++;
											    price_total+=temp.getPrice();
											}
											else
											if(temp.getAge()== 0)
											{
												cantBaby++;
											    price_total+=temp.getPrice();
											}
											else
											if(temp.getAge()> 2 && temp.getAge()< 5)
											{
												cantBasic++;
											    price_total+=temp.getPrice();
											}
											else
											if(temp.getAge()>= 5  && temp.getAge()<= 12)
											{
												cantChild++;
											    price_total+=temp.getPrice();
											}
											else
											{
												cantjr++;
												price_total+=temp.getPrice();
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
										 {
											beanReservation.setPrice(price_total);
											
											String cotizacion = (String)httpSession.getAttribute("cotizacion");
											tx = session.beginTransaction();
											
											if(cotizacion != null)
											{
												BeanQuote beanQuote = new BeanQuote();
												beanQuote.setClient_name(beanReservation.getClient_name());
												beanQuote.setDate_begin(beanReservation.getDate_begin());
												beanQuote.setDate_blocked(beanReservation.getDate_blocked());
												beanQuote.setDate_end(beanReservation.getDate_end());
												beanQuote.setDate_to_pay(beanReservation.getDate_to_pay());
												beanQuote.setId_transport(beanReservation.getId_transport());
												beanQuote.setId_type_reservation(beanReservation.getId_type_reservation());
												beanQuote.setNotes(beanReservation.getNotes());
												beanQuote.setPrice(beanReservation.getPrice());
												
												session.save(beanQuote);
												
												BeanQuote_Room beanQuote_Room = new BeanQuote_Room();
												beanQuote_Room.setId_quote(beanQuote);
												beanQuote_Room.setId_room(beanRoom);
												
												session.save(beanQuote_Room);
												
												for (BeanReservation_Person beanReservation_Person : listGuestAdd) 
												{
													BeanQuote_Person beanQuote_Person = new BeanQuote_Person();
													beanQuote_Person.setAge(beanReservation_Person.getAge());
													beanQuote_Person.setName(beanReservation_Person.getName());
													beanQuote_Person.setPrice(beanReservation.getPrice());
													beanQuote_Person.setId_quote(beanQuote);
													session.save(beanQuote_Person);
												}
											}
											else
											{
												session.update(beanRoom);
												session.save(beanReservation);
												
												beanReservation_Rooms.setId_reservation(beanReservation);
												beanReservation_Rooms.setId_room(beanRoom);
												session.save(beanReservation_Rooms);
												
												for (BeanReservation_Person beanReservation_Person : listGuestAdd) 
												{
													
													session.save(beanReservation_Person);
												}
												
											}
											tx.commit();
											
											if (new SessionUtil().prepareDataForNewReservation(request,beanUser,session) == 1) 
											{  
												 httpSession.removeAttribute("listGuestAdd");
												 httpSession.removeAttribute("roomSelected");
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
												 
												 httpSession.setAttribute("done", getText("done.reservation.new"));
												 forward = "success";
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
									 httpSession.setAttribute("error", getText("error.min.person.per.room"));
									  forward = "error";
								 }
						}
			        else
					if (process.equals("DeleteGuest"))
					 {
						List<BeanReservation_Person> listGuestAdd;
						listGuestAdd = (List<BeanReservation_Person> )httpSession.getAttribute("listGuestAdd");
						
						 for (int i = 0; i < listGuestAdd.size(); i++)
					     {
							if (listGuestAdd.get(i).getName().equals(getGuest_name()))
							{
								if(listGuestAdd.get(i).getAge()== Integer.parseInt(getGuest_age()))
								{
									listGuestAdd.remove(i);
									break;
								}
								
							}
							else
							{
								continue;
							}
						 }
						 
					     httpSession.removeAttribute("listGuestAdd");
					     httpSession.setAttribute("listGuestAdd", listGuestAdd);
			
					 }
					else
					if (process.equals("ModifyGuest"))
					{
						List<BeanReservation_Person> listGuestAdd;
						listGuestAdd = (List<BeanReservation_Person> )httpSession.getAttribute("listGuestAdd");
							 
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
    
	public String getTypes_reservation() {
		return types_reservation;
	}

	public void setTypes_reservation(String types_reservation) {
		this.types_reservation = types_reservation;
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
		
}
