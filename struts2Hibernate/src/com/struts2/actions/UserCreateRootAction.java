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

public class UserCreateRootAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		UserCreateRoot userCreateRoot = (UserCreateRoot) form;

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
	                
					String query = " FROM BeanUser u"
								 + " WHERE u.id_company = '" + userCreateRoot.getId_company().trim() + "'" 
								 + " AND u.name='ROOT'";
	                             
					List list = session.createQuery( query ).list();
	                
					if ( list.size() > 0 )
					  {
						com.bituos.Error error = new com.bituos.Error("El usuario ya existe.", request);
					
						forward = mapping.findForward("error");
					  }
					else 
					  {
						BeanUser bean = new BeanUser();
						BeanCompany beanCompany = (BeanCompany) session.load( BeanCompany.class, new Integer( userCreateRoot.getId_company().trim() )); 

						bean.setActive( "Y");
						bean.setAddress( "" );
						bean.setId_company( beanCompany );
						bean.setName( "ROOT" );
						bean.setPassword( userCreateRoot.getPassword() );
						bean.setRfc("");
						bean.setEmail( userCreateRoot.getEmail() );

						query =" FROM BeanProcess u"
							 + " WHERE u.id NOT IN (61,62,63,64,101)"; 

						Iterator iter = session.createQuery( query ).list().iterator();
						
						tx = session.beginTransaction();

						session.save( bean );

						while (iter.hasNext())
						  {
							BeanProcessUsers beanProcessUsers = new BeanProcessUsers();
							
							beanProcessUsers.setId_process( (BeanProcess) iter.next() );
							beanProcessUsers.setId_user( bean );
							
							session.save( beanProcessUsers );
						  }

						tx.commit();

						httpSession.setAttribute("done", new String("main.jsp"));
						forward = mapping.findForward("ok");
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
