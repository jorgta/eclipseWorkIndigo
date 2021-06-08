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
public class ReservationChangeAction extends ActionSupport  {

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
	
	
	
	
	public String forward()
	{
		return ERROR;
	}
	
	public void validate() {	
       
        HttpServletRequest request = ServletActionContext.getRequest();
        httpSession = request.getSession(); 
        httpSession.setAttribute("module","pre");
        
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
    			  BeanReservation_Rooms beanReservation_Rooms = (BeanReservation_Rooms)httpSession.getAttribute("roomSelected");
				  
    			  Date date_begin = beanReservation_Rooms.getId_room().getDate_begin();
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
           else
	      if(process.equals("addNewGuest"))
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
			
				
			if(process.equals("returnReservation"))
		     {
		    	 httpSession.removeAttribute("showViewListGuest");
		    	 httpSession.setAttribute("showViewDataReservation", "showViewDataReservation");
		     }else
			     if(process.equals("returReservation"))
			     {
			    	 httpSession.removeAttribute("addGuest");
			     }
			     else
			     if (process.equals("ChangeReservation"))
				 {
				    
				    String query =   " FROM BeanReservation_Rooms rr"+
					                 " WHERE rr.id = " +  getId(); 
					
					java.util.List list = session.createQuery(query).list();
					
					if ( !list.isEmpty() )
					{
						 BeanReservation_Rooms beanReservation_Rooms = (BeanReservation_Rooms)list.get(0);
						 httpSession.setAttribute("roomSelected",beanReservation_Rooms);
						 httpSession.setAttribute("showViewDataReservation","showViewDataReservation");
						 
						 Date dateBegin = beanReservation_Rooms.getId_room().getDate_begin();
						 DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm");
						 String date_begin2 = date.format(dateBegin);
						 httpSession.setAttribute("date_begin", date_begin2);
						 Date dateEnd = beanReservation_Rooms.getId_room().getDate_end();
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
						    BeanReservation_Rooms beanReservation_Rooms = (BeanReservation_Rooms)httpSession.getAttribute("roomSelected");
						    Date date_begin = beanReservation_Rooms.getId_room().getDate_begin();
							Date date_end = beanReservation_Rooms.getId_room().getDate_end();
							Date date_to_pay = Utils.StrToDate( getDate_to_pay(),"dd/mm/yy") ;
							httpSession.setAttribute("type_status_reservation", types_status_reservation);
							httpSession.setAttribute("type_transportation", types_transportation);
							httpSession.setAttribute("name_client", getName_client());
							httpSession.setAttribute("notes", getNotes());
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
									
									tx = session.beginTransaction();
								
									BeanReservation beanReservation = (BeanReservation)session.get(BeanReservation.class,beanReservation_Rooms.getId_reservation().getId());
							    	beanReservation.setId_type_status_reservation(beanStatus);
							    	beanReservation.setId_transport(beanTransport);
							    	beanReservation.setClient_name(getName_client().toUpperCase());
									beanReservation.setDate_to_pay( date_to_pay);
									beanReservation.setNotes(getNotes());
									beanReservation.setPrice(0);
									beanReservation.setDate_begin(date_begin);
									beanReservation.setDate_end(date_end);
									
									
									
								    BeanRoom beanRoom = (BeanRoom)session.get(BeanRoom.class,beanReservation_Rooms.getId_room().getId());
										
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
										    
										    String status =   " FROM BeanType_Status_Room r"+
							  				  				  " WHERE r.id = " +  3;
							
											java.util.List status_room = session.createQuery(status).list();
											if ( !status_room.isEmpty() )
											{
												BeanType_Status_Room bean_status = (BeanType_Status_Room)status_room.get(0);
												beanRoom.setId_type_status_room(bean_status);
												
											}
										    
									}
									else
									if(beanReservation.getId_type_status_reservation().getId() == 1)
									{
										    Calendar fecha = Calendar.getInstance();
											fecha.add(Calendar.HOUR, hour2UnblockedwhitoutPay);
											Calendar blocked= fecha;
											beanReservation.setDate_blocked(blocked.getTime());
											
											String status1 =   " FROM BeanType_Status_Room r"+
															   " WHERE r.id = " +  2;
											java.util.List status_room1 = session.createQuery(status1).list();
											if ( !status_room1.isEmpty() )
											{
												BeanType_Status_Room bean_status = (BeanType_Status_Room)status_room1.get(0);
												beanRoom.setId_type_status_room(bean_status);
											}
											
									}
									else
									if(beanReservation.getId_type_status_reservation().getId() == 3)
									{
										    Calendar blocked = Calendar.getInstance();
											beanReservation.setDate_blocked(blocked.getTime());
											
											String status2 =   " FROM BeanType_Status_Room r"+
															   " WHERE r.id = " +  4;
											java.util.List status_room2 = session.createQuery(status2).list();
											if ( !status_room2.isEmpty() )
											{
												BeanType_Status_Room bean_status = (BeanType_Status_Room)status_room2.get(0);
												beanRoom.setId_type_status_room(bean_status);
											}
									}
									session.update(beanReservation);
									session.update(beanRoom);
									
									tx.commit();
								
								  httpSession.setAttribute("beanReservation_Rooms", beanReservation_Rooms);
								  httpSession.removeAttribute("showViewDataReservation");
								  httpSession.setAttribute("showViewListGuest", "showViewListGuest");
								  
								  List<BeanReservation_Person> listGuestAdd = new ArrayList<BeanReservation_Person>();
									 

									String guest =   " FROM BeanReservation_Person rp"+
													 " WHERE rp.id_reservation_room.id = " + beanReservation_Rooms.getId();
									
									java.util.List list_guest = session.createQuery(guest).list();
									if ( !list_guest.isEmpty() )
									{
										for (int i = 0; i < list_guest.size(); i++) {
											BeanReservation_Person beanReservation_Person = (BeanReservation_Person)list_guest.get(i);
											listGuestAdd.add(beanReservation_Person);
											
										}
										
									}
									
									 httpSession.setAttribute("listGuestAdd", listGuestAdd);
									 httpSession.setAttribute("beanRoom", beanRoom);
									 httpSession.setAttribute("beanReservation", beanReservation);
								}
				}
				else
					if(process.equals("addNewGuest"))
				    {
				    	BeanReservation_Rooms beanReservation_Rooms = (BeanReservation_Rooms)httpSession.getAttribute("roomSelected");
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
								   price = beanReservation_Rooms.getId_room().getPrice1();
							 }
						     else
							 if(Integer.parseInt(guest_age) == 0)
							 {
								price = beanReservation_Rooms.getId_room().getPriceBaby();
							   
							 }
						   else
							if(Integer.parseInt(guest_age) > 2 && Integer.parseInt(guest_age)< 5)
							{
								price = beanReservation_Rooms.getId_room().getPriceBasic() ;
								
							}
							else
							if(Integer.parseInt(guest_age)>= 5  && Integer.parseInt(guest_age)<= 12)
							{
								price = beanReservation_Rooms.getId_room().getPriceChild();
								
							}
							else
							{
								price = beanReservation_Rooms.getId_room().getPriceJr();
								
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
														httpSession.setAttribute("done","El huésped se ha modificado con éxito");
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
								httpSession.setAttribute("listGuestAdd",listGuestAdd);
								
						 }
									 
						
				    }
			     else
			    	 if(process.equals("confirmReservation"))
						 {
						    BeanReservation_Rooms beanReservation_Rooms = (BeanReservation_Rooms)httpSession.getAttribute("roomSelected");
						    BeanRoom beanRoom = (BeanRoom)httpSession.getAttribute("beanRoom");
						    BeanReservation beanReservation = (BeanReservation)httpSession.getAttribute("beanReservation");
							List<BeanReservation_Person> listGuestAdd;
							listGuestAdd = (List<BeanReservation_Person> )httpSession.getAttribute("listGuestAdd");
							
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
									
										//obtener cantidad de adultos y los niños por grupo de edades
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
											price_total+=beanReservation_Rooms.getId_room().getPrice1();
										}
										else
										if(cantAdults == 2)
										{
											price_total+=beanReservation_Rooms.getId_room().getPrice2();
										}
										else
										if(cantAdults == 3)
										{
											price_total+=beanReservation_Rooms.getId_room().getPrice3();
										}
										else
										{
											price_total+=beanReservation_Rooms.getId_room().getPrice4();
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
													//sumar al precio total la cantidad de niños por grupos de edades por tarifa
													
													price_total+=(cantBaby*beanReservation_Rooms.getId_room().getPriceBaby())+(cantBasic*beanReservation_Rooms.getId_room().getPriceBasic())+(cantChild*beanReservation_Rooms.getId_room().getPriceChild())+(cantjr*beanReservation_Rooms.getId_room().getPriceJr());
													
												    tx = session.beginTransaction();
												     
												    String reservation_persons =   " FROM BeanReservation_Person rp"+
												    							    " WHERE rp.id_reservation_room.id = " +  beanReservation_Rooms.getId();
													java.util.List reservations_persons = session.createQuery(reservation_persons).list();
													if ( !reservations_persons.isEmpty() )
													{
														for (int i = 0; i < reservations_persons.size(); i++) 
														{
															BeanReservation_Person beanReservation_Person = (BeanReservation_Person)reservations_persons.get(i);
															beanReservation_Person = (BeanReservation_Person)session.get(BeanReservation_Person.class, beanReservation_Person.getId());
															session.delete(beanReservation_Person);
														}
														
													}
												    
													 for (BeanReservation_Person beanReservation_Person : listGuestAdd) 
													 {
														     beanReservation_Person.setId_reservation_room(beanReservation_Rooms);
															 session.save(beanReservation_Person);
													 }
													 
													 tx.commit();
													 if (new SessionUtil().prepareDataForChangeReservation(request,beanUser,session) == 1) 
													 {  
														httpSession.setAttribute("done", "Reservación modificada con éxito");
														forward = "success";

													 }
													else
													 {				 
													    forward = "error";
													 }
												 
													
												}
												else
												{
													httpSession.setAttribute("error", getText("No hay capacidad en el ómnibus para la fecha de la reservación"));
													forward = "error";
													
												}
												
											}
											else
											{
												httpSession.setAttribute("error", getText("No hay capacidad en el ómnibus para la fecha de la reservación"));
												forward = "error";
												
											}
											
										}
									 }			
										
							       }
								else
								{
									httpSession.setAttribute("error", getText("No existen huéspedes para la habitación"));
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
									if(listGuestAdd.get(i).getId()==getId())
									{
										listGuestAdd.remove(i);
										break;
									}
									else
									{
										continue;
									}
									 
								}
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
								    	 if(listGuestAdd.get(i).getId()==getId())
											{
								    		    beanReservation_Person= listGuestAdd.get(i);
												break;
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
	
	
		
}
