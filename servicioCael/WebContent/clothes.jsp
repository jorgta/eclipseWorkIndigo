<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<jsp:include page="top.jsp" />
<SCRIPT language="JavaScript">
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
var futureImages= imageFiles.substring(imageSeparator+1,imageFiles.length)
+ ';' + nextImage;
setTimeout("RunSlideShow('"+pictureName+"','"+futureImages+"',"+displaySecs+")",
displaySecs*600);
// Cache the next image to improve performance.
imageSeparator = futureImages.indexOf(";");
nextImage = futureImages.substring(0,imageSeparator);
if (slideCache[nextImage] == null) {
slideCache[nextImage] = new Image;
slideCache[nextImage].src = nextImage;
}
}
</SCRIPT>
<TR><TD><br /></TD></TR>
<TR><TD><h2></h2></TD></TR>


<TR><TD><br /></TD></TR>

<TR>
	<TD>
	    <P class="generalText">

	    El uniforme se caractiza por su camisa de color blanco con el nombre de la empresa, pantalon y corbata color verde oscuro, cada activo persona lleva siempre puesto un gafete que lo identifica como persona
	    perteneciente al equipo de seguridad
	    Adicional al uniforme del personal de seguridad de Servicio Cael, tenemos:
	    
<br>
<br>	    
&bull;Fornitura<br>
&bull;Gas lacrimógeno<br>
&bull;Tolete o tonfa<br>
&bull;Esposas de muñeca<br>
&bull;Equipos de radio comunicación<br>
&bull;Linterna<br>
&bull;Gafete con logotipo y datos de la empresa y datos de la persona de seguridad<br>
&bull;Celular<br>
&bull;Vehiculos con logotipo y datos de la empresa<br>
&bull;Cuaderno de bitácora<br>
&bull;Relojes Checadores<br>

<br><br>



	    
	    
		<IMG border="0" src="/sicservicioCaelg/img/clothes/DSC03677.jpg" width="480" height="480" id="clothes">
		<SCRIPT language="JavaScript">
				RunSlideShow("clothes", "/servicioCael/img/clothes/DSC03677.JPG;"+
										"/servicioCael/img/clothes/DSC03680.JPG;"+
										"/servicioCael/img/clothes/DSC03682.JPG;"+
										"/servicioCael/img/clothes/DSC03686.JPG;"+
										"/servicioCael/img/clothes/DSC03694.JPG;"+
										"/servicioCael/img/clothes/DSC03675.JPG;"+
										"/servicioCael/img/clothes/DSC03676.JPG;"+
										"/servicioCael/img/clothes/DSC03692.JPG;"+
										"/servicioCael/img/clothes/DSC03696.JPG", 5 );

		</SCRIPT>
	</TD>
</TR>

<TR><TD><br /></TD></TR>
<TR><TD><br /></TD></TR>
<TR>
	<TD>
		<a href="http://www.sicurezza.com"> <bean:message key="label.contact.link" />  </a>
	</TD>
</TR>

<jsp:include page="bottom.jsp" />
