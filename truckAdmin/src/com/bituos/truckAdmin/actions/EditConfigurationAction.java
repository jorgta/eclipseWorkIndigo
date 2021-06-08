package com.bituos.truckAdmin.actions;

import com.bituos.truckAdmin.Beans.*;
import com.bituos.truckAdmin.forms.*;

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


import javax.servlet.http.*;
import com.bituos.truckAdmin.*;
import com.bituos.*;

import com.ibm.icu.text.ArabicShaping;

import java.util.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

public class EditConfigurationAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		EditConfigurationForm editConfigurationForm = (EditConfigurationForm) form;

		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("done"); 
									
		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
		  forward = mapping.findForward("login");
		else
		  {
			Config config = new Config( request );
		
			SessionFactory sessionFactory = null;
			Session    session = null;
			Transaction tx = null;

			try {
				if(editConfigurationForm.getProcess().equals("edit"))
				{
					  sessionFactory = config.getConfiguration(request).buildSessionFactory();
					  session = sessionFactory.openSession();
				
					 // httpSession.removeAttribute("beanTruckTireConfiguration");
					 
					  List truckTireConfigurationDetailList =(List) httpSession.getAttribute("truckTireConfigurationDetailList");
					  
					  
					  
					  
					  
					  BeanTruckTireConfiguration beanTruckTireConfiguration = ((BeanTruckTireConfigurationDetail)truckTireConfigurationDetailList.get(0)).getId_truck_tire_configuration();
					     
		              
					  beanTruckTireConfiguration.setDescription(editConfigurationForm.getDescription());
					  beanTruckTireConfiguration.setAxis_count(Integer.valueOf( editConfigurationForm.getAxis_count()));
					 // Date ahora = new Date();								
					  //beanTruckTireConfiguration.setDate_reg(ahora);
						
					  
					  httpSession.setAttribute("beanTruckTireConfiguration", beanTruckTireConfiguration);
					  
					 // tx = session.beginTransaction();			 
						
						
					//  session.save( beanTruckTireConfiguration );
					 
					  
					  List list = new ArrayList<BeanTruckTireConfigurationDetail>();
					  BeanTruckTireConfigurationDetail beanTruckTireConfigurationDetail;
					  int sides= 2;
					  for(int i =1; i <= Integer.valueOf( editConfigurationForm.getAxis_count()); i++ )
					  {  //[]
						 
						
						for(int j =1; j <= sides; j++ )
						{
						  beanTruckTireConfigurationDetail =  new BeanTruckTireConfigurationDetail();					  
						  beanTruckTireConfigurationDetail.setAxis_number(i);
						  if(j == 1)						  
						    beanTruckTireConfigurationDetail.setAxis_side("L");
						  
						  if(j == 2)
							beanTruckTireConfigurationDetail.setAxis_side("R"); 
						  
						  
						  beanTruckTireConfigurationDetail.setId_truck_tire_configuration(beanTruckTireConfiguration);
						//  session.save( beanTruckTireConfigurationDetail );					 
						  list.add(beanTruckTireConfigurationDetail);
						  
						}
						  
						  
					  }
					  
					  
					  
				
					 // tx.commit();
					  int axis_number = beanTruckTireConfiguration.getAxis_count();
					  httpSession.setAttribute("axis_number", axis_number);
					  
				
					  httpSession.setAttribute("truckTireConfigurationDetailList", list);
					  httpSession.setAttribute("save", true);
					  
					  
					  forward = mapping.findForward("loop");
					  
					 // httpSession.setAttribute("done", new String("pre.jsp?&process='truckTireConfigurationNew'&action='pre.do'&target=''"));
				   
		
				}
				else if(editConfigurationForm.getProcess().equals("save"))
				{
				
				  sessionFactory = config.getConfiguration(request).buildSessionFactory();
				  session = sessionFactory.openSession();
			
				   tx = session.beginTransaction();			 
					
					 
				      //List truckTireConfigurationDetailList =(List) httpSession.getAttribute("truckTireConfigurationDetailList");
				      
				      httpSession.removeAttribute("truckTireConfigurationDetailList");
				      String query =   " FROM BeanTruckTireConfigurationDetail u" 
						   			 + " WHERE u.id_truck_tire_configuration.id  = " + editConfigurationForm.getId_configuration() ;  
			
				      java.util.List truckTireConfigurationDetailList = session.createQuery(query).list();
				      
				      BeanTruckTireConfiguration beanTruckTireConfiguration = ((BeanTruckTireConfigurationDetail)truckTireConfigurationDetailList.get(0)).getId_truck_tire_configuration();
					  
				      
				      Iterator iterDelete = truckTireConfigurationDetailList.iterator();
				      while ( iterDelete.hasNext() )
					  {				        
				    	session.delete((BeanTruckTireConfigurationDetail) iterDelete.next());
					  }
					 
				      
					  
					 // BeanTruckTireConfiguration beanTruckTireConfiguration =(BeanTruckTireConfiguration) httpSession.getAttribute("beanTruckTireConfiguration");
				      beanTruckTireConfiguration.setDescription(editConfigurationForm.getDescription());
				      beanTruckTireConfiguration.setAxis_count(Integer.valueOf( editConfigurationForm.getAxis_count()) );
				      session.update( beanTruckTireConfiguration );	
					  
					 
					  String pipe ="|";
					  String pipepipe ="||";
					  
					  // ||id_truck_configuration|axis_number|axis_side|tire_position||
					  String configuration =  pipepipe +
											  String.valueOf( beanTruckTireConfiguration.getDescription()) + pipe;
					  
					  
					  //
						
					  BeanTruckTireConfigurationDetail bean;
					 
					  int tire_position;
					  int sides;
					  int index= 0;
					  
					  Iterator iter;
					  for(int i=1; i <= Integer.valueOf(editConfigurationForm.getAxis_count()); i++ )
					  {
						  sides=0;
						  iter = truckTireConfigurationDetailList.iterator();
						  while ( iter.hasNext() )
						  {
							  bean= (BeanTruckTireConfigurationDetail) iter.next();
							  	
							
							  
							 if(bean.getAxis_side().equals("L") 
							     && bean.getAxis_number()== i 
							     && Integer.valueOf(editConfigurationForm.getTire_for_axis(index)) == 2)
							  {
								  tire_position=1;
								  bean.setTire_position(tire_position);
								  configuration = configuration + 
				                                  String.valueOf( bean.getAxis_number() ) +  pipe +
								                  bean.getAxis_side() + pipe +
								                  String.valueOf( tire_position ) + pipepipe ;
								  sides++;
								  session.save( bean );
								  if(sides==2)					  
									 break;
								 
							  }
							  else if(bean.getAxis_side().equals("R") 
								     && bean.getAxis_number()== i 
								     && Integer.valueOf(editConfigurationForm.getTire_for_axis(index)) == 2)
							  {
								  tire_position=1;
								  bean.setTire_position(tire_position);
								  configuration = configuration + 
					                              String.valueOf( bean.getAxis_number() ) +  pipe +
								                  bean.getAxis_side() + pipe +
								                  String.valueOf( tire_position ) + pipepipe ;
								  sides++;
								  session.save( bean );
								  if(sides==2)					  
									 break;
							  }
							 
							  
							  if(bean.getAxis_side().equals("L")
									     && bean.getAxis_number()== i 
									     && Integer.valueOf(editConfigurationForm.getTire_for_axis(index)) == 4)
							  {
								  tire_position=21;
								  bean.setTire_position(tire_position);
								  configuration = configuration + 
					                              String.valueOf( bean.getAxis_number() ) +  pipe +
								                  bean.getAxis_side() + pipe +
								                  String.valueOf( tire_position ) + pipepipe ;
								  sides++;
								  session.save( bean );
								  if(sides==2)					  
									 break;
							  }
							  else if(bean.getAxis_side().equals("R") 
									     && bean.getAxis_number()== i 
									     && Integer.valueOf(editConfigurationForm.getTire_for_axis(index)) == 4)
									  {
								  tire_position=12;
								  bean.setTire_position(tire_position);
								  configuration = configuration + 
					                              String.valueOf( bean.getAxis_number() ) +  pipe +
								                  bean.getAxis_side() + pipe +
								                  String.valueOf( tire_position ) + pipepipe ;
								  sides++;
								  session.save( bean );
								  if(sides==2)					  
									 break;
							  }
							  
							  
							  if(bean.getAxis_side().equals("L") 
							     && bean.getAxis_number()== i 
							     && Integer.valueOf(editConfigurationForm.getTire_for_axis(index)) == 6)
							  {
								  tire_position=321;
								  bean.setTire_position(tire_position);
								  configuration = configuration + 
					                              String.valueOf( bean.getAxis_number() ) +  pipe +
								                  bean.getAxis_side() + pipe +
								                  String.valueOf( tire_position ) + pipepipe ;
								  sides++;
								  session.save( bean );
								  if(sides==2)					  
									 break;
							  }
							  else if(bean.getAxis_side().equals("R") 
								     && bean.getAxis_number()== i 
								     && Integer.valueOf(editConfigurationForm.getTire_for_axis(index)) == 6)
							  {
								  tire_position=123;
								  bean.setTire_position(tire_position);
								  configuration = configuration + 
					                              String.valueOf( bean.getAxis_number() ) +  pipe +
								                  bean.getAxis_side() + pipe +
								                  String.valueOf( tire_position ) + pipepipe ;
								  sides++;
								  session.save( bean );
								  if(sides==2)					  
									 break;
							  }
					
							  
							  //tire_position = Integer.valueOf(truckTireConfigurationNewForm.getTire_for_axis(i));
							 // bean.setTire_position(tire_position);
							  
							  
							 
						  }
						  index++;
					  }
					  
					  
					  //String s=(String)truckTireConfigurationNewForm.getTire_for_axis(0);
					  
					  beanTruckTireConfiguration.setConfiguration(configuration);
					  session.update(beanTruckTireConfiguration);
					  tx.commit();
					  
					  
					  
					  forward = mapping.findForward("ok");
					  httpSession.setAttribute("done", new String("pre.jsp?&process='controlPanel'&action='pre.do'&target=''"));
					   
					  
				  
				  
				 
				  
				 // httpSession.setAttribute("done", new String("pre.jsp?&process='truckTireConfigurationNew'&action='pre.do'&target=''"));
			   
	
				}

				
				// do something here

			} catch (Exception e) {

                e.printStackTrace();
                
				// Report the error using the appropriate name and ID.
				com.bituos.Error error = new com.bituos.Error( e.getMessage(), request);
				errors.add("name", new ActionError("id"));

			}
			finally	{
				if ( session != null )
					 session.close(); 
				  
				if ( sessionFactory != null )
					 sessionFactory.close();
			}

		  }          
 


		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}

		// Finish with
		return (forward);

	}
}
