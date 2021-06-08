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
	<META name="GENERATOR" content="Eclipse">
	<META http-equiv="Content-Style-Type" content="text/css">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="/eventAdmin/GridViewCSSThemes/WhiteChromeGridView.css" rel="stylesheet" type="text/css" />	
	<SCRIPT type="text/javascript" src="/eventAdmin/GridViewCSSThemes/js/ajaxfile.js"></SCRIPT>	 
	<link rel="stylesheet" href="/eventAdmin/GridViewCSSThemes/bootstrap.min.css">
	<SCRIPT type="text/javascript" src="/eventAdmin/GridViewCSSThemes/js/jquery.min.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="/eventAdmin/GridViewCSSThemes/js/bootstrap.min.js"></SCRIPT>	
	<script src="/eventAdmin/js/utilajax.js"></script>
	<script src="/eventAdmin/tui.calendar-master/examples/js/data/connection.js"></script>

 	 
 
	
    <script type="text/javascript" src="/eventAdmin/GridViewCSSThemes/js/moment.2.22.2/moment.min.js"></script>
	<script type="text/javascript" src="/eventAdmin/GridViewCSSThemes/js/moment.2.22.2/moment-with-locales.min.js"></script>
	
	
	<script type="text/javascript" src="/eventAdmin/GridViewCSSThemes/js/tempusdominus-bootstrap-4/tempusdominus-bootstrap-4.min.js"></script>
	<link rel="stylesheet" href="/eventAdmin/GridViewCSSThemes/js/tempusdominus-bootstrap-4/tempusdominus-bootstrap-4.min.css" />
    <TITLE>
    </TITLE>
</HEAD>
 
<BODY>

	<CENTER><H1> <bean:message key="label.sale.change" />  </H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 42;
			    
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
		        
		        
		         String visibleUserData = (String)ses.getAttribute("visibleUserData");   
		         String clientNumber = (String)ses.getAttribute("clientNumber"); 
		         String clientName = (String)ses.getAttribute("clientName"); 
		         String total = "";
		         /*Object d = (Object)ses.getAttribute("total");
		         out.write(d.toString());*/
		         if(ses.getAttribute("total") != null)
		          total = (String)ses.getAttribute("total"); 

		         BeanList listEdit_currentList = (BeanList) ses.getAttribute( "listEdit_currentList" );							    
		         BeanListOptions listEdit_currentOption = (BeanListOptions) ses.getAttribute( "listEdit_currentOption" );							    
		         BeanListOptionMenu listEdit_currentMenu = (BeanListOptionMenu) ses.getAttribute( "listEdit_currentMenu" );
		         
		         String currentId_list = (String) ses.getAttribute("currentId_list" );
		         String currentId_option = (String) ses.getAttribute("currentId_option" );
		         String currentId_menu = (String) ses.getAttribute("currentId_menu" );

		         
		         
		         
		         BeanSale beanSale = (BeanSale) ses.getAttribute( "beanSale" );	
		         BeanSaleFlower beanSaleFlower = (BeanSaleFlower) ses.getAttribute( "beanSaleFlower" );
		         BeanSaleMusic beanSaleMusic = (BeanSaleMusic) ses.getAttribute( "beanSaleMusic" );
		        // BeanSaleProduct beanSaleProduct = (BeanSaleProduct) ses.getAttribute( "beanSaleProduct" );
		         
		         
		         
				 
		         
		         
		         BeanSaloon sale_currentSaloon = (BeanSaloon) ses.getAttribute( "sale_currentSaloon" );							    
		         BeanFlower sale_currentFlower = (BeanFlower) ses.getAttribute( "sale_currentFlower" );							    
		         BeanMusic sale_currentMusic = (BeanMusic) ses.getAttribute( "sale_currentMusic" );							    
				 String clString;
				 String coString;
				 String cmString;
				 String csString;
				 String cfString;
				 String cmuString;
				 String cprdString;
		         
				
				 if(ses.getAttribute("selectedSale") == "yes")
				 {
				 
					 if ( clientName == null )
				           clientName = new String("");
				         
			         if ( total == null )
			        	 total = new String("");
				         
					 if ( beanSale != null ) 
						 clString = new Integer( beanSale.getId_list().getId() ).toString();  //clString = new Integer( listEdit_currentList.getId() ).toString(); 
					 else
					   clString = "-1";
						 
				     if ( beanSale != null ) 
					   coString = new Integer( beanSale.getId_list_option().getId() ).toString(); 
					 else
					   coString = "-1";
						 
					 if ( beanSale != null ) 
					   cmString = new Integer( beanSale.getId_list_option_menu().getId() ).toString(); 
					 else
					   cmString = "-1";
			         
					 if ( beanSale != null ) 
					   csString = new Integer( beanSale.getId_saloon().getId() ).toString(); 
					 else
					   csString = "-1";
				         
					 if ( beanSaleFlower != null ) 
					   cfString = new Integer( beanSaleFlower.getId_flower().getId() ).toString(); 
					 else
					   cfString = "-1";
				         
					 if ( beanSaleMusic != null ) 
						   cmuString = new Integer( beanSaleMusic.getId_music().getId() ).toString(); 
					 else
						 cmuString = "-1";
					 
					 //if ( beanSaleProduct != null ) 
					//	 cprdString = new Integer( beanSaleProduct.getId_product().getId() ).toString(); 
					// else
					//	 cprdString = "-1";
				 
				 }
				 else
				 {      // String currentId_list = (String) ses.getAttribute("currentId_list" );
		         //String currentId_option = (String) ses.getAttribute("currentId_option" );
		        // String currentId_menu = (String) ses.getAttribute("currentId_menu" );

					 
					 if ( clientName == null )
				           clientName = new String("");
				         
			         if ( total == null )
			        	 total = new String("");
				         
					 if ( currentId_list != null ) 
					   clString = new Integer( currentId_list ).toString(); 
					 else
					   clString = "-1";
						 
					 if ( currentId_option != null ) 
					   coString = new Integer( currentId_option ).toString(); 
					 else
					   coString = "-1";
						 
					 if ( currentId_menu != null ) 
					   cmString = new Integer( currentId_menu ).toString(); 
					 else
					   cmString = "-1";
			         
					 if ( sale_currentSaloon != null ) 
					   csString = new Integer( sale_currentSaloon.getId() ).toString(); 
					 else
					   csString = "-1";
				         
					 if ( sale_currentFlower != null ) 
					   cfString = new Integer( sale_currentFlower.getId() ).toString(); 
					 else
					   cfString = "-1";
				         
					 if ( sale_currentMusic != null ) 
						   cmuString = new Integer( sale_currentMusic.getId() ).toString(); 
					 else
						 cmuString = "-1"; 
				 }
			         
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
			 <html:form action="/saleChange">
			 <html:hidden property="process" value=""/>
			 <html:hidden property="id_sale" value="<%=id_sale%>"/>
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
												  if (beanSale != null)
												    cuString = new Integer( beanSale.getId() ).toString(); 
												  
												  
												  
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
				<TABLE border="0" ALIGN="CENTER">
				<TBODY>

					
				<%if(visibleUserData == "yes")
					{
					%>
					<TR>
						<TD class="column_field">Nombre del Cliente</TD>
						<TD colspan="2">						
									<%=clientName%>
		
						</TD>
			       
					</TR>
					
						<TR>
						<TD class="column_field"><bean:message key="label.client.number" /></TD>
						<TD>
						<html:text property="id_client" value='<%=clientNumber%>' size="15" disabled="true"></html:text>
									
		
						</TD>
			        	<TD width="346">
			        	 	<FONT COLOR="RED"> <html:errors property="id_client"/></FONT>
			        	</TD>
					</TR>
					<TR>
						<TD class="column_field">
							  <bean:message key="label.event.date" />
						</TD>	
						<TD>
							  <html:text property="date_event" readonly="true" onclick="javascript:showCal('calendar5');"> </html:text>
							  <A href="javascript:showCal('calendar5')"><IMG src="/eventAdmin/Calendar/calendar.png" border="0" /></A>
							  <SPAN id=date1 style="POSITION: relative"> </SPAN>
						      <FONT COLOR="RED"> <html:errors property="date_event"/></FONT>
						</TD>	
						<TD>
							<html:submit onclick="process.value='calendar';"> <bean:message key="label.view.availabilty" /> </html:submit>
						</TD>
					</TR>
					<TR>
					    <TD class="column_field">
					    	<bean:message key="label.hour.begin" />
					    </TD>
						<TD>
							<html:select property="id_hour">						
								<logic:iterate id="id" indexId="indexId" name="listHours" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>>
								    	<bean:write name="id" property="hour" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
						</TD>
					</TR>
					<TR>
					    <TD class="column_field">
					    	<bean:message key="label.hour.quantity" />
					    </TD>
						<TD>						   
						   <html:text property="hourQuantity"></html:text>
						   <FONT COLOR="RED"> <html:errors property="hourQuantity"/></FONT>
						</TD>
					</TR>
					<TR>
					    <TD class="column_field">
					    	<bean:message key="label.persons.count" />
					    </TD>
						<TD>						   
						   <html:text property="personsCount"></html:text>
						   <FONT COLOR="RED"> <html:errors property="personsCount"/></FONT>
						</TD>
					</TR>
					<TR>
					    <TD class="column_field"><bean:message key="label.list.prices" /></TD>
						<TD>
							<html:select property="id_list" onchange="process.value='selectList'; submit();">						
								<logic:iterate id="id" indexId="indexId" name="listList" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>    
									    <logic:equal name="id" property="id" value="<%=clString%>"> 
									    		SELECTED
									    </logic:equal>>
								    	<bean:write name="id" property="description" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
						</TD>
						<TD>
							<html:submit onclick="process.value='selectList';"> <bean:message key="label.select" /> </html:submit>
						</TD>
					</TR>

					<TR>
					    <TD class="column_field">
					    	<bean:message key="label.list.options" />
					    </TD>
						<TD>
							<html:select property="id_option" onchange="process.value='selectOption'; submit();">						
								<logic:iterate id="id" indexId="indexId" name="listOptionsList" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>    
									    <logic:equal name="id" property="id" value="<%=coString%>"> 
									    		SELECTED
									    </logic:equal>>
								    	<bean:message key="label.between" /> <bean:write name="id" property="min" /> <bean:message key="label.and" /> <bean:write name="id" property="max" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
						</TD>
						<TD>
							<html:submit onclick="process.value='selectOption';"> <bean:message key="label.select" /> </html:submit>
						</TD>
					</TR>
					<TR>
					    <TD class="column_field">
					    	<bean:message key="label.list.menu" />
					    </TD>
						<TD>
							<html:select property="id_menu">						
								<logic:iterate id="id" indexId="indexId" name="listOptionMenuList" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
									    <logic:equal name="id" property="id" value="<%=cmString%>"> 
									    		SELECTED
									    </logic:equal>>
								    	<bean:write name="id" property="name" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
						    <FONT COLOR="RED"> <html:errors property="id_menu"/></FONT>
						</TD>
						<TD>
						</TD>
					</TR>
					<TR>
					    <TD class="column_field"><bean:message key="label.saloon" /></TD>
						<TD>
							<html:select property="id_saloon">						
								<logic:iterate id="id" indexId="indexId" name="listSaloon" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
									    <logic:equal name="id" property="id" value="<%=csString%>"> 
									    		SELECTED
									    </logic:equal>>
								    	<bean:write name="id" property="description" />:-<bean:write name="id" property="min_occupancy" />-<bean:write name="id" property="max_occupancy" />  
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
						    <FONT COLOR="RED"> <html:errors property="id_saloon"/></FONT>
						</TD>
						<TD>
						</TD>
					</TR>
					<TR>
					    <TD class="column_field">
					    	<bean:message key="label.flowers" />
					    </TD>
						<TD>
							<html:select property="id_flower">						
								<logic:iterate id="id" indexId="indexId" name="listFlowers" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
									    <logic:equal name="id" property="id" value="<%=cfString%>"> 
									    		SELECTED
									    </logic:equal>>
								    	<bean:write name="id" property="description" />:-<bean:write name="id" property="price" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
						    <FONT COLOR="RED"> <html:errors property="id_flower"/></FONT>
						</TD>
						<TD>
						</TD>
					</TR>
					<TR>
					    <TD class="column_field"><bean:message key="label.music" /></TD>
						<TD>
							<html:select property="id_music">						
								<logic:iterate id="id" indexId="indexId" name="listMusic" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
									    <logic:equal name="id" property="id" value="<%=cmuString%>"> 
									    		SELECTED
									    </logic:equal>>
								    	<bean:write name="id" property="description" />:-<bean:write name="id" property="price" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
						    <FONT COLOR="RED"> <html:errors property="id_music"/></FONT>
						</TD>
						<TD>
						</TD>
					</TR>
	
					
					
					<TR>
					    <TD class="column_field">
					        <bean:message key="label.products" />
					    </TD>
					    <TD class="column_field">
					    	<bean:message key="label.quantity" />
					    </TD>
					</TR>
					<TR>
						<TD>
							<html:select property="id_product">						
								<logic:iterate id="id" indexId="indexId" name="listProduct" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
								     
									    >
								    	<bean:write name="id" property="description" />:- <bean:write name="id" property="price" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
						</TD>
						<TD>						   
						   <html:text property="productQuantity"></html:text>
						   <FONT COLOR="RED"> <html:errors property="productQuantity"/></FONT>
						</TD>
						<TD>
							<html:submit onclick="process.value='addProduct';"> <bean:message key="label.add" /> </html:submit>
						</TD>
					</TR>
					<TR>
						<TD colspan="3">
						
						</TD>
					</TR>
					<TR> 
					 
					    <logic:present name="qnCarListProduct" scope="session">
							<TD colspan="3">
								<TABLE border="1">
									<TR class="column_field"> 
										<TD>
										   <bean:message key="label.quantity" />
										</TD>
										<TD>
										   <bean:message key="label.description" />
										</TD>
										<TD>
										   <bean:message key="label.price" />
										</TD>
									</TR> 
									<logic:iterate id="id" indexId="indexId" name="qnCarListProduct" scope="session" >
										<TR> 
											<TD>
										    	<bean:write name="id" property="qty" />
											</TD>
											<TD>
										    	<bean:write name="id" property="description" />
											</TD>
											<TD>
										    	<bean:write name="id" property="price" />
											</TD>
										<TR> 
								    </logic:iterate>			
								</TABLE>
							</TD>
						</logic:present>		
					</TR>
					<TR>
						<TD colspan="3">
						
						</TD>
					</TR>
					<TR> 
					    <TD class="column_field">
					    	<bean:message key="label.total" />
					    </TD>
						<TD>
							<html:text property="total" value='<%=total%>' size="15" disabled="true"></html:text>
						</TD>
						<TD>
							<html:submit onclick="process.value='updateTotal';"> <bean:message key="label.update.total" /> </html:submit>
						</TD>
					</TR>
					<TR>
					    <TD class="column_field">
					    	<bean:message key="label.email.optional" />
					    </TD>
						<TD>						   
						   <html:text property="anotherEmail"></html:text>
						   <FONT COLOR="RED"> <html:errors property="anotherEmail"/></FONT>
						</TD>
					</TR>
					<TR> 
						<TD>
							<html:submit onclick="process.value='register';"><bean:message key="label.quote.register" /></html:submit>						
						</TD>
					</TR>
				    <%
			        }
					%>
					
				</TBODY>
			</TABLE>

			
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
	var theForm = document.forms['saleChangeForm'];
	
	function __doPost( eventArgument,value) {
	        
       theForm.process.value = eventArgument;	        

      
       if(eventArgument == 'selectSale')
       {	           
           theForm.id_sale.value= value; 	
                       	           
       }          
      
    
       theForm.submit();
	}
	//]]>
	</script>


</html:html>
