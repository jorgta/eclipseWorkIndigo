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

import com.struts2.Beans.*;

import javax.servlet.http.*;

//Hibernate 3.0

/*
import org.hibernate.Session;
import org.hibernate.*;
import org.hibernate.cfg.*;
*/

//Hibernate 2.1

import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import com.bituos.*;



public class Mail {

  public Mail( String to, String msg, String subject, HttpServletRequest req)
    {
    	try
    	  {
			SessionFactory sessionFactory = null;
			net.sf.hibernate.Session sessionH = null;

			try
			  {
				Config config = new Config( req );

				sessionFactory = config.getConfiguration(req).buildSessionFactory();
				sessionH = sessionFactory.openSession();
				BeanControlPanel control_panel = (BeanControlPanel) sessionH.load( BeanControlPanel.class, new Integer( 1 ) );
				Authenticator authenticator = null;

				String password = control_panel.getPassword();
                 
				 // Get system properties
				 Properties props = System.getProperties();

				 // Setup mail server
				 props.put("mail.smtp.host", control_panel.getServer() );
   
				 // Get session
				//javax.mail.Session session = javax.mail.Session.getInstance(props, null);

				javax.mail.Session session = javax.mail.Session.getInstance(props, authenticator);
   
				 // Define message
				 MimeMessage message = new MimeMessage(session);
				 message.setFrom(      new InternetAddress( control_panel.getEmail() ) );
				 message.addRecipient( Message.RecipientType.TO, new InternetAddress(to));
				 message.setSubject(   subject );
				 message.setText(      msg );

				 // Send message
				if ( control_panel.getIs_secure().equals("Y") ) 
				  {
					Transport transport = session.getTransport("smtp");
				
					transport.connect( control_panel.getServer(), control_panel.getUser_name(), password);
					transport.sendMessage(message, message.getAllRecipients());
					transport.close();
   
				  }
				else
				  Transport.send(message);
			  }
			catch( Throwable  m)
			  {
				m.printStackTrace();

				com.bituos.Error e = new com.bituos.Error("Error en el envio del mail.", req);
				//resp.sendRedirect("/ub/notOK.jsp");
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
