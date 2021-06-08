<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<HEAD>

<%@ page session="true" import="java.util.*,com.emptyProject.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>

<%@ page 
language="java"

contentType="text/html; charset=ISO-8859-1"

pageEncoding="ISO-8859-1"

%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>Pantalla principal.</TITLE>
</HEAD>

<BODY>

<form action="login.jsp">
<%
    HttpSession ses = request.getSession();
    String address = "http://" + request.getServerName() + ":" + request.getServerPort() + "/emptyProject/login.jsp";
    ses.removeAttribute( "beanUser" );
    ses.removeAttribute( "processList" );
%>
</form>
<SCRIPT type="text/javascript" LANGUAGE="JavaScript">
	parent.location="<%=address%>";
</SCRIPT>

</BODY>

</html:html>
