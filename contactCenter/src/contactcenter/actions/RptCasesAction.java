package contactcenter.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import contactcenter.forms.RptCasesForm;

/**
 * @version 	1.0
 * @author 		Bituos Tools S de RL MI
 */

import java.util.*;

import com.tecunsa.*;
import com.tecunsa.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import javax.servlet.http.*;


public class RptCasesAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// Obtiene y retorna valores
		RptCasesForm rptForm = (RptCasesForm) form;

		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		httpSession.removeAttribute("done");

		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
		  forward = mapping.findForward("login");
		else if ( rptForm.getProcess().equals("rptUsers111") )  //Para reportes que necesitan parametros
		{
			//Recibe de rptForm el nombre del reporte a desplegar
			String rptname = rptForm.getProcess();

			httpSession.setAttribute("crystal_rptname", new String(rptname) + ".rpt" );
			//forward declarado en struts-config.xml
			forward = mapping.findForward("print");
		}

		else
		{
			//Recibe de rptForm el nombre del reporte a desplegar
			String rptname = rptForm.getProcess();

			httpSession.setAttribute("crystal_rptname", new String(rptname) + ".rpt" );
			//forward declarado en struts-config.xml
			forward = mapping.findForward("print");
		}



		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}


		// Termina con
		return (forward);

	}
}
