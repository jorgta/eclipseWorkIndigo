/*
 * Created on Jul 4, 2006
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

public class SecurityUtil {
	
	public int createProcessList( HttpServletRequest req, int id_user )
	  {
		try
		  {
			HttpSession ses = req.getSession();

			ses.removeAttribute("processList");

			Config config = new Config( req );

			SessionFactory sessionFactory = null;
			Session    session = null;

			sessionFactory = config.getConfiguration(req).buildSessionFactory();
			session = sessionFactory.openSession();			

			
			String query =   " FROM BeanProcessUsers p" 
						   + " WHERE p.id_user=" + id_user 
						   + " ORDER BY p.id_process.id_type_process.id, p.id";
							     
							   
			ses.setAttribute("processList", session.createQuery(query).list()  );

			query =   " FROM BeanTypeProcess p" 
				    + " WHERE p.id > 0"   
				    + " ORDER BY p.id";
							   
							   
			ses.setAttribute("processTypeList", session.createQuery(query).list()  );
			return 0;
		  }
		catch( Throwable  m )
		  {
			m.printStackTrace();
			return -1;
		  }
	  }
}
