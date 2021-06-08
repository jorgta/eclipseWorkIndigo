package com.bituos.gjv.actions;

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
import com.bituos.gjv.Beans.*;
import com.bituos.gjv.forms.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import com.bituos.gjv.forms.SearchForm;
import java.io.File;
import java.io.FilenameFilter;

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

		/*
		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
			forward = mapping.findForward("login");
		else {*/
			Config config = new Config( request );

			SessionFactory sessionFactory = null;
			Session    session = null;
			Transaction tx = null;
			String query;
			BeanUser beanUser = (BeanUser) httpSession.getAttribute( "beanUser" );
			try 
			{
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				if ( searchForm.getFilter().equals("")  )
				  {
					com.bituos.Error e = new com.bituos.Error("Debe de especificar lo que desea buscar.", request);
					httpSession.setAttribute("done", "javascript:history.back();");
					forward = mapping.findForward("error");
				  }
				else if ( searchForm.getConcept().equals("user") )  //user
				  {
					if ( searchForm.getForward().equals("delete") )
					  {					
						
						//Pone en sesión una lista de usuarios que son buscados por nombre
						query = " FROM BeanUser u" +
							    " WHERE u.name LIKE '" + searchForm.getFilter() + "%'" +
							    " AND u.active='Y'" +
							    " AND u.id <> " +  beanUser.getId();

						List list = session.createQuery( query ).list();

						httpSession.setAttribute("filterList", list );
						  //forward declarado en struts-config.xml
						forward = mapping.findForward("userDelete");

					  }
					else if ( searchForm.getForward().equals("asign") )
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
						  forward = mapping.findForward("asign");
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
								" WHERE u.name LIKE '" + searchForm.getFilter() + "%'" +
								" AND u.active='N'" +
								" AND u.id <> " +  beanUser.getId();

						List list = session.createQuery( query ).list();

						httpSession.setAttribute("filterList", list );
						//forward declarado en struts-config.xml
						forward = mapping.findForward("userActivate");

					  }
					else if ( searchForm.getForward().equals("change") )
					  {
						//Pone en sesión una lista de usuarios que son buscados por nombre para ser activados
					    query = " FROM BeanUser u" +
								" WHERE u.name LIKE '" + searchForm.getFilter() + "%'"; //+
								/*" AND u.active='N'" +
								" AND u.id <> " +  beanUser.getId();*/

						List list = session.createQuery( query ).list();
						
						if(!list.isEmpty())
						{
						  httpSession.setAttribute("filterList", list );
						  
						}
						else
						{
						  BeanUser bean = new BeanUser();
						  list.add(bean);
						  UserChangeForm userChangeForm = new UserChangeForm();
						  httpSession.setAttribute("userChangeForm",userChangeForm );
						  httpSession.setAttribute("filterList", list );
						}
						
						forward = mapping.findForward("userChange");
					  }
					else if ( searchForm.getForward().equals("keyword") )
					  {
						File file = new File("C:\\wssdwork\\bituos\\WebContent");
						String[] textFiles = file.list();
					    List fileList = new ArrayList();

						for (int j = 0; j < textFiles.length; j++)
						  {
							if (textFiles[j].endsWith(".jsp"))
							  fileList.add( (textFiles[j]) );
						  }


						httpSession.setAttribute("fileList", fileList );
						//forward declarado en struts-config.xml
						forward = mapping.findForward("searchResult");
					  }
					else
					{
						com.bituos.Error e = new com.bituos.Error("Opcion no programada. Consulte a soporte técnico.", request);
						httpSession.setAttribute("done", "/bituos/buy/search.jsp");
						//forward declarado en struts-config.xml
						forward = mapping.findForward("error");
					}
				  }

				else if ( searchForm.getConcept().equals("contact") )  //product
				  {
					if ( searchForm.getForward().equals("delete") )
					  {
						// product list
						query = " FROM BeanContactRH u" +
								" WHERE (u.first_name LIKE '" + searchForm.getFilter() + "%'" +	//description
								"        OR u.id LIKE '%" + searchForm.getFilter() + "%'" +				//id
								"        )" +
								" AND u.active='Y'";


						List list = session.createQuery( query ).list();

						if(!list.isEmpty())
						{
						  httpSession.removeAttribute("filterList");
						  httpSession.setAttribute("filterList", list );
						}
						else
						{				
						  list = new ArrayList();
					  	  BeanContactRH beanContactRH= new BeanContactRH();
					  	  list.add(beanContactRH);	
					  	  httpSession.removeAttribute("filterList");
					  	  httpSession.setAttribute("filterList", list );
						}						
							

						forward = mapping.findForward("contactDelete");
					  }
					else if ( searchForm.getForward().equals("change") )
					 {
						// product list
						query = " FROM BeanContactRH u" +
								" WHERE (u.first_name LIKE '" + searchForm.getFilter() + "%'" +	//description
								"        OR u.id LIKE '%" + searchForm.getFilter() + "%'" +				//id
								"        )";

						
						List list = session.createQuery( query ).list();
						
						if(!list.isEmpty())
						{
						  httpSession.removeAttribute("filterList");
						  httpSession.setAttribute("filterList", list );
						}
						else
						{				
						  list = new ArrayList();
					  	  BeanContactRH beanContactRH= new BeanContactRH();
					  	  list.add(beanContactRH);
					  	  httpSession.removeAttribute("filterList");
					  	  httpSession.setAttribute("filterList", list );
						}						
						
						forward = mapping.findForward("contactChange");
					 }
					else if ( searchForm.getForward().equals("activate") )
					 {
						// product list
					   query = " FROM BeanContactRH u" +
					   		   " WHERE (u.first_name LIKE '" + searchForm.getFilter() + "%'" +	//description
					           "        OR u.id LIKE '%" + searchForm.getFilter() + "%'" +				//id
					           "        )" +
					           " AND u.active='N'";


					   List list = session.createQuery( query ).list();

					   if(!list.isEmpty())
						{
						  httpSession.removeAttribute("filterList");
						  httpSession.setAttribute("filterList", list );
						}
					   else
						{				
						  list = new ArrayList();
					  	  BeanContactRH beanContactRH= new BeanContactRH();
					  	  list.add(beanContactRH);	
					  	  httpSession.removeAttribute("filterList");
					  	  httpSession.setAttribute("filterList", list );
						}		

					   forward = mapping.findForward("contactActivate");
					 }
					else if ( searchForm.getForward().equals("note") )
					 {
						// product list
					   query = " FROM BeanContactRH u" +
					   		   " WHERE (u.first_name LIKE '" + searchForm.getFilter() + "%'" +	//description
					           "        OR u.id LIKE '%" + searchForm.getFilter() + "%'" +				//id
					           "        )";


					   List list = session.createQuery( query ).list();

					   if(!list.isEmpty())
						{
						  httpSession.removeAttribute("filterList");
						  httpSession.setAttribute("filterList", list );
						}
					   else
						{				
						  list = new ArrayList();
					  	  BeanContactRH beanContactRH= new BeanContactRH();
					  	  list.add(beanContactRH);	
					  	  httpSession.removeAttribute("filterList");
					  	  httpSession.setAttribute("filterList", list );
						}		

					   forward = mapping.findForward("contactNoteNew");
					 }		
					else if ( searchForm.getForward().equals("noteChange") )
					 {
						// product list
					   query = " FROM BeanContactRH u" +
					   		   " WHERE (u.first_name LIKE '" + searchForm.getFilter() + "%'" +	//description
					           "        OR u.id LIKE '%" + searchForm.getFilter() + "%'" +				//id
					           "        )";


					   List list = session.createQuery( query ).list();

					   if(!list.isEmpty())
						{
						  httpSession.removeAttribute("filterList");
						  httpSession.setAttribute("filterList", list );
						}
					   else
						{				
						  list = new ArrayList();
					  	  BeanContactRH beanContactRH= new BeanContactRH();
					  	  list.add(beanContactRH);	
					  	  httpSession.removeAttribute("filterList");
					  	  httpSession.setAttribute("filterList", list );
						}		

					   forward = mapping.findForward("contactNoteChange");
					 }					
				  }
				else if ( searchForm.getConcept().equals("contactGroup") )  //product
				  {
					if ( searchForm.getForward().equals("delete") )
					  {
						// product list
						query = " FROM BeanContactRHGroup u" +
								" WHERE (u.first_name LIKE '" + searchForm.getFilter() + "%'" +	//name
								"        OR u.id LIKE '%" + searchForm.getFilter() + "%'" +				//id
								"        )" +
								" AND u.active='Y'";


						List list = session.createQuery( query ).list();

						if(!list.isEmpty())
						{
						  httpSession.removeAttribute("filterList");
						  httpSession.setAttribute("filterList", list );
						}
						else
						{				
						  list = new ArrayList();
					  	  BeanContactRHGroup beanContactRH= new BeanContactRHGroup();
					  	  list.add(beanContactRH);
					  	  
					  	  httpSession.removeAttribute("filterList");
					  	  httpSession.setAttribute("filterList", list );
						}						
							

						forward = mapping.findForward("contactGroupDelete");
														
					  }
					else if ( searchForm.getForward().equals("change") )
					 {
						// product list
						query = " FROM BeanContactRHGroup u" +
								" WHERE (u.first_name LIKE '" + searchForm.getFilter() + "%'" +	//description
								"        OR u.id LIKE '%" + searchForm.getFilter() + "%'" +				//id
								"        )";

						
						List list = session.createQuery( query ).list();
						
						if(!list.isEmpty())
						{
						  httpSession.removeAttribute("filterList");
						  httpSession.setAttribute("filterList", list );
						}
						else
						{				
						  list = new ArrayList();
					  	  BeanContactRHGroup beanContactRH= new BeanContactRHGroup();
					  	  list.add(beanContactRH);
					  	  httpSession.removeAttribute("filterList");
					  	  httpSession.setAttribute("filterList", list );
						}						
						
						forward = mapping.findForward("contactGroupChange");
					 }
					else if ( searchForm.getForward().equals("activate") )
					 {
						// product list
					   query = " FROM BeanContactRHGroup u" +
					   		   " WHERE (u.first_name LIKE '" + searchForm.getFilter() + "%'" +	//description
					           "        OR u.id LIKE '%" + searchForm.getFilter() + "%'" +				//id
					           "        )" +
					           " AND u.active='N'";


					   List list = session.createQuery( query ).list();

						if(!list.isEmpty())
						{
						  httpSession.removeAttribute("filterList");
						  httpSession.setAttribute("filterList", list );
						}
					   else
						{				
						  list = new ArrayList();
					  	  BeanContactRHGroup beanContactRH= new BeanContactRHGroup();
					  	  list.add(beanContactRH);	
					  	  httpSession.removeAttribute("filterList");
					  	  httpSession.setAttribute("filterList", list );
						}		

					   forward = mapping.findForward("contactGroupActivate");
					 }
					else if ( searchForm.getForward().equals("note") )
					 {
						// product list
					   query = " FROM BeanContactRHGroup u" +
					   		   " WHERE (u.first_name LIKE '" + searchForm.getFilter() + "%'" +	//description
					           "        OR u.id LIKE '%" + searchForm.getFilter() + "%'" +				//id
					           "        )";


					   List list = session.createQuery( query ).list();

						if(!list.isEmpty())
						{
						  httpSession.removeAttribute("filterList");
						  httpSession.setAttribute("filterList", list );
						}
					   else
						{				
						  list = new ArrayList();
					  	  BeanContactRHGroup beanContactRH= new BeanContactRHGroup();
					  	  list.add(beanContactRH);	
					  	  
					  	  httpSession.removeAttribute("filterList");
					  	  httpSession.setAttribute("filterList", list );
						}		

					   forward = mapping.findForward("contactGroupNoteNew"); //contactGroupNoteChange
					 }	
					else if ( searchForm.getForward().equals("noteChange") )
					 {
						// product list
					   query = " FROM BeanContactRHGroup u" +
					   		   " WHERE (u.first_name LIKE '" + searchForm.getFilter() + "%'" +	//description
					           "        OR u.id LIKE '%" + searchForm.getFilter() + "%'" +				//id
					           "        )";


					   List list = session.createQuery( query ).list();

					   if(!list.isEmpty())
						{
						  httpSession.removeAttribute("filterList");
						  httpSession.setAttribute("filterList", list );
						}
					   else
						{				
						  list = new ArrayList();
						  BeanContactRHGroup beanContactRH= new BeanContactRHGroup();
					  	  list.add(beanContactRH);	
					  	  httpSession.removeAttribute("filterList");
					  	  httpSession.setAttribute("filterList", list );
						}		

					   forward = mapping.findForward("contactGroupNoteChange");
					 }					
					
					else if ( searchForm.getForward().equals("note") )
					 {
						// product list
					   query = " FROM BeanContactRHGroup u" +
					   		   " WHERE (u.first_name LIKE '" + searchForm.getFilter() + "%'" +	//description
					           "        OR u.id LIKE '%" + searchForm.getFilter() + "%'" +				//id
					           "        )";


					   List list = session.createQuery( query ).list();

						if(!list.isEmpty())
						{
						  httpSession.removeAttribute("filterList");
						  httpSession.setAttribute("filterList", list );
						}
					   else
						{				
						  list = new ArrayList();
					  	  BeanContactRHGroup beanContactRH= new BeanContactRHGroup();
					  	  list.add(beanContactRH);	
					  	  
					  	  httpSession.removeAttribute("filterList");
					  	  httpSession.setAttribute("filterList", list );
						}		

					   forward = mapping.findForward("contactGroupNoteChange"); //
					 }
					
				  }	
				else if ( searchForm.getConcept().equals("provider") )  //product
				  {
					if ( searchForm.getForward().equals("delete") )
					  {
						// product list
						query = " FROM BeanContactPPS u" +
								" WHERE (u.first_name LIKE '%" + searchForm.getFilter() + "%'" +	//description
								"        OR u.id LIKE '%" + searchForm.getFilter() + "%'" +				//id
								"        )" +
								" AND u.active='Y'";


						List list = session.createQuery( query ).list();

						if(!list.isEmpty())
						  httpSession.setAttribute("filterList", list );
						else
						{				
						  list = new ArrayList();
						  BeanContactPPS beanContactPPS= new BeanContactPPS();
					  	  list.add(beanContactPPS);	
					  	  httpSession.setAttribute("filterList", list );
						}						
							

						forward = mapping.findForward("providerDelete");
					  }
					else if ( searchForm.getForward().equals("change") )
					 {
						// product list
						query = " FROM BeanContactPPS u" +
								" WHERE (u.first_name LIKE '%" + searchForm.getFilter() + "%'" +	//description
								"        OR u.id LIKE '%" + searchForm.getFilter() + "%'" +				//id
								"        )";

						
						List list = session.createQuery( query ).list();
						
						if(!list.isEmpty())
						  httpSession.setAttribute("filterList", list );
						else
						{				
						  list = new ArrayList();
						  BeanContactPPS beanContactPPS= new BeanContactPPS();
					  	  list.add(beanContactPPS);	
					  	  httpSession.setAttribute("filterList", list );
						}						
						
						forward = mapping.findForward("providerChange");
					 }
					else if ( searchForm.getForward().equals("activate") )
					 {
						// product list
					   query = " FROM BeanContactPPS u" +
					   		   " WHERE (u.first_name LIKE '%" + searchForm.getFilter() + "%'" +	//description
					           "        OR u.id LIKE '%" + searchForm.getFilter() + "%'" +				//id
					           "        )" +
					           " AND u.active='N'";


					   List list = session.createQuery( query ).list();

					   if(!list.isEmpty())
						  httpSession.setAttribute("filterList", list );
					   else
						{				
						  list = new ArrayList();
						  BeanContactPPS beanContactPPS= new BeanContactPPS();
					  	  list.add(beanContactPPS);	
					  	  httpSession.setAttribute("filterList", list );
						}		

					   forward = mapping.findForward("providerActivate");
					 }
				  }
				else
				{
					com.bituos.Error e = new com.bituos.Error("Opcion no programada. Consulte a soporte técnico.", request);
					httpSession.setAttribute("done", "/gjv/login.jsp");
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
		//}  //beanUser exists

		// Termina con
		return (forward);

	}
}
