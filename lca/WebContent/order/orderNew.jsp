<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">-->



<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*,com.bituos.Utils,com.bituos.lca.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
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
    <SCRIPT type="text/javascript" src="/lca/js/ajaxfile.js"></SCRIPT>
    <SCRIPT language="JavaScript" src="/lca/getSystemDate.js"></SCRIPT>
	<SCRIPT language="Javascript" src="/lca/Calendar/calendar.js"></SCRIPT>
	<SCRIPT language="Javascript" src="/lca/Calendar/mycalendar.js"></SCRIPT>
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
.box1 {
	position: absolute;
	margin-left: auto;
	margin-right: auto;
	left:100;
	right:0;
	width:300px;
	height:50px;
	vertical-align: middle;
	background:yellow;
	/*background-image: url(./truckimg/bg1.jpg);*/
	border: solid #D64078 3px;
	top: 160px;
	z-index: 10;
}


</style>
    
    
</HEAD>

<BODY style=" padding:50px; background-image: url('spitxtr.jpg')">

	
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 61;
			    
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
		        

		        //Patient
		        BeanPatient currentPatient = (BeanPatient) ses.getAttribute( "orderNew_currentPatient" );
				
				String currentPatient_String = "-1";
				
				if ( currentPatient != null )
					currentPatient_String = new Integer( currentPatient.getId() ).toString();
				
		        //Doctor
		        BeanDoctor currentDoctor = (BeanDoctor) ses.getAttribute( "orderNew_currentDoctor" );
				
				String currentDoctor_String = "-1";
				
				if ( currentDoctor != null )
					currentDoctor_String = new Integer( currentDoctor.getId() ).toString();

				
		        //Branch
		        BeanBranch currentBranch = (BeanBranch) ses.getAttribute( "orderNew_currentBranch" );
				
				String currentBranch_String = "-1";
				
				if ( currentBranch != null )
					currentBranch_String = new Integer( currentBranch.getId() ).toString();
				
				
		        //Company
		        BeanCompany currentCompany = (BeanCompany) ses.getAttribute( "orderNew_currentCompany" );
				
				String currentCompany_String = "-1";
				
				if ( currentCompany != null )
					currentCompany_String = new Integer( currentCompany.getId() ).toString();
				
		        
				String  dateTo = Utils.DateToStr(Utils.Today());
				//String  dateFrom= Utils.DateToStr(Utils.Date_plus(Utils.Today(), -7));
				
				
				
				
				

		        
		        
			  %>
			  
			  <SCRIPT>	
			  
				var cursorX;
				var cursorY;
				document.onmousemove = function(e){
				    cursorX = e.pageX;
				    cursorY = e.pageY;
				}
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

        if(mode == 'addMaterial'){ 
        	/*document.getElementById("truckdivTemp").style.width= 200 + 'px';
            document.getElementById("truckdivTemp").style.height= 200 + 'px';*/
          	document.getElementById("divTemp").style.left = (cursorX + 5)+'px';
          	document.getElementById("divTemp").style.top = (cursorY - 100)+'px';
          	document.getElementById("divTemp").style.visibility='visible';
          	document.getElementById("divTemp").style.display = 'block';
          	
          	
        	
          	//DoRequest( mode,rid);
        	}	
        
        document.onclick=function(e)
        {
	        var e = window.event || e;
	        var target = e.target || e.srcElement;
	        if(target.id=="aceptar")
	        {	        	
	        	DoRequest( mode,rid);        	
	        }
	        
	        
	        
        }
        
        
        			       
        
}

function closeDiv(p) 
{
	
    var div = document.getElementById(p);
    
    if (div.style.display !== 'none') {
    	div.style.display = 'none';
    }
    else {
    	div.style.display = 'block';
    }
}



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

			 <html:form action="/orderNew" enctype="multipart/form-data" >
			<html:hidden property="process" value="-1"/>
			<html:hidden property="id_testtemp" value=""/>
			<html:hidden property="id_material" value=""/>
			
			<div id='divTemp'  class='box1' style="visibility: hidden;">
			 Cantidad: <html:text property="quantity" size="25"></html:text>
			 </br>
			 Es para Paciente? <html:checkbox property="is_patient" />
			  <button type="button" id="aceptar">Aceptar</button> 
			  <button type="button" id="cancelar" onclick="closeDiv('divTemp');">Cancelar</button> 
			 
			  
			</div>
			

			
			<logic:messagesPresent property="quantity">
			   <div id='divTemp1'  class='box1' style="visibility: visible; top: 200px; left: 200px;"  onmousemove="closeDiv('divTemp1');">
				<FONT COLOR="RED"> <html:errors property="quantity"/></FONT>
				
		      </div>	
			</logic:messagesPresent>
            
               <table ALIGN="CENTER" border="0" style="width: 50%;">
         
              
					<TR bgcolor="lightblue">
						<TD colspan="4" align="center" >					 
						 <div class="x-blue_dg_caption"> <bean:message key="label.patients" />   </div>					 
						</TD>
					</TR>
					<TR>
						<TD><bean:message key="label.patient" /></TD>
						<TD>
						    <html:select property="id_patient">
						    	<logic:iterate id="id" indexId="indexId" name="orderNew_listPatient" scope="session" >
						    		<OPTION value="<bean:write name="id" property="id" />"
						    			<logic:equal name="id" property="id" value="<%=currentPatient_String%>"> 
						    				SELECTED
						    			</logic:equal>>
						    			<bean:write name="id" property="name" />
						    		</option>
							    </logic:iterate>
					  	    </html:select> 
					  	</TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="id_patient"/></FONT> </TD>
						<TD>
						</TD>
					</TR>
					
					
					
					

					<TR>
						<TD><bean:message key="label.doctor" /></TD>
						<TD>
						    <html:select property="id_doctor">
						    	<logic:iterate id="id" indexId="indexId" name="orderNew_listDoctor" scope="session" >
						    		<OPTION value="<bean:write name="id" property="id" />"
						    			<logic:equal name="id" property="id" value="<%=currentDoctor_String%>"> 
						    				SELECTED
						    			</logic:equal>>
						    			<bean:write name="id" property="name" />
						    		</option>
							    </logic:iterate>
					  	    </html:select> 
					  	</TD>
						<TD><bean:message key="label.sent.by.doctor" /><html:checkbox property="sent_by_doctor" ></html:checkbox></TD>
	  					<TD>						
						
					</TR>

					<TR>
						<TD><bean:message key="label.branch.to.delivery" /></TD>
						<TD>
						    <html:select property="id_branch">
						    	<logic:iterate id="id" indexId="indexId" name="orderNew_listBranch" scope="session" >
						    		<OPTION value="<bean:write name="id" property="id" />"
						    			<logic:equal name="id" property="id" value="<%=currentBranch_String%>"> 
						    				SELECTED
						    			</logic:equal>>
						    			<bean:write name="id" property="name" />
						    		</option>
							    </logic:iterate>
					  	    </html:select> 
					  	    
					  	 </TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="id_branch"/></FONT> 
						<TD>
						</TD>
					</TR>

			          <TR>
						<TD><bean:message key="label.password" /></TD>
						<TD>
						    
					  	    <html:password property="password"></html:password>
					  	 </TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="password"/></FONT> 
						<TD>
																
						</TD>
					</TR>
			         <tr>
			          <td>
			          <bean:message key="label.order.date_delivery" />

						</td>
			          <td>  
			                 <html:text property="date_delivery" readonly="true" onclick="javascript:showCal('calendar6');" value="<%=dateTo%>"> </html:text>
							  <A href="javascript:showCal('calendar6')"><IMG src="/lca/Calendar/calendar.png" border="0" /></A>
							  <SPAN id="date6" style="POSITION: relative"> </SPAN>
						      <FONT COLOR="RED"> <html:errors property="date_delivery"/></FONT>
				     </td>
				     <TD>																
						</TD>
					</tr>
					
					<tr>
			          <td>
			          <bean:message key="label.neworder.notes" />

						</td>
			        
					<TD>
					<html:textarea property="notes" rows="7" cols="40"></html:textarea>
					</TD>					
		        	<TD> <FONT COLOR="RED"> <html:errors property="notes"/></FONT> 
					</tr>
					
					 
					<TR>
						<TD><bean:message key="label.company" /></TD>
						<TD>
						    <html:select property="id_company">
						    	<logic:iterate id="id" indexId="indexId" name="orderNew_companyList" scope="session" >
						    		<OPTION value="<bean:write name="id" property="id" />"
						    			<logic:equal name="id" property="id" value="<%=currentCompany_String%>"> 
						    				SELECTED
						    			</logic:equal>>
						    			<bean:write name="id" property="full_name" />
						    		</option>
							    </logic:iterate>
					  	    </html:select> 
				  		</TD>
					<TD></TD>
				</TR>
				 <logic:present name="orderNew_pkgList" scope="session">	
				 <TR>
						<TD><bean:message key="label.order.new.pkg" /></TD>
						<TD>
						    <html:select property="id_pkg">
						    	<logic:iterate id="id" indexId="indexId" name="orderNew_pkgList" scope="session" >
						    		<OPTION value="<bean:write name="id" property="id" />">
						    			<bean:write name="id" property="description" />
						    		</option>
							    </logic:iterate>
					  	    </html:select> 
					  	</TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="id_pkg"/></FONT> </TD>
						<TD>
						   <html:submit onclick="process.value='pkgAdd';"><bean:message key="label.add" /></html:submit>	
						</TD>
					</TR>
					</logic:present>
					
					<TR>
						<TD><bean:message key="label.tests" /></TD>
						<TD>
						    <html:select property="id_test">
								<logic:iterate id="id" indexId="indexId" name="orderNew_listTest" scope="session" >
								<OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>>  
								<bean:write name="id" property="name" /> </OPTION>
							    </logic:iterate>
					  	    </html:select> 
					  	    
					  	 </TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="id_test"/></FONT> 
						<TD>
							<html:submit onclick="process.value='testAdd';"><bean:message key="label.add" /></html:submit>										
						</TD>
					</TR>
					<TR bgcolor="lightblue">
						<TD colspan="4" align="center" >					 
						 <div class="x-blue_dg_caption"> <bean:message key="label.list.materials" />   </div>					 
						</TD>
					</TR>
					<tr>
					   <td colspan="4">
					   <logic:present name="orderNew_materialList" scope="session">					
			            
						<table dir="ltr" id="sp__contentTable" class="x-blue_dg_table" style="margin-left:auto;margin-right:auto;" align="center" width="70%">
						<thead>
						<tr class="dg_tr" style="" id="sp_row_h1">
				
						<th style="background-color:#fcfaf6;width:6%;" bgcolor="#fcfaf6"></th>
						<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search2" value=""  onkeyup="FAjax('data.jsp','POST','list=orderNew_materialList&field=description&value='+ this.value+ '&bean=BeanMaterial&module=orderNew&active=1&currentUser=on');"/></b> </a></th>
			    		<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input disabled="disabled" title="Teclee aqui para buscar" type="text" id="search2" value=""  onkeyup="FAjax('data.jsp','POST','list=orderNew_materialList&field=name&value='+ this.value+ '&bean=BeanMaterial&module=orderNew&active=1&currentUser=on');"/></b> </a></th>
			    		
						</tr>
						
						
						<tr class="dg_tr" style="" id="sp_row_h2" onclick="onMouseClickRow('sp_','','#fdfde7','#ffffff','#F7F9FB');" onmouseover="onMouseOverRow('sp_','','#fdfde7','#f9f9e3');" onmouseout="onMouseOutRow('sp_','','','#fdfde7');">
						<th class="x-blue_dg_th_normal dg_center dg_nowrap" style="background-color:#fcfaf6;width:2%;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"></th>
						<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=2&amp;sp_sort_field_by=&amp;sp_sort_field_type=string&amp;sp_sort_type=asc');" title="Sort"><b><bean:message key="label.description" /></b> </a></th>
						<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=2&amp;sp_sort_field_by=&amp;sp_sort_field_type=string&amp;sp_sort_type=asc');" title="Sort"><b><bean:message key="label.haslabel" /></b> </a></th>
							
						</tr>
						</thead>
									
						<tbody>
			
						<% String cuStyleBackground="rgb(255, 255, 255)";
						   String color;
						   int i=0;
						%>
						
						<logic:iterate id="id" indexId="indexId" name="orderNew_materialList" scope="session" >
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
		
						<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:sp__doPostBack("addMaterial","<bean:write name="id" property="id" />","");' title="Seleccionar registro">Seleccionar</a></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="description" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1">
						
						<logic:equal name="id" property="haslabel" value="1"> 							
						
					      Si
					  
						</logic:equal>
						<logic:equal name="id" property="haslabel" value="0"> 
				
					      No
					    
							
						</logic:equal>
						
						</label></td>
						
						 
					
						</tr>
						
						<%  
						 i++;
						%>
						</logic:iterate>
						
						
						
						</tbody>
						
					
	
						</table>
                         
	
						</logic:present>
					   </td>
					</tr>
					
					<logic:present name="orderNew_materialDetailList" scope="session">	
					<TR bgcolor="lightblue">
						<TD colspan="4" align="center" >					 
						 <div class="x-blue_dg_caption"> <bean:message key="label.list.materialsTouse" />   </div>					 
						</TD>
					</TR>
					
					<TR>
                    <TD colspan="4">
										
			
						<table dir="ltr" id="sp__contentTable" class="x-blue_dg_table" style="margin-left:auto;margin-right:auto;" align="center" width="70%">
					    <thead>
						<tr class="dg_tr" style="" id="sp_row_h1">
				
						
						</tr>
						
						
						
					
						<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=2&amp;sp_sort_field_by=&amp;sp_sort_field_type=string&amp;sp_sort_type=asc');" title="Sort"><b><bean:message key="label.description" /></b> </a></th>
						<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=2&amp;sp_sort_field_by=&amp;sp_sort_field_type=string&amp;sp_sort_type=asc');" title="Sort"><b><bean:message key="label.amount" /></b> </a></th>
						<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=2&amp;sp_sort_field_by=&amp;sp_sort_field_type=string&amp;sp_sort_type=asc');" title="Sort"><b><bean:message key="label.labtechnician" /></b> </a></th>
						 <th class="x-blue_dg_th_normal dg_center dg_nowrap" style="background-color:#fcfaf6;width:6%;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6">Borrar</th>	
						</tr>
						</thead>
									
						<tbody>
			
						<% String cuStyleBackground="rgb(255, 255, 255)";
						   String color;
						   int i=0;
						%>
						
						<logic:iterate id="id" indexId="indexId" name="orderNew_materialDetailList" scope="session" >
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
		
						
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="id_material.description" /></label></td>			
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="quantity" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style="">
						<label class="x-blue_dg_label" id="created_at_1">
						
						<logic:equal name="id" property="is_patient" value="1"> 							
						
					      Paciente
					  
						</logic:equal>
						<logic:equal name="id" property="is_patient" value="0"> 
				
					      Laboratorista
					    
							
						</logic:equal>
						
						
						
						</label></td>
						
						<td class="x-blue_dg_td dg_center dg_nowrap" style=" width:6%;"><img class="dg_opacity dg_pointer" onclick="javascript:sp_verifyDelete('<bean:write name="id" property="id_material.id" />','materialDelete');" src="./grid/index_data/delete.gif" alt="Borrar Registro" title="Borrar Registro"></td>
						</tr>
						
						<%  
						 i++;
						%>
						</logic:iterate>
						
						
						
						</tbody>
						
						<tfoot >						
						<tr  class="dg_tr" style="" id="sp_row_h2" onclick="onMouseClickRow('sp_','','#fdfde7','#ffffff','#F7F9FB');" onmouseover="onMouseOverRow('sp_','','#fdfde7','#f9f9e3');" onmouseout="onMouseOutRow('sp_','','','#fdfde7');">
						
						
						<td  colspan="5" class="x-blue_dg_th dg_left" style="background-color:#fcfaf6; padding-left: 95%" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><img class="dg_opacity dg_pointer" onclick="javascript:sp_verifyDelete('0','materialDeleteAll');" src="./grid/index_data/delete.gif" alt="Borrar Todo" title="Borrar Todo"></td>
			          
						</tr>
						</tfoot>
	
						</table>

	
						
					
					</TD>
					</TR>
					
					</logic:present>
					
					<TR bgcolor="lightblue">
						<TD colspan="4" align="center" >					 
						 <div class="x-blue_dg_caption"> <bean:message key="label.list.analisis" />   </div>					 
						</TD>
					</TR>
                    
                    <TR>
                    <TD colspan="4">
						<logic:present name="orderNew_testList" scope="session">					
			
						<table dir="ltr" id="sp__contentTable" class="x-blue_dg_table" style="margin-left:auto;margin-right:auto;" align="center" width="70%">
						<thead>
						
						
						<tr class="dg_tr" style="" id="sp_row_h2" onclick="onMouseClickRow('sp_','','#fdfde7','#ffffff','#F7F9FB');" onmouseover="onMouseOverRow('sp_','','#fdfde7','#f9f9e3');" onmouseout="onMouseOutRow('sp_','','','#fdfde7');">
						
	
						<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=2&amp;sp_sort_field_by=&amp;sp_sort_field_type=string&amp;sp_sort_type=asc');" title="Sort"><b><bean:message key="label.tests" /></b> </a></th>
						<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=4&amp;sp_sort_field_by=&amp;sp_sort_field_type=&amp;sp_sort_type=desc');" title="Sort"><b><bean:message key="label.price" /></b> </a></th>
						<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=5&amp;sp_sort_field_by=&amp;sp_sort_field_type=&amp;sp_sort_type=desc');" title="Sort"><b><bean:message key="label.indications" /></b> </a></th>
					    <th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=5&amp;sp_sort_field_by=&amp;sp_sort_field_type=&amp;sp_sort_type=desc');" title="Sort"><b><bean:message key="label.materials_to_patient" /></b> </a></th>
			            <th class="x-blue_dg_th_normal dg_center dg_nowrap" style="background-color:#fcfaf6;width:6%;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6">Borrar</th>
	
		
						</tr>
						</thead>
									
						<tbody>
			
						<% String cuStyleBackground="rgb(255, 255, 255)";
						   String color;
						   int i=0;
						%>
						
						<logic:iterate id="id" indexId="indexId" name="orderNew_testList" scope="session" >
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
		
						
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="name" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="price" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="indications" /></label></td>
			            <td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="materials_to_patient" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=" width:6%;"><img class="dg_opacity dg_pointer" onclick="javascript:sp_verifyDelete('<bean:write name="id" property="id" />','testDelete');" src="./grid/index_data/delete.gif" alt="Borrar Registro" title="Borrar Registro"></td>
	
						</tr>
						
						<%  
						 i++;
						%>
						</logic:iterate>
						
						
						
						</tbody>
						
						<tfoot >						
						<tr  class="dg_tr" style="" id="sp_row_h2" onclick="onMouseClickRow('sp_','','#fdfde7','#ffffff','#F7F9FB');" onmouseover="onMouseOverRow('sp_','','#fdfde7','#f9f9e3');" onmouseout="onMouseOutRow('sp_','','','#fdfde7');">
						
						
						<td  colspan="5" class="x-blue_dg_th dg_left" style="background-color:#fcfaf6; padding-left: 95%" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><img class="dg_opacity dg_pointer" onclick="javascript:sp_verifyDelete('0','testDeleteAll');" src="./grid/index_data/delete.gif" alt="Borrar Todo" title="Borrar Todo"></td>
			          
						</tr>
						</tfoot>
	
						</table>

	
						</logic:present>
					
					</TD>
					</TR>
					
	


                    <logic:present name="orderNew_testList" scope="session">
						<TR>
						<TD>
						 <img height="40" width="40"  class="dg_opacity dg_pointer" onclick="DoRequest('done','');" src="./grid/index_data/Save.png" alt="Guardar" title="Guardar">
						 </br>
						 <b>Guardar</b>
						</TD>
						</TR>
					</logic:present>
				</table>

  
				
	
			</html:form>

		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	
</BODY>

<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

//<![CDATA[
var theForm = document.forms['orderNewForm'];
var quantity =0;
function DoRequest( eventArgument,value) {

   
   theForm.process.value = eventArgument;	        
   
   if(eventArgument == 'testDelete')
	  {	 
	    
	   theForm.id_testtemp.value = value; 
	  
	  }
     		                       	           
  
   if(eventArgument == 'addMaterial')
	  {	 
	    
	    theForm.id_material.value = value; 

	  }
   
   if(eventArgument == 'materialDelete')
	  {	 
	    
	    theForm.id_material.value = value; 

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
			if(grid == "testDelete")
			{
				DoRequest("testDelete",rid);
			}
			
			if(grid == "materialDelete")
			{
				
				DoRequest("materialDelete",rid);
			}
			
		}
		else
		{
			_dgStopPropagation(e);
			false;
		}
	}
	else
	{
		if(confirm("Esta seguro que quire borrar todos los regisros?"))
		{
			if(grid == "testDeleteAll")
			{
				DoRequest("testDeleteAll",rid);
			}
			
			if(grid == "materialDeleteAll")
			{
				
				DoRequest("materialDeleteAll",rid);
			}
			
			
			
		}
		else
		{
			_dgStopPropagation(e);
			false;
		}
	}
	
};






  document.getElementById("name").focus();
  

</SCRIPT>

</html:html>
