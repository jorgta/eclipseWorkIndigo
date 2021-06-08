<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="/servicioCael/theme/Master.css" rel="stylesheet" type="text/css">

<HTML>
<HEAD>
<TITLE><bean:message key="label.contact.company.full.name" /></TITLE>

<link rel="stylesheet" type="text/css" href="/servicioCael/Menu/menu.css" />
<SCRIPT type="text/javascript" src="/servicioCael/Menu/menu.js"></SCRIPT>
<style type="text/css">



/*Example CSS for the two demo scrollers*/

#pscroller1{
width: 200px;
height: 100px;
border: 1px solid black;
padding: 5px;
background-color: lightyellow;
}

#pscroller2{
width: 350px;
height: 20px;
border: 1px solid black;
padding: 3px;
}

#pscroller2 a{
text-decoration: none;
}

.someclass{ //class to apply to your scroller(s) if desired
}

</style>

<SCRIPT type="text/javascript">

/*Example message arrays for the two demo scrollers*/

var pausecontent=new Array()
pausecontent[0]='<a href="contacto.jsp" target="blank">Contactenos!</a><br />Llamenos para solicitar presupuestos sin compromiso!'
pausecontent[1]='<a href="contacto.jsp">DSPM</a><br />Estamos registrados ante el Departamento de Seguridad Publica Municipal.'
pausecontent[2]='<a href="map.jsp">Ubicación</a><br />Visitanos en nuestras oficinas.'

var pausecontent2=new Array()
pausecontent2[0]='<a href="support.jsp" target="blank">Soporte Tecnico</a><br />Realice una solicitud en linea!'
pausecontent2[1]='<a href="telefonia.jsp">Telefonia ilimitada</a><br />Conozca la mejor opción de telefonia ilimitada al mejor precio.'
pausecontent2[2]='<a href="map.jsp">Ubicación</a><br />Visitanos en nuestras oficinas.'


</SCRIPT>

<SCRIPT type="text/javascript">

/***********************************************
* Pausing up-down scroller- © Dynamic Drive (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit http://www.dynamicdrive.com/ for this script and 100s more.
***********************************************/

function pausescroller(content, divId, divClass, delay){
this.content=content //message array content
this.tickerid=divId //ID of ticker div to display information
this.delay=delay //Delay between msg change, in miliseconds.
this.mouseoverBol=0 //Boolean to indicate whether mouse is currently over scroller (and pause it if it is)
this.hiddendivpointer=1 //index of message array for hidden div
document.write('<div id="'+divId+'" class="'+divClass+'" style="position: relative; overflow: hidden"><div class="innerDiv" style="position: absolute; width: 100%" id="'+divId+'1">'+content[0]+'</div><div class="innerDiv" style="position: absolute; width: 100%; visibility: hidden" id="'+divId+'2">'+content[1]+'</div></div>')
var scrollerinstance=this
if (window.addEventListener) //run onload in DOM2 browsers
window.addEventListener("load", function(){scrollerinstance.initialize()}, false)
else if (window.attachEvent) //run onload in IE5.5+
window.attachEvent("onload", function(){scrollerinstance.initialize()})
else if (document.getElementById) //if legacy DOM browsers, just start scroller after 0.5 sec
setTimeout(function(){scrollerinstance.initialize()}, 500)
}

// -------------------------------------------------------------------
// initialize()- Initialize scroller method.
// -Get div objects, set initial positions, start up down animation
// -------------------------------------------------------------------

pausescroller.prototype.initialize=function(){
this.tickerdiv=document.getElementById(this.tickerid)
this.visiblediv=document.getElementById(this.tickerid+"1")
this.hiddendiv=document.getElementById(this.tickerid+"2")
this.visibledivtop=parseInt(pausescroller.getCSSpadding(this.tickerdiv))
//set width of inner DIVs to outer DIV's width minus padding (padding assumed to be top padding x 2)
this.visiblediv.style.width=this.hiddendiv.style.width=this.tickerdiv.offsetWidth-(this.visibledivtop*2)+"px"
this.getinline(this.visiblediv, this.hiddendiv)
this.hiddendiv.style.visibility="visible"
var scrollerinstance=this
document.getElementById(this.tickerid).onmouseover=function(){scrollerinstance.mouseoverBol=1}
document.getElementById(this.tickerid).onmouseout=function(){scrollerinstance.mouseoverBol=0}
if (window.attachEvent) //Clean up loose references in IE
window.attachEvent("onunload", function(){scrollerinstance.tickerdiv.onmouseover=scrollerinstance.tickerdiv.onmouseout=null})
setTimeout(function(){scrollerinstance.animateup()}, this.delay)
}


// -------------------------------------------------------------------
// animateup()- Move the two inner divs of the scroller up and in sync
// -------------------------------------------------------------------

pausescroller.prototype.animateup=function(){
var scrollerinstance=this
if (parseInt(this.hiddendiv.style.top)>(this.visibledivtop+5)){
this.visiblediv.style.top=parseInt(this.visiblediv.style.top)-5+"px"
this.hiddendiv.style.top=parseInt(this.hiddendiv.style.top)-5+"px"
setTimeout(function(){scrollerinstance.animateup()}, 50)
}
else{
this.getinline(this.hiddendiv, this.visiblediv)
this.swapdivs()
setTimeout(function(){scrollerinstance.setmessage()}, this.delay)
}
}

// -------------------------------------------------------------------
// swapdivs()- Swap between which is the visible and which is the hidden div
// -------------------------------------------------------------------

pausescroller.prototype.swapdivs=function(){
var tempcontainer=this.visiblediv
this.visiblediv=this.hiddendiv
this.hiddendiv=tempcontainer
}

pausescroller.prototype.getinline=function(div1, div2){
div1.style.top=this.visibledivtop+"px"
div2.style.top=Math.max(div1.parentNode.offsetHeight, div1.offsetHeight)+"px"
}

// -------------------------------------------------------------------
// setmessage()- Populate the hidden div with the next message before it's visible
// -------------------------------------------------------------------

pausescroller.prototype.setmessage=function(){
var scrollerinstance=this
if (this.mouseoverBol==1) //if mouse is currently over scoller, do nothing (pause it)
setTimeout(function(){scrollerinstance.setmessage()}, 100)
else{
var i=this.hiddendivpointer
var ceiling=this.content.length
this.hiddendivpointer=(i+1>ceiling-1)? 0 : i+1
this.hiddendiv.innerHTML=this.content[this.hiddendivpointer]
this.animateup()
}
}

pausescroller.getCSSpadding=function(tickerobj){ //get CSS padding value, if any
if (tickerobj.currentStyle)
return tickerobj.currentStyle["paddingTop"]
else if (window.getComputedStyle) //if DOM2
return window.getComputedStyle(tickerobj, "").getPropertyValue("padding-top")
else
return 0
}

</SCRIPT>
<style>
<!--
.intro{
position:absolute;
left:0;
top:0;
layer-background-color:#8AC9F7;
background-color:#8AC9F7;
border:0.1px solid red;
z-index:9;
}
-->
</style>

<style>
<!--
.styling{
background-color:#FFFFFF;
color:#000000;
font: bold 15px MS Sans Serif;
padding: 3px;
}
-->
</style>



</HEAD>

<BODY background=#12345>

<div id="i1" class="intro"></div><div id="i2" class="intro"></div>
<SCRIPT language="JavaScript1.2">

/*
Left-Right Curtain Script- © Dynamic Drive (www.dynamicdrive.com)
For full source code, 100's more free DHTML scripts, and TOS,
visit http://dynamicdrive.com
*/

/*
var ns4=document.layers?1:0
var ie4=document.all?1:0
var ns6=document.getElementById&&!document.all?1:0

var speed=20
var temp=new Array()
var temp2=new Array()
if (ns4){
for (i=1;i<=2;i++){
temp[i]=eval("document.i"+i+".clip")
temp2[i]=eval("document.i"+i)
temp[i].width=window.innerWidth/2
temp[i].height=window.innerHeight
temp2[i].left=(i-1)*temp[i].width
}
}
else if (ie4||ns6){
var clipright=ns6?window.innerWidth/2*0.98:document.body.clientWidth/2,clipleft=0
for (i=1;i<=2;i++){
temp[i]=ns6?document.getElementById("i"+i).style:eval("document.all.i"+i+".style")
temp[i].width=ns6?window.innerWidth/2*0.98:document.body.clientWidth/2
temp[i].height=ns6?window.innerHeight-1: document.body.offsetHeight
temp[i].left=(i-1)*parseInt(temp[i].width)
}
}


function openit(){
window.scrollTo(0,0)
if (ns4){
temp[1].right-=speed
temp[2].left+=speed
if (temp[2].left>window.innerWidth/2)
clearInterval(stopit)
}
else if (ie4||ns6){
clipright-=speed
temp[1].clip="rect(0 "+clipright+" auto 0)"
clipleft+=speed
temp[2].clip="rect(0 auto auto "+clipleft+")"
if (clipright<=0){
clearInterval(stopit)
if (ns6){
temp[1].display="none"
temp[2].display="none"
}
}
}
}

function gogo(){
stopit=setInterval("openit()",100)
}
gogo()

*/

</SCRIPT>

<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

// Browser Slide-Show script.
// With image cross fade effect for those browsers that support it.
var slideCache = new Array();
function RunSlideShow(pictureName,imageFiles,displaySecs)
	{
		var imageSeparator = imageFiles.indexOf(";");
		var nextImage = imageFiles.substring(0,imageSeparator);
		if (document.all)
			{
			document.getElementById(pictureName).style.filter="blendTrans(duration=2)";
			document.getElementById(pictureName).filters.blendTrans.Apply();
			}
		document.getElementById(pictureName).src = nextImage;
		if (document.all)
			{
			document.getElementById(pictureName).filters.blendTrans.Play();
			} 
		var futureImages= imageFiles.substring(imageSeparator+1,imageFiles.length) + ';' + nextImage;
		setTimeout("RunSlideShow('"+pictureName+"','"+futureImages+"',"+displaySecs+")",
		displaySecs*600);
		// Cache the next image to improve performance.
		imageSeparator = futureImages.indexOf(";");
		nextImage = futureImages.substring(0,imageSeparator);
		if (slideCache[nextImage] == null) 
			{
				slideCache[nextImage] = new Image;
				slideCache[nextImage].src = nextImage;
			}
	}
	
</SCRIPT>



<TABLE width="800" align="center">

	<TR>
		
		<TD>
		  <TABLE>
		  	<TR>
		  	   <TD width="100">
					<div align="CENTER">
					 <!--   <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="189" height="189">
					    <param name="movie" value="flash/sicureza2.swf" />
					    <param name="quality" value="high" />
					    <embed src="flash/sicureza2.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="189" height="189"></embed>
					  </object>-->
					  
					</div>
					<IMG width="156" height="156" border="1" src="/servicioCael/img/logo.jpg">
		  	   </TD>
		  	   <TD>
			      <IMG width="700" height="156" border="1" src="/servicioCael/img/banners/tropa.jpg" id="banner">
				<SCRIPT language="JavaScript">
				RunSlideShow("banner","/servicioCael/img/banners/tropa.jpg;"+
									  "/servicioCael/img/banners/accesories.jpg;"+
									  "/servicioCael/img/banners/shirt_logo.jpg;"+
									  "/servicioCael/img/banners/dspm.jpg;"+
									  "/servicioCael/img/banners/banner_carro.jpg;"+
									  "/servicioCael/img/banners/basc.jpg", 10);
				</SCRIPT>
		  	   </TD>
		  	<TR>
		  </TABLE>
		</TD>
	</TR>
	<TR>
		
		<TD>
			
			<div align="center" class="horizontalcssmenu">
			
			<ul id="cssmenu1">
				<li style="border-left: 1px solid #999999">
					<A href="index.jsp"><bean:message key="label.start" /></A>					
				</li>

				
				<li style="border-left: 1px solid #999999">
					<A href="bussinessInfo.jsp"><bean:message key="label.company" /></A>					
				</li>

			
				<li style="border-left: 1px solid #999999">
					<A href="/servicioCael/clothes.jsp"> <bean:message key="label.clothes" /> </A>					
			    </li>

				
				<li style="border-left: 1px solid #999999">
					<A href="/servicioCael/certificates.jsp"> <bean:message key="label.certificates" /> </A>					
				</li>

				<li style="border-left: 1px solid #999999">
					<A href="/servicioCael/mail"> <bean:message key="label.intranet" /> </A>					
				</li>

				<li style="border-left: 1px solid #999999">
					<A href="/servicioCael/contacto.jsp"> <bean:message key="label.contact" /> </A>					
				</li>
			</ul>

			<br style="clear: left" />
			</div>

		</TD>		
	</TR>



