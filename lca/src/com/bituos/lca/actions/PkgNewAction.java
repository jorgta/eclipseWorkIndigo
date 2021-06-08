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
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

public class PkgNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		
		PkgNewForm dataForm = (PkgNewForm) form;

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
					
					//List<BeanTestPkgDetail> pkgNew_testList = new ArrayList<BeanTestPkgDetail>();
					   /* BeanTestPkgDetail t =new BeanTestPkgDetail();
					    t.setId(1);			   
					    BeanTest beanTest = (BeanTest) session.load( BeanTest.class, new Integer( 413 ) );	               
					    t.setId_test(beanTest);
					    BeanTestPkg beanTestPkg= new BeanTestPkg();
					    t.setId_test_pkg(beanTestPkg);
					    t.setPrice(100.0);
					    t.setTest_name(beanTest.getName());
					    pkgNew_testList.add(t);*/
					List<BeanTestPkgDetail> pkgNew_testList = (List<BeanTestPkgDetail>) httpSession.getAttribute( "pkgNew_testList");
					//List<BeanTest> orderNew_testList = (List<BeanTest>) httpSession.getAttribute( "pkgNew_testList");
				    BeanTest beanTest =  (BeanTest) session.load( BeanTest.class, new Integer( dataForm.getId_test() ) );
				    BeanTestPkgDetail bean;
				    boolean isInTheList = false;
				    
					if ( pkgNew_testList != null)
					{
						Iterator iter = pkgNew_testList.iterator();
				    	while ( iter.hasNext() )
						  {   							 
							 bean= (BeanTestPkgDetail) iter.next();
							 
							 if(beanTest.getId() == bean.getId_test().getId() )
							 {
								 isInTheList = true;
								 break;
							   
							 }
						  }
				    	
				        if(!isInTheList)
				        {   
				        	bean = new BeanTestPkgDetail();
				        	bean.setId_test(beanTest);
				            bean.setTest_name(beanTest.getName());
				            bean.setPrice(Double.valueOf( dataForm.getTest_price()));
				           
				        	pkgNew_testList.add( bean );
				        }
					}
					else
					{
						pkgNew_testList = new ArrayList<BeanTestPkgDetail>();
						bean = new BeanTestPkgDetail();
						bean.setId_test(beanTest);
			            bean.setTest_name(beanTest.getName());
			            bean.setPrice(Double.valueOf( dataForm.getTest_price()));
			           
			        	pkgNew_testList.add( bean );
					}
					
					httpSession.removeAttribute("pkgNew_testList");
					httpSession.setAttribute("pkgNew_testList",pkgNew_testList);
					
				
					
					forward = mapping.findForward("loop");
				  }
				else if ( dataForm.getProcess().equals("testDelete") )
				  {
					List pkgNew_testList = (List ) httpSession.getAttribute( "pkgNew_testList");
				    BeanTest beanTest =  (BeanTest) session.load( BeanTest.class, new Integer( dataForm.getId_testtemp() ) );
				   
				    BeanTestPkgDetail bean;
				    if ( pkgNew_testList != null)
				    {
				    	Iterator iter = pkgNew_testList.iterator();
				    	
							 
				    	while ( iter.hasNext() )
						  {   	
				    		 bean= (BeanTestPkgDetail) iter.next();

							 if(beanTest.getId() == bean.getId_test().getId() )
							 {
								 pkgNew_testList.remove(bean);
								break;
							 }
						  }
				    }
				    
				    if ( pkgNew_testList == null || pkgNew_testList.isEmpty())				   
					  httpSession.removeAttribute("pkgNew_testList");	
				    else
				      httpSession.setAttribute("pkgNew_testList",pkgNew_testList);
				    


					forward = mapping.findForward("loop");
				  }
				else if ( dataForm.getProcess().equals("testDeleteAll") )
				  {					
					httpSession.removeAttribute("pkgNew_testList");	

				

					forward = mapping.findForward("loop");
				  }
				else if ( dataForm.getProcess().equals("done") )
				  {
					List<BeanTestPkgDetail> pkgNew_testList = (List<BeanTestPkgDetail>) httpSession.getAttribute( "pkgNew_testList");
					BeanTestPkg beanTestPkg = new BeanTestPkg();
					
					
					String query =  " FROM BeanTestPkg u" +
									" WHERE u.description = '" + dataForm.getDescription() + "'";
	
					List list = session.createQuery( query ).list();
	
				    if ( list.isEmpty() )
				      {				    	
				     
						beanTestPkg.setDescription(dataForm.getDescription());
						
						beanTestPkg.setDate_reg(Utils.Today());
	
						tx = session.beginTransaction();
						session.save( beanTestPkg );
						
						Iterator iter = pkgNew_testList.iterator();
						BeanTestPkgDetail bean;
						 
				    	while ( iter.hasNext() )
				    	{
				    		bean= (BeanTestPkgDetail) iter.next();
				    		bean.setId_test_pkg(beanTestPkg);
				    		session.save(bean);
				    	}
		
						
						tx.commit();
						
						
						httpSession.removeAttribute("pkgNew_testList");
	
						httpSession.setAttribute("done", new String("./pre.jsp?&process='pkgNew'&action='pre.do'&target=''"));
						forward = mapping.findForward("ok");
				      }
				    else
				    {
				       com.bituos.Error error = new com.bituos.Error("Ya existe un paquete con el nombre " + dataForm.getDescription(), request);
					   httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
					   forward = mapping.findForward("error");
				    }
				
				  }
				else if ( dataForm.getProcess().equals("addMaterial") )
				{
					/*List<MaterialDetail> orderNew_materialDetailList = (List<MaterialDetail>) httpSession.getAttribute( "orderNew_materialDetailList");
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

					forward = mapping.findForward("loop");*/
				}
				else if ( dataForm.getProcess().equals("materialDelete") )
				  {
					/*List<MaterialDetail> orderNew_materialDetailList = (List<MaterialDetail>) httpSession.getAttribute( "orderNew_materialDetailList");
				      
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
				    
				    forward = mapping.findForward("loop");*/
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
