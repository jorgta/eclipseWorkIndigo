package contactcenter.actions;

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

import com.tecunsa.*;
import com.tecunsa.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import contactcenter.forms.LoginForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class LoginAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		LoginForm loginForm = (LoginForm) form;

		HttpSession httpSession = request.getSession();

		httpSession.removeAttribute("configuration");
		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		try {
			sessionFactory = config.getConfiguration(request).buildSessionFactory();
			session = sessionFactory.openSession();

			//Verifica que el usuario exista
			String query =   " FROM BeanUser u"
						   + " WHERE u.name = '" + loginForm.getName().toUpperCase() + "'"
						   + " AND   u.password = '" + loginForm.getPassword() + "'";

			List list = session.createQuery(query).list();

			if (!list.isEmpty()){

				BeanUser beanUser = new BeanUser ();

				beanUser = (BeanUser) list.get(0);

				if (beanUser.getActive().equals("Y"))
				  {
					SecurityUtil util = new SecurityUtil();

					if ( util.createProcessList(request, beanUser.getId() ) != 0 )
						{
						  com.tecunsa.Error error = new com.tecunsa.Error( "Error aplicando las directivas de seguridad", request);

						  httpSession.setAttribute("done", new String("login.jsp"));
						//forward declarado en struts-config.xml
						  forward = mapping.findForward("error");
						}
				    else
						{
						  httpSession.setAttribute("beanUser", beanUser );
						  new Log().registerEvent("login", 1, request);
						  //forward declarado en struts-config.xml
						  forward = mapping.findForward("ok");
						
						  Background background = new Background( request );
						}
				  }
				else
				  {
					errors.add("name", new org.apache.struts.action.ActionError("error.notactive.user"));
					com.tecunsa.Error error = new com.tecunsa.Error("Usuario inactivo.", request);
					httpSession.setAttribute("done", new String("login.jsp"));
					//forward declarado en struts-config.xml
					forward = mapping.findForward("error");
				  }

			}
			else
			  {
				  errors.add("name", new org.apache.struts.action.ActionError("error.notexist.username"));
				  //com.tecunsa.Error error = new com.tecunsa.Error("Usuario inexistente o contraseña incorrecta.", request);
				  com.tecunsa.Error error = new com.tecunsa.Error("Usuario inexistente (" + loginForm.getName() + ") o contraseña incorrecta.", request);
				  httpSession.setAttribute("done", new String("login.jsp"));

				  forward = mapping.findForward("error");
			  }
		} catch (Exception e) {
			e.printStackTrace();

			com.tecunsa.Error error = new com.tecunsa.Error( "Error interno, consulte a soporte tecnico. (" + e.getMessage() + ")", request);
			httpSession.setAttribute("done", "javascript:history.back();");
			//forward declarado en struts-config.xml
			forward = mapping.findForward("error");
		}
		finally	{
			if ( session != null )
				 session.close();

			if ( sessionFactory != null )
				 sessionFactory.close();
		}


		// Termina con
		return (forward);

	}
}