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
import contactcenter.forms.*;

import java.util.*;

import com.tecunsa.*;
import com.tecunsa.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import contactcenter.forms.ClientChangeForm;
import contactcenter.forms.ClientSelectForm;

/**
 * @version 	1.0
 * @author
 */
public class ClientSelectAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		ClientSelectForm clientSelectForm = (ClientSelectForm) form;
		ClientChangeForm clientChangeForm = new ClientChangeForm();

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

				String query =  " FROM BeanClient u" +
								" WHERE u.id = " + clientSelectForm.getId() +
								" AND u.active = 'Y'" +
								" ORDER BY u.id";

				List list1 = session.createQuery( query ).list();

				if ( list1.size() != 0 )
					{

						BeanClient bean = (BeanClient) session.load( BeanClient.class, new Integer( clientSelectForm.getId() ) );

						clientChangeForm.setId(bean.getId());
						clientChangeForm.setName(bean.getName());
						clientChangeForm.setAddress(bean.getAddress());
						clientChangeForm.setRfc(bean.getRfc());
						clientChangeForm.setEmail(bean.getEmail());
						clientChangeForm.setTelephone(bean.getTelephone());
						clientChangeForm.setEmail1(bean.getEmail1());

						httpSession.setAttribute("clientChangeForm", clientChangeForm );
						httpSession.setAttribute("done", "main.jsp" );
						forward = mapping.findForward("ok");
					}
				else {
					com.tecunsa.Error e = new com.tecunsa.Error("El cliente no existe o está dado de baja", request);
					httpSession.setAttribute("done", "client/clientSelect.jsp");
					forward = mapping.findForward("error");
				}
			} catch (Throwable  m) {
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
