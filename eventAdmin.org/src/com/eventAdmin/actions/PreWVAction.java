package com.eventAdmin.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eventAdmin.Beans.*;
import com.eventAdmin.forms.*;

/**
 * @version 	1.0
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 */

import java.util.*;

import com.eventAdmin.*;
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

		if ( preWVForm.getProcess().equals("showQuote") ) //
		  {
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
				boolean error = false;
				Aes aes = new Aes();
				String decryptedID = null;
				int id = -1;
				
				try
				  {
					decryptedID = aes.decrypt(preWVForm.getNip() );
					id = new Integer( decryptedID ).intValue();
				  }
				catch(Throwable  m)
				  {
					id = -2;
				  }
				
				if ( id == -2 )
				  error = true;
				
				if ( !error )
				  {
					String query =  " FROM BeanQuote u" +
									" WHERE u.id=" + id; 

					List list = session.createQuery( query ).list();
				
					if ( list.size() != 0 )
						{
							httpSession.setAttribute( "crystal_rptname", new String("rptQuote.rpt") ) ;
							httpSession.setAttribute( "parameterCount", new Integer( 1 ) ) ;
												
							httpSession.setAttribute( "parameter1", new Integer( id ) ) ;
							httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
							httpSession.setAttribute( "parameterName1", new String("id_quote") ) ;
						}
					else
					  {
						id = -1;
					    error = true;
					  }
				  }
				
				if ( !error )
				  forward = mapping.findForward("printGlobal");
				
				if ( error )
					{
					  if ( id == -1)
					    {
					      com.bituos.Error e = new com.bituos.Error("No existe la cotizacion.", request);
					    }
					  
					  if ( id == -2)
					    {
						  com.bituos.Error e1 = new com.bituos.Error("Error de seguridad", request);
					    }
						  
					  httpSession.setAttribute("done", "www.bituos.com" );
	
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
		if ( preWVForm.getProcess().equals("showSale") ) //
		  {
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
				boolean error = false;
				Aes aes = new Aes();
				String decryptedID = null;
				int id = -1;
				
				try
				  {
					decryptedID = aes.decrypt(preWVForm.getNip() );
					id = new Integer( decryptedID ).intValue();
				  }
				catch(Throwable  m)
				  {
					id = -2;
				  }
				
				if ( id == -2 )
				  error = true;
				
				if ( !error )
				  {
					String query =  " FROM BeanSale u" +
									" WHERE u.id=" + id; 

					List list = session.createQuery( query ).list();
				
					if ( list.size() != 0 )
						{
							httpSession.setAttribute( "crystal_rptname", new String("rptSale.rpt") ) ;
							httpSession.setAttribute( "parameterCount", new Integer( 1 ) ) ;
												
							httpSession.setAttribute( "parameter1", new Integer( id ) ) ;
							httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
							httpSession.setAttribute( "parameterName1", new String("id_sale") ) ;
						}
					else
					  {
						id = -1;
					    error = true;
					  }
				  }
				
				if ( !error )
				  forward = mapping.findForward("printGlobal");
				
				if ( error )
					{
					  if ( id == -1)
					    {
					      com.bituos.Error e = new com.bituos.Error("No existe la venta.", request);
					    }
					  
					  if ( id == -2)
					    {
						  com.bituos.Error e1 = new com.bituos.Error("Error de seguridad", request);
					    }
						  
					  httpSession.setAttribute("done", "www.bituos.com" );
	
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
	    else if ( preWVForm.getProcess().equals("endsa") )  // encrypt
		{
		  try
			{
				boolean error = false;
				Aes aes = new Aes();
				String encryptedID = aes.encrypt( preWVForm.getNip() );
				int id = -1;

				
				com.bituos.Error e1 = new com.bituos.Error("Error de seguridad:" + encryptedID, request);
					  
				httpSession.setAttribute("done", "www.bituos.com" );
		
				forward = mapping.findForward("error");
			}
		  catch( Throwable  m)
			{
			  m.printStackTrace();

			  com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);

			  forward = mapping.findForward("error");
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
