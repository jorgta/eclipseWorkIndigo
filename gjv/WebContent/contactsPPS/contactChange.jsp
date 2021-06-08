<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true" import="java.util.*, com.bituos.gjv.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"

%>




<%@page import="com.bituos.gjv.forms.ContactChangeForm"%>
<html:html>
<HEAD>

	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	
	<META http-equiv="Content-Style-Type" content="text/css">
	<script language='javascript' src="/gjv/calendar/popcalendar.js"></script> 
	<script language='javascript' src="/gjv/calendar/popcalendar.js"></script> 
	<link rel="stylesheet" type="text/css" href="/gjv/theme/screen.css">
	<link rel="stylesheet" type="text/css" href="/gjv/theme/dropdown.css">
	<script type="text/javascript" src="/gjv/theme/helpers.js"></script>
	<script type="text/javascript" src="/gjv/theme/date.js"></script>
	<script type="text/javascript" src="/gjv/theme/form.js"></script>
	<LINK href="/gjv/theme/grid.css" rel="stylesheet" type="text/css">
    <TITLE>Cambios a usuarios
    </TITLE>
</HEAD>

<BODY style="margin: 1cm; margin-top: 0.2cm; margin-right: 1.5cm">

<script language="JavaScript">
function selectContact(id,proc)
{	
   
   document.forms[1].idProvider.value=id; 
  // alert(document.forms[1].idContact.value);
   document.forms[1].process.value= proc; 
   //alert(document.forms[1].married.value == "on"); 
  
   document.forms[1].submit();
   //alert(document.forms[1].idContact.value)
}


</script>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 44;
			    
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
		        
		       
			  %>

		
	
	        <html:form action="/search"  style="width: 100%">	
	         <fieldset class="login" >
			    <strong>
			    <legend><bean:message key="label.filter" /></legend> 
			    </strong> 
			<TABLE width="90%" border="0" cellspacing="0" cellpadding="0"
					align="center" bordercolor="1px #000000 solid">
				<TBODY >
				
					<TR align="left">
						<TD colspan="0">
						   <html:hidden property="concept" value="provider"/>
						   <html:hidden property="forward" value="change"/>
						   
						   <html:text property="filter"></html:text>
						</TD>
			        	<TD><FONT COLOR="red"> <html:errors property="filter"/></FONT></TD> 
					</TR>

					<TR >
					    <TD>				    	
                   			<input type="submit" value=" Buscar "/>
						</TD>
					</TR>
				</TBODY>
			</TABLE>	
			</fieldset>
			<fieldset class="" >
			    <strong>
			    <legend><bean:message key="label.privider.list" /></legend> 
			    </strong>    				

			<div class="grid-header-button grid-header-button;">
			  
						<table class="grid" align="left">		  
						  <tr class="rowHead rowHead rowFoot">	     
						    
						     <th width="19%" class="first-cell"><bean:message key="label.contact.first_name"/></th>
						     <th width="19%"><bean:message key="label.contact.last_name" /></th>
						     <th width="19%"><bean:message key="label.contact.address" /></th>	
						     <th width="19%"><bean:message key="label.contact.city" /></th>
						     <th width="19%"><bean:message key="label.contact.country" /></th>
						  </tr>
						  </table>
						  <div style="overflow: scroll; height: 150px; width: 100%">
						   <table class="grid" style="width: 100%">	
						   <% Integer  counter = 0; 
	           				  String clase;
	           			   %>
	           			   <logic:iterate id="id" indexId="indexId" name="filterList" scope="session">
                    		<bean:define id="contacto" name="id" property="id"></bean:define>
							<logic:notEqual name="id" property="id" value="0"> 
			                       <%
			            	     if ((counter % 2)==1)						
			          	        	clase="rowEven";						
								 else
									clase="rowOdd";
								 %>				
								  <tr class="<%=clase%>">									 
									  <td width="19%">
									     <a href="#" onclick="selectContact('<bean:write name="id" property="id"/>','select')"> 
								  			<bean:write name="id" property="first_name" />
								  	    </a>
								  	  </td>
									  <td width="19%"><bean:write name="id" property="last_name" /></td>
									  <td width="19%"><bean:write name="id" property="address" /></td>
									  <td width="19%"><bean:write name="id" property="city" /></td>	
									  <td width="19%"><bean:write name="id" property="country" /></td>								     
								  </tr>			
								  <%counter++;%>					      
								</logic:notEqual>
							</logic:iterate>    
				</table>
			  </div>
			</div>			
			</fieldset>				
			</html:form>
			
			
			<%
			BeanContactPPS beanContactPPS= (BeanContactPPS)((List)ses.getAttribute("filterList")).get(0);
			
			if(beanContactPPS.getId()!=0)
			{
			%>

			<html:form action="/providerChangeAction" style="width: 100%">
			<html:hidden property="process" value="change"/>
               			
			<fieldset class="login" >
			    <strong>
			    <legend>Detalles del Proveedor</legend> 
			    </strong>
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.privider.id" />
					       </font>
					   </strong>
					   <p style="padding-left: 177px">	
			    		<html:text property="idProvider" onmouseout="style.backgroundColor='#FFFFFF'"></html:text>	
			    						
						<FONT COLOR="FF0000"> <html:errors property="idProvider" /></FONT>
					</div>
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.first_name" />
					       </font>
					   </strong>	
					   <p style="padding-left: 177px">
			    		<html:text property="first_name" onmouseout="style.backgroundColor='#FFFFFF'"></html:text>
			    						
						<FONT COLOR="FF0000"> <html:errors property="first_name" /></FONT>
					</div>	
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.last_name" />
					       </font>
					   </strong>
					   <p style="padding-left: 177px">	
			    		<html:text property="last_name" onmouseout="style.backgroundColor='#FFFFFF'"></html:text>
			    							
						<FONT COLOR="FF0000"> <html:errors property="last_name" /></FONT>
					</div>	
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.enterprise_name" />
					       </font>
					   </strong>	
					   <p style="padding-left: 177px">
			    		<html:text property="enterprise_name" onmouseout="style.backgroundColor='#FFFFFF'"></html:text>	
			    					
						<FONT COLOR="FF0000"> <html:errors property="enterprise_name" /></FONT>
					</div>	

					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.address" />
					       </font>
					   </strong>
					   <p style="padding-left: 177px">	
			    		<html:text property="address" onmouseout="style.backgroundColor='#FFFFFF'"></html:text>	
			    						
						<FONT COLOR="FF0000"> <html:errors property="address" /></FONT>
					</div>
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.city" />
					       </font>
					   </strong>
					   <p style="padding-left: 177px">	
			    		<html:text property="city" onmouseout="style.backgroundColor='#FFFFFF'"></html:text>
			    							
						<FONT COLOR="FF0000"> <html:errors property="city" /></FONT>
					</div>
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.country" />
					       </font>
					   </strong>
					   <p style="padding-left: 177px">	
			    		<html:text property="country" onmouseout="style.backgroundColor='#FFFFFF'"></html:text>		
			    					
						<FONT COLOR="FF0000"> <html:errors property="country" /></FONT>
					</div>
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.office_phone" />
					       </font>
					   </strong>
					   <p style="padding-left: 177px">	
			    		<html:text property="office_phone" onmouseout="style.backgroundColor='#FFFFFF'"></html:text>	
			    						
						<FONT COLOR="FF0000"> <html:errors property="office_phone" /></FONT>
					</div>
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.home_phone" />
					       </font>
					   </strong>	
					   <p style="padding-left: 177px">
			    		<html:text property="home_phone" onmouseout="style.backgroundColor='#FFFFFF'"></html:text>	
			    						
						<FONT COLOR="FF0000"> <html:errors property="home_phone" /></FONT>
					</div>
	
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.cel_phone" />
					       </font>
					   </strong>	
					   <p style="padding-left: 177px">
			    		<html:text property="cel_phone" onmouseout="style.backgroundColor='#FFFFFF'"></html:text>	
			    						
						<FONT COLOR="FF0000"> <html:errors property="cel_phone" /></FONT>
						
					</div>

			  </fieldset>     
			
			
				
		
			<CENTER>      
           <P>
        	<input type="button" value=" Cambiar" onclick="selectContact(document.forms[1].idProvider.value,'change')"/>
      		</P>
	    	</CENTER>
			
			</html:form>
            <%} %>
		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	<logic:notPresent name="beanUser" scope="session">
	   <logic:redirect href="../login.jsp">  </logic:redirect>
	</logic:notPresent>
	<br>
	<br>
	<br>
	<br>
	<br>
	<hr>
</BODY>
</html:html>
