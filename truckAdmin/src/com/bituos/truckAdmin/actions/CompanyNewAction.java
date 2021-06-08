package com.bituos.truckAdmin.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.bituos.truckAdmin.Beans.*;
import com.bituos.truckAdmin.forms.*;

/**
 * @version 	1.0
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 */

import java.util.*;

import com.bituos.truckAdmin.*;
import com.bituos.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import javax.servlet.http.*;
import java.io.*;


public class CompanyNewAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		CompanyNewForm companyNewForm = (CompanyNewForm) form;

		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("done");
									
		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
		  forward = mapping.findForward("login");
		else
		  {
			Config config = new Config( request );
		
			SessionFactory sessionFactory = null;
			Session    session = null;
			Transaction tx = null;

			try {
				sessionFactory = config.getConfiguration(request).buildSessionFactory();
				session = sessionFactory.openSession();
			
				String query =   " FROM BeanCompany u" 
							   + " WHERE u.name = '" + companyNewForm.getName() + "'";  
				
				java.util.List list = session.createQuery(query).list();
				
				if ( !list.isEmpty() )
					{
					  httpSession.setAttribute("done", "company/companyNew.jsp" );
					  com.bituos.Error error = new com.bituos.Error("Esa empresa ya existe, debe seleccionar otro nombre.", request);
					  forward = mapping.findForward("error");
					}
				else
				  {   
					BeanControlPanel control_panel = (BeanControlPanel) session.load( BeanControlPanel.class, new Integer( 1 ) );

					////////logo for the company///////
					//String PATH_SERVER = "/Tomcat/webapps/files/uploadtruckAdmin/";

					//String PATH_SERVER = "/eclipseWork/files/WebContent/uploadtruckAdmin";
					//String wwwPath = "/files/uploadtruckAdmin/" ;

					///////////////////////////////////////////////////
					//PATH PARA GUARDAR LOS ARCHIVOS
					///////////////////////////////////////////////////
					String PATH_SERVER = control_panel.getPath_server();
					String wwwPath = control_panel.getPath_www();
					
					int MAX_SIZE = 50 * 1024; //50 kb
				
			
					FormFile theFile;
					String contentType;
					String fileName;
					int fileSize;
					byte[] fileData;
				
					theFile = companyNewForm.getImage();
					contentType = theFile.getContentType();
					fileName = theFile.getFileName();
					fileSize = theFile.getFileSize();
					fileData = theFile.getFileData();

					if ( fileSize > MAX_SIZE)
					  {
						httpSession.setAttribute("done", "javascript:history.back();" );
						com.bituos.Error error = new com.bituos.Error("El tamanno del archivo no debe sobrepasar los " + MAX_SIZE + " Bytes (" + MAX_SIZE/1024 + " KBytes).", request);
						forward = mapping.findForward("error");
					  }
					else
					  {					
						BeanCompany bean = new BeanCompany();
				  	
						tx = session.beginTransaction();
						bean.setName( companyNewForm.getName() );
						bean.setFull_name( companyNewForm.getFullname() );
						bean.setRfc( companyNewForm.getRfc() );
						bean.setAddress( companyNewForm.getAddress() );
						bean.setActive( "Y" );
						bean.setLogo_file_name( wwwPath + "company_logo_" + bean.getId() );
				
						bean.setSmtp_server( companyNewForm.getSmtp_server() );
						bean.setSmtp_user_name( companyNewForm.getSmtp_user_name() );
						bean.setSmtp_password( companyNewForm.getSmtp_password() );
						bean.setSmtp_email( companyNewForm.getSmtp_email() );
						

					    if ( companyNewForm.getSmtp_is_secure().equals("on") )
					    	bean.setSmtp_is_secure("Y");
					    else
					    	bean.setSmtp_is_secure("N");
					    
					    bean.setSmtp_port(companyNewForm.getSmtp_port());
						
					    bean.setWeb_site(companyNewForm.getWeb_site());
					    
					    bean.setAdditional1( companyNewForm.getAdditional1() );
					    bean.setAdditional2( companyNewForm.getAdditional2() );
					    bean.setAdditional3( companyNewForm.getAdditional3() );
					    bean.setAdditional4( companyNewForm.getAdditional4() );
					    bean.setAdditional5( companyNewForm.getAdditional5() );
					    bean.setAdditional6( companyNewForm.getAdditional6() );
					    bean.setAdditional7( companyNewForm.getAdditional7() );
					    bean.setAdditional8( companyNewForm.getAdditional8() );
					    bean.setAdditional9( companyNewForm.getAdditional9() );
					    bean.setAdditional10( companyNewForm.getAdditional10() );

					    bean.setContractSigner( companyNewForm.getContractSigner() );
					    bean.setPhones( companyNewForm.getPhones() );
					    bean.setStreetContract( companyNewForm.getStreetContract() );
					    bean.setColonyContract( companyNewForm.getColonyContract() );
					    bean.setCityContract( companyNewForm.getCityContract() );
					    bean.setStateContract( companyNewForm.getStateContract() );

					    bean.setNameRptQuote( companyNewForm.getNameRptQuote() );
					    bean.setNameRptSale( companyNewForm.getNameRptSale() );
					    bean.setNameRptContract( companyNewForm.getNameRptContract() );
					    
					    
						session.save( bean );

						bean.setLogo_file_name( wwwPath + "company_logo_" + bean.getId() + "_" + fileName );
						session.update( bean );

						File dir = new File( PATH_SERVER );
						dir.mkdirs();
	
						FileOutputStream out = new FileOutputStream(PATH_SERVER +  "company_logo_" + bean.getId() + "_" + fileName );
						out.write( theFile.getFileData() );
						out.close();
	
						tx.commit();
					
						forward = mapping.findForward("ok");
						httpSession.setAttribute("done", "company/companyNew.jsp" );
					  }

				  }
				
				// do something here

			} catch (Exception e) {

				e.printStackTrace();
                
				// Report the error using the appropriate name and ID.
				com.bituos.Error error = new com.bituos.Error( e.getMessage(), request);
				errors.add("name", new ActionError("id"));

			}
			finally	{
				if ( session != null )
					 session.close(); 
				  
				if ( sessionFactory != null )
					 sessionFactory.close();
			}

		  }          

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);

			// Forward control to the appropriate 'failure' URI (change name as desired)
			//	forward = mapping.findForward("failure");

		} else {

			// Forward control to the appropriate 'success' URI (change name as desired)
			// forward = mapping.findForward("success");

		}

		// Finish with
		return (forward);

	}
}
