<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<HEAD>

<%@ page session="true" import="java.util.*, com.bituos.gjv.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>

<%@ page 
language="java"

contentType="text/html; charset=ISO-8859-1"

pageEncoding="ISO-8859-1"

%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>Pantalla principal.</TITLE>

<%
	String process = request.getParameter("process");
	String action = request.getParameter("action");
	String target = request.getParameter("target");
%>

</HEAD>

<logic:present name="beanUser" scope="session" >
				
<body>
			<form name="preForm" method="post" action=<%=action%> target=<%=target%> >
				<input type="hidden" name="process" value="<%=process%>" />
				
					<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">
					<!--
					

							preForm.submit();

					// -->
					</SCRIPT>
					
			</form>
</body>
</logic:present>

<logic:notPresent name="beanUser" scope="session" >
	<logic:redirect href="logout.jsp"></logic:redirect>
</logic:notPresent>

</html:html>
