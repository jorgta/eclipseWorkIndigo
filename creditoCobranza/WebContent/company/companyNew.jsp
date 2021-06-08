<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page session="true" import="java.util.*,com.struts2.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Trip Admin</title>
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
<s:if test="#session.module == 'pre'"> 
    <s:if test="#session.processList != null"> 

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
		          response.sendRedirect( "pre.jsp?process=logout&action=pre&target=_parent");
		        							 
		        else
		          found = 0;
		        
		        
		        ses.removeAttribute("module");
			  %>

	<body class="bodyMain"><div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
		
		 

		<div id="main-content"> <!-- Main Content Section with everything -->
			
			<noscript> <!-- Show a notification if the user has disabled javascript -->
				<div class="notification error png_bg">
					<div>
						Javascript is disabled or is not supported by your browser. Please <a href="#" title="Upgrade to a better browser">upgrade</a> your browser or <a href="#" title="Enable Javascript in your browser">enable</a> Javascript to navigate the interface properly.
					</div>
				</div>
			</noscript>
			
			<!-- Page Head -->
			<h2></h2>
			<p id="page-intro">Alta de Compañia</p>
			 <a href="#messages" rel="modal" title="3 Messages">3 Messages</a>
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
				
			</div>
			
			
			<ul class="shortcut-buttons-set">
				
				<li><a class="shortcut-button" href="#"><span>
					<img src="resources/images/icons/Profile.png" alt="icon" /><br />
					Write an Article
				</span></a></li>
				
				<li><a class="shortcut-button" href="#"><span>
					<img src="resources/images/icons/paper_content_pencil_48.png" alt="icon" /><br />
					Create a New Page
				</span></a></li>
				
				<li><a class="shortcut-button" href="#"><span>
					<img src="resources/images/icons/image_add_48.png" alt="icon" /><br />
					Upload an Image
				</span></a></li>
				
				<li><a class="shortcut-button" href="#"><span>
					<img src="resources/images/icons/clock_48.png" alt="icon" /><br />
					Add an Event
				</span></a></li>
				
				<li><a class="shortcut-button" href="#messages" rel="modal"><span>
					<img src="resources/images/icons/comment_48.png" alt="icon" /><br />
					Open Modal
				</span></a></li>
				
			</ul><!-- End .shortcut-buttons-set -->
			
			<div class="clear"></div> <!-- End .clear -->
			
			<div class="content-box"><!-- Start Content Box -->
				
				<div class="content-box-header">
					
					<h3>Content box</h3>
					
					<ul class="content-box-tabs">
					<li><a href="#tab1" class="default-tab" >Nuevo Compañia</a></li>
						<li><a href="#tab2" >Compañias Registradas</a></li> <!-- href must be unique and match the id of target div -->
						
					</ul>
					
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
					<div class="tab-content default-tab"  id="tab1">
							
							
						 
				
						
					    <s:if test="#session.done != null"> 
								<div class="notification success png_bg">
									<a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
									<div>
										 <s:label value="%{#session.done}"></s:label>
									</div>
								</div>
						</s:if>
						
						<s:if test="#session.error != null"> 
						<div class="notification error png_bg">
							<a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
							<div>
								 <s:label value="%{#session.error}"></s:label>
							</div>
						</div>
						</s:if>

						
						<form action="companyNew" method="post" enctype="multipart/form-data">
							
							<fieldset> <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
								
								<p>
									<label>Nombre corto</label>
									<s:if test="fieldErrors['name'] != null">
										<s:textfield id="username" name="name" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('name').get(0)"/></span>
									</s:if>
									<s:else>
									<s:textfield id="username" name="name" cssClass="text-input small-input" />
									</s:else>
			
										<br /><small>Nombre corto que usara para entrar en el sistema</small>
								</p>
								
								<p>
									<label>Password</label>
									<s:if test="fieldErrors['password'] != null">
										<s:textfield id="password" name="password" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('password').get(0)"/></span>
									</s:if>
									<s:else>
									<s:textfield id="password" name="password" cssClass="text-input small-input" />
									</s:else>
			
										<br /><small>Password de la compañia</small>
								</p>
								
								<p>
									<label>Localización</label>
									
																	
									<s:if test="fieldErrors['location'] != null">
										<s:textfield id="location" name="location" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('location').get(0)"/></span>
									</s:if>
									<s:else>
										<s:textfield id="location" name="location" cssClass="text-input small-input" />
									</s:else>
									
									
									
								</p>
								<p>
									<label>Telefono 1</label>
									
																	
									<s:if test="fieldErrors['phone1'] != null">
										<s:textfield id="phone1" name="phone1" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('phone1').get(0)"/></span>
									</s:if>
									<s:else>
										<s:textfield id="phone1" name="phone1" cssClass="text-input small-input" />
									</s:else>
									
									
									
								</p>
								<p>
									<label>Telefono 2</label>
									
																	
									<s:if test="fieldErrors['phone2'] != null">
										<s:textfield id="phone2" name="phone2" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('phone2').get(0)"/></span>
									</s:if>
									<s:else>
										<s:textfield id="phone2" name="phone2" cssClass="text-input small-input" />
									</s:else>
									
									
									
								</p>
								
								<p>
									<label>Direccion</label>									
	
									<s:if test="fieldErrors['address'] != null">
										<s:textfield id="adress" name="address" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('address').get(0)"/></span>
									</s:if>
									<s:else>
										<s:textfield id="adress" name="address" cssClass="text-input small-input" />
									</s:else>
								</p>
								<p>
									<label>Tiempo para reportar</label>									
	
									<s:if test="fieldErrors['timeToReport'] != null">
										<s:textfield id="timeToReport" name="timeToReport" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('timeToReport').get(0)"/></span>
									</s:if>
									<s:else>
										<s:textfield id="timeToReport" name="timeToReport" cssClass="text-input small-input" />
									</s:else>
								</p>
								
						
								
								<p>
									 
									<s:file name="myFile" label="Seleccione la imagen para subir" accept="image/*" />
								</p>
								
								<p>
									<label>Password del usuario ROOT</label>									
	
									<s:if test="fieldErrors['rootpassword'] != null">
										<s:textfield id="rootpassword" name="rootpassword" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('rootpassword').get(0)"/></span>
									</s:if>
									<s:else>
										<s:textfield id="rootpassword" name="rootpassword" cssClass="text-input small-input" />
									</s:else>
								</p>
								
								
								<p>
									<label>Confirmacion password del usuario ROOT</label>									
	
									<s:if test="fieldErrors['rootpassword2'] != null">
										<s:textfield id="rootpassword2" name="rootpassword2" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('rootpassword2').get(0)"/></span>
									</s:if>
									<s:else>
										<s:textfield id="rootpassword2" name="rootpassword2" cssClass="text-input small-input" />
									</s:else>
								</p>
								
								
								<p>
									<label>Email del usuario ROOT</label>									
	
									<s:if test="fieldErrors['rootemail'] != null">
										<s:textfield id="rootemail" name="rootemail" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('rootemail').get(0)"/></span>
									</s:if>
									<s:else>
										<s:textfield id="rootemail" name="rootemail" cssClass="text-input small-input" />
									</s:else>
								</p>
								
								<p>
									<input class="button" type="submit" value="Guardar" />
								</p>
								
							
							


								
							</fieldset>
							
							<div class="clear"></div><!-- End .clear -->
							
						</form>
						
					</div> <!-- End #tab2 -->     
					
					
					<div class="tab-content" id="tab2"> <!-- This is the target div. id must match the href of this div's tab -->
						
						<s:if test="hasActionMessages()">
						<div class="notification success png_bg">
							<a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
							<div>
								<s:actionmessage/>
							</div>
						</div>
						</s:if>
						
						<s:if test="hasActionErrors()">
						<div class="notification error png_bg">
							<a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
							<div>
								<s:actionerror/>
							</div>
						</div>
						
				
						</s:if>
						
						
						<table>
							
							<thead>
														
								<tr>
								   <th>Activo</th>								  
								   <th>Nombre</th>								   
								   <th>Dirección</th>
								   <th>Compañia</th>								   
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
											<a href="#" title="First Page">&laquo; First</a><a href="#" title="Previous Page">&laquo; Previous</a>
											<a href="#" class="number" title="1">1</a>
											<a href="#" class="number" title="2">2</a>
											<a href="#" class="number current" title="3">3</a>
											<a href="#" class="number" title="4">4</a>
											<a href="#" title="Next Page">Next &raquo;</a><a href="#" title="Last Page">Last &raquo;</a>
										</div> <!-- End .pagination -->
										<div class="clear"></div>
									</td>
								</tr>
							</tfoot>
						 
							<tbody>
							<s:iterator value="#session.companyNewBeanList" var="currentCompany">
							
								<tr>
								
									<s:if test='#currentCompany.active == "Y"'>
										<td><input type="checkbox" checked="checked" disabled="disabled"/></td>
									</s:if>
									<s:else>
									    <td><input type="checkbox" disabled="disabled"/></td>
									</s:else>
									
									
									<td><s:property value="#currentCompany.name"/></td>
									<td><s:property value="#currentCompany.address"/></td>
									<td><s:property value="#currentCompany.full_name"/></td>
													
									<td>
										<!-- Icons -->
										 <a href="#" title="Edit"><img src="resources/images/icons/pencil.png" alt="Edit" /></a>
										 <a href="#" title="Delete"><img src="resources/images/icons/cross.png" alt="Delete" /></a> 
										 <a href="#" title="Edit Meta"><img src="resources/images/icons/hammer_screwdriver.png" alt="Edit Meta" /></a>
									</td>
								</tr>
								</s:iterator>
								
								
								
								
								
		
							</tbody>
							
						</table>
						
					</div> <!-- End #tab1 -->
					
   
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->
			

			

			<div class="clear"></div>
			
			
	
			
			<s:include value="/footer.jsp"></s:include>
			
		</div> <!-- End #main-content -->

	</div></body>
	
    </s:if>
	</s:if>
	<s:else>
	 <%
	   out.write("Llamada ilegal del modulo Nuevo Empresa");
	 %>
	</s:else>
</s:if>

<script>


$( ".errorMessage" ).remove();
//$( ".actionMessage" ).remove();



</script>
</html>
