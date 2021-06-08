package com.bituos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetServerTime
 */
public class GetServerTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetServerTime() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setIntHeader("Refresh", 1);
		   // Get current time
		  // Calendar calendar = new GregorianCalendar();
		   Calendar calendar = Calendar.getInstance();
		   String am_pm;
		   int hour = calendar.get(Calendar.HOUR_OF_DAY);
		   int minute = calendar.get(Calendar.MINUTE);
		   int second = calendar.get(Calendar.SECOND);
		   if(calendar.get(Calendar.AM_PM) == 0)
		      am_pm = "AM";
		   else
		      am_pm = "PM";
		   String CT = hour+":"+ minute +":"+ second +" "+ am_pm;
		   response.getWriter().println( CT);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setIntHeader("Refresh", 1);
		   // Get current time
		  // Calendar calendar = new GregorianCalendar();
		   Calendar calendar = Calendar.getInstance();
		   String am_pm;
		   int hour = calendar.get(Calendar.HOUR_OF_DAY);
		   int minute = calendar.get(Calendar.MINUTE);
		   int second = calendar.get(Calendar.SECOND);
		   if(calendar.get(Calendar.AM_PM) == 0)
		      am_pm = "AM";
		   else
		      am_pm = "PM";
		   String CT = hour+":"+ minute +":"+ second +" "+ am_pm;
		   response.getWriter().println(CT);
	}

}
