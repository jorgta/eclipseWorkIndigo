package com.struts2.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;



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

import java.io.*;


public class CompanyAffiliateAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		CompanyAffiliateForm companyAffiliateForm = (CompanyAffiliateForm) form;

		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("done");
									
		Config config = new Config( request );
	
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		try 
		  {
			sessionFactory = config.getConfiguration(request).buildSessionFactory();
			session = sessionFactory.openSession();
		
			BeanContact bean = new BeanContact();
			BeanControlPanel control_panel = (BeanControlPanel) session.load( BeanControlPanel.class, new Integer( 1 ) );
			
			
			try 
			  {
				tx = session.beginTransaction();
			
				bean.setCompany( companyAffiliateForm.getCompany().toUpperCase() );
				bean.setName( companyAffiliateForm.getName().toUpperCase() );
				bean.setEmail( companyAffiliateForm.getEmail() );
				bean.setPhone( companyAffiliateForm.getPhone() );
				bean.setDate_reg( Utils.Today() );
				bean.setIp( request.getRemoteAddr() + "(" + request.getRemoteHost() + ")" );
				bean.setComments( companyAffiliateForm.getComments() );

				session.save(bean);

			    tx.commit();	
			    
				String query =  " FROM BeanEmail u" +
								" WHERE u.email <> ''";
								
				List list = session.createQuery( query ).list();
			    
				//String emailLuis = "dsa1972@yahoo.com";		  	
				//String emailLuis = "Lic. Luis R. Morales Casanova <rmorales@mosacpublicidad.com>";		  	

				Iterator iter = list.iterator();
				
				while ( iter.hasNext() )
				  {
					new com.bituos.Mail( ((BeanEmail) iter.next()).getEmail(),
												"Hola\n\n" + 
												"Se ha hecho una nueva afiliacion de una empresa:" + "\n\n" +
												"Compannia: " + bean.getCompany() + ":\n\n" +
												"Nombre: " + bean.getName() + ":\n\n" +
												"email: " + bean.getEmail() + ":\n\n" +
												"Telefono: " + bean.getPhone() + ":\n\n" +
												"Comentarios: " + bean.getComments() + ":\n\n" +
												"Fecha de registro: " + Utils.DateToStr( bean.getDate_reg() ) + ":\n\n" 
												, 
												"cardRegalo.com Afiliacion de empresa.",
												request);
				  }


				forward = mapping.findForward("ok");
				httpSession.setAttribute("done", "main.jsp" );
			  } 
			catch (Throwable  e) 
			  {
			  	tx.rollback();   
			  	
				e.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");
				com.bituos.Error er = new com.bituos.Error("Error interno (" + e.getMessage() + ")", request);
				forward = mapping.findForward("globalError");
				tx.rollback();			  	

			  }
		// do something here

		} 
	  catch (Exception e) 
	    {

			e.printStackTrace();
            
			// Report the error using the appropriate name and ID.
			com.bituos.Error error = new com.bituos.Error( e.getMessage(), request);
			errors.add("name", new ActionError("id"));

		}
	  finally	
	    {
			if ( session != null )
				 session.close(); 
			  
			if ( sessionFactory != null )
				 sessionFactory.close();
		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) 
		  {
			saveErrors(request, errors);
		  }
		// Write logic determining how the user should be forwarded.

		// Finish with
		return (forward);

	}
}
