
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML XHTML 1.0 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*,com.eventAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*,com.bituos.*, com.google.gson.Gson"
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
	


	
    <TITLE>
    </TITLE>
    
    
    
</HEAD>

<BODY>

	<H1> <bean:message key="label.user.activate" />  </H1>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 13;
			    
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
		        
		        
		        //configure datagrid
				 
		        int counter =0;
		        String clase ="";
 
		    	//lista de columnas
		    	ArrayList<String> cols = new ArrayList<String> ();
		    	cols.add("id");
		     	cols.add("Nombre del Usuario");
		    	cols.add("Direccion");
		    	cols.add("Correo");
		    	ses.setAttribute("cols", cols );
		    	
		    	//lista de campos a imprimir
		    	ArrayList<String> fields = new ArrayList<String>();
		    	fields.add("id");
		    	fields.add("name");
		    	fields.add("address");
		    	fields.add("email");
		    	ses.setAttribute("fields", fields );
		    	
		    	
		    	//campo que se usara para la etiqueta de accion
		    	String fieldNameToAction = "id";
		    	
 
		    	//lista de acciones de la primera columna  
		        String[] process = new String[20];
		        process[0]="selectUser";		 
		        
		        
		        //campo que senvia a la forma
		        String[] fieldname = new String[20];
		        fieldname[0]="id";	
		        		        
		        //etiqueta de accion
		        String[] processTag = new String[20];
		        processTag[0]="Activar";
		        
		      //campos para ignorar en el gridview
		    	String[]  field= new String[20];
		    	field[0] = "id";
		    	//field[1] = "id_intern";
		    	
		    	
		    	
		    	List<String> ignore = Arrays.asList(field);

		         //lista de parametros 
		 
				MappingDynamicJSON mappingDynamicJSON = new MappingDynamicJSON();
				
				mappingDynamicJSON.setFields("processTag", processTag[0]);
				mappingDynamicJSON.setFields("process",process[0]);
				mappingDynamicJSON.setFields("bean","BeanUser");
				mappingDynamicJSON.setFields("list","listBean");
				mappingDynamicJSON.setFields("fields","fields");
				mappingDynamicJSON.setFields("active","N");
				mappingDynamicJSON.setFields("searchValue",""); 
				mappingDynamicJSON.setFields("searchParam","");
				mappingDynamicJSON.setFields("fieldNameToAction",fieldNameToAction);
				mappingDynamicJSON.setFields("ignore",ignore);
				
				
				
				
				//mappingDynamicJSON.setFields("nestedField1","id");
				//mappingDynamicJSON.setFields("nestedField2","id_client");
				//mappingDynamicJSON.setFields("nestedFieldOrder","date_reg");
				//mappingDynamicJSON.setFields("nestedBean","BeanQuote");
				//mappingDynamicJSON.setFields("beanUser",beanUser);
				
				 //end configure

				 
				 
				
				String resltJsonString = new Gson().toJson(mappingDynamicJSON);
				PrintWriter pw=response.getWriter();
				//pw.println(resltJsonString);
				
				//end configure
				
				//nombre de la forma
				String formName="userActivateForm";
			  %>

			
 
 
  
		 <div class="container">
			
			<div class="card  mb-3"  >
			
			  <div class="card-header">
			    <H1> Activar </H1>
			  </div>
			  <div class="card-body">
			     
			    <h5 class="card-title"><bean:message key="label.client.select" /> </h5>
			    <p class="card-text">
			    
			    
			     </p>
			    <!-- 
			    <a href="#" class="btn btn-primary">Go somewhere</a>
			     -->
			    
						<html:form action="/userActivate">
						<html:hidden property="process" value=""/>
						 <html:hidden property="id" value=""/>
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
								    	 
											  
								    		  if ((counter % 2)==1)						
							          	          clase="AltRowStyle";				
											  else
												  clase="RowStyle";	
											%>				
			    			 
								    			   <tr class="<%=clase%>">
												     <th scope="row">
														  <a href='#button'  class='btn btn-outline-secondary btn-sm' role='button'   onclick='javascript:__doPost("<%=process[0]%>","<bean:write name="id" property="id" />")' ><%=processTag[0]%></a>
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
			   $('#bologna-list a').on('click', function (e) {
				   e.preventDefault()
				   $(this).tab('show')
				 })

				var theForm = document.forms["<%=formName%>"];
				
				function __doPost( eventArgument,value) {
				        
			       theForm.process.value = eventArgument;	        
			
			      
			       if(eventArgument == '<%=process[0]%>')
			       {	           
			           theForm.id.value= value; 	
			                       	           
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
