<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Central de Alarmas</title>
<link href="style/style.css" rel="stylesheet" type="text/css">
<link href="style/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="style/contentslider.css">
<script type="text/javascript" src="style/contentslider.js"></script>
<script type="text/javascript" src="style/jquery-latest.js"></script><!--Common jQuery plugin here -->

<!--Contact starts here -->
<script type="text/javascript" src="style/jquery_004.js"></script>
<script type="text/javascript" src="style/jquery_003.js"></script>
<link href="style/jquery.css" type="text/css" rel="stylesheet">
<!--Contact ends here -->

<!--Twitter start here -->
<script type="text/javascript" src="style/jquery.js"></script>
<script type="text/javascript" src="style/jquery_005.js"></script>
<script type="text/javascript" src="style/jquery_002.js"></script>
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

<body id="TotalBodyId" style="margin-top: 0px; background-position: 0pt 0pt;">
<!--Inline css is needed for the top banner/ad, top-margin should be same as the ad-block height-->


<!--HeaderAd starts here -->
<div id="headerContent" style="width: 100%; height: 60px; margin-top: -60px;">
<div id="headerBt" style="width: 100px; float: left; margin-top: -55px;"><img src="style/btn-close2.gif" alt="Close" class="close-w" border="0"></div>
<div class="mastHead">
<img src="style/no-img.gif" alt="">
&lt;- Your advertisement / notice / announcement can go here -&gt; &lt;- Have a Question regarding this template? <a href="#"> Contact Us</a>. -&gt;<br>
 &lt;- All plugins, including the jQuery based 'quick contact' and 
'twitter feeds' are VERY easy to configure or remove from the template. 
-&gt;</div></div>
<!--HeaderAd ends here -->

<!--footerAd starts here -->
<!--<div id="footerContent" style="width:100%; height:40px;">
<div id="footerBt" style="width:100px;float:left; padding-top:3px;"><img src="style/btn-close.png" alt="Close" class="close-w" border="0"></div>
<div class="mastHead">
Did you ever notice: When you put the 2 words "The" and "IRS" together, it spells "Theirs?" Get more funny quotes at <a href="https://twitter.com/funnyoneliners" target="new">Funny One Liners</a>. 
</div>
</div>-->
<!--footerAd ends here -->

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
			  <strong>Slogan</strong>
			  </font>			  
			  </h3>
       	    <div id="slideshow">
            <div id="slider1" class="sliderwrapper">
     <div style="display: none; z-index: 238; opacity: 1; visibility: visible;" class="contentdiv">
       <div class="sliderleftcontent">
                    	<h2>Bienvenido a nuestro sitio web.</h2>
                    	<p>Algun Texto aqu√≠ Algun Texto aqu√≠  Algun Texto aqu√≠    Algun Texto aqu√≠ Algun Texto aqu√≠ Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui</p>
                        <a href="#">Leer Mas</a>
            </div>
                	<img src="style/slideshowimg.png" alt="" height="242" width="365"></div>
                    <div style="display: none; z-index: 239; opacity: 1; visibility: visible;" class="contentdiv">
       <div class="sliderleftcontent">
                    	<h2>Central de alarmas</h2>
                    	<p>Le ofrece la soluciÛn para solicitar ayuda, simplemente con  presionar un botÛn y podr· comunicar su emergencia a nuestros             operadores, quienes har·n los     enlaces con las personas            allegadas y a los servicios de  emergencia que usted requiera.</p>
                        <a href="#">Leer Mas</a>
                    </div>
                	<img src="images/slidelogo2.png" alt="" height="242" width="365"></div>
                    <div style="display: none; z-index: 240; opacity: 1; visibility: visible;" class="contentdiv">
       <div class="sliderleftcontent">
                    	<h2>Trabaja con nosotros.</h2>
                    	<p>Algun Texto aqu√≠ Algun Texto aqu√≠  Algun Texto aqu√≠    Algun Texto aqu√≠ Algun Texto aqu√≠ Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui.</p>
                        <a href="#">Leer Mas</a>
                    </div>
                	<img src="style/slideshowimg.png" alt="" height="242" width="365"></div>
                    <div style="display: none; z-index: 241; opacity: 1; visibility: visible;" class="contentdiv">
       <div class="sliderleftcontent">
                    	<h2>Promociones.</h2>
                    	<p>>Algun Texto aqu√≠ Algun Texto aqu√≠  Algun Texto aqu√≠    Algun Texto aqu√≠ Algun Texto aqu√≠ Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui Algun Texto aqui.</p>
                        <a href="#">Leer Mas</a>
                    </div>
                	<img src="style/slideshowimg.png" alt="" height="242" width="365"></div>
                    <div style="display: block; z-index: 242; opacity: 1; visibility: visible;" class="contentdiv">
       <div class="sliderleftcontent">
                    	<h2>Algun slogan va aqui.</h2>
                    	<p>Sistema de respuesta de emegrencia personal.</p>
                        <a href="#">Leer Mas</a>
                    </div>
                	<img src="style/slideshowimg5.png" alt=""  height="238" width="127"></div>
                    <div id="paginate-slider1" class="pagination"><a rel="1" href="#1" class="toc">1</a> <a rel="2" href="#2" class="toc">2</a> <a rel="3" href="#3" class="toc">3</a> <a rel="4" href="#4" class="toc">4</a> <a rel="5" href="#5" class="toc selected">5</a> </div>
                    
              </div></div>
          </div>
          </div>
            </div>
        
        	
        	<div id="bodycontentblank">
            	<div id="bodycontent">
                	<div id="bodyleftcontent">
                    	<div id="welcomecontent">
                        	<h2>Central de Alarmas</h2>
                            	<p> Le ofrece la soluciÛn para solicitar ayuda, simplemente con  presionar un botÛn y podr· comunicar su emergencia a nuestros operadores, quienes har·n los enlaces con las personas allegadas y a los servicios de  emergencia que usted requiera.


								
								<!--Donec accumsan justo eget lectus 
dignissim elementum tristique turpis fringilla. Aenean placerat 
tincidunt placerat. Fusce mi sem, bibendum a tempor sodales, egestas 
vitae ipsum. Sed pulvinar mattis faucibus. Suspendisse sed sapien eros. 
Sed id quam massa. Integer vehicula pulvinar nisi at egestas. Proin est 
nunc, sollicitudin id eleifend in, fringilla at odio. Nulla blandit, 
augue eu aliquam mattis, nisi ante ultricies lectus, vitae feugiat ipsum
 nibh laoreet velit. Aliquam non nunc facilisis ligula vehicula tempus. 
Vestibulum nisi enim, pretium ut ullamcorper non, varius eu eros. 
Curabitur non enim id ligula lobortis eleifend dictum utn habitant morbi
 tristique senectus et netus et malesuada fames--> <a href="#">Leer mas</a></p>
                        </div>
                        <div id="featuredservice">
                       	  <h2>Servicios destacados</h2>
                            
                            <ul class="services">
                            	<li class="featuredservices">
                                <img src="images/serviciodestavados1.png" alt=" " height="80" width="116">
                                <h3>SISTEMA DE RESPUESTA A EMERGENCIA  PERSONAL (P.  E.  R.  S.)</h3>                                  
								<p>&nbsp;
								Personal Emergency Response Systems
		
								<a href="#" class="readMore">Leer mas</a></p>						
                                </li>                                
                                <li class="featuredservices">
                                <img src="style/rack.jpg" alt=" " height="80" width="116">
								<h3>Servicios 2</h3>                                  
								<p>DescripciÛn del servicio. &nbsp;<a href="#" class="readMore">Leer mas</a></p>						
                                </li>
                                <li class="featuredservices last" style="background:none;">
                                <img src="style/clock.jpg" alt=" " height="80" width="116">
								<h3>Servicios 3</h3>                                  
								<p>DescripciÛn del servicio. &nbsp;<a href="#" class="readMore">Leer mas</a></p>						
                                </li>
                            </ul>                        
                        </div>
                    </div>
                    
                    <div id="bodyRight">
                 <!--       <form action="#">
                          <input class="search" value="Search the site here..." type="text">
                          <input class="button" name="search" type="button">
                        </form>-->
                        
                        <div class="latestNews">
                        <h2><span>&nbsp;</span>AtenciÛn y seguridad para:</h2>
                            <ul class="news">
                                <li><a href="#"><span>*</span> Adultos mayores</a></li>
                                <li><a href="#"><span>*</span> Embarazos de alto riesgo</a></li>
                                <li><a href="#"><span>*</span> Discapacitados</a></li>
                                <li><a href="#"><span>*</span> Menores de edad</a></li>
                                <li><a href="#"><span>*</span> Convalecencia</a></li>
                                <li><a href="#"><span>*</span> Convalecencia</a></li>
                                <li><a href="#"><span>*</span> PrevenciÛn de riesgos</a></li>                               
                                <li class="nobg"><a href="#" class="viewMore">Ver Todos</a></li>
                            </ul>
                      </div><!--end of latest news-->
                        
                        <div class="testimonial">
                        	<h2><span>&nbsp;</span> Horario</h2>
                        	<blockquote><img src="style/quote.gif" alt=" " height="21" width="24">
							<!--Posuere cubilia Curaeis sagfa ettis risusat risus feu hregiat sed feugat nuctum. Etiam euismod dabus lorem, dui -->
							LAS 24 HORAS DEL DIA </br>
                            LOS 365 DIAS DEL A—O 

							</blockquote>           					
                           
                          
                          <hr style="width:245px; float:left; margin:0 10px; color:#fff;background-color:#fff; height:1px; border:none;">
           					<blockquote><img src="style/quote.gif" alt=" " height="21" width="24">
							<!--Sosuere cubilia Curaeis sagfa ettis risusat risus feu hregiat sed feugat nuctum tiam euismod dabus lorem-->
							Algun texto aqui
							</blockquote>
           					
                        </div><!--end of testimonials-->
                    </div>
                    <!--end of right column -->
                </div>
            </div>

            <div id="footer-wrap">
                <div class="footer">
                	<div class="pages">
	                    <h4>Pages</h4>
         <!--               <ul>
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
         <!--               <ul>
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
   <!--                     <ul>
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
   <!--                     <ul>
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
                        <p class="rights">¬© Your Copyright Info Here. Designed by: <strong><a href="http://www.bituos.com/" title="bituos Tools" target="_blank">Bituos Tools</a></strong></p>
                    </div>
                    <!--end of additional info-->

                </div>
            </div><!--end of footer wrapper-->
            <script type="text/javascript">

featuredcontentslider.init({
	id: "slider1",  //id of main slider DIV
	contentsource: ["inline", ""],  //Valid values: ["inline", ""] or ["ajax", "path_to_file"]
	toc: "#increment",  //Valid values: "#increment", "markup", ["label1", "label2", etc]
	nextprev: ["", ""],  //labels for "prev" and "next" links. Set to "" to hide.
	revealtype: "click", //Behavior of pagination links to reveal the slides: "click" or "mouseover"
	enablefade: [true, 0.2],  //[true/false, fadedegree]
	autorotate: [true, 3000],  //[true/false, pausetime]
	onChange: function(previndex, curindex){  //event handler fired whenever script changes slide
		//previndex holds index of last slide viewed b4 current (1=1st slide, 2nd=2nd etc)
		//curindex holds index of currently shown slide (1=1st slide, 2nd=2nd etc)
	}
})

</script>
    


<div style="display: none; position: fixed; opacity: 0.9; top: 40%; background: none repeat scroll 0% 0% rgb(221, 221, 221); left: 40%; width: 20%;" id="cookieInjectorDiv"><div align="center">Wireshark Cookie Dump:<br><input id="cookieInjectorCookie" type="text"><br><button onclick="cookieInjector.writeCookie();">OK</button><button onclick="cookieInjector.hide();">Cancel</button></div></div></body></html>