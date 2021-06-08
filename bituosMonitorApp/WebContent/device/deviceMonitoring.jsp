<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page session="true" import="java.util.*,com.struts2.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Bituos Monitor</title>
<link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />	
<script type="text/javascript" src="resources/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="resources/scripts/simpla.jquery.configuration.js"></script>
<script type="text/javascript" src="resources/scripts/facebox.js"></script>
<script type="text/javascript" src="resources/scripts/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="resources/scripts/jquery.datePicker.js"></script>
<script type="text/javascript" src="resources/scripts/jquery.date.js"></script>
<script type="text/javascript" src="resources/scripts/plotly-latest.min.js"></script>
 





 
</head>
<s:if test="#session.beanUser != null"> 

  <s:if test="#session.module == 'pre'"> 
    <s:if test="#session.processList != null"> 


			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 35;
			    
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
			<p id="page-intro">Monitoreo en Dispositivos</p>
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
					
					<s:if test="#session.deviceSelected == null"> 
						<li><a href="#tab1" class="default-tab" >Dispositivos Registrados</a></li> <!-- href must be unique and match the id of target div -->
						<li><a href="#tab2" >Dispositivo Monitoriado</a></li>	
						
					</s:if>
					<s:else>
					    <li><a href="#tab1" >Dispositivos Registrados</a></li> <!-- href must be unique and match the id of target div -->
						<li><a href="#tab2"  class="default-tab">Dispositivo Monitoriado</a></li>	
						
					</s:else>
					
					</ul>
					
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
					
					<s:if test="#session.deviceSelected == null"> 
					   <div class="tab-content"  id="tab2">
					</s:if>
					<s:else>
					   <div class="tab-content default-tab"  id="tab2">
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
						
						
						
						<s:if test="#session.deviceSelected != null">
							<form id='deviceChangeForm' action="deviceChange" method="post">
								 
								<s:hidden name="id" id="id" />
								<input type="hidden" name="masterid" id="masterid" value="" />
								<input type="hidden" name="detailid" id="detailid" value="" />
								<input type="hidden" name="process" id="process" value="add" />
								
								<fieldset> <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
									
									
									<p>
										<label>Nombre del equipo</label>
										<s:if test="fieldErrors['hostname'] != null">
										  <s:if test="#session.deviceSelected != null">									
											<s:textfield id="hostname" name="hostname" cssClass="text-input small-input"  disabled="true" />
										 </s:if>
										 <s:else>
											<s:textfield id="hostname" name="hostname" cssClass="text-input small-input" disabled="true" />
										 </s:else>
											<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('hostname').get(0)"/></span>
										</s:if>
										<s:else>
										<s:textfield id="hostname" name="hostname" cssClass="text-input small-input" value="%{#session.deviceSelected.hostname}" disabled="true" />
										
										</s:else>
				
											<br /><small>Nombre del equipo</small>
									</p>
									
									<p>
										<label>Mother Board ID</label>
										
																		
										<s:if test="fieldErrors['mbid'] != null">
											<s:textfield id="mbid" name="mbid" cssClass="text-input small-input"  disabled="true" />
											<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('mbid').get(0)" /></span>
										</s:if>
										<s:else>
											<s:textfield id="mbid" name="mbid" cssClass="text-input small-input" value="%{#session.deviceSelected.mbid}" disabled="true"  />
										</s:else>
										
										
										
									</p>
									
									<p>
										<label>CPU ID</label>									
		
										<s:if test="fieldErrors['cpuid'] != null">
											<s:textfield id="cpuid" name="cpuid" cssClass="text-input small-input"  disabled="true" />
											<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('cpuid').get(0)"/></span>
										</s:if>
										<s:else>
											<s:textfield id="cpuid" name="cpuid" cssClass="text-input small-input" value="%{#session.deviceSelected.cpuid}"  disabled="true" />
										</s:else>
									</p>
									
									<p>
										<label><s:text name="label.counter.master" /></label>									
								 
										 
										 <s:if test="#session.changeDeviceListMaster != null">									
											<s:select id="devicemaster" name="devicemaster" cssClass="text-input small-input" listKey="id" listValue="name_type_counter_master"  list="#session.changeDeviceListMaster" value="#session.masterSelected" onchange="change(this.value,'OnChangeMaster')"/>
										</s:if>
										<s:else>
											<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('devicemaster').get(0)"/></span>
										</s:else>	
										 									
												
									</p>
									
									<p>
										<label><s:text name="label.counter.detail" /></label>
										<s:if test="#session.changeDeviceListDetail != null">									
											<s:select id="detail" name="detail" cssClass="text-input small-input" listKey="id" listValue="name_type_counter_detail"  list="#session.changeDeviceListDetail" value=""/>
										</s:if>
										<s:else>
											<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('detail').get(0)"/></span>
										</s:else>																	
									</p>
									
									<p>
										<label><s:text name="label.counter.timetoreport" /></label>
						 
										
										
										<s:if test="fieldErrors['timetoreport'] != null">
										<s:textfield id="timetoreport" name="timetoreport" cssClass="text-input small-input" />
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('timetoreport').get(0)"/></span>
										</s:if>
										<s:else>
											<s:textfield id="timetoreport" name="timetoreport" cssClass="text-input small-input" />
										</s:else>
										
										
										
									</p>
									
							 
									
	
									<p>
									<input class="button" type="submit" value="Agregar" />
								   </p>
								</fieldset>
								
							
							<s:if test="#session.listCounter != null">
							
									
									<table>							
									<thead>
																
										<tr>
										 						  
										   <th><s:text name="label.counter.master" /></th>								   
										   <th><s:text name="label.counter.detail" /></th>
										  	<th><s:text name="label.grid.action" /></th>						   
										</tr>
										
									</thead>
								 
									<tfoot>
										<tr>
											<td colspan="6">
												<div class="bulk-actions align-left">
													<!--<select name="dropdown">
														<option value="option1">Choose an action...</option>
														<option value="option2">Edit</option>
														<option value="option3">Delete</option>
													</select>
													<a class="button" href="#">Apply to selected</a>-->
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
									
									<s:iterator value="#session.listCounter" var="currentCounter">
									
										<tr>


											<td><s:property value="#currentCounter.id_type_counter_detail.id_type_counter_master.name_type_counter_master"/></td>											
											<td> <s:property value="#currentCounter.id_type_counter_detail.name_type_counter_detail"/></td>
									
								
															
											<td>
												<!-- Icons -->
												 <a href="#" title="Edit"><img src="resources/images/icons/pencil.png" alt="Edit" /></a>
												 <a href="deviceChange.action?id=<s:property value="#currentCounter.id_device.id"/>&counterid=<s:property value="#currentCounter.id"/>&process=DeleteCounter" title="Delete"><img src="resources/images/icons/cross.png" alt="Delete" /></a> 
												 <a href="#" title="Edit Meta"><img src="resources/images/icons/hammer_screwdriver.png" alt="Edit Meta" /></a>
											</td>
										</tr>
										</s:iterator>
										
										
										
										
										
				
									</tbody>
									
								</table>
						</s:if>
						
						
								
								<div class="clear"></div><!-- End .clear -->
								
							</form>
							
 
						
						</s:if>
						<s:else>
							<span class="input-notification error png_bg" >no a seleccionado algun dispositivo</span>
						</s:else>	
						
						
						
					</div> <!-- End #tab2 -->     
					
				    <s:if test="#session.deviceSelected == null"> 
					   <div class="tab-content default-tab"  id="tab1">
					</s:if>
					<s:else>
					   <div class="tab-content" id="tab1"> <!-- This is the target div. id must match the href of this div's tab -->
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
								   <th>Activo</th>								  
								   <th>Host Name</th>								   
								   <th>MB ID</th>
								   <th>CPu ID</th>								   
								</tr>
								
							</thead>
						 
							<tfoot>
								<tr>
									<td colspan="6">
										<div class="bulk-actions align-left">
											<!--<select name="dropdown">
												<option value="option1">Choose an action...</option>
												<option value="option2">Edit</option>
												<option value="option3">Delete</option>
											</select>
											<a class="button" href="#">Apply to selected</a>-->
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
							
							<s:iterator value="#session.changeDeviceBeanList" var="currentDevice">
							
								<tr>
								
									<s:if test='#currentDevice.active == 1'>
										<td><input type="checkbox" checked="checked" disabled="disabled"/></td>
									</s:if>
									<s:else>
									    <td><input type="checkbox" disabled="disabled"/></td>
									</s:else>
									
									
									<td><s:property value="#currentDevice.hostname"/></td>
									<td><s:property value="#currentDevice.mbid"/></td>
									<td><s:property value="#currentDevice.cpuid"/></td>
													
									<td>
										<!-- Icons -->
										 <a href="deviceChange.action?id=<s:property value="#currentDevice.id"/>&process=selectDevice2Change" title="Edit"><img src="resources/images/icons/pencil.png" alt="Edit" /></a>
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
	<s:else>
	 <%
	   out.write("Llamada ilegal del modulo Cambio de Usuario");
	 %>
	</s:else>
	

	
	
</s:if>

<script>


$( ".errorMessage" ).remove();
//$( ".actionMessage" ).remove();

var url ="";
function change(deviceid,process)
{	
	document.getElementById("process").value=process;
	if(process=="OnChangeMaster")
	{
		document.getElementById("masterid").value = deviceid;
		//alert(document.getElementById("deviceChangeForm"));
		//document.deviceChangeForm.submit(); 
		$( "#deviceChangeForm" ).submit();
		
	}
	//url= "deviceChange.action?id="+deviceid+"&process="+p;
	//alert(document.getElementById("masterid").value);

	
	 
}



$( ".errorMessage" ).remove();
//$( ".actionMessage" ).remove();
$(function()
{
  $("input[type='checkbox']").change(function()
	{
  		var item=$(this);    
		if(item.is(":checked"))
		  {	 
			//var checkbox=item.attr("name");
		    window.location.href= url ;
		  }
	  	else
		  {
			window.location.href= url ;
		  }
 	});
});






</script>
</html>
