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
	<LINK href="/contactCenter/theme/Master.css" rel="stylesheet" type="text/css">
	
    <TITLE>
    </TITLE>
</HEAD>
<BODY>

	<CENTER><H1> <bean:message key="label.sign.contract" />  </H1></CENTER>
	
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

			<html:form action="/sale">
			<html:hidden property="process" value="signContract"/>
			<TABLE border="1" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD class="column_field">
							<bean:message key="label.sale.number" />
						</TD>
						<TD align="left" colspan="2">
	 					   	<html:text property="id_sale"></html:text>
	    					<FONT COLOR="RED"> <html:errors property="id_sale"/></FONT>
						</TD>
					</TR>
					<TR>
					    <TD class="column_field">
					    	<bean:message key="label.email.optional" />
					    </TD>
						<TD>						   
						   <html:text property="anotherEmail"></html:text>
						   <FONT COLOR="RED"> <html:errors property="anotherEmail"/></FONT>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			
			<CENTER>
	    	  <html:submit><bean:message key="label.continue" /></html:submit>
	    	  <BR>
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

   document.getElementById("id_sale").focus();
 
  
//-->
</SCRIPT>

</html:html>
