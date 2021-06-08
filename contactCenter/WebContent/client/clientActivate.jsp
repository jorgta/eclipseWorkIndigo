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

	<CENTER><H1> <bean:message key="label.client.activate" />  </H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 23;
			    
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
						<TD><bean:message key="label.filter" /></TD>
					</TR>
					<TR>
						<TD>
						   <html:hidden property="concept" value="client"/>
						   <html:hidden property="forward" value="activate"/>
						   
						   <html:text property="filter"></html:text>
						</TD>
			        	<TD><FONT COLOR="RED"> <html:errors property="filter"/></FONT></TD> 
					</TR>

					<TR>
					    <TD>
					    	<A onclick="submit();" ><IMG src="/contactCenter/img/button_search.gif" border="0" ></A>
						</TD>
					</TR>

					<TR>
						<TD>
						<html:select property="filter3" multiple="multiple"
							size="10">
							<logic:iterate id="id" indexId="indexId" name="filterList"
								scope="session">
								<OPTION value='<%="'"%>'
									<bean:write name="id" property="id" />=""><bean:write name="id"
									property="id" /> -- <bean:write name="id"
									property="name" /></OPTION>
							</logic:iterate>
						</html:select>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			
			</html:form>


		<HR>
		<html:form action="/clientActivate">
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR ALIGN="CENTER">
						<TD><bean:message key="label.client.number" /><BR>
						<html:text property="id" value="" size="9"></html:text><BR>
			        		 <FONT COLOR="RED"> <html:errors property="id"/></FONT></TD> 
					</TR>
					<TR ALIGN="CENTER">
						<TD><A onclick="submit();" ><IMG src="/contactCenter/img/button_activate.gif" border="0" ></A></TD>
					</TR>
					<TR ALIGN="CENTER">
					    <TD>
					      <A onclick="javascript:window.close();" ><IMG src="/contactCenter/img/close.gif" border="0" ></A>
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

  document.getElementById("filter").focus();
  
//-->
</SCRIPT>
</html:html>
