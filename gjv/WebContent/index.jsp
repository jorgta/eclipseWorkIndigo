<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<HEAD><TITLE>Grupo GJV</TITLE>


<META name=description content="">
<META name=keywords content="">
<LINK rel=stylesheet type=text/css href="GJV/nonie.css" media=screen>

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

<META name=GENERATOR content="MSHTML 8.00.6001.18876"></HEAD>
<BODY onLoad="">
<DIV id="wrap">
<DIV id="container">

<DIV id="branding">

<h1 id="logo"><img src="images/logo.png"> </h1>

<UL id="mainnav" class="clear">
  <LI class="right" onMouseOver="mouseOver(this)" onMouseOut="mouseOut(this)"><A href="contact.jsp">contactos</A></LI>
  <LI class="right" onMouseOver="mouseOver(this)" onMouseOut="mouseOut(this)"><A href="controlGasFE.jsp">Facturacion</A></LI>
  <LI class="right" onMouseOver="mouseOver(this)" onMouseOut="mouseOut(this)"><A href="#"></A></LI>
  <LI id="home" class="selected" onMouseOver="mouseOver(this)" onMouseOut="mouseOut(this)"><A href="bottom.jsp">home</A></LI>
  <LI class="left" onMouseOver="mouseOver(this)" onMouseOut="mouseOut(this)"><A id="aHome" href="mapa.jsp">cobertura</A></LI>
  <LI class="left" onMouseOver="mouseOver(this)" onMouseOut="mouseOut(this)"><A href="gallery.jsp">galeria</A></LI>
  </UL>
  
  
<UL id=info class=clear>
  <LI class="left addresss">GJV <br> </LI>

  <li class="lang" id="current_language">Web e-mail: <span><A href="http://email.secureserver.net" target="blank">aqui</A></span></li>
  <li class="lang" id="current_language"> </li>
</UL>

</DIV>



<DIV  id="flash_head" style="height: 520px;">
       <img id="slideShow" src="images/1.jpg" width="920" height="520">
	   
	   <script language="JavaScript">
				RunSlideShow("slideShow","images/1.jpg;" +
										 "images/2.jpg;"+
										 "images/3.jpg;"+
										 "images/4.jpg",5);
		</script>
	    
</DIV>




