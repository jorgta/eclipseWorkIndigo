package com.struts2.actions;




import java.util.*;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
public class DeviceActivateAction extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730294291529491868L;
	
	private String id;
	private String process;

	
	private HttpSession httpSession = null;
	private HttpServletRequest request = null;
	//private static final Log  log  = (Log) LogFactory.getLog(LoginAction.class);
	
	//@RequiredStringValidator(fieldName = "username", type = ValidatorType.FIELD, message = "Login User is required")
	 
	public String forward()
	{
		return ERROR;
	}
	
	
	public void validate() 
	  {	
        String fieldr = "Campo requerido";
        String fieldnospaces = "El campo no tener espacion";
        String fieldinvalid = "Formato Invalido";
	    String error_DeviceNotSelected = "Debe seleccionar algun dispositivo";
        
        HttpServletRequest request = ServletActionContext.getRequest();
        httpSession = request.getSession(); 
        httpSession.setAttribute("module","pre");
        //httpSession = null;
    	request = null;
    	
    	httpSession.removeAttribute( "done" );
    	httpSession.removeAttribute( "information" );
    	httpSession.removeAttribute( "error" );
    	httpSession.removeAttribute( "attention" );
   	
    	if ( process.equals("deviceActivate") )
    	  { 
        	if ( httpSession.getAttribute("listDevice2Activate") == null )
        	  { 
    			this.addFieldError("process", getText(error_DeviceNotSelected));
				httpSession.setAttribute("attention", new String("No hay dispositivos a seleccionar"));
        	  }
        	else 
	      	  {
	  			List<BeanDevice> listDevice2Activate = (ArrayList<BeanDevice>)httpSession.getAttribute("listDevice2Activate");
	      		
	  			if ( listDevice2Activate.size() == 0 )
	  	      	  { 
	    			this.addFieldError("process", getText(error_DeviceNotSelected));
					httpSession.setAttribute("attention", new String("Debe seleccionar algun dispositivo"));
	  	      	  }
	      	  }
    	  }
    		
    	
		
		/*if ( id != null )
		  {
			id = id.trim();
			if (id.length() == 0)
				this.addFieldError("id",getText(fieldr));
			if (id.indexOf(" ") > 0 )
			this.addFieldError("id",getText(fieldnospaces));
		  }
		else
			this.addFieldError("id",getText(fieldr));
		*/
		 
         
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
					if ( process.equals("deviceActivate") )
					  {
						List<BeanDevice> listDevice2Activate = (ArrayList<BeanDevice>) httpSession.getAttribute("listDevice2Activate");
		                
						int devicesCount = listDevice2Activate.size();
		                Iterator<BeanDevice> iter = listDevice2Activate.iterator();						

						tx = session.beginTransaction();
		                while ( iter.hasNext() )
						  {
							BeanDevice bean = (BeanDevice) iter.next();		
							
						    bean.setActive(1);
							session.update( bean );
						  }
						tx.commit();
						
						if ( devicesCount > 1 )
						  httpSession.setAttribute("done", new String("Dispositivos activados"));
						else
						  httpSession.setAttribute("done", new String("Dispositivo activado"));
						
						int deactivatedDevicesCount = new SessionUtil().prepareDataForDeviceActivate( request, beanUser, session);
						
						forward = "success";
					  }
					else if (process.equals("DeviceAdd2Activate"))				  
					  {
						//BeanUser bean = (BeanUser) session.load( BeanUser.class, new Integer( getId().toString() ) );
						String query =  " FROM BeanDevice u" +
										" WHERE u.id = " + getId()+
										" AND u.active = 0" +
										" ORDER BY u.hostname";
		                
						
						List beanList = session.createQuery( query ).list();
						if(!beanList.isEmpty())
						  {
							BeanDevice bean = (BeanDevice)beanList.get(0);
							bean.setActive(1);
			                
			                List listDevice2Activate = (ArrayList<BeanDevice>)httpSession.getAttribute("listDevice2Activate");
			                
			                if(listDevice2Activate==null)		
			                {	
			                  listDevice2Activate = new ArrayList<BeanDevice>();
			                  listDevice2Activate.add(bean);
			                }else
			                {
			                	listDevice2Activate.add(bean);
			                	 
			                }
		
			                httpSession.setAttribute( "listDevice2Activate", listDevice2Activate);
						  }
						else
						  {
							query = " FROM BeanDevice u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active = 0" +										
					  				" ORDER BY u.hostname";
						
							List list = session.createQuery( query ).list();
 
							httpSession.setAttribute("listDeviceActivate", list );				
						  }
					  }
					else if (process.equals("DeviceRemove2Activate"))				  
					  {
						//BeanUser bean = (BeanUser) session.load( BeanUser.class, new Integer( getId().toString() ) );		                
		                //bean.setActive("Y");
		                
		                List listDevice2Activate = (ArrayList<BeanDevice>)httpSession.getAttribute("listDevice2Activate");
		                
		                if(listDevice2Activate != null)
		                  {
		                	Iterator iter = listDevice2Activate.iterator();						
							while ( iter.hasNext() )
							  {
								BeanDevice bean = (BeanDevice) iter.next();
								if( bean.getId() == Integer.valueOf(getId()) ) 
								  {
								    listDevice2Activate.remove(bean);
								    break;
								  }
							  }	
		                  }
		                	 
		                
		                httpSession.removeAttribute("listDevice2Activate");
		                
		                httpSession.setAttribute("listDevice2Activate", listDevice2Activate);
					  }
	
				}

				
				
		  } 
		catch (Exception e) 
		  {
			e.printStackTrace();
		 
			httpSession.setAttribute("error", "Ocurrio un error: "+ e.getMessage() );
							
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




	
	

}
