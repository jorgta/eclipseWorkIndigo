<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="/crystal-tags-reportviewer.tld" prefix="Web_Reporting_Tag_Library" %>
<%@ page 
language="java"
contentType="text/html;charset=UTF-8"
pageEncoding="ISO-8859-1"
%>

<%@ page session="true" import="java.util.*, com.eventAdmin.*,com.eventAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>


<jsp:useBean id="crystal_rptname" type="java.lang.String" scope="session"></jsp:useBean>

<%  
    HttpSession ses = request.getSession();
    String report = (String) ses.getAttribute( "crystal_rptname" );
    //String reportSourceVar = (String) ses.getAttribute( "crystal_rptname" ) + "?rand=" + (new Random()).nextDouble();
    String reportSourceVar = (String) ses.getAttribute( "crystal_rptname" );
    int i = 1;
%>


<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">
//alert("<%=report%>");
//-->
</script>


<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>Report</TITLE>
</HEAD>
<BODY>

<Web_Reporting_Tag_Library:viewer viewerName="<%=report%>"
	reportSourceVar="<%=reportSourceVar%>" 
	reportSourceType="reportingComponent"
	displayToolbarExportButton="true" 
	displayToolbarPrintButton="true" 
	displayToolbarRefreshButton="true" 
	displayToolbarCrystalLogo="false"
	allowDrillDown="true"
	isOwnPage="true">
<Web_Reporting_Tag_Library:report reportName="<%=report%>"></Web_Reporting_Tag_Library:report>
</Web_Reporting_Tag_Library:viewer>
</BODY>
</HTML>
