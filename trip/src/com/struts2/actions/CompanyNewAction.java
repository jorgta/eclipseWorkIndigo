package com.struts2.actions;




import java.util.*;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import java.io.IOException; 


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.ExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import com.struts2.Beans.*;
import com.bituos.*;

import org.hibernate.classic.Session;

/*
@Validations(requiredStrings = { 
	    @RequiredStringValidator(fieldName = "username", type = ValidatorType.FIELD, message = "Login User is required"), 
	    @RequiredStringValidator(fieldName = "password", type = ValidatorType.FIELD, message = "Password is required")
	}, expressions = {
	  @ExpressionValidator(expression = "company.trim().equals('bituos') == true", message = "Empresa debe ser bituos."),

	})*/
public class CompanyNewAction extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730294291529491868L;
	
	private String name;
	private String full_name;
	private String address;
	private String rfc;
	private String active;
	
	private String rootpassword;
	private String rootemail;
	private String rootrfc;
	private String rootpassword2;
	
	
	private File myFile;
    private String myFileContentType;
    private String myFileFileName;
    private String destPath;
	
	private HttpSession httpSession =null;
	private HttpServletRequest request = null;
	//private static final Log  log  = (Log) LogFactory.getLog(LoginAction.class);
	
	//@RequiredStringValidator(fieldName = "username", type = ValidatorType.FIELD, message = "Login User is required")
	 
	public String forward()
	{
		return ERROR;
	}
	
	
	public void validate() {	
        String fieldr="Campo requerido";
        String fieldnospaces ="El campo no tener espacion";
        String fieldinvalid ="Formato Invalido";
        String passwordcompare ="Los password no son iguales";
        HttpServletRequest request = ServletActionContext.getRequest();
        httpSession = request.getSession(); 
        httpSession.setAttribute("module","pre");
        httpSession =null;
    	request = null;
    	
		if ( name != null )
		  {
			name = name.trim();
			if (name.length() == 0)
				this.addFieldError("name",getText(fieldr));
			if (name.indexOf(" ") > 0 )
			this.addFieldError("name",getText(fieldnospaces));
		  }
		else
			this.addFieldError("name",getText(fieldr));
		
	
		if ( full_name != null )
		  {
			full_name = full_name.trim();
			if (full_name.length() == 0)
				this.addFieldError("full_name",getText(fieldr));
			if (full_name.indexOf(" ") > 0 )
			this.addFieldError("full_name",getText(fieldnospaces));
		  }
		else
			this.addFieldError("full_name",getText(fieldr));


		if ( address != null )
		  {
			address = address.trim();
			if (address.length() == 0)
				this.addFieldError("address",getText(fieldr));
			if (address.indexOf(" ") > 0 )
			this.addFieldError("address",getText(fieldnospaces));
		  }
		else
			this.addFieldError("address",getText(fieldr));
		
		if ( rfc != null )
		  {
			rfc = rfc.trim();
			if (rfc.length() == 0)
				this.addFieldError("rfc",getText(fieldr));
			if (rfc.indexOf(" ") > 0 )
			this.addFieldError("rfc",getText(fieldnospaces));
		  }
		else
			this.addFieldError("rfc",getText(fieldr));
		
		
		
		if ( rootpassword != null )
		  {
			rootpassword = rootpassword.trim();
			if (rootpassword.length() == 0)
				this.addFieldError("rootpassword",getText(fieldr));
			if (rootpassword.indexOf(" ") > 0 )
			this.addFieldError("rootpassword",getText(fieldnospaces));
		  }
		else
			this.addFieldError("rootpassword",getText(fieldr));
		
		
		if ( rootpassword2 != null )
		  {
			rootpassword2 = rootpassword2.trim();
			if (rootpassword2.length() == 0)
				this.addFieldError("rootpassword2",getText(fieldr));
			if (rootpassword2.indexOf(" ") > 0 )
			this.addFieldError("rootpassword2",getText(fieldnospaces));
		  }
		else
			this.addFieldError("rootpassword",getText(fieldr));
		
		
		if ( !rootpassword.equals(rootpassword2)  )
		  {
			this.addFieldError("rootpassword",getText(passwordcompare));
			this.addFieldError("rootpassword2",getText(passwordcompare));
		  }
		
		
		if ( rootemail != null )
		  {
			rootemail = rootemail.trim();
			if (rootemail.length() == 0)
				this.addFieldError("rootemail",getText(fieldr));
			if (rootemail.indexOf(" ") > 0 )
			this.addFieldError("rootemail",getText(fieldnospaces));
		  }
		else
			this.addFieldError("rootemail",getText(fieldr));
		
		
		if ( !Utils.isEmail( rootemail) )
			this.addFieldError("rootemail",getText(fieldinvalid));
		
 
       
		/*if ( !Utils.isEmail( rfc) )
			this.addFieldError("email",getText(fieldinvalid));*/
		
         
         //addActionMessage("Bienvenido " + name + "");
        
        /*if (active.equals("")) {
            this.addFieldError("address",getText("Company can't be blanked"));
            }*/
  
        
}

	 
	public String  execute() throws Exception  {
			//ActionErrors errors = new ActionErrors();
			//ActionForward forward = new ActionForward();
			 String forward=SUCCESS;
			// return value
	
			//LoginForm loginForm = (LoginForm) form;
	
		//	httpSession = request.getSession();
			HttpServletRequest request = ServletActionContext.getRequest();
			httpSession = request.getSession(); 
			 
			Config config = new Config( request );
			
			
			SessionFactory sessionFactory = null;
			
			Session    session = null;
			Transaction tx = null;
		
				
				
			try 
			  {

				                   
				sessionFactory = config.getConfiguration(request).buildSessionFactory();
				session = sessionFactory.openSession();	
				
				BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");
				if ( beanUser == null )
				{
					forward =  "logout";	
				}
				else
				{
					  
				
					String query =   " FROM BeanCompany u" 
								   + " WHERE u.name = '" +  getName() + "'";  
					
					java.util.List list = session.createQuery(query).list();
					
					if ( !list.isEmpty() )
						{
						  //httpSession.setAttribute("done", "company/companyNew.jsp" );
						  //com.bituos.Error error = new com.bituos.Error("Esa empresa ya existe, debe seleccionar otro nombre.", request);
						  httpSession.setAttribute("error", "Esa empresa ya existe, debe seleccionar otro nombre.");	
						  //forward = mapping.findForward("error");
						}
					else
					  {   
						////////logo for the company///////
						//String PATH_SERVER = "/Tomcat/webapps/files/uploadeventAdmin/";
						//String wwwPath = "/files/uploadeventAdmin/" ;
						BeanControlPanel control_panel = (BeanControlPanel) session.load( BeanControlPanel.class, new Integer( 1 ) );
						
						
						String PATH_SERVER = control_panel.getPath_server();
						String wwwPath = control_panel.getPath_www() ;
			            setDestPath(PATH_SERVER);
						int MAX_SIZE = 50 * 1024; //50 kb
					
						
						
						//FormFile theFile;
						String contentType;
						String fileName ="";
						double fileSize; 
						byte[] fileData;
					
						
						BeanCompany bean = new BeanCompany();
					  	
						tx = session.beginTransaction();
						bean.setName( getName() );
						bean.setFull_name( getFull_name() );
						bean.setRfc( getRfc() );
						bean.setAddress(getAddress() );
						bean.setActive( "Y" );
						bean.setUsername(beanUser.getName());
						
						
						bean.setLogo_file_name( "companylogo");
						
						
						
						session.save( bean );
						
						//bean.setLogo_file_name( "companylogo" +bean.getId());
						
						File destFile;
						String fileExt = "";
						double bytes ;
						if(myFile != null)	
						{
						  fileName = myFile.getName();
						  
							 
						  int index = myFileFileName.lastIndexOf('.');
		                  if (index == -1) {
		                	  fileExt =  "";
		                  } else {
		                	  fileExt= myFileFileName.substring(index + 1);
		                  }
		                  
		                  
						  fileName = "companylogo" + String.valueOf(bean.getId()) +"."+ fileExt;
						  bean.setLogo_file_name(fileName);
							 
						  destFile  = new File(destPath, fileName);					
						  bytes = myFile.length();
						  fileSize = (bytes / 1024);
						  FileUtils.copyFile(myFile, destFile);
						  if ( fileSize > MAX_SIZE)
						  {
							//httpSession.setAttribute("error", "El tamanno del archivo no debe sobrepasar los " + MAX_SIZE + " Bytes (" + MAX_SIZE/1024 + " KBytes).");	
							httpSession.setAttribute("error", "El tamanno del archivo no debe sobrepasar los " + MAX_SIZE + " Bytes (" + MAX_SIZE/1024 + " KBytes).");
							forward = ERROR;
							return  forward;
						  }
						  
						  session.update( bean );	
						}
			
						
				    
							
						query = " FROM BeanUser u"
							  + " WHERE u.id_company.id = " + bean.getId()
							  + " AND u.name='ROOT'";
                            
						list = session.createQuery( query ).list();
		               
						if ( list.size() > 0 )
						  {
							//com.bituos.Error error = new com.bituos.Error("El usuario ya existe.", request);						
							//forward = mapping.findForward("error");
							httpSession.setAttribute("error","El usuario ya existe.");
							forward = ERROR;
						  }
						else 
						  {
							BeanUser beanUserNew = new BeanUser();
							//BeanCompany beanCompany = (BeanCompany) session.load( BeanCompany.class, new Integer( userCreateRoot.getId_company().trim() )); 
		
							beanUserNew.setActive( "Y");
							beanUserNew.setAddress( "" );
							beanUserNew.setId_company( bean );
							beanUserNew.setName( "ROOT" );
							beanUserNew.setPassword( getRootpassword() );
							//beanUser.setRfc("");
							beanUserNew.setEmail( getRootemail() );
		
							query =" FROM BeanProcess u"
								 + " WHERE u.id NOT IN (61,62,63,64,65)"; 
		
							Iterator iter = session.createQuery( query ).list().iterator();
							
							tx = session.beginTransaction();
		
							session.save( beanUserNew );
		
							while (iter.hasNext())
							  {
								BeanProcessUsers beanProcessUsers = new BeanProcessUsers();
								
								beanProcessUsers.setId_process( (BeanProcess) iter.next() );
								beanProcessUsers.setId_user( beanUserNew );
								
								session.save( beanProcessUsers );
							  }
							
							
							
							
							tx.commit();
								
							
							if (new SessionUtil().prepareDataForCompanyNew(request,beanUser,session) != 1) 
							{
								forward = "ERROR";
							}
							
							httpSession.setAttribute("done", "Empresa registrada correctamente");
						  }
		 
						
						  
			
					  }
				}
	
				
		  } 
		catch (Exception e) 
		  {
			e.printStackTrace();
			httpSession.setAttribute("error", "Ocurrio un error: "+ e.getMessage());
			 
							
			forward = ERROR;
						
			//errors.add("unknown", new org.apache.struts.action.ActionError("error.unknow"));
		  }
		finally	
		  {
			if ( session != null )
				 session.close(); 
				  
			if ( sessionFactory != null )
				 sessionFactory.close();
		  }

		// Write logic determining how the user should be forwarded.

		/*if (forward !="") {
			
			forward = ERROR;
		}
		else 
		   forward = "links";*/


		// Finish with
		return (forward);

	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFull_name() {
		return full_name;
	}


	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getRfc() {
		return rfc;
	}


	public void setRfc(String rfc) {
		this.rfc = rfc;
	}


	public String getActive() {
		return active;
	}


	public void setActive(String active) {
		this.active = active;
	}


	public File getMyFile() {
		return myFile;
	}


	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}


	public String getMyFileContentType() {
		return myFileContentType;
	}


	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}


	public String getMyFileFileName() {
		return myFileFileName;
	}


	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}


	public String getDestPath() {
		return destPath;
	}


	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}


	public String getRootpassword() {
		return rootpassword;
	}


	public void setRootpassword(String rootpassword) {
		this.rootpassword = rootpassword;
	}


	public String getRootemail() {
		return rootemail;
	}


	public void setRootemail(String rootemail) {
		this.rootemail = rootemail;
	}


	public String getRootrfc() {
		return rootrfc;
	}


	public void setRootrfc(String rootrfc) {
		this.rootrfc = rootrfc;
	}


	public String getRootpassword2() {
		return rootpassword2;
	}


	public void setRootpassword2(String rootpassword2) {
		this.rootpassword2 = rootpassword2;
	}




	
	

}
