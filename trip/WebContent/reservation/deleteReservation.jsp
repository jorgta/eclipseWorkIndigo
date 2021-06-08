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
<script type="text/javascript" src="resources/scripts/jquery.datePicker.js"></script>
<script type="text/javascript" src="resources/scripts/jquery.date.js"></script>

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
			<p id="page-intro">Cancelar reservación</p>
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
					   <li><a href="#tab1" class="default-tab" >Habitaciones reservadas</a></li> <!-- href must be unique and match the id of target div -->
					</ul>	
					
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
				
					<s:if test='%{#session.roomSelected == null && #session.addReservation == null}'>
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
						<table>
							<thead>
														
								<tr>
								  <!--  <th>Imagen de la habitación</th>			-->					  
								   <th>Hotel al que pertenece</th>								   
								   <th>Descripción</th>
								   <th>Estado de la habitación</th>
								   <th>Fecha de inicio</th>
								   <th>Fecha de bloqueo</th>
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
							<s:iterator value="#session.roomsReserved" var="currentRoom_Reservation">
							
								<tr>
									<!--<td><img src="<s:property value="#currentRoom.image1"/>"></img></div></td>-->
									
									<td><s:property value="#currentRoom_Reservation.id_room.id_hotel.description"/></td>
									<td><s:property value="#currentRoom_Reservation.id_room.description"/></td>
									<td><s:property value="#currentRoom_Reservation.id_room.id_type_status_room.status"/></td>
									<td><s:property value="#currentRoom_Reservation.id_reservation.date_begin"/></td>
									<td><s:property value="#currentRoom_Reservation.id_reservation.date_blocked"/></td>
																						
									<td>
										<!-- Icons -->
										 <a href="reservationDelete.action?id=<s:property value="#currentRoom_Reservation.id"/>&process=CancelReservation" title="Cancelar reservación" title="Cancelar reservación"><img src="resources/images/icons/cross.png" alt="Cancelar reservación" /></a> 
						           </td>
								</tr>
								</s:iterator>
							</tbody>
							
						</table>		
										
					</div> <!-- End #tab1 -->     
						
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
	   out.write("Llamada ilegal del módulo Nueva Reservación");
	 %>
</s:if>

<script>

$(function() {
	$( ".datepicker" ).datepicker({
		  dateFormat: "dd/mm/yy"
	});
});
/*$(document).ready(function() {
    $('#btnAdd').click(function() {
        var num     = $('.clonedInput').length;
        var newNum  = new Number(num + 1);

        var newElem = $('#name_guest' + num).clone().attr('id', 'name_guest' + newNum);
        newElem.attr('name', 'name_guest' + newNum);
        var newElem = $('#age_guest' + num).clone().attr('id', 'age_guest' + newNum);
        newElem.attr('age', 'age_guest' + newNum);

        newElem.children(':first').attr('id', 'name' + newNum).attr('name', 'name' + newNum);
        $('#input' + num).after(newElem);
        $('#btnDel').attr('disabled','');

        if (newNum == 4)
        $('#btnAdd').attr('disabled','disabled');
});

});*/

/*$(document).ready(function() {

    var MaxInputs       = 8; //Número Maximo de Campos
    var contenedor       = $("#contenedor"); //ID del contenedor
    var AddButton       = $("#agregarCampo"); //ID del Botón Agregar

    //var x = número de campos existentes en el contenedor
    var x = $("#contenedor div").length + 1;
    var FieldCount = x-1; //para el seguimiento de los campos

    $(AddButton).click(function (e) {
        if(x <= MaxInputs) //max input box allowed
        {
            FieldCount++;
            //agregar campo
            $(contenedor).append('<div><input type="text" name="name_guest[]" id="name'+ FieldCount +'" placeholder="Texto '+ FieldCount +'"/><a href="#" class="eliminar">&times;</a><input type="text" name="age_guest[]" id="age_'+ FieldCount +'" placeholder="Texto '+ FieldCount +'"/><a href="#" class="eliminar">&times;</a></div>');
            x++; //text box increment
        }
        return false;
    });

    $("body").on("click",".eliminar", function(e){ //click en eliminar campo
        if( x > 1 ) {
            $(this).parent('div').remove(); //eliminar el campo
            x--;
        }
        return false;
    });
});*/
 
$( ".errorMessage" ).remove();
</script>
</html>
