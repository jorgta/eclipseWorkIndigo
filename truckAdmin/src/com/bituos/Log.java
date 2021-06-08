/*
 * Created on Aug 18, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.bituos;

/**
 * @author dsanchez
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import com.bituos.truckAdmin.Beans.*;

import javax.servlet.http.*;

//Hibernate 2.1

import net.sf.hibernate.*;
import net.sf.hibernate.cfg.*;


public class Log {
	public void registerEvent ( String description, int idLogType, HttpServletRequest request, Session session )
	  {
        try
          {
          	/* 
			Config config = new Config( request );
			
			SessionFactory sessionFactory = null;
			Session    session = null;
			*/
			Transaction tx = null;
			
			BeanLog bean = new BeanLog();
			
			try {
				/* 
				sessionFactory = config.getConfiguration(request).buildSessionFactory();
				session = sessionFactory.openSession();
				*/
				
				HttpSession httpSession = request.getSession();
				BeanLog_Type beanLog_Type = (BeanLog_Type) session.load( BeanLog_Type.class, new Integer(idLogType)  );
				BeanUser beanUser = (BeanUser) httpSession.getAttribute("beanUser");
			
				bean.setEvent( description );
				bean.setId_log_type( beanLog_Type );
				bean.setId_user( beanUser );
				bean.setIp( request.getRemoteAddr() + "(" + request.getRemoteHost() + ")" );
				bean.setDate_reg( Utils.Today() );

				tx = session.beginTransaction();
				session.save( bean );
				tx.commit();
						
				// do something here
			
			} catch (Exception e) {
			
				e.printStackTrace();
			
			}
			finally	{
				/*
				if ( session != null )
					 session.close(); 
					  
				if ( sessionFactory != null )
					 sessionFactory.close();
				*/	 
			}
          }
		catch (Exception e) 
		  {
			e.printStackTrace();
		  }
			

	  }

}
