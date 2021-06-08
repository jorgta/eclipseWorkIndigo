<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page session="true" import="java.util.*,com.struts2.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>

<html>
<HEAD>

<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>Administracion</TITLE>



</HEAD>



<body>

<img border="0" width="150" height="55" name ="company_logo" alt="logo de la empresa" src='<s:property value="id_user.id_company.logo_file_name"/>'>

</body>

</html>