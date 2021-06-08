package com.struts2.actions;

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

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import com.struts2.Beans.*;
import com.struts2.forms.ListProductEditForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class ListProductEditAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//Recibe  valores del  jsp y se los pasa al a un objeto
		ListProductEditForm listProductEditForm = (ListProductEditForm) form;

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

				/*caseNewForm.getProcess().equals()
				    obtiene  el valor de la variable process y compara con una cadena
				*/

				//si el process = "validate"
				
				String query = "nothing";
				
				if(listProductEditForm.getProcess().equals("select"))
				  {
					BeanProduct bean = (BeanProduct) session.load( BeanProduct.class, new Integer(listProductEditForm.getId_product()));

				    listProductEditForm.setDescription( bean.getDescription() );
				    listProductEditForm.setPrice( new Double( bean.getPrice()).toString() );
				    
				    if ( bean.getPerPerson().equals("Y") )
					  listProductEditForm.setPerPerson("on");
				    else
				      listProductEditForm.setPerPerson("off");
					
					forward = mapping.findForward("loop");
				  }
				else if(listProductEditForm.getProcess().equals("create"))
				  {
				    //Busca listas con ese nombre
					  query =  " FROM BeanProduct u" +
							   " WHERE u.id_company = " + beanUser.getId_company().getId() +  
							   " AND u.description = '" + listProductEditForm.getDescription() + "'" +  
							   " AND u.active = 'Y' ";  

					List list = session.createQuery( query ).list();
					
					if ( list.size() == 0 )
					  {
					  	BeanProduct bean = new BeanProduct();

					  	tx = session.beginTransaction();
						
						bean.setId_company( beanUser.getId_company() );
						bean.setDescription( listProductEditForm.getDescription() );
						bean.setPrice( new Double( listProductEditForm.getPrice() ).doubleValue() );
						
						if ( listProductEditForm.getPerPerson().equals("on") )
							bean.setPerPerson( "Y" );
						else
							bean.setPerPerson( "N" );

						bean.setActive( "Y" );
					
						session.save( bean );
						tx.commit();

					    int id_product = LocalUtils.putProductInSession( request, bean.getId() );
					    
					    if ( listProductEditForm.getId_product() == null )
					      LocalUtils.putProductInSession( request, 0 );
					    else
						  LocalUtils.putProductInSession( request, new Integer( listProductEditForm.getId_product() ).intValue() );

						forward = mapping.findForward("loop");
					  }
					else
					  {
						com.bituos.Error e = new com.bituos.Error("Existe una producto con esa descripcion.", request);
						httpSession.setAttribute("done", "javascript:history.back();" );
						
						forward = mapping.findForward("error");
					  }
				  }
				else if(listProductEditForm.getProcess().equals("delete"))
				  {
					BeanProduct beanProduct = (BeanProduct) session.load( BeanProduct.class, new Integer(listProductEditForm.getId_product()));
					boolean delete = true;

					//used in quote
					query = " FROM BeanQuoteProduct u" +
							" WHERE u.id_product =" + listProductEditForm.getId_product();
					
					List list = session.createQuery( query ).list();

					if ( list.size() > 0 )
					  delete = false;

					//used in sale
					query = " FROM BeanSaleProduct u" +
							" WHERE u.id_product =" + listProductEditForm.getId_product();
			
					list = session.createQuery( query ).list();
			
					if ( list.size() > 0 )
					  delete = false;

					tx = session.beginTransaction();
					if ( delete )
					  session.delete( beanProduct );
					else
					  {
						beanProduct.setActive("N");
					    session.update( beanProduct );
					  }
					
					tx.commit();
					
				    int id_product = LocalUtils.putProductInSession( request, 0 );
				    
				    if ( listProductEditForm.getId_product() == null )
					      LocalUtils.putProductInSession( request, 0 );
				    else
					  LocalUtils.putProductInSession( request, new Integer( listProductEditForm.getId_product() ).intValue() );
					
					forward = mapping.findForward("loop");
				  }
				else if(listProductEditForm.getProcess().equals("update"))
				  {
					BeanProduct bean = (BeanProduct) session.load( BeanProduct.class, new Integer( listProductEditForm.getId_product() ));

				  	tx = session.beginTransaction();
					
					bean.setDescription( listProductEditForm.getDescription() );
					bean.setPrice( new Double( listProductEditForm.getPrice() ).doubleValue() );

					if ( listProductEditForm.getPerPerson().equals("on") )
						bean.setPerPerson( "Y" );
					else
						bean.setPerPerson( "N" );
				
					session.update( bean );
					tx.commit();

				    int id_product = LocalUtils.putProductInSession( request, bean.getId() );
				    
				    if ( listProductEditForm.getId_product() == null )
					      LocalUtils.putMusicInSession( request, 0 );
				    else
					  LocalUtils.putMusicInSession( request, new Integer( listProductEditForm.getId_product() ).intValue() );

					forward = mapping.findForward("loop");
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

