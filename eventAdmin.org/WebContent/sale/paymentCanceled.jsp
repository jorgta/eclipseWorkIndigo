<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*, com.eventAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"

%>




<html:html>
<HEAD>

	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM WebSphere Studio">

	<META http-equiv="Content-Style-Type" content="text/css">
	<LINK href="/eventAdmin/theme/Master.css" rel="stylesheet" type="text/css">
	<link href="/eventAdmin/GridViewCSSThemes/YahooGridView.css" rel="stylesheet" type="text/css" />
	<SCRIPT type="text/javascript" src="/eventAdmin/GridViewCSSThemes/js/ajaxfile.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="ingridTheme/jquery.js"></SCRIPT>
    <TITLE>
    </TITLE>
</HEAD>
<SCRIPT language="JavaScript" src="/eventAdmin/getSystemDate.js"></SCRIPT>
<SCRIPT language="Javascript" src="/eventAdmin/Calendar/calendar.js"></SCRIPT>
<SCRIPT language="Javascript" src="/eventAdmin/Calendar/mycalendar.js"></SCRIPT>
<BODY>

	<CENTER><H1> <bean:message key="label.sale.paymenCanceled" />  </H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 44;
			    
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
		        
		        

		         
		         
		       
		         BeanSale currentSale = (BeanSale) ses.getAttribute( "currentSale" );
		         String total = (String) ses.getAttribute( "total" );
				
				
			         
		        int counter =0;
		        String clase ="";
		        
		        
		        
			  %>
			  
			  
<SCRIPT>				  
function FAjax(url,metodo,valor,parameters)
{
   
   var val= trim(valor);
   
   var empty = (val.length == 0); 
    
   /*if(!empty)
   {*/   
      
	   var inputs = document.getElementsByTagName('input');
	
		for(var i=0; i<inputs.length; i++){
			if(inputs[i].getAttribute('type')=='radio'){
			   if(inputs[i].checked)	
			   {
			     parameters =parameters + '&searchParam=' + inputs[i].value;
			    // alert(parameters);
			   }
			
			}
		} 
		
		  
		  
	   
	  // alert(parameters);
	   var ajax=creaAjax();
	  
	   if (metodo.toUpperCase()=='GET'){
	    document.getElementById('capa').style.visibility= "visible";
	    ajax.open ('GET', url, true);
	    ajax.onreadystatechange = function() {
	         if (ajax.readyState==1) {
	                 document.getElementById('capa').innerHTML="Buscando.......";
	         }
	         else if (ajax.readyState==4){
	            if(ajax.status==200){ 
	                 document.getElementById('capa').innerHTML=ajax.responseText; 
	            }
	            else if(ajax.status==404)
	                 {
	
	                     document.getElementById('capa').innerHTML = "La direccion existe";
	                    
	                 }
	                 else
	                 {
	                     document.getElementById('capa').innerHTML = "Error: ".ajax.status;
	                     
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
	                        insertData("<tr class='SelectedRowStyle'><td colspan='5'>Buscando.......</td></tr> ");
	                
	             }else if (ajax.readyState==4){
	                if(ajax.status==200){
	            
	                  //insertData(ajax.responseText);
	                    //document.getElementById('capa').innerHTML=ajax.responseText;
	                   insertData(ajax.responseText);
	                    
	                    
	                }else if(ajax.status==404){
	                    // document.getElementById('capa').innerHTML= "La direccion no existe";
			             
			              
			              insertData("<tr class='RowStyle'><td colspan='5'>La direccion no existe</td></tr> ");
			              
	                }else{
	                   // document.getElementById('capa').innerHTML= "Error: ".ajax.status;
	                 
			        
			                 insertData("<tr class='RowStyle'><td colspan='5'>Error: " +ajax.status+"</td></tr> ");
	                }
	            }
	        }
	        ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	        ajax.send(parameters);
	        return;
	    }
   
	//}
	
	 
}

function hiddeCapa()
{
  document.getElementById('capa').style.visibility= "hidden";
  
}

function insertData(data)
{
  $($('#GridView1').find('.AltRowStyle')).remove();	
  $($('#GridView1').find('.RowStyle')).remove();
  $($('#GridView1').find('.SelectedRowStyle')).remove();							    			   			   
  $($('#GridView1').find('#HeaderStyle')).after(data);
}

</SCRIPT>
			<%
		    String id_sale= (String)ses.getAttribute( "id_sale");	      
		    %>
		    <center>
			 <html:form action="/paymentCanceled">
			 <html:hidden property="process" value=""/>
			 <html:hidden property="id_sale" value="<%=id_sale%>"/>
			 <html:hidden property="id_payment" value=""/>
			  		<table class="GridViewStyle" cellspacing="0" border="0" id="GridView1" style="border-collapse:collapse;">
					  <tr id="HeaderStyle" class="HeaderStyle">
												<th scope="col">&nbsp;</th>
												<th scope="col"><a href="javascript:__doPostBack('GridView1','Sort$CompanyName')">Nombre del Cliente</a></th>												
												<th scope="col"><a href="javascript:__doPostBack('GridView1','Sort$City')">Evento</a></th>
												<th scope="col"><a href="javascript:__doPostBack('GridView1','Sort$Address')">Salon</a></th>
								
					  </tr>
											
								 			
										
										 <logic:iterate id="id" indexId="indexId" name="listBeanSale" scope="session" >
									    	<logic:notEqual name="id" property="id" value="">
									    			   	 
									    		 <%
									    		 // BeanSale beanSale = (BeanSale) ses.getAttribute( "currentSale" );
												  String cuString="";
												  if (currentSale != null)
												    cuString = new Integer( currentSale.getId() ).toString(); 
												  
												  
												  
									    		  if ((counter % 2)==1)						
								          	          clase="AltRowStyle";						
												  else
													  clase="RowStyle";	%>			
									    					
				    			
									    			
									    			  <logic:equal name="id" property="id" value="<%=cuString%>">
									    			   <tr class="SelectedRowStyle">
									    			</logic:equal> 
									    			
									    			<logic:notEqual name="id" property="id" value="<%=cuString%>">
									    			   <tr class="<%=clase%>">
									    			</logic:notEqual> 
																<td>
																
																  <a href="#button" class="button icon approve" onclick="javascript:__doPost('selectSale','<bean:write name="id" property="id" />')" >Seleccionar</a>
																</td>
																<td><bean:write name="id" property="client_name" /></td>																
																<td><bean:write name="id" property="list_description" /></td>
																<td><bean:write name="id" property="saloon_description" /></td>
															
																
															</tr>
											</logic:notEqual> 

									    		<%counter++;%>	
								    		</logic:iterate>	
								    		
								    							<tr class="PagerStyle">
											<td colspan="5">
											<table border="0">
												<tr>
												
												  <td><a href="#button" class="button icon search">
												  <input title="Teclee aqui para buscar" type="text" id="search" value=""  onkeyup="FAjax('dataSale.jsp','POST',this.value,'bean=BeanSale&list=listBeanSale&fields=fields&searchValue='+this.value+'&active=Y');"/>	
												  </a>
												  </td>
													 <td><a href="javascript:__doFilter('')"><input type="radio" name="fieldFilter" id="fieldFilter" value="client_name" checked="checked">Nombre de Cliente</td>
													<td><a href="javascript:__doFilter('')"><input type="radio" name="fieldFilter" id="fieldFilter" value="list_description">Evento</a></td>
													<td><a href="javascript:__doFilter('')"><input type="radio" name="fieldFilter" id="fieldFilter" value="saloon_description">Salon</a></td>
													
										
								
												</tr>
											</table>
											 
					        		         
										</td>
										</tr>
    						
											
					</table>
			    </br>
							    <% if(currentSale !=null) 
			    {
			    %>
			     
                <table class="GridViewStyle" cellspacing="0" border="0" id="GridView1" style="border-collapse:collapse;">
					  <tr id="HeaderStyle" class="HeaderStyle">
					  							<th scope="col">&nbsp;</th>												
												<th scope="col"><a href="javascript:__doPostBack('GridView1','Sort$CompanyName')">Fecha</a></th>												
												<th scope="col"><a href="javascript:__doPostBack('GridView1','Sort$City')">Abono</a></th>
												<th scope="col"><a href="javascript:__doPostBack('GridView1','Sort$Address')">Adeudo Actual</a></th>
								
					  </tr>
											
								 			
										
										 <logic:iterate id="id" indexId="indexId" name="paymentsRecordList" scope="session" >
									    	
									    			   	 
									    		 <%
									    		  
																								  
												  
									    		  if ((counter % 2)==1)						
								          	          clase="AltRowStyle";						
												  else
													  clase="RowStyle";	%>			
									    					
				    			
									    			
									    			
									    			      <tr class="<%=clase%>">
									    			
															    <td><A href="#" onclick="javascript:__doPost('cancel','<bean:write name="id" property="id" />')">Cancelar</A></td>
																<td><bean:write name="id" property="date_reg" /></td>														
																
																<td><bean:write name="id" property="quantity" /></td>
																<td><bean:write name="id" property="currentCharge" /></td>
															
																
															</tr>
											

									    		<%counter++;%>	
								    		</logic:iterate>	
								    		
								    		<tr class="PagerStyle">
											<td colspan="5" align="right">
											
											
												Adeudo Total:									
												
												  <%
												    out.write(String.valueOf( total));
												  %>
										
											 
					        		         
										</td>
										</tr>
    						
											
					</table>
				<%} %>

			
			</html:form>
			
			
			</center>
			

		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	<logic:notPresent name="beanUser" scope="session">
	   <logic:redirect href="../login.jsp">  </logic:redirect>
	</logic:notPresent>

</BODY>

  <script type="text/javascript">
	//<![CDATA[
	var theForm = document.forms['paymentCanceledForm'];
	
	function __doPost( eventArgument,value) {
	        
       theForm.process.value = eventArgument;	        

      
       if(eventArgument == 'selectSale')
       {	           
           theForm.id_sale.value= value; 	
                       	           
       } 
       
       if(eventArgument == 'cancel')
       {	           
           theForm.id_payment.value= value; 	
                       	           
       }  
      
    
       theForm.submit();
	}
	//]]>
	</script>


</html:html>
