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

import com.eventAdmin.forms.SaleForm;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */
public class SaleAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();

		//Recibe  valores del  jsp y se los pasa al a un objeto
		SaleForm saleForm = (SaleForm) form;

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
				int id_sale = 0;
				
				if( saleForm.getProcess().equals("signContract") )
				  {
					boolean showError = false;

					if ( saleForm.getId_sale() == null )
					    {
							com.bituos.Error e = new com.bituos.Error("Debe especificar un numero de venta valido.", request);
							showError = true;
					    }

					if ( !showError)
						try
						  {
							id_sale = new Integer( saleForm.getId_sale().trim() ).intValue();
						  }
						catch (Exception m)
						  {
							com.bituos.Error e = new com.bituos.Error("Debe especificar un numero de venta valido.", request);
							showError = true;
						  }
					
					
					if ( !showError)
					  {
						query = " FROM BeanSale u" +
								" WHERE u.id_company = " + beanUser.getId_company().getId() +
						  		" AND u.id_intern =" + id_sale;
						
						List list = session.createQuery( query ).list();
		
						if ( list.size() == 0 )
						    {
								com.bituos.Error e = new com.bituos.Error("No existe venta con ese numero.", request);
								showError = true;
						    }
						
						if ( !showError)
						  {
							//verificar sino esta firmado
							query = " FROM BeanSale u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId() +
							  		" AND u.id_intern =" +  saleForm.getId_sale() +
									" AND u.contract_date_reg IS NOT NULL";
							
							list = session.createQuery( query ).list();
							
							if ( list.size() > 0 )
							    {
									com.bituos.Error e = new com.bituos.Error("Para este evento ya fue firmado el contrato.", request);
									showError = true;
							    }
						  }
					  }
					
					if ( !showError)
					  {
						BeanSale beanSale = (BeanSale) session.load( BeanSale.class, new Integer(id_sale) );

						beanSale.setContract_date_reg( Utils.Today() );
						
					  	tx = session.beginTransaction();

					  	session.save( beanSale );
						
						tx.commit();

					  
						//envio de email
						Aes aes = new Aes();
						String encID = Aes.encrypt( new Integer( beanSale.getId()).toString() ); 
						BeanControlPanel beanControlPanel = (BeanControlPanel) session.load( BeanControlPanel.class, new Integer(1));
						
						try
						  {
							if ( Utils.isEmail( beanSale.getId_client().getEmail() ))
								new com.bituos.Mail( beanSale.getId_client().getEmail()
										, "Hola\n\n" + 
										  "Se ha formalizado la firma de la venta " + id_sale + " a su nombre." + "\n\n" +
										  "La venta solicitada la podra ver dando click en el siguiente enlace:" + "\n\n" +
										  "http://" + beanControlPanel.getHosting_server_name() + "/eventAdmin/preWV.do?process=showSale&nip=" + encID + "\n\n" +
										  beanUser.getId_company().getFull_name()
										, "Firma de venta " + beanSale.getId_intern() + " de Evento de " + beanUser.getId_company().getFull_name()
										, request);
							
							if ( Utils.isEmail( beanSale.getAnotherEmail() ))
								new com.bituos.Mail( saleForm.getAnotherEmail()
										, "Hola\n\n" + 
										  "Se ha formalizado la firma de la venta " + id_sale + " a su nombre." + "\n\n" +
										  "La venta solicitada la podra ver dando click en el siguiente enlace:" + "\n\n" +
										  "http://" + beanControlPanel.getHosting_server_name() + "/eventAdmin/preWV.do?process=showSale&nip=" + encID + "\n\n" +
										  beanUser.getId_company().getFull_name()
										, "Venta " + beanSale.getId_intern() + " de Evento de " + beanUser.getId_company().getFull_name()
										, request);

							if ( Utils.isEmail( saleForm.getAnotherEmail() ))
								new com.bituos.Mail( saleForm.getAnotherEmail()
										, "Hola\n\n" + 
										  "Se ha formalizado la firma de la venta " + id_sale + " a su nombre." + "\n\n" +
										  "La venta solicitada la podra ver dando click en el siguiente enlace:" + "\n\n" +
										  "http://" + beanControlPanel.getHosting_server_name() + "/eventAdmin/preWV.do?process=showSale&nip=" + encID + "\n\n" +
										  beanUser.getId_company().getFull_name()
										, "Venta " + beanSale.getId_intern() + " de Evento de " + beanUser.getId_company().getFull_name()
										, request);
						  }
						catch (Exception m)
						  {
							
						  }
						
						//httpSession.setAttribute( "crystal_rptname", new String("rptContract.rpt") ) ;
						httpSession.setAttribute( "crystal_rptname", beanUser.getId_company().getNameRptContract() ) ;
						httpSession.setAttribute( "parameterCount", new Integer(1) ) ;

						//id quote
						httpSession.setAttribute( "parameter1", new Integer( id_sale ) );
						httpSession.setAttribute( "parameterType1", new String("Integer") ) ;
						httpSession.setAttribute( "parameterName1", new String("id_sale") ) ;
						
					    forward = mapping.findForward("printGlobal");
					  }
					else
					  forward = mapping.findForward("error");
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

