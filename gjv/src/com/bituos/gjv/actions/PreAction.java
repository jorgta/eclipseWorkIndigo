package com.bituos.gjv.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import java.util.*;

import com.bituos.*;
import com.bituos.gjv.forms.*;
import com.bituos.gjv.Beans.*;
import com.bituos.gjv.forms.PreForm;

import javax.servlet.http.*;

/**
 * @version 	1.0
 * @author
 */

import java.text.*;

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
		HttpSession ses = request.getSession();

		Config config = new Config( request );
		SessionFactory sessionFactory = null;
		Session session= null;
		PreForm preForm = (PreForm) form;
		Transaction tx = null;
		BeanUser beanUser = (BeanUser) ses.getAttribute("beanUser");

		try
		  {
			sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			session = sessionFactory.openSession();

			if ( beanUser == null )
			  {
				//ses.setAttribute("nextLink",  new String("/bituos/validate.do?process=" + preForm.getProcess() ) );
				forward = mapping.findForward("login");
			  }
			else if ( preForm.getProcess().equals("userNew") )
			  {
				forward = mapping.findForward("userNew");
			  }
			else if ( preForm.getProcess().equals("userDelete") )
			  {
				 
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();


				String query =  " FROM BeanUser u" +
								" WHERE u.active='Y'" +
								" AND u.id <>" + beanUser.getId() +
								" ORDER BY u.full_name";


				List list = session.createQuery( query ).list();
				ses.setAttribute("filterList", list );

				forward = mapping.findForward("userDelete");
              
			  }
			else if ( preForm.getProcess().equals("userActivate") )
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();


				 String query =  " FROM BeanUser u" +
								" WHERE u.active='N'" +
								" AND u.id <>" + beanUser.getId() +
								" ORDER BY u.full_name";


				List list = session.createQuery( query ).list();
				ses.setAttribute("filterList", list );
				forward = mapping.findForward("userActivate");
            
			  }
			else if ( preForm.getProcess().equals("userChange") )
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

			    UserChangeForm userChangeForm = new UserChangeForm();

				String query =  " FROM BeanUser u" +
								" WHERE u.active = 'Y'" +
								" AND u.id =" + beanUser.getId();

				List list1 = session.createQuery( query ).list();

				if ( list1.size() != 0 )
				{
				  ses.removeAttribute("userChangeForm");
				  BeanUser bean = (BeanUser) list1.get(0);

				  userChangeForm.setIdUser( bean.getId() );
				  userChangeForm.setName( bean.getName() );
				  userChangeForm.setPassword( bean.getPassword() );		
				  userChangeForm.setConfirmpassword( bean.getPassword() );	
				  userChangeForm.setRfc( bean.getRfc());
				  userChangeForm.setEmail( bean.getEmail());	
				  userChangeForm.setFull_name(bean.getFull_name());
				  userChangeForm.setAddress(bean.getAddress());
				  userChangeForm.setTelephone(bean.getTelephone());

				  ses.setAttribute("userChangeForm",userChangeForm );

				  query = " FROM BeanUser u" +
					      " ORDER BY u.name";

			      List list = session.createQuery( query ).list();

			      ses.setAttribute("filterList", list );
			      ses.setAttribute("done", "main.jsp" );
				  forward = mapping.findForward("userChange");

				}
				forward = mapping.findForward("userChange");
            
			  }
			else if ( preForm.getProcess().equals("processAdmin") ) //Administracion de cuentas
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
				    com.bituos.Error e = new com.bituos.Error("Usted es el único usuario disponible.", request);
				    ses.setAttribute("done", "javascript:window.close();" );
				   //forward  declarado en struts-config.xml
				    forward = mapping.findForward("error");
				  }
			    else
				  {
				    BeanUser processAdminCurrentUser = (BeanUser) list.get(0);
				    ses.setAttribute("processAdmin_currentUser", processAdminCurrentUser );
				    ses.setAttribute("processAdmin_listUsers", list );

					   //Procesos negados
				    query = " FROM BeanProcess p" +
						    " WHERE p.id NOT IN ( SELECT pa.id_process.id" +
						    "                     FROM BeanProcessUsers pa" +
						    "                     WHERE pa.id_user.id=" + processAdminCurrentUser.getId() +
						    "                   )" +
						    " AND p.id NOT IN (61,62,63, 64, 101)";

				    list = session.createQuery( query ).list();
				    ses.setAttribute("processAdmin_listDeny", list );

				    //Procesos permitidos
				    query = " FROM BeanProcess p" +
						    " WHERE p.id IN ( SELECT pa.id_process.id" +
						    "                 FROM BeanProcessUsers pa" +
						    "                 WHERE pa.id_user.id=" + processAdminCurrentUser.getId() +
						    "               )";

				    list = session.createQuery( query ).list();
				    ses.setAttribute("processAdmin_listAllow", list );

				    //Perfiles
				    query = " FROM BeanProfile p" +
						   " WHERE p.id = p.id";
				    list = session.createQuery( query ).list();
				    ses.setAttribute("processAdmin_profileList", list );

				    BeanProfile processAdminCurrentProfile = (BeanProfile) list.get(0);
				    ses.setAttribute("processAdmin_currentProfile", processAdminCurrentProfile );

				    //Procesos asgignados al perfil  actual
				    query = " FROM BeanProcess p" +
						   " WHERE p.id IN ( SELECT pp.id_process.id" +
						   "                 FROM BeanProcessProfile pp" +
						   "                 WHERE pp.id_profile.id = " + processAdminCurrentProfile.getId() +
						   "               )";
				    list = session.createQuery( query ).list();
				    ses.setAttribute("processAdmin_profileProcessAsignedList", list );


				    //Procesos NO asgignados al perfil  actual
				    query = " FROM BeanProcess p" +
						   " WHERE p.id NOT IN ( SELECT pp.id_process.id" +
						   "                     FROM BeanProcessProfile pp" +
						   "                     WHERE pp.id_profile.id = " + processAdminCurrentProfile.getId() +
						   "                   )";
				    list = session.createQuery( query ).list();
				    ses.setAttribute("processAdmin_profileProcessDenyList", list );


				    //users vs profiles





				    //Denegar Perfiles
				    query = " FROM BeanProfile p" +
					 	    " WHERE p.id NOT IN ( SELECT up.id_profile.id" +
						    "                     FROM BeanUserProfile up" +
						    "                     WHERE up.id_user.id=" + processAdminCurrentUser.getId() +
						    "                   )";


				    list = session.createQuery( query ).list();
				    ses.setAttribute("processAdmin_profileListDeny", list );


				    //Permitir perfilles
				    query = " FROM BeanProfile p" +
						    " WHERE p.id IN ( SELECT up.id_profile.id" +
						    "                 FROM BeanUserProfile up" +
						    "                 WHERE up.id_user.id=" + processAdminCurrentUser.getId() +
						    "               )";

				    list = session.createQuery( query ).list();
				    ses.setAttribute("processAdmin_profileListAllow", list );
				   //forward  declarado en struts-config.xml
				    forward = mapping.findForward("processAdmin");
				  }
			  }	
			else if ( preForm.getProcess().equals("contactNewRH") )
			  {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaingreso= new Date();
				dateFormat.format(fechaingreso);
				
				ses.removeAttribute("fechaingreso");
				ses.setAttribute("fechaingreso",Utils.DateToStr(fechaingreso));
				//contactChangeForm.setStarting_date(Utils.DateToStr(beanInOut.getStarting_date()));
				forward = mapping.findForward("contactNewRH");
			  }
			else if ( preForm.getProcess().equals("contactChangeRH") )
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				ContactChangeForm contactChangeForm = new ContactChangeForm();

				String query =  " FROM BeanContactRH u";

				List list = session.createQuery( query ).list();

				if ( !list.isEmpty()  )
				{
					
				  ses.removeAttribute("contactChangeForm");
				 
				  BeanContactRH bean = (BeanContactRH) list.get(0);				  

				  contactChangeForm.setIdContact(String.valueOf(bean.getId()));
				  contactChangeForm.setFirst_name( bean.getFirst_name() );
				  contactChangeForm.setLast_name( bean.getLast_name() );		
				  contactChangeForm.setAddress( bean.getAddress() );	
				  contactChangeForm.setColony( bean.getColony());
				  contactChangeForm.setPhone( bean.getPhone());	
				  contactChangeForm.setCel_phone(bean.getCel_phone());
				 				  
				  String casado;
				  if(bean.getMarried()== 'N')
				  {
					  casado="false";					  
				  }
				  else
				  {
					  casado="true";					  
				  }			  
			  
				  ses.removeAttribute("casado");
				  ses.setAttribute("casado",casado );
				  
				  contactChangeForm.setNSS(bean.getNSS());
				  contactChangeForm.setWife_name(bean.getWife_name());
				  
				  
				  contactChangeForm.setDate_of_birth(Utils.DateToStr( bean.getDate_of_birth()));
				  
				  
				  query =  " FROM BeanInOut u" +
	              		   " WHERE u.idContactRH.id =" + bean.getId();

				  list =   session.createQuery( query ).list();
				  BeanInOut beanInOut = (BeanInOut) list.get(list.size() - 1);
				 
				  Date fechaend = new Date();
				  if(beanInOut.getEnding_date() != null)
					contactChangeForm.setEnding_date(Utils.DateToStr(beanInOut.getEnding_date()));	
				  else
				    contactChangeForm.setEnding_date(Utils.DateToStr(fechaend));	

				  ses.removeAttribute("contactChangeForm");
				  ses.setAttribute("contactChangeForm",contactChangeForm );

				  query = " FROM BeanContactRH u" +
					      " ORDER BY u.first_name";

				  
			      list = session.createQuery( query ).list();
			      
			      			      
			      if ( !list.isEmpty() )
			       {  
			    	 ses.removeAttribute("filterList");
			         ses.setAttribute("filterList", list );
			       }
			      else
			       {
			    	 query =  " FROM BeanContactRH u" +
				       		  " WHERE u.id = 0";

			    	 list = session.createQuery( query ).list();
			    	 BeanContactRH beanContactRH= new BeanContactRH();
			    	 list.add(beanContactRH);	
			    	 
			    	 ses.removeAttribute("filterList");
			    	 ses.setAttribute("filterList", list );
			      }
  
			      ses.setAttribute("done", "main.jsp" );				  

				}
				else
				{
		
				  query =  " FROM BeanContactRH u" +
	       		  		   " WHERE u.id = 0";

				
				  List contactList = new ArrayList();
			  	  BeanContactRH beanContactRH= new BeanContactRH();
			  	  contactList.add(beanContactRH);				  	  
			  	  
			  	  ses.removeAttribute("filterList");
			  	  ses.setAttribute("filterList", contactList );
			  	  
			  	  
				}
				forward = mapping.findForward("contactChangeRH");
				
			  }
			else if ( preForm.getProcess().equals("contactDeleteRH") )
			  {
				String query =  " FROM BeanContactRH u" +
								" WHERE u.active='Y'" +								
								" ORDER BY u.first_name";
				List list = session.createQuery( query ).list();
				
				if ( !list.isEmpty() )
				{								
					 ses.removeAttribute("filterList");
			         ses.setAttribute("filterList", list );
			         ses.removeAttribute("BeanContactRH");
			         ses.setAttribute("BeanContactRH", list.get(0) );
				}
			    else
			      {
			    	 query =  " FROM BeanContactRH u" +
				       		  " WHERE u.id = 0";
		
			    	 list = session.createQuery( query ).list();
			    	 BeanContactRH beanContactRH= new BeanContactRH();
			    	 list.add(beanContactRH);	
			    	 
			    	 ses.removeAttribute("filterList");
			    	 ses.setAttribute("filterList", list );
			      }
				
				forward = mapping.findForward("contactDeleteRH");
			  }
			else if ( preForm.getProcess().equals("contactActivateRH") )
			  {
				String query =  " FROM BeanContactRH u" +
								" WHERE u.active='N'" +								
								" ORDER BY u.first_name";
				List list = session.createQuery( query ).list();
				
				if ( !list.isEmpty() )
				{								
					 ses.removeAttribute("filterList");
			         ses.setAttribute("filterList", list );
			         ses.removeAttribute("BeanContactRH");
			         ses.setAttribute("BeanContactRH", list.get(0) );
				}
			    else
			      {
			    	 query =  " FROM BeanContactRH u" +
				       		  " WHERE u.id = 0";
		
			    	 list = session.createQuery( query ).list();
			    	 BeanContactRH beanContactRH= new BeanContactRH();
			    	 list.add(beanContactRH);	
			    	 
			    	 ses.removeAttribute("filterList");
			    	 ses.setAttribute("filterList", list );
			      }
				
				forward = mapping.findForward("contactActivateRH");
			  }
			else if ( preForm.getProcess().equals("contactNoteNewRH") )
			  {
				String query =  " FROM BeanContactRH u" +							
								" ORDER BY u.first_name";
				
				List list = session.createQuery( query ).list();
				
				if ( !list.isEmpty() )
				{								
					 ses.removeAttribute("filterList");
				     ses.setAttribute("filterList", list );
				     ses.removeAttribute("BeanContactRH");
				     ses.setAttribute("BeanContactRH", list.get(0) );
				}
				else
				  {
					 query =  " FROM BeanContactRH u" +
				       		  " WHERE u.id = 0";
				
					 list = session.createQuery( query ).list();
					 BeanContactRH beanContactRH= new BeanContactRH();
					 list.add(beanContactRH);
					 
					 ses.removeAttribute("filterList");
					 ses.setAttribute("filterList", list );
				  }


				forward = mapping.findForward("contactNoteNewRH");
			  }
			else if ( preForm.getProcess().equals("contactNoteChangeRH") )
			  {
				String query =  " FROM BeanContactRH u" +							
								" ORDER BY u.first_name";
				
				List list = session.createQuery( query ).list();
				
				if ( !list.isEmpty() )
				{								
					 ses.removeAttribute("filterList");
				     ses.setAttribute("filterList", list );
				     ses.removeAttribute("BeanContactRH");
				     ses.setAttribute("BeanContactRH", list.get(0) );
				     
				     query =  " From BeanNotes u" +							
							  " WHERE u.idContactRH.id=" + ((BeanContactRH)list.get(0)).getId();
		
				     list = session.createQuery( query ).list();
				     
				     ses.removeAttribute("notesList");
				     ses.setAttribute("notesList", list );
				     
				     ses.removeAttribute("noteId");
				     ses.setAttribute("noteId", "0" );
				     
				}
				else
				  {
					 query =  " FROM BeanContactRH u" +
				       		  " WHERE u.id = 0";
				
					 list = session.createQuery( query ).list();
					 BeanContactRH beanContactRH= new BeanContactRH();
					 list.add(beanContactRH);	
					 
					 ses.removeAttribute("filterList");
					 ses.setAttribute("filterList", list );
				  }


				forward = mapping.findForward("contactNoteChangeRH");
			  }
			else if ( preForm.getProcess().equals("contactNewRHGroup") )
			  {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaingreso= new Date();
				dateFormat.format(fechaingreso);
				
				ses.removeAttribute("fechaingreso");
				ses.setAttribute("fechaingreso",Utils.DateToStr(fechaingreso));
				//contactChangeForm.setStarting_date(Utils.DateToStr(beanInOut.getStarting_date()));
				forward = mapping.findForward("contactGroupNewRH");
			  }
			else if ( preForm.getProcess().equals("contactChangeRHGroup") )
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				ContactGroupChangeForm contactChangeForm = new ContactGroupChangeForm();

				String query =  " FROM BeanContactRHGroup u";

				List list = session.createQuery( query ).list();

				if ( !list.isEmpty()  )
				{
				  ses.removeAttribute("contactChangeGroupForm");
				  //ses.removeAttribute("casado");
				  BeanContactRHGroup bean = (BeanContactRHGroup) list.get(0);				  

				  contactChangeForm.setIdContact(String.valueOf(bean.getId()));
				  contactChangeForm.setFirst_name( bean.getFirst_name() );
				  contactChangeForm.setLast_name( bean.getLast_name() );		
				  contactChangeForm.setAddress( bean.getAddress() );	
				  contactChangeForm.setColony( bean.getColony());
				  contactChangeForm.setPhone( bean.getPhone());	
				  contactChangeForm.setCel_phone(bean.getCel_phone());
				 				  
				  String casado;
				  if(bean.getMarried()== 'N')
				  {
					  casado="false";					  
				  }
				  else
				  {
					  casado="true";					  
				  }			  
			  
				  ses.removeAttribute("casado");
				  ses.setAttribute("casado",casado );
				  
				  contactChangeForm.setNSS(bean.getNSS());
				  contactChangeForm.setWife_name(bean.getWife_name());
				  
				  
				  contactChangeForm.setDate_of_birth(Utils.DateToStr( bean.getDate_of_birth()));
				  
				  
				  query =  " FROM BeanInOutGroup u" +
	              		   " WHERE u.idContactRHGroup.id =" + bean.getId();

				  list =   session.createQuery( query ).list();
				  BeanInOutGroup beanInOut = (BeanInOutGroup) list.get(list.size() - 1);
				 
				  Date fechaend = new Date();
				  if(beanInOut.getEnding_date() != null)
					contactChangeForm.setEnding_date(Utils.DateToStr(beanInOut.getEnding_date()));	
				  else
				    contactChangeForm.setEnding_date(Utils.DateToStr(fechaend));	

				  ses.removeAttribute("contactChangeForm");
				  ses.setAttribute("contactChangeForm",contactChangeForm );

				  query = " FROM BeanContactRHGroup u" +
					      " ORDER BY u.first_name";

				  
			      list = session.createQuery( query ).list();
			      
			      			      
			      if ( !list.isEmpty() )
			      {
			    	  
			    	 ses.removeAttribute("filterList");
			         ses.setAttribute("filterList", list );
			      }
			      else
			       {
			    	 query =  " FROM BeanContactRHGroup u" +
				       		  " WHERE u.id = 0";

			    	 list = session.createQuery( query ).list();
			    	 BeanContactRHGroup beanContactRH= new BeanContactRHGroup();
			    	 list.add(beanContactRH);	
			    	 
			    	 ses.removeAttribute("filterList");
			    	 ses.setAttribute("filterList", list );
			      }

			      ses.setAttribute("done", "main.jsp" );				  

				}
				else
				{
		
				  query =  " FROM BeanContactRHGroup u" +
	       		  		   " WHERE u.id = 0";

				
				  List contactList = new ArrayList();
			  	  BeanContactRHGroup beanContactRH= new BeanContactRHGroup();
			  	  contactList.add(beanContactRH);				  	  
			  	  
			  	  ses.removeAttribute("filterList");
			  	  ses.setAttribute("filterList", contactList );
			  	  
			  	  
				}
				forward = mapping.findForward("contactGroupChangeRH");
				
			  }
			else if ( preForm.getProcess().equals("contactDeleteRHGroup") )
			  {
				String query =  " FROM BeanContactRHGroup u" +
								" WHERE u.active='Y'" +								
								" ORDER BY u.first_name";
				List list = session.createQuery( query ).list();
				
				if ( !list.isEmpty() )
				{						
					 ses.removeAttribute("filterList");
			         ses.setAttribute("filterList", list );
			         
			         ses.removeAttribute("BeanContactRHGroup");
			         ses.setAttribute("BeanContactRHGroup", list.get(0) );
				}
			    else
			      {
			    	 query =  " FROM BeanContactRHGroup u" +
				       		  " WHERE u.id = 0";
		
			    	 list = session.createQuery( query ).list();
			    	 BeanContactRHGroup beanContactRH= new BeanContactRHGroup();
			    	 list.add(beanContactRH);	
			    	 
			    	 ses.removeAttribute("filterList");
			    	 ses.setAttribute("filterList", list );
			      }
				
				forward = mapping.findForward("contactGroupDeleteRH");
			  }
			else if ( preForm.getProcess().equals("contactActivateRHGroup") )
			  {
				String query =  " FROM BeanContactRHGroup u" +
								" WHERE u.active='N'" +								
								" ORDER BY u.first_name";
				List list = session.createQuery( query ).list();
				
				if ( !list.isEmpty() )
				{								
					 ses.removeAttribute("filterList");
			         ses.setAttribute("filterList", list );
			         
			         ses.removeAttribute("BeanContactRHGroup");
			         ses.setAttribute("BeanContactRHGroup", list.get(0) );
				}
			    else
			      {
			    	 query =  " FROM BeanContactRHGroup u" +
				       		  " WHERE u.id = 0";
		
			    	 list = session.createQuery( query ).list();
			    	 BeanContactRHGroup beanContactRH= new BeanContactRHGroup();
			    	 list.add(beanContactRH);	
			    	 
			    	 ses.removeAttribute("filterList");
			    	 ses.setAttribute("filterList", list );
			      }
				
				forward = mapping.findForward("contactGroupActivateRH");
			  }
			else if ( preForm.getProcess().equals("contactNoteNewRHGroup") )
			  {
				String query =  " FROM BeanContactRHGroup u" +							
								" ORDER BY u.first_name";
				
				List list = session.createQuery( query ).list();
				
				if ( !list.isEmpty() )
				{								
					 ses.removeAttribute("filterList");
				     ses.setAttribute("filterList", list );
				     
				     ses.removeAttribute("BeanContactRHGroup");
				     ses.setAttribute("BeanContactRHGroup", list.get(0) );
				}
				else
				  {
					 query =  " FROM BeanContactRHGroup u" +
				       		  " WHERE u.id = 0";
				
					 list = session.createQuery( query ).list();
					 BeanContactRHGroup beanContactRH= new BeanContactRHGroup();
					 list.add(beanContactRH);	
					 
					 ses.removeAttribute("filterList");
					 ses.setAttribute("filterList", list );
				  }


				forward = mapping.findForward("contactGroupNoteNewRH");
			  }
			else if ( preForm.getProcess().equals("contactNoteChangeRHGroup") )
			  {
				String query =  " FROM BeanContactRHGroup u" +							
								" ORDER BY u.first_name";
				
				List list = session.createQuery( query ).list();
				
				if ( !list.isEmpty() )
				{								
					 ses.removeAttribute("filterList");
				     ses.setAttribute("filterList", list );
				     
				     ses.removeAttribute("BeanContactRHGroup");
				     ses.setAttribute("BeanContactRHGroup", list.get(0) );
				     
				     query =  " From BeanNotesGroup u" +							
							  " WHERE u.idContactRH.id=" + ((BeanContactRHGroup)list.get(0)).getId();
		
				     list = session.createQuery( query ).list();
				     
				     ses.removeAttribute("notesList");
				     ses.setAttribute("notesList", list );
				     
				     ses.removeAttribute("noteId");
				     ses.setAttribute("noteId", "0" );
				     
				}
				else
				  {
					 query =  " FROM BeanContactRHGroup u" +
				       		  " WHERE u.id = 0";
				
					 list = session.createQuery( query ).list();
					 BeanContactRHGroup beanContactRH= new BeanContactRHGroup();
					 list.add(beanContactRH);	
					 
					 ses.removeAttribute("filterList");
					 ses.setAttribute("filterList", list );
				  }


				forward = mapping.findForward("contactGroupNoteChangeRH");
			  }
			else if ( preForm.getProcess().equals("providerNewPPS") )
			  {
				forward = mapping.findForward("providerNewPPS");
			  }
			else if ( preForm.getProcess().equals("providerChangePPS") )
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				ProviderChangeForm providerChangeForm = new ProviderChangeForm();

				String query =  " FROM BeanContactPPS u";

				List list = session.createQuery( query ).list();

				if ( !list.isEmpty()  )
				{
				  ses.removeAttribute("providerChangeForm");
				 
				  BeanContactPPS bean = (BeanContactPPS) list.get(0);				  

				  
				  providerChangeForm.setFirst_name( bean.getFirst_name() );
				  providerChangeForm.setLast_name( bean.getLast_name() );
				  providerChangeForm.setEnterprise_name( bean.getEnterprise_name() );				  
				  providerChangeForm.setAddress( bean.getAddress() );	
				  providerChangeForm.setCity (bean.getCity() );	
				  providerChangeForm.setCountry (bean.getCountry() );	
				  providerChangeForm.setOffice_phone (bean.getOffice_phone() );	
				  providerChangeForm.setHome_phone (bean.getHome_phone() );
				  providerChangeForm.setCel_phone(bean.getCel_phone());
				  
					

				  ses.setAttribute("providerChangeForm",providerChangeForm );

				  query = " FROM BeanContactPPS u" +
					      " ORDER BY u.first_name";

				  
			      list = session.createQuery( query ).list();
			      
			      			      
			      if ( !list.isEmpty() )
			         ses.setAttribute("filterList", list );
			      else
			       {
			    	 query =  " FROM BeanContactPPS u" +
				       		  " WHERE u.id = 0";

			    	 list = session.createQuery( query ).list();
			    	 BeanContactPPS beanContactPPS= new BeanContactPPS();
			    	 list.add(beanContactPPS);	
			    	 ses.setAttribute("filterList", list );
			      }

			      ses.setAttribute("done", "main.jsp" );				  

				}
				else
				{
		
				  query =  " FROM BeanContactPPS u" +
	       		  		   " WHERE u.id = 0";

				
				  List contactList = new ArrayList();
				  BeanContactPPS beanContactPPS= new BeanContactPPS();
			  	  contactList.add(beanContactPPS);				  	  
			  	  
			  	  ses.setAttribute("filterList", contactList );
			  	  
			  	  
				}
				forward = mapping.findForward("providerChangePPS");
				
			  }
			else if ( preForm.getProcess().equals("providerDeletePPS") )
			  {
				String query =  " FROM BeanContactPPS u" +
								" WHERE u.active='Y'" +								
								" ORDER BY u.first_name";
				List list = session.createQuery( query ).list();
				
				if ( !list.isEmpty() )
				{								
			         ses.setAttribute("filterList", list );
			         ses.setAttribute("BeanContactPPS", list.get(0) );
				}
			    else
			      {
			    	 query =  " FROM BeanContactPPS u" +
				       		  " WHERE u.id = 0";
		
			    	 list = session.createQuery( query ).list();
			    	 BeanContactPPS beanContactPPS= new BeanContactPPS();
			    	 list.add(beanContactPPS);	
			    	 ses.setAttribute("filterList", list );
			      }
				
				forward = mapping.findForward("providerDeletePPS");
			  }
			else if ( preForm.getProcess().equals("providerActivatePPS") )
			  {
				String query =  " FROM BeanContactPPS u" +
								" WHERE u.active='N'" +								
								" ORDER BY u.first_name";
				List list = session.createQuery( query ).list();
				
				if ( !list.isEmpty() )
				{								
			         ses.setAttribute("filterList", list );
			         ses.setAttribute("BeanContactPPS", list.get(0) );
				}
			    else
			      {
			    	 query =  " FROM BeanContactPPS u" +
				       		  " WHERE u.id = 0";
		
			    	 list = session.createQuery( query ).list();
			    	 BeanContactPPS beanContactPPS= new BeanContactPPS();
			    	 list.add(beanContactPPS);	
			    	 ses.setAttribute("filterList", list );
			      }
				
				forward = mapping.findForward("providerActivatePPS");
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
		//forward = mapping.findForward("showBasquet");
		//forward = mapping.findForward("checkout");

		// Finish with
		return (forward);

	}
}

