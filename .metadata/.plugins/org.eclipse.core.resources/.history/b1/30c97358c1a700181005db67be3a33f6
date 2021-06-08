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
import com.struts2.forms.ListEditForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class ListEditAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//Recibe  valores del  jsp y se los pasa al a un objeto
		ListEditForm listEditForm = (ListEditForm) form;

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
				
				if(listEditForm.getProcess().equals("selectList"))
				  {
					BeanList bean = (BeanList) session.load( BeanList.class, new Integer( listEditForm.getId_list() ));

					listEditForm.setListDescription( bean.getDescription() );
					
					int id_list = LocalUtils.putListInSession( request, new Integer(listEditForm.getId_list()).intValue() );
				    int id_option = LocalUtils.putOptionInSession( request, id_list, 0 );
				    int id_menu = LocalUtils.putMenuInSession( request, id_option, 0 );

					forward = mapping.findForward("loop");
				  }
				else if ( listEditForm.getProcess().equals("selectListOption") )
				  {
					BeanListOptions bean = (BeanListOptions) session.load( BeanListOptions.class, new Integer( listEditForm.getId_option() ));

					listEditForm.setOptionMin( new Integer( bean.getMin() ).toString() );
					listEditForm.setOptionMax( new Integer( bean.getMax() ).toString() );
					listEditForm.setOptionPrice( new Double( bean.getPrice() ).toString() );

					int id_list = LocalUtils.getCurrentList( request );
				    int id_option = LocalUtils.putOptionInSession( request, id_list, new Integer(listEditForm.getId_option()).intValue() );
				    int id_menu = LocalUtils.putMenuInSession( request, id_option, 0 );

					forward = mapping.findForward("loop");
				  }
				else if(listEditForm.getProcess().equals("createList"))
				  {
				    //Busca listas con ese nombre
					  query =  " FROM BeanList u" +
							   " WHERE u.id_company = " + beanUser.getId_company().getId() +  
							   " AND u.description = '" + listEditForm.getListDescription() + "'" +  
							   " AND u.active = 'Y' ";  

					List list = session.createQuery( query ).list();
					
					if ( list.size() == 0 )
					  {
					  	BeanList bean = new BeanList();

					  	tx = session.beginTransaction();
						
						bean.setId_company( beanUser.getId_company() );
						bean.setDescription( listEditForm.getListDescription() );
						bean.setActive( "Y" );
					
						session.save( bean );
						tx.commit();

					    int id_list = LocalUtils.putListInSession( request, bean.getId() );
					    int id_option = LocalUtils.putOptionInSession( request, id_list, 0 );
					    int id_menu = LocalUtils.putMenuInSession( request, id_option, 0 );

						forward = mapping.findForward("loop");
					  }
					else
					  {
						com.bituos.Error e = new com.bituos.Error("Existe una lista con ese nombre.", request);
						httpSession.setAttribute("done", "javascript:history.back();" );
						
						forward = mapping.findForward("error");
					  }
				  }
				else if(listEditForm.getProcess().equals("createOption"))
				  {
					  query =  " FROM BeanListOptions u" +
					  		   " WHERE u.id_list = " + listEditForm.getId_list() +  
							   " AND (  " + listEditForm.getOptionMin() + " BETWEEN u.min AND u.max" +  
							   "       OR  " + listEditForm.getOptionMax() + " BETWEEN u.min AND u.max" + ")";  
					
					  List list = session.createQuery( query ).list();
					  
					  if ( list.size() == 0 )
						  {
						  	BeanListOptions bean = new BeanListOptions();
							BeanList beanList = (BeanList) session.load( BeanList.class, new Integer(listEditForm.getId_list()));
	
						  	tx = session.beginTransaction();
							
							bean.setId_list( beanList );
							bean.setMin( new Integer( listEditForm.getOptionMin() ).intValue() );
							bean.setMax( new Integer( listEditForm.getOptionMax() ).intValue() );
							bean.setPrice( new Double( listEditForm.getOptionPrice() ).doubleValue() );
							bean.setActive( "Y" );
						
							session.save( bean );
							tx.commit();
	
						    int id_list = LocalUtils.getCurrentList( request );
					        int id_option = LocalUtils.putOptionInSession( request, id_list, bean.getId() );
						    int id_menu = LocalUtils.putMenuInSession( request, id_option, 0 );

						    forward = mapping.findForward("loop");
						  }
						else
						  {
							com.bituos.Error e = new com.bituos.Error("Existe un intervalo de personas que se solapa que estos numeros de personas.", request);
							httpSession.setAttribute("done", "javascript:history.back();" );
							
							forward = mapping.findForward("error");
						  }
					  
				  }
				else if ( listEditForm.getProcess().equals("selectMenu") )
				  {
					BeanListOptionMenu bean = (BeanListOptionMenu) session.load( BeanListOptionMenu.class, new Integer( listEditForm.getId_menu() ));

					listEditForm.setMenuName( bean.getName() );
					listEditForm.setMenuDescription( bean.getDescription() );
					listEditForm.setPrice1( new Double( bean.getPrice1() ).toString() );
					listEditForm.setPrice2( new Double( bean.getPrice2() ).toString() );
					listEditForm.setPrice3( new Double( bean.getPrice3() ).toString() );
					listEditForm.setPrice4( new Double( bean.getPrice4() ).toString() );
					listEditForm.setPrice5( new Double( bean.getPrice5() ).toString() );
					listEditForm.setPrice6( new Double( bean.getPrice6() ).toString() );
					listEditForm.setPrice7( new Double( bean.getPrice7() ).toString() );

					int id_list = LocalUtils.getCurrentList( request );
				    int id_option = LocalUtils.putOptionInSession( request, id_list, new Integer(listEditForm.getId_option()).intValue() );
				    int id_menu = LocalUtils.putMenuInSession( request, id_option, 0 );

					forward = mapping.findForward("loop");
				  }
				else if(listEditForm.getProcess().equals("createMenu"))
				  {
					  query =  " FROM BeanListOptionMenu u" +
					  		   " WHERE u.id_list_option = " + listEditForm.getId_option() +  
							   " AND u.name = '" + listEditForm.getMenuName() + "'";  
					
					  List list = session.createQuery( query ).list();
					  
					  if ( list.size() == 0 )
						  {
						  	BeanListOptionMenu bean = new BeanListOptionMenu();
						  	BeanListOptions beanListOptions = (BeanListOptions) session.load( BeanListOptions.class, new Integer(listEditForm.getId_option()));
	
						  	tx = session.beginTransaction();
							
							bean.setId_list_option( beanListOptions );
							bean.setName( listEditForm.getMenuName() );
							bean.setDescription( listEditForm.getMenuDescription() );
							bean.setActive( "Y" );
							bean.setPrice1( new Double( listEditForm.getPrice1() ).doubleValue() );
							bean.setPrice2( new Double( listEditForm.getPrice2() ).doubleValue() );
							bean.setPrice3( new Double( listEditForm.getPrice3() ).doubleValue() );
							bean.setPrice4( new Double( listEditForm.getPrice4() ).doubleValue() );
							bean.setPrice5( new Double( listEditForm.getPrice5() ).doubleValue() );
							bean.setPrice6( new Double( listEditForm.getPrice6() ).doubleValue() );
							bean.setPrice7( new Double( listEditForm.getPrice7() ).doubleValue() );
							
							session.save( bean );
							tx.commit();
	
						    int id_list = LocalUtils.getCurrentList( request );
					        int id_option = LocalUtils.getCurrentOption( request );
						    int id_menu = LocalUtils.putMenuInSession( request, id_option, bean.getId() );
	
						    forward = mapping.findForward("loop");
						  }
						else
						  {
							com.bituos.Error e = new com.bituos.Error("Existe un menu con esa descripcion.", request);
							httpSession.setAttribute("done", "javascript:history.back();" );
							
							forward = mapping.findForward("error");
						  }
				  }
				else if(listEditForm.getProcess().equals("deleteList"))
				  {
					BeanList beanList = (BeanList) session.load( BeanList.class, new Integer(listEditForm.getId_list()));
					boolean delete = true;

					//list in quote
					query = " FROM BeanQuote u" +
							" WHERE u.id_list =" + listEditForm.getId_list();
					
					List list = session.createQuery( query ).list();

					if ( list.size() > 0 )
					  delete = false;

					//list option in quote
					query = " FROM BeanQuote u" +
							" WHERE u.id_list_option IN ( SELECT a.id " + 
							"                             FROM BeanListOptions a" + 
							"                             WHERE a.id_list = " + listEditForm.getId_list() +
							"                           )";
			
					list = session.createQuery( query ).list();
		
					if ( list.size() > 0 )
					  delete = false;

					//list in sale
					query = " FROM BeanSale u" +
							" WHERE u.id_list =" + listEditForm.getId_list();
			
					list = session.createQuery( query ).list();
			
					if ( list.size() > 0 )
					  delete = false;

					//list option in sale
					query = " FROM BeanSale u" +
							" WHERE u.id_list_option IN ( SELECT a.id " + 
							"                             FROM BeanListOptions a" + 
							"                             WHERE a.id_list = " + listEditForm.getId_list() +
							"                           )";
			
					list = session.createQuery( query ).list();
		
					if ( list.size() > 0 )
					  delete = false;
					
					
					tx = session.beginTransaction();
					if ( delete )
					  {
						query = " FROM BeanListOptions u" + 
								" WHERE u.id_list = " + listEditForm.getId_list();
						
						list = session.createQuery( query ).list();
						
						Iterator iter = list.iterator();
						
						while ( iter.hasNext() )
						  {
							session.delete( (BeanListOptions) iter.next() );
						  }
		
					    session.delete( beanList );
					  }
					else
					  {
						beanList.setActive("N");
					    session.update( beanList );
					  }
					
					tx.commit();
					
				    int id_list = LocalUtils.putListInSession( request, 0 );
				    int id_option = LocalUtils.putOptionInSession( request, id_list, 0 );
				    int id_menu = LocalUtils.putMenuInSession( request, id_option, 0 );
					
					forward = mapping.findForward("loop");
				  }
				else if(listEditForm.getProcess().equals("deleteOption"))
				  {
					BeanListOptions beanListOptions = (BeanListOptions) session.load( BeanListOptions.class, new Integer(listEditForm.getId_option()));
					boolean delete = true;

					//list option in quote
					query = " FROM BeanQuote u" +
							" WHERE u.id_list_option =" + listEditForm.getId_option();
					
					List list = session.createQuery( query ).list();

					if ( list.size() > 0 )
					  delete = false;

					//list option in sale
					query = " FROM BeanSale u" +
							" WHERE u.id_list_option =" + listEditForm.getId_option();
			
					list = session.createQuery( query ).list();
			
					if ( list.size() > 0 )
					  delete = false;

					tx = session.beginTransaction();
					if ( delete )
					  {
						query = " FROM BeanListOptionMenu u" + 
								" WHERE u.id_list_option = " + listEditForm.getId_option();
						
						list = session.createQuery( query ).list();
						
						Iterator iter = list.iterator();
						
						while ( iter.hasNext() )
						  {
							session.delete( (BeanListOptionMenu) iter.next() );
						  }
		
					    session.delete( beanListOptions );
					  }
					else
					  {
						beanListOptions.setActive("N");
					    session.update( beanListOptions );
					  }
					
					tx.commit();
					
					
				    int id_list = LocalUtils.getCurrentList( request );
				    int id_option = LocalUtils.putOptionInSession( request, id_list, 0 );
				    int id_menu = LocalUtils.putMenuInSession( request, id_option, 0 );
					
					forward = mapping.findForward("loop");
				  }
				else if(listEditForm.getProcess().equals("deleteMenu"))
				  {
					BeanListOptionMenu beanListOptionMenu = (BeanListOptionMenu) session.load( BeanListOptionMenu.class, new Integer(listEditForm.getId_menu()));
					boolean delete = true;

					//list in quote
					query = " FROM BeanQuote u" +
							" WHERE u.id_list_option_menu =" + listEditForm.getId_menu();
					
					List list = session.createQuery( query ).list();

					if ( list.size() > 0 )
					  delete = false;

					//list in sale
					query = " FROM BeanSale u" +
							" WHERE u.id_list_option_menu =" + listEditForm.getId_list();
			
					list = session.createQuery( query ).list();
			
					if ( list.size() > 0 )
					  delete = false;

					tx = session.beginTransaction();
					if ( delete )
					  session.delete( beanListOptionMenu );
					else
					  {
						beanListOptionMenu.setActive("N");
					    session.update( beanListOptionMenu );
					  }
					
					tx.commit();
					
				    int id_option = LocalUtils.getCurrentOption( request );
				    int id_menu = LocalUtils.putMenuInSession( request, id_option, 0 );
					
					forward = mapping.findForward("loop");
				  }
				else if(listEditForm.getProcess().equals("updateList"))
				  {
				    //Busca listas con ese nombre
					  query =  " FROM BeanList u" +
							   " WHERE u.id_company = " + beanUser.getId_company().getId() +  
							   " AND u.description = '" + listEditForm.getListDescription() + "'" +  
							   " AND u.active = 'Y' ";  

					List list = session.createQuery( query ).list();
					
					if ( list.size() == 0 )
					  {
						BeanList bean = (BeanList) session.load( BeanList.class, new Integer( listEditForm.getId_list() ));

					  	tx = session.beginTransaction();
						
						bean.setDescription( listEditForm.getListDescription() );
					
						session.update( bean );
						tx.commit();

					    int id_option = LocalUtils.getCurrentOption(request );
					    int id_list = LocalUtils.putListInSession( request, bean.getId() );
					    LocalUtils.putOptionInSession( request, id_list, id_option );
					    int id_menu = LocalUtils.putMenuInSession( request, id_option, 0 );

						forward = mapping.findForward("loop");
					  }
					else
					  {
						com.bituos.Error e = new com.bituos.Error("Existe una lista con ese nombre.", request);
						httpSession.setAttribute("done", "javascript:history.back();" );
						
						forward = mapping.findForward("error");
					  }
				  }
				else if(listEditForm.getProcess().equals("updateOption"))
				  {
					BeanListOptions bean = (BeanListOptions) session.load( BeanListOptions.class, new Integer( listEditForm.getId_option() ));

				  	tx = session.beginTransaction();
					
					bean.setPrice( new Double( listEditForm.getOptionPrice() ).doubleValue() );
				
					session.update( bean );
					tx.commit();

					forward = mapping.findForward("loop");
				  }
				else if(listEditForm.getProcess().equals("updateMenu"))
				  {
				    BeanListOptionMenu bean = (BeanListOptionMenu) session.load( BeanListOptionMenu.class, new Integer( listEditForm.getId_menu() ));

				  	tx = session.beginTransaction();
					
					bean.setName( listEditForm.getMenuName() );
					bean.setDescription( listEditForm.getMenuDescription() );
					bean.setPrice1( new Double( listEditForm.getPrice1() ).doubleValue() );
					bean.setPrice2( new Double( listEditForm.getPrice2() ).doubleValue() );
					bean.setPrice3( new Double( listEditForm.getPrice3() ).doubleValue() );
					bean.setPrice4( new Double( listEditForm.getPrice4() ).doubleValue() );
					bean.setPrice5( new Double( listEditForm.getPrice5() ).doubleValue() );
					bean.setPrice6( new Double( listEditForm.getPrice6() ).doubleValue() );
					bean.setPrice7( new Double( listEditForm.getPrice7() ).doubleValue() );
					
					session.update( bean );
					tx.commit();

				    int id_list = LocalUtils.getCurrentList( request );
			        int id_option = LocalUtils.getCurrentOption( request );
				    int id_menu = LocalUtils.putMenuInSession( request, id_option, bean.getId() );

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

