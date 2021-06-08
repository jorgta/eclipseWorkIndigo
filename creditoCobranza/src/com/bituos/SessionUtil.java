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
				httpSession.removeAttribute("listUserActivate");
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
	
	
	public int prepareProcessAdmin( HttpServletRequest req, BeanUser beanUser ,Session    session )
	  { 
		HttpSession httpSession = req.getSession();
		
		try
		  {
			

			 

			httpSession.removeAttribute("done");
		    httpSession.removeAttribute("error");
		    httpSession.removeAttribute("information");
		    httpSession.removeAttribute("attention");
		    httpSession.setAttribute("module","pre");
		    httpSession.removeAttribute("listUsers2Change");
		    httpSession.removeAttribute("beanUser2Change");
		    httpSession.removeAttribute("listProcesses2Change");
		    httpSession.removeAttribute("listProcess2Change");
		    httpSession.removeAttribute("currentBeanUserROOT");
		    
		    
		    String userRoot ="AND u.name <> 'ROOT'";
			if(beanUser.getName().equals("ROOT"))
				userRoot="";
			 
				
			String query =  " FROM BeanUser u" +
							" WHERE u.id_company in (" + beanUser.getId_company().getId() + ") " +
							userRoot;
				

				
			List list = session.createQuery(query).list();
			
			if (!list.isEmpty()) 
			  {  
				 httpSession.setAttribute("listUsers2Change",list);
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
	
	

	


}
