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

import contactcenter.forms.SearchUserForm;

/**
 * @version 	1.0
 * @author      Bituos Tools S de RL MI
 */

public class SearchUserAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//Recibe  y retorna valores
		SearchUserForm searchUserForm = (SearchUserForm) form;

		HttpSession httpSession = request.getSession();

		//Usuario que se logeo y que esta en sesión
		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
			forward = mapping.findForward("login");
		else
		  {
			Config config = new Config( request );

			SessionFactory sessionFactory = null;
			Session    session = null;
			Transaction tx = null;
			String query;

			try
			  {

				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

					/*casetNewForm.getProcess().equals()
					obtiene  el valor de la variable process y compara con una cadena
					*/


				if ( searchUserForm.getConcept().equals("name") )
					  {
						//Lista de usuarios por nombre y activos
						query = " FROM BeanUser u" +
								" WHERE u.active='Y'" +
								" AND ( u.name LIKE '%" + searchUserForm.getFilterName().toUpperCase() + "%'" +
								"       OR " +
								"       u.full_name LIKE '%" + searchUserForm.getFilterName().toUpperCase() + "%'" +
								"     )";

						List list = session.createQuery( query ).list();

						//Pone en sesión la lista
						httpSession.setAttribute("filterList", list );

						//forward declarado en struts-config.xml
						forward = mapping.findForward("caseNew");

					  }
				else if ( searchUserForm.getConcept().equals("email") )
					{

					   //Lista de usuarios por email y activos
					  query =    " FROM BeanUser u" +
							  " WHERE u.email LIKE '%" + searchUserForm.getFilterEmail() + "%'" +
							  " AND u.active='Y'";

					    List list = session.createQuery( query ).list();

					   //Pone en sesión la lista
					    httpSession.setAttribute("filterList", list );

					   //forward declarado en struts-config.xml
					    forward = mapping.findForward("caseNew");

					}
				else if ( searchUserForm.getConcept().equals("address") )
					  {
						//Lista usuarios por dirección
						query = " FROM BeanUser u" +
							    " WHERE u.address LIKE '%" + searchUserForm.getFilterAddress().toUpperCase() + "%'" +
							    " AND u.active='Y'";

						List list = session.createQuery( query ).list();

						   //Pone en sesión la lista
						httpSession.setAttribute("filterList", list );

						  //forward declarado en struts-config.xml
						forward = mapping.findForward("caseNew");
					  }
				else
					{
						com.tecunsa.Error e = new com.tecunsa.Error("Opcion no programada. Consulte a soporte técnico.", request);
						httpSession.setAttribute("done", "main.jsp");
						//forward declarado en struts-config.xml
						forward = mapping.findForward("error");
					}



			  }
			catch( Throwable  m)
			  {
					m.printStackTrace();
					httpSession.setAttribute("done", "javascript:history.back();");
					com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
					//forward declarado en struts-config.xml
					forward = mapping.findForward("error");
			}
			finally	{
				if ( session != null )
					session.close();

				if ( sessionFactory != null )
					sessionFactory.close();
			}
		}


		// Finish with
		return (forward);

	}
}
