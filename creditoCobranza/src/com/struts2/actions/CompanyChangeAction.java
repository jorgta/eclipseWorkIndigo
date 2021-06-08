package com.struts2.actions;




import java.io.File;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;




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
public class CompanyChangeAction extends ActionSupport  {

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
	
	private String password;
	private String phone1;
	private String phone2;
	private String timeToReport;
	private String logo_file_name;
	private String location;
	
	
	private File myFile;
    private String myFileContentType;
    private String myFileFileName;
    private String destPath;
	
	private String id;
	private String process;
	
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
    	
        if(!process.equals("select2CompanyChange"))
        {
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
  		
  		
  		
        }
  		
         
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
					
				
					  
				if (process.equals("selectedCompany"))
				  {
					
					BeanCompany beanCompany2Change= (BeanCompany)httpSession.getAttribute("beanCompany2Change");
					String query="";
				    List list;
				    List listProcessUsers;
				
					if ( beanCompany2Change!= null )
					  {   
						////////logo for the company///////
						//String PATH_SERVER = "/Tomcat/webapps/files/uploadeventAdmin/";
						//String wwwPath = "/files/uploadeventAdmin/" ;
						//String PATH_SERVER = "/eclipseWorkIndigo/files/WebContent/uploadTrip";
						//String wwwPath = "/files/uploadTrip/" ;
			            //setDestPath(PATH_SERVER);
						//int MAX_SIZE = 50 * 1024; //50 kb
					
						//BeanControlPanel control_panel = (BeanControlPanel) session.load( BeanControlPanel.class, new Integer( 1 ) );
				
						//FormFile theFile;
						//String contentType;
						//String fileName ="";
						//double fileSize; 
						//byte[] fileData;
						
						query =   " FROM BeanControlPanel u";  
						list = session.createQuery(query).list();
						Iterator iterBeanControlPanel = session.createQuery( query ).list().iterator();
						String PATH_SERVER = "";
						String wwwPath = "";
						//FormFile theFile;
						String contentType ="";
						String fileName ="";
						double fileSize; 
						byte[] fileData;
						int MAX_SIZE = 0;
						BeanControlPanel control_panel;
						File dir; 
						boolean exists = false;
						while (iterBeanControlPanel.hasNext())
					    {
						    control_panel = new BeanControlPanel();
						    control_panel = (BeanControlPanel) iterBeanControlPanel.next(); 
						    dir = new File(control_panel.getPath_server());
						    exists = dir.exists();
						    PATH_SERVER = control_panel.getPath_server();
						    if (exists)
						    {
						    	
						    	wwwPath = control_panel.getPath_www() ;
					            setDestPath(PATH_SERVER);
								MAX_SIZE = 50 * 1024; //50 kb
						    	break;
						    }
						 
 
					    }
						
						if (!exists)
						{
							httpSession.setAttribute("error", "La ruta para almacenar el logotipo no existe, consulte a soporte tecnico.");
							forward = ERROR;
							return  forward;
						}
					
						
						
						
						File destFile;
						double bytes ;
						if(myFile != null)	
						{
						  fileName = myFile.getName();
						  destFile  = new File(destPath, myFileFileName);					
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
						}
						
						
				    
						 if(!forward.endsWith(ERROR))
						 {
							//BeanCompany bean = new BeanCompany();
					  	
							tx = session.beginTransaction();
							beanCompany2Change.setName( getName() );
							beanCompany2Change.setLocation(getLocation());
							beanCompany2Change.setPhone1(getPhone1());
							beanCompany2Change.setPhone2(getPhone2());
							if(!getTimeToReport().equals(""))
							  beanCompany2Change.setTimeToReport(Integer.valueOf(getTimeToReport()));
					 
							 

	

							beanCompany2Change.setAddress(getAddress() );
							beanCompany2Change.setActive( 1);
							
							//beanCompany2Change.setLogo_file_name( wwwPath + "company_logo_" + bean.getId() );
							
						 
							session.update( beanCompany2Change );
							if(myFile != null)
							{
							   beanCompany2Change.setLogo_file_name( wwwPath + "company_logo_" + beanCompany2Change.getId() + "_" + fileName );
							   session.update( beanCompany2Change );		
							   dir = new File( PATH_SERVER );
							   dir.mkdirs();
							}
							
							
							query = " FROM BeanUser u"
								  + " WHERE u.id_company.id = " + beanCompany2Change.getId()
								  + " AND u.name='ROOT'";
		                     
							list = session.createQuery( query ).list();
			               
							if ( list.isEmpty() )
							  {
								//com.bituos.Error error = new com.bituos.Error("El usuario ya existe.", request);						
								//forward = mapping.findForward("error");
								httpSession.setAttribute("error","El usuario ROOT no existe.");
								forward = ERROR;
							  }
							else 
							  {
								BeanUser beanUserROOT = (BeanUser)list.get(0);
								//BeanCompany beanCompany = (BeanCompany) session.load( BeanCompany.class, new Integer( userCreateRoot.getId_company().trim() )); 
			
								beanUserROOT.setActive( 1);
								beanUserROOT.setAddress( "" );
								//beanUserROOT.setId_company( bean );
								//beanUserROOT.setName( "ROOT" );
								beanUserROOT.setPassword( getRootpassword() );
								//beanUser.setRfc("");
								beanUserROOT.setEmail( getRootemail() );
			
								query =" FROM BeanProcess u"
									 + " WHERE u.id_type_process != 30"; 
			
			
								Iterator iterProcess = session.createQuery( query ).list().iterator();
								
								tx = session.beginTransaction();
			
								session.update( beanUserROOT );
								
								
			
								while (iterProcess.hasNext())
								  {
									BeanProcessUsers beanProcessUsers = new BeanProcessUsers();
									BeanProcess bp= (BeanProcess) iterProcess.next(); 
									
									query = " FROM BeanProcessUsers u" +
											" WHERE u.id_process.id =" + bp.getId()+
											" AND u.id_user.id=" + beanUserROOT.getId();
									
									listProcessUsers = session.createQuery( query ).list();
									if( listProcessUsers.isEmpty() )
									{
										beanProcessUsers.setId_process( bp );
										beanProcessUsers.setId_user( beanUserROOT );
										session.save(beanProcessUsers);
									}
									 
								  }
								
								
								
								
								tx.commit();
								
								
								String encryptedName = AESCrypt.encrypt(beanCompany2Change.getName()); 
								String encryptedPassword = AESCrypt.encrypt(beanCompany2Change.getPassword());
								String encryptedId = AESCrypt.encrypt(String.valueOf( beanCompany2Change.getId() ));
								String encryptedLocation = AESCrypt.encrypt(beanCompany2Change.getLocation());
								
								XMLWriterDOM xml = new XMLWriterDOM();
								xml.setPath(PATH_SERVER);
								xml.setFilename(beanCompany2Change.getName());
								xml.setId(String.valueOf( encryptedId) );
								xml.setLocation(encryptedLocation);
								xml.setName(encryptedName);
								xml.setPassword(encryptedPassword);
								xml.XMLWriter();
									
								
								if (new SessionUtil().prepareDataForCompanyChange(request,beanUser,session) != 1) 
								{
									forward = "ERROR";
								}
								httpSession.setAttribute("done", "Cambio registrado correctamente");
							  }
		
						 }
						  
			
					  }

					

			       }else if (process.equals("select2CompanyChange"))
			       {
			    	    String query =    " FROM BeanCompany u" 
						                + " WHERE u.id = " + getId();
						                
						    
				
						List list = session.createQuery(query).list();
						
						if (!list.isEmpty()) 
						{  
							BeanCompany bean = (BeanCompany)list.get(0);							
							httpSession.setAttribute("beanCompany2Change",bean);
							
							query =    " FROM BeanUser u" 
				                	 + " WHERE u.id_company.id = " + bean.getId();
							
							list = session.createQuery(query).list();
							BeanUser beanUserROOT = (BeanUser) list.get(0); 
							
							httpSession.setAttribute("currentBeanUserROOT",beanUserROOT);
						
						}
						else
						{
							httpSession.setAttribute("message", "La compañia ya no existe");	
						}
			       }
				}
				
				
		  } 
		catch (Exception e) 
		  {
			e.printStackTrace();
			
			//addActionError("Ocurrio un error: "+ e.getMessage() );
			httpSession.setAttribute("error","Ocurrio un error: "+ e.getMessage());
							
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


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getProcess() {
		return process;
	}


	public void setProcess(String process) {
		this.process = process;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhone1() {
		return phone1;
	}


	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}


	public String getPhone2() {
		return phone2;
	}


	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}


 


	public String getTimeToReport() {
		return timeToReport;
	}


	public void setTimeToReport(String timeToReport) {
		this.timeToReport = timeToReport;
	}


	public String getLogo_file_name() {
		return logo_file_name;
	}


	public void setLogo_file_name(String logo_file_name) {
		this.logo_file_name = logo_file_name;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}




	
	

}
