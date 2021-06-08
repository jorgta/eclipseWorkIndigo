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

		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");
		String process = preForm.getProcess();
	    
		if ( beanUser == null )
		  forward = mapping.findForward("login");
		else if ( process.equals("'userNew'") )  //new user
			{
		      forward = mapping.findForward("userNew");
			}
		else if ( process.equals("'userDelete'") ) //delete user
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();
	
					String query =  " FROM BeanUser u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active = 'Y'" +
									" AND u.id <> " + beanUser.getId() + 
					  				" ORDER BY u.name";
								
					List list = session.createQuery( query ).list();
	
					if ( list.size() != 0 )
						{
						  httpSession.setAttribute("listUserDelete", list );
	
						  forward = mapping.findForward("userDelete");
						}
					else
						{
						  com.bituos.Error e = new com.bituos.Error("No existen usuarios para borrar", request);
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
		else if ( process.equals("'userActivate'") ) //activate user
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					String query =  " FROM BeanUser u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active = 'N'" +
									" ORDER BY u.name";
							
					List list = session.createQuery( query ).list();

					if ( list.size() != 0 )
						{
						  httpSession.setAttribute("listUserActive", list );

						  forward = mapping.findForward("userActivate");
						}
					else
						{
						  com.bituos.Error e = new com.bituos.Error("No existen usuarios para activar", request);
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
		else if ( process.equals("'userChange'") ) //select user
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					String query =  " FROM BeanUser u" +
									" WHERE u.active = 'Y'" +
									" AND u.id <> " + beanUser.getId() +
									" AND u.id_company = " + beanUser.getId_company().getId() +
					  				" ORDER BY u.name";
					
					List list = session.createQuery( query ).list();

					//httpSession.setAttribute( "list", list);

					if ( list.size() != 0 )
						{
						  httpSession.setAttribute("listUserChange", list );

						  forward = mapping.findForward("userSelect");
						}
					else
						{
						  com.bituos.Error e = new com.bituos.Error("No existen usuarios para cambiarle los datos.", request);
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
		else if ( process.equals("'processAdmin'") ) //process Admin
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					String query =  " FROM BeanUser u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active = 'Y'" +
									" AND u.id <> " + beanUser.getId() +
									" ORDER BY u.name";

					List list = session.createQuery( query ).list();

					if ( list.size() == 0 )
					  {
						com.bituos.Error e = new com.bituos.Error("Ud es el unico usuario disponible.", request);
						httpSession.setAttribute("done", "main.jsp" );

						forward = mapping.findForward("error");
					  }
					else
					  {
					  	BeanUser processAdminCurrentUser = (BeanUser) list.get(0);
						httpSession.setAttribute("processAdmin_currentUser", processAdminCurrentUser );
						httpSession.setAttribute("processAdmin_listUsers", list );

						query = " FROM BeanProcess p" +
								" WHERE p.id NOT IN ( SELECT pa.id_process.id" +
								"                     FROM BeanProcessUsers pa" +
								"                     WHERE pa.id_user.id=" + processAdminCurrentUser.getId() +
								"                   )" +
								" AND p.id NOT IN (61, 62, 63, 64, 101)";
								                    
						list = session.createQuery( query ).list();
						httpSession.setAttribute("processAdmin_listDeny", list );
						
						query = " FROM BeanProcess p" +
								" WHERE p.id IN ( SELECT pa.id_process.id" +
								"                 FROM BeanProcessUsers pa" +
								"                 WHERE pa.id_user.id=" + processAdminCurrentUser.getId() +
								"               )";
								                    
						list = session.createQuery( query ).list();
						httpSession.setAttribute("processAdmin_listAllow", list );

						forward = mapping.findForward("processAdmin");
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
		else if ( process.equals("'clientNew'") )  //new client
			{
			  forward = mapping.findForward("clientNew");
			}
		else if ( process.equals("'clientDelete'") ) //delete user
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					String query =  " FROM BeanClient u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active = 'Y'" +
									" AND u.id <> " + beanUser.getId() +
					  				" ORDER BY u.name";
							
					List list = session.createQuery( query ).list();

					if ( list.size() != 0 )
						{
						  httpSession.setAttribute("list", list );

						  forward = mapping.findForward("clientDelete");
						}
					else
						{
						  com.bituos.Error e = new com.bituos.Error("No existen clientes para borrar", request);
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
		else if ( process.equals("'clientActivate'") ) //activate client
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					String query =  " FROM BeanClient u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active = 'N'" +
									" ORDER BY u.name";
						
					List list = session.createQuery( query ).list();

					if ( list.size() != 0 )
						{
						  httpSession.setAttribute("list", list );

						  forward = mapping.findForward("clientActivate");
						}
					else
						{
						  com.bituos.Error e = new com.bituos.Error("No existen clientes para activar", request);
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
		else if ( process.equals("'clientChange'") ) //select client
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					String query =  " FROM BeanClient u" +
									" WHERE u.active = 'Y'" +
									" AND u.id <> " + beanUser.getId() +
									" AND u.id_company = " + beanUser.getId_company().getId() +
									" ORDER BY u.name";
				
					List list = session.createQuery( query ).list();

					if ( list.size() != 0 )
						{
						  httpSession.setAttribute("list", list );

						  forward = mapping.findForward("clientSelect");
						}
					else
						{
						  com.bituos.Error e = new com.bituos.Error("No existen clientes para cambiarle los datos.", request);
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
		
		else if ( preForm.getProcess().equals("'quoteNew'") )  //Cotizacion nueva
		  {
			  try
				{
				  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				  session = sessionFactory.openSession();

				  //Lista de clientes activos
				 String query =  " FROM BeanClient u" +
								 " WHERE u.id_company = " + beanUser.getId_company().getId() +  
								 " AND u.active = 'Y'" +  
								 " ORDER BY u.id";

				  List list = session.createQuery( query ).list();

				  if ( list.size() != 0 )
					  {
						query = " FROM BeanClient u" +
								" WHERE u.id_company = " + beanUser.getId_company().getId() +  
								" AND u.active = 'Y'" +  
								" AND u.id IN ( SELECT c.id_client.id" +  
								"               FROM BeanQuote c" +  
								"               ORDER BY c.date_reg" +  
								"              )" +  
								" ORDER BY u.name";
						
						list = session.createQuery( query ).list();

						//	los 6 primeros
						if ( list.size() > 6)
						  list = list.subList(0, 6);
						
						httpSession.setAttribute("filterList", list );

						list.clear();

						httpSession.setAttribute("listList", list );
						httpSession.setAttribute("listOptionsList", list );
						httpSession.setAttribute("listOptionMenuList", list );

						httpSession.removeAttribute( "quoteNew_currentSaloon" );
						httpSession.removeAttribute( "quoteNew_currentFlower" );
						httpSession.removeAttribute( "quoteNew_currentMusic" );
						httpSession.removeAttribute( "qnCarListProduct" );
						
						httpSession.setAttribute("visibleCase", "no");
						httpSession.setAttribute("visibleUserData", "no");

						httpSession.removeAttribute("clientName");
						
						  //forward  declarado en struts-config.xml
						forward = mapping.findForward("quoteNew");
					  }
				  else
					  {
						com.bituos.Error e = new com.bituos.Error("No existen clientes activos, por favor registre uno.", request);
						httpSession.setAttribute("done", "javascript:window.close();" );
						  //forward  declarado en struts-config.xml
						forward = mapping.findForward("error");
					  }

				}
			  catch( Throwable  m)
				{
				  m.printStackTrace();
				  com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
				//forward  declarado en struts-config.xml
				  forward = mapping.findForward("error");
				}
			  finally
				{
				  session.close();
				  sessionFactory.close();
				}
		  }
		
		else if ( process.equals("'quoteDelete'") ) //delete quote
		  {
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				String query =  " FROM BeanQuote u" +
								" WHERE u.id_company = " + beanUser.getId_company().getId() +
				  				" ORDER BY u.id";
							
				List list = session.createQuery( query ).list();

				if ( list.size() != 0 )
					{
					  httpSession.setAttribute("listQuoteDelete", list );

					  forward = mapping.findForward("quoteDelete");
					}
				else
					{
					  com.bituos.Error e = new com.bituos.Error("No existen cotizaciones para borrar", request);
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
		else if ( preForm.getProcess().equals("'listEdit'") )  //Editar listas de precios
		  {
			  try
				{
				  int id_list = LocalUtils.putListInSession( request, 0 );
				  int id_option = LocalUtils.putOptionInSession( request, id_list, 0 );
				  int id_menu = LocalUtils.putMenuInSession( request, id_option, 0 );
				  
					  //forward  declarado en struts-config.xml
				  forward = mapping.findForward("listEdit");
				}
			  catch( Throwable  m)
				{
				  m.printStackTrace();
				  com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
				//forward  declarado en struts-config.xml
				  forward = mapping.findForward("error");
				}
		  }
		else if ( preForm.getProcess().equals("'listFlowerMusicEdit'") )  
		  {
			  try
				{
				  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				  session = sessionFactory.openSession();

				  LocalUtils.putFlowerInSession(request, 0);
				  LocalUtils.putMusicInSession(request, 0);

				  forward = mapping.findForward("listFlowerMusicEdit");
				}
			  catch( Throwable  m)
				{
				  m.printStackTrace();
				  com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
				//forward  declarado en struts-config.xml
				  forward = mapping.findForward("error");
				}
			  finally
				{
				  session.close();
				  sessionFactory.close();
				}
		  }
		
		else if ( preForm.getProcess().equals("'listSaloonEdit'") )  
		  {
			  try
				{
				  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				  session = sessionFactory.openSession();

				  LocalUtils.putSaloonInSession(request, 0);

				  forward = mapping.findForward("listSaloonEdit");
				}
			  catch( Throwable  m)
				{
				  m.printStackTrace();
				  com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
				//forward  declarado en struts-config.xml
				  forward = mapping.findForward("error");
				}
			  finally
				{
				  session.close();
				  sessionFactory.close();
				}
		  }
		else if ( preForm.getProcess().equals("'listProductEdit'") )  
		  {
			  try
				{
				  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				  session = sessionFactory.openSession();

				  LocalUtils.putProductInSession( request, 0);
				  
				  forward = mapping.findForward("listProductEdit");
				}
			  catch( Throwable  m)
				{
				  m.printStackTrace();
				  com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
				//forward  declarado en struts-config.xml
				  forward = mapping.findForward("error");
				}
			  finally
				{
				  session.close();
				  sessionFactory.close();
				}
		  }
		else if ( preForm.getProcess().equals("'availabilitySaloonEventType'") )  //ver disponibilidad 
		  {
			  try
				{
				  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				  session = sessionFactory.openSession();

				  String query = " FROM BeanSaloon u" +
								 " WHERE u.id_company = " + beanUser.getId_company().getId() +  
								 " AND u.active = 'Y'" +  
								 " ORDER BY u.description";
					
				  List list = session.createQuery( query ).list();
				  httpSession.setAttribute("aset_SaloonList", list );
	
				  query = " FROM BeanList u" +
						  " WHERE u.id_company = " + beanUser.getId_company().getId() +  
						  " AND u.active = 'Y'" +  
						  " ORDER BY u.description";
		
				  list = session.createQuery( query ).list();
				  httpSession.setAttribute("aset_listList", list );

	  			  forward = mapping.findForward("availabilitySaloonEventType");

				}
			  catch( Throwable  m)
				{
				  m.printStackTrace();
				  com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
				//forward  declarado en struts-config.xml
				  forward = mapping.findForward("error");
				}
			  finally
				{
				  session.close();
				  sessionFactory.close();
				}
		  }
		
		else if ( process.equals("'cardNew'") ) // card New
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					String query =  " FROM BeanClient u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active = 'Y'";
							
					List list = session.createQuery( query ).list();

					if ( list.size() != 0 )
						{
						  httpSession.setAttribute("list", list );

						  forward = mapping.findForward("cardNew");
						}
					else
						{
						  com.bituos.Error e = new com.bituos.Error("No existen clientes.", request);
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
		else if ( process.equals("'cardDelete'") ) //delete card
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					String query =  " FROM BeanCard u" +
									" WHERE u.id_client.id_company = " + beanUser.getId_company().getId() +
									" AND u.active = 'Y'";
						
					List list = session.createQuery( query ).list();

					if ( list.size() != 0 )
						{
						  httpSession.setAttribute("list", list );

						  forward = mapping.findForward("cardDelete");
						}
					else
						{
						  com.bituos.Error e = new com.bituos.Error("No existen tarjetas para borrar", request);
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
		else if ( process.equals("'cardActivate'") ) //activate card
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					String query =  " FROM BeanCard u" +
									" WHERE u.id_client.id_company = " + beanUser.getId_company().getId() +
									" AND u.active = 'N'";
					
					List list = session.createQuery( query ).list();

					if ( list.size() != 0 )
						{
						  httpSession.setAttribute("list", list );

						  forward = mapping.findForward("cardActivate");
						}
					else
						{
						  com.bituos.Error e = new com.bituos.Error("No existen tarjetas para activar", request);
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
		else if ( process.equals("'cardRecharge'") )  //card Recharge
			{
			  forward = mapping.findForward("cardRecharge");
			}
		else if ( process.equals("'cardBuy'") )  //card Buy
			{
			  forward = mapping.findForward("cardBuy");
			}
		else if ( process.equals("'companyNew'") )  //new company
			{
			  forward = mapping.findForward("companyNew");
			}
		else if ( process.equals("'companyDelete'") ) //delete company
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					String query =  " FROM BeanCompany u" +
									" WHERE u.active = 'Y'";
					
					List list = session.createQuery( query ).list();

					if ( list.size() != 0 )
						{
						  httpSession.setAttribute("list", list );

						  forward = mapping.findForward("companyDelete");
						}
					else
						{
						  com.bituos.Error e = new com.bituos.Error("No existen empresas para borrar", request);
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
		else if ( process.equals("'companyActivate'") ) //activate company
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					String query =  " FROM BeanCompany u" +
									" WHERE u.active = 'N'";
				
					List list = session.createQuery( query ).list();

					if ( list.size() != 0 )
						{
						  httpSession.setAttribute("list", list );

						  forward = mapping.findForward("companyActivate");
						}
					else
						{
						  com.bituos.Error e = new com.bituos.Error("No existen empresas para activar", request);
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
		else if ( process.equals("'companyChange'") ) //select company
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					String query =  " FROM BeanCompany u" +
									" WHERE u.active = 'Y'" +
									" ORDER BY u.full_name";
			
					List list = session.createQuery( query ).list();

					if ( list.size() != 0 )
						{
						  httpSession.setAttribute("list", list );

						  forward = mapping.findForward("companySelect");
						}
					else
						{
						  com.bituos.Error e = new com.bituos.Error("No existen empresas para cambiarle los datos.", request);
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
		else if ( process.equals("'rpt.ourClients'") ) //rpt Our clients
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					String query =  " FROM BeanClient u" +
									" WHERE u.active = 'Y'" +
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
		else if ( process.equals("'rpt.cardHistory'") ) //rpt Our clients
			  {
				forward = mapping.findForward("rpt.cardHistory");
			  }
		else if ( process.equals("'userCreateRoot'") ) //create company root
				{
				  try
					{
					  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					  session = sessionFactory.openSession();
		
					  String query =  " FROM BeanCompany c" +
									  " WHERE c.id NOT IN ( SELECT u.id_company" +
									  "                     FROM BeanUser u" +
									  "                     WHERE name='ROOT'" +
									  "                   )" +
									  " ORDER BY c.name";
					
					  List list = session.createQuery( query ).list();
		
					  httpSession.setAttribute("list", list );
					  
					  if ( list.size() == 0 )
					    {
					      com.bituos.Error e = new com.bituos.Error("Todas las empresas activas ya tienen creado el usuario 'root'", request);
						  httpSession.setAttribute("done", "main.jsp" );
					      forward = mapping.findForward("error");
						}
			          else
					    forward = mapping.findForward("userCreateRoot");
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
