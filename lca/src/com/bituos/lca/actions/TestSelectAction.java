package com.bituos.lca.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

public class TestSelectAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		
		TestSelectForm dataForm = (TestSelectForm) form;

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

			try 
			  {
				sessionFactory = config.getConfiguration(request).buildSessionFactory();
				session = sessionFactory.openSession();
			     
				String query =   " FROM BeanTest u" 
						       + " WHERE u.id = " + dataForm.getId_test();  
						   
				java.util.List list = session.createQuery(query).list();
				
				if ( !list.isEmpty() )
				  {   
					
					BeanTest beanTestForChange = (BeanTest)list.get(0);
					httpSession.setAttribute("beanTestForChange", beanTestForChange );
					
					/*query =  " FROM BeanTest u" +
					//" WHERE u.active = 'Y'" +
	  				 " ORDER BY u.name";
					
					list = session.createQuery( query ).list();*/

								    
				  //TestParameters
					query =  " FROM BeanTestParameter u" +
							 " WHERE u.id_test = " + dataForm.getId_test() +
							 " AND u.active = 1 " +
			  				 " ORDER BY u.id";
						
					list = session.createQuery( query ).list();
			
				    httpSession.setAttribute("listTestParameter", list );			    
			   
					if(!list.isEmpty())
					{
					  BeanTestParameter beanTestParameterCurrent= (BeanTestParameter)list.get(0);
					  httpSession.setAttribute("beanTestParameterCurrent",beanTestParameterCurrent);
					  
	            	  
	            		  
            		  query =  " FROM BeanParameterLimits u" +
							   " WHERE u.id_test_parameter = " + beanTestParameterCurrent.getId() +								  
			  				   " ORDER BY u.id";
					
				      list = session.createQuery( query ).list();
				      httpSession.removeAttribute("beanParameterLimitList");
    	              httpSession.setAttribute("beanParameterLimitList", list );
	            		  
		            
					}else
					{
						httpSession.removeAttribute("listTestParameter");
						/*httpSession.removeAttribute("beanParameterLimitList");
						httpSession.removeAttribute("beanTestParameterCurrent");*/
						
						
					}
										
					forward = mapping.findForward("testChange");
					//httpSession.setAttribute("done", new String("pre.jsp?&process='patientNew'&action='pre.do'&target=''"));
				  }
				else
				  {
				  	httpSession.setAttribute("done", "pre.jsp?&process='testChange'&action='pre.do'&target=''" );
				  	//httpSession.setAttribute("done", "javascript:history.back();" );
				  	
					com.bituos.Error error = new com.bituos.Error("No existe un analisis con ese nombre.", request);
					forward = mapping.findForward("error");
				  }
					
				  
		
			  } 
			catch (Exception e) 
			  {

                e.printStackTrace();
                
				// Report the error using the appropriate name and ID.
                
                String err_desc = e.getMessage(); 
                		
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
