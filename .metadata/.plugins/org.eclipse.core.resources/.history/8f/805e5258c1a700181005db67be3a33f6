package com.struts2.actions;

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
import java.io.*;

import com.bituos.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import com.struts2.Beans.*;
import com.struts2.forms.AvailabilitySaloonEventTypeForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class AvailabilitySaloonEventTypeAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//Recibe  valores del  jsp y se los pasa al a un objeto
		AvailabilitySaloonEventTypeForm availabilitySaloonEventTypeForm = (AvailabilitySaloonEventTypeForm) form;

		HttpSession httpSession = request.getSession();

		httpSession.removeAttribute("done");

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

			try 
			  {
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();


				//Objeto de tipo  BeanCase
				BeanQuote bean = new BeanQuote();

				String error = new String();

				/*caseNewForm.getProcess().equals()
				    obtiene  el valor de la variable process y compara con una cadena
				*/

				//si el process = "validate"
				
				String query = "nothing";
				
				if(availabilitySaloonEventTypeForm.getProcess().equals("showCalendar"))
				  {
					query = " FROM BeanQuote u" +
							" WHERE u.id_company = " + beanUser.getId_company().getId();
					
					
					if ( availabilitySaloonEventTypeForm.getCheckList().equals("on") )
				      query = query + " AND u.id_list = " + availabilitySaloonEventTypeForm.getId_list();

					if ( availabilitySaloonEventTypeForm.getCheckSaloon().equals("on") )
					  query = query + " AND u.id_saloon = " + availabilitySaloonEventTypeForm.getId_saloon();

					List list = session.createQuery( query ).list();

					httpSession.setAttribute("listCalendar", list );
					
					forward = mapping.findForward("showCalendar");
				  }
				else
				  {
					httpSession.setAttribute("done", "javascript:history.back();");
					com.bituos.Error e = new com.bituos.Error("Opcion no programada", request);
					//forward declarado en struts-config.xml
					forward = mapping.findForward("error");
				  }
			  } 
			catch (Exception m) 
			  {
				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");
				com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
				//forward declarado en struts-config.xml
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

		// Termina con
		return (forward);

	}
}

