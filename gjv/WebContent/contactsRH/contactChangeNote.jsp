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




<%@page import="com.bituos.gjv.forms.ContactChangeForm"%>
<html:html>
<HEAD>

	<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	
	<META http-equiv="Content-Style-Type" content="text/css">
	<script language='javascript' src="/gjv/calendar/popcalendar.js"></script> 
	<script language='javascript' src="/gjv/calendar/popcalendar.js"></script> 
	<link rel="stylesheet" type="text/css" href="/gjv/theme/screen.css">
	<link rel="stylesheet" type="text/css" href="/gjv/theme/dropdown.css">
	<script type="text/javascript" src="/gjv/theme/helpers.js"></script>
	<script type="text/javascript" src="/gjv/theme/date.js"></script>
	<script type="text/javascript" src="/gjv/theme/form.js"></script>
	<LINK href="/gjv/theme/grid.css" rel="stylesheet" type="text/css">
    <TITLE>Cambios a usuarios
    </TITLE>
</HEAD>

<BODY style="margin: 1cm; margin-top: 0.2cm; margin-right: 1.5cm" onLoad="document.forms[0].filter.value='';">

<script language="JavaScript">
function selectContact(id,proc)
{	
   //alert(proc +" "+ id);
   document.forms[1].process.value= proc;
   
   if(proc=='select')   
      document.forms[1].idContact.value = id; 
      
   if(proc=='selectNote' || proc=='change')  
       document.forms[1].idNote.value = id;
  
 // alert(document.forms[1].idNote.value);
 // alert(document.forms[1].idContact.value);
  
  
   document.forms[1].submit();
  
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
			    
			    int TAG = 26;
			    
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
		        
		        
		        String casado = (String) ses.getAttribute( "casado" );
			  %>

		
	
	        <html:form action="/search"  style="width: 100%">	
	         <fieldset class="login" >
			    <strong>
			    <legend><bean:message key="label.filter" /></legend> 
			    </strong> 
			<TABLE width="90%" border="0" cellspacing="0" cellpadding="0"
					align="center" bordercolor="1px #000000 solid">
				<TBODY >
				
					<TR align="left">
						<TD colspan="0">
						   <html:hidden property="concept" value="contact"/>
						   <html:hidden property="forward" value="noteChange"/>
						   
						   <html:text property="filter"></html:text>
						</TD>
			        	<TD><FONT COLOR="red"> <html:errors property="filter"/></FONT></TD> 
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
			    <legend><bean:message key="label.contact.list" /></legend> 
			    </strong>    				

			<div class="grid-header-button grid-header-button;">	
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
						<table class="grid" align="left">		  
						  <tr class="rowHead rowHead rowFoot">	     
						    
						     <th width="19%" class="first-cell"><bean:message key="label.contact.first_name"/></th>
						     <th width="19%"><bean:message key="label.contact.last_name" /></th>
						     <th width="19%"><bean:message key="label.contact.address" /></th>	
						     <th width="19%"><bean:message key="label.contact.colony" /></th>
						     <th width="19%"><bean:message key="label.contact.phone" /></th>
						  </tr>
						  </table>
						  <div style="overflow: scroll; height: 150px; width: 100%">
						   <table class="grid" style="width: 100%">	
						    <% Integer  counter = 0; 
	           				  String clase;
	           			   %>
	           			   <logic:iterate id="id" indexId="indexId" name="filterList" scope="session">
                    		<bean:define id="contacto" name="id" property="id"></bean:define>
							<logic:notEqual name="id" property="id" value="0"> 
			                      <%
			            	     if ((counter % 2)==1)						
			          	        	clase="rowEven";						
								 else
									clase="rowOdd";
								 %>						
								  <tr class="<%=clase%>">									 
									  <td width="19%">
									     <a href="#" onClick="selectContact('<bean:write name="id" property="id"/>','select')"> 
								  			<bean:write name="id" property="first_name" />
								  	    </a>
								  	  </td>
									  <td width="19%"><bean:write name="id" property="last_name" /></td>
									  <td width="19%"><bean:write name="id" property="address" /></td>
									  <td width="19%"><bean:write name="id" property="colony" /></td>	
									  <td width="19%"><bean:write name="id" property="phone" /></td>								     
								  </tr>		
								   <%counter++;%>						      
								</logic:notEqual>
							</logic:iterate>    
				</table>
			  </div>
			</div>			
			</fieldset>				
			</html:form>
			
			
			<%
			BeanContactRH beanContactRH= (BeanContactRH)((List)ses.getAttribute("filterList")).get(0);
			BeanContactRH beanContact= (BeanContactRH)(ses.getAttribute("BeanContactRH"));
			
			String id;
			if(beanContactRH.getId()!=0)
			{
				id =String.valueOf( beanContact.getId());
			%>

			<html:form action="/contactNoteChangeAction" style="width: 100%">
			   <html:hidden property="idContact" value="<%=id%>"/>
			   <html:hidden property="process" value="change"/>
			   <html:hidden property="idNote" value=""/>
			   <fieldset class="" >
			     <strong>
			     <legend><bean:message key="label.contact.listNotes" /></legend> 
			     </strong>    				

			      <div class="grid-header-button grid-header-button;">			  
						<table class="grid" align="left">		  
						  <tr class="rowHead rowHead rowFoot">	
						     <th width="19%"><bean:message key="label.contact" /></th>	
						     <th width="19%" class="first-cell"><bean:message key="label.contact.fechaNote"/></th>
						     <th width="19%"><bean:message key="label.contact.note" /></th>						      
						  </tr>
						</table>
						<div style="overflow: scroll; height: 150px; width: 100%">
						   <table class="grid" style="width: 100%">	
						    <%Integer counterNote = 0; 
	           				  String claseNote="";
	           			   %>
	           			   <logic:iterate id="idNotes" indexId="indexId" name="notesList" scope="session">
                    		
							<logic:notEqual name="idNotes" property="id" value="0"> 
			                      <%
			            	     if ((counterNote % 2)==1)						
			            	    	 claseNote="rowEven";						
								 else
									 claseNote="rowOdd";
								 %>						
								  <tr class="<%=claseNote%>">									 
									  <td width="19%">
									     <a href="#" onClick="selectContact('<bean:write name="idNotes" property="id"/>','selectNote')"> 
								  			<bean:write name="idNotes" property="idContactRH.first_name" />
								  	    </a>
								  	  </td>	
								  	   <td width="19%"><bean:write name="idNotes" property="fecha" /></td>								  
									  <td width="19%"><bean:write name="idNotes" property="note" /></td>
									 	
									 								     
								  </tr>		
								   <%counterNote++;%>						      
								</logic:notEqual>
							</logic:iterate>    
						</table>
				  </div>
				  </div>
			 </fieldset>
			   
			    <%
                String noteId= String.valueOf(ses.getAttribute("noteId"));
			    if(!noteId.equals("0"))
			    {
                %>
			   <fieldset class="" >
			    <strong>
			    <legend>Detalles del Contacto</legend> 
			    </strong>
					<div>		
			    		<strong>
					       <font color="#000000" size="3px"><bean:message key="label.contact.first_name" />:
					       </font>
					   </strong>	
			    		<bean:write name="BeanContactRH" property="first_name" scope="session"/>					
						<FONT COLOR="FF0000"> <html:errors property="first_name" /></FONT>
					</div>
					
					<div>		
			    		<strong>
					       <font color="#000000" size="3px"><bean:message key="label.contact.last_name" />:
					       </font>
					   </strong>	
			    		<bean:write name="BeanContactRH" property="last_name" scope="session"/>					
						<FONT COLOR="FF0000"> <html:errors property="last_name" /></FONT>
					</div>
					
				<fieldset class="login" >
			    <strong>
			    <legend>Detalle de la Nota</legend>
			    </strong>
			    <div>	
			      <strong>
					<font color="#000000" size="3px">
					
				  </font>
				  </strong>				  
			      <html:textarea  property="note"  style="width: 600px;
						height: 120px;
						border: 3px solid #cccccc;
						padding: 5px;
						font-family: Tahoma, sans-serif;
						background-image: url(bg.gif);
						background-position: bottom right;
						background-repeat: no-repeat;"></html:textarea>
			      <FONT COLOR="FF0000"> <html:errors property="note" /></FONT>
			    </div>
				<div class="date">	
			      <strong>
					<font color="#000000" size="3px">Fecha
					  </font>
					</strong>	
			    	<html:text  maxlength="10"  property="fecha"  styleClass="default"></html:text>	
			    						
					<FONT COLOR="FF0000"> <html:errors property="fecha" /></FONT>
				</div>
			    
			    </fieldset>	
			    <CENTER>      
               <P>
               
        	      <input type="button" value="Guardar Cambios" onClick="selectContact(<%=noteId%>,'change')"/>
      		   </P>
	    	   </CENTER>
			</fieldset>
			 <%
			}
             %>
               			
				
			</html:form>
            <%} %>
		  </logic:present>
		  
		  <logic:notPresent name="processList" scope="session">
		     <logic:redirect href="../login.jsp"> </logic:redirect>
		  </logic:notPresent>
	  
	</logic:present>
	
	<logic:notPresent name="beanUser" scope="session">
	   <logic:redirect href="../login.jsp">  </logic:redirect>
	</logic:notPresent>
	
	<br>
	<br>
	<br>
	<br>
	<br>
	<hr>
	
</BODY>
</html:html>
