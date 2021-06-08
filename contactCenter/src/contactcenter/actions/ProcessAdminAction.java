package contactcenter.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import contactcenter.forms.ProcessAdminForm;


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

public class ProcessAdminAction extends Action {

	public ActionForward execute(
	ActionMapping mapping,
	ActionForm form,
	HttpServletRequest request,
	HttpServletResponse response)
	throws Exception {

	ActionErrors errors = new ActionErrors();
	ActionForward forward = new ActionForward();
	//Recibe y retorna valores
	ProcessAdminForm processAdminForm = (ProcessAdminForm) form;

	HttpSession httpSession = request.getSession();

	Config config = new Config( request );

	SessionFactory sessionFactory = null;
	Session    session = null;
	Transaction tx = null;

	httpSession.removeAttribute("done" );

	//Usuario que se logeo y que esta en sesión
	BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

	if ( beanUser == null )
	  forward = mapping.findForward("login");
	else if ( processAdminForm.getAction().equals("userAllow") )  //allow process
		{
			try
			  {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				/*casetNewForm.getProcess().equals()
				    obtiene  el valor de la variable process y compara con una cadena
				*/

				if (  processAdminForm.getProcess() != null )
				  {
					//Obtiene un objetos de la BD con el valor que le pasa processAdminForm
					BeanProcess beanProcess = (BeanProcess) session.load(BeanProcess.class, new Integer(processAdminForm.getProcess()) );
					BeanUser beanCurrentUser = (BeanUser) session.load(BeanUser.class, new Integer(processAdminForm.getUser()) );

					//Objeto nuevo
					BeanProcessUsers bean = new BeanProcessUsers();

					//Lista los procesos del el usuario actual
					String query =  " FROM BeanProcessUsers u" +
								" WHERE u.id_process.id = " + beanProcess.getId() +
								" AND u.id_user.id =" + beanCurrentUser.getId();

					// Do exist that permit ?
					if ( session.createQuery( query).list().size() == 0 )
					  {
						bean.setId_process(beanProcess);
						bean.setId_user(beanCurrentUser);

						//Guarda el objeto en la BD
						tx=session.beginTransaction();
						session.save( bean );
						tx.commit();

						httpSession.setAttribute("processAdmin_currentUser", beanCurrentUser );

						//Actualiza la lista de usuarios
						query =    " FROM BeanUser u" +
								" WHERE u.active = 'Y'" +
								" AND u.id <> " + beanUser.getId();

						List list = session.createQuery( query ).list();

						httpSession.setAttribute("processAdmin_listUsers", list );

						//Actualiza la lista de procesos que no tiene el usuario
						query = " FROM BeanProcess p" +
								" WHERE p.id NOT IN ( SELECT pa.id_process.id" +
								"                     FROM BeanProcessUsers pa" +
								"                     WHERE pa.id_user.id=" + beanCurrentUser.getId() +
								"                   )"; // +
								//" AND p.id NOT IN (61,62,63,64,75,101)";

						list = session.createQuery( query ).list();
						httpSession.setAttribute("processAdmin_listDeny", list );

						//Actualiza la lista de procesos que  tiene el usuario
						query = " FROM BeanProcess p" +
								" WHERE p.id IN ( SELECT pa.id_process.id" +
								"                 FROM BeanProcessUsers pa" +
								"                 WHERE pa.id_user.id=" + beanCurrentUser.getId() +
								"               )";

						list = session.createQuery( query ).list();
						httpSession.setAttribute("processAdmin_listAllow", list );
					  }

				  }


				httpSession.setAttribute("done", new String("admin/processAdmin.jsp"));
				  //forward declarado en struts-config.xml
				forward = mapping.findForward("loop");
			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");

				com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
				//forward declarado en struts-config.xml
				forward = mapping.findForward("error");
			  }
			finally
			  {
				session.close();
				sessionFactory.close();
			  }
		}
	else if ( processAdminForm.getAction().equals("userDeny") ) //deny process
		  {
			try
			  {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();


				if (  processAdminForm.getProcess() != null )
				  {
					//Obtiene un objetos de la BD con el valor que le pasa processAdminForm
					BeanUser beanCurrentUser = (BeanUser) session.load(BeanUser.class, new Integer(processAdminForm.getUser()) );

					//Listado de procesos del usuario actual
					String query =   " FROM BeanProcessUsers pu" +
								" WHERE pu.id_user = " + beanCurrentUser.getId() +
								" AND pu.id_process = " + processAdminForm.getProcess();

					List list = session.createQuery( query ).list();

					if ( list.size() > 0 )
					  {
						//Obtiene el primer elemento de la lista
						BeanProcessUsers bean = (BeanProcessUsers) list.get(0);

						//Elimina el objeto de la BD
						tx=session.beginTransaction();
						session.delete( bean );
						tx.commit();

						httpSession.setAttribute("processAdmin_currentUser", beanCurrentUser );

						//Actualiza la lista de usuarios
						query =     " FROM BeanUser u" +
								 " WHERE u.active = 'Y'" +
								 " AND u.id <> " + beanUser.getId();

						list = session.createQuery( query ).list();

						httpSession.setAttribute("processAdmin_listUsers", list );

						//Actualiza la lista de porcesos que no tiene el usuario
						query = " FROM BeanProcess p" +
								" WHERE p.id NOT IN ( SELECT pa.id_process.id" +
								"                     FROM BeanProcessUsers pa" +
								"                     WHERE pa.id_user.id=" + beanCurrentUser.getId() +
								"                   )";// +
								//" AND p.id NOT IN (61,62,63,64,75,101)";

						list = session.createQuery( query ).list();
						httpSession.setAttribute("processAdmin_listDeny", list );

						//Actualiza la lista de porcesos que tiene el usuario
						query = " FROM BeanProcess p" +
								" WHERE p.id IN ( SELECT pa.id_process.id" +
								"                 FROM BeanProcessUsers pa" +
								"                 WHERE pa.id_user.id=" + beanCurrentUser.getId() +
								"               )";

						list = session.createQuery( query ).list();
						httpSession.setAttribute("processAdmin_listAllow", list );
					  }

				  }

				forward = mapping.findForward("loop");
			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");

				com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
				//forward declarado en struts-config.xml
				forward = mapping.findForward("error");
			  }
			finally
			  {
				session.close();
				sessionFactory.close();
			  }
		  }
	else if ( processAdminForm.getAction().equals("userChange") ) //change user
		  {
			try
			  {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				//Lista los usuarios
				String query =  " FROM BeanUser u" +
							" WHERE u.active = 'Y'" +
							" AND u.id <> " + beanUser.getId();

				List list = session.createQuery( query ).list();

				if ( list.size() == 0 )
				  {
					com.tecunsa.Error e = new com.tecunsa.Error("Usted es el unico usuario disponible.", request);
					//Pone en sesión
					httpSession.setAttribute("done", "main.jsp");
					//forward declarado en struts-config.xml
					forward = mapping.findForward("error");
				  }
				else
				  {
					//Obtiene un objetos de la BD con el valor que le pasa processAdminForm
					BeanUser beanCurrentUser = (BeanUser) session.load(BeanUser.class, new Integer(processAdminForm.getUser()) );

					//Pone en sesión
					httpSession.setAttribute("processAdmin_currentUser", beanCurrentUser );
					httpSession.setAttribute("processAdmin_listUser", list );

					//Actualiza la lista de procesos denegados
					query = " FROM BeanProcess p" +
							" WHERE p.id NOT IN ( SELECT pa.id_process.id" +
							"                     FROM BeanProcessUsers pa" +
							"                     WHERE pa.id_user.id=" + processAdminForm.getUser() +
							"                   )";// +
							//" AND p.id NOT IN (61,62,63,64,75,101)";

					list = session.createQuery( query ).list();
					httpSession.setAttribute("processAdmin_listDeny", list );

					//Actualiza la lista de procesos permitidos
					query = " FROM BeanProcess p" +
							" WHERE p.id IN ( SELECT pa.id_process.id" +
							"                 FROM BeanProcessUsers pa" +
							"                 WHERE pa.id_user.id=" + processAdminForm.getUser() +
							"               )";

					list = session.createQuery( query ).list();
					httpSession.setAttribute("processAdmin_listAllow", list );
					//forward declarado en struts-config.xml
					forward = mapping.findForward("loop");
				  }
			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");

				com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);

				//forward declarado en struts-config.xml
				forward = mapping.findForward("error");
			  }
			finally
			  {
				session.close();
				sessionFactory.close();
			  }
		  }
	else if ( processAdminForm.getAction().equals("profileNew") )  //profile new
		{
			try
			  {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				//Verifica que el nuevo perfil no exista
				String query =   " FROM BeanProfile u" +
							" WHERE u.name = '" + processAdminForm.getProfileNew() + "'";

				List list = session.createQuery( query ).list();

				if ( list.size() > 0 )
				  {
					com.tecunsa.Error e = new com.tecunsa.Error("Ese perfil ya existe.", request);
					httpSession.setAttribute("done", "javascript:history.back();");
					//forward declarado en struts-config.xml
					forward = mapping.findForward("error");
				  }
				else
				  {
					//nuevo objeto
					BeanProfile beanProfile = new BeanProfile();
					//Guarda el bojeto en la BD
					tx = session.beginTransaction();
					beanProfile.setName( processAdminForm.getProfileNew() );
					session.save( beanProfile );

					tx.commit();

					//Listado de los perfiles
				       query = " FROM BeanProfile p";

					list = session.createQuery( query ).list();
					httpSession.setAttribute("processAdmin_profileList", list );

					//forward declarado en struts-config.xml
					forward = mapping.findForward("loop");
				  }
			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");

				com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
				//forward declarado en struts-config.xml
				forward = mapping.findForward("error");
			  }
			finally
			  {
				session.close();
				sessionFactory.close();
			  }
		}
	else if ( processAdminForm.getAction().equals("profileDelete") )  //delete profile
		{
			try
			  {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				String query =  " FROM BeanProfile u" +
								" WHERE u.id= " + processAdminForm.getProfileDelete();

				List list = session.createQuery( query ).list();

				if ( list.size() == 0 )
				  {
					com.tecunsa.Error e = new com.tecunsa.Error("Ese perfil ya fue borrado.", request);
					httpSession.setAttribute("done", "javascript:history.back();");
					//forward declarado en struts-config.xml
					forward = mapping.findForward("error");
				  }
				else
				  {
					BeanProfile beanProfile = (BeanProfile) list.get(0);

					// Listado de perfiles
					query =  " FROM BeanUserProfile u" +
						     " WHERE u.id_profile.id= " + beanProfile.getId();

					list = session.createQuery( query ).list();
					Iterator iter = list.iterator();

					tx = session.beginTransaction();
					while( iter.hasNext() )
					  {
						BeanUserProfile beanUserProfile = (BeanUserProfile) iter.next();
						//Borra perfiles
						session.delete( beanUserProfile );
					  }


					// Lista de losprocesos de los perfiles
					query =  " FROM BeanProcessProfile u" +
							 " WHERE u.id_profile.id= " + beanProfile.getId();

					list = session.createQuery( query ).list();
					iter = list.iterator();

					tx = session.beginTransaction();
					while( iter.hasNext() )
					  {
						BeanProcessProfile beanProcessProfile = (BeanProcessProfile) iter.next();
						//Borra los perfiles
						session.delete( beanProcessProfile );
					  }


					session.delete( beanProfile );
					tx.commit();

					query = " FROM BeanProfile p";

					list = session.createQuery( query ).list();
					httpSession.setAttribute("processAdmin_profileList", list );
					//forward declarado en struts-config.xml
					forward = mapping.findForward("loop");
				  }
			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");

				com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
				//forward declarado en struts-config.xml
				forward = mapping.findForward("error");
			  }
			finally
			  {
				session.close();
				sessionFactory.close();
			  }
		}
	else if ( processAdminForm.getAction().equals("profileAsignProcess") )  //delete profile
		{
			try
			  {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				BeanProfile beanCurrentProfile = (BeanProfile) session.load(BeanProfile.class, new Integer(processAdminForm.getProfileAsignProcess_id_profile()) );
				BeanProcess beanProcess = (BeanProcess) session.load(BeanProcess.class, new Integer(processAdminForm.getProfileAsignProcess_id_process()) );
				BeanProcessProfile bean = new BeanProcessProfile();

				bean.setId_profile( beanCurrentProfile );
				bean.setId_process( beanProcess );

				//Guarda el proceso del perfil
				tx = session.beginTransaction();
				session.save( bean );
				tx.commit();

				//Procesos asignados al perfil actual
				String query =  " FROM BeanProcess p" +
								" WHERE p.id IN ( SELECT pp.id_process.id" +
								"                 FROM BeanProcessProfile pp" +
								"                 WHERE pp.id_profile.id = " + beanCurrentProfile.getId() +
								"               )";
				List list = session.createQuery( query ).list();
				httpSession.setAttribute("processAdmin_profileProcessAsignedList", list );


				//ProcesosNO  asignados al perfil actual
				query = " FROM BeanProcess p" +
						" WHERE p.id NOT IN ( SELECT pp.id_process.id" +
						"                     FROM BeanProcessProfile pp" +
						"                     WHERE pp.id_profile.id = " + beanCurrentProfile.getId() +
						"                   )";
				list = session.createQuery( query ).list();
				httpSession.setAttribute("processAdmin_profileProcessDenyList", list );

				httpSession.setAttribute("processAdmin_currentProfile", beanCurrentProfile );

				//forward declarado en struts-config.xml
				forward = mapping.findForward("loop");
			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");

				com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
				//forward declarado en struts-config.xml
				forward = mapping.findForward("error");
			  }
			finally
			  {
				session.close();
				sessionFactory.close();
			  }
		}
	else if ( processAdminForm.getAction().equals("profileDeleteProcess") )  //delete profile
		{
			try
			  {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				BeanProfile beanCurrentProfile = (BeanProfile) session.load(BeanProfile.class, new Integer(processAdminForm.getProfileAsignProcess_id_profile()) );
				BeanProcess beanProcess = (BeanProcess) session.load(BeanProcess.class, new Integer(processAdminForm.getProfileDeleteProcess_id_process()) );

				//Borra el proceso del perfil
				String query =  " FROM BeanProcessProfile p" +
								" WHERE p.id_profile.id = " + beanCurrentProfile.getId() +
								" AND   p.id_process.id = " + beanProcess.getId();

				List list = session.createQuery( query ).list();


				tx = session.beginTransaction();
				session.delete( (BeanProcessProfile) list.get(0) );
				tx.commit();

				//procesos asignados a perfil actual
				query = " FROM BeanProcess p" +
						" WHERE p.id IN ( SELECT pp.id_process.id" +
						"                 FROM BeanProcessProfile pp" +
						"                 WHERE pp.id_profile.id = " + beanCurrentProfile.getId() +
						"               )";
				list = session.createQuery( query ).list();
				httpSession.setAttribute("processAdmin_profileProcessAsignedList", list );


				//procesos NO asignados a perfil actual
				query = " FROM BeanProcess p" +
						" WHERE p.id NOT IN ( SELECT pp.id_process.id" +
						"                     FROM BeanProcessProfile pp" +
						"                     WHERE pp.id_profile.id = " + beanCurrentProfile.getId() +
						"                   )";
				list = session.createQuery( query ).list();
				httpSession.setAttribute("processAdmin_profileProcessDenyList", list );

				httpSession.setAttribute("processAdmin_currentProfile", beanCurrentProfile );

				//forward declarado en struts-config.xml
				forward = mapping.findForward("loop");
			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");

				com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
				//forward declarado en struts-config.xml
				forward = mapping.findForward("error");
			  }
			finally
			  {
				session.close();
				sessionFactory.close();
			  }
		}
	else if ( processAdminForm.getAction().equals("profileAsignChange") )  //profile Change
		{
			try
			  {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				BeanProfile beanCurrentProfile = (BeanProfile) session.load(BeanProfile.class, new Integer(processAdminForm.getProfileAsignProcess_id_profile()) );

				//procesos asignados a perfil actual
				String query =  " FROM BeanProcess p" +
								" WHERE p.id IN ( SELECT pp.id_process.id" +
								"                 FROM BeanProcessProfile pp" +
								"                 WHERE pp.id_profile.id = " + beanCurrentProfile.getId() +
								"               )";
				List list = session.createQuery( query ).list();
				httpSession.setAttribute("processAdmin_profileProcessAsignedList", list );


				//procesos NO asignados a perfil actual
				query = " FROM BeanProcess p" +
						" WHERE p.id NOT IN ( SELECT pp.id_process.id" +
						"                     FROM BeanProcessProfile pp" +
						"                     WHERE pp.id_profile.id = " + beanCurrentProfile.getId() +
						"                   )";
				list = session.createQuery( query ).list();
				httpSession.setAttribute("processAdmin_profileProcessDenyList", list );

				httpSession.setAttribute("processAdmin_currentProfile", beanCurrentProfile );

				//forward declarado en struts-config.xml
				forward = mapping.findForward("loop");
			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");

				com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
				//forward declarado en struts-config.xml
				forward = mapping.findForward("error");
			  }
			finally
			  {
				session.close();
				sessionFactory.close();
			  }
		}
	else if ( processAdminForm.getAction().equals("userAsignProfileUserAdd") )  //delete profile
		{
			try
			  {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				BeanUser beanUserFrom = (BeanUser) session.load(BeanUser.class, new Integer(processAdminForm.getProfileUser_id_userFrom()) );
				BeanUser beanUserTo = (BeanUser) session.load(BeanUser.class, new Integer(processAdminForm.getProfileUser_id_userTo()) );
				BeanProcessUsers a;

				//Guarda una lista de nuevos procesos para el usuario
			        String query =  " FROM BeanProcessUsers p" +
								" WHERE p.id_user.id = " + beanUserFrom.getId() +
								" AND  p.id_process.id NOT IN ( SELECT p1.id_process.id" +
								" 							    FROM BeanProcessUsers p1" +
								" 							    WHERE p1.id_user.id= " + beanUserTo.getId() +
								"                             )";

				List list = session.createQuery( query ).list();
				Iterator iter = list.iterator();

				tx = session.beginTransaction();
				while ( iter.hasNext() )
				  {
					BeanProcessUsers currentProcessUser = (BeanProcessUsers) iter.next();
					BeanProcessUsers newProcessUser = new BeanProcessUsers();

					newProcessUser.setId_process( currentProcessUser.getId_process() );
					newProcessUser.setId_user( beanUserTo );
					session.save( newProcessUser );
				  }
				tx.commit();

				//forward declarado en struts-config.xml
				forward = mapping.findForward("loop");
			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");

				com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
				//forward declarado en struts-config.xml
				forward = mapping.findForward("error");
			  }
			finally
			  {
				session.close();
				sessionFactory.close();
			  }
		}

	else if ( processAdminForm.getAction().equals("userAsignProfileUserAsign") )  //delete profile
		{
			try
			  {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				BeanUser beanUserFrom = (BeanUser) session.load(BeanUser.class, new Integer(processAdminForm.getProfileUser_id_userFrom()) );
				BeanUser beanUserTo = (BeanUser) session.load(BeanUser.class, new Integer(processAdminForm.getProfileUser_id_userTo()) );
				BeanProcessUsers a;

				//Borra los accesos previos
				String query =  " FROM BeanProcessUsers p" +
								" WHERE p.id_user.id = " + beanUserTo.getId();
				List list = session.createQuery( query ).list();

				Iterator iter = list.iterator();

				tx = session.beginTransaction();
				while ( iter.hasNext() )
				  {
					BeanProcessUsers currentProcessUser = (BeanProcessUsers) iter.next();

					session.delete( currentProcessUser );
				  }

				//Guarda nuevos procesos
				query = " FROM BeanProcessUsers p" +
						" WHERE p.id_user.id = " + beanUserFrom.getId() +
						" AND  p.id_process.id NOT IN ( SELECT p1.id_process.id" +
						" 							    FROM BeanProcessUsers p1" +
						" 							    WHERE p1.id_user.id= " + beanUserTo.getId() +
						"                             )";

				list = session.createQuery( query ).list();
				iter = list.iterator();

				while ( iter.hasNext() )
				  {
					BeanProcessUsers currentProcessUser = (BeanProcessUsers) iter.next();
					BeanProcessUsers newProcessUser = new BeanProcessUsers();

					newProcessUser.setId_process( currentProcessUser.getId_process() );
					newProcessUser.setId_user( beanUserTo );
					session.save( newProcessUser );
				  }
				tx.commit();


				forward = mapping.findForward("loop");
			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");

				com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
				//forward declarado en struts-config.xml
				forward = mapping.findForward("error");
			  }
			finally
			  {
				session.close();
				sessionFactory.close();
			  }
		}
	else if ( processAdminForm.getAction().equals("profileAsignUserChangeUser") )  //asign profile
		{
			try
			  {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				BeanUser user = (BeanUser) session.load(BeanUser.class, new Integer(processAdminForm.getProfileUser_id_user()) );

				httpSession.setAttribute("processAdmin_currentUser", user );

				//Asgina un perfil al usuario
				String query =  " FROM BeanUserProfile p" +
							" WHERE p.id_user.id = " + user.getId();

				List list = session.createQuery( query ).list();

				if ( list.size() == 0)
				  httpSession.removeAttribute("processAdmin_profileAsignUser_currentProfile" );
				else
				  {
					BeanUserProfile beanUserProfile = (BeanUserProfile) list.get(0);
					httpSession.setAttribute("processAdmin_profileAsignUser_currentProfile", beanUserProfile.getId_profile() );
				  }
				//forward declarado en struts-config.xml
				forward = mapping.findForward("loop");
			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");

				com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
				//forward declarado en struts-config.xml
				forward = mapping.findForward("error");
			  }
			finally
			  {
				session.close();
				sessionFactory.close();
			  }
		}

	else if ( processAdminForm.getAction().equals("profileAsignUserAsign") )  //asign profile
		{
			try
			  {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				BeanUser user = (BeanUser) session.load(BeanUser.class, new Integer(processAdminForm.getProfileUser_id_user()) );
				BeanProfile profile = (BeanProfile) session.load(BeanProfile.class, new Integer(processAdminForm.getProfileUser_id_profile()) );
				BeanUserProfile bean = new BeanUserProfile();

				//Guarda el perfil en la BD
				tx = session.beginTransaction();
				bean.setId_user( user );
				bean.setId_profile( profile );

				session.save( bean);

				//Borra los procesos previos
				String query =  " FROM BeanProcessUsers p" +
								" WHERE p.id_user.id = " + user.getId();
				List list = session.createQuery( query ).list();

				Iterator iter = list.iterator();

				while ( iter.hasNext() )
				  {
					BeanProcessUsers currentProcessUser = (BeanProcessUsers) iter.next();

					session.delete( currentProcessUser );
				  }

				//Guarda nuevos procesos

				query = " FROM BeanProcessProfile p" +
						" WHERE p.id_profile.id= " + profile.getId();

				list = session.createQuery( query ).list();
				iter = list.iterator();

				while ( iter.hasNext() )
				  {
					BeanProcessProfile beanProcessProfile = (BeanProcessProfile) iter.next();
					BeanProcessUsers newProcessUser = new BeanProcessUsers();

					newProcessUser.setId_process( beanProcessProfile.getId_process() );
					newProcessUser.setId_user( user );
					session.save( newProcessUser );
				  }

				//Borra los perfiles asignados
				query = " FROM BeanUserProfile p" +
						" WHERE p.id_user.id = " + user.getId();

				list = session.createQuery( query ).list();
				iter = list.iterator();

				while ( iter.hasNext() )
				  {
					BeanUserProfile beanUserProfile = (BeanUserProfile) iter.next();

					session.delete( beanUserProfile );
				  }

				//Asigna un nuevo perfilm al usuario

				BeanUserProfile beanUserProfile = new BeanUserProfile();
				beanUserProfile.setId_user( user );
				beanUserProfile.setId_profile( profile );
				session.save( beanUserProfile );

				tx.commit();

				httpSession.setAttribute("processAdmin_profileAsignUser_currentProfile", profile );
				//forward declarado en struts-config.xml
				forward = mapping.findForward("loop");
			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");

				com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
				//forward declarado en struts-config.xml
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
		com.tecunsa.Error e = new com.tecunsa.Error("Opcion no programada. Consulte a soporte tecnico.", request);
		httpSession.setAttribute("done", "main.jsp");

		forward = mapping.findForward("error");

	  }


	// Termina con
	return (forward);

}
}
