package com.bituos;

import com.struts2.Beans.*;

import javax.servlet.http.*;

//Hibernate

import org.hibernate.*;
import org.hibernate.cfg.*;



public class Config{
	
	  static Configuration configuration = null;
	 
	  public Config(  )
	    {
		  initConfiguration();
	    }
		
	
	  public Config( HttpServletRequest req )
	    {
			try
			  {
				HttpSession ses = req.getSession();
				
				if ( (Configuration)ses.getAttribute( "configuration" ) == null )
				  {
					Configuration  conf = new AnnotationConfiguration().configure();
					
					conf.addClass( BeanCompany.class);
					conf.addClass( BeanDevice.class);
					
					conf.addClass( BeanCommand.class);
					conf.addClass( BeanTypeCommand.class);
					
					conf.addClass( BeanCounter.class);
					conf.addClass( BeanCounterLog.class);
					conf.addClass( BeanCounterLogDetail.class );
					conf.addClass( BeanType_Counter_Detail.class);
					conf.addClass( BeanType_Counter_Master.class);

					conf.addClass( BeanTypeOS.class);

					
					
					
					
					

					
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
	/*  
	public Configuration getConfiguration( HttpServletRequest req   )
	  {
		HttpSession ses = req.getSession();
		Configuration  conf = (Configuration) ses.getAttribute( "configuration" );
		
		if ( conf != null )
		  return conf;

		return null;
	  }
	*/
	  
	private int initConfiguration( )
	  {
		int result = 99;
		
		try
		  {
			configuration = new AnnotationConfiguration().configure();
			
			configuration.addClass( BeanCompany.class);
			configuration.addClass( BeanDevice.class);
			
			configuration.addClass( BeanCommand.class);
			configuration.addClass( BeanTypeCommand.class);
			
			configuration.addClass( BeanCounter.class);
			configuration.addClass( BeanCounterLog.class);
			configuration.addClass( BeanCounterLogDetail.class );
			configuration.addClass( BeanType_Counter_Detail.class);
			configuration.addClass( BeanType_Counter_Master.class);

			configuration.addClass( BeanTypeOS.class);
			
			
			result = 0;
		  }
		catch( Throwable  m )
		  {
			m.printStackTrace();
		  }
		
		return result;
	  }
	  
	  
	public Configuration getConfiguration(   )
	  {
		if ( configuration == null )
		  {
			initConfiguration();
		  }
		return configuration;
	  }
	  

}
