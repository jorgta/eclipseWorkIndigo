package com.struts2.actions;



import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.struts2.Beans.*;
import com.bituos.*;

import org.hibernate.classic.Session;


/**
 * @version 	1.0
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 */



import java.util.*;

import com.struts2.*;
import com.opensymphony.xwork2.ActionSupport;
import com.struts2.Beans.*;
import com.bituos.*;

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


public class RptAction extends  ActionSupport  {

	
	  private String p1; 	
	  private String p2;	
	  private String p3;	  
	  private String p4;	
	  private String p5;	  
	  private String p6;	  	
	  private String p7;  	
	  private String p8;	 
	  private String p9;
	  private String p10;
	  
	  private String process;
	  
	  private HttpSession httpSession =null;
	  private HttpServletRequest request = null;
	  
	  
	public String execute()
		throws Exception {

		String forward="";
		HttpServletRequest request = ServletActionContext.getRequest();
		httpSession = request.getSession(); 

		HttpSession httpSession = request.getSession();
							
		Config config = new Config( request );
		
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		httpSession.removeAttribute("done" );

		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
		  forward = "login";		
		else if ( getProcess().equals("rptLog") ) //log on crystal
			  {
				httpSession.setAttribute( "crystal_rptname", new String("rptLog.rpt") ) ;
				httpSession.setAttribute( "parameterCount", new Integer( 1 ) ) ;
				
				httpSession.setAttribute( "parameter1", new Integer(  beanUser.getId_company().getId() ) ) ;
				httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
				httpSession.setAttribute( "parameterName1", new String("id_company") ) ;

				forward = "printGlobal";
			  }		
		else
		  {
			com.bituos.Error e = new com.bituos.Error("Opcion no programada. Consulte a soporte tecnico.", request);
			
			forward = ERROR;
		  }

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		/*if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}*/
		// Write logic determining how the user should be forwarded.

		// Finish with
		return (forward);

	}


	public String getP1() {
		return p1;
	}


	public void setP1(String p1) {
		this.p1 = p1;
	}


	public String getP2() {
		return p2;
	}


	public void setP2(String p2) {
		this.p2 = p2;
	}


	public String getP3() {
		return p3;
	}


	public void setP3(String p3) {
		this.p3 = p3;
	}


	public String getP4() {
		return p4;
	}


	public void setP4(String p4) {
		this.p4 = p4;
	}


	public String getP5() {
		return p5;
	}


	public void setP5(String p5) {
		this.p5 = p5;
	}


	public String getP6() {
		return p6;
	}


	public void setP6(String p6) {
		this.p6 = p6;
	}


	public String getP7() {
		return p7;
	}


	public void setP7(String p7) {
		this.p7 = p7;
	}


	public String getP8() {
		return p8;
	}


	public void setP8(String p8) {
		this.p8 = p8;
	}


	public String getP9() {
		return p9;
	}


	public void setP9(String p9) {
		this.p9 = p9;
	}


	public String getP10() {
		return p10;
	}


	public void setP10(String p10) {
		this.p10 = p10;
	}


	public String getProcess() {
		return process;
	}


	public void setProcess(String process) {
		this.process = process;
	}
	
	
	
	
}
