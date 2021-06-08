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

import com.bituos.gjv.forms.UserChangeForm;


/**
 * @version 	1.0
 * @author
 */
public class UserChangeAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		UserChangeForm userChangeForm = (UserChangeForm) form;

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
				
				
				if(userChangeForm.getProcess().equals("change"))
				{
				  BeanUser bean = (BeanUser) session.load( BeanUser.class, new Integer(userChangeForm.getIdUser()));
				  System.out.println(bean.getId());
				  bean.setName(userChangeForm.getName());
				  bean.setFull_name(userChangeForm.getFull_name());
				  bean.setPassword(userChangeForm.getPassword());
                  bean.setActive(bean.getActive());
				  bean.setAddress(userChangeForm.getAddress());
				  bean.setRfc(userChangeForm.getRfc().toUpperCase());
				  bean.setEmail(userChangeForm.getEmail());
				  bean.setTelephone(userChangeForm.getTelephone());
				  
				  String query = " FROM BeanUser u" +
			     				 " ORDER BY u.name";

				  List list = session.createQuery( query ).list();

				  ses.setAttribute("filterList", list );
			
				  tx = session.beginTransaction();
				  session.update( bean );
				  tx.commit();

				  ses.setAttribute("done", new String("/gjv/user/userChange.jsp"));
				  forward = mapping.findForward("ok");
				  //forward = mapping.findForward("userChange");
				}
				
				if(userChangeForm.getProcess().equals("select"))
				{
					//UserChangeForm userChangeForm = new UserChangeForm();

					String query =  " FROM BeanUser u" +
									" WHERE u.active = 'Y'" +
									" AND u.id =" + userChangeForm.getIdUser();

					List list1 = session.createQuery( query ).list();

					if ( list1.size() != 0 )
					{
					  BeanUser bean = (BeanUser) list1.get(0);

					  userChangeForm.setIdUser( bean.getId() );
					  userChangeForm.setName( bean.getName() );
					  userChangeForm.setPassword( bean.getPassword() );	
					  userChangeForm.setConfirmpassword( bean.getPassword() );	
					  userChangeForm.setRfc( bean.getRfc());
					  userChangeForm.setEmail( bean.getEmail());	
					  userChangeForm.setFull_name(bean.getFull_name());
					  userChangeForm.setAddress(bean.getAddress());
					  userChangeForm.setTelephone(bean.getTelephone());
					
				

					  ses.setAttribute("userChangeForm",userChangeForm );
					  

					  query = " FROM BeanUser u" +
						      " ORDER BY u.name";

				      List list = session.createQuery( query ).list();

				      ses.removeAttribute("filterList" );
				      ses.setAttribute("filterList", list );
				      ses.setAttribute("done", "main.jsp" );
					 

					}
					//ses.setAttribute("done", new String("/user/userChange.jsp"));
					forward = mapping.findForward("userChange");
		            
					
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

