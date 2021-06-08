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

public class EventNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		EventNewForm eventNewForm = (EventNewForm) form;

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
			
				if (eventNewForm.getProcess().equals("select"))
				  {
				
					 BeanTire bean = (BeanTire) session.load( BeanTire.class, new Integer( eventNewForm.getId_tire() ) );
					 httpSession.setAttribute("currentBeanTire", bean );
					 
					 String ctTireString = String.valueOf( bean.getSerial_number());
					 httpSession.setAttribute("ctTireString", ctTireString );
					 
					 String ctpString=eventNewForm.getId_type_place();
					 httpSession.setAttribute("ctpString", ctpString );
					 
					 String cteString= eventNewForm.getId_type_event();
					 httpSession.setAttribute("cteString", cteString );
					
					 String query =   " FROM BeanTire u" 
								    + " WHERE u.active= 'Y'";
					 List list = session.createQuery(query).list();					
					
	
					 list = session.createQuery( query ).list();
					  if(!list.isEmpty())
					    {	
						 							 
					      httpSession.setAttribute("listTireForEvent", list );
					      	
					    }
					  else
					    {
						  list = new ArrayList<BeanTire>();
						
					    }
					 
					 forward = mapping.findForward("loop");
				
				  }
				else if (eventNewForm.getProcess().equals("add"))
				  {
					BeanEvent bean = new BeanEvent();
					BeanTire beanTire = (BeanTire) httpSession.getAttribute( "currentBeanTire" );
					BeanTypePlace beanTypePlace = (BeanTypePlace) session.load( BeanTypePlace.class, new Integer( eventNewForm.getId_type_place() ) );
					 
					BeanTypeEvent beanTypeEvent = (BeanTypeEvent) session.load( BeanTypeEvent.class, new Integer( eventNewForm.getId_type_event() ) );
					
					 String ctpString=eventNewForm.getId_type_place();
					 httpSession.setAttribute("ctpString", ctpString );
					 
					 String cteString= eventNewForm.getId_type_event();
					 httpSession.setAttribute("cteString", cteString );
					
					if(beanTire != null)
					{
			
						tx = session.beginTransaction();
						bean.setId_tire(beanTire);
						bean.setId_type_place(beanTypePlace);
						bean.setDeep(new Integer( eventNewForm.getDeep()));
						bean.setDesign(eventNewForm.getDesign());
						bean.setOdometer(eventNewForm.getOdometer());
						bean.setComments(eventNewForm.getComments());
						Date ahora = new Date();								
						bean.setDate_reg(ahora);
						bean.setId_type_event(beanTypeEvent);
					
						session.save( bean );
						tx.commit();
						
						forward = mapping.findForward("ok");
						httpSession.setAttribute("done", new String("./pre.jsp?&process='eventNew'&action='pre.do'&target=''"));
		
					}
					else
					{
						com.bituos.Error e = new com.bituos.Error("No selecciono ningun neumático.", request);
						httpSession.setAttribute("done", "./pre.jsp?&process='eventNew'&action='pre.do'&target=''" );

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
