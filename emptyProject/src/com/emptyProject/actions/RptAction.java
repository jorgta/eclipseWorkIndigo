package com.emptyProject.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.emptyProject.Beans.*;
import com.emptyProject.forms.*;

/**
 * @version 	1.0
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 */

import java.util.*;

import com.emptyProject.*;
import com.bituos.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import javax.servlet.http.*;

import com.crystaldecisions.report.web.viewer.*;
//import com.crystaldecisions.report.web.viewer.CrystalReportViewer;
import com.crystaldecisions.reports.reportengineinterface.JPEReportSourceFactory;
import com.crystaldecisions.sdk.occa.report.reportsource.IReportSourceFactory2;
import com.crystaldecisions.sdk.occa.report.reportsource.IReportSource;
import com.crystaldecisions.report.web.viewer.CrPrintMode;
import com.crystaldecisions.sdk.occa.report.lib.*;
import com.crystaldecisions.sdk.occa.report.data.*;
import com.crystaldecisions.sdk.occa.report.lib.PropertyBag;
//import com.crystaldecisions.xml.serialization.CrystalSAXAttributes;
//import com.crystaldecisions.report.web.viewer.CrystalReportViewerBase;

import java.io.*;


public class RptAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		RptForm rptForm = (RptForm) form;

		HttpSession httpSession = request.getSession();
							
		Config config = new Config( request );
		
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		String query;

		httpSession.removeAttribute("done" );

		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
		  forward = mapping.findForward("login");
		else 
			try 
		      {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
		

				if ( rptForm.getProcess().equals("'rptLog'") ) //log on crystal
				  {
						httpSession.setAttribute( "crystal_rptname", new String("rptLog.rpt") ) ;
						httpSession.setAttribute( "parameterCount", new Integer( 1 ) ) ;
						
						/*httpSession.setAttribute( "parameter1", new Integer(  beanUser.getId() ) ) ;
						httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
						httpSession.setAttribute( "parameterName1", new String("id_company") ) ;*/
	
						forward = mapping.findForward("printGlobal");
				  }
				else
				  {
					com.bituos.Error e = new com.bituos.Error("Opcion no programada. Consulte a soporte tecnico.", request);
					
					forward = mapping.findForward("error");
				  }
		      }
			catch( Throwable  m)
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
