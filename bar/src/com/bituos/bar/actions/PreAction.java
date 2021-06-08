package com.bituos.bar.actions;

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

import com.bituos.bar.Beans.*;
import com.bituos.bar.forms.*;

import java.util.*;

import com.bituos.bar.*;
import com.bituos.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import javax.servlet.http.*;

// href=<%="'"%><bean:write name="id" property="id_process.url" /><%="'"%> >
//<html:submit /> <bean:write name="id" property="id_process.description" /> </html:submit>


public class PreAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value

		PreForm preForm = (PreForm) form;

		HttpSession httpSession = request.getSession();
							
		Config config = new Config( request );
		
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		httpSession.removeAttribute("done" );

		//BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");
		String process = preForm.getProcess();
	    
/*		if ( beanUser == null )
		  forward = mapping.findForward("login");
		else */ if ( process.equals("'userNew'") )  //new user
			{
		      forward = mapping.findForward("userNew");
			}
		else if ( process.equals("'userDelete'") ) //delete user
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();
	
					/*String query =  " FROM BeanUser u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active = 'Y'" +
									" AND u.id <> " + beanUser.getId() + 
					  				" ORDER BY u.name";
								
					List list = session.createQuery( query ).list();*/
	
					/*if ( list.size() != 0 )
						{
						  httpSession.setAttribute("listUserDelete", list );
	
						  forward = mapping.findForward("userDelete");
						}
					else
						{
						 /* com.bituos.Error e = new com.bituos.Error("No existen usuarios para borrar", request);
						  httpSession.setAttribute("done", "main.jsp" );
	
						  forward = mapping.findForward("error");
						}*/
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
		else if ( process.equals("'loadAgenteList'") ) // cargar meseros en session
		  {
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				String query =  " FROM BeanAgente u" +
								" WHERE u.eliminado = 'N'" +
				  				" ORDER BY u.nombre";
							
				List list = session.createQuery( query ).list();
				
				httpSession.setAttribute("agenteList", list );
				
		

			    forward = mapping.findForward("loadAgenteList");
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
		else if ( process.equals("'sponsorNew'") )  //new company
			{
			  forward = mapping.findForward("sponsorNew");
			}
		else if ( process.equals("'sponsorDelete'") )  //new company
		{
		  
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				String query =  " FROM BeanSponsor u" +
								" WHERE u.active = 'Y'" +								
				  				" ORDER BY u.name";
							
				List list = session.createQuery( query ).list();

				if ( list.size() != 0 )
					{
					  httpSession.setAttribute("listSponsorDelete", list );

					  forward = mapping.findForward("sponsorDelete");
					}
				else
					{
					  com.bituos.Error e = new com.bituos.Error("No existen Patrocinadores para borrar", request);
					  httpSession.setAttribute("done", "main.jsp" );

					  forward = mapping.findForward("error");
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
	else if ( process.equals("'rptCampaign'") ) //
    {
		
		try
		  {
			sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			session = sessionFactory.openSession();

			String query =  " FROM BeanCampaign u" +							
			  				" ORDER BY u.date_reg";
						
			List list = session.createQuery( query ).list();

			if ( list.size() != 0 )
				{
				  httpSession.setAttribute("listCampaign", list );				  
				  httpSession.setAttribute("id_campaign", "0" );

				  forward = mapping.findForward("rptCampaign");
				}
			else
				{
				  com.bituos.Error e = new com.bituos.Error("No existen campañas creadas", request);
				  httpSession.setAttribute("done", "main.jsp" );

				  forward = mapping.findForward("error");
				}
			
			
		  }
		catch( Throwable  m)
		  {
			m.printStackTrace();
			
			com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
			
			forward = mapping.findForward("error");
		  }
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
