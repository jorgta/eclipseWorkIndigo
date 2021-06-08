<%@ page session="true" contentType="text/html" import="java.util.*, java.lang.*, javax.servlet.http.HttpSession.*"%>

<DIV style="margin: 6px">

<jsp:useBean id="msgError" type="java.lang.String" scope="session"></jsp:useBean>


<!-- tpl:insert page="theme/A_green.htpl" --><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" href="/captura/theme/green.css" type="text/css">
<%-- tpl:put name="headarea" --%>
<title>Error</title>
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
                  <td width="150"><img border="0" width="100" height="76" alt="Company's LOGO" src="/gjv/images/logo.png"></td>
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
         <td valign="top" height="350"><%-- tpl:put name="bodyarea" --%>
					<FONT size="4" COLOR=RED>La información NO pudo ser procesada	debido al siguiente error</FONT>:<BR>

<BR>
<%
    HttpSession ses = request.getSession();
    
	String msg = (String) ses.getAttribute( "msgError" );
%>
<FONT class="error" > <%= msg %> </FONT>
<BR>
<%
	String url = (String) request.getSession().getAttribute( "done" );
	if ( url != null )
		if ( url != "" )
		  {   
		   out.write("<A href=");
//		   out.write("");
		     out.write(url);
//		   if  ( url.indexOf("?") == -1 )
//		     out.write(url + "?" + Math.random() );
//		   else
//		     out.write(url + "&" + Math.random() );
//		   out.write("");
		   out.write(">");
		   out.write("<BR>");
		   out.write("Presione aqui para continuar</A>");
		   //out.write("</FONT>");
		   
		   
		  }  
%><BR>
					<BR>
		<%-- /tpl:put --%></td>
      </tr>
      <tr>
         <td valign="top" height="20" class="footer"></td>
      </tr>
   </tbody>
</table>
</body>
</html><!-- /tpl:insert -->