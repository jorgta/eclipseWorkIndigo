<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>


<HEAD><TITLE>Grupo GJV</TITLE>
<meta http-equiv="Content-Type"  content="text/html; charset=utf-8">


<META name=description content="">
<META name=keywords content="">
<LINK rel=stylesheet type=text/css href="GJV/nonie.css" media=screen>
<LINK rel=stylesheet type=text/css href="/gjv/theme/gallery.css" media=screen>
<META name=GENERATOR content="MSHTML 8.00.6001.18876">
 


</HEAD>
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
	
</script>


		

		
		
		<SCRIPT type=text/javascript charset=utf-8>
        function mouseOver(tag)
		{
		  if(tag.id=='home')		  
		    tag.className='selected';
		  else
		  {
		   var tagHome= document.getElementById('home');
		    tagHome.className='';
		    if(tag.className=='left')
			  tag.className='left selected';
			else
		      tag.className='right selected';
		  }
		}
		
		   function mouseOut(tag)
		{
		  if(tag.id=='home')		  
		    tag.className='';
		  else		 
		  { 
		    if(tag.className=='left selected')
			  tag.className='left';
			else
		      tag.className='right';
			
		  }
		}
		
		
		</SCRIPT>


<BODY>
<DIV id="wrap">
<DIV id="container">

<DIV id="branding">

<h1 id="logo"><img src="images/logo.png">  </h1>

<UL id="mainnav" class="clear">
  <LI class="right" onMouseOver="mouseOver(this)" onMouseOut="mouseOut(this)"><A href="contact.jsp">contactos</A></LI>
  <LI class="right" onMouseOver="mouseOver(this)" onMouseOut="mouseOut(this)"><A href="controlGasFE.jsp">Facturacion</A></LI>
  <LI class="right" onMouseOver="mouseOver(this)" onMouseOut="mouseOut(this)"><A href="#"></A></LI>
  <LI id="home" class="selected" onMouseOver="mouseOver(this)" onMouseOut="mouseOut(this)"><A href="bottom.jsp">home</A></LI>
  <LI class="left" onMouseOver="mouseOver(this)" onMouseOut="mouseOut(this)"><A id="aHome" href="mapa.jsp">cobertura</A></LI>
  <LI class="left" onMouseOver="mouseOver(this)" onMouseOut="mouseOut(this)"><A href="gallery.jsp">galeria</A></LI>
  </UL>
  
  
<UL id=info class=clear>
  <LI class="left addresss">Libramiento perimetral sur <br> 
  							Luis Donaldo Colosio <br> 
  							Km 1 mas 720 Hidalgo del Parral
</LI>

<li class="lang" id="current_language">Web e-mail: <span><A href="http://email.secureserver.net">aqui</A></span></li>
</UL>

</DIV>

<script>
var mapframe;
function showMap()
{
 mapChih.style.display='block';
 mapframe= document.getElementById('mapFrame');
 mapframe.style.display='none';
 jimenez.style.display='none';

}

function closeMap()
{
  mapChih.style.display='none';
  mapframe.style.display='block';
}


function showHint(tag)
{ 


 if(tag=='jimenez')
 {
    jimenez.style.display='block';
 }
 
  if(tag=='parral')
 {
    parral.style.display='block';
 }
 
  if(tag=='barbara')
 {
    barbara.style.display='block';
 }
 
   if(tag=='chih')
 {
    chih.style.display='block';
 }
 


}


function hiddeHint(tag)
{ 
 
 if(tag=='jimenez')
 {
    jimenez.style.display='none';
 }
 
  if(tag=='parral')
 {
    parral.style.display='none';
 }
 
  if(tag=='barbara')
 {
    barbara.style.display='none';
 }
 
 if(tag=='chih')
 {
    chih.style.display='none';
 }
 
 

}

</script>

<P style="position:absolute; float:left; left: 41px; top: 147px; width: 893px; height: 36px;"> <a  href="#" onmouseover="showMap()" style="position:absolute; left: 828px; top: 2px;"> </a></P>

<%--<iframe id="mapFrame" width="900" height="400" frameborder="0" style="position:absolute; padding: 10px; left: 28px; top: 175px;" scrolling="yes" marginheight="0" marginwidth="0" src="http://www.google.es/maps?f=q&amp;source=s_q&amp;hl=es&amp;geocode=&amp;q=Hidalgo%2Bdel%2BParral,%2BChihuahua%2Bmexico%2BLIBRAMIENTO+PERIMETRAL&amp;sll=26.92383,-105.678091&amp;sspn=0.032524,0.054502&amp;ie=UTF8&amp;hq=&amp;hnear=Libramiento+Perimetral,+Hidalgo+del+Parral,+Chihuahua,+M%C3%A9xico&amp;t=h&amp;ll=26.9192,-105.663972&amp;spn=0.015306,0.038624&amp;z=15&amp;output=embed"></iframe>--%>

<!--<iframe id="mapFrame" 
" frameborder="0" style="background-color:#993300; position:absolute; 
						 padding: 10px; left: 242px; top: 189px; width: 452px; height: 533px;" scrolling="no" marginheight="0" marginwidth="0" src="images/mapachihuahua.JPG">-->
						
						 
</iframe>

<div style="background-color:#993300; position:absolute; 
						 padding: 10px; left: 242px; top: 189px; width: 452px; height: 533px;">
<img src="images/mapachihuahua.JPG"/>
</div>
<br />






<div id="mapChih" style="display:none;                          
						 width:553px; 
						 height:696px; 
						 position:absolute; 
						 border:#000000 1px solid; 
						 left: 196px; top: 174px;
						 background:#993333;">
						 <img style="padding: 10px" 
						 src="images/chihuahua.JPG" width="534" height="663" /> 
						
						 <img  onmouseover="showHint('jimenez')" onmouseout="hiddeHint('jimenez')"
						 src="images/dot.JPG" style="float:right; position:absolute; 
						 left: 391px; top: 503px; height: 10px; width: 12px;" />
						
						 
						<div id="jimenez" style="position:absolute; left: 122px; top: 307px; display:none;
												 width: 90px; height: 20px; z-index: 10;">
												 
												 <img src="images/mapjimenez.gif" style="border:#000000 1px solid;"/>						</div>
						
						 <img  onmouseover="showHint('parral')" onmouseout="hiddeHint('parral')"
						 src="images/dot.JPG" style="float:right; position:absolute; 
						 left: 322px; top: 524px; height: 10px; width: 12px;" />
						
						 
						<div id="parral" style="position:absolute; left: 68px; top: 325px; display:none; z-index: 10; 
												 width: 90px; height: 20px;">
												 
												 <img src="images/mapparral.gif" style="border:#000000 1px solid;"/>					</div>
							
							<img  onmouseover="showHint('barbara')" onmouseout="hiddeHint('barbara')"
						 src="images/dot.JPG" style="float:right; position:absolute; 
						 left: 308px; top: 537px; height: 10px; width: 12px;" />					 
								
								<div id="barbara" style="position:absolute; left: 42px; top: 349px; display:none; 
												 width: 90px; height: 20px; z-index: 10;">
												 
												<img src="images/mapsantabarbara.gif" style="border:#000000 1px solid;"/></div>		
												 
												 
						<img  onmouseover="showHint('chih')" onmouseout="hiddeHint('chih')"
						 src="images/dot.JPG" style="float:right; position:absolute; 
						 left: 283px; top: 349px; height: 8px; width: 9px;" />					 
								
								<div id="chih" style="position:absolute; left: 18px; top: 41px; display:none; 
												 width: 129px; height: 22px;">
												 
												 <img src="images/mapchihuahua.gif" style="border:#000000 1px solid;"/>												 </div>			 
						 
						
						<input name="" type="button" style="position:absolute; left: 487px; top: 674px; color:#000000; text-decoration:none; height: 22px;" onclick="closeMap()" value="Cerrar"/>
						
</div>


</body>
</html>