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

import com.eventAdmin.forms.PaymentCanceledForm;
import com.eventAdmin.forms.UserChangeForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class PaymentCanceledAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//Recibe  valores del  jsp y se los pasa al a un objeto
		PaymentCanceledForm paymenCanceledForm = (PaymentCanceledForm) form;
		//paymenCanceledForm paymenCanceledForm1 = new paymenCanceledForm();

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
				if(paymenCanceledForm.getProcess().equals("selectedSale"))
				  {
					  query = " FROM BeanSale u" +
		      	              " WHERE u.id_company = " + beanUser.getId_company().getId() +
		      	              " AND u.id = " + paymenCanceledForm.getId_sale();
		
					  List list = session.createQuery( query ).list();
					
					  if ( list.size() > 0 )
					    {
						    
						
						  
						  
						  BeanSale beanSale = (BeanSale) session.load( BeanSale.class, new Integer(paymenCanceledForm.getId_sale()));
						 
						  
						  
						  httpSession.setAttribute("id_sale", paymenCanceledForm.getId_sale() );						  
						 
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
						    }
				          else
				          {
				        	  /*beanPaymentsRecord = new BeanPaymentsRecord();  
				        	beanPaymentsRecord.setId_sale(beanSale);
							beanPaymentsRecord.setId_intern(beanSale.getId_intern());
							beanPaymentsRecord.setDate_reg(beanSale.getDate_reg());
							beanPaymentsRecord.setQuantity(0);
							currentTotal = currentTotal -  0;
							beanPaymentsRecord.setCurrentCharge(currentTotal);*/
							//beanPaymentsRecordList.add(beanPaymentsRecord);
				        	httpSession.setAttribute("paymentsRecordList", paymentsRecordList );
				        	 
				          }
				          
				          
				          
				          httpSession.setAttribute("total",String.valueOf( total ) );
						 
						  forward = mapping.findForward("loop");
					    }			  
					  else
					   {
						 com.bituos.Error e = new com.bituos.Error("Error consulte al soporte tecnico.", request);
						 httpSession.setAttribute("done", "javascript:window.close();" );						  
						 forward = mapping.findForward("error");
					   }
				  }
				else if(paymenCanceledForm.getProcess().equals("cancel"))
				  {
					//BeanSale beanSale = (BeanSale) session.load( BeanSale.class, new Integer(paymenCanceledForm.getId_sale()));
					BeanSale beanSale = (BeanSale) httpSession.getAttribute( "currentSale" );
					double total = beanSale.getTotal();
			       
					//httpSession.setAttribute("id_sale", beanSale.getId() );						  
						 
					//httpSession.setAttribute("currentSale", beanSale );	 
					  
					query = " FROM BeanPayments u" +
							" WHERE u.id = " + paymenCanceledForm.getId_payment();
					
					
					List list = session.createQuery( query ).list();
					
					BeanPayments beanPayments = new BeanPayments();
					
					if ( list.size() > 0 )
				    {
					  
					  tx = session.beginTransaction();					
					
					  ((BeanPayments)list.get(0)).setCanceled('Y');
					  session.update((BeanPayments)list.get(0));
					  tx.commit();
						
					  query = " FROM BeanPayments u" +
			      	          " WHERE u.id_company = " + beanSale.getId_company().getId() +
			      	          " AND u.id_sale = " + beanSale.getId() +
						      " AND u.id_intern = " + beanSale.getId_intern() +
						      " AND u.canceled = 'N'" +
						      " ORDER BY u.date_reg ASC";

			          list = session.createQuery( query ).list();
			          
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
							    
							
							 
							httpSession.setAttribute("total", String.valueOf( total ) );
							forward = mapping.findForward("loop");
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

