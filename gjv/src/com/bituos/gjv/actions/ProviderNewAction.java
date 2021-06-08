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
import java.text.*;

import com.bituos.*;
import com.bituos.gjv.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import com.bituos.gjv.forms.ProviderNewForm;

/**
 * @version 	1.0
 * @author
 */
public class ProviderNewAction extends Action {

	public ActionForward execute(
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response
                              ) throws Exception 
     {

	   ActionErrors errors = new ActionErrors();
	   ActionForward forward = new ActionForward();
		// return value
	   ProviderNewForm providerNewForm = (ProviderNewForm) form;

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
		  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
		  session = sessionFactory.openSession();
         
		  String query ="";
		  List list =null;
				
		  query =   " FROM BeanContactPPS u" +
				  	" WHERE u.id = '" + providerNewForm.getId()+ "'";                        

		  list = session.createQuery(query).list();


		  if ( list.isEmpty() )
		  {
			BeanContactPPS beanContactPPS = new BeanContactPPS();			
			
			beanContactPPS.setId(Integer.parseInt(providerNewForm.getId()));
			beanContactPPS.setFirst_name(providerNewForm.getFirst_name());
			beanContactPPS.setLast_name(providerNewForm.getLast_name()); 
			beanContactPPS.setEnterprise_name(providerNewForm.getEnterprise_name());  
			beanContactPPS.setAddress(providerNewForm.getAddress());
			beanContactPPS.setCity(providerNewForm.getCity()); 
			beanContactPPS.setCountry(providerNewForm.getCountry());   
			beanContactPPS.setOffice_phone(providerNewForm.getOffice_phone());
			beanContactPPS.setHome_phone(providerNewForm.getHome_phone());
			beanContactPPS.setCel_phone(providerNewForm.getCel_phone());
			beanContactPPS.setActive('Y');

            tx = session.beginTransaction();
            session.save(beanContactPPS);           
            tx.commit();
    
            forward = mapping.findForward("ok");
            httpSession.setAttribute("done", "contactsRH/contactNew.jsp" );
		  }
		  else
		  {
		    com.bituos.Error error = new com.bituos.Error("El proveedor ya existe", request);
            httpSession.setAttribute("done", "javascript:history.back();");
            forward = mapping.findForward("error");
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
