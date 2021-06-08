<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*,com.bituos.truckAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"

%>




<html:html>
<HEAD>
    <TITLE>
    </TITLE>
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
</HEAD>

<BODY>

	<H1> <bean:message key="label.truck.change" />  </H1>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 83;
			    
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
		        
		        BeanTruck beanTruck = (BeanTruck) ses.getAttribute( "currentVehicle" );
		        
		        BeanTruckTireConfiguration beanTruckTireConfiguration = (BeanTruckTireConfiguration) ses.getAttribute( "beanTruckTireConfiguration" );
			       
		        
		       // beanTruck = (L) ses.getAttribute( "listTruckTireConfiguration" );
		        
		        /*if(beanTruck == null)
		        	response.sendRedirect("../links.jsp");*/
			  %>

				

			<html:form action="/truckChange" enctype="multipart/form-data">
		    <html:hidden property="id" value="<%= String.valueOf(beanTruck.getId()) %>"/>
			<html:hidden property="process" value=""/>
			 <html:hidden property="id_truck_tire_configuration" value=""/>
			
		   	<table border="0" align="center">
		   	<TR bgcolor="lightblue">
			<td colspan="3" align="center" >					 
			 <div class="x-blue_dg_caption"> <bean:message key="label.configurations.list" />   </div>					 
			</td>
			</TR>
			
			<tr><td colspan="3">
			  <br>
			</td>
			</tr>
		   	<tr>
		   	 <td>
						
					<script type="text/javascript">
					<!--
					function sp__doPostBack(mode,rid,param){
			
					        if(mode == 'save'){ DoRequest( mode,rid);}			     
					        else if(mode == 'edit')  { DoRequest( mode,rid);}
					        else if(mode == 'select')  { DoRequest( mode,rid);}
					        
					}
					//-->
					</script>
					
					
					
					<table class="x-blue_dg_tbl_outside" dir="ltr" style="margin-left:auto;margin-right:auto;" align="center" border="0" width="100%">
					<tbody>
				
					</tbody>
					</table>
					<table dir="ltr" id="sp__contentTable" class="x-blue_dg_table" style="margin-left:auto;margin-right:auto;" align="center" width="70%">
					<thead>
					<tr class="dg_tr" style="" id="sp_row_h1">
			
					<th style="background-color:#fcfaf6;width:6%;" bgcolor="#fcfaf6"></th>
					<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search1" value=""  onkeyup="FAjax('data.jsp','POST','list=listTruckTireConfiguration&field=description&value='+ this.value+ '&bean=BeanTruckTireConfiguration&module=truckEdit');"/>	</b> </a></th>
					<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search2" value=""  onkeyup="FAjax('data.jsp','POST','list=listTruckTireConfiguration&field=date_reg&value='+ this.value+ '&bean=BeanTruckTireConfiguration&module=truckEdit');"/></b> </a></th>
					<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search3" value=""  onkeyup="FAjax('data.jsp','POST','list=listTruckTireConfiguration&field=configuration&value='+ this.value+ '&bean=BeanTruckTireConfiguration&module=truckEdit');"/></b> </a></th>
					
					</tr>
					
					
					<tr class="dg_tr" style="" id="sp_row_h2" onclick="onMouseClickRow('sp_','','#fdfde7','#ffffff','#F7F9FB');" onmouseover="onMouseOverRow('sp_','','#fdfde7','#f9f9e3');" onmouseout="onMouseOutRow('sp_','','','#fdfde7');">
					<th class="x-blue_dg_th_normal dg_center dg_nowrap" style="background-color:#fcfaf6;width:2%;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"></th>
					<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=2&amp;sp_sort_field_by=&amp;sp_sort_field_type=string&amp;sp_sort_type=asc');" title="Sort"><b>Desripcion</b> </a></th>
					<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=4&amp;sp_sort_field_by=&amp;sp_sort_field_type=&amp;sp_sort_type=desc');" title="Sort"><b>Fecha de Registro</b> </a></th>
					<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=5&amp;sp_sort_field_by=&amp;sp_sort_field_type=&amp;sp_sort_type=desc');" title="Sort"><b>Copnfiguracion</b> </a></th>
		
					</tr>
					</thead>
								
					<tbody>
		
					<% String cuStyleBackground="rgb(255, 255, 255)";
					   String color;
					   int i=0;
					%>
					<logic:iterate id="id" indexId="indexId" name="listTruckTireConfiguration" scope="session" >
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
					<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:sp__doPostBack("select","<bean:write name="id" property="id" />","");' title="Edit record">Seleccionar</a></td>
					
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="description" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="date_reg" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="configuration" /></label></td>
		
					</tr>
					
					<%  
					 i++;
					%>
					</logic:iterate>
					
					
					
					</tbody>
					
					</table>
			  </td>
			</tr>
			
			
			<%if(beanTruckTireConfiguration != null)
						  {	
						%>
								<TR>
									<TD><bean:message key="label.truck.configuration" />
									<%=beanTruckTireConfiguration.getDescription() %> </TD>
									
									
									     
									 
								</TR>
						<%
					     }
						%>		
				
				
				
			<TR bgcolor="lightblue">
			<td colspan="3" align="center" >					 
			 <div class="x-blue_dg_caption"> Datos Del Veh�culo   </div>					 
			</td>
			</TR>
			
			<tr><td colspan="3">
			  <br>
			</td>
			</tr>
						
			<tr>
			<td>
			<TABLE border="0" align="left">
				<TBODY>
					<TR>
						<TD><bean:message key="label.truck.description" /></TD>
						<TD><html:text property="description" value='<%=beanTruck.getDescription()%>'></html:text>
						</TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="description"/></FONT> 
					</TR>
					<TR>
						<TD><bean:message key="label.truck.registration" /></TD>
						<TD><html:text property="registration" value='<%=beanTruck.getRegistration()%>'></html:text></TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="registration"/></FONT> 
					</TR>
					<TR>
						<TD><bean:message key="label.truck.model" /></TD>
						<TD><html:text property="model" value='<%=beanTruck.getModel()%>'></html:text></TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="model"/></FONT> 
					</TR>
					
										
					
				</TBODY>
			</TABLE>
			</td>
			</tr>
		</table>
			
			<script type="text/javascript">
				//<![CDATA[
				var theForm = document.forms['truckChangeForm'];
				
				function DoRequest( eventArgument,value) {
				
				     
			       theForm.process.value = eventArgument;	        
			      // alert(theForm.process.value);          
			       theForm.id_truck_tire_configuration.value= value; 		                       	           
		      
			    
			       theForm.submit();
				}
				//]]>
				</script>
				
			
			
			<CENTER>
			  <P>
			    
			<CENTER><P><input type="button" name="Guardar" value="<bean:message key="label.register" />" onclick='javascript:sp__doPostBack("save","","");'> </P></CENTER>		
			<CENTER><P><html:button property="finish" onclick="location='/truckAdmin/main.jsp';"> <bean:message key="label.finish" /> </html:button> </P></CENTER>			
				  </P>
			
			</CENTER>			
			
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

  document.getElementById("fullname").focus();
  
//-->
</SCRIPT>



</html:html>
