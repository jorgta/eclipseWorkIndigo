package com.bituos.lca.actions;

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

import com.bituos.lca.Beans.*;
import com.bituos.lca.forms.*;

import java.util.*;

import com.bituos.lca.*;
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
		  try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
	
				//Branch
				String query =   " FROM BeanBranch u" +
								 " WHERE u.active = 1" +
				  				 " ORDER BY u.name";
							
				List list = session.createQuery( query ).list();
	
			    httpSession.setAttribute("listBranch", list );
	
			    forward = mapping.findForward("userNew");
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
						  
						//Branch
						  query =   " FROM BeanBranch u" +
									" WHERE u.active = 1" +
						  		    " ORDER BY u.name";
									
						  list = session.createQuery( query ).list();
			
					      httpSession.setAttribute("listBranch", list );

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
		else if ( preForm.getProcess().equals("'quoteToSale'") )  //Cotizacion a venta
		  {
			  forward = mapping.findForward("quoteToSale");
		  }
		else if ( preForm.getProcess().equals("'saleChange'") )  //Cotizacion a venta
		  {
			try
			{
			  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			  session = sessionFactory.openSession();
			  
			  String query = " FROM BeanSale u" +
			        	     " WHERE u.id_company = " + beanUser.getId_company().getId() +
			  				 " ORDER BY u.id DESC LIMIT 0, 8";
	
			  List list = session.createQuery( query ).list();
			
			  if ( list.size() > 0 )
			    {
				  
				  httpSession.setAttribute("selectedSale", "no");
				  httpSession.setAttribute("visibleUserData", "no");
				  httpSession.setAttribute("listBeanSale", list );
				  httpSession.setAttribute("total", "0.0" );
				  forward = mapping.findForward("saleChange");
			    }			  
			  else
			   {
				 com.bituos.Error e = new com.bituos.Error("No existen ventas, por favor registre una.", request);
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
			
			com.bituos.Error e = new com.bituos.Error("Opcion no programada. Consulte a soporte tecnico.", request);
			
			forward = mapping.findForward("error");
			
//			try
//			  {
//				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
//				session = sessionFactory.openSession();
//
//				String query =  " FROM BeanQuote u" +
//								" WHERE u.id_company = " + beanUser.getId_company().getId() +
//				  				" ORDER BY u.id";
//							
//				List list = session.createQuery( query ).list();
//
//				if ( list.size() != 0 )
//					{
//					  httpSession.setAttribute("listQuoteDelete", list );
//
//					  forward = mapping.findForward("quoteDelete");
//					}
//				else
//					{
//					  com.bituos.Error e = new com.bituos.Error("No existen cotizaciones para borrar", request);
//					  httpSession.setAttribute("done", "main.jsp" );
//
//					  forward = mapping.findForward("error");
//					}
//			  }
//			catch( Throwable  m)
//			  {
//				m.printStackTrace();
//				
//				com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
//				
//				forward = mapping.findForward("error");
//			  }
//			finally
//			  {
//				session.close();
//				sessionFactory.close();
//			  }
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
		
		else if ( process.equals("'signContract'") )  //card Buy
			{
			  forward = mapping.findForward("signContract");
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
		else if ( process.equals("'companyChangeByUser'") ) //select company
		  {
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
                
				String query =  " FROM BeanCompany u" +
								" WHERE u.active = 'Y'" +
								" AND u.id=" + beanUser.getId_company().getId() +
								" ORDER BY u.full_name";
		
				//List list = session.createQuery( query ).list();
				
				BeanCompany bean = (BeanCompany) session.load( BeanCompany.class, new Integer( beanUser.getId_company().getId() ) );
                
				CompanyChangeByUserForm companyChangeByUserForm = new CompanyChangeByUserForm();

				companyChangeByUserForm.setName( bean.getName() );
				companyChangeByUserForm.setAddress( bean.getAddress() );
				companyChangeByUserForm.setId( bean.getId() );
				companyChangeByUserForm.setRfc( bean.getRfc() );
				companyChangeByUserForm.setFullname( bean.getFull_name() );
				
				companyChangeByUserForm.setSmtp_server( bean.getSmtp_server() );
				companyChangeByUserForm.setSmtp_user_name( bean.getSmtp_user_name() );
				companyChangeByUserForm.setSmtp_password( bean.getSmtp_password() );
				companyChangeByUserForm.setSmtp_email( bean.getSmtp_email() );
				
				companyChangeByUserForm.setSmtp_port( bean.getSmtp_port());
				
				companyChangeByUserForm.setWeb_site(bean.getWeb_site());				
				



			    if ( bean.getSmtp_is_secure().equals("Y") )
			    	companyChangeByUserForm.setSmtp_is_secure("on");
			    else
			    	companyChangeByUserForm.setSmtp_is_secure("off");

				httpSession.setAttribute("companyChangeByUserForm", companyChangeByUserForm );

				forward = mapping.findForward("companyChangeByUser");

	
				
				

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
		else if ( process.equals("'representativeNew'") ) 
		  {
			forward = mapping.findForward("representativeNew");
		  }	
		else if ( process.equals("'patientNew'") ) //select company
		  {			
			 forward = mapping.findForward("patientNew");
		  }	
		else if ( process.equals("'doctorNew'") ) //select company
		  {
			
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				//Test
				String query =  " FROM BeanRepresentative u" +
								//" WHERE u.active = 'Y'" +
				  				" ORDER BY u.name";
							
				List list = session.createQuery( query ).list();

			    httpSession.setAttribute("listBeanRepresentative", list );			
			    forward = mapping.findForward("patientNew");
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
			
			forward = mapping.findForward("doctorNew");
		  }	
		else if ( process.equals("'doctorDelete'") ) //select company
		  {
			
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
                 
				//Test
				String query =  " FROM BeanDoctor u" +
								" WHERE u.active = 1" +
				  				" ORDER BY u.id DESC LIMIT 0, 20";
							
				List list = session.createQuery( query ).list();

			    httpSession.setAttribute("listBeanDoctor", list );			
			    forward = mapping.findForward("doctorDelete");
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
		else if ( process.equals("'doctorActivate'") ) //select company
		  {
			
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
               
				//Test
				String query =  " FROM BeanDoctor u" +
								" WHERE u.active = 0" +
				  				" ORDER BY u.id DESC LIMIT 0, 20";
							
				List list = session.createQuery( query ).list();

			    httpSession.setAttribute("listBeanDoctor", list );			
			    forward = mapping.findForward("doctorActivate");
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
		else if ( process.equals("'doctorChange'") ) //select company
		  {
			
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
             
				//Test
				String query =  " FROM BeanDoctor u" +								
				  				" ORDER BY u.id DESC LIMIT 0, 20";
							
				List list = session.createQuery( query ).list();

			    httpSession.setAttribute("listBeanDoctor", list );			
			    forward = mapping.findForward("doctorSelect");
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
		else if ( process.equals("'doctorSelected'") ) 
		  {		
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				
				/*String query =  " FROM BeanRepresentative u" +						
				  				" ORDER BY u.name";
							
				List list = session.createQuery( query ).list();

			    httpSession.setAttribute("listBeanRepresentative", list );*/			
			    
			    forward = mapping.findForward("doctorSelected");
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
		else if ( process.equals("'testNew'") ) //select company
		  {
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

			    httpSession.removeAttribute("beanParameterLimitList");
			    httpSession.removeAttribute( "testNew_parameterLimitsList");

			    //Test
				String query =  " FROM BeanTest u" +
								//" WHERE u.active = 'Y'" +
				  				" ORDER BY u.name";
							
				List list = session.createQuery( query ).list();

			    httpSession.setAttribute("listTest", list );			    

			    BeanTest beanTest = (BeanTest)list.get(0);
			    
			    httpSession.setAttribute("beanTestCurrent", beanTest );
			    
			    
				//TestParameters				
				query =  " FROM BeanTestParameter u" +
						 " WHERE u.id_test.id = " + beanTest.getId() +
						 " AND u.active = 1 " +
		 				 " ORDER BY u.id";
			

				list = session.createQuery( query ).list();
		
			    httpSession.setAttribute("listTestParameter", list );
			    
			    
			    if ( !list.isEmpty())
			      {
				    BeanTestParameter beanTestParameter = (BeanTestParameter) list.get(0);
				    
				    query =   " FROM BeanParameterLimits u" 
		    			    + " WHERE u.id_test_parameter = " + beanTestParameter.getId();  
		   
					list = session.createQuery(query).list();
					
					if ( !list.isEmpty() )
					  {   
						httpSession.removeAttribute("beanParameterLimitList");
						httpSession.setAttribute("beanParameterLimitList",list);
						
						BeanParameterLimits beanParameterLimits = (BeanParameterLimits)list.get(0);
						BeanTestParameter beanTestParameterCurrent= (BeanTestParameter)beanParameterLimits.getId_test_parameter();							
						httpSession.setAttribute("beanTestParameterCurrent",beanTestParameterCurrent);					
					  }
			      }
					    
		
				forward = mapping.findForward("testNew");
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
		else if ( process.equals("'testChange'") ) //select company
		  {
			
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
				String query =  " FROM BeanTest u" +
							//" WHERE u.active = 'Y'" +
								" ORDER BY u.id DESC LIMIT 0, 20";
						
				List rptOrder_orderList = session.createQuery( query ).list();
	
	            httpSession.setAttribute("testList", rptOrder_orderList );
	            
	                       
	            
	            
	            httpSession.removeAttribute("beanParameterLimitsCurrent");
	            httpSession.removeAttribute("testNew_parameterLimitsList");
	            httpSession.removeAttribute("beanTestParameterCurrent");
	            httpSession.removeAttribute("beanParameterLimitList");
	            
	            
	            
				forward = mapping.findForward("testSelect");
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
		else if ( process.equals("'pkgNew'") ) //select company
		  {
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

			
				String query =  " FROM BeanTest u" +
								//" WHERE u.active = 'Y'" +
				  				" ORDER BY u.name";
							
				List list = session.createQuery( query ).list();

			    httpSession.setAttribute("listTest", list );

				
				/*query =  " FROM BeanTestParameter u" +
						 //" WHERE u.active = 'Y'" +
		  				 " ORDER BY u.name";
					
				list = session.createQuery( query ).list();
		
			    httpSession.setAttribute("listTestParameter", list );*/
			    
			    //httpSession.removeAttribute( "testNew_parameterLimitsList");
			    
			   // List<BeanTestPkgDetail> pkgNew_testList = new ArrayList<BeanTestPkgDetail>();
			   /* BeanTestPkgDetail t =new BeanTestPkgDetail();
			    t.setId(1);			   
			    BeanTest beanTest = (BeanTest) session.load( BeanTest.class, new Integer( 413 ) );	               
			    t.setId_test(beanTest);
			    BeanTestPkg beanTestPkg= new BeanTestPkg();
			    t.setId_test_pkg(beanTestPkg);
			    t.setPrice(100.0);
			    t.setTest_name(beanTest.getName());
			    pkgNew_testList.add(t);*/
			    
			   // httpSession.setAttribute("pkgNew_testList", pkgNew_testList );
			    
			    
		
				forward = mapping.findForward("pkgNew");
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
		else if ( process.equals("'materialNew'") ) //
		  {
			forward = mapping.findForward("materialNew");
		  }			
		else if ( process.equals("'materialNew'") ) //
		  {
			forward = mapping.findForward("materialNew");
		  }			
		else if ( process.equals("'orderNew'") ) 
		  {
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				//Patient
				String query =  " FROM BeanPatient u" +
								//" WHERE u.active = 'Y'" +
				  				" ORDER BY u.name";
							
				List list = session.createQuery( query ).list();

			    httpSession.setAttribute("orderNew_listPatient", list );

				//Test
				query =  " FROM BeanTest u" +
						 " WHERE u.active = 1" +
						 " AND u.id IN ( SELECT a.id_test.id" +
						 "               FROM BeanTestParameter a" +
						 "             )" +
		  				 " ORDER BY u.name";
							
				list = session.createQuery( query ).list();

			    httpSession.setAttribute("orderNew_listTest", list );

				//Branch
				query =  " FROM BeanBranch u" +
						 " WHERE u.active = 1" +
		  				 " ORDER BY u.name";
							
				list = session.createQuery( query ).list();

			    httpSession.setAttribute("orderNew_listBranch", list );
			    
			    //Set User Branch to current branch
			    
			    httpSession.setAttribute("orderNew_currentBranch", beanUser.getId_branch() );

				//Doctor
				query =  " FROM BeanDoctor u" +
						 " WHERE u.active = 1" +
		  				 " ORDER BY u.name";
							
				list = session.createQuery( query ).list();

			    httpSession.setAttribute("orderNew_listDoctor", list );

			    httpSession.removeAttribute( "orderNew_testList");
			    
				//Material
				query =  " FROM BeanMaterial u" +
						 " WHERE u.active = 1" +
		 				 " ORDER BY u.description";
							
				list = session.createQuery( query ).list();
		
			    httpSession.setAttribute("orderNew_materialList", list );
		       
				//Company
			    query =  " FROM BeanCompany u" +
						 " WHERE u.active = 'Y'" +
						 " ORDER BY u.name";
					
				list = session.createQuery( query ).list();
		
			    httpSession.setAttribute("orderNew_companyList", list );

				BeanCompany bean = (BeanCompany) session.load( BeanCompany.class, new Integer( 0 ) );

				httpSession.setAttribute("orderNew_currentCompany", bean );
				
				httpSession.removeAttribute("orderNew_materialDetailList");
				
                 
				 query =  " FROM BeanTestPkg u" +						 
		 				  " ORDER BY u.description";
					
		         list = session.createQuery( query ).list();
		         
		         httpSession.removeAttribute( "orderNew_beanTestPkgList");
		         
		         httpSession.removeAttribute("orderNew_pkgList");
		         httpSession.setAttribute("orderNew_pkgList", list );
			    
			    forward = mapping.findForward("orderNew");
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
		else if ( process.equals("'orderResult'") ) 
		  {
		    httpSession.removeAttribute( "orderResult_beanOrder");
			
			forward = mapping.findForward("orderResult");
		  }
		else if ( process.equals("'orderResultChange'") ) 
		  {
		    httpSession.removeAttribute( "orderResult_beanOrder");
			
			forward = mapping.findForward("orderResultChange");
		  }	
		else if ( process.equals("'representativeNew'") ) //
		  {
			forward = mapping.findForward("representativeNew");
		  }		
		else if ( process.equals("'rptOrder'") ) //
		  {
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
				String query =  " FROM BeanTestOrder u" +
							//" WHERE u.active = 'Y'" +
								" ORDER BY u.id DESC LIMIT 0, 20";
						
				List rptOrder_orderList = session.createQuery( query ).list();
	
	            httpSession.setAttribute("rptOrder_orderList", rptOrder_orderList );
	            
				forward = mapping.findForward("rptOrder");
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
		else if ( process.equals("'rptTicket'") ) //
		  {
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
				String query =  " FROM BeanTestOrder u" +
							//" WHERE u.active = 'Y'" +
								" ORDER BY u.id DESC LIMIT 0, 20";
						
				List rptOrder_orderList = session.createQuery( query ).list();
	
	            httpSession.setAttribute("rptOrder_orderList", rptOrder_orderList );
	            
				forward = mapping.findForward("rptTicket");
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
		else if ( process.equals("'rptResult'") ) //
		  {
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
				String query =  " FROM BeanTestOrder u" +
							//" WHERE u.active = 'Y'" +
								" ORDER BY u.id DESC LIMIT 0, 20";
						
				List rptOrder_orderList = session.createQuery( query ).list();
	
	            httpSession.setAttribute("rptOrder_orderList", rptOrder_orderList );
	            
				forward = mapping.findForward("rptResult");
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
		else if ( process.equals("'rptOrders'") ) //
		  {
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
				String query =  " FROM BeanBranch u";
						
				List rptBranchList = session.createQuery( query ).list();
	
	            httpSession.setAttribute("rptBranchList", rptBranchList );
	            
				forward = mapping.findForward("rptOrders");
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
		else if ( process.equals("'rptQuote'") ) //
		  {
			forward = mapping.findForward("rptQuote");
		  }
		else if ( process.equals("'rptQuotes'") ) //
		  {
			forward = mapping.findForward("rptQuotes");
		  }
		else if ( process.equals("'rptSales'") ) //
		  {
			forward = mapping.findForward("rptSales");
		  }
		else if ( process.equals("'rptContracts'") ) //
		  {
			forward = mapping.findForward("rptContracts");
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
