<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!--<html xmlns="http://www.w3.org/1999/xhtml">-->
<html:html >
<head>
<%@ page session="true" import="java.util.*,com.emptyProject.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>
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
<logic:present name="beanUser" scope="session" >
  <logic:present name="processList" scope="session">
	<body class="bodyMenu"><div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
		
		<div id="sidebar"><div id="sidebar-wrapper"> <!-- Sidebar with logo and menu -->
			
			
			<h1 id="sidebar-title"><a href="#">Simpla Admin</a></h1>
		  
			<!-- Logo (221px wide) -->
			<a href="#"><img id="logo" src="resources/images/logo.png" alt="Simpla Admin logo" /></a>
		  
			<!-- Sidebar Profile links -->
			<div id="profile-links">
				Hello, John Doe<!--<a href="#" title="Edit your profile">John Doe</a>, you have <a href="#messages" rel="modal" title="3 Messages">3 Messages</a>--><br />
				<br />
				<a href="#" title="View the Site">View the Site</a> | <a href="logout.jsp" title="Sign Out">Sign Out</a>
			</div>        
			
			<ul id="main-nav">  <!-- Accordion Menu -->
			
			
				<logic:iterate id="processType" indexId="indexprocessType" name="processTypeList" scope="session" >
				  <bean:define id="pt" name="processType" > </bean:define>
				  <li> 
					<a href="#" class="nav-top-item"> <!-- Add the class "current" to current menu item -->
					  <bean:write name="processType" property="description" />
					</a>
					<ul>  
	
						<logic:iterate id="process" indexId="indexprocess" name="processList" scope="session" >
							<bean:define id="p" name="process" property="id_process"> </bean:define>
					        
						<%					          
					         if ( ((BeanTypeProcess) pt).getId() == ((BeanProcess) p).getId_type_process().getId() )
					         {
					              String s = ((BeanProcess) p).getUrl();
					    %> 
							  		
							 <li><a href="/emptyProject/pre.do?process='<bean:write name="process" property="id_process.action"/>'&action='<bean:write name="process" property="id_process.url"/>'&target='mainFrame'" target="mainFrame"><bean:write name="process" property="id_process.description"/></a></li> 
									  		
								
					      <%  
							 }
					      %>  
					
						</logic:iterate>
					</ul>
				  </li>	
				</logic:iterate>

				


				
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
	
	
	</logic:present>
</logic:present>
<logic:notPresent name="beanUser" scope="session" >
	<logic:redirect href="login.jsp"></logic:redirect>
</logic:notPresent>

</html:html>
