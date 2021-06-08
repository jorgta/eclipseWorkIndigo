<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<HTML>
<HEAD>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>Salida</TITLE>


</HEAD>
<BODY >

<form action="login.jsp">
<%
    HttpSession ses = request.getSession();
    String address = "http://" + request.getServerName() + ":" + request.getServerPort() + "/gjv/login.jsp";
    ses.removeAttribute( "beanUser" );
    ses.removeAttribute( "processList" );
%>
</form>
<SCRIPT type="text/javascript" LANGUAGE="JavaScript">
	parent.location="<%=address%>";
</SCRIPT>

</BODY>
</HTML>
