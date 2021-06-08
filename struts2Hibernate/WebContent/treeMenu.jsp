<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<HEAD>

<%@ page session="true" import="java.util.*,com.struts2.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>


<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>Administracion</TITLE>

<script src="TreeMenu.js" type="text/javascript"></script>
<style type="text/css">
/* Menu container */

.menu	{
	position:relative;
	width:320px;
	min-height:100px;
	border:solid #FF9900 0px;
	padding:10px 5px 10px 5px;
	margin:12px 12px 12px 10px;
	_width:364px;
	_height:100px;
	}

/* Menu styles */
.menu ul
	{
	margin:0px;
	padding:0px;
	text-decoration:none;
	}
.menu li
	{
	margin:0px 0px 0px 5px;
	padding:0px;
	list-style-type:none;
	text-align:left;
	font-family:Arial,Helvetica,sans-serif;
	font-weight:normal;
	font-size:13px;
	line-height:22px;
	}

/* Submenu styles */
.menu ul ul 
	{ }
.menu li li
	{ margin:0px 0px 0px 16px; }
.menu li li.open,
.menu li li.close
	{ margin:0px 0px 0px 16px; }

/* Symbol styles */
.menu .symbol-gif,
.menu .symbol-png,
.menu .symbol-jpg,
.menu .symbol-txt,
.menu .symbol-zip,
.menu .symbol-html,
.menu .symbol-file,
.menu .symbol-item
	{
	float:left;
	width:18px;
	height:16px;
	background-position:left center;
	background-repeat:no-repeat;
	}
.menu .symbol-gif   { background-image:url(icons/gif.png); }
.menu .symbol-png   { background-image:url(icons/gif.png); }
.menu .symbol-jpg   { background-image:url(icons/jpeg.png); }
.menu .symbol-txt   { background-image:url(icons/txt.png); }
.menu .symbol-zip   { background-image:url(icons/zip.png); }
.menu .symbol-html  { background-image:url(icons/html.png); }
.menu .symbol-file  { background-image:url(icons/file.png); }
.menu .symbol-item  { background-image:url(icons/page.png); }
.menu .symbol-open,
.menu .symbol-close
	{
	float:left;
	width:34px;
	height:16px;
	background-position:left center;
	background-repeat:no-repeat;
	}
.menu .symbol-close { background-image:url(icons/plus-folder-closed.png); }
.menu .symbol-open  { background-image:url(icons/minus-folder-open.png); }
.menu .symbol-item.last  { }
.menu .symbol-close.last { }
.menu .symbol-open.last  { }

/* Menu line styles */
.menu li.item  { font-weight:normal; }
.menu li.close { font-weight:normal; }
.menu li.open  { font-weight:bold; }
.menu li.item.last  { }
.menu li.close.last { }
.menu li.open.last  { }

/* Show All / Hide All buttons */
.buttons
	{
	position:absolute;
	left:250px;
	top:10px;
	}
</style>

</HEAD>




<body bgcolor="#ffffff" onload="">

<s:if test="#session.beanUser != null"> 
	<s:if test="#session.processList != null"> 
 	
	<div class="menu">
	<ul id="example">	
	<li>Other Sites
 	<ul>
 	
	 <s:iterator value="#session.processTypeList" var="processType">
	 <li><s:property value="description"/>
	 <ul>
		
 		<s:iterator value="#session.processList" var="processUsers">
		  
		  <s:if test="#processType.id == id_process.id_type_process.id">
		    
		    
			<li><a href="/struts2Hibernate/pre.jsp?process=<s:property value="id_process.action"/>&action=<s:property value="id_process.action"/>&target=<s:property value="id_process.target"/>"><s:property value="id_process.description"/></a></li>
		      

		
		  </s:if>		        
		</s:iterator> 
		
     </ul>
     </li>     
	</s:iterator>	
  </ul>
  <script type="text/javascript">make_tree_menu('example');</script>
  </div> 



	 

	 
	</s:if>	
</s:if>
<s:else>
  <s:action name="logout" executeResult="true"></s:action>
  
</s:else>

</body>





</html>
