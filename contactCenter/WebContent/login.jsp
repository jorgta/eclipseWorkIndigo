<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<HEAD>

<%@ page 
language="java"

contentType="text/html; charset=ISO-8859-1"   

pageEncoding="ISO-8859-1"

session="true"

import="java.util.*, java.io.*, javax.servlet.http.HttpSession.*"

%>

<jsp:include page="noCache.jsp" />


<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="/contactCenter/theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>Login</TITLE>
</HEAD>

<BODY>
<H1 ALIGN="CENTER">LOGIN</H1>


<logic:present name="beanUser" scope="session">
   <logic:redirect href="links.jsp">  </logic:redirect>
</logic:present>

<html:form action="/login">
<TABLE border="0" ALIGN="CENTER">
	<TBODY>
		<TR>
			<TD>Usuario:</TD>
			<TD><html:text property="name"></html:text></TD>
		    <TD> <FONT COLOR="RED"> <html:errors property="name"/></FONT> </TD>
		</TR>
		<TR>
			<TD>Contraseña:</TD>
			<TD><html:password property="password"></html:password></TD>
		    <TD> <FONT COLOR="RED"> <html:errors property="password"/></FONT> </TD>
		</TR>
	</TBODY>
</TABLE>


<CENTER>
<input id="sws" type="image" src="img/button_login.gif" />
</CENTER>
</html:form>

</BODY>
<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

  document.getElementById("name").focus();
  
//-->
</SCRIPT>
</html:html>
