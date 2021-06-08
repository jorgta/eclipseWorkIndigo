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

public class TruckNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		TruckNewForm truckNewForm = (TruckNewForm) form;

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
			
				if(truckNewForm.getProcess().endsWith("save"))
				{
					String query =   " FROM BeanTruck u" 
								   + " WHERE u.registration  = '" + truckNewForm.getRegistration().toUpperCase() + "'";  
					
					java.util.List list = session.createQuery(query).list();
					
					if ( list.isEmpty() )
					  {   
						BeanTruck bean = new BeanTruck();
					  	
						tx = session.beginTransaction();
						bean.setDescription(truckNewForm.getDescription());
						bean.setModel(truckNewForm.getModel());
						bean.setRegistration(truckNewForm.getRegistration());	
						Date ahora = new Date();								
						bean.setDate_reg(ahora);
						bean.setActive("Y");
						BeanTruckTireConfiguration beanTruckTireConfiguration=(BeanTruckTireConfiguration)httpSession.getAttribute("beanTruckTireConfiguration");
						bean.setId_truck_configuration(beanTruckTireConfiguration);
					
						session.save( bean );
						tx.commit();
						
						forward = mapping.findForward("ok");
						httpSession.setAttribute("done", new String("./pre.jsp?&process='truckNew'&action='pre.do'&target=''"));
					  }
					else
					  {
					  	httpSession.setAttribute("done", "truck/truckNew.jsp" );
						com.bituos.Error error = new com.bituos.Error("Ese vehículo ya existe, debe seleccionar otro.", request);
						forward = mapping.findForward("error");
					  }
				}else if(truckNewForm.getProcess().equals("select"))
				{
								
					BeanTruckTireConfiguration beanTruckTireConfiguration = (BeanTruckTireConfiguration) session.load( BeanTruckTireConfiguration.class, new Integer( truckNewForm.getId_truck_tire_configuration() ) );
					
					if ( beanTruckTireConfiguration !=null )
					  {   
						httpSession.removeAttribute("beanTruckTireConfiguration");
						httpSession.setAttribute("beanTruckTireConfiguration", beanTruckTireConfiguration);						
						forward = mapping.findForward("loop");
						//httpSession.setAttribute("done", new String("truck/truckNew.jsp"));
					  }
					else
					  {
					  	httpSession.setAttribute("done", "truck/truckNew.jsp" );
						com.bituos.Error error = new com.bituos.Error("Esa configuración  ya no existe, debe seleccionar otro.", request);
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
