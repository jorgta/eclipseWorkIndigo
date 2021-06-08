package com.servicioCael;

/**
 * @version 	1.0
 * @author		Bituos Tools S de RL MI
 */


import com.bituos.Beans.*;
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
				conf.addClass( BeanCase.class);
				conf.addClass( BeanCaseNotes.class);
				conf.addClass( BeanControlPanel.class);
				conf.addClass( BeanLogin.class);
				conf.addClass( BeanLog.class);
				conf.addClass( BeanLogType.class);
				conf.addClass( BeanProcess.class);
				conf.addClass( BeanProcessUsers.class);
				conf.addClass( BeanProcessProfile.class);
				conf.addClass( BeanProfile.class);
				conf.addClass( BeanStates.class);
				conf.addClass( BeanTypeCase.class);
				conf.addClass( BeanTypeCaseStatus.class);
				conf.addClass( BeanTypeProcess.class);
				conf.addClass( BeanUser.class);
				conf.addClass( BeanType_user.class);
				conf.addClass( BeanUserProfile.class);
				conf.addClass( BeanUserUser.class);
				conf.addClass( BeanSupport.class);

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
