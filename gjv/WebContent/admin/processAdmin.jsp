<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*, com.bituos.gjv.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>




<html:html>
<HEAD>

	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM WebSphere Studio">
	
	<META http-equiv="Content-Style-Type" content="text/css">

    <TITLE>
    </TITLE>
    <style>
    


form {
	background:#fff;
	padding:1em;
	border:1px solid #eee;
}

form {
	margin:1em;
	width:27em;
}

    .login {
	background:url(/gjv/theme/user.gif) no-repeat 95% 1em;
			}
	fieldset div 
	{
	margin:0.3em 0;
	clear:both;
}

legend {
	color:#0b77b7;
	font-size:1.2em;
}
legend span {
	width:10em;
	text-align:right;
}

fieldset {
	border:1px solid #ddd;
	padding:0 0.5em 0.5em;
}

    </style>
</HEAD>

<BODY>



	
	<logic:present name="beanUser" scope="session">
	

		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 201;
			    
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
					 //System.out.println(processAdmin_currentProfile.getName());
					String cpString = new Integer( processAdmin_currentProfile.getId() ).toString(); 
				   //String cpString ="1";
				%>

<%  //users vs process %>
<fieldset class="login" >
	<strong>
	<H1><legend><bean:message key="label.process.admin" /></legend> </H1>
	</strong>
	
			  
		                <html:form action="/processAdmin" style="width:100%;">
					<fieldset class="login" >
				        <strong>
			             <legend>Usuarios</legend> 
			           </strong>
							
			    			 
	                    		<html:hidden property="action" value="userChange"/>
	                    		<html:hidden property="process" value="0"/>
							    
								
								<tr>
									<td>							    
									</td>							    
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
							     	</td>
									<td>
										<table>
											<tr>
										    	<td>
											 		<font color=blue>Permisos para usuario:  <bean:write name="processAdmin_currentUser" property="name" /></font>
												</td>
											</tr>
											<tr>
												<td>
													<bean:message key="label.to.change.user" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
						</fieldset>		
						</html:form>
			  


			  
		                <html:form action="/processAdmin" style="width:100%;">
		                <fieldset class="login" >
				        <strong>
			             <legend>Procesos</legend> 
			           </strong>
			                    <html:hidden property="user" value=""/>
			                    <html:hidden property="action" value=""/>
                                <html:hidden property="process" value=""/>

								<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">
								document.forms(1).user.value = '<bean:write name="processAdmin_currentUser" property="id" />';
								//-->
								</script>
							<tr>
								<td>							    
								  
								</td>							    
								<td>
			
								</td>
								<td>
									
									
							    </td>
							</tr>
							<script type="text/javascript">
							function selectProc(chk,action)
							{
							 // var form  = document.forms[0];
							  document.forms[0].action.value=action;
							  document.forms[0].process.value=chk.value;
			            	  document.forms[0].submit();
			          		}
							
							</script>
						<table>
						
			             <%int i =0,j=3; String style="";%>
			                    <logic:iterate id="id" indexId="indexId" name="processAdmin_listDeny" scope="session" >
								  	
								  <%
									  style= "margin-left:"+ String.valueOf(j) + "px;";
									  if(i==0)
										  out.println("<tr>");
									  else if(i > 2)
									  {
										  out.println("<tr>");
										  i=0;
									  }
										
									  i++;
								 %>
									 <td>
									  <input style="<%= style %>"; onclick="selectProc(this,'userAllow');" type="checkbox" name="option1" value=<%="'"%><bean:write name="id" property="id" /><%="'"%>><bean:write name="id" property="description"/> 
									 </td> 
							      
							    </logic:iterate>
							    </table>
							    <%i =0;%>
							    <table>
						        <logic:iterate id="id" indexId="indexId" name="processAdmin_listAllow" scope="session" >				 
							 	   <%
									  style= "margin-left:"+ String.valueOf(j) + "px;";
									  if(i==0)
										  out.println("<tr>");
									  else if(i > 2)
									  {
										  out.println("<tr>");
										  i=0;
									  }
										
									  i++;
								   %>
								   <td>
								    <input style="<%= style %>"; onclick="selectProc(this,'userDeny');" type="checkbox" name="option2" checked value=<%="'"%><bean:write name="id" property="id" /><%="'"%>><bean:write name="id" property="description"/> 
									</td>
						
										     
								</logic:iterate>
						
						</table>
				                    

									<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">
									document.forms(2).user.value = '<bean:write name="processAdmin_currentUser" property="id" />';
									//-->
									</script>
					

				
			    		
				  </fieldset>
			   				
				
				</html:form>

			</fieldset>  
			  


<%  //create and delete profiles %>
			  
<HR>
<!------------------------------------------------------------------------------------>
<HR>



			   <fieldset class="login" >
				        <strong>
			             <H1><legend><bean:message key="label.profiles" /></legend></H1> 
			           </strong>
		               <html:form action="/processAdmin">
		                <html:hidden property="action" value="profileDelete"/>
			            <html:hidden property="process" value="0"/>
			            
							   <table>			   								
								<tr>
									<td>							    
									  <bean:message key="label.profiles" />
									</td>							    
									<td>							    
				        		        <FONT COLOR="RED"> <html:errors property="profileDelete"/></FONT> 
						        		<html:select property="profileDelete">
											<logic:iterate id="id" indexId="indexId" name="processAdmin_profileList" scope="session" >
											    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  <bean:write name="id" property="name" /> </OPTION>
										    </logic:iterate>
									 	</html:select> 
							     	</td>
									<td>
										<html:submit><bean:message key="label.delete" /></html:submit>
								    </td>
								</tr>
								</table>
								
						</html:form>

		                <html:form action="/processAdmin">
	                    <html:hidden property="action" value="profileNew"/>
                         <table>
						<tr>
							<td>							    
							  <bean:message key="label.profile" />
							</td>							    
							<td>
			                    <html:text property="profileNew" value=""/>
			        		    <FONT COLOR="RED"> <html:errors property="profileNew"/></FONT> 
							</td>
							<td>
								<html:submit><bean:message key="label.register" /></html:submit>
						    </td>
						</tr>
						</table>
						</html:form>
				</fieldset>
		                
		           	

			        		  
 					

<%  //profiles vs processes %>

<HR>
<!------------------------------------------------------------------------------------>
<HR>


							<script type="text/javascript">
					     	function selectProfile(proc,chk,action)
							{

			            	  
			          		}
			          	</script>


		                <html:form action="/processAdmin" style="width:100%">
		                <fieldset class="login" >
				        <strong>
			             <H1><legend><bean:message key="label.profiles.vs.processes" /></legend></H1> 
			           </strong>
		                <html:hidden property="action" value="profileAsignProcess"/>
			            <html:hidden property="process" value="0"/>
			           
			            
		
							   <table >			   								
								<tr>	
								<td>						    
									  <bean:message key="label.profiles" />
															    
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
								</td>
								<td style="padding-left: 20px">
								     <bean:message key="label.processes.denied"/>
									<FONT COLOR="RED"> <html:errors property="profileAsignProcess_id_process"/></FONT> 
						        		<html:select property="profileAsignProcess_id_process" >
											<logic:iterate id="id" indexId="indexId" name="processAdmin_profileProcessDenyList" scope="session" >
											    <OPTION value=<%="'"%><bean:write name="id" property="id" /><%="'"%> >  <bean:write name="id" property="description" /> </OPTION>
										    </logic:iterate>
									    </html:select> 
							     	</td>
									<td>
										<html:submit><bean:message key="label.allow" /></html:submit>
									</td>							

								     </tr>
							      <tr>
							      <td>
							      </td>
							      <td style="padding-left: 38px"> <bean:message key="label.processes.allowed" />
															    
				        		        <FONT COLOR="RED"> <html:errors property="profileDeleteProcess_id_process"/></FONT> 
						        		<html:select  property="profileDeleteProcess_id_process">
											<logic:iterate id="id" indexId="indexId" name="processAdmin_profileProcessAsignedList" scope="session" >
											    <OPTION value=<%="'"%><bean:write name="id" property="id" /><%="'"%> >  <bean:write name="id" property="description" /> </OPTION>
										    </logic:iterate>
									    </html:select> 
						      </td>
							      <td>
							       <html:submit onclick="action.value='profileDeleteProcess'; process.value='0';"><bean:message key="label.delete" /></html:submit>																		    
							      </td>								
							      </tr>
							   
								</table>

														    
									 
							     	
										
							

					</fieldset>
						</html:form>

			  









<%  //users vs users %>

<HR>
<!------------------------------------------------------------------------------------>
<HR>



			  
			   <fieldset class="login" >
				        <strong>
			             <H1><legend><bean:message key="label.users.vs.users" /></legend></H1> 
			           </strong>



			                <html:form action="/processAdmin">
		                    <html:hidden property="user" value="nothing"/>
		                    <html:hidden property="action" value="userAsignProfileUserAdd"/>
								<tr>
									<td>
									</td>
									<td>
										<html:submit><bean:message key="label.add" /></html:submit>
									</td>
									<td>
									</td>
								</tr>
														
								<tr>
									<td>
										<html:select property="profileUser_id_userFrom"  >
											<logic:iterate id="id" indexId="indexId" name="processAdmin_listUsers" scope="session" >
											    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  <bean:write name="id" property="name" /> </OPTION>
										    </logic:iterate>
										</html:select> 
									</td>
									<td>
									</td>
									<td>
										<html:select property="profileUser_id_userTo"  >
											<logic:iterate id="id" indexId="indexId" name="processAdmin_listUsers" scope="session" >
											    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  <bean:write name="id" property="name" /> </OPTION>
										    </logic:iterate>
										</html:select> 
									</td>
								</tr>
								<tr>
									<td>
									</td>
									<td>
										<html:submit  onclick="action.value='userAsignProfileUserAsign';"><bean:message key="label.asign" /></html:submit>

									</td>
									<td>
									</td>
								</tr>
														
							</html:form>

			  </fieldset>


<%  //profiles vs users %>

<HR>
<!------------------------------------------------------------------------------------>
<HR>


			   <fieldset class="login" >
				        <strong>
			             <H1><legend><bean:message key="label.profiles.vs.users" /></legend></H1> 
			           </strong>
			  

			                <html:form action="/processAdmin">
		                    <html:hidden property="action" value="profileAsignUserAsign"/>
							<table>
							<tr>
						    	<td>
								</td>
						    	<td>
									<logic:present name="processAdmin_profileAsignUser_currentProfile" scope="session">
							 		  <font color=blue>Perfil actual:  <bean:write name="processAdmin_profileAsignUser_currentProfile" property="name" /></font>
							 		</logic:present>
								</td>
						    	<td>
								</td>
							</tr>

							<tr>
								<td>
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

								</td>
								<td>
								    <bean:message key="label.profiles" />

									<html:select property="profileUser_id_profile">
										<logic:iterate id="id" indexId="indexId" name="processAdmin_profileList" scope="session" >
										    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  <bean:write name="id" property="name" /> </OPTION>
									    </logic:iterate>
									</html:select> 

								</td>
								<td>
									<html:submit><bean:message key="label.asign" /></html:submit>
							    </td>
							</tr>
							</table>
							</html:form>	
							<TR>
								<TD>
								    <BR>
								    <BR>
								    <BR>
								    
								</TD>							
							</TR>				
						
			  </fieldset>
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
