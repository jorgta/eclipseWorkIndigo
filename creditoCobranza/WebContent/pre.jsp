<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
<HEAD>

<TITLE>Pantalla principal</TITLE>

<%
	String process = request.getParameter("process");
	String action = request.getParameter("action");
	String target = request.getParameter("target");

	
%>

</HEAD>


				
<body>
        <s:set var="process"><%=process%></s:set>
        <s:set var="action"><%=action%></s:set>
        <s:set var="tarjet"><%=target%></s:set> 


        
		<s:if test="#session.beanUser != null"> 	
			<s:form name="preForm" action="%{#action}" method="post" >
			<s:hidden name="process" value="%{#process}"/>
	
					<script type="text/javascript">
					    var tar= "<%=target%>";
					    if(tar != "null")
					    {
					    	//alert(tar);
							preForm.target = tar; 
						    //alert(preForm.target);	
					    	
					    }
						

						preForm.submit();

					</script>
					
			</s:form>
		</s:if>
		<s:else>
		  <%  
			response.sendRedirect("./");
			%> 
		  
		  
		</s:else>
</body>


</html>
