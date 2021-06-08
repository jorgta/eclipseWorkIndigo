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


public class TableSelectAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value

		TableSelectForm tableSelectForm = (TableSelectForm) form;

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
				
				if ( tableSelectForm.getProcess().equals("currentTable") || tableSelectForm.getProcess().equals("'currentTable'") ) //delete user
				  {
					
				
					
					
				   //BeanAgente beanAgente = (BeanAgente)httpSession.getAttribute("beanAgente");
				   				    
				    query =	" FROM BeanComandas c " +
				    	    " WHERE  c.id_mesa.id = " + tableSelectForm.getId_table() +
				    	    " AND c.id_agente.id = " + tableSelectForm.getId_agent() +
				    	    " AND c.cerrada = 'N' ";
				    
				   

				    list = session.createQuery( query ).list();
				    
				    if (!list.isEmpty())
			        {
			    	   				    	
				    	BeanComandas  currentComanda  = (BeanComandas ) list.get(0);
				    	httpSession.setAttribute("currentComanda", currentComanda);
				    	
				        query = "FROM BeanCategoria";
				    	list = session.createQuery( query ).list();
				    	
				    	if (!list.isEmpty())
				        {			    		
				       		
				    	
				    		
				    	  httpSession.setAttribute("categoryList", list);
				    	  list = new ArrayList<BeanMarca>();
				    	  httpSession.setAttribute("marcaList", list);
				    	  list = new ArrayList<BeanProducto>();
				    	  httpSession.setAttribute("productoList", list);	
				    	  //crear campo id en BeanDetalleComanda
				    	  query =	" FROM BeanDetalleComanda dc " +
							        " WHERE dc.id_comanda.id_comanda = " +  currentComanda.getId_comanda();							       			   
						
						 
						  list = session.createQuery( query ).list();
						  double total =0;
						  if (!list.isEmpty())  
						    {							  
					    	  httpSession.setAttribute("beanDetallaComandaList", list);
					    	  Iterator iter = list.iterator();	
							  BeanDetalleComanda beanDetalleComandaTemp = new BeanDetalleComanda();	 
							  
						      while ( iter.hasNext() )
							    {			
						    	  beanDetalleComandaTemp = (BeanDetalleComanda)iter.next();
						    	  total = total + (beanDetalleComandaTemp.getPrecio_unit() * beanDetalleComandaTemp.getCantidad());
						    	  httpSession.setAttribute("total", total);
							    }
						    }
						  else
						    {
							  list = new ArrayList<BeanDetalleComanda>();
					    	  httpSession.setAttribute("beanDetallaComandaList", list);
					    	  httpSession.setAttribute("total", total);
						    }
							    	  
				    	  httpSession.setAttribute("backgroundColorCategoria", "white");
				    	  httpSession.setAttribute("backgroundColorMarca", "white");
				    	  httpSession.setAttribute("backgroundColorProducto", "white");
				    	  
			    					    	  
				          forward = mapping.findForward("currentTable");
				          
				        }
				    }
			      else
			        {	
				      httpSession.setAttribute("done", "agent/tables.jsp" );							
				      com.bituos.Error  error= new com.bituos.Error("La mesa ya fue cerrada", request);							
				      forward = mapping.findForward("error");   
			        }			    
				    
					
				   // forward = mapping.findForward("currentTable");

				  
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
