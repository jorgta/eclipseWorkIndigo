<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*,com.eventAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"

%>




<html:html>
<HEAD>

	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="Eclipse">
	<META http-equiv="Content-Style-Type" content="text/css">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="/eventAdmin/GridViewCSSThemes/WhiteChromeGridView.css" rel="stylesheet" type="text/css" />	
	<SCRIPT type="text/javascript" src="/eventAdmin/GridViewCSSThemes/js/ajaxfile.js"></SCRIPT>	 
	<link rel="stylesheet" href="/eventAdmin/GridViewCSSThemes/bootstrap.min.css">
	<SCRIPT type="text/javascript" src="/eventAdmin/GridViewCSSThemes/js/jquery.min.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="/eventAdmin/GridViewCSSThemes/js/bootstrap.min.js"></SCRIPT>	
	<script src="/eventAdmin/js/utilajax.js"></script>
	<script src="/eventAdmin/tui.calendar-master/examples/js/data/connection.js"></script>
	
    <TITLE>
    </TITLE>
</HEAD>

<BODY>

	<H1> <bean:message key="label.client.change" /> </H1>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 24;
			    
			    List list = (List) ses.getAttribute("processList");
			    Iterator iter = list.iterator();
			    BeanProcessUsers beanProcessUser;
			    int found = 0;
			    
			    while( iter.hasNext() )
			      {
			        beanProcessUser = (BeanProcessUsers) iter.next(); 
			        
			        if ( beanProcessUser.getId_process().getId() == TAG )
			          {
			            found = 1;
			            break;
			          }
			      } 
		        if ( found == 0 )
		          response.sendRedirect( "../login.jsp");
		        else
		          found = 0;
			  %>


			
			 <div class="container">
			
			<div class="card  mb-3"  >
			
			  <div class="card-header">
			    <H1> Cambios </H1>
			  </div>
			  <div class="card-body">
			     
			    <h5 class="card-title"><bean:message key="label.client.select" /> </h5>
			    <p class="card-text">
			    
			    
			     </p>
			    <!-- 
			    <a href="#" class="btn btn-primary">Go somewhere</a>
			     -->
			    
						<html:form action="/clientChange">
						<html:hidden property="process" value="updateClient"/>
						 <html:hidden property="id" value=""/>
						 <ul class="list-group">
					 
								
								 <li class="list-group-item">
								  
								 
								    <div class="form-group">
								      <label class="control-label col-sm-3" for="name"><bean:message key="label.client.name" />:</label>
								      <div class="col-sm-5">								     
								        <html:text property="name" size="60" styleClass="form-control"  ></html:text>
								        <div class="col-sm-6">
									        <small id="nameHelp" class="text-danger">
									          <html:errors property="name"/>
									        </small>      
									      </div>
								      </div>
								    </div>
								    <div class="v-group">
								      <label class="control-label col-sm-2" for="email"><bean:message key="label.client.email" />:</label>
								      <div class="col-sm-5"> 								          
								        <html:text property="email" size="60" styleClass="form-control"  ></html:text>
								        <div class="col-sm-6">
									        <small id="emailHelp" class="text-danger">
									          <html:errors property="email"/>
									        </small>      
									      </div>
								      </div>
								    </div>					    
								    
								      <div class="form-group">
								      <label class="control-label col-sm-2" for="rfc"><bean:message key="label.client.rfc" />:</label>
								      <div class="col-sm-5">          
								        <html:text property="rfc" size="60" styleClass="form-control"  ></html:text>
								        <div class="col-sm-6">
									        <small id="rfcHelp" class="text-danger">
									          <html:errors property="rfc"/>
									        </small>      
									      </div>
								      </div>
								    </div>
								    
								     <div class="form-group">
								      <label class="control-label col-sm-2" for="address"><bean:message key="label.address" />:</label>
								      <div class="col-sm-5">          
								       
								        <html:text property="address" size="60" styleClass="form-control"  ></html:text>
								        <div class="col-sm-6">
									        <small id="addressHelp" class="text-danger">
									          <html:errors property="address"/>
									        </small>      
									      </div>
								      </div>
								    </div>
								    
								    
								      <div class="form-group">
								      <label class="control-label col-sm-2" for="address"><bean:message key="label.phone" />:</label>
								      <div class="col-sm-5">          
								      
								      <html:text property="phone" size="60" styleClass="form-control"  ></html:text>
								      <div class="col-sm-6">
									        <small id="phoneHelp" class="text-danger">
									          <html:errors property="phone"/>
									        </small>      
									      </div>
								      </div>
								    </div>
								    
								     <div class="form-group">
								      <label class="control-label col-sm-2" for="address"><bean:message key="label.company" />:</label>
								      <div class="col-sm-5">          
								     	 <html:text property="company" size="60" styleClass="form-control"  ></html:text>
								     	 <div class="col-sm-6">
									        <small id="companyHelp" class="text-danger">
									          <html:errors property="company"/>
									        </small>      
									      </div>
								      </div>
								    </div>
								    
								    
			 
								    <!-- 
								    <div class="form-group">        
								      <div class="col-sm-offset-2 col-sm-10">
								        <div class="checkbox">
								          <label><input type="checkbox" name="remember"> Remember me</label>
								        </div>
								      </div>
								    </div>
								     -->
								    
								    
								    
								    <div class="form-group">        
								      <div class="col-sm-offset-2 col-sm-10">
								        <button type="submit" class="btn btn-secondary"><bean:message key="label.update" /> </button>
								      </div>
								    </div>
								</li>
								
					
						
						</html:form>
						</div>
					
					<footer class="page-footer font-small blue">
			
					  <!-- Copyright -->
					  <div class="footer-copyright text-center py-3">© 2019 Copyright:
					    <a href="https://www.bituos.com"> bituos.com</a>
					  </div>
					  <!-- Copyright -->
					
					</footer>
			   	 
			  </div>
			</div>
			
			
			
			

		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	<logic:notPresent name="beanUser" scope="session">
	   <logic:redirect href="../login.jsp">  </logic:redirect>
	</logic:notPresent>
	
</BODY>

<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

  document.getElementById("name").focus();
  
//-->
</SCRIPT>

</html:html>
