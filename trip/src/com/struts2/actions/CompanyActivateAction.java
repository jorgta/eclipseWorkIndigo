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
public class CompanyActivateAction extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6730294291529491868L;
	
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
        
        HttpServletRequest request = ServletActionContext.getRequest();
        httpSession = request.getSession(); 
        httpSession.setAttribute("module","pre");
        httpSession =null;
    	request = null;
    	
        
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
					
					
					
					if (process.equals("companyActivate"))
					  {
						
						List<BeanCompany> listCompany2Activate = (ArrayList<BeanCompany>)httpSession.getAttribute("listCompany2Activate");
		                String query = "";
		                List list;
		                List listProcessUsers;
						int company=listCompany2Activate.size();
		                Iterator<BeanCompany> iter = listCompany2Activate.iterator();						
						while ( iter.hasNext() )
						  {
							BeanCompany bean = (BeanCompany) iter.next();		                
						    bean.setActive("Y");
							tx = session.beginTransaction();
							session.update( bean );
							
							query =   " FROM BeanUser u"
									+ " WHERE u.id_company.id = " + bean.getId()
									+ " AND u.name='ROOT'";
							
							list = session.createQuery( query ).list();
							BeanUser beanUserROOT = (BeanUser)list.get(0);
								
		
							query =" FROM BeanProcess u"
								 + " WHERE u.id NOT IN (61,62,63,64,65)"; 
		
							Iterator iterProcess = session.createQuery( query ).list().iterator();
							
							tx = session.beginTransaction();
		
							//session.save( beanUserNew );
		
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
							
						  }
						
			
						
						if (new SessionUtil().prepareDataForCompanyActivate(request,beanUser,session) != 1) 
						  {								   			
							forward = "ERROR";
						  }
								
						if (company > 1)
						  {
						    httpSession.setAttribute("done", "Compañias activados");
						  }
						 else
						  {	
						    httpSession.setAttribute("done", "Compañia activada");
						  }
					
					  }
					else if (process.equals("companyAdd2Activate"))				  
					  {
						//BeanUser bean = (BeanUser) session.load( BeanUser.class, new Integer( getId().toString() ) );
						String query =  " FROM BeanCompany u" +
										" WHERE u.id = " + getId()+
										" AND u.active = 'N'";
		                
						
						List beanList = session.createQuery( query ).list();
						if(!beanList.isEmpty())
						{
							BeanCompany bean = (BeanCompany)beanList.get(0);
							bean.setActive("Y");
			                
			                List listCompany2Activate = (ArrayList<BeanCompany>)httpSession.getAttribute("listCompany2Activate");
			                
			                if(listCompany2Activate==null)		
			                {	
			                	listCompany2Activate = new ArrayList<BeanCompany>();
			                	listCompany2Activate.add(bean);
			                }else
			                {
			                	listCompany2Activate.add(bean);
			                	 
			                }
		
			                httpSession.setAttribute("listCompany2Activate", listCompany2Activate);
						}else
						{
							query = " FROM BeanCompany u" +
									" WHERE u.id_company = " + beanUser.getId_company().getId() +
									" AND u.active = 'N'" +										
					  				" ORDER BY u.name";
						
							List list = session.createQuery( query ).list();
 
							httpSession.setAttribute("listCompanyActivate", list );				
			 
						}
		                 
		                
					  }
					else if (process.equals("companyRemove2Activate"))				  
					  {
						//BeanUser bean = (BeanUser) session.load( BeanUser.class, new Integer( getId().toString() ) );		                
		                //bean.setActive("Y");
		                
		                List listCompany2Activate = (ArrayList<BeanCompany>)httpSession.getAttribute("listCompany2Activate");
		                
		                if(listCompany2Activate != null)
		                {
		                	Iterator iter = listCompany2Activate.iterator();						
							while ( iter.hasNext() )
							  {
								BeanCompany bean = (BeanCompany) iter.next();
								if( bean.getId() == Integer.valueOf(getId()) ) 
								{
									listCompany2Activate.remove(bean);
								   break;
								}
							  }	
		                }
		                
		                
		                
		                	 
		                
		                httpSession.removeAttribute("listCompany2Activate");
		                
		                httpSession.setAttribute("listCompany2Activate", listCompany2Activate);
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
