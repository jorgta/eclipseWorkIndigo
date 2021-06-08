
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML XHTML 1.0 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page session="true" import="java.util.*,com.bituos.truckAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
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
var container;

var mygrid1;
			
$(document).ready(
function() {
		
		// create the grid  ?
		// returns a jQ object with a 'g' property - that's ingrid
		 mygrid1 = $("#table1").ingrid({ 
										url:'data.jsp?bean=BeanUser&list=listUserActive&fields=fields&active=N',
										height: 350,
										savedStateLoad: true,
										rowClasses: ['grid-row-style1','grid-row-style2','grid-row-style3','grid-row-style1','grid-row-style1','grid-row-style3'],
										sorting: true,
										colSortParams: ['id','name','active','address','email'],										
										onRowSelect: function(tr, selected){											
											var str 		= selected ? 'SELECTED' : 'UNSELECTED';
											var tr_html	= $(tr).html();											
											//alert( str + ' Row. InnerHTML is : ' + tr_html);	
											/*var txt = document.forms[0].id;											
											var td= $(tr).children(":first-child");											
											var str = td.text();
 											str = str.replace(/^\s*|\s*$/g,"");
										    txt.value=str;	*/	
										    
										},
										rowSelection: true
																					
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
							


//FAjax('ajax.php','capaContenedora','campo1='+document.getElementById('campo1').value+'&amp;campo2='+document.getElementById('campo2').value,'POST');

 function creaAjax(){
  var objetoAjax=false;
  try {
   /*Para navegadores distintos a internet explorer*/
   objetoAjax = new ActiveXObject("Msxml2.XMLHTTP");
  } catch (e) {
   try {
     /*Para explorer*/
     objetoAjax = new ActiveXObject("Microsoft.XMLHTTP");
     } 
     catch (E) {
     objetoAjax = false;
   }
  }

  if (!objetoAjax && typeof XMLHttpRequest!='undefined') {
   objetoAjax = new XMLHttpRequest();
  }
  return objetoAjax;
}
 var tableId;
 
function FAjax(url,metodo)
{
  // 'data.jsp?bean=BeanUser&list=listUserDelete&fields=fields'
  
   var ajax=creaAjax();
  
  if (metodo.toUpperCase()=='GET'){
    document.getElementById('capa').style.visibility= "visible";
    ajax.open ('GET', url, true);
    ajax.onreadystatechange = function() {
         if (ajax.readyState==1) {
                 document.getElementById('capa').innerHTML="Cargando.......";
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
   
	
 
}


function hiddeCapa()
{
  document.getElementById('capa').style.visibility= "hidden";
}

</SCRIPT>


	
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
			  %>

			

			<html:form action="/userActivate">
			<html:hidden property="id" value=""/>
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD>
						

						
						
							<!--<html:text property="id"  styleClass="form-login" size="30" maxlength="2048"></html:text>-->
						</TD>
						
					</TR>
					<TR>
					    <TD>
					    <!--<html:submit ><bean:message key="label.activate" /></html:submit>-->
					       <!-- <button class="button icon approve" type="submit"  ><bean:message key="label.activate" /></button>--> 
						 
						</TD>
					</TR>
					<TR>
						<TD>
			            <!--<html:button property="finish" onclick="location='/truckAdmin/main.jsp';"> <bean:message key="label.finish" /> </html:button>-->

						<!--<a href="#" class="button danger" onclick="location='/truckAdmin/main.jsp';" ><bean:message key="label.finish" /></a>-->
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

<script>
  function sendForm(val)
  {
    
     document.forms[0].id.value=val;
	 document.forms[0].submit();
	 
	
  }
</SCRIPT>



	 
	 
	 <table>
<tr>
<td>Buscar


</td>
 <td >
  <input title="Teclee aqui para buscar" type="text" id="name" value=""  onkeyup="FAjax('data.jsp?bean=BeanUser&list=listUserActive&fields=fields&searchValue='+this.value+'&searchParam=name&active=N','GET');"/>

</td>
<td><a href="#" class="button danger" onclick="location='/truckAdmin/main.jsp';" ><bean:message key="label.finish" /></a></td>
</tr>
<tr>
<td colspan="3">	
	 <div id="capa"  style="overflow: scroll; height: 150px; width: 500px; position: absolute; z-index: 5000; background-color: silver; visibility: hidden;">

	 </div>
</td>
</tr>
</table> 
	 

<div id="">
<TABLE id="table1">
  <THEAD>
    <TR>
       <TH >Id</TH>
      <TH ><bean:message key="label.name" /></TH>
      <TH ><bean:message key="label.active" /></TH>
	  <TH ><bean:message key="label.address" /></TH>
	  <TH ><bean:message key="label.email" /></TH>	
	</TR>
    
    </THEAD>
    <TBODY>

   
    </TBODY>
	</TABLE>
	</div>
	
			
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
