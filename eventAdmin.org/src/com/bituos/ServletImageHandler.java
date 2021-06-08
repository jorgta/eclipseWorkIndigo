/* Servlet to handle embedded images and charts */ 
package com.bituos;


import javax.servlet.*; 
import javax.servlet.http.*; 
import java.io.*; 
import java.util.*; 

import com.crystaldecisions.report.web.viewer.CrystalImageHandler; 

public class ServletImageHandler extends HttpServlet { 

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	try { 
		CrystalImageHandler imageHandler = new CrystalImageHandler(); 
		imageHandler.handleImage(request, response, 
		getServletConfig().getServletContext()); 
	} catch ( Exception e ) { 
		System.out.println( "There was an exception in the ServletImageHandler.class"); 
		System.out.println( e ); 
	} 
  } 

} 

