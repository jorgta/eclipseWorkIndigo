<%@ page session="true" import="java.util.*,com.bituos.bar.Beans.*, java.io.*, javax.servlet.http.HttpSession.*"%>

<%

		HttpSession ses = request.getSession();
		/*net.sf.hibernate.Session sessionHibernate= null;
		com.bituos.Config configuration = new com.bituos.Config( request );
		net.sf.hibernate.SessionFactory sessionFactory = configuration.getConfiguration( request ).buildSessionFactory();
		sessionHibernate = sessionFactory.openSession();*/
		



if ( request.getParameter("id") != null )
  {
	
	try
	  {

		List listSize = (List)ses.getAttribute("marcaList");
		Iterator iter = listSize.iterator();	
		BeanMarca bean = new BeanMarca();	 
		 byte[] bytes =null;
		   while ( iter.hasNext() )
			 {			
			   bean = (BeanMarca)iter.next();
			   if(bean.getId() == Integer.valueOf( request.getParameter("id")) )
			   {  
				  bytes =  bean.getFoto();
				  break;
			   }
			 }
		 

		 
		 
		 response.setContentType("image/gif");
		
 		 OutputStream o = response.getOutputStream();
		 response.reset();

		 o.write(bytes);
		 o.flush();

		 o.close();
	  }
	catch( Throwable  m)
	  {
		m.printStackTrace();
		
		com.bituos.Error e = new com.bituos.Error("Error interno (" + m.getMessage() + ")", request);
		
	  }
	finally
	  {
		/*sessionHibernate.close();
		sessionFactory.close();*/
	  }

	

	
  }
%>