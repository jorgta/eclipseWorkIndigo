<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<HEAD>

<%@ page session="true" import="java.util.*,com.bituos.truckAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>

<%@ page 
language="java"

contentType="text/html; charset=ISO-8859-1"

pageEncoding="ISO-8859-1"

%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">

<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE><bean:message key="label.user" />: <bean:write name="beanUser" property="name" /></TITLE>
<script language="JavaScript" src="treeMenuCode.js"></script>

</HEAD>


<logic:present name="beanUser" scope="session" >

<logic:present name="processList" scope="session">
<script language="JavaScript">
var treeMenu           = new TreeMenu();  // This is the main menu.
var treeMenuName       = "myMenu_1.0";    // Make this unique for each tree menu.
var treeMenuDays       = 7;               // Number of days to keep the cookie.
var treeMenuFrame      = "menuFrame";     // Name of the menu frame.
var treeMenuImgDir     = "Menu_graphics/"      // Path to graphics directory.
var treeMenuBackground = "";              // Background image for menu frame.   
var treeMenuBgColor    = "#ffffff";       // Color for menu frame background.   
var treeMenuFgColor    = "#000000";       // Color for menu item text.
var treeMenuHiBg       = "#008080";       // Color for selected item background.
var treeMenuHiFg       = "#ffffff";       // Color for selected item text.
var treeMenuFont       = 
      "MS Sans Serif,Arial,Helvetica";    // Text font face.
var treeMenuFontSize   = 1;               // Text font size.
var treeMenuRoot       = "event Administrator";     // Text for the menu root.
var treeMenuFolders    = 0;               // Sets display of '+' and '-' icons.
var treeMenuAltText    = true;            // Use menu item text for icon image ALT text.

var i = 0;



<logic:iterate id="processType" indexId="indexprocessType" name="processTypeList" scope="session" >

  <bean:define id="pt" name="processType"></bean:define>
 
 treeMenu.addItem(new TreeMenuItem('<bean:write name="processType" property="description" />'));

	var childTree = new TreeMenu();

	<logic:iterate id="process" indexId="indexprocess" name="processList" scope="session" >
		<bean:define id="p" name="process" property="id_process"></bean:define>
        
	<%					          
         if ( ((BeanTypeProcess) pt).getId() == ((BeanProcess) p).getId_type_process().getId() )
         {
              String s = ((BeanProcess) p).getUrl();
    %> 
		  		childTree.addItem(new TreeMenuItem("<bean:write name="process" property="id_process.description" />","/truckAdmin/pre.jsp?&process='<bean:write name="process" property="id_process.action" />'&action='<bean:write name="process" property="id_process.url" />'&target='<bean:write name="process" property="id_process.target" />'","mainFrame")); //"  + Math.random() + "
		  		treeMenu.items[i].makeSubmenu(childTree);
			 
      <%  
		 }
      %>  

	</logic:iterate>
		i=i+1;

</logic:iterate>

// -->
</script>


	<frameset COLS="20%,80%" frameborder="yes" framespacing=1>
	
		<frameset ROWS="80%,20%" frameborder="no" framespacing=1>
	  		<frame name="menuFrame" src="treeMenu.jsp" marginheight=10 marginwidth=5 scrolling=auto>
	  		<frame name="companyLogo" src="companyLogo.jsp" marginheight=20 marginwidth=20 scrolling=auto>
		</frameset>
		
  		<frame name="mainFrame" src="main.jsp" marginheight=20 marginwidth=20 scrolling=auto>
	</frameset>


</logic:present>
</logic:present>
<logic:notPresent name="beanUser" scope="session" >
	<logic:redirect href="login.jsp"></logic:redirect>
</logic:notPresent>


</html:html>
