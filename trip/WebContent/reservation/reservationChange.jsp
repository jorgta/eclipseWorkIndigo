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
 
 
<link rel="stylesheet" href="datepicker/jquery-ui.css">
<script src="datepicker/jquery-1.10.2.js"></script>
<script src="datepicker/jquery-ui.js"></script>
<link rel="stylesheet" href="datepicker/style.css">


 
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
			<p id="page-intro"><label ><s:text name="label.reservation.change"/></p>
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
					 <s:if test='%{#session.roomSelected == null}'>
						<li><a href="#tab1" class="default-tab" ><label ><s:text name="label.reservation.reservations" /></label></a></li> <!-- href must be unique and match the id of target div -->
						<li><a href="#tab2" ><label ><s:text name="label.reservation.change" /></label></a></li>
					</s:if>
					<s:else>
					<s:if test='%{#session.roomSelected != null}'>
						<li><a href="#tab1"><label ><s:text name="label.reservation.reservations" /></label></a></li> <!-- href must be unique and match the id of target div -->
						<li><a href="#tab2" class="default-tab"><label ><s:text name="label.reservation.change" /></label></a></li>
					</s:if>
					</s:else>
					
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
				
					<s:if test='%{#session.roomSelected == null}'>
					  <div class="tab-content default-tab"  id="tab1">
					</s:if>
					<s:else>
					   <div class="tab-content" id="tab1">
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
						 <s:if test="#session.done != null"> 
								<div class="notification success png_bg">
									<a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
									<div>
										 <s:label value="%{#session.done}"></s:label>
									</div>
								</div>
						</s:if>
						<s:form>
						<fieldset style="border:1px groove #ccc; padding-left: 10px; padding-bottom: 20px;">
				            <legend style="font-weight:bold; ">Búsqueda:</legend>
                         
                         <input type="text" style="text-align: center;" id="txtBusqueda" onkeyup="Buscar()" class="text-input medium-input" placeholder="Hotel,Habitación,Estado,Fecha de Check-In, Fecha de Check-Out"/>
                         
                         
                         </fieldset>
						
						<table id="tabla1">
							<thead>
														
								<tr>
								   <th><label ><s:text name="label.room.hotelToOwner" /></label></th>								   
								   <th><label ><s:text name="label.room.description" /></label></th>
								   <th><label ><s:text name="label.room.state" /></label></th>
								   <th>Fecha de Check-In</th>
								   <th>Fecha de Check-Out</th>
                                </tr>
								
							</thead>
						  <tbody>
							<s:iterator value="#session.roomsReservation2Change" var="currentRoom">
							
								<tr>
									<s:if test="#currentRoom.id_room.id_type_status_room.id == 2">	
									<td style="color: red;"><s:property value="#currentRoom.id_room.id_hotel.description" /></td>
									<td style="color: red;"><s:property value="#currentRoom.id_room.description"/></td>
									<td style="color: red;"><s:property value="#currentRoom.id_room.id_type_status_room.status"/></td>
								    <td style="color: red;"><s:property value="#currentRoom.id_room.date_begin"/></td>
									<td style="color: red;"><s:property value="#currentRoom.id_room.date_end"/></td>
								
								
								</s:if>
								<s:elseif test="#currentRoom.id_room.id_type_status_room.id == 3">
									
								    <td style="color: blue;"><s:property value="#currentRoom.id_room.id_hotel.description" /></td>
									<td style="color: blue;"><s:property value="#currentRoom.id_room.description"/></td>
									<td style="color: blue;"><s:property value="#currentRoom.id_room.id_type_status_room.status"/></td>
								    <td style="color: blue;"><s:property value="#currentRoom.id_room.date_begin"/></td>
									<td style="color: blue;"><s:property value="#currentRoom.id_room.date_end"/></td>
								</s:elseif>
								<s:elseif test="#currentRoom.id_room.id_type_status_room.id == 4">
									<td style="color: green;"><s:property value="#currentRoom.id_room.id_hotel.description" /></td>
									<td style="color: green;"><s:property value="#currentRoom.id_room.description"/></td>
									<td style="color: green;"><s:property value="#currentRoom.id_room.id_type_status_room.status"/></td>
								    <td style="color: green;"><s:property value="#currentRoom.id_room.date_begin"/></td>
									<td style="color: green;"><s:property value="#currentRoom.id_room.date_end"/></td>
								</s:elseif>
								    <td>
										<!-- Icons -->
										<a href="reservationChange.action?id=<s:property value="#currentRoom.id"/>&process=ChangeReservation" title="Modificar reservación"><img src="resources/images/icons/pencil.png" alt="Modificar reservación" /></a>
									</td>
								</tr>
								</s:iterator>
							</tbody>
							
						</table>	
						</s:form>	
										
					</div> <!-- End #tab1 -->     
					
					
					<s:if test='%{#session.roomSelected != null}'>
					 <div class="tab-content default-tab" id="tab2">
					</s:if>
					<s:else>
					   <div class="tab-content"  id="tab2" >
					</s:else>
					<s:if test="%{#session.roomSelected != null}">
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
					<form  action="reservationChange" method="post" enctype="multipart/form-data">
						<input type="hidden" name="process" value="" />
						
						<s:if test="#session.showViewDataReservation != null ">
						<s:if test="#session.roomSelected != null">
						<div  id="datos" style="display: block">
					      <fieldset style="border:1px groove #ccc; padding-left: 10px;">
							<legend style="font-weight:bold;">Datos generales de la reservación</legend>
                      
						    <fieldset class="column-left"> <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
							<p>
							<label ><s:text name="label.room.date_begin" /></label>
							 <s:textfield id="date_begin" name="date_begin" cssClass="text-input medium-input" readonly="true" value="%{#session.date_begin}"/>
							</p>
						    <p>
							<label ><s:text name="label.room.date_end" /></label>
							  <s:textfield name="date_end" cssClass="text-input medium-input"  readonly="true" value="%{#session.date_end}"/>
						   </p>
	                       <p>
							<label ><s:text name="label.reservation.status_reservation" /></label>
							<s:if test="#session.type_status_reservation != null">
								<select name="types_status_reservation" id="types_status_reservation" onclick="ChangeDate(this)">
								   <option value="0">--Seleccione--</option>
								<s:iterator value="#session.types_status_reservation" var="currentStatus">
									<option value="<s:property value="#currentStatus.id" />"<s:if test="#session.type_status_reservation==#currentStatus.id"> selected</s:if>><s:property value="#currentStatus.status"/></option>
								 </s:iterator>
								</select>
							 </s:if>
							 <s:else>
							 <s:if test="fieldErrors['types_status_reservation'] != null">
							  <select name="types_status_reservation" id="types_status_reservation" onchange="ChangeDate(this)">
								   <option value="0">--Seleccione--</option>
								<s:iterator value="#session.types_status_reservation" var="currentStatus">
									<option value="<s:property value="#currentStatus.id"/>" <s:if test="#session.roomSelected.id_reservation.id_type_status_reservation.id==#currentStatus.id"> selected</s:if>><s:property value="#currentStatus.status"/></option>
								</s:iterator>
								</select>
								<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('types_status_reservation').get(0)" /></span>
							 </s:if>
							 <s:else>
							    <select name="types_status_reservation" id="types_status_reservation" onchange="ChangeDate(this)">
								   <option value="0">--Seleccione--</option>
								   <s:if test="#session.roomSelected.id_reservation.id_type_status_reservation.id==1">
								     <option value="1" selected="selected">RESERVADA SIN ANTICIPO</option>
								     <option value="2">PAGADA PARCIALMENTE</option>
								     <option value="3">PAGADA COMPLETAMENTE</option>
								   </s:if>
								   <s:elseif test="#session.roomSelected.id_reservation.id_type_status_reservation.id==2">
								     <option value="2" selected="selected">PAGADA PARCIALMENTE</option>
								     <option value="3">PAGADA COMPLETAMENTE</option>
								   </s:elseif>
								   <s:else>
								    <option value="3" selected="selected">PAGADA COMPLETAMENTE</option>
								   
								   </s:else>
								   </select>
								   </s:else>
								
								
							 </s:else>
							
							 </p>
						 	<p>
							 <label ><s:text name="label.reservation.date_pay" /></label>
							 <s:if test="#session.date_to_pay2 != null">
								<s:if test="fieldErrors['date_to_pay'] != null">
									<s:textfield id="date_to_pay" name="date_to_pay" cssClass="text-input small-input datepicker" readonly="true"/>
									<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('date_to_pay').get(0)" /></span>
								</s:if>
								<s:else>
								<s:textfield id="date_to_pay" name="date_to_pay" cssClass="text-input small-input datepicker"  readonly="true" value="%{#session.date_to_pay2}"/>
								</s:else>
							 </s:if>
							 <s:else>
								<s:if test="fieldErrors['date_to_pay'] != null">
									<s:textfield id="date_to_pay" name="date_to_pay" cssClass="text-input medium-input datepicker" readonly="true"/>
									<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('date_to_pay').get(0)" /></span>
								</s:if>
								<s:else>
							   	 	<s:textfield id="date_to_pay" name="date_to_pay" cssClass="text-input medium-input datepicker"  readonly="true" value="%{#session.roomSelected.id_reservation.date_to_pay}" />
								</s:else>
							 </s:else>
							 </p>
							 <p>
							<label ><s:text name="label.reservation.selectTransport" /></label>
							<s:if test="#session.type_transportation != null">
								<select name="types_transportation" id="types_transportation">
								   <option value="0">--Seleccione--</option>
								<s:iterator value="#session.types_transportation" var="currentTransport">
								   <option value="<s:property value="#currentTransport.id"/>" <s:if test="#session.type_transportation==#currentTransport.id">selected</s:if> ><s:property value="#currentTransport.description"/></option>
								</s:iterator>
								</select>
							</s:if>
							<s:else>
							    <s:if test="fieldErrors['types_transportation'] != null">
								<select name="types_transportation" id="types_transportation">
								   <option value="0">--Seleccione--</option>
								   <s:iterator value="#session.types_transportation" var="currentTransport">
									<option value="<s:property value="#currentTransport.id"/>"><s:property value="#currentTransport.description"/></option>
								   </s:iterator>
								</select>
								<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('types_transportation').get(0)" /></span>
								</s:if>
								<s:else>
							   <select name="types_transportation" id="types_transportation">
								   <option value="0">--Seleccione--</option>
								<s:iterator value="#session.types_transportation" var="currentTransport">
									<option value="<s:property value="#currentTransport.id"/>" <s:if test="#session.roomSelected.id_reservation.id_transport.id==#currentTransport.id">selected</s:if>><s:property value="#currentTransport.description"/></option>
								</s:iterator>
								</select>
								</s:else>
							</s:else>
							</p>
							</fieldset>
							<fieldset class="column-left">
							<p>
							<label ><s:text name="label.reservation.name_client" /></label>
							<s:if test="#session.name_client != null">
							  <s:textfield id="name_client" name="name_client" cssClass="text-input medium-input" value="%{#session.name_client}"/>
							</s:if>
							<s:else>
							<s:if test="fieldErrors['name_client'] != null">
								<s:textfield id="name_client" name="name_client" cssClass="text-input medium-input" />
								<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('name_client').get(0)"/></span>
							</s:if>
							<s:else>
							<s:textfield id="name_client" name="name_client" cssClass="text-input medium-input" value="%{#session.roomSelected.id_reservation.client_name}"/>
							</s:else>
							</s:else>
							</p>
							<div style="clear: both;">
							<p>
							<label ><s:text name="label.reservation.notes" /></label>
							<s:if test="#session.notes != null">
							    <textarea class="" id="textarea" name="notes" cols="50" rows="5"><s:property value="%{#session.notes}"/></textarea>
							</s:if>
							<s:else>
							    <textarea class="" id="textarea" name="notes" cols="50" rows="5"><s:property value="%{#session.roomSelected.id_reservation.notes}"/></textarea>
							</s:else>
							</p>
							</div>
							<p>
							 <input class="button" type="button" onclick="showStep(2,'addReservation')" value="Siguiente" />
							</p>
			 		      </fieldset>
			 		     </fieldset>
                       </div>
                       </s:if>
                      </s:if>
                  
                    <!-- Datos generales de la reservación -->   
                   
                   
                    <!-- Datos de cantidad de adultos y de niños para cada room -->   
                   
             <s:if test="#session.showViewListGuest != null">
                     
					<!-- Datos de los huéspedes -->   
              
               <fieldset style="border:1px groove #ccc; padding-left: 10px;">
				 <legend style="font-weight:bold; ">Datos de los huéspedes de la habitación: <s:property value="#session.roomSelected.id_room.description"/></legend>
                 <table>
					<thead>
					<tr>								   			  
					  <th>
					    <p>	
					    <s:if test="#session.beanReservation_Person != null">
					           <s:if test="fieldErrors['guest_name'] != null">
									<input type="text" id="guest_name" name="guest_name" class="text-input medium-input"  placeholder="Nombre y Apellidos"/>
		                            <span class="input-notification error png_bg" ><s:property value="fieldErrors.get('guest_name').get(0)"/></span>
							  </s:if>
							 <s:else>
							      <input type="text" id="guest_name" name="guest_name" class="text-input medium-input" placeholder="Nombre y Apellidos" value="<s:property value="%{#session.beanReservation_Person.name}"/>"></input>
		                      </s:else>
                          </s:if>
                         <s:else>
							<s:if test="fieldErrors['guest_name'] != null">
									<input type="text" id="guest_name" name="guest_name" class="text-input medium-input" placeholder="Nombre y Apellidos"/>
	                                <span class="input-notification error png_bg" ><s:property value="fieldErrors.get('guest_name').get(0)"/></span>
								</s:if>
								<s:else>
								 <input type="text" id="guest_name" name="guest_name" class="text-input medium-input" placeholder="Nombre y Apellidos"/>
                               </s:else>
                         </s:else>								 
						</p>								   
					</th>								   
				    <th>
					    <p>	
					    <s:if test="#session.beanReservation_Person != null">
						       <s:if test="fieldErrors['guest_age'] != null">
						         	<input type="text" id="guest_age" name="guest_age" class="text-input small-input" placeholder="Edad"/>
		           				<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('guest_age').get(0)"/></span>
								</s:if>
		           				<s:else>
		           					<input type="text" id="guest_age" name="guest_age" class="text-input small-input"  placeholder="Edad" value="<s:property value="%{#session.beanReservation_Person.age}"/>" ></input>
		           				</s:else>
		           		 </s:if>
		           		<s:else>
		           		 	 <s:if test="fieldErrors['guest_age'] != null">
						         	<input type="text" id="guest_age" name="guest_age" class="text-input small-input" placeholder="Edad"/>
		           				<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('guest_age').get(0)"/></span>
								</s:if>
		           				<s:else>
		           					<input type="text" id="guest_age" name="guest_age" class="text-input small-input" placeholder="Edad" />
		           				</s:else>
		           		</s:else>
		           		
					    </p>
				    </th>
				    
				    <th>
				      <p>
				         <input class="button" type="button" onclick="showStep(2,'addNewGuest')" value="Agregar"/>
		           	 </p>
				     
				    </th>
				   </tr>
					
						
					</thead>
				 	<tbody>
					<s:iterator value="#session.listGuestAdd" var="currentGuest">
					
						<tr>
							<td><s:property value="#currentGuest.name"/></td>
							<td><s:property value="#currentGuest.age"/></td>
							
																				
							<td>
								<!-- Icons -->
								 <a href="reservationChange.action?id=<s:property value="#currentGuest.id"/>&process=ModifyGuest" title="Mofificar" title="Mofificar"><img src="resources/images/icons/pencil.png" alt="Modificar" /></a> 
							     <a href="reservationChange.action?id=<s:property value="#currentGuest.id"/>&process=DeleteGuest" title="Eliminar"><img src="resources/images/icons/cross.png" alt="Eliminar" /></a>
						</td>
						</tr>
						</s:iterator>
						
						
					</tbody>
					
					<tfoot>
					    
					
					</tfoot>
					
				</table>
				
				
			 	</fieldset>
			 	
			 	
			   <div style="padding-bottom: 100px;">
			  	 	<input class="button" type="button" onclick="showStep(2,'returnReservation')" value="Anterior"/>
			     	<input class="button" type="button" onclick="showStep(2,'confirmReservation')" value="Guardar"/>
			 	</div>
			 </s:if>
			 </form>
			</s:if>
		 </div>
			
			<div class="clear"></div><!-- End .clear -->
		 </s:if>
		 <s:else>
		  <small> <label ><s:text name="label.reservation.room_selected" /></label></small>
		</s:else>
		
						<!-- End #tab2 -->
		</div> <!-- End .content-box-content -->
				
	    </div> <!-- End .content-box -->
		
        	<div class="clear"></div>
			
			<div id="footer">
				<small> <!-- Remove this notice or replace it with whatever you want -->
						&#169; Copyright 2016 compañia | Powered by <a href="http://www.bituos.com" target="_blank">Bituost Tools</a> | <a href="#">Top</a>
				</small>
			</div><!-- End #footer -->
			
		</div> <!-- End #main-content -->

	</div>
	</body>
	</s:if>
	</s:if>
	

	 <%
	   out.write("Llamada ilegal del módulo Cambios datos en reservación");
	 %>


<script>

$(function() {
	
	$( ".datepicker" ).datepicker({
		defaultDate: "+0",
		minDate:"+0",
		maxDate:"+20",
		dateFormat: "dd/mm/yy"
	});
	
});

 
$( ".errorMessage" ).remove();
</script>

<script type="text/javascript">

   function showStep(formindex,process){

		
		if (process=='addReservation'){
			//alert(process);
			document.forms[formindex].process.value='addReservation';
		}
		else if (process=='addGuest') {
			//alert(process);
			document.forms[formindex].process.value='addGuest';
			
		}
		else if (process=='returReservation') {
			document.forms[formindex].process.value='returReservation';
			
		}
		else if (process=='confirmReservation') {
			//alert(process);
			document.forms[formindex].process.value='confirmReservation';
			
		}
		document.forms[formindex].submit();
	}
    
   function Buscar() {
       var tabla = document.getElementById('tabla1');
       var busqueda = document.getElementById('txtBusqueda').value.toLowerCase();
       var cellsOfRow="";
       var found=false;
       var compareWith="";
       for (var i = 1; i < tabla.rows.length; i++) {
           cellsOfRow = tabla.rows[i].getElementsByTagName('td');
           found = false;
           for (var j = 0; j < cellsOfRow.length; j++)
           {
               compareWith = cellsOfRow[j].innerHTML.toLowerCase();
               if (busqueda.length == 0 || (compareWith.indexOf(busqueda) > -1))
               {
                   found = true;
               }
           }
           if(found)
           {
               tabla.rows[i].style.display = '';
           } else {
               tabla.rows[i].style.display = 'none';
           }
       }
   }
   
   function showStep(formindex,process){

		
		if (process=='addReservation'){
			//alert(process);
			document.forms[formindex].process.value='addReservation';
		}
		else if (process=='addNewGuest') {
			//alert(process);
			document.forms[formindex].process.value='addNewGuest';
			
		}
		else if (process=='returnReservation') {
			document.forms[formindex].process.value='returnReservation';
			
		}
		else if (process=='confirmReservation') {
			//alert(process);
			document.forms[formindex].process.value='confirmReservation';
			
		}
		else if (process=='selectedRooms') {
			//alert(process);
			document.forms[formindex].process.value='selectedRooms';
			
		}
		else if (process=='roomSelected') {
			//alert(process);
			document.forms[formindex].process.value='roomSelected';
			
		}
		else if (process=='addListGuest') {
			//alert(process);
			document.forms[formindex].process.value='addListGuest';
			
		}
		
				
		document.forms[formindex].submit();
	}
   
   function ChangeDate(seleccion)
   {
	   //var date_begin=document.getElementById("date_begin").value;
	    
		//$.datepicker.parseDate( "yy-mm-dd", date_begin );
		//alert(date_begin);
       
	  if(seleccion.value == 1)
	  {
		$( ".datepicker" ).datepicker( "setDate", "+1d" );
	  }
	  else
	  if(seleccion.value == 2)
	  {
		  $( ".datepicker" ).datepicker( "setDate", "+20d" );
	  }
	  else
		  $( ".datepicker" ).datepicker( "setDate", "+0d" );  
    }
</script>
</html>
