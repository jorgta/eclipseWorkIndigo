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

public class DoctorNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		DoctorNewForm doctorNewForm = (DoctorNewForm) form;

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
			
				String query =   " FROM BeanDoctor u" 
							   + " WHERE u.name = '" + doctorNewForm.getName().toUpperCase() + "'";  
				
				java.util.List list = session.createQuery(query).list();
				
				if ( list.isEmpty() )
				  {   
				  	BeanDoctor bean = new BeanDoctor();
				  	BeanRepresentative beanRepresentative =  (BeanRepresentative) session.load( BeanRepresentative.class, new Integer( doctorNewForm.getId_representative() ) );
					BeanDoctorRepresentative beanDoctorRepresentative = new BeanDoctorRepresentative();
				  	
					tx = session.beginTransaction();
					
					bean.setName( doctorNewForm.getName().toUpperCase() );
					bean.setAddress( doctorNewForm.getAddress() );
					bean.setNotes( doctorNewForm.getNotes() );
					bean.setEmail( doctorNewForm.getEmail() );
					bean.setPhone( doctorNewForm.getPhone() );
					bean.setActive( 1 );
					bean.setPhone_cel(doctorNewForm.getPhone_cel());
				
					session.save( bean );
					
					beanDoctorRepresentative.setId_doctor(bean);
					beanDoctorRepresentative.setId_representative(beanRepresentative);
					
					session.save( beanDoctorRepresentative );
					
					tx.commit();
					
					forward = mapping.findForward("ok");
					httpSession.setAttribute("done", new String("doctor/doctorNew.jsp"));
				  }
				else
				  {
				  	httpSession.setAttribute("done", "user/userNew.jsp" );
					com.bituos.Error error = new com.bituos.Error("Ese medico ya existe, debe seleccionar otro.", request);
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
