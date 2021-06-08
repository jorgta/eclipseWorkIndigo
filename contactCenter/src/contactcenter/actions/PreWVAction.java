package contactcenter.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import contactcenter.forms.PreWVForm;

/**
 * @version 	1.0
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 */

import contactcenter.forms.*;
import java.util.*;

import com.tecunsa.*;
import com.tecunsa.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import javax.servlet.http.*;

public class PreWVAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		PreWVForm preWVForm = (PreWVForm) form;

		HttpSession httpSession = request.getSession();
							
		Config config = new Config( request );
		
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		httpSession.removeAttribute("done" );
		httpSession.removeAttribute("list" );

		if ( preWVForm.getProcess().equals("viewFile") ) //view File
		  {
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();

				String query =  " FROM BeanFiles u" +
								" WHERE id=" + preWVForm.getParameter1();
	
				List list = session.createQuery( query ).list();
				
				if ( list.size() == 0)
				  {
					com.tecunsa.Error e = new com.tecunsa.Error("Archivo inexistente.", request);

					forward = mapping.findForward("error");
				  }
				else
				 {
				 	BeanFiles file = (BeanFiles) list.get(0);
					//next: lo que recibe el jsp son parametros y no variables en session
					httpSession.setAttribute("url", new String("http://tokio.no-ip.org") + file.getFullFileName() );
					httpSession.setAttribute("msg", file.getFileName() );

					forward = mapping.findForward("viewFile");
				 }


			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();

				com.tecunsa.Error e = new com.tecunsa.Error("Error interno (" + m.getMessage() + ")", request);

				forward = mapping.findForward("error");
			  }
			finally
			  {
				session.close();
				sessionFactory.close();
			  }
		  }
		else
		  {
			com.tecunsa.Error e = new com.tecunsa.Error("Opcion no programada. Consulte a soporte tecnico.", request);
		
			forward = mapping.findForward("error");
        
		  }

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
		// Write logic determining how the user should be forwarded.

		// Finish with
		return (forward);

	}
}
