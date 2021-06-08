<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">-->



<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*,com.bituos.Utils, com.bituos.lca.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
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
    
    
<SCRIPT language="JavaScript" src="/lca/getSystemDate.js"></SCRIPT>
<SCRIPT language="Javascript" src="/lca/Calendar/calendar.js"></SCRIPT>
<SCRIPT language="Javascript" src="/lca/Calendar/mycalendar.js"></SCRIPT>
</HEAD>

<BODY style=" padding:50px; background-image: url('spitxtr.jpg')">

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

		        
		        
		        BeanTestOrderTest currentBeanTestOrderTest = (BeanTestOrderTest) ses.getAttribute( "orderResult_currentBeanTestOrderTest" );
				
				String currentTestOrderTest_String = null;
				
				if ( currentBeanTestOrderTest != null )
				  currentTestOrderTest_String = new Integer( currentBeanTestOrderTest.getId() ).toString();
				
				BeanTestResult currentBeanTestResult = (BeanTestResult) ses.getAttribute( "orderResult_currentBeanTestResult" );
			    
				String currentTestResult_String = null;
				
				if ( currentBeanTestResult != null )
				  currentTestResult_String = new Integer( currentBeanTestResult.getId() ).toString();
				
				
				
				String  dateTo = Utils.DateToStr(Utils.Today());
				String  dateFrom= Utils.DateToStr(Utils.Date_plus(Utils.Today(), -7));
				
				
			  %>
    
		<SCRIPT>				  
function FAjax(url,metodo,parameters)
{
	   var ajax=creaAjax();

		  
		  
		if(metodo.toUpperCase()=='POST'){
	        ajax.open ('POST', url, true);
	        ajax.onreadystatechange = function() {
	            if (ajax.readyState==1) {
	            	
	            	
	            	document.getElementById("sp_row_0").parentNode.innerHTML = "<tr class='dg_tr' id='searching'><td colspan='5' align='center'><img src='./grid/index_data/loading.gif' alt='Buscando...'></td></tr>";

	            	  
					}else if (ajax.readyState==4){
	                if(ajax.status==200){	           
	                	document.getElementById('searching').parentNode.innerHTML =ajax.responseText;                      
	                }else if(ajax.status==404){	     		             
	                	document.getElementById('searching').parentNode.innerHTML ="<tr class='dg_tr' id='searching'><td colspan='4'>La direccion no existe.......</td></tr>"; 
			              
	                }else{
	                	document.getElementById('searching').parentNode.innerHTML ="<tr class='dg_tr' id='searching'><td colspan='4'>"  + ajax.status + "</td></tr>"; 
			              
	                }
	            }
	        }
	        ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	        ajax.send(parameters);
	        return;
	    }
   
	
	
	 
}


</SCRIPT>	
			
		   
	            <html:form action="/rpt">
				<html:hidden property="process" value="rptOrders"/>				
	
	               <table ALIGN="CENTER" border="0" style="width: 50%;">
						<TR bgcolor="lightblue">
							<TD colspan="4" align="center" >					 
							 <div class="x-blue_dg_caption"> <bean:message key="label.orders" />   </div>					 
							</TD>
						</TR>
						<tr bgcolor="lightblue">
						   <td></td>
						   <td>Filtro</td>
						   <td>Filtrar</td>
						</tr>
						
						<tr bgcolor="lightblue">
						<td>Fecha</td>
					     <TD>
					     
					         <table>
					         <tr>
					          <td>De</td>
					          <td>  
					                 <html:text property="p1" readonly="true" onclick="javascript:showCal('calendar2');" value="<%=dateFrom %>"> </html:text>
									  <A href="javascript:showCal('calendar2')"><IMG src="/lca/Calendar/calendar.png" border="0" /></A>
									  <SPAN id="date2" style="POSITION: relative"> </SPAN>
								      <FONT COLOR="RED"> <html:errors property="p1"/></FONT>
						     </td>
					          <td>Hasta</td>
					          <td>
					              <html:text property="p2" readonly="true" onclick="javascript:showCal('calendar3');" value="<%=dateTo %>"> </html:text>
								  <A href="javascript:showCal('calendar3')"><IMG src="/lca/Calendar/calendar.png" border="0" /></A>
								  <SPAN id="date3" style="POSITION: relative"> </SPAN>
							      <FONT COLOR="RED"> <html:errors property="p2"/></FONT>
					          </td>
					         </tr>					         
					         </table>
					    </TD>
					    <td>
	
					    </td>
					
					   </tr>
					   
					   
					 <tr bgcolor="lightblue">
						<td>Sucursal Captura</td>
					    <td>
					       					        
					        <html:select property="p3">						
								<logic:iterate id="id" indexId="indexId" name="rptBranchList" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>>
								    	<bean:write name="id" property="name" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>	
							
														  
						<FONT COLOR="RED"> <html:errors property="p3"/></FONT>
						</td>
					    <td>
					     <select name="p5">
					      <OPTION value= '1'>
						    	SI
						    </OPTION>
				    	
						    <OPTION value= '2'>
						    	NO
						    </OPTION>
						 </select>	
					    </td>
					
					 </tr>
					 

					  <tr bgcolor="lightblue">
						<td>Sucursal Entrega</td>
					    <td>
					       					        
					        <html:select property="p6">						
								<logic:iterate id="id" indexId="indexId" name="rptBranchList" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>>
								    	<bean:write name="id" property="name" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>	
							
														  
							<FONT COLOR="RED"> <html:errors property="p6"/></FONT>
						</td>
					    <td>
					     <select name="p7">
					      <OPTION value= '1'>
						    	SI
						    </OPTION>
				    	
						    <OPTION value= '2'>
						    	NO
						    </OPTION>
						 </select>	
					    </td>
					
					 </tr>
					 
					 
						
						<tr>
						<td colspan="3">
						<html:submit>Enviar</html:submit>
						</td>
						</tr>
				</html:form>
				
				
				
  	   
		    
		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	 </logic:present>
	
	
</BODY>

<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

//<![CDATA[
var theForm = document.forms['rptForm'];

function DoRequest( eventArgument,value) {

   
   theForm.process.value = eventArgument;	        
   
   if(eventArgument == 'rptOrder')
	  {	 
	    
	   theForm.p1.value = value; 
	  
	  }
     		                       	           
  

   theForm.submit();
}
//]]>


  function sp_verifyDelete(rid,prm,e){
	if(!e) e = window.event; 
	
	if(rid != 0)
	{
		if(confirm("Esta seguro que quire borrar este regisro?"))
		{
			DoRequest("testDelete",rid);
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
			DoRequest("testDeleteAll",rid);
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
