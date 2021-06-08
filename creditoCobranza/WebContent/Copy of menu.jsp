<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>

<!--<html xmlns="http://www.w3.org/1999/xhtml">-->
<html:html >
<head>
<%@ page session="true" import="java.util.*,com.struts2.Beans.*, java.io.*,javax.servlet.http.HttpSession.*"%>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Admin</title>
<link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />	
<script type="text/javascript" src="resources/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="resources/scripts/simpla.jquery.configuration.js"></script>
<script type="text/javascript" src="resources/scripts/facebox.js"></script>
<script type="text/javascript" src="resources/scripts/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="resources/scripts/jquery.datePicker.js"></script>
<script type="text/javascript" src="resources/scripts/jquery.date.js"></script>
</head>
<s:if test="#session.beanUser != null"> 
	<s:if test="#session.processList != null"> 
	<body class="bodyMenu"><div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
		
		<div id="sidebar"><div id="sidebar-wrapper"> <!-- Sidebar with logo and menu -->
			
			
			<h1 id="sidebar-title"><a href="#">Bituos Monitor</a></h1>
		  
			<!-- Logo (221px wide) -->
			<a href="#"><img id="logo" src="resources/images/logo.png" alt="Bituos Monitor" /></a>
		  
			<!-- Sidebar Profile links -->
			<div id="profile-links">
				Hola ,<s:label value="%{#session.beanUser.name}"></s:label>
				<!--<a href="#" title="Edit your profile">John Doe</a>, you have <a href="#messages" rel="modal" title="3 Messages">3 Messages</a>--><br />
				<br />
				<a href="#" title="View the Site">Ver el Sitio</a> | <a href="pre.jsp?process=logout&action=pre" title="Sign Out" target="_parent">Sign Out</a>
			</div>        
			
			<ul id="main-nav">  <!-- Accordion Menu -->
			
			
				 
				<s:iterator value="#session.processTypeList" var="processType">
				  
				  <li> 
					<a href="#" class="nav-top-item"> <!-- Add the class "current" to current menu item -->
					  <s:property value="description"/>
					</a>
					<ul>  
	
						
						<s:iterator value="#session.processList" var="processUsers">
							
							<s:if test="#processType.id == id_process.id_type_process.id">
					        
					
							  		
							 <li>

							 <a href="././pre.jsp?process=<s:property value="id_process.action"/>&action=<s:property value="id_process.url"/>" target="<s:property value="id_process.target"/>"><s:property value="id_process.description"/></a>
		                    		
							 
							 </li> 
									  		
								
					    
					           </s:if>
						</s:iterator> 
					</ul>
				  </li>	
				</s:iterator>	

				


				
			</ul> <!-- End #main-nav -->
			
			<div id="messages" style="display: none"> <!-- Messages are shown when a link with these attributes are clicked: href="#messages" rel="modal"  -->
				
				<h3>3 Messages</h3>
			 
				<p>
					<strong>17th May 2009</strong> by Admin<br />
					Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus magna. Cras in mi at felis aliquet congue.
					<small><a href="#" class="remove-link" title="Remove message">Remove</a></small>
				</p>
			 
				<p>
					<strong>2nd May 2009</strong> by Jane Doe<br />
					Ut a est eget ligula molestie gravida. Curabitur massa. Donec eleifend, libero at sagittis mollis, tellus est malesuada tellus, at luctus turpis elit sit amet quam. Vivamus pretium ornare est.
					<small><a href="#" class="remove-link" title="Remove message">Remove</a></small>
				</p>
			 
				<p>
					<strong>25th April 2009</strong> by Admin<br />
					Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus magna. Cras in mi at felis aliquet congue.
					<small><a href="#" class="remove-link" title="Remove message">Remove</a></small>
				</p>
				
				<form action="#" method="post">
					
					<h4>New Message</h4>
					
					<fieldset>
						<textarea class="textarea" name="textfield" cols="79" rows="5"></textarea>
					</fieldset>
					
					<fieldset>
					
						<select name="dropdown" class="small-input">
							<option value="option1">Send to...</option>
							<option value="option2">Everyone</option>
							<option value="option3">Admin</option>
							<option value="option4">Jane Doe</option>
						</select>
						
						<input class="button" type="submit" value="Send" />
						
					</fieldset>
					
				</form>
				
			</div> <!-- End #messages -->
			
		</div></div> <!-- End #sidebar -->
		
		 <!-- End #main-content -->

	</div></body>
	
	
	</s:if>	
</s:if>
<s:else>
  <s:action name="logon" executeResult="true"></s:action>
  
</s:else>





<logic:notPresent name="beanUser" scope="session" >
	<logic:redirect href="login.jsp"></logic:redirect>
</logic:notPresent>

</html:html>
