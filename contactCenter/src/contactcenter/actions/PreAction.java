package contactcenter.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.taglibs.standard.lang.jpath.expression.ListLiteral;

import contactcenter.forms.PreForm;


/**
 * @version 	1.0
 * @author 		Bituos Tools S de RL MI
 */

import java.util.*;

import com.tecunsa.*;
import com.tecunsa.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import javax.servlet.http.*;

public class PreAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		// Pasa los valores del jsp al  bean
		PreForm preForm = (PreForm) form;

		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		httpSession.removeAttribute("done" );

		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
		  forward = mapping.findForward("loginAction");

		/*preForm.getProcess().equals()
		   obtiene y compara el valor de la variable process de preForm con una cadena
		*/
		else if ( preForm.getProcess().equals("userNew") )  //nuevo usuario
		{
		    //forward userNew declarado en struts-config.xml
			try
			  {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				//Lista de usuarios activos y excluyendo el usuario que esta en sesión
				String query =  " FROM BeanUser u" +
								" WHERE u.active = 'Y'" +
								" ORDER BY u.name";

				List list = session.createQuery( query ).list();

				if ( list.size() != 0 )
					{

					  httpSession.setAttribute("userList", list );
					//forward  declarado en struts-config.xml
					   forward = mapping.findForward("userNew");
					}
				else
					{
					  com.tecunsa.Error e = new com.tecunsa.Error("Debe existir en el sistema al menos un usuario", request);
					  httpSession.setAttribute("done", "javascript:window.close();" );
					//forward  declarado en struts-config.xml
					  forward = mapping.findForward("error");
					}
			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();
				com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
									 //forward  declarado en struts-config.xml
				 forward = mapping.findForward("error");
			  }
			finally
			  {
				session.close();
				sessionFactory.close();
			  }
		}
		else if ( preForm.getProcess().equals("userDelete") ) //Eliminar usuario
			  {
				try
				  {
					//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					//Lista de usuarios activos y excluyendo el usuario que esta en sesión
					String query =      " FROM BeanUser u" +
										" WHERE u.active = 'Y'" +
										" AND u.id <> " + beanUser.getId() +
										" ORDER BY u.id";


					List list = session.createQuery( query ).list();

					if ( list.size() != 0 )
						{

						  httpSession.setAttribute("filterList", list );
						//forward  declarado en struts-config.xml
						   forward = mapping.findForward("userDelete");
						}
					else
						{
						  com.tecunsa.Error e = new com.tecunsa.Error("No existen usuarios para borrar", request);
						  httpSession.setAttribute("done", "javascript:window.close();" );
						//forward  declarado en struts-config.xml
						  forward = mapping.findForward("error");
						}
				  }
				catch( Throwable  m)
				  {
					m.printStackTrace();
					com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
                                         //forward  declarado en struts-config.xml
					 forward = mapping.findForward("error");
				  }
				finally
				  {
					session.close();
					sessionFactory.close();
				  }
			  }
		else if ( preForm.getProcess().equals("userActivate") ) //Activar usuario
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					//Lista de usuarios inactivos
					String query =    " FROM BeanUser u" +
								" WHERE u.active = 'N'" +
								" ORDER BY u.id";

					List list = session.createQuery( query ).list();

					if ( list.size() != 0 )
						{
						  httpSession.setAttribute("filterList", list );
					         //forward  declarado en struts-config.xml
						  forward = mapping.findForward("userActivate");
						}
					else
						{
						  com.tecunsa.Error e = new com.tecunsa.Error("No existen usuarios para activar", request);
						  httpSession.setAttribute("done", "javascript:window.close();" );
						 //forward  declarado en struts-config.xml
						  forward = mapping.findForward("error");
						}
				  }
				catch( Throwable  m)
				  {
					m.printStackTrace();
					com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
					//forward  declarado en struts-config.xml
					forward = mapping.findForward("error");
				  }
				finally
				  {
					session.close();
					sessionFactory.close();
				  }
			  }
		else if ( preForm.getProcess().equals("userChange") ) //Seleccionar usuario para ser modificado
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					//Lista de usuarios activos
					String query =  " FROM BeanUser u" +
									" WHERE u.active = 'Y'" +
									" ORDER BY u.id";

					List list = session.createQuery( query ).list();

					if ( list.size() != 0 )
						{
						  httpSession.setAttribute("list", list );
						//forward  declarado en struts-config.xml
						  forward = mapping.findForward("userSelect");
						}
					else
						{
						  com.tecunsa.Error e = new com.tecunsa.Error("No existen usuarios para cambiarle los datos.", request);
						  httpSession.setAttribute("done", "javascript:window.close();" );
						//forward  declarado en struts-config.xml
						  forward = mapping.findForward("error");
						}

				  }
				catch( Throwable  m)
				  {
					m.printStackTrace();
					com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
					forward = mapping.findForward("error");
				  }
				finally
				  {
					session.close();
					sessionFactory.close();
				  }
			  }
			else if ( preForm.getProcess().equals("clientNew") )  //new client
			  {
				  forward = mapping.findForward("clientNew");
			  }
			else if ( preForm.getProcess().equals("clientDelete") ) //delete user
			  {
				  try
					{
					  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					  session = sessionFactory.openSession();
	
					  String query =  " FROM BeanClient u" +
									  " WHERE u.active = 'Y'" +
									  " AND u.id NOT IN ( SELECT DISTINCT c.id_client.id" +
									  "                   FROM BeanCase c " +
									  "                 )" +
									  " ORDER BY u.id";
	
					  List list = session.createQuery( query ).list();
	
					  if ( list.size() != 0 )
						  {
							//httpSession.setAttribute("list", list );
	
							httpSession.setAttribute("filterList", list );
							forward = mapping.findForward("clientDelete");
						  }
					  else
						  {
							com.tecunsa.Error e = new com.tecunsa.Error("No existen clientes para borrar.", request);
							httpSession.setAttribute("done", "javascript:window.close();" );
							forward = mapping.findForward("error");
						  }
	
					}
				  catch( Throwable  m)
					{
					  m.printStackTrace();
					  com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
					  forward = mapping.findForward("error");
					}
				  finally
					{
					  session.close();
					  sessionFactory.close();
					}
			  }
			else if ( preForm.getProcess().equals("clientActivate") ) //activate client
			  {
				  try
					{
					  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					  session = sessionFactory.openSession();
	
					  String query =  " FROM BeanClient u" +
									  " WHERE u.active = 'N'" +
									  " ORDER BY u.id";
	
					  List list = session.createQuery( query ).list();
	
					  if ( list.size() != 0 )
						  {
							httpSession.setAttribute("filterList", list );
							forward = mapping.findForward("clientActivate");
						  }
					  else
						  {
							com.tecunsa.Error e = new com.tecunsa.Error("No existen clientes para activar.", request);
							httpSession.setAttribute("done", "javascript:window.close();" );
							forward = mapping.findForward("error");
						  }
	
					}
				  catch( Throwable  m)
					{
					  m.printStackTrace();
					  com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
					  forward = mapping.findForward("error");
					}
				  finally
					{
					  session.close();
					  sessionFactory.close();
					}
			  }
		  else if ( preForm.getProcess().equals("clientChange") ) //select client
		  {
			  try
				{
				  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				  session = sessionFactory.openSession();

				  String query =  " FROM BeanClient u" +
								  " WHERE u.active = 'Y'" +
								  " ORDER BY u.id";

				  List list = session.createQuery( query ).list();

				  if ( list.size() != 0 )
					  {
						httpSession.setAttribute("filterList", list );
						forward = mapping.findForward("clientSelect");
					  }
				  else
					  {
						com.tecunsa.Error e = new com.tecunsa.Error("No existen clientes para modificar.", request);
						httpSession.setAttribute("done", "javascript:window.close();" );
						forward = mapping.findForward("error");
					  }

				}
			  catch( Throwable  m)
				{
				  m.printStackTrace();
				  com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
				  forward = mapping.findForward("error");
				}
			  finally
				{
				  session.close();
				  sessionFactory.close();
				}
		  }
		  else if ( preForm.getProcess().equals("asignSupervisor") )  //Asignar supervisor
			{
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					//Lista de usuarios activos
					String query =  " FROM BeanUser u" +
									" WHERE u.active = 'Y'" +
									" AND u.id = u.id";

					List list = session.createQuery( query ).list();

					if ( list.size() != 0 )
						{
						  httpSession.setAttribute("filterList", list );
						//forward  declarado en struts-config.xml
						  forward = mapping.findForward("asignSupervisor");
						}
					else
						{
					 	  com.tecunsa.Error e = new com.tecunsa.Error("No existen Usuarios.", request);
						  httpSession.setAttribute("done", "javascript:window.close();" );
						//forward  declarado en struts-config.xml
						  forward = mapping.findForward("error");
						}

				  }
				catch( Throwable  m)
				  {
					m.printStackTrace();
					com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
					  //forward  declarado en struts-config.xml
					forward = mapping.findForward("error");
				  }
				finally
				  {
					session.close();
					sessionFactory.close();
				  }
			}
		  else if ( preForm.getProcess().equals("asignSalesAgent") )  //Asignar agente de ventas
			  {
				  try
					{
					  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					  session = sessionFactory.openSession();

					  //Lista de usuarios activos
					  String query =  " FROM BeanUser u" +
									  " WHERE u.active = 'Y'" +
									  " AND u.id = u.id";

					  List list = session.createQuery( query ).list();

					  if ( list.size() != 0 )
						  {
							httpSession.setAttribute("filterList", list );
						  //forward  declarado en struts-config.xml
							forward = mapping.findForward("asignSalesAgent");
						  }
					  else
						  {
							com.tecunsa.Error e = new com.tecunsa.Error("No existen Usuarios.", request);
							httpSession.setAttribute("done", "javascript:window.close();" );
						  //forward  declarado en struts-config.xml
							forward = mapping.findForward("error");
						  }
					}
				  catch( Throwable  m)
					{
					  m.printStackTrace();
					  com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
						//forward  declarado en struts-config.xml
					  forward = mapping.findForward("error");
					}
				  finally
					{
					  session.close();
					  sessionFactory.close();
					}
			  }

		  else if ( preForm.getProcess().equals("processAdmin") ) //Administracion de cuentas
			{
			   try
				 {
				   sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				   session = sessionFactory.openSession();

				   //Lista de usuarios activos excepto el que esta en sesión
				   String query =  " FROM BeanUser u" +
							   " WHERE u.active = 'Y'" +
							   " AND u.id <> " + beanUser.getId();

				   List list = session.createQuery( query ).list();

				   if ( list.size() == 0 )
					 {
					   com.tecunsa.Error e = new com.tecunsa.Error("Usted es el único usuario disponible.", request);
					   httpSession.setAttribute("done", "javascript:window.close();" );
					  //forward  declarado en struts-config.xml
					   forward = mapping.findForward("error");
					 }
				   else
					 {
					   BeanUser processAdminCurrentUser = (BeanUser) list.get(0);
					   httpSession.setAttribute("processAdmin_currentUser", processAdminCurrentUser );
					   httpSession.setAttribute("processAdmin_listUsers", list );

					   //Procesos negados
					   query = " FROM BeanProcess p" +
							   " WHERE p.id NOT IN ( SELECT pa.id_process.id" +
							   "                     FROM BeanProcessUsers pa" +
							   "                     WHERE pa.id_user.id=" + processAdminCurrentUser.getId() +
							   "                   )" +
							   " AND p.id NOT IN (61,62,63, 64, 101)";

					   list = session.createQuery( query ).list();
					   httpSession.setAttribute("processAdmin_listDeny", list );

					   //Procesos permitidos
					   query = " FROM BeanProcess p" +
							   " WHERE p.id IN ( SELECT pa.id_process.id" +
							   "                 FROM BeanProcessUsers pa" +
							   "                 WHERE pa.id_user.id=" + processAdminCurrentUser.getId() +
							   "               )";

					   list = session.createQuery( query ).list();
					   httpSession.setAttribute("processAdmin_listAllow", list );

					   //Perfiles
					   query = " FROM BeanProfile p" +
							   " WHERE p.id = p.id";
					   list = session.createQuery( query ).list();
					   httpSession.setAttribute("processAdmin_profileList", list );

					   BeanProfile processAdminCurrentProfile = (BeanProfile) list.get(0);
					   httpSession.setAttribute("processAdmin_currentProfile", processAdminCurrentProfile );

					   //Procesos asgignados al perfil  actual
					   query = " FROM BeanProcess p" +
							   " WHERE p.id IN ( SELECT pp.id_process.id" +
							   "                 FROM BeanProcessProfile pp" +
							   "                 WHERE pp.id_profile.id = " + processAdminCurrentProfile.getId() +
							   "               )";
					   list = session.createQuery( query ).list();
					   httpSession.setAttribute("processAdmin_profileProcessAsignedList", list );


					   //Procesos NO asgignados al perfil  actual
					   query = " FROM BeanProcess p" +
							   " WHERE p.id NOT IN ( SELECT pp.id_process.id" +
							   "                     FROM BeanProcessProfile pp" +
							   "                     WHERE pp.id_profile.id = " + processAdminCurrentProfile.getId() +
							   "                   )";
					   list = session.createQuery( query ).list();
					   httpSession.setAttribute("processAdmin_profileProcessDenyList", list );


					   //users vs profiles


					   //Denegar Perfiles
					   query = " FROM BeanProfile p" +
							   " WHERE p.id NOT IN ( SELECT up.id_profile.id" +
							   "                     FROM BeanUserProfile up" +
							   "                     WHERE up.id_user.id=" + processAdminCurrentUser.getId() +
							   "                   )";


					   list = session.createQuery( query ).list();
					   httpSession.setAttribute("processAdmin_profileListDeny", list );


					   //Permitir perfilles
					   query = " FROM BeanProfile p" +
							   " WHERE p.id IN ( SELECT up.id_profile.id" +
							   "                 FROM BeanUserProfile up" +
							   "                 WHERE up.id_user.id=" + processAdminCurrentUser.getId() +
							   "               )";

					   list = session.createQuery( query ).list();
					   httpSession.setAttribute("processAdmin_profileListAllow", list );
					  //forward  declarado en struts-config.xml
					   forward = mapping.findForward("processAdmin");
					 }
				 }
			   catch( Throwable  m)
				 {
				   m.printStackTrace();
				   com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
				   forward = mapping.findForward("error");
				 }
			   finally
				 {
				   session.close();
				   sessionFactory.close();
				 }
			 }
		  else if ( preForm.getProcess().equals("typeCaseNew") )  //tipo de caso nuevo
			{
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					//lista los tipo de casos
					String query =  " FROM BeanTypeCase u" +
									" WHERE u.id = u.id" +
									" AND u.id NOT IN ( SELECT c.id_type_case.id" +
									"					FROM BeanCase c" +
									"                  )" +
									" ORDER BY u.days";

					List list = session.createQuery( query ).list();

					httpSession.setAttribute("typeslist", list );
					httpSession.setAttribute("visibleUserData","no");
					httpSession.setAttribute("visibleCase","no");
					httpSession.removeAttribute("client" );
					//forward  declarado en struts-config.xml
					forward = mapping.findForward("typeCaseNew");
				 }
				catch( Throwable  m)
				  {
					m.printStackTrace();
					com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
					//forward  declarado en struts-config.xml
					forward = mapping.findForward("error");
				  }
				finally
				  {
					session.close();
					sessionFactory.close();
				  }

		    }

  		  else if ( preForm.getProcess().equals("caseNew") )  //Caso nuevo
		  {
			  try
				{
				  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				  session = sessionFactory.openSession();

				  //Lista de clientes activos
				 String query =  " FROM BeanClient u" +
								 " WHERE u.active = 'Y'" +  
								 " ORDER BY u.id";

				  List list = session.createQuery( query ).list();

				  if ( list.size() != 0 )
					  {
						query = " FROM BeanClient u" +
								" WHERE u.active = 'Y'" +  
								" AND u.id IN ( SELECT c.id_client.id" +  
								"               FROM BeanCase c" +  
								"               ORDER BY c.date_reg" +  
								"              )" +  
								" ORDER BY u.id";
						list = session.createQuery( query ).list();

						//	los 6 primeros
						if ( list.size() > 6)
						  list = list.subList(0, 6);
						
						httpSession.setAttribute("filterList", list );

						//Lista de tipos de casos
						query =  " FROM BeanTypeCase u" +
							     " ORDER BY ";

						list = session.createQuery( query ).list();
						httpSession.setAttribute("typeCaseList", list );
						httpSession.setAttribute("visibleCase","no");
						httpSession.setAttribute("visibleUserData","no");

						httpSession.removeAttribute("clientName");
						
						  //forward  declarado en struts-config.xml
						forward = mapping.findForward("caseNew");
					  }
				  else
					  {
						com.tecunsa.Error e = new com.tecunsa.Error("No existen clientes para modificar.", request);
						httpSession.setAttribute("done", "javascript:window.close();" );
						  //forward  declarado en struts-config.xml
						forward = mapping.findForward("error");
					  }

				}
			  catch( Throwable  m)
				{
				  m.printStackTrace();
				  com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
				//forward  declarado en struts-config.xml
				  forward = mapping.findForward("error");
				}
			  finally
				{
				  session.close();
				  sessionFactory.close();
				}
		  }
		  else if ( preForm.getProcess().equals("caseResolve") )  //Resolver caso
		  {
				try
				{
				  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				  session = sessionFactory.openSession();

				  //Lista de casos asignados a al supervisor
				  String query =  " FROM BeanCase u" +
								  " WHERE u.id_user.id = " + beanUser.getId() +
								  " AND u.id_type_case_status.id = 1"+
								//" ORDER BY u.id_type_case.days, u.date_last_change";
								" ORDER BY u.id";

				  List list = session.createQuery( query ).list();

				  if ( list.size() != 0 )
					  {
						httpSession.setAttribute("casesList", list );

						 //Crea una lista vacia de notas de casos
						query =  " FROM BeanCaseNotes u" +
								 " WHERE u.id = 98989898989898989898" +
								 " ORDER BY u.id";

						list = session.createQuery( query ).list();
						httpSession.setAttribute("notesList", list );

						//lista los tipo de casos
						query = " FROM BeanTypeCase u" +
								" ORDER BY u.days";

						list = session.createQuery( query ).list();

						httpSession.setAttribute("typeCaseList", list );


						//lista los tipo de orden de los casos
						query = " FROM BeanTypeCaseOrder u";

						list = session.createQuery( query ).list();

						httpSession.setAttribute("typeCaseOrderList", list );

						//Crea una lista vacia de files 
					   query =  " FROM BeanFiles u" +
								" WHERE u.id = 98989898989898989898" +
								" ORDER BY u.id";

					   list = session.createQuery( query ).list();
					   httpSession.setAttribute("fileList", list );


					   httpSession.setAttribute("id_caseSelected","");
					   httpSession.setAttribute("id_orderSelected","");

						query = " FROM BeanUser u" +
								" WHERE u.active = 'Y'" +
								" AND u.id <> " + beanUser.getId() +
								" ORDER BY u.name";


						list = session.createQuery( query ).list();
						httpSession.setAttribute("filterList", list );

						BeanControlPanel control_panel = (BeanControlPanel) session.load( BeanControlPanel.class, new Integer( 1 ) );
						httpSession.setAttribute("control_panel", control_panel );

						  //forward  declarado en struts-config.xml
						forward = mapping.findForward("caseResolve");
					  }
				  else
					  {
						com.tecunsa.Error e = new com.tecunsa.Error("No existen mas casos para resolver.", request);
						httpSession.setAttribute("done", "javascript:window.close();" );
						  //forward  declarado en struts-config.xml
						forward = mapping.findForward("error");
					  }

				}
			  catch( Throwable  m)
				{
				  m.printStackTrace();
				  com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
					//forward  declarado en struts-config.xml
				  forward = mapping.findForward("error");
				}
			  finally
				{
				  session.close();
				  sessionFactory.close();
				}

		  }
		  else if ( preForm.getProcess().equals("caseClose") ) //cerrar caso
			{
			  try
				{
				  httpSession.removeAttribute( "showCaseData" );
				  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				  session = sessionFactory.openSession();

				 //Lista  de casos
				  String query =  " FROM BeanCase u" +
								  " WHERE u.id_user_capture = " + beanUser.getId() +
								  " AND u.id_type_case_status = 2" +
								  " ORDER BY u.id_type_case, u.date_reg";

				  List list = session.createQuery( query ).list();

				  if ( list.size() != 0 )
					  {
						httpSession.setAttribute("list", list );
						httpSession.setAttribute("visible","no");
						  //forward  declarado en struts-config.xml
						forward = mapping.findForward("caseClose");
					  }
				  else
					  {
						com.tecunsa.Error e = new com.tecunsa.Error("No existen casos para cerrar.", request);
						httpSession.setAttribute("done", "javascript:window.close();" );
						  //forward  declarado en struts-config.xml
						forward = mapping.findForward("error");
					  }

				}
			  catch( Throwable  m)
				{
				  m.printStackTrace();
				  com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
					//forward  declarado en struts-config.xml
				  forward = mapping.findForward("error");
				}
			  finally
				{
				  session.close();
				  sessionFactory.close();
				}
			}
			////////////////////////////

			/////////////////////////////////////////////////////////////////////////////////


			else if ( preForm.getProcess().equals("messageNew") ) //message New
				{
					try
					  {
						sessionFactory = config.getConfiguration( request ).buildSessionFactory();
						session = sessionFactory.openSession();

						//Lista  de casos de usuarios activos
						String query =  	" FROM BeanUser u" +
										" WHERE u.active = 'Y'" +
										" ORDER BY u.name";

						List list = session.createQuery( query ).list();

						if ( list.size() != 0 )
							{
							  httpSession.setAttribute("listUser", list );
							//forward  declarado en struts-config.xml
							  forward = mapping.findForward("messageNew");
							}
						else
							{
							  com.tecunsa.Error e = new com.tecunsa.Error("No existe usuarios a los cuales enviarle un msg.", request);
							  httpSession.setAttribute("done", "javascript:window.close();" );
							//forward  declarado en struts-config.xml
							  forward = mapping.findForward("error");
							}

					  }
					catch( Throwable  m)
					  {
						m.printStackTrace();
						com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
						//forward  declarado en struts-config.xml
						forward = mapping.findForward("error");
					  }
					finally
					  {
						session.close();
						sessionFactory.close();
					  }
				}
				else if ( preForm.getProcess().equals("messageView") ) //message View
				{
					try
					  {
						sessionFactory = config.getConfiguration( request ).buildSessionFactory();
						session = sessionFactory.openSession();

						//Lista  de mensajes
						String query =         " FROM BeanMessage u" +
										" WHERE u.status IN ('N', 'R')" +
										" AND u.id_user_to.id = " + beanUser.getId() +
										" ORDER BY u.status, u.date_reg";

						List list = session.createQuery( query ).list();

						if ( list.size() != 0 )
							{
							  httpSession.setAttribute("listMessage", list );
							 //forward  declarado en struts-config.xml
							  forward = mapping.findForward("messageView");
							}
						else
							{
							  com.tecunsa.Error e = new com.tecunsa.Error("No existe mensajes para Ud.", request);
							  httpSession.setAttribute("done", "javascript:window.close();" );
							//forward  declarado en struts-config.xml
							  forward = mapping.findForward("error");
							}

					  }
					catch( Throwable  m)
					  {
						m.printStackTrace();
						com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
						//forward  declarado en struts-config.xml
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
				com.tecunsa.Error e = new com.tecunsa.Error("Opcion no programada. Consulte a soporte técnico.", request);
				httpSession.setAttribute("done", "javascript:window.close();");
				//forward  declarado en struts-config.xml
				forward = mapping.findForward("error");
 		  	}


		//Si un mensage es requerido, guarda la clave especifica
	        //en el request para ser usado por la etiqueta <struts:errors>

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}


		// Termina con
		return (forward);

	}
}
