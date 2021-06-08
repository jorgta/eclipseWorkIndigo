<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*, com.bituos.bar.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mesas en uso</title>

<style type="text/css">
#wrapper {
  MARGIN: 0px;
  PADDING: 0px;
  WIDTH: 100%;
  MIN-HEIGHT: 100%; 
  BACKGROUND-COLOR: #55649D;
  BORDER-TOP: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
  BORDER-RIGHT: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
  BORDER-LEFT: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
  BORDER-BOTTOM: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
}
* html #wrapper {HEIGHT: 100%;}  /* IE Hack since IE does not support "min-height" in the #wrapper*/
#footer {
  MARGIN: -40px 0px 0px 72px;
  PADDING: 0px;
  WIDTH: 100%;
  HEIGHT: 65px;
  /*BACKGROUND-COLOR: #00FF00;*/
  BORDER-TOP: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
  BORDER-RIGHT: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
  BORDER-LEFT: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
  BORDER-BOTTOM: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
}



</style>
</head>
<body>

<%

HttpSession ses = request.getSession();
BeanAgente beanAgente = (BeanAgente)ses.getAttribute("beanAgente");
%>

<table border="0" align="center">
<tr>
<td colspan="4" align="center">
Mesa
</td>

</tr>

    
		       
		         <%
		          int colCounter = 0;
		         %>
		         <html:form styleClass="form" action="/tableSelect">
			    <input type="hidden" name="id_agent" value="<%= beanAgente.getId() %>" />
			    <input type="hidden" name="process" value="" />
			    <input type="hidden" name="id_table" value="" />
		           <logic:iterate id="id" indexId="indexId" name="listOpenComandas" scope="session" >
		                <%
		                 if (colCounter == 0)
		                	out.write("<tr>");
		         		%>
		          
			                          
			               <td>		                   
					       <div id="wrapper">
					         <a onclick="__doPost('currentTable',<%="'"%><bean:write name="id" property="id_mesa.id" /><%="'"%>)"; href="#"><img src="/bar/images/mesa.jpg"  width="115" height="115" border="0"></a>
					       </div>
		 					<div id="footer"> 				
		 					 <label style="font-size: 30px" ><bean:write name="id" property="id_mesa.id" /></label>
		 					
		 					</div>
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
				   </html:form>
	                   

		      
		   </tr>
		   <tr>
		   <td colspan="4" align="center">
		     
		     <a href="/bar/loginAgent.do?process=getTables&id_agent=<%=beanAgente.getId() %>">Asignar Mesa</a>
		   </td>		   
		   </tr>
		   </table>
		       
		       
		   

</body>

  <script type="text/javascript">
	//<![CDATA[
	var theForm = document.forms['tableSelectForm'];

	function __doPost( eventArgument,value) {
		
       theForm.process.value = eventArgument;	        

      
       if(eventArgument == 'currentTable')
       {	           
           theForm.id_table.value= value; 	
                       	           
       }   
       
 

    
       theForm.submit();
	}
	//]]>
	</script>
</html>