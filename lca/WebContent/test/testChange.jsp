<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">-->



<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*,com.bituos.lca.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
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
    
  <style>
  
   .textBox
   {
     height:30px; 
	 width:300px
   }
  </style>
  
  
     <style type="text/css">
      .LockOff {
         display: none;
         visibility: hidden;
      }

      .LockOn {
         display: block;
         visibility: visible;
         position: absolute;
         z-index: 999;
         top: 0px;
         left: 0px;
         width: 105%;
         height: 105%;
         background-color: #ccc;
         text-align: center;
         padding-top: 20%;
         filter: alpha(opacity=75);
         opacity: 0.75;
      }
   </style>

   <script type="text/javascript">
      function skm_LockScreen(str)
      {
         var lock = document.getElementById('skm_LockPane');
         if (lock)
            lock.className = 'LockOn';

         lock.innerHTML = str;
      }
   </script> 
    
    
</HEAD>

<BODY style=" padding:50px; background-image: url('spitxtr.jpg')">

	
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 103;
			    
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
		        
		        
		        BeanTest beanTestforChange = (BeanTest) ses.getAttribute( "beanTestForChange" );
		        
		        
		        BeanTestParameter beanTestParameterCurrent = (BeanTestParameter) ses.getAttribute( "beanTestParameterCurrent" );
		        
		        String id_test_parameter = "0";
		        if(beanTestParameterCurrent != null)
		        id_test_parameter =String.valueOf( beanTestParameterCurrent.getId() );
		    	//httpSession.setAttribute("beanParameterLimitList",list);
		    	
		    	 BeanParameterLimits beanParameterLimitsCurrent = (BeanParameterLimits) ses.getAttribute( "beanParameterLimitsCurrent" );
		    	
		    	 String id_parameter_limit = "0";
			        if(beanParameterLimitsCurrent != null)
			        	id_parameter_limit =String.valueOf( beanParameterLimitsCurrent.getId() );
			        
		    	 
				
			        
				
		        
				 
			  %>

			
            <html:form action="/testChange" enctype="multipart/form-data" >
			<html:hidden property="process" value="-1"/>
			<html:hidden property="id_test" value="<%=String.valueOf( beanTestforChange.getId() ) %>"/>
			<html:hidden property="id_parameter_limits" value="<%=String.valueOf( id_parameter_limit ) %>"/>
			
			
            
               <table ALIGN="CENTER" border="0" style="width: 70%;">
					<TR bgcolor="lightblue">
						<TD colspan="4" align="center" >					 
						 <div class="x-blue_dg_caption"> <bean:message key="label.tests" />   </div>					 
						</TD>
					</TR>
					<TR>
						<TD><bean:message key="label.name" /></TD>
						<TD><html:text property="test_name" value="<%=beanTestforChange.getName() %>" size="60"></html:text></TD>
			        	<TD colspan="2"> <FONT COLOR="RED"> <html:errors property="test_name"/></FONT></TD> 
					</TR>
					<TR>
						<TD><bean:message key="label.price" /></TD>
						<TD><html:text property="test_price" value="<%=String.valueOf( beanTestforChange.getPrice()) %>" size="60"></html:text></TD>					
			        	<TD colspan="2"> <FONT COLOR="RED"> <html:errors property="test_price"/></FONT></TD> 
					</TR>
					<TR>
						<TD><bean:message key="label.indications" /></TD>
						<TD><html:text property="test_indications" value="<%=beanTestforChange.getIndications() %>" size="60"></html:text></TD>					
			        	<TD colspan="2"> <FONT COLOR="RED"> <html:errors property="test_indications"/></FONT></TD> 
					</TR>
					<TR>
						<TD><bean:message key="label.materials_to_patient" /></TD>
						<TD><html:text property="test_materials_to_patient" value="<%=beanTestforChange.getMaterials_to_patient() %>" size="60"></html:text></TD>					
			        	<TD colspan="2"> <FONT COLOR="RED"> <html:errors property="test_materials_to_patient"/></FONT></TD> 
					</TR>
					<TR>
						<TD><bean:message key="label.method" /></TD>
						<TD><html:text property="test_method" value="<%=beanTestforChange.getMethod()%>" size="60"></html:text></TD>					
			        	<TD> <FONT COLOR="RED"> <html:errors property="test_method"/></FONT></TD> 
						<TD>
							<html:submit onclick="process.value='testSave';"><bean:message key="label.editSave" /></html:submit>										
						</TD>
					</TR>
				</table>

  
           
             <logic:present name="listTestParameter" scope="session">
               <table ALIGN="CENTER" border="0" style="width: 70%;">
					<TR bgcolor="lightblue">
						<TD colspan="4" align="center" >					 
						 <div class="x-blue_dg_caption"> <bean:message key="label.test.parameter.asociation" />   </div>					 
						</TD>
					</TR>
					<%-- <TR>
						<TD><bean:message key="label.tests" /></TD>
						<TD>
						    <html:select property="id_test">
								<logic:iterate id="id" indexId="indexId" name="listTest" scope="session" >
								<OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  <bean:write name="id" property="name" /> </OPTION>
							    </logic:iterate>
					  	    </html:select> 
					  	    
					  	 </TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="id_test"/></FONT></TD> 
						<TD>
						</TD>
					</TR>--%>
					<TR>
						<TD><bean:message key="label.parameters" /></TD>
						<TD>
						    <html:select property="id_test_parameter"  onchange="process.value='selectParameter'; submit(); skm_LockScreen('Estamos procesando su solicitud...');" onclick="">
								<logic:iterate id="id" indexId="indexId" name="listTestParameter" scope="session" >
								<OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
								<logic:equal name="id" property="id" value="<%=id_test_parameter%>"> 
									    		SELECTED
								</logic:equal>
								>  <bean:write name="id" property="name" /> </OPTION>
							    </logic:iterate>
					  	    </html:select> 
					  	    <html:submit onclick="process.value='testParameterDelete';"><bean:message key="label.delete" /></html:submit>
					  	    
					  	    
					  	</TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="id_test_parameter"/></FONT> </TD>
						<TD>
						</TD>
					</TR> 
					<logic:present name="beanTestParameterCurrent" scope="session">
					<TR>
						<TD><bean:message key="label.parameter" /></TD>
						<TD><html:text property="test_parameter_name" value="<%=beanTestParameterCurrent.getName()%>"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="test_parameter_name"/></FONT></TD> 
						<TD>
						</TD>
					</TR>
					<TR>
						<TD><bean:message key="label.measurement.unit" /></TD>
						<TD><html:text property="test_parameter_measurement_unit" value="<%=beanTestParameterCurrent.getMeasurement_unit()%>"></html:text></TD>					
			        	<TD> <FONT COLOR="RED"> <html:errors property="test_parameter_measurement_unit"/></FONT></TD> 
						<TD>
						</TD>
					</TR>
					<TR>
						<TD><bean:message key="label.answer.is_numeric" /></TD>
						<TD>
						<% if(beanTestParameterCurrent.getIs_numeric() == 1)
						{
						%>
			  			   	
			  			   <input type="checkbox" name="test_parameter_is_numeric"  checked="checked">
			  			   	  			   
			  			<%
						}
						else
						{
						%>
						   <input type="checkbox" name="test_parameter_is_numeric" >
						<% 
						}
						%>
						 </TD>					
						<TD></TD>
						<TD>
						</TD>
					</TR>
					
					
					
					<TR>
						<TD><bean:message key="label.answer.is_free.text" /></TD>
						<TD>
						<% if(beanTestParameterCurrent.getIs_free_text() == 1)
						{
						%>			  			   	
			  			   <input type="checkbox" name="test_parameter_is_free_text"  checked="checked">
			  			   	  			   
			  			<%
						}
						else
						{
						%>
						   <input type="checkbox" name="test_parameter_is_free_text" >
						<% 
						}
						%>
						 </TD>					
						<TD></TD>
						<TD>
						</TD>
					</TR>
					</logic:present>
					
					
				
					
					
				  <logic:present name="beanParameterLimitList" scope="session">					
			       <tr><td colspan="4">
						<table dir="ltr" id="sp__contentTable" class="x-blue_dg_table" style="margin-left:auto;margin-right:auto;" align="center" width="100%">
						<thead>
						
						<tr class="dg_tr" style="" id="sp_row_h1">
				
					<!-- 	<th style="background-color:#fcfaf6;width:6%;" bgcolor="#fcfaf6"></th>
						<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search1" value=""  onkeyup="FAjax('data.jsp','POST','list=testList&field=name&value='+ this.value+ '&bean=BeanTest&module=testSelect');"/>	</b> </a></th>
						<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search1" value=""  onkeyup="FAjax('data.jsp','POST','list=testList&field=price&value='+ this.value+ '&bean=BeanTest&module=testSelect');"/>	</b> </a></th>						
						<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search3" value=""  onkeyup="FAjax('data.jsp','POST','list=testList&field=method&value='+ this.value+ '&bean=BeanTest&module=testSelect');"/></b> </a></th>
						 -->
						</tr>
			
						<tr class="dg_tr" style="" id="sp_row_h2" onclick="onMouseClickRow('sp_','','#fdfde7','#ffffff','#F7F9FB');" onmouseover="onMouseOverRow('sp_','','#fdfde7','#f9f9e3');" onmouseout="onMouseOutRow('sp_','','','#fdfde7');">
						
	                    <th class="x-blue_dg_th_normal dg_center dg_nowrap" style="background-color:#fcfaf6;width:2%;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"></th>
						<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=2&amp;sp_sort_field_by=&amp;sp_sort_field_type=string&amp;sp_sort_type=asc');" title="Sort"><b><bean:message key="label.description" /></b> </a></th>
						<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=2&amp;sp_sort_field_by=&amp;sp_sort_field_type=string&amp;sp_sort_type=asc');" title="Sort"><b><bean:message key="label.min.reference.value" /></b> </a></th>
						<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=5&amp;sp_sort_field_by=&amp;sp_sort_field_type=&amp;sp_sort_type=desc');" title="Sort"><b><bean:message key="label.max.reference.value" /></b> </a></th>

						<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=5&amp;sp_sort_field_by=&amp;sp_sort_field_type=&amp;sp_sort_type=desc');" title="Sort"><b><bean:message key="label.answer.show.in.report" /></b> </a></th>
                        <th class="x-blue_dg_th_normal dg_center dg_nowrap" style="background-color:#fcfaf6;width:6%;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6">Borrar</th>

		
						</tr>
						</thead>
									
						<tbody>
			
						<% String cuStyleBackground="rgb(255, 255, 255)";
						   String color;
						   int i=0;
						%>
						
						<logic:iterate id="id" indexId="indexId" name="beanParameterLimitList" scope="session" >
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
		
						<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:DoRequest("selectParameterLimit","<bean:write name="id" property="id" />");' title="Select record">Seleccionar</a></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="description" /></label></td>
						
						<% if(beanTestParameterCurrent.getIs_numeric() == 1)
						{
						%>
			  			   <td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="limit_from_num" /></label></td>		
						   <td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="limit_to_num" /></label></td>		  			   
			  			<%
						}
						else
						{
						%>
						   <td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="limit_from_str" /></label></td>		
						   <td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="limit_to_str" /></label></td>
						
						<% 
						}
						%>
						
			
						
						<logic:equal name="id" property="show_in_report" value="1"> 
							<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">SI</label></td>
						</logic:equal>
						<logic:notEqual name="id" property="show_in_report" value="1"> 
							<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">NO</label></td>
						</logic:notEqual>
						
						<td class="x-blue_dg_td dg_center dg_nowrap" style=" width:6%;"><img class="dg_opacity dg_pointer" onclick="javascript:sp_verifyDelete('<bean:write name="id" property="id" />','parameterLimitDelete');" src="./grid/index_data/delete.gif" alt="Borrar Registro" title="Borrar Registro"></td>
						</tr>
						
			
						
						<%  
						 i++;
						%>
						</logic:iterate>

					</table>
					
					</td></tr>
					
					</logic:present>
					
					
					
					
					
					<TR bgcolor="lightblue">
						<TD colspan="4" align="center" >					 
						 <div class="x-blue_dg_caption"> <bean:message key="label.references.values" />   </div>					 
						</TD>
					</TR>
					<%
					if (beanParameterLimitsCurrent != null)
					{
					%>
					<TR>
						<TD colspan="4">

							<TABLE>
								<TR>
									<TD><bean:message key="label.description" /></TD>
									<TD><html:text property="parameter_limit_description" value="<%=beanParameterLimitsCurrent.getDescription() %>"></html:text></TD>					
						        	<TD> <FONT COLOR="RED"> <html:errors property="parameter_limit_description"/></FONT></TD> 
									<TD></TD>
								</TR>
								
								
							<% if(beanTestParameterCurrent.getIs_numeric() == 1)
							    {
							%>
				  			   <TR>
									<TD><bean:message key="label.min_value" /></TD>
									<TD><html:text property="parameter_limit_from" value="<%=String.valueOf( beanParameterLimitsCurrent.getLimit_from_num() ) %>"></html:text></TD>					
						        	<TD> <FONT COLOR="RED"> <html:errors property="parameter_limit_from"/></FONT></TD> 
									<TD></TD>
								</TR>
								<TR>
									<TD><bean:message key="label.max_value" /></TD>
									<TD><html:text property="parameter_limit_to" value="<%=String.valueOf( beanParameterLimitsCurrent.getLimit_to_num() ) %>"></html:text></TD>					
						        	<TD> <FONT COLOR="RED"> <html:errors property="parameter_limit_to"/></FONT></TD> 
									<TD>
									</TD>
								</TR>	  			   
				  			<%
								}
								else
								{
							%>
							    <TR>
									<TD><bean:message key="label.min_value" /></TD>
									<TD><html:text property="parameter_limit_from" value="<%=String.valueOf( beanParameterLimitsCurrent.getLimit_from_str() ) %>"></html:text></TD>					
						        	<TD> <FONT COLOR="RED"> <html:errors property="parameter_limit_from"/></FONT></TD> 
									<TD></TD>
								</TR>
								<TR>
									<TD><bean:message key="label.max_value" /></TD>
									<TD><html:text property="parameter_limit_to" value="<%=String.valueOf( beanParameterLimitsCurrent.getLimit_to_str() ) %>"></html:text></TD>					
						        	<TD> <FONT COLOR="RED"> <html:errors property="parameter_limit_to"/></FONT></TD> 
									<TD>
									</TD>
								</TR>
							
							<% 
								}
							%>
						

								<TR>
									<TD><bean:message key="label.answer.show.in.report" /></TD>
									<TD>
										<% if(beanParameterLimitsCurrent.getShow_in_report() == 1)
										    {
										%>
										  <input type="checkbox" name="parameter_limit_show_in_report" checked="checked">
									 
									    <%
										    }
										  else
										    {
									    %>
									      <input type="checkbox" name="parameter_limit_show_in_report" >
										   									   										
										<%
										    }
									    %>
								
						  			   
									 </TD>					
									<TD></TD>
									<TD>
										<html:submit onclick="process.value='ParameterLimitAdd';"><bean:message key="label.add" /></html:submit>	
										<html:submit onclick="process.value='ParameterLimitCancel';"><bean:message key="label.editClear" /></html:submit>									
									</TD>
								</TR>
							</TABLE>
						</TD>
					</TR>					
					
					
					<%
					} else if (beanParameterLimitsCurrent == null)
					{
					%>
					<TR>
						<TD colspan="4">

							<TABLE>
								<TR>
									<TD><bean:message key="label.description" /></TD>
									<TD><html:text property="parameter_limit_description" value=""></html:text></TD>					
						        	<TD> <FONT COLOR="RED"> <html:errors property="parameter_limit_description"/></FONT></TD> 
									<TD></TD>
								</TR>
							
				  			   <TR>
									<TD><bean:message key="label.min_value" /></TD>
									<TD><html:text property="parameter_limit_from"  value=""></html:text></TD>					
						        	<TD> <FONT COLOR="RED"> <html:errors property="parameter_limit_from"/></FONT></TD> 
									<TD></TD>
								</TR>
								<TR>
									<TD><bean:message key="label.max_value" /></TD>
									<TD><html:text property="parameter_limit_to"  value=""></html:text></TD>					
						        	<TD> <FONT COLOR="RED"> <html:errors property="parameter_limit_to"/></FONT></TD> 
									<TD>
									</TD>
								</TR>	  			   
				  			
						

								<TR>
									<TD><bean:message key="label.answer.show.in.report" /></TD>
									<TD>
										
										  <input type="checkbox" name="parameter_limit_show_in_report">
							
								
						  			   
									 </TD>					
									<TD></TD>
									<TD>
										<html:submit onclick="process.value='ParameterLimitAdd';"><bean:message key="label.add" /></html:submit>
										
										
																				
									</TD>
								</TR>
							</TABLE>
						</TD>
					</TR>
					<%}%>
					
					
					
					
					<TR bgcolor="lightblue">
						<TD colspan="4" align="center" >					 
						 <div class="x-blue_dg_caption">Lista de Valores De Referencia Nuevos o Con Cambios </div>					 
						</TD>
					</TR>
					
					<tr>
					  <td colspan="4">
					   <logic:present name="testNew_parameterLimitsList" scope="session">					
			           <tr><td colspan="4">
						<table dir="ltr" id="sp__contentTable" class="x-blue_dg_table" style="margin-left:auto;margin-right:auto;" align="center" width="100%">
						<thead>
						
						<tr class="dg_tr" style="" id="sp_row_h1">
				
					<!-- 	<th style="background-color:#fcfaf6;width:6%;" bgcolor="#fcfaf6"></th>
						<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search1" value=""  onkeyup="FAjax('data.jsp','POST','list=testList&field=name&value='+ this.value+ '&bean=BeanTest&module=testSelect');"/>	</b> </a></th>
						<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search1" value=""  onkeyup="FAjax('data.jsp','POST','list=testList&field=price&value='+ this.value+ '&bean=BeanTest&module=testSelect');"/>	</b> </a></th>						
						<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search3" value=""  onkeyup="FAjax('data.jsp','POST','list=testList&field=method&value='+ this.value+ '&bean=BeanTest&module=testSelect');"/></b> </a></th>
						 -->
						</tr>
			
						<tr class="dg_tr" style="" id="sp_row_h2" onclick="onMouseClickRow('sp_','','#fdfde7','#ffffff','#F7F9FB');" onmouseover="onMouseOverRow('sp_','','#fdfde7','#f9f9e3');" onmouseout="onMouseOutRow('sp_','','','#fdfde7');">
						
<!-- 	                      <th class="x-blue_dg_th_normal dg_center dg_nowrap" style="background-color:#fcfaf6;width:2%;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"></th> -->
						
	                    
						<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=2&amp;sp_sort_field_by=&amp;sp_sort_field_type=string&amp;sp_sort_type=asc');" title="Sort"><b><bean:message key="label.description" /></b> </a></th>
						<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=2&amp;sp_sort_field_by=&amp;sp_sort_field_type=string&amp;sp_sort_type=asc');" title="Sort"><b><bean:message key="label.min.reference.value" /></b> </a></th>
						<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=5&amp;sp_sort_field_by=&amp;sp_sort_field_type=&amp;sp_sort_type=desc');" title="Sort"><b><bean:message key="label.max.reference.value" /></b> </a></th>

						<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=5&amp;sp_sort_field_by=&amp;sp_sort_field_type=&amp;sp_sort_type=desc');" title="Sort"><b><bean:message key="label.answer.show.in.report" /></b> </a></th>


		
						</tr>
						</thead>
									
						<tbody>
			
						<% String cuStyleBackground="rgb(255, 255, 255)";
						   String color;
						   int i=0;
						%>
						
						<logic:iterate id="id" indexId="indexId" name="testNew_parameterLimitsList" scope="session" >
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
		
						<%-- <td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:DoRequest("selectParameterLimit","<bean:write name="id" property="id" />");' title="Select record">Seleccionar</a></td> --%>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="description" /></label></td>
						
						<% if(beanTestParameterCurrent.getIs_numeric() == 1)
						{
						%>
			  			 <td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="limit_from_num" /></label></td>		
						 <td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="limit_to_num" /></label></td>		  			   
			  			<%
						}
						else
						{
						%>
						    <td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="limit_from_str" /></label></td>		
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="limit_to_str" /></label></td>
						
						<% 
						}
						%>
						
			
						
						<logic:equal name="id" property="show_in_report" value="1"> 
							<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">SI</label></td>
						</logic:equal>
						<logic:notEqual name="id" property="show_in_report" value="1"> 
							<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">NO</label></td>
						</logic:notEqual>
						
						
						</tr>
						
			
						
						<%  
						 i++;
						%>
						</logic:iterate>

					</table>
					
					</td></tr>
					
					</logic:present>
					  
					  </td>
					</tr>
					
					<TR>
						<TD></TD>
						<TD></TD>					
			        	<TD> </TD> 
						<TD>
							<html:submit onclick="process.value='link';"><bean:message key="label.register" /></html:submit>										
						</TD>
					</TR>

				</table>
			
			</logic:present>
				
				
	
			</html:form>

		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	<div id="skm_LockPane" class="LockOff"></div> 
</BODY>



<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

//<![CDATA[
var theForm = document.forms['testChangeForm'];

function DoRequest( eventArgument,value) {

   
   theForm.process.value = eventArgument;	        
   
   if(eventArgument == 'selectParameterLimit')
   { 
       theForm.id_parameter_limits.value = value; 	  
	}
   
   if(eventArgument == 'parameterLimitDelete')
   { 
       theForm.id_parameter_limits.value = value; 	  
	}
   


   theForm.submit();
}
//]]>


function sp_verifyDelete(rid,grid){
	  //alert(rid);
	//if(!e) e = window.event; 
	
	if(rid != 0)
	{
		if(confirm("Esta seguro que quire borrar este regisro?"))
		{
			if(grid == "parameterLimitDelete")
			{
				DoRequest("parameterLimitDelete",rid);
			}
			
			
		}
		else
		{
			_dgStopPropagation(e);
			false;
		}
	}
}
	

</SCRIPT>




</html:html>
