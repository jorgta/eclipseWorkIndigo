/*
 * Created on Jun 8, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.bituos;

/**
 * @author David Sanchez Aroche (dsa1972@yahoo.com)
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;



import com.bituos.bituosMonitor.Beans.*;

import javax.servlet.http.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import com.bituos.*;



public class Mail {

  public Mail( String to, String msg, String subject)
    {
    	try
    	  {
			SessionFactory sessionFactory = null;
			net.sf.hibernate.Session sessionH = null;

			try
			  {
				Config config = new Config( );

				sessionFactory = config.getConfiguration().buildSessionFactory();
				sessionH = sessionFactory.openSession();
				BeanControlPanel control_panel = (BeanControlPanel) sessionH.load( BeanControlPanel.class, new Integer( 1 ) );
				Authenticator authenticator = null;

				String password = control_panel.getSmtp_password();
                 
				 // Get system properties
				 Properties props = System.getProperties();

				 // Setup mail server
				 props.put("mail.smtp.host", control_panel.getSmtp_server() );
				
   
				 // Get session
				//javax.mail.Session session = javax.mail.Session.getInstance(props, null);

				javax.mail.Session session = javax.mail.Session.getInstance(props, authenticator);
   
				 // Define message
				 MimeMessage message = new MimeMessage(session);
				 message.setFrom(      new InternetAddress( control_panel.getSmtp_email() ) );
				 message.addRecipient( Message.RecipientType.TO, new InternetAddress(to));
				 message.setSubject(   subject );
				 message.setText(      msg );

				 // Send message
				if ( control_panel.getSmtp_is_secure().equals("Y") ) 
				  {
					Transport transport = session.getTransport("smtp");
				
					transport.connect( control_panel.getSmtp_server(), control_panel.getSmtp_user_name(), password);
					transport.sendMessage(message, message.getAllRecipients());
					transport.close();
   
				  }
				else
				  Transport.send(message);
			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();

				return;
			  }
			finally
			  {
				sessionH.close();
				sessionFactory.close();
			  }
    	  }
		catch( Throwable  m)
		  {
			//mx.com.bafar.ub.Error e = new mx.com.bafar.ub.Error("Error en el envio del mail.", req);
			//resp.sendRedirect("/ub/notOK.jsp");

			m.printStackTrace();

			return;
		  }

    }

	private class Authenticator extends javax.mail.Authenticator {
			private PasswordAuthentication authentication;

			public Authenticator() {
				String username = "auth-user";
				String password = "auth-password";
				authentication = new PasswordAuthentication(username, password);
			}

			protected PasswordAuthentication getPasswordAuthentication() {
				return authentication;
			}
		}

}
