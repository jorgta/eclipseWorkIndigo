/*
 * Created on Jul 4, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.bituos;

/**
 * @author dsanchez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.struts2.Beans.*;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import javassist.expr.NewArray;
import java.lang.String;

import javax.servlet.http.*;

//Hibernate 2.1

import org.apache.naming.java.javaURLContextFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

public class SessionUtil {

	
	public int prepareDataForUserNew( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  { 
		HttpSession httpSession = req.getSession();
		
		try
		  {
			

			httpSession.removeAttribute("newUserBeanList");

 			
			//session = session;	
			
			httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    String userRoot ="AND u.name <> 'ROOT'";
			if(beanUser.getName().equals("ROOT"))
				userRoot="";
			 
				
			
				
			String query =  " FROM BeanUser u" +
							" WHERE u.id_company = " + beanUser.getId_company().getId() +
							" AND u.active = 1" +
							" AND u.id <> " + beanUser.getId() + 	
							  userRoot +
			  				" ORDER BY u.name";
			
			
				
			List list = session.createQuery(query).list();
			
			if (!list.isEmpty()) 
			{  
				httpSession.setAttribute("newUserBeanList",list);
				return 1;
			}
			else
			  {			 
				  httpSession.setAttribute("information", "No hay usuarios registrados." );
				  return 0; 
			  }
			
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	
	public int prepareDataForUserDelete( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  {
		
		   HttpSession httpSession = req.getSession();
		try
		  {
			

			httpSession.removeAttribute("listUserDelete");
			httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    
		    String userRoot ="AND u.name <> 'ROOT'";
			if(beanUser.getName().equals("ROOT"))
				userRoot="";
			 
				
			
				
			String query =  " FROM BeanUser u" +
							" WHERE u.id_company = " + beanUser.getId_company().getId() +
							" AND u.id <> " + beanUser.getId() +
							userRoot +
							" AND u.active = 1" +										
							" ORDER BY u.name";
			
			
				
			List list = session.createQuery(query).list();
			
			if (!list.isEmpty()) 
			{  
				httpSession.setAttribute("listUserDelete",list);
				return 1;
			}
			else
			  {			 
				  httpSession.setAttribute("information", "No hay usuarios registrados." );
				  return 0; 
			  }
			
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	
	public int prepareDataForUserChange( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  {
		
		HttpSession httpSession = req.getSession();
		try
		  {
			httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention"); 
		    httpSession.setAttribute("module","pre");
		    String userRoot ="AND u.name <> 'ROOT'";
			httpSession.removeAttribute("beanUser2Change");

			if(beanUser.getName().equals("ROOT"))
				userRoot="";
		 
			String query =   " FROM BeanUser u" 
			               + " WHERE u.id_company.name = '" +  beanUser.getId_company().getName() + "'" 
			               + " AND u.id <> " + beanUser.getId()  
			               +   userRoot
			               + " AND  u.active= 1"
						   + " AND  u.id_company.active = 1";  
				
			List list = session.createQuery(query).list();
			
			if (!list.isEmpty()) 
			{  
				httpSession.setAttribute("changeUserBeanList",list);
				return 1;
			}
			else
			  {			 
				  httpSession.setAttribute("information", "No hay usuarios registrados." );
				  return 0; 
			  }
			
			
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	
	public int prepareDataForUserActivate( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  {
		
		HttpSession httpSession = req.getSession();
		try
		  {
			httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention"); 
		    httpSession.setAttribute("module","pre");
		    String userRoot ="AND u.name <> 'ROOT'";
			httpSession.removeAttribute("listUser2Activate");

			if(beanUser.getName().equals("ROOT"))
				userRoot="";
		 
			 String query = " FROM BeanUser u" +
							" WHERE u.id_company = " + beanUser.getId_company().getId() +
							" AND u.id <> " + beanUser.getId() +
							  userRoot +
							" AND u.active = 0" +										
							" ORDER BY u.name"; 
				
			List list = session.createQuery(query).list();
			
			if (!list.isEmpty()) 
			{  
				httpSession.setAttribute("listUserActivate",list);
				return 1;
			}
			else
			  {			 
				httpSession.setAttribute("preActionError", "No existen usuarios para borrar" );
				  return 0; 
			  }
			
			
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	

	
	public int prepareDataForDeviceNew( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  { 
		HttpSession httpSession = req.getSession();
		
		try
		  {
			

			httpSession.removeAttribute("newDeviceBeanList");

			
			//session = session;	
			
			httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    String userRoot ="AND u.name <> 'ROOT'";
			if(beanUser.getName().equals("ROOT"))
				userRoot="";
			 
				
			
				
			String query =  " FROM BeanDevice u" +
							" WHERE u.id_company = " + beanUser.getId_company().getId() +
							" AND u.active = 1" +							
			  				" ORDER BY u.hostname";
			
			
				
			List list = session.createQuery(query).list();
			
			if (!list.isEmpty()) 
			{  
				httpSession.setAttribute("newDeviceBeanList",list);
				return 1;
			}
			else
			  {			 
				  httpSession.setAttribute("information", "No hay dispositivos registrados." );
				  return 0; 
			  }
			
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	
	public int prepareDataForDeviceDelete( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  {
		
		   HttpSession httpSession = req.getSession();
		try
		  {
			httpSession.removeAttribute("listDeviceDelete");
			httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    
		    String userRoot ="AND u.name <> 'ROOT'";
			if(beanUser.getName().equals("ROOT"))
				userRoot="";
			 
				
			String query =  " FROM BeanDevice u" +
							" WHERE u.id_company = " + beanUser.getId_company().getId() +
							" AND u.active = 1" +							
			  				" ORDER BY u.hostname";
			
			List list = session.createQuery(query).list();
			
			if (!list.isEmpty()) 
			  {  
				httpSession.setAttribute("listDeviceDelete",list);
				return 1;
			  }
			else
			  {			 
				httpSession.setAttribute("information", "No hay dispositivos registrados." );
				return 0; 
			  }
			
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	
	public int prepareDataForDeviceChange( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  {
		
		HttpSession httpSession = req.getSession();
		try
		  {
			httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention"); 
		    httpSession.setAttribute("module","pre");
		    String userRoot ="AND u.name <> 'ROOT'";
			httpSession.removeAttribute("beanDevice2Change");
			httpSession.removeAttribute("deviceSelected"); 
			httpSession.removeAttribute("masterSelected"); 
			httpSession.removeAttribute("listCounter");

			if(beanUser.getName().equals("ROOT"))
				userRoot="";
		 
			String query =  " FROM BeanDevice u" +
							" WHERE u.id_company = " + beanUser.getId_company().getId() +
							" AND u.active = 1" +							
							" ORDER BY u.hostname";  
			
			
			String queryMaster =    " FROM BeanType_Counter_Master u" +
									" WHERE u.active = 1" +							
									" ORDER BY u.name_type_counter_master";  
			
			List listMaster = session.createQuery(queryMaster).list();
			String id = String.valueOf( ((BeanType_Counter_Master)listMaster.get(0)).getId());
			
			String queryDetail =  	" FROM BeanType_Counter_Detail u" +
									" WHERE u.id_type_counter_master = " + ((BeanType_Counter_Master)listMaster.get(0)).getId() +
									" AND u.active = 1" +							
									" ORDER BY u.name_type_counter_detail";  
			
			String queryCounters =  " FROM BeanCounter u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active = 1" +							
									" ORDER BY u.hostname";  
			
				
			
			
			List listDetail = session.createQuery(queryDetail).list();
			List listCounter = new ArrayList<BeanCounter>(); 
			List list = session.createQuery(query).list();
			
			if (!list.isEmpty()) 
			{  
				httpSession.setAttribute("masterSelected",id);
				
				httpSession.setAttribute("changeDeviceBeanList",list);
			    httpSession.setAttribute("changeDeviceListMaster",listMaster);
				httpSession.setAttribute("changeDeviceListDetail",listDetail);
				httpSession.setAttribute("changeDeviceListCounter",listCounter);
				return 1;
			}
			else
			  {			 
				  httpSession.setAttribute("information", "No hay dispositivos registrados." );
				  return 0; 
			  }
			
			
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	
	public int prepareDataForDeviceActivate( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  {
		
		HttpSession httpSession = req.getSession();
		try
		  {
			httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention"); 
		    httpSession.setAttribute("module","pre");
		    String userRoot ="AND u.name <> 'ROOT'";
			httpSession.removeAttribute("listDeviceActivate");
			httpSession.removeAttribute("listDevice2Activate");

			if(beanUser.getName().equals("ROOT"))
				userRoot="";
		 
			String query = " FROM BeanDevice u" +
						   " WHERE u.id_company = " + beanUser.getId_company().getId() +
						   " AND u.active = 0" +							
						   " ORDER BY u.hostname";
				
			List list = session.createQuery(query).list();
			
			if (!list.isEmpty()) 
			{  
				httpSession.setAttribute("listDeviceActivate",list);
				return 1;
			}
			else
			  {			 
				httpSession.setAttribute("preActionError", "No existen dispositivos para activar" );
				return 0; 
			  }
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
		
	

	public int prepareDataForDeviceMonitoring( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  {
		
		HttpSession httpSession = req.getSession();
		try
		  {
			httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention"); 
		    httpSession.setAttribute("module","pre");
		    String userRoot ="AND u.name <> 'ROOT'";
			httpSession.removeAttribute("beanDevice2Change");
			httpSession.removeAttribute("deviceSelected"); 
			httpSession.removeAttribute("masterSelected"); 
			httpSession.removeAttribute("listCounter");

			if(beanUser.getName().equals("ROOT"))
				userRoot="";
		 
			String query =  " FROM BeanDevice u" +
							" WHERE u.id_company = " + beanUser.getId_company().getId() +
							" AND u.active = 1" +							
							" ORDER BY u.hostname";  
			
			
			String queryMaster =    " FROM BeanType_Counter_Master u" +
									" WHERE u.active = 1" +							
									" ORDER BY u.name_type_counter_master";  
			
			List listMaster = session.createQuery(queryMaster).list();
			String id = String.valueOf( ((BeanType_Counter_Master)listMaster.get(0)).getId());
			
			String queryDetail =  	" FROM BeanType_Counter_Detail u" +
									" WHERE u.id_type_counter_master = " + ((BeanType_Counter_Master)listMaster.get(0)).getId() +
									" AND u.active = 1" +							
									" ORDER BY u.name_type_counter_detail";  
			
			String queryCounters =  " FROM BeanCounter u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active = 1" +							
									" ORDER BY u.hostname";  
			
				
			
			
			List listDetail = session.createQuery(queryDetail).list();
			List listCounter = new ArrayList<BeanCounter>(); 
			List list = session.createQuery(query).list();
			
			if (!list.isEmpty()) 
			{  
				httpSession.setAttribute("masterSelected",id);
				
				httpSession.setAttribute("changeDeviceBeanList",list);
			    httpSession.setAttribute("changeDeviceListMaster",listMaster);
				httpSession.setAttribute("changeDeviceListDetail",listDetail);
				httpSession.setAttribute("changeDeviceListCounter",listCounter);
				return 1;
			}
			else
			  {			 
				  httpSession.setAttribute("information", "No hay dispositivos registrados." );
				  return 0; 
			  }
			
			
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	
	
	
	public int prepareDataForCompanyNew( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  {
		
		HttpSession httpSession = req.getSession();
		try
		  {
			httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention"); 
		    httpSession.setAttribute("module","pre");
		    String userRoot ="AND u.name <> 'ROOT'";
		    
		    String query =  " FROM BeanCompany u" +		
		    				" WHERE u.id <> " + beanUser.getId_company().getId() +
							" ORDER BY u.name";
		    				
			
		    
		    List list = session.createQuery(query).list();
		    
		    if (!list.isEmpty()) 
			{  
				httpSession.setAttribute("companyNewBeanList",list);				
				return 1;

			}
			else
			  {
				httpSession.setAttribute("information", "No hay compañias registradas");				
				return 0;
			  }
			
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	
    public int prepareDataForCompanyDelete( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  {
		
		HttpSession httpSession = req.getSession();
		try
		  {
			httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention"); 
		    httpSession.setAttribute("module","pre");
		    String userRoot ="AND u.name <> 'ROOT'";
		    
			String query =  " FROM BeanCompany u" +
							" WHERE u.id <> " + beanUser.getId_company().getId() +
							" AND u.active = 1";

			List list = session.createQuery( query ).list();
			
			if ( list.size() != 0 )
			{							  				   
			  httpSession.setAttribute("listCompanyDelete",list);			  
			  return 1;
			}
			else
			{
			  
			  httpSession.setAttribute("information", "No existen empresas para borrar");		 
			  return 0;
			}

			
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	
	public int prepareDataForCompanyActivate( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  { 
		HttpSession httpSession = req.getSession();
		
		try
		  {
			

			httpSession.removeAttribute("listCompanyActivate");

			httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    httpSession.removeAttribute("listCompany2Activate");
		    String userRoot ="AND u.name <> 'ROOT'";
			if(beanUser.getName().equals("ROOT"))
				userRoot="";
			 
				
			String query =  " FROM BeanCompany u" +
							" WHERE u.id <> " + beanUser.getId_company().getId() +
							" AND u.active = 0";
				

				
			List list = session.createQuery(query).list();
			
			if (!list.isEmpty()) 
			  {  
				 httpSession.setAttribute("listCompanyActivate",list);
				 return 1;
			  }
			else
			  {			 
				 httpSession.setAttribute("information", "No hay compañias para ativar." );
				 return 0; 
			  }
			
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	
	public int prepareDataForCompanyChange( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  { 
		HttpSession httpSession = req.getSession();
		
		try
		  {
			

			httpSession.removeAttribute("listCompanyChange");

			httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    httpSession.removeAttribute("listCompany2Change");
		    httpSession.removeAttribute("beanCompany2Change");
		    httpSession.removeAttribute("currentBeanUserROOT");
		    
		    
		    String userRoot ="AND u.name <> 'ROOT'";
			if(beanUser.getName().equals("ROOT"))
				userRoot="";
			 
				
			String query =  " FROM BeanCompany u" +
							" WHERE u.id <> " + beanUser.getId_company().getId();
				

				
			List list = session.createQuery(query).list();
			
			if (!list.isEmpty()) 
			  {  
				 httpSession.setAttribute("listCompanyChange",list);
				 return 1;
			  }
			else
			  {			 
				 httpSession.setAttribute("information", "No hay compañias para realizar cambios." );
				 return 0; 
			  }
			
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	
	
	public int prepareRptLog( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  { 
		HttpSession httpSession = req.getSession();
		
		try
		  {

			httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");

		    return 1;

		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	
	public int prepareRptReservation( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  { 
		HttpSession httpSession = req.getSession();
		try
		  {
			HttpServletRequest request = ServletActionContext.getRequest();
			httpSession = request.getSession(); 
			 
			Config config = new Config( request );
			
			
			SessionFactory sessionFactory = null;
			
			//Session    session = null;
			Transaction tx = null;
			
			httpSession.removeAttribute("type");
			httpSession.removeAttribute("roomsReserved");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    
		    
		    String query2 = " FROM BeanReservation_Rooms rr" +
		    				" WHERE rr.id_room.id_hotel.id_company.id = " + beanUser.getId_company().getId();

		    List rooms_reservation = session.createQuery(query2).list();
		    
		    if(!rooms_reservation.isEmpty())
		    {
		    	/*Calendar date_actual = Calendar.getInstance();
		    	 
		    	Date date = date_actual.getTime();
		    	for (int i = 0; i < rooms_reservation.size(); i++) 
		    	{
		    		BeanReservation_Rooms beanReservation_Rooms = (BeanReservation_Rooms)rooms_reservation.get(i);
					if(beanReservation_Rooms.getId_reservation().getId_type_status_reservation().getId()== 3)
					{
						if (beanReservation_Rooms.getId_reservation().getDate_begin().after(date))
						{
						   continue;
						}
						else
						{
							rooms_reservation.remove(i);
						}
					}
					else
					if (beanReservation_Rooms.getId_reservation().getDate_blocked().after(date))
					{
					   continue;
					}
					else
					{
						rooms_reservation.remove(i);
					}
				}*/
		    			    	
		    	httpSession.setAttribute("roomsReserved",rooms_reservation);
				return 1;   
		    }
		    
		    else
		    {
				return 0;
		    }
			
	    	
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	
	
	public int prepareRptQuote( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  { 
		HttpSession httpSession = req.getSession();
		try
		  {
			HttpServletRequest request = ServletActionContext.getRequest();
			httpSession = request.getSession(); 
			 
			Config config = new Config( request );
			
			
			SessionFactory sessionFactory = null;
			
			//Session    session = null;
			Transaction tx = null;
			
			httpSession.removeAttribute("type");
			httpSession.removeAttribute("roomsReserved");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    
		    
		    String query2 = " FROM BeanQuote_Room rr" +
		    				" WHERE rr.id_room.id_hotel.id_company.id = " + beanUser.getId_company().getId();

		    List listQuoteRoom = session.createQuery(query2).list();
		    
		    if(!listQuoteRoom.isEmpty())
		    {		    	
		    			    	
		    	httpSession.setAttribute("listQuoteRoom",listQuoteRoom);
				return 1;   
		    }
		    
		    else
		    {
				return 0;
		    }
			
	    	
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	
	public int prepareDataForHotelNew( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  {
		HttpSession httpSession = req.getSession();
		try
		  {
			

			httpSession.removeAttribute("hotelsNewBeanList");
      
			//session = session;	
			
			httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    	
		    String query =   " FROM BeanHotel u"+ 
				              " WHERE u.id_company.id = " + beanUser.getId_company().getId()+
				              " AND u.active = 1";
				
		    List list = session.createQuery(query).list();
			
			if (!list.isEmpty()) 
			{  
				httpSession.setAttribute("hotelsNewBeanList",list);
				return 1;

			}
			else
			  {
				httpSession.setAttribute("information", "No hay hoteles registrados");
				return 0;
			  }
			
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	

	
	
	
	public int prepareDataForHotelActivate( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  {
		
		HttpSession httpSession = req.getSession();
		try
		  {
			//httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention"); 
		    httpSession.setAttribute("module","pre");
		    httpSession.removeAttribute("listHotel2Activate");

				 
			 String query = " FROM BeanHotel h" +
							" WHERE h.id_company = " + beanUser.getId_company().getId() +
							" AND h.active = 0 " +										
							" ORDER BY h.description"; 
				
			List list = session.createQuery(query).list();
			
			if (!list.isEmpty()) 
			{  
				httpSession.setAttribute("listHotelActivate",list);
				return 1;
			}
			else
			  {			 
				httpSession.setAttribute("preActionError", "No existen hoteles para desactivar" );
				 return 0; 
			  }
			
			
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	
	public int prepareDataForRoomNew( HttpServletRequest req, BeanUser beanUser ,Session    session)
	  {
		HttpSession httpSession = req.getSession();
		try
		  {
			
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    
		    String query = " FROM BeanHotel u"+ 
                           " WHERE u.id_company.id = " + beanUser.getId_company().getId()+
                           " AND u.active = 1";
     
		    List list = session.createQuery(query).list();
		   		    
			if (!list.isEmpty()) 
			{  
				httpSession.setAttribute("listHotel2AddRoom",list);
				return 1;
			}
			
			else
		    {
				return 0;
		    }
			
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	public int prepareDataForlistSpecs( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  {
		HttpSession httpSession = req.getSession();
		try
		  {
			

			httpSession.removeAttribute("listSpecs2AddRoom");
    
			//session = session;	
			
			//httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    	
		    String query =   " FROM BeanSpec s";
				
		    List list = session.createQuery(query).list();
			
			if (!list.isEmpty()) 
			{  
				httpSession.setAttribute("listSpecs2AddRoom",list);
				return 1;

			}
			else
			  {
				httpSession.setAttribute("information", "No hay características registradas");
				return 0;
			  }
			
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	
	
	

	
	
	public int prepareDataForRoomDelete( HttpServletRequest req, BeanUser beanUser ,Session    session)
	  {
		HttpSession httpSession = req.getSession();
		try
		  {
			
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    
		  	
		    String query =  " FROM BeanRoom r" + 
						    " WHERE r.active = 1 AND r.id_type_status_room.id = 1";  

				java.util.List list = session.createQuery(query).list();
				 
				if (!list.isEmpty() )
				{
					httpSession.setAttribute("listRoomsHability",list);
					return 1;
					
				}
				else
				{
					return 0;
				}
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	public int prepareDataForRoomActivate( HttpServletRequest req, BeanUser beanUser ,Session    session)
	  {
		HttpSession httpSession = req.getSession();
		try
		  {
			
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    
		  	
		    String query =  " FROM BeanRoom r" + 
						    " WHERE r.active = 0 ";  

				java.util.List list = session.createQuery(query).list();
				 
				if (!list.isEmpty() )
				{
					httpSession.setAttribute("listRoomsNotHability",list);
					return 1;
					
				}
				else
				{
					return 0;
				}
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	public int prepareDataForRoomChange( HttpServletRequest req, BeanUser beanUser ,Session    session)
	{
		HttpSession httpSession = req.getSession();
		try
		  {
			
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    
		  	
		    String query =  " FROM BeanRoom r" + 
							" WHERE r.active = 1 AND r.id_type_status_room.id = 1";  

		    	java.util.List list = session.createQuery(query).list();
		         
		    	if (!list.isEmpty() )
		    	{
		    		httpSession.setAttribute("listRoomsHability",list);
					return 1;
		    		
		    	}
		    	else
			    {
					return 0;
			    }
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }

	
	public int prepareDataForNewReservation( HttpServletRequest req, BeanUser beanUser ,Session    session)
	  {
		HttpSession httpSession = req.getSession();
		try
		  {
			/*HttpServletRequest request = ServletActionContext.getRequest();
			httpSession = request.getSession(); 
			 
			Config config = new Config( request );*/
			
			
			 SessionFactory sessionFactory = null;
			
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
			 
			 
			 
			 String query =   " FROM BeanType_Status_Reservation"; 
			
			 List list = session.createQuery(query).list();
			 
			 if ( !list.isEmpty() )
			 {
				httpSession.setAttribute("types_status_reservation", list);
		     }
			 query =   " FROM BeanTransport transport"; 
			
			 list = session.createQuery(query).list();
			
			 if ( !list.isEmpty() )
			 {
				httpSession.setAttribute("types_transportation", list);
		     }
			
			//Session    session = null;
			Transaction tx = null;
			
			
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    
		    String query2 = " FROM BeanReservation_Rooms rr"+
				            " WHERE rr.id_reservation.id_type_status_reservation = 1";

		    List rooms_reservationBlocked = session.createQuery(query2).list();
		    List<BeanReservation_Rooms> lisRoom2Unblocked = new ArrayList<BeanReservation_Rooms>();
			if(!rooms_reservationBlocked.isEmpty())
		    {
		       Calendar date_actual = Calendar.getInstance();
		        for (int i = 0; i < rooms_reservationBlocked.size(); i++)
			    {
			    	BeanReservation_Rooms beanReservation_Rooms = (BeanReservation_Rooms)rooms_reservationBlocked.get(i);
					
			    	    if(beanReservation_Rooms.getId_reservation().getDate_blocked().equals(date_actual.getTime()) || beanReservation_Rooms.getId_reservation().getDate_blocked().before(date_actual.getTime()))
						{
							BeanType_Status_Room type_status = (BeanType_Status_Room)session.get(BeanType_Status_Room.class,1);
							beanReservation_Rooms = (BeanReservation_Rooms)session.get(BeanReservation_Rooms.class,beanReservation_Rooms.getId());
							//BeanReservation beanReservation = (BeanReservation)session.get(BeanReservation.class,beanReservation_Rooms.getId_reservation().getId());
							lisRoom2Unblocked.add(beanReservation_Rooms);
							tx = session.beginTransaction();
							BeanRoom beanRoom = (BeanRoom)session.get(BeanRoom.class,beanReservation_Rooms.getId_room().getId());
							beanRoom.setId_type_status_room(type_status);
							
							query = " FROM BeanReservation_Person rp"+
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
						}
			    	
						
				}
		    }
		    
		   query = " FROM BeanRoom r"+
		           " WHERE r.active = 1" +
		           " AND r.id_type_status_room.id = 1" +
		           " OR r.id_type_status_room.id = 2" +
		           " ORDER BY id_type_status_room.id,description";  //devuelvo todas las habitaciones que están libres y que no fueron reservadas completamente 

		    
		   
		    List room2Show = session.createQuery(query).list();
		    
		    if (!room2Show.isEmpty()) 
			{  
		    	httpSession.setAttribute("roomsList",room2Show);
				httpSession.setAttribute("lisRoom2Unblocked",lisRoom2Unblocked);
				//httpSession.setAttribute("listRoomsBlocked",rooms_reservationBlocked);
				return 1;
			}
			else
		    {
				return 0;
		    }
		 }
		catch( Throwable  m )
		{
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		}
	}
	public int prepareDataForDeleteReservation( HttpServletRequest req, BeanUser beanUser ,Session    session)
	  {
		HttpSession httpSession = req.getSession();
		try
		  {
					
			
			Transaction tx = null;
			
			
			httpSession.removeAttribute("roomsReserved");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    httpSession.removeAttribute("roomSelected"); 
		    
		    
		    String query2 = "FROM BeanReservation_Rooms rr" ;   

		    List rooms_reservation = session.createQuery(query2).list();
		    
		    if(!rooms_reservation.isEmpty())
		    {
		    	Calendar date_actual = Calendar.getInstance();
		    	//date_actual.add(Calendar.MONTH, 1);
		    	Date date = date_actual.getTime();
		    	for (int i = 0; i < rooms_reservation.size(); i++) 
		    	{
		    		BeanReservation_Rooms beanReservation_Rooms = (BeanReservation_Rooms)rooms_reservation.get(i);
					if(beanReservation_Rooms.getId_reservation().getId_type_status_reservation().getId()== 3)
					{
						if (beanReservation_Rooms.getId_reservation().getDate_begin().after(date))
						{
						   continue;
						}
						else
						{
							rooms_reservation.remove(i);
						}
					}
					else
					if (beanReservation_Rooms.getId_reservation().getDate_blocked().after(date))
					{
					   continue;
					}
					else
					{
						rooms_reservation.remove(i);
					}
				}
		    			    	
		    	httpSession.setAttribute("roomsReserved",rooms_reservation);
				return 1;   
		    }
		    
		    else
		    {
				return 0;
		    }
			
	    	
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
	public int prepareDataForChangeReservation( HttpServletRequest req, BeanUser beanUser ,Session    session)
	  {
		HttpSession httpSession = req.getSession();
		try
		  {
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
			 httpSession.removeAttribute("addReservation");
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
			 httpSession.removeAttribute("roomSelected");

			 
			
			String query =   " FROM BeanType_Status_Reservation status"; 
			
			List list = session.createQuery(query).list();
			
			if ( !list.isEmpty() )
			{
				httpSession.setAttribute("types_status_reservation", list);
		    }
			query =   " FROM BeanTransport transport"; 
			
			list = session.createQuery(query).list();
			
			if ( !list.isEmpty() )
			{
				httpSession.setAttribute("types_transportation", list);
		    }
			

			Transaction tx = null;
			httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    
		    String query2 = " FROM BeanReservation_Rooms rr"+
				            " WHERE rr.id_reservation.id_type_status_reservation <> 3";   //

		    List rooms_reservationBlocked = session.createQuery(query2).list();
		    
		    if(!rooms_reservationBlocked.isEmpty())
		    {
		       Calendar blocked = Calendar.getInstance();
			   
			    for (int i = 0; i < rooms_reservationBlocked.size(); i++)
			    {
			    	BeanReservation_Rooms beanReservation_Rooms = (BeanReservation_Rooms)rooms_reservationBlocked.get(i);
					if(beanReservation_Rooms.getId_reservation().getDate_blocked().equals(blocked.getTime()) || beanReservation_Rooms.getId_reservation().getDate_blocked().before(blocked.getTime()))
					{
						BeanType_Status_Room type_status = (BeanType_Status_Room)session.get(BeanType_Status_Room.class,1);
						beanReservation_Rooms = (BeanReservation_Rooms)session.get(BeanReservation_Rooms.class,beanReservation_Rooms.getId());
						BeanReservation beanReservation = (BeanReservation)session.get(BeanReservation.class,beanReservation_Rooms.getId_reservation().getId());
						
						tx = session.beginTransaction();
						BeanRoom beanRoom = (BeanRoom)session.get(BeanRoom.class,beanReservation_Rooms.getId_room().getId());
						beanRoom.setId_type_status_room(type_status);
						
						query = " FROM BeanReservation_Person rp"+
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
					}
						
				}
		    }
		    
		    String rooms2Change = " FROM BeanReservation_Rooms rr"+
            					  " ORDER BY rr.id_reservation.id_type_status_reservation.status";   //

		    List rooms_reservation = session.createQuery(rooms2Change).list();
		    if(!rooms_reservation.isEmpty())
		    {
		    	Calendar date_actual = Calendar.getInstance();
		    	//date_actual.add(Calendar.MONTH, 1);
		    	Date date = date_actual.getTime();
		    	for (int i = 0; i < rooms_reservation.size(); i++) 
		    	{
		    		
		    		BeanReservation_Rooms beanReservation_Rooms = (BeanReservation_Rooms)rooms_reservation.get(i);
					if(beanReservation_Rooms.getId_reservation().getId_type_status_reservation().getId()== 3)
					{
						if (beanReservation_Rooms.getId_reservation().getDate_begin().after(date))
						{
						   continue;
						}
						else
						{
							rooms_reservation.remove(i);
						}
					}
					else
					if (beanReservation_Rooms.getId_reservation().getDate_blocked().after(date))
					{
					   continue;
					}
					else
					{
						rooms_reservation.remove(i);
					}
				}
		    	httpSession.setAttribute("roomsReservation2Change",rooms_reservation);
				return 1;
			}
			
			else
		    {
				return 0;
		    }
			
	    	
		  }
		catch( Throwable  m )
		  {
			httpSession.setAttribute("module","pre");
			httpSession.setAttribute("error", new String("Ocurrio un error: "+ m.getMessage()));
			m.printStackTrace();
			return -1;
		  }
	  }
}
