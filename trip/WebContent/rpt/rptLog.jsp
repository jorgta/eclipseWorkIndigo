<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page session="true" import="java.util.*,com.struts2.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Log</title>
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



 

<s:if test="#session.beanUser != null">  
<s:if test="#session.module == 'pre'"> 
    <s:if test="#session.processList != null"> 

			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 79;
			    
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
			<h2>Log</h2>
			<p id="page-intro"></p>
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
						 <!-- <li><a href="#tab1">Table</a></li> -->
						<li><a href="#tab2"  class="default-tab">Log del sistema</a></li>
					</ul>
					 
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
					
				<!--	<div class="tab-content" id="tab1"> 
						
						<div id="progress1"></div> <br/><br/>
						<div id="progress2"></div> <br/><br/>
						<div id="progress3"></div> <br/><br/>
						<div id="progress4"></div><br/><br/>
	                    <input type='button' id='changedProgress' value="Random Progress" />
						<button id="create-user">Create new user</button>
					</div>-->     <!-- End #tab1 -->
					
					<div class="tab-content  default-tab" id="tab2">
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
						
						<form action="rpt.action" method="post" >
							<input type="hidden" name="process" value="rptLog" />
							<fieldset> <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
								
								<p>
									<label>De </label>
									<s:if test="fieldErrors['p1'] != null">
										<s:textfield id="p1" name="p1" cssClass="text-input small-input datepicker" readonly="true"/>
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('p1').get(0)" /></span>
									</s:if>
									<s:else>
									<s:textfield id="p1" name="p1" cssClass="text-input small-input datepicker"  readonly="true"/>
									</s:else>
									<br /><small>De esta fecha</small>
								</p>
								
								<p>
									<label>A </label>
									<s:if test="fieldErrors['p2'] != null">
										<s:textfield id="p2" name="p2" cssClass="text-input small-input datepicker" readonly="true"/>
										<span class="input-notification error png_bg" ><s:property value="fieldErrors.get('p2').get(0)"/></span>
									</s:if>
									<s:else>
									<s:textfield id="p2" name="p2" cssClass="text-input small-input datepicker" readonly="true" />
									</s:else>
										<br /><small>A esta fecha</small>
								</p>
						
								<p>
									<input class="button" type="submit" value="Buscar" />
								</p>
							</fieldset>
							
							<div class="clear"></div><!-- End .clear -->
							
						</form>
						
					</div> <!-- End #tab2 -->        
					
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
	<s:else>
	 <%
	   out.write("Llamada ilegal del modulo Reportes");
	 %>
	</s:else>
	
	 
</s:if>

<script>


$( ".errorMessage" ).remove();
//$( ".actionMessage" ).remove();


	$(function() {
		$( ".datepicker" ).datepicker({
			  dateFormat: "dd/mm/yy"
		});
	});
	 
	//default
  /*$(function() {
	    $( "#progressbar" ).progressbar({
	      value: 9
	    });
	  })*/
	  
	  
	  
(function ($) {
    $.fn.progressbar = function (options) 
    {
        var settings = $.extend({
        width:'300px',
        height:'25px',
        color:'#0ba1b5',
        padding:'0px',
        border:'1px solid #ddd'},options);
 
        //Set css to container
        $(this).css({
            'width':settings.width,
            'border':settings.border,
            'border-radius':'5px',
            'overflow':'hidden',
            'display':'inline-block',
            'padding': settings.padding,
            'margin':'0px 10px 5px 5px'
            });
 
        // add progress bar to container
        var progressbar =$("<div></div>");
        progressbar.css({
        'height':settings.height,
        'text-align': 'right',
        'vertical-align':'middle',
        'color': '#fff',
        'width': '0px',
        'border-radius': '3px',
        'background-color': settings.color
        });
 
        $(this).append(progressbar);
 
        this.progress = function(value)
        {
            var width = $(this).width() * value/100;
            progressbar.width(width).html(value+"% ");
        }
        return this;
    };
 
}(jQuery));
  
   
	  
	  /*var bar1 = $("#progress1").progressbar();
	  bar1.progress(60); 
	   
	  var bar2 = $("#progress2").progressbar({color:'#145ABA'});
	  bar2.progress(90);
	   
	  var bar3 = $("#progress3").progressbar({width:'400px',color:'#0A8F2B',border:'2px solid #0A8F2B',padding:'3px'});
	  bar3.progress(80);
	   
	  var bar4 =$("#progress4").progressbar({width:'500px',color:'#B3240E',border:'1px solid #000000'});
	  bar4.progress(50);*/
	  
	  
	  $(document).ready(function()
			  {
			  	
			  	var bar1 = $("#progress1").progressbar();
			  	bar1.progress(60); 
			  	
			  	var bar2 = $("#progress2").progressbar({color:'#145ABA'});
			  	bar2.progress(90);

			  	var bar3 = $("#progress3").progressbar({width:'400px',color:'#0A8F2B',border:'2px solid #0A8F2B',padding:'3px'});
			  	bar3.progress(80);
			  	
			  	var bar4 =$("#progress4").progressbar({width:'500px',color:'#B3240E',border:'1px solid #000000'});
			  	bar4.progress(50);

			  	$("#changedProgress").click(function()
			  	{
			  		x = getRandomArbitary(10,90);
			  		bar1.progress(x);
			  		x = getRandomArbitary(10,90);		
			  		bar2.progress(x);
			  		x = getRandomArbitary(10,90);		
			  		bar3.progress(x);
			  		x = getRandomArbitary(10,90);
			  		bar4.progress(x);
			  	});
			  	
			  });
			  function getRandomArbitary (min, max) {
			      return parseInt(Math.random() * (max - min) + min);
			  }
			  
			  
		  
</script>


<script>
	$(function() {
		var dialog, form,

			// From http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
			emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
			name = $( "#name" ),
			email = $( "#email" ),
			password = $( "#password" ),
			allFields = $( [] ).add( name ).add( email ).add( password ),
			tips = $( ".validateTips" );

		/*function updateTips( t ) {
			tips
				.text( t )
				.addClass( "ui-state-highlight" );
			setTimeout(function() {
				tips.removeClass( "ui-state-highlight", 1500 );
			}, 500 );
		}*/

		/*function checkLength( o, n, min, max ) {
			if ( o.val().length > max || o.val().length < min ) {
				o.addClass( "ui-state-error" );
				updateTips( "Length of " + n + " must be between " +
					min + " and " + max + "." );
				return false;
			} else {
				return true;
			}
		}*/

		/*function checkRegexp( o, regexp, n ) {
			if ( !( regexp.test( o.val() ) ) ) {
				o.addClass( "ui-state-error" );
				updateTips( n );
				return false;
			} else {
				return true;
			}
		}*/

		function addUser() {
			var valid = true;
	
			return valid;
		}

		dialog = $( "#messages" ).dialog({
			autoOpen: false,
			height: 500,
			width: 650,
			modal: true
		});

		/*form = dialog.find( "form" ).on( "submit", function( event ) {
			event.preventDefault();
			addUser();
		});*/

		$( "#create-user" ).button().on( "click", function() {
			dialog.dialog( "open" );
		});
		
		
		$(".messages").on("click",function(){
			dialog.dialog( "open" );
			  //post code
			});
		
		
		
	});
	</script>
</html>
