package com.eventAdmin.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.eventAdmin.Beans.*;
import com.eventAdmin.forms.*;

/**
 * @version 	1.0
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 */

import java.util.*;

import com.eventAdmin.*;
import com.bituos.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import javax.servlet.http.*;
import java.io.*;

public class CompanyChangeByUserAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		CompanyChangeByUserForm companyChangeByUserForm = (CompanyChangeByUserForm) form;

		HttpSession httpSession = request.getSession();
							
		Config config = new Config( request );
		
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;

		BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");

		if ( beanUser == null )
		  forward = mapping.findForward("login");
		else 
			{
				try
				  {
					sessionFactory = config.getConfiguration( request ).buildSessionFactory();
					session = sessionFactory.openSession();
	                
					BeanControlPanel control_panel = (BeanControlPanel) session.load( BeanControlPanel.class, new Integer( 1 ) );

					////////logo for the company///////
					//String PATH_SERVER = "/Tomcat/webapps/files/uploadeventAdmin/";

					//String PATH_SERVER = "/eclipseWork/files/WebContent/uploadEventAdmin";
					//String wwwPath = "/files/uploadEventAdmin/" ;

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
				
					theFile = companyChangeByUserForm.getImage();
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
						BeanCompany bean = (BeanCompany) session.load( BeanCompany.class, new Integer( companyChangeByUserForm.getId() ) );
	                
						tx = session.beginTransaction();
						bean.setFull_name( companyChangeByUserForm.getFullname() );
						bean.setAddress( companyChangeByUserForm.getAddress() );
						bean.setRfc( companyChangeByUserForm.getRfc() );
						bean.setLogo_file_name( wwwPath + "/company_logo_" + bean.getId() );
					
						bean.setSmtp_server( companyChangeByUserForm.getSmtp_server() );
						bean.setSmtp_user_name( companyChangeByUserForm.getSmtp_user_name() );
						bean.setSmtp_password( companyChangeByUserForm.getSmtp_password() );
						bean.setSmtp_email( companyChangeByUserForm.getSmtp_email() );
						bean.setSmtp_port(companyChangeByUserForm.getSmtp_port());
						
						bean.setWeb_site(companyChangeByUserForm.getWeb_site());
						
						bean.setAdditional1(companyChangeByUserForm.getAdditional1());
						bean.setAdditional2(companyChangeByUserForm.getAdditional2());
						bean.setAdditional3(companyChangeByUserForm.getAdditional3());
						bean.setAdditional4(companyChangeByUserForm.getAdditional4());
						bean.setAdditional5(companyChangeByUserForm.getAdditional5());
						bean.setAdditional6(companyChangeByUserForm.getAdditional6());
						bean.setAdditional7(companyChangeByUserForm.getAdditional7());
						bean.setAdditional8(companyChangeByUserForm.getAdditional8());
						bean.setAdditional9(companyChangeByUserForm.getAdditional9());
						bean.setAdditional10(companyChangeByUserForm.getAdditional10());
						
						
						bean.setContractSigner(companyChangeByUserForm.getContractSigner());
						bean.setPhones(companyChangeByUserForm.getPhones());
						bean.setStreetContract(companyChangeByUserForm.getStreetContract());
						bean.setColonyContract(companyChangeByUserForm.getColonyContract());
						bean.setCityContract(companyChangeByUserForm.getCityContract());
						bean.setStateContract(companyChangeByUserForm.getStateContract());
						
						
						

					    if ( companyChangeByUserForm.getSmtp_is_secure().equals("on") )
					    	bean.setSmtp_is_secure("Y");
					    else
					    	bean.setSmtp_is_secure("N");

					    session.save( bean );

						bean.setLogo_file_name( wwwPath + "/company_logo_" + bean.getId() + "_" + fileName );
						session.update( bean );

						File dir = new File( PATH_SERVER );
						dir.mkdirs();
	
						FileOutputStream out = new FileOutputStream(PATH_SERVER +  "/company_logo_" + bean.getId() + "_" + fileName );
						out.write( theFile.getFileData() );
						out.close();

						tx.commit();

						httpSession.setAttribute("done", new String("/eventAdmin/company/companyChangeByUser.jsp"));
						forward = mapping.findForward("ok");
					  }

				  }
				catch( Throwable  m)
				  {
					m.printStackTrace();
					
					com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
					
					forward = mapping.findForward("error");
				  }
				finally
				  {
					session.close();
					sessionFactory.close();
				  }
			}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.isEmpty()) {
			saveErrors(request, errors);
		}
		// Write logic determining how the user should be forwarded.

		// Finish with
		return (forward);

	}
}
