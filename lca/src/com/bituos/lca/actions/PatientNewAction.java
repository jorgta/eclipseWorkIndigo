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

public class PatientNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		
		PatientNewForm patientNewForm = (PatientNewForm) form;

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
			    
				String query =   " FROM BeanPatient u" 
							   + " WHERE u.name = '" + patientNewForm.getName().toUpperCase() +"'";  
							   
				
				java.util.List list = session.createQuery(query).list();
				
				if ( list.isEmpty() )
				  {   
					BeanPatient bean = new BeanPatient();
				  	
					tx = session.beginTransaction();
					
					bean.setName( patientNewForm.getName().toUpperCase() );
					bean.setStreet(patientNewForm.getStreet());
					bean.setExternal_number(patientNewForm.getExternal_number());
					bean.setCity(patientNewForm.getCity());
			        bean.setNotes(patientNewForm.getNotes());
					bean.setShip_street(patientNewForm.getStreet());
					bean.setShip_external_number(patientNewForm.getShip_external_number());
					bean.setShip_city(patientNewForm.getShip_city());
					bean.setEmail(patientNewForm.getEmail());
					bean.setDiscount(Double.valueOf(patientNewForm.getDiscount()));

					bean.setInvoice_street( patientNewForm.getInvoice_street() );
					bean.setInvoice_external_number(patientNewForm.getInvoice_external_number() );
					bean.setInvoice_city(patientNewForm.getInvoice_city() );
				
					bean.setAge( patientNewForm.getAge() );
					bean.setPhone( patientNewForm.getPhone() );

			        if ( patientNewForm.getIs_male().equals("on") )
			          {
			        	bean.setIs_male( 1 );
			          }
				    else
			          {
				    	bean.setIs_male( 0 );
			          }

			        session.save( bean );
					tx.commit();
					
					forward = mapping.findForward("ok");
					httpSession.setAttribute("done", new String("pre.jsp?&process='patientNew'&action='pre.do'&target=''"));
				  }
				else
				  {
				  	httpSession.setAttribute("done", "pre.jsp?&process='patientNew'&action='pre.do'&target=''" );
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
