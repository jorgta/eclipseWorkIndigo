package com.struts2.actions;




//import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
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
public class RoomChangeAction extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730294291529491868L;
	
    private int id; 
	private String description;
	private Date date_reg;
	private File myFile;
    private String myFileContentType;
    private String myFileFileName;
    private String destPath;
    private String process;
	private HttpSession httpSession =null;
	private HttpServletRequest request = null;
	private String []specs;
	private String price1;
	private String price2;
	private String price3;
	private String price4;
	private String priceBaby;
	private String priceBasic;
	private String priceChild;
	private String priceJr;
	private String date_begin;
	private String date_end;
	
	

	public String forward()
	{
		return ERROR;
	}
	
	public void validate() {	
       
		HttpServletRequest request = ServletActionContext.getRequest();
        httpSession = request.getSession(); 
        httpSession.setAttribute("module","pre");
        httpSession =null;
    	request = null;
    	
    	
        if(process.equals("selectedRoom"))
        {
        	if(description != null)
    		{
    			description = description.trim();
    			if(description.length() == 0)
    				this.addFieldError("description",getText("error.field.required"));
    		}
    		else
				this.addFieldError("description",getText("error.field.required"));
			
    		 if (date_begin.length() != 0 )
	 		  {
	    		  if (!Utils.IsDate2(date_begin))
	  				this.addFieldError("date_begin",getText("error.field.invalid.date2"));
	 		  }
	    	  else
	    		  this.addFieldError("date_begin",getText("error.field.required")); 
	    	  
	    	  if (date_end.length() != 0 )
	 		  {
	    		  if (!Utils.IsDate2(date_end))
	  				this.addFieldError("date_end",getText("error.field.invalid.date2"));
	 		  }
	    	  else
	    		  this.addFieldError("date_end",getText("error.field.required"));
	    	  
	    	  if (price1.length() != 0 )
	 		  {
	    		  price1 = price1.trim();
	    		  if(price1.length()== 0)
	    			  this.addFieldError("price1",getText("error.field.required"));
	    		  else
	    			if (!Utils.isDouble(price1) || Double.parseDouble(price1) < 0)
	    				this.addFieldError("price1",getText("error.field.invalid.format"));
	 		  }
	    	  else
	    		  this.addFieldError("price1",getText("error.field.required"));

              if (price2.length() != 0 )
	 		  {
            	  price2 = price2.trim();
            	  if(price2.length()== 0)
	    			  this.addFieldError("price2",getText("error.field.required"));
            	  else
	    		  if (!Utils.isDouble(price2) || Double.parseDouble(price2) < 0)
	  				this.addFieldError("price2",getText("error.field.invalid.format"));
	 		  }
	    	  else
	    		  this.addFieldError("price2",getText("error.field.required"));  
	    	 
	    	  if (price3.length() != 0 )
	 		  {
	    		  price3 = price3.trim();
	    		  if(price3.length()== 0)
	    			  this.addFieldError("price3",getText("error.field.required"));
            	  else
	    		  if (!Utils.isDouble(price3) || Double.parseDouble(price3) < 0)
	  				this.addFieldError("price3",getText("error.field.invalid.format"));
	 		  }
	    	  else
	    		  this.addFieldError("price3",getText("error.field.required"));  
	    	  
	    	  if (price4.length() != 0 )
	 		  {
	    		  price4 = price4.trim();
	    		  if(price4.length()== 0)
	    			  this.addFieldError("price4",getText("error.field.required"));
            	  else
	    		  if (!Utils.isDouble(price4) || Double.parseDouble(price4) < 0)
	  				this.addFieldError("price4",getText("error.field.invalid.format"));
	 		  }
	    	  else
	    		  this.addFieldError("price4",getText("error.field.required")); 
	    	  
	    	  if (priceBaby.length() != 0 )
	 		  {
	    		  priceBaby = priceBaby.trim();
	    		  if(priceBaby.length()== 0)
	    			  this.addFieldError("priceBaby",getText("error.field.required"));
            	  else
	    		  if (!Utils.isDouble(priceBaby) || Double.parseDouble(priceBaby) < 0)
	  				this.addFieldError("priceBaby",getText("error.field.invalid.format"));
	 		  }
	    	  else
	    		  this.addFieldError("priceBaby",getText("error.field.required")); 
	    	  
	    	  if (priceBasic.length() != 0 )
	 		  {
	    		  priceBasic = priceBasic.trim();
	    		  if(priceBasic.length()== 0)
	    			  this.addFieldError("priceBasic",getText("error.field.required"));
            	  else
	    		  if (!Utils.isDouble(priceBasic) || Double.parseDouble(priceBasic) < 0)
	  				this.addFieldError("priceBasic",getText("error.field.invalid.format"));
	 		  }
	    	  else
	    		  this.addFieldError("priceBasic",getText("error.field.required")); 
	    	  
	    	  if (priceChild.length() != 0 )
	 		  {
	    		  priceChild = priceChild.trim();
	    		  if(priceChild.length()== 0)
	    			  this.addFieldError("priceChild",getText("error.field.required"));
            	  else
	    		  if (!Utils.isDouble(priceChild) || Double.parseDouble(priceChild) < 0)
	  				this.addFieldError("priceChild",getText("error.field.invalid.format"));
	 		  }
	    	  else
	    		  this.addFieldError("priceChild",getText("error.field.required")); 
	    	  
	    	  if (priceJr.length() != 0 )
	 		  {
	    		  priceJr = priceJr.trim();
	    		  if(priceJr.length()== 0)
	    			  this.addFieldError("priceJr",getText("error.field.required"));
            	  else
	    		  if (!Utils.isDouble(priceJr) || Double.parseDouble(priceJr) < 0)
	  				this.addFieldError("priceJr",getText("error.field.invalid.format"));
	 		  }
	    	  else
	    		  this.addFieldError("priceJr",getText("error.field.required")); 
	    	  
	   }
    	  
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
			
			httpSession.removeAttribute("information");
			httpSession.removeAttribute("error");
			httpSession.removeAttribute("done");
			
			
			BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");
			if ( beanUser == null )
				  forward =  "logout";
			if (process.equals("selectedRoom"))
			{
				BeanRoom beanRoom = (BeanRoom)httpSession.getAttribute("beanRoom2Change");
				if (beanRoom!= null )
				{  
					    
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				        Date date_begin = simpleDateFormat.parse(getDate_begin());
                        Date date_end = simpleDateFormat.parse(getDate_end());
						
						if( date_begin.after(date_end))
						{
							httpSession.setAttribute("error", getText("error.field.since.must.be.lower.than.until"));
							forward = "error";
						}
						else
						{
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
							
							    tx = session.beginTransaction();
								BeanRoom bean =(BeanRoom)session.get(BeanRoom.class,beanRoom.getId());
								bean.setDescription(getDescription());
								bean.setPrice1(Double.parseDouble(getPrice1()));
								bean.setPrice2(Double.parseDouble(getPrice2()));
								bean.setPrice3(Double.parseDouble(getPrice3()));
								bean.setPrice4(Double.parseDouble(getPrice4()));
								bean.setPriceBaby((Double.parseDouble(getPriceBaby())));
								bean.setPriceBasic((Double.parseDouble(getPriceBasic())));
								bean.setPriceChild((Double.parseDouble(getPriceChild())));
								bean.setPriceJr((Double.parseDouble(getPriceJr())));
								bean.setDate_begin(date_begin);
								bean.setDate_end(date_end);
								
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
									
									  
									  fileName = "roomimagen" + bean.getId() + "."+fileExt;
										 
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
										  bean.setImage1(fileName);
										  File dir = new File( PATH_SERVER );
										  dir.mkdirs();
									  }
									  
									  
								}
								
								if(!forward.endsWith(ERROR))
								{	
									session.update(bean);
									
									String query2 =  " FROM BeanRoom_Specs rs" +
													 " WHERE rs.id_room.id = " + bean.getId();
		                
									List list2 = session.createQuery(query2).list();
									
									if (!list2.isEmpty()) 
									{ 
									  for (int i = 0; i < list2.size(); i++) 
									  {
										  BeanRoom_Specs beanRoom_Specs = (BeanRoom_Specs)list2.get(i);
										  beanRoom_Specs =(BeanRoom_Specs)session.get(BeanRoom_Specs.class,beanRoom_Specs.getId());
										  session.delete(beanRoom_Specs);
									  }
								   }
								   httpSession.removeAttribute("listSpecsAdd");
								   String [] specsAdd = request.getParameterValues("specs");
								   List<BeanRoom_Specs> listSpecsAdd = new ArrayList<BeanRoom_Specs>();
								   
								   if(specsAdd != null)
								   {
									   for (int i = 0; i < specsAdd.length; i++) 
									   {
										   int id = Integer.parseInt(specsAdd[i]);
										   String query3 =  " FROM BeanSpec s" +
										   					" WHERE s.id = " + id;
						       
										   List list3 = session.createQuery(query3).list();
										
										  if (!list3.isEmpty()) 
										  {
											 BeanSpec beanSpec = (BeanSpec) list3.get(0);
											 BeanRoom_Specs beanRoom_Specs = new BeanRoom_Specs();
											 beanRoom_Specs.setId_room(bean);
											 beanRoom_Specs.setId_spec(beanSpec);
											 beanRoom_Specs.setSpec_description(beanSpec.getDescription());
											 beanRoom_Specs.setDate_reg(Utils.Today());
											 session.save(beanRoom_Specs);
											 listSpecsAdd.add(beanRoom_Specs);
										  }
										  
										  if(!listSpecsAdd.isEmpty())
										   {
											   httpSession.setAttribute("listSpecsAdd",listSpecsAdd);
										   } 
									   }
								   }
								   
								   tx.commit();
								   httpSession.setAttribute("beanRoom2Change",bean);
									
									if (new SessionUtil().prepareDataForRoomChange(request,beanUser,session) != 1) 
									{
										forward = "error";
									}
									else
									{
										forward = "success";
										httpSession.removeAttribute("date_begin");
										httpSession.removeAttribute("date_end");
										
										Date dateBegin = bean.getDate_begin();
										DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm");
										String date_begin2 = date.format(dateBegin);
										httpSession.setAttribute("date_begin", date_begin2);
										Date dateEnd = bean.getDate_end();
										DateFormat date2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
										String date_end2 = date2.format(dateEnd);
										httpSession.setAttribute("date_end", date_end2);
										
										httpSession.setAttribute("done", getText("done.room.change"));
										  
									}
								}
								
						}
				       
				}
			}
			else
			if (process.equals("selectedRoom2Change"))
			{
					httpSession.removeAttribute("listSpecsAdd")	;
					httpSession.removeAttribute("listSpecs")	;
				
					String query =    " FROM BeanRoom r" 
					                + " WHERE r.id = " + getId();
					                
					List list = session.createQuery(query).list();
					
					if (!list.isEmpty()) 
					{  
						BeanRoom bean = (BeanRoom)list.get(0);
						
						
						String query2 =   " FROM BeanRoom_Specs spec" +
										  " WHERE spec.id_room.id = " + bean.getId();
		                
						List list2 = session.createQuery(query2).list();
						
						if(!list2.isEmpty())
						{
							httpSession.setAttribute("listSpecsAdd",list2);
							
						}
						String query3 =   " FROM BeanSpec spec";
      
						List list3 = session.createQuery(query3).list();
						
						if(!list3.isEmpty())
						{
							httpSession.setAttribute("listSpecs",list3);
							
						}
						
						Date dateBegin = bean.getDate_begin();
						DateFormat date = new SimpleDateFormat("yyyy/MM/dd HH:mm");
						String date_begin = date.format(dateBegin);
						httpSession.setAttribute("date_begin", date_begin);
						Date dateEnd = bean.getDate_end();
						DateFormat date2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
						String date_end = date2.format(dateEnd);
						httpSession.setAttribute("date_end", date_end);
												
						httpSession.setAttribute("beanRoom2Change",bean);
						
					}
					else
					{
						httpSession.setAttribute("attention", "No ha seleccionado la habitación a modificar");
						forward = "success";
					}
			}
			
			
	  } 
	catch (Exception e) 
	  {
		e.printStackTrace();
	
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


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getSpecs() {
		return specs;
	}

	public void setSpecs(String[] specs) {
		this.specs = specs;
	}
	
	public String getPrice1() {
		return price1;
	}

	public void setPrice1(String price1) {
		this.price1 = price1;
	}

	public String getPrice2() {
		return price2;
	}

	public void setPrice2(String price2) {
		this.price2 = price2;
	}

	public String getPrice3() {
		return price3;
	}

	public void setPrice3(String price3) {
		this.price3 = price3;
	}

	public String getPrice4() {
		return price4;
	}

	public void setPrice4(String price4) {
		this.price4 = price4;
	}

	public String getPriceBaby() {
		return priceBaby;
	}

	public void setPriceBaby(String priceBaby) {
		this.priceBaby = priceBaby;
	}

	public String getPriceBasic() {
		return priceBasic;
	}

	public void setPriceBasic(String priceBasic) {
		this.priceBasic = priceBasic;
	}

	public String getPriceChild() {
		return priceChild;
	}

	public void setPriceChild(String priceChild) {
		this.priceChild = priceChild;
	}

	public String getPriceJr() {
		return priceJr;
	}

	public void setPriceJr(String priceJr) {
		this.priceJr = priceJr;
	}
	
	public String getDate_begin() {
		return date_begin;
	}

	public void setDate_begin(String date_begin) {
		this.date_begin = date_begin;
	}

	public String getDate_end() {
		return date_end;
	}

	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}
		
}
