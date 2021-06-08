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


import javax.servlet.http.*;
import com.eventAdmin.*;
import com.struts2.Beans.*;
import com.struts2.forms.*;
import com.bituos.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

public class UserNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		UserNewForm userNewForm = (UserNewForm) form;

		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("done");
									
		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
		  forward = mapping.findForward("login");
		else
		  {
			Config config = new Config( request );
		
			SessionFactory sessionFactory = null;
			Session    session = null;
			Transaction tx = null;

			try {
				sessionFactory = config.getConfiguration(request).buildSessionFactory();
				session = sessionFactory.openSession();
			
				String query =   " FROM BeanUser u" 
							   + " WHERE u.id_company = " +  beanUser.getId_company().getId()  
							   + " AND   u.name = '" + userNewForm.getName().toUpperCase() + "'";  
				
				java.util.List list = session.createQuery(query).list();
				
				if ( list.isEmpty() )
				  {   
				  	BeanUser bean = new BeanUser();
				  	
					tx = session.beginTransaction();
					bean.setId_company( beanUser.getId_company() );
					bean.setName( userNewForm.getName().toUpperCase() );
					bean.setPassword( userNewForm.getPassword() );
					bean.setRfc( userNewForm.getRfc() );
					bean.setAddress( userNewForm.getAddress() );
					bean.setEmail( userNewForm.getEmail() );
					bean.setActive( "Y" );
				
					session.save( bean );
					tx.commit();
					
					forward = mapping.findForward("ok");
					httpSession.setAttribute("done", new String("user/userNew.jsp"));
				  }
				else
				  {
				  	httpSession.setAttribute("done", "user/userNew.jsp" );
					com.bituos.Error error = new com.bituos.Error("Ese usuario ya existe, debe seleccionar otro.", request);
					forward = mapping.findForward("error");
				  }
				
				// do something here

			} catch (Exception e) {

                e.printStackTrace();
                
				// Report the error using the appropriate name and ID.
				com.bituos.Error error = new com.bituos.Error( e.getMessage(), request);
				errors.add("name", new ActionError("id"));

			}
			finally	{
				if ( session != null )
					 session.close(); 
				  
				if ( sessionFactory != null )
					 sessionFactory.close();
			}

		  }          
 


		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}

		// Finish with
		return (forward);

	}
}
