package com.struts2.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


/**
 * @version 	1.0
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 */

import java.util.*;

import com.eventAdmin.*;
import com.struts2.Beans.*;
import com.struts2.forms.*;
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
					////////logo for the company///////
					//String PATH_SERVER = "/Tomcat/webapps/files/uploadeventAdmin/";
					//String wwwPath = "/files/uploadeventAdmin/" ;
					String PATH_SERVER = "/eclipseWork/files/WebContent/uploadEventAdmin";
					String wwwPath = "/files/uploadEventAdmin/" ;

					int MAX_SIZE = 50 * 1024; //50 kb
				
					BeanControlPanel control_panel = (BeanControlPanel) session.load( BeanControlPanel.class, new Integer( 1 ) );
			
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
