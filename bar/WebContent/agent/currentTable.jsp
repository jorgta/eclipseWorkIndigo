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
<SCRIPT type="text/javascript" src="/bar/js/ajaxfile.js"></SCRIPT>

<title>Mesa Actual</title>
<style type="text/css">
#wrapper {
  MARGIN: 0px;
  PADDING: 0px;
  WIDTH: 100%;
  MIN-HEIGHT: 100%; 
  /*BACKGROUND-COLOR: #55649D;*/
  BORDER-TOP: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
  BORDER-RIGHT: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
  BORDER-LEFT: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
  BORDER-BOTTOM: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
}
* html #wrapper {HEIGHT: 100%;}  /* IE Hack since IE does not support "min-height" in the #wrapper*/
#footer {
  MARGIN: -40px 0px 0px 45px;
  PADDING: 0px;
  WIDTH: 100%;
  HEIGHT: 65px;
  /*BACKGROUND-COLOR: #00FF00;*/
  BORDER-TOP: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
  BORDER-RIGHT: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
  BORDER-LEFT: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
  BORDER-BOTTOM: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
}

#footer1 {
  MARGIN: -35px 0px 0px 25px;
  PADDING: 0px;
  WIDTH: 100%;
  HEIGHT: 65px;
  /*BACKGROUND-COLOR: #00FF00;*/
  BORDER-TOP: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
  BORDER-RIGHT: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
  BORDER-LEFT: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
  BORDER-BOTTOM: 0px solid black;  /* Test/Debug, otherwise leave actual table border as 0px */
}

.BG {
background-image:url(images/table.gif);
background-repeat:no-repeat;/*dont know if you want this to repeat, ur choice.*/
height:/*size of image*/;
width:/*size of image*/
}



</style>
</head>
<body onload="init();">

<%
		HttpSession ses = request.getSession();
		BeanComandas currentComanda= (BeanComandas)ses.getAttribute("currentComanda");
		
		
		int colCounter = 0;
		String backgroundColorCategoria = (String)ses.getAttribute("backgroundColorCategoria");
		String backgroundColorMarca = (String)ses.getAttribute("backgroundColorMarca");
		String backgroundColorProducto = (String)ses.getAttribute("backgroundColorProducto");
		
  	  
%>

<SCRIPT>				  
function FAjax(url,metodo,valor,parameters)
{
   
	  
	   
	  
	   var ajax=creaAjax();
	   //alert(ajax);
	  
	   if (metodo.toUpperCase()=='GET'){
	    document.getElementById('capa').style.visibility= "visible";
	    ajax.open ('GET', url, true);
	    ajax.onreadystatechange = function() {
	         if (ajax.readyState==1) {
	                // document.getElementById('capa').innerHTML="Buscando.......";
	         }
	         else if (ajax.readyState==4){
	            if(ajax.status==200){ 
	                 //document.getElementById('capa').innerHTML=ajax.responseText; 
	                 document.getElementById("currentHour").innerText = ajax.responseText;
	            }
	            else if(ajax.status==404)
	                 {
	
	            	 document.getElementById("currentHour").innerText = "La direccion existe";
	                    
	                    
	                 }
	                 else
	                 {
	                	 document.getElementById("currentHour").innerText = "Error: ".ajax.status;
	                     
	                 }
	        }
	     }
	     
	    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	    ajax.send(null);
	    return
		  }
		  
		  
		if(metodo.toUpperCase()=='POST'){
	        ajax.open ('POST', url, true);
	        ajax.onreadystatechange = function() {
	            if (ajax.readyState==1) {
	            
			               // document.getElementById('capa').innerHTML="Buscando.......";
	                       // insertData("<tr class='SelectedRowStyle'><td colspan='5'>Buscando.......</td></tr> ");
	                
	             }else if (ajax.readyState==4){
	                if(ajax.status==200){
	            
	                  //insertData(ajax.responseText);
	                    //document.getElementById('capa').innerHTML=ajax.responseText;
	                  // insertData(ajax.responseText);
	                   document.getElementById("currentHour").innerText = ajax.responseText;
	                   
	                    
	                }else if(ajax.status==404){
	                    // document.getElementById('capa').innerHTML= "La direccion no existe";
			             
			              
			             // insertData("<tr class='RowStyle'><td colspan='5'>La direccion no existe</td></tr> ");
			              document.getElementById("currentHour").innerText ="La direccion no existe";
			              
	                }else{
	                   // document.getElementById('capa').innerHTML= "Error: ".ajax.status;
	                 
			        
			                // insertData("<tr class='RowStyle'><td colspan='5'>Error: " +ajax.status+"</td></tr> ");
			                 document.getElementById("currentHour").innerText = "Error: " +ajax.status;
	                }
	            }
	        }
	        ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	        //ajax.send(parameters);
	        ajax.send();
	        return;
	    }
   
	//}
	
	 
}


</SCRIPT>
 <center>
  <html:form styleClass="form" action="/tableDetailNew"> 
  <input type="hidden" name="process" value="" />
  <input type="hidden" name="id_categoria" value="" />
  <input type="hidden" name="id_marca" value="" />
  <input type="hidden" name="id_producto" value="" />
  <input type="hidden" name="cantidad" value="" />
  <input type="hidden" name="id_agent" value="" />
  <input type="hidden" name="id_comanda" value="" />
  
  

	<table border="0" style="background-color: #e6dfda;">
	  
	  <tr>
	    <td valign="top">	
	    <!-- CATEGORIAS -->
	    <div  style="max-height:250px; overflow-y:scroll; overflow-x: hidden; background-color: <%=backgroundColorCategoria%>;">	       
		    <table border="1" >
		       <tr>
			   <td valign="top" colspan="4">	
			   CATEGORIAS
			    </td>
			   </tr>
		      <logic:iterate id="id" indexId="indexId" name="categoryList" scope="session" >
		                <%
		                 if (colCounter == 0)
		                	out.write("<tr>");
		         		%>
		          
			                          
			               <td>		                   
					        <a onclick="__doPost('selectCategory',<%="'"%><bean:write name="id" property="id_categoria" /><%="'"%>)"; href="#"><img src="./imageCategory.jsp?id=<bean:write name="id" property="id_categoria" />"  width="115" border="0"></a>
					      
		 			
					       </td>	
					                          		      
					   
				       <% if (colCounter == 3)
				       		{
		                	  out.write("</tr>");
		                	  colCounter = 0;
				       		}
				         else
				        	 colCounter++;
		                %>
				       
				    
				   </logic:iterate>
		
		      
		    </table>
		    </div>
		   <!--MARCA  -->  
		   <div style="max-height:250px; overflow-y:scroll; overflow-x: hidden; background-color: <%=backgroundColorMarca%>">
		     <table border="2" height="" >
		     
		     <%
		       List marcaList = (List)ses.getAttribute("marcaList");
			   if(marcaList.size() > 0)
				   out.write("<tr><td valign='top' colspan='4'>MARCA</td></tr>");
		     %>
		          
		                 <% colCounter= 0; %>
		       <logic:iterate id="id" indexId="indexId" name="marcaList" scope="session" >
		                <%
		               
		                 if (colCounter == 0)
		                	out.write("<tr>");
		         		%>
		          
			                          
			               <td >		                   
					        <a onclick="__doPost('selectMarca',<%="'"%><bean:write name="id" property="id" /><%="'"%>)"; href="#"><img src="./imageMarca.jsp?id=<bean:write name="id" property="id" />"  width="115" border="0"></a>
					      
		 			
					       </td>	
					                          		      
					   
				       <% if (colCounter == 3)
				       		{
		                	  out.write("</tr>");
		                	  colCounter = 0;
				       		}
				         else
				        	 colCounter++;
		                %>
				       
				    
				   </logic:iterate> 
		    </table>
		    </div>
		   <!-- PRODUCTO -->  
		   <div style="max-height:264px; overflow-y:scroll; overflow-x: hidden; background-color: <%=backgroundColorProducto%>">
		     <table border="1" height="">
		                 <% colCounter= 0; %>
		        <%
		       List productoList = (List)ses.getAttribute("productoList");
			   if(productoList.size() > 0)
				   out.write("<tr><td valign='top' colspan='4'>PRODUCTO</td></tr>");
		       %>
		       <logic:iterate id="id" indexId="indexId" name="productoList" scope="session" >
		                <%
		               
		                 if (colCounter == 0)
		                	out.write("<tr>");
		         		%>
		          
			                          
			               <td>		                   
					        <a onclick="__doPost('selectProducto',<%="'"%><bean:write name="id" property="id" /><%="'"%>)"; href="#"><img src="./imageProducto.jsp?id=<bean:write name="id" property="id" />"  width="115" border="0"></a>
					      
		 			
					       </td>	
					                          		      
					   
				       <% if (colCounter == 3)
				       		{
		                	  out.write("</tr>");
		                	  colCounter = 0;
				       		}
				         else
				        	 colCounter++;
		                %>
				       
				    
				   </logic:iterate> 
		    </table>
		    </div>
	    </td>
	    <td rowspan="3" style="background-image: url('/bar/images/receipt.jpg'); background-repeat:no-repeat; background-position: top center;" valign="top">
	    <!-- DETALLE -->  
		    <table border="0" style="margin-top: 80px;" width="90%" align="center" cellpadding="0" cellspacing="0">
		      <tr>
		          <td colspan="3">Nota # <%=currentComanda.getId_comanda() %></td>
		      </tr>  
		      <tr>
		          <td colspan="3">Mesa: <%=currentComanda.getId_mesa().getId() %></td>
		      </tr> 
		      <tr>
		          <td colspan="3">Fecha: <%=currentComanda.getFecha() %> </td>
		      </tr> 
		      <tr>
		          <td colspan="3">Agente:  <%=currentComanda.getId_agente().getNombre() %> </td>
		      </tr> 
		      <script language="JavaScript">
		      function init(){
		    	  window.setInterval("getHour()",1000);
		    	  }
		      
		      function getHour()
		  	  {
		    	 // FAjax(url,metodo,valor,parameters)
		    	 
		    	  FAjax('/bar/GetServerTime','POST','','');
		    	  
		    	  //alert(document.getElementById("currentHour").innerText);
	       	  }
		      
		    
		     
		      </script>
		      <tr>
		          <td colspan="3"><label id="currentHour"></label></td>
		      </tr> 
		
		      <%
		      SimpleDateFormat formateador = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		      double total = (Double)ses.getAttribute("total");
		      %>
		    <tr>
		    <td colspan="3">
		    <table border="1" style="width: 100%" cellpadding="0" cellspacing="0" >
				<tr>
				  <td align="left" width="18%">Cantidad</td>
		          <td align="center" width="23%">Precio</td>
		          <td align="center" width="60%">Descripcion</td>
				</tr>
			</table>
		    <div style="max-height:300px;  overflow-y:scroll; overflow-x: hidden;">
		    <table border="0" width="100%" cellpadding="0" cellspacing="0">
		           
		    <logic:iterate id="detail" indexId="indexId" name="beanDetallaComandaList" scope="session" >
		         <bean:define id="p" name="detail" property="date_capture"> </bean:define>
		      <tr>		     
		          <td width="19%" valign="top"><bean:write name="detail" property="cantidad" /></td>
		          <td width="25%" valign="top"><bean:write name="detail" property="precio_unit" /></td>
		          <td width="61%"><bean:write name="detail" property="id_producto.descripcion" /> <%=formateador.format((Date)p)%> </td>		         
		      </tr> 
		    </logic:iterate>
		  </table>
		    </div>
		    </td>
		  </tr>
		     <tr>
		          <td colspan="3" style="">&nbsp</td>
		      </tr>
		      <tr>
		          <td colspan="3" style="">Notas</td>
		      </tr>
		      <tr>
		          <td colspan="3">
		          <html:textarea property="notes" cols="30" rows="5" />
		          </td>
		      </tr> 
		       <tr>
		          <td colspan="3">Total: <%=total%></td>
		      </tr> 
		    </table>
	   
	    
	    </td>
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
				var w = 380;
		        var h = 209;	        
		       
				
				function openModal( eventArgument,value) { 
					
				var retVal=showModalDialog(confirmPage,"Title without space","titlebar=no; resizable:no; status=no; scroll:off; dialogWidth:" + w +"px; dialogHeight:" + h +"px; dialogLeft:" + left +"px; dialogTop:" + top +"px;");
				
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
	    <td rowspan="3">
	    	 <!-- MESA -->  
		    <table border="0" height="" style="background-color: yellow;" cellpadding="0" cellspacing="0">
		     <tr>
		          <td>MESA</td>
		      </tr> 
		      <tr>
		          <td>
		          <div id="wrapper">
		          <a href="#" onclick="__doPost('changeFromTable',<%="'"%><%=currentComanda.getId_agente().getId()%><%="'"%>)"; ><img src="/bar/images/gray.png"  width="130" height="130" border="0"></a>
		          </div>
		          	<div id="footer"> 		          		
		          	 <label style="font-size: 30px; color: white;" ><%=currentComanda.getId_mesa().getId()%></label>
		          	</div>
		          </td>
		      </tr>  
		        <tr>
		          <td>
		            <div id="wrapper">
		          <a href="#" onClick="openModal('closeComanda',<%="'"%><%=currentComanda.getId_comanda()%><%="'"%>)"><img src="/bar/images/confirmgray.png"  width="130" height="130" border="0"></a>
		          </div>
		          	<div id="footer1"> 		          		
		          	 <label style="font-size: 16px; color: white;" >Cerrar</label>
		          	</div>

					</td>
		      </tr>  
		    </table>
		    
		     	 <!-- PRODUCTO -->  
		    <table border="0" height="" style="background-color: orange;" cellpadding="0" cellspacing="0">
		      <tr>
		          <td>PRODUCTO</td>
		      </tr> 
			   <script language="JavaScript" type="text/javascript">
				<!--			
            
				var valReturned;
				var left = (screen.width/2)-(w/2);
				var top = (screen.height/2)-(h/2);
				var w = 200;
		        var h = 200;
				function showModal() { 
				var retVal=showModalDialog('agent/modal.jsp',null, "dialogWidth:" + w +"px; dialogHeight:" + h +"px; dialogLeft:" + left +"px; dialogTop:" + top +"px;");
				
				if (retVal) 
				 {
					//alert(retVal.cantidad);           
					document.forms['tableDetailNewForm'].cantidad.value= retVal.cantidad; 	
					document.getElementById("quiantity").innerText ="Cantidad:" + retVal.cantidad;
				 }
				else 
				 {
					document.forms['tableDetailNewForm'].cantidad.value=1;					
				     document.getElementById("quiantity").innerText = "Cantidad:" + 1;
				 }
				
				}
				//-->
				</script>
		      <tr>
		          <td>          
		           <div id="wrapper">
		          <a href="#" onClick="showModal()"><img src="/bar/images/chnagequantity.png"  width="130" height="130" border="0"></a>
		          </div>
		          	<div id="footer1"> 		          		
		          	 <label id="quiantity" style="font-size: 16px; color: white;" >Cantidad</label>
						
		          	</div>
		          	</td>
		      </tr>  
		        <tr>
		          <td>
		          	<div id="wrapper">
		          <a href="#"><img src="/bar/images/notes.png"  width="130" height="130" border="0"></a>
		          </div>
		          	<div id="footer1"> 		          		
		          	 <label style="font-size: 16px; color: white;" >Notas</label>
		          	</div>
					</td>
		      </tr>  
		        <tr>
		          <td>    	
		          <div id="wrapper">
		          <a href="#" onclick="__doPost('cancelProduct',<%="'"%><%=currentComanda.getId_comanda()%><%="'"%>)";><img src="/bar/images/cancelred.png"  width="130" height="130" border="0"></a>
		          </div>
		          	<div id="footer1"> 		          		
		          	 <label style="font-size: 16px; color: white;" >Cancelar</label>
		          	</div>
		          	</td>
		      </tr>  
		    </table>
	    
	  	     	 <!-- BOTON SALIR -->  
		    <table border="0" height="" style="border-color: silver;" cellpadding="0" cellspacing="0">
		      <tr>
		          <td>         
		          <div id="wrapper">
		          <a href="#"><img src="/bar/images/quit.png"  width="130" height="130" border="0"></a>
		          </div>
		          	<div id="footer1"> 		          		
		          	 <label style="font-size: 16px; color: white;" >Salir</label>
		          	</div>
		          	</td>
		          	</td>
		      </tr>  
		    </table>
	    </td>
	  </tr>
	  
	</table>
   </html:form>
   </center>

</body>


  <script type="text/javascript">
	//<![CDATA[
	var theForm = document.forms['tableDetailNewForm'];

	function __doPost( eventArgument,value) {
		
       theForm.process.value = eventArgument;	        

      
       if(eventArgument == 'selectCategory')
       {	           
           theForm.id_categoria.value= value; 	
                       	           
       }  
       
       if(eventArgument == 'selectMarca')
       {	           
           theForm.id_marca.value= value; 	
                       	           
       }
       
       if(eventArgument == 'selectProducto')
       {	           
           theForm.id_producto.value= value; 
           if(theForm.cantidad.value=='')
             theForm.cantidad.value=1;            	           
       }
       
       if(eventArgument == 'changeFromTable')
       {	           
           theForm.id_agent.value= value; 
       }   
       
       
       if(eventArgument == 'cancelProduct')
       {	           
           theForm.id_comanda.value= value; 
       } 
       
       if(eventArgument == 'closeComanda')
       {	           
           theForm.id_comanda.value= value; 
       } 

    
       theForm.submit();
	}
	//]]>
	</script>
</html:html>