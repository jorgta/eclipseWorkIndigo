
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%@ page session="true" import="java.util.*,com.bituos.truckAdmin.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>

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
	//String active = request.getParameter("active");
	//String process = request.getParameter("process");
	

	
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
	
	
	queryFilter = "  u." + field + " LIKE '" + searchValue + "%'" ;
	
	
	if(searchValue == null || searchValue == "")
	{
		query =  " FROM " + bean;
	}
	else
	{
	    query =  " FROM " + bean + " u" +
		         " WHERE " + queryFilter;
	}


	
	listObjects = sessionHibernate.createQuery( query ).list();
	
	ses.removeAttribute(list);
	ses.setAttribute(list, listObjects );
	String cols ="";
	if(listObjects.isEmpty())
	{
		if (module.equals("truck"))
			cols ="6";
		else if (module.equals("tire"))
			cols ="5";
		else if (module.equals("tireDelete"))
			cols ="9";
		else if (module.equals("eventNew"))
			cols ="9";
		else if (module.equals("controlPanel"))
			cols ="5";
		else if (module.equals("truckNew"))
			cols ="5";
		else if (module.equals("truckEdit"))
			cols ="5";
		
		
	  
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
				
				 if (module.equals("truck"))
				  {
			%>
			
				
					<tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
					onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
					onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
					onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
					<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:sp__doPostBack("edit","<bean:write name="id" property="id" />","");' title="Edit record">Edit</a></td>
					
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="registration" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="description" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="model" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=" width:6%;"><img class="dg_opacity dg_pointer" onclick="javascript:sp_verifyDelete('<bean:write name="id" property="id" />','&amp;sp_page_size=15&amp;sp_p=1');" src="./grid/index_data/delete.gif" alt="Borrar Registro" title="Borrar Registro"></td>
					</tr>
			<%   }
				 else if(module.equals("tire"))
				 {
			
			%>
				     <tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
					onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
					onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
					onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
					<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:sp__doPostBack("select","<bean:write name="id" property="id" />","");' title="Select record">Seleccionar</a></td>
					
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="registration" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="description" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="model" /></label></td>

				</tr>
				<%} 
				 else if(module.equals("tireDelete"))
				 {				
				%>
				   <tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
					onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
					onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
					onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
					
					
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="design" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="deep" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="position" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="serial_number" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="id_type_tire_status.description" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="id_place.description" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="id_type_measure.description" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="id_truck.registration" /></label></td>
					
					<td class="x-blue_dg_td dg_center dg_nowrap" style=" width:6%;"><img class="dg_opacity dg_pointer" onclick="javascript:sp_verifyDelete('<bean:write name="id" property="id" />','&amp;sp_page_size=15&amp;sp_p=1');" src="./grid/index_data/delete.gif" alt="Borrar Registro" title="Borrar Registro"></td>
					</tr>
						
				<%} 				 			
				else if(module.equals("eventNew"))
				 {				
				%>
					<tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
					onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
					onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
					onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
					
					<td class="x-blue_dg_td_main dg_center dg_nowrap"  style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:sp__doPostBack("select","<bean:write name="id" property="id" />","");' title="Select record">Seleccionar</a></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="design" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="deep" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="position" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="serial_number" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="id_type_tire_status.description" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="id_place.description" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="id_type_measure.description" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="id_truck.registration" /></label></td>
		
					</tr>
						
				<%} 			 			
				else if(module.equals("controlPanel"))
				 {
				%>
				  <tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
					onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
					onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
					onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
					<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:sp__doPostBack("edit","<bean:write name="id" property="id" />","");' title="Edit record">Edit</a></td>
					
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="description" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="date_reg" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="configuration" /></label></td>
					<td class="x-blue_dg_td dg_center dg_nowrap" style=" width:6%;"><img class="dg_opacity dg_pointer" onclick="javascript:sp_verifyDelete('<bean:write name="id" property="id" />','&amp;sp_page_size=15&amp;sp_p=1');" src="./grid/index_data/delete.gif" alt="Borrar Registro" title="Borrar Registro"></td>
				</tr>
				
				<%} 			 			
				else if(module.equals("truckNew"))
				 {
				%>

				 
				<tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
			onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
			onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
			onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
			<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:sp__doPostBack("select","<bean:write name="id" property="id" />","");' title="Edit record">Seleccionar</a></td>
			
			<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="description" /></label></td>
			<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="date_reg" /></label></td>
			<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="configuration" /></label></td>

			</tr>
				<%} 			 			
				else if(module.equals("truckEdit"))
				 {
				%>

	           <tr class="dg_tr" style="background: none repeat scroll 0% 0% <%=cuStyleBackground %>;" id="sp_row_<%=i%>" 
				onclick="onMouseClickRow('sp_','<%=i%>','#fdfde7','#ffffff','#F7F9FB');" 
				onmouseover="onMouseOverRow('sp_','<%=i%>','#fdfde7','#f9f9e3');" 
				onmouseout="onMouseOutRow('sp_','<%=i%>','#F7F9FB','#fdfde7');">
				<td class="x-blue_dg_td_main dg_center dg_nowrap" style=" width:8%; background-color:<%=color%>;"><a class="x-blue_dg_a" href='javascript:sp__doPostBack("select","<bean:write name="id" property="id" />","");' title="Edit record">Seleccionar</a></td>
				
				<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="created_at_1"><bean:write name="id" property="description" /></label></td>
				<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1"><bean:write name="id" property="date_reg" /></label></td>
				<td class="x-blue_dg_td dg_center dg_nowrap" style=""><label class="x-blue_dg_label" id="updated_at_1">  <bean:write name="id" property="configuration" /></label></td>
	
				</tr>
				<%} 
				%>
			
			<%  
			 i++;
			%>
			</logic:iterate>