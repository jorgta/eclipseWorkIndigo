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
<BODY>

	<CENTER><H1> <bean:message key="label.list.prices" />  </H1></CENTER>
	
	<logic:present name="beanUser" scope="session">
	
		  <logic:present name="processList" scope="session">
			  <%
			    HttpSession ses = request.getSession();
			    BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
			    
			    int TAG = 51;
			    
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
		         
		         String visibleData = (String)ses.getAttribute("visibleData");   
		         
		         BeanList listEdit_currentList = (BeanList) ses.getAttribute( "listEdit_currentList" );							    
		         BeanListOptions listEdit_currentOption = (BeanListOptions) ses.getAttribute( "listEdit_currentOption" );							    
		         BeanListOptionMenu listEdit_currentMenu = (BeanListOptionMenu) ses.getAttribute( "listEdit_currentMenu" );							    
				 String clString;
				 String coString;
				 String cmString;
				 
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
			  %>

			
			<html:form action="/listEdit">
			<input type="hidden" name="process" value="" />
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
					    <TD colspan="6" class="column_field"><bean:message key="label.list.prices" /></TD>
					</TR>
					<TR>
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
			        		<FONT COLOR="RED"> <html:errors property="id_list"/></FONT> 
						</TD>
						<TD>
							<html:submit onclick="process.value='selectList';"> <bean:message key="label.select" /> </html:submit>
						</TD>
						<TD>
							<html:submit onclick="process.value='deleteList';"> <bean:message key="label.delete" /> </html:submit>
						</TD>
					    <TD class="column_field"><bean:message key="label.description" /></TD>
						<TD>
							<html:text property="listDescription" size="20" disabled="false"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="listDescription"/></FONT> 
						</TD>
						<TD>
							<html:submit onclick="process.value='createList';"> <bean:message key="label.list.create" /> </html:submit>
						</TD>
						<TD>
						</TD>
					</TR>
					<TR>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
							<html:submit onclick="process.value='updateList';"> <bean:message key="label.update.data" /> </html:submit>
						</TD>
						<TD>
						</TD>
					</TR>
					<TR>
					    <TD colspan="6" class="column_field"><bean:message key="label.list.options" /></TD>
					</TR>
					<TR>
						<TD>
							<html:select property="id_option" onchange="process.value='selectListOption'; submit();">						
								<logic:iterate id="id" indexId="indexId" name="listOptionsList" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%>    
									    <logic:equal name="id" property="id" value="<%=coString%>"> 
									    		SELECTED
									    </logic:equal>>
								    	<bean:message key="label.between" /> <bean:write name="id" property="min" /> <bean:message key="label.and" /> <bean:write name="id" property="max" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
			        		<FONT COLOR="RED"> <html:errors property="id_option"/></FONT> 
						</TD>
						<TD>
							<html:submit onclick="process.value='selectListOption';"> <bean:message key="label.select" /> </html:submit>
						</TD>
						<TD>
							<html:submit onclick="process.value='deleteOption';"> <bean:message key="label.delete" /> </html:submit>
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
					</TR>
					<TR>
					    <TD></TD>
					    <TD></TD>
					    <TD></TD>
					    <TD class="column_field"><bean:message key="label.person.min" /></TD>
						<TD>
							<html:text property="optionMin" size="3" disabled="false"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="optionMin"/></FONT> 
						</TD>
						<TD>
							<html:submit onclick="process.value='createOption';"> <bean:message key="label.option.create" /> </html:submit>
						</TD>
					</TR>
					<TR>
					    <TD></TD>
					    <TD></TD>
					    <TD></TD>
					    <TD class="column_field"><bean:message key="label.person.max" /></TD>
						<TD>
							<html:text property="optionMax" size="3" disabled="false"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="optionMax"/></FONT> 
						</TD>
						<TD>
							<html:submit onclick="process.value='updateOption';"> <bean:message key="label.update.data" /> </html:submit>
						</TD>
					</TR>
					<TR>
					    <TD></TD>
					    <TD></TD>
					    <TD></TD>
					    <TD class="column_field"><bean:message key="label.price" /></TD>
						<TD>
							<html:text property="optionPrice" size="3" disabled="false"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="optionPrice"/></FONT> 
						</TD>
					</TR>
					<TR>
					    <TD colspan="6" class="column_field"><bean:message key="label.list.menu" /></TD>
					</TR>
					<TR>
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
							<html:submit onclick="process.value='selectMenu';"> <bean:message key="label.select" /> </html:submit>
						</TD>
						<TD>
							<html:submit onclick="process.value='deleteMenu';"> <bean:message key="label.delete" /> </html:submit>
						</TD>
					</TR>
					<TR>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
					    <TD class="column_field"><bean:message key="label.menu.name" /></TD>
						</TD>
						<TD>
							<html:text property="menuName" size="20" disabled="false"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="menuName"/></FONT> 
						</TD>
						<TD>
							<html:submit onclick="process.value='createMenu';"> <bean:message key="label.menu.create" /> </html:submit>
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
					</TR>
					<TR>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
					    <TD class="column_field"><bean:message key="label.products.included" /></TD>
						<TD>
							<html:textarea property="menuDescription" cols="20" rows="5" disabled="false"></html:textarea>
			        		<FONT COLOR="RED"> <html:errors property="menuDescription"/></FONT> 
						</TD>

						<TD>
							<html:submit onclick="process.value='updateMenu';"> <bean:message key="label.update.data" /> </html:submit>
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
					</TR>
					<TR>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
					    <TD class="column_field"><bean:message key="label.price.monday" /></TD>
						<TD>
							<html:text property="price1" size="3"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="price1"/></FONT> 
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
					</TR>
					<TR>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
					    <TD class="column_field"><bean:message key="label.price.tuesday" /></TD>
						<TD>
							<html:text property="price2" size="3"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="price2"/></FONT> 
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
					</TR>
					<TR>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
					    <TD class="column_field"><bean:message key="label.price.wednesday" /></TD>
						<TD>
							<html:text property="price3" size="3"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="price3"/></FONT> 
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
					</TR>
					<TR>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
					    <TD class="column_field"><bean:message key="label.price.thursday" /></TD>
						<TD>
							<html:text property="price4" size="3"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="price4"/></FONT> 
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
					</TR>
					<TR>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
					    <TD class="column_field"><bean:message key="label.price.friday" /></TD>
						<TD>
							<html:text property="price5" size="3"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="price5"/></FONT> 
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
					</TR>
					<TR>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
					    <TD class="column_field"><bean:message key="label.price.saturday" /></TD>
						<TD>
							<html:text property="price6" size="3"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="price6"/></FONT> 
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
					</TR>
					<TR>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
					    <TD class="column_field"><bean:message key="label.price.sunday" /></TD>
						<TD>
							<html:text property="price7" size="3"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="price7"/></FONT> 
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
						<TD>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			<CENTER>
			  
	<HR>		  
			</CENTER>			
			
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

  document.getElementById("filter").focus();
  
//-->
</SCRIPT>
</html:html>
