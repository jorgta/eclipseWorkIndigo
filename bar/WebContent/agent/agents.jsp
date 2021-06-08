<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*, com.bituos.bar.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%

%>
<center>
<table border="1">

		         <%
		          int colCounter = 0;
		         %>
           <logic:iterate id="id" indexId="indexId" name="agenteList" scope="session" >
                        <%
		                 if (colCounter == 0)
		                	out.write("<tr>");
		         		%>

		       
		       <td >	
		         <table cellpadding="0" cellspacing="0">
		           <tr>	                   
			          	<td valign="top" align="center">		                   
		       			<bean:write name="id" property="nombre" />
		       			</td>
			        </tr>
		           <tr>	
		             <td valign="top">                   
			     		 <a href="./form/loginagent.jsp?id=<bean:write name="id" property="id" />"><img src="./image.jsp?id=<bean:write name="id" property="id" />"  width="115" border="0"></a>
		             </td>
		           </tr>
		         </table>
		       </td>		                   

		      
		      <% if (colCounter == 3)
		       		{
                	  out.write("</tr>");
                	  colCounter = 0;
		       		}
		         else
		        	 colCounter++;
                %>
		       
		       
		   </logic:iterate>
		   </table>
		   </center>
</body>
</html:html>