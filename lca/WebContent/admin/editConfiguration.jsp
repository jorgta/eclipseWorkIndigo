<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*,com.bituos.lca.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"

%>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Configuracion</title>
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
<H1> <bean:message key="label.tire.edit.configuration" />   </H1>
	
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
		        
		        List truckTireConfigurationDetailList = (List)ses.getAttribute("truckTireConfigurationDetailList");
		        
		       // ((BeanTruckTireConfigurationDetail)truckTireConfigurationDetailList.get(0)).getId_truck_tire_configuration();
		        
		        BeanTruckTireConfiguration beanTruckTireConfiguration = ((BeanTruckTireConfigurationDetail)truckTireConfigurationDetailList.get(0)).getId_truck_tire_configuration();
		        if(beanTruckTireConfiguration != null)
		        {
		          axis_number= beanTruckTireConfiguration.getAxis_count();
		          desc = beanTruckTireConfiguration.getDescription();
			      
		        }
		 
		         
		        boolean save = (Boolean) ses.getAttribute( "save" );
		        
		       
		        
		        
		        
		        String ctpString = "2";
		        
		      // out.write(Integer.valueOf(axis));
		        
			  %>
			  
			  
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
        else if(mode == 'edit')  { DoRequest( mode,rid);}			       
      
}
//-->
</script>
			  
			  <html:form action="/editConfiguration" enctype="multipart/form-data">
			  <html:hidden property="process" value=""/>
			  <html:hidden property="id_configuration" value="<%=String.valueOf( beanTruckTireConfiguration.getId()) %>"/>
			  
			   <TABLE border="0" ALIGN="CENTER">
				<TBODY>
								
					<TR>
						<TD><bean:message key="label.configuration.position.description" /></TD>
						<TD><html:text property="description" value="<%=desc%>"></html:text></TD>
			        	<TD> <FONT COLOR="RED"> <html:errors property="description"/></FONT> 
					</TR>
					<TR>
						<TD><bean:message key="label.configuration.axis_count" /></TD>
						<TD><html:text property="axis_count" value="<%=String.valueOf(axis_number )%>"></html:text></TD>
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
							<logic:iterate id="id" indexId="indexId" name="listTireQuantityAxis" scope="session" >
							    <OPTION value= <%="'"%><bean:write name="id" property="quantity" /><%="'"%>  
								    <logic:equal name="id" property="id" value="<%=ctpString%>"> 
								    		SELECTED
								    </logic:equal>>
							    	<bean:write name="id" property="quantity" />
							    </OPTION>
					    	</logic:iterate>						
							</select>	
							
							 
							 
							</TD> 
				       <TD colspan="4"> <FONT COLOR="RED"> <html:errors property="tire_for_axis[<%=index %>]"/></FONT>
						</TR>
			  <%
			 
	             }
	          %>
	          
	          
					
					
					</TBODY>
					</TABLE>
					
								<CENTER><P>
			
			<% if(save == false)
			  {
			%>
			
			<input type="button" name="save" value="<bean:message key="label.update" />" onclick='javascript:sp__doPostBack("edit","","");'> 
			<%
			 
			  }
			else
			{
			%>
			
			<input type="button" name="saveposition" value="<bean:message key="label.configuration.tire.position" />" onclick='javascript:sp__doPostBack("save","","");'> 
			<%			 
			  }			
			%>
			

			</P></CENTER>			
			<CENTER><P><html:button property="finish" onclick="location='/truckAdmin/main.jsp';"> <bean:message key="label.finish" /> </html:button> </P></CENTER>			
			
			
						  <script type="text/javascript">
				//<![CDATA[
				var theForm = document.forms['editConfigurationForm'];
				
				function DoRequest( eventArgument,value) {
				
				        
			       theForm.process.value = eventArgument;	        
				           
			      // theForm.id_configuration.value= value; 		                       	           
		      
			    
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
</html:html>