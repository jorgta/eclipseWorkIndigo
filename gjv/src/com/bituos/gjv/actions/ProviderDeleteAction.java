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

import com.bituos.gjv.forms.ProviderDeleteForm;


public class ProviderDeleteAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
	
		ProviderDeleteForm providerDeleteForm = (ProviderDeleteForm) form;

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
		
			  if(providerDeleteForm.getProcess().equals("delete"))
			  {
				BeanContactPPS bean = (BeanContactPPS) session.load( BeanContactPPS.class, new Integer( providerDeleteForm.getId() ) );
    
			    String query =  " FROM BeanContactPPS u" +
			    			    " WHERE u.id = " + providerDeleteForm.getId() +
			    			    " AND u.active='Y'";
            
			    List list = session.createQuery( query ).list();
    
                if(!list.isEmpty())
                {              
                  tx = session.beginTransaction();
                  bean.setActive('N');
                  session.update(bean);
                  tx.commit();
    
                  httpSession.setAttribute("BeanContactPPS", bean);
                  
                  httpSession.removeAttribute("filterList");

                  query = " FROM BeanContactPPS u" +
				  	      " WHERE u.active='Y'";
                          
                
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
                  httpSession.setAttribute("done", "contactsPPS/contactDelete.jsp" );
                          
                
                                
			  
                }
              else 
               {
            	  com.bituos.Error e = new com.bituos.Error("El identificador de contacto no es válido o ya ha sido eliminado.", request);
                  httpSession.setAttribute("done", "contactsRH/contactDelete.jsp");
                  forward = mapping.findForward("error");
                }   
			  }
			  else if(providerDeleteForm.getProcess().equals("select"))
			  {
				String query =  " FROM BeanContactPPS u" +
  			    				" WHERE u.id = " + providerDeleteForm.getId() +
  			    				" AND u.active='Y'";

				List list = session.createQuery( query ).list();
				
				if(!list.isEmpty())
				{              
				  httpSession.setAttribute("BeanContactPPS", list.get(0));
				}
			  	
				forward = mapping.findForward("providerDeletePPS");
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

		return (forward);

	}
}
