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

%>


<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<TITLE><bean:message key="label.enter" /></TITLE>
</HEAD>
<BODY>

<html:form action="/login">
<TABLE border="0" ALIGN="CENTER">
	<TBODY>
		<TR>
			<TD><bean:message key="label.user" /></TD>
			<TD><html:text property="username" size="10"></html:text></TD>
			<TD><FONT COLOR="RED"> <html:errors property="username"/></FONT></TD>
		</TR>
		<TR>
			<TD><bean:message key="label.password" /></TD>
			<TD><html:password property="password" size="10"></html:password></TD>
			<TD><FONT COLOR="RED"> <html:errors property="password"/></FONT></TD>
		</TR>
	</TBODY>
</TABLE>
<CENTER>
	<P>
		<IMG width="25" height="25" src="/gjv/img/bullet_key.png" border="0">
		<html:submit><bean:message key="label.enter" /></html:submit>
	</P>
</CENTER>
</html:form>


</BODY>
<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

  document.getElementById("username").focus();
  
//-->
</SCRIPT>

</html:html>