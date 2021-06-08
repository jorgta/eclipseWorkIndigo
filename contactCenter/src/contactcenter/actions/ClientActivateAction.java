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

import contactcenter.forms.ClientActivateForm;

/**
 * @version 	1.0
 * @author
 */
public class ClientActivateAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		ClientActivateForm clientActivateForm = (ClientActivateForm) form;

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

				String query =  " FROM BeanClient u" +
								" WHERE u.id = " + clientActivateForm.getId() +
								" AND u.active='N'";

				List list = session.createQuery( query ).list();

				if ( list.size() != 0 )
				{
						BeanClient bean = (BeanClient)list.get(0);
						bean.setActive("Y");

						tx = session.beginTransaction();
						session.update( bean );
						tx.commit();

					//refresh the list to appear update

					query =  " FROM BeanClient u" +
							" WHERE u.active='N'";
					list = session.createQuery( query ).list();

					httpSession.setAttribute("filterList", list );


						httpSession.setAttribute("done", "client/clientActivate.jsp" );
						forward = mapping.findForward("ok");
				}
				else {
					com.tecunsa.Error e = new com.tecunsa.Error("El identificador de cliente no es válido o ya está activo.", request);
					httpSession.setAttribute("done", "client/clientActivate.jsp");
					forward = mapping.findForward("error");
				}
			}
			catch( Throwable  m)
			{
					m.printStackTrace();
					httpSession.setAttribute("done", "javascript:history.back();");
					com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
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


		// Finish with
		return (forward);

	}
}
