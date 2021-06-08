package contactcenter.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import contactcenter.forms.RptForm;

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


public class RptAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// Obtiene y retorna valores
		RptForm rptForm = (RptForm) form;

		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		httpSession.removeAttribute("done");

		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
		  forward = mapping.findForward("login");
		else if ( rptForm.getProcess().equals("rptCases") )  //llamada de reportes
		   {
			 try
				{
				  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				  session = sessionFactory.openSession();
	
				 //Lista  de tipos de casos
				  String query =  " FROM BeanTypeCaseStatus u" +
								  " WHERE u.id = u.id"+
								  " ORDER BY u.id";
	
				  List list = session.createQuery( query ).list();
				  httpSession.setAttribute("typeCaseStatusList",list);
				  //forward  declarado en struts-config.xml
				  forward = mapping.findForward("rptCases");
	
				}
			 catch( Throwable  m)
				{
				  m.printStackTrace();
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
//		else if ( rptForm.getProcess().equals("rptCase") )  //Para reportes que necesitan parametros
//		{
//			forward = mapping.findForward("rptCase");
//		}
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
