package com.bituos.bituosMonitor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.catalina.CometEvent;
import org.apache.catalina.CometProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


 
public class ChatServlet extends HttpServlet implements CometProcessor {
	private static final long serialVersionUID = 1L;
		
	protected ArrayList<HttpServletResponse> connections =  new ArrayList<HttpServletResponse>();
	protected MessageSender messageSender = null;
	
	public void init() throws ServletException {
	    messageSender = new MessageSender();
	    Thread messageSenderThread = new Thread(messageSender, "MessageSender[" + ""+ "]");
	    messageSenderThread.setDaemon(true);
	    messageSenderThread.start();
	}
	
	public void destroy() {
	    connections.clear();
	    //messageSender.stop();
	    messageSender = null;
	}
	
	 /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * Process the given Comet event.
	 * 
	 * @param event The Comet event that will be processed
	 * @throws IOException
	 * @throws ServletException
	 */
	public void event(CometEvent event)
	    throws IOException, ServletException {
	
	    // Note: There should really be two servlets in this example, to avoid
	    // mixing Comet stuff with regular connection processing
	    HttpServletRequest request = event.getHttpServletRequest();
	    HttpServletResponse response = event.getHttpServletResponse();
	    
	    if (event.getEventType() == CometEvent.EventType.BEGIN) {
	        begin(event, request, response);
	    } else if (event.getEventType() == CometEvent.EventType.ERROR) {
	        error(event, request, response);
	    } else if (event.getEventType() == CometEvent.EventType.END) {
	        end(event, request, response);
	    } else if (event.getEventType() == CometEvent.EventType.READ) {
	        read(event, request, response);
	    }
	}
	
	protected void begin(CometEvent event, HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {
	    log("Begin for session: " + request.getSession(true).getId());
	    
	    PrintWriter writer = response.getWriter();
	    writer.println("<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">");
	    writer.println("<html>");
	    writer.flush();
	
	    System.out.println("Action param: " + request.getParameter("action"));
	    synchronized(connections) {
	        connections.add(response);
	    }
	}
	
	protected void end(CometEvent event, HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {
	    log("End for session: " + request.getSession(true).getId());
	    synchronized(connections) {
	        connections.remove(response);
	    }
	    
	    PrintWriter writer = response.getWriter();
	    writer.println("</body>");
	    
	    event.close();
	    
	}
	
	protected void error(CometEvent event, HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {
	    log("Error for session: " + request.getSession(true).getId());
	    synchronized(connections) {
	        connections.remove(response);
	    }
	    event.close();
	}
	
	protected void read(CometEvent event, HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {
	    log("Read for session: " + request.getSession(true).getId());
	    /*
	    InputStream is = request.getInputStream();
	    byte[] buf = new byte[512];
	    while (is.available() > 0) {
	        log("Available: " + is.available());
	        int n = is.read(buf);
	        if (n > 0) {
	            log("Read " + n + " bytes: " + new String(buf, 0, n) 
	                    + " for session: " + request.getSession(true).getId());
	        } else if (n < 0) {
	            log("End of file: " + n);
	            end(event, request, response);
	            return;
	        }
	    }*/
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException {
	    // Compatibility method: equivalent method using the regular connection model
	    PrintWriter writer = response.getWriter();
	    writer.println("<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">");
	    writer.println("<html>");
	    writer.println("Chat example only supports Comet processing");
	    writer.println("Configure a connector that supports Comet and try again.");
	    writer.println("</body>");
	}
}
