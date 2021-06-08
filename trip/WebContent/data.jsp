<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page session="true" import="java.util.*,com.struts2.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>

<% 
HttpSession ses = request.getSession();
String bean = request.getParameter("bean");
String list = request.getParameter("list");
String field = request.getParameter("field");
String searchValue  = request.getParameter("value");
String module = request.getParameter("module");
String active = request.getParameter("active");
String includeCurrentUser = request.getParameter("includeCurrentUser");
String pagination = request.getParameter("pagination");
String pageNumber = request.getParameter("page");
String status = request.getParameter("status");

org.hibernate.Session sessionHibernate= null;
com.bituos.Config configuration = new com.bituos.Config( request );
org.hibernate.SessionFactory sessionFactory = configuration.getConfiguration( request ).buildSessionFactory();
sessionHibernate = sessionFactory.openSession();
BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
BeanCompany beanComany = (BeanCompany) ses.getAttribute( "BeanCompany" );

String query =  "";

String queryFilterLike = "" ;
String queryFilterANDCompany = " AND u.id_company = " + beanUser.getId_company().getId() ;
String queryFilterWHEREActive = "" ;
String queryFilterANDAAtive = "" ;
String queryFilterNoCurrentUser = "" ;
String queryFilterANDStatus = "" ;

ses.removeAttribute(list );
List listObjects = null;

if (pagination != null)
{
	//TODO: crear paginacion pagination
	if (pagination.equals("on"))
	{
		//pageNumber
	}
		
}



	queryFilterLike = "  u." + field + " LIKE '%" + searchValue + "%'" ;
	
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
	
	if(status != null)
	{					
		queryFilterANDStatus =" AND u.id_type_status_room.id = 1" +
							  " OR u.id_type_status_room.id = 2" ;
	   
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
	
	if (includeCurrentUser != null)
	{
		if (includeCurrentUser.equals("off"))
		query = query + queryFilterNoCurrentUser + queryFilterANDCompany;
	}
	
	
	if(status != null)
	{	
		query = query + queryFilterANDStatus;
	}
	
	
	listObjects = sessionHibernate.createQuery( query ).list();
	
	ses.removeAttribute(list);
	ses.setAttribute(list, listObjects );
	String cols ="";
	if(listObjects.isEmpty())
	{
		if (module.equals("rptReservation"))
			cols ="6";
		else if (module.equals("userDelete"))
		{
			cols ="4";
		}
		else if (module.equals("newReservation"))
		{
			cols ="6";
		}
	
	  
		out.write("<tr><td colspan='" + cols +"'>No hay registros con este valor: "+ searchValue +"</td></tr>");
	
	  return;
	}



%>
        
       
            <%
			 if (module.equals("rptReservation"))
			  {
			%>
			   <s:iterator value="#session.roomsReserved" var="currentRoom_Reservation">
				<tr>
					<td> <s:property value="#currentRoom_Reservation.id_room.id_hotel.description"/></td>
					<td><s:property value="#currentRoom_Reservation.id_room.description"/></td>
					<td><s:property value="#currentRoom_Reservation.id_room.id_type_status_room.status"/></td>
					<td><s:property value="#currentRoom_Reservation.id_reservation.date_begin"/></td>
					<td><s:property value="#currentRoom_Reservation.id_reservation.date_blocked"/></td>																				
					<td>
						<!-- Icons -->
						 <a href="rpt.action?id=<s:property value="#currentRoom_Reservation.id"/>&process=rptReservation" title="Reporte de reservacion" title="Reporte de reservacion"><img src="resources/images/icons/report.png" alt="Reporte de reservacion" /></a> 
		           </td>
			  </tr>
			  	 </s:iterator>
			<% }
			  else if(module.equals("newReservation"))
			  {			
			%>
			<s:iterator value="#session.roomsList" var="currentRoom">
							
								 <tr>
									<s:if test="#currentRoom.id_type_status_room.id == 2">	
									<td></td>
						            <td style="color: red;"><s:property value="#currentRoom.id_hotel.description" /></td>
						            <td style="color: red;"><s:property value="#currentRoom.description"/></td>
									<td style="color: red;"><s:property value="#currentRoom.id_type_status_room.status"/></td>
									<td style="color: red;"><s:property value="#currentRoom.date_begin"/></td>
									<td style="color: red;"><s:property value="#currentRoom.date_end"/></td>
								</s:if>
								<s:else>
								    <td><input type="checkbox" name="rooms" value="<s:property value="#currentRoom.id"/>" id="<s:property value="#currentRoom.id"/>"/></td>
						            <td style="color: green;"><s:property value="#currentRoom.id_hotel.description" /></td>
						            <td style="color: green;"><s:property value="#currentRoom.description"/></td>
									<td style="color: green;"><s:property value="#currentRoom.id_type_status_room.status"/></td>
									<td style="color: green;"><s:property value="#currentRoom.date_begin"/></td>
									<td style="color: green;"><s:property value="#currentRoom.date_end"/></td>
								</s:else> 
								</tr>
								</s:iterator>
		

			<%} 
			 else if(module.equals("rptQuote"))
			 {
			%> 
			
			     <s:iterator value="#session.listQuoteRoom" var="currentQuoteRoom">
							
					<tr>						
						
						<td><s:property value="#currentQuoteRoom.id_room.id_hotel.description"/></td>
						<td><s:property value="#currentQuoteRoom.id_room.description"/></td>
						<td><s:property value="#currentQuoteRoom.id_room.id_type_status_room.status"/></td>
						<td><s:property value="#currentQuoteRoom.id_quote.date_begin"/></td>
						<td><s:property value="#currentQuoteRoom.id_quote.date_blocked"/></td>
																			
						<td>
							<!-- Icons -->
							 <a href="rpt.action?p1=<s:property value="#currentQuoteRoom.id_quote.id"/>&process=rptQuote&type=" title="Reporte de reservacion" title="Reporte de reservacion"><img src="resources/images/icons/report.png" alt="Reporte de reservacion" /></a> 
							 <a href="rpt.action?p1=<s:property value="#currentQuoteRoom.id_quote.id"/>&process=rptQuote&type=pdf" title="Ver en PDF" title="Ver en PDF"><img src="resources/images/icons/doc_pdf.png" alt="Reporte de reservacion" /></a> 
							 <a href="rpt.action?p1=<s:property value="#currentQuoteRoom.id_quote.id"/>&process=rptQuote&type=download" title="Descargar PDF" title="Descargar PDF"><img src="resources/images/icons/page_save.png" alt="Reporte de reservacion" /></a> 
			           </td>
					</tr>
					</s:iterator>
			
			<% }
			  /*else if(module.equals("newReservation"))
			  {	*/		
			%>
			
