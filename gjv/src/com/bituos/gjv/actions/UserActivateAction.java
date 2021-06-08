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

import com.bituos.gjv.forms.UserActivateForm;

/**
 * @version 	1.0
 * @author
 */
public class UserActivateAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		UserActivateForm userActivateForm = (UserActivateForm) form;

		HttpSession httpSession = request.getSession();

		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
			forward = mapping.findForward("login");
		else {
			Config config = new Config( request );

			SessionFactory sessionFactory = null;
			Session    session = null;
			Transaction tx = null;

			try {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
      if(userActivateForm.getProcess().equals("userActivate"))
				{
            BeanUser bean = (BeanUser) session.load( BeanUser.class, new Integer( userActivateForm.getId() ) );
    
            String query =  " FROM BeanUser u" +
                    " WHERE u.id = " + userActivateForm.getId() +
                    " AND u.active='N'";
            
            List list = session.createQuery( query ).list();
            
            if ( list.size() != 0 ){
              bean.setActive("Y");
              tx = session.beginTransaction();
              session.update( bean );
              tx.commit();
    
              //query to return  no active users
    
              query =  " FROM BeanUser u" +
                      " WHERE u.active='N'";
              list = session.createQuery( query ).list();
              httpSession.setAttribute("filterList", list );
    
    
              httpSession.setAttribute("done", new String("user/userActivate.jsp"));
              forward = mapping.findForward("ok");
    
            }
            else {
            	com.bituos.Error e = new com.bituos.Error("El identificador de usuario no es válido o ya ha sido activado.", request);
              httpSession.setAttribute("done", "user/userActivate.jsp");
              forward = mapping.findForward("error");
            }
        }
        else
        {
          forward = mapping.findForward("loop");
          String userSeleced=userActivateForm.getId();	
          httpSession.setAttribute("userSeleced", userSeleced);
          
        }
				
			} catch (Exception e) {
				e.printStackTrace();

				com.bituos.Error error = new com.bituos.Error( "Error interno, consulte a soporte tecnico. (" + e.getMessage() + ")", request);
				httpSession.setAttribute("done", "javascript:history.back();");

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
		// into the request for use by the <struts:errors> tag.

		//else
		  // forward = mapping.findForward("ok");

		// Finish with
		return (forward);

	}
}

