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

import contactcenter.forms.UserActivateForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
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

		// Recibe  valores del  jsp y se los pasa al bean de tipo BeanUser
		UserActivateForm userActivateForm = (UserActivateForm) form;

		HttpSession httpSession = request.getSession();

		//Usuario que se logeo y que esta en sesión
		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
			forward = mapping.findForward("login");
		else {
			Config config = new Config( request );

			SessionFactory sessionFactory = null;
			Session    session = null;
			Transaction tx = null;

			try {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				//Lista de usuarios inactivos
				String query =  " FROM BeanUser u" +
							" WHERE u.id = " + userActivateForm.getId() +
							" AND u.active='N'";

				List list = session.createQuery( query ).list();

				if ( list.size() != 0 ){

					//Recibiendo valores de userActivateForm
					BeanUser bean = (BeanUser) session.load( BeanUser.class, new Integer( userActivateForm.getId() ) );

					//Asigna un valor al campo active del objeto
					bean.setActive("Y");

					//Actualiza la informacion el la BD
					tx = session.beginTransaction();
					session.update( bean );
					tx.commit();



					//Actualiza la lista de usuarios inactivos
					query =  " FROM BeanUser u" +
						      " WHERE u.active='N'";

					list = session.createQuery( query ).list();
					httpSession.setAttribute("filterList", list );


					httpSession.setAttribute("done", new String("user/userActivate.jsp"));
					//forward userNew declarado en struts-config.xml
					forward = mapping.findForward("ok");

				}
				else {
					com.tecunsa.Error e = new com.tecunsa.Error("El identificador de usuario no es válido o ya ha sido activado.", request);
					httpSession.setAttribute("done", "user/userActivate.jsp");
					//forward userNew declarado en struts-config.xml
					forward = mapping.findForward("error");
				}

			} catch (Exception e) {
				e.printStackTrace();

				com.tecunsa.Error error = new com.tecunsa.Error( "Error interno, consulte a soporte tecnico. (" + e.getMessage() + ")", request);
				httpSession.setAttribute("done", "javascript:history.back();");
				//forward userNew declarado en struts-config.xml
				forward = mapping.findForward("error");
			}
			finally	{
				if ( session != null )
					session.close();

				if ( sessionFactory != null )
					sessionFactory.close();
			}
		}


		// Termina con
		return (forward);

	}
}

