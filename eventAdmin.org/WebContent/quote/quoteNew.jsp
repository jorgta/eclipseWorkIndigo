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
	<META name="GENERATOR" content="IBM WebSphere Studio">
	
	<META http-equiv="Content-Style-Type" content="text/css">
	<LINK href="/eventAdmin/theme/Master.css" rel="stylesheet" type="text/css">
	
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
			         
			         
		         
			  %>

			<html:form action="/search">
			 <html:hidden property="concept" value="quote"/>
			 <html:hidden property="forward" value="new"/>
			 <html:hidden property="aditional1" value=""/>

		<%if(visibleUserData != "yes")
			{
			%>

			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD colspan="3" align="center">
							<H4><bean:message key="label.search" /></H4>
						</TD>
					</TR>
					<TR>
						<TD class="column_field">
						   <bean:message key="label.name" />
						</TD>
						<TD>						   
						   <html:text property="filter"></html:text>
						</TD>
						<TD>
					    	<html:submit onclick="aditional1.value='name';"><bean:message key="label.search" /></html:submit>
						</TD>
					</TR>
					<TR>
						<TD class="column_field">
							<bean:message key="label.rfc" />
						</TD>
						<TD>						   
						   <html:text property="filter2"></html:text>
						</TD>
						<TD>
					    	<html:submit onclick="aditional1.value='rfc';"><bean:message key="label.search" /></html:submit>
						</TD>
			        	
					</TR>
					<TR>
						<TD class="column_field">
						  	<bean:message key="label.address" />
						</TD>
						<TD>						   
						   <html:text property="filter3"></html:text>
						</TD>
						<TD>
					    	<html:submit onclick="aditional1.value='address';"><bean:message key="label.search" /></html:submit>
						</TD>
					</TR>
					<TR>
						<TD class="column_field">
						  	<bean:message key="label.phone" />
						</TD>
						<TD>						   
						   <html:text property="filter4"></html:text>
						</TD>
						<TD>
					    	<html:submit onclick="aditional1.value='phone';"><bean:message key="label.search" /></html:submit>
						</TD>
					</TR>
					<TR>
						<TD></TD>
						<TD colspan="2">					
						    <TABLE border="1" class="table_campo">	
						      <TR class="table_encabezado">
						        <TD><bean:message key="label.client.number" /><TD>
						        <TD><bean:message key="label.name" /><TD>
						        <TD><bean:message key="label.phone" /><TD>
						        <TD><bean:message key="label.address" /><TD>
						      </TR>
							  <logic:iterate id="id" indexId="indexId" name="filterList" scope="session" >
							      <TR>
							        <TD> <bean:write name="id" property="id_intern" /> <TD>
							        <TD> <bean:write name="id" property="name" /> <TD>
							        <TD> <bean:write name="id" property="phone" /> <TD>
							        <TD> <bean:write name="id" property="address" /> <TD>
							      </TR>
					    	  </logic:iterate>						
						    </TABLE>	
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			
		    <%
	        }
			%>
			</html:form>
			<HR>
			
			
<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">


  
//-->
</SCRIPT>
			
			<html:form action="/quoteNew" onsubmit="sbt();">
			<input type="hidden" name="process" value="" />
			<input type="hidden" name="checkUserData" value="NO" />
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD class="column_field"><bean:message key="label.client.number" /></TD>
						<TD>
							<%if(visibleUserData == "yes")
								{
								%>
									<html:text property="id_client" value='<%=clientNumber%>' size="15" disabled="true"></html:text>
									<%=clientName%>
								<%  
								}
							  else
								{
								%>
									<html:text property="id_client" size="15" disabled="false"></html:text>
									<html:submit onclick="process.value='validateClient';checkUserData.value='no';"> <bean:message key="label.validate" /> </html:submit>
								<%  
								}
							%>
						</TD>
			        	<TD width="346">
			        	 	<FONT COLOR="RED"> <html:errors property="id_client"/></FONT>
			        	</TD>
					</TR>
					
				<%if(visibleUserData == "yes")
					{
					%>
					<TR>
						<TD class="column_field">
							  <bean:message key="label.event.date" />
						</TD>	
						<TD>
							  <html:text property="date_event" readonly="true" onclick="javascript:showCal('calendar1');"> </html:text>
							  <A href="javascript:showCal('calendar1')"><IMG src="/eventAdmin/Calendar/calendar.png" border="0" /></A>
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
						.
						</TD>
					</TR>
					<TR> 
					    <logic:present name="qnCarListProduct" scope="session">
							<TD>
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
						.
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
					    <TD class="column_field">
					    	<bean:message key="label.notes" />
					    </TD>
						<TD>						   
						   <html:textarea property="notes" cols="50" rows="20"></html:textarea>
						   <FONT COLOR="RED"> <html:errors property="notes"/></FONT>
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

 if ( document.getElementById("id_client").disabled == false )
   document.getElementById("id_client").focus();
 else
   document.getElementById("hourQuantity").focus();
 
 function sbt()
   {
     if( document.forms[1].process.value == 'calendar')
       document.forms[1].target='_blank';
   }
  
//-->
</SCRIPT>
</html:html>
