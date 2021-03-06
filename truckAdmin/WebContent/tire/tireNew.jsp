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

	<title>Seleccionar Veh?culo</title>
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
</HEAD>

<BODY>

	<H1> <bean:message key="label.tire.new" />  </H1>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 91;
			    
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
		        
		        
		        String ctsString = (String) ses.getAttribute( "ctsString" );
		        String ctpString = (String) ses.getAttribute( "ctpString" );
		        String ctmString = (String) ses.getAttribute( "ctmString" );
		        String cttString = (String) ses.getAttribute( "cttString" );
		        
		        
			  %>

                <script type="text/javascript">
				<!--
				function sp__doPostBack(mode,rid,param){
		              
				        if(mode == 'select'){ DoRequest( mode,rid);}			     
				        else if(mode == 'add')  { DoRequest( mode,rid);}			       
				        
				}
				//-->
				</script>

			<html:form action="/tireNew" enctype="multipart/form-data">
			<html:hidden property="process" value=""/>
			<TABLE ALIGN="CENTER" border="0" >
				<TBODY>
			

					
							
					<tr>
					<td colspan="3">
					  <br>
					</td>
					</tr>
					
			
					<TR bgcolor="lightblue">
					<td colspan="4" align="center" >					 
					 <div class="x-blue_dg_caption"> Datos del Neum?tico   </div>					 
					</td>
					</TR>
					
					<tr><td colspan="4">
					  <br>
					</td></tr>

					<TR>
						<TD><bean:message key="label.tire.design" /></TD>
						<TD><html:text property="design" size="60"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="design"/></FONT> 
					</TR>
					<script type="text/javascript">
                      function showTextDeep()
                      {
                    	 
                    	 // alert(document.getElementsByName("deep")[0].value);
                    	  
                    	 document.getElementsByName("deep")[0].disabled = false; 
                      }
					</script>
				
					<TR>
						<TD><bean:message key="label.tire.deep" /></TD>
						<TD>
						    
						    
						    <html:select property="id_type_unit_of_measure" onclick="showTextDeep();">						
							<logic:iterate id="id" indexId="indexId" name="listUnitOfMeasure" scope="session" >
							    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>>
							    	<bean:write name="id" property="description" />
							    </OPTION>
					    	</logic:iterate>						
							</html:select>		
						    
						 <html:text property="deep" size="10" disabled="true"></html:text>
						
						
						</TD>
						
			        	<TD> <FONT COLOR="RED"> <html:errors property="deep"/></FONT> 
					</TR>
				
		
					<TR>
						<TD><bean:message key="label.tire.serial_number" /></TD>
						<TD><html:text property="serial_number" size="40"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="serial_number"/></FONT> 
					</TR>
					
						<TR>
						<TD><bean:message key="label.tire.km_int" /></TD>
						<TD><html:text property="km_int" size="40"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="km_int"/></FONT> 
					</TR>
					
					<TR>
						<TD><bean:message key="label.tire.status" /></TD>
						<TD>
					   		<html:select property="id_type_tire_status">						
							<logic:iterate id="id" indexId="indexId" name="listTypeTireStatus" scope="session" >
							    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
								    <logic:equal name="id" property="id" value="<%=ctsString%>"> 
								    		SELECTED
								    </logic:equal>>
							    	<bean:write name="id" property="description" />
							    </OPTION>
					    	</logic:iterate>						
							</html:select>						
						    
                       </TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="id_type_tire_status"/></FONT> 
					</TR>
					
	
					<TR>
						<TD><bean:message key="label.tire.measure" /></TD>
						<TD>
					   		<html:select property="id_type_measure">						
							<logic:iterate id="id" indexId="indexId" name="listTypeMeasure" scope="session" >
							    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  
								    <logic:equal name="id" property="id" value="<%=ctmString%>"> 
								    		SELECTED
								    </logic:equal>>
							    	<bean:write name="id" property="description" />
							    </OPTION>
					    	</logic:iterate>						
							</html:select>						
						    
                       </TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="id_type_measure"/></FONT> 
					</TR>

						
							
						
						
					
					
				</TBODY>
			</TABLE>
			
			<!-- END This code was generated by datagrid.class.php v7.8.x (http://www.apphp.com/php-datagrid/index.php) END -->
			
			<CENTER><P>
			<input type="button" name="add" value="<bean:message key="label.register" />" onclick='javascript:sp__doPostBack("add","","");'> 
			
			

			</P></CENTER>			
			<CENTER><P><html:button property="finish" onclick="location='/truckAdmin/main.jsp';"> <bean:message key="label.finish" /> </html:button> </P></CENTER>			
			
			<script type="text/javascript">
				//<![CDATA[
				var theForm = document.forms['tireNewForm'];
				
				function DoRequest( eventArgument,value) {
				
				        
			       theForm.process.value = eventArgument;	        
				           
			      // theForm.id_truck.value= value; 		                       	           
		      
			    
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
	
</BODY>

<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">

  document.getElementById("design").focus();
  
//-->
</SCRIPT>

</html:html>
