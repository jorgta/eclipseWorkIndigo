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

public class DoctorChangeAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		DoctorChangeForm doctorNewForm = (DoctorChangeForm) form;

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
			
				BeanDoctor  beanDoctor =(BeanDoctor)httpSession.getAttribute( "beanDoctorSelectedForChange" );
				
				String query =   " FROM BeanDoctor u" 
							   + " WHERE u.id = " + beanDoctor.getId();  
				
				java.util.List list = session.createQuery(query).list();
				
				 
					
			  	BeanDoctor bean = (BeanDoctor)list.get(0);
			  	BeanRepresentative beanRepresentative =  (BeanRepresentative) session.load( BeanRepresentative.class, new Integer( doctorNewForm.getId_representative() ) );
				
			  	
				tx = session.beginTransaction();
				
				bean.setName( doctorNewForm.getName().toUpperCase() );
				bean.setAddress( doctorNewForm.getAddress() );
				bean.setNotes( doctorNewForm.getNotes() );
				bean.setEmail( doctorNewForm.getEmail() );
				bean.setPhone( doctorNewForm.getPhone() );
				bean.setActive( 1 );
				bean.setPhone_cel(doctorNewForm.getPhone_cel());
			
				session.update( bean );
				

				
			
				query =   " FROM BeanDoctorRepresentative u" 
					   	+ " WHERE u.id_doctor.id = " + beanDoctor.getId(); 
					   //	+ " AND u.id_representative.id =" +beanRepresentative.getId();
					   
				
				list = session.createQuery(query).list();
				if (list.isEmpty())
				{
					BeanDoctorRepresentative beanDoctorRepresentative = new BeanDoctorRepresentative();
					beanDoctorRepresentative.setId_doctor(bean);
					beanDoctorRepresentative.setId_representative(beanRepresentative);
					session.save( beanDoctorRepresentative );
				}else
				{
					
					//list.get(0);
					
					
					query =   " FROM BeanDoctorRepresentative u" 
					   	    + " WHERE u.id_doctor.id = " + beanDoctor.getId()  
					    	+ " AND u.id_representative.id =" +beanRepresentative.getId();
					   
				
					java.util.List list2 = session.createQuery(query).list();
				    
				    if(list2.isEmpty())
				    {
				    	BeanDoctorRepresentative beanDoctorRepresentative =(BeanDoctorRepresentative) list.get(0);
				    	beanDoctorRepresentative.setId_representative(beanRepresentative);
				    	session.update( beanDoctorRepresentative );
				    }else
				    {
				    	//no hacer nada
				    }
				    
				    
					
				}
				
							
				
				
				
				tx.commit();
				
				forward = mapping.findForward("ok");
				httpSession.setAttribute("done", new String("doctor/doctorNew.jsp"));
				 
			
				
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
