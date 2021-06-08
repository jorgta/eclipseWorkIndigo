<%@ page session="true" import="java.util.*, 
								java.io.*,
								java.sql.*"%>
								
<%

java.sql.Connection cnx;
java.sql.Statement stmt;
java.sql.ResultSet result;

//String databaseURL = "jdbc:interbase://localhost:3060/c:/data/BARMisty.gdb";
//String databaseURL = "jdbc:interbase://localhost/c:/data/TEST.gdb";
String databaseURL = "jdbc:interbase://192.168.0.140/C:/data/interbase/FacturacionJorgeTorres.gdb";
 


if ( request.getParameter("id") != null )
  {
	try
	{
		try
		{
		Class.forName ("interbase.interclient.Driver");
		
		}
		catch (java.lang.ClassNotFoundException e)
		{
		out.write( "InterClient not found in class path");
		out.write(e.getMessage ());
		}
		
		 String username = null;
		 int userid =  Integer.parseInt(request.getParameter("id")) ; 
		 
		 cnx = java.sql.DriverManager.getConnection(databaseURL,"SYSDBA","masterkey");

		 //String sql = "SELECT * FROM cliente WHERE id = " +String.valueOf( userid ) ;
		 String sql = "SELECT * FROM cliente WHERE id = " +String.valueOf( userid ) ;
		 java.sql.Statement sment = cnx.createStatement();

		 java.sql.ResultSet rs = sment.executeQuery(sql);

			 
		 byte[] returndata = null;
		
		 
		 if ( rs.next() ) 
		 {
			
	
		     
			 byte[] bytes = rs.getBytes("FOTO");
			 
			 response.setContentType("image/gif");
		
			 
			
			OutputStream o = response.getOutputStream();
			 response.reset();

			 o.write(bytes);

			 o.flush();

			 o.close();
			

		 

		 } else {

		 // The row does not exist in the database

		 }
		
		
		}
	catch( java.sql.SQLException SQLe )
	{
	out.write(SQLe.getMessage()+" \n"+
	" SQLState: " + SQLe.getSQLState()+"\n"+
	" ErrorCode: " + SQLe.getErrorCode());
	}
	
  }
%>