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
<SCRIPT language="JavaScript" src="/eventAdmin/getSystemDate.js"></SCRIPT>
<SCRIPT language="Javascript" src="/eventAdmin/Calendar/calendar.js"></SCRIPT>
<SCRIPT language="Javascript" src="/eventAdmin/Calendar/mycalendar.js"></SCRIPT>
<BODY>

	<CENTER><H1> <bean:message key="label.quote.availability" />  </H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 38;
			    
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
		         
		         BeanList listEdit_currentList = (BeanList) ses.getAttribute( "listEdit_currentList" );							    
		         BeanSaloon quoteNew_currentSaloon = (BeanSaloon) ses.getAttribute( "quoteNew_currentSaloon" );							    
				 String clString;
				 String csString;
		         
				 if ( listEdit_currentList != null ) 
				   clString = new Integer( listEdit_currentList.getId() ).toString(); 
				 else
				   clString = "-1";
					 
				 if ( quoteNew_currentSaloon != null ) 
				   csString = new Integer( quoteNew_currentSaloon.getId() ).toString(); 
				 else
				   csString = "-1";
			         
			  %>

			
			
<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">


  
//-->
</SCRIPT>
			
			<html:form action="/availabilitySaloonEventType" onsubmit="sbt();">
			<input type="hidden" name="process" value="" />
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD class="column_field">
							  <bean:message key="label.event.date" />
						</TD>	
						<TD>
							  <html:text property="date_event" readonly="true" onclick="javascript:showCal('calendar4');"> </html:text>
							  <A href="javascript:showCal('calendar4')"><IMG src="/eventAdmin/Calendar/calendar.png" border="0" /></A>
							  <SPAN id=date1 style="POSITION: relative"> </SPAN>
						      <FONT COLOR="RED"> <html:errors property="date_event"/></FONT>
						</TD>	
					</TR>
					<TR>
					    <TD class="column_field"><bean:message key="label.list.prices" /></TD>
						<TD>
							<html:select property="id_list">						
								<logic:iterate id="id" indexId="indexId" name="aset_listList" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>    
									    <logic:equal name="id" property="id" value="<%=clString%>"> 
									    		SELECTED
									    </logic:equal>>
								    	<bean:write name="id" property="description" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
						</TD>
					    <TD>
					      <html:checkbox property="checkList"></html:checkbox>
			        	  <FONT COLOR="RED"> <html:errors property="checkList"/></FONT> 
					    </TD>
					</TR>

					<TR>
					    <TD class="column_field"><bean:message key="label.saloon" /></TD>
						<TD>
							<html:select property="id_saloon">						
								<logic:iterate id="id" indexId="indexId" name="aset_SaloonList" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
									    <logic:equal name="id" property="id" value="<%=csString%>"> 
									    		SELECTED
									    </logic:equal>>
								    	<bean:write name="id" property="description" />:-<bean:write name="id" property="min_occupancy" />-<bean:write name="id" property="max_occupancy" />  
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
						    <FONT COLOR="RED"> <html:errors property="id_saloon"/></FONT>
						</TD>
					    <TD>
					      <html:checkbox property="checkSaloon"></html:checkbox>
			        	  <FONT COLOR="RED"> <html:errors property="checkSaloon"/></FONT> 
					    </TD>
					</TR>
					<TR> 
						<TD>
							<html:submit onclick="process.value='showCalendar';"><bean:message key="label.view.availabilty" /></html:submit>						
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

 if ( document.getElementById("id_client").disabled == false )
   document.getElementById("id_client").focus();
 else
   document.getElementById("hourQuantity").focus();
 
 function sbt()
   {
     if( document.forms[1].process.value == 'calendar')
       document.forms[1].target='_blank';
   }
  
//-->
</SCRIPT>
</html:html>
