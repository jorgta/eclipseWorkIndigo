<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML XHTML 1.0 Transitional//EN">

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
		<link rel="stylesheet" href="theme/gh-buttons.css">
		<link href="/eventAdmin/GridViewCSSThemes/YahooGridView.css" rel="stylesheet" type="text/css" />
	
    <TITLE>
    </TITLE>
</HEAD>

<BODY>

	<H1> <bean:message key="label.user.change" />  </H1>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 14;
			    
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

			

			<html:form action="/userChange">
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD><bean:message key="label.name" /></TD>
						<TD><html:text property="name" readonly="true" disabled="true" size="60"></html:text>
			        		<TD> <FONT COLOR="RED"> <html:errors property="name"/></FONT> 
						</TD>
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
			
			 <button class="button icon approve" type="submit"  ><bean:message key="label.change" /> </button> 
					   
			
			</P></CENTER>			
	        <CENTER>
	        <a href="#" class="button danger" onclick="location='/eventAdmin/main.jsp';" ><bean:message key="label.finish" /></a>
					
	       
			</html:form>

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
