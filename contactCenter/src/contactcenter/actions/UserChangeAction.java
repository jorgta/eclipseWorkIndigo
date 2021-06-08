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

import contactcenter.forms.UserChangeForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class UserChangeAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		// Recibe  valores del  jsp y se los pasa al bean de tipo BeanUser
		UserChangeForm userChangeForm = (UserChangeForm) form;

		HttpSession httpSession = request.getSession();

		//Usuario que se logeo y que esta en sesión
		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		if ( beanUser == null )
			forward = mapping.findForward("login");
		else {

			try {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				//Recibiendo valores de userChangeForm
				BeanUser bean = (BeanUser) session.load( BeanUser.class, new Integer(userChangeForm.getId()));

				//Pasando al bean los valores provenientes del userChangeForm
				bean.setId(userChangeForm.getId());
				bean.setFull_name( userChangeForm.getFull_name().toUpperCase() );
				bean.setPassword( userChangeForm.getPassword());
				bean.setAddress( userChangeForm.getAddress().toUpperCase() );
				//bean.setRfc(userChangeForm.getRfc().toUpperCase());
				bean.setEmail(userChangeForm.getEmail());
				bean.setTelephone(userChangeForm.getTelephone());

				//Actualiza la informacion el la BD
				tx = session.beginTransaction();
				session.update( bean );
				tx.commit();

				httpSession.setAttribute("done", new String("user/userSelect.jsp"));
				//forward userNew declarado en struts-config.xml
				forward = mapping.findForward("ok");

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

