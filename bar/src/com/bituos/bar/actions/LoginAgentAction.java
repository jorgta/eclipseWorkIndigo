package com.bituos.bar.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 */

import com.bituos.bar.Beans.*;
import com.bituos.bar.forms.*;


import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;



import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrinterName;

import java.util.*;

import com.bituos.*;
import com.crystaldecisions.sdk.occa.report.document.PaperSize;


//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import javax.servlet.http.*;

// href=<%="'"%><bean:write name="id" property="id_preForm.getProcess().url" /><%="'"%> >
//<html:submit /> <bean:write name="id" property="id_preForm.getProcess().description" /> </html:submit>


public class LoginAgentAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value

		LoginAgentForm loginAgentForm = (LoginAgentForm) form;

		HttpSession httpSession = request.getSession();
							
		Config config = new Config( request );
		
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		String query;
		List list;
	    
		 
			try
			  {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
				
				if ( loginAgentForm.getProcess().equals("login") ) //delete user
				  {

					/*****************************test de impresion**************/
					PrinterJob printJob = PrinterJob.getPrinterJob();
				
					PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
					String printName="";
					
					for(int i =0; i <= services.length; i++)
					{
					 // if(services[i].getName().equals("OKI MB470(PS)"))   
					  if(services[i].getName().equals("GP-5890X"))
					  {
						 printName = services[i].getName();
						 break;
					  }
					}
					
					AttributeSet aset = new HashAttributeSet();
			        aset.add(new PrinterName(printName, null));
			        services = PrintServiceLookup.lookupPrintServices(null, aset);
			        
			        
			        
			        for (PrintService printService : services) {
			            PrintService printers[] = PrintServiceLookup.lookupPrintServices(null, aset);
			                if (printers.length == 1) {
			               
			                	
		 				   PageFormat format = new PageFormat();
						   Paper paper = new Paper();
									   
						   double paperWidth=(double) (58*2.83);//5.8cm //2.83 is scaling factor to convert mm to pixels
						   double paperHeight=(double) (58*2.83);
						    
						   double leftMargin = (double)(0.19*2.83);
						   double rightMargin = (double)(0.25*2.83);
						   double topMargin = (double)(0*2.83);
						   double bottomMargin = (double)(0.01*2.83);
						
						   paper.setSize(paperWidth, paperHeight);
						   paper.setImageableArea(leftMargin, topMargin,
						        (paperWidth - leftMargin - rightMargin),
						        (paperHeight - topMargin - bottomMargin));
						
						   format.setPaper(paper);
						
									                	
			              printJob.setPrintService(printers[0]);//le asignamos la impresora de trabajo al job	
			            
			              IntroPage introPage= new IntroPage();
			              printJob.setPrintable(introPage, format);
			              //printJob.printDialog();
			             	try {
			            	   //printJob.print();
			            	   }
			            	   catch (Exception e) {
			            	       e.printStackTrace();
			            	   }
			               
			                }
			        }
					
			        /*****************************end test de impresion**************/
					
					BeanAgente beanAgente = (BeanAgente) session.load( BeanAgente.class, new Integer(loginAgentForm.getId_agent()));
		
				    httpSession.setAttribute("beanAgente", beanAgente );
				    httpSession.removeAttribute("listTable" );
				    httpSession.removeAttribute("listOpenComandas" ); 
				  
			  
				    query =  " FROM BeanComandas c" +
			    			 " WHERE c.id_agente.id = " + loginAgentForm.getId_agent() +
						     " AND c.cerrada = 'N'";
				
				    
				    list = session.createQuery( query ).list();
				  
				    if (!list.isEmpty())	
				     { 
				        
				    	
				    	httpSession.setAttribute("listOpenComandas", list );
				       
				        
				        query = " FROM BeanMesa m " +
					    		" WHERE m.id not in ( SELECT c.id_mesa  " +
					    		" 					  FROM BeanComandas c   " +
					    		" 					  WHERE  c.cerrada = 'N') " ;
	
					    
	
					    list = session.createQuery( query ).list();
					    
					    if (!list.isEmpty())
					     {	
					    	list.size();
					    	
						    httpSession.setAttribute("listTable", list );
						    forward = mapping.findForward("tables");
						    
					     }
					    else
					     {	
						    httpSession.setAttribute("done", "agent/allocationTables.jsp" );							
						    com.bituos.Error  error= new com.bituos.Error("No hay mesas para asignar", request);							
						    forward = mapping.findForward("error");   
					     }
					    
					    if (forward == null)
						   {	 
						     httpSession.setAttribute("done", "agent/allocationTables.jsp" );							
						     com.bituos.Error  error= new com.bituos.Error("El agente no tiene mesas asignadas", request);							
						     forward = mapping.findForward("error"); 
						  }
				     }  
				    else
				     {			  
					    
					    
					    
				    	query = " FROM BeanMesa m " +
					    		" WHERE m.id not in ( SELECT c.id_mesa  " +
					    		" 					  FROM BeanComandas c   " +
					    		" 					  WHERE  c.cerrada = 'N') " ;
	
					    
	
					    list = session.createQuery( query ).list();
					    if (!list.isEmpty())
					     {	
					    	list.size();
					    	
						    httpSession.setAttribute("listTable", list );
						    forward = mapping.findForward("tableAsing");
						    
					     }
					    else
					     {	
						    httpSession.setAttribute("done", "agent/allocationTables.jsp" );							
						    com.bituos.Error  error= new com.bituos.Error("No hay mesas para asignar", request);							
						    forward = mapping.findForward("error");   
					     }
					  
					    if (forward == null)
					     {	 
					       httpSession.setAttribute("done", "agent/allocationTables.jsp" );							
					       com.bituos.Error  error= new com.bituos.Error("El agente no tiene mesas asignadas", request);							
					       forward = mapping.findForward("error"); 
					     }
				   }
					  
		

				  }
				else if ( loginAgentForm.getProcess().equals("getTables") ) 
				  {
					
					
					query = " FROM BeanMesa m " +
				    		" WHERE m.id not in ( SELECT c.id_mesa  " +
				    		" 					  FROM BeanComandas c   " +
				    		" 					  WHERE  c.cerrada = 'N') " ;

				    

				    list = session.createQuery( query ).list();
				    if (!list.isEmpty())
				     {	
				    	list.size();
				    	
					    httpSession.setAttribute("listTable", list );
					    forward = mapping.findForward("tableAsing");
					    
				     }
				    else
				     {	
					    httpSession.setAttribute("done", "agent/allocationTables.jsp" );							
					    com.bituos.Error  error= new com.bituos.Error("No hay mesas para asignar", request);							
					    forward = mapping.findForward("error");   
				     }
				  
				    if (forward == null)
				     {	 
				       httpSession.setAttribute("done", "agent/allocationTables.jsp" );							
				       com.bituos.Error  error= new com.bituos.Error("El agente no tiene mesas asignadas", request);							
				       forward = mapping.findForward("error"); 
				     }
				  }
			
				
				
			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();
				
				com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
				
				forward = mapping.findForward("error");
			  }
			finally
			  {
				if(session != null)
				 session.close();
				
				if(sessionFactory != null)
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

