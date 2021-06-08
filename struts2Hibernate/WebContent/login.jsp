<?xml version="1.0" encoding="utf-8"?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

	<title>Login</title>
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


<%-- hasActionErrors() method is defined in ActionSupport --%>
<%-- <s:if test="hasActionErrors()">
    <div class="errorDiv">
        <s:actionerror/>
    </div>
</s:if>

<div id="result" class="result ui-widget-content ui-corner-all">Submit form bellow.</div>
    
 <ul id="formerrors" class="errorMessage"></ul>

<s:form action="login" method="post"  cssClass="yform">

 <fieldset>
  <legend>AJAX Form with Validation</legend> 
  
   <div class="type-text">
        <label for="echo">Company: <span id="logincompanyError"></span></label>
 	    <s:textfield id="logincompany" 	name="company" />
   </div>

   <div class="type-text">
        <label for="echo">Company: <span id="logincompanyError"></span></label>
 	    <s:textfield id="loginuser" 	name="username" />
   </div>
   
     <div class="type-text">
        <label for="echo">Company: <span id="logincompanyError"></span></label>
 	    <s:textfield id="loginpassword" 	name="password" />
   </div>
    <div class="type-button">

   
   	<s:submit label="login"/>
      </div>

   

</fieldset>

</s:form> --%>
 


    
<div id="col3">
<div id="col3_content" class="clearfix">   
  
    
    <ul id="formerrors" class="errorMessage"></ul>
    <s:form id="formValidateCustom" action="login" theme="simple"  cssClass="yform" cssStyle="width:50%;">
        <fieldset>
            <legend>AJAX Form with Validation</legend>
             <div class="type-text">
	            <label for="echo">Company: <s:fielderror fieldName="company" /></span></label>
			   	<s:textfield 
		     		id="logincompany" 
		     		name="company" 
		     	/>
	        </div>
	        <div class="type-text">
	            <label for="echo">User: <span id="loginuserError"><s:fielderror fieldName="username" /></span></label>
			   	<s:textfield 
		     		id="loginuser" 
		     		name="username" 
		     	/>
	        </div>
	        <div class="type-text">
	            <label for="echo">Password: <span id="loginpasswordError"><s:fielderror fieldName="password" /></span></label>
		     	<s:textfield 
		     		id="loginpassword" 
		     		name="password" 
		     	/>
	        </div>
	        <div class="type-button">

		    	
		    	
<s:submit label="login"/>
		    	
	        </div>
        </fieldset>
    </s:form>
    <img id="indicator" src="images/indicator.gif" alt="Loading..." style="display:none"/> 
</div>
</div>
</body>
</html>