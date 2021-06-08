<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML XHTML 1.0 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*,com.bituos.lca.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"

%>




<html:html>
<HEAD>

	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM WebSphere Studio">
	
	<META http-equiv="Content-Style-Type" content="text/css">
		<link rel="stylesheet" href="theme/gh-buttons.css">


<!-- initialize -->
    <!-- DataGrid CSS definitions - START -->
	<link href="./grid/index_data/print.css" type="text/css" rel="stylesheet" media="print">
	<link rel="stylesheet" type="text/css" href="./grid/index_data/style_002.css">
	<!--[if IE]><link rel="stylesheet" type="text/css" href="modules/datagrid/styles/x-blue/style_IE.css" /><![endif]-->
	<!-- DataGrid CSS definitions - END -->


    <script type="text/javascript" src="./grid/index_data/jquery.js"></script>	
    <SCRIPT type="text/javascript" src="/lca/js/ajaxfile.js"></SCRIPT>
	
    <TITLE>
    </TITLE>
</HEAD>

<BODY>

	<H1>  </H1>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 11;
			    
			    List list = (List) ses.getAttribute("processList");
			    Iterator iter = list.iterator();
			    BeanProcessUsers beanProcessUser;
			    int found = 0;
			    
			    while( iter.hasNext() )
			      {
			        beanProcessUser = (BeanProcessUsers) iter.next(); 
			        
			        if ( beanProcessUser.getId_process().getId() == TAG )
			          {
			            found = 1;
			            break;
			          }
			      } 
		        if ( found == 0 )
		          response.sendRedirect( "../login.jsp");
		        else
		          found = 0;
			  %>

<SCRIPT>				  
function FAjax(url,metodo,parameters)
{
	   var ajax=creaAjax();

		  
		  
	   
		if(metodo.toUpperCase()=='POST'){
	        ajax.open ('POST', url, true);
	        ajax.onreadystatechange = function() {
	            if (ajax.readyState==1) {
	            	 document.getElementById('sp_row_0').parentNode.innerHTML ="<tr class='dg_tr' id='searching'><td colspan='9' align='center'><img src='./grid/index_data/loading.gif' alt='Buscando...'></td></tr>"; 	  
	            }else if (ajax.readyState==4){
	                if(ajax.status==200){	           
	                	document.getElementById('searching').parentNode.innerHTML =ajax.responseText;                      
	                }else if(ajax.status==404){	     		             
	                	document.getElementById('searching').parentNode.innerHTML ="<tr class='dg_tr' id='searching'><td colspan='8'>La direccion no existe.......</td></tr>"; 
			              
	                }else{
	                	document.getElementById('searching').parentNode.innerHTML ="<tr class='dg_tr' id='searching'><td colspan='8'>"  + ajax.status + "</td></tr>"; 
			              
	                }
	            }
	        }
	        ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	        ajax.send(parameters);
	        return;
	    }
   
	
	
	 
}


</SCRIPT>

<script type="text/javascript">
<!--
function sp__doPostBack(mode,rid,param){

        if(mode == 'activate'){ DoRequest( mode,rid);}			     
        			       
        
}



//-->
</script>
			

			<html:form action="/userSelect">
			<html:hidden property="id" value=""/>
			<html:hidden property="process" value=""/>
			<table ALIGN="CENTER" border="0" >
			
			<TR bgcolor="lightblue">
			<td colspan="5" align="center" >					 
			 <div class="x-blue_dg_caption">  <bean:message key="label.user.select" />   </div>					 
			</td>
			</TR>
			
			<tr>
			<td colspan="4">
			  <br>
			</td>
			</tr>
		     <tr>
			  <td>
	       
		             <table class="x-blue_dg_tbl_outside" dir="ltr" style="margin-left:auto;margin-right:auto;" align="center" border="0" width="100%">
						<tbody>
					  
						</tbody>
						</table>
						<table dir="ltr" id="sp__contentTable" class="x-blue_dg_table" style="margin-left:auto;margin-right:auto;" align="center" width="100%">
						<thead>
						<tr class="dg_tr" style="" id="sp_row_h1">
				
						<th style="background-color:#fcfaf6;width:6%;" bgcolor="#fcfaf6"></th>
						<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search2" value=""  onkeyup="FAjax('data.jsp','POST','list=listUserChange&field=name&value='+ this.value+ '&bean=BeanUser&module=userSelect&active=Y&currentUser=off');"/></b> </a></th>
			    		<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search2" value=""  onkeyup="FAjax('data.jsp','POST','list=listUserChange&field=address&value='+ this.value+ '&bean=BeanUser&module=userSelect&active=Y&currentUser=off');"/></b> </a></th>
						<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search3" value=""  onkeyup="FAjax('data.jsp','POST','list=listUserChange&field=email&value='+ this.value+ '&bean=BeanUser&module=userActivate&active=Y&currentUser=off');"/></b> </a></th>
						
						</tr>
						
						
						<tr class="dg_tr" style="" id="sp_row_h2" onclick="onMouseClickRow('sp_','','#fdfde7','#ffffff','#F7F9FB');" onmouseover="onMouseOverRow('sp_','','#fdfde7','#f9f9e3');" onmouseout="onMouseOutRow('sp_','','','#fdfde7');">
						<th class="x-blue_dg_th_normal dg_center dg_nowrap" style="background-color:#fcfaf6;width:2%;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"></th>
						<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=2&amp;sp_sort_field_by=&amp;sp_sort_field_type=string&amp;sp_sort_type=asc');" title="Sort"><b>Nombre</b> </a></th>
		
						<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=5&amp;sp_sort_field_by=&amp;sp_sort_field_type=&amp;sp_sort_type=desc');" title="Sort"><b>Direccion</b> </a></th>
			            <th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=5&amp;sp_sort_field_by=&amp;sp_sort_field_type=&amp;sp_sort_type=desc');" title="Sort"><b>Email</b> </a></th>
						
						</tr>
						</thead>
									
						<tbody>
			
						<% String cuStyleBackground="rgb(255, 255, 255)";
						   String color;
						   int i=0;
						%>
						<logic:iterate id="id" indexId="indexId" name="listUserChange" scope="session" >
						<%
						  		 // BeanSale beanSale = (BeanSale) ses.getAttribute( "currentSale" );
							  
							  if (cuStyleBackground == "rgb(255, 255, 255)")		  
							  {
								  cuStyleBackground = "rgb(247, 249, 251)";
								  color = "#d9e3f1";
							  }
							  else
							  {
								  cuStyleBackground = "rgb(255, 255, 255)";
								  color = "#e4ecf7";
							  }
							  
							  
						%>	
						<tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
						onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
						onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
						onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
						<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:sp__doPostBack("activate","<bean:write name="id" property="id" />","");' title="Seleccionar registro">Seleccionar</a></td>
						
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="name" /></label></td>
		
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="address" /></label></td>
			            <td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="email" /></label></td>
	
						</tr>
						
						<%  
						 i++;
						%>
						</logic:iterate>
						
						
						
						</tbody>
						
						</table>
			</td>
			</tr>
			</table>
			
			

			
	

			
			</html:form>

		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	<logic:notPresent name="beanUser" scope="session">
	   <logic:redirect href="../login.jsp">  </logic:redirect>
	</logic:notPresent>
	
</BODY>

<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">


//<![CDATA[
var theForm = document.forms['userSelectForm'];

function DoRequest( eventArgument,value) {

       
   theForm.process.value = eventArgument;	        
           
   theForm.id.value= value; 		                       	           


   theForm.submit();
}
//]]>

</SCRIPT>
</html:html>
