package org.bituos;

import org.hibernate.*;
import org.hibernate.cfg.*;

import com.bituos.SMS.Beans.BeanSms;



public class Config {

	
	  public Configuration getConfiguration( )
	    {
			try
			  {
				Configuration  conf = new Configuration();

				conf.addClass( BeanSms.class);

				return conf;
			  }
			catch( Throwable  m )
			  {
				m.printStackTrace();
				
				return null;
			  }
	    }

}
