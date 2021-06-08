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

import contactcenter.forms.ClientDeleteForm;

/**
 * @version 	1.0
 * @author
 */
public class ClientDeleteAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		ClientDeleteForm clientDeleteForm = (ClientDeleteForm) form;

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

				String query =  " FROM BeanClient u" +
								" WHERE u.id = " + clientDeleteForm.getId() +
								" AND u.active='Y'";

				List list = session.createQuery( query ).list();
				if ( list.size() == 0 )
					{
					  com.tecunsa.Error e = new com.tecunsa.Error("El identificador de cliente no es válido o ya ha sido eliminado.", request);
					  httpSession.setAttribute("done", "client/clientDelete.jsp");
					  forward = mapping.findForward("error");
					}
				else
				  {
					BeanClient bean = (BeanClient)list.get(0);

					// borrar relacion entre user vs client
					query = " FROM BeanUserClient u" +
							" WHERE u.id_client.id = " + clientDeleteForm.getId();

					list = session.createQuery( query ).list();
					
					Iterator iter = list.iterator();
					
					while ( iter.hasNext() )
					  {
						session.delete( (BeanUserClient) iter.next() );
					  }
	
					// borrar client
					query = " FROM BeanClient u" +
							" WHERE u.id = " + clientDeleteForm.getId() +
							" AND u.id NOT IN ( SELECT DISTINCT c.id_client.id" +
							"                   FROM BeanCase c " +
							"                 )" ;

					list = session.createQuery( query ).list();

					tx = session.beginTransaction();

					if ( list.size() > 0 )
					  {
						session.delete( bean );
					  }
					else
					  {
						bean.setActive("N");
						session.update( bean );
					  }


					tx.commit();

					query =  	" FROM BeanClient u" +
								" WHERE u.active = 'Y'" +
								" AND u.id NOT IN ( SELECT DISTINCT c.id_client.id" +
								"                   FROM BeanCase c " +
								"                 )" +
								" ORDER BY u.id";
					list = session.createQuery( query ).list();

					httpSession.setAttribute("filterList", list );

					httpSession.setAttribute("done", "client/clientDelete.jsp" );
					forward = mapping.findForward("ok");
				  }

			  }
			catch( Throwable  m)
			  {
				if ( tx != null)
				  tx.rollback();
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


		// Finish with
		return (forward);

	}
}

