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

public class ClientActivateAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		ClientActivateForm clientActivateForm = (ClientActivateForm) form;

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
	                
					BeanClient bean = (BeanClient) session.load( BeanClient.class, new Integer( clientActivateForm.getId().toString() ) );
	                
					bean.setActive("Y");
					tx = session.beginTransaction();
					session.update( bean );
					tx.commit();

					httpSession.setAttribute("done", new String("/eventAdmin/client/clientActivate.jsp"));
					forward = mapping.findForward("ok");
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
