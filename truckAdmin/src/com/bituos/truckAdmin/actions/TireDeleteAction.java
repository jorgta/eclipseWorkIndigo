package com.bituos.truckAdmin.actions;

import com.bituos.truckAdmin.Beans.*;
import com.bituos.truckAdmin.forms.*;

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
import com.bituos.truckAdmin.*;
import com.bituos.*;
import java.util.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

public class TireDeleteAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		TireDeleteForm tireDeleteForm = (TireDeleteForm) form;

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
			
				if (tireDeleteForm.getProcess().equals("delete"))
				  {
				
					String query =   " FROM BeanTire u" 
								   + " WHERE u.id  = '" + tireDeleteForm.getId_tire() + "'" +
								     " AND u.active= 'Y'";
					java.util.List list = session.createQuery(query).list();					
					
	
					 list = session.createQuery( query ).list();
					  if(!list.isEmpty())
					    {	
						  BeanTire bean = (BeanTire) session.load( BeanTire.class, new Integer( tireDeleteForm.getId_tire() ) );
						  tx = session.beginTransaction();
						  bean.setActive("N");
						  session.update(bean);
						  tx.commit();
							 
					      httpSession.setAttribute("listTireDelete", list );
					      //forward = mapping.findForward("tireDelete");
					      httpSession.setAttribute("done", new String("./pre.jsp?&process='tireDelete'&action='pre.do'&target=''"));
					      forward = mapping.findForward("ok");	
					    }
					  else
					    {
						  list = new ArrayList<BeanTire>();
						  //httpSession.setAttribute("tireDelete", list );
						  
					      httpSession.setAttribute("done", "./pre.jsp?&process='tireDelete'&action='pre.do'&target=''" );
						  com.bituos.Error error = new com.bituos.Error("No hay Neumaticos para eliminar.", request);
						  forward = mapping.findForward("error");
					    }
				
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
