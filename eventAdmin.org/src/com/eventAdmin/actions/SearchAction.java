package com.eventAdmin.actions;

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
import com.eventAdmin.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import com.eventAdmin.forms.SearchForm;

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

				if ( searchForm.getConcept().equals("client") )  //client
				  {
				  	if ( searchForm.getForward().equals("delete") )
				  	  {

						query = " FROM BeanClient u" +
								" WHERE u.name LIKE '%" + searchForm.getFilter() + "%'" +
								" AND u.id_company = " + beanUser.getId_company().getId() +
								" AND u.active='Y'"+
								" ORDER BY u.id";

						List list = session.createQuery( query ).list();


						httpSession.setAttribute("filterList", list );

						httpSession.setAttribute("done", "client/clientDelete.jsp" );
						forward = mapping.findForward("clientDelete");
				  	  }
					else if ( searchForm.getForward().equals("activate") )
					  {
						query = " FROM BeanClient u" +
								" WHERE u.name LIKE '%" + searchForm.getFilter() + "%'" +
								" AND u.id_company = " + beanUser.getId_company().getId() +
								" AND u.active='N'";

						List list = session.createQuery( query ).list();

						httpSession.setAttribute("filterList", list );

						forward = mapping.findForward("clientActivate");
					  }
					else if ( searchForm.getForward().equals("change") )
					  {
						query = " FROM BeanClient u" +
								" WHERE u.name LIKE '%" + searchForm.getFilter() + "%'" +
								" AND u.id_company = " + beanUser.getId_company().getId() +
								" AND u.active='Y'";

						List list = session.createQuery( query ).list();

						httpSession.setAttribute("filterList", list );

						forward = mapping.findForward("clientSelect");
					  }
					else if ( searchForm.getForward().equals("creditPaymentNew") )
					  {
						query = " FROM BeanClient u" +
								" WHERE u.active = 'Y'" +
								" AND u.name LIKE '%" + searchForm.getFilter() + "%'" +
								" AND u.id IN ( SELECT c.id_client.id" +
								"               FROM BeanCxC c" +
								"               WHERE c.remain > 0" +
								"             )" +
								" AND u.id_company = " + beanUser.getId_company().getId() +
								" ORDER BY u.id";


						List list = session.createQuery( query ).list();

						httpSession.setAttribute("filterList", list );

						forward = mapping.findForward("creditPaymentNew");
					  }

					else if ( searchForm.getForward().equals("creditPaymentDelete") )
					  {
						query = " FROM BeanClient u" +
								" WHERE u.active = 'Y'" +
								" AND u.name LIKE '%" + searchForm.getFilter() + "%'" +
								" AND u.id IN ( SELECT c.id_cxc.id_client.id" +
								"               FROM BeanCxCDetail c" +
								"             )" +
								" AND u.id_company = " + beanUser.getId_company().getId() +
								" ORDER BY u.id";


						List list = session.createQuery( query ).list();

						httpSession.setAttribute("filterList", list );

						forward = mapping.findForward("creditPaymentDelete");
					  }


					else if ( searchForm.getForward().equals("payment") )
						  {
							query = " FROM BeanCxc u" +
									" WHERE u.id LIKE '%" + searchForm.getFilter() + "%'" ;


							List list = session.createQuery( query ).list();

							httpSession.setAttribute("list", list );

							forward = mapping.findForward("payments_details");
						  }

					else if ( searchForm.getForward().equals("asignSalesAgent") )
					{
						//una lista de usuarios que son buscados por nombre para ser asignados
						 query = " FROM BeanClient u" +
								 " WHERE u.name LIKE '%" + searchForm.getFilter() + "%'" +
								 " AND u.id_company = " + beanUser.getId_company().getId() +
								 " AND u.active='Y'";

					  List list = session.createQuery( query ).list();
					  if(list.size()>0)
						{
						  httpSession.setAttribute("filterList", list );
						//forward declarado en struts-config.xml
						  forward = mapping.findForward("clientAsignSalesAgent");
						}
					  else
						{
							com.bituos.Error e = new com.bituos.Error("Usuario no existe o fue dado de baja.", request);
							httpSession.setAttribute("done", "javascript:history.back();");
							forward = mapping.findForward("error");
						}

					}

					else
					{
						com.bituos.Error e = new com.bituos.Error("Opcion no programada. Consulte a soporte t?cnico.", request);
						httpSession.setAttribute("done", "main.jsp");
						forward = mapping.findForward("error");
					}
				  }

				else if ( searchForm.getConcept().equals("user") )  //user
				  {
					if ( searchForm.getForward().equals("delete") )
					  {
						//Pone en sesi?n una lista de usuarios que son buscados por nombre
						query = " FROM BeanUser u" +
							    " WHERE u.name LIKE '%" + searchForm.getFilter() + "%'" +
								" AND u.id_company = " + beanUser.getId_company().getId() +
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
							     " AND u.id_company = " + beanUser.getId_company().getId() +
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
						//Pone en sesi?n una lista de usuarios que son buscados por nombre para ser activados
						  query = " FROM BeanUser u" +
								  " WHERE u.name LIKE '%" + searchForm.getFilter() + "%'" +
								  " AND u.id_company = " + beanUser.getId_company().getId() +
								  " AND u.active='N'";

						List list = session.createQuery( query ).list();

						httpSession.setAttribute("filterList", list );
						//forward declarado en struts-config.xml
						forward = mapping.findForward("userActivate");

					  }
					else
					{
						com.bituos.Error e = new com.bituos.Error("Opcion no programada. Consulte a soporte t?cnico.", request);
						httpSession.setAttribute("done", "main.jsp");
						//forward declarado en struts-config.xml
						forward = mapping.findForward("error");
					}
				  }
				else if ( searchForm.getConcept().equals("quote") )  //case
				  {
					if ( searchForm.getForward().equals("new") )
					  {
						//Pone en sesi?n una lista de clientes que son buscados por algun criterio
						
						if ( searchForm.getAditional1().equals("name") )
							query = " FROM BeanClient u" +
									" WHERE u.name LIKE '%" + searchForm.getFilter().toUpperCase() + "%'" +
									" AND u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active='Y'";
						else if ( searchForm.getAditional1().equals("rfc") )
							query = " FROM BeanClient u" +
									" WHERE u.rfc LIKE '%" + searchForm.getFilter2() + "%'" +
									" AND u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active='Y'";
						else if ( searchForm.getAditional1().equals("address") )
							query = " FROM BeanClient u" +
									" WHERE u.address LIKE '%" + searchForm.getFilter3().toUpperCase() + "%'" +
									" AND u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active='Y'";
						else
							query = " FROM BeanClient u" +
									" WHERE u.phone LIKE '%" + searchForm.getFilter4().toUpperCase() + "%'" +
									" AND u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active='Y'";

						List list = session.createQuery( query ).list();

						httpSession.setAttribute("filterList", list );
						  //forward declarado en struts-config.xml
						forward = mapping.findForward("quoteNew");

					  }
					else if ( searchForm.getForward().equals("resolve") )
					  {
						//Pone en sesi?n una lista de usuarios que son buscados por nombre
						query = " FROM BeanUser u" +
								" WHERE (u.name LIKE '%" + searchForm.getFilter().toUpperCase() + "%'" +
								"        OR " +
								"        u.full_name LIKE '%" + searchForm.getFilter().toUpperCase() + "%'" +
								"       )" +
								" AND u.id_company = " + beanUser.getId_company().getId() +
								" AND u.active='Y'";

						List list = session.createQuery( query ).list();

						httpSession.setAttribute("filterList", list );
						  //forward declarado en struts-config.xml
						forward = mapping.findForward("caseResolve");

					  }
					else
					{
						com.bituos.Error e = new com.bituos.Error("Opcion no programada. Consulte a soporte t?cnico.", request);
						httpSession.setAttribute("done", "main.jsp");
						//forward declarado en struts-config.xml
						forward = mapping.findForward("error");
					}
				  }

				else
				{
					com.bituos.Error e = new com.bituos.Error("Opcion no programada. Consulte a soporte t?cnico.", request);
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
