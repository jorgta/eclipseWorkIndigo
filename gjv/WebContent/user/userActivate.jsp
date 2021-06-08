<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@ page session="true" import="java.util.*, com.bituos.gjv.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"

%>




<html:html>
<HEAD>

	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META http-equiv="Content-Style-Type" content="text/css">
	<script language='javascript' src="/gjv/calendar/popcalendar.js"></script> 	
	<link rel="stylesheet" type="text/css" href="/gjv/theme/screen.css">
	<link rel="stylesheet" type="text/css" href="/gjv/theme/dropdown.css">
	<script type="text/javascript" src="/gjv/theme/helpers.js"></script>
	<script type="text/javascript" src="/gjv/theme/date.js"></script>
	<script type="text/javascript" src="/gjv/theme/form.js"></script>
	<LINK href="/gjv/theme/grid.css" rel="stylesheet" type="text/css">
	
	
    <TITLE>Activar Usuario</TITLE>
</HEAD>

<BODY style="margin: 1cm; margin-top: 0.2cm; margin-right: 1.5cm">

<script language="JavaScript">
function selectUser(id)
{
	
	for(var i =0; i < document.forms[1].length; i++)
	{ //alert(document.forms[1].elements[i].type=='text');
	  if(document.forms[1].elements[i].type=='text')
	     document.forms[1].elements[i].value=id;
	}
	  
	 
	 
}
</script>

<script>
   var varTD=null;
   var varIndex=-1;
  function over(td)
  {
    td.className = "tagTdOver";
  }
  
  function out(td)
  {
    if(td.className != "tagTdSelected")
	{
	 td.className = "tagTd";
	// varTD.className ="tagTd";
	}
  }
  
  function selected(td)
  {
    td.className = "tagTdSelected";	
	if(varTD != null)
	  if(td.cellIndex!=varTD.cellIndex)
	     varTD.className = "tagTd";
	 
	 
	 varTD=td;
	 //alert(td.outerText);
	 document.forms[0].filter.value=td.outerText;
	 document.forms[0].submit();
	 
	// varTD.className = "tagTd";
  }
</script>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 14;
			    
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
              
              
         // String userSeleced=(String) ses.getAttribute("userSeleced");
			  %>

			<html:form action="/search" style="width: 100%">
			<html:hidden property="process" value="" />
			<fieldset class="login" >
			    <strong>
			    <legend><bean:message key="label.filter" /></legend> 
			    </strong>  
			    <TABLE width="90%" border="0" cellspacing="0" cellpadding="0"
					align="center" bordercolor="1px #000000 solid">
				<TBODY >					
					<TR align="left">
						<TD colspan="0">
						   <html:hidden property="concept" value="user"/>
						   <html:hidden property="forward" value="activate"/>
						   
						   <html:text property="filter"></html:text>
						</TD>
			        	<TD><FONT COLOR=RED> <html:errors property="filter"/></FONT></TD> 
					</TR>
					<TR >
					    <TD>					    	
                			<input type="submit" value=" Buscar "/>                
						</TD>
					</TR>
				</TBODY>
			    </TABLE>
			</fieldset>	
						

              <fieldset class="" >
			    <strong>
			    <legend><bean:message key="label.user.list" /></legend> 
			    </strong>         
            	  	  <table    border="0" cellpadding="0" cellspacing="0" class="tags" align="center" >
					  <tr>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">A</strong> </td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">B</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">C</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">D</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">E</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">F</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">G</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">H</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">I</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">J</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">K</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">L</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">M</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">N</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">Ñ</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">O</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">P</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">Q</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">R</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">S</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">T</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">U</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">V</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">W</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">X</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">Y</strong></td>
					    <td class="tagTd" align="center" onClick="selected(this);" onMouseOver="over(this);" onMouseOut="out(this);"><strong class="labelABC">Z</strong></td>
					  </tr>
					</table>
			        
					  <div style="overflow: scroll; height: 150px; width: 100%">

				
					<table class="grid" align="left" style="width: 100%">		  
						<tr class="rowHead rowHead rowFoot">    
						     <th width="10%" class="first-cell">*</th>						     
						     <th width="15%"><bean:message key="label.user"/></th>
						     <th width="15%"><bean:message key="label.full.name"/></th>
						     <th width="15%"><bean:message key="label.address" /></th>	
						     <th width="15%"><bean:message key="label.rfc" /></th>	
						     <th width="15%"><bean:message key="label.email" /></th>						     
						 </tr>
					</table>
						   <table class="grid" style="width: 100%">	
						    <% Integer  counter = 0; 
	           				  String clase;
	           			   %>
	           			   <logic:iterate id="id" indexId="indexId" name="filterList" scope="session">
                    		<bean:define id="usuario" name="id" property="id"></bean:define>
							<logic:notEqual name="id" property="id" value="0"> 
			                     <%
			            	     if ((counter % 2)==1)						
			          	        	clase="rowEven";						
								 else
									clase="rowOdd";
								 %>						
								  <tr class="<%=clase%>">									 
									  <td width="10%">
									    <a href="#"; onclick="selectUser('<bean:write name="id" property="id"/>')"> 
								  			<font color="#000000" size="1px">Seleccionar</font> 
									    </a>
								  	  </td>								  	  
								  	  <td width="15%"><bean:write name="id" property="name" /></td>
									  <td width="15%"><bean:write name="id" property="full_name" /></td>									 
									  <td width="15%"><bean:write name="id" property="address" /></td>	
									  <td width="15%"><bean:write name="id" property="rfc" /></td>	
									  <td width="15%"><bean:write name="id" property="email" /></td>								     
								  </tr>		
								  <%counter++;%>						      
								</logic:notEqual>
							</logic:iterate>    
							</table>
						  </div>
				
			  </fieldset>			
              
                      
			
			
			</html:form>
			

			<html:form action="/userActivateAction" style="width: 70%">
			<html:hidden property="process" value="userActivate" />
			<fieldset class="login" >
			    <strong>
			    <legend><bean:message key="label.user.activate" /></legend> 
			    </strong> 
			    <div>		
			    		<strong>
					       <font color="#000000" size="3px"><bean:message key="label.user.number" />
					       </font>
					   </strong>	
			    		<html:text property="id" value="" size="9" ></html:text>			
						<FONT COLOR="FF0000"> <html:errors property="id" /></FONT>
				</div>
				<CENTER> 
					<input type="submit" value=" Activar " />
				</CENTER> 
				</fieldset>
				

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
</html:html>
