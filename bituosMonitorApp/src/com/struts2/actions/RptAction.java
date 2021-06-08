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
	  private String type;
	  
	  private HttpSession httpSession =null;
	  private HttpServletRequest request = null;
	  
		public void validate() {	
	        String fieldr="Campo requerido";
	        String fieldnospaces ="El campo no tener espacion";
	        String fieldinvalid ="Formato Invalido";
	        
	        HttpServletRequest request = ServletActionContext.getRequest();
	        httpSession = request.getSession(); 
	        httpSession.setAttribute("module","pre");
	        httpSession =null;
	        
	        if (process.isEmpty()) 
	          {
	            this.addFieldError("process",getText("El poceso no es valido"));
	          } 
	        else if ( getProcess().equals("rptLog") )
	          {
		    	if ( p1 != null )
		  		  {
		    			p1 = p1.trim();
		  			if (p1.length() == 0)
		  				this.addFieldError("p1",getText(fieldr));
		  			if (p1.indexOf(" ") > 0 )
		  			this.addFieldError("p1",getText(fieldnospaces));
		  		  }
		  		else
		  			this.addFieldError("p1",getText(fieldr));
		         
	        
	        
		    	if ( p2 != null )
		  		  {
		    		p2 = p2.trim();
		  			if (p2.length() == 0)
		  				this.addFieldError("p2",getText(fieldr));
		  			if (p2.indexOf(" ") > 0 )
		  			this.addFieldError("p2",getText(fieldnospaces));
		  		  }
		  		else
		  			this.addFieldError("p2",getText(fieldr));
	          }
	        else if ( getProcess().equals("rptReservation") )
	          {
		    	if ( p1 != null )
		  		  {
		    			p1 = p1.trim();
		  			if (p1.length() == 0)
		  				this.addFieldError("p1",getText(fieldr));
		  			if (p1.indexOf(" ") > 0 )
		  			this.addFieldError("p1",getText(fieldnospaces));
		  		  }
		  		else
		  			this.addFieldError("p1",getText(fieldr));
		
	          }
		}
	
	  
	public String execute()
		throws Exception {

		String forward=SUCCESS;
		HttpServletRequest request = ServletActionContext.getRequest();
		httpSession = request.getSession(); 

		HttpSession httpSession = request.getSession();
							
		Config config = new Config( request );
		
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		try 
		  {
		 

		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");
		String EXPORT_FILE = "";
		
		if ( beanUser == null )
		  forward = "login";		
		else if ( getProcess().equals("rptLog") ) //log on crystal
			  {
				Date dateBegin = Utils.StrToDate( getP1(),"dd/mm/yy" );
				Date dateEnd = Utils.StrToDate( getP2(),"dd/mm/yy"  );
				
				if( dateBegin.getTime() > dateEnd.getTime() )
				  {
					forward = "rptLogerror";
					httpSession.setAttribute("error", "La fecha inicial es mayor que la fecha final.");
				  }
				else
				  {
					httpSession.setAttribute( "crystal_rptname", new String("rptLog.rpt") ) ;
					httpSession.setAttribute( "parameterCount", new Integer( 3 ) ) ;
					
					httpSession.setAttribute( "parameter1", new Integer(  beanUser.getId_company().getId() ) ) ;
					httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
					httpSession.setAttribute( "parameterName1", new String("id_company") ) ;
					
					httpSession.setAttribute( "parameter2",  dateBegin ) ;
					httpSession.setAttribute( "parameterType2", new String("Date") ) ;
					httpSession.setAttribute( "parameterName2", new String("date_begin") ) ;
					
	
					
					httpSession.setAttribute( "parameter3",  dateEnd ) ;
					httpSession.setAttribute( "parameterType3", new String("Date") ) ;
					httpSession.setAttribute( "parameterName3", new String("date_end") ) ;
					forward = "printGlobal";
				  }
				 

				
			  } else if ( getProcess().equals("rptReservation") )
			  {
				    httpSession.setAttribute( "crystal_rptname", new String("rptReservation.rpt") ) ;
					httpSession.setAttribute( "parameterCount", new Integer( 1 ) ) ;
					
					httpSession.setAttribute( "parameter1", new Integer( getP1() ) ) ;
					httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
					httpSession.setAttribute( "parameterName1", new String("id_reservation") ) ;
					
					EXPORT_FILE = "rptReservation" + getP1();					
					httpSession.setAttribute( "EXPORT_FILE",EXPORT_FILE);
							
							
					httpSession.removeAttribute("type");
					if(getType() != null)
					{
						if(!getType().equals(""))
						{ 
							
							httpSession.setAttribute( "type", getType() ) ;	
						}	
					}

					forward = "printGlobal";
					 
			  }
			  else if ( getProcess().equals("rptQuote") )
			  {
				    httpSession.setAttribute( "crystal_rptname", new String("rptQuote.rpt") ) ;
					httpSession.setAttribute( "parameterCount", new Integer( 1 ) ) ;
					
					httpSession.setAttribute( "parameter1", new Integer( getP1() ) ) ;
					httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
					httpSession.setAttribute( "parameterName1", new String("id_quote") ) ;
					
					EXPORT_FILE = "rptQuote" + getP1();					
					httpSession.setAttribute( "EXPORT_FILE",EXPORT_FILE);
					
					httpSession.removeAttribute("type");
					if(getType() != null)
					{
						if(!getType().equals(""))
						{ 
							
							httpSession.setAttribute( "type", getType() ) ;	
						}	
					}

					forward = "printGlobal";
					 
			  }
		
		
		else
		  {
			httpSession.setAttribute("error", "Opcion no programada");
			
			forward = "rptLogerror";
		  }

 
		  } 
		catch (Exception e) 
		  {
			e.printStackTrace();
			httpSession.setAttribute("error", "Ocurrio un error: "+ e.getMessage());
								
			forward = ERROR;
						
		  }
		finally	
		  {
			if ( session != null )
				 session.close(); 
				  
			if ( sessionFactory != null )
				 sessionFactory.close();
		  }
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


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
