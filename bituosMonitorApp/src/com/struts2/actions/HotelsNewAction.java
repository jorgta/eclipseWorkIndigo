package com.struts2.actions;




import java.sql.Date;
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
import com.crystaldecisions.sdk.prompting.report.Util;

import org.hibernate.classic.Session;

/*
@Validations(requiredStrings = { 
	    @RequiredStringValidator(fieldName = "username", type = ValidatorType.FIELD, message = "Login User is required"), 
	    @RequiredStringValidator(fieldName = "password", type = ValidatorType.FIELD, message = "Password is required")
	}, expressions = {
	  @ExpressionValidator(expression = "company.trim().equals('bituos') == true", message = "Empresa debe ser bituos."),

	})*/
public class HotelsNewAction extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730294291529491868L;
	

	private String description;
	private String address;
	private Date date_reg;
	private File myFile;
    private String myFileContentType;
    private String myFileFileName;
    private String destPath;
    private String types_reservation;
	
	private HttpSession httpSession =null;
	private HttpServletRequest request = null;
	
	public String forward()
	{
		return ERROR;
	}
	
	public void validate() {	
       
		HttpServletRequest request = ServletActionContext.getRequest();
        httpSession = request.getSession(); 
        httpSession.setAttribute("module","pre");
       
        
    	if(description !=null)
    	{
    		description = description.trim();
    		if(description.length()== 0)
    			this.addFieldError("description",getText("error.field.required"));
    	}
    	else
    	   this.addFieldError("description",getText("error.field.required"));
    	
    	if(address !=null)
    	{
    		address = address.trim().toUpperCase();
    		if(address.length()== 0)
    			this.addFieldError("address",getText("error.field.required"));
    	}
    	else
    	   this.addFieldError("address",getText("error.field.required"));
    	
    	if (types_reservation.length()!= 0)
  	    {
	  	    if(types_reservation.equals("0"))
	  	    {
	  	    	this.addFieldError("types_reservation",getText("error.field.required"));
	  	    }
	  	    else
	  	    {
    	    	httpSession.setAttribute("type_reservation", types_reservation);
    	    } 	
	  	}
    	httpSession =null;
     	request = null;
   }

	public String  execute() throws Exception  {
	    String forward=SUCCESS;
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
				  forward =  "logout";
			
			String query =   " FROM BeanHotel u" 
						   + " WHERE u.description = '" +  getDescription() + "'"
						   + " AND u.id_company.id = " + beanUser.getId_company().getId();  
			
			java.util.List list = session.createQuery(query).list();
			
			if ( !list.isEmpty() )
				{
				  httpSession.setAttribute("error", getText("error.exist.hotels"));	
     			}
			else
			  {  
				////////logo for the hotel///////
				
			    BeanControlPanel control_panel = (BeanControlPanel) session.load( BeanControlPanel.class, new Integer( 1 ) );
			    String PATH_SERVER = control_panel.getPath_server();
			    String wwwPath = control_panel.getPath_www();
			    
	            setDestPath(PATH_SERVER);
				int MAX_SIZE = 50 * 1024; //50 kb
				
				//FormFile theFile;
				String contentType;
				String fileName ="";
				double fileSize; 
				byte[] fileData;
			    BeanHotel bean = new BeanHotel();
			    BeanType_Reservation beanType = null;
				tx = session.beginTransaction();
				bean.setDescription( getDescription().toUpperCase());
				bean.setAddress(getAddress() );
				bean.setActive(1);
				bean.setDate_reg( Utils.Today() );
				bean.setId_company(beanUser.getId_company());
				bean.setUserName(beanUser.getName());
				bean.setImage1("hotellogo" + bean.getId() );
				
				String query2 =   " FROM BeanType_Reservation t"+
				  				  " WHERE t.id = " +  types_reservation; 

				java.util.List list2 = session.createQuery(query2).list();
				
				if ( !list2.isEmpty() )
				{
					 beanType = (BeanType_Reservation)list2.get(0);
				}
				bean.setId_type_reservation(beanType);
				session.save( bean );
				
				String fileExt = "";
				File destFile;
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
				
				  
				  fileName = "hotellogo" + bean.getId() + "."+fileExt;
					 
				  destFile  = new File(destPath, fileName);					
				  bytes = myFile.length();
				  fileSize = (bytes / 1024);
				  FileUtils.copyFile(myFile, destFile);
				  if ( fileSize > MAX_SIZE)
				  {
					httpSession.setAttribute("error", "El tamanno del archivo no debe sobrepasar los " + MAX_SIZE + " Bytes (" + MAX_SIZE/1024 + " KBytes).");
					forward = ERROR;
					return  forward;
				  }
				  else
				  {
					  bean.setImage1( "hotellogo" + bean.getId() + "."+fileExt);	
					  File dir = new File( PATH_SERVER );
					  dir.mkdirs();
				  }
				  
				 	
				}
				
				if(!forward.endsWith(ERROR))
				{	
					session.update( bean );
					
					tx.commit();

					if (new SessionUtil().prepareDataForHotelNew(request,beanUser,session) != 1) 
					{  				
				    	forward = "ERROR";
					}
					else
					{  				
						httpSession.setAttribute("done",getText("done.hotel.add"));
					}
				
				  }
	
			  }
 		
	  } 
	catch (Exception e) 
	  {
		e.printStackTrace();
		
		httpSession.setAttribute("module","pre"); 
		httpSession.setAttribute("error", "Ocurrio un error interno : "+ e.getMessage() + " consulte a soporte tecnico");	
	 
						
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


	// Finish with
	return (forward);

}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public Date getDate_reg() {
		return date_reg;
	}

	public void setDate_reg(Date date_reg) {
		this.date_reg = date_reg;
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
    
	public String getTypes_reservation() {
		return types_reservation;
	}

	public void setTypes_reservation(String types_reservation) {
		this.types_reservation = types_reservation;
	}
		
		
}
