<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<html:html>

<HEAD>

				

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=windows-1252">
<META NAME="Generator" CONTENT="Microsoft FrontPage 5.0">
<TITLE>Administracion</TITLE>
<META NAME="Versión" CONTENT="8.0.3514">
<META NAME="Fecha" CONTENT="4/12/96">
</HEAD>
<BODY TEXT="#000000" LINK="#0000ff" VLINK="#800080" BGCOLOR="#ffffff">


<%
	
    String address = "http://" + request.getServerName() + ":" + request.getServerPort() + "/contactCenter/login.jsp";
%>

<P><A HREF="<%=address%>" TARGET="_blank" ><font size="2">Sistema de administracion.</font></A></P>


<P><A href="contact.jsp" target="principal"><FONT size="2">Contacto</FONT></A><BR>


<BR />

<P><A HREF="main.jsp" TARGET="principal"><font size="2">Inicio</font></A></P>
<P><A HREF="mailto:dsa1972@yahoo.com"><IMG SRC="img/3demail.gif" BORDER=0 WIDTH=49 HEIGHT=48 ALT="Envíame un e-mail, lo estoy esperando!"></A></P>
<FONT SIZE=1>Si quiere chatear con el creador. En estos momentos</FONT><A HREF="http://edit.yahoo.com/config/send_webmesg?.target=dsa1972&amp;.src=pg"><IMG SRC="http://opi.yahoo.com/online?u=dsa1972&m=g&t=2" BORDER=0 WIDTH=125 HEIGHT=25 /></A>

</BODY>
</html:html>