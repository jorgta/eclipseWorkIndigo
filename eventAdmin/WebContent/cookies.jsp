<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>cookies.jsp</TITLE>
</HEAD>
<BODY>
<logic:present name="beanUser" scope="session">
   
   <P><SPAN class="table_campo"><SPAN class="table_captura_header"><SPAN
		class="table_capture_field"><SPAN class="table_campo">The object
	'beanUser' does exist.</SPAN></SPAN></SPAN></SPAN><BR>
   </P>
</logic:present>

<logic:notPresent name="beanUser" scope="session">
   
   <P><SPAN class="table_campo"><SPAN class="table_captura_header"><SPAN
		class="table_capture_field"><SPAN class="table_campo"><SPAN
		class="table_capture_field">The object 'beanUser' does not exist</SPAN>.</SPAN></SPAN></SPAN></SPAN><BR>
   </P>
</logic:notPresent>


<P> Para el buen funcionamiento de esta pagina, favor de cambiar la configuracion de su explorador<BR>de internet<BR>Internet Explorer:<BR>-abra el Internet Explorer<BR>-Vaya al menu Herramientas<BR>-Seleccione &quot;Opciones de Internet&quot;<BR>
-Selecccion el tab &quot;General&quot;<BR>
-En el recuadro &quot;Archivos temporales de Internet&quot;, seleccione &quot;Configuracion&quot;<BR>
-Seleccione la opcion &quot;Cada vez que se visita la pagina&quot;<BR>
-Clic en el boton &quot;aceptar&quot;<BR>
-Clic en el boton &quot;aceptar&quot;<BR></P>

</BODY>
</HTML>
