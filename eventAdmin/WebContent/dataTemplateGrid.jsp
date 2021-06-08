<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ page session="true" import="java.util.*,com.eventAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>

<%
    HttpSession ses = request.getSession();
	String dir = request.getParameter("dir");
	String orderField = request.getParameter("orderField");
	String bean = request.getParameter("bean");
	String list = request.getParameter("list");
	String fields = request.getParameter("fields");
	String searchValue  = request.getParameter("searchValue");
	String searchParam = request.getParameter("searchParam");
	String active = request.getParameter("active");
	String current =  request.getParameter("current");

	String process =  request.getParameter("process");
	String processTag =  request.getParameter("processTag");
	
	
	String fieldNameToAction =  request.getParameter("fieldNameToAction");
	
	
	String[] ignoreRequest =  request.getParameterValues("ignore[]");

	
	List<String> ignore = Arrays.asList(ignoreRequest);
	
	 
	
	String nestedField1 = request.getParameter("nestedField1");//"id";
	String nestedField2 = request.getParameter("nestedField2");//"id_client";
	String nestedFieldOrder = request.getParameter("nestedFieldOrder"); //"";//"date_reg";
	String nestedBean = request.getParameter("nestedBean"); //"BeanQuote";	
	
	//String beanu =  request.getParameter("beanUser[name]");//si pasas un bean por paranetro
	
	/*
	PrintWriter pw=response.getWriter();
	pw.println(ignoreRequest);
	
	
	 
	 
	Enumeration<String> myJsonData = request.getParameterNames();
	
	while(myJsonData.hasMoreElements())
	{
		Object objOri=myJsonData.nextElement();

		String param=(String)objOri;

		String value=request.getParameter(param);

		pw.println("Parameter Name is '"+param+"' and Parameter Value is '"+value+"'");

	}	*/	
	
	
	/*String value=request.getParameter("data[0][processTag]");
	pw.println(value);*/
	
	
	net.sf.hibernate.Session sessionHibernate= null;
	com.bituos.Config configuration = new com.bituos.Config( request );
	net.sf.hibernate.SessionFactory sessionFactory = configuration.getConfiguration( request ).buildSessionFactory();
	sessionHibernate = sessionFactory.openSession();
	BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
	
	
	
	
	String query =  "";
	
	String queryFilter = "" ;
	String orderOperatorField = " ORDER BY u.";

		
	ses.removeAttribute(list );
	List listObjects = null;
	
	if(searchParam != null)
	{
	  queryFilter = " AND u." + searchParam + " LIKE '" + searchValue + "%'" ;
	  if (orderField == null)
	  {
	  	orderOperatorField = orderOperatorField + searchParam;
	  }
	  else
	  {
		  orderOperatorField = orderOperatorField + orderField;
	  }
	  
	  dir = "asc";
	}
	
	//nestedField1 ="id";
	//nestedField2 ="id_client";
	//nestedFieldOrder = "";//"date_reg";
	//nestedBean = "BeanQuote";	
	String queryFilter2=""; 
	
	
	
	if(nestedBean != null && nestedField1 != null)
	{
	  queryFilter2= createQueryIN(nestedBean, nestedField1, nestedField2,  nestedFieldOrder);
	}
	
	String queryFilter3=""; 
	if(active != null) 
	{
		queryFilter3 = " AND u.active = '"+ active +"'";
	}
 
	query =  " FROM " + bean + " u" +
			 " WHERE u.id_company.id = " + beanUser.getId_company().getId() +
			   queryFilter +
			   queryFilter3 +
			   queryFilter2 +
			 " AND u.id <> " + beanUser.getId()+
			  orderOperatorField + " " + dir ;
			 
 
			 
	listObjects = sessionHibernate.createQuery( query ).list();
	
	if ( listObjects.size() > 9)
		listObjects = listObjects.subList(0, 9);
	
	ses.setAttribute(list, listObjects );
	
	sessionHibernate.close();
	sessionFactory.close();
	
	
	
	String currentBean= (String)ses.getAttribute("selectedQuote");
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

	   <logic:iterate id="id" indexId="indexId"  name="<%=list %>" scope="session" >
	 	
    	<logic:notEqual name="id" property="id" value="">
    			   	 
    		     <%			    		  
				  if (current != null && !current.endsWith(""))
				    cuString = new Integer( current ).toString(); 
				  
	    		  if ((counter % 2)==1)						
          	          clase="AltRowStyle";				
				  else
					  clase="RowStyle";	
				 %>		
				 
			    <logic:equal name="id" property="id" value="<%=cuString%>">
    			   <tr class="SelectedRowStyle">
    			</logic:equal> 
    			
    			<logic:notEqual name="id" property="id" value="<%=cuString%>">
    			   <tr class="<%=clase%>">
    			</logic:notEqual> 
				    <th scope="row">
					  <a href='#button' class='btn btn-outline-secondary btn-sm' role='button'   onclick='javascript:__doPost("<%=process%>","<bean:write name="id" property="<%=fieldNameToAction%>" />")' ><%=processTag%></a>
					</th>
					<logic:iterate id="idfields" indexId="indexId" name="fields"  scope="session" >
						<bean:define id="fld" name="idfields" > </bean:define>
						<% if(!ignore.contains(((String) fld)) )
						{ 
				   
				        %>
							<td>    
				     	       <bean:write name="id" property="<%=((String) fld)%>" /> 
				     	    </td>
				     	<%
						}
				     	%>
					</logic:iterate>
				  </tr>
				
		</logic:notEqual> 

    		<%counter++;%>	
   		</logic:iterate>	

<%} 
	

	
	
	sessionHibernate.close();
	sessionFactory.close();

%>


<%!
	public String createQueryIN(String nestedBean, String nestedField1, String nestedField2, String nestedOrderField)
	{
 
		String orderOperator =" ";
		if(nestedOrderField != null)
		{
		   orderOperator =" ORDER BY f.";
		}
		else
		{
			nestedOrderField =" ";
		}
		
		
		String nestedQuery="SELECT f." + nestedField2 + " FROM " + nestedBean + " f " + orderOperator + nestedOrderField ;	
		String nestedOperator =" IN (" + nestedQuery + ")";	
		String queryFilter2=  " AND u." + nestedField1 + nestedOperator;
		
		 
	  	
		return queryFilter2;
	}
%>




