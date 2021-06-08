<%@ page session="true" contentType="text/html" import="java.util.*, java.lang.*, javax.servlet.http.HttpSession.*, java.io.*"%>


<DIV style="margin: 6px">


<!-- tpl:insert page="/theme/A_green.htpl" --><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">

<link rel="stylesheet" href="/contactCenter/theme/green.css" type="text/css">
<%-- tpl:put name="headarea" --%>
<TITLE>A_green</TITLE>
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
         <TD valign="top" height="350"><%-- tpl:put name="bodyarea" --%>Descarga de archivos...<BR>
         <BR>
         <BR>

<%
	String fileName = request.getParameter("fileName");
	String msg = request.getParameter("msg");
	String path_server = request.getParameter("path_server") + "/";

	if ( fileName != null )
	  {
		  ///////////////////////////////////////////////////
		  //PATH PARA LEER LOS ARCHIVOS 
		  ///////////////////////////////////////////////////
		  //String PATH_SERVER = "/Tomcat/webapps/files/uploadContactCenter/";
		  //String PATH_SERVER = "/wssdwork/files/WebContent/uploadContactCenter/";

		  response.setContentType("application/x-download");
		  response.setHeader("Content-Disposition", "attachment; filename=" + fileName );
		  
		  ServletOutputStream stream = response.getOutputStream();
		  BufferedInputStream buf = null;

		  //File file = new File( PATH_SERVER + fileName );
		  File file = new File( path_server + fileName );
		  
		  response.setContentLength( (int) file.length() );
		  
		  FileInputStream input = new FileInputStream( file );
		  buf = new BufferedInputStream( input );
		  int readBytes = 0;
		  
		  //read fromn the file; write to the ServletOutputStream
		  while( (readBytes = buf.read()) != -1 )
		    stream.write( readBytes );
		  
		  if (stream != null)
		    stream.close();
		  if ( buf != null)
		     buf.close();
		  
	  }
	
	/*    
	if ( url != null )
		if ( !url.equals("") )
		  {   
		   out.write("<BR>");
		   out.write("<A href=");
		   out.write("'");
		   ///if  ( url.indexOf("?") == -1 )
		   out.write(url);
		   //out.write( "http://localhost:9080" + url );

		   //else
		   //  out.write(url);
		   
		   out.write("'");
		   out.write(" type='multipart' target='_blank'>");
		   
		   if ( msg != null )
		     {
				if ( msg != "" )
				  {   
				   out.write( msg );
				   out.write("</A>");
			     }  
			 }
		   else
		     out.write(">Presione aqui para continuar</A>");
	     }  
	*/


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