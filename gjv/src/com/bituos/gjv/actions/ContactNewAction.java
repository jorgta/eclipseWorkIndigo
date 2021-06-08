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

import com.bituos.gjv.forms.ContactNewForm;

/**
 * @version 	1.0
 * @author
 */
public class ContactNewAction extends Action {

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
	   ContactNewForm contactNewForm = (ContactNewForm) form;

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
		  
		  query =   " FROM BeanContactRH u" +
		  			" WHERE u.NSS =" + contactNewForm.getNSS();
		  
		  /*query = " FROM  BeanInOut o"+
		  		  " WHERE o.idContactRH.first_name=" + contactNewForm.getFirst_name() +
		  		  " AND o.idContactRH.last_name=" + contactNewForm.getLast_name() +
		  		  " AND o.ending_date ='" + contactNewForm.getEnding_date() + "'";*/
		 
		  list = session.createQuery(query).list();


		  if ( list.isEmpty() )
		  {
			BeanContactRH beanContactRH = new BeanContactRH();
			BeanInOut beanInOut = new BeanInOut();
			
			//beanContactRH.setId(Integer.parseInt(contactNewForm.getId()));
			beanContactRH.setFirst_name(contactNewForm.getFirst_name());
			beanContactRH.setLast_name(contactNewForm.getLast_name());            
			beanContactRH.setAddress(contactNewForm.getAddress());
			beanContactRH.setColony(contactNewForm.getColony());            
			beanContactRH.setPhone(contactNewForm.getPhone());
			beanContactRH.setCel_phone(contactNewForm.getCel_phone());
			beanContactRH.setActive('Y');

			//System.out.println(contactNewForm.getMarried());
			if(contactNewForm.getMarried().equals("true"))
			  beanContactRH.setMarried('Y');
			else
			  beanContactRH.setMarried('N');
			
	
			beanContactRH.setNSS(contactNewForm.getNSS());       
			beanContactRH.setWife_name(contactNewForm.getWife_name());
			beanContactRH.setDate_of_birth(Utils.StrToDate(contactNewForm.getDate_of_birth()));
			
			beanInOut.setIdContactRH(beanContactRH);
			Date fechaing= new Date();
			beanInOut.setStarting_date(fechaing);
			//beanInOut.setStarting_date(Utils.StrToDate(contactNewForm.getStarting_date()));
			//beanInOut.setEnding_date(Utils.StrToDate(contactNewForm.getEnding_date()));
			
            
            tx = session.beginTransaction();
            session.save(beanContactRH);
            session.save(beanInOut);
            tx.commit();
    
            forward = mapping.findForward("ok");
            httpSession.setAttribute("done", "contactsRH/contactNew.jsp" );
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
