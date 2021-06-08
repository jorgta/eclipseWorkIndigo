package com.bituos;

/*import com.bituos.bar.Beans.BeanProcess;
import com.bituos.bar.Beans.BeanProcessUsers;
import com.bituos.bar.Beans.BeanTypeProcess;*/
import com.bituos.bar.Beans.*;

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

				conf.addClass( BeanAgente.class);
				
				conf.addClass( BeanMesa.class);
				conf.addClass( BeanComandas.class);
				conf.addClass( BeanCategoria.class);
				conf.addClass( BeanMarca.class);
				conf.addClass( BeanMoneda.class);
				conf.addClass( BeanProducto.class);
				conf.addClass( BeanDetalleComanda.class);
				
				

				
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
