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

	<CENTER><H1><bean:message key="label.menu.type.case" /></H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 201;
			    
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

			

			<html:form action="/typeCaseNew">
			<html:hidden property="process" value=""/>
			<CENTER><H3><bean:message key="label.add.type.case" /></H3></CENTER>
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
					
						<TD class="column_field">
							
							<bean:message key="label.description" />
							
						</TD>
						<TD><html:text property="description"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="description"/></FONT> 
					</TR>
					<TR>
						<TD class="column_field"><bean:message key="label.days" /></TD>
						<TD><html:text property="days" size="10"></html:text></TD>
			        		<TD> <FONT COLOR="RED"> <html:errors property="days"/></FONT> 			        		
					</TR>
					<TR>
						<TD></TD>
						<TD><html:submit onclick="process.value='add'"><bean:message key="label.register" /></html:submit></TD>
					</TR>
				</TBODY>
			</TABLE>
			<HR><HR>
			<CENTER><H3><bean:message key="labe.existing.types" /></H3></CENTER>
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD></TD>
						<TD>
							<html:select property="id_type_case" multiple="multiple" size="10">						
								<logic:iterate id="id" indexId="indexId" name="typeslist" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  > 
								     	<bean:write name="id" property="description" /> --- <bean:message key="label.days" />-<bean:write name="id" property="days" />--
								    </OPTION>
						    	</logic:iterate>						
							</html:select>
							<FONT COLOR="RED"> <html:errors property="id_type_case"/>
						</TD>
						<TD><html:submit onclick="process.value='delete'"><bean:message key="label.delete" /></html:submit></TD>
					</TR>
					<TR>
						<TD></TD>
						<TD></TD>
						<TD></TD>
					</TR>
					<TR>
						<TD></TD>
						<TD></TD>
						<TD></TD>
					</TR>
					<TR>
						<TD></TD>
						<TD>
						    <BR>
						    <BR>
						    <BR>
						   <html:button property="a" onclick="javascript:window.close();"><bean:message key="label.close" /></html:button>
						</TD>
						<TD></TD>
					</TR>
				</TBODY>					
			</TABLE>
			
			</html:form>
			
			
			

		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	
</BODY>
</html:html>
