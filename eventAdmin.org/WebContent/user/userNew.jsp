<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">-->



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
	<META name="GENERATOR" content="IBM WebSphere Studio">
	
	<META http-equiv="Content-Style-Type" content="text/css">
	<!--<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">-->
		<script language='javascript' src="/eventAdmin/calendar/popcalendar.js"></script> 
	<script language='javascript' src="/eventAdmin/calendar/popcalendar.js"></script> 
	
	
	<link rel="stylesheet" type="text/css" href="/eventAdmin/theme/dropdown.css">
	<script type="text/javascript" src="/eventAdmin/theme/helpers.js"></script>
	<script type="text/javascript" src="/eventAdmin/theme/date.js"></script>
	<script type="text/javascript" src="/eventAdmin/theme/form.js"></script>
	
	<link rel="stylesheet" href="theme/gh-buttons.css">
	
    <TITLE>
    </TITLE>
  <style>
  
   .textBox
   {
     height:30px; 
	 width:300px
   }
  </style>
    
    
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
		          response.sendRedirect( "../login.jsp");
		        else
		          found = 0;
			  %>

			
<H1> <bean:message key="label.user.new"/> </H1>
			<html:form action="/userNew">

			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD><bean:message key="label.name" /></TD>
						<TD><html:text property="name" size="60"></html:text></TD>
			        		<TD> <FONT COLOR="RED"> <html:errors property="name"/></FONT> 
					</TR>
					<TR>
						<TD><bean:message key="label.password" /></TD>
						<TD><html:password property="password"></html:password></TD>
			        		<TD> <FONT COLOR="RED"> <html:errors property="password"/></FONT> 
					</TR>
					<TR>
						<TD><bean:message key="label.address" /></TD>
						<TD><html:text property="address" size="60"></html:text></TD>
			        		<TD> <FONT COLOR="RED"> <html:errors property="address"/></FONT> 
					</TR>
					<TR>
						<TD><bean:message key="label.email" /></TD>
						<TD><html:text property="email" size="30"></html:text></TD>
			        		<TD> <FONT COLOR="RED"> <html:errors property="email"/></FONT> 
					</TR>
				</TBODY>
			</TABLE>
			<CENTER><P>
			
			<html:submit styleClass="button icon user"  > <bean:message key="label.register" /> </html:submit>
			</P></CENTER>			
			<CENTER><P><html:button styleClass="button icon user" property="finish" onclick="location='/eventAdmin/main.jsp';"> <bean:message key="label.finish" /> </html:button> </P></CENTER>	
			
                    
		 		
				
			</html:form>

		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	
</BODY>

<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

  document.getElementById("name").focus();
  
//-->
</SCRIPT>

</html:html>
