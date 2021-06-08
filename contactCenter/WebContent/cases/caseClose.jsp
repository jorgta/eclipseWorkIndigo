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

	<CENTER><H1> <bean:message key="label.close.case" />  </H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 32;
			    
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
		          
		        String showCaseData = (String)ses.getAttribute("showCaseData");   
		        String id_case = (String)ses.getAttribute("id_caseSelected");
				if(id_case==null)
				  {
				    id_case="";
				  }
			  %>		
			<HR>
			
			<html:form action="/caseClose">
			
			<input type="hidden" name="process" value="" />
			<input type="hidden" name="id_case" value="" />
			<input type="hidden" name="successful" value="?" />
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					
						<TR>
							<TD>
								<TABLE border="1" ALIGN="CENTER">
								<TBODY>
										<TR class="table_encabezado">
					 						<TD><CENTER><bean:message key="label.case.number" /></CENTER></TD>
											<TD><CENTER><bean:message key="label.client" /></CENTER></TD>
											<TD><CENTER><bean:message key="label.short_description" /></CENTER></TD>
											<TD></TD>
											<TD></TD>
											<TD></TD>
										</TR>
										
										<logic:iterate id="id" indexId="indexId" name="list" scope="session" >									
											  <TR>										    
												<TD><A onclick='process.value="caseRead";id_case.value=<bean:write name="id" property="id" />; submit();'><bean:write name="id" property="id" /></A></TD>						
												<TD><A onclick='process.value="caseRead";id_case.value=<bean:write name="id" property="id" />; submit();'><bean:write name="id" property="id_client.name" /></A></TD>						
												<TD><A onclick='process.value="caseRead";id_case.value=<bean:write name="id" property="id" />; submit();'><bean:write name="id" property="short_description" /></A></TD>		
												<TD><A onclick='process.value="caseRead";id_case.value=<bean:write name="id" property="id" />;'><html:submit><bean:message key="label.read.case" /></html:submit></A></TD>
												<TD><A onclick='process.value="caseClose";id_case.value=<bean:write name="id" property="id" />;successful.value="Y";'><html:submit><bean:message key="label.close.case.successful" /></html:submit></A></TD>
												<TD><A onclick='process.value="caseClose";id_case.value=<bean:write name="id" property="id" />;successful.value="N";'><html:submit><bean:message key="label.close.case.unsuccessful" /></html:submit></A></TD>
										      </TR>
						    			</logic:iterate>
				    		    	
								</TBODY>
							</TABLE>
						</TD>
					</TR>
					
				</TBODY>
			</TABLE>
			
			<BR>
			<BR>
			
			<% 
			if ( showCaseData == "yes") 
			{
			%>
				<TABLE align="center">
					<TBODY>
						<TR>
							<TD>
								<P id="caseRead">
									<H3><bean:message key="label.cases.number" /><%=id_case%></H3>
									<html:textarea property="case_description" cols="70" rows="5" readonly="true"></html:textarea>
								
							</TD>
						</TR>
						<TR>
							<TD>
								<H3><bean:message key="label.notes"/></H3>

							    <TABLE class="table_campo" border="1">
							      <TR class="table_encabezado">
								      <TD>
									    <bean:message key="label.date" />
								      </TD>
								      <TD>
									    <bean:message key="label.description" />
								      </TD>
								      <TD>
									    <bean:message key="label.user" />
								      </TD>
							      </TR>
								  <logic:iterate id="id" indexId="indexId" name="notesList" scope="session" >
								      <TR>
									      <TD>
										    <bean:write name="id" property="date_reg" />
									      </TD>
									      <TD>
										    <bean:write name="id" property="note" />
									      </TD>
									      <TD>
										    <bean:write name="id" property="id_user.name" />
									      </TD>
								      </TR>
							      </logic:iterate>						
							    </TABLE>
							</TD>
						</TR>
						<TR>
							<TD>
							  <BR>
							</TD>
						</TR>
						<TR>
							<TD>
								<H3><bean:message key="label.files"/></H3>
							    <TABLE class="table_campo" border="1">
							      <TR class="table_encabezado">
								      <TD>
									    <bean:message key="label.file" />
								      </TD>
								      <TD>
									    <bean:message key="label.description" />
								      </TD>
								      <TD>
									    <bean:message key="label.user" />
								      </TD>
								      <TD>
									    <bean:message key="label.date" />
								      </TD>
							      </TR>
								  <logic:iterate id="id" indexId="indexId" name="fileList" scope="session" >
								      <TR>
									      <TD>
										    <A href='<bean:write name="id" property="fullFileName" />' target="_blank"> <bean:write name="id" property="fileName" /> </A>
									      </TD>
									      <TD>
										    <bean:write name="id" property="description" />
									      </TD>
									      <TD>
										    <bean:write name="id" property="id_user.name" />
									      </TD>
									      <TD>
										    <bean:write name="id" property="date_reg" />
									      </TD>
								      </TR>
							      </logic:iterate>						
							    </TABLE>
							</TD>
						</TR>
					</TBODY>			
				</TABLE>
			<%
			}
			%>

			<CENTER> 
				<html:button property="a" onclick="javascript:window.close();"><bean:message key="label.exit" /></html:button>							
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
</html:html>
