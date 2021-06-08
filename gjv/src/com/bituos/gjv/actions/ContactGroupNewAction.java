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

import com.bituos.gjv.forms.ContactGroupNewForm;

/**
 * @version 	1.0
 * @author
 */
public class ContactGroupNewAction extends Action {

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
	   ContactGroupNewForm contactGroupNewForm = (ContactGroupNewForm) form;

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
				
		  /*query =   " FROM BeanContactRH u" +
				  	" WHERE u.id = '" + contactNewForm.getId()+ "'";*/
		  
		  query =   " FROM BeanContactRHGroup u" +
		  			" WHERE u.NSS =" + contactGroupNewForm.getNSS();
		  
		  /*query = " FROM  BeanInOut o"+
		  		  " WHERE o.idContactRH.first_name=" + contactNewForm.getFirst_name() +
		  		  " AND o.idContactRH.last_name=" + contactNewForm.getLast_name() +
		  		  " AND o.ending_date ='" + contactNewForm.getEnding_date() + "'";*/
		 
		  list = session.createQuery(query).list();


		  if ( list.isEmpty() )
		  {
			BeanContactRHGroup beanContactRHGroup = new BeanContactRHGroup();
			BeanInOutGroup beanInOutGroup = new BeanInOutGroup();
			
			//beanContactRH.setId(Integer.parseInt(contactNewForm.getId()));
			beanContactRHGroup.setFirst_name(contactGroupNewForm.getFirst_name());
			beanContactRHGroup.setLast_name(contactGroupNewForm.getLast_name());            
			beanContactRHGroup.setAddress(contactGroupNewForm.getAddress());
			beanContactRHGroup.setColony(contactGroupNewForm.getColony());            
			beanContactRHGroup.setPhone(contactGroupNewForm.getPhone());
			beanContactRHGroup.setCel_phone(contactGroupNewForm.getCel_phone());
			beanContactRHGroup.setActive('Y');

			//System.out.println(contactGroupNewForm.getMarried());
			if(contactGroupNewForm.getMarried().equals("true"))
				beanContactRHGroup.setMarried('Y');
			else
				beanContactRHGroup.setMarried('N');
			
	
			beanContactRHGroup.setNSS(contactGroupNewForm.getNSS());       
			beanContactRHGroup.setWife_name(contactGroupNewForm.getWife_name());
			beanContactRHGroup.setDate_of_birth(Utils.StrToDate(contactGroupNewForm.getDate_of_birth()));
			
			beanInOutGroup.setIdContactRHGroup(beanContactRHGroup);
			Date fechaing= new Date();
			beanInOutGroup.setStarting_date(fechaing);
			//beanInOut.setStarting_date(Utils.StrToDate(contactNewForm.getStarting_date()));
			//beanInOut.setEnding_date(Utils.StrToDate(contactNewForm.getEnding_date()));
			
            
            tx = session.beginTransaction();
            session.save(beanContactRHGroup);
            session.save(beanInOutGroup);
            tx.commit();
    
            forward = mapping.findForward("ok");
            httpSession.setAttribute("done", "contactsRHGroup/contactNew.jsp" );
		  }
		  else
		  {
		    com.bituos.Error error = new com.bituos.Error("El contacto ya existe", request);
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
