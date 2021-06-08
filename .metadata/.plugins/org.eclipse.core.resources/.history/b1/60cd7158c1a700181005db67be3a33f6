package com.struts2.actions;

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

import java.util.*;

import com.eventAdmin.*;
import com.struts2.Beans.*;
import com.struts2.forms.*;
import com.bituos.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import javax.servlet.http.*;

public class PreWVAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		PreWVForm preWVForm = (PreWVForm) form;

		HttpSession httpSession = request.getSession();
							
		Config config = new Config( request );
		
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		httpSession.removeAttribute("done" );
		httpSession.removeAttribute("list" );

		if ( preWVForm.getProcess().equals("rpt.ourClients") ) //rpt Our clients
		  {
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				String query =  " FROM BeanCompany u" +
								" WHERE u.active='Y'" +
								" ORDER BY u.name";
	
				List list = session.createQuery( query ).list();

				httpSession.setAttribute("list", list );

				forward = mapping.findForward("rpt.ourClients");
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
	    else if ( preWVForm.getProcess().equals("rpt.cardCredit") ) //rpt card credit
			{
			  try
				{
				  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				  session = sessionFactory.openSession();

				  String query =  " FROM BeanCard u" +
								  " WHERE u.id='" + preWVForm.getId_card() + "'";
	
				  List list = session.createQuery( query ).list();

				  if ( list.size() > 0 )
				    {
						/*BeanCard beanCard = (BeanCard) session.load( BeanCard.class, preWVForm.getId_card() );
						boolean mustCheck = false;
						boolean error = false;
						
						if ( beanCard.getIs_secure().equals("Y") )
							if ( !beanCard.getNip().equals(preWVForm.getNip() ) )
							  {
								error = true;
							  }
	
						if ( error == false )
						  httpSession.setAttribute("list", list );*/
					}
				  
				  forward = mapping.findForward("rpt.cardCredit");
				  
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
		else
		  {
			com.bituos.Error e = new com.bituos.Error("Opcion no programada. Consulte a soporte tecnico.", request);
		
			forward = mapping.findForward("error");
        
		  }

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
		// Write logic determining how the user should be forwarded.

		// Finish with
		return (forward);

	}
}
