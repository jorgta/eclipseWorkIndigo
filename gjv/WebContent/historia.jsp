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
<li class="lang" id="current_language">Login: <span><A href="login.jsp" target="blank">aqui</A></span></li>
<li class="lang" id="current_language">Web e-mail: <span><A href="http://email.secureserver.net" target="blank">aqui</A></span></li>
</UL>

</DIV>



<DIV  id="flash_head"  style="HEIGHT: 311px;">
       <img id="slideShow" src="images/oldFOTOSJVparHistoria.png" width="920" height="311">

	   <script language="JavaScript">
				RunSlideShow("slideShow","images/oldFOTOSJVparHistoria.png;" +
										 "images/historia1.jpg;" +
										 "images/historia2.jpg;"+
										 "images/historia3.jpg",5);
		</script>
	    
</DIV>


<div class="column" style=" padding-top: 0px; width: 100% " >
<h3><strong>GRUPO J.V. DE PARRAL, S.A. DE C.V.</strong></h3>
<p>En 1954 <br>
En una modesta distribuidora de cervezas ubicada en C. 20 de Noviembre No. 9 de la ciudad de Hidalgo del Parral, Chihuahua se colocaron unos casillero de madera en una superficie de 4 mts2, los clientes de la distribuidora de cerveza fueron los primeros en hacer compras de Vinos y Licores, posteriormente algunos otros particulares.
<br>
<br>
En 1960 <br>
Se adquirió un local en calle 20 de Noviembre No. 12 y se acondicionó un espacio para, la venta al público por menudeo y en su interior se instalaron las oficinas de la distribuidora.
<br>
<br>

En 1965<br>
Se instala la primer sucursal en la Avenida Maclovio Herrera colonia centro en la ciudad de Hidalgo del Parral, Chihuahua.
<br>
<br>

En 1970 <br>
Se organizó un almacén para la venta de mayoreo en la cruzada Pedro Alvarado. 
<br>
<br>
	
En 1975 <br>
Se intensifica la instalación de sucursales en Hidalgo del Parral Chih. una en Cd. Jiménez Chih., Cd. Camargo, Chih., Chihuahua, Chih., Cd. Juárez Chih., Comarca Lagunera, Guadalajara Jalisco y se establece la marca comercial "J.V".
<br>
<br>			
		
En 1982 <br>
Se traspasan las sucursales foráneas a los Sres. Eduardo Arturo Villalobos Chávez, en la Comarca lagunera, Carlos Enrique Villalobos Chávez y Carlos Chávez Martínez en ciudad Chihuahua, Chih., Antonio Villalobos Chávez en Guadalajara Jal., Alonso Villalobos Chávez en ciudad Juárez, Chih., y por útimo a la Sra. Elia Elena Villalobos Chávez en ciudad Camargo, Chihuahua.
El Sr. Juan Eduardo Villalobos Armendáriz y la Sra. Elia Elena Chávez Martínez quedan como socios con 10 puntos de venta que existían hasta ese año en los municipios de Hidalgo del Parral y una sucursal en el municipio de Jiménez, Chihuahua.

<br>
<br>

En 1986 <br>
El Sr. Juan Eduardo Villalobos Armendáriz, liquidan la sociedad con la Sra. Elia Elena Chávez Martínez y se integra la Sra. Norma Lucero Sotelo de Villalobos en sociedad con el Sr. Juan Eduardo Villalobos Armendáriz, adquiriendo a la Sra. Elia Elena Chávez Martínez 5 puntos de venta lo que a ella correspondía en ese momento, los cuales se agregaron a esta sociedad, y así se consolida Grupo J.V.  en los municipios de Hidalgo del Parral, Santa Bárbara, Chihuahua y ciudad Jiménez, Chihuahua.
<br>
<br>

En 1987  <br>
Se inició la adquisición de terrenos y se construyó un nuevo concepto, modificando paulatinamente su infraestructura e imagen institucional de expendios al menudeo a tiendas de conveniencia J.V.
<br>
<br>

En 1989  <br>
Se adquirió maquinaría para la fabricación de Hielo triturado para enfriar y así obsequiarlo a nuestros clientes en Tiendas J.V. 
Al mismo tiempo se inició la fabricación de Hielo cilíndrico 100% purificado, para vender al público en general.
Durante esta misma década se instaló Agua Purificada J.V. en calle Purísima No. 1-a, en la ciudad de Hidalgo del parral, Chihuahua. 

<br>
<br>

En 1992  <br>
J.V. Paso a ser una sociedad denominada Grupo J.V. de Parral SA de CV.   
<br>
<br>

En 2002  <br>
Inicia operaciones la primera estación de servicio denominada: 
Gasolinera J.V. SA de CV. en Carretera Corta a Chihuahua km. 5 mas 250 en la ciudad de Hidalgo del Parral, Chihuahua por los mismos accionistas, posteriormente durante estos años se han instalado nuevas sucursales bajo el concepto de Tiendas J.V. y Gasolineras J.V. en los municipios de Hidalgo del Parral, Jiménez y Santa Bárbara, Chihuahua.

<br>
<br>


En 2010  <br>
Agosto 26 fallecé a la edad de 80 años en la ciudad de El Paso Tx, su fundador Sr. Juan Eduardo Villalobos Armendariz. 


<br>
<br>
			
En 2011  <br>
Operadora JVJR SA de CV, inicia en actividades agrícolas con producción de Nuez Pecanera.

<br>
<br>	

					
<hr>
</div>
</BODY>
</HTML>