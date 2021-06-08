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
%>


<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE></TITLE>
<html:errors/>
</HEAD>

<BODY>

	<H1> <bean:message key="label.rpt.log" />  </H1>


<logic:present name="list" scope="session">

	<TABLE border="1" class="table_campo">
	    <TR class="table_encabezado">
		  <TD> <bean:message key="label.card.id" /></TD> 
		  <TD> <bean:message key="label.amount" /></TD> 
		  <TD> <bean:message key="label.name" /></TD> 
	    <TR>
	    
	<logic:iterate id="id" indexId="indexId" name="list" scope="session" >
	    <TR>
		  <TD> <bean:write name="id" property="id" /> </TD> 
		  <TD> <bean:write name="id" property="amount" /> </TD> 
		  <TD> <bean:write name="id" property="id_client.name" /> </TD> 
	    <TR>
	</logic:iterate>
	
	
	
	</TABLE>
</logic:present>

<html:link href="javascript:history.back();"> Regresar </html:link>

</BODY>
</html:html>
