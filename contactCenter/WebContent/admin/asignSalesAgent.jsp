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

	<CENTER><H1><bean:message key="label.user.asign.sales.agent" /></H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 203;
			    
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

			<html:form action="/search">
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD class="column_field"><bean:message key="label.filter" /></TD>
					</TR>
					<TR>
						<TD>
						   <html:hidden property="concept" value="client"/>
						   <html:hidden property="forward" value="asignSalesAgent"/>
						   
						   <html:text property="filter"></html:text>
						</TD>
			        	<TD><FONT COLOR="RED"> <html:errors property="filter"/></FONT></TD> 
					</TR>

					<TR>
					    <TD>
					    	<html:submit><bean:message key="label.search" /></html:submit>
						</TD>
					</TR>

					<TR>
						<TD>						
							<html:select property="filter3" multiple="multiple" size="10">						
								<logic:iterate id="id" indexId="indexId" name="filterList" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  
								    <bean:write name="id" property="id" /> -- <bean:write name="id" property="name" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			
			</html:form>
			<HR>

			<html:form action="/asignSalesAgent">
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD class="column_field"><bean:message key="label.client.number" /></TD>
						<TD><html:text property="id_client"></html:text></TD>
			        		<TD> <FONT COLOR="RED"> <html:errors property="id_client"/></FONT> 
					</TR>
					<TR>
						<TD class="column_field"><bean:message key="label.sales.agent.number" /></TD>
						<TD><html:text property="id_user" size="60"></html:text></TD>
			        		<TD> <FONT COLOR="RED"> <html:errors property="id_user"/></FONT> 
					</TR>
					<TR>
						<TD colspan="2" align="center">						
							<html:submit><bean:message key="label.register" /></html:submit>
						</TD>
					</TR>
					
				</TBODY>
			</TABLE>
			
			<BR>
			<CENTER>
			  <html:button property="a" onclick="javascript:window.close();"><bean:message key="label.exit" /></html:button>
			
			</html:form>

		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	
</BODY>
</html:html>
