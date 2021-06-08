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

public class DoctorSelectAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		DoctorSelectForm dataForm = (DoctorSelectForm) form;

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
							   + " WHERE u.id =" + dataForm.getId_doctor();  
				
				java.util.List list = session.createQuery(query).list();
				
			  	BeanDoctor bean = new BeanDoctor();
			    bean = (BeanDoctor)list.get(0);
			  	
				//tx = session.beginTransaction();
		
				/*bean.setActive( 1 );					
			
				session.update( bean );*/
									
				//tx.commit();
			    httpSession.setAttribute("beanDoctorSelectedForChange", bean );
			    
			    query =  " FROM BeanRepresentative u" +						
				  		 " ORDER BY u.name";
							
				list = session.createQuery( query ).list();
				
				httpSession.setAttribute("listBeanRepresentative", list );
				
				
				query =   " FROM BeanDoctorRepresentative u" 
			   	    	+ " WHERE u.id_doctor.id = " + bean.getId() ; 
			   	    	//+ " AND u.id_representative.id =" +beanRepresentative.getId();
				
				
				java.util.List list2 = session.createQuery(query).list();
				BeanDoctorRepresentative beanDoctorRepresentative;
				if(!list2.isEmpty())
				{
				   beanDoctorRepresentative =(BeanDoctorRepresentative) list2.get(0);				   
				   BeanRepresentative  beanRepresentative = beanDoctorRepresentative.getId_representative();
				   httpSession.setAttribute("beanRepresentative", beanRepresentative );
				}
				
				
				
				forward = mapping.findForward("doctorChange");
				//httpSession.setAttribute("done", new String("pre.jsp?&process='doctorSelected'&action='pre.do'&target=''"));
		

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
