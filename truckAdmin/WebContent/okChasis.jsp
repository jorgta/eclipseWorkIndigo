<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" contentType="text/html" import="java.util.*, java.lang.*, javax.servlet.http.HttpSession.*"%>

<DIV style="margin: 6px">


<!-- tpl:insert page="/theme/A_green.htpl" --><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" href="/truckAdmin/theme/green.css" type="text/css">
<%-- tpl:put name="headarea" --%>
<title>ok</title>
<%-- /tpl:put --%>
</head>
<body>
<table width="760" cellspacing="0" cellpadding="0" border="0">
   <tbody>
      <tr>
         <td valign="top">
         <table class="header" cellspacing="0" cellpadding="0" border="0" width="100%">
            <tbody>
               <tr>
                  <td width="150"><img border="0" width="150" height="55" alt="Companys LOGO" src='<bean:write name="beanUser" property="id_company.logo_file_name"/>'  ></td>
                  <td></td>
               </tr>
            </tbody>
         </table>
         </td>
      </tr>
      <tr>
         <td valign="top" height="20" class="nav_head"></td>
      </tr>
      <tr class="content-area">
         <td valign="top" height="350"><%-- tpl:put name="bodyarea" --%>La información ha sido registrada satisfactoriamente<BR>
<%
	String url = (String) request.getSession().getAttribute( "done" );
	String msg = (String) request.getSession().getAttribute( "msg" );
	String htmlTable = (String) request.getSession().getAttribute( "htmlTable" );
	
	if ( msg != null )
		if ( msg != "" )
		  {   
		   out.write( (String) request.getSession().getAttribute( "msg" ) );
		   out.write( "<BR>" );
		   
		   
		   request.getSession().removeAttribute( "msg" );
	     }  

	if ( url != null )
		if ( url != "" )
		  {   
		   out.write("<A href=");
		   out.write("");
		   if  ( url.indexOf("?") == -1 )
		     out.write(url + "?" + Math.random() );
		   else
		     out.write(url + "&" + Math.random() );
		   
		   out.write("");
		   out.write(">Presione aqui para continuar</A>");
	     }  
	
%>
<center>
<% 
	if(htmlTable != "")
	     out.write( htmlTable);
%>
</center>

					<BR><%-- /tpl:put --%></td>
      </tr>
    
      
      <tr>
         <td valign="top" height="20" class="footer"></td>
      </tr>
   </tbody>
</table>


</body>
</html><!-- /tpl:insert -->