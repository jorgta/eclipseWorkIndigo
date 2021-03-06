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

import com.eventAdmin.forms.ListSaloonEditForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class ListSaloonEditAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//Recibe  valores del  jsp y se los pasa al a un objeto
		ListSaloonEditForm listSaloonEditForm = (ListSaloonEditForm) form;

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
				
				if ( listSaloonEditForm.getProcess().equals("selectSaloon") )
				  {
					BeanSaloon bean = (BeanSaloon) session.load( BeanSaloon.class, new Integer( listSaloonEditForm.getId_saloon() ));

				  	listSaloonEditForm.setDescription( bean.getDescription() );
				  	listSaloonEditForm.setMin( new Integer( bean.getMin_occupancy() ).toString() );
				  	listSaloonEditForm.setMax( new Integer( bean.getMax_occupancy() ).toString() );
					
					if ( bean.getH0().equals("Y") )
						listSaloonEditForm.setH0( "on" );
					else
						listSaloonEditForm.setH0( "off" );

					if ( bean.getH1().equals("Y") )
						listSaloonEditForm.setH1( "on" );
					else
						listSaloonEditForm.setH1( "off" );

					
					if ( bean.getH2().equals("Y") )
						listSaloonEditForm.setH2( "on" );
					else
						listSaloonEditForm.setH2( "off" );

					
					if ( bean.getH3().equals("Y") )
						listSaloonEditForm.setH3( "on" );
					else
						listSaloonEditForm.setH3( "off" );

					
					if ( bean.getH4().equals("Y") )
						listSaloonEditForm.setH4( "on" );
					else
						listSaloonEditForm.setH4( "off" );

					
					if ( bean.getH4().equals("Y") )
						listSaloonEditForm.setH4( "on" );
					else
						listSaloonEditForm.setH4( "off" );

					
					if ( bean.getH5().equals("Y") )
						listSaloonEditForm.setH5( "on" );
					else
						listSaloonEditForm.setH5( "off" );

					
					if ( bean.getH6().equals("Y") )
						listSaloonEditForm.setH6( "on" );
					else
						listSaloonEditForm.setH6( "off" );

					
					if ( bean.getH7().equals("Y") )
						listSaloonEditForm.setH7( "on" );
					else
						listSaloonEditForm.setH7( "off" );

					
					if ( bean.getH8().equals("Y") )
						listSaloonEditForm.setH8( "on" );
					else
						listSaloonEditForm.setH8( "off" );

					
					if ( bean.getH9().equals("Y") )
						listSaloonEditForm.setH9( "on" );
					else
						listSaloonEditForm.setH9( "off" );

					
					if ( bean.getH10().equals("Y") )
						listSaloonEditForm.setH10( "on" );
					else
						listSaloonEditForm.setH10( "off" );

					
					if ( bean.getH11().equals("Y") )
						listSaloonEditForm.setH11( "on" );
					else
						listSaloonEditForm.setH11( "off" );

					
					if ( bean.getH12().equals("Y") )
						listSaloonEditForm.setH12( "on" );
					else
						listSaloonEditForm.setH12( "off" );

					
					if ( bean.getH13().equals("Y") )
						listSaloonEditForm.setH13( "on" );
					else
						listSaloonEditForm.setH13( "off" );

					
					if ( bean.getH14().equals("Y") )
						listSaloonEditForm.setH14( "on" );
					else
						listSaloonEditForm.setH14( "off" );

					
					if ( bean.getH15().equals("Y") )
						listSaloonEditForm.setH15( "on" );
					else
						listSaloonEditForm.setH15( "off" );

					
					if ( bean.getH16().equals("Y") )
						listSaloonEditForm.setH16( "on" );
					else
						listSaloonEditForm.setH16( "off" );

					
					if ( bean.getH17().equals("Y") )
						listSaloonEditForm.setH17( "on" );
					else
						listSaloonEditForm.setH17( "off" );

					
					if ( bean.getH18().equals("Y") )
						listSaloonEditForm.setH18( "on" );
					else
						listSaloonEditForm.setH18( "off" );

					
					if ( bean.getH19().equals("Y") )
						listSaloonEditForm.setH19( "on" );
					else
						listSaloonEditForm.setH19( "off" );

					
					if ( bean.getH20().equals("Y") )
						listSaloonEditForm.setH20( "on" );
					else
						listSaloonEditForm.setH20( "off" );

					
					if ( bean.getH21().equals("Y") )
						listSaloonEditForm.setH21( "on" );
					else
						listSaloonEditForm.setH21( "off" );

					
					if ( bean.getH22().equals("Y") )
						listSaloonEditForm.setH22( "on" );
					else
						listSaloonEditForm.setH22( "off" );

					if ( bean.getH23().equals("Y") )
						listSaloonEditForm.setH23( "on" );
					else
						listSaloonEditForm.setH23( "off" );
				
					forward = mapping.findForward("loop");
				  }
				else if ( listSaloonEditForm.getProcess().equals("createSaloon") )
				  {
				    //Busca listas con ese nombre
					  query =  " FROM BeanSaloon u" +
							   " WHERE u.id_company = " + beanUser.getId_company().getId() +  
							   " AND u.description = '" + listSaloonEditForm.getDescription() + "'" +  
							   " AND u.active = 'Y' ";  

					List list = session.createQuery( query ).list();
					
					if ( list.size() == 0 )
					  {
					  	BeanSaloon bean = new BeanSaloon();

					  	tx = session.beginTransaction();
						
						bean.setId_company( beanUser.getId_company() );
						bean.setDescription( listSaloonEditForm.getDescription() );
						bean.setMin_occupancy( new Integer( listSaloonEditForm.getMin() ).intValue() );
						bean.setMax_occupancy( new Integer( listSaloonEditForm.getMax() ).intValue() );
						bean.setActive( "Y" );
					
						if ( listSaloonEditForm.getH0().equals("on") )
							bean.setH0( "Y" );
						else
							bean.setH0( "N" );

						if ( listSaloonEditForm.getH1().equals("on") )
							bean.setH1( "Y" );
						else
							bean.setH1( "N" );

						
						if ( listSaloonEditForm.getH2().equals("on") )
							bean.setH2( "Y" );
						else
							bean.setH2( "N" );

						
						if ( listSaloonEditForm.getH3().equals("on") )
							bean.setH3( "Y" );
						else
							bean.setH3( "N" );

						
						if ( listSaloonEditForm.getH4().equals("on") )
							bean.setH4( "Y" );
						else
							bean.setH4( "N" );

						
						if ( listSaloonEditForm.getH4().equals("on") )
							bean.setH4( "Y" );
						else
							bean.setH4( "N" );

						
						if ( listSaloonEditForm.getH5().equals("on") )
							bean.setH5( "Y" );
						else
							bean.setH5( "N" );

						
						if ( listSaloonEditForm.getH6().equals("on") )
							bean.setH6( "Y" );
						else
							bean.setH6( "N" );

						
						if ( listSaloonEditForm.getH7().equals("on") )
							bean.setH7( "Y" );
						else
							bean.setH7( "N" );

						
						if ( listSaloonEditForm.getH8().equals("on") )
							bean.setH8( "Y" );
						else
							bean.setH8( "N" );

						
						if ( listSaloonEditForm.getH9().equals("on") )
							bean.setH9( "Y" );
						else
							bean.setH9( "N" );

						
						if ( listSaloonEditForm.getH10().equals("on") )
							bean.setH10( "Y" );
						else
							bean.setH10( "N" );

						
						if ( listSaloonEditForm.getH11().equals("on") )
							bean.setH11( "Y" );
						else
							bean.setH11( "N" );

						
						if ( listSaloonEditForm.getH12().equals("on") )
							bean.setH12( "Y" );
						else
							bean.setH12( "N" );

						
						if ( listSaloonEditForm.getH13().equals("on") )
							bean.setH13( "Y" );
						else
							bean.setH13( "N" );

						
						if ( listSaloonEditForm.getH14().equals("on") )
							bean.setH14( "Y" );
						else
							bean.setH14( "N" );

						
						if ( listSaloonEditForm.getH15().equals("on") )
							bean.setH15( "Y" );
						else
							bean.setH15( "N" );

						
						if ( listSaloonEditForm.getH16().equals("on") )
							bean.setH16( "Y" );
						else
							bean.setH16( "N" );

						
						if ( listSaloonEditForm.getH17().equals("on") )
							bean.setH17( "Y" );
						else
							bean.setH17( "N" );

						
						if ( listSaloonEditForm.getH18().equals("on") )
							bean.setH18( "Y" );
						else
							bean.setH18( "N" );

						if ( listSaloonEditForm.getH19().equals("on") )
							bean.setH19( "Y" );
						else
							bean.setH19( "N" );
						
						if ( listSaloonEditForm.getH20().equals("on") )
							bean.setH20( "Y" );
						else
							bean.setH20( "N" );

						
						if ( listSaloonEditForm.getH21().equals("on") )
							bean.setH21( "Y" );
						else
							bean.setH21( "N" );

						
						if ( listSaloonEditForm.getH22().equals("on") )
							bean.setH22( "Y" );
						else
							bean.setH22( "N" );

						if ( listSaloonEditForm.getH23().equals("on") )
							bean.setH23( "Y" );
						else
							bean.setH23( "N" );

						session.save( bean );
						tx.commit();

					    int id_saloon = LocalUtils.putSaloonInSession( request, bean.getId() );

						forward = mapping.findForward("loop");
					  }
					else
					  {
						com.bituos.Error e = new com.bituos.Error("Existe una lista con ese nombre.", request);
						httpSession.setAttribute("done", "javascript:history.back();" );
						
						forward = mapping.findForward("error");
					  }
				  }
				else if(listSaloonEditForm.getProcess().equals("deleteSaloon"))
				  {
					BeanSaloon beanSaloon = (BeanSaloon) session.load( BeanSaloon.class, new Integer(listSaloonEditForm.getId_saloon()));
					boolean delete = true;

					//list in quote
					query = " FROM BeanQuote u" +
							" WHERE u.id_saloon =" + listSaloonEditForm.getId_saloon();
					
					List list = session.createQuery( query ).list();

					if ( list.size() > 0 )
					  delete = false;

					//list in sale
					query = " FROM BeanSale u" +
							" WHERE u.id_saloon =" + listSaloonEditForm.getId_saloon();
			
					list = session.createQuery( query ).list();
			
					if ( list.size() > 0 )
					  delete = false;

					tx = session.beginTransaction();
					if ( delete )
					  session.delete( beanSaloon );
					else
					  {
						beanSaloon.setActive("N");
					    session.update( beanSaloon );
					  }
					
					tx.commit();
					
				    int id_saloon = LocalUtils.putSaloonInSession( request, 0 );
					
					forward = mapping.findForward("loop");
				  }
				else if(listSaloonEditForm.getProcess().equals("updateSaloon"))
				  {
					BeanSaloon bean = (BeanSaloon) session.load( BeanSaloon.class, new Integer( listSaloonEditForm.getId_saloon() ));

				  	tx = session.beginTransaction();
					
					bean.setDescription( listSaloonEditForm.getDescription() );

					bean.setMin_occupancy( new Integer( listSaloonEditForm.getMin() ).intValue() );
					bean.setMax_occupancy( new Integer( listSaloonEditForm.getMax() ).intValue() );
					
					if ( listSaloonEditForm.getH0().equals("on") )
						bean.setH0( "Y" );
					else
						bean.setH0( "N" );

					if ( listSaloonEditForm.getH1().equals("on") )
						bean.setH1( "Y" );
					else
						bean.setH1( "N" );

					
					if ( listSaloonEditForm.getH2().equals("on") )
						bean.setH2( "Y" );
					else
						bean.setH2( "N" );

					
					if ( listSaloonEditForm.getH3().equals("on") )
						bean.setH3( "Y" );
					else
						bean.setH3( "N" );

					
					if ( listSaloonEditForm.getH4().equals("on") )
						bean.setH4( "Y" );
					else
						bean.setH4( "N" );

					
					if ( listSaloonEditForm.getH4().equals("on") )
						bean.setH4( "Y" );
					else
						bean.setH4( "N" );

					
					if ( listSaloonEditForm.getH5().equals("on") )
						bean.setH5( "Y" );
					else
						bean.setH5( "N" );

					
					if ( listSaloonEditForm.getH6().equals("on") )
						bean.setH6( "Y" );
					else
						bean.setH6( "N" );

					
					if ( listSaloonEditForm.getH7().equals("on") )
						bean.setH7( "Y" );
					else
						bean.setH7( "N" );

					
					if ( listSaloonEditForm.getH8().equals("on") )
						bean.setH8( "Y" );
					else
						bean.setH8( "N" );

					
					if ( listSaloonEditForm.getH9().equals("on") )
						bean.setH9( "Y" );
					else
						bean.setH9( "N" );

					
					if ( listSaloonEditForm.getH10().equals("on") )
						bean.setH10( "Y" );
					else
						bean.setH10( "N" );

					
					if ( listSaloonEditForm.getH11().equals("on") )
						bean.setH11( "Y" );
					else
						bean.setH11( "N" );

					
					if ( listSaloonEditForm.getH12().equals("on") )
						bean.setH12( "Y" );
					else
						bean.setH12( "N" );

					
					if ( listSaloonEditForm.getH13().equals("on") )
						bean.setH13( "Y" );
					else
						bean.setH13( "N" );

					
					if ( listSaloonEditForm.getH14().equals("on") )
						bean.setH14( "Y" );
					else
						bean.setH14( "N" );

					
					if ( listSaloonEditForm.getH15().equals("on") )
						bean.setH15( "Y" );
					else
						bean.setH15( "N" );

					
					if ( listSaloonEditForm.getH16().equals("on") )
						bean.setH16( "Y" );
					else
						bean.setH16( "N" );

					
					if ( listSaloonEditForm.getH17().equals("on") )
						bean.setH17( "Y" );
					else
						bean.setH17( "N" );

					
					if ( listSaloonEditForm.getH18().equals("on") )
						bean.setH18( "Y" );
					else
						bean.setH18( "N" );

					
					if ( listSaloonEditForm.getH19().equals("on") )
						bean.setH19( "Y" );
					else
						bean.setH19( "N" );

					
					if ( listSaloonEditForm.getH20().equals("on") )
						bean.setH20( "Y" );
					else
						bean.setH20( "N" );

					
					if ( listSaloonEditForm.getH21().equals("on") )
						bean.setH21( "Y" );
					else
						bean.setH21( "N" );

					
					if ( listSaloonEditForm.getH22().equals("on") )
						bean.setH22( "Y" );
					else
						bean.setH22( "N" );

					if ( listSaloonEditForm.getH23().equals("on") )
						bean.setH23( "Y" );
					else
						bean.setH23( "N" );
				
					session.update( bean );
					tx.commit();

				    int id_saloon = LocalUtils.putSaloonInSession( request, bean.getId() );

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

