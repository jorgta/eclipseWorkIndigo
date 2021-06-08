package com.eventAdmin.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eventAdmin.Beans.*;
import com.eventAdmin.forms.*;

/**
 * @version 	1.0
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 */

import java.util.*;

import com.eventAdmin.*;
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
				
				if ( rptForm.getProcess().equals("quote") )  
					{
					    int id_intern = 0;
					    boolean error = false;
					    int id = 0;
					    
					    try    
					      {
					    	id_intern = new Integer( rptForm.getP1().trim() ).intValue();
					      }
					    catch( Throwable  m)
					      {
					    	error = true;
					      }
					    
					    if ( !error )
					      {
							query = " FROM BeanQuote u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId() +
									" AND u.id_intern = " + id_intern; 
							
							List list = session.createQuery( query ).list();
							
							if (list.size() > 0)
							  {
							    id = ((BeanQuote) list.get(0)).getId();
							    
								//httpSession.setAttribute( "crystal_rptname", new String("rptQuote.rpt") ) ;
								httpSession.setAttribute( "crystal_rptname", beanUser.getId_company().getNameRptQuote() ) ;
								httpSession.setAttribute( "parameterCount", new Integer( 1 ) ) ;
													
								httpSession.setAttribute( "parameter1", new Integer( id ) );
								httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
								httpSession.setAttribute( "parameterName1", new String("id_quote") ) ;
			
								forward = mapping.findForward("printGlobal");
							  }
						    else
							  {
								com.bituos.Error e = new com.bituos.Error("La cotizacion no existe.", request);
								
								forward = mapping.findForward("error");
							  }
					      }
					    else
						  {
							com.bituos.Error e = new com.bituos.Error("Formato incorrecto de numero de cotizacion.", request);
							
							forward = mapping.findForward("error");
						  }
					    
					}
				else if ( rptForm.getProcess().equals("quotes") )
				      {
						if (    rptForm.getP1()==null 
							 || rptForm.getP2()==null 
						   )
							{
							  httpSession.setAttribute("done", "/eventAdmin/rpt/rptQuotes.jsp");
							  com.bituos.Error e = new com.bituos.Error("Los campos Fecha inicial o Fecha Final no deben ser vacios", request);
						      forward = mapping.findForward("error");
							}
						else
						  {
							if ( rptForm.getP1().equals("") 
								 || 
								 rptForm.getP2().equals("") 
							   )
							  {
								httpSession.setAttribute("done", "/eventAdmin/rpt/rptQuotes.jsp");
								com.bituos.Error e = new com.bituos.Error("Se deben especificar ambas fechas.", request);
							    forward = mapping.findForward("error");
							  }
							else
							  {
								Date dateBegin = Utils.StrToDate( rptForm.getP1() );
								Date dateEnd = Utils.StrToDate( rptForm.getP2() );
							    
								if( dateBegin.getTime() > dateEnd.getTime() )
								  {
									httpSession.setAttribute("done", "/eventAdmin/rpt/rptQuotes.jsp");
									com.bituos.Error e = new com.bituos.Error("La fecha inicial es mayor que la fecha final.", request);
								    forward = mapping.findForward("error");
								  }
								else
								  {
							
									int id = 0;
									id = beanUser.getId_company().getId();
								    httpSession.setAttribute( "crystal_rptname", new String("rptQuotes.rpt") ) ;
									httpSession.setAttribute( "parameterCount", new Integer( 3 ) ) ;
														
									httpSession.setAttribute( "parameter1", new Integer( id ) );
									httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
									httpSession.setAttribute( "parameterName1", new String("id_company") ) ;
									
			 
									
									httpSession.setAttribute( "parameter2",  dateBegin ) ;
									httpSession.setAttribute( "parameterType2", new String("Date") ) ;
									httpSession.setAttribute( "parameterName2", new String("date_begin") ) ;
									
			 
									
									httpSession.setAttribute( "parameter3",  dateEnd ) ;
									httpSession.setAttribute( "parameterType3", new String("Date") ) ;
									httpSession.setAttribute( "parameterName3", new String("date_end") ) ;
				
					
									forward = mapping.findForward("printGlobal");
								  }
							  }
						  }
				      }
				else if ( rptForm.getProcess().equals("sales") )
			      {
					int id_sale = 0;
					boolean solo = true;
					
					try
					  {
						id_sale = new Integer( rptForm.getP3() ).intValue();
						
					  }
					catch( Throwable  m)
					  {
						solo = false;
					  }
					
					if ( solo )
					  {
						//httpSession.setAttribute( "crystal_rptname", new String("rptSale.rpt") ) ;
						httpSession.setAttribute( "crystal_rptname", beanUser.getId_company().getNameRptSale() ) ;
						httpSession.setAttribute( "parameterCount", new Integer( 1 ) ) ;
											
						httpSession.setAttribute( "parameter1", new Integer( id_sale ) );
						httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
						httpSession.setAttribute( "parameterName1", new String("id_sale") ) ;
	
						forward = mapping.findForward("printGlobal");
					  }
					else
					  {
						if (    rptForm.getP1()==null 
							 || rptForm.getP2()==null 
						   )
						  {
						    httpSession.setAttribute("done", "/eventAdmin/rpt/rptSales.jsp");
						    com.bituos.Error e = new com.bituos.Error("Los campos Fecha inicial o Fecha Final no deben ser vacios", request);
					        forward = mapping.findForward("error");
						  }
						else
						  {
							if ( rptForm.getP1().equals("") 
								 || 
								 rptForm.getP2().equals("") 
							   )
							  {
								httpSession.setAttribute("done", "/eventAdmin/rpt/rptSales.jsp");
								com.bituos.Error e = new com.bituos.Error("Se deben especificar ambas fechas.", request);
							    forward = mapping.findForward("error");
							  }
							else
							  {
								Date dateBegin = Utils.StrToDate( rptForm.getP1() );
								Date dateEnd = Utils.StrToDate( rptForm.getP2() );
								
								if ( dateBegin.getTime() > dateEnd.getTime())
								  {
									httpSession.setAttribute("done", "/eventAdmin/rpt/rptQuotes.jsp");
									com.bituos.Error e = new com.bituos.Error("La fecha inicial es mayor que la fecha final.", request);
								    forward = mapping.findForward("error");
								  }
								else
								  {
							
									int id = 0;
									id = beanUser.getId_company().getId();
								    httpSession.setAttribute( "crystal_rptname", new String("rptSales.rpt") ) ;
									httpSession.setAttribute( "parameterCount", new Integer( 3 ) ) ;
														
									httpSession.setAttribute( "parameter1", new Integer( id ) );
									httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
									httpSession.setAttribute( "parameterName1", new String("id_company") ) ;
									
									httpSession.setAttribute( "parameter2",  dateBegin ) ;
									httpSession.setAttribute( "parameterType2", new String("Date") ) ;
									httpSession.setAttribute( "parameterName2", new String("date_begin") ) ;
									
									httpSession.setAttribute( "parameter3",  dateEnd ) ;
									httpSession.setAttribute( "parameterType3", new String("Date") ) ;
									httpSession.setAttribute( "parameterName3", new String("date_end") ) ;
				
									forward = mapping.findForward("printGlobal");
								  }
								
							  }
						  }
					  }
			      }
				else if ( rptForm.getProcess().equals("contracts") )
			      {
					int id_sale = 0;
					boolean solo = true;
					boolean showError = false;
					
					try
					  {
						id_sale = new Integer( rptForm.getP3() ).intValue();
						
					  }
					catch( Throwable  m)
					  {
						solo = false;
					  }
					
					if ( solo )
					  {
						//verificar si esta firmado
						query = " FROM BeanSale u" +
								" WHERE u.id_company = " + beanUser.getId_company().getId() +
						  		" AND u.id_intern =" +  rptForm.getP3();
						
						List list = session.createQuery( query ).list();
						
						if ( list.size() == 0 )
						    {
								com.bituos.Error e = new com.bituos.Error("La venta no existe.", request);
								showError = true;
						    }
						
						if ( !showError)
						  {
							BeanSale beanSale = (BeanSale) list.get(0);
							
							if ( beanSale.getContract_date_reg() == null  )
							    {
									com.bituos.Error e = new com.bituos.Error("Este evento no tiene firmado el contrato.", request);
									showError = true;
							    }
							
						  }
					

						if ( !showError)
						  {
							int len = beanUser.getId_company().getNameRptContract().length();
							String subst = beanUser.getId_company().getNameRptContract().substring(0, len - 4);
							String rptName = subst + "Preview.rpt";
								
								
							//httpSession.setAttribute( "crystal_rptname", new String("rptContractPreview.rpt") ) ;
							httpSession.setAttribute( "crystal_rptname", rptName) ;
							httpSession.setAttribute( "parameterCount", new Integer( 1 ) ) ;
												
							httpSession.setAttribute( "parameter1", new Integer( id_sale ) );
							httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
							httpSession.setAttribute( "parameterName1", new String("id_sale") ) ;
		
							forward = mapping.findForward("printGlobal");
						  }
						else
						  forward = mapping.findForward("error");
						
					  }
					else
					  {
						if (    rptForm.getP1()==null 
							 || rptForm.getP2()==null 
						   )
						  {
						    httpSession.setAttribute("done", "/eventAdmin/rpt/rptContracts.jsp");
						    com.bituos.Error e = new com.bituos.Error("Los campos Fecha inicial o Fecha Final no deben ser vacios", request);
					        forward = mapping.findForward("error");
						  }
						else
						  {
							if ( rptForm.getP1().equals("") 
								 || 
								 rptForm.getP2().equals("") 
							   )
							  {
								httpSession.setAttribute("done", "/eventAdmin/rpt/rptContracts.jsp");
								com.bituos.Error e = new com.bituos.Error("Se deben especificar ambas fechas.", request);
							    forward = mapping.findForward("error");
							  }
							else
							  {
								Date dateBegin = Utils.StrToDate( rptForm.getP1() );
								Date dateEnd = Utils.StrToDate( rptForm.getP2() );
								
								if ( dateBegin.getTime() > dateEnd.getTime())
								  {
									httpSession.setAttribute("done", "/eventAdmin/rpt/rptContracts.jsp");
									com.bituos.Error e = new com.bituos.Error("La fecha inicial es mayor que la fecha final.", request);
								    forward = mapping.findForward("error");
								  }
								else
								  {
									int id = 0;
									
									id = beanUser.getId_company().getId();
									
								    httpSession.setAttribute( "crystal_rptname", new String("rptContracts.rpt") ) ;
									httpSession.setAttribute( "parameterCount", new Integer( 3 ) ) ;
														
									httpSession.setAttribute( "parameter1", new Integer( id ) );
									httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
									httpSession.setAttribute( "parameterName1", new String("id_company") ) ;
									
									httpSession.setAttribute( "parameter2",  dateBegin ) ;
									httpSession.setAttribute( "parameterType2", new String("Date") ) ;
									httpSession.setAttribute( "parameterName2", new String("date_begin") ) ;
									
									httpSession.setAttribute( "parameter3",  dateEnd ) ;
									httpSession.setAttribute( "parameterType3", new String("Date") ) ;
									httpSession.setAttribute( "parameterName3", new String("date_end") ) ;
				
									forward = mapping.findForward("printGlobal");
								  }
								
							  }
						  }
					  }
			      }
				else if ( rptForm.getProcess().equals("'rptLog'") ) //log on crystal
					  {
						httpSession.setAttribute( "crystal_rptname", new String("rptLog.rpt") ) ;
						httpSession.setAttribute( "parameterCount", new Integer( 1 ) ) ;
						
						httpSession.setAttribute( "parameter1", new Integer(  beanUser.getId_company().getId() ) ) ;
						httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
						httpSession.setAttribute( "parameterName1", new String("id_company") ) ;
	
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
