<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
		
		<title>Admin | Sign In</title>
		
		<!--                       CSS                       -->
	  
		<!-- Reset Stylesheet -->
		<link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen">
	  
		<!-- Main Stylesheet -->
		<link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen">
		
		<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
		<link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen">	
		
		<!-- Colour Schemes
	  
		Default colour scheme is green. Uncomment prefered stylesheet to use it.
		
		<link rel="stylesheet" href="resources/css/blue.css" type="text/css" media="screen" />
		
		<link rel="stylesheet" href="resources/css/red.css" type="text/css" media="screen" />  
	 
		-->
		
		<!-- Internet Explorer Fixes Stylesheet -->
		
		<!--[if lte IE 7]>
			<link rel="stylesheet" href="resources/css/ie.css" type="text/css" media="screen" />
		<![endif]-->
		
		<!--                       Javascripts                       -->
	  
		<!-- jQuery -->
		<script type="text/javascript" src="resources/scripts/jquery-1.js"></script>
		
		<!-- jQuery Configuration -->
		<script type="text/javascript" src="resources/scripts/simpla.js"></script>
		
		<!-- Facebox jQuery Plugin -->
		<script type="text/javascript" src="resources/scripts/facebox.js"></script>
		
		<!-- jQuery WYSIWYG Plugin -->
		<script type="text/javascript" src="resources/scripts/jquery.js"></script>
		
		<!-- Internet Explorer .png-fix -->
		
		<!--[if IE 6]>
			<script type="text/javascript" src="resources/scripts/DD_belatedPNG_0.0.7a.js"></script>
			<script type="text/javascript">
				DD_belatedPNG.fix('.png_bg, img, li');
			</script>
		<![endif]-->
		
	</head>
  
	<body id="login">
		
		<div id="login-wrapper" class="png_bg">
			<div id="login-top">
			
				<h1>Credito y Cobranza Agent Frontend</h1>
				<!-- Logo (221px width) -->
				<img id="logo" src="resources/images/logocreditoycobranza.png" alt="Simpla Admin logo">
			</div> <!-- End #logn-top -->
			
			<div id="login-content">
				
				<s:form action="login">
				
						
					  <s:if test="#session.error != null"> 
						<div class="notification error png_bg">
							<a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
							<div>
								 Usuario inexistente o contraseña incorrecta.
							</div>
						</div>
						</s:if>
					<div class="notification information png_bg">
						<div>
							Solo de click "Sign In". Usuario y Contraseña Necesarios.
						</div>
					</div>
					<p>
						<label>EMPRESA</label>
						<input class="text-input" type="text" name="company"  value="BITUOS">						
					</p>
					<div class="clear"></div>
					<p>
						<label>Username</label>
						<input class="text-input" type="text" name="username" value="admin">
						
					</p>
					<div class="clear"></div>
					<p>
						<label>Password</label>
						<input class="text-input" name="password"  type="password" value="j">
					</p>
					<div class="clear"></div>
					<p id="remember-password">
						<input type="checkbox">Recuerdame
					</p>
					<div class="clear"></div>
					<p>
						<input class="button" value="Sign In" type="submit">
					</p>
					
				</s:form>
			</div> <!-- End #login-content -->
			
		</div> <!-- End #login-wrapper -->
		
  
  

    <div id="facebox" style="display:none;">       <div class="popup">         <table>           <tbody>             <tr>               <td class="tl"></td><td class="b"></td><td class="tr"></td>             </tr>             <tr>               <td class="b"></td>               <td class="body">                 <div class="content">                 </div>                 <div class="footer">                   <a href="#" class="close">                     <img src="resources/images/closelabel.gif" title="close" class="close_image">                   </a>                 </div>               </td>               <td class="b"></td>             </tr>             <tr>               <td class="bl"></td><td class="b"></td><td class="br"></td>             </tr>           </tbody>         </table>       </div>     </div></body></html>