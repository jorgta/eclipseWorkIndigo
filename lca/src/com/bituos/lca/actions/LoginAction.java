package com.bituos.lca.actions;

import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.bituos.lca.Beans.*;
import com.bituos.lca.forms.*;

import java.util.*;

import com.bituos.lca.*;
import com.bituos.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;


/**
 * @version 	1.0
 * @author      CIRM
 */

/**
 * @version 	2.0
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 */


/*test   *

import java.sql.*;

import java.util.*;

import java.io.*;

/**/

public class LoginAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		
		throws Exception 
		  {
			ActionErrors errors = new ActionErrors();
			ActionForward forward = new ActionForward();
			// return value
	
			LoginForm loginForm = (LoginForm) form;
	
			HttpSession httpSession = request.getSession();
								
			Config config = new Config( request );
			
			SessionFactory sessionFactory = null;
			Session    session = null;
			Transaction tx = null;
			
			
			/*test
			String jdbcdriver;
	
			String jdbcurl;
	
			String username;
	
			String password;
	
			String sql;
	
			username = "dsanchez";
	
			password = "d";
	
			sql = "select * from log";
	  
			jdbcdriver = "com.mysql.jdbc.Driver";
	
			jdbcurl = "jdbc:mysql://tokio.no-ip.org/truckAdmin";
	
			Class.forName(jdbcdriver);
	
			Connection c;
	
			c = DriverManager.getConnection(jdbcurl, username, password);
	
	
			Statement s = c.createStatement();
	
			ResultSet rs = s.executeQuery(sql);
	
			ResultSetMetaData rsmd = rs.getMetaData();
	
	
			int numberOfColumns = rsmd.getColumnCount();
	
	
			 
			/* */
					
			try 
			  {
				sessionFactory = config.getConfiguration(request).buildSessionFactory();
				session = sessionFactory.openSession();			
				
				String query =   " FROM BeanUser u" 
				              /* + " WHERE u.id_company.name = '" + loginForm.getCompany() + "'" */ 
							   + " WHERE   u.name = '" + loginForm.getUsername().toUpperCase() + "'"  
	                           + " AND   u.password = '" + loginForm.getPassword() + "'" 
							   + " AND u.id_company.active = 'Y'";  
					
				List list = session.createQuery(query).list();
					
				if (!list.isEmpty()){   
					
					BeanUser beanUser = new BeanUser ();
					
					beanUser = (BeanUser) list.get(0);	
					
					if (beanUser.getActive().equals("Y")) 
					  {  
						SecurityUtil util = new SecurityUtil();
					
						//util.createProcessList(request, beanUser.getId() );
						if ( util.createProcessList(request, beanUser.getId()) != 0 )
						  {
							com.bituos.Error error = new com.bituos.Error( "Error aplicando las directivas de seguridad", request);
									
							errors.add("unknown", new org.apache.struts.action.ActionError("error.unknow"));
							httpSession.setAttribute("done", new String("login.jsp"));
						  }
						else
						  {
							httpSession.setAttribute("beanUser", beanUser ); 
							new Log().registerEvent("login", 1, request, session);

						/*	new com.bituos.Mail( "sanchez_aroche@yahoo.es",
													"", 
													"truckAdmin: Login: " + beanUser.getName() + " (" + beanUser.getEmail() + ")",
													request);*/

						  }
						
					  } 
				    else
				      { 
						errors.add("username", new org.apache.struts.action.ActionError("error.notactive.user"));    
						com.bituos.Error error = new com.bituos.Error("Usuario inactivo.", request);
						httpSession.setAttribute("done", new String("login.jsp"));
				      }
	
				}
				else
				  {
					  errors.add("username", new org.apache.struts.action.ActionError("error.notexist.username"));
					  com.bituos.Error error = new com.bituos.Error("Usuario inexistente o contraseña incorrecta.", request);
					  httpSession.setAttribute("done", new String("login.jsp"));
				  }
		  } 
		catch (Exception e) 
		  {
			e.printStackTrace();
			
			com.bituos.Error error = new com.bituos.Error( e.getMessage(), request);
							
			forward = mapping.findForward("error");
						
			errors.add("unknown", new org.apache.struts.action.ActionError("error.unknow"));
		  }
		finally	
		  {
			if ( session != null )
				 session.close(); 
				  
			if ( sessionFactory != null )
				 sessionFactory.close();
		  }

		// Write logic determining how the user should be forwarded.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			forward = mapping.findForward("error");
		}
		else 
		   forward = mapping.findForward("links");


		// Finish with
		return (forward);

	}
}
