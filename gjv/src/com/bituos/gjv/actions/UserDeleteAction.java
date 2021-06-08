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

import com.bituos.gjv.forms.UserDeleteForm;


public class UserDeleteAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
	
		UserDeleteForm userDeleteForm = (UserDeleteForm) form;

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
		
			  BeanUser bean = (BeanUser) session.load( BeanUser.class, new Integer( userDeleteForm.getId() ) );
    
			  String query =  " FROM BeanUser u" +
			    			  " WHERE u.id = " + userDeleteForm.getId() +
			    			  " AND u.active='Y'";
            
			  List list = session.createQuery( query ).list();
    
              if(list.size() != 0)
              {              
                tx = session.beginTransaction();
                bean.setActive("N");
                session.update(bean);
                tx.commit();
    
             
                httpSession.removeAttribute("filterList");

                query = " FROM BeanUser u" +
					    " WHERE u.active='Y'"+
                        " AND u.id <> " +beanUser.getId();
                
                list = session.createQuery( query ).list();
                httpSession.setAttribute("filterList", list );
    
    
                httpSession.setAttribute("done", new String("user/userDelete.jsp"));
                forward = mapping.findForward("ok");
              
                String userSeleced="";	
                httpSession.setAttribute("userSeleced", userSeleced);
		
              }
              else 
              {
            	com.bituos.Error e = new com.bituos.Error("El identificador de usuario no es válido o ya ha sido eliminado.", request);
                httpSession.setAttribute("done", "user/userDelete.jsp");
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
