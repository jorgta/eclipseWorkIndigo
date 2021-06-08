package com.bituos.lca.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.bituos.lca.Beans.*;
import com.bituos.lca.forms.*;

/**
 * @version 	1.0
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 */

import java.util.*;

import com.bituos.lca.*;
import com.bituos.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import javax.servlet.http.*;
import java.io.*;

public class CompanyChangeAction extends Action {

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		CompanyChangeForm companyChangeForm = (CompanyChangeForm) form;

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
				
					theFile = companyChangeForm.getImage();
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
						BeanCompany bean = (BeanCompany) session.load( BeanCompany.class, new Integer( companyChangeForm.getId() ) );
	                
						tx = session.beginTransaction();
						bean.setFull_name( companyChangeForm.getFullname() );
						bean.setAddress( companyChangeForm.getAddress() );
						bean.setRfc( companyChangeForm.getRfc() );
						bean.setLogo_file_name( wwwPath + "/company_logo_" + bean.getId() );
					
						bean.setSmtp_server( companyChangeForm.getSmtp_server() );
						bean.setSmtp_user_name( companyChangeForm.getSmtp_user_name() );
						bean.setSmtp_password( companyChangeForm.getSmtp_password() );
						bean.setSmtp_email( companyChangeForm.getSmtp_email() );

					    if ( companyChangeForm.getSmtp_is_secure().equals("on") )
					    	bean.setSmtp_is_secure("Y");
					    else
					    	bean.setSmtp_is_secure("N");

						bean.setSmtp_port(companyChangeForm.getSmtp_port());
						
						bean.setWeb_site(companyChangeForm.getWeb_site());
						
						
					  
					    
					    session.save( bean );

						bean.setLogo_file_name( wwwPath + "/company_logo_" + bean.getId() + "_" + fileName );
						session.update( bean );

						File dir = new File( PATH_SERVER );
						dir.mkdirs();
	
						FileOutputStream out = new FileOutputStream(PATH_SERVER +  "/company_logo_" + bean.getId() + "_" + fileName );
						out.write( theFile.getFileData() );
						out.close();

						tx.commit();

						httpSession.setAttribute("done", new String("/truckAdmin/company/companySelect.jsp"));
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
