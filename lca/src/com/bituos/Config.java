package com.bituos;

import com.bituos.lca.Beans.*;

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
				conf.addClass( BeanDoctor.class);
				conf.addClass( BeanBranch.class);
				conf.addClass( BeanRepresentative.class);
				conf.addClass( BeanDoctorRepresentative.class);
				conf.addClass( BeanDoctorVisit.class);
				conf.addClass( BeanTestGroupTest.class);
				conf.addClass( BeanPatient.class);				
				conf.addClass( BeanTestOrder.class);
				conf.addClass( BeanTestOrderTest.class);
				conf.addClass( BeanTestParameter.class);
				conf.addClass( BeanTestRepresentative.class);
				conf.addClass( BeanTestResult.class);
				conf.addClass( BeanTestDoctor.class);
				
				
				conf.addClass( BeanTypeProcess.class);
				conf.addClass( BeanProcess.class);
				conf.addClass( BeanProcessUsers.class);				
				conf.addClass( BeanLog_Type.class);
				conf.addClass( BeanLog.class);	
				conf.addClass( BeanTest.class);	
				conf.addClass( BeanTestGroup.class);	
				
				conf.addClass( BeanParameterLimits.class);	
				conf.addClass( BeanMaterial.class);	
				conf.addClass( BeanOrderMaterial.class);	
				conf.addClass( BeanTestPkg.class);	
				conf.addClass( BeanTestPkgDetail.class);	
				conf.addClass( BeanOrderPkg.class);	
				
				
				
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
