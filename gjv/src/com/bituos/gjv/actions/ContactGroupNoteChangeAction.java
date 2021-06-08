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

import com.bituos.gjv.forms.ContactGroupNoteChangeForm;


/**
 * @version 	1.0
 * @author
 */
public class ContactGroupNoteChangeAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		ContactGroupNoteChangeForm contactNoteChangeForm = (ContactGroupNoteChangeForm) form;

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
				
			
				if(contactNoteChangeForm.getProcess().equals("change"))
				{
					
					
				  BeanNotesGroup bean = (BeanNotesGroup) session.load( BeanNotesGroup.class, new Integer(contactNoteChangeForm.getIdNote()));
			
				  bean.setNote(contactNoteChangeForm.getNote());
				  bean.setFecha(Utils.StrToDate(contactNoteChangeForm.getFecha()));
				 
				   
				  
				  
				  tx = session.beginTransaction();
				  session.update( bean );
				 
				  tx.commit();
				  
				  
				  String query =  " FROM BeanNotesGroup u"+
	   							  " WHERE u.idContactRH.id  =" + bean.getIdContactRH().getId();
  
				  List list = session.createQuery( query ).list();
  
				  ses.removeAttribute("notesList");	
				  ses.setAttribute("notesList", list );
			
						          				 
				  forward = mapping.findForward("ok");
				  ses.setAttribute("done", "contactsRHGroup/contactChangeNote.jsp" );
				  //forward = mapping.findForward("userChange");
				}
				
				if(contactNoteChangeForm.getProcess().equals("select"))
				{
					//UserChangeForm userChangeForm = new UserChangeForm();
				    BeanContactRHGroup bean = (BeanContactRHGroup) session.load( BeanContactRHGroup.class, new Integer(contactNoteChangeForm.getIdContact()));
				   
					
				    
				    String query =  " FROM BeanNotesGroup u"+
           		   					" WHERE u.idContactRH.id =" + bean.getId();
									

					List list = session.createQuery( query ).list();

					if ( !list.isEmpty() )
					 {
					   ses.removeAttribute("notesList");	
					   ses.setAttribute("notesList", list );
					 }
					else
			         {
			    	   BeanContactRHGroup beanContactRH= new BeanContactRHGroup();
			    	   list.add(beanContactRH);	
			    	   
			    	   ses.removeAttribute("notesList");
			    	   ses.setAttribute("notesList", list );
			         }
      
				      ses.setAttribute("done", "main.jsp" );
				      forward = mapping.findForward("contactGroupNoteChangeRH");
				      //System.out.println(String.valueOf(contactChangeForm.getStarting_date()));
				}
				if(contactNoteChangeForm.getProcess().equals("selectNote"))
				{
					//UserChangeForm userChangeForm = new UserChangeForm();
					BeanNotesGroup bean = (BeanNotesGroup) session.load( BeanNotesGroup.class, new Integer(contactNoteChangeForm.getIdNote()));
				   
					contactNoteChangeForm.setNote(bean.getNote());
					contactNoteChangeForm.setFecha(Utils.DateToStr(bean.getFecha()));	
				    
				    String query =  " FROM BeanNotesGroup u"+
           		   					" WHERE u.idContactRH.id  =" + bean.getIdContactRH().getId();
									

					List list = session.createQuery( query ).list();

					if ( !list.isEmpty() )
					 {
					   ses.removeAttribute("notesList");
					   ses.setAttribute("notesList", list );
					   
					   ses.removeAttribute("noteId");
					   ses.setAttribute("noteId", bean.getId() );
					 }
					else
			         {
			    	   
			    	   BeanNotesGroup beanNotes= new BeanNotesGroup();
			    	   list.add(beanNotes);	
			    	   
			    	   ses.removeAttribute("notesList");
			    	   ses.setAttribute("notesList", list );
			         }
      
				      ses.setAttribute("done", "main.jsp" );
				      forward = mapping.findForward("contactGroupNoteChangeRH");
				      //System.out.println(String.valueOf(contactChangeForm.getStarting_date()));
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

