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

	<H1> <bean:message key="label.process.admin" /> </H1>
	
	<logic:present name="beanUser" scope="session">
	

		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 200;
			    
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



			    <%
					BeanUser processAdmin_currentUser = (BeanUser) ses.getAttribute( "processAdmin_currentUser" );							    
					String cuString = new Integer( processAdmin_currentUser.getId() ).toString(); 
	
					BeanProfile processAdmin_currentProfile = (BeanProfile) ses.getAttribute( "processAdmin_currentProfile" );							    
					String cpString = new Integer( processAdmin_currentProfile.getId() ).toString(); 
				%>

<%  //users vs process %>

			  <TABLE align="center">
		                <html:form action="/processAdmin">
							    
	                    		<html:hidden property="action" value="userChange"/>
	                    		<html:hidden property="process" value="0"/>
							    
								
								<TR>
									<TD>							    
									</TD>							    
									<TD>							    
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
							     	</TD>
									<TD>
										<TABLE>
											<TR>
										    	<TD>
											 		<font color=blue>Permisos para usuario:  <bean:write name="processAdmin_currentUser" property="name" /></font>
												</TD>
											</TR>
											<TR>
												<TD>
													<bean:message key="label.to.change.user" />
												</TD>
											</TR>
										</TABLE>
									</TD>
								</TR>
								
						</html:form>
			  </TABLE>


			  <TABLE align="center">
		                <html:form action="/processAdmin">
			                    <html:hidden property="user" value=""/>
			                    <html:hidden property="action" value="userAllow"/>

								<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">
								document.forms(1).user.value = '<bean:write name="processAdmin_currentUser" property="id" />';
								//-->
								</SCRIPT>
							<TR>
								<TD>							    
								  <bean:message key="label.processes.denied" />
								</TD>							    
								<TD>
									<html:select property="process">
										<logic:iterate id="id" indexId="indexId" name="processAdmin_listDeny" scope="session" >
										    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  <bean:write name="id" property="description" /> </OPTION>
									    </logic:iterate>
									</html:select> 
								</TD>
								<TD>
									<html:submit><bean:message key="label.alow" /></html:submit>
									
							    </TD>
							</TR>
						</html:form>					
						

		                <html:form action="/processAdmin">
				                    <html:hidden property="user" value="nothing"/>
				                    <html:hidden property="action" value="userDeny"/>

									<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">
									document.forms(2).user.value = '<bean:write name="processAdmin_currentUser" property="id" />';
									//-->
									</SCRIPT>
				                    
							
							<TR>
								<TD>							    
								  <bean:message key="label.processes.allowed" />
								</TD>							    
								<TD>
									<html:select property="process"  >
										<logic:iterate id="id" indexId="indexId" name="processAdmin_listAllow" scope="session" >
										    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  <bean:write name="id" property="description" /> </OPTION>
									    </logic:iterate>
									</html:select> 
								</TD>
								<TD>									
									<html:submit><bean:message key="label.deny" /></html:submit>
								</TD>
							</TR>
													
						</html:form>

			  </TABLE>
			  


<%  //create and delete profiles %>
			  
<HR>
<!------------------------------------------------------------------------------------>
<HR>

<H1> <bean:message key="label.profiles" /> </H1>

			  <TABLE align="center">
		                <html:form action="/processAdmin">
		                <html:hidden property="action" value="profileDelete"/>
			            <html:hidden property="process" value="0"/>
							    				   								
								<TR>
									<TD>							    
									  <bean:message key="label.profiles" />
									</TD>							    
									<TD>							    
				        		        <FONT COLOR="RED"> <html:errors property="profileDelete"/></FONT> 
						        		<html:select property="profileDelete">
											<logic:iterate id="id" indexId="indexId" name="processAdmin_profileList" scope="session" >
											    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  <bean:write name="id" property="name" /> </OPTION>
										    </logic:iterate>
									 	</html:select> 
							     	</TD>
									<TD>
										<html:submit><bean:message key="label.delete" /></html:submit>
								    </TD>
								</TR>
								
						</html:form>

		                <html:form action="/processAdmin">
	                    <html:hidden property="action" value="profileNew"/>

						<TR>
							<TD>							    
							  <bean:message key="label.profile" />
							</TD>							    
							<TD>
			                    <html:text property="profileNew" value=""/>
			        		    <FONT COLOR="RED"> <html:errors property="profileNew"/></FONT> 
							</TD>
							<TD>
								<html:submit><bean:message key="label.register" /></html:submit>
						    </TD>
						</TR>
						</html:form>					
			  </TABLE>


<%  //profiles vs processes %>

<HR>
<!------------------------------------------------------------------------------------>
<HR>

<H1> <bean:message key="label.profiles.vs.processes" /> </H1>


			  <TABLE align="center">
		                <html:form action="/processAdmin">
		                <html:hidden property="action" value="profileAsignProcess"/>
			            <html:hidden property="process" value="0"/>
							    				   								
								<TR>
									<TD>							    
									  <bean:message key="label.profiles" />
									</TD>							    
									<TD>							    
				        		        <FONT COLOR="RED"> <html:errors property="profileAsignProcess_id_profile"/></FONT> 
						        		<html:select property="profileAsignProcess_id_profile" onchange="action.value='profileAsignChange'; submit();">
											<logic:iterate id="id" indexId="indexId" name="processAdmin_profileList" scope="session" >
											    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%> 
									    			<logic:equal name="id" property="id" value="<%=cpString%>"> 
									    				SELECTED
									    			</logic:equal>>
											    <bean:write name="id" property="name" /> </OPTION>
										    </logic:iterate>
									    </html:select> 
							     	</TD>
									<TD>							    
									  <bean:message key="label.processes.denied" />
									</TD>							    
									<TD>							    
				        		        <FONT COLOR="RED"> <html:errors property="profileAsignProcess_id_process"/></FONT> 
						        		<html:select property="profileAsignProcess_id_process">
											<logic:iterate id="id" indexId="indexId" name="processAdmin_profileProcessDenyList" scope="session" >
											    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  <bean:write name="id" property="name" /> </OPTION>
										    </logic:iterate>
									    </html:select> 
							     	</TD>
									<TD>
										<html:submit><bean:message key="label.allow" /></html:submit>
								    </TD>
								</TR>
								

								<TR>
									<TD>							    
									</TD>							    
									<TD>							    
							     	</TD>
									<TD>							    
									  <bean:message key="label.processes.allowed" />
									</TD>							    
									<TD>							    
				        		        <FONT COLOR="RED"> <html:errors property="profileDeleteProcess_id_process"/></FONT> 
						        		<html:select property="profileDeleteProcess_id_process">
											<logic:iterate id="id" indexId="indexId" name="processAdmin_profileProcessAsignedList" scope="session" >
											    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  <bean:write name="id" property="name" /> </OPTION>
										    </logic:iterate>
									    </html:select> 
							     	</TD>
									<TD>
										<html:submit onclick="action.value='profileDeleteProcess'; process.value='0';"><bean:message key="label.delete" /></html:submit>																		    </TD>
								</TR>
								


						</html:form>

			  </TABLE>









<%  //users vs users %>

<HR>
<!------------------------------------------------------------------------------------>
<HR>

<H1> <bean:message key="label.users.vs.users" /> </H1>

			  <TABLE align="center">



			                <html:form action="/processAdmin">
		                    <html:hidden property="user" value="nothing"/>
		                    <html:hidden property="action" value="userAsignProfileUserAdd"/>
								<TR>
									<TD>
									</TD>
									<TD>
										<html:submit><bean:message key="label.add" /></html:submit>
									</TD>
									<TD>
									</TD>
								</TR>
														
								<TR>
									<TD>
										<html:select property="profileUser_id_userFrom"  >
											<logic:iterate id="id" indexId="indexId" name="processAdmin_listUsers" scope="session" >
											    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  <bean:write name="id" property="name" /> </OPTION>
										    </logic:iterate>
										</html:select> 
									</TD>
									<TD>
									</TD>
									<TD>
										<html:select property="profileUser_id_userTo"  >
											<logic:iterate id="id" indexId="indexId" name="processAdmin_listUsers" scope="session" >
											    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  <bean:write name="id" property="name" /> </OPTION>
										    </logic:iterate>
										</html:select> 
									</TD>
								</TR>
								<TR>
									<TD>
									</TD>
									<TD>
										<html:submit  onclick="action.value='userAsignProfileUserAsign';"><bean:message key="label.asign" /></html:submit>

									</TD>
									<TD>
									</TD>
								</TR>
														
							</html:form>

			  </TABLE>


<%  //profiles vs users %>

<HR>
<!------------------------------------------------------------------------------------>
<HR>

<H1> <bean:message key="label.profiles.vs.users" /> </H1>

			  <TABLE align="center">

			                <html:form action="/processAdmin">
		                    <html:hidden property="action" value="profileAsignUserAsign"/>

							<TR>
						    	<TD>
								</TD>
						    	<TD>
									<logic:present name="processAdmin_profileAsignUser_currentProfile" scope="session">
							 		  <font color=blue>Perfil actual:  <bean:write name="processAdmin_profileAsignUser_currentProfile" property="name" /></font>
							 		</logic:present>
								</TD>
						    	<TD>
								</TD>
							</TR>

							<TR>
								<TD>
								    <bean:message key="label.user" />
					        		<html:select property="profileUser_id_user" onchange="action.value='profileAsignUserChangeUser';submit();">
										<logic:iterate id="id" indexId="indexId" name="processAdmin_listUsers" scope="session" >
								    		<OPTION value="<bean:write name="id" property="id" />"
								    			<logic:equal name="id" property="id" value="<%=cuString%>"> 
								    				SELECTED
								    			</logic:equal>>
								    			<bean:write name="id" property="name" />
								    		</option>
							    		</logic:iterate>
									</html:select> 

								</TD>
								<TD>
								    <bean:message key="label.profiles" />

									<html:select property="profileUser_id_profile">
										<logic:iterate id="id" indexId="indexId" name="processAdmin_profileList" scope="session" >
										    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  <bean:write name="id" property="name" /> </OPTION>
									    </logic:iterate>
									</html:select> 

								</TD>
								<TD>
									<html:submit><bean:message key="label.asign" /></html:submit>
							    </TD>
							</TR>
							</html:form>	
							<TR>
								<TD>
								    <BR>
								    <BR>
								    <BR>
									<html:button property="a" onclick="javascript:window.close();"><bean:message key="label.exit" /></html:button>								
								</TD>							
							</TR>				
						
			  </TABLE>
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
