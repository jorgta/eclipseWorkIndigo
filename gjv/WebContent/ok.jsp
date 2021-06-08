<%@ page session="true" contentType="text/html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" href="/gjv/theme/green.css" type="text/css">
<title>ok</title>
</head>
<body>
<table width="760" cellspacing="0" cellpadding="0" border="0">
   <tbody>
      <tr>
         <td valign="top">
         <table class="header" cellspacing="0" cellpadding="0" border="0" width="100%">
            <tbody>
               <tr>
                  <td width="150"><img border="0" width="100" height="76" alt="Company's LOGO"  src="/gjv/images/logo.png"></td>
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

					<BR><%-- /tpl:put --%></td>
      </tr>
      <tr>
         <td valign="top" height="20" class="footer"></td>
      </tr>
   </tbody>
</table>
</body>
</html>