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

import contactcenter.forms.UserNewForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class UserNewAction extends Action {


	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		// Recibe  valores del  jsp y se los pasa al bean de tipo BeanUser
		UserNewForm userNewForm = (UserNewForm) form;

		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("done");

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

				//Lista de los nombres de usurio para que no esten repetidos
				String query = " FROM BeanUser u" +
							   " WHERE u.name = '" + userNewForm.getName().toUpperCase() + "'";

				List list = session.createQuery(query).list();

				if ( list.isEmpty() )
				  {
					//Objeto bean de tipo BeanUser
					BeanUser bean = new BeanUser();
					BeanUserUser beanUserUser = new BeanUserUser();
					BeanUser beanSupervisor = (BeanUser) session.load( BeanUser.class, new Integer(userNewForm.getId_supervisor()));

					//Pasando al bean los valores provenientes del userNewForm
					bean.setName(userNewForm.getName().toUpperCase());
					bean.setFull_name(userNewForm.getFull_name().toUpperCase() );
					bean.setPassword(userNewForm.getPassword());
					bean.setActive("Y");
					bean.setAddress( userNewForm.getAddress().toUpperCase() );
					//bean.setRfc(userNewForm.getRfc().toUpperCase());
					bean.setEmail(userNewForm.getEmail());
					bean.setTelephone(userNewForm.getTelephone());

					
					//Guarda la informacion el la BD
					tx = session.beginTransaction();
					
					session.save(bean);

					beanUserUser.setId_user( bean );
					beanUserUser.setId_supervisor( beanSupervisor );
					session.save(beanUserUser);
					
					
					tx.commit();

					//forward userNew declarado en struts-config.xml
					forward = mapping.findForward("ok");
					httpSession.setAttribute("done", "user/userNew.jsp" );
				  }
				else
				  {
					com.tecunsa.Error error = new com.tecunsa.Error("El usuario ya existe", request);
					httpSession.setAttribute("done", "javascript:history.back();");
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
