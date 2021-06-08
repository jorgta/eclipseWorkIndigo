<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*, com.eventAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*,com.bituos.*, com.google.gson.Gson"
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
<SCRIPT language="JavaScript" src="/eventAdmin/getSystemDate.js"></SCRIPT>
<SCRIPT language="Javascript" src="/eventAdmin/Calendar/calendar.js"></SCRIPT>
<SCRIPT language="Javascript" src="/eventAdmin/Calendar/mycalendar.js"></SCRIPT>
<BODY>

	<CENTER><H1> <bean:message key="label.quote.new" />  </H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 31;
			    
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
		         String total = (String)ses.getAttribute("total"); 

		         BeanList listEdit_currentList = (BeanList) ses.getAttribute( "listEdit_currentList" );							    
		         BeanListOptions listEdit_currentOption = (BeanListOptions) ses.getAttribute( "listEdit_currentOption" );							    
		         BeanListOptionMenu listEdit_currentMenu = (BeanListOptionMenu) ses.getAttribute( "listEdit_currentMenu" );							    
		         BeanSaloon quoteNew_currentSaloon = (BeanSaloon) ses.getAttribute( "quoteNew_currentSaloon" );							    
		         BeanFlower quoteNew_currentFlower = (BeanFlower) ses.getAttribute( "quoteNew_currentFlower" );							    
		         BeanMusic quoteNew_currentMusic = (BeanMusic) ses.getAttribute( "quoteNew_currentMusic" );							    
				 String clString;
				 String coString;
				 String cmString;
				 String csString;
				 String cfString;
				 String cmuString;
				 
				 String quoteNew_currentIdHour = (String) ses.getAttribute( "quoteNew_currentIdHour" );	
				 
				 if ( quoteNew_currentIdHour == null )
					 quoteNew_currentIdHour = new String("");
				 
				 if ( clientNumber == null )
					 clientNumber = new String("");
		         
		         if ( clientName == null )
			           clientName = new String("");
			         
		         if ( total == null )
		        	 total = new String("");
			         
				 if ( listEdit_currentList != null ) 
				   clString = new Integer( listEdit_currentList.getId() ).toString(); 
				 else
				   clString = "-1";
					 
				 if ( listEdit_currentOption != null ) 
				   coString = new Integer( listEdit_currentOption.getId() ).toString(); 
				 else
				   coString = "-1";
					 
				 if ( listEdit_currentMenu != null ) 
				   cmString = new Integer( listEdit_currentMenu.getId() ).toString(); 
				 else
				   cmString = "-1";
		         
				 if ( quoteNew_currentSaloon != null ) 
				   csString = new Integer( quoteNew_currentSaloon.getId() ).toString(); 
				 else
				   csString = "-1";
			         
				 if ( quoteNew_currentFlower != null ) 
				   cfString = new Integer( quoteNew_currentFlower.getId() ).toString(); 
				 else
				   cfString = "-1";
			         
				 if ( quoteNew_currentMusic != null ) 
					   cmuString = new Integer( quoteNew_currentMusic.getId() ).toString(); 
				 else
					 cmuString = "-1";
			         
			    
				 
				List  filterList = (List) ses.getAttribute("filterList" );
				 
				  
				 
				BeanClient currentBean = (BeanClient) ses.getAttribute( "selectedClient" );
				String qidString;
	  
		        if ( currentBean != null ) 
		        {
		     	  qidString = new Integer( currentBean.getId() ).toString(); 
		        }
				else
				{
				 qidString = "";
				}
			         
				ses.setAttribute( "from","quoteNew" );		
				  
				
				int counter =0;
		        String clase ="";
 
		    	//lista de columnas
		    	ArrayList<String> cols = new ArrayList<String> ();
		    	cols.add("id");
		    	cols.add("id_intern");
		    	cols.add("Nombre del Cliente");
		    	cols.add("RFC");
		    	cols.add("Direccion");
		    	cols.add("Telefono");
		    	ses.setAttribute("cols", cols );
		    	
		    	//lista de campos a imprimir
		    	ArrayList<String> fields = new ArrayList<String>();
		    	fields.add("id");
		    	fields.add("id_intern");
		    	fields.add("name");
		    	fields.add("rfc");
		    	fields.add("address");
		    	fields.add("phone");
		    	ses.setAttribute("fields", fields );
		    	
		    	
		    	//campo que se usara para la etiqueta de accion
		    	String fieldNameToAction = "id_intern";
		    	
 
		    	//lista de acciones de la primera columna  
		        String[] process = new String[20];
		        process[0]="validateClient";		 
		        
		        
		        //campo que senvia a la forma
		        String[] fieldname = new String[20];
		        fieldname[0]="id_client";	
		        		        
		        //etiqueta de accion
		        String[] processTag = new String[20];
		        processTag[0]="Seleccionar";
		        
		      //campos para ignorar en el gridview
		    	String[]  field= new String[20];
		    	field[0] = "id";
		    	field[1] = "id_intern";
		    	
		    	
		    	
		    	List<String> ignore = Arrays.asList(field);

		         //lista de parametros 
		 
				MappingDynamicJSON mappingDynamicJSON = new MappingDynamicJSON();
				
				mappingDynamicJSON.setFields("processTag", processTag[0]);
				mappingDynamicJSON.setFields("process",process[0]);
				mappingDynamicJSON.setFields("bean","BeanClient");
				mappingDynamicJSON.setFields("list","listBean");
				mappingDynamicJSON.setFields("fields","fields");
				mappingDynamicJSON.setFields("active","Y");
				mappingDynamicJSON.setFields("searchValue",""); 
				mappingDynamicJSON.setFields("searchParam","");
				mappingDynamicJSON.setFields("fieldNameToAction",fieldNameToAction);
				mappingDynamicJSON.setFields("ignore",ignore);
				mappingDynamicJSON.setFields("current",qidString);
				
				
				
				
				
				mappingDynamicJSON.setFields("nestedField1","id");
				mappingDynamicJSON.setFields("nestedField2","id_client");
				mappingDynamicJSON.setFields("nestedFieldOrder","date_reg");
				mappingDynamicJSON.setFields("nestedBean","BeanQuote");
				//mappingDynamicJSON.setFields("beanUser",beanUser);
				
				 //end configure

				 
				 
				
				String resltJsonString = new Gson().toJson(mappingDynamicJSON);
				PrintWriter pw=response.getWriter();
				//pw.println(resltJsonString);

				
				//nombre de la forma
				String formName="quoteNewForm";
					
		         
			  %>
			  
			  <div class="container">
				
				<div class="card  mb-3"  >
				
				  <div class="card-header">
				    <H1> Cambios </H1>
				  </div>
				  <div class="card-body">
				     
				    <h5 class="card-title"><bean:message key="label.client.select" /> </h5>
				    <p class="card-text">
				    
				    
				     </p>
				    <!-- 
				    <a href="#" class="btn btn-primary">Go somewhere</a>
				     -->
				    
							 <html:form action="/quoteNew"  styleClass="needs-validation">
							 <html:hidden property="process" value=""/>
							 <html:hidden property="id" value=""/>
							 <html:hidden property="id_client" value=""/>							 
							 <html:hidden property="concept" value="quote"/>
							 <html:hidden property="forward" value="new"/>
							 <html:hidden property="aditional1" value=""/>
							 <input type="hidden" name="checkUserData" value="NO" />
							 <html:hidden property="date_event" value="" />
							 <ul class="list-group">
							 <li class="list-group-item ">
							 <table  class="HeaderStyle cellspacing="0" border="1" id="GridView1" style="border-collapse:collapse; ">
							 		<tr class="HeaderStyle">	
									   <td scope="col">&nbsp;</td>
				
									  <logic:iterate id="idfields" indexId="indexfieldsId" name="fields"  scope="session" >
											   
											 <bean:define id="fld" name="idfields" > </bean:define>
											  
											 <% if(!ignore.contains(((String) fld)) )
											 { 
											 %>								
												  
												<td> <input title="Teclee aqui para buscar" type="text" id="search" value=""  onkeyup='FAjax("dataTemplateGrid.jsp","POST",<%=resltJsonString%>,this.value,"<%=((String) fld)%>");'/></td>
											 
											 <%
											}
									     	%>
									     	
									     	
									     	
										</logic:iterate>
									 </tr>
									 										
									  <tr id="HeaderStyle" class="HeaderStyle">			
									  <td scope="col">&nbsp;</td>
									  <logic:iterate id="idfields" indexId="indexId" name="cols"  scope="session" >
									  	<bean:define id="fld" name="idfields" > </bean:define>
									  	<% if(!ignore.contains(((String) fld)) )
										 { 
										 %>
											<td>	
											<a href="javascript:__doPostBack('GridView1','Sort$CompanyName')"><bean:write name="idfields"/></a>
											</td>
										<%
										}
								     	%>
										
									  </logic:iterate>   
						
									  </tr>
									  
										 <logic:iterate id="id" indexId="indexId" name="listBean" scope="session" >
										 	
									    	<logic:notEqual name="id" property="id" value="">
									    		 <%		
									    		  String cuString="";
												  if (currentBean != null)
												    cuString = new Integer( currentBean.getId() ).toString(); 
												  
									    		  if ((counter % 2)==1)						
								          	          clase="AltRowStyle";				
												  else
													  clase="RowStyle";	
												 %>		
												 
											    <logic:equal name="id" property="id" value="<%=cuString%>">
								    			   <tr class="SelectedRowStyle">
								    			</logic:equal> 
								    			
								    			<logic:notEqual name="id" property="id" value="<%=cuString%>">
								    			   <tr class="<%=clase%>">
								    			</logic:notEqual> 
									    			
									    			
													    <th scope="row">
														  <a href='#button' class='btn btn-outline-secondary btn-sm' role='button'   onclick='javascript:__doPost("<%=process[0]%>","<bean:write name="id" property="<%=fieldNameToAction%>" />")' ><%=processTag[0]%></a>
														</th>
														<logic:iterate id="idfields" indexId="indexId" name="fields"  scope="session" >
															<bean:define id="fld" name="idfields" > </bean:define>
															<% if(!ignore.contains(((String) fld)) )
															{ 
													   
													        %>
																<td>    
													     	       <bean:write name="id" property="<%=((String) fld)%>" /> 
													     	    </td>
													     	<%
															}
													     	%>
														</logic:iterate>
													  </tr>
													
											</logic:notEqual> 
				
									    		<%counter++;%>	
								    		</logic:iterate>	
				 					
									</table>
									</li>
									 
					  		<%if(visibleUserData == "yes")
							{
							%>
								<li class="list-group-item">
							
							    
								  <div class="form-row">						 
								    <div class="col-md-4 mb-3">	
									    <label for="date_event"><bean:message key="label.event.date" /></label>
								    	<div class="input-group">								    	 
				 
										 
							 			
							 			
										<div class="input-group date"  data-target-input="nearest">
						                   
						                     <input type="text" class="form-control datetimepicker-input" id="datetimepicker4" data-toggle="datetimepicker" data-target="#datetimepicker4"/>
						                    <div class="input-group-append"  data-toggle="datetimepicker">
						                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
						                    </div>
						                    
						                    <FONT COLOR="RED"> <html:errors property="date_event"/></FONT>
						                </div>
										<div class="input-group-append">
										   <button type="submit" class="btn btn-secondary" onclick="process.value='calendar';"><bean:message key="label.view.availabilty" />  </button>
										 </div>
										 
								    	</div>								    
								    </div> 						 
							   </div>  
							   
							   	  <div class="form-row">
								    <div class="col-md-4 mb-3">								      
								      <label for="id_hour"><bean:message key="label.hour.begin" /></label>								      	      
									<html:select property="id_hour" styleClass="form-control">						
										<logic:iterate id="id" indexId="indexId" name="listHours" scope="session" >
										    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>    
												    <logic:equal name="id" property="id" value="<%=quoteNew_currentIdHour%>"> 
												    		SELECTED
												    </logic:equal>>
											    	<bean:write name="id" property="hour" />
											 </OPTION>
											    
								    	</logic:iterate>						
									</html:select>							
									<FONT COLOR="RED"> <html:errors property="id_hour"/></FONT>						      			      
								    </div>		   								    					 
							    </div>
							   
							   
								<div class="form-row">
								    <div class="col-md-4 mb-3">								      
								      <label for="hourQuantity"><bean:message key="label.hour.quantity" /></label>								      	      
								      <html:text property="hourQuantity" styleClass="form-control"></html:text>								      			      
								      <FONT COLOR="RED"> <html:errors property="hourQuantity"/></FONT> 
								    </div>							 
							    </div>
							    
							    
							    <div class="form-row">
								    <div class="col-md-4 mb-3">								      
								      <label for="personsCount"><bean:message key="label.persons.count" /></label>								      	      
								      <html:text property="personsCount" styleClass="form-control"></html:text>								      			      
								      <FONT COLOR="RED"> <html:errors property="personsCount"/></FONT> 
								    </div>							 
							    </div>
							    
							    
							    <div class="form-row">
								    <div class="col-md-4 mb-3">		
								    	<label for="id_list"><bean:message key="label.list.prices" /></label>
								    	<div class="input-group">
										    <html:select property="id_list" onchange="process.value='selectList'; submit();" styleClass="custom-select">						
											<logic:iterate id="id" indexId="indexId" name="listList" scope="session" >
											    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>    
												    <logic:equal name="id" property="id" value="<%=clString%>"> 
												    		SELECTED
												    </logic:equal>>
											    	<bean:write name="id" property="description" />
											    </OPTION>
									    	</logic:iterate>						
										 </html:select>	
									
										  <div class="input-group-append">
										   <button type="submit" class="btn btn-secondary" onclick="process.value='selectList';"><bean:message key="label.select" />  </button>
										  </div>
									   </div>				        
								    </div>   						 
							   </div>
							   
							   	<div class="form-row">
								    <div class="col-md-4 mb-3">		
								    	<label for="id_option"><bean:message key="label.list.options" /></label>
								    	<div class="input-group">
										    <html:select property="id_option" onchange="process.value='selectOption'; submit();" styleClass="custom-select">						
											<logic:iterate id="id" indexId="indexId" name="listOptionsList" scope="session" >
											    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>    
												    <logic:equal name="id" property="id" value="<%=coString%>"> 
												    		SELECTED
												    </logic:equal>>
											    	<bean:message key="label.between" /> <bean:write name="id" property="min" /> <bean:message key="label.and" /> <bean:write name="id" property="max" />
											    </OPTION>
									    	</logic:iterate>						
										 </html:select>	
									
										  <div class="input-group-append">
										   <button type="submit" class="btn btn-secondary" onclick="process.value='selectOption';"><bean:message key="label.select" />  </button>
										  </div>
									   </div>				        
								    </div>   						 
							   </div>
	 
							    
							    <div class="form-row">
								    <div class="col-md-4 mb-3">								      
								      <label for="id_menu"><bean:message key="label.list.menu" /></label>								      	      
									  <html:select property="id_menu" styleClass="custom-select">						
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
								    </div>		   								    					 
							    </div>
							    
							    <logic:present name="quoteNew_listSaloon" scope="session">  
							    <div class="form-row">
								    <div class="col-md-4 mb-3">								      
								      <label for="id_saloon"><bean:message key="label.saloon" /></label>								      	      
									  <html:select property="id_saloon" styleClass="custom-select">						
										<logic:iterate id="id" indexId="indexId" name="quoteNew_listSaloon" scope="session" >
										    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
											    <logic:equal name="id" property="id" value="<%=csString%>"> 
											    		SELECTED
											    </logic:equal>>
										    	<bean:write name="id" property="description" />:-<bean:write name="id" property="min_occupancy" />-<bean:write name="id" property="max_occupancy" />
										    </OPTION>
								    	</logic:iterate>						
									</html:select>							
									<FONT COLOR="RED"> <html:errors property="id_saloon"/></FONT>						      			      
								    </div>						     								    					 
							    </div>
							    </logic:present>
							    
							    <logic:present name="quoteNew_listSaloon" scope="session">  
							    <div class="form-row">
								    <div class="col-md-4 mb-3">								      
								      <label for="id_flower"><bean:message key="label.flowers" /></label>								      	      
									 <html:select property="id_flower" styleClass="custom-select">						
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
								    </div>						     								    					 
							    </div>
							    </logic:present>
							    
							    <logic:present name="listMusic" scope="session">  
							    <div class="form-row">
								    <div class="col-md-5 mb-3">								      
								      <label for="id_music"><bean:message key="label.music" /></label>								      	      
									 <html:select property="id_music" styleClass="custom-select">						
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
								    </div>						     								    					 
							    </div>
							    </logic:present>
							    
							    <logic:present name="listProduct" scope="session">  
							    <div class="form-row">
								    <div class="col-md-8 mb-3">		
								    	<label for="id_product"><bean:message key="label.products" /></label>
								    	<div class="input-group">
										    <html:select property="id_product"  styleClass="custom-select">						
												<logic:iterate id="id" indexId="indexId" name="listProduct" scope="session" >
												    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
													    >
												    	<bean:write name="id" property="description" />:- <bean:write name="id" property="price" />
												    </OPTION>
										    	</logic:iterate>						
											</html:select>	 
									   </div>				        
								    </div>  
								 
								    <div class="col-md-4 mb-3">	
									    <label for="productQuantity"><bean:message key="label.quantity" /></label>
								    	<div class="input-group">
								    	<html:text property="productQuantity"></html:text>
										<FONT COLOR="RED"> <html:errors property="productQuantity"/></FONT>
											
										<div class="input-group-append">
										   <button type="submit" class="btn btn-secondary" onclick="process.value='addProduct';"><bean:message key="label.add" />  </button>
										 </div>
								    	</div>								    
								    </div> 						 
							    </div>
							   </logic:present>
							  
							  
							   <logic:present name="qnCarListProduct" scope="session">	
								    <div class="form-row">						 
									    <div class="col-md-5 mb-3">	
									    <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
									     <label for="" ><bean:message key="label.description" /></label>
									     </nav>
									     
									    </div>
									    <div class="col-md-3 mb-3">	
									    <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
									     <label for=""   ><bean:message key="label.price" /></label>
									      </nav>
									    </div>
									    <div class="col-md-2 mb-3">
									    <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">	
									    <label for=""    ><bean:message key="label.quantity" /></label>
									     </nav>
									    </div>
								    </div>
					    
								    <div class="form-row">
								     <div class="col-md-10 mb-3">	
									    <ul class="list-group">										   
											    <logic:iterate id="id" indexId="indexId" name="qnCarListProduct" scope="session" >									 
							 						<li class="list-group-item d-flex justify-content-between align-items-center">
												    <div class="col-md-5 mb-3">											    	
												    	<label for=""><bean:write name="id" property="description" /></label>	 
												    </div>
												    <div class="col-md-3 mb-3">
												    	<label for=""><bean:write name="id" property="price" /></label>
												    </div>
												    <div class="col-md-2 mb-3">
												    	<span class="badge badge-primary badge-pill"><bean:write name="id" property="qty" /></span>
												  	</div>
												  	</li>	
													 
											    </logic:iterate>
											
									   	</ul>
									   	 </div>
									    
								    </div>							    
							    </logic:present>
							    
							    
							    
								 	 
							 	<div class="form-row">						 
								    <div class="col-md-4 mb-3">	
									    <label for="total"><bean:message key="label.total" /></label>
								    	<div class="input-group">								    	 
								    	<html:text property="total" value='<%=total%>' size="15" disabled="true" styleClass="form-control"></html:text>
										<FONT COLOR="RED"> <html:errors property="total"/></FONT>
											
										<div class="input-group-append">
										   <button type="submit" class="btn btn-secondary" onclick="process.value='updateTotal';"><bean:message key="label.update.total" />  </button>
										 </div>
								    	</div>								    
								    </div> 						 
							   </div>   
							  
							    <div class="form-row">						 
								    <div class="col-md-4 mb-3">	
									    <label for="anotherEmail"><bean:message key="label.email.optional" /></label>
								    	<div class="input-group">								    	 
								    	<html:text property="anotherEmail"  styleClass="form-control"></html:text>
										 <FONT COLOR="RED"> <html:errors property="anotherEmail"/></FONT>
											
									 
								    	</div>								    
								    </div> 						 
							   </div>   
		 
				      			<div class="form-row">						 
								    <div class="col-md-6 mb-3">	
									    <label for="notes"><bean:message key="label.notes" /></label>
								    	<div class="input-group">								    	 
								    	 
								    	 <html:textarea property="notes"  rows="10" styleClass="form-control rounded-0"></html:textarea>
										 <FONT COLOR="RED"> <html:errors property="anotherEmail"/></FONT>
											
									 
								    	</div>								    
								    </div> 						 
							   </div> 	
							   
							   	<div class="form-row">						 
								    <div class="col-md-4 mb-3">	
										<div class="input-group-append">
										   <button type="submit" class="btn btn-secondary" onclick="process.value='register';"><bean:message key="label.quote.register" />  </button>
										   
										 </div>
								    	</div>								    
								 </div> 						 
							   </div>   
					     
					 				 
							<%
							}
					     	%>  		
										 		
										            
													
													
							
							</li>
					
									
						
				
						</html:form>
						</div>
						
						<footer class="page-footer font-small blue">
				
						  <!-- Copyright -->
						  <div class="footer-copyright text-center py-3">© 2019 Copyright:
						    <a href="https://www.bituos.com"> bituos.com</a>
						  </div>
						  <!-- Copyright -->
						
						</footer>
				   	 
				  </div>
				</div>


 
			   <script type="text/javascript">
			   
			   
				 //https://tempusdominus.github.io/bootstrap-4/Usage/#using-locales   
			    
			    	
			    	 //$('input[name ="date_event"]').val(CurrDate);

			   
			    	
				$(function () {
 
			        var d = new Date();
			        var dateFormat = "DD/MM/YYYY";
			        var month = d.getMonth()+1;
			        var day = d.getDate();
			        var CurrDate = day + '/' + month + '/' +  d.getFullYear();
			        dateCurr = moment(CurrDate, dateFormat);
			        console.log($('input[name ="date_event"]')!= null); 
			        console.log($('input[name ="date_event"]').val() != null);
			        var hidden_fields = $( this ).find( 'input:hidden' );
			        if ($('input[name ="date_event"]')!= null)
		        	{
			        	if ($('input[name ="date_event"]').val() != null)
						{
				        	//console.log("dfsd");
				        	console.log($('input[name ="date_event"]').val());
				        	if ($('input[name ="date_event"]').val() != '')
				        	{
				        		dateCurr = moment($('input[name ="date_event"]').val(), dateFormat);
				        	}
				        	else
				        	{
				        		$('input[name ="date_event"]').val(CurrDate);
				        	}
				        	
						}
			        	
			        	 
		        	}
			        
			        
			         
	            
			    	 $('#datetimepicker4').datetimepicker({
			    		 	locale: 'es',
                        format: 'L',
                        format: dateFormat,
                        date: dateCurr,
                        
		             });
			    	 
			    	 
			    	 $('#datetimepicker4').on("change.datetimepicker", function (e) {
				    		if(e.date != null)
				    		{
				    			console.log(e.date._i); 
				    			$('input[name ="date_event"]').val(e.date._i);
				    		}
				    		 
				    		if(e.oldDate != null)
				    		{
			    			 	console.log(e.oldDate._i); 
				    		}
			    	      
			    	      
			    	   });
			    	 
	            });
 
				var theForm = document.forms["<%=formName%>"];
				
				function __doPost( eventArgument,value) {
				        
			       theForm.process.value = eventArgument;	        
			
			      
			       if(eventArgument == '<%=process[0]%>')
			       {	            
			           theForm.<%=fieldname[0]%>.value= value; 	
			       
			          
			                 	           
			       }          
			      
			       theForm.submit();
				}
				
				
 
				   

				</script>
			
			
			

			
			

		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	<logic:notPresent name="beanUser" scope="session">
	   <logic:redirect href="../login.jsp">  </logic:redirect>
	</logic:notPresent>

</BODY>



 
</html:html>
