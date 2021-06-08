package com.bituos.bituosMonitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletResponse;

public class MessageSender implements Runnable {

	protected boolean running = true;
    protected final ArrayList<String> messages = new ArrayList<String>();
    private ServletResponse connection;
    public synchronized void setConnection(ServletResponse connection){
        this.connection = connection;
        notify();
    }
    public void send(String message) {
        synchronized (messages) {
            messages.add(message);
           // log("Message added #messages=" + messages.size());
            System.out.println("Message added #messages=" + messages.size()); 
            messages.notify();
        }
    }
    public void run() {
        while (running) {
            if (messages.size() == 0) {
                try {
                    synchronized (messages) {
                        messages.wait();
                    }
                } catch (InterruptedException e) {
                    // Ignore
                }
            }
            String[] pendingMessages = null;
            synchronized (messages) {
                pendingMessages = messages.toArray(new String[0]);
                messages.clear();
            }
            try {
                if (connection == null){
                    try{
                        synchronized(this){
                            wait();
                        }
                    } catch (InterruptedException e){
                        // Ignore
                    }
                }
                PrintWriter writer = connection.getWriter();
                for (int j = 0; j < pendingMessages.length; j++) {
                    final String forecast = pendingMessages[j] + "<br>";
                    writer.println(forecast);
                    //log("Writing:" + forecast);
                    System.out.println("Writing:" + forecast); 
                }
                writer.flush();
                writer.close();
                connection = null;
                //log("Closing connection");
                System.out.println("Closing connection"); 
            } catch (IOException e) {
                //log("IOExeption sending message", e);
                System.out.println("IOExeption sending message" + e); 
            }
        }
    }
	public ServletResponse getConnection() {
		return connection;
	}
    
    

}
