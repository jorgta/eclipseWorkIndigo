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
<TITLE><bean:message key="label.user" />: <bean:write name="beanUser" property="name" /></TITLE>
<script language="JavaScript" src="treeMenuCode.js"></script>

</HEAD>


<logic:present name="beanUser" scope="session" >





	<frameset COLS="16%,80%" frameborder="no" framespacing=0>
	
		<frameset ROWS="100%,0%" frameborder="no" framespacing=1>
	  		<frame name="menuFrame" src="menu.jsp" marginheight=10 marginwidth=5 scrolling=auto>
	  		<!--  <frame name="companyLogo" src="companyLogo.jsp" marginheight=20 marginwidth=20 scrolling=auto>-->
		</frameset>
		
  		<frame name="mainFrame" src="main.jsp" marginheight=20 marginwidth=20 scrolling=auto>
	</frameset>



</logic:present>
<logic:notPresent name="beanUser" scope="session" >
	<logic:redirect href="login.jsp"></logic:redirect>
</logic:notPresent>


</html:html>
