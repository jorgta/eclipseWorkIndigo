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
<BODY>

	<CENTER><H1> <bean:message key="label.convert.quote.to.sale" />  </H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 34;
			    
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
		         
		         String visibleCase= (String)ses.getAttribute("visibleCase"); 
		         String visibleUserData= (String)ses.getAttribute("visibleUserData");   
		         
		        
		         
		         String qidString;
		         String qclString;
		         String qdeString;
		         BeanQuote currentBean= (BeanQuote)ses.getAttribute("selectedQuote"); 
		          
		         if ( currentBean != null ) 
		         {
		        	 qidString = new Integer( currentBean.getId() ).toString(); 
		        	 qclString = currentBean.getClient_name();
		        	 qdeString = currentBean.getDate_event().toString();
		        		 
		         }
				 else
				 {
					 qidString = "";
					 qclString = "";
					 qdeString = "";
				 }
		         
		         
		        int counter =0;
		        String clase ="";
 
		    	//lista de columnas
		    	ArrayList<String> cols = new ArrayList<String> ();
		    	cols.add("id");
		    	cols.add("Fecha de registro");
		    	cols.add("Saloon");
		    	cols.add("Cliente");		    	  
		    	cols.add("Dia del evento");
		    	cols.add("Telefono");
		    	ses.setAttribute("cols", cols );
		    	
		    	//lista de campos a imprimir
		    	ArrayList<String> fields = new ArrayList<String>();
		    	fields.add("id");
		    	fields.add("date_reg");
		    	fields.add("saloon_description");
		    	fields.add("client_name");			    	 
		    	fields.add("date_event");
		    	fields.add("client_phone");

		    	ses.setAttribute("fields", fields );
		    	
		    	
		    	//campo que se usara para la etiqueta de accion
		    	String fieldNameToAction = "id";
		    	
 
		    	//lista de acciones de la primera columna  
		        String[] process = new String[20];
		        process[0]="selectQuoteToSale";		 
		        
		        
		        //campo que se envia a la forma
		        String[] fieldname = new String[20];
		        fieldname[0]="id_quote";	
		        		        
		        //etiqueta de accion
		        String[] processTag = new String[20];
		        processTag[0]="Seleccionar";
		        
		      //campos para ignorar para impresion en el gridview
		    	String[]  field= new String[20];
		    	field[0] = "id";
		    	//field[1] = "id_intern";
		    	
		    	
		    	
		    	List<String> ignore = Arrays.asList(field);

		         //lista de parametros 
		 
				MappingDynamicJSON mappingDynamicJSON = new MappingDynamicJSON();
				
				mappingDynamicJSON.setFields("processTag", processTag[0]);
				mappingDynamicJSON.setFields("process",process[0]);
				mappingDynamicJSON.setFields("bean","BeanQuote");
				mappingDynamicJSON.setFields("list","listBean");
				mappingDynamicJSON.setFields("fields","fields");
				//mappingDynamicJSON.setFields("active","Y");//quote no lo tiene
				mappingDynamicJSON.setFields("searchValue",""); 
				mappingDynamicJSON.setFields("searchParam","");
				mappingDynamicJSON.setFields("fieldNameToAction",fieldNameToAction);
				mappingDynamicJSON.setFields("ignore",ignore);
				mappingDynamicJSON.setFields("current",qidString);
				/*
				mappingDynamicJSON.setFields("nestedField1","id");
				mappingDynamicJSON.setFields("nestedField2","id_client");
				mappingDynamicJSON.setFields("nestedFieldOrder","date_reg");
				mappingDynamicJSON.setFields("nestedBean","BeanQuote");
				*/ 
				
				 //end configure

				 
				 
				
				String resltJsonString = new Gson().toJson(mappingDynamicJSON);
				PrintWriter pw=response.getWriter();
				//pw.println(resltJsonString);

				//nombre de la forma
				String formName="quoteForm";
					
					
			  %>


		  <div class="container">
			
			<div class="card  mb-3"  >
			
			  <div class="card-header">
			    <H1> Cambios </H1>
			  </div>
			  <div class="card-body">
			     
			    <h5 class="card-title"><bean:message key="label.quote.select" /> </h5>
			    <p class="card-text">
			    
			    
			     </p>
				     
			<html:form action="/quote">
			<html:hidden property="process" value="quoteToSale"/>
			<html:hidden property="id_quote" value="<%=qidString%>"/>	
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
			
			<li class="list-group-item">			
				<div class="form-row">
				    <div class="col-md-4 mb-3">								      
				      <label for="id_quote"><bean:message key="label.quote.number" /></label>								      	      
				       	
				      <input type="text" value="<%=qidString%>"  disabled="true"  class="form-control"  />					      			      
				      <FONT COLOR="RED"> <html:errors property="id_quote"/></FONT> 
				    </div>	
				    
				    <div class="col-md-4 mb-3">								      
				      <label for="id_quote"><bean:message key="label.client.name" /></label>								      	      
				       	
				      <input type="text" value="<%=qclString%>"  disabled="true"  class="form-control"  />					      			      
				      <FONT COLOR="RED"> <html:errors property="id_quote"/></FONT> 
				    </div>
				    
				    <div class="col-md-4 mb-3">								      
				      <label for="id_quote"><bean:message key="label.quote.date_event" /></label>								      	      
				       	
				      <input type="text" value="<%=qdeString%>"  disabled="true"  class="form-control"  />					      			      
				      <FONT COLOR="RED"> <html:errors property="id_quote"/></FONT> 
				    </div>						 
			    </div>			    
			    <div class="form-row">
				    <div class="col-md-4 mb-3">								      
				      <label for="anotherEmail"><bean:message key="label.email.optional" /></label>								      	      
				      <html:text property="anotherEmail" size="15"  styleClass="form-control"></html:text>							      			      
				      <FONT COLOR="RED"> <html:errors property="anotherEmail"/></FONT> 
				    </div>							 
			    </div>
			    <div class="input-group-append">
				   <button type="submit" class="btn btn-secondary" ><bean:message key="label.continue" />  </button>
				 </div>			 
			</li>
			
			</ul>
			
  
			 

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
