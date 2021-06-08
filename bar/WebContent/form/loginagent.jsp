<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*, com.bituos.bar.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>


<html:html>
<title>Agente</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="style.css" type="text/css" media="all" />
	<link rel="stylesheet" href="css/validationEngine.jquery.css" type="text/css" media="screen" title="no title" charset="utf-8" />
    
		<link rel="stylesheet" href="css/template.css" type="text/css" media="screen" title="no title" charset="utf-8" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js" type="text/javascript"></script>

		<script src="js/jquery.validationEngine-en.js" type="text/javascript"></script>
		<script src="js/jquery.validationEngine.js" type="text/javascript"></script>

		<script>
		/*$(document).ready(function() {
			
			
			
			
			$("#form1").validationEngine({
				ajaxSubmit: true,
					ajaxSubmitFile: "ajaxSubmit.jsp",
					ajaxSubmitMessage: "Thank you, We will contact you soon !",
				success :  false,
				failure : function() {}
			})
			

		
		});*/
		</script>
        
        
</head>
<body>


<%

   HttpSession ses = request.getSession();

   BeanAgente bean = new BeanAgente();
	if ( request.getParameter("id") != null )
	  {
		
			List listSize = (List)ses.getAttribute("agenteList");
			Iterator iter = listSize.iterator();	
				 
			// byte[] bytes =null;
			   while ( iter.hasNext() )
				 {			
				   bean = (BeanAgente)iter.next();
				   if(bean.getId() == Integer.valueOf( request.getParameter("id")) )
				   {  
					  //bytes =  bean.getFoto();
					  ses.setAttribute("agent", bean);
					  break;
				   }
				 }
		
	
		String name = bean.getNombre();
	  }
%>
<div id="wrapper">
  <div id="form-div">
 
    <html:form styleClass="form" action="/loginAgent">
    <input type="hidden" name="id_agent" value="<%= bean.getId() %>" />
    <input type="hidden" name="process" value="login" />
      <p class="name">
    
        
        <html:text property="name" style="" styleClass="text-input" value='<%=bean.getNombre() %>' ></html:text>
        <label for="name">Nombre</label>
      </p>
      <p class="email">
       
        <html:password property="password" style="" styleClass="text-input" value='' ></html:password>
        <label for="email">Password</label>
      </p>
 
      <p class="submit">
        <input type="submit" value="Send" />
      </p>
    </html:form>

  </div>
</div>
</body>
</html:html>