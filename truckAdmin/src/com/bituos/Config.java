package com.bituos;

import com.bituos.truckAdmin.Beans.*;

import javax.servlet.http.*;

//Hibernate 2.1

import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;


public class Config {

  public Config( HttpServletRequest req )
    {
		try
		  {
			HttpSession ses = req.getSession();

			if ( ses.getAttribute( "configuration" ) == null )
			  {
				Configuration  conf = new Configuration();

				conf.addClass( BeanControlPanel.class);
				conf.addClass( BeanCompany.class);
				conf.addClass( BeanUser.class);
				conf.addClass( BeanTypeProcess.class);
				conf.addClass( BeanProcess.class);
				conf.addClass( BeanProcessUsers.class);				
				conf.addClass( BeanLog_Type.class);
				conf.addClass( BeanLog.class);	
				
				conf.addClass( BeanTypeTire.class);	
				conf.addClass( BeanTypeTireStatus.class);	
				conf.addClass( BeanTypeEvent.class);	
				conf.addClass( BeanTypeMeasure.class);	
				conf.addClass( BeanTypePlace.class);
				conf.addClass( BeanEvent.class);
				conf.addClass( BeanTruck.class);
				conf.addClass( BeanTire.class);
				conf.addClass( BeanTruckTireConfiguration.class);
				conf.addClass( BeanTruckTireConfigurationDetail.class);
				conf.addClass( BeanTireQuantityAxis.class);
				conf.addClass( BeanTypeUnitOfMeasure.class);
				conf.addClass( BeanTireMovements.class);
				conf.addClass( BeanTypePosition.class);
				conf.addClass( BeanTireTruck.class);
				
				
				ses.setAttribute("configuration", conf );
			  }
		  }
		catch( Throwable  m )
		  {
			m.printStackTrace();
		  }
    }

/**
 * @return Retorna configuracion de Hibernate
 */
	public Configuration getConfiguration( HttpServletRequest req  )
	  {
		HttpSession ses = req.getSession();
		Configuration  conf = (Configuration) ses.getAttribute( "configuration" );

		if ( conf != null )
		  return conf;

		return null;
	  }
}
