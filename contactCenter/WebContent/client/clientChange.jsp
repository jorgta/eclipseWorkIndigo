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

	<CENTER><H1> <bean:message key="label.client.change" /> </H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			  	
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );

				//String iffcb = (String) ses.getAttribute( "iffcb" );

			    int TAG = 24;
			    
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
		          
		        String id_state= (String)ses.getAttribute("id_state");
			  %>


			<html:form action="/clientChange">
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD><bean:message key="label.name" /></TD>
						<TD width="486"><html:text property="name" size="80"></html:text></TD>
			        	<TD width="346"><FONT COLOR="RED"> <html:errors property="name"/></FONT></TD> 
					</TR>
					<TR>
						<TD><bean:message key="label.address" /></TD>
						<TD width="486"><html:text property="address" size="80"></html:text></TD>
			        	<TD width="346"><FONT COLOR="RED"> <html:errors property="address"/></FONT></TD> 
					</TR>
					<TR>
						<TD><bean:message key="label.rfc" /></TD>
						<TD width="486"><html:text property="rfc"></html:text></TD>
			        	<TD width="346"><FONT COLOR="RED"> <html:errors property="rfc"/></FONT></TD> 
					</TR>
					<TR>
						<TD><bean:message key="label.email" /></TD>
						<TD width="486"><html:text property="email" size="40"></html:text></TD>
			        	<TD width="346"><FONT COLOR="RED"> <html:errors
							property="email" /></FONT></TD> 
					</TR>
					<TR>
						<TD><bean:message key="label.email.aditional" /></TD>
						<TD width="486"><html:text property="email1" size="40"></html:text></TD>
			        	<TD width="346"><FONT COLOR="RED"> <html:errors
							property="email1" /></FONT></TD> 
					</TR>
					<TR>
						<TD><bean:message key="label.telephone" /></TD>
						<TD width="486"><html:text property="telephone" size="40"></html:text></TD>
			        	<TD width="346"><FONT COLOR="RED"> <html:errors property="telephone"/></FONT></TD> 
					</TR>
					<TR>
						<TD></TD>
							<TD><CENTER><A onclick="submit();" ><IMG src="/contactCenter/img/button_change.gif" border="0" ></A>
								<A onclick="javascript:window.close();" ><IMG src="/contactCenter/img/close.gif" border="0" ></A></CENTER>
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

  document.getElementById("name").focus();
  
//-->
</SCRIPT>
</html:html>
