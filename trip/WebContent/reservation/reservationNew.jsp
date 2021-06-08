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
<script src="resources/scripts/fcJS.js" type="text/javascript" charset="utf-8"></script>
<script src="resources/scripts/fcjs.min.js" type="text/javascript" charset="utf-8"></script>
<script src="resources/scripts/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>

 
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
			    
			    int TAG = 14;
			    
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
	

	<body class="bodyMain" ><div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
		
		 

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
			<p id="page-intro"><label ><s:text name="label.reservation.new"/></p>
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
					 <s:if test='%{#session.showTabReservation == null}'>
						<li><a href="#tab1" class="default-tab" ><label ><s:text name="label.reservation.rooms" /></label></a></li> <!-- href must be unique and match the id of target div -->
						<li><a href="#tab2" ><label ><s:text name="label.reservation.room" /></label></a></li>
					</s:if>
					<s:else>
					<s:if test='%{#session.showTabReservation != null}'>
						<li><a href="#tab1"><label ><s:text name="label.reservation.rooms" /></label></a></li> <!-- href must be unique and match the id of target div -->
						<li><a href="#tab2" class="default-tab"><label ><s:text name="label.reservation.room" /></label></a></li>
					</s:if>
					</s:else>
					</ul>
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
				
					<s:if test='%{#session.showTabReservation == null}'>
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
						<s:if test="#session.error != null"> 
								<div class="notification error png_bg">
									<a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
									<div>
										 <s:label value="%{#session.error}"></s:label>
									</div>
								</div>
								</s:if>
						<s:form action="reservationNew">
				         <input type="hidden" name="process" value="" />
				         
				         <fieldset style="border:1px groove #ccc; padding-left: 10px; padding-bottom: 20px;">
				            <legend style="font-weight:bold; ">Búsqueda:</legend>
                         
                         <input type="text" style="text-align: center;" id="txtBusqueda" onkeyup="Buscar()" class="text-input medium-input" placeholder="Hotel,Habitación,Estado,Fecha de Check-In, Fecha de Check-Out"/>
                         
                         
                         </fieldset>
                         
				         <table id="tabla1">
							<thead>
							   	
								<tr>
								   <th></th>
								   <th>Hotel al que pertenece</th>								   
								   <th>Descripción</th>
								   <th>Estado de la habitación</th>
								   <th>Fecha de Check-In</th>
								   <th>Fecha de Check-Out</th>
								   
                                </tr>
								
							</thead>
						 
							
						    <tbody>
							 <s:iterator value="#session.roomsList" var="currentRoom">
							
								 <tr>
									<s:if test="#currentRoom.id_type_status_room.id == 2">	
									<td></td>
						            <td style="color: red;"><s:property value="#currentRoom.id_hotel.description" /></td>
						            <td style="color: red;"><s:property value="#currentRoom.description"/></td>
									<td style="color: red;"><s:property value="#currentRoom.id_type_status_room.status"/></td>
									<td style="color: red;"><s:property value="#currentRoom.date_begin"/></td>
									<td style="color: red;"><s:property value="#currentRoom.date_end"/></td>
								</s:if>
								<s:else>
								    <td><input type="checkbox" name="rooms" value="<s:property value="#currentRoom.id"/>" id="<s:property value="#currentRoom.id"/>"/></td>
						            <td style="color: green;"><s:property value="#currentRoom.id_hotel.description" /></td>
						            <td style="color: green;"><s:property value="#currentRoom.description"/></td>
									<td style="color: green;"><s:property value="#currentRoom.id_type_status_room.status"/></td>
									<td style="color: green;"><s:property value="#currentRoom.date_begin"/></td>
									<td style="color: green;"><s:property value="#currentRoom.date_end"/></td>
								</s:else> 
								</tr>
								</s:iterator>
							</tbody>
							
						</table>	
						<input class="button" type="button" onclick="showStep(1,'selectedRooms')" value="Reservar" />
					</s:form>	
										
					</div> <!-- End #tab1 -->  
					<!-- Datos generales de la reservación -->     
					<s:if test='%{#session.showTabReservation != null}'>
					 <div class="tab-content default-tab" id="tab2">
					</s:if>
					<s:else>
					   <div class="tab-content"  id="tab2" >
					</s:else>
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
						<form  action="reservationNew" method="post" enctype="multipart/form-data">
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
									<option value="<s:property value="#currentStatus.id"/>"><s:property value="#currentStatus.status"/></option>
								</s:iterator>
								</select>
								<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('types_status_reservation').get(0)" /></span>
							 </s:if>
							 <s:else>
							    <select name="types_status_reservation" id="types_status_reservation" onchange="ChangeDate(this)">
								   <option value="0">--Seleccione--</option>
								<s:iterator value="#session.types_status_reservation" var="currentStatus">
									<option value="<s:property value="#currentStatus.id"/>"><s:property value="#currentStatus.status"/></option>
								</s:iterator>
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
							   	 	<s:textfield id="date_to_pay" name="date_to_pay" cssClass="text-input medium-input datepicker"  readonly="true" />
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
									<option value="<s:property value="#currentTransport.id"/>"><s:property value="#currentTransport.description"/></option>
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
							<s:textfield id="name_client" name="name_client" cssClass="text-input medium-input" />
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
							    <textarea class="" id="textarea" name="notes" cols="50" rows="5"></textarea>
							</s:else>
							</p>
							</div>
							<p>
							   <input type="checkbox"  name="cotizacion" value="cotizacion"/>
							   <s:label value=""><s:text name="label.reservation.cotizacion" /></s:label>
							</p>
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
                   
                   <s:if test="#session.showViewSelectedRoom != null">
                    <s:if test="#session.listroomSelected != null || #session.roomSelected != null ">
                   <s:if test="#session.listroomSelected.size()>1">
                     <fieldset style="border:1px groove #ccc; padding-left: 10px; padding-bottom: 10px; padding-top: 10px;">
					  <legend style="font-weight:bold; ">Seleccione la habitación:</legend>
                       
                    
                    <div style="float: left; padding-left: 50px;"> 
                          <select name="rooms" id="rooms" onchange="showStep(2,'roomSelected')">
								   <s:iterator value="#session.listroomSelected" var="currentRoom">
								     <option value="<s:property value="#currentRoom.id"/>"<s:if test="#session.roomSelected.id ==#currentRoom.id"> selected</s:if> ><s:property value="#currentRoom.description"/></option>
								   </s:iterator>
						  </select>
						 </div> 
					   </fieldset>
					 </s:if>
					
				      <fieldset style="border:1px groove #ccc; padding-left: 10px; padding-bottom: 10px; padding-top: 10px;">
					  <legend style="font-weight:bold; ">Añadir cantidad de huéspedes a la habitación: <s:property value="#session.roomSelected.description"/></legend>
                     
				         <div style="float: left; padding-right: 20px;">
							<label ><s:text name="label.reservation.cant.adults" /></label>
							<s:if test="#session.max_adults_per_room != null">
								<s:if test="#session.cant_adult != null">
								 <s:if test="fieldErrors['cant_adults'] != null">
									 <%int count = (Integer)ses.getAttribute("max_adults_per_room");%>
									  <select name="cant_adults" id="cant_adults" onchange="fillChildrens(this)">
									  <%int i;%>
									  <%for (i = 1; i<= count; i++){%>
									     <option value="<%=i%>" <s:if test="#session.cant_adult==<%=i%>">selected</s:if>><%=i%></option>
									 <%}%>
									  </select>
									  <span class="input-notification error png_bg" ><s:property value="fieldErrors.get('cant_adults').get(0)" /></span>
								 </s:if>
								 <s:else>
									  <%int count = (Integer)ses.getAttribute("max_adults_per_room");%>
									  <select name="cant_adults" id="cant_adults" onchange="fillChildrens(this)">
									  <%int i;%>
									  <%for (i = 1; i<= count; i++){%>
									      <option value="<%=i%>" <s:if test="#session.cant_adult==<%=i%>">selected</s:if>><%=i%></option>
									 <%}%>
									  </select>
								 </s:else>
								  
								 </s:if>
								 <s:else>
								      <s:if test="fieldErrors['cant_adults'] != null"> 
									   <%int count = (Integer)ses.getAttribute("max_adults_per_room");%>
									  <select name="cant_adults" id="cant_adults" onchange="fillChildrens(this)">
									  <%int i;%>
									  <%for (i = 1; i<= count; i++){%>
									     <option value="<%=i%>"><%=i%></option>
									 <%}%>
									  </select>
									  <span class="input-notification error png_bg" ><s:property value="fieldErrors.get('cant_adults').get(0)" /></span>
									  </s:if>
									  <s:else>
									  <%int count = (Integer)ses.getAttribute("max_adults_per_room");%>
									  <select name="cant_adults" id="cant_adults" onchange="fillChildrens(this)">
									  <%int i;%>
									  <%for (i = 1; i<= count; i++){%>
									     <option value="<%=i%>"><%=i%></option>
									 <%}%>
									  </select>
									  </s:else>
								 </s:else>
							</s:if>
							</div>
							
							 <div style="float: left; padding-left: 30px;">
							<label><s:text name="label.reservation.cant.childrens" /></label>
							<s:if test="#session.cant_children != null">
							   <select name="cant_childrens" id="cant_childrens">
								   <option value="0" <s:if test="#session.cant_children==0">selected</s:if> >0</option>
								   <option value="1" <s:if test="#session.cant_children==1">selected</s:if> >1</option>
								   <option value="2" <s:if test="#session.cant_children==2">selected</s:if> >2</option>
								   <option value="3" <s:if test="#session.cant_children==3">selected</s:if> >3</option>
								   <option value="4" <s:if test="#session.ccant_children==4">selected</s:if>>4</option>
							  </select>
							</s:if>
							<s:else>
							   <select name="cant_childrens" id="cant_childrens">
								   <option value="0">0</option>
								   <option value="1">1</option>
								   <option value="2">2</option>
								   <option value="3">3</option>
								   <option value="4">4</option>
							   </select>
							</s:else>
							</div>
						<div style="float: left; padding-top: 25px; padding-left: 30px;">
							<input class="button" type="button" onclick="showStep(2,'addListGuest')" value="Agregar"/>	
						</div>
						
				       </fieldset>
					  </s:if>
				     
					
			 		 
               
          
                <!-- Fin de los datos de cantidad de adultos y de niños para cada room -->       
                    
                   
                    
               <!-- Datos de los huéspedes -->   
              
               <fieldset style="border:1px groove #ccc; padding-left: 10px;">
				 <legend style="font-weight:bold; ">Datos de los huéspedes de la habitación: <s:property value="#session.roomSelected.description"/></legend>
                  
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
								 <a href="reservationNew.action?guest_name=<s:property value="#currentGuest.name"/>&guest_age=<s:property value="#currentGuest.age"/>&process=ModifyGuest" title="Mofificar" title="Mofificar"><img src="resources/images/icons/pencil.png" alt="Modificar" /></a> 
							     <a href="reservationNew.action?guest_name=<s:property value="#currentGuest.name"/>&guest_age=<s:property value="#currentGuest.age"/>&process=DeleteGuest" title="Eliminar"><img src="resources/images/icons/cross.png" alt="Eliminar" /></a>
								 
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
	  <div class="clear"></div><!-- End .clear -->
		
						<!-- End #tab2 -->
		</div> <!-- End .content-box-content -->
		</s:if>
		
				
	    </div> <!-- End .content-box -->
			
        	<div class="clear"></div>
	 </s:if>
	 <s:else>
	  <small> <label ><s:text name="label.reservation.room_selected" /></label></small>
	</s:else>
			
			
			<div id="footer">
				<small> <!-- Remove this notice or replace it with whatever you want -->
						&#169; Copyright 2016 compañia | Powered by <a href="http://www.bituos.com" target="_blank">Bituost Tools</a> | <a href="#">Top</a>
				</small>
			</div><!-- End #footer -->
			
		</div> <!-- End #main-content -->

	  </div>
	</body>
	</s:if>
    

	
	 <%
	   out.write("Llamada ilegal del módulo Nueva Reservación");
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
   
   function fillChildrens(sel) 
   {
	   var valor_combo=document.getElementById("cant_adults").options[document.getElementById("cant_adults").selectedIndex].value;
       var select_childrens = document.getElementById("cant_childrens");
	   
       var count = select_childrens.options.length;
      // alert(count);
       while (count > 0)
   	   {
    	   select_childrens.options.remove(count);
    	   count --;
   	   }
       if(valor_combo != 0)
       {
    	   for ( var i = 0; i <= 4-valor_combo; i++) {
    		   var op1 = document.createElement('option');
    		   var txt1 = document.createTextNode(i);
        	   //alert(txt1);
    		   op1.value = i;
        	   op1.appendChild(txt1);
        	   select_childrens.appendChild(op1); 
		}
       }
    	  
	 }
	   

</script>

<script>
$('#tabla1').fcJS();
</script>

</html>
