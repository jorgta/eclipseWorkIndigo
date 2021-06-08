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
import java.text.*;

import com.bituos.*;
import com.bituos.gjv.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import com.bituos.gjv.forms.ContactNoteNewForm;

/**
 * @version 	1.0
 * @author
 */
public class ContactNoteNewAction extends Action {

	public ActionForward execute(
                                ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response
                              ) throws Exception 
     {

	   ActionErrors errors = new ActionErrors();
	   ActionForward forward = new ActionForward();
		// return value
	   ContactNoteNewForm contactNoteNewForm = (ContactNoteNewForm) form;

	   HttpSession ses = request.getSession();
	   ses.removeAttribute("done");

	   BeanUser beanUser = (BeanUser) ses.getAttribute("beanUser");

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
		  sessionFactory = config.getConfiguration( request ).buildSessionFactory();
		  session = sessionFactory.openSession();
         
		  if(contactNoteNewForm.getProcess().equals("select"))
			{
			  String query =  " FROM BeanContactRH u"+
							  " WHERE u.id =" + contactNoteNewForm.getIdContact();
			  
			  BeanContactRH bean = (BeanContactRH) session.load( BeanContactRH.class, new Integer(contactNoteNewForm.getIdContact()));
			  
			  
			  List list = session.createQuery( query ).list();
			
			  if ( !list.isEmpty())
			  {					
			    ses.removeAttribute("BeanContactRH");			    
			    ses.setAttribute("BeanContactRH",bean );		
			    
			    forward = mapping.findForward("contactNoteNewRH");		
			  }
		   }
		  if(contactNoteNewForm.getProcess().equals("add"))
			{ 
			  BeanContactRH bean = (BeanContactRH) session.load( BeanContactRH.class, new Integer(contactNoteNewForm.getIdContact()));
			  BeanNotes beanNote = new BeanNotes();		  
			 
			  beanNote.setNote(contactNoteNewForm.getNote());			 
			  beanNote.setFecha(Utils.StrToDate(contactNoteNewForm.getFecha()));
			  beanNote.setIdContactRH(bean);
			  
			  
			  
	          tx = session.beginTransaction();	         
	          session.save(beanNote);
	          tx.commit();
				    
	          forward = mapping.findForward("ok");
	          ses.setAttribute("done", "contactsRH/contactNote.jsp" );
			}


	  } 
      catch (Exception e) 
      {
				e.printStackTrace();
				com.bituos.Error error = new com.bituos.Error( "Error interno, consulte a soporte tecnico. (" + e.getMessage() + ")", request);
				ses.setAttribute("done", "javascript:history.back();");
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

		return (forward);

	}
}
