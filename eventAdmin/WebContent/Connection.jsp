<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<HEAD>

<%@ page 
language="java"

contentType="text/html; charset=ISO-8859-1"

pageEncoding="ISO-8859-1"

session="true"

import="java.util.*, java.io.*, javax.servlet.http.HttpSession.*"
%>


<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE></TITLE>
</HEAD>

<BODY>
<H1 ALIGN="CENTER">Connection Details</H1>


<logic:present name="beanUser" scope="session">
   
   <P><SPAN class="table_campo"><SPAN class="table_captura_header"><SPAN
		class="table_capture_field"><SPAN class="table_campo">The object
	'beanUser' does exist.</SPAN></SPAN></SPAN></SPAN><BR>
   </P>
</logic:present>

<logic:notPresent name="beanUser" scope="session">
   
   <P><SPAN class="table_campo"><SPAN class="table_captura_header"><SPAN
		class="table_capture_field"><SPAN class="table_campo"><SPAN
		class="table_capture_field">The object 'beanUser' does not exist</SPAN>.</SPAN></SPAN></SPAN></SPAN><BR>
   </P>
</logic:notPresent>


<%
    HttpSession ses = request.getSession();
%>

Detalles de la conexion:
<BR>
<BR>

<TABLE border="1">
	<TBODY>
		<TR>
			<TD>Duración máxima</TD>
			<TD><%=ses.getMaxInactiveInterval()/60%> minutos</TD>
		</TR>
		<TR>
			<TD>Inicio de sesión</TD>
			<TD><%=new Date(ses.getCreationTime())%></TD>
		</TR>
		<TR>
			<TD>Tiempo consumido de la sesión</TD>
			<TD><%=(ses.getLastAccessedTime() - ses.getCreationTime())/1000/60%> minutos</TD>
		</TR>
		<TR>
			<TD>Tiempo restante de la sesión</TD>
			<TD>
			     <%=(ses.getCreationTime() + ses.getMaxInactiveInterval()*1000 - ses.getLastAccessedTime())/1000/60%> minutos 
			</TD>
		</TR>
		<TR>
			<TD>Última interacción</TD>
			<TD><%=new Date(ses.getLastAccessedTime())%></TD>
		</TR>
	</TBODY>
</TABLE>



</BODY>
</html:html>
