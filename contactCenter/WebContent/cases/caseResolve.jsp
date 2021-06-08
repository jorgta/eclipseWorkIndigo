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

<SCRIPT LANGUAGE="JavaScript">	
		box = ("structure.checked");
		box.checked = false;	
		
		function processAndSubmit(process)
		  {		       	    	    
		   //alert( process );		    
		   document.forms(0).process.value = process;
		   document.forms(0).submit();
		  }
		  
		/*function changeVisibility( process,visible)
		  {
		  //alert(process); 		  
		  if(visible=="visible")
		     document.getElementById( process ).style.visibility="visible";
		     else
		     document.getElementById( process ).style.visibility="hidden";
		  		  }*/
		  		  
		function countChars()
		  {		       	    	    
		   //alert( process );		    
		   document.forms(0).notes_char_count.value = document.forms(0).notes.value.length+1;
		  }
</SCRIPT>

<BODY>

	<CENTER><H1><bean:message key="label.cases.review" /></H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 33;
			    
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
		          
				 String id_case = (String)ses.getAttribute("id_caseSelected");
				 if(id_case==null)
				   {
				     id_case="";
				   }

				 String id_note = (String)ses.getAttribute("id_noteSelected");
				 if(id_note==null)
				   {
				     id_note="";
				   }
				 
				 String case_description = (String)ses.getAttribute("case_description");
				 if(case_description==null)
				   {
				     case_description="";
				   }
				   
				 String id_order_selected = (String)ses.getAttribute("id_orderSelected");
				 if(id_order_selected==null)
				   {
				     id_case="";
				   }

				 //list = (List)ses.getAttribute("typeCaseList");
				 
				 
		  // <logic:equal name="id" property="id" value="<%=id_case% 
		  //  >"> 
			//			    				SELECTED
			//			    			/logic:equal>
			  %>


			<HR>
			
			<jsp:useBean id="control_panel" class="com.tecunsa.Beans.BeanControlPanel" scope="session"/>
			

			<html:form action="/caseResolve" enctype="multipart/form-data">
			<html:hidden property="process" value=""/>
			<input type="hidden" name="date_reg" value="" />
			<TABLE border="0" ALIGN="CENTER" width="200">
				<TBODY>
					<TR>
						<TD class="column_field"><bean:message key="label.order" /></TD>
						<TD>
							<html:select property="id_type_case_order" onchange="processAndSubmit('selectOrder')">						
								<logic:iterate id="id" indexId="indexId" name="typeCaseOrderList" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
										<logic:equal name="id" property="id" value="<%=id_order_selected%>"> 
							    				SELECTED
						    			</logic:equal>>
								    	<bean:write name="id" property="description" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
						</TD>
					</TR>
					<TR>
						<TD class="column_field"><bean:message key="label.cases.pending" /></TD>
						<TD>
							<html:select property="id_case" multiple="multiple" size="8" onchange="processAndSubmit('selectCase')">						
								<logic:iterate id="id" indexId="indexId" name="casesList" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
										<logic:equal name="id" property="id" value="<%=id_case%>"> 
							    				SELECTED
						    			</logic:equal>>
								     	<bean:write name="id" property="id" />-><bean:write name="id" property="id_client.name" />-><bean:write name="id" property="short_description" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>
			        		<FONT COLOR="RED"> <html:errors property="id_case"/></FONT>
						</TD>
					</TR>

					<TR>
						<TD class="column_field">
						</TD>
						<TD class="column_field">
							<CENTER><H2><bean:message key="label.cases.number" /><%=id_case%></H2></CENTER>
							<BR/>
							<bean:message key="label.description" />:<BR/>
							<%=case_description%>
						</TD>

					</TR>

					<TR>
						<TD class="column_field"><bean:message key="label.notas.addded" /></TD>
						<TD align="center">
							<html:select property="id_note" size="8"  onchange="processAndSubmit('selectNote')">						
								<logic:iterate id="id" indexId="indexId" name="notesList" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
										<logic:equal name="id" property="id" value="<%=id_note%>"> 
							    				SELECTED
						    			</logic:equal>>
								    <bean:write name="id" property="id_user.name" /> -- <bean:write name="id" property="date_reg" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>
						</TD>
					</TR>
					<TR>
						<TD class="column_field">
						</TD>
						<TD class="column_field">
							<bean:message key="label.body.note" />
							<BR>
							<html:textarea property="note_readed" cols="100" rows="5" readonly="true"></html:textarea>
						</TD>
					</TR>
					<TR>
						<TD class="column_field">
						    <bean:message key="label.files" />
						</TD>
						<TD colspan="2">
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
									    <A href='/contactCenter/viewFile.jsp?fileName=<bean:write name="id" property="realFileName" />&msg=<bean:write name="id" property="fileName" />&path_server=<bean:write name="control_panel" property="path_server"/>' target="_blank"> <bean:write name="id" property="fileName" /> </A>
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
					<TR>
						<TD class="column_field"><bean:message key="label.note" /></TD>
						<TD colspan="1">
						    <html:textarea property="notes" cols="50" rows="4"  onkeydown="javascript:countChars();"></html:textarea>
			        		<FONT COLOR="RED"> <html:errors property="notes"/></FONT>
						    <html:text property="notes_char_count" size="3" readonly="true"></html:text>
						</TD>
					</TR>
					<TR>
						<TD class="column_field"><bean:message key="label.case.forward" /></TD>
						<TD colspan="1">
						    <TABLE>
								<TR>
									<TD class="column_field">
									   <bean:message key="label.user.number" />
									</TD>
									<TD class="column_field">
									   <bean:message key="label.type.case" />
									</TD>
								</TR>
								<TR>
									<TD>
									    <html:text property="id_user" size="20"></html:text>
						        		<FONT COLOR="RED"> <html:errors property="id_user"/></FONT>
									</TD>
									<TD>
										<html:select property="id_type_case">						
											<logic:iterate id="id" indexId="indexId" name="typeCaseList" scope="session" >
											    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  
											    	<bean:write name="id" property="description" />
											    </OPTION>
									    	</logic:iterate>						
										</html:select>							
									</TD>
								</TR>
								<TR>
									<TD class="column_field" colspan="2">
									   <bean:message key="label.file.description" />
									</TD>

								</TR>
								<TR>
									<TD colspan="2">
										<html:text property="fileDescription" size="80"></html:text>
						        	 	<FONT COLOR="RED"> <html:errors property="fileDescription"/></FONT> 
									</TD>
								</TR>
								<TR>
									<TD class="column_field" colspan="2">
									   <bean:message key="label.file" />
									</TD>
								</TR>
								<TR>
									<TD colspan="2">
						        	   <FONT COLOR="RED"><html:errors property="file"/></FONT>
						        		<BR>
									   <html:file property="file" size="80"></html:file>
									</TD>
								</TR>
						    </TABLE>
						</TD>
					</TR>
					<TR>
						<TD align="center"></TD>
						<TD align="center">
			        		<html:submit onclick="process.value='addNote';"><bean:message key="label.add.note" /></html:submit>
						    
			        		<html:checkbox value="N" property="isPrivate"><bean:message key="label.note.for.the.client" /></html:checkbox>
			        		
			        		<BR/>
			        		<html:submit onclick="process.value='forward';"><bean:message key="label.user.forward" /></html:submit>
			        		<BR/>

						   <html:submit onclick="process.value='resolveCase';"><bean:message key="label.case.resolved" /></html:submit>
						</TD>
					</TR>					
				</TBODY>
			</TABLE>
			
			<BR>

			</html:form>

			<HR>
			<HR>


			<html:form action="/preWV">
			<html:hidden property="process" value="viewFile"/>
			<html:hidden property="parameter1" value=""/>
			
			</html:form>

			

			<HR>
				
			<html:form action="/search">
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD class="column_field"><bean:message key="label.filter" /></TD>
					</TR>
					<TR>
						<TD>
						   <html:hidden property="concept" value="case"/>
						   <html:hidden property="forward" value="resolve"/>
						   
						   <html:text property="filter"></html:text>
						</TD>
			        	<TD><FONT COLOR="RED"> <html:errors property="filter"/></FONT></TD> 
					</TR>

					<TR align="center">
					    <TD>
					    	<CENTER>
					    		<P align="left">
					    			<html:submit><bean:message key="label.search" /></html:submit>					    		
					    		</P>
					    	</CENTER>
						</TD>
					</TR>

					<TR>
						<TD>						
							<html:select property="filter3" multiple="multiple" size="10">						
								<logic:iterate id="id" indexId="indexId" name="filterList" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  
								    <bean:write name="id" property="id" /> -- <bean:write name="id" property="name" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			
			</html:form>

		   <CENTER>
		   <BR>
		   <html:button property="a" onclick="javascript:window.close();"><bean:message key="label.exit" /></html:button>
		   </CENTER>

		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	
</BODY>
</html:html>
