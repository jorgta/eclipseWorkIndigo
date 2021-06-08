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

public class TestChangeAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		
		TestChangeForm dataForm = (TestChangeForm) form;

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
			    
				if ( dataForm.getProcess().equals("testSave") )
				  {
					String query =   " FROM BeanTest u" 
							       + " WHERE u.name = '" + dataForm.getTest_name().toUpperCase() +"'";  
							   
					java.util.List list = session.createQuery(query).list();
					
					if ( !list.isEmpty() )
					  {   
						
						BeanTest  bean = (BeanTest)list.get(0);
						
						BeanTest beanTestforChange = (BeanTest) httpSession.getAttribute( "beanTestForChange" );
						httpSession.removeAttribute( "beanTestForChange" );
						if(beanTestforChange.getId() == bean.getId())
						  {
						
							tx = session.beginTransaction();
						
							bean.setName( dataForm.getTest_name() );
							bean.setPrice( new Double( dataForm.getTest_price() ).floatValue() );
							bean.setIndications( dataForm.getTest_indications() );
							bean.setMaterials_to_patient( dataForm.getTest_materials_to_patient() );
							bean.setActive( 1 );
							bean.setMethod( dataForm.getTest_method() );
		
							session.update( bean );
							tx.commit();
							
							//TestParameters
							query = " FROM BeanTest u" +
									" WHERE u.active = 1" +
					  				" ORDER BY u.name";
										
							list = session.createQuery( query ).list();
	
						    httpSession.setAttribute("listTest", list );
						    
						    httpSession.setAttribute( "beanTestForChange",bean );
							
							forward = mapping.findForward("loop");
						//httpSession.setAttribute("done", new String("pre.jsp?&process='patientNew'&action='pre.do'&target=''"));
						  }
						else
						  {
							/*httpSession.setAttribute("done", new String("./pre.jsp?&process='testNew'&action='pre.do'&target=''"));
							forward = mapping.findForward("ok");*/
						  	
							
							com.bituos.Error error = new com.bituos.Error("Existe un analisis con ese nombre: <strong>" + dataForm.getParameter_limit_description() +"</strong> elija otro nombre para el analisis", request);
							httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
							forward = mapping.findForward("error");
						  }
					  }
					else
					  {
						
						
						BeanTest bean = (BeanTest) httpSession.getAttribute( "beanTestForChange" );
						tx = session.beginTransaction();
					
						bean.setName( dataForm.getTest_name() );
						bean.setPrice( new Double( dataForm.getTest_price() ).floatValue() );
						bean.setIndications( dataForm.getTest_indications() );
						bean.setMaterials_to_patient( dataForm.getTest_materials_to_patient() );
						bean.setActive( 1 );
						bean.setMethod( dataForm.getTest_method() );
	
						session.update( bean );
						tx.commit();
						
						//TestParameters
						query = " FROM BeanTest u" +
								" WHERE u.active = 1" +
				  				" ORDER BY u.name";
									
						list = session.createQuery( query ).list();

					    httpSession.setAttribute("listTest", list );
						
						forward = mapping.findForward("loop");
						//httpSession.setAttribute("done", new String("pre.jsp?&process='patientNew'&action='pre.do'&target=''"));
						 
					  }
					
				  }
				else if ( dataForm.getProcess().equals("link") )
				  {
					String query = "";
					BeanTestParameter beanTestParameter = null;
					
					
					
					beanTestParameter =  (BeanTestParameter) session.load( BeanTestParameter.class, new Integer( dataForm.getId_test_parameter() ) );
				    BeanTest beanTest =  (BeanTest) session.load( BeanTest.class, new Integer( dataForm.getId_test() ) );
				    
				    
				    query =  " FROM BeanTestParameter u" +
							 " WHERE u.name='" + beanTestParameter.getName() +"'" +
							 " AND u.id_test.id = " + dataForm.getId_test() +
							 " AND u.active = 1" +
				  			 " ORDER BY u.name";
							
				    List list = session.createQuery( query ).list();
				    
				    BeanTestParameter beanTestParameterUpdate=(BeanTestParameter)list.get(0);
				    
				    tx = session.beginTransaction();
				    
				    
				    if(!list.isEmpty())
				    {	
				    	if(beanTestParameterUpdate.getId() == beanTestParameter.getId())
					    {
					    	//beanTestParameterUpdate.setName(dataForm.getTest_parameter_name());
					    	beanTestParameterUpdate.setMeasurement_unit(dataForm.getTest_parameter_measurement_unit());
					    	
					    	
					    	
					    	query =   " FROM BeanParameterLimits u" 
				    	    		+ " WHERE u.id_test_parameter =" + beanTestParameterUpdate.getId();
			    		
				    		list = session.createQuery( query ).list();
					    	if ( dataForm.getTest_parameter_is_free_text() !=null  )
					          {
					    		
					    		if(list.isEmpty())
							    {
					    			beanTestParameterUpdate.setIs_free_text( 1 );					    								    	
	    							session.save( beanTestParameterUpdate );    							
	    							BeanParameterLimits beanParameterLimits = new BeanParameterLimits();
	    							beanParameterLimits.setId_test_parameter(beanTestParameterUpdate);
	    							beanParameterLimits.setDescription("NA");
	    							beanParameterLimits.setLimit_from_num(-999999);
	    							beanParameterLimits.setLimit_to_num(999999);
	    							beanParameterLimits.setLimit_from_str("NA");
	    							beanParameterLimits.setLimit_to_str("NA");  
	    							
	    							beanParameterLimits.setShow_in_report( 0 );  
	    							beanTestParameterUpdate.setIs_numeric(0);
	    							session.save( beanParameterLimits );
							    }
					    		else
							    {
							    	com.bituos.Error error = new com.bituos.Error("Sí el parametro <strong>" + beanTestParameterUpdate.getName() +"</strong> es de texto libre no se puede agregar mas de un limite, Sí tiene un limite eliminelo", request);
									httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
									forward = mapping.findForward("error");
									return forward;
							    }
					    		
					    		
					          }
						    else
					          {
						    	query =   " FROM BeanParameterLimits u" 
				    	    			+ " WHERE u.id_test_parameter =" + beanTestParameterUpdate.getId()
				    	    			+ " AND u.id_test_parameter.is_free_text = 1";
			    		
						    	list = session.createQuery( query ).list();
				    		
						    	if(!list.isEmpty())
							    {
							        Iterator iter = list.iterator();
							        while(iter.hasNext())
							        {	
							        	BeanParameterLimits beanParameterLimits = (BeanParameterLimits)iter.next();
							        	
							        	session.delete(beanParameterLimits);
							        }
							        
							        beanTestParameterUpdate.setIs_free_text( 0 );
							    	if ( dataForm.getTest_parameter_is_numeric().equals("on") )
							         {					        	
							        	beanTestParameterUpdate.setIs_numeric(1);
							         }
								    else
							         {						    
								    	beanTestParameterUpdate.setIs_numeric(0);
							         }
							    	
							    }
						    	else
						    	{
						    		beanTestParameterUpdate.setIs_free_text( 0 );
							    	if ( dataForm.getTest_parameter_is_numeric().equals("on") )
							         {					        	
							        	beanTestParameterUpdate.setIs_numeric(1);
							         }
								    else
							         {						    
								    	beanTestParameterUpdate.setIs_numeric(0);
							         }
						    		
						    	}
						    		
					          }
					    	
					    	
					    	
					    	//tx.commit();
					    	
					    	//session.update( beanTestParameterUpdate );
					    	
					    }else
					    {
					    	com.bituos.Error error = new com.bituos.Error("Existe un parametro con ese nombre: <strong>" + dataForm.getParameter_limit_description() +"</strong> elija otro nombre para el analisis: " + beanTestParameterUpdate.getName() , request);
							httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
							forward = mapping.findForward("error");
							return forward;
					    }
				    }
				    else
				    {
				    	beanTestParameterUpdate.setName(dataForm.getTest_parameter_name());
				    	beanTestParameterUpdate.setMeasurement_unit(dataForm.getTest_parameter_measurement_unit());
				    	if ( dataForm.getTest_parameter_is_numeric().equals("on") )
				          {					        	
				        	beanTestParameterUpdate.setIs_numeric(1);
				          }
					    else
				          {						    
					    	beanTestParameterUpdate.setIs_numeric(0);
				          }
				    	
				    	
				    	/*if ( dataForm.getTest_parameter_is_free_text().equals("on") )
				          {
				    		beanTestParameterUpdate.setIs_free_text( 1 );
				          }
					    else
				          {
					    	beanTestParameterUpdate.setIs_free_text( 0 );
				          }*/
				    	
				    }
				    
				    
				       
					query =  " FROM BeanTestParameter u" +
							 " WHERE u.id = " + dataForm.getId_test_parameter() +
							 " AND u.id_test.id = " + dataForm.getId_test() +
							 " AND u.active = 1" +
				  			 " ORDER BY u.name";
									
				    list = session.createQuery( query ).list();
				       
					if ( !list.isEmpty() )
					 {

					  beanTestParameterUpdate.setId_test(beanTest);
					  
					  session.update( beanTestParameterUpdate );
					  

					//beanTestParameter.setId_test( beanTest );
			    	//  session.update( beanTestParameter );

					  List<BeanParameterLimits> testNew_parameterLimitsList = (List<BeanParameterLimits>) httpSession.getAttribute( "testNew_parameterLimitsList");
					  java.util.List list2;	   
					  if ( testNew_parameterLimitsList != null  )
					     {
						   Iterator iter = testNew_parameterLimitsList.iterator();
						   Double min = 0.0, max = 0.0;
						   while ( iter.hasNext() )
						     {
							   BeanParameterLimits beanParameterLimits = (BeanParameterLimits) iter.next();
							   
							   beanParameterLimits.setId_test_parameter( beanTestParameter );
							   
							   query =   " FROM BeanParameterLimits u" 
							    	   + " WHERE u.id = " + beanParameterLimits.getId() 
							    	   + " AND u.id_test_parameter =" + dataForm.getId_test_parameter();
							   
							   list2 = session.createQuery(query).list();
							   BeanParameterLimits beanParameterLimitsUpdate;
								if ( !list2.isEmpty() )
								  {   
									beanParameterLimitsUpdate = (BeanParameterLimits)list2.get(0);
									
									
									
									beanParameterLimitsUpdate.setDescription(beanParameterLimits.getDescription());
									
									if(beanTestParameterUpdate.getIs_numeric() == 1)
									  {
										
										try
									      {
									    	min = Double.valueOf( beanParameterLimits.getLimit_from_num() );
									    	beanParameterLimitsUpdate.setLimit_from_num(beanParameterLimits.getLimit_from_num());
									      }
									    catch(Exception e)
									      {
									    	com.bituos.Error error = new com.bituos.Error("El valor minimo debe ser numerico", request);
											httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
											forward = mapping.findForward("error");
											return forward;
									      }
										
									    try
									      {
									    	max = Double.valueOf( beanParameterLimits.getLimit_to_num() );
									    	beanParameterLimitsUpdate.setLimit_to_num(beanParameterLimits.getLimit_to_num());
									      }
									    catch(Exception e)
									      {
									    	com.bituos.Error error = new com.bituos.Error("El valor maximo debe ser numerico", request);
											httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
											forward = mapping.findForward("error");
											return forward;
									      }
									    
										
										
										
									  }
									else
								 	  {									
										beanParameterLimitsUpdate.setLimit_from_str(beanParameterLimits.getLimit_from_str());
										beanParameterLimitsUpdate.setLimit_to_str(beanParameterLimits.getLimit_to_str());
									  }
									
									beanParameterLimitsUpdate.setShow_in_report(beanParameterLimits.getShow_in_report());
									
									session.update( beanParameterLimitsUpdate ); 
								  }else
								  {
									 //beanParameterLimitsNewOrUpdate = new BeanParameterLimits();
									 // beanTestParameter.getIs_numeric()
									  if(beanTestParameterUpdate.getIs_numeric() == 1)
									  {									  
										  try
									      {
									    	min = Double.valueOf( beanParameterLimits.getLimit_from_num() );
									    	min = Double.valueOf( beanParameterLimits.getLimit_from_str() );
									    	//beanParameterLimits.setLimit_from_num(min);
									      }
									    catch(Exception e)
									      {
									    	com.bituos.Error error = new com.bituos.Error("El valor minimo debe ser numerico", request);
											httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
											forward = mapping.findForward("error");
											return forward;
									      }
										
									    try
									      {
									    	max = Double.valueOf( beanParameterLimits.getLimit_to_num() );
									    	max = Double.valueOf( beanParameterLimits.getLimit_to_str() );
									    	//beanParameterLimits.setLimit_to_num(max);
									      }
									    catch(Exception e)
									      {
									    	com.bituos.Error error = new com.bituos.Error("El valor maximo debe ser numerico", request);
											httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
											forward = mapping.findForward("error");
											return forward;
									      }
									  }
									  else
									  {
										  //beanParameterLimits.setLimit_from_str(dataForm.getParameter_limit_from());
										 // beanParameterLimits.setLimit_to_str(dataForm.getParameter_limit_to());
										  
									  }
								    
									 session.save( beanParameterLimits ); 
								  }
							   
					    	   
						     }
					     }
			    	     
			    	     tx.commit();
					  
			    	    // beanParameterLimitList
			    	     httpSession.removeAttribute("beanParameterLimitList");
					     httpSession.setAttribute("done", new String("./pre.jsp?&process='testChange'&action='pre.do'&target=''"));
					     forward = mapping.findForward("ok");
					 }
					else
					{
						com.bituos.Error error = new com.bituos.Error("Error Interno Consulte a soprte tecnico ", request);
						httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
						forward = mapping.findForward("error");
					}
					

				
				}
				else if ( dataForm.getProcess().equals("ParameterLimitAdd") )
				  {
					
					if ( dataForm.getTest_parameter_is_free_text() !=null  )
			          {
						com.bituos.Error error = new com.bituos.Error("Eligio la opción Texto libre por lo que no es necesario agregar limites de referencia", request);
						httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
						return mapping.findForward("error");
			          }
					
					
					List<BeanParameterLimits> testNew_parameterLimitsList = (List<BeanParameterLimits>) httpSession.getAttribute( "testNew_parameterLimitsList");
					BeanTestParameter beanTestParameterCurrent;
					
					beanTestParameterCurrent = (BeanTestParameter) httpSession.getAttribute( "BeanTestParameterCurrent");
					if(beanTestParameterCurrent==null)					
					   beanTestParameterCurrent =  (BeanTestParameter) session.load( BeanTestParameter.class, new Integer( dataForm.getId_test_parameter()) );
						
					
					
					BeanParameterLimits	bean;
					boolean isInTheList = false;

					if(dataForm.getId_parameter_limits().equals("0") )
					{
						if ( testNew_parameterLimitsList == null)
						  {
							testNew_parameterLimitsList = new ArrayList<BeanParameterLimits>();
						  }
						else
						  {							 
							Iterator iter = testNew_parameterLimitsList.iterator();
					    	while ( iter.hasNext() )
							  {   							 
								 bean= (BeanParameterLimits) iter.next();
								 
								 if(dataForm.getParameter_limit_description().equals(   bean.getDescription().toUpperCase() ))
								 {
									 isInTheList = true;
									 break;
								   
								 }
							  }
						  }
				    	
				        if(!isInTheList)
				        {
							
				        	
				        	
				        	List<BeanParameterLimits> beanParameterLimitList = (List<BeanParameterLimits>) httpSession.getAttribute( "beanParameterLimitList");
				        	if ( beanParameterLimitList != null)
							  {
				        		Iterator iter = beanParameterLimitList.iterator();
						    	while ( iter.hasNext() )
								  {   							 
									 bean= (BeanParameterLimits) iter.next();
									 
									 if(dataForm.getParameter_limit_description().equals( bean.getDescription().toUpperCase() ))
									 {
										 isInTheList = true;
										 break;
									   
									 }
								  }
							  }
				        	
				        	 if(!isInTheList)
						     {
					        	BeanParameterLimits beanParameterLimits = new  BeanParameterLimits();
					        	
					        	String query =   " FROM BeanTestParameter u" 
					    	   		           + " WHERE u.id = " + beanTestParameterCurrent.getId();
					   
			        	        List list = session.createQuery(query).list();
			        	        
			        	        BeanTestParameter beanTestParameter= (BeanTestParameter)list.get(0);
		
								beanParameterLimits.setDescription( dataForm.getParameter_limit_description() );
						        if ( dataForm.getTest_parameter_is_numeric().equals("on") )
						          {						        	
						        	beanTestParameter.setIs_numeric(1);
						        	
						        	
						        	beanParameterLimits.setLimit_from_num( new Double( dataForm.getParameter_limit_from() ).doubleValue() );
									beanParameterLimits.setLimit_to_num( new Double( dataForm.getParameter_limit_to() ).doubleValue() );
		
									beanParameterLimits.setLimit_from_str( dataForm.getParameter_limit_from() );
									beanParameterLimits.setLimit_to_str( dataForm.getParameter_limit_to() );
						          }
							    else
						          {
							    	beanTestParameter.setIs_numeric(0);
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
						        
					
					
		
						       
								testNew_parameterLimitsList.add(beanParameterLimits);
								
								httpSession.removeAttribute("beanTestParameterCurrent");
								httpSession.setAttribute("beanTestParameterCurrent",beanTestParameter);
								httpSession.removeAttribute("testNew_parameterLimitsList");
								httpSession.setAttribute("testNew_parameterLimitsList",testNew_parameterLimitsList);
								
								forward = mapping.findForward("loop");
						     }
				           else
					         {
					    	   com.bituos.Error error = new com.bituos.Error("Ya existe <strong>regidtrado</strong> un parametro con el nombre " + dataForm.getParameter_limit_description(), request);
							   httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
							   forward = mapping.findForward("error");
					         }
				        }
				        else
				         {
				    	   com.bituos.Error error = new com.bituos.Error("Ya existe un parametro con el nombre <strong>" + dataForm.getParameter_limit_description() +"</strong> elija otro nombre para el parametro", request);
						   httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
						   forward = mapping.findForward("error");
				         }
						
						
					} 
				  else
					{
						
						BeanParameterLimits beanParameterLimits =  (BeanParameterLimits) session.load( BeanParameterLimits.class, new Integer( dataForm.getId_parameter_limits() ) );
						
						if ( testNew_parameterLimitsList == null)
						  {
							testNew_parameterLimitsList = new ArrayList<BeanParameterLimits>();
						  }
						
						
						/*BeanParameterLimits	bean;
						boolean isInTheList = false;*/
						Iterator iter = testNew_parameterLimitsList.iterator();
				    	while ( iter.hasNext() )
						  {   							 
							 bean= (BeanParameterLimits) iter.next();
							 
							 if(beanParameterLimits.getId() == bean.getId() )
							 {
								 isInTheList = true;
								 break;
							   
							 }
						  }
				    	
				        if(!isInTheList)
				        {   
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
	
					        
						    
							
							testNew_parameterLimitsList.add(beanParameterLimits);
							
							
							httpSession.removeAttribute("testNew_parameterLimitsList");
							httpSession.setAttribute("testNew_parameterLimitsList",testNew_parameterLimitsList);
							
							forward = mapping.findForward("loop");
				        }
				      else
				        {
				    	   com.bituos.Error error = new com.bituos.Error("Ya existe un parametro con el nombre " + dataForm.getParameter_limit_description(), request);
						   httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
						   forward = mapping.findForward("error");
				        }
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
						query =   " FROM BeanTestParameter u" 
					    		 + " WHERE u.id = " + dataForm.getId_test_parameter()
					    		 + " AND u.active = 1"; 
					   
						list = session.createQuery(query).list();
						//httpSession.setAttribute("beanParameterLimitList",list);
						BeanTestParameter beanTestParameterCurrent= (BeanTestParameter)list.get(0);
						httpSession.setAttribute("beanTestParameterCurrent",beanTestParameterCurrent);	
						
					}
					
					forward = mapping.findForward("loop");

					  
				  }
				else if ( dataForm.getProcess().equals("selectParameterLimit") )
				 { 
 
					String query =   " FROM BeanParameterLimits u" 
		    			   		   + " WHERE u.id = " + dataForm.getId_parameter_limits();  
		   
					java.util.List list = session.createQuery(query).list();
					
					if ( !list.isEmpty() )
					  {   						
						
						BeanParameterLimits beanParameterLimitsCurrent = (BeanParameterLimits)list.get(0);
						httpSession.setAttribute("beanParameterLimitsCurrent",beanParameterLimitsCurrent);
						forward = mapping.findForward("loop");
					  }
	
				 }
				else if ( dataForm.getProcess().equals("parameterLimitDelete") )
				 { 

					String query =   " FROM BeanParameterLimits u" 
		    			   		   + " WHERE u.id = " + dataForm.getId_parameter_limits();  
		   
					java.util.List list = session.createQuery(query).list();
					BeanParameterLimits beanParameterLimits = (BeanParameterLimits)list.get(0);
					
					tx = session.beginTransaction();
		    	    session.delete( beanParameterLimits );
				    tx.commit();
				    
				    
				    query =   " FROM BeanParameterLimits u" 
 			   		   		+ " WHERE u.id_test_parameter = " + dataForm.getId_test_parameter();  
				    
				    list = session.createQuery(query).list();
				    
				    
				    httpSession.removeAttribute("beanParameterLimitList");
			    	httpSession.setAttribute( "beanParameterLimitList", list);
					
					forward = mapping.findForward("loop");
					  
	
				 }			
				else if ( dataForm.getProcess().equals("ParameterLimitCancel") )
				 { 
				   httpSession.removeAttribute("beanParameterLimitsCurrent");
				   forward = mapping.findForward("loop");
					  
	
				 }
				else if ( dataForm.getProcess().equals("testParameterDelete") )
				 { 
				  
				   
				   
					BeanTestParameter beanTestParameterCurrent = (BeanTestParameter) httpSession.getAttribute( "beanTestParameterCurrent" );
					 if(beanTestParameterCurrent != null )
					 {
					 
					
						httpSession.removeAttribute("beanTestParameterCurrent");
						BeanTestParameter beanTestParameter;					
						List<BeanTestParameter> listTestParameter = (List<BeanTestParameter>) httpSession.getAttribute( "listTestParameter");
			        	
						
				    	
				    	tx = session.beginTransaction();
				    	
						Iterator iter = listTestParameter.iterator();
				    	while ( iter.hasNext() )
						  {   							 
				    		beanTestParameter= (BeanTestParameter) iter.next();
							 
							 if(beanTestParameter.getId() == beanTestParameterCurrent.getId() )
							 {
								 listTestParameter.remove(beanTestParameter);
								 beanTestParameter.setActive(0);
								 session.update(beanTestParameter);
								 
								 break;
							   
							 }
						  }
				    	
						 tx.commit();
						
				    	 httpSession.removeAttribute("listTestParameter");
				    	 httpSession.setAttribute( "listTestParameter", listTestParameter);
				    	 forward = mapping.findForward("loop");
					 }
					else
					 {
						 com.bituos.Error error = new com.bituos.Error("Seleccione un analisis para eliminarlo", request);
						 httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
						 forward = mapping.findForward("error");
					 }
						 
					
				    
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
