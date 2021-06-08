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
		<IMG border="0" src="/servicioCael/img/certificates/basc.jpg" width="480" height="480" id="certificates">
		<SCRIPT language="JavaScript">
				RunSlideShow("certificates","/servicioCael/img/certificates/basc.jpg;"+
				"/servicioCael/img/certificates/dspm1.jpg;"+
				"/servicioCael/img/certificates/dspm.jpg",5);
		</SCRIPT>
	</TD>
</TR>

<TR><TD><br /></TD></TR>
<TR><TD><br /></TD></TR>
<TR>
	<TD>
		<a href='<bean:message key="label.contact.link" />'> <bean:message key="label.contact.link" />  </a>
	</TD>
</TR>

<jsp:include page="bottom.jsp" />
