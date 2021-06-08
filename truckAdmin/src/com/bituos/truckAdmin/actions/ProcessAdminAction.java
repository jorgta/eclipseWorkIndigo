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

public class ProcessAdminAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		ActionForm actionForm = (ActionForm) form;

		ProcessAdminForm processAdminForm = (ProcessAdminForm) form;

		HttpSession httpSession = request.getSession();
							
		Config config = new Config( request );
		
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		httpSession.removeAttribute("done" );

		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
		  forward = mapping.findForward("login");
		else if ( processAdminForm.getAction().equals("userAllow") )  //allow process
			{
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();
	
	                if ( processAdminForm.getProcess() != null)
	                  {
						BeanProcess beanProcess = (BeanProcess) session.load(BeanProcess.class, new Integer(processAdminForm.getProcess()) ); 
						BeanUser beanCurrentUser = (BeanUser) session.load(BeanUser.class, new Integer(processAdminForm.getUser()) ); 
						BeanProcessUsers bean = new BeanProcessUsers(); 


						String query =  " FROM BeanProcessUsers u" +
										" WHERE u.id_process.id = " + beanProcess.getId() +
										" AND u.id_user.id =" + beanCurrentUser.getId();
									
						// Do exist that permit ?				
						if ( session.createQuery( query).list().size() == 0 )
						  {
							bean.setId_process(beanProcess);
							bean.setId_user(beanCurrentUser);
					
							tx=session.beginTransaction();
							session.save( bean );
							tx.commit();
					
							httpSession.setAttribute("processAdmin_currentUser", beanCurrentUser );
										
							query = " FROM BeanUser u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active = 'Y'" +
									" AND u.id <> " + beanUser.getId();

							List list = session.createQuery( query ).list();

							httpSession.setAttribute("processAdmin_listUsers", list );

							query = " FROM BeanProcess p" +
									" WHERE p.id NOT IN ( SELECT pa.id_process.id" +
									"                     FROM BeanProcessUsers pa" +
									"                     WHERE pa.id_user.id=" + beanCurrentUser.getId() +
									"                   )" +
									" AND p.id NOT IN (61, 62, 63, 64, 101)";
								                    
							list = session.createQuery( query ).list();
							httpSession.setAttribute("processAdmin_listDeny", list );
						
							query = " FROM BeanProcess p" +
									" WHERE p.id IN ( SELECT pa.id_process.id" +
									"                 FROM BeanProcessUsers pa" +
									"                 WHERE pa.id_user.id=" + beanCurrentUser.getId() +
									"               )";
								                    
							list = session.createQuery( query ).list();
							httpSession.setAttribute("processAdmin_listAllow", list );
						  }
					
						forward = mapping.findForward("loop");
	                  	
	                  }
	                else
	                  {
						com.bituos.Error e = new com.bituos.Error("Debe seleccionar un proceso valido", request);
						//httpSession.setAttribute("done", "javascript:history.back();");
						httpSession.setAttribute("done", "/truckAdmin/admin/processAdmin.jsp");

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
		else if ( processAdminForm.getAction().equals("userDeny") ) //deny process
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();
	
	
					//BeanProcess beanProcess = (BeanProcess) session.load(BeanProcess.class, new Integer(processAdminForm.getProcess()) ); 
					BeanUser beanCurrentUser = (BeanUser) session.load(BeanUser.class, new Integer(processAdminForm.getUser()) ); 

					String query =  " FROM BeanProcessUsers pu" +
									" WHERE pu.id_user = " + beanCurrentUser.getId() +
									" AND pu.id_process = " + processAdminForm.getProcess();

					List list = session.createQuery( query ).list();

					// Do exist that permit ?				
					if ( list.size() > 0 )
					  {
						BeanProcessUsers bean = (BeanProcessUsers) session.createQuery( query ).list().get(0);
	
						tx=session.beginTransaction();
						session.delete( bean );
						tx.commit();
						
						httpSession.setAttribute("processAdmin_currentUser", beanCurrentUser );
											
						query =  " FROM BeanUser u" +
								" WHERE u.id_company = " + beanUser.getId_company().getId() +
								" AND u.active = 'Y'" +
								" AND u.id <> " + beanUser.getId();
	
						list = session.createQuery( query ).list();
	
						httpSession.setAttribute("processAdmin_listUsers", list );
	
						query = " FROM BeanProcess p" +
								" WHERE p.id NOT IN ( SELECT pa.id_process.id" +
								"                     FROM BeanProcessUsers pa" +
								"                     WHERE pa.id_user.id=" + beanCurrentUser.getId() +
								"                   )" +
								" AND p.id NOT IN (61, 62, 63, 64, 101)";
	
						list = session.createQuery( query ).list();
						httpSession.setAttribute("processAdmin_listDeny", list );
							
						query = " FROM BeanProcess p" +
								" WHERE p.id IN ( SELECT pa.id_process.id" +
								"                 FROM BeanProcessUsers pa" +
								"                 WHERE pa.id_user.id=" + beanCurrentUser.getId() +
								"               )";
									                    
						list = session.createQuery( query ).list();
						httpSession.setAttribute("processAdmin_listAllow", list );

					  }
					  
					forward = mapping.findForward("loop");
					  
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
		else if ( processAdminForm.getAction().equals("userChange") ) //change user
			  {
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					String query =  " FROM BeanUser u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active = 'Y'" +
									" AND u.id <> " + beanUser.getId();

					List list = session.createQuery( query ).list();

					if ( list.size() == 0 )
					  {
						com.bituos.Error e = new com.bituos.Error("Ud es el unico usuario disponible.", request);
						httpSession.setAttribute("done", "main.jsp" );

						forward = mapping.findForward("error");
					  }
					else
					  {
					  	BeanUser beanCurrentUser = (BeanUser) session.load(BeanUser.class, new Integer(processAdminForm.getUser()) ); 
					  	 
						httpSession.setAttribute("processAdmin_currentUser", beanCurrentUser );
						httpSession.setAttribute("processAdmin_listUser", list );

						query = " FROM BeanProcess p" +
								" WHERE p.id NOT IN ( SELECT pa.id_process.id" +
								"                     FROM BeanProcessUsers pa" +
								"                     WHERE pa.id_user.id=" + processAdminForm.getUser() +
								"                   )" +
								" AND p.id NOT IN (61, 62, 63, 64, 101)";

						list = session.createQuery( query ).list();
						httpSession.setAttribute("processAdmin_listDeny", list );
						
						query = " FROM BeanProcess p" +
								" WHERE p.id IN ( SELECT pa.id_process.id" +
								"                 FROM BeanProcessUsers pa" +
								"                 WHERE pa.id_user.id=" + processAdminForm.getUser() +
								"               )";
								                    
						list = session.createQuery( query ).list();
						httpSession.setAttribute("processAdmin_listAllow", list );

						forward = mapping.findForward("loop");
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
