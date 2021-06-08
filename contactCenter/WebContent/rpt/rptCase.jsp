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
<SCRIPT language="JavaScript" src="/contactCenter/getSystemDate.js"></SCRIPT>
<SCRIPT language="Javascript" src="/contactCenter/Calendar/calendar.js"></SCRIPT>
<SCRIPT language="Javascript" src="/contactCenter/Calendar/mycalendar.js"></SCRIPT>
<BODY>

	<CENTER><H1> <bean:message key="label.reports" />  </H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 42;
			    
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
		         
		         String visibleCase= (String)ses.getAttribute("visibleCase"); 
		         String visibleUserData= (String)ses.getAttribute("visibleUserData");   
			  %>

			<html:form action="/rptCase">
			<TABLE border="1" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD class="column_field">
							<bean:message key="label.case.number" />
						</TD>
						<TD align="left" colspan="2">
	 					   	<html:text property="id_case"></html:text>
	    					<FONT COLOR="RED"> <html:errors property="id_case"/></FONT>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			
			<CENTER>
	    	  <html:submit><bean:message key="label.search" /></html:submit>
	    	  <BR>
			  <html:button property="a" onclick="javascript:window.close();"><bean:message key="label.exit" /></html:button>
			</CENTER>

			</html:form>
			<HR>		

		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	<logic:notPresent name="beanUser" scope="session">
	   <logic:redirect href="../login.jsp">  </logic:redirect>
	</logic:notPresent>

</BODY>
</html:html>
