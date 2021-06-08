
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ page session="true" import="java.util.*,com.eventAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>

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
	String process = request.getParameter("process");
	
	net.sf.hibernate.Session sessionHibernate= null;
	com.bituos.Config configuration = new com.bituos.Config( request );
	net.sf.hibernate.SessionFactory sessionFactory = configuration.getConfiguration( request ).buildSessionFactory();
	sessionHibernate = sessionFactory.openSession();
	BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
	BeanCompany beanComany = (BeanCompany) ses.getAttribute( "BeanCompany" );
	
	
	String query =  "";
	
	String queryFilter = "" ;

	ses.removeAttribute(list );
	List listObjects = null;
	
	if(searchParam != null)
	{
	  queryFilter = "  u." + searchParam + " LIKE '" + searchValue + "%'" ;
	  sort = searchParam;
	  dir = "asc";
	}
	

	
	query =  " FROM " + bean + " u" +
			 " WHERE " + queryFilter +
			 " AND u.id_company = " + beanUser.getId_company().getId() +
			 " ORDER BY u." + sort + " " + dir ;

	
	listObjects = sessionHibernate.createQuery( query ).list();
	
	ses.setAttribute(list, listObjects );
	
    String cuString ="";        



	Integer  counter = 0;
	String clase;
	 
	%> 
									
    <% if(listObjects.isEmpty()) 
      {
    %>
    <tr class='RowStyle'>
    <td colspan="5">
      No hay resultados para su busqueda...
    </td>
    </tr>
      
    <%} %>
	
	<% if(!listObjects.isEmpty()) 
      {
    %>
	<logic:iterate id="id" indexId="indexId" name="<%=list %>" scope="session" >
	   		 <%if ((counter % 2)==1)						
        	          clase="AltRowStyle";						
		  else
			  clase="RowStyle";	%>			
   					
   				
   			<logic:equal name="id" property="id" value="<%=cuString%>">
   			   <tr class="SelectedRowStyle">
   			</logic:equal> 
   			
   			<logic:notEqual name="id" property="id" value="<%=cuString%>">
   			   <tr class="<%=clase%>">
   			</logic:notEqual> 
   					
						<td>
						  <!--   <a href="javascript:__doPost('removeCoHolder','<bean:write name="id" property="id" />')">Remover</a>-->
						  <a href="#button" class="button icon approve" onclick="javascript:__doPost('selectSale','<bean:write name="id" property="id" />')" >Seleccionar</a>
						</td>
							<td><bean:write name="id" property="client_name" /></td>																
							<td><bean:write name="id" property="list_description" /></td>
							<td><bean:write name="id" property="saloon_description" /></td>
						
					</tr>


   		<%counter++;%>	
  		</logic:iterate>

<%} 

	sessionHibernate.close();
	sessionFactory.close();

%>
						



