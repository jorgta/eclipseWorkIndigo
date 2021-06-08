package com.bituos.lca.actions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.bituos.lca.Beans.*;
import com.bituos.lca.forms.*;
import com.bituos.MailSender;

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

public class OrderResultChangeAction extends Action {
	int id = 0;
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form, 
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		
		OrderResultChangeForm dataForm = (OrderResultChangeForm) form;

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
			    
				if ( dataForm.getProcess().equals("selectOrder") )
				  {
					String query =  " FROM BeanTestOrder u" +
									" WHERE u.id = " + dataForm.getId_order() +
									" AND is_finished = 1";
					
					List list = session.createQuery( query ).list();
					
				    if ( !list.isEmpty() )
				      {
						BeanTestOrder beanTestOrder = (BeanTestOrder) session.load( BeanTestOrder.class, new Integer( dataForm.getId_order() ) );
						
					    httpSession.setAttribute("orderResult_beanOrder", beanTestOrder );
						
						try
						  {
							sessionFactory = config.getConfiguration( request ).buildSessionFactory();
							session = sessionFactory.openSession();

							//Test
					    	BeanTest beanTest = null;
						    BeanTestOrderTest beanTestOrderTest = null;

						    query =  " FROM BeanTestOrderTest u" +
									 " WHERE u.id_test_order.id = " + beanTestOrder.getId() +
					  				 " ORDER BY u.id";
										
							list = session.createQuery( query ).list();

						    httpSession.setAttribute("orderResult_listTestOrderTest", list );

						    //current TestOrderTest
						    if ( !list.isEmpty() )
						      {
							    Iterator iter = list.iterator();
							    LinkedList lstItems = new LinkedList<TestResult>();
							    while ( iter.hasNext() )
							      {
								    beanTestOrderTest = (BeanTestOrderTest) iter.next();
								    
								    httpSession.setAttribute("orderResult_currentBeanTestOrderTest", beanTestOrderTest );
	
								    beanTest = beanTestOrderTest.getId_test();
								    
								    //Parametros
								    //Solo funcionara si cada test tiene al menos un parametro definido
									/*query =  " FROM BeanTestResult u" +
											 " WHERE u.id_order.id = " + beanTestOrder.getId() +
											 " AND u.id_test_parameter.id IN ( SELECT a.id" +
											 "                                 FROM BeanTestParameter a" +
											 "                                 WHERE a.id_test.id = " + beanTestOrderTest.getId_test().getId() +
											 "                               )" +
							  				 " ORDER BY u.test_parameter_name";*/
								    
									 query = " FROM BeanTestResult u" +
											 " WHERE u.id_order.id = " + beanTestOrder.getId() +
											 " AND u.id_test_parameter.id IN ( SELECT t.id" +
											 "                                 FROM BeanTestParameter t" +
											 "                                 WHERE t.id_test.id = " + beanTestOrderTest.getId_test().getId() +
											 "                               )" +
							  				 " ORDER BY u.id"; 
								    
								    
								    /*query =  " FROM BeanTestResult u" +
											 " WHERE u.id_order.id = " + beanTestOrder.getId() +								
							  				 " ORDER BY u.test_name";*/
										
									list = session.createQuery( query ).list();
					
									Iterator iter1 = list.iterator();
									
									BeanTestResult[] beanTestResults = new BeanTestResult[list.size()];
									
									TestResult testResult = new TestResult();
									int index = 0;
									if(!list.isEmpty())
									{
									    while ( iter1.hasNext() )
									      {
									    	//id = testResult.getId();
										    BeanTestResult beanTestResult = (BeanTestResult) iter1.next();
										    beanTestResults[index] = beanTestResult;
										    testResult = new TestResult();
										    testResult.setId(beanTestResult.getId());
										    testResult.setId_order(String.valueOf( beanTestResult.getId_order().getId() ));
										    testResult.setTest_name(beanTestResult.getTest_name());
										    testResult.setTest_parameter_name(beanTestResult.getTest_parameter_name());
										    testResult.setId_test(String.valueOf( beanTestOrderTest.getId_test().getId()));
										    
										    testResult.setIs_numeric(String.valueOf( beanTestResult.getIs_numeric()));
										    testResult.setMust_compare(String.valueOf( beanTestResult.getMust_compare()));
										    testResult.setIs_free_text(String.valueOf( beanTestResult.getIs_free_text()));
										    
										    testResult.setFree_text(beanTestResult.getValue_free_text());
										    
										    if(beanTestResult.getIs_numeric() ==1)
										    {
											    testResult.setMax_value(String.valueOf( beanTestResult.getMax_value()));
											    testResult.setMin_value(String.valueOf( beanTestResult.getMin_value()));
											    testResult.setValue(String.valueOf( beanTestResult.getValue()) );
										    }else
										    {										    
											    testResult.setMax_value_str(beanTestResult.getMax_value_str());
											    testResult.setMin_value_str(beanTestResult.getMin_value_str());
											    testResult.setValue_str(beanTestResult.getValue_str());
										    }
										    
										    
										    
										   
										    
										    
										    lstItems.add(testResult);
										    
										    index++;
									      }
										
									    dataForm.setLstItems(lstItems);
									    httpSession.setAttribute("orderResult_beanTestResults",beanTestResults );
										httpSession.setAttribute("beanTestResultsSize",index );
										
										
									}
							      }
							   /* httpSession.setAttribute("orderResult_listTestResult", list );
					    
							   
							    Iterator iter1 = list.iterator();

							    if ( iter1.hasNext() )
							      {
								    BeanTestResult beanTestResult = (BeanTestResult) iter1.next();

								    httpSession.setAttribute("orderResult_currentBeanTestResult", beanTestResult );
							      }*/
							   // dataForm.setLstItems(lstItems);
							    /*httpSession.setAttribute("orderResult_beanTestResults",beanTestResults );
								httpSession.setAttribute("beanTestResultsSize",index );*/
							    forward = mapping.findForward("loop");
						      }
						    else
						      {
						    	com.bituos.Error e = new com.bituos.Error("Error interno consulte a soporte tecnico ", request);									
								return mapping.findForward("error"); 
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
				    else
					  {
						com.bituos.Error e = new com.bituos.Error("La orden <strong>"+ dataForm.getId_order() +"</strong> no existe o no se le han capturado los resultados", request);
						
						forward = mapping.findForward("error");
					  }

				  }
				else if ( dataForm.getProcess().equals("selectTest") )
				  {
					try
					  {
						sessionFactory = config.getConfiguration( request ).buildSessionFactory();
						session = sessionFactory.openSession();

						BeanTestOrder beanTestOrder = (BeanTestOrder) httpSession.getAttribute("orderResult_beanOrder");
						BeanTestOrderTest beanTestOrderTest = (BeanTestOrderTest) session.load( BeanTestOrderTest.class, new Integer( dataForm.getId_test() ) );
						
						/*String query =   " FROM BeanTestResult u" +
										 " WHERE u.id_order.id = " + beanTestOrder.getId() +
										 " AND u.id_test_parameter.id IN ( SELECT a.id" +
										 "                                 FROM BeanTestOrderTest a" +
										 "                                 WHERE a.id_test.id = " + beanTestOrderTest.getId_test().getId() +
										 "                               )" +
						  				 " ORDER BY u.test_parameter_name"; */
						
						/*String query =   " FROM BeanTestResult u" +
										 " WHERE u.id_order.id = " + beanTestOrder.getId() +
										 " AND u.id_test_parameter.id =" + beanTestOrderTest.getId() +
						  				 " ORDER BY u.test_parameter_name"; */
						
						String query =   " FROM BeanTestResult u" +
										 " WHERE u.id_order.id = " + beanTestOrder.getId() +
										 " AND u.id_test_parameter.id IN ( SELECT t.id" +
										 "                                 FROM BeanTestParameter t" +
										 "                                 WHERE t.id_test.id = " + beanTestOrderTest.getId_test().getId() +
										 "                               )" +
						  				 " ORDER BY u.test_parameter_name"; 
				
						
						List list = session.createQuery( query ).list();
						
						Iterator iter1 = list.iterator();
						
						BeanTestResult[] beanTestResults = new BeanTestResult[list.size()];
						int index = 0;
						if(!list.isEmpty())
						{
						    while ( iter1.hasNext() )
						      {
							    BeanTestResult beanTestResult = (BeanTestResult) iter1.next();
							    beanTestResults[index] = beanTestResult;
							    index++;
						      }
							
													
							//dataForm.setBeanTestResults(beanTestResults);
							httpSession.setAttribute("orderResult_beanTestResults",beanTestResults );
							httpSession.setAttribute("beanTestResultsSize",index );
						}
						
						//BeanTestResult[] testResult =(BeanTestResult[]) objects;
						
		
					    //httpSession.setAttribute("orderResult_listTestResult", list );
							
					    httpSession.setAttribute("orderResult_currentBeanTestOrderTest", beanTestOrderTest );
					    
					     /*
					    Iterator iter = list.iterator();
					    
					    BeanTestResult beanTestResult = (BeanTestResult) iter.next();

					    httpSession.setAttribute("orderResult_currentBeanTestResult", beanTestResult );*/


					    
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
				else if ( dataForm.getProcess().equals("selectTestResult") )
				  {
					try
					  {
						sessionFactory = config.getConfiguration( request ).buildSessionFactory();
						session = sessionFactory.openSession();

						/*BeanTestResult beanTestResult = (BeanTestResult) session.load( BeanTestResult.class, new Integer( dataForm.getId_test_result() ) );

					    httpSession.setAttribute("orderResult_currentBeanTestResult", beanTestResult );

					    forward = mapping.findForward("loop");*/
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
				else if ( dataForm.getProcess().equals("captureValue") )
				  {
					
					
					try
					  {
						sessionFactory = config.getConfiguration( request ).buildSessionFactory();
						session = sessionFactory.openSession();
	
						tx = session.beginTransaction();
						int index=0;
						for(TestResult beanTestResultElement : dataForm.getLstItems()) 
						{
							
							BeanTestResult beanTestResult =  (BeanTestResult) session.load( BeanTestResult.class, new Integer( beanTestResultElement.getId()) );
							  
							
							/*String query =   " FROM BeanParameterLimits u" +
							 				 " WHERE u.id_test_parameter.id = " + beanTestResult.getId_test_parameter().getId();
							
							List list = session.createQuery( query ).list();*/
							
							if ( beanTestResultElement.getIs_free_text().equals("0") )
							{
								if ( beanTestResultElement.getIs_numeric().equals("1") )
								{
									if(beanTestResultElement.getConfirm() != null)
									{
									  beanTestResult.setValue( Double.valueOf( beanTestResultElement.getValue() ) );
									}else
									{
										if(beanTestResultElement.getMust_compare().equals("1"))
										{
										  if( Double.valueOf(beanTestResultElement.getValue()) >= beanTestResult.getMin_value() &&
											  Double.valueOf(beanTestResultElement.getValue()) <= beanTestResult.getMax_value()	 
										     )
										     {
											  beanTestResult.setValue( Double.valueOf( beanTestResultElement.getValue() ) );
										     }
										   else
										     {
											
											   com.bituos.Error e = new com.bituos.Error("No confirmo el valor del parametro: " + beanTestResult.getTest_parameter_name(), request);									
											   httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
											   forward = mapping.findForward("error");
											   return forward;
										     }
										  }
										else
										{
											 try
											 {
												 beanTestResult.setValue( Double.valueOf( beanTestResultElement.getValue() ) );
											 }catch(Exception ex)
											 {
												 com.bituos.Error e = new com.bituos.Error("El valor del parametro   <strong>" + beanTestResult.getTest_parameter_name() + "</strong> debe ser numerico", request);									
												 httpSession.setAttribute("url", new String("'javascript:history.go(-1);'"));
												 forward = mapping.findForward("error");
												 return forward;
											 }
											 
											 
										}
									}
										
								}else
								{
									if(!beanTestResultElement.getValue_str().equals("") )
									{
									  beanTestResult.setValue_str( beanTestResultElement.getValue_str());
									}else
									{
										beanTestResult.setValue_str(null);
									}
								}
							}
							else
							{
								beanTestResult.setValue_free_text(beanTestResultElement.getFree_text());
							}
							
							
							
							
							index++;	
							
							
							//Test
							/*if ( dataForm.getIs_numeric().equals("1") )
							  beanTestResult.setValue( Double.valueOf( dataForm.getValue() ) );
							else
							  beanTestResult.setValue_str( dataForm.getValue() );*/

							beanTestResult.setDate_reg( Utils.Today() );
						    
							tx = session.beginTransaction();
								session.update( beanTestResult );
							tx.commit();
						}
						  
                        
						
						BeanTestOrder beanTestOrder = (BeanTestOrder) httpSession.getAttribute("orderResult_beanOrder");
						
						String query =   " FROM BeanTestResult u" +
										 " WHERE u.id_order.id = " + beanTestOrder.getId() +
										 " AND date_reg IS NULL";

						List list = session.createQuery( query ).list();
						
						
						if ( list.isEmpty() )
						  {
							BeanTestOrder beanTestOrder1 =  (BeanTestOrder) session.load( BeanTestOrder.class, new Integer( beanTestOrder.getId()) );
							
							beanTestOrder1.setIs_finished(1);
							
							tx = session.beginTransaction();
							  session.update( beanTestOrder1 );
							tx.commit();
							
						
							if(beanTestOrder.getEmail() != null)
							  if(!beanTestOrder.getEmail().isEmpty())
							  {
								BeanControlPanel beanControlPanel =  (BeanControlPanel) session.load( BeanControlPanel.class, new Integer( 1 ) );
								boolean wasSend = true;
								String error = "";
	                            
								com.bituos.MailSender mailSender = new MailSender();
	
								  wasSend = mailSender.send(
										    beanControlPanel.getEmail_smtp_server(),
										    beanControlPanel.getEmail_smtp_port(),
										    beanControlPanel.getEmail_is_secure(),
										    beanControlPanel.getEmail_username(),
										    beanControlPanel.getEmail_password(),
											beanTestOrder.getEmail(),
											"",
											"",
											"Laboratorio Analisis Clinicos Especializados: Orden lista" + beanTestOrder1.getId(),
											"HTML",
											"<HTML>" +
											"<HEAD></HEAD>" +
											"<BODY>" +
											"<TABLE width='800' align='center'>" +
											"  <TR>" +
											"	<TD colspan='3'>" +
											//"       <img src='http://" + beanControlPanel.getHosting_server_name() + beanAd.getWebfilename() + "' border=0 width=500 height=500 onclick='/bituos/adAdmin.do?process=registerEvent&id=1&id_email=" + adEmail.getId() + "&action=click'>" +
											"		<BR>" +
											"		<BR>" +
											//"		<STRONG>Oferta valida al " + Utils.DateToStrInv( beanAd.getExpiration_date() ) + " o agotar existencias.</STRONG>" +
											"		<BR>" +
											"		<BR>" +
											"		<STRONG>Si no puede visualizar correctamente la imagen, favor de dar click en el siguiente</STRONG>" +
											"		<BR>" +
											//"		<a href='http://" + beanControlPanel.getHosting_server_name() + "/bituos/adAdmin.do?process=show&id=" + beanAd.getId() + "&id_email=" + adEmail.getId() + "'>enlace</a>" +
											"		<BR>" +
											"		<BR>" +
											"		<BR>" +
											"		<FONT color=RED>" +
											"		<ul>" +
											"		<li>Los precios no incluyen IVA</li>" +
											"		<li>Algunos precios son mostrados en Dolares y se venderan al tipo de cambio el dia de la compra</li>" +
											"		<li>Promociones validas hasta la fecha de vencimiento y/o agotar existencias</li>" +
											"		</ul>" +
											"		</FONT>" +
											"		<BR>" +
											"		<BR>" +
											"	</TD>		" +
											"  </TR>" +
											"  <TR>" +
											"	<TD width='100'>" +
											"	    <IMG border='0' src='http://" + beanControlPanel.getHosting_server_name() + "/bituos/img/PPI_logo_s.jpg' width='65' height='49'>" +
											"	</TD>" +
											"	<TD width='100'>" +
											"		<a href='http://www.bituos.com'>www.bituos.com</a>" +
											"	</TD>" +
											"	<TD width='100'>" +
											"	    <IMG border='0' src='http://" + beanControlPanel.getHosting_server_name() + "/bituos/img/telmex/Logo_Rep_Telmex_Sol_Av_Baja.jpg' width='114' height='49'>" +
											"	</TD>" +
											"  </TR>" +
											"</TABLE>" +
											"</BODY>" +
											"</HTML>",									
											request,
											false );
				
							  }
							
							
							//imprimir reporte de resultados
							  
						    httpSession.setAttribute( "crystal_rptname", new String("rptTestResult.rpt") ) ;
							httpSession.setAttribute( "parameterCount", new Integer( 1 ) ) ;
							
							httpSession.setAttribute( "parameter1", new Integer( beanTestOrder.getId() ) ) ;
							httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
							httpSession.setAttribute( "parameterName1", new String("test_result_order_id") ) ;
							

							forward = mapping.findForward("printGlobal");
						  }
						else
						{
							
							
							BeanTestOrderTest currentBeanTestOrderTest = (BeanTestOrderTest) httpSession.getAttribute( "orderResult_currentBeanTestOrderTest" );											
							List orderResult_listTestOrderTest = (List) httpSession.getAttribute( "orderResult_listTestOrderTest" );							
							orderResult_listTestOrderTest.remove(currentBeanTestOrderTest);
							
							currentBeanTestOrderTest = (BeanTestOrderTest)orderResult_listTestOrderTest.get(0);
							httpSession.setAttribute("orderResult_listTestOrderTest", orderResult_listTestOrderTest );
							
							 
							httpSession.setAttribute("orderResult_currentBeanTestOrderTest", currentBeanTestOrderTest);
							
							
							query =  " FROM BeanTestResult u" +
									 " WHERE u.id_order.id = " + beanTestOrder.getId() +
									 " AND u.id_test_parameter.id IN ( SELECT a.id" +
									 "                                 FROM BeanTestOrderTest a" +
									 "                                 WHERE a.id_test.id = " + currentBeanTestOrderTest.getId_test().getId() +
									 "                               )" +
					  				 " ORDER BY u.test_parameter_name"; 

							list = session.createQuery( query ).list();
							
							Iterator iter1 = list.iterator();
							
							BeanTestResult[] beanTestResults = new BeanTestResult[list.size()];
							index = 0;
							if(!list.isEmpty())
							{
							    while ( iter1.hasNext() )
							      {
								    BeanTestResult beanTestResult = (BeanTestResult) iter1.next();
								    beanTestResults[index] = beanTestResult;
								    index++;
							      }
								
														
								//dataForm.setBeanTestResults(beanTestResults);
								httpSession.setAttribute("orderResult_beanTestResults",beanTestResults );
								httpSession.setAttribute("beanTestResultsSize",index );
							}
						 
						  //httpSession.setAttribute( "messageResult", new String("Aun faltan resultados por capturar") ) ;
						    forward = mapping.findForward("loop");
						  
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
				else if ( dataForm.getProcess().equals("testDeleteAll") )
				  {
				  }
				else if ( dataForm.getProcess().equals("done") )
				  {
//					BeanTestOrder bean = new BeanTestOrder();
//				    BeanBranch beanBranch =  (BeanBranch) session.load( BeanBranch.class, new Integer( dataForm.getId_branch() ) );
//				    BeanPatient beanPatient =  (BeanPatient) session.load( BeanPatient.class, new Integer( dataForm.getId_patient() ) );
//				    
//				  	
//					bean.setId_branch( beanBranch );
//					bean.setId_patient( beanPatient );
//					bean.setDate_reg( Utils.Today() );
//					bean.setPatient_name( beanPatient.getName() );
//					bean.setIs_finished( 0 );
//					bean.setDiscount( 0 );
//					bean.setPrice( 0 );
//					bean.setId_user( beanUser );
//					bean.setId_branch( beanBranch );
					

//					tx = session.beginTransaction();
//					  session.save( bean );
//					tx.commit();
					
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
