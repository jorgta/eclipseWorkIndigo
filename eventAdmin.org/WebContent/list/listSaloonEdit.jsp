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
<BODY>

	<CENTER><H1> <bean:message key="label.saloon" />  </H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 53;
			    
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
		         
		         String visibleData = (String)ses.getAttribute("visibleData");   
		         
		         BeanSaloon listSaloonEdit_currentSaloon = (BeanSaloon) ses.getAttribute( "listSaloonEdit_currentSaloon" );							    
				 String csString;
				 
				 if ( listSaloonEdit_currentSaloon != null ) 
					 csString = new Integer( listSaloonEdit_currentSaloon.getId() ).toString(); 
				 else
					 csString = "-1";
					 
			  %>

			
			<html:form action="/listSaloonEdit">
			<input type="hidden" name="process" value="" />
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
					    <TD colspan="6" class="column_field"><bean:message key="label.saloon" /></TD>
					</TR>
					<TR>
						<TD>
							<html:select property="id_saloon">						
								<logic:iterate id="id" indexId="indexId" name="listSaloon" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%> 
									    <logic:equal name="id" property="id" value="<%=csString%>"> 
									    		SELECTED
									    </logic:equal>>
								    	<bean:write name="id" property="description" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
			        		<FONT COLOR="RED"> <html:errors property="id_saloon"/></FONT> 
						</TD>
						<TD>
							<html:submit onclick="process.value='selectSaloon';"> <bean:message key="label.select" /> </html:submit>
						</TD>
						<TD>
							<html:submit onclick="process.value='deleteSaloon';"> <bean:message key="label.delete" /> </html:submit>
						</TD>
					    <TD class="column_field"><bean:message key="label.description" /></TD>
						<TD>
							<html:text property="description" size="20" disabled="false"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="description"/></FONT> 
						</TD>
						<TD>
							<html:submit onclick="process.value='createSaloon';"> <bean:message key="label.saloon.create" /> </html:submit>
						</TD>
					</TR>
					<TR>
						<TD></TD>
						<TD></TD>
						<TD></TD>
					    <TD class="column_field"><bean:message key="label.person.min" /></TD>
						<TD>
							<html:text property="min" size="3" disabled="false"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="min"/></FONT> 
						</TD>
						<TD>
							<html:submit onclick="process.value='updateSaloon';"> <bean:message key="label.update.data" /> </html:submit>
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.person.max" /></TD>
						<TD colspan="2">
							<html:text property="max" size="3" disabled="false"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="max"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.12am" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h0"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h0"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.1am" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h1"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h1"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.2am" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h2"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h2"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.3am" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h3"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h3"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.4am" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h4"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h4"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.5am" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h5"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h5"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.6am" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h6"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h6"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.7am" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h7"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h7"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.8am" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h8"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h8"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.9am" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h9"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h9"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.10am" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h10"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h10"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.11am" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h11"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h11"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.12m" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h12"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h12"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.1pm" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h13"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h13"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.2pm" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h14"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h14"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.3pm" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h15"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h15"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.4pm" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h16"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h16"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.5pm" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h17"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h17"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.6pm" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h18"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h18"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.7pm" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h19"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h19"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.8pm" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h20"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h20"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.9pm" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h21"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h21"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.10pm" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h22"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h22"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD colspan="3"></TD>
					    <TD class="column_field"><bean:message key="label.open.11pm" /></TD>
						<TD colspan="2">
					        <html:checkbox property="h23"></html:checkbox>
			        		<FONT COLOR="RED"> <html:errors property="h23"/></FONT> 
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			<CENTER>
			  
	<HR>		  
			</CENTER>			
			
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
