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
import com.struts2.forms.ListFlowerMusicEditForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class ListFlowerMusicEditAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//Recibe  valores del  jsp y se los pasa al a un objeto
		ListFlowerMusicEditForm listFlowerMusicEditForm = (ListFlowerMusicEditForm) form;

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

				String error = new String();

				/*caseNewForm.getProcess().equals()
				    obtiene  el valor de la variable process y compara con una cadena
				*/

				//si el process = "validate"
				
				String query = "nothing";
				
				if ( listFlowerMusicEditForm.getProcess().equals("selectFlower") )
				  {
					BeanFlower bean = (BeanFlower) session.load( BeanFlower.class, new Integer(listFlowerMusicEditForm.getId_flower()));

					listFlowerMusicEditForm.setFlowerDescription( bean.getDescription() );
					listFlowerMusicEditForm.setFlowerPrice( new Double( bean.getPrice()).toString() );
				    
				    if ( bean.getPerPerson().equals("Y") )
				    	listFlowerMusicEditForm.setFlowerPerPerson("on");
				    else
				    	listFlowerMusicEditForm.setFlowerPerPerson("off");
					
					forward = mapping.findForward("loop");
				  }
				else if ( listFlowerMusicEditForm.getProcess().equals("createFlower") )
				  {
				    //Busca listas con ese nombre
					  query =  " FROM BeanFlower u" +
							   " WHERE u.id_company = " + beanUser.getId_company().getId() +  
							   " AND u.description = '" + listFlowerMusicEditForm.getFlowerDescription() + "'" +  
							   " AND u.active = 'Y' ";  

					List list = session.createQuery( query ).list();
					
					if ( list.size() == 0 )
					  {
					  	BeanFlower bean = new BeanFlower();

					  	tx = session.beginTransaction();
						
						bean.setId_company( beanUser.getId_company() );
						bean.setDescription( listFlowerMusicEditForm.getFlowerDescription() );
						bean.setPrice( new Double( listFlowerMusicEditForm.getFlowerPrice() ).doubleValue() );
						
						if ( listFlowerMusicEditForm.getFlowerPerPerson().equals("on") )
							bean.setPerPerson( "Y" );
						else
							bean.setPerPerson( "N" );

						bean.setActive( "Y" );
					
						session.save( bean );
						tx.commit();

					    int id_flower = LocalUtils.putFlowerInSession( request, bean.getId() );
					    
					    if ( listFlowerMusicEditForm.getId_music() == null )
					      LocalUtils.putMusicInSession( request, 0 );
					    else
						  LocalUtils.putMusicInSession( request, new Integer( listFlowerMusicEditForm.getId_music() ).intValue() );

						forward = mapping.findForward("loop");
					  }
					else
					  {
						com.bituos.Error e = new com.bituos.Error("Existe una paquete de flores con esa descripcion.", request);
						httpSession.setAttribute("done", "javascript:history.back();" );
						
						forward = mapping.findForward("error");
					  }
				  }
				else if(listFlowerMusicEditForm.getProcess().equals("deleteFlower"))
				  {
					BeanFlower beanFlower = (BeanFlower) session.load( BeanFlower.class, new Integer(listFlowerMusicEditForm.getId_flower()));
					boolean delete = true;

					//used in quote
					query = " FROM BeanQuoteFlower u" +
							" WHERE u.id_flower =" + listFlowerMusicEditForm.getId_flower();
					
					List list = session.createQuery( query ).list();

					if ( list.size() > 0 )
					  delete = false;

					//used in sale
					query = " FROM BeanSaleFlower u" +
							" WHERE u.id_flower =" + listFlowerMusicEditForm.getId_flower();
			
					list = session.createQuery( query ).list();
			
					if ( list.size() > 0 )
					  delete = false;

					tx = session.beginTransaction();
					if ( delete )
					  session.delete( beanFlower );
					else
					  {
						beanFlower.setActive("N");
					    session.update( beanFlower );
					  }
					
					tx.commit();
					
				    int id_flower = LocalUtils.putFlowerInSession( request, 0 );
				    
				    if ( listFlowerMusicEditForm.getId_music() == null )
					      LocalUtils.putMusicInSession( request, 0 );
				    else
					  LocalUtils.putMusicInSession( request, new Integer( listFlowerMusicEditForm.getId_music() ).intValue() );
					
					forward = mapping.findForward("loop");
				  }
				else if(listFlowerMusicEditForm.getProcess().equals("updateFlower"))
				  {
					BeanFlower bean = (BeanFlower) session.load( BeanFlower.class, new Integer( listFlowerMusicEditForm.getId_flower() ));

				  	tx = session.beginTransaction();
					
					bean.setDescription( listFlowerMusicEditForm.getFlowerDescription() );
					bean.setPrice( new Double( listFlowerMusicEditForm.getFlowerPrice() ).doubleValue() );

					if ( listFlowerMusicEditForm.getFlowerPerPerson().equals("on") )
						bean.setPerPerson( "Y" );
					else
						bean.setPerPerson( "N" );
				
					session.update( bean );
					tx.commit();

				    int id_flower = LocalUtils.putFlowerInSession( request, bean.getId() );
				    
				    if ( listFlowerMusicEditForm.getId_music() == null )
					      LocalUtils.putMusicInSession( request, 0 );
				    else
					  LocalUtils.putMusicInSession( request, new Integer( listFlowerMusicEditForm.getId_music() ).intValue() );

					forward = mapping.findForward("loop");
				  }
				else if ( listFlowerMusicEditForm.getProcess().equals("selectMusic") )
				  {
					BeanMusic bean = (BeanMusic) session.load( BeanMusic.class, new Integer(listFlowerMusicEditForm.getId_music()));

					listFlowerMusicEditForm.setMusicDescription( bean.getDescription() );
					listFlowerMusicEditForm.setMusicPrice( new Double( bean.getPrice()).toString() );
				    
				    if ( bean.getPerPerson().equals("Y") )
				    	listFlowerMusicEditForm.setMusicPerPerson("on");
				    else
				    	listFlowerMusicEditForm.setMusicPerPerson("off");
					
					forward = mapping.findForward("loop");
				  }
				else if(listFlowerMusicEditForm.getProcess().equals("createMusic"))
				  {
				    //Busca listas con ese nombre
					  query =  " FROM BeanMusic u" +
							   " WHERE u.id_company = " + beanUser.getId_company().getId() +  
							   " AND u.description = '" + listFlowerMusicEditForm.getMusicDescription() + "'" +  
							   " AND u.active = 'Y' ";  

					List list = session.createQuery( query ).list();
					
					if ( list.size() == 0 )
					  {
						BeanMusic bean = new BeanMusic();

					  	tx = session.beginTransaction();
						
						bean.setId_company( beanUser.getId_company() );
						bean.setDescription( listFlowerMusicEditForm.getMusicDescription() );
						bean.setPrice( new Double( listFlowerMusicEditForm.getMusicPrice() ).doubleValue() );
						
						if ( listFlowerMusicEditForm.getMusicPerPerson().equals("on") )
							bean.setPerPerson( "Y" );
						else
							bean.setPerPerson( "N" );

						bean.setActive( "Y" );
					
						session.save( bean );
						tx.commit();

					    int id_music = LocalUtils.putMusicInSession( request, bean.getId() );
					    
					    if ( listFlowerMusicEditForm.getId_flower() == null )
					      LocalUtils.putFlowerInSession( request, 0 );
					    else
						  LocalUtils.putFlowerInSession( request, new Integer( listFlowerMusicEditForm.getId_flower() ).intValue() );

						forward = mapping.findForward("loop");
					  }
					else
					  {
						com.bituos.Error e = new com.bituos.Error("Existe una paquete de musica con esa descripcion.", request);
						httpSession.setAttribute("done", "javascript:history.back();" );
						
						forward = mapping.findForward("error");
					  }
				  }
				else if(listFlowerMusicEditForm.getProcess().equals("deleteMusic"))
				  {
					BeanMusic beanMusic = (BeanMusic) session.load( BeanMusic.class, new Integer(listFlowerMusicEditForm.getId_music()));
					boolean delete = true;

					//used in quote
					query = " FROM BeanQuoteMusic u" +
							" WHERE u.id_music =" + listFlowerMusicEditForm.getId_music();
					
					List list = session.createQuery( query ).list();

					if ( list.size() > 0 )
					  delete = false;

					//used in sale
					query = " FROM BeanSaleMusic u" +
							" WHERE u.id_music =" + listFlowerMusicEditForm.getId_music();
			
					list = session.createQuery( query ).list();
			
					if ( list.size() > 0 )
					  delete = false;

					tx = session.beginTransaction();
					if ( delete )
					  session.delete( beanMusic );
					else
					  {
						beanMusic.setActive("N");
					    session.update( beanMusic );
					  }
					
					tx.commit();
					
				    int id_music = LocalUtils.putMusicInSession( request, 0 );
				    
				    if ( listFlowerMusicEditForm.getId_flower() == null )
					      LocalUtils.putFlowerInSession( request, 0 );
				    else
					  LocalUtils.putFlowerInSession( request, new Integer( listFlowerMusicEditForm.getId_flower() ).intValue() );
					
					forward = mapping.findForward("loop");
				  }
				else if(listFlowerMusicEditForm.getProcess().equals("updateMusic"))
				  {
					BeanMusic bean = (BeanMusic) session.load( BeanMusic.class, new Integer( listFlowerMusicEditForm.getId_music() ));

				  	tx = session.beginTransaction();
					
					bean.setDescription( listFlowerMusicEditForm.getMusicDescription() );
					bean.setPrice( new Double( listFlowerMusicEditForm.getMusicPrice() ).doubleValue() );

					if ( listFlowerMusicEditForm.getMusicPerPerson().equals("on") )
						bean.setPerPerson( "Y" );
					else
						bean.setPerPerson( "N" );
				
					session.update( bean );
					tx.commit();

				    int id_music = LocalUtils.putMusicInSession( request, bean.getId() );
				    
				    if ( listFlowerMusicEditForm.getId_flower() == null )
					      LocalUtils.putFlowerInSession( request, 0 );
				    else
					  LocalUtils.putFlowerInSession( request, new Integer( listFlowerMusicEditForm.getId_flower() ).intValue() );

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

