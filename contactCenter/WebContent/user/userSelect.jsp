<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*, com.tecunsa.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"

%>




<html:html>
<HEAD>

	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM WebSphere Studio">
	
	<META http-equiv="Content-Style-Type" content="text/css">
	<LINK href="/contactCenter/theme/Master.css" rel="stylesheet" type="text/css">
	
    <TITLE>
    </TITLE>
</HEAD>

<BODY>

	<CENTER><H1> <bean:message key="label.user.select" />  </H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 14;
			    
			    List list = (List) ses.getAttribute("processList");
			    Iterator iter = list.iterator();
			    BeanProcessUsers beanProcessUser;
			    int found = 0;
			    
			    while( iter.hasNext() )
			      {
			        beanProcessUser = (BeanProcessUsers) iter.next(); 
			        
			        if ( beanProcessUser.getId_process().getId() == TAG )
			          {
			            found = 1;
			            break;
			          }
			      } 
		        if ( found == 0 )
		          response.sendRedirect( "../login.jsp");
		        else
		          found = 0;
			  %>

			

			<html:form action="/userSelect">
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR align="center">
						<TD>
						<html:select property="id">
						
						<logic:iterate id="id" indexId="indexId" name="list" scope="session" >
						    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  
						    <bean:write name="id" property="id" />-<bean:write name="id" property="name" /></OPTION>
					    </logic:iterate>

						
						</html:select> 
							
						</TD>
						
					</TR>
					<TR align="center">
					    <TD>
					    	<html:submit onclick="submit();"><bean:message key="label.select" /></html:submit>
						</TD>
					</TR>
					<TR align="center">
						<TD>
			           		<html:button property="a" onclick="javascript:window.close();"><bean:message key="label.exit" /></html:button>			           			    
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			
			</html:form>

		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	<logic:notPresent name="beanUser" scope="session">
	   <logic:redirect href="../login.jsp">  </logic:redirect>
	</logic:notPresent>
	
</BODY>
<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

  document.getElementById("id").focus();
  
//-->
</SCRIPT>
</html:html>
