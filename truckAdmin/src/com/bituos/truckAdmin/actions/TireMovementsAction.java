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
 * @author 
 */


import javax.servlet.http.*;
import com.bituos.truckAdmin.*;
import com.bituos.*;
import java.util.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

public class TireMovementsAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		TireMovementsForm tireMovementsForm = (TireMovementsForm) form;

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
				sessionFactory = config.getConfiguration(request).buildSessionFactory();
				session = sessionFactory.openSession();
			
				if(tireMovementsForm.getProcess().equals("add"))
				 {
				
					String query ="";
					List list=null;
					
					
					
					if(tireMovementsForm.getId_type_place().equals("1"))//Mover a Almacén
					{
						
					}else if(tireMovementsForm.getId_type_place().equals("2"))//Mover a Taller
					{
						
					}else if(tireMovementsForm.getId_type_place().equals("3"))//Mover a Vehicle
					{
						BeanTire beanTire =(BeanTire)httpSession.getAttribute("selectedBeanTire");
						if(beanTire == null)
						{
							httpSession.setAttribute("done", "/pre.jsp?&process='tireMovements'&action='pre.do'&target=''" );
							com.bituos.Error error = new com.bituos.Error("No selecciono Ningun Neumático  .", request);
							forward = mapping.findForward("error");
							return forward;
						}
						
						BeanTruck beanTruck =(BeanTruck)httpSession.getAttribute("beanTruck");
						if(beanTruck == null)
						{
							httpSession.setAttribute("done", "/pre.jsp?&process='tireMovements'&action='pre.do'&target=''" );
							com.bituos.Error error = new com.bituos.Error("No selecciono Ningun Vehículo.", request);
							forward = mapping.findForward("error");
							return forward;
						}
				
						BeanTireMovements beanTireMovements = new BeanTireMovements();
						BeanTypePlace beanTypePlace = (BeanTypePlace) session.load( BeanTypePlace.class, new Integer( tireMovementsForm.getId_type_place() ) );
						BeanTypePosition beanTypePosition = (BeanTypePosition) session.load( BeanTypePosition.class, new Integer( 1 ) );//En el Vheiculo
						
						BeanTruckTireConfigurationDetail beanTruckTireConfigurationDetail = (BeanTruckTireConfigurationDetail)httpSession.getAttribute("beanTruckTirePosition");
						if(beanTruckTireConfigurationDetail == null)
						{
							httpSession.setAttribute("done", "/pre.jsp?&process='tireMovements'&action='pre.do'&target=''" );
							com.bituos.Error error = new com.bituos.Error("No selecciono ninguna posicion para el Neumático.", request);
							forward = mapping.findForward("error");
							return forward;
						}
						
						tx = session.beginTransaction();
						
						
						
						beanTireMovements.setId_tire(beanTire);
						Date ahora = new Date();
						beanTireMovements.setDate_reg(ahora);
						beanTireMovements.setDeep(Double.valueOf( tireMovementsForm.getDeep() ));
						beanTireMovements.setMeasurentment(Double.valueOf(tireMovementsForm.getMeasurement()));
						beanTireMovements.setId_type_measure(beanTire.getId_type_measure());
						beanTireMovements.setId_type_place(beanTypePlace);
						
						if(beanTire.getKm_int() <= Integer.valueOf( tireMovementsForm.getKm_int() ) )	
						{
						    beanTireMovements.setKm_int(Integer.valueOf( tireMovementsForm.getKm_int() ) );
						}
						else
						{
							httpSession.setAttribute("done", "pre.jsp?&process='tireMovements'&action='pre.do'&target=''" );
							com.bituos.Error error = new com.bituos.Error("El kilometraje del neumático no puede ser menor que el registrado anteriormente.", request);
							forward = mapping.findForward("error");
							return forward;
						}
							
						//BeanTireMovements beanTireMovements = (beanTireMovements) session.load( BeanTruckTireConfigurationDetail.class, new Integer( 0 ) );//Posicion Ninguna
						
						query = " FROM BeanTireMovements t" +
					            " WHERE t.id_tire =" + beanTire.getId() +				
					            " ORDER by t.id DESC LIMIT 1";
				
						list = session.createQuery(query).list();
						BeanTireMovements beanTireMovementsPrevious= null;
						
						if(!list.isEmpty())
						{
							beanTireMovementsPrevious = (BeanTireMovements) list.get(0);						
						
							if(beanTireMovementsPrevious.getKm_truck() <= Integer.valueOf( tireMovementsForm.getKm_truck() ) )
							{
							    
							    beanTireMovements.setKm_truck(Integer.valueOf(tireMovementsForm.getKm_truck())); 
							}
							else
							{
								httpSession.setAttribute("done", "pre.jsp?&process='tireMovements'&action='pre.do'&target=''" );
								com.bituos.Error error = new com.bituos.Error("El kilometraje del vehículo no puede ser menor que el registrado anteriormente.", request);
								forward = mapping.findForward("error");
								return forward;
							}
						}
						  
						
						
						
						
						beanTireMovements.setDesign(beanTire.getDesign());
						beanTireMovements.setBuy_of_reference(tireMovementsForm.getBuy_of_reference());
						beanTireMovements.setPrice(Double.valueOf(tireMovementsForm.getPrice()));
						
						
						query = " FROM BeanTireTruck t" +
		                        " WHERE t.id_tire =" + beanTire.getId() +
		                        " AND t.active ='Y'"+
		                        " ORDER BY t.id DESC LIMIT 1";
						
						list = session.createQuery(query).list();
						if( !list.isEmpty())
						{
							BeanTireTruck beanTireTruckOld = (BeanTireTruck)list.get(0);
							beanTireTruckOld.setActive("N");
							session.update(beanTireTruckOld);
						}
							
						
						BeanTireTruck beanTireTruck = new BeanTireTruck();
						beanTireTruck.setId_tire(beanTire);
						beanTireTruck.setId_truck(beanTruck);
						
						beanTireTruck.setId_type_position(beanTypePosition);
						
						
						beanTireTruck.setPosition(beanTruckTireConfigurationDetail);
						beanTireTruck.setActive("Y");
						
						
							
						session.save(beanTireMovements);
						session.save( beanTireTruck );
						
						tx.commit();
						
						query = " FROM BeanTireTruck t" +
					            " WHERE t.id_truck =" + beanTruck.getId() +
					            " AND t.active = 'Y'";
						
						
						
						list = session.createQuery(query).list();	
						String[] tires = null;
						String[] sides = null;
						if(!list.isEmpty())
						{ 
						    tires = new String[list.size()];
						    sides = new String[list.size()];
						    List<BeanTireTruck> tireExtras = new ArrayList<BeanTireTruck>();
							                      
							BeanTireTruck bean;
							Iterator iter = list.iterator();
							int counter = 0 ;
							while ( iter.hasNext() )
							  {   							 
								 bean= (BeanTireTruck) iter.next();
								  if(bean.getId_type_position().getId() == 1)
								   {								   
									  
									  String tdSelected = "tire" + String.valueOf( bean.getPosition().getId() );
									  tires[counter]=tdSelected;
									  sides[counter]=bean.getPosition().getAxis_side();
									  
									 
								   }else if(bean.getId_type_position().getId() == 2)
								   {
									   String tdSelectedExtra = "tireExtra" + String.valueOf( bean.getId() );								   
									   tireExtras.add(bean); 
									   tires[counter]=tdSelectedExtra;
									   sides[counter]="NA";
									   
								   }
								  counter++;
							  }
							httpSession.setAttribute("tires", tires ); 
							httpSession.setAttribute("sides", sides );
							
							
						}
					}else if(tireMovementsForm.getId_type_place().equals("4"))// Vehículo  extra
					{
						BeanTire beanTire =(BeanTire)httpSession.getAttribute("selectedBeanTire");
						if(beanTire == null)
						{
							httpSession.setAttribute("done", "/pre.jsp?&process='tireMovements'&action='pre.do'&target=''" );
							com.bituos.Error error = new com.bituos.Error("No selecciono Ningun Neumático  .", request);
							forward = mapping.findForward("error");
							return forward;
						}
						
						BeanTruck beanTruck =(BeanTruck)httpSession.getAttribute("beanTruck");
						if(beanTruck == null)
						{
							httpSession.setAttribute("done", "/pre.jsp?&process='tireMovements'&action='pre.do'&target=''" );
							com.bituos.Error error = new com.bituos.Error("No selecciono Ningun Vehículo.", request);
							forward = mapping.findForward("error");
							return forward;
						}
				
						BeanTireMovements beanTireMovements = new BeanTireMovements();
						BeanTypePlace beanTypePlace = (BeanTypePlace) session.load( BeanTypePlace.class, new Integer( tireMovementsForm.getId_type_place() ) );
						BeanTypePosition beanTypePosition = (BeanTypePosition) session.load( BeanTypePosition.class, new Integer( 2 ) );//En el Vehículo  extra
						
						BeanTruckTireConfigurationDetail beanTruckTireConfigurationDetail = (BeanTruckTireConfigurationDetail) session.load( BeanTruckTireConfigurationDetail.class, new Integer( 0 ) );//Posicion Ninguna
						/*BeanTruckTireConfigurationDetail beanTruckTireConfigurationDetail = (BeanTruckTireConfigurationDetail)httpSession.getAttribute("beanTruckTirePosition");
						if(beanTruckTireConfigurationDetail == null)
						{
							httpSession.setAttribute("done", "/pre.jsp?&process='tireMovements'&action='pre.do'&target=''" );
							com.bituos.Error error = new com.bituos.Error("No selecciono Ningun Posicion para el Neumático.", request);
							forward = mapping.findForward("error");
							return forward;
						}*/
						tx = session.beginTransaction();
						
						beanTireMovements.setId_tire(beanTire);
						Date ahora = new Date();
						beanTireMovements.setDate_reg(ahora);
						beanTireMovements.setDeep(Double.valueOf( tireMovementsForm.getDeep() ));
						beanTireMovements.setMeasurentment(Double.valueOf(tireMovementsForm.getMeasurement()));
						beanTireMovements.setId_type_measure(beanTire.getId_type_measure());
						beanTireMovements.setId_type_place(beanTypePlace);
						beanTireMovements.setKm_int(Integer.valueOf( tireMovementsForm.getKm_int()));
						beanTireMovements.setKm_truck(Integer.valueOf( tireMovementsForm.getKm_truck()));
						beanTireMovements.setDesign(beanTire.getDesign());
						beanTireMovements.setBuy_of_reference(tireMovementsForm.getBuy_of_reference());
						beanTireMovements.setPrice(Double.valueOf(tireMovementsForm.getPrice()));
						
						
						query = " FROM BeanTireTruck t" +
		                        " WHERE t.id_truck =" + beanTruck.getId() +
		                        " AND t.id_tire =" + beanTire.getId() +
		                        " AND t.active ='Y'"+
		                        " ORDER BY t.id DESC LIMIT 1";
						
						list = session.createQuery(query).list();
						if( !list.isEmpty())
						{
							BeanTireTruck beanTireTruckOld = (BeanTireTruck)list.get(0);
							beanTireTruckOld.setActive("N");
							session.update(beanTireTruckOld);
						}
							
						
						BeanTireTruck beanTireTruck = new BeanTireTruck();
						beanTireTruck.setId_tire(beanTire);
						beanTireTruck.setId_truck(beanTruck);
						
						beanTireTruck.setId_type_position(beanTypePosition);
						
						
						beanTireTruck.setPosition(beanTruckTireConfigurationDetail);
						beanTireTruck.setActive("Y");
						
						
							
						session.save(beanTireMovements);
						session.save( beanTireTruck );
						
						tx.commit();
						
						query = " FROM BeanTireTruck t" +
					            " WHERE t.id_truck =" + beanTruck.getId() +
					            " AND t.active = 'Y'";
						
						
						
						list = session.createQuery(query).list();	
						String[] tires = null;
						String[] sides = null;
						if(!list.isEmpty())
						{ 
						    tires = new String[list.size()];
						    sides = new String[list.size()];
						    List<BeanTireTruck> tireExtras = new ArrayList<BeanTireTruck>();
							List<String> tireExtrasTd = new ArrayList<String>();
						                            
							BeanTireTruck bean;
							Iterator iter = list.iterator();
							int counter = 0 ;
							while ( iter.hasNext() )
							  {   							 
								 bean= (BeanTireTruck) iter.next();
								  if(bean.getId_type_position().getId() == 1)
								   {								   
									  
									  String tdSelected = "tire" + String.valueOf( bean.getPosition().getId() );
									  tires[counter]=tdSelected;
									  sides[counter]=bean.getPosition().getAxis_side();
									  
									  //counter++;
								   }else if(bean.getId_type_position().getId() == 2)
								   {
									   String tdSelectedExtra = "tireExtra" + String.valueOf( bean.getId() );								   
									   tireExtras.add(bean); 
									   tires[counter]=tdSelectedExtra;
									   sides[counter]="NA";
									   
								   }
								  counter++;
							  }
							httpSession.setAttribute("tires", tires ); 
							httpSession.setAttribute("sides", sides );
						}
						
					}else if(tireMovementsForm.getId_type_place().equals("5"))//Mover a Renovado 
					{
						
					}else if(tireMovementsForm.getId_type_place().equals("6"))//Mover a Basura
					{
						
					}else if(tireMovementsForm.getId_type_place().equals("7"))//Mover a No Asignado
					{
						
					}
					
					
					
					httpSession.removeAttribute("truckHtml");
					
					forward = mapping.findForward("ok");
					httpSession.setAttribute("done", new String("./pre.jsp?&process='tireMovements'&action='pre.do'&target=''"));
					
	
				 } 
				else if(tireMovementsForm.getProcess().equals("select"))
				 {
					
					String query = "";					
					
					List list;
					
				
					BeanTire bean = (BeanTire) session.load( BeanTire.class, new Integer( tireMovementsForm.getId_tire() ) );
					httpSession.removeAttribute("selectedBeanTire");
					httpSession.setAttribute("selectedBeanTire", bean );
					
					tireMovementsForm.setDesign(bean.getDesign());
					tireMovementsForm.setDeep(String.valueOf( bean.getDeep() ));
					tireMovementsForm.setId_type_unit_of_measure_description(bean.getId_type_unit_of_measure().getDescription());
					
					query = "  FROM BeanTypeMeasure u";
					list = session.createQuery(query).list();
					httpSession.setAttribute("beanTypeMeasureList", list );
					
					BeanTypeMeasure beanTypeMeasure = bean.getId_type_measure();
					httpSession.setAttribute("beanTypeMeasure", beanTypeMeasure );
						
					
					query = " FROM BeanTypePlace u" +
				  		    " WHERE u.id <> 7";
					list = session.createQuery(query).list();
					httpSession.setAttribute("beanTypePlaceList", list );
					
					BeanTypePlace beanTypePlace = bean.getId_type_place();
					httpSession.setAttribute("beanTypePlace", beanTypePlace );
					
					
					query = "  FROM BeanTypeTireStatus u";
					list = session.createQuery(query).list();
					httpSession.setAttribute("beanTypeTireStatusList", list );
					
					BeanTypeTireStatus beanTypeTireStatus = bean.getId_type_tire_status();
					httpSession.setAttribute("beanTypeTireStatus", beanTypeTireStatus );

					tireMovementsForm.setSerial_number(bean.getSerial_number());
					tireMovementsForm.setKm_int(String.valueOf(  bean.getKm_int()));
					
					
					
					query = " FROM BeanTireMovements t" +
				            " WHERE t.id_tire =" + tireMovementsForm.getId_tire() +				
				            " ORDER by t.id DESC LIMIT 1";
					
					list = session.createQuery(query).list();
					BeanTireMovements beanTireMovements = null;
					
					if(!list.isEmpty())
					{
					   beanTireMovements = (BeanTireMovements) list.get(0);
					   tireMovementsForm.setDesign(beanTireMovements.getDesign());
					   tireMovementsForm.setDeep(String.valueOf( beanTireMovements.getDeep()) );
					  
					   httpSession.removeAttribute("beanTypeMeasure");
					   beanTypeMeasure = beanTireMovements.getId_type_measure();
					   httpSession.setAttribute("beanTypeMeasure", beanTypeMeasure );
					 
					   httpSession.removeAttribute("beanTypePlace");
					   beanTypePlace = beanTireMovements.getId_type_place();
					   httpSession.setAttribute("beanTypePlace", beanTypePlace );
					   
					   tireMovementsForm.setKm_int(String.valueOf( beanTireMovements.getKm_int()));
					   
					   tireMovementsForm.setKm_truck(String.valueOf( beanTireMovements.getKm_truck()));
					   
					   tireMovementsForm.setPrice(String.valueOf(beanTireMovements.getPrice()));
					   
					   tireMovementsForm.setBuy_of_reference(beanTireMovements.getBuy_of_reference());
					   
					   //beanTireMovements.setMeasurentment(Double.valueOf(tireMovementsForm.getMeasurement()));
					   tireMovementsForm.setMeasurement(String.valueOf( beanTireMovements.getMeasurentment()) );
					   
					   
					   query = " FROM BeanTireTruck t" +
                               " WHERE t.id_tire =" + beanTireMovements.getId_tire().getId() +
                               " AND t.active ='Y'" +
                               " ORDER BY t.id DESC LIMIT 1"; 

					   list = session.createQuery(query).list();
					   BeanTruck beanTruck =null;
					   //BeanTireTruck beanTireTruck = null;
					   if(!list.isEmpty())
					   {
						   beanTruck = ((BeanTireTruck)list.get(0)).getId_truck();
						   query = " FROM BeanTireTruck t" +
                   		   		   " WHERE t.id_truck =" + beanTruck.getId();

						   String[] tires = null;
						   String[] sides = null;
						   List<BeanTireTruck> tireExtras = null;
						   List<String> tireExtrasTd = null;
						   Iterator iter;
						   if(!list.isEmpty())
						   { 
						      tires = new String[list.size()];
						      sides = new String[list.size()]; 
						      tireExtras = new ArrayList<BeanTireTruck>();
						     
							  BeanTireTruck beanTireTruck;
							  iter = list.iterator();
							  int counter = 0 ;
							  while ( iter.hasNext() )
							  {   							 
								  beanTireTruck= (BeanTireTruck) iter.next();
								  if(beanTireTruck.getId_type_position().getId() == 1)
								   {								   
									  
									  String tdSelected = "tire" + String.valueOf( beanTireTruck.getPosition().getId() );
									  tires[counter]=tdSelected;
									  sides[counter]=beanTireTruck.getPosition().getAxis_side();
									  
									  //counter++;
								   }else if(beanTireTruck.getId_type_position().getId() == 2)
								   {
									   String tdSelectedExtra = "tireExtra" + String.valueOf( beanTireTruck.getId() );								   
									   tireExtras.add(beanTireTruck); 
									   tires[counter]=tdSelectedExtra;
									   sides[counter]="NA";
									   
								   }
								  
								  counter++;
							  }
							  httpSession.setAttribute("tires", tires ); 
							  httpSession.setAttribute("sides", sides );
							  
						  }
						   
						   
						  query =   " FROM BeanTruckTireConfigurationDetail u" +
			            	        " WHERE u.id_truck_tire_configuration.id = " + beanTruck.getId_truck_configuration().getId();

						
						  list = session.createQuery(query).list();
						  int maxCols = 0;
						  int tireAxis=0;
						  int tireFrontAxis=0;
						  int tireBottomAxis=0;
						  List axisList1 = null;
						  List<BeanTruckTireConfigurationDetail> axisList2 = new ArrayList<BeanTruckTireConfigurationDetail>(); 
						  int[] tireByAxis = new int[beanTruck.getId_truck_configuration().getAxis_count()];	
						  String[] typeAxis = new String[beanTruck.getId_truck_configuration().getAxis_count()];
						
						  if(!list.isEmpty())
						  {
						    httpSession.removeAttribute("beanTruckTireConfigurationDetailList");
						    httpSession.setAttribute("beanTruckTireConfigurationDetailList", list );
						  
						 
						    BeanTruckTireConfigurationDetail beanTruckTireConfigurationDetail= null;
						 
						    for(int i= 1; i <= beanTruck.getId_truck_configuration().getAxis_count(); i++ ) 
						    {
							  
						
							   query =   " FROM BeanTruckTireConfigurationDetail u" +
								  		 " WHERE u.id_truck_tire_configuration.id = " + beanTruck.getId_truck_configuration().getId() +
								         " AND u.axis_number = " + i +
								         " GROUP BY u.is_front_axis";
					  
							   beanTruckTireConfigurationDetail = (BeanTruckTireConfigurationDetail)session.createQuery(query).list().get(0);
					  
							  
							  
							   typeAxis[i - 1]= beanTruckTireConfigurationDetail.getIs_front_axis();
							
							  
							   query =   " FROM BeanTruckTireConfigurationDetail u" +
								   		 " WHERE u.id_truck_tire_configuration.id = " + beanTruck.getId_truck_configuration().getId() +
								         " AND u.axis_number = " + i;// +
								      
							  
							   axisList1 = session.createQuery(query).list();
							  
							  
							   if(!axisList1.isEmpty())	
							   {
							      maxCols =axisList1.size();
							      tireByAxis[i-1]=axisList1.size();
							      iter = axisList1.iterator();						    
							      while(iter.hasNext())
						    	  {						     
							        beanTruckTireConfigurationDetail = (BeanTruckTireConfigurationDetail)iter.next();
							    						      
							        axisList2.add(beanTruckTireConfigurationDetail);
							     
						    	  } 
							   } 
							  else
								maxCols = 0;
							  
							  if(tireAxis > maxCols)
							  { 
								  maxCols = tireAxis; 
							  } 
							  
						    }
						  
						    BuildTruckHtml buildTruckHtml = new  BuildTruckHtml(beanTruck.getId_truck_configuration().getAxis_count(), maxCols); 
						    buildTruckHtml.setBeanTruckTireConfigurationDetailList(axisList2);
						    buildTruckHtml.setTireByAxis(tireByAxis);
						    buildTruckHtml.setTypeAxis(typeAxis);
						    buildTruckHtml.setTireExtras(tireExtras);
						    buildTruckHtml.setSession(session);
						    
						    
						  
						    String truckHtml = buildTruckHtml.getHTMLV2();
						    httpSession.removeAttribute("truckHtml");
						    httpSession.setAttribute("truckHtml", truckHtml );
						  
						  
						    query =" FROM BeanTruck u" +
				                   " WHERE u.id = " + beanTruck.getId() +
				                   " AND u.active = 'Y'";
						    
						    list = session.createQuery(query).list();
						    if(!list.isEmpty())
						     {	
							    httpSession.removeAttribute("beanTruckList");
							    httpSession.setAttribute("beanTruckList", list ); 
						     }
						    
						    httpSession.removeAttribute("beanTruck");
						    httpSession.setAttribute("beanTruck", beanTruck );
						  
						  }
						
						
						
					   }else
					   {  
						  httpSession.removeAttribute("truckHtml");
						  query =  " FROM BeanTruck u" +
				                   " WHERE u.active = 'Y'";
				    
					      list = session.createQuery(query).list();
					      if(!list.isEmpty())
					        {	
						      httpSession.removeAttribute("beanTruckList");
						      httpSession.setAttribute("beanTruckList", list ); 
					        }
					    
					      httpSession.removeAttribute("beanTruck");
					    
						   //httpSession.removeAttribute("beanTruck");
					   }
					   
					   
					   
					  
					}else
					{
					  httpSession.removeAttribute("beanTruckList");
					  httpSession.removeAttribute("beanTruck");
					  httpSession.removeAttribute("truckHtml");
					}
						

		
					  
					
					
					forward = mapping.findForward("loop");
					
				 }
				else if(tireMovementsForm.getProcess().equals("selectPlace"))
				 {
					String query;
					List list;
					BeanTire beanTire = (BeanTire)httpSession.getAttribute("selectedBeanTire" );
					tireMovementsForm.setDesign(beanTire.getDesign());
					tireMovementsForm.setDeep(String.valueOf( beanTire.getDeep() ));
					tireMovementsForm.setId_type_unit_of_measure_description(beanTire.getId_type_unit_of_measure().getDescription());
					tireMovementsForm.setSerial_number(beanTire.getSerial_number());
					tireMovementsForm.setKm_int(String.valueOf( beanTire.getKm_int()));
					
					BeanTypePlace beanTypePlace = (BeanTypePlace) session.load( BeanTypePlace.class, new Integer( tireMovementsForm.getId_place() ) );
				    httpSession.setAttribute("beanTypePlace", beanTypePlace ); 
					
				    httpSession.removeAttribute("truckHtml");
				    
					if(tireMovementsForm.getId_place().equals("3") )//vehiculo
					{					
					 	
					  query =   " FROM BeanTruck u" +
					            " WHERE u.id <> 0" +
					            " AND u.active = 'Y'" +	
					            " ORDER BY u.date_reg DESC LIMIT 20";
					  list = session.createQuery(query).list();
					  if(!list.isEmpty())
					  {	
						  httpSession.removeAttribute("beanTruckList");
						  httpSession.setAttribute("beanTruckList", list ); 
					  }
					  
						  
					  
					  
					  
					}else if(tireMovementsForm.getId_place().equals("4") )//vehiculo extra
					{
					  query =   " FROM BeanTruck u" +
			            		" WHERE u.id <> 0" +
			            		" AND u.active = 'Y'" +	
			            		" ORDER BY u.date_reg DESC LIMIT 20";
					  
					  list = session.createQuery(query).list();
					  if(!list.isEmpty())
					  {	
						  httpSession.removeAttribute("beanTruckList");
						  httpSession.setAttribute("beanTruckList", list ); 
					  }
					}
					else
					{
						httpSession.removeAttribute("beanTruckList");
					}
						
					forward = mapping.findForward("loop");
				 }else if(tireMovementsForm.getProcess().equals("selectVehicle"))
				 {
						
					String query = "";				
					List list;
					
					BeanTruck beanTruck = (BeanTruck) session.load( BeanTruck.class, new Integer( tireMovementsForm.getId_truck() ) );
					httpSession.removeAttribute("beanTruck");
					httpSession.setAttribute("beanTruck", beanTruck );
					
					BeanTire beanTire =  (BeanTire)httpSession.getAttribute("selectedBeanTire");
					String sql  = "";	
					
					query = " FROM BeanTireTruck t" +
	                        " WHERE t.id_truck =" + beanTruck.getId() +
	                        " AND t.active = 'Y'";
		
		
					tireMovementsForm.setId_type_unit_of_measure_description(beanTire.getId_type_unit_of_measure().getDescription());
					tireMovementsForm.setSerial_number(beanTire.getSerial_number());
					
					list = session.createQuery(query).list();	
					String[] tires = null;
					String[] sides = null;
					List<BeanTireTruck> tireExtras = null;
					List<String> tireExtrasTd = null;
					Iterator iter;
					if(!list.isEmpty())
					{ 
					    tires = new String[list.size()];
					    sides = new String[list.size()];
					    tireExtras = new ArrayList<BeanTireTruck>();
					   
					    
						BeanTireTruck bean;
						iter = list.iterator();
						int counter = 0 ;
						while ( iter.hasNext() )
						  {   							 
							 bean= (BeanTireTruck) iter.next();
							  if(bean.getId_type_position().getId() == 1)
							   {								   
								  
								  String tdSelected = "tire" + String.valueOf( bean.getPosition().getId() );
								  tires[counter]=tdSelected;
								  sides[counter]=bean.getPosition().getAxis_side();
								  
								  
							   }else if(bean.getId_type_position().getId() == 2)
							   {
								   String tdSelectedExtra = "tireExtra" + String.valueOf( bean.getId() );								   
								   tireExtras.add(bean); 
								   tires[counter]=tdSelectedExtra;
								   sides[counter]="NA";
								   
							   }
							  counter++;
						  }
						httpSession.setAttribute("tires", tires ); 
						httpSession.setAttribute("sides", sides );
						
					}
					
					
					
					query =   " FROM BeanTruckTireConfigurationDetail u" +
			            	  " WHERE u.id_truck_tire_configuration.id = " + beanTruck.getId_truck_configuration().getId();

						
					list = session.createQuery(query).list();
					int maxCols = 0;
					int tireAxis=0;
					int tireFrontAxis=0;
					int tireBottomAxis=0;
					List axisList1 = null;
					List<BeanTruckTireConfigurationDetail> axisList2 = new ArrayList<BeanTruckTireConfigurationDetail>(); 
					int[] tireByAxis = new int[beanTruck.getId_truck_configuration().getAxis_count()];	
					String[] typeAxis = new String[beanTruck.getId_truck_configuration().getAxis_count()];
					//Iterator iter;
					//BeanTruckTireConfigurationDetail beanTruckTireConfigurationDetail= null;
					if(!list.isEmpty())
					{
					  httpSession.removeAttribute("beanTruckTireConfigurationDetailList");
					  httpSession.setAttribute("beanTruckTireConfigurationDetailList", list );
					  
					 // Iterator iter;
					  BeanTruckTireConfigurationDetail beanTruckTireConfigurationDetail= null;
					 
					  for(int i= 1; i <= beanTruck.getId_truck_configuration().getAxis_count(); i++ ) 
					  {
						  
					
						  query =   " FROM BeanTruckTireConfigurationDetail u" +
							  		" WHERE u.id_truck_tire_configuration.id = " + beanTruck.getId_truck_configuration().getId() +
							        " AND u.axis_number = " + i +
							        " GROUP BY u.is_front_axis";
				  
						  beanTruckTireConfigurationDetail = (BeanTruckTireConfigurationDetail)session.createQuery(query).list().get(0);
				  
						  
						  
						  typeAxis[i - 1]= beanTruckTireConfigurationDetail.getIs_front_axis();
						
						  
						  query =   " FROM BeanTruckTireConfigurationDetail u" +
							  		" WHERE u.id_truck_tire_configuration.id = " + beanTruck.getId_truck_configuration().getId() +
							        " AND u.axis_number = " + i;// +
							       // " ORDER BY u.tire_position";
						  
						  axisList1 = session.createQuery(query).list();
						  
						  
						  if(!axisList1.isEmpty())	
						  {
						    maxCols =axisList1.size();
						    tireByAxis[i-1]=axisList1.size();
						    iter = axisList1.iterator();						    
						    while(iter.hasNext())
					    	{						     
						      beanTruckTireConfigurationDetail = (BeanTruckTireConfigurationDetail)iter.next();
						    						      
						      axisList2.add(beanTruckTireConfigurationDetail);
						     
					    	} 
						  } 
						  else
							maxCols = 0;
						  
						  if(tireAxis > maxCols)
						  { 
							  maxCols = tireAxis; 
						  } 
						  
					  }
					  
					  BuildTruckHtml buildTruckHtml = new  BuildTruckHtml(beanTruck.getId_truck_configuration().getAxis_count(), maxCols); 
					  buildTruckHtml.setBeanTruckTireConfigurationDetailList(axisList2);
					  buildTruckHtml.setTireByAxis(tireByAxis);
					  buildTruckHtml.setTypeAxis(typeAxis);
					  buildTruckHtml.setTireExtras(tireExtras);
					  buildTruckHtml.setSession(session);
					  
					  String truckHtml = buildTruckHtml.getHTMLV2();
					  httpSession.removeAttribute("truckHtml");
					  httpSession.setAttribute("truckHtml", truckHtml );
					  
					}
					
					forward = mapping.findForward("loop");
					
				 }else if(tireMovementsForm.getProcess().equals("selectPosition"))					 
				 {
					BeanTruckTireConfigurationDetail beanTruckTireConfigurationDetail = (BeanTruckTireConfigurationDetail) session.load( BeanTruckTireConfigurationDetail.class, new Integer( tireMovementsForm.getId_truck_tire_configuration_detail() ) );
					httpSession.setAttribute("beanTruckTirePosition", beanTruckTireConfigurationDetail );
					httpSession.removeAttribute("truckHtml");
					 
					BeanTire beanTire = (BeanTire)httpSession.getAttribute("selectedBeanTire" );
					tireMovementsForm.setDesign(beanTire.getDesign());
					tireMovementsForm.setDeep(String.valueOf( beanTire.getDeep() ));
					tireMovementsForm.setId_type_unit_of_measure_description(beanTire.getId_type_unit_of_measure().getDescription());
					tireMovementsForm.setSerial_number(beanTire.getSerial_number());
							 
					String query =  " FROM BeanTireMovements t" +
									" WHERE t.id_tire =" + beanTire.getId() +				
									" ORDER by t.id DESC LIMIT 1";
					
					List list = session.createQuery(query).list();
					BeanTireMovements beanTireMovements = null;
					
					if(!list.isEmpty())
					{
					  beanTireMovements = (BeanTireMovements) list.get(0);
					  tireMovementsForm.setKm_int(String.valueOf( beanTireMovements.getKm_int()));
						
					}else{ 
					  tireMovementsForm.setKm_int(String.valueOf( beanTire.getKm_int()));
					}
					 
					
					 
					 
					BeanTypePlace beanTypePlace = (BeanTypePlace) session.load( BeanTypePlace.class, new Integer( 3) );
					httpSession.removeAttribute("beanTypePlace");
					httpSession.setAttribute("beanTypePlace" , beanTypePlace);
					 
					 
					forward = mapping.findForward("loop");
				 }else if(tireMovementsForm.getProcess().equals("selectExtraTire"))					 
				 {
					 BeanTruckTireConfigurationDetail beanTruckTireConfigurationDetail = (BeanTruckTireConfigurationDetail) session.load( BeanTruckTireConfigurationDetail.class, new Integer( tireMovementsForm.getId_truck_tire_configuration_detail() ) );
					 httpSession.setAttribute("beanTruckTirePosition", beanTruckTireConfigurationDetail );
					 httpSession.removeAttribute("truckHtml");
					 
					 BeanTire beanTire = (BeanTire)httpSession.getAttribute("selectedBeanTire" );
					 tireMovementsForm.setDesign(beanTire.getDesign());
					 tireMovementsForm.setDeep(String.valueOf( beanTire.getDeep() ));
					 tireMovementsForm.setId_type_unit_of_measure_description(beanTire.getId_type_unit_of_measure().getDescription());
					 tireMovementsForm.setSerial_number(beanTire.getSerial_number());
					 tireMovementsForm.setKm_int(String.valueOf( beanTire.getKm_int()));
					 

					 
					 forward = mapping.findForward("loop");
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
