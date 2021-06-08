<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*,com.bituos.truckAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"

%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nueva Configuración</title>
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
	<H1> <bean:message key="label.tire.new.configuration" />   </H1>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 102;
			    
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
		        
		        
		        int axis_number =0;// = (Integer) ses.getAttribute( "axis_number" );
		        String desc = "";
		       
		        BeanTruckTireConfiguration beanTruckTireConfiguration = (BeanTruckTireConfiguration) ses.getAttribute( "beanTruckTireConfiguration" );
		        if(beanTruckTireConfiguration != null)
		        {
		          axis_number= beanTruckTireConfiguration.getAxis_count();
		          desc = beanTruckTireConfiguration.getDescription();
			      
		        }
		        else
		        {
		        	 List truckTireConfigurationDetailList = (List) ses.getAttribute( "truckTireConfigurationDetailList" );
		        	 
		        }
		 
		         
		        boolean save = (Boolean) ses.getAttribute( "save" );
		        
		        
		        String ctpString = "0";
		        
		        String hmtlConfiguration=(String)ses.getAttribute( "hmtlConfiguration" );
		        
		        boolean axisNumberSet=(Boolean) ses.getAttribute( "axisNumberSet");
		    
		     
		        
			  %>
	
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

<script type="text/javascript">
<!--
function sp__doPostBack(mode,rid,param){

        if(mode == 'save'){ DoRequest( mode,rid);}			     
        else if(mode == 'add')  { DoRequest( mode,rid);}			       
        else if(mode == 'addTirePosition')  { DoRequest( mode,rid);}	
}



//-->
</script>
		
<SCRIPT>	
		function test(tag){
alert(tag.innerText);
   /* if(mode == 'test')
    { 
    	alert(rid);
    }*/
}	  
</SCRIPT>
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
             
             <html:form action="/truckTireConfigurationNew">
			 <html:hidden property="process" value=""/>
			 <html:hidden property="id_truck_tire" value=""/>
		
			 
			 
			 <TABLE border="0" ALIGN="CENTER">
				<TBODY>
						
					<TR>
						<TD><bean:message key="label.configuration.position.description" /></TD>
						<TD><html:text property="description" value="<%=desc%>"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="description"/></FONT> 
					</TR>
					<TR>
						<TD><bean:message key="label.configuration.axis_count" /></TD>
						<TD><html:text property="axis_count" value="<%=String.valueOf(axis_number )%>" readonly="<%=axisNumberSet %>"></html:text></TD>
			        	<TD colspan="4"> <FONT COLOR="RED"> <html:errors property="axis_count"/></FONT> 
					</TR>
	
	            <%	        
	             for(int i =0; i < axis_number; i++)
	             {
	            	 String index = String.valueOf(i);
	             %>
            	       <TR> 
					   <TD><bean:message key="label.configuration.axis.tire.count"/><%=" " + String.valueOf(Integer.valueOf(index) + 1)  %> </TD>							
						<TD>
							
							 							 
							
							<select name="tire_for_axis[<%=index %>]">					
							<logic:iterate id="id" indexId="indexId" name="listTireQuantityAxis"  scope="session" >
							    <OPTION value= <%="'"%><bean:write name="id" property="quantity" /><%="'"%>  
								    <logic:equal name="id" property="id" value="<%=ctpString%>"> 
								    		SELECTED
								    </logic:equal>>
							    	<bean:write name="id" property="quantity" />
							    </OPTION>
					    	</logic:iterate>						
							</select>	
							
							 
							 
							</TD> 
							
							<TD>Es eje Delantero?<TD>
							<TD>	
							
							<select name="axis_type[<%=index %>]">			
							   
							    
							    <% if(axis_number == 1) 
							      {
							    %>
							     <OPTION value="Y" disabled="disabled">
							    	SI
							    </OPTION>
							     <OPTION value="N" selected="selected">
							    	NO
							    </OPTION>	
							    <%} 
							    else
							      {
							    %>	
							     <OPTION value="Y">
							    	SI
							    </OPTION>
							     <OPTION value="N">
							    	NO
							     </OPTION>
							    <%
							      }
							    %>	
							</select>						
						
							</TD> 
							<TD colspan="4"> <FONT COLOR="RED"> <html:errors property="axis_type"/></FONT>
						</TR>
						
						
				     <tr>
					<td>
					<center> 
			  <%
			 
	             }			        	
			     
			     if(axis_number > 1)  
			    	 out.write(hmtlConfiguration);
			     
			 %>
		
			 <%
	        	   

									  
				 
										/*	String ejeDelantero="<tr>" +
									    "<td></td>" +
									    "<td onclick='test(this);' style='background-image:url(./truckimg/tfl.jpg);background-repeat:no-repeat;background-size:22px 60px;'> <center>" + 
									    "<div style='width: 10px; word-wrap: break-word; text-align: center'><font color='#FFFFFF' style='text-'> 11L</font></div></center></td>" +
									    "<td onclick='test(this);' style='margin:0px' ><img src='./truckimg/ejedelantero.jpg' width='75' height='60'/></td>" +
									    "<td onclick='test(this);' style='background-image:url(./truckimg/tfr.jpg);background-repeat:no-repeat;background-size:22px 60px;'> <center>" +
									    "<div style='width: 10px; word-wrap: break-word; text-align: center'><font color='#FFFFFF'> 11R</font></div></center></td>" +
									    "<td></td>" +
									    "</tr>";
	               String ejeTrasero=  "<tr>" +
									    "<td style='background-image:url(./truckimg/tfl.jpg);background-repeat:no-repeat;background-size:22px 60px;'> <center>" + 
									    "<div style='width: 10px; word-wrap: break-word; text-align: center'><font color='#FFFFFF' style='text-'> 11L</font></div></center></td>" +
									    "<td  style='background-image:url(./truckimg/tfl.jpg);background-repeat:no-repeat;background-size:22px 60px;'> <center>" + 
									    "<div style='width: 10px; word-wrap: break-word; text-align: center'><font color='#FFFFFF' style='text-'> 11L</font></div></center></td>" +
									    "<td><img src='./truckimg/ejetrasero.jpg' width='75' height='63'/></td>" +
									    "<td  style='background-image:url(./truckimg/tfr.jpg);background-repeat:no-repeat;background-size:22px 60px;'> <center>" + 
									    "<div style='width: 10px; word-wrap: break-word; text-align: center'><font color='#FFFFFF' style='text-'> 11L</font></div></center></td>" +
									    "<td  style='background-image:url(./truckimg/tfr.jpg);background-repeat:no-repeat;background-size:22px 60px;'> <center>" + 
									    "<div style='width: 10px; word-wrap: break-word; text-align: center'><font color='#FFFFFF' style='text-'> 11L</font></div></center></td>" +
									  "</tr>";*/

			 
			 
			 %>
			 


	               </center>
				  </td>										
				</tr>
				</TBODY>
				</TABLE>


				
				
				
			<CENTER><P>
			
			<% if(save == false)
			  {
			%>
			
			<input type="button" name="save" value="<bean:message key="label.register" />" onclick='javascript:sp__doPostBack("add","","");'> 
			<%
			 
			  }
			else
			{
			%>
			
			<input type="button" name="saveposition" value="<bean:message key="label.configuration.tire.position" />" onclick='javascript:sp__doPostBack("addTirePosition","","");'> 
			<%			 
			  }			
			%>
			
			
			

			</P></CENTER>			
			<CENTER><P><html:button property="finish" onclick="location='/truckAdmin/main.jsp';"> <bean:message key="label.finish" /> </html:button> </P></CENTER>			
			
			
			  <script type="text/javascript">
				//<![CDATA[
				var theForm = document.forms['truckTireConfigurationNewForm'];
				
				function DoRequest( eventArgument,value) {
				
				        
			       theForm.process.value = eventArgument;	        
				           
			       theForm.id_truck_tire.value= value; 		                       	           
		      
			    
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

<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

  //document.getElementsByName("axis_count")[0]="";
  //document.getElementsByName("axis_count")[0].value="";
  
//-->
</SCRIPT>

</html:html>