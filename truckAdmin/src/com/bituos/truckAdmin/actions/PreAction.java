package com.bituos.truckAdmin.actions;

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

import com.bituos.truckAdmin.Beans.*;
import com.bituos.truckAdmin.forms.*;

import java.util.*;

import com.bituos.truckAdmin.*;
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
				
				companyChangeByUserForm.setAdditional1(bean.getAdditional1());
				companyChangeByUserForm.setAdditional2(bean.getAdditional2());
				companyChangeByUserForm.setAdditional3(bean.getAdditional3());
				companyChangeByUserForm.setAdditional4(bean.getAdditional4());
				companyChangeByUserForm.setAdditional5(bean.getAdditional5());
				companyChangeByUserForm.setAdditional6(bean.getAdditional6());
				companyChangeByUserForm.setAdditional7(bean.getAdditional7());
				companyChangeByUserForm.setAdditional8(bean.getAdditional8());
				companyChangeByUserForm.setAdditional9(bean.getAdditional9());
				companyChangeByUserForm.setAdditional10(bean.getAdditional10());
				
		
				
				companyChangeByUserForm.setContractSigner(bean.getContractSigner());
				companyChangeByUserForm.setPhones(bean.getPhones());
				companyChangeByUserForm.setStreetContract(bean.getStreetContract());
				companyChangeByUserForm.setColonyContract(bean.getColonyContract());
				companyChangeByUserForm.setCityContract(bean.getCityContract());
			    companyChangeByUserForm.setStateContract(bean.getStateContract());


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
		else if ( process.equals("'paymentNew'") ) //select company
		  {
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				String query =   " FROM BeanSale u" +
					       	     " WHERE u.id_company = " + beanUser.getId_company().getId() +
					 			 " ORDER BY u.id DESC LIMIT 0, 8";
		
				List list = session.createQuery( query ).list();

				if ( list.size() != 0 )
					{
								
					  httpSession.setAttribute("listBeanSale", list );

			          
			         /* List paymentsRecordList = new ArrayList<PaymentsRecord>();
			          httpSession.setAttribute("paymentsRecordList", paymentsRecordList );*/
			          
			          
			          httpSession.setAttribute("total", "0.0" );
			          
			          httpSession.removeAttribute("currentSale");

					  forward = mapping.findForward("paymentNew");
					}
				else
					{
					  com.bituos.Error e = new com.bituos.Error("Error interno consulte a soprte tecnico.", request);
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
		else if ( process.equals("'paymentCanceled'") ) //select company
		  {
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				String query =   " FROM BeanSale u" +
					       	     " WHERE u.id_company = " + beanUser.getId_company().getId() +
					 			 " ORDER BY u.id DESC LIMIT 0, 8";
		
				List list = session.createQuery( query ).list();

				if ( list.size() != 0 )
					{
					  
					  httpSession.setAttribute("listBeanSale", list );

			          
			         /* List paymentsRecordList = new ArrayList<PaymentsRecord>();
			          httpSession.setAttribute("paymentsRecordList", paymentsRecordList );*/
			          
			          
			          httpSession.setAttribute("total", "0.0" );
			          
			          httpSession.removeAttribute("currentSale");
					
					  httpSession.setAttribute("listBeanSale", list );

					  forward = mapping.findForward("paymentCanceled");
					}
				else
					{
					  com.bituos.Error e = new com.bituos.Error("No existen pago para cambiarle los datos.", request);
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
		else if ( process.equals("'truckNew'") )  //new user
		{
		  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
		  session = sessionFactory.openSession();
		
		  String query = " FROM BeanTruckTireConfiguration";
		  List list = session.createQuery( query ).list();
		 			  
		  list = session.createQuery( query ).list();
		  
		  if(!list.isEmpty())
		    {		
			  httpSession.removeAttribute("beanTruckTireConfiguration");
			  httpSession.setAttribute("listTruckTireConfiguration", list );
			  forward = mapping.findForward("truckNew");
		
		    }
		  else
		   {
			  httpSession.removeAttribute("beanTruckTireConfiguration");
			  list = new ArrayList<BeanTruckTireConfiguration>();
			  httpSession.setAttribute("listTruckTireConfiguration", list );
			  forward = mapping.findForward("truckNew");
		   }
			
			
			
		}
		else if ( process.equals("'selectVehicle'") )  //new user
		{
			try
			{
			  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			  session = sessionFactory.openSession();
			  String query = " FROM BeanTruck u" +
			  				 " WHERE u.active='Y'";

			  List list = session.createQuery( query ).list();
			  if(!list.isEmpty())
			    {	
				  httpSession.removeAttribute("listTruckEditOrDelete");
				  httpSession.setAttribute("listTruckEditOrDelete", list );
			      
				  query = " FROM BeanTruckTireConfiguration";  
		
				  list = session.createQuery(query).list();
		
				  if ( !list.isEmpty() )
				    {
					  httpSession.removeAttribute("listTruckTireConfiguration");
					  httpSession.setAttribute("listTruckTireConfiguration", list );
				    }
				  else
					{
					  list = new ArrayList<BeanTruckTireConfiguration>();	
					  httpSession.setAttribute("listTruckTireConfiguration", list );
					}
				
				  
				 
			      forward = mapping.findForward("selectVehicle");
			    }
			  else
			    {
				  com.bituos.Error e = new com.bituos.Error("No hay Vehículos registrados.", request);
				  httpSession.setAttribute("done", "main.jsp" );

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
		else if ( process.equals("'tireNew'") )  //new user
		{
			try
			{
			  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			  session = sessionFactory.openSession();
			  
			  String query = "  FROM BeanTypeTireStatus";
			  List list = session.createQuery( query ).list();
			  httpSession.setAttribute("listTypeTireStatus", list );
			  
			  String ctsString="2";//Nueva
			  httpSession.setAttribute("ctsString", ctsString );
			  
			  query = " FROM BeanTypePlace";
			  list = session.createQuery( query ).list();
			  httpSession.setAttribute("listTypePlace", list );	
			  
			  String ctpString="1";
			  httpSession.setAttribute("ctpString", ctpString );
			  
			  query = " FROM BeanTypeMeasure";
			  list = session.createQuery( query ).list();
			  httpSession.setAttribute("listTypeMeasure", list );
			  
			  query = " FROM BeanTypeUnitOfMeasure";
			  list = session.createQuery( query ).list();
			  httpSession.setAttribute("listUnitOfMeasure", list );
			  
			  
			  String ctmString="1";  
			  httpSession.setAttribute("ctmString", ctmString );
			  
			  
			  httpSession.removeAttribute("selectedTruck");
			  
			  list = new ArrayList<BeanTruckTireConfigurationDetail>();
	          httpSession.setAttribute("truckTireDetailList", list );
			  
			  query = " FROM BeanTruck u" +
			  		  " WHERE u.active ='Y'";
			  list = session.createQuery( query ).list();
			  if(!list.isEmpty())
			    {			  
				   httpSession.setAttribute("listTruck", list );
				   
				   String cttString = "Sin selección";
				   httpSession.setAttribute("cttString", cttString );
				   
				   
				   forward = mapping.findForward("tireNew");
			
			    }
			  else
			   {
				  com.bituos.Error e = new com.bituos.Error("No hay Vehículos registrados.", request);
				  httpSession.setAttribute("done", "main.jsp" );

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
		else if ( process.equals("'tireDelete'") )
		{
			try
			{
			  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			  session = sessionFactory.openSession();
			  
			  String query = "  FROM BeanTire u" +
				             "  WHERE u.active= 'Y'";
			  List list = session.createQuery( query ).list();
			 
			  
			  if(!list.isEmpty())
			    {			  
				  httpSession.setAttribute("listTireDelete", list );
				  forward = mapping.findForward("tireDelete");
			
			    }
			  else
			   {
				  com.bituos.Error e = new com.bituos.Error("No hay Neumático registrados.", request);
				  httpSession.setAttribute("done", "main.jsp" );

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
		else if ( process.equals("'tireMovements'") )  //new user
		{
			try
			{
		

			  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			  session = sessionFactory.openSession();
			  
			  
			  
			  String query = "";
			  List list = null;
			// nehumaticos 
			  query = "  FROM BeanTire u" +
				      "  WHERE u.active = 'Y'";	  
			  
			  
			  
			  list = session.createQuery( query ).list();
			  httpSession.setAttribute("beanTireList", list );

			  
			  
			  
			  query = "  FROM BeanTypeTireStatus";			  
			  list = session.createQuery( query ).list();
			  httpSession.setAttribute("listTypeTireStatus", list );
			  
			  String ctsString="2";//Nueva
			  httpSession.setAttribute("ctsString", ctsString );
			  
			  query = " FROM BeanTypePlace u" +
			  		  " WHERE u.id <> 7";
			  list = session.createQuery( query ).list();
			  httpSession.setAttribute("listTypePlace", list );	
			  
			  String ctpString="1";
			  httpSession.setAttribute("ctpString", ctpString );
			  
			  query = " FROM BeanTypeMeasure";
			  list = session.createQuery( query ).list();
			  httpSession.setAttribute("listTypeMeasure", list );
			  
			  query = " FROM BeanTypeUnitOfMeasure";
			  list = session.createQuery( query ).list();
			  httpSession.setAttribute("listUnitOfMeasure", list );
			  
			  
			  String ctmString="1";  
			  httpSession.setAttribute("ctmString", ctmString );
			  
			  
			  
			  httpSession.removeAttribute("beanTruckList");
			  httpSession.removeAttribute("selectedTruck");
			  
			  httpSession.removeAttribute("selectedBeanTire");
			  
			  list = new ArrayList<BeanTruckTireConfigurationDetail>();
	          httpSession.setAttribute("truckTireDetailList", list );
	          
	          httpSession.removeAttribute("selectedBeanTire");
	          
	          httpSession.removeAttribute("truckHtml");
	          forward = mapping.findForward("tireMovements");
	          
	          
			  
			 /* query = " FROM BeanTruck";
			  list = session.createQuery( query ).list();
			  if(!list.isEmpty())
			    {			  
				   httpSession.setAttribute("listTruck", list );
				   
				   String cttString = "Sin selección";
				   httpSession.setAttribute("cttString", cttString );
				   
				   
				   forward = mapping.findForward("tireMovements");
			
			    }
			  else
			   {
				  com.bituos.Error e = new com.bituos.Error("No hay Vehículos registrados.", request);
				  httpSession.setAttribute("done", "main.jsp" );

				  forward = mapping.findForward("error");
			   }*/
			  
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
		else if ( process.equals("'eventNew'") )
		{
			try
			{
			  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			  session = sessionFactory.openSession();
			  			  
			  String query = " FROM BeanTypePlace";
			  List list = session.createQuery( query ).list();
			  httpSession.setAttribute("listTypePlace", list );
			  
			 
			  String ctpString="1";
			  httpSession.setAttribute("ctpString", ctpString );
			  
			  String ctTireString = "Sin selección";
			  httpSession.setAttribute("ctTireString", ctTireString );
			  
			  
			  query = " FROM BeanTypeEvent";
			  list = session.createQuery( query ).list();
			  httpSession.setAttribute("listTypeEvent", list );
			  
			  
			  String cteString="1";
			  httpSession.setAttribute("cteString", cteString );
			  
			  query = "  FROM BeanTire u" +
	                  "  WHERE u.active= 'Y'";
			  
			  list = session.createQuery( query ).list();
			  
			  if(!list.isEmpty())
			    {			  
				  httpSession.setAttribute("listTireForEvent", list );
				  forward = mapping.findForward("eventNew");
			
			    }
			  else
			   {
				  com.bituos.Error e = new com.bituos.Error("No hay Neumático registrados.", request);
				  httpSession.setAttribute("done", "main.jsp" );

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
		else if ( process.equals("'truckChange'") )  //new user
		{
			try
			{
			  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			  session = sessionFactory.openSession();
			  String query = " FROM BeanTruck u";

			  List list = session.createQuery( query ).list();
			  if(!list.isEmpty())
			    {			  
			      httpSession.setAttribute("listTruckEdit", list );
			      forward = mapping.findForward("truckChange");
			    }
			  else
			    {
				  com.bituos.Error e = new com.bituos.Error("No hay Vehículos para cambiarle los datos.", request);
				  httpSession.setAttribute("done", "main.jsp" );

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
		else if ( process.equals("'controlPanel'") )  //new user
		{
		
			try
			{
			  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			  session = sessionFactory.openSession();
			  			  
			  String query = " FROM BeanTruckTireConfiguration";
			  List list = session.createQuery( query ).list();
			 			  
			  list = session.createQuery( query ).list();
			  
			  if(!list.isEmpty())
			    {			  
				  httpSession.setAttribute("listTruckTireConfiguration", list );
				  forward = mapping.findForward("controlPanel");
			
			    }
			  else
			   {
				  list = new ArrayList<BeanTruckTireConfiguration>();
				  httpSession.setAttribute("listTruckTireConfiguration", list );
				  forward = mapping.findForward("controlPanel");
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
		else if ( process.equals("'truckTireConfigurationNew'") )  //new user
		{
		
			try
			{
			  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			  session = sessionFactory.openSession();
			  			  
			 
			  httpSession.removeAttribute("htmlTable");
			  httpSession.removeAttribute("truckTireConfigurationDetailList");
			  httpSession.removeAttribute("beanTruckTireConfiguration");
			  List list = new ArrayList<BeanTruckTireConfigurationDetail>();
			  httpSession.setAttribute("truckTireConfigurationDetailList", list);
			  httpSession.setAttribute("axis_number", 0);
			  
			  String query = " FROM BeanTireQuantityAxis";
			  list = session.createQuery( query ).list();
			  httpSession.setAttribute("listTireQuantityAxis", list );
			  
			  
			  httpSession.setAttribute("axisNumberSet", false);
			  httpSession.setAttribute("save", false);
			 
			  
			 // BeanTruckTireConfiguration beanTruckTireConfiguration = new BeanTruckTireConfiguration();
			  //httpSession.setAttribute("beanTruckTireConfiguration", beanTruckTireConfiguration);
			  
			  
			  forward = mapping.findForward("truckTireConfigurationNew");
			   
			  
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
