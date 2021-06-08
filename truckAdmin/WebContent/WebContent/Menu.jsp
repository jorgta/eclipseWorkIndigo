<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<HEAD>

<%@ page session="true" import="java.util.*,com.bituos.truckAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>

<%@ page 
language="java"

contentType="text/html; charset=ISO-8859-1"

pageEncoding="ISO-8859-1"

%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="/truckAdmin/theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>Administracion</TITLE>
<script language="JavaScript" src="MenuJavascript.js">
//-->
</script>


</HEAD>
<logic:present name="beanUser" scope="session" >

	<noscript>
		Necesita activar JavaScript en su navegador para poder ver el Menú de opciones.
		<BR />
		<A href = "logout.jsp" > <img src="img/button_logout.gif" border="0"> </A>
	</noscript>
	
	<body bgcolor="#ffffff" onload="parent.treeMenuDisplay();">
	</body>

</logic:present>

<logic:notPresent name="beanUser" scope="session" >
	<logic:redirect href="login.jsp"></logic:redirect>
</logic:notPresent>

</html:html>
