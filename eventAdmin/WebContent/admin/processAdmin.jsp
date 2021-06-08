<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*,com.eventAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
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

	<H1> <bean:message key="label.process.admin" /> </H1>
	
	<logic:present name="beanUser" scope="session">
	

		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 100;
			    
			    List list = (List) ses.getAttribute("processList");
			    Iterator iter = list.iterator();
			    BeanProcessUsers beanProcessUser;
			    int found = 0;

			    while( iter.hasNext() )
			      {
			        beanProcessUser = (BeanProcessUsers) iter.next(); 
			        
			        
			        //int b = beanProcessUser.getId_process().getId(); 

	                //out.write( "alert(" + b + ")" );
			        
			        //if ( b == TAG )
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
<table align="center">
		                <html:form action="/processAdmin">
							    
							    <%
									BeanUser processAdmin_currentUser = (BeanUser) ses.getAttribute( "processAdmin_currentUser" );							    
									String cuString = new Integer( processAdmin_currentUser.getId() ).toString(); 
								%>
							    
								
								<tr>
									<td>							    
						        		<html:select property="user" onchange="submit();">
											<logic:iterate id="id" indexId="indexId" name="processAdmin_listUsers" scope="session" >
									    		<OPTION value="<bean:write name="id" property="id" />"
									    			<logic:equal name="id" property="id" value="<%=cuString%>"> 
									    				SELECTED
									    			</logic:equal>>
									    			<bean:write name="id" property="name" />
									    		</option>
								    		</logic:iterate>
										</html:select> 
			                    		<html:hidden property="action" value="userChange"/>
			                    		<html:hidden property="process" value="0"/>
			                    		<BR>
								         <bean:message key="label.to.change.user" />
							     	</td>
								</tr>
								
						</html:form>
<tr><td><br /></td></tr>
<tr><td><br /></td></tr>
</table>

<table align="center">
		                <html:form action="/processAdmin">
				                    <html:hidden property="user" value="nothing"/>

									<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">
									document.forms(1).user.value = '<bean:write name="processAdmin_currentUser" property="id" />';
									//-->
									</script>
				                    
				                    <html:hidden property="action" value="userDeny"/>
							
							<tr>
								<td>
								  Procesos permitidos
								</td>
								<td>
									<html:select property="process"  >
										<logic:iterate id="id" indexId="indexId" name="processAdmin_listAllow" scope="session" >
										    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  <bean:write name="id" property="description" /> </OPTION>
									    </logic:iterate>
									</html:select> 
								</td>
								<td>
									<A onclick="submit();" ><img src="/eventAdmin/img/button_deny.gif" border="0" ></A>
<!--								    <html:submit ><bean:message key="label.deny" /></html:submit> -->
								</td>
							</tr>
													
						</html:form>

			                <html:form action="/processAdmin">
				                    <html:hidden property="user" value=""/>

									<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">
									document.forms(2).user.value = '<bean:write name="processAdmin_currentUser" property="id" />';
									//-->
									</script>
							<tr>
								<td>
								  Procesos NO permitidos
								</td>
								<td>
				                    <html:hidden property="action" value="userAllow"/>
									<html:select property="process">
										<logic:iterate id="id" indexId="indexId" name="processAdmin_listDeny" scope="session" >
										    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  <bean:write name="id" property="description" /> </OPTION>
									    </logic:iterate>
									</html:select> 
								</td>
								<td>
									<A onclick="submit();" ><img src="/eventAdmin/img/button_allow.gif" border="0" ></A>
							    </td>
							</tr>
							<tr><td><br /></td></tr>
							</html:form>
</table>

<table align="center">
							<tr>
								<td>									
		      						<html:button property="finish" onclick="location='/eventAdmin/main.jsp';"> <bean:message key="label.finish" /> </html:button>
								</td>
							</tr>
</table>
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
