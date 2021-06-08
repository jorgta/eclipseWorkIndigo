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

	<CENTER><H1> <bean:message key="label.case.new" />  </H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 31;
			    
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
		         
		         String visibleCase = (String)ses.getAttribute("visibleCase"); 
		         String visibleUserData = (String)ses.getAttribute("visibleUserData");   
		         String clientName = (String)ses.getAttribute("clientName"); 
		         
		         if ( clientName == null )
		           clientName = new String("");
			  %>

			<html:form action="/search">
			 <html:hidden property="concept" value="case"/>
			 <html:hidden property="forward" value="new"/>
			 <html:hidden property="aditional1" value=""/>
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD colspan="3" align="center">
							<H4><bean:message key="label.search" /></H4>
						</TD>
					</TR>
					<TR>
						<TD class="column_field">
						   <bean:message key="label.name" />
						</TD>
						<TD>						   
						   <html:text property="filter"></html:text>
						</TD>
						<TD>
					    	<html:submit onclick="aditional1.value='name';"><bean:message key="label.search" /></html:submit>
						</TD>
					</TR>
					<TR>
						<TD class="column_field">
							<bean:message key="label.email" />
						</TD>
						<TD>						   
						   <html:text property="filter2"></html:text>
						</TD>
						<TD>
					    	<html:submit onclick="aditional1.value='email';"><bean:message key="label.search" /></html:submit>
						</TD>
			        	
					</TR>
					<TR>
						<TD class="column_field">
						  	<bean:message key="label.address" />
						</TD>
						<TD>						   
						   <html:text property="filter3"></html:text>
						</TD>
						<TD>
					    	<html:submit onclick="aditional1.value='address';"><bean:message key="label.search" /></html:submit>
						</TD>
					</TR>
					<TR>
						<TD class="column_field">
						  	<bean:message key="label.sales.agent" />
						</TD>
						<TD>						   
						   <html:text property="filter4"></html:text>
						</TD>
						<TD>
					    	<html:submit onclick="aditional1.value='sales_agent';"><bean:message key="label.search" /></html:submit>
						</TD>
					</TR>
					<TR>
						<TD></TD>
						<TD colspan="2">					
						    <TABLE border="1" class="table_campo">	
						      <TR class="table_encabezado">
						        <TD><bean:message key="label.client.number" /><TD>
						        <TD><bean:message key="label.name" /><TD>
						        <TD><bean:message key="label.email" /><TD>
						        <TD><bean:message key="label.address" /><TD>
						      </TR>
							  <logic:iterate id="id" indexId="indexId" name="filterList" scope="session" >
							      <TR>
							        <TD> <bean:write name="id" property="id" /> <TD>
							        <TD> <bean:write name="id" property="name" /> <TD>
							        <TD> <bean:write name="id" property="email" /> <TD>
							        <TD> <bean:write name="id" property="address" /> <TD>
							      </TR>
					    	  </logic:iterate>						
						    </TABLE>	
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			
			</html:form>
			<HR>
			
			<html:form action="/caseNew" enctype="multipart/form-data">
			<input type="hidden" name="process" value="" />
			<input type="hidden" name="checkUserData" value="NO" />
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					
					<TR>
						<TD class="column_field"><bean:message key="label.client.number" /></TD>
						<TD width="486">
							
							<%if(visibleUserData == "yes")
								{
								%>
									<html:text property="id_client" size="15" disabled="true"></html:text>
									<BR>
									(<%=clientName%>)
								<%  
								}
							  else
								{
								%>
									<html:text property="id_client" size="15" disabled="false"></html:text>
									<html:submit onclick="process.value='validate';checkUserData.value='no';">Validar</html:submit>
								<%  
								}
							%>
							
							
							
						</TD>
			        	<TD width="346"><FONT COLOR="RED"> <html:errors property="id_client"/></FONT></TD>
					</TR>
					
					<% if ( visibleCase == "yes") 
					{
					%>
					<TR>
						<TD class="column_field"><bean:message key="label.short_description" /></TD>
						<TD width="486"><html:text property="short_description"></html:text></TD>
			        	<TD width="346"><FONT COLOR="RED"> <html:errors property="short_description"/></FONT></TD> 
					</TR>
					<TR>
						<TD class="column_field"><bean:message key="label.description" /></TD>
						<TD width="486"><html:textarea property="description" cols="75" rows="10"></html:textarea></TD>
			        	<TD width="346"><FONT COLOR="RED"> <html:errors property="description"/></FONT></TD> 
					</TR>
					<TR>
						<TD class="column_field"><bean:message key="label.type.case" /></TD>
						<TD>						
							<html:select property="id_type_case">						
								<logic:iterate id="id" indexId="indexId" name="typeCaseList" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  
								    	<bean:write name="id" property="description" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
						</TD>
						<TD></TD>
					</TR>
					<TR>
						<TD class="column_field"><bean:message key="label.file.description" /></TD>
						<TD>
							<html:text property="fileDescription" size="40"></html:text>
						</TD>
			        	<TD> 
			        	 	<FONT COLOR="RED"> <html:errors property="fileDescription"/></FONT> 
						</TD>
					</TR>
					<TR>
						<TD class="column_field"><bean:message key="label.file" /></TD>
						<TD>
			        	   <FONT COLOR="RED"><html:errors property="file"/></FONT>
			        		<BR>
						   <html:file property="file" size="80"></html:file>
						</TD>
					</TR>																				

					<TR> 
						<TD></TD>
						<TD>
							<%if(visibleUserData == "yes")
							{
							%>
							<html:submit onclick="process.value='register';checkUserData.value='yes';"><bean:message key="label.register" /></html:submit>						
							<%  
							}
							else
							{
							%>
							<html:submit onclick="process.value='register';checkUserData.value='no';"><bean:message key="label.register" /></html:submit>
							<%  
							}
							%>
						</TD>
						<TD></TD>
					</TR>
					<%  
					}
					%>
					
				</TBODY>
			</TABLE>
			<CENTER>
			  
			 
			  
			  <BR>
  		      
  		     <html:button property="a" onclick="javascript:window.close();"><bean:message key="label.exit" /></html:button>
	<HR>
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
