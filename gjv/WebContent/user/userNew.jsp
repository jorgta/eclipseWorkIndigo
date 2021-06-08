<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>

<%@ page session="true" import="java.util.*, com.bituos.gjv.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<html:html>
<HEAD>

	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META http-equiv="Content-Style-Type" content="text/css">
		<script language='javascript' src="/gjv/calendar/popcalendar.js"></script> 
	<script language='javascript' src="/gjv/calendar/popcalendar.js"></script> 
	<link rel="stylesheet" type="text/css" href="/gjv/theme/screen.css">
	<link rel="stylesheet" type="text/css" href="/gjv/theme/dropdown.css">
	<script type="text/javascript" src="/gjv/theme/helpers.js"></script>
	<script type="text/javascript" src="/gjv/theme/date.js"></script>
	<script type="text/javascript" src="/gjv/theme/form.js"></script>
	<LINK href="/gjv/theme/grid.css" rel="stylesheet" type="text/css">
    <TITLE>Nuevo Usuario</TITLE>
</HEAD>

<BODY>

	
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 11;
			    
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
		          response.sendRedirect( "gjv/login.jsp");
		        else
		          found = 0;
			  %>

			

			<html:form action="/userNewAction" style="width: 100%">
			<fieldset class="login" >
			    <strong>
			    <legend><bean:message key="label.user.new"/></legend> 
			    </strong>
					<div  >		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.user"/>
					       </font>
					   </strong>	
					   <p style="padding-left: 165px">
			    		<html:text property="name" style="" ></html:text>	
			    		<FONT  COLOR="FF0000"> <html:errors property="name" /></FONT>
			    	   </p>				
						
					</div>
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.password"/>
					       </font>
					   </strong>	
					   <p style="padding-left: 165px">
			    		<html:password property="password" ></html:password>	
			    		<FONT COLOR="FF0000"> <html:errors property="password" /></FONT>
			    	   </p>				
						
					</div>	
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.confirmpassword"/>
					       </font>
					   </strong>
					   	<p style="padding-left: 165px">
			    		<html:password property="confirmpassword" ></html:password>	
			    		<FONT COLOR="FF0000"> <html:errors property="confirmpassword" /></FONT>	
			    	   </p>			
						
					</div>	
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.full.name" />
					       </font>
					   </strong>
					   <p style="padding-left: 165px">	
			    		<html:text property="full_name"></html:text>
			    		<FONT COLOR="FF0000"> <html:errors property="full_name" /></FONT>	
			    		</p>			
						
					</div>	
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.address" />
					       </font>
					   </strong>	
					   <p style="padding-left: 165px">
			    		<html:text property="address"></html:text>	
			    		<FONT COLOR="FF0000"> <html:errors property="address" /></FONT>
			    		</p>			
						
					</div>	
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.rfc" />
					       </font>
					   </strong>
					   <p style="padding-left: 165px">	
			    		<html:text property="rfc"></html:text>
			    		<FONT COLOR="FF0000"> <html:errors property="rfc" /></FONT>
			    		</p>				
						
					</div>
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.email" />
					       </font>
					   </strong>
					   <p style="padding-left: 165px">	
			    		<html:text property="email"></html:text>
			    		<FONT COLOR="FF0000"> <html:errors property="email" /></FONT>	
			    		</p>			
						
					</div>
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.telephone" />
					       </font>
					   </strong>
					   <p style="padding-left: 165px">	
			    		<html:text property="phone_home"></html:text>	
			    		<FONT COLOR="FF0000"> <html:errors property="phone_home" /></FONT>
			    		</p>			
						
					</div>
					
				</fieldset>


			<CENTER>
			  <P>
			  <input type="submit" value=" Guardar "/>
			
        </P>
			</CENTER>
     
			</html:form>

		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	
</BODY>
</html:html>
