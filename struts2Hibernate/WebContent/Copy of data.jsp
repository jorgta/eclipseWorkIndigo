
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<HEAD>
<%@ page session="true" import="java.util.*,com.struts2v2.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>
<%
    HttpSession ses = request.getSession();
	String list = request.getParameter("list"); //lista de objetos
	String col = request.getParameter("cols"); //lista columnas
	String fields = request.getParameter("fields"); //lista columnas
	String action = request.getParameter("action"); //lista columnas
	String process = request.getParameter("process");
	String sort = request.getParameter("sort");
	String bean = request.getParameter("bean");
	String dir = request.getParameter("dir");
	
	
	
	
	
	
	String target = request.getParameter("target");

	
	
	net.sf.hibernate.Session sessionHibernate= null;
	com.bituos.Config configuration = new com.bituos.Config( request );
	net.sf.hibernate.SessionFactory sessionFactory = configuration.getConfiguration( request ).buildSessionFactory();
	sessionHibernate = sessionFactory.openSession();
	BeanUser beanUser = (BeanUser) ses.getAttribute( "beanUser" );
	
	ses.removeAttribute(list );
	
	List listObjects;
	String query =  "";
					
	
	query =  " FROM " + bean + " u" +
			 " WHERE u.id_company = " + beanUser.getId_company().getId() +
			 " AND u.active = 'Y'" +
			 " AND u.id <> " + beanUser.getId()+
			 " ORDER BY u." + sort + " " + dir ;
	


	listObjects = sessionHibernate.createQuery( query ).list();
	ses.setAttribute(list, listObjects );
	
	sessionHibernate.close();
	sessionFactory.close();
	
    
%>
</HEAD>

<html:html>
<BODY>
  
<logic:present name="beanUser" scope="session">
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


</logic:present>
<logic:notPresent name="beanUser" scope="session">
	   <logic:redirect href="./login.jsp">  </logic:redirect>
</logic:notPresent>
</BODY>
</html:html>

