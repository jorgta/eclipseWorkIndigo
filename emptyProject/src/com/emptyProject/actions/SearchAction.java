package com.emptyProject.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.*;

import com.bituos.*;
import com.emptyProject.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import com.emptyProject.forms.SearchForm;

/**
 * @version 	1.0
 * @author
 */

public class SearchAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value

		SearchForm searchForm = (SearchForm) form;

		HttpSession httpSession = request.getSession();

		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
			forward = mapping.findForward("login");
		else {
			Config config = new Config( request );

			SessionFactory sessionFactory = null;
			Session    session = null;
			Transaction tx = null;
			String query;

			try {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				 if ( searchForm.getConcept().equals("user") )  //user
				  {
					if ( searchForm.getForward().equals("delete") )
					  {
						//Pone en sesión una lista de usuarios que son buscados por nombre
						query = " FROM BeanUser u" +
							    " WHERE u.name LIKE '%" + searchForm.getFilter() + "%'" +								
							    " AND u.active='Y'";

						List list = session.createQuery( query ).list();

						httpSession.setAttribute("filterList", list );
						  //forward declarado en struts-config.xml
						forward = mapping.findForward("userDelete");

					  }
					else if ( searchForm.getForward().equals("asignSupervisor") )
					{
					    //una lista de usuarios que son buscados por nombre para ser asignados
					     query = " FROM BeanUser u" +
							     " WHERE u.name LIKE '%" + searchForm.getFilter() + "%'" +							    
							     " AND u.active='Y'";

					  List list = session.createQuery( query ).list();
					  if(list.size()>0)
						{
						  httpSession.setAttribute("filterList", list );
						//forward declarado en struts-config.xml
						  forward = mapping.findForward("userAsignSupervisor");
						}
					  else
						{
							com.bituos.Error e = new com.bituos.Error("Usuario no existe o fue dado de baja.", request);
							httpSession.setAttribute("done", "javascript:history.back();");
							forward = mapping.findForward("error");
						}

					}
					else if ( searchForm.getForward().equals("activate") )
					  {
						//Pone en sesión una lista de usuarios que son buscados por nombre para ser activados
						  query = " FROM BeanUser u" +
								  " WHERE u.name LIKE '%" + searchForm.getFilter() + "%'" +							
								  " AND u.active='N'";

						List list = session.createQuery( query ).list();

						httpSession.setAttribute("filterList", list );
						//forward declarado en struts-config.xml
						forward = mapping.findForward("userActivate");

					  }
					else
					{
						com.bituos.Error e = new com.bituos.Error("Opcion no programada. Consulte a soporte técnico.", request);
						httpSession.setAttribute("done", "main.jsp");
						//forward declarado en struts-config.xml
						forward = mapping.findForward("error");
					}
				  }
				else if ( searchForm.getConcept().equals("quote") )  //case
				  {
					if ( searchForm.getForward().equals("new") )
					  {
		
						//List list = session.createQuery( query ).list();

						//httpSession.setAttribute("filterList", list );
						  //forward declarado en struts-config.xml
						forward = mapping.findForward("quoteNew");

					  }
					else if ( searchForm.getForward().equals("resolve") )
					  {
			
						//List list = session.createQuery( query ).list();

						//httpSession.setAttribute("filterList", list );
						  //forward declarado en struts-config.xml
						forward = mapping.findForward("caseResolve");

					  }
					else
					{
						com.bituos.Error e = new com.bituos.Error("Opcion no programada. Consulte a soporte técnico.", request);
						httpSession.setAttribute("done", "main.jsp");
						//forward declarado en struts-config.xml
						forward = mapping.findForward("error");
					}
				  }

				else
				{
					com.bituos.Error e = new com.bituos.Error("Opcion no programada. Consulte a soporte técnico.", request);
					httpSession.setAttribute("done", "main.jsp");
					//forward declarado en struts-config.xml
					forward = mapping.findForward("error");
				}

			}
			catch( Throwable  m)
			{
					m.printStackTrace();
					httpSession.setAttribute("done", "javascript:history.back();");
					com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
				        //forward declarado en struts-config.xml
					forward = mapping.findForward("error");
			}
			finally	{
				if ( session != null )
					session.close();

				if ( sessionFactory != null )
					sessionFactory.close();
			}
		}

		// Termina con
		return (forward);

	}
}
