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

	<title>Movimientos de Neumático </title>
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
    
    <style type="text/css">
.box1 {
	position: absolute;
	margin-left: auto;
	margin-right: auto;
	left:200;
	right:0;
	width:500px;
	height:550px;
	vertical-align: middle;
	/*background:orange;*/
	background-image: url(./truckimg/bg1.jpg);
	border: solid #D64078 3px;
	top: 160px;
	z-index: 10;
	border-radius: 25px;
}
.box2 {
	position: absolute;
	margin-left: 2px;
 
    top: 1px;
	margin-top:0px;
	left:0;
	right:0;
	width:60px;
	height:497px;
	background:d9e3f1;
	border: 1px solid black;
}
.box3 {
	position: absolute;
	margin-left: auto;
	margin-right: auto;
	left:0;
	right:0;
	width:200px;
	height:550px;
 
	background-image: url(./truckimg/bg1.jpg);
	border: solid #D64078 3px;
	top: 160px;
	z-index: 11;
}




#divTrash
{
	/*float:left; */
	width:100%; 
	height:100%;
	/*margin:10px;*/
	/*padding:10px;*/
	border:1px solid #aaaaaa;
	 
}


#divExtra
{
	/*float:left; */
	width:100%; 
	height:100%;
	/*margin:10px;*/
	/*padding:10px;*/
	border:1px solid #aaaaaa;
	 
}

#divAlmacen
{
	/*float:left; */
	width:100%; 
	height:100%;
	/*margin:10px;*/
	/*padding:10px;*/
	border:1px solid #aaaaaa;
	
}
</style>
</HEAD>

<BODY onload="onloadDiv();">

	<H1> <bean:message key="label.tire.movements" />  </H1>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 104;
			    
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
		        
		        
		        String ctsString = (String) ses.getAttribute("ctsString");
		        String ctpString = (String) ses.getAttribute("ctpString");
		        String ctmString = (String) ses.getAttribute("ctmString");
		        String cttString = (String) ses.getAttribute("cttString");
		        
		        String truckHtml = (String) ses.getAttribute("truckHtml");
		        String htmlTableWidth = (String) ses.getAttribute("htmlTableWidth");
		        int i=0;
			  %>
		      
		       <script type="text/javascript">
		       
		    </script>
			<script type="text/javascript">
			<!--
			function sp__doPostBack(mode,rid,param){
	
			        if(mode == 'save'){ DoRequest( mode,rid);}			     
			        else if(mode == 'edit')  { DoRequest( mode,rid);}
			        else if(mode == 'select')  { DoRequest( mode,rid);}
			        else if(mode == 'selectVehicle')  { DoRequest( mode,rid);}
			        else if(mode == 'selectPosition')  { DoRequest( mode,rid);}
			        else if(mode == 'selectExtraTire')  { DoRequest( mode,rid);}
			        else if(mode == 'add')  { DoRequest( mode,rid);}
			        else if(mode == 'selectPlace')  { DoRequest( mode,rid);}
			         
			        
			}
			//-->
			</script>
			
			<SCRIPT>
			var cursorX;
			var cursorY;
			document.onmousemove = function(e){
			    cursorX = e.pageX;
			    cursorY = e.pageY;
			}
			
			function FAjax(url,metodo,parameters,rowidlist)
			{
				
			   var ajax=creaAjax();
			 // alert( rowidlist.toString());
				  
				if(metodo.toUpperCase()=='POST'){
			        ajax.open ('POST', url, true);
			        ajax.onreadystatechange = function() {
			            if (ajax.readyState==1) {
			            	
			            	var rowid = rowidlist.toString();
			            	//alert(document.getElementById("sp_row_0").parentNode);
			            	document.getElementById(rowid + "_row_0").parentNode.innerHTML = "<tr class='dg_tr' id='searching'><td colspan='5' align='center'><img src='./grid/index_data/loading.gif' alt='Buscando...'></td></tr>";
			            	//tirelist
			            	//trucklist
			            	  
							}else if (ajax.readyState==4){
			                if(ajax.status==200){	     
			                	//alert(ajax.responseText);
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
			
			
			
			
			function FAjaxDiv(url,metodo,parameters,rowidlist)
			{
				//alert("Cursor at: " + cursorX + ", " + cursorY);
			   var ajax=creaAjax();
			   // alert( url);
				  
				if(metodo.toUpperCase()=='POST'){
			        ajax.open ('POST', url, true); //false = síncrono  true = asíncrono
			        ajax.onreadystatechange = function() {
			            if (ajax.readyState==1) {
			            	
			            	var rowid = rowidlist.toString();
			            	//alert(document.getElementById("sp_row_0").parentNode);
			            	//document.getElementById(rowid + "_row_0").parentNode.innerHTML = "<tr class='dg_tr' id='searching'><td colspan='5' align='center'><img src='./grid/index_data/loading.gif' alt='Buscando...'></td></tr>";
			            	//tirelist
			            	//trucklist
			            	  
							}else if (ajax.readyState==4){
			                if(ajax.status==200){	     
			                	
			                	//alert(<%=htmlTableWidth%>);
			                	document.getElementById("truckdivTemp").style.width= <%=htmlTableWidth%> + 'px';
			                	document.getElementById("truckdivTemp").style.height=  500 + 'px';;
			                	document.getElementById("truckdivTemp").style.left = (cursorX + 5)+'px';
			                	document.getElementById("truckdivTemp").style.top = (cursorY - 100)+'px';
			                	document.getElementById("truckdivTemp").style.visibility='visible';
			                	document.getElementById("truckdivTemp").innerHTML =ajax.responseText; 
			                	//document.body.innerHTML =ajax.responseText;                      
			                }else if(ajax.status==404){	     		             
			                	//document.getElementById('searching').parentNode.innerHTML ="<tr class='dg_tr' id='searching'><td colspan='4'>La direccion no existe.......</td></tr>"; 
					              
			                }else{
			                	//document.getElementById('searching').parentNode.innerHTML ="<tr class='dg_tr' id='searching'><td colspan='4'>"  + ajax.status + "</td></tr>"; 
					              
			                }
			            }
			        }
			        ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			        ajax.send(parameters);
			        return;
			    }
			   
				
				
				 
			}
			
			</SCRIPT>
			
			
			<script>
			var haveError = false;
			function  MoveTireAjax(url,metodo,parameters)
			{
			     //alert(parameters);
			   /* return;*/
			    
				var ajax=creaAjax();	
			    
				if(metodo.toUpperCase()=='POST'){
			        ajax.open ('POST', url, false);  //false = síncrono  true = asíncrono
			        ajax.onreadystatechange = function() {
			            if (ajax.readyState==1) {
							}else if (ajax.readyState==4){
			                if(ajax.status==200){	     
			        			 //document.getElementById("truckdivTemp").innerHTML =ajax.responseText; 
			                	 var  v= ajax.responseText.trim();
			                	 
			                	// alert(v.length);
			                	 if(v.length != 0)
			                	 {
			                		  
			                		 document.getElementById('errorOnDragg').style.visibility='visible';
				        			 document.getElementById('errorOnDraggMessage').innerHTML=ajax.responseText;
				        			 haveError = true;  
				        			 //alert(haveError);
			                	 }
			                	 else
			                	 {
			                		 document.getElementById('errorOnDragg').style.visibility='hidden';
			                		  
			                		  
			                	 }
			                	  
			        			// alert(v);
			        			 
			        			 
			                	                     
			                }else if(ajax.status==404){	     		             
			                	//document.getElementById('searching').parentNode.innerHTML ="<tr class='dg_tr' id='searching'><td colspan='4'>La direccion no existe.......</td></tr>"; 
			                	alert("La direccion no existe.......");
			                }else{
			                	//document.getElementById('searching').parentNode.innerHTML ="<tr class='dg_tr' id='searching'><td colspan='4'>"  + ajax.status + "</td></tr>"; 
			                	// alert(ajax.status);
			                }
			            }
			        }
			        ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			        ajax.send(parameters);
			        return;
			    }
				
			}
			
			
			 var isMoved = false;
			 var deep;
			 var km_truck;
			 var km_int;
	            
			 function UpdateFields (newDeep, newKm_truck, newKm_int ) {
		            /*
				    var deep = document.getElementsByName("deep")[0];
		            var km_truck = document.getElementsByName ("km_truck")[0];
		            var km_int = document.getElementsByName ("km_int")[0];
		            deep.value = newDeep;
		            km_truck.value = newKm_truck;
		            km_int.value = newKm_int;*/  
				    deep = newDeep;
		            km_truck  = newKm_truck;
		            km_int  = newKm_int;
		            
		        }
		
		        function ShowModal () {
		            var deep = document.getElementsByName("deep")[0];
		            var km_truck = document.getElementsByName("km_truck")[0];
		            var km_int = document.getElementsByName("km_int")[0];
		
		            var sharedObject = {};		             
		            sharedObject.deep = deep.value;
		            sharedObject.km_truck = km_truck.value ;
		            sharedObject.km_int = km_int.value ;
		            
		            
		            if (window.showModalDialog) {
		                var retValue = showModalDialog ("modal.html", sharedObject, "dialogWidth:370px; dialogHeight:200px; dialogLeft:300px;");
		                
		                if (retValue) {
		                    UpdateFields (retValue.deep, retValue.km_truck,retValue.km_int);
		                    isMoved = true;
		                }
		                else
		                {
		                  //alert(retValue);
		                	isMoved = false;
		                }
		            }
		            else {
		                    // for similar functionality in Opera, but it's not modal!
		                var modal = window.open ("modal.html", null, "width=200,height=200,left=300,modal=yes,alwaysRaised=yes", null);
		                modal.dialogArguments = sharedObject;
		            }
		        }
		        
		        
			function allowDrop(ev) {
			    ev.preventDefault();
			}
			
			function drag(ev) {	
				
				ev.dataTransfer.setData("text", ev.target.id);
				
			}
			
			var font = "";
			function drop(ev) {
			    ev.preventDefault();			 			  
				var data = ev.dataTransfer.getData("text");
				
				var dataHTML = document.getElementById(data);
				var x = dataHTML.getElementsByTagName("center");
				
				
				 htmlDIV= dataHTML.innerHTML;
				 //alert(htmlDIV);
				  //alert(dataHTML.innerHTML.onclick);	
				 
				 /*var elem = dataHTML.parentNode;
			     if (typeof elem.onclick == "function") {
						elem.onclick.apply(elem);
				  }		*/	
				


				if(dataHTML.parentNode.id == ev.target.id)
				{
				   alert('mismo origen mismo destino');
				}else if(dataHTML.parentNode.id == 'divTrash' &&  ev.target.id == 'divAlmacen')
				{
				  alert('Esta sacando de la basura a el almacen');
				}else if(dataHTML.parentNode.id == 'divAlmacen' &&  ev.target.id == 'divTrash')
				{
				  alert('Esta moviendo a la basura desde el almacen');
				}else if(dataHTML.parentNode.id == 'divExtra' &&  ev.target.id == 'divAlmacen')
				{
				  alert('Esta sacando de la extra a el almacen');
				}else if(dataHTML.parentNode.id == 'divAlmacen' &&  ev.target.id == 'divExtra')
				{
				  alert('Esta moviendo a la elmacen desde a extra');
				}
				else if(dataHTML.parentNode.id == 'divTrash' &&  ev.target.id == 'divExtra')
				{
				  alert('Esta sacando de la basura');
				}
				else if(dataHTML.parentNode.id == 'divExtra' &&  ev.target.id == 'divTrash')
				{
				  alert('Esta moviendo a la basura');
				}else{

					var parenteNode = dataHTML.parentNode;  
					
					if(ev.target.id == 'divTrash'  || ev.target.id == 'divExtra' || ev.target.id == 'divAlmacen')	
					{     
					   var url = parenteNode.style.backgroundImage;
					   var data_truck_tire_cong_detail_id_target_origin = parenteNode.firstChild.getAttribute("data-truck-tire-cong-detail-id");
				       var data_id_tire = parenteNode.firstChild.getAttribute("data-id-tire");
				       
				       ShowModal();	 
				       if(isMoved != true)
				         return;
					       
					   var active ="";   
					   if(ev.target.id == 'divAlmacen') {
						  dataHTML.id = 'almacenTire' +  data_id_tire;
						 // alert(dataHTML.parentNode.innerHTML);
						  //data_truck_tire_cong_detail_id_target = 0; //posicion ninguna
						  active="Y";
						  MoveTireAjax("dataDragg.jsp","POST","id_tire=" +data_id_tire+ "&truck_tire_cong_detail_id_origin=" + data_truck_tire_cong_detail_id_target_origin + "&targetPosition=" + data_truck_tire_cong_detail_id_target +"&targetPlace=1&active="+active+"&deep="+deep+"&km_truck="+km_truck+"&km_int="+km_int);
	
					    }
					  
					  
					    if(ev.target.id == 'divTrash'){
						  dataHTML.id = 'trashTire' +  data_id_tire;
						  data_truck_tire_cong_detail_id_target = 0; //posicion ninguna
						 // alert(dataHTML.parentNode.innerHTML);
						  //MoveTireAjax('dataDragg.jsp','POST','id_tire=' +data_id_tire+ '&truck_tire_cong_detail_id=' + data_truck_tire_cong_detail_id + '&targetPosition=&targetPlace=6');
						  active="Y";
						  MoveTireAjax("dataDragg.jsp","POST","id_tire=" +data_id_tire+ "&truck_tire_cong_detail_id_origin=" + data_truck_tire_cong_detail_id_target_origin + "&targetPosition=" + data_truck_tire_cong_detail_id_target +"&targetPlace=6&active="+active+"&deep="+deep+"&km_truck="+km_truck+"&km_int="+km_int);
							
					    }
						   
					  
					  
					    if(ev.target.id == 'divExtra'){					    	
						  dataHTML.id = 'extraTire' +  data_id_tire; 						  
						  data_truck_tire_cong_detail_id_target = 0; //posicion ninguna
						   dataHTML.setAttribute("data-truck-tire-cong-detail-id",data_truck_tire_cong_detail_id_target) ;
						   //alert(dataHTML.parentNode.innerHTML);
						   active="Y";
						  MoveTireAjax("dataDragg.jsp","POST","id_tire=" +data_id_tire+ "&truck_tire_cong_detail_id_origin=" + data_truck_tire_cong_detail_id_target_origin + "&targetPosition=" + data_truck_tire_cong_detail_id_target +"&targetPlace=4&active="+active+"&deep="+deep+"&km_truck="+km_truck+"&km_int="+km_int);
							
						  
					    }
						  
					    if (!haveError)
				          { 
						     if(parenteNode.style.backgroundImage.slice(4, -1) == '"./truckimg/tfrselected.jpg"'){	 
							    parenteNode.style.backgroundImage  = "url('./truckimg/tfr.png')";				 
						      }
						     else if(parenteNode.style.backgroundImage.slice(4, -1) == '"./truckimg/tflselected.jpg"'){
						        parenteNode.style.backgroundImage  = "url('./truckimg/tfl.png')";	
					         }
						   
					         var x2 = parenteNode.getElementsByTagName("center");	
					         
					         
	
					         parenteNode.innerHTML = "<div style='border: 0px solid #a1a1a1; height: 60px;' draggable='false' ondragstart='drag(event)' data-truck-tire-cong-detail-id='" +data_truck_tire_cong_detail_id_target_origin+"'  data-id-tire='' id='draggabletire" +data_id_tire+"' >" +
						      						 "<center><font size='4' color='white'>" +
						      						 x2[0].getElementsByTagName("font")[0].innerHTML +				                                   
						                             "</font></center>"+
						                             "</div>";
						                            
						   
	
						    var elem = parenteNode;
						    if (typeof elem.onclick == "function") {
								//elem.onclick.apply(elem);
						    }
						   
						    dataHTML.innerHTML=  htmlDIV;
						   //alert( dataHTML.id);
						 
						   dataHTML.firstChild.firstChild.innerHTML = "";
						    
						   
					  		dataHTML.style.backgroundImage  = "url('./truckimg/extra.png')";						  
					  		ev.target.appendChild(dataHTML);
				        }
					 // alert(ev.target.id);
					  //alert( ev.target.parentNode.innerHTML);
	
	
					}
					 else
					 { 
						  
						 
						 if(dataHTML.parentNode.id == 'divTrash'  || dataHTML.parentNode.id ==  'divExtra' || dataHTML.parentNode.id == 'divAlmacen')	
						  {      //alert(ev.target.getAttribute("data-id-tire"));
							 if( ev.target.getAttribute("data-id-tire") == '')
							   {
								 
								 var data_truck_tire_cong_detail_id_target = ev.target.parentNode.firstChild.getAttribute("data-truck-tire-cong-detail-id");
							     var data_id_tire = dataHTML.getAttribute("data-id-tire");
							     var data_truck_tire_cong_detail_id_target_origin = dataHTML.getAttribute("data-truck-tire-cong-detail-id");
							    
							    // alert(data_truck_tire_cong_detail_id_target);
								  //alert(data_truck_tire_cong_detail_id_target_origin);							     
							     
								 
								 ShowModal();	 
							     if(isMoved != true)
							       return;
								
							     
							      
							      var active = 'N';	 
							     
							      MoveTireAjax("dataDragg.jsp","POST","id_tire=" +data_id_tire+ "&truck_tire_cong_detail_id_origin=" + data_truck_tire_cong_detail_id_target_origin + "&targetPosition=" + data_truck_tire_cong_detail_id_target +"&targetPlace=3&active="+active+"&deep="+deep+"&km_truck="+km_truck+"&km_int="+km_int);
							    
							     
							     if (!haveError)
							     {
									 
									 ev.target.setAttribute("data-id-tire", dataHTML.getAttribute("data-id-tire"));					
									  //alert(ev.target.parentNode.innerHTML);
									 dataHTML.parentNode.removeChild(dataHTML);
							
									 
								     if(ev.target.parentNode.style.backgroundImage.slice(4, -1) == '"./truckimg/tfr.png"')						   
								       {	
									     ev.target.style.backgroundImage  = "url('./truckimg/tfrselected.jpg')";
								       }
								     else  if(ev.target.parentNode.style.backgroundImage.slice(4, -1) == '"./truckimg/tfl.png"')
								       { 
									     ev.target.style.backgroundImage  = "url('./truckimg/tflselected.jpg')";
							           }
								  
								     ev.target.draggable = true;  
							        //alert(ev.target.parentNode.innerHTML);
							     }
							   }
							 else
							  { 
								 alert("Esta posiscion tiene asignado un neumatico");
							  }
						   //alert(  ev.target.innerHTML);
						  }
					    else
					      { 
					    	//confirm("ADVERTENCIA: Si realiza esta operación, algunos registros serán "+"afectados irremediablemente."+Char(13)+"¿Que quiere hacer?","NO continuar","Continuar");
					    	//confirm("ADVERTENCIA: Si realiza esta operación, algunos registros serán "+"afectados. \n" + "¿Desea Continuar?");
					    	 // prompt("Introduzca su nombre:","");
					    
					    	ShowModal();	 
					        if(isMoved != true)
					        	return;
					       
							  
					   
					    	
					    	 if(ev.target.firstChild.getAttribute("data-id-tire") == '' || ev.target.firstChild.getAttribute("data-id-tire") == null)
							  { 
					    		 var data_truck_tire_cong_detail_id_target = ev.target.firstChild.getAttribute("data-truck-tire-cong-detail-id");
							     var data_id_tire = dataHTML.getAttribute("data-id-tire");
							     var data_truck_tire_cong_detail_id_target_origin = dataHTML.getAttribute("data-truck-tire-cong-detail-id");
							    // alert(data_id_tire); //id tire
							    // alert(dataHTML.getAttribute("data-truck-tire-cong-detail-id")); //posision donde estaba							     
							     //alert(data_truck_tire_cong_detail_id);//posision a donde se pondra
							     var active ='Y';
							     MoveTireAjax("dataDragg.jsp","POST","id_tire=" +data_id_tire+ "&truck_tire_cong_detail_id_origin=" + data_truck_tire_cong_detail_id_target_origin + "&targetPosition=" + data_truck_tire_cong_detail_id_target +"&targetPlace=3&active="+active+"&deep="+deep+"&km_truck="+km_truck+"&km_int="+km_int);
       
							     if (!haveError)
							     {
  
						    		 ev.target.firstChild.setAttribute("data-id-tire", dataHTML.getAttribute("data-id-tire"));
									 dataHTML.setAttribute("data-id-tire",null);
									 ev.target.firstChild.draggable = true;
									 dataHTML.draggable = false;
									  //alert(dataHTML.parentNode.id);
					
									 var url = ev.target.style.backgroundImage;
									  //alert(url.substring(4, url.length-1)); 
									  var url2 = dataHTML.parentNode.style.backgroundImage;
									  //alert(dataHTML.parentNode.style.backgroundImage.slice(4, -1)=='"./truckimg/tfrselected.jpg"');
									  //alert(dataHTML.parentNode.style.backgroundImage=="url('./truckimg/tfrselected.jpg')");
									   
								     //if(url.substring(4, url.length-1) == '"./truckimg/tfr.png"')
								     if( ev.target.style.backgroundImage.slice(4, -1)=='"./truckimg/tfr.png"')
								       {	 
									     ev.target.style.backgroundImage  = "url('./truckimg/tfrselected.jpg')";
									     
									     if(dataHTML.parentNode.style.backgroundImage.slice(4, -1)=='"./truckimg/tfrselected.jpg"')	
									     {
									        dataHTML.parentNode.style.backgroundImage  = "url('./truckimg/tfr.png')";
									     }
									     else
									     {
									    	 dataHTML.parentNode.style.backgroundImage  = "url('./truckimg/tfl.png')"; 
									     }
								       }
								     else  if(ev.target.style.backgroundImage.slice(4, -1) == '"./truckimg/tfl.png"')
								       {
									     ev.target.style.backgroundImage  = "url('./truckimg/tflselected.jpg')";
									     
									     if(dataHTML.parentNode.style.backgroundImage.slice(4, -1)=='"./truckimg/tflselected.jpg"')	
									     {
									        dataHTML.parentNode.style.backgroundImage  = "url('./truckimg/tfl.png')";
									     }
									     else
									     {
									    	 dataHTML.parentNode.style.backgroundImage  = "url('./truckimg/tfr.png')"; 
									     }
									     
									     
									     
									     
							           }
							     }
							      
						      //alert(ev.target.innerHTML);
						      //alert(dataHTML.parentNode.innerHTML);
							   }
							 else
							  {
								 alert("Esta posiscion tiene asignado un neumatico");
							  } 
					 
					      }
					   
					 }
					
					
					
				}
				
				 
			    
				
				
			    //alert(parenteNode.innerHTML);
		

			}
			
			/*function sp__doPostBack(v1, v2,v3)
			{
				alert("click");
			}
			*/
			
			</script>
            
			<div id='truckdivTemp'  class='box3' style="visibility: hidden;">
			</div>
			
			 
			
			<div id='errorOnDragg' style="position: absolute; left: 50%; visibility: hidden;">
		        <div id='errorOnDraggMessage' style="position: relative; left: -50%; border: dotted red 1px; background-color: yellow;">
		             
		        </div>
		        <input type="button" value="Cerrar" onclick="this.parentNode.style.visibility='hidden';">
		    </div>
		    
			<html:form action="/tireMovements" enctype="multipart/form-data">
             <html:hidden property="process" value=""/>
			 <html:hidden property="id_truck_tire_configuration" value=""/>
			 <html:hidden property="id_truck_tire_configuration_detail" value=""/>
			 <html:hidden property="id_tire" value=""/>
			 <html:hidden property="id_place" value=""/>
			 <html:hidden property="id_truck" value=""/>
			 
			 
			
			<table ALIGN="CENTER" border="0">
			
			<TR bgcolor="lightblue">
			<td colspan="8" align="center" >					 
			 <div class="x-blue_dg_caption"> <bean:message key="label.tire.instock" />   </div>					 
			</td>
			</TR>
			
			<tr><td colspan="8">
			  <br>
			</td>
			</tr>
		     <tr>
			  <td colspan="8">

			
			
			
			
				<table dir="ltr" id="sp__contentTable" class="x-blue_dg_table" style="margin-left:auto;margin-right:auto;" align="center" width="100%">
				<thead>
				<tr class="dg_tr" style="" id="sp_row_h1">
		
				<th style="background-color:#fcfaf6;width:6%;" bgcolor="#fcfaf6"></th>
				<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search1" value=""  onkeyup="FAjax('data.jsp','POST','list=beanTireList&field=design&value='+ this.value+ '&bean=BeanTire&module=tireMovements&&active=Y','tirelist');"/>	</b> </a></th>
				<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search2" value=""  onkeyup="FAjax('data.jsp','POST','list=beanTireList&field=deep&value='+ this.value+ '&bean=BeanTire&module=tireMovements&active=Y','tirelist');"/></b> </a></th>
				<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search3" value=""  onkeyup="FAjax('data.jsp','POST','list=beanTireList&field=id_type_unit_of_measure.description&value='+ this.value+ '&bean=BeanTire&module=tireMovements&active=Y','tirelist');"/></b> </a></th>
				<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search1" value=""  onkeyup="FAjax('data.jsp','POST','list=beanTireList&field=id_type_measure.description&value='+ this.value+ '&bean=BeanTire&module=tireMovements&active=Y','tirelist');"/>	</b> </a></th>
				<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search2" value=""  onkeyup="FAjax('data.jsp','POST','list=beanTireList&field=id_type_tire_status.description&value='+ this.value+ '&bean=BeanTire&module=tireMovements&active=Y','tirelist');"/></b> </a></th>
			    <th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search2" value=""  onkeyup="FAjax('data.jsp','POST','list=beanTireList&field=serial_number&value='+ this.value+ '&bean=BeanTire&module=tireMovements&active=Y','tirelist');"/></b> </a></th>
		
			
				</tr>
				
				
				<tr class="dg_tr" style="" id="sp_row_h2" onclick="onMouseClickRow('sp_','','#fdfde7','#ffffff','#F7F9FB');" onmouseover="onMouseOverRow('sp_','','#fdfde7','#f9f9e3');" onmouseout="onMouseOutRow('sp_','','','#fdfde7');">
				<th class="x-blue_dg_th_normal dg_center dg_nowrap" style="background-color:#fcfaf6;width:2%;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"></th>
				<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=2&amp;sp_sort_field_by=&amp;sp_sort_field_type=string&amp;sp_sort_type=asc');" title="Sort"><b>Diseño</b> </a></th>
				<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=4&amp;sp_sort_field_by=&amp;sp_sort_field_type=&amp;sp_sort_type=desc');" title="Sort"><b>Profundidad</b> </a></th>
							<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=5&amp;sp_sort_field_by=&amp;sp_sort_field_type=&amp;sp_sort_type=desc');" title="Sort"><b>Unidad Medida</b> </a></th>
				<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=5&amp;sp_sort_field_by=&amp;sp_sort_field_type=&amp;sp_sort_type=desc');" title="Sort"><b>Tipo de Medida</b> </a></th>
	
				<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=5&amp;sp_sort_field_by=&amp;sp_sort_field_type=&amp;sp_sort_type=desc');" title="Sort"><b>Estatus</b> </a></th>
		        <th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=5&amp;sp_sort_field_by=&amp;sp_sort_field_type=&amp;sp_sort_type=desc');" title="Sort"><b>Numero de Serie</b> </a></th>
			     

				
				</tr>
				</thead>
							
				<tbody>
	
				<% String cuStyleBackground="rgb(255, 255, 255)";
				   String color;
				   //int i=0;
				%>
				<logic:iterate id="id" indexId="indexId" name="beanTireList" scope="session" >
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
				<tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="tirelist_row_<%=i%>" 
				onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
				onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
				onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
				<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:sp__doPostBack("select","<bean:write name="id" property="id" />","");' title="Select record">Seleccionar</a></td>
				
				<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="design" /></label></td>
				<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="deep" /></label></td>
				<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="id_type_unit_of_measure.description" /></label></td>
				 <td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="id_type_measure.description" /></label></td>
				<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="id_type_tire_status.description" /></label></td>
	            <td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="serial_number" /></label></td>	
			    
			    
			
				
				</tr>
				
				<%  
				 i++;
				%>
				</logic:iterate>
				
				
				
				</tbody>
				
				</table>
			
			
			 </td>
			 </tr>
			 
		
			<tr>
		       <td colspan="8">
				  <br>
				</td>
			</tr>
					
					
			<logic:present name="selectedBeanTire" scope="session">
			
			<TR bgcolor="lightblue">
			<logic:notPresent name="beanTruckList" scope="session"	>
			  <td colspan="8" align="center" >					 
			 <div class="x-blue_dg_caption"> Neumático    </div>					 
			  </td>
			</logic:notPresent>
			
			<logic:present name="beanTruckList" scope="session">
			
			<td colspan="4" align="center" >					 
			 <div class="x-blue_dg_caption"> Neumático    </div>					 
			</td>
			
			<td colspan="4" align="center" >					 
			 <div class="x-blue_dg_caption"> Vehículos     </div>					 
			</td>
			
			</logic:present>
			
			</TR>
			
			<tr>
		       <td colspan="8">
				  <br>
				</td>
			</tr>
			
			
			<TR>
				<TD colspan="4" ><bean:message key="label.tire.design" />
				<html:text property="design" size="60"></html:text>
				 <FONT COLOR="RED"> <html:errors property="design"/>
				 </TD>
				 <TD colspan="4" rowspan="11" width="60%" valign="top">
						 <logic:present name="beanTruckList" scope="session">
						    <table dir="ltr" id="sp__contentTable" class="x-blue_dg_table" style="margin-left:auto;margin-right:auto;" align="center" width="100%" >
								<thead>
								<tr class="dg_tr" style="" id="sp_row_h1">
						
								<th style="background-color:#fcfaf6;width:11%;" bgcolor="#fcfaf6"></th>
								<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search1" value=""  onkeyup="FAjax('data.jsp','POST','list=beanTruckList&field=description&value='+ this.value+ '&bean=BeanTruck&module=truckSelectMovements&active=Y','trucklist');"/>	</b> </a></th>
								<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search2" value=""  onkeyup="FAjax('data.jsp','POST','list=beanTruckList&field=registration&value='+ this.value+ '&bean=BeanTruck&module=truckSelectMovements&active=Y','trucklist');"/></b> </a></th>
								<th class="x-blue_dg_th dg_center dg_nowrap" style="background-color:#fcfaf6;width:150px;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');"  title="Sort"><b><input title="Teclee aqui para buscar" type="text" id="search2" value=""  onkeyup="FAjax('data.jsp','POST','list=beanTruckList&field=model&value='+ this.value+ '&bean=BeanTruck&module=truckSelectMovements&active=Y','trucklist');"/></b> </a></th>

								</tr>
								
								
								<tr class="dg_tr" style="width:11%;" id="sp_row_h2" onclick="onMouseClickRow('sp_','','#fdfde7','#ffffff','#F7F9FB');" onmouseover="onMouseOverRow('sp_','','#fdfde7','#f9f9e3');" onmouseout="onMouseOutRow('sp_','','','#fdfde7');">
								<th class="x-blue_dg_th_normal dg_center dg_nowrap" style="background-color:#fcfaf6;width:2%;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"></th>
								<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=2&amp;sp_sort_field_by=&amp;sp_sort_field_type=string&amp;sp_sort_type=asc');" title="Sort"><b>Descripcion</b> </a></th>
								<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=2&amp;sp_sort_field_by=&amp;sp_sort_field_type=string&amp;sp_sort_type=asc');" title="Sort"><b>Registro</b> </a></th>
								<th class="x-blue_dg_th dg_left" style="background-color:#fcfaf6;" onmouseover="bgColor='#e4ecf7';" onmouseout="bgColor='#fcfaf6';" bgcolor="#fcfaf6"><a class="x-blue_dg_a_header" href="javascript:void('sort');" onclick="javascript:sp__doPostBack('sort','','&amp;sp_page_size=15&amp;sp_p=1&amp;sp_sort_field=2&amp;sp_sort_field_by=&amp;sp_sort_field_type=string&amp;sp_sort_type=asc');" title="Sort"><b>Modelo</b> </a></th>
								</tr>
								</thead>
											
								<tbody>
								
									<% cuStyleBackground="rgb(255, 255, 255)";
									   color ="";
									   i=0;
									%>
										<logic:iterate id="id" indexId="indexId" name="beanTruckList" scope="session" >
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
										<tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="trucklist_row_<%=i%>" 
										onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
										onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
										onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
										<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" onmouseover='FAjaxDiv("dataConfiguration.jsp","POST","idtruck=<bean:write name="id" property="id" />","tirelist");' onmouseout='mout();' href='javascript:sp__doPostBack("selectVehicle","<bean:write name="id" property="id" />","");' title="Edit record">Seleccionar</a></td>
										
										<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="description" /></label></td>
										<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="registration" /></label></td>
										<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="model" /></label></td>
						
										
										</tr>
										
										<%  
										 i++;
										%>
										</logic:iterate>
								
								
							
								
								</tbody>
								
								</table>
			
						 </logic:present>
						 
						 
						 
						 <%-- <logic:notPresent name="beanTruckList" scope="session">
						 <FONT COLOR="RED"> No hay vehículo registrados </FONT> 
						 </logic:notPresent> --%>
				</TD>	
			</TR>
			
			<TR>
			     <TD colspan="4"> 
				    <bean:message key="label.tire.deep" />
				    <html:text property="deep" size="10"></html:text>
				    <html:text property="id_type_unit_of_measure_description" size="16" disabled="true"></html:text>				    
				    <FONT COLOR="RED"> <html:errors property="deep"/></FONT> 
			     </TD>
			</TR>
			
			<%BeanTypeMeasure beanTypeMeasure = (BeanTypeMeasure) ses.getAttribute( "beanTypeMeasure" );		     %>
			<TR>
				<TD colspan="4"><bean:message key="label.tire.measure" />
			   		<html:select property="id_type_measure">						
					<logic:iterate id="id" indexId="indexId" name="beanTypeMeasureList" scope="session" >
					    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
						    <logic:equal name="id" property="id" value="<%=String.valueOf(beanTypeMeasure.getId())%>"> 
						    		SELECTED
						    </logic:equal>>
					    	<bean:write name="id" property="description" />
					    </OPTION>
			    	</logic:iterate>						
					</html:select>						
				    <FONT COLOR="RED"> <html:errors property="id_type_measure"/></FONT> 
                </TD>
	        	  
			 </TR>
			
			
			<%BeanTypePlace beanTypePlace = (BeanTypePlace) ses.getAttribute( "beanTypePlace" );		     %>
			<TR>
				<TD colspan="4"><bean:message key="label.tire.place" />
			   		<html:select property="id_type_place" onchange="javascript:sp__doPostBack('selectPlace',this.value,'');">						
					<logic:iterate id="id" indexId="indexId" name="beanTypePlaceList" scope="session" >
					    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
						    <logic:equal name="id" property="id" value="<%=String.valueOf(beanTypePlace.getId())%>"> 
						    		SELECTED
						    </logic:equal>>
					    	<bean:write name="id" property="description" />
					    </OPTION>
			    	</logic:iterate>						
					</html:select>						
				    <FONT COLOR="RED"> <html:errors property="id_type_place"/></FONT> 
                </TD>
	        	  
			 </TR>
			 
			 <%BeanTypeTireStatus beanTypeTireStatus = (BeanTypeTireStatus) ses.getAttribute( "beanTypeTireStatus" );	%>
			 <TR>
					<TD colspan="4"><bean:message key="label.tire.status" />
				   		<html:select property="id_type_tire_status" disabled="true">						
						<logic:iterate id="id" indexId="indexId" name="listTypeTireStatus" scope="session" >
						    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
							    <logic:equal name="id" property="id" value="<%=String.valueOf(beanTypeTireStatus.getId())%>"> 
							    		SELECTED
							    </logic:equal>>
						    	<bean:write name="id" property="description" />
						    </OPTION>
				    	</logic:iterate>						
						</html:select>						
					    
                       <FONT COLOR="RED"> <html:errors property="id_type_tire_status"/></FONT>
		        	</TD>  
			</TR>
			
			<TR>
				<TD colspan="4"><bean:message key="label.tire.serial_number" />
				<html:text property="serial_number" size="40" disabled="true"></html:text>
				<FONT COLOR="RED"> <html:errors property="serial_number"/></FONT> 
				</TD>
			</TR>
			
			<TR>
				<TD colspan="4"><bean:message key="label.tire.km_int" />
				<html:text property="km_int" size="15"></html:text>
				<FONT COLOR="RED"> <html:errors property="km_int"/></FONT> 
				</TD>
			</TR>
			<TR>
				<TD colspan="4"><bean:message key="label.tire.km_truck" />
				<html:text property="km_truck" size="15"></html:text>
				<FONT COLOR="RED"> <html:errors property="km_truck"/></FONT> 
				</TD>
			</TR>
			
			<TR>
				<TD colspan="4"><bean:message key="label.tire.price" />
				<html:text property="price" size="15"></html:text>
				<FONT COLOR="RED"> <html:errors property="price"/></FONT> 
				</TD>
			</TR>
			<TR>
				<TD colspan="4"><bean:message key="label.tire.measurementMax" />
				<html:text property="measurement" size="15"></html:text>
				<FONT COLOR="RED"> <html:errors property="measurement"/></FONT> 
				</TD>
			</TR>
			
			<TR>
				<TD colspan="4"><bean:message key="label.tire.priceReference" /> </br>
				<html:textarea property="buy_of_reference" cols="50" rows="10" ></html:textarea>
				<FONT COLOR="RED"> <html:errors property="buy_of_reference"/></FONT> 
				</TD>
			</TR>
			
			
				
			
			
			
			</logic:present>
			
			<logic:present name="truckHtml" scope="session">
				<TR>
				<TD colspan="8" valign="top" align="center">
				
				
				<%=truckHtml%>
				</TD>					
				</TR>
			 </logic:present>
			 </table>
			
			
			<CENTER><P>
			<input type="button" name="add" value="<bean:message key="label.register" />" onclick='javascript:sp__doPostBack("add","","");'> 
			
			

			</P></CENTER>			
			<CENTER><P><html:button property="finish" onclick="location='/truckAdmin/main.jsp';"> <bean:message key="label.finish" /> </html:button> </P></CENTER>			
			
			<script type="text/javascript">
				//<![CDATA[
				var theForm = document.forms['tireMovementsForm'];
				
				function DoRequest( eventArgument,value) {
				
					    
			       theForm.process.value = eventArgument;	        
				      
			     // alert();
			       theForm.id_tire.value= value; 
			       
			       theForm.id_truck.value= value; 	
			       
			       if (eventArgument == 'selectPosition')
			    	{
			           theForm.id_truck_tire_configuration_detail.value= value;	
			           var v=value.toString();
			           
			        	   closeDiv(); 
			        
			    	}
			       
			       if (eventArgument == 'selectPlace')
			    	{
			    	   theForm.process.value = eventArgument;
					   theForm.id_place.value=value;
					   //alert(theForm.id_place.value);
			    	}
			       
			       
			       if (eventArgument == 'selectExtraTire')
			    	{
			           theForm.id_truck_tire_configuration_detail.value= value;	
			           var v=value.toString();
			           //alert(document.getElementById("tire"+ v ).id);
			           closeDiv(); 
			    	}
			       
			    
			     theForm.submit();
				}
				//]]>
				
				function selectPlace(eventArgument,myselect)
				{
					theForm.process.value = eventArgument;
					theForm.id_place.value=myselect.options[myselect.selectedIndex].value;
					//alert(theForm.id_place.value);
					
					theForm.submit();
					
				}
				
				function btnClose()
				{
					/*if(confirm("Este Neumáticos quedara registrado como Extra para este Vehículo"))
			          {
			        	   closeDiv(); 
			          }
			          else
			          {			           	 
			           	false;
			          }	*/
					 closeDiv(); 
				}
				
				function closeDiv() 
				{
					
				    var div = document.getElementById('truckdiv');
				    
				    if (div.style.display !== 'none') {
				    	div.style.display = 'none';
				    }
				    else {
				    	div.style.display = 'block';
				    }
				}
				
				
				function selectTire(value)
				{
				/*"var frm = document.forms['tireMovementsForm'];"+*/
				  //alert(value);
				  //alert(document.getElementById('tireExtra9'));
				  //document.getElementById('tireExtra9').bgColor  = 'Green';
				  //alert(document.getElementById('tireExtra9').bgColor);
				}
				function mover(value)
				{
					//FAjaxDiv(url,metodo,parameters,rowidlist);
					//alert(value);
				}
				function mout(value)
				{
					//alert(value);
					document.getElementById("truckdivTemp").style.visibility='hidden'
				}
				
				function onloadDiv()
				{
					//alert();

					
					<% 
					String[] tires= (String[]) ses.getAttribute("tires");
					String[] sides= (String[]) ses.getAttribute("sides");
					if(tires !=null && tires !=null)
					{
					  for(int x=0; x < tires.length; x++)
					  {
					%>
					    var tdselected= "<%= tires[x]%>";
					    var side= "<%= sides[x]%>";
					    var draggableselected= "draggable" + tdselected;
					    //alert(draggableselected);
					    
					    if(side == "L" )
					    {
					     document.getElementById(tdselected).style.backgroundImage = "url(./truckimg/tflselected.jpg)";
					     document.getElementById(draggableselected).draggable = 'true';
					    // alert(document.getElementById(draggableselected).draggable);
					    }else if(side == "R" )
					    {
					      document.getElementById(tdselected).style.backgroundImage = "url(./truckimg/tfrselected.jpg)";
					      document.getElementById(draggableselected).draggable = 'true';
					      //alert(document.getElementById(draggableselected).draggable);
					    }
					    else if(side == "NA" )
					    {
					    	document.getElementById(tdselected).bgColor = 'green';
					    	
					    	//alert(document.getElementById(draggableselected).draggable);
					    	document.getElementById(draggableselected).draggable = 'true';
					    	//alert(document.getElementById(draggableselected).draggable);
					    	
					    }
					<% 
					  }
					}
					%>
					
					//document.getElementById("tireExtra9").bgColor = 'Green';

					   
				
				}
				
	
				
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
	
</BODY>

<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

  document.getElementById("design").focus();
  
//-->
</SCRIPT>

</html:html>
