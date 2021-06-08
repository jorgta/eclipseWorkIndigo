package com.bituos.gjv.actions;

import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import java.util.*;

import com.bituos.*;
import com.bituos.gjv.Beans.*;
import com.bituos.gjv.forms.*;


public class LoginAction extends  Action
{
	public ActionForward execute( ActionMapping mapping, 
								  ActionForm form,
								  HttpServletRequest request, 
								  HttpServletResponse response) throws Exception 

	{
		ActionErrors errors = new ActionErrors();

		ActionForward forward = new ActionForward();
		// return value
		LoginForm loginForm = (LoginForm) form;

		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("done");

		httpSession.removeAttribute("configuration");
		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		String nextLink = (String) httpSession.getAttribute( "nextLink" );
		
		try
		  {
			sessionFactory = config.getConfiguration(request).buildSessionFactory();
			session = sessionFactory.openSession();

			String query =   " FROM BeanUser u"+
							 " WHERE (u.name='" + loginForm.getUsername() + "'" +
							 "       OR u.email='" + loginForm.getUsername() + "'" +
							 "       )" +
							 " AND u.password='" + loginForm.getPassword() + "'";

			List list = session.createQuery(query).list();

			if (!list.isEmpty()){

				BeanUser beanUser = new BeanUser ();

				beanUser = (BeanUser) list.get(0);

				if (beanUser.getActive().equals("Y"))
				  {
					SecurityUtil util = new SecurityUtil();

					if ( util.createProcessList(request, beanUser.getId() ) != 0 )
						{
						  com.bituos.Error error = new com.bituos.Error( "Error aplicando las directivas de seguridad", request);

						  errors.add("unknown", new org.apache.struts.action.ActionError("error.unknow"));
						  httpSession.setAttribute("done", new String("login.jsp"));

						  forward = mapping.findForward("error");
						}
				    else
						{
						  httpSession.setAttribute("beanUser", beanUser );
						  new Log().registerEvent("login", 1, request);

						  		
						  //if ( nextLink == null )
						    forward = mapping.findForward("ok");
						  //else
						    //forward = mapping.findForward("forward");
						}
				  }
				else
				  {
					errors.add("name", new org.apache.struts.action.ActionError("error.notactive.user"));
					com.bituos.Error error = new com.bituos.Error("Usuario inactivo.", request);
					httpSession.setAttribute("done", new String("login.jsp"));

					forward = mapping.findForward("error");
				  }

			  }
			else
			  {
				  httpSession.setAttribute("done", "javascript:history.back();");

				  errors.add("name", new org.apache.struts.action.ActionError("error.notexist.username"));
				  com.bituos.Error error = new com.bituos.Error("Usuario inexistente o contraseña incorrecta.", request);
				  httpSession.setAttribute("done", new String("login.jsp"));

				  forward = mapping.findForward("error");
			  }
		  }
		catch (Exception e)
		  {
			e.printStackTrace();

			com.bituos.Error error = new com.bituos.Error( "Error interno, consulte a soporte tecnico. (" + e.getMessage() + ")", request);

			forward = mapping.findForward("error");
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

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}

		// Write logic determining how the user should be forwarded.

		// Finish with
		return (forward);

	}

}
