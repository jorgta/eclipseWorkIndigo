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

public class TruckTireConfigurationNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		TruckTireConfigurationNewForm truckTireConfigurationNewForm = (TruckTireConfigurationNewForm) form;

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
				
				if(truckTireConfigurationNewForm.getProcess().equals("add"))
				{
				
				  sessionFactory = config.getConfiguration(request).buildSessionFactory();
				  session = sessionFactory.openSession();
			
				  String query =   " FROM BeanTruckTireConfiguration u" 
					             + " WHERE u.description  = '" + truckTireConfigurationNewForm.getDescription() + "'";  
		
		          java.util.List list = session.createQuery(query).list();
		
					if ( list.isEmpty() )
					  {  
						  httpSession.removeAttribute("beanTruckTireConfiguration");
			              BeanTruckTireConfiguration beanTruckTireConfiguration = new BeanTruckTireConfiguration();
						  
						  beanTruckTireConfiguration.setDescription(truckTireConfigurationNewForm.getDescription());
						  beanTruckTireConfiguration.setAxis_count(Integer.valueOf( truckTireConfigurationNewForm.getAxis_count()));
						  Date ahora = new Date();								
						  beanTruckTireConfiguration.setDate_reg(ahora);
							
						  
						  httpSession.setAttribute("beanTruckTireConfiguration", beanTruckTireConfiguration);
						  
					
		
						
						 
						  
						  list = new ArrayList<BeanTruckTireConfigurationDetail>();
						  BeanTruckTireConfigurationDetail beanTruckTireConfigurationDetail;
						  int sides= 2;
						  
						 for(int i =1; i <= Integer.valueOf( truckTireConfigurationNewForm.getAxis_count()); i++ )
						  {  
							/* beanTruckTireConfigurationDetail =  new BeanTruckTireConfigurationDetail();					  
							 beanTruckTireConfigurationDetail.setAxis_number(i);
							 beanTruckTireConfigurationDetail.setId_truck_tire_configuration(beanTruckTireConfiguration);
								 
							  list.add(beanTruckTireConfigurationDetail);*/
							 
							 for(int j =1; j <= sides; j++ )
							{
							  beanTruckTireConfigurationDetail =  new BeanTruckTireConfigurationDetail();					  
							  beanTruckTireConfigurationDetail.setAxis_number(i);
							  if(j == 1)						  
							    beanTruckTireConfigurationDetail.setAxis_side("L");
							  
							  if(j == 2)
								beanTruckTireConfigurationDetail.setAxis_side("R"); 
							  
							  
							  beanTruckTireConfigurationDetail.setId_truck_tire_configuration(beanTruckTireConfiguration);
												 
							  list.add(beanTruckTireConfigurationDetail);
							  
							}
					      
							  
							  
						  }
						  
						  int axis_number = beanTruckTireConfiguration.getAxis_count();
						  httpSession.setAttribute("axis_number", axis_number);
						  
					
						  httpSession.setAttribute("truckTireConfigurationDetailList", list);
						  httpSession.setAttribute("save", true);
						  
						  
						  
						  httpSession.setAttribute("axisNumberSet", true);
						  
						  forward = mapping.findForward("loop");
							  
							 // httpSession.setAttribute("done", new String("pre.jsp?&process='truckTireConfigurationNew'&action='pre.do'&target=''"));
						   
					  }
					else
					{
						//httpSession.setAttribute("done", "truck/truckNew.jsp" );
						httpSession.setAttribute("done", new String("pre.jsp?&process='controlPanel'&action='pre.do'&target=''") );
						com.bituos.Error error = new com.bituos.Error("Ya existe una configuración con esa descripción.", request);
						forward = mapping.findForward("error");
					}
				}
			  else if(truckTireConfigurationNewForm.getProcess().equals("addTirePosition"))
				{
				  
				  sessionFactory = config.getConfiguration(request).buildSessionFactory();
				  session = sessionFactory.openSession();
	
				   tx = session.beginTransaction();			 
					
				 
				   httpSession.removeAttribute("htmlTable");
				  
				  
				  BeanTruckTireConfiguration beanTruckTireConfiguration =(BeanTruckTireConfiguration) httpSession.getAttribute("beanTruckTireConfiguration");
				  session.save( beanTruckTireConfiguration );	
				  
				  List truckTireConfigurationDetailList =(List) new ArrayList<BeanTruckTireConfigurationDetail>();//httpSession.getAttribute("truckTireConfigurationDetailList");
				  
				  
				  String pipe ="|";
				  String pipepipe ="||";
				  
				  // ||id_truck_configuration|axis_number|axis_side|tire_position||
				  String configuration =  pipepipe +
										  String.valueOf( beanTruckTireConfiguration.getId()) + pipe;
				  
				  


				
				 String tr="";
				 
				// String hmtlConfiguration=htmlChasisFront; 
					
				 BeanTruckTireConfigurationDetail bean;
				 
				 int tire_position;
				 int sides;
				 int index= 0;
				  
				 int maxCols=0;
				 int tireAxis=0;
				 Iterator iter;
				 for(int i=0; i < Integer.valueOf(truckTireConfigurationNewForm.getAxis_count()); i++ )
				 {
					 
					 tireAxis = Integer.valueOf(truckTireConfigurationNewForm.getTire_for_axis(i)); 
					
					 if(tireAxis > maxCols)
					  { 
						  maxCols = tireAxis; 
					  } 
					 
					 for(int j=0; j < tireAxis; j++ )
					 {
						 bean = new BeanTruckTireConfigurationDetail();
						 bean.setAxis_number((i + 1));
						 bean.setId_truck_tire_configuration(beanTruckTireConfiguration);
						 if(j < (tireAxis / 2))						
						   bean.setAxis_side("L");					   
						
						 
						 if(j >= (tireAxis / 2))						 
						   bean.setAxis_side("R");						 						 
						 
						  
												 						 
						 if ((tireAxis / 2) == 1)
							 bean.setTire_position(1); 
						 
											 
						 
						 if ((tireAxis / 2) == 2 && j ==0)
							 bean.setTire_position(1); 
						 else if ((tireAxis / 2) == 2 && j ==1)
							 bean.setTire_position(2);
						 else if ((tireAxis / 2) == 2 && j ==2)
							 bean.setTire_position(1);
						 else if ((tireAxis / 2) == 2 && j ==3)
							 bean.setTire_position(2);
						 
						 
						 
						 if ((tireAxis / 2) == 3 && j ==0)
							 bean.setTire_position(1); 
						 else if ((tireAxis / 2) == 3 && j ==1)
							 bean.setTire_position(2);
						 else if ((tireAxis / 2) == 3 && j ==2)
							 bean.setTire_position(3);
						 else if ((tireAxis / 2) == 3 && j ==3)
							 bean.setTire_position(1);
						 else if ((tireAxis / 2) == 3 && j ==4)
							 bean.setTire_position(2);
						 else if ((tireAxis / 2) == 3 && j ==5)
							 bean.setTire_position(3);
						 
						 
						
						 if(truckTireConfigurationNewForm.getAxis_type(i).equals("Y"))
						  {
							  bean.setIs_front_axis("Y"); 
						  }
						  else
						  {
							  bean.setIs_front_axis("N");
						  }
						 
						 configuration = configuration + 
				                         String.valueOf( bean.getAxis_number() ) +  pipe +
						                 bean.getAxis_side() + pipe +
						                 String.valueOf(bean.getTire_position()) + pipepipe ;
						 
						
						 session.save( bean );
						
					 }
					 
					 
				 }
				 
				 beanTruckTireConfiguration.setConfiguration(configuration);
				 session.update(beanTruckTireConfiguration);
				 tx.commit();

					
				String axisFront="<td onclick='test(this);' style='margin:0px' ><img src='./truckimg/ejedelantero.jpg' width='75' height='60'/></td>";
				
				String axisBottom = "<td><img src='./truckimg/ejetrasero.jpg' width='75' height='63'/></td>";
				

				
				
				String tLeft ="<td width='22' style='background-image:url(./truckimg/tfl.jpg);background-repeat:no-repeat;background-size:22px 60px;'></td>";

				String tRigth ="<td width='22' style='background-image:url(./truckimg/tfr.jpg);background-repeat:no-repeat;background-size:22px 60px;'></td>";
		
				String hmtlChasisBody="";
				
								 
					
					
					
				  String trOpen="<tr>";				
				  String tdOpen="<td width='22'>";
				  String tdClose="</td>";					
				  String trClose="</tr>";
				
				  
				  String htmlTableWidth =String.valueOf((maxCols * 22) + 75) ;
				  String htmlTableStart="<table width='"+ htmlTableWidth +"' border='0' cellspacing='0'  cellpadding='0'>";
				  String htmlTableEnd="</table>";
				  
				  hmtlChasisBody= "<td><img src='./truckimg/chasis.jpg' width='75' height='186' /></td>";
				  
				  String htmlChasisBottom  = "<td><img src='./truckimg/chasisback.jpg' width='75' height='41'  alt=''/></td>";
				  
				  String tdTemp="";
				  String trTemp="";
				  String tdMidleChasisFront =  "<td width='75'><img  src='./truckimg/chasisfront.jpg' width='75' height='37'  alt=''/></td>";
				  boolean chasisFrontAsigned = false;
				  boolean chasisBodyAsigned = false;
					
				  boolean tireLeftAsigned = false;
				  boolean  tireRigthAsigned = false;
				  boolean  axisFrontAsigned = false;
				  boolean  axisBottomAsigned = false;
				  boolean  tireLeftBottomAsigned = false;
				  boolean  tireRigthBottomAsigned = false;
				  
				  String htmlConfiguration=""; 
				  String htmlConfigurationBottom=""; 
				  
				  String query ="";
	              List list;
	              index = 1;
	              for(int i=0; i < Integer.valueOf(truckTireConfigurationNewForm.getAxis_count()); i++ )
				  {
	            	  
					  if(truckTireConfigurationNewForm.getAxis_type(i).equals("Y"))
					  {
						  axisFrontAsigned = false;
						  trTemp =  trOpen; //abre tr de el el frente del chasis
						  
						  if(!chasisFrontAsigned)
						  {					  
							  for(int j=1; j <= (maxCols + 1); j++)
							  {
								 if( ((maxCols / 2) + 1)== j)
								 {
								   tdTemp = tdTemp + tdMidleChasisFront; 
								   chasisFrontAsigned = true;
								 }
								 else
								   tdTemp = tdTemp + tdOpen + tdClose;
							  }
							  trTemp = trTemp + tdTemp + trClose; //cierra tr de el el frente del chasis
						  }
						  
						  
						  
						  
						  query =" FROM BeanTruckTireConfigurationDetail u" +						  		
								 " WHERE u.id_truck_tire_configuration.id = " + beanTruckTireConfiguration.getId()  +
						         " AND u.is_front_axis  = '" + truckTireConfigurationNewForm.getAxis_type(i) +  "'" +
						         " AND u.axis_number = " + index;
						         
						  
			              
						  list = session.createQuery( query ).list(); //listar los lados del eje (index)
			            
			              iter = list.iterator();
			              String axisRow="";
			              String axisTdLeft_axisFront="";
			              
			              String axisTdLeft="";
			              String axisTdRigth="";
			              int tireByAxis= Integer.valueOf( truckTireConfigurationNewForm.getTire_for_axis(i));//llantas por eje
			              while ( iter.hasNext() )//obtener los lados de cada eje (index)
						  {
							  bean= (BeanTruckTireConfigurationDetail) iter.next();
							  
							  int tdEmptyLeftAndRigth =  ((maxCols + 1) - (tireByAxis + 1))/ 2;//cantidad de td vacios a la izquierda
							  int tdTireLeftPosition =  tdEmptyLeftAndRigth + 1;//posicion donde ira la llanta izquierda
							  int tdTireRigthPosition =  1;//posicion donde ira la llanta derecha
							  int tireCount = 1;
							  int tdEmptyLeftCount = 0;
							  int tdEmptyRigthCount = 0;
							  //axisFrontAsigned=false; //no ha sido agregado el eje
							  for(int j=1; j <= (maxCols + 1); j++) //construye los tr con sus td para los ejes
							  {
								  if(!axisFrontAsigned )
								  {									  
								     //si el td es el del medio
									 if( ((maxCols / 2) + 1)== j)//agregar el eje en el td que esta en el medio del tr
								      {							    	
										// axisRow= cadena vacia
										//trTemp  = tr cerrado del frente chasis 
										   axisTdLeft_axisFront =  axisTdLeft + axisFront;
										   axisFrontAsigned = true;
										   break;
								      }
								  }	 
									
								  
								
								  
								  if(bean.getAxis_side().equals("L"))//si es el lado izquierdo
								  {								  
																			   
									  if(j == tdTireLeftPosition)	
									  {	 
										 
										  for(int x=1; x <= (tireByAxis/2); x++)
										  {	
										    axisTdLeft= axisTdLeft + tLeft ;
										  }
										  
										  tireLeftAsigned = true;
									  }
									  else if(tdEmptyLeftCount < tdEmptyLeftAndRigth)
									  {
										  axisTdLeft=   axisTdLeft + tdOpen + tdClose;  
										  tdEmptyLeftCount++;
										  //break;
									  }
									  
									
									
		 
								  }
								  else if(bean.getAxis_side().equals("R"))
								    {							  
									  	
										 								   
									  if(j == tdTireRigthPosition )	
									  { 
										  for(int x=1; x <= (tireByAxis/2); x++)
										  {	
										    axisTdRigth= axisTdRigth +  tRigth ;
										  }
										  
										 // 
									  }
									  else if(tdEmptyRigthCount < tdEmptyLeftAndRigth)
									  {
										  axisTdRigth=   axisTdRigth + tdOpen + tdClose;  
										  tdEmptyRigthCount++;
										  //break;
									  }
												  
								
								  }
						
						   } 
							  
						}

			              if(htmlConfiguration.equals("") )
			              {
			            	  htmlConfiguration = trTemp + trOpen + axisTdLeft_axisFront + axisTdRigth + trClose;
			              }
			              else
			              {
			            	  htmlConfiguration = htmlConfiguration + trOpen + axisTdLeft_axisFront + axisTdRigth + trClose;
			              }
			            	  
			              
			              index++;	  
					  }
					  else
					  {
						 
						 // chasisBodyAsigned=true;
						  tdTemp="";
						  trTemp ="";
						  
						  if(!chasisBodyAsigned)
						  trTemp =  trOpen;
						  
						  if(!chasisBodyAsigned)
						  {
							  for(int j=1; j <= (maxCols + 1); j++)
							  {
								 if( ((maxCols / 2) + 1)== j/* && chasisBodyAsigned == false*/)
								 {
								   tdTemp = tdTemp + hmtlChasisBody; 
								   chasisBodyAsigned = true;
								 }
								 else
								   tdTemp = tdTemp + tdOpen + tdClose;
							  }
							  trTemp = trTemp + tdTemp + trClose;
						  }
						  
						  
						  
						  
						  query =" FROM BeanTruckTireConfigurationDetail u" +						  		
								 " WHERE u.id_truck_tire_configuration.id = " + beanTruckTireConfiguration.getId()  +
						         " AND u.is_front_axis  = '" + truckTireConfigurationNewForm.getAxis_type(i) +  "'" +
						         " AND u.axis_number = " + index ;
		              
					      list = session.createQuery( query ).list();
		            
		                  iter = list.iterator();
		               
			              String axisTdLeft_axisBottom="";
		                  String axisRow="";
		                  int tireByAxis= Integer.valueOf( truckTireConfigurationNewForm.getTire_for_axis(i));//llantas por eje
		                  String axisTdLeft="";
			              String axisTdRigth="";
			              int y = 0;
			              while ( iter.hasNext() )
						  {
							  bean= (BeanTruckTireConfigurationDetail) iter.next();
							  int tdEmptyLeftAndRigth =  ((maxCols + 1) - (tireByAxis + 1))/ 2;//cantidad de td vacios a la izquierda
							  int tdTireLeftPosition =  tdEmptyLeftAndRigth + 1;//posicion donde ira la llanta
							  int tdTireRigthPosition =  1;//( ((maxCols / 2) + 1) + 1 );
							  int tireCount = 0;
							  int tdEmptyLeftCount = 0;
							  int tdEmptyRigthCount = 0;
							  
							  if(!axisBottomAsigned )
							  {				  
							     //si el td es el del medio
								 if( ((maxCols / 2))== y)//agregar el eje en el td que esta en el medio del tr
							      {							    	
									// axisRow= cadena vacia
									//trTemp  = tr cerrado del frente chasis 
									   axisTdLeft_axisBottom =  axisTdLeft + axisBottom;
									   axisBottomAsigned = true;
									  // break;
							      }
										 
								 	
							  }	
							  
							  if(bean.getAxis_side().equals("L"))
							  {
									axisTdLeft= axisTdLeft + tLeft ;
							  } 
							  else if(bean.getAxis_side().equals("R"))
							   {
									    axisTdRigth= axisTdRigth +  tRigth ;

							   }
							  y++;
							  
							  if(maxCols== y)
							  axisBottomAsigned = false;
							  /*for(int j=1; j <= (maxCols + 1); j++)
							  {
													  
								  if(!axisBottomAsigned )
								  {									  
								     //si el td es el del medio
									 if( ((maxCols / 2) + 1)== j)//agregar el eje en el td que esta en el medio del tr
								      {							    	
										// axisRow= cadena vacia
										//trTemp  = tr cerrado del frente chasis 
										   axisTdLeft_axisBottom =  axisTdLeft + axisBottom;
										   axisBottomAsigned = true;
										   break;
								      }
								  }	 
								  
								  if(bean.getAxis_side().equals("L"))
								  {
									  if(j == tdTireLeftPosition)	
									  {	 
										 
										  for(int x=1; x <= (tireByAxis/2); x++)
										  {	
										    axisTdLeft= axisTdLeft + tLeft ;
										  }
										  
										  tireLeftAsigned = true;
									  }
									  else if(tdEmptyLeftCount < tdEmptyLeftAndRigth)
									  {
										   axisTdLeft=   axisTdLeft + tdOpen + tdClose;  
										  tdEmptyLeftCount++;
										  //break;
									  }
									 
	
			
		 
								  }
								  else if(bean.getAxis_side().equals("R") && tireRigthBottomAsigned == false)
								    {
									  if(j == tdTireRigthPosition )	
									  { 
										  for(int x=1; x <= (tireByAxis/2); x++)
										  {	
										    axisTdRigth= axisTdRigth +  tRigth ;
										  }
										  
										 // 
									  }
									  else if(tdEmptyRigthCount < tdEmptyLeftAndRigth)
									  {
										  axisTdRigth=   axisTdRigth + tdOpen + tdClose;  
										  tdEmptyRigthCount++;
										  //break;
									  }
									  
			
								  }
								  
							  }*/
								  
							  
						  }
			              
			    
			              axisBottomAsigned=false;
			              htmlConfiguration = htmlConfiguration + trTemp + trOpen + axisTdLeft_axisBottom + axisTdRigth + trClose;
			
			              index++;
					  }
						  
				  }
				
	              tdTemp ="";
	              for(int j=1; j <= (maxCols + 1); j++)
				  {
					 if( ((maxCols / 2) + 1)== j)
					 {
					   tdTemp = tdTemp + htmlChasisBottom; 
					   
					 }
					 else
					   tdTemp = tdTemp + tdOpen + tdClose;
				  }
				  trTemp = trTemp + tdTemp + trClose;
	              
	              
	              htmlTableStart = htmlTableStart + htmlConfiguration + trOpen +  tdTemp  + trClose + htmlTableEnd;
	              String htmlTable=htmlTableStart;
	              httpSession.setAttribute("htmlTable", htmlTable);
				  forward = mapping.findForward("okChasis");
				  
				  
				  httpSession.setAttribute("axisNumberSet", true);
				  
				  httpSession.setAttribute("done", new String("pre.jsp?&process='truckTireConfigurationNew'&action='pre.do'&target=''"));
				   
				  
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
