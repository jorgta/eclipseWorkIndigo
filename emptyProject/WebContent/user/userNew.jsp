<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*,com.testWF.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"

%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Simpla Admin</title>
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
	<body class="bodyMain">
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 11;
			    
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
	
	
	
			<div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
				
				 
		
				<div id="main-content"> <!-- Main Content Section with everything -->
					
					<noscript> <!-- Show a notification if the user has disabled javascript -->
						<div class="notification error png_bg">
							<div>
								Javascript is disabled or is not supported by your browser. Please <a href="http://browsehappy.com/" title="Upgrade to a better browser">upgrade</a> your browser or <a href="http://www.google.com/support/bin/answer.py?answer=23852" title="Enable Javascript in your browser">enable</a> Javascript to navigate the interface properly.
							</div>
						</div>
					</noscript>
					
					<!-- Page Head -->
					<h2>Welcome John</h2>
					<p id="page-intro">What would you like to do?</p>
                   

					
					<ul class="shortcut-buttons-set hidden">
						
						<li><a class="shortcut-button" href="#messages" rel="modal"><span>
							<img src="resources/images/icons/Profile.png" alt="icon" /><br />
							Agregar Usuario
						</span></a></li>
						
					</ul><!-- End .shortcut-buttons-set -->
					

		
					
					<div class="clear"></div> <!-- End .clear -->
					
					<div class="content-box"><!-- Start Content Box -->
						
						<div class="content-box-header">
							 
							
							<%										   
							
		                       Boolean foundErrors = (Boolean)ses.getAttribute("foundErrors");
							   String defaultTab= (String)ses.getAttribute( "defaultTab" );	
							%>
					       
					        <h3>Usuarios</h3>
							
							<ul class="content-box-tabs">
								<li>
								  <%										   
								   if( defaultTab.equals("#tab1")  )	
									 out.write("<a href=\"#tab1\" class=\"default-tab\"  >Lista de Usuarios</a>"); 					   
								   else
									 out.write("<a href=\"#tab1\">Lista de Usuarios</a>");
							       %>
								  </li> 
								<li>
								   <%										   
								   if( defaultTab.equals("#tab2")  )	
									 out.write("<a href=\"#tab2\" class=\"default-tab\"  >Agregar un Usuario</a>"); 					   
								   else
									 out.write("<a href=\"#tab2\">Agregar un Usuario</a>");
							       %>
								  
								</li>
								<li>
								   <%										   
								   if( defaultTab.equals("#tab3")  )	
									 out.write("<a href=\"#tab3\" class=\"default-tab\" id=\"single\" >Agregar Varios Usuarios</a>"); 					   
								   else
									 out.write("<a href=\"#tab3\" id=\"single\">Agregar un Usuario</a>");
							       %>
								 
								</li>
							</ul>
							
							<div class="clear"></div>
							
						</div> <!-- End .content-box-header -->
						
						<div class="content-box-content">
							
							
						 <%										   
						   if( defaultTab.equals("#tab1")  )						     
						     out.write("<div class=\"tab-content default-tab\" id=\"tab1\">");
						   else
							 out.write("<div class=\"tab-content\" id=\"tab1\">");
					      %>

								<%										   
								   String notification = (String) ses.getAttribute( "notification" );	
								%>
								<div class="notification <%=notification %> png_bg">
								<a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
								<div>
									<%										   
										String msg = (String) ses.getAttribute( "msgNotification" );
										out.write(msg);
									%>

								</div>
							    </div>
								
								<table>
									
									<thead>
										<tr>
										   <th>Activo</th>
										   <th>Nombre de Usuario</th>
										   <th>Nombre</th>
										   <th>Dirección</th>
										   <th>RFC</th>
										   
										</tr>
										
									</thead>
								 
									<tfoot>
										<tr>
											<td colspan="6">
											  <html:form action="/userNew"  >
											<html:hidden property="process" value="pagination"/>
											<html:hidden property="pagina" value=""/>	
											<html:hidden property="tab" value="1"/>	
												<div class="bulk-actions align-left">
													<%
													 String optionSelected = (String)ses.getAttribute("rowsPerPage");
													%>
													
													<html:select property="rowtoshow">														
														<option value="5">5</option>
														<option value="10">10</option>
														<option value="15">15</option>
													</html:select> 	
													
													<a class="button" href="#" onclick="sendSubmit('changeRowToShow','');">Apicar Selecciòn</a>
												</div>

																						
												
												<div class="pagination">
												
												
												<bean:define id="pg" name="pageCurrent" scope="session"> </bean:define>
												<bean:define id="pl" name="pageLast" scope="session"> </bean:define>
												
												<a href="#" title="First Page" onclick="sendSubmit('pagination','1');">&laquo; First</a><a href="#" title="Previous Page" onclick="sendSubmit('pagination','<%= Integer.valueOf((String)pg) - 1 %>');">&laquo; Previous</a>
												
												<logic:iterate id="id" indexId="indexId" name="pagesList" scope="session" >
													
													
													<!--<bean:write name='id'/>--> <!--imprime una variable tipo string de session-->
											
													<logic:equal name="id" value='<%= (String)pg %>'>
													   <a href="#" class="number current" title="<bean:write name='id'/>" onclick="sendSubmit('pagination','<bean:write name='id'/>');"><bean:write name='id'/></a>
													</logic:equal>
													
													<logic:notEqual name="id" value='<%= (String)pg %>'>
													   <a href="#" class="number" title="<bean:write name='id'/>" onclick="sendSubmit('pagination','<bean:write name='id'/>');"><bean:write name='id'/></a>
													</logic:notEqual>

												</logic:iterate>	

												<a href="#" title="Next Page" onclick="sendSubmit('pagination','<%= Integer.valueOf((String)pg) + 1 %>');">Next &raquo;</a><a href="#" title="Last Page" onclick="sendSubmit('pagination','<%=  (String)pl %>');">Last &raquo;</a>
												</div> <!-- End .pagination -->
											</html:form>
												<div class="clear"></div>
											</td>
										</tr>
									</tfoot>
								 
									<tbody>
									   <logic:iterate id="id" indexId="indexId" name="listUser" scope="session" >
										<tr>
										<logic:equal name="id" property="active" value="Y">
											 <td>											 
											  <input type="checkbox" readonly="readonly" checked onclick="javascript: return false;"/> 
											 
											 </td>
										</logic:equal> 
										<logic:equal name="id" property="active" value="N">
											 <td><input type="checkbox"  onclick="javascript: return false;"/></td>
										</logic:equal> 
											
											<td><bean:write name="id" property="name"/></td>
											<td><bean:write name="id" property="full_name" /></td>
											<td><bean:write name="id" property="address" /></td>
											<td><bean:write name="id" property="rfc" /></td>
									
										</tr>
									  </logic:iterate>
										
									</tbody>
									
								</table>
								
							</div> <!-- End #tab1 -->
							
							
							<%										   
						   if( defaultTab.equals("#tab2")  )						     
						     out.write("<div class=\"tab-content default-tab\" id=\"tab2\">");
						   else
							 out.write("<div class=\"tab-content\" id=\"tab2\">");
					        %>

								<form action="#" method="post">
									
									<fieldset> <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
										
										<p>
											<label>Small form input</label>
												<input class="text-input small-input" type="text" id="small-input" name="small-input" /> <span class="input-notification success png_bg">Successful message</span> <!-- Classes for input-notification: success, error, information, attention -->
												<br /><small>A small description of the field</small>
										</p>
										
										<p>
											<label>Medium form input</label>
											<input class="text-input medium-input datepicker" type="text" id="medium-input" name="medium-input" /> <span class="input-notification error png_bg">Error message</span>
										</p>
										
										<p>
											<label>Large form input</label>
											<input class="text-input large-input" type="text" id="large-input" name="large-input" />
										</p>
										
										<p>
											<label>Checkboxes</label>
											<input type="checkbox" name="checkbox1" /> This is a checkbox <input type="checkbox" name="checkbox2" /> And this is another checkbox
										</p>
										
										<p>
											<label>Radio buttons</label>
											<input type="radio" name="radio1" /> This is a radio button<br />
											<input type="radio" name="radio2" /> This is another radio button
										</p>
										
										<p>
											<label>This is a drop down list</label>              
											<select name="dropdown" class="small-input">
												<option value="option1">Option 1</option>
												<option value="option2">Option 2</option>
												<option value="option3">Option 3</option>
												<option value="option4">Option 4</option>
											</select> 
										</p>
										
										<p>
											<label>Textarea with WYSIWYG</label>
											<textarea class="text-input textarea wysiwyg" id="textarea" name="textfield" cols="79" rows="15"></textarea>
										</p>
										
										<p>
											<input class="button" type="submit" value="Submit" />
										</p>
										
									</fieldset>
									
									<div class="clear"></div><!-- End .clear -->
									
								</form>
								
							</div> <!-- End #tab2 --> 
							
							
							<%										   
						   if( defaultTab.equals("#tab3")  )						     
						     out.write("<div class=\"tab-content default-tab\" id=\"tab3\">");
						   else
							 out.write("<div class=\"tab-content\" id=\"tab3\">");
					        %>
							
							

						   <div id="messages" class="facebox" style="display: none;"> <!-- Messages are shown when a link with these attributes are clicked: href="#messages" rel="modal"  -->
						
						     <h3>Nuevo Usuario</h3>
										
							<h4>Datos</h4>
							<html:form action="/userNew"  >
							<html:hidden property="process" value="addUserToList"/>	
							<html:hidden property="tab" value="3"/>		
							<html:hidden property="id" value=""/>	
											

							<table class="mytabla">
							<tr>
							<td>
								<h5>Nombre Completo</h5>				
							</td>
							<td>
								<span class="input-notification error png_bg"><html:errors property="full_name" /></span>				
							</td>
							</tr>
							<tr>
							<td colspan="2">									
								<fieldset>								
									<html:text property="full_name" styleClass="text-input large-input"></html:text>
								</fieldset>
							</td>
							</tr>
							<tr>
							<td>
								<h5>Nombre de Usuario</h5>				
							</td>
							<td>
								<span  class="input-notification error png_bg" ><html:errors property="name" /></span>				
							</td>
							</tr>
							<tr>
							<td colspan="2">									
								<fieldset>
									<html:text property="name" styleClass="text-input large-input"></html:text>
								</fieldset>
							</td>
							</tr>
							
							<tr>
							<td>
								<h5>Contraseña</h5>				
							</td>
							<td>
								<span class="input-notification error png_bg"><html:errors property="password" /> </span>				
							</td>
							</tr>
							<tr>
							<td colspan="2">									
								<fieldset>
									<html:text property="password" styleClass="text-input medium-input"></html:text>
								</fieldset>
							</td>
							</tr>
							
							<tr>
							<td>
								<h5>Dirección</h5>				
							</td>
							<td>
								<span class="input-notification error png_bg"> <html:errors property="address" /></span>				
							</td>
							</tr>
							<tr>
							<td colspan="2">									
								<fieldset>
									<html:text property="address" styleClass="text-input large-input"></html:text>
								</fieldset>
							</td>
							</tr>
							
							<tr>
							<td>
								<h5>RFC</h5>				
							</td>
							<td>
								<span class="input-notification error png_bg"> <html:errors property="rfc" /></span>				
							</td>
							</tr>
							<tr>
							<td colspan="2">									
								<fieldset>
									<html:text property="rfc" styleClass="text-input large-input"></html:text>
								</fieldset>
							</td>
							</tr>
							
							<tr>
							<td>
								<h5>Correo Electronico</h5>				
							</td>
							<td>
								<span class="input-notification error png_bg" ><html:errors property="email" /> </span>				
							</td>
							</tr>
							<tr>
							<td colspan="2">									
								<fieldset>
									<html:text property="email" styleClass="text-input large-input"></html:text>
								</fieldset>
							</td>
							</tr>
							
							</table>
							
				           
													
							
							<fieldset>
								
								<input class="button" type="submit" value="Enviar"/>
								
								
								
							</fieldset>
						   </html:form>
					
						
					</div>
							   <%
			                    
			       							   
			                    if (!foundErrors)
								  {	 
								     notification = (String) ses.getAttribute( "notification" );	
								%>
								<div class="notification <%=notification %> png_bg">
								<a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
								<div>
									<%										   
										msg = (String) ses.getAttribute( "msgNotification" );
										out.write(msg);
									%>
								</div>
							    </div>
							    <%} %>
							    
								
								<%									   
								 if ( !((List) ses.getAttribute( "listUserTemp" )).isEmpty()  )	
								  {	 
								     notification = (String) ses.getAttribute( "notification" );	
								%>
								<div class="notification <%=notification %> png_bg">
								<a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
								<div>
									<%										   
										msg = (String) ses.getAttribute( "msgNotification" );
										out.write(msg);
									%>
								</div>
							    </div>
							    <%} %>
								
								<table>
									
									<thead>
										<tr>
										   
										   <th>Nombre de Usuario</th>
										   <th>Nombre Completo</th>
										   <th>Dirección</th>
										   <th>RFC</th>
										   <th>Acción?</th>
										</tr>
										
									</thead>
								 
									<tfoot>
										<tr>
											<td colspan="6">
												<div class="bulk-actions align-left">
													<select name="dropdown">
														<option value="option1">Choose an action...</option>
														<option value="option2">Edit</option>
														<option value="option3">Delete</option>
													</select>
													<a class="button" href="#">Apply to selected</a>
												</div>

												
												<div class="pagination">
						
												</div> <!-- End .pagination -->
												 
												<div class="clear"></div>
											</td>
										</tr>
									</tfoot>
								 
									<tbody>
									   <logic:iterate id="id" indexId="indexId" name="listUserTemp" scope="session" >
										<tr>
										
											
											<td><bean:write name="id" property="name"/></td>
											<td><bean:write name="id" property="full_name" /></td>
											<td><bean:write name="id" property="address" /></td>
											<td><bean:write name="id" property="rfc" /></td>
											<td>
												<!-- Icons -->
												 
												 <a href="#" title="Delete"><img src="resources/images/icons/cross.png" alt="Delete" onclick="sendSubmit('removeUserFromList','<bean:write name="id" property="id"/>');" /></a> 
												 
											</td>
										</tr>
									  </logic:iterate>
										
									</tbody>
									
								</table>
								
								
								
							</div> <!-- End #tab3 -->     
							
							
							
							       
							
						</div> <!-- End .content-box-content -->
						
					</div> <!-- End .content-box -->
					
					 
					
					 <!-- End .content-box -->
					<div class="clear"></div>
					
					
					
					
					
					<div id="footer">
						<small> <!-- Remove this notice or replace it with whatever you want -->
								&#169; Copyright 2009 Your Company | Powered by <a href="#">Simpla Admin</a> | <a href="#">Top</a>
						</small>
					</div><!-- End #footer -->
					
				</div> <!-- End #main-content -->
		
			</div>
	
		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	</body>
	
	<script LANGUAGE="JavaScript" type="text/javascript">
	//<![CDATA[   
	        var theForm = document.forms['userNewForm'];
	         
		function sendSubmit(eventArgument, value)
		{
			if (eventArgument == 'removeUserFromList' ) 
			{
		      
		      
		      for(var i = 0; i < theForm.length; i++)
			   {
			     if (theForm[i].process.value == 'addUserToList' ) 
			       {			         
			         theForm[i].id.value = value;
			         theForm[i].process.value = eventArgument;
			         theForm[i].submit();
			         
			         break;
			       }
			   }
			    
			}
			
			
			if (eventArgument == 'pagination' ) 
			{
		      for(var i = 0; i < theForm.length; i++)
			   {
			     if (theForm[i].process.value == eventArgument ) 
			       {
			         theForm[i].pagina.value = value;
			         theForm[i].submit();
			         break;
			       }
			   }
			    
			}
			
		  
			if (eventArgument == 'changeRowToShow' ) 
			{
		      for(var i = 0; i < theForm.length; i++)
			   {
			     if (theForm[i].process.value == 'pagination' ) 
			       {
			         theForm[i].process.value = eventArgument;
			         theForm[i].submit();
			         break;
			       }
			   }
			    
			}
			
					  
		  }
							
	 //]]>
	</script>
</html:html>
