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

import java.util.*;

import com.bituos.*;


//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import javax.servlet.http.*;

// href=<%="'"%><bean:write name="id" property="id_preForm.getProcess().url" /><%="'"%> >
//<html:submit /> <bean:write name="id" property="id_preForm.getProcess().description" /> </html:submit>


public class AllocationTablesAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value

		AllocationTablesForm allocationTablesForm = (AllocationTablesForm) form;

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
				
				if ( allocationTablesForm.getProcess().equals("allocateTable") ) //delete user
				  {
					
				    BeanAgente beanAgente = (BeanAgente)httpSession.getAttribute("beanAgente");
					
				    query =  " FROM BeanMesa m" +
			    			 " WHERE m.id = " + allocationTablesForm.getId_table();
						    
				  
				    list = session.createQuery( query ).list();
				  
				    if (list.isEmpty())	
				     { 
				       httpSession.setAttribute("done", "agent/allocationTables.jsp" );							
					   com.bituos.Error  error= new com.bituos.Error("No hay dado de alta ninguna mesa", request);							
					   forward = mapping.findForward("error"); 
				     }  
				    else
				     {			  
								    
					   query =  " SELECT c.id_mesa " + 
					      	    " FROM BeanComandas c " +
					    	    " WHERE  c.id_mesa.id = " + allocationTablesForm.getId_table() +
					    	    " AND c.cerrada = 'N' ";
	
					   list = session.createQuery( query ).list();
				       if (list.isEmpty())
				        {
				    	   
				    	  BeanMesa  beanMesa  = (BeanMesa ) session.load( BeanMesa .class, new Integer(allocationTablesForm.getId_table()));
				    	 
				    	 			    	  
				    	  tx = session.beginTransaction();
				    	  BeanComandas beanComanda = new BeanComandas();
				    	  beanComanda.setId_agente(beanAgente);
				    	  beanComanda.setId_mesa(beanMesa);
				    	  beanComanda.setAgente_nombre(beanAgente.getNombre());
				    	  beanComanda.setFecha(Utils.Today());
				    	  Calendar calendario = new GregorianCalendar();
				    	  int hora =calendario.get(Calendar.HOUR_OF_DAY);
				    	  int minutos = calendario.get(Calendar.MINUTE);
				    	  //segundos = calendario.get(Calendar.SECOND);
				    	  beanComanda.setHora_apertura(hora);
				    	  beanComanda.setMinuto_apertura(minutos);
				    	  beanComanda.setCerrada('N');
				    	  
				    	  
				    	  session.save( beanComanda );
				    	
				    	  tx.commit();
				    	  
				    	  query =  " FROM BeanComandas c" +
					    		   " WHERE c.id_agente.id = " + beanAgente.getId() +
								   " AND c.cerrada = 'N'";						
						    
						  list = session.createQuery( query ).list();
						  
						  httpSession.setAttribute("listOpenComandas", list );
				    	  
					      forward = mapping.findForward("tables");
					    }
				      else
				        {	
					      httpSession.setAttribute("done", "agent/allocationTables.jsp" );							
					      com.bituos.Error  error= new com.bituos.Error("La mesa ya no esta disponible", request);							
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
