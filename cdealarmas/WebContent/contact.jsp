<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Central de Alarmas</title>
<link href="style/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="style/jquery-latest.js"></script><!--Common jQuery plugin here -->

<!--Contact starts here -->
<script type="text/javascript" src="style/jquery_002.js"></script>
<script type="text/javascript" src="style/jquery.js"></script>
<link href="style/jquery.css" type="text/css" rel="stylesheet">
<!--Contact ends here -->

<!--Twitter start here -->
<script type="text/javascript" src="style/jquery_005.js"></script>
<script type="text/javascript" src="style/jquery_003.js"></script>
<script type="text/javascript" src="style/jquery_004.js"></script>
<link rel="stylesheet" href="style/contactable.css" type="text/css">
<!--Twitter ends here -->

<link href="style/ad.css" type="text/css" rel="stylesheet"><!--ad css here -->


<script type="text/javascript">
//Contact starts here	
	$(function(){
		$('#contactAt').contactable({
	 		recipient: 'email@companyname.com',
	 		subject: 'The Quick Message'
	 	});
	});
	
//Contact Ends here

		
$(document).ready(function(){

//Twit start here
		twitShowStatus = 1;
		function twitShow() {
				$('div#twitable').animate({"marginLeft": "-=5px"}, "fast"); 
				$('#twitform').animate({"marginLeft": "-=0px"}, "fast");
				$('div#twitable').animate({"marginLeft": "+=748px"}, "slow");
				$('#twitform').animate({"marginLeft": "+=745px"}, "slow");
				$('.twitContainer .close').css({right: '-4px'});
				twitShowStatus = 0;
			}
		function twitHide() {
				$('#twitform').animate({"marginLeft": "-=745px"}, "slow");
				$('div#twitable').animate({"marginLeft": "-=748px"}, "slow").animate({"marginLeft": "+=5px"}, "fast"); 
				$('.twitContainer .close').css({right: '0px'});
				twitShowStatus =1;
			}
		
			$('div#twitable').click(
				function() {
				if(twitShowStatus==1)
					twitShow();
				else
					twitHide();

			});
		
		username = 'templateworldIN';// twitter User name
		$('#twitform').twit(username);
		$('#twitform').twit(username, {
		limit: 5,
		label: 'Twitter',
		title: 'My tweets'
		});

//Twitter ends here	

//Ad starts	here
//-HeaderAd starts
		$('div#headerBt').click(
		function() {
			$('#headerContent').animate({"marginTop": "-=60px"}, "slow");
			$(this).animate({"marginTop": "-=60px"}, "slow").animate({"marginTop": "+=5px"}, "fast"); 
			
			$('#TotalBodyId').animate({"marginTop": "-=60px"}, "slow");
			$('#TotalBodyId').css({backgroundPosition: "0 0"});

		});

//-FooterAd starts
		$('div#footerBt').click(
		function() {
			$('#footerContent').animate({"marginBottom": "-=45px"}, "slow");
			$(this).animate({"marginBottom": "-=45px"}, "slow").animate({"marginBottom": "+=5px"}, "fast"); 
		});

//Ad ends here
							
});
	
</script>

</head>

<body id="TotalBodyId">
<!--Inline css is needed for the top banner/ad, top-margin should be same as the ad-block height-->




<!--Contact starts here -->
<!--<div id="contactAt"><div id="contactable"></div><div id="contactForm" class="feedContainer"><a id="close_feedback" title="Close"><img src="style/btn-close.png" alt="Close" class="close" border="0"></a><div class="topCrv"></div><div class="leftHand"><div class="leftshad"><div class="rightHand"><div class="rightshad"><div class="gradient"><form id="contactFormId" method="" action=""><div id="loading">Please Wait..<br><img src="style/ajax-loader.gif" border="0"></div><div class="holder"><input id="recipient" name="recipient" value="email@companyname.com" type="hidden"><input id="subject" name="subject" value="The Quick Message" type="hidden"><label><input value="Name" class="example" placeholder="Name" id="name" name="name" type="text"></label><label><input value="Email" class="example" placeholder="Email" id="email" name="email" type="text"></label><label><textarea class="example" placeholder="Message" id="comment" name="comment">Message</textarea></label><label><input name="Submit" value="SEND" class="btn" type="submit"><span style=" padding-top:4px;">Please feel free to get in touch,<br>we value your feedback</span></label></div></form><p class="thankNote" id="callback"></p></div></div></div></div></div><div class="botCrv"></div></div></div>-->
<!--Contact ends here -->

<!--Twitter starts here -->
<!--<div id="twitContent">
	<div style="display: block;" id="twitable"></div>
	<div id="twitform" class="twitContainer lastNode twit"><a id="closeTwitter" title="Close"><img src="style/btn-close.png" alt="Close" class="close" border="0"></a> <div class="topCrv"></div> <div class="leftHand"> <div class="leftshad"> <div class="rightHand"> <div class="rightshad"> <div class="gradient"> <div class="newsWrap"><h3> <a href="http://twitter.com/templateworldIN/" target="_blank">  <img src="style/tw-logo_normal.jpg" alt="templateworldIN" title="templateworldIN" style="vertical-align: middle;"> </a>&nbsp;&nbsp;<a href="http://twitter.com/templateworldIN/" target="_blank">templateworldIN</a></h3><p class="odd"> <span>We are on FaceBook! - <a href="http://facebook.com/TemplateWorld" target="_blank">http://facebook.com/TemplateWo...</a></span></p><p class="even"> <span>5 *NEW* Dynamic CSS Websites and Grunge Style Templates Added! -&gt;&nbsp; <a href="http://bit.ly/aBkgOm" target="_blank">http://bit.ly/aBkgOm</a></span></p><p class="odd"> <span>20 Years of Adobe Photoshop (via @<a href="http://twitter.com/tweetmeme">tweetmeme</a>) -&gt; <a href="http://ow.ly/1nWaKR" target="_blank">http://ow.ly/1nWaKR</a></span></p><p class="even"> <span>45 High Quality And Inspiring 3D Desktop Wallpapers (via @<a href="http://twitter.com/smashingapps">smashingapps</a>) -&gt; <a href="http://su.pr/1Q8hty" target="_blank">http://su.pr/1Q8hty</a></span></p><p class="odd"> <span>30 Beautiful Artworks of Robots (@<a href="http://twitter.com/sixrevisions">sixrevisions</a>) -&gt; <a href="http://is.gd/7k5ww" target="_blank">http://is.gd/7k5ww</a></span></p></div></div></div></div></div></div><div class="botCrv"></div></div>
</div>-->
<!--Twitter ends here -->
	<div id="wraper">
    	<div id="headerblank">
       	  <div id="header">
           	<ul>
                	<li><a href="index.jsp" class="active">Inicio</a></li>
                    <li><a href="#">Acerca de</a></li>
                    <!--<li><a href="http://demo.templateworld.com/full-websites/049a/blog.html">Blog</a></li>-->
                    <li><a href="#">Servicios</a></li>
                    <!--<li><a href="http://demo.templateworld.com/full-websites/049a/portfolio.html">Portfalio</a></li>-->
                    <!--<li><a href="http://demo.templateworld.com/full-websites/049a/usefullinks.html">Useful&nbsp;Links</a></li>-->
                    <li><a href="contact.jsp">Contacto</a></li>
            </ul>
           	  <h1>
			  <!--<a href="index.htm"><img src="style/logo.png" alt="" height="47" width="325"></a>-->
			  <a href="index.jsp"><img src="images/logo.png" alt="" height="71" width="59"></a>
			  <font color="#FF0000" style="vertical-align:middle;">
			  <strong>Central De Alarmas</strong>
			  </font>			  
			  </h1>
           	   <h3>
			  
			  <font color="#FF0000" style="vertical-align:middle;">
			  <strong>AYUDA, SEGURIDAD Y TRANQUILIDAD</strong>
			  </font>			  
			  </h3>
 
          </div>
        	
        	
        	<div id="bodycontentblank">
            	<div id="bodycontent">
               	  <div id="bodyleftcontent">
                   	<div id="leftPan">
                        	<h2>Central de Alarmas en Google Maps</h2>
               		  <!--<img class="map" src="style/images/google-map.jpg" alt=" " height="380" width="600">-->
<iframe height="380" width="600" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com.mx/maps?hl=es&amp;ie=UTF8&amp;q=central+de+alarmas+sa+de+cv+chihuahua+google+maps&amp;fb=1&amp;gl=mx&amp;hq=central+de+alarmas+sa+de+cv&amp;hnear=0x86ea449d5d484033:0xb7f1a7a706dd1d7b,Chihuahua,+CHIH&amp;cid=0,0,14052777577666248369&amp;ll=28.657272,-106.11678&amp;spn=0.006295,0.006295&amp;t=m&amp;iwloc=A&amp;output=embed"></iframe><br /><small><a href="https://maps.google.com.mx/maps?hl=es&amp;ie=UTF8&amp;q=central+de+alarmas+sa+de+cv+chihuahua+google+maps&amp;fb=1&amp;gl=mx&amp;hq=central+de+alarmas+sa+de+cv&amp;hnear=0x86ea449d5d484033:0xb7f1a7a706dd1d7b,Chihuahua,+CHIH&amp;cid=0,0,14052777577666248369&amp;ll=28.657272,-106.11678&amp;spn=0.006295,0.006295&amp;t=m&amp;iwloc=A&amp;source=embed" style="color:#0000FF;text-align:left">Ver mapa más grande</a></small>
					  
					  </div>
                	</div>
                    
                    <div id="bodyRight">                       
                        
                        <div class="latestNews">
                        <h2>Contactanos</h2>
                            <p class="address">
                              <table>
                              <tr>
                                <td>Telefono:</td>
                                <td>(614) 414-5634</td>                                
                              </tr>
                              <tr>
                                <td></td>
                                <td>(614) 414-5687</td>                                
                              </tr>
                              <tr>
                                <td>E-Mail:</td>
                                <td>centraldealarmas@prodigy.net.mx,
                                    ayudamedica@cdealarmas.com.mx
                                </td>                                
                              </tr>
                              <tr>
                                <td>Pagina Web:</td>
                                <td>www.cdealarmas.com</td>                                
                              </tr>
                              </table>
       							
       							
    						</p>                                                    
                      </div><!--end of address-->
                        
                        <div class="latestNews">
                        	<h2>Sucursal</h2>
                        	<p class="address">
       							Central de Alarmas,<br>
       							Automaticas S.A de C.V,<br>
       							Chihuahua, Chih. Mx<br>
    						</p>
                        </div><!--end of testimonials-->
                    </div>
                    <!--end of right column -->
                </div>
            </div>

            <div id="footer-wrap">
                <div class="footer">
                	<div class="pages">
	                    <h4>Pages</h4>
             <!--           <ul>
                        	<li><a href="http://demo.templateworld.com/full-websites/049a/index.html">Home</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/about.html">About Us</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/blog.html">Blog</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/services.html">Services</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/portfolio.html">Portfolio</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/usefullinks.html">Useful Links</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/contact.html">Contact Us</a></li>
                        </ul>-->
                    </div>
                    <!--end of pages div-->
                    
                    <div class="services">
                    	<h4>Services</h4>
  <!--                      <ul>
                        	<li><a href="http://demo.templateworld.com/full-websites/049a/more.html">Services One</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/more.html">Services Two</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/more.html">Services Three</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/more.html">Services Four</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/more.html">Services Five</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/more.html">Services Six</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/more.html">Services Seven</a></li>
                        </ul>-->
                    </div>
                    <!--end of services-->
                    
                    <div class="various">
                    	<h4>Services</h4>
<!--                        <ul>
                        	<li><a href="http://demo.templateworld.com/full-websites/049a/more.html">Register</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/more.html">Log in</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/tos.html">Terms of Service</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/contact.html">Contact</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/sitemap.html">Site Map</a></li>
                        </ul>-->
                    </div>
                    <!--end of various-->
                    
                    <div class="additional">
                    	<h4>Additional</h4>
<!--                        <ul>
                        	<li><a href="http://demo.templateworld.com/full-websites/049a/more.html">Lorem ipsum</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/more.html">Vivamus sit mrus</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/more.html">Vestim eeros elit</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/more.html">Sit amet congue</a></li>
                            <li><a href="http://demo.templateworld.com/full-websites/049a/more.html">Suspe ndieoncus</a></li>
                        </ul>-->
                    </div>
                    <!--end of additional info-->
                    
                    <div class="socialbookmarking">
                    	<!--<h4>Keep in touch!</h4>
                        <p>We will be glad to assist you if you have any problem with out products.</p>-->
                        
                        <div class="bookmarkIcons">
<!--                   	    <img src="style/facebook_icon.gif" alt=" " height="32" width="32">
                        <img src="style/linken-icon.gif" alt=" " height="32" width="32">
                        <img src="style/in_icon.gif" alt=" " height="32" width="32">
                        <img src="style/rss_icon.gif" alt=" " height="32" width="32">
                        <img src="style/twitter_icon.gif" alt=" " height="32" width="32">-->
                        </div>
                        <p class="rights">Designed by: <strong><a href="http://www.bituos.com/" title="bituos Tools" target="_blank">Bituos Tools</a></strong></p>
                    </div>
                    <!--end of additional info-->

                </div>
            </div><!--end of footer wrapper-->
    </div>


<div style="display: none; position: fixed; opacity: 0.9; top: 40%; background: none repeat scroll 0% 0% rgb(221, 221, 221); left: 40%; width: 20%;" id="cookieInjectorDiv"><div align="center">Wireshark Cookie Dump:<br><input id="cookieInjectorCookie" type="text"><br><button onclick="cookieInjector.writeCookie();">OK</button><button onclick="cookieInjector.hide();">Cancel</button></div></div></body></html>