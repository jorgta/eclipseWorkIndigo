package com.bituos;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */


import com.bituos.gjv.Beans.*;

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
				conf.addClass( BeanLog.class);
				conf.addClass( BeanLogType.class);
				conf.addClass( BeanUser.class);
				conf.addClass( BeanProcess.class);
				conf.addClass( BeanProcessUsers.class);
				conf.addClass( BeanTypeProcess.class);
				//conf.addClass( BeanSecciones.class);
				
				conf.addClass( BeanProcessProfile.class);
				conf.addClass( BeanProfile.class);
				conf.addClass( BeanUserProfile.class);				
				
				conf.addClass( BeanContactPPS.class);
				
				conf.addClass( BeanContactRH.class);
				conf.addClass( BeanInOut.class);	
				conf.addClass( BeanNotes.class);
				
				conf.addClass( BeanContactRHGroup.class);
				conf.addClass( BeanInOutGroup.class);
				conf.addClass( BeanNotesGroup.class);
				
				
				

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
