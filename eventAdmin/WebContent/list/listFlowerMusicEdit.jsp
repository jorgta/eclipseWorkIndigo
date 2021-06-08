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

	<CENTER><H1> <bean:message key="label.flowers" />  </H1></CENTER>
	<CENTER><H1> &  </H1></CENTER>
	<CENTER><H1> <bean:message key="label.music" />  </H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 52;
			    
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
		         
		         BeanFlower listFlowerMusicEdit_currentFlower = (BeanFlower) ses.getAttribute( "listFlowerMusicEdit_currentFlower" );							    
		         BeanMusic listFlowerMusicEdit_currentMusic = (BeanMusic) ses.getAttribute( "listFlowerMusicEdit_currentMusic" );							    
				 String cfString;
				 String cmString;
				 
				 if ( listFlowerMusicEdit_currentFlower != null ) 
					 cfString = new Integer( listFlowerMusicEdit_currentFlower.getId() ).toString(); 
				 else
					 cfString = "-1";
					 
				 if ( listFlowerMusicEdit_currentMusic != null ) 
				   cmString = new Integer( listFlowerMusicEdit_currentMusic.getId() ).toString(); 
				 else
				   cmString = "-1";
			  %>

			
			<html:form action="/listFlowerMusicEdit">
			<input type="hidden" name="process" value="" />
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
					    <TD colspan="6" class="column_field"><bean:message key="label.flowers" /></TD>
					</TR>
					<TR>
						<TD>
							<html:select property="id_flower">						
								<logic:iterate id="id" indexId="indexId" name="listFlower" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%> 
									    <logic:equal name="id" property="id" value="<%=cfString%>"> 
									    		SELECTED
									    </logic:equal>>
								    	<bean:write name="id" property="description" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
			        		<FONT COLOR="RED"> <html:errors property="id_flower"/></FONT> 
						</TD>
						<TD>
							<html:submit onclick="process.value='selectFlower';"> <bean:message key="label.select" /> </html:submit>
						</TD>
						<TD>
							<html:submit onclick="process.value='deleteFlower';"> <bean:message key="label.delete" /> </html:submit>
						</TD>
					    <TD class="column_field"><bean:message key="label.description" /></TD>
						<TD>
							<html:text property="flowerDescription" size="20" disabled="false"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="flowerDescription"/></FONT> 
						</TD>
						<TD>
							<html:submit onclick="process.value='createFlower';"> <bean:message key="label.flower.create" /> </html:submit>
						</TD>
						<TD>
						</TD>
					</TR>
					<TR>
					    <TD></TD>
					    <TD></TD>
					    <TD></TD>
					    <TD class="column_field"><bean:message key="label.price" /></TD>
						<TD>
							<html:text property="flowerPrice" size="3" disabled="false"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="flowerPrice"/></FONT> 
						</TD>
						<TD>
							<html:submit onclick="process.value='updateFlower';"> <bean:message key="label.update.data" /> </html:submit>
						</TD>
					</TR>
					<TR>
					    <TD></TD>
					    <TD></TD>
					    <TD></TD>
					    <TD class="column_field"><bean:message key="label.per.person" /></TD>
					    <TD>
					      <html:checkbox property="flowerPerPerson"></html:checkbox>
			        	  <FONT COLOR="RED"> <html:errors property="flowerPerPerson"/></FONT> 
					    </TD>
					</TR>
					<TR>
					    <TD colspan="6" class="column_field"><bean:message key="label.music" /></TD>
					</TR>
					<TR>
						<TD>
							<html:select property="id_music">						
								<logic:iterate id="id" indexId="indexId" name="listMusic" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%> 
									    <logic:equal name="id" property="id" value="<%=cmString%>"> 
									    		SELECTED
									    </logic:equal>>
								    	<bean:write name="id" property="description" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
			        		<FONT COLOR="RED"> <html:errors property="id_music"/></FONT> 
						</TD>
						<TD>
							<html:submit onclick="process.value='selectMusic';"> <bean:message key="label.select" /> </html:submit>
						</TD>
						<TD>
							<html:submit onclick="process.value='deleteMusic';"> <bean:message key="label.delete" /> </html:submit>
						</TD>
					    <TD class="column_field"><bean:message key="label.description" /></TD>
						<TD>
							<html:text property="musicDescription" size="20" disabled="false"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="musicDescription"/></FONT> 
						</TD>
						<TD>
							<html:submit onclick="process.value='createMusic';"> <bean:message key="label.music.create" /> </html:submit>
						</TD>
						<TD>
						</TD>
					</TR>
					<TR>
					    <TD></TD>
					    <TD></TD>
					    <TD></TD>
					    <TD class="column_field"><bean:message key="label.price" /></TD>
						<TD>
							<html:text property="musicPrice" size="3" disabled="false"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="musicPrice"/></FONT> 
						</TD>
						<TD>
							<html:submit onclick="process.value='updateMusic';"> <bean:message key="label.update.data" /> </html:submit>
						</TD>
					</TR>
					<TR>
					    <TD></TD>
					    <TD></TD>
					    <TD></TD>
					    <TD class="column_field"><bean:message key="label.per.person" /></TD>
					    <TD>
					      <html:checkbox property="musicPerPerson"></html:checkbox>
			        	  <FONT COLOR="RED"> <html:errors property="musicPerPerson"/></FONT> 
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
