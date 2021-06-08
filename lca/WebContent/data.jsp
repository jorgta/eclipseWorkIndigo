
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ page session="true" import="java.util.*,com.bituos.lca.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>

<%
    HttpSession ses = request.getSession();
	//String dir = request.getParameter("dir");
	//String sort = request.getParameter("sort");
	String bean = request.getParameter("bean");
	String list = request.getParameter("list");
	String field = request.getParameter("field");
	String searchValue  = request.getParameter("value");
	String module = request.getParameter("module");
	//String searchParam = request.getParameter("searchParam");
	String active = request.getParameter("active");
	//String process = request.getParameter("process");
	String currentUser = request.getParameter("currentUser");

	
	net.sf.hibernate.Session sessionHibernate= null;
	com.bituos.Config configuration = new com.bituos.Config( request );
	net.sf.hibernate.SessionFactory sessionFactory = configuration.getConfiguration( request ).buildSessionFactory();
	sessionHibernate = sessionFactory.openSession();
	BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
	BeanCompany beanComany = (BeanCompany) ses.getAttribute( "BeanCompany" );
	
	
	
	String query =  "";
	
	String queryFilterLike = "" ;
	String queryFilterANDCompany = " AND u.id_company = " + beanUser.getId_company().getId() ;
	String queryFilterWHEREActive = "" ;
	String queryFilterANDAAtive = "" ;
	String queryFilterNoCurrentUser = "" ;

	ses.removeAttribute(list );
	List listObjects = null;
	
	
	queryFilterLike = "  u." + field + " LIKE '" + searchValue + "%'" ;

	if(active != null)
	{
		try
		{
			Integer.valueOf(active);
			queryFilterWHEREActive =" WHERE u.active = "+ active;		
			queryFilterANDAAtive =" AND u.active = "+ active ;
		}
		catch(Exception e)
		{
		 	
		 queryFilterWHEREActive =" WHERE u.active = '"+ active +"'";		
		 queryFilterANDAAtive =" AND u.active = '"+ active +"'";
		}
	}
	
	queryFilterNoCurrentUser = " AND u.id <> " + beanUser.getId();
	
	
	
	if(searchValue == null || searchValue == "" )
	{
		if(active == null || active == "")
		{
			query =  " FROM " + bean + " u";
		}
		else
			query =  " FROM " + bean + " u" + queryFilterWHEREActive ;
		
	}
	else
	{
		if (!module.equals("rptOrder"))
		{
		    query =  " FROM " + bean + " u" +
			         " WHERE " + queryFilterLike + queryFilterANDAAtive;
		}else 
		{
			query =  " FROM " + bean + " u" +
	                 " WHERE " + queryFilterLike;
		}
	}

	if (currentUser != null)
	{
		if (currentUser.equals("off"))
		query = query + queryFilterNoCurrentUser + queryFilterANDCompany;
	}

	
	listObjects = sessionHibernate.createQuery( query ).list();
	
	ses.removeAttribute(list);
	ses.setAttribute(list, listObjects );
	String cols ="";
	if(listObjects.isEmpty())
	{
		if (module.equals("rptOrder"))
			cols ="4";
		else if (module.equals("userDelete"))
		{
			cols ="4";
		}
		else if (module.equals("userActivate"))
		{
			cols ="4";
		}
		else if (module.equals("userSelect"))
		{
			cols ="4";
		}
		else if (module.equals("orderNew"))
		{
			cols ="3";
		}else if (module.equals("rptTicket"))
		{
			cols ="4";
		}
		else if (module.equals("rptResult"))
		{
			cols ="4";
		}
		else if (module.equals("doctorDelete"))
		{
			cols ="5";
		}
		else if (module.equals("doctorActivate"))
		{
			cols ="5";
		}
		else if (module.equals("testSelect"))
		{
			cols ="4";
		}
		
		
		
		
		
	  
		out.write("<tr class='dg_tr' id='searching'><td colspan='" + cols +"'>No hay registros con este valor: "+ searchValue +"</td></tr>");

	  return;
	}
	%> 
	
	<%         String cuStyleBackground="rgb(255, 255, 255)";
			   String color;
			   int i=0;
			%>
			<logic:iterate id="id" indexId="indexId" name="<%=list%>" scope="session" >
			<%
			  		 // BeanSale beanSale = (BeanSale) ses.getAttribute( "currentSale" );
				  
			  if (cuStyleBackground == "rgb(255, 255, 255)")		  
			  {
				  cuStyleBackground = "rgb(247, 249, 251)";
				  color = "#d9e3f1";
			  }
			  else
			  {
				  cuStyleBackground = "rgb(255, 255, 255)";
				  color = "#e4ecf7";
			  }				  
			
			 if (module.equals("rptOrder"))
			  {
			%>
			
						<tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
						onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
						onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
						onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
		
						<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:DoRequest("rptOrder","<bean:write name="id" property="id" />");' title="Select record">Seleccionar</a></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="id" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="patient_name" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="date_reg" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="id_patient.street" /></label></td>

						</tr>
			<% }
			  else if(module.equals("userDelete"))
			  {			
			%>
		
		         <tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
				onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
				onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
				onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">

				
				<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="name" /></label></td>

				<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="address" /></label></td>
	            <td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="email" /></label></td>
				<td class="x-blue_dg_td dg_center dg_nowrap" style=" width:6%;"><img class="dg_opacity dg_pointer" onclick="javascript:sp_verifyDelete('<bean:write name="id" property="id" />','&amp;sp_page_size=15&amp;sp_p=1');" src="./grid/index_data/delete.gif" alt="Borrar Registro" title="Borrar Registro"></td>
				</tr>
			<%} 
			 else if(module.equals("userActivate"))
			 {
			%> 
			    <tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
				onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
				onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
				onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
				<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:sp__doPostBack("activate","<bean:write name="id" property="id" />","");' title="Edit record">Seleccionar</a></td>
				
				<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="name" /></label></td>

				<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="address" /></label></td>
	            <td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="email" /></label></td>

				</tr>
				
			<% 
			 }else if(module.equals("userSelect"))
			 {
			%> 
						<tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
						onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
						onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
						onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
						<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:sp__doPostBack("activate","<bean:write name="id" property="id" />","");' title="Seleccionar registro">Seleccionar</a></td>
						
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="name" /></label></td>
		
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="address" /></label></td>
			            <td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="email" /></label></td>
	
						</tr>
			<% 
			 }else if(module.equals("orderNew"))
			 {
			%> 
						<tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
						onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
						onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
						onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
		
						<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:sp__doPostBack("addMaterial","<bean:write name="id" property="id" />","");' title="Seleccionar registro">Seleccionar</a></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="description" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1">
						
						<logic:equal name="id" property="haslabel" value="1"> 							
						
					      Si
					  
						</logic:equal>
						<logic:equal name="id" property="haslabel" value="0"> 
				
					      No
					    
							
						</logic:equal>
						
						</label></td>
						
						 
					
						</tr>
			<% 
			 }else if(module.equals("rptTicket"))
			 {
			%> 
						<tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
						onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
						onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
						onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
		
						<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:DoRequest("rptTicket","<bean:write name="id" property="id" />");' title="Select record">Seleccionar</a></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="id" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="patient_name" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="date_reg" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="id_patient.street" /></label></td>

						</tr>
			<% 
			 }else if(module.equals("rptResult"))
			 {
			%> 
						<tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
						onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
						onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
						onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
		
						<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:DoRequest("rptResult","<bean:write name="id" property="id" />");' title="Select record">Seleccionar</a></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="id" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="patient_name" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="date_reg" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="id_patient.street" /></label></td>

						</tr>
			<% 
			 }else if(module.equals("doctorDelete"))
			 {
			%> 
						<tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
						onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
						onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
						onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
		
						<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:DoRequest("delete","<bean:write name="id" property="id" />");' title="Select record">Seleccionar</a></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="name" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="address" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="phone" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="phone_cel" /></label></td>

						</tr>
			  
			<% 
			 }else if(module.equals("doctorActivate"))
			 {
			%> 
						<tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
						onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
						onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
						onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
		
						<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:DoRequest("activate","<bean:write name="id" property="id" />");' title="Select record">Seleccionar</a></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="name" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="address" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="phone" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="phone_cel" /></label></td>

						</tr>
			<% 
			 }else if(module.equals("doctorSelect"))
			 {
			%> 
						<tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
						onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
						onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
						onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
		
						<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:DoRequest("select","<bean:write name="id" property="id" />");' title="Select record">Seleccionar</a></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="name" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="address" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="phone" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="phone_cel" /></label></td>

						</tr>
			<% 
			 }else if(module.equals("testSelect"))
			 {
			%> 
						<tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
						onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
						onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
						onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
		
						<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:DoRequest("select","<bean:write name="id" property="id" />");' title="Select record">Seleccionar</a></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="name" /></label></td>
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="price" /></label></td>		
						<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="method" /></label></td>

						</tr>
			<% 
			 }				
			%>                               
		 
				
				
				
				
				
				
			
			<%  
			 i++;
			%>
			</logic:iterate>