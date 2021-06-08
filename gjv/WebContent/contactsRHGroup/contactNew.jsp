<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>

<%@ page session="true" import="java.util.*, com.bituos.gjv.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

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
    <TITLE>Nuevo Contacto</TITLE>
</HEAD>

<BODY style="margin: 1cm; margin-top: 0.2cm; margin-right: 1.5cm" >
<script language="JavaScript">
function selectContact()
{	
  
 /* if(document.forms[0].yesmarried.checked == 'false' && document.forms[0].nomarried.checked == 'false');
  {
     document.forms[0].nomarried.checked= 'true';
      document.forms[0].submit();
  }*/
document.forms[0].submit();



}

</script>

	
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
		          response.sendRedirect( "gjv/login.jsp");
		        else
		          found = 0;
			  %>

			

	        <html:form action="/contactGroupNewAction" style="width: 100%">
             	<fieldset class="login" >
			    <strong>
			    <legend>Detalles del Contacto</legend> 
			    </strong>
			    
					
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.first_name" />
					       </font>
					   </strong>	
					    <p style="padding-left: 167px">
			    		<html:text property="first_name" onmouseout="style.backgroundColor='#FFFFFF'"></html:text>
			    							
						<FONT COLOR="FF0000"> <html:errors property="first_name" /></FONT>
						</p>
					</div>	
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.last_name" />
					       </font>
					   </strong>	
					    <p style="padding-left: 167px">
			    		<html:text property="last_name" onmouseout="style.backgroundColor='#FFFFFF'"></html:text>
			    							
						<FONT COLOR="FF0000"> <html:errors property="last_name" /></FONT>
						</p>
					</div>	
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.address" />
					       </font>
					   </strong>	
					    <p style="padding-left: 167px">
			    		<html:text property="address" onmouseout="style.backgroundColor='#FFFFFF'"></html:text>
			    							
						<FONT COLOR="FF0000"> <html:errors property="address" /></FONT>
						</p>
					</div>	
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.colony" />
					       </font>
					   </strong>	
					    <p style="padding-left: 167px">
			    		<html:text property="colony" onmouseout="style.backgroundColor='#FFFFFF'"></html:text>	
			    						
						<FONT COLOR="FF0000"> <html:errors property="colony" /></FONT>
						</p>
					</div>	
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.phone" />
					       </font>
					   </strong>
					    <p style="padding-left: 167px">	
			    		<html:text property="phone" onmouseout="style.backgroundColor='#FFFFFF'"></html:text>	
			    						
						<FONT COLOR="FF0000"> <html:errors property="phone" /></FONT>
						</p>
					</div>	
					
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.cel_phone" />
					       </font>
					   </strong>	
					    <p style="padding-left: 167px">
			    		<html:text property="cel_phone" onmouseout="style.backgroundColor='#FFFFFF'"></html:text>
			    							
						<FONT COLOR="FF0000"> <html:errors property="cel_phone" /></FONT>
						</p>
					</div>
					
					<div class="radio">		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.married" />
					       </font>
					   </strong>
					      <p style="padding-left: 167px">		
					       	
			    		    Casado <input id="yesmarried" name="married" value="true" type="radio">
			    		    
			    		    
			    		   
				            Soltero <input  id="nomarried" name="married" value="false" type="radio"> 
				            
				            <FONT COLOR="FF0000"> <html:errors property="married" /></FONT>
				          </p>				
						
					</div>
			
		
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.NSS" />
					       </font>
					   </strong>	 
					   <p style="padding-left: 167px">
			    		<html:text property="NSS"></html:text>	
			    					
						<FONT COLOR="FF0000"> <html:errors property="NSS" /></FONT>
						</p>
					</div>
			
					<div>		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.wife_name" />
					       </font>
					   </strong>	
					   <p style="padding-left: 167px">
			    		<html:text property="wife_name"></html:text>		
			    					
						<FONT COLOR="FF0000"> <html:errors property="wife_name" /></FONT>
						</p>
					</div>
					
					<div class="date" style="z-index: 100;">		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.contact.date_of_birth" />
					       </font>
					   </strong>						    
			    		<html:text  maxlength="10"  property="date_of_birth" value="DD/MM/YYYY" styleClass="default"></html:text>					
						
						<FONT COLOR="FF0000"> <html:errors property="date_of_birth" /></FONT>
						
					</div>
					<%
					String fechaingreso = (String)ses.getAttribute("fechaingreso"); 					
					%>
					<div class="date" style="z-index: 99;">		
			    		<strong style="float: left">
					       <font color="#000000" size="3px"><bean:message key="label.profiles.starting_date" />
					       </font>
					   </strong>	
					   
			    		<html:text style=""  maxlength="10"  property="starting_date" value="<%=fechaingreso%>" styleClass="default"></html:text>					
						
						<FONT style="z-index: 0" COLOR="FF0000"> <html:errors property="starting_date" /></FONT>
						
					</div>
					

		

		     </fieldset>

			<CENTER>
			  <P>
			  <input type="button" value="Guardar"  onclick="selectContact()"/>
			  </P>
			</CENTER>
			
	
			</html:form>

		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	

		
	
	<br>
	<br>
	<br>
	<br>
	<br>
	<hr>
	
</BODY>
</html:html>
