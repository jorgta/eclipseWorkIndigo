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

import com.bituos.gjv.forms.ProviderChangeForm;


/**
 * @version 	1.0
 * @author
 */
public class ProviderChangeAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		ProviderChangeForm providerChangeForm = (ProviderChangeForm) form;

		HttpSession ses = request.getSession();

		BeanUser beanUser = (BeanUser) ses.getAttribute("beanUser");

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		if ( beanUser == null )
			forward = mapping.findForward("login");
		else {

			try {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
				
			
				if(providerChangeForm.getProcess().equals("change"))
				{
					
					
				  BeanContactPPS bean = (BeanContactPPS) session.load( BeanContactPPS.class, new Integer(providerChangeForm.getIdProvider()));
				  
				  bean.setFirst_name( providerChangeForm.getFirst_name() );
				  bean.setLast_name( providerChangeForm.getLast_name() );
				  bean.setEnterprise_name( providerChangeForm.getEnterprise_name() );				  
				  bean.setAddress( providerChangeForm.getAddress() );	
				  bean.setCity (providerChangeForm.getCity() );	
				  bean.setCountry (providerChangeForm.getCountry() );	
				  bean.setOffice_phone (providerChangeForm.getOffice_phone() );	
				  bean.setHome_phone (providerChangeForm.getHome_phone() );
				  bean.setCel_phone(providerChangeForm.getCel_phone());

			
				  tx = session.beginTransaction();
				  session.update( bean );
				
				  tx.commit();
				  
				  String query = " FROM BeanContactPPS u" +
			      		         " ORDER BY u.first_name";

		  
				  List list = session.createQuery( query ).list();
	      
	      			      
			      if ( !list.isEmpty() )
			         ses.setAttribute("filterList", list );
			      else
			       {			    	 
			    	 BeanContactPPS beanContactPPS= new BeanContactPPS();
			    	 list.add(beanContactPPS);	
			    	 ses.setAttribute("filterList", list );
			       }
				  
						          				 
				  forward = mapping.findForward("ok");
				  ses.setAttribute("done", "contactsPPS/contactChange.jsp" );
				  //forward = mapping.findForward("userChange");
				}
				
				if(providerChangeForm.getProcess().equals("select"))
				{
					//UserChangeForm userChangeForm = new UserChangeForm();
					 
					String query =  " FROM BeanContactPPS u"+
           		   					" WHERE u.id =" + providerChangeForm.getIdProvider();
									

					List list = session.createQuery( query ).list();

					if ( list.size() != 0 )
					{
					  
					  BeanContactPPS bean = (BeanContactPPS) list.get(0);
    				 

					  providerChangeForm.setFirst_name( bean.getFirst_name() );
					  providerChangeForm.setLast_name( bean.getLast_name() );
					  providerChangeForm.setEnterprise_name( bean.getEnterprise_name() );				  
					  providerChangeForm.setAddress( bean.getAddress() );	
					  providerChangeForm.setCity (bean.getCity() );	
					  providerChangeForm.setCountry (bean.getCountry() );	
					  providerChangeForm.setOffice_phone (bean.getOffice_phone() );	
					  providerChangeForm.setHome_phone (bean.getHome_phone() );
					  providerChangeForm.setCel_phone(bean.getCel_phone());
					  
					 
					 

					  ses.removeAttribute("contactChangeForm");
					  ses.setAttribute("contactChangeForm",providerChangeForm );
					  					
					  query = " FROM BeanContactPPS u" +
						      " ORDER BY u.first_name";

				      list = session.createQuery( query ).list();
				      
				      if ( !list.isEmpty() )
				         ses.setAttribute("filterList", list );
				      else
				       {
				    	  BeanContactPPS beanContactPPS= new BeanContactPPS();
				    	 list.add(beanContactPPS);	
				    	 ses.setAttribute("filterList", list );
				       }

				      
				      ses.setAttribute("done", "main.jsp" );
				      forward = mapping.findForward("providerChangePPS");
				      //System.out.println(String.valueOf(contactChangeForm.getStarting_date()));
					}
					
					
		            
					
				}
				

			} catch (Exception e) {
				e.printStackTrace();

				com.bituos.Error error = new com.bituos.Error( "Error interno, consulte a soporte tecnico. (" + e.getMessage() + ")", request);
				ses.setAttribute("done", "javascript:history.back();");

				forward = mapping.findForward("error");

			}
			finally	{
				if ( session != null )
					session.close();

				if ( sessionFactory != null )
					sessionFactory.close();
			}
		}

		// If a message is required, save the specified key(s)

		return (forward);

	}
}

