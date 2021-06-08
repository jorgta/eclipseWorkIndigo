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
			    
			    int TAG = 41;
			    
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

			<html:form action="/rptCases">
			 <html:hidden property="concept" value=""/>

			<TABLE border="1" ALIGN="CENTER">
				<TBODY>
					<TR >
						<TD>
						</TD>
						<TD colspan="2" class="column_field">
							<bean:message key="label.filter" />
						</TD>
						<TD class="column_field">
							<bean:message key="label.filter.activate" />
						</TD>
					</TR>
					<TR>
						<TD class="column_field">
						   <bean:message key="label.user" />
						</TD>
						<TD align="left" colspan="2">
	 					   	<html:text property="id_user"></html:text>
	    					<FONT COLOR="RED"> <html:errors property="id_user"/></FONT>
						</TD>
						<TD>
							<html:select property="filter_id_user" >
								<OPTION value= '0'><bean:message key="label.no" /></OPTION>						    	
								<OPTION value= '1'><bean:message key="label.yes" /></OPTION>
							</html:select>					
						</TD>
					</TR>
					<TR>
						<TD class="column_field"><bean:message key="label.client" /></TD>
						<TD align="left" colspan="2">
	 					   	<html:text property="id_client"></html:text>
	    					<FONT COLOR="RED"> <html:errors property="id_client"/></FONT>
						</TD>
						<TD>
							<html:select property="filter_id_client" >
								<OPTION value= '0'><bean:message key="label.no" /></OPTION>						    	
								<OPTION value= '1'><bean:message key="label.yes" /></OPTION>
							</html:select>					
						</TD>
					</TR>
					<TR>
						<TD class="column_field"><bean:message key="label.date" /></TD>
						<TD align="left">
						  <bean:message key="label.from" />
						  <BR>


						  <html:text property="date_begin"> </html:text>
						  <A href="javascript:showCal('calendar1')"><IMG src="/contactCenter/Calendar/calendar.png" border="0" /></A>
						  <SPAN id=date1 style="POSITION: relative"> </SPAN>
					      <FONT COLOR="RED"> <html:errors property="date_begin"/></FONT>
						</TD>							

						<TD>						   
						  <bean:message key="label.to" />
						  <BR>
						  <html:text property="date_end"></html:text>
						  <A href="javascript:showCal('calendar2')"><IMG src="/contactCenter/Calendar/calendar.png" border="0" /></A>
						  <SPAN id=date2 style="POSITION: relative"> </SPAN>
			        	  <FONT COLOR="RED"> <html:errors property="date_end"/></FONT>
						</TD>
						<TD>
							<html:select property="filter_date" >
								<OPTION value= '0'><bean:message key="label.no" /></OPTION>						    	
								<OPTION value= '1'><bean:message key="label.yes" /></OPTION>
							</html:select>					
						</TD>
						<TD>
						</TD>			        	
			        	
					</TR>

					<TR>
						<TD class="column_field"><bean:message key="label.case.status" /></TD>
						<TD colspan="2">						
							<html:select property="id_type_case_status" >						
								<logic:iterate id="id" indexId="indexId" name="typeCaseStatusList" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  
								    <bean:write name="id" property="description" /> 
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
						</TD>
						<TD>
							<html:select property="filter_id_type_case_status" >
								<OPTION value= '0'><bean:message key="label.no" /></OPTION>						    	
								<OPTION value= '1'><bean:message key="label.yes" /></OPTION>
							</html:select>					
						</TD>
						<TD>
						</TD>
					</TR>
					<TR>
						<TD class="column_field"><bean:message key="label.address" /></TD>
						<TD align="left" colspan="2">
	 					   	<html:text property="address"></html:text>
	    					<FONT COLOR="RED"> <html:errors property="address"/></FONT>
						</TD>
						<TD>
							<html:select property="filter_address" >
								<OPTION value= '0'><bean:message key="label.no" /></OPTION>						    	
								<OPTION value= '1'><bean:message key="label.yes" /></OPTION>
							</html:select>					
						</TD>
						<TD>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			
			<CENTER>
	    	  <html:submit onclick="concept.value='date';"><bean:message key="label.search" /></html:submit>
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
