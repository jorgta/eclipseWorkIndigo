package com.bituos.gjv.actions;

import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import java.util.*;

import com.bituos.*;
import com.bituos.gjv.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import com.bituos.gjv.forms.ProviderActivateForm;

/**
 * @version 	1.0
 * @author
 */
public class ProviderActivateAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		ProviderActivateForm providerActivateForm = (ProviderActivateForm) form;

		HttpSession httpSession = request.getSession();

		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
			forward = mapping.findForward("login");
		else {
			Config config = new Config( request );

			SessionFactory sessionFactory = null;
			Session    session = null;
			Transaction tx = null;

		 try 
		  {
			  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
			  session = sessionFactory.openSession();
			if(providerActivateForm.getProcess().equals("activate"))
			 {
				BeanContactPPS bean = (BeanContactPPS) session.load( BeanContactPPS.class, new Integer( providerActivateForm.getId() ) );
    
                String query =  " FROM BeanContactPPS u" +
                                " WHERE u.id = " + providerActivateForm.getId() +
                                " AND u.active='N'";
            
                List list = session.createQuery( query ).list();
            
                if ( !list.isEmpty() )
                {
                  bean.setActive('Y');
                  tx = session.beginTransaction();
                  session.update( bean );
                  tx.commit();
    
              //query to return  no active users
    
                  query =  " FROM BeanContactPPS u" +
                      	   " WHERE u.active='N'";
                  list = session.createQuery( query ).list();
                  //httpSession.setAttribute("filterList", list );
                  
                  if ( !list.isEmpty() )
                  {
                	  httpSession.setAttribute("filterList", list);
                	  httpSession.setAttribute("BeanContactPPS", list.get(0) );
                  }
 			      else
 			       {
 			    	 
 			    	 BeanContactPPS beanContactPPS= new BeanContactPPS();
 			    	 list.add(beanContactPPS);	
 			    	 httpSession.setAttribute("filterList", list );
 			       }
    
    
                  forward = mapping.findForward("ok");
                  httpSession.setAttribute("done", "contactsPPS/contactActivate.jsp" );
    
                }
               else 
                {
                  com.bituos.Error e = new com.bituos.Error("El identificador de contacto no es válido o ya ha sido activado.", request);
                  httpSession.setAttribute("done", "contactsPPS/contactActivate.jsp");
                  forward = mapping.findForward("error");
                }                        
            
             }
			if(providerActivateForm.getProcess().equals("select"))
  			{
              String query =  " FROM BeanContactPPS u" +
              		   		  " WHERE u.id = " + providerActivateForm.getId() +
                              " AND u.active='N'";

              List list = session.createQuery( query ).list();
            	
              if(!list.isEmpty())
			   {              
				 httpSession.setAttribute("BeanContactPPS", list.get(0));
			   }
			  	
			  forward = mapping.findForward("providerActivatePPS");
              
             
  			}
           				
		} 
	   catch (Exception e) 
	    {
		  e.printStackTrace();

		  com.bituos.Error error = new com.bituos.Error( "Error interno, consulte a soporte tecnico. (" + e.getMessage() + ")", request);
		  httpSession.setAttribute("done", "javascript:history.back();");

		  forward = mapping.findForward("error");
	     }
	   finally	
	     {
			if ( session != null )
			  session.close();

			if ( sessionFactory != null )
			  sessionFactory.close();
		  }
		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		//else
		  // forward = mapping.findForward("ok");

		// Finish with
		return (forward);

	}
}

