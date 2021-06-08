package com.bituos.truckAdmin.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bituos.truckAdmin.Beans.*;
import com.bituos.truckAdmin.forms.*;

/**
 * @version 	1.0
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 */

import java.util.*;

import com.bituos.truckAdmin.*;
import com.bituos.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import javax.servlet.http.*;

public class TruckSelectAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		TruckSelectForm truckSelectForm = (TruckSelectForm) form;

		HttpSession httpSession = request.getSession();
							
		Config config = new Config( request );
		
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
		  forward = mapping.findForward("login");
		else 
			{
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				    session = sessionFactory.openSession();
					if(truckSelectForm.getProcess().equals("edit"))
					  {				    
	                
					
					    BeanTruck bean = (BeanTruck) session.load( BeanTruck.class, new Integer( truckSelectForm.getId_truck() ) );
					
					    httpSession.setAttribute("currentVehicle",bean);

					
					    forward = mapping.findForward("edit");
					  }
					 else if(truckSelectForm.getProcess().equals("delete"))
					  {
									
						 String query ="";
						 List list = null;
						 BeanTruck bean = (BeanTruck) session.load( BeanTruck.class, new Integer( truckSelectForm.getId_truck() ) );
					     tx = session.beginTransaction();
					     
						 
						 
						 query = "  FROM BeanTireTruck u" +
		                         "  WHERE u.id_truck = " + bean.getId()  +
		                         "  AND u.active ='Y'" ;
						 
						 list = session.createQuery( query ).list();
						 
						 if(!list.isEmpty())
						 {
							 
							 query = "  FROM BeanTire u" +
						      		 "  WHERE u.active = 'Y'" +
						      		 "  u.id =" + ((BeanTire)list.get(0)).getId();
					  
					  
					  
						  	list = session.createQuery( query ).list();
						  	httpSession.setAttribute("beanTireList", list );
					  
							com.bituos.Error e = new com.bituos.Error("El Vehículo tiene Neumáticos asignados.", request);							
							httpSession.setAttribute("done", "./pre.jsp?&process='tireMovements'&action='pre.do'&target=''" );
							forward = mapping.findForward("error"); 
						 }
						 else
						 {
							 
							 
							 bean.setActive("N");
							 session.update(bean);
							 tx.commit();
							 
							 query = " FROM BeanTruck u" +
	  				 		         " WHERE u.active='Y'";

							 list = session.createQuery( query ).list();
							 if(!list.isEmpty())
							  {			  
							    httpSession.setAttribute("listTruckEditOrDelete", list );
							    forward = mapping.findForward("selectVehicle");
							  }
							 else
							  {
								list = new ArrayList<BeanTruck>();
								httpSession.setAttribute("listTruckEditOrDelete", list );
							  }
					  
					  

							httpSession.setAttribute("done", new String("./pre.jsp?&process='selectVehicle'&action='pre.do'&target=''"));
							forward = mapping.findForward("ok");		
						 }
							 
						 
						 
						 
						 
						 				 
					  }
					
					
				  }
				catch( Throwable  m)
				  {
					m.printStackTrace();
					
					com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
					
					forward = mapping.findForward("error");
				  }
				finally
				  {
					session.close();
					sessionFactory.close();
				  }
			}

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
		// Write logic determining how the user should be forwarded.

		// Finish with
		return (forward);

	}
}
