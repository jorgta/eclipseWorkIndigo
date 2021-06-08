package com.eventAdmin.actions;

import javax.servlet.http.*;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import java.util.*;
import java.io.*;

import com.bituos.*;
import com.eventAdmin.Beans.*;


//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import com.eventAdmin.forms.PaymentNewForm;
import com.eventAdmin.forms.UserChangeForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class PaymentNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//Recibe  valores del  jsp y se los pasa al a un objeto
		PaymentNewForm paymentNewForm = (PaymentNewForm) form;
		//paymentNewForm paymentNewForm1 = new paymentNewForm();

		HttpSession httpSession = request.getSession();

		httpSession.removeAttribute("done");

		//Usuario que se logeo y que esta en sesión
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
				//se encarga de decir al sistema, donde se encuentran todos los archivos de mapeo de Hibernate
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();


				

				String error = new String();

				String query = "nothing";
				if(paymentNewForm.getProcess().equals("selectSale"))
				  {
					  query = " FROM BeanSale u" +
		        	          " WHERE u.id_company = " + beanUser.getId_company().getId() +
		        	          " AND u.id = " + paymentNewForm.getId_sale();

					  List list = session.createQuery( query ).list();
					
					  if ( list.size() > 0 )
					    {
						    
						
						  
						  
						  BeanSale beanSale = (BeanSale) session.load( BeanSale.class, new Integer(paymentNewForm.getId_sale()));
						 
						  
						  
						  httpSession.setAttribute("id_sale", paymentNewForm.getId_sale() );						  
						 
						  httpSession.setAttribute("currentSale", beanSale );	 
						  
						 
						  query = " FROM BeanPayments u" +
			        	          " WHERE u.id_company.id = " + beanSale.getId_company().getId() +
			        	          " AND u.id_sale.id = " + beanSale.getId() +
						          " AND u.id_intern = " + beanSale.getId_intern() +
						  		  " AND u.canceled = 'N'" +
						  		  " ORDER BY u.date_reg ASC";

				          list = session.createQuery( query ).list();
				          
				          double total = beanSale.getTotal();
				          double currentTotal = beanSale.getTotal();
				          PaymentsRecord paymentsRecord;
				          List paymentsRecordList = new ArrayList<PaymentsRecord>();
				          if ( list.size() > 0 )
						    {
								
								Iterator iter = list.iterator();
												
								
								while ( iter.hasNext() )
								  {
									BeanPayments beanPayments = (BeanPayments) iter.next();
									paymentsRecord = new PaymentsRecord();	
									
									paymentsRecord.setId(beanPayments.getId());
									paymentsRecord.setId_sale(beanPayments.getId_sale());
									paymentsRecord.setId_intern(beanPayments.getId_intern());
									paymentsRecord.setDate_reg(beanPayments.getDate_reg());
									paymentsRecord.setQuantity(beanPayments.getQuantity());
									currentTotal = currentTotal -  beanPayments.getQuantity();
									paymentsRecord.setCurrentCharge(currentTotal);
									
									if(beanPayments.getCanceled() != 'N')
										paymentsRecord.setCanceled(beanPayments.getCanceled());
									
									paymentsRecordList.add(paymentsRecord);
									
									
								  }
						
								
								List<PaymentsRecord> sortedList = sort(paymentsRecordList, "currentCharge");							
								
				        	    
				        	  
				        	  httpSession.setAttribute("paymentsRecordList", paymentsRecordList );
				        	  
				        	  
				        	  query =   " FROM BeanTypePayment";
						      list = session.createQuery( query ).list();
						         
						      httpSession.setAttribute("typePaymentList", list );
						      httpSession.setAttribute("typePay", "2" );
						      
						      query =   " FROM BeanPaymentTypeForm";
						      list = session.createQuery( query ).list();
						         
						      httpSession.setAttribute("paymentTypeFormList", list );
						      
						    }
				          else
				          {
				        	paymentsRecord = new PaymentsRecord();  
				        	paymentsRecord.setId_sale(beanSale);
				        	paymentsRecord.setId_intern(beanSale.getId_intern());
				        	paymentsRecord.setDate_reg(beanSale.getDate_reg());
				        	paymentsRecord.setQuantity(0);
							currentTotal = currentTotal -  0;
							paymentsRecord.setCurrentCharge(currentTotal);
							paymentsRecordList.add(paymentsRecord);
				        	httpSession.setAttribute("paymentsRecordList", paymentsRecordList );
				        	
				        	query =   " FROM BeanTypePayment";
						    list = session.createQuery( query ).list();
						         
						    httpSession.setAttribute("typePaymentList", list );
						    httpSession.setAttribute("typePay", "1" );
						    
						    query =   " FROM BeanPaymentTypeForm";
						    list = session.createQuery( query ).list();
						         
						    httpSession.setAttribute("paymentTypeFormList", list );
				        	 
				          }
				          
				          
				          
				          httpSession.setAttribute("total", String.valueOf(total) );
						 
						  forward = mapping.findForward("loop");
					    }			  
					  else
					   {
						 com.bituos.Error e = new com.bituos.Error("Error consulte al soporte tecnico.", request);
						 httpSession.setAttribute("done", "javascript:window.close();" );						  
						 forward = mapping.findForward("error");
					   }
				  }
				else if(paymentNewForm.getProcess().equals("payItMyAccount"))
				  {
					BeanSale beanSale = (BeanSale) session.load( BeanSale.class, new Integer(paymentNewForm.getId_sale()));
					double total = beanSale.getTotal();
			       
			        
					query = " FROM BeanPayments u" +
			      	        " WHERE u.id_company.id = " + beanSale.getId_company().getId() +
			      	        " AND u.id_sale.id = " + beanSale.getId() +
						    " AND u.id_intern = " + beanSale.getId_intern() +
						    " AND u.canceled = 'N'" +
						    " ORDER BY u.date_reg ASC";
			
				    List listPayments = session.createQuery( query ).list();
				    
				    double qty=0;
				  
				    if(paymentNewForm.getId_type_payment().equals("1"))
					 {
				       qty = Double.valueOf( paymentNewForm.getQuantity());
					   if (qty == beanSale.getTotal())
					   {
						   com.bituos.Error e = new com.bituos.Error("Debe seleccionar  Tipo de Pago (SAlDO).", request);
						   httpSession.setAttribute("done", "'history.go(-1); return false;'" );						  
						   return mapping.findForward("error");	
					   }
					   
					   if(!listPayments.isEmpty())	
				       {
				    	   com.bituos.Error e = new com.bituos.Error("Debe seleccionar  Tipo de Pago (ABONO).", request);
						   httpSession.setAttribute("done", "'history.go(-1); return false;'" );						  
						   return mapping.findForward("error");	
				       }
													   
					 }
					  
					 if(paymentNewForm.getId_type_payment().equals("2"))
					 {
					   qty = Double.valueOf( paymentNewForm.getQuantity());
					   if (qty == beanSale.getTotal())
					   {
						   com.bituos.Error e = new com.bituos.Error("Debe seleccionar  Tipo de Pago (SAlDO).", request);
						   httpSession.setAttribute("done", "'history.go(-1); return false;'" );						  
						   return mapping.findForward("error");	
					   }
					 }
					 
					 if(paymentNewForm.getId_type_payment().equals("3"))
					 {
					   qty = Double.valueOf( paymentNewForm.getQuantity());
					   if (qty < beanSale.getTotal())
					   {
						   com.bituos.Error e = new com.bituos.Error("La cantidad no corresponde con el SAlDO Total.", request);
						   httpSession.setAttribute("done", "'history.go(-1); return false;'" );						  
						   return mapping.findForward("error");	
					   }
													   
					 }
					 
					
					BeanPayments beanPayments = new BeanPayments();
					
					beanPayments.setId_company(beanSale.getId_company());
					beanPayments.setId_intern(beanSale.getId_intern());
					beanPayments.setId_sale(beanSale);
					beanPayments.setId_user(beanSale.getId_user());
					
					
					//if(paymentNewForm.getId_type_payment().equals("2"))
					beanPayments.setQuantity(Double.parseDouble( paymentNewForm.getQuantity() ));
					
					
					
					beanPayments.setDate_reg(Utils.StrToDate( paymentNewForm.getDate_reg()));
					beanPayments.setCanceled('N');
					//beanPayments.setDate_canceled(date_canceled);
					
					BeanTypePayment beanTypePayment = (BeanTypePayment) session.load( BeanTypePayment.class, new Integer(paymentNewForm.getId_type_payment()));	
					beanPayments.setId_tipo_pago(beanTypePayment);
					BeanPaymentTypeForm beanPaymentTypeForm = (BeanPaymentTypeForm) session.load( BeanPaymentTypeForm.class, new Integer(paymentNewForm.getId_payment_type_form()));
					beanPayments.setId_tipo_forma_pago(beanPaymentTypeForm);
					
					
					
					tx = session.beginTransaction();
					session.save( beanPayments );
					
					
					
					
					 query =  " FROM BeanPayments u" +
			      	          " WHERE u.id_company = " + beanSale.getId_company().getId() +
			      	          " AND u.id_sale = " + beanSale.getId() +
						      " AND u.id_intern = " + beanSale.getId_intern() +
						      " AND u.canceled = 'N'" +
						      " ORDER BY u.date_reg ASC";

			          List list = session.createQuery( query ).list();
			          
			          double currentTotal = beanSale.getTotal();
			          PaymentsRecord paymentsRecord;
			          List paymentsRecordList = new ArrayList<PaymentsRecord>();
			          
			          
			       
			          if ( list.size() > 0 )
					    {
			        	  
							
							Iterator iter = list.iterator();
											
							
							while ( iter.hasNext() )
							  {
								beanPayments = (BeanPayments) iter.next();
								paymentsRecord = new PaymentsRecord();	
								
								paymentsRecord.setId(beanPayments.getId());
								paymentsRecord.setId_sale(beanPayments.getId_sale());
								paymentsRecord.setId_intern(beanPayments.getId_intern());
								paymentsRecord.setDate_reg( beanPayments.getDate_reg());
								paymentsRecord.setQuantity(beanPayments.getQuantity());
								currentTotal = currentTotal -  beanPayments.getQuantity();
								paymentsRecord.setCurrentCharge(currentTotal);
								
								if(beanPayments.getCanceled() != 'N')
									paymentsRecord.setCanceled(beanPayments.getCanceled());

								  paymentsRecordList.add(paymentsRecord);
								
								
							  }
							
						
							 if(currentTotal >= 0)
							 {
			
								
								 
								 tx.commit(); 
								 
								 httpSession.setAttribute("total", String.valueOf(total));
			
								 
								 query =  " FROM BeanPayments u" +
						      	          " WHERE u.id_company = " + beanSale.getId_company().getId() +
						      	          " AND u.id_sale = " + beanSale.getId() +
									      " AND u.id_intern = " + beanSale.getId_intern() +
									      " AND u.canceled = 'N'" +
									      " ORDER BY u.id DESC";

				                 list = session.createQuery( query ).list();
				                
				                 int id_pay = ((BeanPayments)list.get(0)).getId();
				                 
								 
								 httpSession.setAttribute("total", String.valueOf(total));								 
								
								 httpSession.setAttribute( "crystal_rptname", "rptReceiptPayment.rpt" ) ;
								 
								 httpSession.setAttribute( "parameterCount", new Integer( 2 ) ) ;
														
								 httpSession.setAttribute( "parameter1", new Integer(  beanSale.getId() ) );
								 httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
								 httpSession.setAttribute( "parameterName1", new String("id_sale") ) ;
								 
								 httpSession.setAttribute( "parameter2", new Integer(  id_pay ) );
								 httpSession.setAttribute( "parameterType2", new String("Integer") ) ;
								 httpSession.setAttribute( "parameterName2", new String("id_pay") ) ;
								 
								 
				
								 forward = mapping.findForward("printGlobal");
								 
								
							 }
							 else
					         {
					        	// tx.rollback();
					        	 com.bituos.Error e = new com.bituos.Error("El cobro es mayor que el adeudo.", request);
								 httpSession.setAttribute("done", "'history.go(-1); return false;" );						  
								 forward = mapping.findForward("error");					        	
					         }
							
					
							List<PaymentsRecord> sortedList = sort(paymentsRecordList, "currentCharge");
			        	    httpSession.setAttribute("paymentsRecordList", paymentsRecordList );
					    }
			          else
			          {
			        	 list = new ArrayList<BeanPayments>();
			        	 httpSession.setAttribute("paymentsRecordList", paymentsRecordList );	
			        	 httpSession.setAttribute("total", String.valueOf( total ) );
						 forward = mapping.findForward("loop");
			        	 
			          }
			          
			          
			       
			          
			         
				  }				
			  } 
			catch (Exception m) 
			  {
				m.printStackTrace();
				httpSession.setAttribute("done", "javascript:history.back();");
				com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
				//forward declarado en struts-config.xml
				forward = mapping.findForward("error");
			  }
			finally	
			  {
				if ( session != null )
					session.close();

				if ( sessionFactory != null )
					sessionFactory.close();
			  }
		  }

		// Termina con
		return (forward);

	}
	
	public static <T> List<T> sort(List<T> list, String sortByProperty)
    {
            Collections.sort(list, new BeanComparator(sortByProperty));
            return list;
    }
	
	
	
	
}

