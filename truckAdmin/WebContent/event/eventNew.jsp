<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*,com.bituos.truckAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"

%>





<html:html>
<head>
	<title><bean:message key="label.event.new" /></title>
	<link href="./grid/index_data/style.css" type="text/css" rel="stylesheet">
    <META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    
<!-- DataGrid CSS definitions - START -->
<link href="./grid/index_data/print.css" type="text/css" rel="stylesheet" media="print">
<link rel="stylesheet" type="text/css" href="./grid/index_data/style_002.css">
<!--[if IE]><link rel="stylesheet" type="text/css" href="modules/datagrid/styles/x-blue/style_IE.css" /><![endif]-->
<!-- DataGrid CSS definitions - END -->


    <script type="text/javascript" src="./grid/index_data/jquery.js"></script>	
    <SCRIPT type="text/javascript" src="/truckAdmin/js/ajaxfile.js"></SCRIPT>
<style>
    .db_menu_wrap {left: 0px;top: 0px;right: 0px;z-index:9999;width:100%;height:50px;background: -moz-linear-gradient(top, #b8e1fc 0%, #a9d2f3 10%, #90bae4 25%, #90bcea 37%, #90bff0 50%, #6ba8e5 51%, #a2daf5 83%, #bdf3fd 100%); /* firefox */ background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#b8e1fc), color-stop(10%,#a9d2f3), color-stop(25%,#90bae4), color-stop(37%,#90bcea), color-stop(50%,#90bff0), color-stop(51%,#6ba8e5), color-stop(83%,#a2daf5), color-stop(100%,#bdf3fd)); /* webkit */ -webkit-box-shadow: 0px 2px 5px #000000;-moz-box-shadow: 0px 2px 5px #000000;box-shadow: 0px 2px 5px #000000;position: fixed;margin-top:-35px;}
    .db_menu_links {height: 50px; float: left; margin-left:10px; }
    .db_menu_links ul {margin: 0px;padding: 0px;list-style-type: none;}
    .db_menu_links ul li {display: inline;background-image:none;}
    .db_menu_links ul li a {float:left;line-height:50px;padding-right:20px;padding-left:20px;color: #FFF;font-size: 18px;text-decoration: none;text-shadow: -1px -1px 1px #333;display: block;border-right-width: 1px;border-right-style: solid;border-right-color: #FFF;}
    .db_menu_links ul li a:hover {color: #000;text-shadow: -1px -1px 1px #EEE;background: #ffffff; /* old browsers */ background: -moz-linear-gradient(top, #ffffff 0%, #f1f1f1 50%, #e1e1e1 51%, #f6f6f6 100%); /* firefox */ background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffffff), color-stop(50%,#f1f1f1), color-stop(51%,#e1e1e1), color-stop(100%,#f6f6f6)); /* webkit */ }
    .db_menu_buttons { float:right; line-height: 50px; height: 50px; margin-top: -100px; margin-right: 30px; color:#fff; font-size: 16px;text-shadow: -1px -1px 1px #333;}
    .db_menu_buttons SELECT { padding:4px; font-size: 14px; }
    .db_normal { margin-top: -100px;}
    .db_first { border-left-width: 1px; border-left-style: solid; border-left-color: #FFF; -webkit-box-shadow: 0px 2px 5px #000000;-moz-box-shadow: 0px 2px 5px #000000;box-shadow: 0px 2px 5px #000000; margin-top:23px; border-radius: 0 0 12px 12px; height:45px; background-color:#6ba8e5; background: -moz-linear-gradient(top, #b8e1fc 0%, #a9d2f3 10%, #90bae4 25%, #90bcea 37%, #90bff0 50%, #6ba8e5 51%, #a2daf5 83%, #bdf3fd 100%); /* firefox */ background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#b8e1fc), color-stop(10%,#a9d2f3), color-stop(25%,#90bae4), color-stop(37%,#90bcea), color-stop(50%,#90bff0), color-stop(51%,#6ba8e5), color-stop(83%,#a2daf5), color-stop(100%,#bdf3fd)); }
    </style>
    
</head>
<body>
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 92;
			    
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
		        
		        
		        String ctpString = (String) ses.getAttribute( "ctpString" );
		        String ctTireString = (String) ses.getAttribute( "ctTireString" );
		        String cteString = (String) ses.getAttribute( "cteString" );
		        
		        
			  %>


<br><br>

<!-- START This code was generated by datagrid.class.php v7.8.x (http://www.apphp.com/php-datagrid/index.php) START -->
<script type="text/javascript" src="./grid/index_data/dg.js"></script>
<script type="text/javascript" src="./grid/index_data/dg-en.js"></script>
<script type="text/javascript" src="./grid/index_data/highlight.js"></script>
<script type="text/javascript">
<!--
function sp_verifyDelete(rid,prm,e){if(!e) e = window.event; 
if(confirm("Esta seguro que quire borrar este regisro?"))
{
	sp__doPostBack("delete",rid,prm);
}
else
{
	_dgStopPropagation(e);
	false;
}
};
//-->
</script>
			  
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


<div class="x-blue_dg_caption"> <bean:message key="label.tire.list" /> </div><p></p>

  
	         <html:form action="/eventNew">
			 <html:hidden property="process" value=""/>
			 <html:hidden property="id_tire" value=""/>
						 
			<script type="text/javascript">
			<!--
			function sp__doPostBack(mode,rid,param){
	
			        if(mode == 'select'){ DoRequest( mode,rid);}			     
			        else if(mode == 'add')  { DoRequest( mode,rid);}			       
			        
			}
			
			
		
			//-->
			</script>
			
			
			
	
			<table dir="ltr" id="sp__contentTable" class="x-blue_dg_table" style="margin-left:auto;margin-right:auto;" align="center" width="70%">
			<thead>
			<tr class="dg_tr" style="" id="sp_row_h1">
			<th style="background-color:#fcfaf6;width:6%;" bgcolor="#fcfaf6"></th>
			<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search1" value=""  onkeyup="FAjax('data.jsp','POST','list=listTireForEvent&field=design&value='+ this.value+ '&bean=BeanTire&module=eventNew');"/>	</b> </a></th>
			<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search2" value=""  onkeyup="FAjax('data.jsp','POST','list=listTireForEvent&field=deep&value='+ this.value+ '&bean=BeanTire&module=eventNew');"/></b> </a></th>
			<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search3" value=""  onkeyup="FAjax('data.jsp','POST','list=listTireForEvent&field=position&value='+ this.value+ '&bean=BeanTire&module=eventNew');"/></b> </a></th>
			<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search4" value=""  onkeyup="FAjax('data.jsp','POST','list=listTireForEvent&field=serial_number&value='+ this.value+ '&bean=BeanTire&module=eventNew');"/></b> </a></th>
			<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search5" value=""  onkeyup="FAjax('data.jsp','POST','list=listTireForEvent&field=id_type_tire_status.description&value='+ this.value+ '&bean=BeanTire&module=eventNew');"/></b> </a></th>
			<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search6" value=""  onkeyup="FAjax('data.jsp','POST','list=listTireForEvent&field=id_place.description&value='+ this.value+ '&bean=BeanTire&module=eventNew');"/></b> </a></th>
			<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search7" value=""  onkeyup="FAjax('data.jsp','POST','list=listTireForEvent&field=id_type_measure.description&value='+ this.value+ '&bean=BeanTire&module=eventNew');"/></b> </a></th>
			<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search8" value=""  onkeyup="FAjax('data.jsp','POST','list=listTireForEvent&field=id_truck.registration&value='+ this.value+ '&bean=BeanTire&module=eventNew');"/></b> </a></th>
			
			
			
			</tr>
			
			
			<tr class="dg_tr" style="" id="sp_row_h2" onclick="onMouseClickRow('sp_','','#fdfde7','#ffffff','#F7F9FB');" onmouseover="onMouseOverRow('sp_','','#fdfde7','#f9f9e3');" onmouseout="onMouseOutRow('sp_','','','#fdfde7');">
			<th class="x-blue_dg_th_normal dg_center dg_nowrap" style="background-color:#fcfaf6;width:2%;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"></th>
			<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="" title="Sort"><b>Dise?o</b> </a></th>
			<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="" title="Sort"><b>Profundidad</b> </a></th>
			<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="" title="Sort"><b>Posicion</b> </a></th>
			<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="" title="Sort"><b>No Serie</b> </a></th>
			<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="" title="Sort"><b>Status</b> </a></th>
			<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="" title="Sort"><b>Lugar</b> </a></th>
			<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="" title="Sort"><b>Medida</b> </a></th>
			<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="" title="Sort"><b>Identificador de Veh?culo</b> </a></th>
			
			
				</tr>
			</thead>
						
			<tbody>

			<% String cuStyleBackground="rgb(255, 255, 255)";
			   String color;
			   int i=0;
			%>
			<logic:iterate id="id" indexId="indexId" name="listTireForEvent" scope="session" >
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
			
			<td class="x-blue_dg_td_main dg_center dg_nowrap"  style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:sp__doPostBack("select","<bean:write name="id" property="id" />","");' title="Select record">Seleccionar</a></td>
			<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="design" /></label></td>
			<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="deep" /></label></td>
			<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="position" /></label></td>
			<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="serial_number" /></label></td>
			<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="id_type_tire_status.description" /></label></td>
			<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="id_place.description" /></label></td>
			<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="id_type_measure.description" /></label></td>
			<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="id_truck.registration" /></label></td>

			</tr>
			
			<%  
			 i++;
			%>
			</logic:iterate>

			</tbody>			
			</table>
				<!-- END This code was generated by datagrid.class.php v7.8.x (http://www.apphp.com/php-datagrid/index.php) END -->
			
			<div>
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD><bean:message key="label.event.tire" /></TD>
						<TD>
					   							
							<%=ctTireString %>	
						    
                       </TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="id_type_measure"/></FONT> 
					</TR>
					
					<TR>
						<TD><bean:message key="label.event.odometer" /></TD>
						<TD><html:text property="odometer"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="odometer"/></FONT> 
					</TR>
					<TR>
						<TD><bean:message key="label.tire.place" /></TD>
						<TD>
					   		<html:select property="id_type_place">						
							<logic:iterate id="id" indexId="indexId" name="listTypePlace" scope="session" >
							    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
								    <logic:equal name="id" property="id" value="<%=ctpString%>"> 
								    		SELECTED
								    </logic:equal>>
							    	<bean:write name="id" property="description" />
							    </OPTION>
					    	</logic:iterate>						
							</html:select>						
						    
                       </TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="id_place"/></FONT> 
					</TR>
					
				    <TR>
						<TD><bean:message key="label.tire.design" /></TD>
						<TD><html:text property="design"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="design"/></FONT> 
					</TR>
					
				
					
					<TR>
						<TD><bean:message key="label.tire.deep" /></TD>
						<TD><html:text property="deep" size="60"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="deep"/></FONT> 
					</TR>
					
					<TR>
						<TD><bean:message key="label.tire.typeEvent"/></TD>
						<TD>
					   		<html:select property="id_type_event">						
							<logic:iterate id="id" indexId="indexId" name="listTypeEvent" scope="session" >
							    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
								    <logic:equal name="id" property="id" value="<%=cteString%>"> 
								    		SELECTED
								    </logic:equal>>
							    	<bean:write name="id" property="description" />
							    </OPTION>
					    	</logic:iterate>						
							</html:select>						
						    
                       </TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="id_type_event"/></FONT> 
					</TR>
					
				  <TR>
					<TD><bean:message key="label.event.comments" /></TD>
					<TD><html:textarea property="comments" cols="50" rows="10" /></TD>
		        	<TD> <FONT COLOR="RED"> <html:errors property="comments"/></FONT> 
					</TR>
			    </TBODY>
			</TABLE>
			</div>
		


			<CENTER><P>
			<input type="button" name="add" value="<bean:message key="label.register" />" onclick='javascript:sp__doPostBack("add","","");'> 
			
			

			</P></CENTER>			
			<CENTER><P><html:button property="finish" onclick="location='/truckAdmin/main.jsp';"> <bean:message key="label.finish" /> </html:button> </P></CENTER>			
			
			
			  <script type="text/javascript">
				//<![CDATA[
				var theForm = document.forms['eventNewForm'];
				
				function DoRequest( eventArgument,value) {
				
				        
			       theForm.process.value = eventArgument;	        
				           
			       theForm.id_tire.value= value; 		                       	           
		      
			    
			       theForm.submit();
				}
				//]]>
				</script>
			
		
          </html:form>
		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	<logic:notPresent name="beanUser" scope="session">
	   <logic:redirect href="../login.jsp">  </logic:redirect>
	</logic:notPresent>
</body>
</html:html>