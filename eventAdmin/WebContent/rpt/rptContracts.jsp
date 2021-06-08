<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*, com.eventAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"

%>




<html:html>
<HEAD>

	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM WebSphere Studio">
	
	<META http-equiv="Content-Style-Type" content="text/css">
	<LINK href="/eventAdmin/theme/Master.css" rel="stylesheet" type="text/css">
	
    <TITLE>
    </TITLE>
</HEAD>
<SCRIPT language="JavaScript" src="/eventAdmin/getSystemDate.js"></SCRIPT>
<SCRIPT language="Javascript" src="/eventAdmin/Calendar/calendar.js"></SCRIPT>
<SCRIPT language="Javascript" src="/eventAdmin/Calendar/mycalendar.js"></SCRIPT>
<BODY>

	<CENTER><H1> <bean:message key="label.reports" />  </H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 73;
			    
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

			<html:form action="/rpt">
			 <html:hidden property="process" value="contracts"/>

			<TABLE border="1" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD class="column_field">
							<bean:message key="label.sale.number" />
						</TD>
						<TD align="left" colspan="2">
	 					   	<html:text property="p3"></html:text>
	    					<FONT COLOR="RED"> <html:errors property="p1"/></FONT>
						</TD>
					</TR>
					<TR>
						<TD class="column_field">
							<bean:message key="label.date.begin" />
						</TD>
				    	<TD>						  
						     <html:text readonly="true" property="p1" onclick="javascript:showCal('calendar2');"> </html:text>
						     <A href="javascript:showCal('calendar2')"><IMG src="/eventAdmin/Calendar/calendar.png" border="0" /></A>
						  <SPAN id=date1 style="POSITION: relative"> </SPAN>
					      <FONT COLOR="RED"> <html:errors property="p2"/></FONT>
						</TD>							
						<TD>
						</TD>			        	
			        	
					</TR>				
					<TR>
						<TD class="column_field">
						  <bean:message key="label.date.end" />						  
						</TD>
						<TD>						   
						  <html:text readonly="true" property="p2" onclick="javascript:showCal('calendar3');"></html:text>
						  <A href="javascript:showCal('calendar3')"><IMG src="/eventAdmin/Calendar/calendar.png" border="0" /></A>
						  <SPAN id=date2 style="POSITION: relative"> </SPAN>
			        	  <FONT COLOR="RED"> <html:errors property="p3"/></FONT>
						</TD>
					
						<TD>
						</TD>			        	
			        	
					</TR>

					
					
				</TBODY>
			</TABLE>
			
			<CENTER>
	    	  <html:submit ><bean:message key="label.search" /></html:submit>
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
<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

   document.getElementById("p3").focus();
 
  
//-->
</SCRIPT>
</html:html>
