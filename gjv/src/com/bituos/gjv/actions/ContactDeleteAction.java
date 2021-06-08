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

import com.bituos.gjv.forms.ContactDeleteForm;


public class ContactDeleteAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
	
		ContactDeleteForm contactDeleteForm = (ContactDeleteForm) form;

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
		
			  if(contactDeleteForm.getProcess().equals("delete"))
			  {
				//BeanContactRH bean = (BeanContactRH) session.load( BeanContactRH.class, new Integer( contactDeleteForm.getId() ) );
				BeanContactRH bean;				
			    String query =  " FROM BeanContactRH u" +
			    			    " WHERE u.id = " + contactDeleteForm.getId() +
			    			    " AND u.active='Y'";
            
			    List list = session.createQuery( query ).list();
    
                if(!list.isEmpty())
                {              
                  BeanInOut beanInOut;
                  bean = (BeanContactRH)list.get(0);
                  query =  " FROM BeanInOut o" +
                  		   " WHERE o.idContactRH.id ='" + bean.getId() + "'" +
                  		   " AND o.ending_date is null";
                  
                  list = session.createQuery( query ).list();
                  beanInOut = ((BeanInOut)list.get(0));
                  Date fechaend= new Date();
                  
                  tx = session.beginTransaction();
                  bean.setActive('N');                  
                  beanInOut.setEnding_date(fechaend);
                  session.update(bean);
                  session.update(beanInOut);
                  tx.commit();
    
                  httpSession.removeAttribute("BeanContactRH");
                  httpSession.setAttribute("BeanContactRH", bean);
                  
                  httpSession.removeAttribute("filterList");
                  httpSession.removeAttribute("filterList");

                  query = " FROM BeanContactRH u" +
				  	      " WHERE u.active='Y'";
                          
                  list = session.createQuery( query ).list();
                  if ( !list.isEmpty() )
                  {
                	  httpSession.removeAttribute("filterList");
                	  httpSession.setAttribute("filterList", list);
                	  httpSession.removeAttribute("BeanContactRH");
                	  httpSession.setAttribute("BeanContactRH", list.get(0) );
                  }
 			      else
 			       {
 			    	 query =  " FROM BeanContactRH u" +
 				       		  " WHERE u.id = 0";

 			    	 list = session.createQuery( query ).list();
 			    	 BeanContactRH beanContactRH= new BeanContactRH();
 			    	 list.add(beanContactRH);	
 			    	 
 			    	 httpSession.removeAttribute("filterList");
 			    	 httpSession.setAttribute("filterList", list );
 			       }
                  
                  
    
    
                  forward = mapping.findForward("ok");
                  httpSession.setAttribute("done", "contactsRH/contactDelete.jsp" );
                          
                
                                
			  
                }
              else 
               {
            	  com.bituos.Error e = new com.bituos.Error("El identificador de contacto no es válido o ya ha sido eliminado.", request);
                  httpSession.setAttribute("done", "contactsRH/contactDelete.jsp");
                  forward = mapping.findForward("error");
                }   
			  }
			  else if(contactDeleteForm.getProcess().equals("select"))
			  {
				String query =  " FROM BeanContactRH u" +
  			    				" WHERE u.id = " + contactDeleteForm.getId() +
  			    				" AND u.active='Y'";

				List list = session.createQuery( query ).list();
				
				if(!list.isEmpty())
				{              
				  httpSession.removeAttribute("BeanContactRH");
				  httpSession.setAttribute("BeanContactRH", list.get(0));
				}
			  	
				forward = mapping.findForward("contactDeleteRH");
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
