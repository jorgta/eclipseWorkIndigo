package com.bituos.truckAdmin.actions;

import com.bituos.truckAdmin.Beans.*;
import com.bituos.truckAdmin.forms.*;

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
import com.bituos.truckAdmin.*;
import com.bituos.*;
import java.util.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

public class TruckTireConfigurationDeleteOrEditAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		TruckTireConfigurationDeleteOrEditForm truckTireConfigurationDeleteOrEditForm = (TruckTireConfigurationDeleteOrEditForm) form;

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

			try {
				sessionFactory = config.getConfiguration(request).buildSessionFactory();
				session = sessionFactory.openSession();
			
				if(truckTireConfigurationDeleteOrEditForm.getProcess().equals("add"))
				{
					String query =   " FROM BeanTruckTireConfiguration u" 
								   + " WHERE u.description  = '" + truckTireConfigurationDeleteOrEditForm.getDescription() + "'";  
					
					java.util.List list = session.createQuery(query).list();
					
					if ( list.isEmpty() )
					  {   
						BeanTruckTireConfiguration bean = new BeanTruckTireConfiguration();
					  	
						tx = session.beginTransaction();
						bean.setDescription(truckTireConfigurationDeleteOrEditForm.getDescription());
					/*	bean.setModel(truckTireConfigurationNewForm.getModel());
						bean.setRegistration(truckTireConfigurationNewForm.getRegistration());	
						Date ahora = new Date();								
						bean.setDate_reg(ahora);*/
						
					
						session.save( bean );
						tx.commit();
						
						forward = mapping.findForward("ok");
						httpSession.setAttribute("done", new String("truck/truckNew.jsp"));
					  }
					else
					  {
					  	httpSession.setAttribute("done", "truck/truckNew.jsp" );
						com.bituos.Error error = new com.bituos.Error("Ese vehículo ya existe, debe seleccionar otro.", request);
						forward = mapping.findForward("error");
					  }
				}
				else if(truckTireConfigurationDeleteOrEditForm.getProcess().equals("edit"))
				{
					String query =   " FROM BeanTruckTireConfigurationDetail u" 
						   		   + " WHERE u.id_truck_tire_configuration.id  = '" + truckTireConfigurationDeleteOrEditForm.getId_truck_tire_configuration() + "'";  
			
					 java.util.List list = session.createQuery(query).list();
					
					if ( !list.isEmpty() )
					  {   									
					  
						  
						httpSession.setAttribute("truckTireConfigurationDetailList", list);
						
						query = " FROM BeanTireQuantityAxis";
						list = session.createQuery( query ).list();
					    httpSession.setAttribute("listTireQuantityAxis", list );
						
					    httpSession.setAttribute("save", false);
						
						forward = mapping.findForward("edit");
					  }
					else
					  {
					  	httpSession.setAttribute("done", "truck/truckNew.jsp" );
						com.bituos.Error error = new com.bituos.Error("Error interno consulte a soporte tecnico", request);
						forward = mapping.findForward("error");
					  }
					
					
					
				}
			  else if(truckTireConfigurationDeleteOrEditForm.getProcess().equals("delete"))
				{
					
				  
				  String query ="";
				  
				  query =   " FROM BeanTruck u" +
					  	    " WHERE u.id_truck_configuration.id ="  + truckTireConfigurationDeleteOrEditForm.getId_truck_tire_configuration() +
					  	  	" AND u.active ='Y'";
				
				  java.util.List truckList = session.createQuery(query).list();
				
				  if(truckList.isEmpty())
				  {
				  
				  
				    query =   " FROM BeanTruckTireConfigurationDetail u" 
						    + " WHERE u.id_truck_tire_configuration.id ="  + truckTireConfigurationDeleteOrEditForm.getId_truck_tire_configuration() ; 
					
					List truckTireConfigurationDetailList = session.createQuery(query).list();
				      
				   
					
					
					if ( !truckTireConfigurationDetailList.isEmpty() )
					  {   
						BeanTruckTireConfiguration beanTruckTireConfiguration = ((BeanTruckTireConfigurationDetail)truckTireConfigurationDetailList.get(0)).getId_truck_tire_configuration();
					  
					    tx = session.beginTransaction();	
					    
					    Iterator iterDelete = truckTireConfigurationDetailList.iterator();
					    while ( iterDelete.hasNext() )
						{				        
					      session.delete((BeanTruckTireConfigurationDetail) iterDelete.next());
						}
					    
					    session.delete(beanTruckTireConfiguration);
					    
					    tx.commit();
							
						forward = mapping.findForward("ok");
						httpSession.setAttribute("done", new String("pre.jsp?&process='controlPanel'&action='pre.do'&target=''"));
					  }
					else
					  {
						httpSession.setAttribute("done", new String("pre.jsp?&process='controlPanel'&action='pre.do'&target=''") );
						com.bituos.Error error = new com.bituos.Error("Error interno consulte a soporte tecnico.", request);
						forward = mapping.findForward("error");
					  }
				  }
				  else
				  {
					  httpSession.setAttribute("done", new String("pre.jsp?&process='controlPanel'&action='pre.do'&target=''") );
					  com.bituos.Error error = new com.bituos.Error("La Configuracion no puede ser eliminada si ya ha sido asignada a un vehículo.", request);
					  forward = mapping.findForward("error");
				  }
				}
					
				
				// do something here

			} catch (Exception e) {

                e.printStackTrace();
                
				// Report the error using the appropriate name and ID.
				com.bituos.Error error = new com.bituos.Error( e.getMessage(), request);
				errors.add("name", new ActionError("id"));

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
