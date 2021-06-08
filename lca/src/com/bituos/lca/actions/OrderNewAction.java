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
import com.bituos.lca.*;
import com.bituos.*;

//Hibernate
import net.sf.cglib.transform.impl.AddDelegateTransformer;
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

public class OrderNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		
		OrderNewForm dataForm = (OrderNewForm) form;

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
			    
				if ( dataForm.getProcess().equals("testAdd") )
				  {
					List<BeanTest> orderNew_testList = (List<BeanTest>) httpSession.getAttribute( "orderNew_testList");
				    BeanTest beanTest =  (BeanTest) session.load( BeanTest.class, new Integer( dataForm.getId_test() ) );
				    BeanTest bean;
				    boolean isInTheList = false;
				    
					if ( orderNew_testList != null)
					{
						Iterator iter = orderNew_testList.iterator();
				    	while ( iter.hasNext() )
						  {   							 
							 bean= (BeanTest) iter.next();
							 if(beanTest.getId() == bean.getId() )
							 {
								 isInTheList = true;
								 break;
							   
							 }
						  }
				    	
				        if(!isInTheList)
						  orderNew_testList.add( beanTest );
					}
					else
					{
						orderNew_testList = new ArrayList<BeanTest>();
						orderNew_testList.add(beanTest);
					}
					
					httpSession.removeAttribute("orderNew_testList");
					httpSession.setAttribute("orderNew_testList",orderNew_testList);
					
					//save current Patient
					BeanPatient orderNew_currentPatient =  (BeanPatient) session.load( BeanPatient.class, new Integer( dataForm.getId_patient() ) );
					httpSession.setAttribute("orderNew_currentPatient", orderNew_currentPatient );
					
					//save current Doctor
					BeanDoctor orderNew_currentDoctor =  (BeanDoctor) session.load( BeanDoctor.class, new Integer( dataForm.getId_doctor() ) );
					httpSession.setAttribute("orderNew_currentDoctor", orderNew_currentDoctor );
					
					//save current Branch
					BeanBranch orderNew_currentBranch =  (BeanBranch) session.load( BeanBranch.class, new Integer( dataForm.getId_branch() ) );
					httpSession.setAttribute("orderNew_currentBranch", orderNew_currentBranch );

					//save current Company
					BeanCompany orderNew_currentCompany =  (BeanCompany) session.load( BeanCompany.class, new Integer( dataForm.getId_company() ) );
					httpSession.setAttribute("orderNew_currentCompany", orderNew_currentCompany );
										
					
					forward = mapping.findForward("loop");
				  }
				else if ( dataForm.getProcess().equals("testDelete") )
				  {
					List<BeanTestPkg> orderNew_beanTestPkgList = (List<BeanTestPkg>) httpSession.getAttribute( "orderNew_beanTestPkgList");					
					List orderNew_testList = (List ) httpSession.getAttribute( "orderNew_testList");
				    BeanTest beanTest =  (BeanTest) session.load( BeanTest.class, new Integer( dataForm.getId_testtemp() ) );
				    boolean isInTheList = false;
				    if(orderNew_beanTestPkgList == null)
				    {				   
					    BeanTest bean;
					    if ( orderNew_testList != null)
					    {
					    	Iterator iter = orderNew_testList.iterator();
					    	while ( iter.hasNext() )
							  {   							 
								 bean= (BeanTest) iter.next();
								 if(beanTest.getId() == bean.getId() )
								 {
									orderNew_testList.remove(bean);
									break;
								 }
							  }
					    }
				    }else
				    {
				    	BeanTest bean;
				     	Iterator iter = orderNew_testList.iterator();
				     	BeanTestPkgDetail beanTestPkgDetail = new BeanTestPkgDetail();
				     	BeanTestPkg beanTestPkg = new BeanTestPkg();
				    	
						Iterator iter2 = orderNew_beanTestPkgList.iterator();
						while ( iter2.hasNext() )
						  { 		
							 
							 beanTestPkg = ((BeanTestPkg) iter2.next());
							 String query =  " FROM BeanTestPkgDetail u" +
											 " WHERE u.id_test_pkg.id = " + beanTestPkg.getId() +
							  				 " ORDER BY u.id";
										
							 List list = session.createQuery( query ).list();
							 Iterator iterPkgDetail = list.iterator();
							 
							 while ( iterPkgDetail.hasNext() )
							  {  
								 beanTestPkgDetail = (BeanTestPkgDetail)iterPkgDetail.next();
								 if(beanTestPkgDetail.getId_test().getId() == beanTest.getId() )
								 {										
									isInTheList = true;									
									break;
								 }
							  }
							 
							 
							 if(isInTheList)
								 break;
							 
						  }
							 
							 				 
							
							 
							 if(!isInTheList)
							 {								  
						    	while ( iter.hasNext() )
								  {   							 
									 bean= (BeanTest) iter.next();
									 if(beanTest.getId() == bean.getId() )
									 {
										orderNew_testList.remove(bean);
										break;
									 }
								  }								 
								
							 }
							 
						  				    	
				    }
				    
				    
				    
				   
				    
					//save current Patient
					BeanPatient orderNew_currentPatient =  (BeanPatient) session.load( BeanPatient.class, new Integer( dataForm.getId_patient() ) );
					httpSession.setAttribute("orderNew_currentPatient", orderNew_currentPatient );
					
					//save current Doctor
					BeanDoctor orderNew_currentDoctor =  (BeanDoctor) session.load( BeanDoctor.class, new Integer( dataForm.getId_doctor() ) );
					httpSession.setAttribute("orderNew_currentDoctor", orderNew_currentDoctor );
					
					//save current Branch
					BeanBranch orderNew_currentBranch =  (BeanBranch) session.load( BeanBranch.class, new Integer( dataForm.getId_branch() ) );
					httpSession.setAttribute("orderNew_currentBranch", orderNew_currentBranch );
				    
					//save current Company
					BeanCompany orderNew_currentCompany =  (BeanCompany) session.load( BeanCompany.class, new Integer( dataForm.getId_company() ) );
					httpSession.setAttribute("orderNew_currentCompany", orderNew_currentCompany );

					if( isInTheList)
					{
						com.bituos.Error error = new com.bituos.Error("El analisis que intenta eliminar esta incluido en un paquete, elimine todos los analisis e intente de nuevo.", request);
						httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
						forward = mapping.findForward("error");
					}else
					{
						if ( orderNew_testList == null || orderNew_testList.isEmpty())				   
							  httpSession.removeAttribute("orderNew_testList");	
						else
						      httpSession.setAttribute("orderNew_testList",orderNew_testList);
						
						forward = mapping.findForward("loop");
					}
					
				  }
				else if ( dataForm.getProcess().equals("testDeleteAll") )
				  {					
					httpSession.removeAttribute("orderNew_testList");	

					//save current Patient
					BeanPatient orderNew_currentPatient =  (BeanPatient) session.load( BeanPatient.class, new Integer( dataForm.getId_patient() ) );
					httpSession.setAttribute("orderNew_currentPatient", orderNew_currentPatient );
					
					//save current Doctor
					BeanDoctor orderNew_currentDoctor =  (BeanDoctor) session.load( BeanDoctor.class, new Integer( dataForm.getId_doctor() ) );
					httpSession.setAttribute("orderNew_currentDoctor", orderNew_currentDoctor );
					
					//save current Branch
					BeanBranch orderNew_currentBranch =  (BeanBranch) session.load( BeanBranch.class, new Integer( dataForm.getId_branch() ) );
					httpSession.setAttribute("orderNew_currentBranch", orderNew_currentBranch );
					
					//save current Company
					BeanCompany orderNew_currentCompany =  (BeanCompany) session.load( BeanCompany.class, new Integer( dataForm.getId_company() ) );
					httpSession.setAttribute("orderNew_currentCompany", orderNew_currentCompany );

					forward = mapping.findForward("loop");
				  }				
				else if ( dataForm.getProcess().equals("pkgAdd") )
				  {
									
					try
					  {
						sessionFactory = config.getConfiguration( request ).buildSessionFactory();
						session = sessionFactory.openSession();

						boolean isInTheList = false;
						BeanTestPkg beanTestPkg =  (BeanTestPkg) session.load( BeanTestPkg.class, new Integer( dataForm.getId_pkg()) );
						BeanTestPkg beanTestPkg2;
						String pkgName = "";
						List<BeanTestPkg> orderNew_beanTestPkgList = (List<BeanTestPkg>) httpSession.getAttribute( "orderNew_beanTestPkgList");
						if ( orderNew_beanTestPkgList != null)
						{						
							Iterator iter = orderNew_beanTestPkgList.iterator();
					    	while ( iter.hasNext() )
							  {  
					    		beanTestPkg2= (BeanTestPkg) iter.next();
					    		if(beanTestPkg2.getId() == beanTestPkg.getId() )
								 {
									 isInTheList = true;
									 pkgName = beanTestPkg2.getDescription();
									 break;
								   
								 }
							  }
					    	
					    	if(!isInTheList)				   
								orderNew_beanTestPkgList.add(beanTestPkg);
						       
						}
						else
						{
							orderNew_beanTestPkgList = new ArrayList<BeanTestPkg>();
							orderNew_beanTestPkgList.add(beanTestPkg);
							httpSession.removeAttribute("orderNew_beanTestPkgList");
							httpSession.setAttribute("orderNew_beanTestPkgList",orderNew_beanTestPkgList);
						}
						
						if(!isInTheList)
						{					   
						
							
							String query =  " FROM BeanTestPkgDetail u" +
											" WHERE u.id_test_pkg.id = " + dataForm.getId_pkg() +
							  				" ORDER BY u.id";
										
							List list = session.createQuery( query ).list();
	 
						    List<BeanTest> orderNew_testList = (List<BeanTest>) httpSession.getAttribute( "orderNew_testList");
						    //BeanTest beanTest =  (BeanTest) session.load( BeanTest.class, new Integer( dataForm.getId_test() ) );
						    BeanTest bean;
						    BeanTest bean2;
						    BeanTestPkgDetail beanTestPkgDetail = new BeanTestPkgDetail();
						    
						    
							if ( orderNew_testList != null)
							{
								Iterator iter = orderNew_testList.iterator();
								Iterator iter2;
						    	while ( iter.hasNext() )
								  {   							 
									 bean= (BeanTest) iter.next();
									 
									 iter2 = list.iterator();
									 while ( iter2.hasNext() )
									  { 
										 //beanTestPkgDetail = new BeanTestPkgDetail();
										 beanTestPkgDetail = ((BeanTestPkgDetail) iter2.next());
										// 
										 
										 if(beanTestPkgDetail.getId_test().getId() == bean.getId() )
										 {
											 isInTheList = true;
											 break;
										   
										 }
									  }								 								 
									 
								  }
						    	
						    	if(!isInTheList)
								 {
									/* bean2 = new BeanTest();
									 bean2 = beanTestPkgDetail.getId_test();
									 bean2.setPrice(beanTestPkgDetail.getPrice());
									 orderNew_testList.add(bean2);*/
									 
									 iter2 = list.iterator();
									 while ( iter2.hasNext() )
									   { 
									     beanTestPkgDetail = ((BeanTestPkgDetail) iter2.next());
									     bean2 = beanTestPkgDetail.getId_test();
									     bean2.setPrice(beanTestPkgDetail.getPrice());
									     orderNew_testList.add(beanTestPkgDetail.getId_test());
										 
									   }
								 }
						    	
						        
							}
							else
							{
								orderNew_testList = new ArrayList<BeanTest>();
								
								Iterator iter2 = list.iterator();
								while ( iter2.hasNext() )
								 { 
								   beanTestPkgDetail = ((BeanTestPkgDetail) iter2.next());
								   bean2 = beanTestPkgDetail.getId_test();
								   bean2.setPrice(beanTestPkgDetail.getPrice());
								   orderNew_testList.add(beanTestPkgDetail.getId_test());
									 
								  }
								
							}
							
							if(!isInTheList)
							{
							   httpSession.removeAttribute("orderNew_testList");
							   httpSession.setAttribute("orderNew_testList",orderNew_testList);
							   
							   httpSession.removeAttribute("orderNew_beanTestPkgList");
							   httpSession.setAttribute("orderNew_beanTestPkgList",orderNew_beanTestPkgList);
							   forward = mapping.findForward("loop");
							}
							else
							{
								
							   com.bituos.Error error = new com.bituos.Error("Ya existe un analisis con ese nombre agregado a la orden.", request);
							   httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
							   forward = mapping.findForward("error");
							}
					    
						}
						else
						{
						   com.bituos.Error error = new com.bituos.Error("Ya existe un paquete con el nombre " + pkgName + " agregado a la orden", request);
						   httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
						   forward = mapping.findForward("error");
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
						session.close();
						sessionFactory.close();
					  }
				  }				
				else if ( dataForm.getProcess().equals("done") )
				  {
					BeanTestOrder bean = new BeanTestOrder();
				    BeanBranch beanBranch =  (BeanBranch) session.load( BeanBranch.class, new Integer( dataForm.getId_branch() ) );
				    BeanPatient beanPatient =  (BeanPatient) session.load( BeanPatient.class, new Integer( dataForm.getId_patient() ) );
				    BeanCompany  beanCompany =  (BeanCompany) session.load( BeanCompany.class, new Integer( dataForm.getId_company()) );
					
				    
				    bean.setId_branch( beanUser.getId_branch() );
					bean.setId_patient( beanPatient );
					bean.setDate_reg( Utils.Today() );
					bean.setPatient_name( beanPatient.getName() );
					bean.setIs_finished( 0 );
					bean.setDiscount( 0 );
					bean.setPrice( 0 );
					bean.setId_user( beanUser );
					bean.setId_branch_delivery( beanBranch );
					bean.setEmail(beanPatient.getEmail());
					bean.setPassword(dataForm.getPassword());					
					bean.setDate_delivery(Utils.StrToDate( dataForm.getDate_delivery()));
					bean.setNotes(dataForm.getNotes());
					
					bean.setId_company(beanCompany);
					
					tx = session.beginTransaction();
					session.save( bean );
					
					BeanTestDoctor beanTestDoctor =  new BeanTestDoctor();
					if ( dataForm.getSent_by_doctor().equals("on") )
					  {
						String query = " FROM BeanDoctorRepresentative u" 
					                 + " WHERE u.id_doctor.id = " + dataForm.getId_doctor();
						
						List list = session.createQuery(query).list();
						BeanDoctor beanDoctor  =  (BeanDoctor) session.load( BeanDoctor.class, new Integer( dataForm.getId_doctor() ) );
						  
						
						beanTestDoctor.setId_doctor(beanDoctor);
						beanTestDoctor.setId_order(bean);
						beanTestDoctor.setDoctor_name(beanDoctor.getName());
						session.save( beanTestDoctor );
						
						//Hay representante para ese doctor
						if ( !list.isEmpty() ) 
						  {
							Iterator iter = list.iterator();
							
							BeanDoctorRepresentative beanDoctorRepresentative = (BeanDoctorRepresentative) iter.next();
						
							BeanRepresentative beanRepresentative = beanDoctorRepresentative.getId_representative();
							BeanTestRepresentative beanTestRepresentative = new BeanTestRepresentative();
							
							beanTestRepresentative.setId_order( bean );
							beanTestRepresentative.setId_representative( beanRepresentative );
							beanTestRepresentative.setPaid( 0 );

							session.save( beanTestRepresentative );
						  }

					  }
					
					//BeanTestOrderTest beanTestOrderTest =new 
					List orderNew_testList = (List ) httpSession.getAttribute( "orderNew_testList");
					Iterator iter = orderNew_testList.iterator();
					BeanTest beanTest;
					BeanTestOrderTest beanTestOrderTest;
					double total = 0.0;
					
			    	while ( iter.hasNext() )
					  {   							 
						 beanTest = (BeanTest) iter.next();
						 beanTestOrderTest = new BeanTestOrderTest();
						 beanTestOrderTest.setId_test(beanTest);
						 beanTestOrderTest.setId_test_order(bean);
						 beanTestOrderTest.setTest_name( beanTest.getName() );
						 beanTestOrderTest.setTest_price( beanTest.getPrice() );

						 session.save( beanTestOrderTest );

						 total += beanTestOrderTest.getTest_price();
						
						 String query =   " FROM BeanTestParameter u" 
						                + " WHERE u.id_test.id = " + beanTest.getId() +
						                  " AND u.active = 1";
						   
						 List list = session.createQuery(query).list();
						 Iterator iter1 = list.iterator();
						 while ( iter1.hasNext() )
						  { 
							 BeanTestParameter  beanTestParameter = (BeanTestParameter)iter1.next();
							 
						     BeanTestResult  beanTestResult = new BeanTestResult();
						     beanTestResult.setId_test_parameter(beanTestParameter);
						     beanTestResult.setId_order(bean);
						     beanTestResult.setIs_numeric(beanTestParameter.getIs_numeric());
						     
						     beanTestResult.setTest_name( beanTest.getName() );
						     beanTestResult.setTest_parameter_name( beanTestParameter.getName() );
						     beanTestResult.setIs_numeric( beanTestParameter.getIs_numeric() );
						     beanTestResult.setIs_free_text( beanTestParameter.getIs_free_text() );
						     
						     //Solo un valor de referencia ?
							 query =   " FROM BeanParameterLimits u" 
						             + " WHERE u.id_test_parameter.id = " + beanTestParameter.getId();  

							 list = session.createQuery(query).list();

								// beanTestResult.setShow_limits( 0 );
							 
							 beanTestResult.setMust_compare(0);
								 
							 
							 if ( list.size() == 1)
							   {
								 Iterator iterPL = list.iterator();
								 BeanParameterLimits beanParameterLimits = (BeanParameterLimits) iterPL.next();
								 
								 beanTestResult.setMust_compare( 1 );

								 if ( beanTestParameter.getIs_numeric() == 1 )
							       {
									 beanTestResult.setMin_value( beanParameterLimits.getLimit_from_num() );
									 beanTestResult.setMax_value( beanParameterLimits.getLimit_to_num() );
							       }
								 else
							       {
									 beanTestResult.setMin_value_str( beanParameterLimits.getLimit_from_str() );
									 beanTestResult.setMax_value_str( beanParameterLimits.getLimit_to_str() );
							       }

							   }


						     session.save( beanTestResult );
						  }
					  }
					
					bean.setPrice( total );
				    session.update( bean );
				    
				    
				    
				    List orderNew_materialDetailList = (List ) httpSession.getAttribute( "orderNew_materialDetailList");
				    if(orderNew_materialDetailList != null)
				      {
					    Iterator iter3 = orderNew_materialDetailList.iterator();
					    BeanOrderMaterial beanOrderMaterial;
						 while ( iter3.hasNext() )
						   { 
							 beanOrderMaterial = new BeanOrderMaterial();
							 MaterialDetail materialDetail = (MaterialDetail)iter3.next();		
							 BeanMaterial beanMaterial=  materialDetail.getId_material();
							 
							 beanOrderMaterial.setId_material(beanMaterial);
							 beanOrderMaterial.setQty( materialDetail.getQuantity());
							 beanOrderMaterial.setId_order(bean);
							 beanOrderMaterial.setMaterial_description(beanMaterial.getDescription());
							 beanOrderMaterial.setMaterial_has_label(beanMaterial.getHaslabel());
							
							 beanOrderMaterial.setIs_patient(materialDetail.getIs_patient());
							  
							 session.save(beanOrderMaterial);
						   }
				      }
				    /*
				    else
				      {
						
				    	httpSession.setAttribute("done", new String("pre.do?process='orderNew'"));
						com.bituos.Error error = new com.bituos.Error("No selecciono ningun Material.", request);
						forward = mapping.findForward("error");
						return forward;
				      }
				    */
				    
				    List<BeanTestPkg> orderNew_beanTestPkgList = (List<BeanTestPkg>) httpSession.getAttribute( "orderNew_beanTestPkgList");
				    if(orderNew_materialDetailList != null)
				      {
					    BeanOrderPkg beanOrderPkg;
					    Iterator iterpkgList = orderNew_beanTestPkgList.iterator();
					    while ( iterpkgList.hasNext() )
						  { 
					    	BeanTestPkg beanTestPkg = (BeanTestPkg)iterpkgList.next();
					    	beanOrderPkg = new BeanOrderPkg();
					    	beanOrderPkg.setId_pkg(beanTestPkg);
					    	beanOrderPkg.setId_order(bean);
					    	beanOrderPkg.setPkg_name(beanTestPkg.getDescription());	
					    	session.save(beanOrderPkg);
						  }
				      }
					
					tx.commit();
					
					//httpSession.setAttribute("done", new String("./pre.jsp?&process='orderNew'&action='pre.do'&target=''"));
					//forward = mapping.findForward("ok");

				   /* httpSession.setAttribute( "crystal_rptname", new String("rptOrder.rpt") ) ;
					httpSession.setAttribute( "parameterCount", new Integer( 1 ) ) ;
					
					httpSession.setAttribute( "parameter1", new Integer( bean.getId() ) ) ;
					httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
					httpSession.setAttribute( "parameterName1", new String("test_order_id") ) ;

					forward = mapping.findForward("printGlobal");*/
					
					httpSession.removeAttribute("orderNew_materialDetailList");
					forward = mapping.findForward("okRptOrderResult");	
					
					httpSession.setAttribute("urlTicket", new String("./rpt.do?&process=rptOrderResult&p1=rptTicket.rpt&p2="+String.valueOf(bean.getId())+"&target='blank'"));
					httpSession.setAttribute("urlRptOrder", new String("./rpt.do?&process=rptOrderResult&p1=rptOrder.rpt&p2="+String.valueOf(bean.getId())+"&target='blank'"));
				
				  }
				else if ( dataForm.getProcess().equals("addMaterial") )
				{
					List<MaterialDetail> orderNew_materialDetailList = (List<MaterialDetail>) httpSession.getAttribute( "orderNew_materialDetailList");
				    BeanMaterial beanMaterial =  (BeanMaterial) session.load( BeanMaterial.class, new Integer( dataForm.getId_material() ) );
				    BeanMaterial bean;
				    boolean isInTheList = false;
				    boolean isInTheListPacient = false;
				    boolean isInTheListNoPacient = false;
				    MaterialDetail materialDetail = null;
				    String onoff="";
				    
					if ( orderNew_materialDetailList != null)
					{
						Iterator iter = orderNew_materialDetailList.iterator();
						
				    	while ( iter.hasNext() )
						  {   							 
				    		materialDetail= (MaterialDetail) iter.next();
				    		
							 if(beanMaterial.getId() == materialDetail.getId_material().getId())
							 {
								 onoff = dataForm.getIs_patient();
								 if(onoff == null )
									 onoff ="off";
									 
								 
								 if(onoff.equals("on"))
								 {
									 if(materialDetail.getIs_patient() == 1)
									 {
										 isInTheListPacient = true;
									 }else
									 {
										
									     isInTheListNoPacient = true;
										 
									 }
								 }else
								 {
									 if(materialDetail.getIs_patient() == 1)
									 {
										 isInTheListPacient = true;
									 }else
									 {
										
									     isInTheListNoPacient = true;
										 
									 }
								 }
								 
								    
							}	
								 
						  }
				    	
				    	if(isInTheListPacient!=true && isInTheListNoPacient !=true)
						 {
				    		materialDetail = new MaterialDetail();
				        	materialDetail.setId_material(beanMaterial);
				        	materialDetail.setQuantity(Integer.valueOf( dataForm.getQuantity()));
				        	onoff = dataForm.getIs_patient(); 
				        	if(dataForm.getIs_patient()== null )
							  {
				        	    materialDetail.setIs_patient(0);
							  }
							 else
							 {
								 if(onoff.equals("on"))
								 {
								   materialDetail.setIs_patient( 1);
								 }
								 else
								 {
								   materialDetail.setIs_patient( 0); 
								 }
							 }
				        	orderNew_materialDetailList.add( materialDetail );
						 }else if(isInTheListPacient!=true && isInTheListNoPacient ==true)
						 {
							materialDetail = new MaterialDetail();
				        	materialDetail.setId_material(beanMaterial);
				        	materialDetail.setQuantity(Integer.valueOf( dataForm.getQuantity()));			        
							materialDetail.setIs_patient( 1);
						
				        	orderNew_materialDetailList.add( materialDetail );
						 }
						 else if(isInTheListPacient==true && isInTheListNoPacient !=true)
						 {
							materialDetail = new MaterialDetail();
				        	materialDetail.setId_material(beanMaterial);
				        	materialDetail.setQuantity(Integer.valueOf( dataForm.getQuantity()));			        
							materialDetail.setIs_patient( 0);
						
				        	orderNew_materialDetailList.add( materialDetail );
						 }
					}
					else
					{
						onoff = dataForm.getIs_patient();
						materialDetail = new MaterialDetail();
			        	materialDetail.setId_material(beanMaterial);
			        	materialDetail.setQuantity(Integer.valueOf( dataForm.getQuantity()));
			        	if(dataForm.getIs_patient()== null )
						  {
			        	    materialDetail.setIs_patient(0);
						  }
						 else
						 {
							 materialDetail.setIs_patient( 1);
						 }
			        	
						orderNew_materialDetailList = new ArrayList<MaterialDetail>();
						orderNew_materialDetailList.add(materialDetail);
					}
					
					httpSession.removeAttribute("orderNew_materialDetailList");
					httpSession.setAttribute("orderNew_materialDetailList",orderNew_materialDetailList);
					
					//save current Patient
					BeanPatient orderNew_currentPatient =  (BeanPatient) session.load( BeanPatient.class, new Integer( dataForm.getId_patient() ) );
					httpSession.setAttribute("orderNew_currentPatient", orderNew_currentPatient );
					
					//save current Doctor
					BeanDoctor orderNew_currentDoctor =  (BeanDoctor) session.load( BeanDoctor.class, new Integer( dataForm.getId_doctor() ) );
					httpSession.setAttribute("orderNew_currentDoctor", orderNew_currentDoctor );
					
					//save current Branch
					BeanBranch orderNew_currentBranch =  (BeanBranch) session.load( BeanBranch.class, new Integer( dataForm.getId_branch() ) );
					httpSession.setAttribute("orderNew_currentBranch", orderNew_currentBranch );
					
					//save current Company
					BeanCompany orderNew_currentCompany =  (BeanCompany) session.load( BeanCompany.class, new Integer( dataForm.getId_company() ) );
					httpSession.setAttribute("orderNew_currentCompany", orderNew_currentCompany );

					forward = mapping.findForward("loop");
				}
				else if ( dataForm.getProcess().equals("materialDelete") )
				  {
					List<MaterialDetail> orderNew_materialDetailList = (List<MaterialDetail>) httpSession.getAttribute( "orderNew_materialDetailList");
				      
					MaterialDetail materialDetail;
				    if ( orderNew_materialDetailList != null)
				    {
				    	Iterator iter = orderNew_materialDetailList.iterator();
				    	while ( iter.hasNext() )
						  {   							 
				    		materialDetail= (MaterialDetail) iter.next();
							 if(Integer.valueOf( dataForm.getId_material()) == materialDetail.getId_material().getId() )
							 {
								 orderNew_materialDetailList.remove(materialDetail);
								break;
							 }
						  }
				    }
				    
				    if ( orderNew_materialDetailList == null || orderNew_materialDetailList.isEmpty())				   
					  httpSession.removeAttribute("orderNew_materialDetailList");	
				    else
				      httpSession.setAttribute("orderNew_materialDetailList",orderNew_materialDetailList);
				    
					//save current Patient
					BeanPatient orderNew_currentPatient =  (BeanPatient) session.load( BeanPatient.class, new Integer( dataForm.getId_patient() ) );
					httpSession.setAttribute("orderNew_currentPatient", orderNew_currentPatient );
					
					//save current Doctor
					BeanDoctor orderNew_currentDoctor =  (BeanDoctor) session.load( BeanDoctor.class, new Integer( dataForm.getId_doctor() ) );
					httpSession.setAttribute("orderNew_currentDoctor", orderNew_currentDoctor );
					
					//save current Branch
					BeanBranch orderNew_currentBranch =  (BeanBranch) session.load( BeanBranch.class, new Integer( dataForm.getId_branch() ) );
					httpSession.setAttribute("orderNew_currentBranch", orderNew_currentBranch );
					
					//save current Company
					BeanCompany orderNew_currentCompany =  (BeanCompany) session.load( BeanCompany.class, new Integer( dataForm.getId_company() ) );
					httpSession.setAttribute("orderNew_currentCompany", orderNew_currentCompany );
				    
				    forward = mapping.findForward("loop");
				  }
				else if ( dataForm.getProcess().equals("materialDeleteAll") )
				  {					
					httpSession.removeAttribute("orderNew_materialDetailList");	

	
					
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
