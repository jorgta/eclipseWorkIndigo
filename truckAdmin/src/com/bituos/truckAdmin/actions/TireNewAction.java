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

public class TireNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		TireNewForm tireNewForm = (TireNewForm) form;

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
			
				if(tireNewForm.getProcess().equals("add"))
				 {
				
					String query =   " FROM BeanTire u" 
								   + " WHERE u.serial_number  = '" + tireNewForm.getSerial_number().toUpperCase() + "'";  
					
					java.util.List list = session.createQuery(query).list();
					
					if ( list.isEmpty() )
					  {   
						BeanTire bean = new BeanTire();
					
						BeanTypeTireStatus beanTypeTireStatus = (BeanTypeTireStatus) session.load( BeanTypeTireStatus.class, new Integer( tireNewForm.getId_type_tire_status() ) );
					    BeanTypeMeasure beanTypeMeasure = (BeanTypeMeasure) session.load( BeanTypeMeasure.class, new Integer( tireNewForm.getId_type_measure() ) );
						BeanTruck beanTruck = (BeanTruck) session.load( BeanTruck.class, new Integer( 1 ) );
						
						BeanTypeUnitOfMeasure beanUnitOfMeasure =  (BeanTypeUnitOfMeasure) session.load( BeanTypeUnitOfMeasure.class, new Integer(tireNewForm.getId_type_unit_of_measure() ) );
						
						tx = session.beginTransaction();
						
						bean.setSerial_number(tireNewForm.getSerial_number());
						//bean.setId_truck(beanTruck);
						bean.setId_type_tire_status(beanTypeTireStatus);
						
						BeanTypePlace beanTypePlace;
					//	if( tireNewForm.getId_type_tire_status().equals("2"))
						  beanTypePlace = (BeanTypePlace) session.load( BeanTypePlace.class, new Integer( 1 ) );
						/*else
						  beanTypePlace = (BeanTypePlace) session.load( BeanTypePlace.class, new Integer( 7 ) );*/	
						
						
						
						bean.setId_type_place(beanTypePlace);
						bean.setId_type_measure(beanTypeMeasure);
						int km_int =new Integer(tireNewForm.getKm_int());
						bean.setKm_int(km_int);
						int deep =new Integer(tireNewForm.getDeep());
						bean.setDeep(deep);
						bean.setDesign(tireNewForm.getDesign());
						bean.setActive("Y");
						bean.setPosition(0);
						bean.setId_type_unit_of_measure(beanUnitOfMeasure);
						
						
					
						session.save( bean );
						tx.commit();
						
						forward = mapping.findForward("ok");
						httpSession.setAttribute("done", new String("./pre.jsp?&process='tireNew'&action='pre.do'&target=''"));
					
					}
					else
					  {
					  	httpSession.setAttribute("done", "/pre.jsp?&process='tireNew'&action='pre.do'&target=''" );
						com.bituos.Error error = new com.bituos.Error("Este neumático ya existe.", request);
						forward = mapping.findForward("error");
					  }
				 } 
				else if(tireNewForm.getProcess().equals("select"))
				 {
					
					
					BeanTruck bean = (BeanTruck) session.load( BeanTruck.class, new Integer( tireNewForm.getId_truck() ) );
					httpSession.removeAttribute("selectedTruck");
					httpSession.setAttribute("selectedTruck", bean );
					
					
					
					
					String cttString = bean.getRegistration();
					httpSession.setAttribute("cttString", cttString );
					
					
			        String ctsString = tireNewForm.getId_type_tire_status();
			        String ctpString = tireNewForm.getId_place();
			        String ctmString = tireNewForm.getId_type_measure();
			        
			        httpSession.setAttribute("ctsString", ctsString );
			        httpSession.setAttribute("ctpString", ctpString );
			        httpSession.setAttribute("ctmString", ctmString );
			        
			        
			        String query =   " FROM BeanTruckTireConfigurationDetail u" 
						           + " WHERE u.id_truck_tire_configuration  = '" + bean.getId_truck_configuration().getId() + "'" 
						           + " ORDER BY is_front_axis DESC";
			
			        List list = session.createQuery(query).list();
			        
			        if(!list.isEmpty())
			        	httpSession.setAttribute("truckTireDetailList", list );
			        else
			        {
			        	list = new ArrayList<BeanTruckTireConfigurationDetail>();
			        	httpSession.setAttribute("truckTireDetailList", list );
			        }
			     
					
					forward = mapping.findForward("loop");
					
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
