<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ page session="true" import="java.util.*, 
								java.io.*,
								java.sql.*"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Seleccione Mesero</title>

</head>
<body>



		<logic:present name="agentelist" scope="session">
			<table>
				<logic:iterate id="id" indexId="indexId" name="agentelist" scope="session" >
					<tr>
					<TD>
						<bean:write name="id" property="id" />
					</TD>
					<TD>
						<bean:write name="id" property="nombre" />
					</TD>
					<TD>
					<img src='<bean:write name="id" property="foto" />' width="115" border="0">
					</TD>
					
		            </tr>
		        </logic:iterate>
			</table>
		</logic:present>



</body>
</html:html>