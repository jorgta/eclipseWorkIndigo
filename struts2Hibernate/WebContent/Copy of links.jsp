<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>

	<title>Links</title>
	
	
	<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="treeMenuCode.js"></script>
</head>
<body>


<s:if test="#session.beanUser != null"> 
	<s:if test="#session.processList != null"> 
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
	var treeMenuRoot       = "struts2Hibernate";     // Text for the menu root.
	var treeMenuFolders    = 0;               // Sets display of '+' and '-' icons.
	var treeMenuAltText    = true;            // Use menu item text for icon image ALT text.
	
	var i = 0;
    </script>	
	 <s:iterator value="#session.processTypeList" var="processType">
	  <script language="JavaScript">
	    treeMenu.addItem(new TreeMenuItem('<s:property value="description"/>'));

		var childTree = new TreeMenu();	
		
	  </script>	
		
 		<s:iterator value="#session.processList" var="processUsers">
		  
		  <s:if test="#processType.id == id_process.id_type_process.id">
		    
		      
		     <script language="JavaScript">
		        childTree.addItem(new TreeMenuItem('<s:property value="id_process.description"/>','/struts2Hibernate/pre.jsp?&process=<s:property value="id_process.action"/>&action=<s:property value="id_process.action"/>&target=<s:property value="id_process.target"/>',"mainFrame")); 
		  		treeMenu.items[i].makeSubmenu(childTree);
		  		
		  	 </script>	
		
		  </s:if>
		       
		</s:iterator> 
		<script language="JavaScript">
		i=i+1;
		</script>	
	 </s:iterator>	
	 
	 	<frameset COLS="20%,80%" frameborder="yes" framespacing=1>
	
			<frameset ROWS="80%,20%" frameborder="no" framespacing=1>
		  		<frame name="menuFrame" src="treeMenu.jsp" marginheight=10 marginwidth=5 scrolling=auto>
		  		<frame name="companyLogo" src="companyLogo.jsp" marginheight=20 marginwidth=20 scrolling=auto>
			</frameset>
			
	  		<frame name="mainFrame" src="main.jsp" marginheight=20 marginwidth=20 scrolling=auto>
	    </frameset>
	 
	 
	 
	 
	 
	</s:if>	
</s:if>
<s:else>
  <s:action name="initial" executeResult="true"></s:action>
  
</s:else>

</body>
</html>


