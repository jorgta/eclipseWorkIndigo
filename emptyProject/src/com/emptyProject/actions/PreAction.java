package com.emptyProject.actions;

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

import com.emptyProject.Beans.*;
import com.emptyProject.forms.*;

import java.util.*;

import com.emptyProject.*;
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
			   sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			   session = sessionFactory.openSession();
	
			   String query =   " FROM BeanUser u" +
								" WHERE u.id <> " + beanUser.getId() + 
				  				" ORDER BY u.name";
							
			   List list = session.createQuery( query ).list();
			   
				/*attention
				information
				success
				error*/
			   String notification;
			   if ( !list.isEmpty() )				   
				{
				   query =   "SELECT COUNT(*) FROM BeanUser u";
				   list = session.createQuery( query ).list();
				   Integer count = (Integer)list.get(0); 
				   
				   int rowsPerPage = 10;  //manejar modulo de configuracion
				   int pagesTemp = (int)count/(int)rowsPerPage;
				   float pages = (float)count/(float)rowsPerPage;
			
				   if (pages > 1)
				     {
				       pages = pages - pagesTemp;
				       if (pages > (float)0.1)
					     pagesTemp = pagesTemp + 1;
				       
				     }
				   else
					   pagesTemp = 1;  

				   
				  List pagesList = new ArrayList<Integer>();
				  
				  for(int i = 1; i <= pagesTemp; i++)
					  pagesList.add(i);
				  
				  
				  String pageCurrent ="1";
				  httpSession.setAttribute("pageCurrent", pageCurrent );
				  httpSession.setAttribute("pagesList", pagesList );			  
				  

				  httpSession.setAttribute("pageLast", String.valueOf(pagesTemp) );
				  
				  query =   " FROM BeanUser u" +
							" WHERE u.id <> " + beanUser.getId() + 
			  				" ORDER BY id ASC LIMIT 0, "+ rowsPerPage+ ";";

				  list = session.createQuery( query ).list();
				  
				  
				  httpSession.setAttribute("listUser", list );	
				  
				  httpSession.setAttribute("rowsPerPage", String.valueOf( rowsPerPage ) );
				  
		          list = new ArrayList<BeanUser>();
		          httpSession.setAttribute("listUserTemp", list );	
		          
		          httpSession.setAttribute("defaultTab", "#tab1"  );	//manejar modulo de configuracion
		          
		          httpSession.setAttribute("foundErrors", true ); //false
				  
				  notification= "success";
				  httpSession.setAttribute("notification", notification );
				  com.bituos.Notification e = new com.bituos.Notification("Consulta Procesada exitosamente", request);
				}
			  else
				{
				  notification= "information";
				  httpSession.setAttribute("notification", notification );
				  com.bituos.Notification e = new com.bituos.Notification("No existen usuarios creados", request);				  
				}
			
			   forward = mapping.findForward("userNew");
			}
		else if ( process.equals("'userDelete'") ) //delete user
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();
	
					String query =  " FROM BeanUser u" +
									" WHERE u.active = 'Y'" +
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
									" WHERE  u.active = 'N'" +
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
									" WHERE  u.active = 'Y'" +
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
									" WHERE  u.active = 'Y'" +
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
									" WHERE  u.active = 'N'" +
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
		
		else if ( process.equals("'holderNew'") )  //new client
		{
		
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				String query =  " FROM BeanCountry u" +
								" ORDER BY u.country";

				List list = session.createQuery( query ).list();
				httpSession.setAttribute("listCountry", list );
				
				
				query =  " FROM BeanStates u" +
						 " ORDER BY u.name";

				list = session.createQuery( query ).list();
				httpSession.setAttribute("listStates", list );


				forward = mapping.findForward("holderNew");

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
		else if ( process.equals("'agreementNew'") )  //new client
		{
		
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
				
				

				String query =  " FROM BeanAccount u";								

				List list = session.createQuery( query ).list();
				
			
				
			    httpSession.setAttribute("listAccount", list );
			 
			    

				forward = mapping.findForward("agreementNew");

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
		else if ( process.equals("'closeMarketTitleNew'") )  //new client
		{
		
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				String query =  " FROM BeanTypeTransaction u" +
								" ORDER BY u.description";

				List list = session.createQuery( query ).list();
				httpSession.setAttribute("listTypeTransaction", list );
				
				
				query =   " FROM BeanHolder u" +
						  " WHERE u.active = 'Y'" +
						  " ORDER BY u.id";

				list = session.createQuery( query ).list();
				httpSession.setAttribute("listBeanHolder", list );
				
				httpSession.removeAttribute( "currentHolder" );
				
				httpSession.setAttribute( "id_holder","0");
				
				httpSession.setAttribute("idTypeTransaction", "1" );
				
				
				query =   " FROM BeanTitle u";

				list = session.createQuery( query ).list();
				httpSession.setAttribute("listBeanTitle", list );
				
				//httpSession.setAttribute("searchDone", "false" );
			//	httpSession.setAttribute("sellDone", "false" );
				
				
			
				forward = mapping.findForward("closeMarketTitleNew");

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
		else if ( preForm.getProcess().equals("'quoteToSale'") )  //Cotizacion a venta
		  {
			  forward = mapping.findForward("quoteToSale");
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
