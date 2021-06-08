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
	<link rel="stylesheet" type="text/css" media="screen" href="BlockUI/css/jq.css" />
    <script type="text/javascript" src="BlockUI/js/jquery.min.js"></script>
    <script type="text/javascript" src="BlockUI/js/jquery.blockUI.js?v2.38"></script>


<script type="text/javascript">


	$(document).ready(function() {
	
      
     $('input:submit').click(function(){
         $.blockUI({ css: {
            border: 'none',
            padding: '15px',
            backgroundColor: '#000',
            '-webkit-border-radius': '10px',
            '-moz-border-radius': '10px',
            opacity: .5,
            color: '#fff'
        } });
      });

});


</script>
    <TITLE>
    </TITLE>
</HEAD>
<BODY onLoad="st();">

	<CENTER><H1> <bean:message key="label.products.list" />  </H1></CENTER>
	
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
		         
		         String visibleData = (String)ses.getAttribute("visibleData");   
		         
		         BeanProduct listProductEdit_currentProduct = (BeanProduct) ses.getAttribute( "listProductEdit_currentProduct" );							    
				 String cpString;
				 
				 if ( listProductEdit_currentProduct != null ) 
					 cpString = new Integer( listProductEdit_currentProduct.getId() ).toString(); 
				 else
					 cpString = "-1";
					 
			  %>

			
			<html:form action="/listProductEdit">
			<input type="hidden" name="process" value="" />
			<TABLE border="0" ALIGN="CENTER">
				<TBODY>
					<TR>
						<TD>
							<html:select property="id_product">						
								<logic:iterate id="id" indexId="indexId" name="listProductEdit_productList" scope="session" >
								    <OPTION value= <%="'"%><bean:write name="id" property="id" /><%="'"%> 
									    <logic:equal name="id" property="id" value="<%=cpString%>"> 
									    		SELECTED
									    </logic:equal>>
								    	<bean:write name="id" property="description" />
								    </OPTION>
						    	</logic:iterate>						
							</html:select>							
			        		<FONT COLOR="RED"> <html:errors property="id_product"/></FONT> 
						</TD>
						<TD>
						    
							<html:submit onclick="process.value='select';"  > <bean:message key="label.select" /> </html:submit>
						     
						     
						</TD>
						<TD>
							<html:submit onclick="process.value='delete';"> <bean:message key="label.delete" /> </html:submit>
						</TD>
					    <TD class="column_field"><bean:message key="label.description" /></TD>
						<TD>
							<html:text property="description" size="40" disabled="false"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="description"/></FONT> 
						</TD>
						<TD>
							<html:submit onclick="process.value='create';"> <bean:message key="label.product.create" /> </html:submit>
						</TD>
					</TR>
					<TR>
					    <TD></TD>
					    <TD></TD>
					    <TD></TD>
					    <TD class="column_field"><bean:message key="label.price" /></TD>
						<TD>
							<html:text property="price" size="3" disabled="false"></html:text>
			        		<FONT COLOR="RED"> <html:errors property="price"/></FONT> 
						</TD>
						<TD>
							<html:submit onclick="process.value='update';"> <bean:message key="label.update.data" /> </html:submit>
						</TD>
					</TR>
					<TR>
						<TD></TD>
						<TD></TD>
						<TD></TD>
					    <TD class="column_field"><bean:message key="label.per.person" /></TD>
					    <TD>
					      <html:checkbox property="perPerson"></html:checkbox>
			        	  <FONT COLOR="RED"> <html:errors property="perPerson"/></FONT> 
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

  document.getElementById("id_product").focus();


//-->
</SCRIPT>
</html:html>
