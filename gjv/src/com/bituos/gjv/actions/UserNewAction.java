package com.bituos.gjv.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionActivationListener;
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

import com.bituos.gjv.forms.UserNewForm;

/**
 * @version 	1.0
 * @author
 */
public class UserNewAction extends Action {

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
	   UserNewForm userNewForm = (UserNewForm) form;

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
				
		  query =   " FROM BeanUser u" +
				  	" WHERE u.name = '" + userNewForm.getName()+ "'";                        

		  list = session.createQuery(query).list();


		  if ( list.isEmpty() )
		  {
            BeanUser bean = new BeanUser();

            bean.setName(userNewForm.getName());
            bean.setFull_name(userNewForm.getFull_name());
            bean.setPassword(userNewForm.getPassword());
            bean.setActive("Y");
            bean.setAddress(userNewForm.getAddress());
            bean.setRfc(userNewForm.getRfc().toUpperCase());      
            bean.setEmail(userNewForm.getEmail());
            bean.setTelephone(userNewForm.getPhone_home());
            tx = session.beginTransaction();
            session.save(bean);
            tx.commit();
    
            forward = mapping.findForward("ok");
            httpSession.setAttribute("done", "user/userNew.jsp" );
		  }
		  else
		  {
		    com.bituos.Error error = new com.bituos.Error("El usuario ya existe", request);
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
