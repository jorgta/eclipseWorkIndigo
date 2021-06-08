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
import contactcenter.forms.UserSelectForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class UserSelectAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//Recibe  valores del  jsp y se los pasa al bean de tipo BeanUser
		UserSelectForm userSelectForm = (UserSelectForm) form;
		//Retorna valores del  bean al jsp
		UserChangeForm userChangeForm = new UserChangeForm();

		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		//Usuario que se logeo y que esta en sesión
		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
		  forward = mapping.findForward("login");
		else
			{
				try
				  {
					//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();

					//Recibiendo valores de userSelectForm
					BeanUser bean = (BeanUser) session.load( BeanUser.class, new Integer(userSelectForm.getId()));

					//Pasando valores al jsp
					userChangeForm.setId(bean.getId());
					userChangeForm.setName(bean.getName());
					userChangeForm.setFull_name(bean.getFull_name());
					userChangeForm.setPassword(bean.getPassword());
					userChangeForm.setAddress(bean.getAddress());
		//			userChangeForm.setRfc(bean.getRfc());
					userChangeForm.setEmail(bean.getEmail());
					userChangeForm.setTelephone(bean.getTelephone());

					httpSession.setAttribute("userChangeForm", userChangeForm );
					//forward declarado en struts-config.xml
					forward = mapping.findForward("ok");
				  }
				catch( Throwable  m)
				  {
					m.printStackTrace();
					httpSession.setAttribute("done", "javascript:history.back();");
					com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);
					  //forward  declarado en struts-config.xml
					forward = mapping.findForward("error");
				  }
				finally
				  {
					session.close();
					sessionFactory.close();
				  }
			}

		// Termina con
		return (forward);

	}
}
