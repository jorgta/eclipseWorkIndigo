package contactcenter.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import contactcenter.forms.RptCaseForm;


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
import com.tecunsa.Utils;

public class RptCaseAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		RptCaseForm rptCaseForm = (RptCaseForm) form;

		HttpSession httpSession = request.getSession();

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		httpSession.removeAttribute("done");

		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
		  forward = mapping.findForward("login");
		else 
		  {
			httpSession.setAttribute( "crystal_rptname", new String("rptCase.rpt") ) ;
			httpSession.setAttribute( "parameterCount", new Integer(1) ) ;

			//id de caso
			httpSession.setAttribute( "parameter1", new Integer( rptCaseForm.getId_case() ) );
			httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
			httpSession.setAttribute( "parameterName1", new String("id_case") ) ;

//			httpSession.setAttribute( "parameter2", new Integer( rptCaseForm.getId_case() ) );
//			httpSession.setAttribute( "parameterType2", new String("Integer") ) ;
//			httpSession.setAttribute( "parameterName2", new String("id_case_sub_notes") ) ;
																	
		    forward = mapping.findForward("print");
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
