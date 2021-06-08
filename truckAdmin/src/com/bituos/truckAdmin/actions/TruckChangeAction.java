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

public class TruckChangeAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		TruckChangeForm truckChangeForm = (TruckChangeForm) form;

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
	                
					if(truckChangeForm.getProcess().equals("save"))
					{
					
						BeanTruck bean = (BeanTruck) session.load( BeanTruck.class, new Integer( truckChangeForm.getId() ) );
		                
						bean.setModel( truckChangeForm.getModel());
						bean.setRegistration(truckChangeForm.getRegistration());
						bean.setDescription( truckChangeForm.getDescription() );
						
						BeanTruckTireConfiguration beanTruckTireConfiguration = (BeanTruckTireConfiguration) httpSession.getAttribute("beanTruckTireConfiguration");	
						bean.setId_truck_configuration(beanTruckTireConfiguration);
						//bean.setDate_reg( truckChangeForm.getDate_reg() );
						
						tx = session.beginTransaction();
						session.update( bean );
						tx.commit();
	
						 httpSession.setAttribute("done", new String("./pre.jsp?&process='selectVehicle'&action='pre.do'&target=''"));
							
						forward = mapping.findForward("ok");
					}else if(truckChangeForm.getProcess().equals("select"))
					{
						
						BeanTruckTireConfiguration beanTruckTireConfiguration = (BeanTruckTireConfiguration) session.load( BeanTruckTireConfiguration.class, new Integer( truckChangeForm.getId_truck_tire_configuration() ) );
					
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
