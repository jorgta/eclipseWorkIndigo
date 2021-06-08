package com.emptyProject.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.emptyProject.Beans.*;
import com.emptyProject.forms.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * @version 	1.0
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 */


import javax.servlet.http.*;
import com.emptyProject.*;

import com.emptyProject.Beans.BeanCountry;
import com.emptyProject.Beans.BeanStates;
import com.emptyProject.Beans.BeanUser;

import com.bituos.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

public class UserNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		UserNewForm userNewForm = (UserNewForm) form;

		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("done"); 
									
		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
		  forward = mapping.findForward("login");
		else
		  {
			Config config = new Config( request );
		
			SessionFactory sessionFactory = null;
			Session    session = null;
			Transaction tx = null;
			String notification;
			try {
				 sessionFactory = config.getConfiguration(request).buildSessionFactory();
				 session = sessionFactory.openSession();
				 
				 String query;
				 java.util.List list;
				 int beanUserCounter=0;
				 Iterator iter = null;				 
				 BeanUser beanUserNew = null;
				 
				if (userNewForm.getProcess().equals("addUser"))
				  {
				
					query =   " FROM BeanUser u" 
						    + " WHERE u.name = '" + userNewForm.getName().toUpperCase() + "'";  
					
					list = session.createQuery(query).list();
					
					if ( list.isEmpty() )
					  {   
					  	BeanUser bean = new BeanUser();
					  	
						tx = session.beginTransaction();
						//bean.setId_company( beanUser.getId_company() );
						bean.setName( userNewForm.getName().toUpperCase() );
						bean.setPassword( userNewForm.getPassword() );
						bean.setRfc( userNewForm.getRfc() );
						bean.setAddress( userNewForm.getAddress() );
						bean.setEmail( userNewForm.getEmail() );
						bean.setActive( "Y" );
					
						session.save( bean );
						tx.commit();
						
								
						notification= "success";
						httpSession.setAttribute("notification", notification );
						com.bituos.Notification e = new com.bituos.Notification("Datos Guadados exitosamente", request);
						forward = mapping.findForward("userNew");
					  }
					else
					  {
					
						notification= "error";
						httpSession.setAttribute("notification", notification );
						com.bituos.Notification e = new com.bituos.Notification("Ese usuario ya existe, debe seleccionar otro.", request);
						forward = mapping.findForward("userNew");
					  }
				  }
				else if (userNewForm.getProcess().equals("pagination"))
				  {
								 
					 
					if ( Integer.valueOf(userNewForm.getPagina()) > 0 && 
						(Integer.valueOf(userNewForm.getPagina()) <= Integer.valueOf((String)httpSession.getAttribute("pageLast"))) )
					  {
						int rowsPerPage = 10;  //manejar modulo de configuracion
						
						int limitMax = rowsPerPage;
						int limitMin = rowsPerPage * (Integer.valueOf(userNewForm.getPagina()) - 1);
						
						query =   " FROM BeanUser u" +
								  " WHERE u.id <> " + beanUser.getId() + 
		  				          " ORDER BY id ASC LIMIT "+limitMin+", "+ limitMax+ ";";
	
						list = session.createQuery( query ).list();
						
						httpSession.setAttribute("listUser", list );	
						
						String pageCurrent =userNewForm.getPagina();
						httpSession.setAttribute("pageCurrent", pageCurrent );
						
						String currentTab= "#tab" + userNewForm.getTab();
						
						httpSession.setAttribute("defaultTab", currentTab  );
						  
						httpSession.setAttribute("foundErrors", true ); //false
						
						notification= "success";
						httpSession.setAttribute("notification", notification );
						com.bituos.Notification e = new com.bituos.Notification("Datos procesados exitosamente", request);
						forward = mapping.findForward("userNew");
					  }
					else
					{
						notification= "information";
						httpSession.setAttribute("notification", notification );
						com.bituos.Notification e = new com.bituos.Notification("Ya no hay más datos para mostrar", request);
						forward = mapping.findForward("userNew");
						
					}
					
				  }
				else if (userNewForm.getProcess().equals("addUserToList"))
				  {
					
					
					
					  list = (List) httpSession.getAttribute("listUserTemp");
					  httpSession.removeAttribute("listUserTemp");
					  //java.util.List newList = new ArrayList();
					  beanUserCounter =1;	
					  
					  if (!list.isEmpty())
					  {				   	  	
						iter = list.iterator();
						//Reordenar lista
					    while( iter.hasNext() )
					      {		
					    	beanUserNew = new BeanUser();
					    	beanUserNew = (BeanUser) iter.next();
					    	beanUserNew.setId(beanUserCounter);						    
						    beanUserCounter++;
					      } 
					  }
					  
					  beanUserNew = new BeanUser();
					  beanUserNew.setName( userNewForm.getName().toUpperCase() );
					  beanUserNew.setFull_name(userNewForm.getFull_name().toUpperCase());
					  beanUserNew.setPassword( userNewForm.getPassword() );
					  beanUserNew.setRfc( userNewForm.getRfc() );
					  beanUserNew.setAddress( userNewForm.getAddress() );
					  beanUserNew.setEmail( userNewForm.getEmail() );
					  beanUserNew.setActive( "Y" );
	
			
					  
					  beanUserNew.setId(beanUserCounter);			
					  list.add(beanUserNew);  					  
	
					  httpSession.setAttribute("listUserTemp", list );
					
					
					
					
					  String currentTab= "#tab" + userNewForm.getTab();					
					  httpSession.setAttribute("defaultTab", currentTab  );	//manejar desde modulo de configuracion
					
					

					  httpSession.removeAttribute("notification");
					  notification= "success";
					  httpSession.setAttribute("notification", notification );
					  com.bituos.Notification e = new com.bituos.Notification("Usuario Agregado exitosamente a la lista", request);
					  forward = mapping.findForward("userNew");
				  }
				else if(userNewForm.getProcess().equals("removeUserFromList"))
			      {
			     
				    list = (List) httpSession.getAttribute("listUserTemp");				  
				
				    iter = list.iterator();
				  
				 
				    while( iter.hasNext() )
				      {
					    beanUserNew = (BeanUser) iter.next(); 
					    Integer id = new Integer( userNewForm.getId());
				        if ( beanUserNew.getId() == id )
				          {				            
				            list.remove(beanUserNew);
							httpSession.setAttribute("listUserTemp", list );
				            break;
				          }
				      } 
				  
				    String currentTab= "#tab" + userNewForm.getTab();					
					httpSession.setAttribute("defaultTab", currentTab  );
					  
				    httpSession.removeAttribute("notification");
				    notification= "success";
				    httpSession.setAttribute("notification", notification );
				    com.bituos.Notification e = new com.bituos.Notification("Usuario Removido exitosamente de la lista", request);
				 
				  
				    forward = mapping.findForward("userNew");
			    }
			  else if(userNewForm.getProcess().equals("changeRowToShow"))
			    {

				  query =   " FROM BeanUser u" +
							" WHERE u.id <> " + beanUser.getId() + 
			  				" ORDER BY u.name";
				
				  list = session.createQuery( query ).list();

				 
				  if ( !list.isEmpty() )				   
					{
					   query =   "SELECT COUNT(*) FROM BeanUser u";
					   list = session.createQuery( query ).list();
					   Integer count = (Integer)list.get(0); 
					   
					   int rowsPerPage = Integer.valueOf(userNewForm.getRowtoshow());  //manejar modulo de configuracion
					   int pagesTemp = (int)count/(int)rowsPerPage;
					   float pages = (float)count/(float)rowsPerPage;
				
					   if (pages > 1)
					     {
					       pages = pages - pagesTemp;
					       if (pages > (float)0.1)
						     pagesTemp = pagesTemp + 1;
					       
					     }
					   else
						   pagesTemp = 1;  
				
					   
					  List pagesList = new ArrayList<Integer>();
					  
					  for(int i = 1; i <= pagesTemp; i++)
						  pagesList.add(i);
					  
					  
					  String pageCurrent ="1";
					  httpSession.setAttribute("pageCurrent", pageCurrent );
					  httpSession.setAttribute("pagesList", pagesList );			  
					  
				
					  httpSession.setAttribute("pageLast", String.valueOf(pagesTemp) );
					  
					  query =   " FROM BeanUser u" +
								" WHERE u.id <> " + beanUser.getId() + 
				 				" ORDER BY id ASC LIMIT 0, "+ rowsPerPage+ ";";
				
					  list = session.createQuery( query ).list();
					  
					  
					  httpSession.setAttribute("listUser", list );	
					  
					  httpSession.setAttribute("rowsPerPage", String.valueOf( rowsPerPage ) );	
					  
				      list = new ArrayList<BeanUser>();
				      httpSession.setAttribute("listUserTemp", list );	
				     
				      httpSession.setAttribute("defaultTab", "#tab1"  );	//manejar modulo de configuracion
				     
				      httpSession.setAttribute("foundErrors", true ); //false
					  
					  notification= "success";
					  httpSession.setAttribute("notification", notification );
					  com.bituos.Notification e = new com.bituos.Notification("Consulta Procesada exitosamente", request);
					}
				  else
					{
					  notification= "information";
					  httpSession.setAttribute("notification", notification );
					  com.bituos.Notification e = new com.bituos.Notification("No existen usuarios creados", request);				  
					}

				
				
				  forward = mapping.findForward("userNew");
			    }
				
				
				
			
			} catch (Exception e) {

                e.printStackTrace();
                
				
				notification= "error";
				httpSession.setAttribute("notification", notification );
				com.bituos.Notification notifi = new com.bituos.Notification("Error interno (" + e.getMessage() + ")", request);
				forward = mapping.findForward("userNew");

			}
			finally	{
				if ( session != null )
					 session.close(); 
				  
				if ( sessionFactory != null )
					 sessionFactory.close();
			}

		  }          
 


		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}

		// Finish with
		return (forward);

	}
}
