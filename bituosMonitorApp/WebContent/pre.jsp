<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
<HEAD>

<TITLE>Pantalla principal</TITLE>

<%
	String process = request.getParameter("process");
	String action = request.getParameter("action");
	//String target = request.getParameter("target");
	//pre.action?process
%>

</HEAD>


				
<body>
        <s:set var="process"><%=process%></s:set>
        <s:set var="action"><%=action%></s:set>
        
					<!--
					

alert(%{#action} )

					// -->
					</SCRIPT>

        
		<s:if test="#session.beanUser != null"> 	
			<s:form name="preForm" action="%{#action}" method="post" tarjet="_blank">
			<s:hidden name="process" value="%{#process}"/>
		
				
				
					<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">
					<!--
					

							preForm.submit();

					// -->
					</SCRIPT>
					
			</s:form>
		</s:if>
		<s:else>
		  <%  
			response.sendRedirect("/bituosMonitorApp/loginnew.jsp");
			%> 
		  
		  
		</s:else>
</body>


</html>
