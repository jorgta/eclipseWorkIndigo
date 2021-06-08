<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML XHTML 1.0 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*,com.eventAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"

%>




<html:html>
<HEAD>

	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM WebSphere Studio">
	
	<META http-equiv="Content-Style-Type" content="text/css">
		<link rel="stylesheet" href="theme/gh-buttons.css">
	
	
	
	<STYLE type="text/css" media="all">@import "css/site.css";</STYLE>
<!-- include jquery lib -->
<SCRIPT type="text/javascript" src="ingridTheme/jquery.js"></SCRIPT>
<!-- include ingrid lib -->
<SCRIPT type="text/javascript" src="ingridTheme/jquery.ingrid.js"></SCRIPT>
<!-- ingrid default stylesheet -->
<STYLE type="text/css" media="all">@import "ingridTheme/ingrid.css";</STYLE>
<!-- to make ingrid save her state (selected rows, page number, column sort & direction); just include the jQ cookie plugin -->
<SCRIPT type="text/javascript" src="ingridTheme/jquery.cookie.js"></SCRIPT>
<!-- initialize -->
<SCRIPT type="text/javascript">
$(document).ready(
	function() {
		
		// create the grid
		// returns a jQ object with a 'g' property - that's ingrid
		var mygrid1 = $("#table1").ingrid({ 
										url: 'data.jsp?list=listUserChange&cols=cols&fields=fields&action=pre.do&process=userChange',
										height: 350,
										savedStateLoad: true,
										rowClasses: ['grid-row-style1','grid-row-style1','grid-row-style2','grid-row-style1','grid-row-style1','grid-row-style3'],
										onRowSelect: function(tr, selected){											
											var str 		= selected ? 'SELECTED' : 'UNSELECTED';
											var tr_html	= $(tr).html();											
											//alert( str + ' Row. InnerHTML is : ' + tr_html);	
											var txt = document.forms[0].id;
											
											var td= $(tr).children(":first-child");
											
											//alert( td.text());
											var str = td.text();
 											str = str.replace(/^\s*|\s*$/g,"");
										    txt.value=str;
										    																			
										}										
									});
		

		$('#jump20').click(function(){
			// the 'g' object is ingrid - call methods like so:
			mygrid1.g.p.setPage(20)
		});
		
		$('#sel-rows').click(function(){
			// the 'g' object is ingrid - call methods like so:
			var rows = mygrid1.g.getSelectedRows();			
			for (i=0; i<rows.length; i++) {				
				var str = 'SELECTED ROW ' + i + " - InnerHTML is : \n";
				alert( str + $(rows[i]).html() );
				//alert("2");	
			}			
		});
				
	}
); 
</SCRIPT>
	
    <TITLE>
    </TITLE>
</HEAD>

<BODY>

	<H1> <bean:message key="label.user.select" />  </H1>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 11;
			    
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
			  %>

			

			<html:form action="/userSelect">
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD>						
						<html:select property="id">
						
						<logic:iterate id="id" indexId="indexId" name="listUserChange" scope="session" >
						    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>  >  <bean:write name="id" property="name" /> </OPTION>
					    </logic:iterate>

						
						</html:select> 
							
						</TD>
						
					</TR>
					<TR>
					    <TD>
					    <button class="button icon edit" type="submit"  ><bean:message key="label.select" /></button> 
					   
						</TD>
					</TR>
					<TR>
						<TD>
			            
						<a href="#" class="button danger" onclick="location='/eventAdmin/main.jsp';" ><bean:message key="label.finish" /></a>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			
			<%
	//lista de columnas
	ArrayList cols = new ArrayList();
	cols.add("Id");
	cols.add("Nombre");
	cols.add("Activo");
	cols.add("Direccion");
	cols.add("Email");
	ses.setAttribute("cols", cols );
	
	//lista de campos a imprimir
	ArrayList fields = new ArrayList();
	fields.add("id");
	fields.add("name");
	fields.add("active");
	fields.add("address");
	fields.add("email");
	ses.setAttribute("fields", fields );
	
	%>		
 <TABLE id="table1">
  <THEAD>
  <TR>
  <TH>id</TH>
    <TH><bean:message key="label.name" /></TH>
    <TH><bean:message key="label.active" /></TH>
    <TH><bean:message key="label.address" /></TH>
    <TH><bean:message key="label.email" /></TH></TR></THEAD>
    <TBODY>
 
  <logic:iterate id="id" indexId="indexId" name="listUserChange"  scope="session" >
 	<TR>
 	   
 	   <logic:iterate id="idfields" indexId="indexId" name="fields"  scope="session" >
         <bean:define id="fld" name="idfields" > </bean:define>
          <TD>   
          <logic:notEqual name="id" property="<%=((String) fld)%>" value="">         
     	       <bean:write name="id" property="<%=((String) fld)%>" /> 
     	     </logic:notEqual>
     	     
            <logic:equal name="id" property="<%=((String) fld)%>" value="">         
     	       --------------- 
     	     </logic:equal>
     	      
          </TD>
        </logic:iterate>
    </TR>				    
	</logic:iterate>

   
    </TBODY>
	</TABLE>
			
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

  document.getElementById("id").focus();
  
//-->
</SCRIPT>

</html:html>
