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

import contactcenter.forms.ClientChangeForm;

/**
 * @version 	1.0
 * @author
 */
public class ClientChangeAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		ClientChangeForm clientChangeForm = (ClientChangeForm) form;

		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
			forward = mapping.findForward("login");
		else {

			try {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				BeanClient bean = (BeanClient) session.load( BeanClient.class, new Integer(clientChangeForm.getId()));

				bean.setName(clientChangeForm.getName().toUpperCase());
				bean.setAddress(clientChangeForm.getAddress().toUpperCase());
				bean.setRfc(clientChangeForm.getRfc().toUpperCase());
				bean.setEmail(clientChangeForm.getEmail());
				bean.setEmail1(clientChangeForm.getEmail1());
				bean.setTelephone(clientChangeForm.getTelephone());


				tx = session.beginTransaction();
				session.update( bean );
				tx.commit();

				httpSession.setAttribute("done", new String("client/clientSelect.jsp"));
				forward = mapping.findForward("ok");

			} catch (Exception m) {

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

//		else
//		   forward = mapping.findForward("ok");

		// Finish with
		return (forward);

	}
}

