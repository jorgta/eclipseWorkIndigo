<?xml version="1.0" encoding="utf-8"?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

	<title>grid</title>
	<link href="loginTheme/login-box.css" rel="stylesheet" type="text/css" />
	
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="Content-Style-Type" content="text/css" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="keywords" content="struts2, jquery, jquery-ui, plugin, showcase, jqgrid" />
	<meta http-equiv="description" content="A Showcase for the Struts2 jQuery Plugin" />
	<link href="styles/layout.css" rel="stylesheet" type="text/css" />
	<!--[if lte IE 7]>
	<link href="styles/patch_layout.css" rel="stylesheet" type="text/css" />
	<![endif]-->

	<!-- This files are needed for AJAX Validation of XHTML Forms -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/struts/utils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/struts/xhtml/validation.js"></script>

	  <s:if test="%{theme == 'showcase' || theme == null}">
	      <sj:head debug="true" compressed="false" jquerytheme="showcase" customBasepath="themes" loadFromGoogle="%{google}" ajaxhistory="%{ajaxhistory}" defaultIndicator="myDefaultIndicator" defaultLoadingText="Please wait ..."/>
	  </s:if>
	  <s:else>
	      <sj:head debug="true" compressed="false" jquerytheme="%{theme}" loadFromGoogle="true"  ajaxhistory="%{ajaxhistory}" defaultIndicator="myDefaultIndicator" defaultLoadingText="Please wait ..."/>
	  </s:else>

	<!-- This file includes necessary functions/topics for validation and all topic examples -->
	<script type="text/javascript" src="js/showcase.js"></script>
	<!-- Extend the Struts2 jQuery Plugin with an richtext editor -->
	<script type="text/javascript" src="js/extendplugin.js"></script>
	
	
	
	
	
	
	
	
	
	
	<style type="text/css">
.errorDiv{
    background-color:gray;
    border:1px solid black;
    width:400px;
    margin-bottom:8px;
}

.errorMessage
{
   color: red;
}


</style>


    
</head>
<body>

   <s:url id="remoteurl" action="userGrid"/> 
   
    <sjg:grid 
    	id="gridtable" 
    	caption="Usuarios" 
    	dataType="json" 
    	href="%{remoteurl}" 
    	pager="true" 
    	gridModel="gridModel"
    	rowList="10,15,20"
    	rowNum="15"
    	rownumbers="true"
    >
    	
    	<sjg:gridColumn name="id" index="id" title="ID" formatter="integer" sortable="false"/>
    	<sjg:gridColumn name="name" index="name" title="Name" sortable="true"/>
    	<sjg:gridColumn name="password" index="password" title="Password" sortable="false"/>
    	<sjg:gridColumn name="active" index="active" title="Activo" sortable="false"/>
    	<sjg:gridColumn name="address" index="address" title="Direccion" sortable="false"/>
    	<sjg:gridColumn name="id_company.full_name" index="id_company.full_name" title="Company"  sortable="false"/>
    </sjg:grid>




</body>
</html>