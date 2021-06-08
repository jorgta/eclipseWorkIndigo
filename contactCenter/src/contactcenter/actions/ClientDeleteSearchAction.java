package contactcenter.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.*;

import com.tecunsa.*;
import com.tecunsa.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import contactcenter.forms.ClientDeleteSearchForm;

/**
 * @version 	1.0
 * @author
 */

public class ClientDeleteSearchAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value

		ClientDeleteSearchForm clientDeleteSearchForm = (ClientDeleteSearchForm) form;

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
								" WHERE u.name LIKE '%" + clientDeleteSearchForm.getFilter() + "%'" +
								" AND u.active='Y'";

				List list = session.createQuery( query ).list();

				if ( list.size() != 0 )
				  {
					httpSession.setAttribute("filterList", list );

					httpSession.setAttribute("done", "client/clientDelete.jsp" );
					forward = mapping.findForward("clientDelete");
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
