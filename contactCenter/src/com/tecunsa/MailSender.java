package com.tecunsa;

import java.util.Properties;
import java.util.Date; 

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import net.sf.hibernate.SessionFactory;

import com.bituos.Beans.BeanControlPanel;


public class MailSender 
{
	   public static String errorMsg;

	   public static boolean send( 
				  String toAddress,
				  String body,
				  String subject,
				  String HTMLFormat, 
				  HttpServletRequest req,
				  boolean debug )

	   {
			errorMsg = "";

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

					send( control_panel.getServer(),
							  "25",
							  control_panel.getIs_secure(), 
							  control_panel.getTmx_sender_email(), 
							  control_panel.getPassword(),
		   					  toAddress,
		   					  "", 
		   					  "", 
		   					  subject,
		   					  HTMLFormat, 
		   					  body,
		   					  req,
		   					  debug);


				  }
				catch( Throwable  m)
				  {
					m.printStackTrace();

					com.bituos.Error e = new com.bituos.Error("Error en el envio del mail.", req);

					errorMsg = m.getMessage();
					return false;
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

				return true;
			  }
		  return true;
	   }
	   
   public static boolean send(String serverAddress,
		   					  String serverPort,
		   					  String isSecure,
		   					  String senderAddress,
		   					  String passwordAddress,
		   					  String toAddress,
		   					  String ccAddress, 
		   					  String bccAddress, 
		   					  String subject,
		   					  String HTMLFormat, 
		   					  String body,
		   					  HttpServletRequest req,
		   					  boolean debug)
   {
   

	   Properties props = new Properties();   
   
	   
//		 Nombre del host de correo, es smtp.gmail.com
	props.setProperty("mail.smtp.host", serverAddress );
	 
//		 TLS si está disponible
	if ( isSecure.equals("Y") )
	  props.setProperty("mail.smtp.starttls.enable", "true");
	else
	  props.setProperty("mail.smtp.starttls.enable", "false");

//		 Puerto de gmail para envio de correos
	props.setProperty("mail.smtp.port", serverPort);
	

//		 Nombre del usuario
	props.setProperty("mail.smtp.user", senderAddress);

//		 Si requiere o no usuario y password para conectarse.
	props.setProperty("mail.smtp.auth", "true");
	

	
	javax.mail.Session sessionmail = javax.mail.Session.getDefaultInstance(props);
	sessionmail.setDebug(debug);
	
     try 
     {		   

		MimeMessage message = new MimeMessage(sessionmail);
//			 Quien envia el correo
		message.setFrom(new InternetAddress(senderAddress));

//			 A quien va dirigido
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
		message.setRecipients(Message.RecipientType.CC, ccAddress);
		message.setRecipients(Message.RecipientType.BCC, bccAddress);
		message.setSubject(subject);
		message.setSentDate(new Date());
		message.setText(body,"ISO-8859-1",HTMLFormat);//, "HTMLFormat=html"
	
		Transport t = sessionmail.getTransport("smtp");
		t.connect(senderAddress,passwordAddress);
		t.sendMessage(message,message.getAllRecipients());
		t.close();

     }
     catch (Exception m)
     {
		m.printStackTrace();

		com.bituos.Error e = new com.bituos.Error("Error en el envio del mail.", req );

		errorMsg = m.getMessage();
		return false;
     }
     
     return true;
     
   }

}   
