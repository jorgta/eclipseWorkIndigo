package com.bituos.gjv.actions;

import javax.servlet.http.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import java.util.*;

import com.bituos.*;
import com.bituos.gjv.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import com.bituos.gjv.forms.ContactGroupChangeForm;


/**
 * @version 	1.0
 * @author
 */
public class ContactGroupChangeAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		ContactGroupChangeForm contactChangeForm = (ContactGroupChangeForm) form;

		HttpSession ses = request.getSession();

		BeanUser beanUser = (BeanUser) ses.getAttribute("beanUser");

		Config config = new Config( request );

		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		if ( beanUser == null )
			forward = mapping.findForward("login");
		else {

			try {
				sessionFactory = config.getConfiguration( request ).buildSessionFactory();
				session = sessionFactory.openSession();
				
			
				if(contactChangeForm.getProcess().equals("change"))
				{
					
					
					BeanContactRHGroup bean = (BeanContactRHGroup) session.load( BeanContactRHGroup.class, new Integer(contactChangeForm.getIdContact()));
				  //BeanInOut beanInOut =(BeanInOut)session.load( BeanInOut.class, new Integer(contactChangeForm.getId()));;
				  
				  String query =  " FROM BeanInOutGroup u" +
					              " WHERE u.idContactRHGroup.id =" + bean.getId();

				  List list =   session.createQuery( query ).list();
				  BeanInOutGroup beanInOut = (BeanInOutGroup) list.get(0);
				  
				  //System.out.println("Id de Contactto =" + contactChangeForm.getIdContact());
								  
				  bean.setId(Integer.parseInt( contactChangeForm.getIdContact()));
				  bean.setFirst_name(contactChangeForm.getFirst_name()) ;
				  bean.setLast_name(contactChangeForm.getLast_name()) ;		
				  bean.setAddress(contactChangeForm.getAddress()) ;	
				  bean.setColony(contactChangeForm.getColony());
				  bean.setPhone(contactChangeForm.getPhone());	
				  bean.setCel_phone(contactChangeForm.getCel_phone());
				  
				 // System.out.println(contactChangeForm.getMarried());
				  
				  
				  ses.removeAttribute("casado");
				  String casado;
				  
				  if(contactChangeForm.getMarried().equals("true"))
				  {
					  bean.setMarried('Y');
					  casado="true";	
					  //System.out.println(contactChangeForm.getMarried());
				  }
				  else
				  {
					  bean.setMarried('N');
					  casado="false";	
					  //System.out.println(contactChangeForm.getMarried());
				  }
			
				 
				  
				  ses.setAttribute("casado",casado );
					  
				  bean.setNSS(contactChangeForm.getNSS());
				  bean.setWife_name(contactChangeForm.getWife_name());
				  bean.setDate_of_birth(Utils.StrToDate(contactChangeForm.getDate_of_birth()));				  
				  
		  
				  beanInOut.setStarting_date(Utils.StrToDate(contactChangeForm.getStarting_date()));
				  beanInOut.setEnding_date(Utils.StrToDate(contactChangeForm.getEnding_date()));

			
				  tx = session.beginTransaction();
				  session.update( bean );
				  session.update( beanInOut );
				  tx.commit();
				  
				  query = " FROM BeanContactRHGroup u" +
			      		  " ORDER BY u.first_name";

		  
				  list = session.createQuery( query ).list();
	      
	      			      
			      if ( !list.isEmpty() )
			       {
			    	 ses.removeAttribute("filterList");
			         ses.setAttribute("filterList", list );
			       }
			      else
			       {
			    	 query =  " FROM BeanContactRHGroup u" +
				       		  " WHERE u.id = 0";

			    	 list = session.createQuery( query ).list();
			    	 BeanContactRHGroup beanContactRH= new BeanContactRHGroup();
			    	 list.add(beanContactRH);	
			    	 
			    	 ses.removeAttribute("filterList");
			    	 ses.setAttribute("filterList", list );
			       }
				  
						          				 
				  forward = mapping.findForward("ok");
				  ses.setAttribute("done", "contactsRHGroup/contactChange.jsp" );
				  //forward = mapping.findForward("userChange");
				}
				
				if(contactChangeForm.getProcess().equals("select"))
				{
					//UserChangeForm userChangeForm = new UserChangeForm();
					 
					String query =  " FROM BeanContactRHGroup u"+
           		   					" WHERE u.id =" + contactChangeForm.getIdContact();
									

					List list = session.createQuery( query ).list();

					if ( list.size() != 0 )
					{
					  
					  BeanContactRHGroup bean = (BeanContactRHGroup) list.get(0);
    				 

					  contactChangeForm.setIdContact(String.valueOf(bean.getId()));
					  contactChangeForm.setFirst_name( bean.getFirst_name() );
					  contactChangeForm.setLast_name( bean.getLast_name() );		
					  contactChangeForm.setAddress( bean.getAddress() );	
					  contactChangeForm.setColony( bean.getColony());
					  contactChangeForm.setPhone( bean.getPhone());	
					  contactChangeForm.setCel_phone(bean.getCel_phone());
					  
					 
					  ses.removeAttribute("casado");
					  String casado;
					  if(bean.getMarried()== 'Y')
					  {
						  casado="true";						 
					  }
					  else
					  {
						  casado="false";						  
					  }
					 
					  
					  ses.setAttribute("casado",casado );
					  
					  
					  contactChangeForm.setNSS(bean.getNSS());
					  contactChangeForm.setWife_name(bean.getWife_name());
					  contactChangeForm.setDate_of_birth(Utils.DateToStr( bean.getDate_of_birth()));
					
					  query =  " FROM BeanInOutGroup u" +
		              		   " WHERE u.idContactRHGroup.id =" + bean.getId();

					  list =   session.createQuery( query ).list();
					  BeanInOutGroup beanInOut = (BeanInOutGroup) list.get(list.size() - 1);
					  
					
					  
					  contactChangeForm.setStarting_date(Utils.DateToStr(beanInOut.getStarting_date()));
					  Date fechaend = new Date();
					  
					// System.out.println(String.valueOf(beanInOut.getEnding_date()));
					  if(beanInOut.getEnding_date() != null)
						 contactChangeForm.setEnding_date(Utils.DateToStr(beanInOut.getEnding_date()));	
					 else
						 contactChangeForm.setEnding_date(Utils.DateToStr(fechaend));
					  //contactChangeForm.setEnding_date(Utils.DateToStr(beanInOut.getEnding_date()));	

					  ses.removeAttribute("contactChangeForm");
					  ses.setAttribute("contactChangeForm",contactChangeForm );
					  					
					  query = " FROM BeanContactRHGroup u" +
						      " ORDER BY u.first_name";

				      list = session.createQuery( query ).list();
				      
				      if ( !list.isEmpty() )
				      {
				    	 ses.removeAttribute("filterList");
				         ses.setAttribute("filterList", list );
				      }
				      else
				       {
				    	 query =  " FROM BeanContactRHGroup u" +
					       		  " WHERE u.id = 0";

				    	 list = session.createQuery( query ).list();
				    	 BeanContactRHGroup beanContactRH= new BeanContactRHGroup();
				    	 list.add(beanContactRH);
				    	 ses.removeAttribute("filterList");
				    	 ses.setAttribute("filterList", list );
				       }

				      
				      ses.setAttribute("done", "main.jsp" );
				      forward = mapping.findForward("contactGroupChangeRH");
				      //System.out.println(String.valueOf(contactChangeForm.getStarting_date()));
					}
					
					
		            
					
				}
				

			} catch (Exception e) {
				e.printStackTrace();

				com.bituos.Error error = new com.bituos.Error( "Error interno, consulte a soporte tecnico. (" + e.getMessage() + ")", request);
				ses.setAttribute("done", "javascript:history.back();");

				forward = mapping.findForward("error");

			}
			finally	{
				if ( session != null )
					session.close();

				if ( sessionFactory != null )
					sessionFactory.close();
			}
		}

		// If a message is required, save the specified key(s)

		return (forward);

	}
}

