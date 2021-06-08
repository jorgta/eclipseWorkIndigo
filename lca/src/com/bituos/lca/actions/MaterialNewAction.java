package com.bituos.lca.actions;

import com.bituos.lca.Beans.*;
import com.bituos.lca.forms.*;

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
import com.bituos.lca.*;
import com.bituos.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

public class MaterialNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		MaterialNewForm materialNewForm = (MaterialNewForm) form;

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
			
			    //BeanBranch beanBranch =  (BeanBranch) session.load( BeanBranch.class, new Integer( userNewForm.getId_branch() ) );
				String query =   " FROM BeanMaterial u" 
							   + " WHERE u.description = '" +  materialNewForm.getDescription().toUpperCase() + "'"; 
							 
				
				java.util.List list = session.createQuery(query).list();
				
				if ( list.isEmpty() )
				  {   
				  	BeanMaterial bean = new BeanMaterial();
				  	
					tx = session.beginTransaction();
					//bean.setId_company( beanUser.getId_company() );
					bean.setDescription(materialNewForm.getDescription().toUpperCase());
					
					if( materialNewForm.getActive()== null || materialNewForm.getActive().equals("0"))
					{
						bean.setActive(0);						
					}
					else
					{
						bean.setActive(1);
					}
				
					if(materialNewForm.getHaslabel()== null || materialNewForm.getHaslabel().equals("0")  )
					{
						bean.setHaslabel(0);					
					}
					else
					{
						bean.setHaslabel(1);	
					}
					
					
					session.save( bean );
					tx.commit();
					
					forward = mapping.findForward("ok");
					httpSession.setAttribute("done", new String("pre.do?process='materialNew'"));
				  }
				else
				  {
				  	httpSession.setAttribute("done", "pre.do?process='materialNew'" );
					com.bituos.Error error = new com.bituos.Error("Ese material ya existe, debe ingresar otro nombre.", request);
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
