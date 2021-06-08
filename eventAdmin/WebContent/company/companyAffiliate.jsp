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
	<LINK href="/eventAdmin/theme/Master.css" rel="stylesheet" type="text/css">
	
    <TITLE>
    </TITLE>
</HEAD>

<BODY background="/eventAdmin/img/background.jpg">

	<html:form action="/companyAffiliate" enctype="multipart/form-data">
	<TABLE border="0" ALIGN="CENTER">
		<TBODY class="table_campo">
			<TR class="table_encabezado">
			   <TD colspan="3">
				<bean:message key="label.company.leave.your.data" /> 
			   </TD>
			</TR>
			<TR>
				<TD class="table_encabezado"><bean:message key="label.company" /></TD>
				<TD><html:text property="company" size="60"></html:text></TD>
        		<TD> <FONT COLOR="RED"> <html:errors property="company"/></FONT> 
			</TR>
			<TR>
				<TD class="table_encabezado"><bean:message key="label.client.name" /></TD>
				<TD><html:text property="name" size="60"></html:text></TD>
        		<TD> <FONT COLOR="RED"> <html:errors property="name"/></FONT> 
			</TR>
			<TR>
				<TD class="table_encabezado"><bean:message key="label.client.email" /></TD>
				<TD><html:text property="email"></html:text></TD>
       			<TD> <FONT COLOR="RED"> <html:errors property="email"/></FONT> 
			</TR>
			<TR>
				<TD class="table_encabezado"><bean:message key="label.email.reenter" /></TD>
				<TD><html:text property="emailreenter"></html:text></TD>
       			<TD> <FONT COLOR="RED"> <html:errors property="emailreenter"/></FONT> 
			</TR>
			<TR>
				<TD class="table_encabezado"><bean:message key="label.phone" /></TD>
				<TD><html:text property="phone"></html:text></TD>
       			<TD> <FONT COLOR="RED"> <html:errors property="phone"/></FONT> 
			</TR>
			<TR>
				<TD class="table_encabezado"><bean:message key="label.comments" /></TD>
				<TD><html:textarea property="comments" cols="50" rows="4"></html:textarea></TD>
       			<TD> <FONT COLOR="RED"> <html:errors property="comments"/></FONT> 
			</TR>
			<TR>
			    <TD colspan="2" align="CENTER" ><html:submit ><bean:message key="label.register" /></html:submit>
				</TD>
			</TR>
			<TR>
			    <TD colspan="2" align="center">
			      <html:button property="finish" onclick="location='/eventAdmin/main.jsp'">	 <bean:message key="label.finish" /> </html:button>
			    </TD>
			</TR>
		</TBODY>
	</TABLE>
	
	</html:form>
	
</BODY>

<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

  document.getElementById("company").focus();
  top.resizeTo(600,570);
  window.scrollbars.visible = false;

  
//-->
</SCRIPT>


</html:html>
