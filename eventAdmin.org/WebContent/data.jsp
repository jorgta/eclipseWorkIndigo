<%@page import="net.sf.hibernate.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<HEAD>
<%@ page session="true" import="java.util.*,com.eventAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
    HttpSession ses = request.getSession();
	String dir = request.getParameter("dir");
	String sort = request.getParameter("sort");
	String bean = request.getParameter("bean");
	String list = request.getParameter("list");
	String fields = request.getParameter("fields");
	String searchValue  = request.getParameter("searchValue");
	String searchParam = request.getParameter("searchParam");
	String active = request.getParameter("active");
	
	net.sf.hibernate.Session sessionHibernate= null;
	com.bituos.Config configuration = new com.bituos.Config( request );
	net.sf.hibernate.SessionFactory sessionFactory = configuration.getConfiguration( request ).buildSessionFactory();
	sessionHibernate = sessionFactory.openSession();
	BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
	
	
	
	
	String query =  "";
	
	String queryFilter = "" ;
	
	if((List)ses.getAttribute(list ) == null)
	{
		ses.removeAttribute(list );
		List listObjects = null;
		
		if(searchParam != null)
		{
		  queryFilter = " AND u." + searchParam + " LIKE '%" + searchValue + "'" ;
		  sort = searchParam;
		  dir = "asc";
		}
		query =  " FROM " + bean + " u" +
				 " WHERE u.id_company.id = " + beanUser.getId_company().getId() +
				   queryFilter +
				 " AND u.active = '"+ active +"'" +
				 " AND u.id <> " + beanUser.getId()+
				 " ORDER BY u." + sort + " " + dir ;

		
		listObjects = sessionHibernate.createQuery( query ).list();
		ses.setAttribute(list, listObjects );
		
		sessionHibernate.close();
		sessionFactory.close();
	}
	 
	%> 
	<% 
	if(searchParam == null)
	{		
	%> 
	
 <TABLE>
    
     <TBODY>    

    <logic:iterate id="id" indexId="indexId" name="<%=list%>"  scope="session" >
 	<TR>
 	   
 	   <logic:iterate id="idfields" indexId="indexId" name="<%=fields%>"  scope="session" >
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
    <% }%>   
    
    <% 
	if(searchParam != null)
	{		
	%> 
	
	<script type="text/javascript">
	

	</script>
	 <TABLE> 
      <TR>
     <TD colspan="5" align="center" style="width: 100%">
     
        <a href="#" class="button danger" onclick="hiddeCapa();" >Cerrar</a>
     </TD>
     </TR>
    <logic:iterate id="id" indexId="indexId" name="<%=list%>"  scope="session" >
 	<TR>
 	    
 	   <logic:iterate id="idfields" indexId="indexId" name="<%=fields%>"  scope="session" >
         <bean:define id="fld" name="idfields" > </bean:define>
        <% if(((String) fld) == "id"){ 
        	String classStyle="";
        	String txtbutton="";
        	if(active.equals("N"))
        	{
        		classStyle= "button icon approve";
        	  txtbutton = "Activar";
        	}
        	else
        	{
        	  
        	  classStyle= "button danger icon remove";
        	  txtbutton = "Borrar";
        	}
        %>
          <TD> 
 	       <a href="#" class="<%=classStyle%>"  onclick="sendForm('<bean:write name="id" property="<%=((String) fld)%>" />')"> <%=txtbutton%></a>
 	     </TD>
 	    <%} %>
 	   
         
          <TD>        
          <logic:notEqual name="id" property="<%=((String) fld)%>" value="">         
     	       <bean:write name="id" property="<%=((String) fld)%>" /> 
     	     </logic:notEqual>  	     
            	      
          </TD>
        </logic:iterate>   
    </TR>				    
	</logic:iterate>
    

	</TABLE>
	
    <% }%> 

<%
ses.removeAttribute(list );
%>
</body>
</html>