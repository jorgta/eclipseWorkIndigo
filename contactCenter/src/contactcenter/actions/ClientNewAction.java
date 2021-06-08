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

import contactcenter.forms.ClientNewForm;

/**
 * @version 	1.0
 * @author
 */
public class ClientNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		ClientNewForm clientNewForm = (ClientNewForm) form;

		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("done");

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

				String query =   " FROM BeanClient u" +
							     " WHERE u.name = '" + clientNewForm.getName().toUpperCase() + "'";

				List list = session.createQuery(query).list();

				if ( list.isEmpty() )
				  {
					BeanClient bean = new BeanClient();

					bean.setName(clientNewForm.getName().toUpperCase() );
					bean.setActive("Y");
					bean.setAddress(clientNewForm.getAddress().toUpperCase());
					bean.setRfc(clientNewForm.getRfc().toUpperCase());
					bean.setEmail(clientNewForm.getEmail());
					bean.setEmail1(clientNewForm.getEmail1());
					bean.setTelephone(clientNewForm.getTelephone());

					tx = session.beginTransaction();
					session.save( bean );
					tx.commit();

					forward = mapping.findForward("ok");
					httpSession.setAttribute("done", "/contactCenter/pre.do?process=clientNew" );
				  }
				else
				  {
					httpSession.setAttribute("done", "javascript:history.back();");
					com.tecunsa.Error error = new com.tecunsa.Error("El cliente ya existe", request);
					forward = mapping.findForward("error");
				  }

			  }
			catch (Exception m)
			  {
				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");
				com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
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

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.


		return (forward);

	}
}

