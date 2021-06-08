package com.bituos;

import com.eventAdmin.Beans.*;

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
				conf.addClass( BeanClient.class);
				conf.addClass( BeanUser.class);
				conf.addClass( BeanTypeProcess.class);
				conf.addClass( BeanProcess.class);
				conf.addClass( BeanProcessUsers.class);				
				conf.addClass( BeanLog_Type.class);
				conf.addClass( BeanLog.class);			
				conf.addClass( BeanEmail.class);   
				conf.addClass( BeanContact.class);
				
				conf.addClass( BeanSaloon.class);
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
				conf.addClass( BeanTypeHour.class);
			
				conf.addClass(BeanPayments.class);
				conf.addClass(BeanTypePayment.class);
				conf.addClass(BeanPaymentTypeForm.class);
				
				conf.addClass(BeanCalendarProps.class);
				conf.addClass(BeanOptions.class);
				conf.addClass(BeanTimeZone.class);
				conf.addClass(BeanMonthOptions.class);
				conf.addClass(BeanDayNames.class);
				conf.addClass(BeanWeekOptions.class);
				conf.addClass(BeanTemplate.class);
				
				
				conf.addClass(BeanScheduleInfo.class);
				conf.addClass(BeanRaw.class);
				conf.addClass(BeansScheduleinfoAttendees.class);
				conf.addClass(BeanAttendees.class);
				conf.addClass(BeanCreator.class);
				conf.addClass(BeanCreatorUser.class);
				
				
				
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
