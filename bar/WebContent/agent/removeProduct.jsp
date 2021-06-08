<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*, com.bituos.bar.Beans.*, java.io.*, javax.servlet.http.HttpSession.*,java.text.*"%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">

.datagrid table { border-collapse: collapse; text-align: left; width: 100%; } 
.datagrid {font: normal 12px/150% Arial, Helvetica, sans-serif; background: #fff; overflow: hidden; border: 4px solid #006699; }
.datagrid table td, .datagrid table th { padding: 20px 9px; }
.datagrid table thead th {background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F) );background:-moz-linear-gradient( center top, #006699 5%, #00557F 100% );filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F');background-color:#006699; color:#FFFFFF; font-size: 14px; font-weight: bold; border-left: 1px solid #0070A8; } 
.datagrid table thead th:first-child { border: none; }
.datagrid table tbody td { color: #00557F; border-left: 1px solid #E1EEF4;font-size: 20px;font-weight: normal; }
.datagrid table tbody .alt td { background: #E1EEf4; color: #00557F; }
.datagrid table tbody td:first-child { border-left: none; }
.datagrid table tbody tr:last-child td { border-bottom: none; }
.datagrid table tfoot td div { border-top: 1px solid #006699;background: #E1EEf4;} 
.datagrid table tfoot td { padding: 0; font-size: 13px } 
.datagrid table tfoot td div{ padding: 0px; }
.datagrid table tfoot td ul { margin: 0; padding:0; list-style: none; text-align: right; }
.datagrid table tfoot  li { display: inline; }.datagrid table tfoot li a { text-decoration: none; display: inline-block;  padding: 2px 8px; margin: 1px;color: #FFFFFF;border: 1px solid #006699;-webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px; background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F) );background:-moz-linear-gradient( center top, #006699 5%, #00557F 100% );filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F');background-color:#006699; }
.datagrid table tfoot ul.active, 
.datagrid table tfoot ul a:hover { text-decoration: none;border-color: #00557F; color: #FFFFFF; background: none; background-color:#006699;}

</style>
</head>


<body>
     <%
     		  HttpSession ses = request.getSession();
		      SimpleDateFormat formateador = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		      double total = (Double)ses.getAttribute("total");
		      
		      int counter = 0;
	 %>
	 

				
<html:form styleClass="form" action="/deleteProduct"> 
<input type="hidden" name="id_comanda" value="" />
<input type="hidden" name="id_detail" value="" />

 <input type="hidden" name="process" value="" />
<div class="datagrid"><table>
<thead><tr><th>Cantidad</th><th>Precio</th><th>Descripcion</th></tr></thead>
<tfoot><tr><td colspan="3">
<div id="paging">
 <!--  <ul><li><a href="#"><span>Previous</span></a></li><li><a href="#" class="active"><span>1</span></a></li><li><a href="#"><span>2</span></a></li><li><a href="#"><span>3</span></a></li><li><a href="#"><span>4</span></a></li><li><a href="#"><span>5</span></a></li><li><a href="#"><span>Next</span></a></li>
</ul> -->
<ul><li><span><font color="black" size="4">Total: <%=total%></font></span></li>
</ul>

</div>
</tr>
</tfoot>
<tbody>
<logic:iterate id="detail" indexId="indexId" name="beanDetallaComandaList" scope="session" >
   <bean:define id="p" name="detail" property="date_capture"> </bean:define>
   
   
   <%  if ((counter % 2)==1)
	   out.write("<tr>");
   else
	   out.write("<tr class='alt'>");
   %>
		     
    <td><a href="#" onclick="openModal('deleteProduct',<%="'"%><bean:write name="detail" property="id" /><%="'"%>);" stile="text-decoration:none;"><bean:write name="detail" property="cantidad" /></a></td>
    <td><a href="#" onclick="openModal('deleteProduct',<%="'"%><bean:write name="detail" property="id" /><%="'"%>);" stile="text-decoration:none;"><bean:write name="detail" property="precio_unit" /></a></td>
    <td><a href="#" onclick="openModal('deleteProduct',<%="'"%><bean:write name="detail" property="id" /><%="'"%>);" stile="text-decoration:none;"><bean:write name="detail" property="id_producto.descripcion" />  </td></a>		         
</tr> 
  
 <%counter++;%>	
 </logic:iterate>


</tbody>
</table>
</div>
 </html:form>
</body>

	 			<script language="JavaScript" type="text/javascript">
				<!--
				
				var confirmPage;
				var browserName=navigator.appName; 
				if (browserName=="Netscape")
				  { 
					confirmPage = "confirm/confirm2.jsp";
				  }
			    else if (browserName=="Microsoft Internet Explorer")
				  {
				    confirmPage = "confirm/confirm1.jsp";
				  }
				 
				
				
				var valReturned;
				var left = (screen.width/2)-(w/2);
				var top = (screen.height/2)-(h/2);
				var w = 370;
		        var h = 209;	        
		       
				
				function openModal( eventArgument,value) { 
					
				var retVal=showModalDialog(confirmPage,"","titlebar=no; resizable:no; status=no; scroll:off; dialogWidth:" + w +"px; dialogHeight:" + h +"px; dialogLeft:" + left +"px; dialogTop:" + top +"px;");
				
				if (retVal) 
				 {
								
						if(retVal.confirm)    
						{
								
							__doPost( eventArgument,value);
						}

				 }
				else 
				 {
					//alert("no funciono");
					//document.forms['tableDetailNewForm'].cantidad.value=1;					
				     //document.getElementById("quiantity").innerText = "Cantidad:" + 1;
				 }
				
				}
				//-->
				</script>

  <script type="text/javascript">
	//<![CDATA[
	var theForm = document.forms['deleteProductForm'];

	function __doPost( eventArgument,value) {
		
       theForm.process.value = eventArgument;	        

      
       if(eventArgument == 'deleteProduct')
       {	           
           theForm.id_detail.value= value; 	
                       	           
       }  



    
       theForm.submit();
	}
	//]]>
	</script>
</html:html>