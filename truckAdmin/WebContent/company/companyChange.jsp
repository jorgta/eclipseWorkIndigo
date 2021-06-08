<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*,com.bituos.truckAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"

%>




<html:html>
<HEAD>

	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM WebSphere Studio">
	
	<META http-equiv="Content-Style-Type" content="text/css">
	<LINK href="../theme/Master.css" rel="stylesheet" type="text/css">
	
    <TITLE>
    </TITLE>
</HEAD>

<BODY>

	<H1> <bean:message key="label.company.change" />  </H1>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 64;
			    
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

			

			<html:form action="/companyChange" enctype="multipart/form-data">
			<TABLE border="0" align="center">
				<TBODY>
					<TR>
						<TD><bean:message key="label.name" /></TD>
						<TD><html:text property="name" readonly="true" disabled="true"></html:text>
						<TD> </TD>
					</TR>
					<TR>
						<TD><bean:message key="label.fullname" /></TD>
						<TD><html:text property="fullname" size="60"></html:text>
			        		<TD> <FONT COLOR="RED"> <html:errors property="fullname"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD><bean:message key="label.rfc" /></TD>
						<TD><html:text property="rfc"></html:text></TD>
			        		<TD> <FONT COLOR="RED"> <html:errors property="rfc"/></FONT> 
					</TR>
					<TR>
						<TD><bean:message key="label.address" /></TD>
						<TD><html:text property="address" size="60"></html:text></TD>
			        		<TD> <FONT COLOR="RED"> <html:errors property="address"/></FONT> 
					</TR>
					<TR>
						<TD><bean:message key="label.smtp.server" /></TD>
						<TD><html:text property="smtp_server" size="60"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="smtp_server"/></FONT> 
					</TR>
					<TR>
						<TD><bean:message key="label.smtp.userName" /></TD>
						<TD><html:text property="smtp_user_name" size="60"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="smtp_user_name"/></FONT> 
					</TR>
					<TR>
						<TD><bean:message key="label.smtp.password" /></TD>
						<TD><html:password property="smtp_password" size="60"></html:password></TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="smtp_password"/></FONT> 
					</TR>
					<TR>
						<TD><bean:message key="label.smtp.email" /></TD>
						<TD><html:text property="smtp_email" size="60"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="smtp_email"/></FONT> 
					</TR>
					<TR>
					    <TD class="column_field"><bean:message key="label.smtp.is.secure" /></TD>
					    <TD>
					      <html:checkbox property="smtp_is_secure"></html:checkbox>
			        	  <FONT COLOR="RED"> <html:errors property="smtp_is_secure"/></FONT> 
					    </TD>
					</TR>
					<TR>
						<TD><bean:message key="label.smtp.port" /></TD>
						<TD><html:text property="smtp_port" size="10"></html:text></TD>
		       			<TD> <FONT COLOR="RED"> <html:errors property="smtp_port"/></FONT> 
					<TR>
					<TR>
					    <TD class="column_field"><bean:message key="label.smtp.is.secure" /></TD>
					    <TD>
					      <html:checkbox property="web_site"></html:checkbox>
			        	  <FONT COLOR="RED"> <html:errors property="web_site"/></FONT> 
					    </TD>
					</TR>
					<TR>
						<TD><bean:message key="label.image" /></TD>
						<TD><html:file property="image" size="80"></html:file></TD>
		       			<TD> <FONT COLOR="RED"> <html:errors property="image"/></FONT> 
					<TR>
					
					<TR>
						<TD><bean:message key="label.company.contractSigner" /></TD>
						<TD><html:text property="contractSigner" size="80"></html:text></TD>
		       			<TD> <FONT COLOR="RED"> <html:errors property="contractSigner"/></FONT> 
					<TR>
					<TR>
						<TD><bean:message key="label.company.phones" /></TD>
						<TD><html:text property="phones" size="80"></html:text></TD>
		       			<TD> <FONT COLOR="RED"> <html:errors property="phones"/></FONT> 
					<TR>
					<TR>
						<TD><bean:message key="label.company.streetContract" /></TD>
						<TD><html:text property="streetContract" size="80"></html:text></TD>
		       			<TD> <FONT COLOR="RED"> <html:errors property="streetContract"/></FONT> 
					<TR>
					<TR>
						<TD><bean:message key="label.company.colonyContract" /></TD>
						<TD><html:text property="colonyContract" size="80"></html:text></TD>
		       			<TD> <FONT COLOR="RED"> <html:errors property="colonyContract"/></FONT> 
					<TR>											
					<TR>
						<TD><bean:message key="label.company.cityContract" /></TD>
						<TD><html:text property="cityContract" size="80"></html:text></TD>
		       			<TD> <FONT COLOR="RED"> <html:errors property="cityContract"/></FONT> 
					<TR>
					<TR>
						<TD><bean:message key="label.company.stateContract" /></TD>
						<TD><html:text property="stateContract" size="80"></html:text></TD>
		       			<TD> <FONT COLOR="RED"> <html:errors property="stateContract"/></FONT> 
					<TR>												



					<TR>
						<TD>SALÓN  <html:text property="name" readonly="true" disabled="true" style="border-width:0;font-family: Arial;color: #000000;"></html:text>, INCLUYE:</TD>
						
						<TD></TD>
			        	<TD> <FONT COLOR="RED"> </FONT> </TD>
					</TR>
					<TR>
						<TD></TD>
						<TD><html:text property="additional1" size="60"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> </FONT> </TD>
					</TR>
					<TR>
						<TD></TD>
						<TD><html:text property="additional2" size="60"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> </FONT> </TD>
					</TR>
					<TR>
						<TD></TD>
						<TD><html:text property="additional3" size="60"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> </FONT> </TD>
					</TR>
					<TR>
						<TD></TD>
						<TD><html:text property="additional4" size="60"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> </FONT> </TD>
					</TR>
					<TR>
						<TD></TD>
						<TD><html:text property="additional5" size="60"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> </FONT> </TD>
					</TR>	
					<TR>
						<TD></TD>
						<TD><html:text property="additional6" size="60"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> </FONT> </TD>
					</TR>					
					<TR>
						<TD></TD>
						<TD><html:text property="additional7" size="60"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> </FONT> </TD>
					</TR>	
					<TR>
						<TD></TD>
						<TD><html:text property="additional8" size="60"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> </FONT> </TD>
					</TR>
					<TR>
						<TD></TD>
						<TD><html:text property="additional9" size="60"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> </FONT> </TD>
					</TR>	
					<TR>
						<TD></TD>
						<TD><html:text property="additional10" size="60"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> </FONT> </TD>
					</TR>						
					
					
					
					<TR>
						<TD><bean:message key="label.rpt.name.quote" /></TD>
						<TD><html:text property="nameRptQuote" size="80"></html:text></TD>
		       			<TD> <FONT COLOR="RED"> <html:errors property="nameRptQuote"/></FONT> 
					<TR>												
					<TR>
						<TD><bean:message key="label.rpt.name.sale" /></TD>
						<TD><html:text property="nameRptSale" size="80"></html:text></TD>
		       			<TD> <FONT COLOR="RED"> <html:errors property="nameRptSale"/></FONT> 
					<TR>												
					<TR>
						<TD><bean:message key="label.rpt.name.contract" /></TD>
						<TD><html:text property="nameRptContract" size="80"></html:text></TD>
		       			<TD> <FONT COLOR="RED"> <html:errors property="nameRptContract"/></FONT> 
					<TR>												
					
				</TBODY>
			</TABLE>
			<CENTER>
			  <P>
			    <html:submit> <bean:message key="label.change" /> </html:submit>
			    <BR>
		        <html:button property="finish" onclick="location='/truckAdmin/main.jsp';"> <bean:message key="label.finish" /> </html:button>
			  </P>
			
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

  document.getElementById("fullname").focus();
  
//-->
</SCRIPT>

</html:html>
