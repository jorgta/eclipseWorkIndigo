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
	                
					////////logo for the company///////
//					String PATH_SERVER = "/Tomcat/webapps/files/uploadeventAdmin/";
//					String wwwPath = "/files/uploadeventAdmin/" ;
					String PATH_SERVER = "/eclipseWork/files/WebContent/uploadEventAdmin/";
					String wwwPath = "/files/uploadEventAdmin/" ;

					int MAX_SIZE = 50 * 1024; //50 kb
				
					BeanControlPanel control_panel = (BeanControlPanel) session.load( BeanControlPanel.class, new Integer( 1 ) );
			
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

						httpSession.setAttribute("done", new String("/eventAdmin/company/companySelect.jsp"));
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
