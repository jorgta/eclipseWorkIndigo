<%@ page session="true" contentType="text/html" import="java.util.*, java.lang.*, javax.servlet.http.HttpSession.*"%>

<DIV style="margin: 6px">


<!-- tpl:insert page="/theme/A_green.htpl" --><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" href="/contactCenter/theme/green.css" type="text/css">
<%-- tpl:put name="headarea" --%>
<TITLE>ok</TITLE>
<%-- /tpl:put --%>
</HEAD>
<BODY>
<TABLE width="760" cellspacing="0" cellpadding="0" border="0">
   <tbody>
      <TR>
         <TD valign="top">
         <TABLE class="header" cellspacing="0" cellpadding="0" border="0" width="100%">
            <tbody>
               <TR>
                  <TD width="150"><IMG border="0" width="150" height="55" alt="Company's LOGO" src="/contactCenter/theme/logo_green.gif"></TD>
                  <TD></TD>
               </TR>
            </tbody>
         </TABLE>
         </TD>
      </TR>
      <TR>
         <TD valign="top" height="20" class="nav_head"></TD>
      </TR>
      <TR class="content-area">
         <TD valign="top" height="350"><%-- tpl:put name="bodyarea" --%>La información ha sido registrada satisfactoriamente<BR>
<%
	String url = (String) request.getSession().getAttribute( "done" );
	String msg = (String) request.getSession().getAttribute( "msg" );

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
		     out.write(url);

		   else
		     out.write(url);
		   
		   out.write("");
		   out.write(">Presione aqui para continuar</A>");
	     }  
%>

					<BR><%-- /tpl:put --%></TD>
      </TR>
      <TR>
         <TD valign="top" height="20" class="footer"></TD>
      </TR>
   </tbody>
</TABLE>
</BODY>
</HTML><!-- /tpl:insert -->