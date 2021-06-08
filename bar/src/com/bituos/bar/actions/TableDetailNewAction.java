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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import com.bituos.*;


//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import javax.servlet.http.*;

// href=<%="'"%><bean:write name="id" property="id_preForm.getProcess().url" /><%="'"%> >
//<html:submit /> <bean:write name="id" property="id_preForm.getProcess().description" /> </html:submit>


public class TableDetailNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value

		TableDetailNewForm tableDetailNewForm = (TableDetailNewForm) form;

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
				
				if ( tableDetailNewForm.getProcess().equals("selectCategory") ) //delete user
				  {
					
				   //BeanAgente beanAgente = (BeanAgente)httpSession.getAttribute("beanAgente");
				   				    
				    query =	" FROM  BeanMarca m " +
				    	    " WHERE m.id in( SELECT p.id_marca.id    " +
				    		"				 FROM BeanProducto p "	+
				    	    "				 WHERE p.id_categoria.id = "	+ tableDetailNewForm.getId_categoria() +
				    	    "			   )" ;		
				    	   
				    
				 
				    list = session.createQuery( query ).list();
				    
				    if (!list.isEmpty())
			        {
				    	httpSession.setAttribute("marcaList", list);
				    	list = new ArrayList<BeanProducto>();
				    	httpSession.setAttribute("productoList", list);
				    	
				    	httpSession.setAttribute("backgroundColorCategoria", "green");
				    	httpSession.setAttribute("backgroundColorMarca", "white");
				    	httpSession.setAttribute("backgroundColorProducto", "white");
				    	  
				        forward = mapping.findForward("currentTable");
				    }
			      else
			        {	
				      httpSession.setAttribute("done", "agent/currentTable.jsp" );							
				      com.bituos.Error  error= new com.bituos.Error("No hay marcas registradas", request);							
				      forward = mapping.findForward("error");   
			        }
						  
		
				  }
				else if ( tableDetailNewForm.getProcess().equals("selectMarca") ) //delete user
				  {
					query =	" FROM BeanProducto p " +
					        " WHERE p.id_marca.id = " +  tableDetailNewForm.getId_marca();
				   
				    	   
				    
				 
				    list = session.createQuery( query ).list();
				    
				    if (!list.isEmpty())
			        {
				    	httpSession.setAttribute("productoList", list);
				    	
				     	httpSession.setAttribute("backgroundColorCategoria", "white");
				    	httpSession.setAttribute("backgroundColorMarca", "green");
				    	httpSession.setAttribute("backgroundColorProducto", "white");
			    					    	  
				        forward = mapping.findForward("currentTable");
				    }
			      else
			        {	
				      httpSession.setAttribute("done", "agent/currentTable.jsp" );							
				      com.bituos.Error  error= new com.bituos.Error("No hay productos registradas", request);							
				      forward = mapping.findForward("error");   
			        } 					 
	
				    
				  }
				else if ( tableDetailNewForm.getProcess().equals("selectProducto") ) //delete user
				  {
					
					BeanProducto beanProducto = (BeanProducto) session.load( BeanProducto.class, new Integer(tableDetailNewForm.getId_producto()));
				
					BeanDetalleComanda beanDetalleComanda = new BeanDetalleComanda();
					BeanComandas currentComanda =(BeanComandas)httpSession.getAttribute("currentComanda");				
					
					tx = session.beginTransaction();
					beanDetalleComanda.setId_comanda(currentComanda);
					beanDetalleComanda.setId_producto(beanProducto);
					beanDetalleComanda.setCantidad(Integer.parseInt( tableDetailNewForm.getCantidad()));
					beanDetalleComanda.setPrecio_unit(beanProducto.getPrecio_venta());
					beanDetalleComanda.setProducto_descripcion(beanProducto.getDescripcion());//
/*
					Calendar calendar = Calendar.getInstance();
				    String am_pm;
				    int mes = calendar.get(Calendar.MONTH);
				    int dia = calendar.get(Calendar.DAY_OF_MONTH);
				    int year = calendar.get(Calendar.YEAR);
				    
				    int hour = calendar.get(Calendar.HOUR_OF_DAY);
				    int minute = calendar.get(Calendar.MINUTE);
				    int second = calendar.get(Calendar.SECOND);
				    if(calendar.get(Calendar.AM_PM) == 0)
				       am_pm = "AM";
				    else
				      am_pm = "PM";
				    String CT = mes +"/" + dia +"/" + year +" " + hour+":"+ minute +":"+ second +" "+ am_pm;*/
					
					
					Date ahora = new Date();
			       /* SimpleDateFormat formateador = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss ampm");
			        String f = formateador.format(ahora);*/
				
					beanDetalleComanda.setDate_capture(ahora);
					
					
				    session.save( beanDetalleComanda );
				    	
			    	tx.commit();
			    	  
					
					
					
					query =	" FROM BeanDetalleComanda dc " +
					        " WHERE dc.id_comanda = " +  currentComanda.getId_comanda();				   
				
				 
				    list = session.createQuery( query ).list();
				    
				    httpSession.setAttribute("beanDetallaComandaList", list);
				    
				   
					Iterator iter = list.iterator();	
					BeanDetalleComanda beanDetalleComandaTemp = new BeanDetalleComanda();	 
					double total =0;
				    while ( iter.hasNext() )
					  {			
				    	beanDetalleComandaTemp = (BeanDetalleComanda)iter.next();
				    	total = total + (beanDetalleComandaTemp.getPrecio_unit() * beanDetalleComandaTemp.getCantidad());
					  }
					   
				    httpSession.setAttribute("total", total);
					/* FROM BeanDetalleComanda dc
					 WHERE dc.id_comanda =1659
					 AND dc.id_producto.id = 3*/
					
					httpSession.setAttribute("backgroundColorCategoria", "white");
			    	httpSession.setAttribute("backgroundColorMarca", "white");
			    	httpSession.setAttribute("backgroundColorProducto", "green");
				    forward = mapping.findForward("currentTable");
				 
	
				    
				  }
				else if ( tableDetailNewForm.getProcess().equals("changeFromTable") ) //delete user
				  {
					
					BeanAgente beanAgente = (BeanAgente) session.load( BeanAgente.class, new Integer(tableDetailNewForm.getId_agent()));
					
				    httpSession.setAttribute("beanAgente", beanAgente );
				    httpSession.removeAttribute("listTable" );
				    httpSession.removeAttribute("listOpenComandas" ); 
				  
			  
				    query =  " FROM BeanComandas c" +
			    			 " WHERE c.id_agente.id = " + tableDetailNewForm.getId_agent() +
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
				    /*else
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
				     }  */ 
				  }
				else if ( tableDetailNewForm.getProcess().equals("cancelProduct") ) //delete user
				  {  
					
			    	query =	" FROM BeanDetalleComanda dc " +
						    " WHERE dc.id_comanda.id = " +  tableDetailNewForm.getId_comanda()+
			    			" AND dc.cantidad > 0";
					
					 
					list = session.createQuery( query ).list();
					double total =0;
					if (!list.isEmpty())  
					  {							  
												
				    	Iterator iter = list.iterator();							
				    	BeanDetalleComanda beanDetalleComandaTemp = new BeanDetalleComanda();	
				    	
				    	 query =	" SELECT dc.id_producto.id, SUM(dc.cantidad)  " +
						    		" FROM BeanDetalleComanda dc" +
								    " WHERE dc.id_comanda.id = " +  tableDetailNewForm.getId_comanda() +
								    " GROUP BY dc.id_producto" +
								    " HAVING SUM(dc.cantidad) > 0";
							
						List beanDetallaComandaList= new ArrayList<BeanDetalleComanda>();
						List productDetailList = session.createQuery( query ).list();	
						Iterator productDetailListIter;	
						//ProductDetail productDetail = new ProductDetail(); 
						Object[] obj = new Object[2];
						int size = productDetailList.size();
					    while ( iter.hasNext() )
						  {			
					    	beanDetalleComandaTemp = (BeanDetalleComanda)iter.next();
					    	
					    	productDetailListIter = productDetailList.iterator();	
					    	while ( productDetailListIter.hasNext() )
							  {						    		
					    		obj = (Object[])productDetailListIter.next();
					    		
					    		 if ((Integer)obj[0] == beanDetalleComandaTemp.getId_producto().getId() )
					    		   {					    			 
				    				 if(size > 0)
				    				 {
				    				   beanDetalleComandaTemp.setCantidad((Integer)obj[1]);
				    				   total = total + (beanDetalleComandaTemp.getPrecio_unit() * beanDetalleComandaTemp.getCantidad());
								    	
					    			   beanDetallaComandaList.add(beanDetalleComandaTemp);
					    			   size--;
				    				 }
				    				 else
				    					 break;
						    		   					    			 
					    		   }
					    	
					    			 
							  }
					    	
					    	
						  }
					    
					      httpSession.setAttribute("total", total);
					   	
					 
					    
					     httpSession.setAttribute("beanDetallaComandaList", beanDetallaComandaList);
					    }
					 else
					   {
						 list = new ArrayList<BeanDetalleComanda>();
				    	 httpSession.setAttribute("beanDetallaComandaList", list);
				    	 httpSession.setAttribute("total", total);
					   }
					
					 forward = mapping.findForward("cancelProduct");
					
				  }
				else if ( tableDetailNewForm.getProcess().equals("closeComanda") ) //delete user
				  {
					 BeanComandas beanComanda = (BeanComandas) session.load( BeanComandas.class, new Integer(tableDetailNewForm.getId_comanda()));
					
					 tx = session.beginTransaction();
					
				     beanComanda.setFecha(Utils.Today());
		    	     Calendar calendario = new GregorianCalendar();
		    	     int hora =calendario.get(Calendar.HOUR_OF_DAY);
		    	     int minutos = calendario.get(Calendar.MINUTE);
			    	  
					 beanComanda.setCerrada('S');					
					 beanComanda.setHora_cierre(hora);
					 beanComanda.setMinuto_cierre(minutos);
					
			   	     query =	" FROM BeanDetalleComanda dc " +
					            " WHERE dc.id_comanda.id = " +  tableDetailNewForm.getId_comanda();							       			   
				
				 
				     list = session.createQuery( query ).list();
				     double total =0;
				     Iterator iter = list.iterator();	
				     BeanDetalleComanda beanDetalleComandaTemp = new BeanDetalleComanda();	 
				  
			         while ( iter.hasNext() )
				       {			
			    	     beanDetalleComandaTemp = (BeanDetalleComanda)iter.next();
			    	     total = total + (beanDetalleComandaTemp.getPrecio_unit() * beanDetalleComandaTemp.getCantidad());
			    	     httpSession.setAttribute("total", total);
				       }
					 
					 beanComanda.setTotal(total);
					
					 session.update( beanComanda );
			    	
			    	// tx.commit();
			    	
			    	 forward = mapping.findForward("ok");    
					 httpSession.setAttribute("done", new String("/bar/pre.do?&process='loadAgenteList'&action='pre.do'&target='_self'"));
			
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
