package com.struts2.actions;




import java.util.*;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.apache.struts2.ServletActionContext;

//import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;
 



import com.opensymphony.xwork2.ActionSupport;

import com.struts2.Beans.*;
import com.bituos.*;

import org.hibernate.classic.Session;

public class IndexAction extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2072003053543010332L;
	/**
	 * 
	 */
	
	
	private List<BeanModule>      listModule;
  	private Integer             rows             = 0;
  	private Integer             page             = 0;
  	private Integer             total            = 0;
  	private Integer             record           = 0;
  	private String              sord;
  	private String              sidx;
  	private String              searchField;
    private String              searchString;
	private String              searchOper;
    private boolean             loadonce         = false;
	private HttpSession httpSession =null;
    private HttpServletRequest request = null;
 
	
    
	
	public void validate() {	


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
				//sessionFactory = HibernateUtil.getSessionFactory();
				session = sessionFactory.openSession();			
			
				String query =   " FROM BeanModule u";  
					
				List list = session.createQuery(query).list();
					
				if (!list.isEmpty()){   
					
					listModule = list;
					httpSession.setAttribute("listModule", listModule);
					
					query =   " FROM BeanCategory u" +
							  " WHERE u.module_id.id in ( SELECT a.id " +
							  "						      FROM BeanModule a" +
							  "                          )" +
							  " ORDER BY u.module_id";  
					
					
					list = session.createQuery(query).list();
						
					if (!list.isEmpty()){   
					
						httpSession.setAttribute("listCategory", list);

/*
						query =   "  FROM bituos.items i " +
									WHERE i.category_id in (SELECT c.id FROM bituos.categories c
															where c.module_id in (SELECT m.id FROM bituos.modules m)
															order by ordering ASC)
									order by i.category_id  ASC";*/
						
						
					}
	
				}
				else
				  {
					  //errors.add("username", new org.apache.struts.action.ActionError("error.notexist.username"));
					  com.bituos.Error error = new com.bituos.Error("Usuario inexistente o contrase?a incorrecta.", request);
					  httpSession.setAttribute("done", new String("login.jsp"));
					  forward = ERROR;
				  }
		  } 
		catch (Exception e) 
		  {
			e.printStackTrace();
			
			com.bituos.Error error = new com.bituos.Error( e.getMessage(), request);
							
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
	



	public Integer getRows() {
		return rows;
	}


	public void setRows(Integer rows) {
		this.rows = rows;
	}


	public Integer getPage() {
		return page;
	}


	public void setPage(Integer page) {
		this.page = page;
	}


	public Integer getTotal() {
		return total;
	}


	public void setTotal(Integer total) {
		this.total = total;
	}


	public Integer getRecord() {
		return record;
	}


	public void setRecord(Integer record) {
		this.record = record;
	}


	public String getSord() {
		return sord;
	}


	public void setSord(String sord) {
		this.sord = sord;
	}


	public String getSidx() {
		return sidx;
	}


	public void setSidx(String sidx) {
		this.sidx = sidx;
	}


	public String getSearchField() {
		return searchField;
	}


	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}


	public String getSearchString() {
		return searchString;
	}


	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}


	public String getSearchOper() {
		return searchOper;
	}


	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}


	public boolean isLoadonce() {
		return loadonce;
	}


	public void setLoadonce(boolean loadonce) {
		this.loadonce = loadonce;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public List<BeanModule> getGridModel() {
		return listModule;
	}


	public void setGridModel(List<BeanModule> listModule) {
		this.listModule = listModule;
	}
	
	
	


}
