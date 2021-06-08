package com.bituos.SMS;
        
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.bituos.*;

//Hibernate
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;

import com.bituos.SMS.Beans.BeanSms;

public class SMSServer {
	
	
	/*
	public Server() {
	 }
	*/
	
	public String getName( String name )
	  {
		return "SMSServer for (" + name +  ")";
		
	  }



	public BeanSms[] getSMSList( String name) throws Exception
	  {
		Config config = new Config( );
		
		SessionFactory sessionFactory = null;
		Session    session = null;
		Transaction tx = null;
		BeanSms[] beanSmsList = null;
		int index  = 0;

		try
		  {
			sessionFactory = config.getConfiguration( ).buildSessionFactory();
			session = sessionFactory.openSession();
			
			String query = " FROM BeanSms u" +
					       " WHERE u.id_status = 0";
			
			List list = session.createQuery( query ).list();
			
			if ( !list.isEmpty() )
			{
				beanSmsList = new BeanSms[ list.size() ];
		
			  Iterator iter = list.iterator();
		
			  while ( iter.hasNext() )
			    {					
				  BeanSms beanSms = (BeanSms) iter.next();	
				  beanSmsList[index] = beanSms; 
				  index++;				
			    }
			 }
				
		  }
		catch( Throwable  m)
		  {
			m.printStackTrace();
			
		  }
		finally
		  {
			session.close();
			sessionFactory.close();
		  }
		
		return beanSmsList;	
	  }

	
 	
	

}
