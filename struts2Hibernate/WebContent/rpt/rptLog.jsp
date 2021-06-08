<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
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

</HEAD>

<BODY>

	<H1> <bean:message key="label.rpt.log" />  </H1>

<s:if test="#session.list != null"> 


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

</s:if>

<html:link href="javascript:history.back();"> Regresar </html:link>

</BODY>
</html>
