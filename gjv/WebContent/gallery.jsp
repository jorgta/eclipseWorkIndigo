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
<LINK rel=stylesheet type=text/css href="/gjv/theme/gallery.css" media=screen>

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
<BODY onload="">
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
    <LI class="left addresss">GJV <br> </LI>

<li class="lang" id="current_language">Web e-mail: <span><A href="http://email.secureserver.net">aqui</A></span></li>
</UL>



</DIV>


  <div id="showcase" style=" padding-top: 0px; width: 100% ">
    <ul class="templategallery" style="padding-left: 160px; padding-top: 100px ">
  		<li class="norm"><span class="name">Local</span><span class="screen"><a href="fotos.jsp?image=portadaengal.jpg"><img src="images/portada.jpg" alt="Beez Free CSS Template" title="Local" width="180" height="134"></a></span><span class="posted"><strong>Publicada:</strong> 22/03/2010</span></li>
  		<li class="norm"><span class="name">Portada</span><span class="screen"><a href="fotos.jsp?image=TIENDAS J.V. Y HIELOengal.png"><img src="images/TIENDAS J.V. Y HIELOengal.png" alt="Beez Free CSS Template" title="Local" width="180" height="134"></a></span><span class="posted"><strong>Publicada:</strong> 22/03/2010</span></li>
  		<li class="last"><span class="name"></span><span class="screen"><a href="fotos.jsp?image=gjvhielo1.jpg"><img src="images/gjvhielo1.jpg" alt="Bordered Free CSS Template" title="View the Bordered Template Details" width="180" height="134"></a></span><span class="posted"><strong>Publicada:</strong> 22/03/2010</span></li>

		<li class="norm"><span class="name">Local</span><span class="screen"><a href="fotos.jsp?image=IMG_0049.JPG"><img src="images/IMG_0049.JPG" alt="Beez Free CSS Template" title="Local" width="180" height="134"></a></span><span class="posted"><strong>Publicada:</strong> 30/03/2010</span></li>
  		<li class="norm"><span class="name">Portada</span><span class="screen"><a href="fotos.jsp?image=IMG_0056.JPG"><img src="images/IMG_0056.JPG" alt="Beez Free CSS Template" title="Local" width="180" height="134"></a></span><span class="posted"><strong>Publicada:</strong> 30/03/2010</span></li>
  		<li class="last"><span class="name"></span><span class="screen"><a href="fotos.jsp?image=IMG_0060.JPG"><img src="images/IMG_0060.JPG" alt="Bordered Free CSS Template" title="View the Bordered Template Details" width="180" height="134"></a></span><span class="posted"><strong>Publicada:</strong> 30/03/2010</span></li>
        
        <li class="norm"><span class="name">Local</span><span class="screen"><a href="fotos.jsp?image=IMG_0062.JPG"><img src="images/IMG_0062.JPG" alt="Beez Free CSS Template" title="Local" width="180" height="134"></a></span><span class="posted"><strong>Publicada:</strong> 30/03/2010</span></li>
  		<li class="norm"><span class="name">Portada</span><span class="screen"><a href="fotos.jsp?image=DSC02490.JPG"><img src="images/DSC02490.JPG" alt="Beez Free CSS Template" title="Local" width="180" height="134"></a></span><span class="posted"><strong>Publicada:</strong> 30/03/2010</span></li>
  		<li class="norm"><span class="name">Portada</span><span class="screen"><a href="fotos.jsp?image=oldFOTOSJV2.png"><img src="images/oldFOTOSJV2.png" alt="Beez Free CSS Template" title="Local" width="180" height="134"></a></span><span class="posted"><strong>Publicada:</strong> 29/04/2010</span></li>
  	
        


	</ul>
    <div class="clear"></div>
  </div>
</body>
</html>