package com.eventAdmin.actions;

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
import java.io.*;

import com.bituos.*;
import com.eventAdmin.Beans.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import com.eventAdmin.forms.QuoteNewForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class QuoteDeleteAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//Recibe  valores del  jsp y se los pasa al a un objeto
		QuoteNewForm quoteNewForm = (QuoteNewForm) form;

		HttpSession httpSession = request.getSession();

		httpSession.removeAttribute("done");

		//Usuario que se logeo y que esta en sesi?n
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


				//Objeto de tipo  BeanCase
				BeanQuote bean = new BeanQuote();

				String error = new String();

				/*caseNewForm.getProcess().equals()
				    obtiene  el valor de la variable process y compara con una cadena
				*/

				//si el process = "validate"
				
				String query = "nothing";
				
				if(quoteNewForm.getProcess().equals("validate"))
				  {
					//Busca al cliente
					query = " FROM BeanClient u" +
							" WHERE u.id_company = " + beanUser.getId_company().getId() +
						    " AND u.id =" + quoteNewForm.getId_client() +
							" AND u.active = 'Y'" +
					  		" ORDER BY u.name";

					List list = session.createQuery( query ).list();

					httpSession.removeAttribute("clientNumber");
					httpSession.removeAttribute("clientName");

					if( list.size()!=0 )
					  {
						httpSession.setAttribute("clientNumber",  new Integer(((BeanClient) list.get(0)).getId()).toString() );
						httpSession.setAttribute("clientName", ((BeanClient) list.get(0)).getName() );
						httpSession.setAttribute("visibleUserData","yes");
						
						query = " FROM BeanList u" +
								" WHERE u.id_company = " + beanUser.getId_company().getId() +  
								" ORDER BY u.description";
					  	
						list = session.createQuery( query ).list();
						httpSession.setAttribute("listList", list );

						if ( list.size() > 0 )
						  {
							query = " FROM BeanListOptions u" +
									" WHERE u.id_list =" + ((BeanList)list.get(0)).getId() +
							  		" ORDER BY u.min";
				  	
							List list1 = session.createQuery( query ).list();
							httpSession.setAttribute("listOptionsList", list1 );
						  }

						
						
						forward = mapping.findForward("loop");
					  }
					else
					  {
						httpSession.setAttribute("visibleUserData","no");
						httpSession.setAttribute("visibleCase","no");

						httpSession.setAttribute("done", "/eventAdmin/quote/quoteNew.jsp");
						com.bituos.Error e = new com.bituos.Error("El cliente no existe", request);
						//forward declarado en struts-config.xml
						forward = mapping.findForward("error");
					  }
				  }

				else if(quoteNewForm.getProcess().equals("selectList"))
				  {
					BeanList beanList = (BeanList) session.load( BeanList.class, new Integer(quoteNewForm.getId_list()));
					httpSession.setAttribute("listEdit_currentList", beanList );

					//Busca opciones de la lista
					query = " FROM BeanListOptions u" +
							" WHERE u.id_list =" + quoteNewForm.getId_list() +
					  		" ORDER BY u.min";

					List list = session.createQuery( query ).list();

					httpSession.setAttribute("listOptionsList", list );

					//list.clear();

					//httpSession.setAttribute("listOptionMenuList", list );

					forward = mapping.findForward("loop");
				  }
				else if(quoteNewForm.getProcess().equals("selectListOption"))
				  {
					BeanListOptions beanListOptions = (BeanListOptions) session.load( BeanListOptions.class, new Integer(quoteNewForm.getId_list_option()));
					httpSession.setAttribute("listEdit_currentOption", beanListOptions );

					//Busca menu de la lista de opcion seleccionada
					
					if ( quoteNewForm.getId_list_option() == "" )
					  {
						query = " FROM BeanListOptionMenu u" +
								" WHERE u.id_list_option =" + quoteNewForm.getId_list_option();

						List list = session.createQuery( query ).list();
			
						httpSession.setAttribute("listOptionsList", list );
			
						list.clear();
			
						httpSession.setAttribute("listOptionMenuList", list );
					  }

					forward = mapping.findForward("loop");
				  }
				else if( quoteNewForm.getProcess().equals("register") )
				  {
					BeanClient client = (BeanClient) httpSession.getAttribute("client");
				  	boolean ok = false;
				  	
					BeanList beanList = (BeanList) session.load( BeanList.class, new Integer(quoteNewForm.getId_list()));

					//Se pasan los valores a el objeto bean de tipo BeanCase
					bean.setId_list( beanList );
					bean.setDate_reg( Utils.Today() );
					bean.setId_user( beanUser );
					bean.setId_company( beanUser.getId_company() );

					//Guarda la informacion el la BD
					tx = session.beginTransaction();

					
					//envia email
					
//					new com.bituos.Mail(  beanUser.getEmail(),
//										"Hola:\n\n" +
//										"Ud tiene un nuevo caso asignado" + "\n\n" +
//										"Datos del caso:\n" + 
//										"Numero de caso: " + bean.getId() + "\n" +
//										"Nombre del cliente:" + bean.getId_client().getName() + "\n" +
//										"Nombre del usuario que captura:" + beanUser.getName()+ "\n" +
//										"Descripcion corta: " + bean.getShort_description() + "\n" +  
//										"Descripcion larga: " + bean.getDescription() + "\n\n\n" +
//										"Sistema Contact Center."
//										,
//										"Contact Center: caso nuevo: " +  bean.getId(),
//										request);
									
					httpSession.setAttribute("done", "pre.do?process=caseNew" );
					httpSession.setAttribute("msg", "Su numero de caso es " + bean.getId());
					//forward declarado en struts-config.xml
					forward = mapping.findForward("okGlobal");

				  }
				else
				  {
					httpSession.setAttribute("done", "javascript:history.back();");
					com.bituos.Error e = new com.bituos.Error("Opcion no programada", request);
					//forward declarado en struts-config.xml
					forward = mapping.findForward("error");
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
}

