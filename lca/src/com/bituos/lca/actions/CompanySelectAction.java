package com.bituos.lca.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bituos.lca.Beans.*;
import com.bituos.lca.forms.*;

/**
 * @version 	1.0
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 */

import java.util.*;

import com.bituos.lca.*;
import com.bituos.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import javax.servlet.http.*;

public class CompanySelectAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		CompanySelectForm companySelectForm = (CompanySelectForm) form;
		CompanyChangeForm companyChangeForm = new CompanyChangeForm();

		HttpSession httpSession = request.getSession();
							
		Config config = new Config( request );
		
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
		  forward = mapping.findForward("login");
		else 
			{
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();
	                
					BeanCompany bean = (BeanCompany) session.load( BeanCompany.class, new Integer( companySelectForm.getId() ) );
	                
					//CompanyChangeForm companyChangeForm = (CompanyChangeForm) httpSession.getAttribute("companyChangeForm");

					companyChangeForm.setName( bean.getName() );
					companyChangeForm.setAddress( bean.getAddress() );
					companyChangeForm.setId( bean.getId() );
					companyChangeForm.setRfc( bean.getRfc() );
					companyChangeForm.setFullname( bean.getFull_name() );
					
					companyChangeForm.setSmtp_server( bean.getSmtp_server() );
					companyChangeForm.setSmtp_user_name( bean.getSmtp_user_name() );
					companyChangeForm.setSmtp_password( bean.getSmtp_password() );
					companyChangeForm.setSmtp_email( bean.getSmtp_email() );
					
				    if ( bean.getSmtp_is_secure().equals("Y") )
				    	companyChangeForm.setSmtp_is_secure("on");
				    else
				    	companyChangeForm.setSmtp_is_secure("off");

				    companyChangeForm.setSmtp_port( bean.getSmtp_port());
				    companyChangeForm.setWeb_site( bean.getWeb_site());
				    

					
					httpSession.setAttribute("companyChangeForm", companyChangeForm );

					forward = mapping.findForward("companyChange");
				  }
				catch( Throwable  m)
				  {
					m.printStackTrace();
					
					com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
					
					forward = mapping.findForward("error");
				  }
				finally
				  {
					session.close();
					sessionFactory.close();
				  }
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
