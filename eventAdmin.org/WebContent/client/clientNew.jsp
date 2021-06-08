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
	<META name="GENERATOR" content="IBM WebSphere Studio">
	
	<META http-equiv="Content-Style-Type" content="text/css">
	<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
	
    <TITLE>
    </TITLE>
</HEAD>

<BODY>

	<H1> <bean:message key="label.client.new" />  </H1>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 21;
			    
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

			

			<html:form action="/clientNew">
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD class="table_capture_field"><bean:message key="label.name" /></TD>
						<TD class="table_capture_field"><html:text property="name" size="60"></html:text></TD>
			        		<TD> <FONT COLOR="RED"> <html:errors property="name"/></FONT> 
					</TR>
					<TR>
						<TD class="table_capture_field"><bean:message key="label.rfc" /></TD>
						<TD class="table_capture_field"><html:text property="rfc"></html:text></TD>
			        		<TD> <FONT COLOR="RED"> <html:errors property="rfc"/></FONT> 
					</TR>
					<TR>
						<TD class="table_capture_field"><bean:message key="label.address" /></TD>
						<TD class="table_capture_field"><html:text property="address" size="60"></html:text></TD>
			        		<TD> <FONT COLOR="RED"> <html:errors property="address"/></FONT> 
					</TR>
					<TR>
						<TD class="table_capture_field"><bean:message key="label.phone" /></TD>
						<TD class="table_capture_field"><html:text property="phone" size="60"></html:text></TD>
			        		<TD> <FONT COLOR="RED"> <html:errors property="phone"/></FONT> 
					</TR>
					<TR>
						<TD class="table_capture_field"><bean:message key="label.email" /></TD>
						<TD class="table_capture_field"><html:text property="email" size="60"></html:text></TD>
			        		<TD> <FONT COLOR="RED"> <html:errors property="email"/></FONT> 
					</TR>
					<TR>
						<TD class="table_capture_field"><bean:message key="label.company" /></TD>
						<TD class="table_capture_field"><html:text property="company" size="60"></html:text></TD>
			        		<TD> <FONT COLOR="RED"> <html:errors property="company"/></FONT> 
					</TR>
				</TBODY>
			</TABLE>
			<CENTER>
			  <P>
			  <html:submit> <bean:message key="label.register" /> </html:submit>
  		      <BR>
		      <html:button property="finish" onclick="location='/eventAdmin/main.jsp';"> <bean:message key="label.finish" /> </html:button>
			  </P>
			</CENTER>			
			
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
