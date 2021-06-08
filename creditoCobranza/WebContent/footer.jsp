<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page session="true" import="java.util.*,com.struts2.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>

<%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
%>
<div id="footer">
	<small> <!-- Remove this notice or replace it with whatever you want -->
			&#169; Copyright 2016 <%=beanUser.getId_company().getName() %> | Powered by <a href="http://www.bituos.com" target="_blank">Bituos Tools</a> | <a href="#">Top</a>
	</small>
</div><!-- End #footer -->