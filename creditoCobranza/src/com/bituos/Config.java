package com.bituos;

import com.struts2.Beans.*;

import javax.servlet.http.*;

import org.hibernate.*;
import org.hibernate.cfg.*;



public class Config {

  public Config( HttpServletRequest req )
    {
		try
		  {
			HttpSession ses = req.getSession();
			
			if ( (Configuration)ses.getAttribute( "configuration" ) == null )
			  {
				Configuration  conf = new AnnotationConfiguration().configure();
				
				conf.addClass( BeanControlPanel.class);

				conf.addClass( BeanTypeProcess.class);
				conf.addClass( BeanProcess.class);
				conf.addClass( BeanProcessUsers.class);				
				conf.addClass( BeanLog_Type.class);
				conf.addClass( BeanLog.class);
				conf.addClass( BeanUser.class);

				conf.addClass( BeanCompany.class);
				
		 
				
				
				/*
				//conf.addClass( BeanEmail.class);   
				//conf.addClass( BeanContact.class);
				
				/*conf.addClass( BeanSaloon.class);
				conf.addClass( BeanProduct.class);
				conf.addClass( BeanList.class);
				conf.addClass( BeanListOptions.class);					
				conf.addClass( BeanListOptionMenu.class);
				conf.addClass( BeanFlower.class);
				conf.addClass( BeanMusic.class);
				conf.addClass( BeanQuote.class);
				conf.addClass( BeanQuoteProduct.class);
				conf.addClass( BeanQuoteFlower.class);				
				conf.addClass( BeanQuoteMusic.class);
				conf.addClass( BeanSale.class);
				conf.addClass( BeanSaleProduct.class);
				conf.addClass( BeanSaleFlower.class);				
				conf.addClass( BeanSaleMusic.class);
				conf.addClass( BeanPayment.class);
				conf.addClass( BeanTypeHour.class);*/
				
								
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
	public Configuration getConfiguration( HttpServletRequest req   )
	  {
		HttpSession ses = req.getSession();
		Configuration  conf = (Configuration) ses.getAttribute( "configuration" );
		
		if ( conf != null )
		  return conf;

		return null;
	  }
	
	

}
