<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page session="true" import="java.util.*,com.struts2.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<html>
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

 
<link rel="stylesheet" type="text/css" href="datetimepicker-master/jquery.datetimepicker.css"/ >
<script src="datetimepicker-master/jquery.js"></script>
<script src="datetimepicker-master/build/jquery.datetimepicker.full.min.js"></script> 
 
</head>
<s:if test="#session.beanUser != null">  
<s:if test="#session.module == 'pre'"> 
    <s:if test="#session.processList != null"> 

			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 81;
			    
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
		          response.sendRedirect( "../loginnew.jsp");
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
			<p id="page-intro"><label ><s:text name="label.room.new" /></p>
            <a class="messages"  href="#messages1" rel="modal" title="3 Messages">3 Messages</a>
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
					 <s:if test='%{#session.beanHotel2AddRoom == null && #session.beanRoom2Add == null}'>
						<li><a href="#tab1" class="default-tab" ><label ><s:text name="label.hotel.hotels" /></label></a></li> <!-- href must be unique and match the id of target div -->
						<li><a href="#tab2" ><label ><s:text name="label.room.newRoom" /></label></a></li>	
						<li><a href="#tab3" ><label ><s:text name="label.room.rooms" /></label></a></li>
					</s:if>
					<s:else>
					<s:if test='%{#session.beanHotel2AddRoom != null && #session.beanRoom2Add == null}'>
					    <li><a href="#tab1" ><label ><s:text name="label.hotel.hotels" /></label></a></li> <!-- href must be unique and match the id of target div -->
						<li><a href="#tab2"  class="default-tab"><label ><s:text name="label.room.newRoom" /></label></a></li>	
						<li><a href="#tab3" ><label ><s:text name="label.room.rooms" /></label></a></li>
					</s:if>
				   </s:else>
				   <s:else>
					<s:if test='%{#session.beanHotel2AddRoom != null && #session.beanRoom2Add != null}'>
					    <li><a href="#tab1" ><label ><s:text name="label.hotel.hotels" /></label></a></li> <!-- href must be unique and match the id of target div -->
						<li><a href="#tab2" ><label ><s:text name="label.room.newRoom" /></label></a></li>	
						<li><a href="#tab3" class="default-tab" ><label ><s:text name="label.room.rooms" /></label></a></li>
					</s:if>
				   </s:else>
					
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
				
					<s:if test='%{#session.beanHotel2AddRoom != null && #session.beanRoom2Add == null}'>
					   <div class="tab-content default-tab"  id="tab2">
					</s:if>
					<s:else>
					   <div class="tab-content" id="tab2">
					</s:else>
							
					<s:if test="%{#session.beanHotel2AddRoom != null && #session.beanRoom2Add == null}">						
						<s:if test="#session.error != null"> 
						<div class="notification error png_bg">
							<a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
							<div>
								 <s:label value="%{#session.error}"></s:label>
							</div>
						</div>
						</s:if>
						<s:if test="#session.information != null"> 
						<div class="notification information png_bg">
							<a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
							<div>
								 <s:label value="%{#session.information}"></s:label>
							</div>
						</div>
						</s:if>
						
					   <s:if test="#session.attention != null"> 
						<div class="notification attention png_bg">
							<a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
							<div>
								 <s:label value="%{#session.attention}"></s:label>
							</div>
						</div>
						</s:if>
						
						<form action="roomNew" method="post" enctype="multipart/form-data">
							<input type="hidden" name="process" value="selectedHotel" />
							<fieldset class="column-left"> <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
								<p>
									<label ><s:text name="label.room.hotel" /></label>
									<s:if test="fieldErrors['hotel'] != null">
									  <s:if test="#session.beanHotel2AddRoom != null">									
										<s:textfield id="hotel" name="hotel" cssClass="text-input medium-input" />
									 </s:if>
									 <s:else>
										<s:textfield id="hotel" name="hotel" cssClass="text-input medium-input" />
									 </s:else>
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('hotel').get(0)"/></span>
									</s:if>
									<s:else>
									<s:textfield id="hotel" name="hotel" cssClass="text-input medium-input" value="%{#session.beanHotel2AddRoom.description}" readonly="true"/>
									
									</s:else>
							          <br /><small>Nombre del hotel seleccionado.</small>
								  </p>
									  <p>
										<label ><s:text name="label.room.cantRooms" /></label>
										<s:if test="fieldErrors['cant_rooms'] != null">
											<s:textfield id="cant_rooms" name="cant_rooms" cssClass="text-input small-input" />
											<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('cant_rooms').get(0)"/></span>
										</s:if>
										<s:else>
										<s:textfield id="cant_rooms" name="cant_rooms" cssClass="text-input small-input"/>
										</s:else>
									</p>
								  <p>
									<label ><s:text name="label.room.description" /></label>
									<s:if test="fieldErrors['description'] != null">
										<s:textfield id="description" name="description" cssClass="text-input medium-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('description').get(0)"/></span>
									</s:if>
									<s:else>
									<s:textfield id="description" name="description" cssClass="text-input medium-input" />
									</s:else>
									</p>
									<p>
										<label ><s:text name="label.room.date_begin" /></label>
										<s:if test="fieldErrors['date_begin'] != null">
											<s:textfield id="datetimepicker1" name="date_begin" cssClass="text-input small-input datepicker" />
											<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('date_begin').get(0)" /></span>
										</s:if>
										<s:else>
										<s:textfield id="datetimepicker1" name="date_begin" cssClass="text-input small-input datepicker" />
										</s:else>
									 </p>
									 <p>
										<label ><s:text name="label.room.date_end" /></label>
										<s:if test="fieldErrors['date_end'] != null">
											<s:textfield id="datetimepicker2" name="date_end" cssClass="text-input small-input datepicker" />
											<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('date_end').get(0)" /></span>
										</s:if>
										<s:else>
										<s:textfield id="datetimepicker2" name="date_end" cssClass="text-input small-input datepicker" />
										</s:else>
									 </p>
									  <label ><s:text name="label.room.specs" /></label>
								   	<s:iterator value="#session.listSpecs2AddRoom" var="currentSpec">
									    <input type="checkbox" name="specs" value="<s:property value="#currentSpec.id"/>" id="<s:property value="#currentSpec.id"/>"/>
									    <s:label value=""><s:property value="#currentSpec.description"/></s:label>
								   	 </s:iterator>
								  <p> 
									<s:file name="myFile" label="Seleccione la imagen para subir" accept="image/*" />
								</p>
								 </fieldset>
								<fieldset class="column-left">
								 <p>
									<label ><s:text name="label.room.price1" /></label>
									 <s:if test="fieldErrors['price1'] != null">
										<s:textfield id="price1" name="price1" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('price1').get(0)"/></span>
									</s:if>
									<s:else>
										<s:textfield id="price1" name="price1" cssClass="text-input small-input" />
									</s:else>
								 </p>	
								 <p>
									<label ><s:text name="label.room.price2" /></label>
									<s:if test="fieldErrors['price2'] != null">
										<s:textfield id="price2" name="price2" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('price2').get(0)"/></span>
									</s:if>
									<s:else>
										<s:textfield id="price2" name="price2" cssClass="text-input small-input" />
									</s:else>
								</p>  
								<p>
									<label ><s:text name="label.room.price3" /></label>
									<s:if test="fieldErrors['price3'] != null">
										<s:textfield id="price3" name="price3" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('price3').get(0)"/></span>
									</s:if>
									<s:else>
										<s:textfield id="price3" name="price3" cssClass="text-input small-input" />
									</s:else>
								</p>
								<p>
									<label ><s:text name="label.room.price4" /></label>
									<s:if test="fieldErrors['price4'] != null">
										<s:textfield id="price4" name="price4" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('price4').get(0)"/></span>
									</s:if>
									<s:else>
										<s:textfield id="price4" name="price4" cssClass="text-input small-input" />
									</s:else>
								</p>
								</fieldset>
								<fieldset class="column-left">								
								
								<p>
									<label ><s:text name="label.room.priceBaby" /></label>
									<s:textfield id="priceBaby" name="priceBaby" cssClass="text-input small-input" value = "0.0" />
								</p>
								<p>
									<label ><s:text name="label.room.priceBasic" /></label>
									<s:if test="fieldErrors['priceBasic'] != null">
										<s:textfield id="priceBasic" name="priceBasic" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('priceBasic').get(0)"/></span>
									</s:if>
									<s:else>
										<s:textfield id="priceBasic" name="priceBasic" cssClass="text-input small-input" />
									</s:else>
								</p>
								<p>
									<label ><s:text name="label.room.priceChild" /></label>
									<s:if test="fieldErrors['priceChild'] != null">
										<s:textfield id="priceChild" name="priceChild" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('priceChild').get(0)"/></span>
									</s:if>
									<s:else>
										<s:textfield id="priceChild" name="priceChild" cssClass="text-input small-input" />
									</s:else>
								</p>
								<p>
									<label ><s:text name="label.room.priceJr" /></label>
									<s:if test="fieldErrors['priceJr'] != null">
										<s:textfield id="priceJr" name="priceJr" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('priceJr').get(0)"/></span>
									</s:if>
									<s:else>
										<s:textfield id="priceJr" name="priceJr" cssClass="text-input small-input" />
									</s:else>
								</p>
						      
								<p>
									<input class="button" type="submit" value="Guardar" />
								</p>
								
								</fieldset>
								
							 
							
							<div class="clear"></div><!-- End .clear -->
							
						</form>
					</s:if>
					<s:else>
					  <small><label ><s:text name="label.room.selectHotel" /></label></small>
					</s:else>
					</div> <!-- End #tab2 -->     
					
					
					 <s:if test='%{#session.beanHotel2AddRoom == null && #session.beanRoom2Add == null}'>
					   <div class="tab-content default-tab" id="tab1">
					</s:if>
					<s:else>
					   <div class="tab-content"  id="tab1">
					</s:else>
						
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
								   <th><label ><s:text name="label.room.active" /></label></th>								  
								   <th><label ><s:text name="label.hotel.hotel" /></label></th>								   
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
							<s:iterator value="#session.listHotel2AddRoom" var="currentHotel">
							
								<tr>
								    <td><input type="checkbox" checked="checked" disabled="disabled"/></td>
								    <td><s:property value="#currentHotel.description"/></td>
																															
									<td>
										<!-- Icons -->
										 <a href="roomNew.action?id=<s:property value="#currentHotel.id"/>&process=selectHotel2AddRoom" title="Agregar Habitación"><img src="resources/images/icons/add.png" alt="Agregar Habitación" /></a>
								     </td>
								</tr>
								</s:iterator>
							</tbody>
							
						</table>
						
					</div> <!-- End #tab1 -->
					
   	 				<s:if test='%{#session.beanHotel2AddRoom != null && #session.beanRoom2Add != null}'>
					   <div class="tab-content default-tab"  id="tab3">
					</s:if>
					<s:else>
					   <div class="tab-content"  id="tab3">
					</s:else>
					<s:if test="%{#session.beanHotel2AddRoom != null && #session.beanRoom2Add != null}">	
						<s:if test="#session.done != null"> 
						<div class="notification success png_bg">
							<a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
							<div>
								 <s:label value="%{#session.done}"></s:label>
							</div>
						</div>
						</s:if>
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
								    <th><label ><s:text name="label.room.rooms" /></label><label> del <s:property value="#session.beanHotel2AddRoom.description"/></label></th>								   
			                        <th><label ><s:text name="label.room.date_begin" /></label></th>								   
			                        <th><label ><s:text name="label.room.date_end" /></label></th>								   
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
							
							<s:iterator value="#session.listRoomsHability" var="currentRoom">
							     <tr>
									<td><s:property value="#currentRoom.description"/></td>
									<td><s:property value="#currentRoom.date_begin"/></td>
									<td><s:property value="#currentRoom.date_end"/></td>
									
								</tr>
						    </s:iterator>
						    </tbody>
					</table>
		     
		</div> <!-- End #tab3 -->
		</s:if>
		<s:else>
		  <small> <label ><s:text name="label.room.addRoom" /></label> </small>
		</s:else>
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->
			
        	<div class="clear"></div>
			<div id="footer">
				<small> <!-- Remove this notice or replace it with whatever you want -->
						&#169; Copyright 2016 compañia | Powered by <a href="http://www.bituos.com" target="_blank">Bituost Tools</a> | <a href="#">Top</a>
				</small>
			</div><!-- End #footer -->
			
		</div> <!-- End #main-content -->

	</div></body>
	</s:if>
	</s:if>
	
	
	 <%
	   out.write("Llamada ilegal del módulo Nueva Habitación");
	 %>
</s:if>
<script>

jQuery('#datetimepicker1').datetimepicker({ minDate: 0 });
jQuery('#datetimepicker2').datetimepicker({ minDate: 0 });


</script>

<script>
	$(function() {
		var dialog, form,

		dialog = $( "#messages" ).dialog({
			autoOpen: false,
			height: 500,
			width: 650,
			modal: true
		});

		$( "#create-user" ).button().on( "click", function() {
			dialog.dialog( "open" );
		});
		
		
		$(".messages").on("click",function(){
			dialog.dialog( "open" );
			  //post code
			});
		
		
		
	});
	
	$( ".errorMessage" ).remove();
	</script>
</html>
