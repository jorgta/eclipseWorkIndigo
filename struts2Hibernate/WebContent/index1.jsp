<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<script>


  function sendForm()
  {
     
	 document.forms[0].submit();
	 
	// varTD.className = "tagTd";
  }
</script>

<title>Login</title>

<link href="loginTheme/login-box.css" rel="stylesheet" type="text/css" />


</head>
<jsp:include page="noCache.jsp" />

<body>
<logic:present name="beanUser" scope="session">
   <logic:redirect href="links.jsp">  </logic:redirect>
</logic:present>
<html:form action="/login">

<div align="center" style="padding-top:10%">


<div id="login-box" >

<H2>Login</H2>
Lorem Ipsum is simply dummy text of the printing and typesetting industry.
<br />
<br />
<div id="login-box-name" style="margin-top:20px;"><bean:message key="label.company" /> </div>
<div id="login-box-field" style="margin-top:20px;">
<html:text property="company" styleClass="form-login" size="30" maxlength="2048"></html:text>
</div>
<div id="login-box-name"><bean:message key="label.user" /></div><div id="login-box-field">
<html:text property="username" styleClass="form-login" size="30" maxlength="2048"></html:text>
</div>

<div id="login-box-name"><bean:message key="label.password" /></div><div id="login-box-field">
<html:password property="password" styleClass="form-login" size="30" maxlength="2048"></html:password>
</div>
<br />
<span class="login-box-options"><input type="checkbox" name="1" value="1"> Remember Me <a href="#" style="margin-left:30px;">Forgot password?</a></span>
<br />
<br />
<a href="#" onclick="sendForm();"><img src="loginTheme/login-btn.png" width="103" height="42" style="margin-left:90px;" /></a>

</div>

</div>






</html:form>
</body>
<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

  document.getElementById("company").focus();
  
//-->
</SCRIPT>

</html:html>





