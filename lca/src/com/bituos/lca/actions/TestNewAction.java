package com.bituos.lca.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bituos.lca.Beans.*;
import com.bituos.lca.forms.*;

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
import com.bituos.lca.*;
import com.bituos.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

public class TestNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		
		TestNewForm dataForm = (TestNewForm) form;

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

			try 
			  {
				sessionFactory = config.getConfiguration(request).buildSessionFactory();
				session = sessionFactory.openSession();
			    
				if ( dataForm.getProcess().equals("testNew") )
				  {
					String query =   " FROM BeanTest u" 
							       + " WHERE u.name = '" + dataForm.getTest_name().toUpperCase() +"'";  
							   
					java.util.List list = session.createQuery(query).list();
					
					if ( list.isEmpty() )
					  {   
						BeanTest bean = new BeanTest();
					  	
						tx = session.beginTransaction();
						
						bean.setName( dataForm.getTest_name() );
						bean.setPrice( new Double( dataForm.getTest_price() ).floatValue() );
						bean.setIndications( dataForm.getTest_indications() );
						bean.setMaterials_to_patient( dataForm.getTest_materials_to_patient() );
						bean.setActive( 1 );
						bean.setMethod( dataForm.getTest_method() );
	
						session.save( bean );
						tx.commit();
						
						
						
						
						//Update test List
						query =  " FROM BeanTest u" +
								//" WHERE u.active = 'Y'" +
				  				 " ORDER BY u.name";
									
						list = session.createQuery( query ).list();

					    httpSession.setAttribute("listTest", list );
						
						//forward = mapping.findForward("loop");
					    
						httpSession.setAttribute("done", new String("./pre.jsp?&process='testNew'&action='pre.do'&target=''"));
						forward = mapping.findForward("ok");
					  }
					else
					  {
					  	httpSession.setAttribute("done", "pre.jsp?&process='testNew'&action='pre.do'&target=''" );
					  	//httpSession.setAttribute("done", "javascript:history.back();" );
					  	
						com.bituos.Error error = new com.bituos.Error("Existe un analisis con ese nombre.", request);
						forward = mapping.findForward("error");
					  }
					
				  }
				else if ( dataForm.getProcess().equals("testReplicate") )
				  {
					String query =  " FROM BeanTest u" 
						          + " WHERE u.name = '" + dataForm.getTest_name_to_replicate().toUpperCase() +"'";  
						   
					java.util.List list = session.createQuery(query).list();
					
					if ( list.isEmpty() )
					  {   
					    BeanTest beanTest =  (BeanTest) session.load( BeanTest.class, new Integer( dataForm.getId_test_to_replicate() ) );			    

					    BeanTest bean = new BeanTest();
					  	
						tx = session.beginTransaction();
						
						bean.setName( dataForm.getTest_name_to_replicate() );
						bean.setPrice( beanTest.getPrice() );
						bean.setIndications( beanTest.getIndications() );
						bean.setMaterials_to_patient( beanTest.getMaterials_to_patient() );
						bean.setActive( 1 );
						bean.setMethod( beanTest.getMethod() );
	
						session.save( bean );
						
						//Create new Parameters (copy)
						query =  " FROM BeanTestParameter u" +
								 " WHERE id_test = " + beanTest.getId() +
								 " AND u.active = 1";
						
						list = session.createQuery( query ).list();

						Iterator iter = list.iterator();
						while(iter.hasNext())
						{
							BeanTestParameter beanParameter = (BeanTestParameter)iter.next();
							BeanTestParameter beanNewParameter = new BeanTestParameter();
							
							beanNewParameter.setName( beanParameter.getName()  );
							beanNewParameter.setId_test( bean );
							beanNewParameter.setActive( beanParameter.getActive()  );
							beanNewParameter.setMeasurement_unit( beanParameter.getMeasurement_unit()  );
							beanNewParameter.setIs_numeric( beanParameter.getIs_numeric()  );
							beanNewParameter.setIs_free_text( beanParameter.getIs_free_text()  );
							
							session.save( beanNewParameter );
							
							//Create new Parameter Limits(copy)
							query =  " FROM BeanParameterLimits u" +
									 " WHERE u.id_test_parameter.id = " + beanParameter.getId();
					
							list = session.createQuery( query ).list();

							Iterator iterPL = list.iterator();
							while(iterPL.hasNext())
							  {
								BeanParameterLimits beanParameterLimits = (BeanParameterLimits)iterPL.next();
								BeanParameterLimits beanNewParameterLimits = new BeanParameterLimits();
								
								beanNewParameterLimits.setDescription( beanParameterLimits.getDescription()  );
								beanNewParameterLimits.setId_test_parameter( beanNewParameter );
								beanNewParameterLimits.setLimit_from_num( beanParameterLimits.getLimit_from_num() );
								beanNewParameterLimits.setLimit_to_num( beanParameterLimits.getLimit_to_num() );
								beanNewParameterLimits.setLimit_from_str( beanParameterLimits.getLimit_from_str() );
								beanNewParameterLimits.setLimit_to_str( beanParameterLimits.getLimit_to_str() );
								beanNewParameterLimits.setShow_in_report( beanParameterLimits.getShow_in_report() );
								
								session.save( beanNewParameterLimits );
							  }
							
						}

						
						tx.commit();
						
						//Update test List
						query =  " FROM BeanTest u" +
								//" WHERE u.active = 'Y'" +
				  				 " ORDER BY u.name";
									
						list = session.createQuery( query ).list();
	
					    httpSession.setAttribute("listTest", list );
						
						//forward = mapping.findForward("loop");
					    
						httpSession.setAttribute("done", new String("./pre.jsp?&process='testNew'&action='pre.do'&target=''"));
						forward = mapping.findForward("ok");
					  }
					else
					  {
					  	httpSession.setAttribute("done", "pre.jsp?&process='testNew'&action='pre.do'&target=''" );
					  	//httpSession.setAttribute("done", "javascript:history.back();" );
					  	
						com.bituos.Error error = new com.bituos.Error("Existe un analisis con ese nombre.", request);
						forward = mapping.findForward("error");
					  }
					
				  }
				else if ( dataForm.getProcess().equals("selectTest") )
				  {					
					//httpSession.removeAttribute("orderNew_testList");	
					try
					  {
						sessionFactory = config.getConfiguration( request ).buildSessionFactory();
						session = sessionFactory.openSession();

						//Test
						//Next: pq se crea de nuevo el lista ?
						/*
						String query =  " FROM BeanTest u" +
										" WHERE u.active = 1" +
						  				" ORDER BY u.name";
									
						List list = session.createQuery( query ).list();

					    httpSession.setAttribute("listTest", list );
					    */

					    
					    BeanTest beanTest =  (BeanTest) session.load( BeanTest.class, new Integer( dataForm.getId_test() ) );			    
					    httpSession.setAttribute("beanTestCurrent", beanTest );
					    
						//TestParameters				
						String query =  " FROM BeanTestParameter u" +
										" WHERE u.id_test.id = " + dataForm.getId_test() +
										" AND u.active = 1 " +
						 				" ORDER BY u.id";
					

						List list = session.createQuery( query ).list();
						
						if(!list.isEmpty())
						{
							
							BeanTestParameter beanTestParameterCurrent= (BeanTestParameter)list.get(0);
							httpSession.setAttribute("beanTestParameterCurrent",beanTestParameterCurrent);
					
						    httpSession.setAttribute("listTestParameter", list );
						    
						   // httpSession.removeAttribute( "testNew_parameterLimitsList");
						    httpSession.removeAttribute("beanParameterLimitList");
						    
						    query =   " FROM BeanParameterLimits u" 
			    			        + " WHERE u.id_test_parameter = " + beanTestParameterCurrent.getId();  
			   
						    list = session.createQuery(query).list();
						
							if ( !list.isEmpty() )
							  {   
								httpSession.removeAttribute("beanParameterLimitList");
								httpSession.setAttribute("beanParameterLimitList",list);	
							  }					    
					    
						}
						else
						{
							httpSession.removeAttribute( "listTestParameter");
							httpSession.removeAttribute("beanParameterLimitList");
							
							list = new ArrayList<BeanTestParameter>();
							httpSession.setAttribute("listTestParameter",list);
							/*list = new ArrayList<BeanParameterLimits>();
							httpSession.setAttribute("beanParameterLimitList",list);*/
						}
						
					    forward = mapping.findForward("loop");
					  }
					catch( Throwable  m)
					  {
						m.printStackTrace();
						
						com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
						
						forward = mapping.findForward("error");
					  }
					finally
					  {
						session.close();
						sessionFactory.close();
					  }	
					
				  }		
				else if ( dataForm.getProcess().equals("selectParameter") )
				 { 
					httpSession.removeAttribute("testNew_parameterLimitsList");
					
					String query =   " FROM BeanParameterLimits u" 
				    			   + " WHERE u.id_test_parameter = " + dataForm.getId_test_parameter();  
				   
					java.util.List list = session.createQuery(query).list();
					
					if ( !list.isEmpty() )
					  {   
						httpSession.removeAttribute("beanParameterLimitList");
						httpSession.setAttribute("beanParameterLimitList",list);
						
						BeanParameterLimits beanParameterLimits = (BeanParameterLimits)list.get(0);
						BeanTestParameter beanTestParameterCurrent= (BeanTestParameter)beanParameterLimits.getId_test_parameter();							
						httpSession.setAttribute("beanTestParameterCurrent",beanTestParameterCurrent);					

						
					  }
					else
					{						
						httpSession.removeAttribute("beanParameterLimitList");
						BeanTestParameter beanTestParameterCurrent=  (BeanTestParameter) session.load( BeanTestParameter.class, new Integer( dataForm.getId_test_parameter() ) );
					     						
						
						httpSession.setAttribute("beanTestParameterCurrent",beanTestParameterCurrent);	
						
					}
					
					forward = mapping.findForward("loop");

					  
				  }
				else if ( dataForm.getProcess().equals("selectParameterLimit") )
				 { 
					
					
					List<BeanParameterLimits> testNew_parameterLimitsList =(ArrayList<BeanParameterLimits>) httpSession.getAttribute("testNew_parameterLimitsList");
					
									
					
					String query ="";
					
					query =   " FROM BeanParameterLimits u" 
		    				+ " WHERE u.id = " + dataForm.getId_parameter_limit();  
		   
					List list = session.createQuery(query).list();						
					
					if(list.isEmpty())
					{
					  com.bituos.Error error = new com.bituos.Error("Error interno consulte a soporte tecnico", request);
					  httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
					  return mapping.findForward("error");
					}
				
					BeanParameterLimits beanParameterLimitsNew = (BeanParameterLimits)list.get(0);
				
					boolean isInTheList = false; 
					if(testNew_parameterLimitsList != null)
					{
						Iterator iter = testNew_parameterLimitsList.iterator();
						while(iter.hasNext())
						{
							BeanParameterLimits beanParameterLimits = (BeanParameterLimits)iter.next();
							
							
							if(beanParameterLimits.getId() == beanParameterLimitsNew.getId() )
							{
								isInTheList = true; 
								break;
							}
						}
						
						if(!isInTheList)
						{
						  httpSession.removeAttribute("testNew_parameterLimitsList");
						  testNew_parameterLimitsList.add(beanParameterLimitsNew);
						  httpSession.setAttribute("testNew_parameterLimitsList",testNew_parameterLimitsList);
						  forward = mapping.findForward("loop");
						}
						else
						{
						  com.bituos.Error error = new com.bituos.Error("Ya existe un limite de referencia con el nombre <strong>"+beanParameterLimitsNew.getDescription()+"</strong>", request);
						  httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
						  forward = mapping.findForward("error");
						}
						
					}
					else
					{
						testNew_parameterLimitsList = new ArrayList<BeanParameterLimits>(); 
						testNew_parameterLimitsList.add(beanParameterLimitsNew );
						httpSession.setAttribute("testNew_parameterLimitsList",testNew_parameterLimitsList);
						forward = mapping.findForward("loop");
					}

					  
				  }
				else if ( dataForm.getProcess().equals("addAllParameters") )
				 { 
					BeanTestParameter beanTestParameterCurrent = (BeanTestParameter) httpSession.getAttribute( "beanTestParameterCurrent" );
					
					httpSession.removeAttribute("testNew_parameterLimitsList");
					
					String query =   " FROM BeanParameterLimits u" 
				    			   + " WHERE u.id_test_parameter = " + beanTestParameterCurrent.getId();  
				   
					java.util.List list = session.createQuery(query).list();
					
					  
					
					/*List<BeanParameterLimits> list =(ArrayList<BeanParameterLimits>) httpSession.getAttribute("testNew_parameterLimitsList");
					String query ="";
					boolean isInTheList = false; 
					if(!list.isEmpty())
					{
						Iterator iter = list.iterator();
						while(iter.hasNext())
						{
							BeanParameterLimits beanParameterLimits = (BeanParameterLimits)iter.next();
							query =   " FROM BeanParameterLimits u" 
					    			+ " WHERE u.id = " + dataForm.getId_test_parameter();  
					   
							list = session.createQuery(query).list();
							Iterator iter2 = list.iterator();
							while(iter2.hasNext())
							{
								BeanParameterLimits beanParameterLimitsNew = (BeanParameterLimits)iter2.next();
								if(beanParameterLimits.getId() == beanParameterLimitsNew.getId() )
								{
									isInTheList = true; 
									break;
								}
							}
							
						}	
						
					}*/
						//httpSession.removeAttribute("beanParameterLimitList");
						//httpSession.setAttribute("beanParameterLimitList",list);
								
		
					
					forward = mapping.findForward("loop");

					  
				  }
				else if ( dataForm.getProcess().equals("link") )
				  {
					String query = "";
					BeanTestParameter beanTestParameter = null;
					
			       if ( dataForm.getTest_parameter_name() == null )
				     dataForm.setTest_parameter_name( "" );
			       
			       if ( dataForm.getTest_parameter_name().equals( "" ) )
			         {
			    	   //asociacion de parametro seleccionado con analisis seleccionado
				       beanTestParameter =  (BeanTestParameter) session.load( BeanTestParameter.class, new Integer( dataForm.getId_test_parameter() ) );
				       BeanTest beanTest =  (BeanTest) session.load( BeanTest.class, new Integer( dataForm.getId_test() ) );
				       
					   query =  " FROM BeanTestParameter u" +
								" WHERE u.id = " + dataForm.getId_test_parameter() +
								" AND u.id_test.id = " + dataForm.getId_test() +
				  				" ORDER BY u.name";
									
					   List list = session.createQuery( query ).list();
				       
					   if ( list.isEmpty() )
					     {
						   //new 
					       BeanTestParameter beanTestParameter1 = new BeanTestParameter();
					       
					       beanTestParameter1.setName( beanTestParameter.getName() );
					       beanTestParameter1.setId_test( beanTest );
					       beanTestParameter1.setActive( 1 );
					       beanTestParameter1.setMeasurement_unit( dataForm.getTest_parameter_measurement_unit() );
					        
					        if ( dataForm.getTest_parameter_is_numeric().equals("on") )
					          {
					        	beanTestParameter1.setIs_numeric( 1 );
					          }
						    else
					          {
						    	beanTestParameter1.setIs_numeric( 0 );
					          }
					        
					        if ( dataForm.getTest_parameter_is_free_text().equals("on") )
					          {
					        	beanTestParameter1.setIs_free_text( 1 );
					          }
						    else
					          {
						    	beanTestParameter1.setIs_free_text( 0 );
					          }

					        tx = session.beginTransaction();
				    	     session.save( beanTestParameter1 );

							 List<BeanParameterLimits> testNew_parameterLimitsList = (List<BeanParameterLimits>) httpSession.getAttribute( "testNew_parameterLimitsList");
							   
							 if ( testNew_parameterLimitsList != null )
							     {
								   Iterator iter = testNew_parameterLimitsList.iterator();
								   
								   while ( iter.hasNext() )
								     {
									   BeanParameterLimits beanParameterLimits = (BeanParameterLimits) iter.next();
									   
								
									   
									   beanParameterLimits.setId_test_parameter( beanTestParameter1 );										   
							    	   session.save( beanParameterLimits );
									  
									   
								     }
							     }
				    	     
				    	     tx.commit();
					     }
					   else
					     {
						   //update
					       beanTestParameter.setId_test( beanTest );
					       
					       if ( dataForm.getTest_parameter_is_free_text().equals("on") )
					          {
					    	    beanTestParameter.setIs_free_text( 1 );
					          }
						    else
					          {
						    	beanTestParameter.setIs_free_text( 0 );
					          }

				    	   tx = session.beginTransaction();
				    	   session.update( beanTestParameter );

						   List<BeanParameterLimits> testNew_parameterLimitsList = (List<BeanParameterLimits>) httpSession.getAttribute( "testNew_parameterLimitsList");
						   boolean isRegistred= false ;  
							 if ( testNew_parameterLimitsList != null )
							     {
								   Iterator iter = testNew_parameterLimitsList.iterator();
								   
								   while ( iter.hasNext() )
								     {
									   BeanParameterLimits beanParameterLimits = (BeanParameterLimits) iter.next();
									   
									   if(beanParameterLimits.getId_test_parameter() != null)
									   {
										   query =  " FROM BeanParameterLimits u" +
											        " WHERE u.id = " + beanParameterLimits.getId() +
											        " AND u.id_test_parameter = " + beanParameterLimits.getId_test_parameter().getId();
									
								   
										   list = session.createQuery( query ).list();
										   if( list.isEmpty())
										   {
											 beanParameterLimits.setId_test_parameter( beanTestParameter );									   
										     session.save( beanParameterLimits ); 
										   }
									   }
									   else
									   {
										   beanParameterLimits.setId_test_parameter( beanTestParameter );									   
										   session.save( beanParameterLimits );
									   }
										   
										    
									   
									   
								     }
							     }
				    	     
							 tx.commit();	
						
								
				    	      
					     }
					   
					   httpSession.setAttribute("done", new String("./pre.jsp?&process='testNew'&action='pre.do'&target=''"));
					   forward = mapping.findForward("ok");
			         }
			       else
			         {
			    	    // se especifico un nuevo nombre de parametro, el cual hay q darlo de alta
			    	   
						query =   " FROM BeanTestParameter u" 
						        + " WHERE u.name = '" + dataForm.getTest_parameter_name().toUpperCase() + "'";  
						   
						java.util.List list = session.createQuery(query).list();
						
						if ( list.isEmpty() )
						  {
							//nuevo nombre de parametro
							BeanTestParameter bean = new BeanTestParameter();
							BeanTest beanTest =  (BeanTest) session.load( BeanTest.class, new Integer( dataForm.getId_test() ) );
							boolean minMaxerror = false;
							Double min = 0.0;
							Double max = 0.0;

	                        if ( minMaxerror )
	                          {
	    						com.bituos.Error error = new com.bituos.Error("Verifique los valores mininmo y maximo.", request);
	    						forward = mapping.findForward("error");
	                          }
	                        else
	                          {
	    						bean.setName( dataForm.getTest_parameter_name() );
	    						bean.setId_test( beanTest );
	    						bean.setActive( 1 );
	    						tx = session.beginTransaction();
	    						if ( !dataForm.getTest_parameter_is_free_text().equals("on") )
						          {
						        	bean.setIs_free_text( 0 );
						        
	    						
		    						bean.setMeasurement_unit( dataForm.getTest_parameter_measurement_unit() );
							        
							        if ( dataForm.getTest_parameter_is_numeric().equals("on") )
							          {
							        	bean.setIs_numeric( 1 );
							          }
								    else
							          {
								    	bean.setIs_numeric( 0 );
							          }
		    	
							        
		    	
		    						
		    							session.save( bean );
		    							
			   							 List<BeanParameterLimits> testNew_parameterLimitsList = (List<BeanParameterLimits>) httpSession.getAttribute( "testNew_parameterLimitsList");
										   
										 if ( testNew_parameterLimitsList != null )
										     {
											   Iterator iter = testNew_parameterLimitsList.iterator();
											   
											   while ( iter.hasNext() )
											     {
												   BeanParameterLimits beanParameterLimits = (BeanParameterLimits) iter.next();
												   
												   /*if ( dataForm.getParameter_limit_show_in_report().equals("on") )
					    					       {
					    							 beanParameterLimits.setShow_in_report( 1 );
					    					       }
					    						    else
				    					           {
				    								beanParameterLimits.setShow_in_report( 0 );
				    					           }*/
												   
												   
												   if(beanParameterLimits.getId_test_parameter() == null )
												   {
													   beanParameterLimits.setId_test_parameter( bean );									   
													   session.save( beanParameterLimits ); 
													   
												   }
												   else
												   {
													   query =  " FROM BeanParameterLimits u" +
														        " WHERE u.id = " + beanParameterLimits.getId() +
														        " AND u.id_test_parameter = " + beanParameterLimits.getId_test_parameter().getId();
											
													   list = session.createQuery( query ).list();
													   if( !list.isEmpty())
													   {
														 beanParameterLimits.setId_test_parameter( bean );									   
													     session.save( beanParameterLimits ); 
													   }
									   
												   }
												   
												  // beanParameterLimits.setId_test_parameter( bean );
												   
										    	   //session.save( beanParameterLimits );
												   
												   
											     }
											   tx.commit();
										     }
										 else
										 {
											 com.bituos.Error error = new com.bituos.Error("No agrego ningun limite de referencia para el parametro <strong>"+dataForm.getTest_parameter_name()+"</strong>", request);
											  httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
											  return mapping.findForward("error");
										 }
											 
	
									 
		    						
							          }
								    else
							          {
								    	bean.setIs_free_text( 1 );
								    	bean.setMeasurement_unit( dataForm.getTest_parameter_measurement_unit() );
								    	tx = session.beginTransaction();
		    							session.save( bean );
		    							
		    							BeanParameterLimits beanParameterLimits = new BeanParameterLimits();
		    							beanParameterLimits.setId_test_parameter(bean);
		    							beanParameterLimits.setDescription("NA");
		    							beanParameterLimits.setLimit_from_num(-999999);
		    							beanParameterLimits.setLimit_to_num(999999);
		    							beanParameterLimits.setLimit_from_str("NA");
		    							beanParameterLimits.setLimit_to_str("NA");
		    							
		    							
		    							beanParameterLimits.setShow_in_report( 0 );
		    					         
		    								
		    							session.save( beanParameterLimits );
		    								
		    							tx.commit();
										
			                        	
		                             }
				    	   
				    	   
				    	  
						        
						   
						   
						        httpSession.setAttribute("beanTestCurrent", beanTest );
   						      //Test
		   					  	query =  " FROM BeanTest u" +
		   								//" WHERE u.active = 'Y'" +
		   				  				 " ORDER BY u.name";
		   									
		   						list = session.createQuery( query ).list();
		   	
		   					    httpSession.setAttribute("listTest", list );
		   						
		   						//TestParameters
		   						query =  " FROM BeanTestParameter u" +
		   								 " WHERE u.id_test = " + beanTest.getId() +	
		   								 " AND u.active = 1 " +
		   				  				 " ORDER BY u.name";
		   							
		   						list = session.createQuery( query ).list();
		   				
		   					    httpSession.setAttribute("listTestParameter", list );
		   					    
		   					    
		   					    
		   					    httpSession.setAttribute("beanTestParameterCurrent", bean );
		   					    
		   					    httpSession.removeAttribute("testNew_parameterLimitsList");
		   						
		   						query =   " FROM BeanParameterLimits u" 
		   					    		+ " WHERE u.id_test_parameter = " + bean.getId();  
		   					   
		   						list = session.createQuery(query).list();
		   						
		   						if ( !list.isEmpty() )
		   						  {   
		   							httpSession.removeAttribute("beanParameterLimitList");
		   							httpSession.setAttribute("beanParameterLimitList",list);
		   							
		   							BeanParameterLimits beanParameterLimits = (BeanParameterLimits)list.get(0);
		   							BeanTestParameter beanTestParameterCurrent= (BeanTestParameter)beanParameterLimits.getId_test_parameter();							
		   							httpSession.setAttribute("beanTestParameterCurrent",beanTestParameterCurrent);					
		
		   							
		   						  }
		
		   					    forward = mapping.findForward("loop");
		   						//httpSession.setAttribute("done", new String("pre.jsp?&process='patientNew'&action='pre.do'&target=''"));

						   
	                          }
					  }
					else
					  {
					  	httpSession.setAttribute("done", "pre.jsp?&process='testNew'&action='pre.do'&target=''" );
					  	//httpSession.setAttribute("done", "javascript:history.back();" );
					  	
						com.bituos.Error error = new com.bituos.Error("Ya existe un parametro de analisis con ese nombre.", request);
						forward = mapping.findForward("error");
					  }
					
				  }
				
				}
				else if ( dataForm.getProcess().equals("ParameterLimitAdd") )
				  {
					List<BeanParameterLimits> testNew_parameterLimitsList = (List<BeanParameterLimits>) httpSession.getAttribute( "testNew_parameterLimitsList");
					BeanParameterLimits beanParameterLimits = new  BeanParameterLimits();
					
					beanParameterLimits.setDescription( dataForm.getParameter_limit_description() );
			        if ( dataForm.getTest_parameter_is_numeric().equals("on") )
			          {
						beanParameterLimits.setLimit_from_num( new Double( dataForm.getParameter_limit_from() ).doubleValue() );
						beanParameterLimits.setLimit_to_num( new Double( dataForm.getParameter_limit_to() ).doubleValue() );

						beanParameterLimits.setLimit_from_str( dataForm.getParameter_limit_from() );
						beanParameterLimits.setLimit_to_str( dataForm.getParameter_limit_to() );
			          }
				    else
			          {
						beanParameterLimits.setLimit_from_str( dataForm.getParameter_limit_from() );
						beanParameterLimits.setLimit_to_str( dataForm.getParameter_limit_to() );
			          }

			        if ( dataForm.getParameter_limit_show_in_report().equals("on") )
			          {
						beanParameterLimits.setShow_in_report( 1 );
			          }
				    else
			          {
						beanParameterLimits.setShow_in_report( 0 );
			          }

			        
				    BeanTest bean;
				    boolean isInTheList = false;
				    
					if ( testNew_parameterLimitsList == null)
					  {
						testNew_parameterLimitsList = new ArrayList<BeanParameterLimits>();
					  }
					testNew_parameterLimitsList.add(beanParameterLimits);
					
					
					httpSession.removeAttribute("testNew_parameterLimitsList");
					httpSession.setAttribute("testNew_parameterLimitsList",testNew_parameterLimitsList);
					
					forward = mapping.findForward("loop");
				  }
			  } 
			catch (Exception e) 
			  {

                e.printStackTrace();
                
				// Report the error using the appropriate name and ID.
                
                String err_desc = e.getMessage(); 
                		
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
