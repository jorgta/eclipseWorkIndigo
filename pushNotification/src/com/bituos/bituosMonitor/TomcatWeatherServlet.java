package com.bituos.bituosMonitor;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.CometEvent;
import org.apache.catalina.CometProcessor;

/**
 * Servlet implementation class TomcatWeatherServlet
 */
public class TomcatWeatherServlet extends HttpServlet implements CometProcessor {
	private static final long serialVersionUID = 1L;
	
	private MessageSender messageSender = null;
    private static final Integer TIMEOUT = 60 * 1000;
    HttpServletRequest request = null;
    @Override
    public void destroy() {
        //messageSender.stop();
        messageSender = null;
 
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TomcatWeatherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
     * @see CometProcessor#event(CometEvent)
     */
    @Override
    public void event(final CometEvent event) {
        // TODO Auto-generated method stub
    	HttpServletRequest request = event.getHttpServletRequest();
        HttpServletResponse response = event.getHttpServletResponse();
        if (event.getEventType() == CometEvent.EventType.BEGIN) {
            request.setAttribute("org.apache.tomcat.comet.timeout", TIMEOUT);
            //log("Begin for session: " + request.getSession(true).getId());
            System.out.println("Begin for session: " + request.getSession(true).getId()); 
            messageSender.setConnection(response);
            Weatherman weatherman = new Weatherman(95118, 32408);
            new Thread(weatherman).start();
        } else if (event.getEventType() == CometEvent.EventType.ERROR) {
            //log("Error for session: " + request.getSession(true).getId());
            System.out.println("Error for session: " + request.getSession(true).getId()); 
            try {
				event.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if (event.getEventType() == CometEvent.EventType.END) {
            //log("End for session: " + request.getSession(true).getId());
            System.out.println("End for session: " + request.getSession(true).getId()); 
            try {
				event.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if (event.getEventType() == CometEvent.EventType.READ) {
            throw new UnsupportedOperationException("This servlet does not acceptdata");
        }
 
    }
   

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		 
		messageSender = new MessageSender();
        Thread messageSenderThread = new Thread(messageSender, "MessageSender[" + "" + "]");
        messageSenderThread.setDaemon(true);
        messageSenderThread.start();
	}

}
